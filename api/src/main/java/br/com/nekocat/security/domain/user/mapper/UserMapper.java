package br.com.nekocat.security.domain.user.mapper;

import br.com.nekocat.security.domain.user.Users;
import br.com.nekocat.security.domain.user.response.UserResponse;

public class UserMapper {
    public static UserResponse toDto(Users entity) {
        return UserResponse.builder()
                .id(entity.getId())
                .userName(entity.getUserName())
                .profilePic(entity.getProfilePic())
                .build();
    }
}
