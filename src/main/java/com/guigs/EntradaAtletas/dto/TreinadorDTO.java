package com.guigs.EntradaAtletas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TreinadorDTO {
    private Long id;
    private String nome;
    private String equipe;
    private boolean presente;
}

