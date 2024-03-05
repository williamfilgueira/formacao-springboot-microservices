
CREATE TABLE pagamentos (
    id BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
    valor DECIMAL(19, 2) NOT NULL,
    nome VARCHAR(100) DEFAULT NULL,
    numero VARCHAR(19) DEFAULT NULL,
    expiracao VARCHAR(7) NOT NULL,
    codigo VARCHAR(3) DEFAULT NULL,
    status VARCHAR(50) NOT NULL,
    pedido_id BIGINT(20) NOT NULL,
    forma_de_pagamento_id BIGINT NOT NULL
)