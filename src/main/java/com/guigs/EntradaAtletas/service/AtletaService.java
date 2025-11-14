package com.guigs.EntradaAtletas.service;

import com.guigs.EntradaAtletas.dto.AtletaCreateDTO;
import com.guigs.EntradaAtletas.dto.AtletaDTO;
import com.guigs.EntradaAtletas.dto.TreinadorDTO;
import com.guigs.EntradaAtletas.entity.Atleta;
import com.guigs.EntradaAtletas.entity.Treinador;
import com.guigs.EntradaAtletas.repository.AtletaRepository;
import com.guigs.EntradaAtletas.repository.TreinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtletaService {

    @Autowired
    private AtletaRepository atletaRepository;

    @Autowired
    private TreinadorRepository treinadorRepository;

    @Transactional
    public AtletaDTO criarAtleta(AtletaCreateDTO dto) {
        // Validar e buscar treinadores
        List<Treinador> treinadores = treinadorRepository.findAllById(dto.getTreinadoresIds());

        if (treinadores.size() != dto.getTreinadoresIds().size()) {
            throw new RuntimeException("Um ou mais treinadores não encontrados");
        }

        Atleta atleta = new Atleta();
        atleta.setNome(dto.getNome());
        atleta.setNumeroLuta(dto.getNumeroLuta());
        atleta.setCor(dto.getCor());
        atleta.setEquipe(dto.getEquipe());
        atleta.setPresente(false);
        atleta.setTreinadores(treinadores);

        atleta = atletaRepository.save(atleta);
        return converterParaDTO(atleta);
    }

    public List<AtletaDTO> listarTodos() {
        return atletaRepository.findAllByOrderByNumeroLutaAscIdAsc().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public AtletaDTO buscarPorId(Long id) {
        Atleta atleta = atletaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atleta não encontrado"));
        return converterParaDTO(atleta);
    }

    @Transactional
    public AtletaDTO marcarPresenca(Long id) {
        Atleta atleta = atletaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atleta não encontrado"));
        atleta.setPresente(!atleta.isPresente());
        atleta = atletaRepository.save(atleta);
        return converterParaDTO(atleta);
    }

    public List<AtletaDTO> buscarComFiltros(String busca, Boolean presente) {
        List<Atleta> atletas;

        if (busca != null && !busca.trim().isEmpty()) {
            atletas = atletaRepository.findByNomeOrTreinadorNome(busca);
        } else if (presente != null) {
            atletas = atletaRepository.findByPresenteOrderByNumeroLutaAscIdAsc(presente);
        } else {
            atletas = atletaRepository.findAllByOrderByNumeroLutaAscIdAsc();
        }

        // Filtrar por presença se especificado e há busca
        if (presente != null && busca != null && !busca.trim().isEmpty()) {
            atletas = atletas.stream()
                    .filter(a -> a.isPresente() == presente)
                    .collect(Collectors.toList());
        }

        // GARANTIR ordenação por numeroLuta e ID (para manter ordem estável)
        return atletas.stream()
                .sorted(Comparator.comparing(Atleta::getNumeroLuta)
                        .thenComparing(Atleta::getId))
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletarAtleta(Long id) {
        if (!atletaRepository.existsById(id)) {
            throw new RuntimeException("Atleta não encontrado");
        }
        atletaRepository.deleteById(id);
    }

    @Transactional
    public void deletarTodos() {
        atletaRepository.deleteAll();
    }

    @Transactional
    public AtletaDTO atualizarAtleta(Long id, AtletaCreateDTO dto) {
        Atleta atleta = atletaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atleta não encontrado"));

        // Validar e buscar treinadores
        List<Treinador> treinadores = treinadorRepository.findAllById(dto.getTreinadoresIds());

        if (treinadores.size() != dto.getTreinadoresIds().size()) {
            throw new RuntimeException("Um ou mais treinadores não encontrados");
        }

        atleta.setNome(dto.getNome());
        atleta.setNumeroLuta(dto.getNumeroLuta());
        atleta.setCor(dto.getCor());
        atleta.setEquipe(dto.getEquipe());
        atleta.setTreinadores(treinadores);

        atleta = atletaRepository.save(atleta);
        return converterParaDTO(atleta);
    }

    private AtletaDTO converterParaDTO(Atleta atleta) {
        List<TreinadorDTO> treinadoresDTO = atleta.getTreinadores().stream()
                .map(t -> new TreinadorDTO(
                        t.getId(),
                        t.getNome(),
                        t.getEquipe(),
                        t.isPresente()
                ))
                .collect(Collectors.toList());

        return new AtletaDTO(
                atleta.getId(),
                atleta.getNome(),
                atleta.getNumeroLuta(),
                atleta.getCor(),
                atleta.getEquipe(),
                atleta.isPresente(),
                treinadoresDTO
        );
    }
}
