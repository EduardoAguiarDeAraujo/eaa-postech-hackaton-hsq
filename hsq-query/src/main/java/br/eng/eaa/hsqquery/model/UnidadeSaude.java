package br.eng.eaa.hsqquery.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "unidades_saude")
public class UnidadeSaude {

    @Id
    private Long id;

    @Column(nullable = false, unique = true, length = 7)
    private String cnes;

    @Column(nullable = false)
    private String nomeFantasia;

    @Column(nullable = false)
    private String razaoSocial;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    @ManyToMany
    @JoinTable(
            name = "unidade_especialidade",
            joinColumns = @JoinColumn(name = "unidade_id"),
            inverseJoinColumns = @JoinColumn(name = "especialidade_id")
    )
    private Set<Especialidade> especialidades = new HashSet<>();

    public UnidadeSaude() {}

    public UnidadeSaude(Long id, String cnes, String nomeFantasia, String razaoSocial, Endereco endereco, Set<Especialidade> especialidades) {
        this.id = id;
        this.cnes = cnes;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.endereco = endereco;
        this.especialidades = especialidades;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnes() {
        return cnes;
    }

    public void setCnes(String cnes) {
        this.cnes = cnes;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Set<Especialidade> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(Set<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }
}
