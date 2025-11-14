package com.guigs.EntradaAtletas.repository;

import com.guigs.EntradaAtletas.entity.Treinador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TreinadorRepository extends JpaRepository<Treinador, Long> {

    // Buscar por nome (case insensitive)
    List<Treinador> findByNomeContainingIgnoreCase(String nome);

    // Buscar por equipe
    List<Treinador> findByEquipe(String equipe);
}

