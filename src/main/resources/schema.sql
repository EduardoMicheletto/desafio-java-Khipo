CREATE TABLE usuario (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         email VARCHAR(255),
                         login VARCHAR(255),
                         nome VARCHAR(255),
                         password VARCHAR(255),
                         acessos ENUM('USER', 'ADMIN'),
                         PRIMARY KEY (id)
);

CREATE TABLE produto (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         nome VARCHAR(255),
                         valor DECIMAL(38,2),
                         categoria TINYINT CHECK (categoria BETWEEN 0 AND 3),
                         PRIMARY KEY (id)
);

CREATE TABLE pedido (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        id_usuario BIGINT,
                        PRIMARY KEY (id),
                        FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

CREATE TABLE pedido_produto (
                                id BIGINT NOT NULL AUTO_INCREMENT,
                                id_pedido BIGINT,
                                id_produto BIGINT,
                                PRIMARY KEY (id),
                                FOREIGN KEY (id_pedido) REFERENCES pedido(id),
                                FOREIGN KEY (id_produto) REFERENCES produto(id)
);
