package com.guigs.EntradaAtletas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtletaCreateDTO {
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotNull(message = "Número da luta é obrigatório")
    private Integer numeroLuta;

    @NotBlank(message = "Cor é obrigatória")
    private String cor;

    @NotBlank(message = "Equipe é obrigatória")
    private String equipe;

    @NotNull(message = "Treinadores são obrigatórios")
    @Size(min = 1, max = 2, message = "Deve haver entre 1 e 2 treinadores")
    private List<Long> treinadoresIds;
}

