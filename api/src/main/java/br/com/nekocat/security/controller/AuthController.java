package br.com.nekocat.security.controller;

import br.com.nekocat.security.contract.auth.AuthInterface;
import br.com.nekocat.security.domain.user.request.LoginRequest;
import br.com.nekocat.security.domain.user.request.RegisterRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthInterface authInterface;

    private final Logger log = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request, @RequestHeader Map<String, String> headers) {
        return authInterface.login(request);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestPart RegisterRequest request, @RequestPart MultipartFile pic) {
        request.setPicFile(pic);
        return authInterface.register(request);
    }

    @PostMapping(value = "/test")
    public ResponseEntity<?> test(@RequestPart RegisterRequest request, @RequestPart MultipartFile pic) {
        System.out.println();
        return ResponseEntity.ok("get /accounts");
    }
}
