package com.guigs.EntradaAtletas.controller;

import com.guigs.EntradaAtletas.dto.AtletaCreateDTO;
import com.guigs.EntradaAtletas.dto.AtletaDTO;
import com.guigs.EntradaAtletas.service.AtletaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atletas")
@CrossOrigin(origins = "*")
public class AtletaController {

    @Autowired
    private AtletaService atletaService;

    @PostMapping
    public ResponseEntity<AtletaDTO> criarAtleta(@Valid @RequestBody AtletaCreateDTO dto) {
        AtletaDTO atleta = atletaService.criarAtleta(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(atleta);
    }

    @GetMapping
    public ResponseEntity<List<AtletaDTO>> listarTodos(
            @RequestParam(required = false) String busca,
            @RequestParam(required = false) Boolean presente) {
        List<AtletaDTO> atletas = atletaService.buscarComFiltros(busca, presente);
        return ResponseEntity.ok(atletas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtletaDTO> buscarPorId(@PathVariable Long id) {
        AtletaDTO atleta = atletaService.buscarPorId(id);
        return ResponseEntity.ok(atleta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtletaDTO> atualizarAtleta(
            @PathVariable Long id,
            @Valid @RequestBody AtletaCreateDTO dto) {
        AtletaDTO atleta = atletaService.atualizarAtleta(id, dto);
        return ResponseEntity.ok(atleta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAtleta(@PathVariable Long id) {
        atletaService.deletarAtleta(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/presenca")
    public ResponseEntity<AtletaDTO> marcarPresenca(@PathVariable Long id) {
        AtletaDTO atleta = atletaService.marcarPresenca(id);
        return ResponseEntity.ok(atleta);
    }

    @DeleteMapping("/limpar")
    public ResponseEntity<Void> deletarTodos() {
        atletaService.deletarTodos();
        return ResponseEntity.noContent().build();
    }
}
