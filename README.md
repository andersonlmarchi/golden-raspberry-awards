
# Golden Raspberry Awards

Projeto de API RESTful para possibilitar a leitura da lista de indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards.


## Funcionalidades

- Ler arquivo CSV de filmes e inserir eles em H2 ao iniciar a aplicação
  - O arquivo CSV deverá ser colocado na pasta `src/main/resources/static/` e deverá estar organizado na forma de `year;title;studios;producers;winner`
- API Lista os produtores com maior intervalo entre dois prêmios consecutivos e os que obtiveram dois prêmios mais rápido


## Instalação

Para a execução deste projeto é necessário o downloads das seguintes ferramentas:
- Java 17, que pode ser baixado [aqui](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).
- IntelliJ, que pode ser baixado [aqui](https://www.jetbrains.com/pt-br/idea/download/).

## Rodando e testando localmente

Clone o projeto

```bash
  git clone https://github.com/andersonlmarchi/golden-raspberry-awards.git
```

Inicie a IDE e busque pela pasta `golden-raspberry-awards`. É possivel clona o projeto utilizado a IDE recomendada.

Para executar o projeto, o mesmo pode ser feito manualmente clicando com o botão direito no arquivo `/src/main/java/com/awards/films/GoldenRaspberryAwardsApplication.java` na opção `Run GoldenRaspberryAwardsApplication.main()` ou configurando a execução em `Edit Run/Debug configurations`.

Para os testes de integração é parecido com a execução, porém é ncessário clicar com o botão direito no arquivo `src/test/java/com/awards/films/GoldenRaspberryAwardsApplicationTests.java` na opção `Run GoldenRaspberryAwardsApplicationTests` ou configurando a execução em `Edit Run/Debug configurations`.


## Documentação da API

#### Retorna todos os itens

```http
  GET /api/raspberry
```

Não é necessário o envio de nenhum parâmetro. O retorno é o seguinte objeto:

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `producer`  | `string`      | Produtores que receberam a premiação |
| `interval`  | `int`      | O Tempo entre uma premiação e a outra |
| `previousWin`  | `int`      | Ano da primeira premiação |
| `followingWin`  | `int`      | Ano da segunda premiação (analisada) |

Exemplo:
```json

{
    "min": [
        {
            "producer": "Producer 1",
            "interval": 1,
            "previousWin": 2008,
            "followingWin": 2009
        },
        {
            "producer": "Producer 2",
            "interval": 1,
            "previousWin": 2018,
            "followingWin": 2019
        }
    ],
    "max": [
        {
            "producer": "Producer 1",
            "interval": 99,
            "previousWin": 1900,
            "followingWin": 1999
        },
        {
            "producer": "Producer 2",
            "interval": 99,
            "previousWin": 2000,
            "followingWin": 2099
        }
    ]
}

```
