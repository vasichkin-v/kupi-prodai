package com.example.kupiprodai.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER("user"),
    ADMINISTRATOR("administrator");

    Role(String name) {
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
