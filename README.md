# 📦 Base REST API

Este projeto é uma **prova de conceito (PoC)** de um sistema **PDV (Ponto de Venda)**, desenvolvido em **Java 21** com **Spring Boot 3.x**. Foi criado com propósito didático e como parte do meu portfólio, para servir como base para outros projetos e demonstração de boas práticas no desenvolvimento de APIs REST.

---

## ⚙️ Tecnologias e Padrões Utilizados

### 🖥️ API RESTful
- Estrutura organizada em camadas (controller, service, repository)
- Boas práticas de versionamento, validação e tratamento de erros

### 🔐 Autenticação e Autorização
- Autenticação baseada em **JWT (JSON Web Tokens)**
- Integração com **OAuth2 Resource Server** para validação de tokens
- Controle de acesso a endpoints protegidos

### 🛠️ Tecnologias
- **Java 21**
- **Spring Boot 3.2.6**
- **Spring Security** (Segurança e OAuth2 Resource Server)
- **Spring Data JPA** (Persistência de dados)
- **MapStruct** (Mapeamento entre entidades e DTOs)
- **Lombok** (Redução de boilerplate)
- **H2 Database** (Banco em memória para testes)
- **OpenAPI/Swagger** (Documentação automática da API)

---

## 🗂️ Principais Funcionalidades

- ✅ Cadastro de **Usuários**
- ✅ **Autenticação de Usuários** (login e geração de tokens JWT)
- ✅ CRUD de **Clientes**
- ✅ CRUD de **Produtos**
- ✅ CRUD de **Vendas**
- ✅ Documentação da API disponível via Swagger

---

## 🚀 Como rodar o projeto

Certifique-se de ter **Java 21** instalado.

```bash
git clone https://github.com/seu-usuario/base-rest-api.git
cd base-rest-api
./mvnw spring-boot:run
