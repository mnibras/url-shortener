package com.example.urlshortener.domain.model;

public record CreateShortUrlCommand(
        String originalUrl,
        Boolean isPrivate,
        Integer expirationInDays,
        Long userId
) {
}
