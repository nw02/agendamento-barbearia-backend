create table cliente(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    telefone VARCHAR(11) NOT NULL,
    email VARCHAR(50) NOT NULL
);

create table prestador(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    telefone VARCHAR(11) NOT NULL,
    email VARCHAR(50) NOT NULL
);

create table agendamento(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    prestador_id INT NOT NULL,
    cliente_id INT NOT NULL,
    servico VARCHAR(30) NOT NULL,
    data_hora TIMESTAMP NOT NULL,
    status VARCHAR(30) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,

    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    CONSTRAINT fk_prestador FOREIGN KEY (prestador_id) REFERENCES prestador(id)
);