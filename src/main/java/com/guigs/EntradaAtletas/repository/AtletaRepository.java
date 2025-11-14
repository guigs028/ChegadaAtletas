package com.guigs.EntradaAtletas.repository;

import com.guigs.EntradaAtletas.entity.Atleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AtletaRepository extends JpaRepository<Atleta, Long> {

    // Buscar todos ordenado por número da luta
    List<Atleta> findAllByOrderByNumeroLuta();

    // Buscar por nome (case insensitive) ordenado por número da luta
    List<Atleta> findByNomeContainingIgnoreCaseOrderByNumeroLuta(String nome);

    // Buscar por status de presença ordenado por número da luta
    List<Atleta> findByPresenteOrderByNumeroLuta(boolean presente);

    // Buscar atletas por nome do treinador
    @Query("SELECT a FROM Atleta a JOIN a.treinadores t WHERE LOWER(t.nome) LIKE LOWER(CONCAT('%', :nomeTreinador, '%')) ORDER BY a.numeroLuta")
    List<Atleta> findByTreinadorNome(@Param("nomeTreinador") String nomeTreinador);

    // Buscar por nome ou treinador
    @Query("SELECT DISTINCT a FROM Atleta a LEFT JOIN a.treinadores t WHERE " +
           "LOWER(a.nome) LIKE LOWER(CONCAT('%', :busca, '%')) OR " +
           "LOWER(t.nome) LIKE LOWER(CONCAT('%', :busca, '%')) ORDER BY a.numeroLuta")
    List<Atleta> findByNomeOrTreinadorNome(@Param("busca") String busca);
}
