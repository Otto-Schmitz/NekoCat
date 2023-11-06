package br.com.nekocat.security.contract.token;

import org.springframework.security.core.Authentication;

public interface TokenInterface {
    String generateToken(Authentication authentication);

    Long getUserId(String token);

    boolean isValid(String token);


}
