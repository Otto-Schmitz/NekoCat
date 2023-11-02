package br.com.nekocat.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessage {

    INVALID_EMAIL(Text.INVALID_EMAIL),
    BLANK_EMAIL(Text.BLANK_EMAIL),
    BLACK_PASSWORD(Text.BLANK_PASSWORD),
    USER_NOT_AUTHENTICATED(Text.USER_NOT_AUTHENTICATED),
    USER_NOT_FOUND(Text.USER_NOT_FOUND);

    public static class Text {
        public static final String INVALID_EMAIL = "Invalid email";
        public static final String BLANK_EMAIL = "Email field cannot be empty";
        public static final String BLANK_PASSWORD = "Password field cannot be empty";
        public static final String USER_NOT_AUTHENTICATED = "User does not exist or is not authenticated";
        public static final String USER_NOT_FOUND = "User not found";

    }

    private final String message;
}
