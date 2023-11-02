package br.com.nekocat.security.service;

import br.com.nekocat.security.domain.user.Users;
import br.com.nekocat.security.domain.user.contract.AccountInterface;
import br.com.nekocat.security.domain.user.contract.UserAuthenticationInterface;
import br.com.nekocat.security.domain.user.contract.UserInterface;
import br.com.nekocat.security.domain.user.mapper.UserMapper;
import br.com.nekocat.security.domain.user.request.LoginRequest;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import static org.slf4j.LoggerFactory.getLogger;
@Service
public class AuthService implements AccountInterface {
    @Autowired
    private UserInterface userInterface;

    @Autowired
    private UserAuthenticationInterface userAuthenticationInterface;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authManager;

    private final Logger log =  getLogger(AuthService.class);


    @Override
    public ResponseEntity<?> login(LoginRequest request) {
        try {
            UsernamePasswordAuthenticationToken data = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            Authentication authentication = this.authManager.authenticate(data);
            String token = tokenService.generateToken(authentication);
            Long id = tokenService.getUserId(token);
            Users user = userInterface.getById(id);

            return ResponseEntity.ok(UserMapper.toTokenDto(
                    token, "Bearer", user
            ));

//            return ResponseEntity.ok(UserMapper.toScurity(userAuthenticationInterface.get()));
        } catch (RuntimeException e) {
            log.error(String.valueOf(e.fillInStackTrace()));
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
