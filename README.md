# Desafio CASSI - API de Produtos

Este projeto é uma API RESTful desenvolvida em Java com Spring Boot para gerenciar produtos. Ele permite operações de criação, atualização, exclusão e consulta de produtos, oferecendo suporte a filtros, paginação e ordenação.

## Índice

1. [Tecnologias Utilizadas](#tecnologias-utilizadas)
2. [Dependências do Projeto](#dependências-do-projeto)
3. [Pré-requisitos](#pré-requisitos)
4. [Configuração do Ambiente](#configuração-do-ambiente)
5. [Como Rodar o Projeto](#como-rodar-o-projeto)
6. [Testes](#testes)
7. [Documentação da API](#documentação-da-api)
8. [Estrutura do Projeto](#estrutura-do-projeto)
9. [Contribuição](#contribuição)
10. [Licença](#licença)

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.3.4**
- **Maven 3.6+**
- **Banco de Dados**: H2 (padrão), ou outro banco de dados JDBC (como MySQL ou PostgreSQL)
- **Swagger/OpenAPI** para documentação
- **JUnit 5** e **Mockito** para testes
- **Lombok** para simplificação de código

## Dependências do Projeto

As principais dependências configuradas no projeto são:

- **Spring Boot Starter Web**: Para criação de APIs RESTful.
- **Spring Boot Starter Data JPA**: Para persistência de dados usando JPA/Hibernate.
- **H2 Database**: Banco de dados em memória para desenvolvimento e testes.
- **Lombok**: Para reduzir o código boilerplate, como getters, setters e construtores.
- **Spring Boot DevTools**: Para facilitar o desenvolvimento com recarga automática de classes.
- **SpringDoc OpenAPI**: Para gerar a documentação Swagger da API.
- **JUnit 5** e **Mockito**: Para testes unitários e mocks.

## Pré-requisitos

Certifique-se de ter os seguintes itens instalados:

- **JDK 17** - [Download JDK](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- **Maven 3.6+** - [Download Maven](https://maven.apache.org/download.cgi)
- **Git** (opcional, para clonar o repositório)

## Configuração do Ambiente

1. **Clone o repositório:**

   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd desafio-cassi


## Configuração do Banco de Dados:

Por padrão, a aplicação está configurada para usar o banco de dados H2 em memória. As configurações do H2 estão no arquivo src/main/resources/application.properties. Se quiser usar um banco de dados como MySQL ou PostgreSQL, será necessário ajustar as configurações de conexão.

## Configurações do H2:

### O H2 pode ser acessado através de http://localhost:8080/h2-console com as credenciais padrão:

* URL JDBC: jdbc:h2:mem:testdb
* Usuário: sa
* Senha: password


Compilar o projeto com Maven:

      mvn clean install 


Como Rodar o Projeto

      mvn spring-boot:run

## Acesse a aplicação:

A API estará disponível em http://localhost:8080.



## Testes
Para rodar os testes unitários, execute:

       mvn test

Os testes são configurados com o JUnit 5 e o Mockito para criar cenários de teste e mocks.

## Documentação da API
A documentação da API é gerada automaticamente pelo Swagger com o SpringDoc OpenAPI e pode ser acessada em:

* Swagger UI: http://localhost:8080/swagger-ui.html
* OpenAPI Spec: http://localhost:8080/v3/api-docs

# Acesso à API
A API estará disponível em http://localhost:8080/api/products.

## Endpoints Principais
* GET /api/products - Lista todos os produtos com suporte a paginação e filtros.
* GET /api/products/{id} - Retorna os detalhes de um produto específico.
* POST /api/products - Cria um novo produto.
* PUT /api/products/{id} - Atualiza um produto existente.
* DELETE /api/products/{id} - Remove um produto pelo ID.


Documentação com Swagger
O projeto utiliza o SpringDoc OpenAPI para gerar a documentação Swagger automaticamente.

### Acessando a Documentação Swagger
Após iniciar a aplicação, a interface gráfica do Swagger estará disponível em:

Swagger UI: http://localhost:8080/swagger-ui.html
