package br.com.nekocat.security.service;

import br.com.nekocat.security.contract.auth.AuthInterface;
import br.com.nekocat.security.contract.token.TokenInterface;
import br.com.nekocat.security.contract.user.UserInterface;
import br.com.nekocat.security.domain.user.Users;
import br.com.nekocat.security.domain.user.mapper.UserMapper;
import br.com.nekocat.security.domain.user.request.LoginRequest;
import br.com.nekocat.security.domain.user.request.RegisterRequest;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriBuilder;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.slf4j.LoggerFactory.getLogger;
@Service
public class AuthService implements AuthInterface {
    @Autowired
    private UserInterface userInterface;

    @Autowired
    private TokenInterface tokenInterface;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UriBuilder uriBuilder;

    private final Logger log = getLogger(AuthService.class);


    @Override
    public ResponseEntity<?> login(LoginRequest request) {
        try {
            return performLogin(request);
        } catch (RuntimeException e) {
            log.error(String.valueOf(e.fillInStackTrace()));
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    private ResponseEntity<?> performLogin(LoginRequest request) {
        UsernamePasswordAuthenticationToken data = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        Authentication authentication = this.authManager.authenticate(data);
        String token = tokenInterface.generateToken(authentication);
        Long id = tokenInterface.getUserId(token);
        Users user = userInterface.getById(id);

        return ResponseEntity.ok(UserMapper.toTokenDto(
                token, "Bearer", user
        ));
    }

    @Override
    public ResponseEntity<?> register(RegisterRequest request) {
        try {
            return performRegister(request);
        } catch (RuntimeException | IOException | URISyntaxException e) {
            log.error(String.valueOf(e.fillInStackTrace()));
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    private ResponseEntity<?> performRegister(RegisterRequest request) throws IOException, URISyntaxException {
        Users user = UserMapper.toUser(
                request, passwordEncoder.encode(request.getPassword())
        );
        userInterface.save(user);

        return ResponseEntity.created(
                uriBuilder.path("/auth/register")
                        .build()
                        .toURL()
                        .toURI()
        )
                .body(UserMapper.toDto(user));
    }
}
