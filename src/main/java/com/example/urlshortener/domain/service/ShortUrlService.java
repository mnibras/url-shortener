package com.example.urlshortener.domain.service;

import com.example.urlshortener.ApplicationProperties;
import com.example.urlshortener.domain.entity.ShortUrl;
import com.example.urlshortener.domain.model.CreateShortUrlCommand;
import com.example.urlshortener.domain.model.ShortUrlDto;
import com.example.urlshortener.domain.repository.ShortUrlRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@Transactional(readOnly = true)
public class ShortUrlService {

    private final ShortUrlRepository shortUrlRepository;
    private final EntityMapper entityMapper;
    private final ApplicationProperties properties;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int SHORT_KEY_LENGTH = 6;
    private static final SecureRandom RANDOM = new SecureRandom();

    public ShortUrlService(ShortUrlRepository shortUrlRepository,
                           EntityMapper entityMapper,
                           ApplicationProperties properties) {
        this.shortUrlRepository = shortUrlRepository;
        this.entityMapper = entityMapper;
        this.properties = properties;
    }

    public List<ShortUrlDto> findAllPublicShortUrls() {
        return shortUrlRepository.findPublicShortUrls()
                .stream().map(entityMapper::toShortUrlDto).toList();
    }

    @Transactional
    public ShortUrlDto createShortUrl(CreateShortUrlCommand cmd) {
        if (properties.validateOriginalUrl()) {
            boolean urlExists = UrlExistenceValidator.isUrlExists(cmd.originalUrl());
            if (!urlExists) {
                throw new RuntimeException("Invalid URL " + cmd.originalUrl());
            }
        }
        var shortKey = generateUniqueShortKey();
        var shortUrl = new ShortUrl();
        shortUrl.setOriginalUrl(cmd.originalUrl());
        shortUrl.setShortKey(shortKey);
        shortUrl.setCreatedBy(null);
        shortUrl.setIsPrivate(false);
        shortUrl.setClickCount(0L);
        shortUrl.setExpiresAt(Instant.now().plus(properties.defaultExpiryInDays(), DAYS));
        shortUrl.setCreatedAt(Instant.now());
        shortUrlRepository.save(shortUrl);
        return entityMapper.toShortUrlDto(shortUrl);
    }

    @Transactional
    public Optional<ShortUrlDto> accessShortUrl(String shortKey) {
        Optional<ShortUrl> shortUrlOptional = shortUrlRepository.findByShortKey(shortKey);
        if (shortUrlOptional.isEmpty()) {
            return Optional.empty();
        }
        ShortUrl shortUrl = shortUrlOptional.get();
        if (shortUrl.getExpiresAt() != null && shortUrl.getExpiresAt().isBefore(Instant.now())) {
            return Optional.empty();
        }
        shortUrl.setClickCount(shortUrl.getClickCount() + 1);
        shortUrlRepository.save(shortUrl);
        return shortUrlOptional.map(entityMapper::toShortUrlDto);
    }

    private String generateUniqueShortKey() {
        String shortKey;
        do {
            shortKey = generateRandomShortKey();
        } while (shortUrlRepository.existsByShortKey(shortKey));
        return shortKey;
    }

    private static String generateRandomShortKey() {
        StringBuilder sb = new StringBuilder(SHORT_KEY_LENGTH);
        for (int i = 0; i < SHORT_KEY_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}