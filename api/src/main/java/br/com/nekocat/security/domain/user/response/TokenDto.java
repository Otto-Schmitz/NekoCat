package br.com.nekocat.security.domain.user.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Data
@Getter
public class TokenDto {
    private final String token;
    private final String type;
    private final UserResponse user;
}
