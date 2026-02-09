package br.eaa.eng.security.model;

import br.eaa.eng.security.model.enums.PerfilName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="tb_perfil")
public class Perfil implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private PerfilName perfilName;

    @Override
    public String getAuthority() {
        return this.perfilName.toString();
    }

    public Perfil(UUID id, PerfilName perfilName) {
        this.id = id;
        this.perfilName = perfilName;
    }

    @Override
    public String toString() {
        return "Perfil{" +
                "id=" + id +
                ", perfilName=" + perfilName +
                '}';
    }
}
