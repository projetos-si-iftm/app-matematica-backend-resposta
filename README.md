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

## Estrutura do Projeto

O projeto está organizado nas seguintes pastas:

- `controller`: Contém os controladores REST que expõem os endpoints da API.
- `model`: Contém as classes de modelo que representam os dados.
- `repository`: Contém as interfaces de repositório para acesso ao banco de dados.
- `service`: Contém as classes de serviço que implementam a lógica de negócios.

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
