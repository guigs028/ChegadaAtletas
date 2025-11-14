# ❌ ERRO: Permissão Negada no PostgreSQL

## 🔴 Problema Identificado:
```
ERROR: permission denied for schema public
```

O usuário `entradaAtletas` não tem permissão para criar tabelas no banco de dados.

---

## ✅ SOLUÇÃO RÁPIDA:

### Opção 1: Executar Script SQL (RECOMENDADO)

1. **Abra o pgAdmin ou psql como usuário `postgres`**

2. **Execute os comandos:**

```sql
-- Conectar ao banco
\c EntradaAtletas

-- Dar permiss��es
GRANT ALL PRIVILEGES ON SCHEMA public TO entradaAtletas;
GRANT CREATE ON SCHEMA public TO entradaAtletas;
GRANT ALL PRIVILEGES ON DATABASE EntradaAtletas TO entradaAtletas;
ALTER SCHEMA public OWNER TO entradaAtletas;
```

3. **OU execute o arquivo criado:**
```bash
psql -U postgres -d EntradaAtletas -f corrigir_permissoes.sql
```

---

### Opção 2: Usar Usuário Postgres (MAIS FÁCIL)

Se preferir não mexer com permissões, altere o `application.properties`:

```properties
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA_DO_POSTGRES
```

---

## 🚀 DEPOIS DE CORRIGIR:

1. **Limpe as tabelas** (se houver):
```sql
DROP TABLE IF EXISTS atleta_treinador CASCADE;
DROP TABLE IF EXISTS atleta CASCADE;
DROP TABLE IF EXISTS treinador CASCADE;
DROP TABLE IF EXISTS flyway_schema_history CASCADE;
```

2. **Inicie a aplicação:**
```bash
.\mvnw.cmd spring-boot:run
```

3. **Acesse:**
```
http://localhost:8080
```

---

## 📋 Resumo do que fazer:

**ESCOLHA UMA OPÇÃO:**

### ✅ Opção A (Recomendada): Dar permissões ao usuário

No **pgAdmin** ou **psql** como `postgres`:
```sql
\c EntradaAtletas
GRANT ALL PRIVILEGES ON SCHEMA public TO entradaAtletas;
ALTER SCHEMA public OWNER TO entradaAtletas;
```

### ✅ Opção B (Mais rápida): Usar usuário postgres

Edite `application.properties`:
```properties
spring.datasource.username=postgres
spring.datasource.password=sua_senha_postgres
```

---

**Escolha uma opção e depois inicie a aplicação!** 🚀

