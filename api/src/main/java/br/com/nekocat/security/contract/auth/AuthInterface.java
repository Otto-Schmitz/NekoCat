package br.com.nekocat.security.contract.auth;

import br.com.nekocat.security.domain.user.request.AuthRequest;
import br.com.nekocat.security.domain.user.request.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AuthInterface {
    ResponseEntity<?> login(AuthRequest request);

    ResponseEntity<?> register(RegisterRequest request);

    ResponseEntity<?> delete();
}
