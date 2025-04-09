package io.github.robertoaraujo.desafio.controller;

import io.github.robertoaraujo.desafio.infra.dto.request.CriarAnagramaRequestDto;
import io.github.robertoaraujo.desafio.infra.dto.response.CriarAnagramaResponseDto;
import io.github.robertoaraujo.desafio.service.AnagramaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AnagramaControllerTest {

    @Mock
    private AnagramaService service;

    @InjectMocks
    private AnagramaController controller;

    @Test
    void deveRetornar200ComListaDeAnagramas() {
        // criar dados de teste
        CriarAnagramaRequestDto request = new CriarAnagramaRequestDto("a,b");
        List<CriarAnagramaResponseDto> respostaSimulada = List.of(
                new CriarAnagramaResponseDto("ab"),
                new CriarAnagramaResponseDto("ba")
        );
        // mock do serviço
        Mockito.when(service.criarAnagrama(request)).thenReturn(respostaSimulada);

        // chamar o método do controlador
        ResponseEntity<List<CriarAnagramaResponseDto>> resposta = controller.criarAnagramas(request);

        // verificar o resultado dos testes
        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Assertions.assertEquals(2, resposta.getBody().size());
        Assertions.assertEquals("ab", resposta.getBody().get(0).anagrama());
    }
}
