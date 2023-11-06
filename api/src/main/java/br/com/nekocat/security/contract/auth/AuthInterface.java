package br.com.nekocat.security.contract.auth;

import br.com.nekocat.security.domain.user.request.LoginRequest;
import br.com.nekocat.security.domain.user.request.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AuthInterface {
    ResponseEntity<?> login(LoginRequest request);

    ResponseEntity<?> register(RegisterRequest request);
}
