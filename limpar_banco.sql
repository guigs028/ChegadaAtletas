-- Execute este script no PostgreSQL para limpar o banco antes de iniciar

-- Dropar tabelas existentes (se houver)
DROP TABLE IF EXISTS atleta_treinador CASCADE;
DROP TABLE IF EXISTS atleta CASCADE;
DROP TABLE IF EXISTS treinador CASCADE;
DROP TABLE IF EXISTS flyway_schema_history CASCADE;

-- As tabelas serão criadas automaticamente pelo Flyway ao iniciar a aplicação

