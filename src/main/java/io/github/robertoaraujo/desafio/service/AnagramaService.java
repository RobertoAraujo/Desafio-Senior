package io.github.robertoaraujo.desafio.service;

import io.github.robertoaraujo.desafio.infra.dto.request.CriarAnagramaRequestDto;
import io.github.robertoaraujo.desafio.infra.dto.response.CriarAnagramaResponseDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class AnagramaService {

    public List<CriarAnagramaResponseDto> criarAnagrama(@Valid CriarAnagramaRequestDto request) {
        if (request.anagrama() == null || request.anagrama().isEmpty()) {
            throw new IllegalArgumentException("A entrada não pode ser vazia");
        }

        String entrada = request.anagrama();

        // Remove espaços e divide por vírgula
        String[] letras = entrada.replaceAll("\\s+", "").split(",");

        if (letras.length < 2) {
            throw new IllegalArgumentException("o campo anagrama deve conter pelo menos 2 letras");
        }

        // Verifica se todos os caracteres são letras
        for (String letra : letras) {
            if (!letra.matches("[a-zA-ZÀ-ÿ]+")) {
                throw new IllegalArgumentException("Todos os caracteres devem ser letras (com ou sem acento), sem números ou símbolos");
            }
        }
        // Junta as letras em uma única string: ex: "a,b,c" -> "abc"
        String combinacao = String.join("", letras);

        // Geração de anagramas
        Set<String> resultados = new LinkedHashSet<>();
        gerarAnagramas("", combinacao, resultados);

        return resultados.stream().map(CriarAnagramaResponseDto::new).toList();
    }

    private void gerarAnagramas(String prefixo, String restante, Set<String> resultado) {
        int n = restante.length();
        if (n == 0) {
            resultado.add(prefixo);
        } else {
            for (int i = 0; i < n; i++) {
                gerarAnagramas(prefixo + restante.charAt(i),
                        restante.substring(0, i) + restante.substring(i + 1, n), resultado);
            }
        }
    }
}
