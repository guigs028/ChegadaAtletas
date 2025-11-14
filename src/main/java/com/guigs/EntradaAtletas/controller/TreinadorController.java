package com.guigs.EntradaAtletas.controller;

import com.guigs.EntradaAtletas.dto.TreinadorCreateDTO;
import com.guigs.EntradaAtletas.dto.TreinadorDTO;
import com.guigs.EntradaAtletas.service.TreinadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/treinadores")
@CrossOrigin(origins = "*")
public class TreinadorController {

    @Autowired
    private TreinadorService treinadorService;

    @PostMapping
    public ResponseEntity<TreinadorDTO> criarTreinador(@Valid @RequestBody TreinadorCreateDTO dto) {
        TreinadorDTO treinador = treinadorService.criarTreinador(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(treinador);
    }

    @GetMapping
    public ResponseEntity<List<TreinadorDTO>> listarTodos() {
        List<TreinadorDTO> treinadores = treinadorService.listarTodos();
        return ResponseEntity.ok(treinadores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreinadorDTO> buscarPorId(@PathVariable Long id) {
        TreinadorDTO treinador = treinadorService.buscarPorId(id);
        return ResponseEntity.ok(treinador);
    }

    @PatchMapping("/{id}/presenca")
    public ResponseEntity<TreinadorDTO> marcarPresenca(@PathVariable Long id) {
        TreinadorDTO treinador = treinadorService.marcarPresenca(id);
        return ResponseEntity.ok(treinador);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<TreinadorDTO>> buscarPorNome(@RequestParam String nome) {
        List<TreinadorDTO> treinadores = treinadorService.buscarPorNome(nome);
        return ResponseEntity.ok(treinadores);
    }
}

