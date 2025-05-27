package com.example.urlshortener.domain.model;

public record CreateUserCommand(
        String email,
        String password,
        String name,
        Role role) {
}
