package br.com.nekocat.security.domain.user.response;

import br.com.nekocat.security.domain.Role;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UserResponse {
    private Long id;
    private String userName;
    private byte[] profilePic;
    private List<Role> roles;
}
