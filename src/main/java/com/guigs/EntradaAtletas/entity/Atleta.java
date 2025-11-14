package com.guigs.EntradaAtletas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Atleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer numeroLuta;
    private String cor;
    private String equipe;
    private boolean presente = false;

    @ManyToMany
    @JoinTable(
        name = "atleta_treinador",
        joinColumns = @JoinColumn(name = "atleta_id"),
        inverseJoinColumns = @JoinColumn(name = "treinador_id")
    )
    private List<Treinador> treinadores;
}
