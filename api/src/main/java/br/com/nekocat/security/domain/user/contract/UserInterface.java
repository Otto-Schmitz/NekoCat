package br.com.nekocat.security.domain.user.contract;

import br.com.nekocat.security.domain.user.response.UserResponse;

import java.util.List;

public interface UserInterface {
    List<UserResponse> getAll();
}
