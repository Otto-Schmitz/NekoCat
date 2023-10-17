package br.com.nekocat.security.domain;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = "id") @ToString(of = "id")
public class Role {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User usuario;
}
