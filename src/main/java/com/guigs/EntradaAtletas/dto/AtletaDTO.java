package com.guigs.EntradaAtletas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtletaDTO {
    private Long id;
    private String nome;
    private Integer numeroLuta;
    private String cor;
    private String equipe;
    private boolean presente;
    private List<TreinadorDTO> treinadores;
}


