package io.github.robertoaraujo.desafio.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.robertoaraujo.desafio.infra.dto.request.CriarAnagramaRequestDto;
import io.github.robertoaraujo.desafio.infra.dto.response.CriarAnagramaResponseDto;
import io.github.robertoaraujo.desafio.padrao.Strategy;
import io.github.robertoaraujo.desafio.service.AnagramaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AnagramaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AnagramaService anagramaService;

    @MockitoBean
    private Strategy strategy;

    @Autowired
    private ObjectMapper objectMapper;

    private CriarAnagramaRequestDto requestDto;
    private List<CriarAnagramaResponseDto> respostaEsperada;

    @BeforeEach
    void setUp() {
        String entrada = "a,b,c";
        requestDto = new CriarAnagramaRequestDto(entrada);

        respostaEsperada = List.of(
                new CriarAnagramaResponseDto("abc"),
                new CriarAnagramaResponseDto("bac"),
                new CriarAnagramaResponseDto("acb"),
                new CriarAnagramaResponseDto("bca"),
                new CriarAnagramaResponseDto("cab"),
                new CriarAnagramaResponseDto("cba"));
    }

    @Test
    void criarAnagramas_DeveRetornar200EListaDeAnagramas() throws Exception {
        when(anagramaService.criarAnagrama(any(CriarAnagramaRequestDto.class))).thenReturn(respostaEsperada);
        doNothing().when(strategy).executar(); // Mockando o método executar identico do Strategy

        mockMvc.perform(post("/anagramas").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk()).andExpect(jsonPath("$[*].anagrama").value(containsInAnyOrder(
                        "abc", "bac", "acb", "bca", "cab", "cba")));

        verify(strategy).executar();
        verify(anagramaService).criarAnagrama(any(CriarAnagramaRequestDto.class));
    }
}