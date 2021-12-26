package com.example.oauthtest.domain;

import lombok.Getter;

public enum Role {
    GUEST("ROLE_GUEST"),
    USER("ROLE_USER");

    private final String key;

    Role(String key) {
        this.key = key;
    }
    public String getKey() {
        return key;
    }
}
