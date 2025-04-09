package io.github.robertoaraujo.desafio;

import io.github.robertoaraujo.desafio.controller.AnagramaControllerTest;
import io.github.robertoaraujo.desafio.servico.AnagramaServiceTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@Suite
@SelectClasses({AnagramaControllerTest.class, AnagramaServiceTest.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DesafioApplicationTests {

}