package io.github.robertoaraujo.desafio.infra.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "anagrama")
@NoArgsConstructor
public class Anagrama {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_anagrama")
    @Getter
    private Long id;

    @Getter
    @Setter
    private String anagrama;

}
