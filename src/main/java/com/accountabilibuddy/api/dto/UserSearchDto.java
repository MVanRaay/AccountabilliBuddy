package com.accountabilibuddy.api.dto;

public record UserSearchDto(
        Long id,
        String firstName,
        String lastName,
        String email
) {}
