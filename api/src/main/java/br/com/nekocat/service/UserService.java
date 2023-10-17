package br.com.nekocat.service;

import br.com.nekocat.security.domain.user.contract.UserInterface;
import br.com.nekocat.security.domain.user.mapper.UserMapper;
import br.com.nekocat.security.domain.user.repository.UserRepository;
import br.com.nekocat.security.domain.user.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserInterface {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }
}
