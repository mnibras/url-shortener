package com.example.urlshortener.domain.model;

import java.io.Serializable;

public record UserDto(Long id, String name) implements Serializable {
}