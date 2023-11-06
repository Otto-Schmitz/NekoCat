package br.com.nekocat.security.service;

import br.com.nekocat.security.domain.user.Users;
import br.com.nekocat.security.contract.user.UserAuthenticationInterface;
import br.com.nekocat.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static br.com.nekocat.error.ErrorMessage.USER_NOT_AUTHENTICATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
public class UserAuthentication implements UserAuthenticationInterface {
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private AuthenticationManager authManager;

//    @Autowired
//    private TokenService tokenService;

    public Long getId() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        Users userSecurity = (Users) authentication.getPrincipal();

        return userSecurity.getId();
    }

    @Override
    public Users get() {
        return userRepository.findById(getId())
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, USER_NOT_AUTHENTICATED.getMessage()));
    }

//    @Override
//    public Users getByToken(UsernamePasswordAuthenticationToken data) {
//        Authentication authentication = authManager.authenticate(data);
//        String token = tokenService.generateToken(authentication);
//        Long id = tokenService.getUserId(token);
//
//        return userRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, USER_NOT_AUTHENTICATED.getMessage()));
//        return null;
//    }

//    public Long getId(UsernamePasswordAuthenticationToken data) {
//        Authentication authentication = authManager.authenticate(data);
//        Users user = (Users) authentication.getPrincipal();
//
//        return user.getId();
//    }
}
