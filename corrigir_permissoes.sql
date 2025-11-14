-- Execute este script como usuário postgres (superusuário)
-- para dar as permissões corretas ao usuário entradaAtletas

-- Conectar ao banco de dados
\c EntradaAtletas

-- Dar permissão ao usuário no schema public
GRANT ALL PRIVILEGES ON SCHEMA public TO entradaAtletas;

-- Dar permissão para criar tabelas
GRANT CREATE ON SCHEMA public TO entradaAtletas;

-- Dar todas as permissões no banco de dados
GRANT ALL PRIVILEGES ON DATABASE EntradaAtletas TO entradaAtletas;

-- Tornar o usuário dono do schema public (opcional, mas recomendado)
ALTER SCHEMA public OWNER TO entradaAtletas;

-- Confirmar
\l EntradaAtletas

