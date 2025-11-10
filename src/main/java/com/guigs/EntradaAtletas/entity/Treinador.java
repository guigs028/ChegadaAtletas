package com.guigs.EntradaAtletas.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Treinador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String equipe;
    private boolean presente;

    @ManyToMany(mappedBy = "treinadores")
    private List<Atleta> atletas;

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public List<Atleta> getAtletas() {
        return atletas;
    }

    public void setAtletas(List<Atleta> atletas) {
        this.atletas = atletas;
    }

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }

    public Treinador() {}

    public Treinador(String nome, String equipe, boolean presente, List<Atleta> atletas) {
        this.nome = nome;
        this.equipe = equipe;
        this.presente = presente;
        this.atletas = atletas;
    }
}
