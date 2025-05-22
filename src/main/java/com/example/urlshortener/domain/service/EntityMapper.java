package com.example.urlshortener.domain.service;

import com.example.urlshortener.domain.entity.ShortUrl;
import com.example.urlshortener.domain.entity.User;
import com.example.urlshortener.domain.model.ShortUrlDto;
import com.example.urlshortener.domain.model.UserDto;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {

    public ShortUrlDto toShortUrlDto(ShortUrl shortUrl) {
        UserDto userDto = null;
        if (shortUrl.getCreatedBy() != null) {
            userDto = toUserDto(shortUrl.getCreatedBy());
        }

        return new ShortUrlDto(shortUrl.getId(), shortUrl.getShortKey(), shortUrl.getOriginalUrl(), shortUrl.getIsPrivate(),
                shortUrl.getExpiresAt(), userDto, shortUrl.getClickCount(), shortUrl.getCreatedAt());
    }

    public UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getName());
    }
}
