# App Ensino Matemática Backend Resposta

Este é o backend de um aplicativo educacional de matemática voltado para a comunidade externa. Ele é responsável por gerenciar, criar, atualizar e consultar recursos relacionados às rodadas de questões e ao ranking dos alunos, apoiando o acompanhamento do desempenho e da evolução dos participantes.

O sistema é composto por múltiplos microsserviços que se complementam, incluindo os serviços de 👉 [questões](https://github.com/projetos-si-iftm/app-matematica-backend-questao) e 👉 [usuários](https://github.com/projetos-si-iftm/app-matematica-backend), responsáveis respectivamente pelo gerenciamento do banco de questões e das informações dos alunos, professores e turma.

## Sumário

- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Configuração do Ambiente](#configuração-do-ambiente)
- [Endpoints](#endpoints)
- [Licença](#licença)


# Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data MongoDB
- Lombok

## Microsserviços e Infraestrutura
- Spring Cloud Gateway
- Eureka Server

## Biblioteca Compartilhada DTO's

Este projeto utiliza uma biblioteca compartilhada de DTOs desenvolvida especialmente para padronizar a comunicação entre os microsserviços do sistema.
Essa biblioteca contém as classes de transferência de dados (DTOs) utilizadas por todos os serviços — como usuário, questão e resposta — garantindo consistência nas trocas de informações e facilitando a manutenção.

👉 [Repositório da biblioteca de DTO's](https://github.com/fromanoel/app-matematica-dtos)

## Documentação 
- Swagger (OpenAPI)

## Banco de Dados
- MongoDB

### Por que utilizar MongoDB (banco NOSQL)?
- MongoDB oferece uma abordagem de consulta direta, usando filtros baseados em JSON ou BSON. Isso significa que você pode fazer buscas de maneira simples, sem a complexidade das joins de bancos SQL.
- No MongoDB, você pode armazenar dados sem se preocupar com a criação de muitas tabelas ou coleções adicionais, como ocorre em bancos relacionais para normalização. A estrutura de dados no MongoDB (documentos JSON) já permite agrupar informações relacionadas em um único documento, sem a necessidade de muitas referências ou tabelas extras.
- MongoDB é altamente flexível quando se trata de salvar dados. Não há necessidade de seguir um esquema fixo. Se você quiser adicionar novos campos ou novos tipos de dados (por exemplo, um novo tipo de pergunta ou uma nova categoria), você pode fazer isso sem grandes mudanças ou migrações no banco de dados. Isso permite que você se adapte rapidamente a novos requisitos ou alterações no modelo de dados.
- MongoDB é projetado para otimizar o desempenho em grandes volumes de dados, especialmente quando se trata de leitura e gravação rápidas. Ele oferece recursos como índices para acelerar as buscas, o que é essencial em sistemas que lidam com muitos documentos, como um banco de respostas de questões.

# Estrutura do Projeto

O projeto está organizado nas seguintes pastas:

- `controller`: Contém os controladores REST que expõem os endpoints da API.
- `model`: Contém as classes de modelo que representam os dados.
- `repository`: Contém as interfaces de repositório para acesso ao banco de dados.
- `service`: Contém as classes de serviço que implementam a lógica de negócios.
  
```bash
  +---main
|   +---java
|   |   \---br
|   |       \---edu
|   |           \---iftm
|   |               \---app_ensino_matematica_backend_resposta
|   |                   |   AppEnsinoMatematicaBackendRespostaApplication.java
|   |                   |   
|   |                   +---config
|   |                   |       SwaggerConfig.java
|   |                   |       
|   |                   +---controller
|   |                   |       RankingController.java
|   |                   |       RodadaController.java
|   |                   |       
|   |                   +---converter
|   |                   |       RespostaConverter.java
|   |                   |       RodadaConverter.java
|   |                   |       
|   |                   +---model
|   |                   |       Resposta.java
|   |                   |       Rodada.java
|   |                   |       
|   |                   +---repository
|   |                   |       RodadaRepository.java
|   |                   |       
|   |                   \---service
|   |                           RankingService.java
|   |                           RodadaService.java
|   |
|   \---resources
|           application.yml

```

# Configuração do Ambiente

### Pré-requisitos

- Java 11 ou superior
- Maven
- MongoDB

### Configuração do MongoDB

Certifique-se de que o MongoDB esteja em execução e configurado corretamente. Você pode ajustar as configurações de conexão no arquivo `application.yml`.

### Compilação e Execução

Antes de iniciar este serviço, certifique-se de que os seguintes projetos estejam rodando:

[Eureka Server](https://github.com/projetos-si-iftm/app-matematica-eureka) - responsável pelo service discovery

[Gateway](https://github.com/projetos-si-iftm/app-matematica-backend-gateway) - responsável pelo roteamento das requisições

Para compilar e executar o projeto, use os seguintes comandos:

```bash
mvn clean install
mvn spring-boot:run
```
# Endpoints

A documentação completa está disponível via Swagger
👉 [Acesse a documentação Swagger aqui](https://app-matematica-backend-respost-af372f52044d.herokuapp.com/swagger-ui/index.html)

# Licença

Este projeto está licenciado sob a Licença Apache 2.0. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
