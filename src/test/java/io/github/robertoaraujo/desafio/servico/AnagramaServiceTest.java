package io.github.robertoaraujo.desafio.servico;

import io.github.robertoaraujo.desafio.infra.dto.request.CriarAnagramaRequestDto;
import io.github.robertoaraujo.desafio.infra.dto.response.CriarAnagramaResponseDto;
import io.github.robertoaraujo.desafio.service.AnagramaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AnagramaServiceTest {

    private final AnagramaService service = new AnagramaService();

    @Test
    void deveGerarAnagramasValidos_paraTresLetras() {
        CriarAnagramaRequestDto dto = new CriarAnagramaRequestDto("a,b,c");

        List<CriarAnagramaResponseDto> resultado = service.criarAnagrama(dto);

        Assertions.assertEquals(6, resultado.size());
        List<String> esperados = List.of("abc", "acb", "bac", "bca", "cab", "cba");

        List<String> anagramas = resultado.stream().map(CriarAnagramaResponseDto::anagrama).toList();
        Assertions.assertTrue(anagramas.containsAll(esperados));
    }

    @Test
    void deveLancarExcecao_paraEntradaComUmaLetra() {
        CriarAnagramaRequestDto dto = new CriarAnagramaRequestDto("z");
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () ->
                service.criarAnagrama(dto));
        Assertions.assertEquals("o campo anagrama deve conter pelo menos 2 letras", ex.getMessage());
    }

    @Test
    void deveLancarExcecao_paraEntradaComNumeros() {
        CriarAnagramaRequestDto dto = new CriarAnagramaRequestDto("a,b,1");

        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () ->
                service.criarAnagrama(dto));

        Assertions.assertEquals("Todos os caracteres devem ser letras (com ou sem acento), sem números ou símbolos",
                ex.getMessage());
    }

    @Test
    void deveLancarExcecao_paraEntradaVazia() {
        CriarAnagramaRequestDto dto = new CriarAnagramaRequestDto("");

        IllegalArgumentException ex = Assertions
                .assertThrows(IllegalArgumentException.class, () -> service.criarAnagrama(dto));

        Assertions.assertEquals("A entrada não pode ser vazia", ex.getMessage());
    }
}
