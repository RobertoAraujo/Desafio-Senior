package io.github.robertoaraujo.desafio.controller;

import io.github.robertoaraujo.desafio.infra.dto.request.CriarAnagramaRequestDto;
import io.github.robertoaraujo.desafio.infra.dto.response.CriarAnagramaResponseDto;
import io.github.robertoaraujo.desafio.padrao.Strategy;
import io.github.robertoaraujo.desafio.service.AnagramaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/anagramas")
public class AnagramaController {

    private final AnagramaService anagramaService;
    private final Strategy strategy;

    @Autowired
    public AnagramaController(AnagramaService anagramaService, Strategy strategy) {
        this.anagramaService = anagramaService;
        this.strategy = strategy;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CriarAnagramaResponseDto>> criarAnagramas(@Valid @RequestBody CriarAnagramaRequestDto request) {
        strategy.executar();
        return ResponseEntity.status(HttpStatus.OK).body(anagramaService.criarAnagrama(request));
    }
}
