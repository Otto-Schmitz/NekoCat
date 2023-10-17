package br.com.nekocat.security.domain.user;

import br.com.nekocat.security.domain.Role;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

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
}
