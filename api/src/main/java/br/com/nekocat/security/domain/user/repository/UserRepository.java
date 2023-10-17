package br.com.nekocat.security.domain.user.repository;

import br.com.nekocat.security.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
