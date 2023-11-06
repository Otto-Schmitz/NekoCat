package br.com.nekocat.security.contract.user;

import br.com.nekocat.security.domain.user.Users;

public interface UserAuthenticationInterface {
    Users get();
//    Users getByToken(UsernamePasswordAuthenticationToken data);
}
