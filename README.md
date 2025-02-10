# App Ensino Matemática Backend Resposta

Este é o backend do aplicativo de ensino de matemática, responsável por gerenciar as respostas dos alunos e as rodadas de perguntas.

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

## Configuração do Projeto

### Pré-requisitos

- Java 11 ou superior
- Maven
- MongoDB

### Configuração do MongoDB

Certifique-se de que o MongoDB esteja em execução e configurado corretamente. Você pode ajustar as configurações de conexão no arquivo `application.properties`.

### Compilação e Execução

Para compilar e executar o projeto, use os seguintes comandos:

mvn clean install
mvn spring-boot:run

## Endpoints da API

### RodadaController
Salvar Rodada
Endpoint: /report/round
Método: POST
Descrição: Salva uma nova rodada com a lista de respostas. Corpo da Requisição:

![image](https://github.com/user-attachments/assets/0de93d88-bb7b-49d9-bb59-c1cc5b529c67)


### Obter Rodada por ID
Endpoint: /report/round/{id_rodada}
Método: GET
Descrição: Obtém uma rodada pelo ID.
Parâmetros:
id_rodada (UUID): ID da rodada.

### Buscar Rodadas por Categoria e Dificuldade
Endpoint: /report/round/search
Método: GET
Descrição: Busca rodadas por categoria e dificuldade.
Parâmetros:
id_categoria (UUID): ID da categoria.
dificuldade (int): Nível de dificuldade.

## Licença

Este projeto está licenciado sob a Licença Apache 2.0. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
