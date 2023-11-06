package br.com.nekocat.security.contract.user;

import br.com.nekocat.security.domain.user.Users;
import br.com.nekocat.security.domain.user.response.UserResponse;

import java.util.List;

public interface UserInterface {
    void save(Users user);

    List<UserResponse> getAll();

    Users getById(long id);

    Users getByEmail(String email);
}
