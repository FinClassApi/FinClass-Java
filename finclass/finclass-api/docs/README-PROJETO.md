# FinClass API

API REST desenvolvida em **Java + Spring Boot** para o projeto **FinClass**, parte do Challenge Oracle / disciplina de Java Advanced.

A solução expõe endpoints para:

- **Autenticação** (`/api/v1/auth`) – login e cadastro de usuários
- **Despesas** (`/api/v1/despesas`) – cadastro e consulta de despesas
- **Metas Financeiras** (`/api/v1/metas`) – cadastro e consulta de metas
- **Relatórios** (`/api/v1/relatorios`) – resumo financeiro consolidando despesas e metas

---

## Integrantes do grupo

> Preencha com os dados reais da equipe

- Nome Completo 1 – RM XXXXX – Responsável por \<atividade\>
- Nome Completo 2 – RM XXXXX – Responsável por \<atividade\>
- Nome Completo 3 – RM XXXXX – Responsável por \<atividade\>

---

## Como executar a aplicação

Pré-requisitos:

- Java 17
- Maven 3.x

Passos:

```bash
mvn spring-boot:run
```

A aplicação subirá por padrão em: `http://localhost:8080`.

### Banco de dados

- Em desenvolvimento, a aplicação usa **H2 em memória**.
- Para produção / integração com Oracle, configure as propriedades em `application.properties`
  (ou `application-prod.properties`) conforme exemplo comentado no arquivo.

---

## Documentação da API (Swagger / OpenAPI)

Depois de subir a aplicação, acesse:

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

---

## Endpoints principais

### Autenticação

- `POST /api/v1/auth/register` – cadastra um novo usuário
- `POST /api/v1/auth/login` – autentica usuário (retorna informações básicas e mensagem)

### Despesas

- `POST /api/v1/despesas` – cadastra uma despesa
- `GET /api/v1/despesas/usuario/{usuarioId}` – lista despesas do usuário

### Metas Financeiras

- `POST /api/v1/metas` – cadastra uma meta financeira
- `GET /api/v1/metas/usuario/{usuarioId}` – lista metas do usuário

### Relatórios

- `GET /api/v1/relatorios/resumo/{usuarioId}` – retorna um resumo financeiro do usuário com links HATEOAS
  (evolução prevista para a Sprint 2).

---

## Estrutura de pastas (camadas)

- `controller` – Endpoints REST (interface com o cliente)
- `service` – Regras de negócio e orquestração
- `repository` – Acesso ao banco (Spring Data JPA)
- `model` – Entidades JPA (mapeamento objeto–relacional)
- `dto` – Objetos de transferência de dados (entrada/saída)
- `exception` – Tratamento centralizado de erros
- `config` – Configurações (Swagger/OpenAPI, segurança, seed do banco)
- `enums` – Enumerações de apoio (perfil de usuário, categoria de despesa)

Consulte também:

- [`docs/cronograma-sprints.md`](./cronograma-sprints.md)
- [`docs/diagramas.md`](./diagramas.md)
- Pasta `postman/` com a coleção de testes dos endpoints.
