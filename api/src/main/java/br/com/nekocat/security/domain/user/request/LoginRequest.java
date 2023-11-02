package br.com.nekocat.security.domain.user.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import static br.com.nekocat.error.ErrorMessage.Text.*;

@Data
public class LoginRequest {
    @Email(message = INVALID_EMAIL)
    @NotBlank(message = BLANK_EMAIL)
    private String email;

    @NotBlank(message = BLANK_PASSWORD)
    private String password;
}
