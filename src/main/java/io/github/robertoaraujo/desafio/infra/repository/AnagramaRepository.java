package io.github.robertoaraujo.desafio.infra.repository;

import io.github.robertoaraujo.desafio.infra.model.Anagrama;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnagramaRepository extends JpaRepository<Anagrama, Long> {

}
