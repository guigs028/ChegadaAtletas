-- Criar tabela de treinadores
CREATE TABLE treinador (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    equipe VARCHAR(255) NOT NULL,
    presente BOOLEAN DEFAULT FALSE
);

-- Criar tabela de atletas
CREATE TABLE atleta (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    numero_luta INTEGER NOT NULL,
    cor VARCHAR(50) NOT NULL,
    equipe VARCHAR(255) NOT NULL,
    presente BOOLEAN DEFAULT FALSE
);

-- Criar tabela de relacionamento atleta-treinador
CREATE TABLE atleta_treinador (
    atleta_id BIGINT NOT NULL,
    treinador_id BIGINT NOT NULL,
    PRIMARY KEY (atleta_id, treinador_id),
    FOREIGN KEY (atleta_id) REFERENCES atleta(id) ON DELETE CASCADE,
    FOREIGN KEY (treinador_id) REFERENCES treinador(id) ON DELETE CASCADE
);

