package br.com.nekocat.security.domain.user.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserResponse {
    private Long id;
    private String userName;
    private String profilePic;
}
