# App Ensino Matemática Backend Resposta

Este é o backend de um aplicativo de ensino de matemática feito para a comunidade externa, responsável por gerenciar as respostas dos alunos e as rodadas de perguntas.

## Sumário

- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Configuração do Ambiente](#configuração-do-ambiente)
- [Endpoints](#endpoints)
- [Licença](#licença)


## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data MongoDB
- Lombok
- MongoDB

### Por que utilizar MongoDB (banco NOSQL)?
- MongoDB oferece uma abordagem de consulta direta, usando filtros baseados em JSON ou BSON. Isso significa que você pode fazer buscas de maneira simples, sem a complexidade das joins de bancos SQL.
- No MongoDB, você pode armazenar dados sem se preocupar com a criação de muitas tabelas ou coleções adicionais, como ocorre em bancos relacionais para normalização. A estrutura de dados no MongoDB (documentos JSON) já permite agrupar informações relacionadas em um único documento, sem a necessidade de muitas referências ou tabelas extras.
- MongoDB é altamente flexível quando se trata de salvar dados. Não há necessidade de seguir um esquema fixo. Se você quiser adicionar novos campos ou novos tipos de dados (por exemplo, um novo tipo de pergunta ou uma nova categoria), você pode fazer isso sem grandes mudanças ou migrações no banco de dados. Isso permite que você se adapte rapidamente a novos requisitos ou alterações no modelo de dados.
- MongoDB é projetado para otimizar o desempenho em grandes volumes de dados, especialmente quando se trata de leitura e gravação rápidas. Ele oferece recursos como índices para acelerar as buscas, o que é essencial em sistemas que lidam com muitos documentos, como um banco de respostas de questões.

## Estrutura do Projeto

O projeto está organizado nas seguintes pastas:

- `controller`: Contém os controladores REST que expõem os endpoints da API.
- `model`: Contém as classes de modelo que representam os dados.
- `repository`: Contém as interfaces de repositório para acesso ao banco de dados.
- `service`: Contém as classes de serviço que implementam a lógica de negócios.
  
```bash
  src/
├── main/
│   ├── java/
│   │   └── br/
│   │       └── edu/
│   │           └── iftm/
│   │               └── app_ensino_matematica_backend_resposta/
│   │                   ├── config/
│   │                   │   └── WebConfig.java
│   │                   ├── controller/
│   │                   │   └── RodadaController.java
│   │                   ├── model/
│   │                   │   ├── Resposta.java
│   │                   │   ├── Rodada.java
│   │                   │   └── DTO/
│   │                   │       └── RodadaDTO.java
│   │                   │       └── RodadaRequest.java
│   │                   ├── repository/
│   │                   │      └── RodadaDTO.java
│   │                   ├── service/
│   │                   │      └── RodadaService.java
│   │                   └── AppEnsinoMatematicaBackendRespostaoApplication.java
│   └── resources/
│       ├── application.yml
│       
│           
└── test/
    └── java/
        └── br/
            └── edu/
                └── iftm/
                    └── app_ensino_matematica_backend_resposta/
                        └── AppEnsinoMatematicaBackendRespostaApplicationTests.java
```

## Configuração do Ambiente

### Pré-requisitos

- Java 11 ou superior
- Maven
- MongoDB

### Configuração do MongoDB

Certifique-se de que o MongoDB esteja em execução e configurado corretamente. Você pode ajustar as configurações de conexão no arquivo `application.properties`.

### Compilação e Execução

Para compilar e executar o projeto, use os seguintes comandos:

```bash
mvn clean install
mvn spring-boot:run
```
# Endpoints
## Rodada

### POST /report/round
- **Descrição**:  Salva uma nova rodada com a lista de respostas.
- **Resposta**:
- Status: 201 CREATED
- Corpo da resposta: 
```json
{
    "rodada": {
        "idCategoria": "123e4567-e89b-12d3-a456-426614174000",
        "idAluno": "123e4567-e89b-12d3-a456-426614174001",
        "dificuldade": 1,
        "pontuacao": 85
    },
    "respostas": [
        {
            "id_resposta": "223e4567-e89b-12d3-a456-426614174000",
            "id_questao": "223e4567-e89b-12d3-a456-426614174002",
            "isCorrect": false
        },
        {
            "id_resposta": "223e4567-e89b-12d3-a456-426614174004",
            "id_questao": "223e4567-e89b-12d3-a456-426614174006",
            "isCorrect": false
        },
        {
            "id_resposta": "223e4567-e89b-12d3-a456-426614174008",
            "id_questao": "223e4567-e89b-12d3-a456-426614174010",
            "isCorrect": true
        }
    ]
}
```

### GET /report/round/{id_rodada}
- **Descrição**: Obtém uma rodada pelo ID.
- **Parâmetros**:
- `id_rodada` (UUID): ID da rodada.
- **Resposta**:
- Status: 200 OK
- Corpo da resposta:
  
```json
  [
        {
        "id_rodada": "123e4567-e89b-12d3-a456-426614174001",
        "id_categoria": "123e4567-e89b-12d3-a456-426614174000",
        "id_aluno": "223e4567-e89b-12d3-a456-426614174001",
        "dificuldade": 1,
        "pontuacao": 85,
        "respostas": [
            {
                "id_resposta": "223e4567-e89b-12d3-a456-426614174000",
                "id_questao": "223e4567-e89b-12d3-a456-426614174002",
                "isCorrect": false
            },
            {
                "id_resposta": "223e4567-e89b-12d3-a456-426614174004",
                "id_questao": "223e4567-e89b-12d3-a456-426614174006",
                "isCorrect": false
            },
            {
                "id_resposta": "223e4567-e89b-12d3-a456-426614174008",
                "id_questao": "223e4567-e89b-12d3-a456-426614174010",
                "isCorrect": true
            }
        ]
    }
]
```

### GET /report/round/search 
- Descrição: Busca rodadas por categoria e dificuldade.
- **Parâmetros**:
- `id_categoria` (UUID): ID da categoria.
- `dificuldade` (int): Nível de dificuldade.
- **Exemplo de requisição**: /report/round/search?id_categoria={id_categoria}&dificuldade={dificuldade}
- **Resposta**:
- Status: 200 OK
- Corpo da Resposta:
```json
    [
        {
        "id_rodada": "123e4567-e89b-12d3-a456-426614174001",
        "id_categoria": "123e4567-e89b-12d3-a456-426614174000",
        "id_aluno": "223e4567-e89b-12d3-a456-426614174001",
        "dificuldade": 1,
        "pontuacao": 85,
        "respostas": [
            {
                "id_resposta": "223e4567-e89b-12d3-a456-426614174000",
                "id_questao": "223e4567-e89b-12d3-a456-426614174002",
                "isCorrect": false
            },
            {
                "id_resposta": "223e4567-e89b-12d3-a456-426614174004",
                "id_questao": "223e4567-e89b-12d3-a456-426614174006",
                "isCorrect": false
            },
            {
                "id_resposta": "223e4567-e89b-12d3-a456-426614174008",
                "id_questao": "223e4567-e89b-12d3-a456-426614174010",
                "isCorrect": true
            }
        ]
    }
]
```

## Licença

Este projeto está licenciado sob a Licença Apache 2.0. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
