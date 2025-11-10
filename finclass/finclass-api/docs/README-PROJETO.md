# FinClass API

API REST em **Java + Spring Boot** para o projeto **FinClass** (Challenge Oracle / Java Advanced).
A aplicação se conecta ao **Oracle** e consome **procedures e table functions** do banco.

A solução expõe endpoints para:

- **Despesas** - operações via procedures (`pr_despesa_ins/upd/`)
- **Metas Financeiras** operações via procedures (`pr_meta_ins/upd/del`)
- **Relatórios** - consultas via table functions (`fn_relatorio_despesas, fn_relatorio_metas`)

---

## Integrantes do grupo

- JUAN PABLO REBELO COELHO – RM6560657
- MARIA EDUARDA FERNANDES ROCHA – RM560657
- VICTOR DE CARVALHO ALVES – RM560395

---

## Como executar a aplicação

Pré-requisitos:

- Java 17
- Maven 3.x
- Acesso ao Oracle (FIAP): oracle.fiap.com.br:1521:ORCL
- Scripts SQL da Sprint (pasta /sql) já executados no banco

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
