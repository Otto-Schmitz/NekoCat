package br.com.nekocat.security.controller;

import br.com.nekocat.security.domain.user.contract.AccountInterface;
import br.com.nekocat.security.domain.user.request.LoginRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AccountInterface accountInterface;

    private Logger log = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request, @RequestHeader Map<String, String> headers) {
        headers.forEach((key, value) -> {
            log.info(String.format("Header '%s' = %s", key, value));
        });
        return accountInterface.login(request);
    }

    @GetMapping
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("get /accounts");
    }
}
