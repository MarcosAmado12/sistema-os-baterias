DROP TABLE IF EXISTS peca_utilizada;
DROP TABLE IF EXISTS ordem_servico;
DROP TABLE IF EXISTS bateria;
DROP TABLE IF EXISTS funcionario;
DROP TABLE IF EXISTS cliente;

CREATE TABLE cliente (
    id_cliente SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100),
    cpf_cnpj VARCHAR(18) NOT NULL UNIQUE,
    endereco VARCHAR(150)
);

CREATE TABLE funcionario (
    id_funcionario SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100),
    login VARCHAR(30) NOT NULL UNIQUE,
    senha VARCHAR(30) NOT NULL,
    cargo VARCHAR(20) NOT NULL,
    especialidade VARCHAR(60)
);

CREATE TABLE bateria (
    id_bateria SERIAL PRIMARY KEY,
    numero_serie VARCHAR(40) NOT NULL UNIQUE,
    marca VARCHAR(40),
    modelo VARCHAR(40),
    tensao NUMERIC(6,2),
    capacidade_ah NUMERIC(8,2),
    id_cliente INTEGER NOT NULL REFERENCES cliente(id_cliente)
);

CREATE TABLE ordem_servico (
    numero SERIAL PRIMARY KEY,
    data_abertura DATE NOT NULL,
    data_conclusao DATE,
    descricao_problema VARCHAR(300) NOT NULL,
    status VARCHAR(20) NOT NULL,
    valor_total NUMERIC(10,2) NOT NULL DEFAULT 0,
    tipo VARCHAR(20) NOT NULL,
    valor_revisao NUMERIC(10,2),
    valor_mao_de_obra NUMERIC(10,2),
    numero_garantia VARCHAR(40),
    id_cliente INTEGER NOT NULL REFERENCES cliente(id_cliente),
    id_bateria INTEGER NOT NULL REFERENCES bateria(id_bateria),
    id_tecnico INTEGER NOT NULL REFERENCES funcionario(id_funcionario)
);

CREATE TABLE peca_utilizada (
    id_peca SERIAL PRIMARY KEY,
    descricao VARCHAR(100) NOT NULL,
    quantidade INTEGER NOT NULL,
    valor_unitario NUMERIC(10,2) NOT NULL,
    numero_os INTEGER NOT NULL REFERENCES ordem_servico(numero) ON DELETE CASCADE
);

INSERT INTO funcionario (nome, telefone, email, login, senha, cargo, especialidade) VALUES
('Administrador', '(43) 3520-0000', 'gerente@oficina.com', 'admin', 'admin', 'Gerente', NULL),
('Ana Souza', '(43) 99911-0001', 'ana@oficina.com', 'ana', '123', 'Atendente', NULL),
('Carlos Lima', '(43) 99911-0002', 'carlos@oficina.com', 'carlos', '123', 'Tecnico', 'Baterias tracionárias'),
('Rafael Mendes', '(43) 99911-0003', 'rafael@oficina.com', 'rafael', '123', 'Tecnico', 'BMS e eletrônica');

INSERT INTO cliente (nome, telefone, email, cpf_cnpj, endereco) VALUES
('Transportadora Rota Norte', '(43) 3523-1010', 'contato@rotanorte.com', '12.345.678/0001-90', 'Av. Brasil, 1500 - Cornélio Procópio'),
('João Pereira', '(43) 99876-5544', 'joao.pereira@email.com', '123.456.789-09', 'Rua das Flores, 220 - Cornélio Procópio');

INSERT INTO bateria (numero_serie, marca, modelo, tensao, capacidade_ah, id_cliente) VALUES
('LFP48-000123', 'BSL', 'B-LFP48-460', 48, 460, 1),
('LFP80-000457', 'BSL', 'B-LFP80-690', 80, 690, 1),
('LFP51-000981', 'BSL', 'B-LFP51-105', 51.2, 105, 2);
