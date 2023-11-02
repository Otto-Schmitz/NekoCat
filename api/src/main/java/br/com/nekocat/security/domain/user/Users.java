package br.com.nekocat.security.domain.user;

import br.com.nekocat.security.domain.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class Users {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;
    private String userName;
    private String email;
    private String password;
    private String profilePic;
    private LocalDate dateBirth;

    @Builder.Default
    private boolean active = true;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }
}
