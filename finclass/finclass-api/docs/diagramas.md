# Diagramas – FinClass API

Abaixo estão versões em **Mermaid**, que podem ser exportadas para imagens (PNG/SVG) usando ferramentas online,
atendendo ao requisito de entregar diagramas em imagem.

---

## Diagrama Entidade–Relacionamento (DER)

```mermaid
erDiagram
    USUARIO {
        BIGINT id PK
        VARCHAR nome
        VARCHAR email
        VARCHAR senha
        VARCHAR perfil
        TIMESTAMP criado_em
        TIMESTAMP atualizado_em
    }

    DESPESA {
        BIGINT id PK
        VARCHAR descricao
        VARCHAR categoria
        DECIMAL valor
        DATE data
        BIGINT usuario_id FK
    }

    META_FINANCEIRA {
        BIGINT id PK
        VARCHAR nome
        DECIMAL valor_objetivo
        DECIMAL valor_atual
        DATE prazo
        BIGINT usuario_id FK
    }

    USUARIO ||--o{ DESPESA : "possui"
    USUARIO ||--o{ META_FINANCEIRA : "possui"
```

---

## Diagrama de Classes (Entidades)

```mermaid
classDiagram
    class Usuario {
        Long id
        String nome
        String email
        String senha
        PerfilUsuario perfil
        LocalDateTime criadoEm
        LocalDateTime atualizadoEm
    }

    class Despesa {
        Long id
        String descricao
        CategoriaDespesa categoria
        BigDecimal valor
        LocalDate data
    }

    class MetaFinanceira {
        Long id
        String nome
        BigDecimal valorObjetivo
        BigDecimal valorAtual
        LocalDate prazo
    }

    Usuario "1" --> "0..*" Despesa : possui
    Usuario "1" --> "0..*" MetaFinanceira : possui
```

---

## Visão de Arquitetura (Camadas)

```mermaid
graph TD
    A[Cliente Mobile / APEX] --> B[Controllers REST]
    B --> C[Services (Regras de Negócio)]
    C --> D[Repositories (Spring Data JPA)]
    D --> E[(Banco de Dados Oracle/H2)]
```
