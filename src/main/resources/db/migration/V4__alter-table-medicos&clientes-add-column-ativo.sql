ALTER TABLE
    medicos
    ADD
        ativo BOOLEAN NOT NULL DEFAULT TRUE;

ALTER TABLE
    clientes
    ADD
        ativo BOOLEAN NOT NULL DEFAULT TRUE;