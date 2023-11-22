package br.com.nekocat.security.service;

import br.com.nekocat.error.ErrorMessage;
import br.com.nekocat.security.contract.auth.AuthInterface;
import br.com.nekocat.security.contract.token.TokenInterface;
import br.com.nekocat.security.contract.user.UserAuthenticationInterface;
import br.com.nekocat.security.contract.user.UserInterface;
import br.com.nekocat.security.domain.role.mapper.RoleMapper;
import br.com.nekocat.security.domain.user.Users;
import br.com.nekocat.security.domain.user.mapper.UserMapper;
import br.com.nekocat.security.domain.user.request.AuthRequest;
import br.com.nekocat.security.domain.user.request.RegisterRequest;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private UserAuthenticationInterface userAuthenticationInterface;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Logger log = getLogger(AuthService.class);


    @Override
    public ResponseEntity<?> login(AuthRequest request) {
        try {
            return performLogin(request);
        } catch (RuntimeException e) {
            log.error(String.valueOf(e.fillInStackTrace()));
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    private ResponseEntity<?> performLogin(AuthRequest request) {
        UsernamePasswordAuthenticationToken data = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        Authentication authentication = this.authManager.authenticate(data);

        String token = tokenInterface.generateToken(authentication);

        Long id = tokenInterface.getUserId(token);
        Users user = userInterface.getById(id);

        return ResponseEntity.ok(UserMapper.toTokenDto(token, "Bearer", user));
    }

    @Override
    public ResponseEntity<?> register(RegisterRequest request) {
        try {
            return performRegister(request);
        } catch (DataIntegrityViolationException | IOException | URISyntaxException e) {
            log.error(String.valueOf(e.fillInStackTrace()));
            return e instanceof DataIntegrityViolationException
                    ?ResponseEntity.internalServerError().body(ErrorMessage.Text.INVALID_EMAIL)
                    :ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @Transactional
    private ResponseEntity<?> performRegister(RegisterRequest request) throws IOException, URISyntaxException {
        Users user = UserMapper.toUser(request, passwordEncoder.encode(request.getPassword()));
        user.setRoles(RoleMapper.defaultRoleList(user));

        userInterface.save(user);

        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }

    @Override
    public ResponseEntity<?> delete() {
        try {
            return performDelete();
        } catch (RuntimeException e) {
            log.error(String.valueOf(e.fillInStackTrace()));
            return ResponseEntity.badRequest().body(ErrorMessage.Text.USER_NOT_AUTHENTICATED);
        }
    }

    @Transactional
    private ResponseEntity<?> performDelete() {
        userInterface.delete(userAuthenticationInterface.get());

        return ResponseEntity.ok().build();
    }
}
