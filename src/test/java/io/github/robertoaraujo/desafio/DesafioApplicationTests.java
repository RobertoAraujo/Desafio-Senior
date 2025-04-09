package io.github.robertoaraujo.desafio;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.robertoaraujo.desafio.controller.AnagramaController;
import io.github.robertoaraujo.desafio.infra.dto.request.CriarAnagramaRequestDto;
import io.github.robertoaraujo.desafio.infra.dto.response.CriarAnagramaResponseDto;
import io.github.robertoaraujo.desafio.service.AnagramaService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnagramaController.class)
class DesafioApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnagramaService anagramaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveRetornarListaDeAnagramas() throws Exception {
        String entrada = "a,b,c";
        CriarAnagramaRequestDto requestDto = new CriarAnagramaRequestDto(entrada);

        List<CriarAnagramaResponseDto> resposta = List.of(
                new CriarAnagramaResponseDto("abc"),
                new CriarAnagramaResponseDto("acb"),
                new CriarAnagramaResponseDto("bac"),
                new CriarAnagramaResponseDto("bca"),
                new CriarAnagramaResponseDto("cab"),
                new CriarAnagramaResponseDto("cba")
        );

        // Aqui, ao invés de usar o DTO diretamente, usamos any() para evitar problemas de igualdade
        Mockito.when(anagramaService.criarAnagrama(any(CriarAnagramaRequestDto.class)))
                .thenReturn(resposta);

        mockMvc.perform(post("/anagramas").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].anagrama", containsInAnyOrder("abc", "acb", "bac", "bca", "cab", "cba")));
    }

    @Test
    void deveRetornarErroQuandoEntradaForVazia() throws Exception {
        CriarAnagramaRequestDto requestDto = new CriarAnagramaRequestDto("");

        mockMvc.perform(post("/anagramas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(
                        Matchers.anyOf(
                                Matchers.is("[anagrama: O campo anagrama não pode ser nulo]"),
                                Matchers.is("[anagrama: A entrada deve conter apenas letras (com ou sem acento), separadas por vírgula]")
                        )
                ));
    }
}