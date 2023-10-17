package br.com.nekocat.security.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleType {

    USER(Name.USER),
    ADMIN(Name.ADMIN);

    public static class Name {
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }

    private final String role;
}
