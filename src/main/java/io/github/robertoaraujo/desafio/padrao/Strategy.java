package io.github.robertoaraujo.desafio.padrao;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Strategy {

    private final Environment environment;

    public Strategy(Environment environment) {
        this.environment = environment;
    }

    public String getActiveDatabaseProfile() {
        List<String> profiles = Arrays.asList(environment.getActiveProfiles());

        if (profiles.contains("h2")) {
            return "H2";
        } else if (profiles.contains("postgres")) {
            return "Postgres";
        }

        return "Desconhecido";
    }

    public void executar() {
        String dbProfile = getActiveDatabaseProfile();

        switch (dbProfile) {
            case "H2" -> System.out.println("Usando banco de dados H2 em memória.");
            case "Postgres" -> System.out.println("Usando banco de dados PostgreSQL.");
            default -> System.out.println("Nenhum perfil de banco reconhecido.");
        }
    }
}