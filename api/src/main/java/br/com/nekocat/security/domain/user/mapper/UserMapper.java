package br.com.nekocat.security.domain.user.mapper;

import br.com.nekocat.security.domain.user.User;
import br.com.nekocat.security.domain.user.response.UserResponse;

public class UserMapper {
    public static UserResponse toDto(User entity) {
        return UserResponse.builder()
                .id(entity.getId())
                .userName(entity.getUserName())
                .profilePic(entity.getProfilePic())
                .build();
    }
}
