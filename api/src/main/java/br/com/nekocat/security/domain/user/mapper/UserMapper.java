package br.com.nekocat.security.domain.user.mapper;

import br.com.nekocat.security.domain.user.UserSecurity;
import br.com.nekocat.security.domain.user.Users;
import br.com.nekocat.security.domain.user.response.TokenDto;
import br.com.nekocat.security.domain.user.response.UserResponse;

public class UserMapper {
    public static UserResponse toDto(Users entity) {
        return UserResponse.builder()
                .id(entity.getId())
                .userName(entity.getUserName())
                .profilePic(entity.getProfilePic())
                .roles(entity.getRoles())
                .build();
    }

    public static UserSecurity toScurity(Users entity) {
        return new UserSecurity(entity);
    }

    public static TokenDto toTokenDto(String token, String type, Users user) {
        return TokenDto.builder()
                .token(token)
                .type(type)
                .user(toDto(user))
                .build();
    }
}
