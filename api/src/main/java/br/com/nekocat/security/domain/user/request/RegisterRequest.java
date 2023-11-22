package br.com.nekocat.security.domain.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

import static br.com.nekocat.security.error.ErrorMessage.Text.*;

@Data
public class RegisterRequest {
    @NotBlank(message = BLANK_NAME)
    private String name;

    @NotBlank(message = BLANK_USERNAME)
    @Length(min = 1, message = MINIMUM_LENGTH_USERNAME)
    @Length(max = 50, message = MAXiMUM_LENGTH_USERNAME)
    private String userName;

    @NotBlank(message = BLANK_EMAIL)
    @Email(message = INVALID_EMAIL)
    private String email;

    @NotNull(message = NULL_BIRTH_DATE)
    private LocalDate birthDate;

    @NotBlank(message = BLANK_PASSWORD)
    @Length(min = 6, message = MINIMUM_LENGTH_PASSWORD)
    private String password;

//    @NotNull(message = NULL_PROFILE_PIC)
    @Nullable
    private MultipartFile picFile;
}
