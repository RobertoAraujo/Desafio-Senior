# Desafio Anagrama 🔄

Este projeto é uma API REST desenvolvida com **Spring Boot** que recebe uma entrada de letras separadas por vírgula e retorna todas as combinações possíveis (anagramas) dessas letras.
Um anagrama é uma palavra ou frase formada reorganizando as letras de outra palavra ou frase.
---
📌 Exemplo:
A palavra "abc" tem os seguintes anagramas:
➡️ "abc", "acb", "bac", "bca", "cab", "cba"
---

## 🔧 Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Banco de Dados H2 (em memória) e Postgres
- JUnit 5 (Testes)
- Mockito
- Maven

---

## 📦 Como rodar o projeto

### Pré-requisitos

- Java 21+
- Maven ...
- posman ou Insomnia
- Banco de Dados H2 ou Postgres
- IDE (IntelliJ, Eclipse, etc.)

### Passos

```bash
clonar o repositorio git clone git@github.com:RobertoAraujo/Desafio-Senior.git
acesso cd desafio-anagrama
```
---
Padrão de json
```json
{
  "anagrama": "a,b,c"
}
```

