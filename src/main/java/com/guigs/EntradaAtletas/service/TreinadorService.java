package com.guigs.EntradaAtletas.service;

import com.guigs.EntradaAtletas.dto.TreinadorCreateDTO;
import com.guigs.EntradaAtletas.dto.TreinadorDTO;
import com.guigs.EntradaAtletas.entity.Treinador;
import com.guigs.EntradaAtletas.repository.TreinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreinadorService {

    @Autowired
    private TreinadorRepository treinadorRepository;

    @Transactional
    public TreinadorDTO criarTreinador(TreinadorCreateDTO dto) {
        Treinador treinador = new Treinador();
        treinador.setNome(dto.getNome());
        treinador.setEquipe(dto.getEquipe());
        treinador.setPresente(false);

        treinador = treinadorRepository.save(treinador);
        return converterParaDTO(treinador);
    }

    public List<TreinadorDTO> listarTodos() {
        return treinadorRepository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public TreinadorDTO buscarPorId(Long id) {
        Treinador treinador = treinadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Treinador não encontrado"));
        return converterParaDTO(treinador);
    }

    @Transactional
    public TreinadorDTO marcarPresenca(Long id) {
        Treinador treinador = treinadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Treinador não encontrado"));
        treinador.setPresente(!treinador.isPresente());
        treinador = treinadorRepository.save(treinador);
        return converterParaDTO(treinador);
    }

    public List<TreinadorDTO> buscarPorNome(String nome) {
        return treinadorRepository.findByNomeContainingIgnoreCase(nome).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletarTreinador(Long id) {
        if (!treinadorRepository.existsById(id)) {
            throw new RuntimeException("Treinador não encontrado");
        }
        treinadorRepository.deleteById(id);
    }

    @Transactional
    public void deletarTodos() {
        treinadorRepository.deleteAll();
    }

    private TreinadorDTO converterParaDTO(Treinador treinador) {
        return new TreinadorDTO(
                treinador.getId(),
                treinador.getNome(),
                treinador.getEquipe(),
                treinador.isPresente()
        );
    }
}
