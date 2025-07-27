CREATE SCHEMA IF NOT EXISTS estoque;

CREATE TABLE estoque.peca (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255),
    codigo VARCHAR(100),
    codigo_antigo VARCHAR(100),
    quantidade INT NOT NULL,
    aberto INT NOT NULL,
    fechado INT NOT NULL,
    coluna INT NOT NULL,
    prateleira VARCHAR(50),
    linha VARCHAR(50)
);