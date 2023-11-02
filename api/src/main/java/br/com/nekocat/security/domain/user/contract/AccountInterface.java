package br.com.nekocat.security.domain.user.contract;

import br.com.nekocat.security.domain.user.request.LoginRequest;
import org.springframework.http.ResponseEntity;

public interface AccountInterface {
    ResponseEntity<?> login(LoginRequest request);
}
