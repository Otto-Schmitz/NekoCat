package br.com.nekocat.security.service;

import br.com.nekocat.security.domain.user.Users;
import br.com.nekocat.security.contract.user.UserInterface;
import br.com.nekocat.security.domain.user.mapper.UserMapper;
import br.com.nekocat.security.repository.UserRepository;
import br.com.nekocat.security.domain.user.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.nekocat.error.ErrorMessage.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
public class UserService implements UserInterface {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(Users user) {
        userRepository.save(user);
    }

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Users getById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, USER_NOT_FOUND.getMessage()));
    }

    @Override
    public Users getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, USER_NOT_FOUND.getMessage()));
    }
}
