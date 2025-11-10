package com.guigs.EntradaAtletas.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Atleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String equipe;
    private String cor;
    private boolean presente;

    @ManyToMany
    private List<Treinador> treinadores;

    @JoinTable(
        name = "atleta_treinador",
        joinColumns = @JoinColumn(name = "atleta_id"),
        inverseJoinColumns = @JoinColumn(name = "treinador_id")
    )

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

    public List<Treinador> getTreinadores() {
        return treinadores;
    }

    public void setTreinadores(List<Treinador> treinadores) {
        this.treinadores = treinadores;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }

    public Atleta() {}

    public Atleta(List<Treinador> treinadores, String nome, String equipe, String cor, boolean presente) {
        this.treinadores = treinadores;
        this.nome = nome;
        this.equipe = equipe;
        this.cor = cor;
        this.presente = presente;
    }
}
