package br.com.nekocat.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessage {

    INVALID_EMAIL(Text.INVALID_EMAIL),
    BLANK_EMAIL(Text.BLANK_EMAIL),
    BLANK_PASSWORD(Text.BLANK_PASSWORD),
    USER_NOT_AUTHENTICATED(Text.USER_NOT_AUTHENTICATED),
    USER_NOT_FOUND(Text.USER_NOT_FOUND),
    BLANK_NAME(Text.BLANK_NAME),
    BLANK_USERNAME(Text.BLANK_USERNAME),
    MINIMUM_LENGTH_PASSWORD(Text.MINIMUM_LENGTH_PASSWORD),
    BLANK_PROFILE_PIC(Text.NULL_PROFILE_PIC),
    MAXiMUM_LENGTH_USERNAME(Text.MINIMUM_LENGTH_USERNAME),
    MINIMUM_LENGTH_USERNAME(Text.MINIMUM_LENGTH_USERNAME),
    NULL_BIRTH_DATE(Text.NULL_BIRTH_DATE);

    public static class Text {

        //email

        public static final String INVALID_EMAIL = "Invalid email";
        public static final String BLANK_EMAIL = "Email field cannot be empty";

        //password

        public static final String BLANK_PASSWORD = "Password field cannot be empty";
        public static final String MINIMUM_LENGTH_PASSWORD = "Password must have at least 6 characters";

        //user

        public static final String USER_NOT_AUTHENTICATED = "User does not exist or is not authenticated";
        public static final String USER_NOT_FOUND = "User not found";

        //name&username

        public static final String INVALID_NAME = "This name is invalid";
        public static final String BLANK_NAME = "Name cannot be blank";
        public static final String MINIMUM_LENGTH_NAME = "Name must have at least 1 characters";
        public static final String MAXiMUM_LENGTH_NAME = "Name must be less than 255 characters";


        public static final String INVALID_USERNAME = "This name is invalid";
        public static final String BLANK_USERNAME = "Username cannot be blank";
        public static final String MINIMUM_LENGTH_USERNAME = "Username must have at least 1 characters";
        public static final String MAXiMUM_LENGTH_USERNAME = "Username must be less than 50 characters";

        //profilepic

        public static final String NULL_PROFILE_PIC = "Profile Picture cannot be null";
//        public static final String MAXIMUM_LENGTH_PROFILE_PIC = "Profile Picture must be less than 512 characters";

        //birthdate

        public static final String NULL_BIRTH_DATE = "Birth Date cannot be null";
    }

    private final String message;
}
