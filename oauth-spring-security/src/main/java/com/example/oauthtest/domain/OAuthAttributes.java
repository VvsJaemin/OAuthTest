package com.example.oauthtest.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

public enum OAuthAttributes {
    GITHUB("github", (attributes)->{
        return new UserProfile(
                String.valueOf(attributes.get("id")),
                (String) attributes.get("name"),
                (String) attributes.get("email"),
                (String) attributes.get("avatar_url")
        );
    }),
    GOOGLE("google", (attributes)->{
        return new UserProfile(
                String.valueOf(attributes.get("sub")),
                (String) attributes.get("name"),
                (String) attributes.get("email"),
                (String) attributes.get("picture")
        );
    }),

    NAVER("naver", (attributes) -> {
        return new UserProfile(
                (String) attributes.get("id"),
                (String) attributes.get("name"),
                (String) attributes.get("email"),
                (String) attributes.get("profile_image")
        );
    });

    private final String registrationId;
    private final Function<Map<String, Object>, UserProfile> of;

    OAuthAttributes(String registrationId, Function<Map<String, Object>, UserProfile> of) {
        this.registrationId = registrationId;
        this.of = of;
    }

    public static UserProfile extract(String registrationId, Map<String, Object> attributes){
        return Arrays.stream(values())
                .filter(provider -> registrationId.equals(provider.registrationId))
                .findFirst()
                .orElseThrow(IllegalAccessError::new)
                .of.apply(attributes);
    }
}
