/* Sistema de Restaurante - banco de dados (MySQL)
   Projeto final em equipe da disciplina de Laboratório de Banco de Dados
   Engenharia de Software - Universidade Católica de Brasília (UCB)

   Conteúdo: criação das tabelas, dados de exemplo, índice, consultas com JOIN,
   views, triggers, usuários com permissões e transações (COMMIT e ROLLBACK).
*/

-- 1. Criação do Banco de Dados e Estrutura
-- Criação do banco de dados.
DROP DATABASE IF EXISTS RESTAURANTE;

CREATE DATABASE IF NOT EXISTS RESTAURANTE
DEFAULT CHARACTER SET utf8mb4 
DEFAULT COLLATE utf8mb4_unicode_ci;

USE RESTAURANTE;

-- Criação das tabelas conforme a modelagem apresentada.
CREATE TABLE IF NOT EXISTS CLIENTE(
    id_cliente INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    data_cadastro DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP 
);

CREATE TABLE IF NOT EXISTS CARGO(
    id_cargo INT PRIMARY KEY AUTO_INCREMENT,
    nome_cargo VARCHAR(50) NOT NULL,
    salario DECIMAL(10,2) NOT NULL,
    carga_horaria INT NOT NULL,
    descricao VARCHAR(200),
    ativo TINYINT(1) NOT NULL DEFAULT 1
);

CREATE TABLE IF NOT EXISTS FUNCIONARIO(
    id_funcionario INT PRIMARY KEY AUTO_INCREMENT,
    id_cargo INT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(15) NOT NULL,
    data_admissao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    FOREIGN KEY (id_cargo) REFERENCES CARGO (id_cargo)
);

CREATE TABLE IF NOT EXISTS FORNECEDOR(
    id_fornecedor INT PRIMARY KEY AUTO_INCREMENT,
    empresa VARCHAR(100) NOT NULL,
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    nome_representante VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(15) NOT NULL
);

CREATE TABLE IF NOT EXISTS RESERVA(
    id_reserva INT PRIMARY KEY AUTO_INCREMENT,
    id_cliente INT NOT NULL,
    data_reserva DATE NOT NULL,
    horario TIME NOT NULL,
    qnt_pessoas INT NOT NULL,
    status TINYINT(1) NOT NULL DEFAULT 1,
    FOREIGN KEY (id_cliente) REFERENCES CLIENTE (id_cliente)
);

CREATE TABLE IF NOT EXISTS MESA(
    id_mesa INT PRIMARY KEY AUTO_INCREMENT,
    capacidade INT NOT NULL,
    local VARCHAR(45) NOT NULL,
    numero INT NOT NULL UNIQUE,
    status TINYINT(1) NOT NULL DEFAULT 1
);

CREATE TABLE IF NOT EXISTS MESA_RESERVA(
    id_reserva INT,
    id_mesa INT,
    hora_chegada TIME,
    hora_saida TIME,
    observacao VARCHAR(200),
    FOREIGN KEY (id_reserva) REFERENCES RESERVA (id_reserva),
    FOREIGN KEY (id_mesa) REFERENCES MESA (id_mesa),
    PRIMARY KEY (id_reserva, id_mesa)
);

CREATE TABLE IF NOT EXISTS PEDIDO(
    id_pedido INT PRIMARY KEY AUTO_INCREMENT,
    id_funcionario INT,
    id_mesa INT,
    id_reserva INT,
    tipo_consumo VARCHAR(45) NOT NULL,
    status TINYINT(1) NOT NULL DEFAULT 0,
    data DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    observacao VARCHAR(200),
    FOREIGN KEY (id_funcionario) REFERENCES FUNCIONARIO (id_funcionario),
    FOREIGN KEY (id_mesa) REFERENCES MESA (id_mesa),
    FOREIGN KEY (id_reserva) REFERENCES RESERVA (id_reserva)
);

CREATE TABLE IF NOT EXISTS PAGAMENTO(
    id_pagamento INT PRIMARY KEY AUTO_INCREMENT,
    id_pedido INT NOT NULL,
    metodo VARCHAR(45) NOT NULL,
    status TINYINT(1) NOT NULL DEFAULT 0,
    valor_pago DECIMAL(10,2) NOT NULL,
    data DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_pedido) REFERENCES PEDIDO (id_pedido)
);

CREATE TABLE IF NOT EXISTS CATEGORIA_PRODUTO(
    id_categoria INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(45) NOT NULL,
    ativo TINYINT(1) NOT NULL DEFAULT 1,
    data_criacao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    descricao VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS PRODUTO(
    id_produto INT PRIMARY KEY AUTO_INCREMENT,
    id_categoria INT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    disponibilidade TINYINT(1) NOT NULL DEFAULT 1, -- Alterado para 1 (produto nasce disponível ao criar)
    descricao VARCHAR(200),
    FOREIGN KEY (id_categoria) REFERENCES CATEGORIA_PRODUTO (id_categoria)
);

CREATE TABLE IF NOT EXISTS ITEM_PEDIDO(
    id_pedido INT,
    id_produto INT,
    status TINYINT(1) NOT NULL DEFAULT 0, -- Exemplo: 0 = Em preparo, 1 = Entregue
    quantidade INT NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    observacao VARCHAR(200),
    FOREIGN KEY (id_pedido) REFERENCES PEDIDO (id_pedido),
    FOREIGN KEY (id_produto) REFERENCES PRODUTO (id_produto),
    PRIMARY KEY (id_pedido, id_produto)
);

CREATE TABLE IF NOT EXISTS FORNECIMENTO(
    id_produto INT,
    id_fornecedor INT,
    unidade_medida VARCHAR(45) NOT NULL,
    preco_acordado DECIMAL(10,2) NOT NULL,
    prazo_entregas DATE NOT NULL,
    FOREIGN KEY (id_produto) REFERENCES PRODUTO (id_produto),
    FOREIGN KEY (id_fornecedor) REFERENCES FORNECEDOR (id_fornecedor),
    PRIMARY KEY (id_produto, id_fornecedor)
);

-- 2. Inserção de Dados
-- Inserção de dados em todas as tabelas. Cada tabela deve conter no mínimo 30 comandos INSERT. É permitido utilizar ferramentas auxiliares para gerar dados, como o ChatGPT ou geradores automáticos de dados fictícios.

-- INSERTS DO TRABALHO DO RESTAURANTE --

INSERT INTO CLIENTE (nome, telefone, email, cpf) VALUES
('João da Silva', '11987654321', 'joao.silva@email.com', '123.456.789-01'),
('Maria Oliveira', '21976543210', 'maria.oliveira@email.com', '234.567.890-12'),
('José Santos', '31965432109', 'jose.santos@email.com', '345.678.901-23'),
('Ana Souza', '41954321098', 'ana.souza@email.com', '456.789.012-34'),
('Carlos Pereira', '71943210987', 'carlos.pereira@email.com', '567.890.123-45'),
('Juliana Lima', '11932109876', 'juliana.lima@email.com', '678.901.234-56'),
('Marcos Ferreira', '21921098765', 'marcos.ferreira@email.com', '789.012.345-67'),
('Fernanda Costa', '31910987654', 'fernanda.costa@email.com', '890.123.456-78'),
('Lucas Rodrigues', '41909876543', 'lucas.rodrigues@email.com', '901.234.567-89'),
('Beatriz Almeida', '71988765432', 'beatriz.almeida@email.com', '012.345.678-90'),
('Gabriel Martins', '11977654321', 'gabriel.martins@email.com', '111.222.333-44'),
('Larissa Rocha', '21966543210', 'larissa.rocha@email.com', '222.333.444-55'),
('Matheus Ribeiro', '31955432109', 'matheus.ribeiro@email.com', '333.444.555-66'),
('Camila Carvalho', '41944321098', 'camila.carvalho@email.com', '444.555.666-77'),
('Bruno Gomes', '71933210987', 'bruno.gomes@email.com', '555.666.777-88'),
('Amanda Martins', '11922109876', 'amanda.martins@email.com', '666.777.888-99'),
('Rafael Barbosa', '21911098765', 'rafael.barbosa@email.com', '777.888.999-00'),
('Patrícia Araujo', '31900987654', 'patricia.araujo@email.com', '888.999.000-11'),
('Rodrigo Ramos', '41999876543', 'rodrigo.ramos@email.com', '999.000.111-22'),
('Renata Cavalcanti', '71988876543', 'renata.cavalcanti@email.com', '121.232.343-45'),
('Diego Monteiro', '11977765432', 'diego.monteiro@email.com', '232.343.454-56'),
('Vanessa Cardoso', '21966654321', 'vanessa.cardoso@email.com', '343.454.565-67'),
('Thiago Moreira', '31955543210', 'thiago.moreira@email.com', '454.565.676-78'),
('Daniela Dias', '41944432109', 'daniela.dias@email.com', '565.676.787-89'),
('Felipe Correia', '71933321098', 'felipe.correia@email.com', '676.787.898-90'),
('Tatiane Farias', '11922210987', 'tatiane.farias@email.com', '787.898.909-01'),
('Eduardo Nunes', '21911109876', 'eduardo.nunes@email.com', '898.909.010-12'),
('Letícia Vieira', '31900098765', 'leticia.vieira@email.com', '909.010.121-23'),
('Gustavo Pinto', '41999987654', 'gustavo.pinto@email.com', '010.121.232-34'),
('Bianca Duarte', '71988887653', 'bianca.duarte@email.com', '131.353.575-79');

INSERT INTO CARGO (nome_cargo, salario, carga_horaria, descricao) VALUES
('Gerente Geral', 5500.00, 220, 'Gerenciamento geral do restaurante'),
('Gerente Noturno', 4800.00, 220, 'Gerenciamento do turno da noite'),
('Garçom Sênior', 2200.00, 180, 'Atendimento de destaque às mesas'),
('Garçom Júnior', 1600.00, 180, 'Atendimento iniciante às mesas'),
('Chef de Cozinha', 6000.00, 220, 'Responsável pelo cardápio e cozinha'),
('Cozinheiro Chefe', 3200.00, 220, 'Preparo dos pratos principais'),
('Auxiliar de Cozinha', 1700.00, 220, 'Ajuda no pré-preparo'),
('Caixa Principal', 2000.00, 180, 'Operação do caixa principal'),
('Caixa Noturno', 1900.00, 180, 'Operação do caixa da noite'),
('Auxiliar de Limpeza', 1500.00, 180, 'Limpeza geral'),
('Cumim', 1550.00, 180, 'Auxiliar de garçom'),
('Sommelier', 3500.00, 180, 'Especialista em vinhos'),
('Barman', 2400.00, 180, 'Preparo de drinks e bebidas'),
('Recepcionista', 1800.00, 180, 'Recepção de clientes na entrada'),
('Hostess', 1900.00, 180, 'Organização de filas e reservas'),
('Manobrista', 1700.00, 180, 'Estacionamento de veículos'),
('Segurança', 2200.00, 220, 'Segurança patrimonial e interna'),
('Confeiteiro', 2600.00, 220, 'Preparo de doces e sobremesas'),
('Pizzaiolo', 2800.00, 220, 'Preparo de pizzas'),
('Churrasqueiro', 3000.00, 220, 'Preparo de carnes na brasa'),
('Estagiário de Cozinha', 1100.00, 120, 'Auxílio geral na cozinha'),
('Estagiário de Salão', 1100.00, 120, 'Auxílio geral no salão'),
('Auxiliar de Bar', 1600.00, 180, 'Reposição do bar'),
('Auxiliar de Estoque', 1750.00, 220, 'Controle de suprimentos'),
('Comprador', 3100.00, 220, 'Negociação com fornecedores'),
('Nutricionista', 3800.00, 220, 'Controle de qualidade e cardápio'),
('Auxiliar de Manutenção', 2000.00, 220, 'Reparos gerais no restaurante'),
('Social Media', 2500.00, 180, 'Gestão de redes sociais'),
('Auditor interno', 4000.00, 220, 'Auditoria financeira'),
('Diretor de Operações', 8000.00, 220, 'Direção executiva');

INSERT INTO FUNCIONARIO (id_cargo, nome, cpf, email, telefone, data_admissao) VALUES
(1, 'Marcos Oliveira', '111.222.333-01', 'marcos@rest.com', '1191111-0001', '2023-01-10'),
(2, 'Juliana Mendes', '111.222.333-02', 'juliana@rest.com', '1191111-0002', '2023-01-15'),
(3, 'Roberto Santos', '111.222.333-03', 'roberto@rest.com', '1191111-0003', '2023-02-01'),
(4, 'Fernanda Souza', '111.222.333-04', 'fernanda@rest.com', '1191111-0004', '2023-02-10'),
(5, 'Lucas Pereira', '111.222.333-05', 'lucas@rest.com', '1191111-0005', '2023-03-01'),
(6, 'Patricia Lima', '111.222.333-06', 'patricia@rest.com', '1191111-0006', '2023-03-12'),
(7, 'Bruno Costa', '111.222.333-07', 'bruno@rest.com', '1191111-0007', '2023-04-01'),
(8, 'Camila Rocha', '111.222.333-08', 'camila@rest.com', '1191111-0008', '2023-04-15'),
(9, 'Diego Martins', '111.222.333-09', 'diego@rest.com', '1191111-0009', '2023-05-01'),
(10, 'Elaine Dias', '111.222.333-10', 'elaine@rest.com', '1191111-0010', '2023-05-20'),
(11, 'Fabio Alencar', '111.222.333-11', 'fabio@rest.com', '1191111-0011', '2023-06-01'),
(12, 'Gisele Freitas', '111.222.333-12', 'gisele@rest.com', '1191111-0012', '2023-06-10'),
(13, 'Heitor Pedro', '111.222.333-13', 'heitor@rest.com', '1191111-0013', '2023-07-01'),
(14, 'Ingrid Ribeiro', '111.222.333-14', 'ingrid@rest.com', '1191111-0014', '2023-07-15'),
(15, 'Jorge Mendes', '111.222.333-15', 'jorge@rest.com', '1191111-0015', '2023-08-01'),
(16, 'Kelly Azevedo', '111.222.333-16', 'kelly@rest.com', '1191111-0016', '2023-08-10'),
(17, 'Leandro Farias', '111.222.333-17', 'leandro@rest.com', '1191111-0017', '2023-09-01'),
(18, 'Monica Prado', '111.222.333-18', 'monica@rest.com', '1191111-0018', '2023-09-15'),
(19, 'Nelson Antunes', '111.222.333-19', 'nelson@rest.com', '1191111-0019', '2023-10-01'),
(20, 'Priscila Moraes', '111.222.333-20', 'priscila@rest.com', '1191111-0020', '2023-10-15'),
(21, 'Renato Nogueira', '111.222.333-21', 'renato@rest.com', '1191111-0021', '2023-11-01'),
(22, 'Simone Carvalho', '111.222.333-22', 'simone@rest.com', '1191111-0022', '2023-11-10'),
(23, 'Tadeu Moreira', '111.222.333-23', 'tadeu@rest.com', '1191111-0023', '2023-12-01'),
(24, 'Vanesa Campelo', '111.222.333-24', 'vanesa@rest.com', '1191111-0024', '2023-12-15'),
(25, 'Wagner Hugo', '111.222.333-25', 'wagner@rest.com', '1191111-0025', '2024-01-10'),
(26, 'Yasmin Camargo', '111.222.333-26', 'yasmin@rest.com', '1191111-0026', '2024-01-20'),
(27, 'Zeca Meneghel', '111.222.333-27', 'zeca@rest.com', '1191111-0027', '2024-02-01'),
(28, 'Alice Gagarin', '111.222.333-28', 'aliceg@rest.com', '1191111-0028', '2024-02-15'),
(29, 'Breno Bragança', '111.222.333-29', 'brenob@rest.com', '1191111-0029', '2024-03-01'),
(30, 'Carla Teodoro', '111.222.333-30', 'carlat@rest.com', '1191111-0030', '2024-03-15');

INSERT INTO FORNECEDOR (empresa, cnpj, nome_representante, email, telefone) VALUES
('Hortifrúti Bom Preço', '10.000.000/0001-01', 'João Horti', 'vendas1@forn.com', '113333-0001'),
('Distribuidora Geladas', '20.000.000/0001-02', 'Carla Bebidas', 'vendas2@forn.com', '113333-0002'),
('Carnes Nobres Prime', '30.000.000/0001-03', 'Antônio Carnes', 'vendas3@forn.com', '113333-0003'),
('Laticínios Serra Azul', '40.000.000/0001-04', 'Mário Laticinios', 'vendas4@forn.com', '113333-0004'),
('Padaria Trigo Dourado', '50.000.000/0001-05', 'Ana Pães', 'vendas5@forn.com', '113333-0005'),
('Peixaria Boa Pesca', '60.000.000/0001-06', 'Pedro Peixes', 'vendas6@forn.com', '113333-0006'),
('Embalagens EcoPack', '70.000.000/0001-07', 'Lucia Embalagens', 'vendas7@forn.com', '113333-0007'),
('Temperos do Mundo', '80.000.000/0001-08', 'Beto Temperos', 'vendas8@forn.com', '113333-0008'),
('Limpeza Total Química', '90.000.000/0001-09', 'Rosa Limpeza', 'vendas9@forn.com', '113333-0009'),
('Vinhos & Adega Fina', '11.000.000/0001-11', 'Jacques Vinho', 'vendas10@forn.com', '113333-0010'),
('Café Gourmet do Cerrado', '12.000.000/0001-12', 'Tiago Café', 'vendas11@forn.com', '113333-0011'),
('Massas Frescas Bella Italia', '13.000.000/0001-13', 'Gina Massa', 'vendas12@forn.com', '113333-0012'),
('Hortifrúti Verde Vida', '14.000.000/0001-14', 'Marcos Verde', 'vendas13@forn.com', '113333-0013'),
('Açougues Boi Gordo', '15.000.000/0001-15', 'Silvio Boi', 'vendas14@forn.com', '113333-0014'),
('Bebidas Express Distribuidora', '16.000.000/0001-16', 'Fernanda Express', 'vendas15@forn.com', '113333-0015'),
('Sorvetes Gelatoart', '17.000.000/0001-17', 'Giovanni Gelato', 'vendas16@forn.com', '113333-0016'),
('Molhos & Cia', '18.000.000/0001-18', 'Helena Molho', 'vendas17@forn.com', '113333-0017'),
('Frios & Cia', '19.000.000/0001-19', 'Igor Frios', 'vendas18@forn.com', '113333-0018'),
('Orgânicos da Terra', '21.000.000/0001-21', 'Julia Organica', 'vendas19@forn.com', '113333-0019'),
('Grãos & Cereais Ouro', '22.000.000/0001-22', 'Kevin Graos', 'vendas20@forn.com', '113333-0020'),
('Aves & Cia', '23.000.000/0001-23', 'Larissa Frango', 'vendas21@forn.com', '113333-0021'),
('Fornalha de Lenha', '24.000.000/0001-24', 'Miguel Lenha', 'vendas22@forn.com', '113333-0022'),
('Conservas Finas', '25.000.000/0001-25', 'Nadia Conserva', 'vendas23@forn.com', '113333-0023'),
('Doces Caseiros Vovó', '26.000.000/0001-26', 'Otavio Doce', 'vendas24@forn.com', '113333-0024'),
('Gelo & Neve', '27.000.000/0001-27', 'Paula Gelo', 'vendas25@forn.com', '113333-0025'),
('Oleos & Azeites', '28.000.000/0001-28', 'Quentin Azeite', 'vendas26@forn.com', '113333-0026'),
('Queijos Artesanais', '29.000.000/0001-29', 'Raquel Queijo', 'vendas27@forn.com', '113333-0027'),
('Ervas & Especiarias', '31.000.000/0001-31', 'Samanta Erva', 'vendas28@forn.com', '113333-0028'),
('Utensílios Profissionais', '32.000.000/0001-32', 'Tomas Utensilio', 'vendas29@forn.com', '113333-0029'),
('Tecnologia para Restaurantes', '33.000.000/0001-33', 'Vera Tech', 'vendas30@forn.com', '113333-0030');

INSERT INTO MESA (capacidade, local, numero, status) VALUES
(4, 'Salão Principal', 1, 1),
(4, 'Salão Principal', 2, 1),
(4, 'Salão Principal', 3, 1),
(4, 'Salão Principal', 4, 1),
(2, 'Salão Principal', 5, 1),
(2, 'Salão Principal', 6, 1),
(2, 'Salão Principal', 7, 1),
(6, 'Salão Principal', 8, 1),
(6, 'Salão Principal', 9, 1),
(8, 'Salão Principal', 10, 1),
(2, 'Varanda', 11, 1),
(2, 'Varanda', 12, 1),
(4, 'Varanda', 13, 1),
(4, 'Varanda', 14, 1),
(6, 'Varanda', 15, 1),
(2, 'Área Externa', 16, 1),
(4, 'Área Externa', 17, 1),
(4, 'Área Externa', 18, 1),
(6, 'Área Externa', 19, 1),
(8, 'Área Externa', 20, 1),
(2, 'Área VIP', 21, 1),
(4, 'Área VIP', 22, 1),
(6, 'Área VIP', 23, 1),
(8, 'Área VIP', 24, 1),
(10, 'Área VIP', 25, 1),
(4, 'Mezanino', 26, 1),
(4, 'Mezanino', 27, 1),
(6, 'Mezanino', 28, 1),
(2, 'Mezanino', 29, 1),
(8, 'Mezanino', 30, 1);

INSERT INTO RESERVA (id_cliente, data_reserva, horario, qnt_pessoas, status) VALUES
(1, '2026-06-10', '19:30:00', 4, 1),
(2, '2026-06-10', '20:00:00', 2, 1),
(3, '2026-06-11', '21:00:00', 6, 1),
(4, '2026-06-11', '19:00:00', 2, 1),
(5, '2026-06-12', '20:30:00', 4, 1),
(6, '2026-06-12', '21:15:00', 8, 1),
(7, '2026-06-13', '19:45:00', 3, 1),
(8, '2026-06-13', '20:00:00', 5, 1),
(9, '2026-06-14', '18:30:00', 2, 1),
(10, '2026-06-14', '21:00:00', 4, 1),
(11, '2026-06-15', '19:30:00', 6, 1),
(12, '2026-06-15', '20:30:00', 2, 1),
(13, '2026-06-16', '19:00:00', 4, 1),
(14, '2026-06-16', '21:30:00', 10, 1),
(15, '2026-06-17', '20:00:00', 4, 1),
(16, '2026-06-17', '21:00:00', 2, 1),
(17, '2026-06-18', '19:15:00', 6, 1),
(18, '2026-06-18', '20:45:00', 3, 1),
(19, '2026-06-19', '19:30:00', 4, 1),
(20, '2026-06-19', '21:00:00', 8, 1),
(21, '2026-06-20', '19:00:00', 2, 1),
(22, '2026-06-20', '20:15:00', 5, 1),
(23, '2026-06-21', '18:45:00', 4, 1),
(24, '2026-06-21', '21:15:00', 6, 1),
(25, '2026-06-22', '19:30:00', 2, 1),
(26, '2026-06-22', '20:30:00', 4, 1),
(27, '2026-06-23', '19:00:00', 7, 1),
(28, '2026-06-23', '21:00:00', 2, 1),
(29, '2026-06-24', '19:45:00', 4, 1),
(30, '2026-06-24', '20:30:00', 6, 1);

INSERT INTO MESA_RESERVA (id_reserva, id_mesa, hora_chegada, hora_saida, observacao) VALUES
(1, 1, '19:25:00', '21:30:00', 'Cliente pediu cadeira de bebê'),
(2, 3, '19:55:00', '22:00:00', 'Aniversário'),
(3, 8, '20:50:00', '23:00:00', 'Reserva grande de família'),
(4, 5, '18:55:00', '20:30:00', 'Jantar de negócios'),
(5, 2, '20:25:00', '22:30:00', 'Nenhum'),
(6, 10, '21:10:00', '23:30:00', 'Comemoração de formatura'),
(7, 11, '19:40:00', '21:30:00', 'Mesa na varanda preferencial'),
(8, 13, '19:50:00', '22:00:00', 'Nenhum'),
(9, 16, '18:25:00', '20:00:00', 'Área externa'),
(10, 4, '20:55:00', '23:00:00', 'Nenhum'),
(11, 9, '19:25:00', '21:45:00', 'Aniversário'),
(12, 12, '20:25:00', '22:15:00', 'Nenhum'),
(13, 17, '18:50:00', '21:00:00', 'Área externa'),
(14, 25, '21:25:00', '23:59:00', 'Área VIP completa'),
(15, 6, '19:55:00', '22:00:00', 'Nenhum'),
(16, 18, '20:50:00', '22:30:00', 'Nenhum'),
(17, 15, '19:10:00', '21:30:00', 'Varanda espaçosa'),
(18, 19, '20:40:00', '23:00:00', 'Nenhum'),
(19, 7, '19:25:00', '21:30:00', 'Nenhum'),
(20, 24, '20:55:00', '23:30:00', 'VIP'),
(21, 20, '18:50:00', '20:45:00', 'Nenhum'),
(22, 21, '20:10:00', '22:30:00', 'Casal'),
(23, 22, '18:40:00', '21:00:00', 'Nenhum'),
(24, 23, '21:10:00', '23:15:00', 'Aniversário'),
(25, 26, '19:25:00', '21:15:00', 'Mezanino'),
(26, 27, '20:25:00', '22:30:00', 'Nenhum'),
(27, 28, '18:55:00', '21:30:00', 'Família'),
(28, 29, '20:55:00', '22:45:00', 'Nenhum'),
(29, 30, '19:40:00', '22:00:00', 'Mesa grande mezanino'),
(30, 14, '20:25:00', '22:30:00', 'Nenhum');

INSERT INTO CATEGORIA_PRODUTO (tipo, ativo, descricao) VALUES
('Bebidas Não Alcoólicas', 1, 'Sucos, refrigerantes e água'),
('Bebidas Alcoólicas', 1, 'Cervejas, destilados e coquetéis'),
('Pratos Principais', 1, 'Massas, carnes e pratos executivos'),
('Sobremesas', 1, 'Doces, bolos e sorvetes'),
('Entradas e Aperitivos', 1, 'Porções, saladas e petiscos'),
('Massas Frescas', 1, 'Massas artesanais da casa'),
('Pizzas', 1, 'Pizzas tradicionais e especiais'),
('Churrasco', 1, 'Cortes nobres assados na brasa'),
('Sanduíches e Burgers', 1, 'Lanches artesanais'),
('Sopas e Caldos', 1, 'Opções quentes para dias frios'),
('Saladas Especiais', 1, 'Pratos leves e fit'),
('Frutos do Mar', 1, 'Peixes e camarões frescos'),
('Menu Kids', 1, 'Pratos infantis'),
('Cafés e Chás', 1, 'Bebidas quentes'),
('Vinhos Tintos', 1, 'Carta de vinhos tintos nacionais e importados'),
('Vinhos Brancos e Rosés', 1, 'Vinhos leves e refrescantes'),
('Espumantes', 1, 'Bebidas comemorativas'),
('Drinques com Álcool', 1, 'Caipirinhas e coquetéis da casa'),
('Drinques Sem Álcool', 1, 'Mocktails refrescantes'),
('Sucos Naturais', 1, 'Frutas da estação'),
('Refrigerantes', 1, 'Latas e garrafas 350ml'),
('Águas', 1, 'Com e sem gás'),
('Porções de Frios', 1, 'Queijos e embutidos'),
('Petiscos de Boteco', 1, 'Porções tradicionais'),
('Tortas e Bolos', 1, 'Fatias do dia'),
('Sorvetes Artesanais', 1, 'Taças e bolas'),
('Menus Executivos', 1, 'Opções de almoço rápido'),
('Acompanhamentos', 1, 'Arroz, fritas, farofa'),
('Molhos Especiais', 1, 'Molhos da casa'),
('Produtos Veganos', 1, 'Opções sem ingredientes animais');

INSERT INTO PRODUTO (id_categoria, nome, preco, disponibilidade, descricao) VALUES
(1, 'Refrigerante Cola Lata', 6.00, 1, 'Lata 350ml'),
(1, 'Suco Natural de Laranja', 9.00, 1, 'Copo 400ml'),
(2, 'Cerveja Pilsen Artesanal', 14.00, 1, 'Garrafa 600ml'),
(3, 'Filé Parmegiana', 45.00, 1, 'Acompanha arroz e fritas'),
(3, 'Espaguete à Bolonhesa', 38.00, 1, 'Massa fresca ao molho caseiro'),
(4, 'Pudim de Leite', 12.00, 1, 'Fatia tradicional'),
(5, 'Batata Frita com Cheddar e Bacon', 28.00, 1, 'Porção grande'),
(6, 'Ravioli de Abóbora', 42.00, 1, 'Molho sálvia e manteiga'),
(7, 'Pizza Margherita', 55.00, 1, 'Mussarela, tomate e manjericão'),
(8, 'Picanha na Brasa', 89.00, 1, 'Acompanha vinagrete e farofa'),
(9, 'Burger Artesanal Cheddar Bacon', 34.00, 1, 'Blend 180g'),
(10, 'Caldo Verde', 22.00, 1, 'Com couve e paio'),
(11, 'Salada Caesar com Frango', 29.00, 1, 'Alface americana, molho caesar e crostini'),
(12, 'Camarão na Moranga', 95.00, 1, 'Serve duas pessoas'),
(13, 'Mini Filé com Fritas Kids', 25.00, 1, 'Porção infantil'),
(14, 'Café Expresso', 6.00, 1, 'Café curto e encorpado'),
(15, 'Vinho Tinto Cabernet Sauvignon', 75.00, 1, 'Garrafa 750ml'),
(16, 'Vinho Branco Chardonnay', 70.00, 1, 'Garrafa 750ml'),
(17, 'Espumante Moscatel', 80.00, 1, 'Garrafa 750ml'),
(18, 'Caipirinha de Limão', 18.00, 1, 'Cachaça artesanal e limão tahiti'),
(19, 'Mojito Sem Álcool', 16.00, 1, 'Hortelã, limão e água com gás'),
(20, 'Suco Verde Detox', 12.00, 1, 'Couve, limão, gengibre e maçã'),
(21, 'Água Mineral com Gás', 5.00, 1, 'Garrafa 500ml'),
(22, 'Tábua de Frios mista', 58.00, 1, 'Queijos e presunto parma'),
(23, 'Bolinho de Bacalhau (6un)', 36.00, 1, 'Receita tradicional portuguesa'),
(24, 'Torta Holandesa', 14.00, 1, 'Fatia com cobertura de chocolate'),
(25, 'Taça de Sorvete de Chocolate', 15.00, 1, 'Duas bolas com calda'),
(26, 'Prato Executivo Frango Grelhado', 26.00, 1, 'Almoço dia de semana'),
(27, 'Porção de Arroz Branco', 10.00, 1, 'Acompanhamento'),
(28, 'Hambúrguer Vegano de Grão de Bico', 32.00, 1, 'Opção 100% vegetal');

INSERT INTO FORNECIMENTO (id_produto, id_fornecedor, unidade_medida, preco_acordado, prazo_entregas) VALUES
(1, 2, 'Fardo', 36.00, '2026-06-12'),
(2, 1, 'Quilograma', 4.50, '2026-06-11'),
(3, 2, 'Fardo', 20.00, '2026-06-13'),
(4, 3, 'Quilograma', 28.50, '2026-06-15'),
(5, 3, 'Quilograma', 45.00, '2026-06-14'),
(6, 10, 'Pacote', 12.00, '2026-06-12'),
(7, 4, 'Litro', 32.00, '2026-06-13'),
(8, 6, 'Quilograma', 52.00, '2026-06-11'),
(9, 6, 'Quilograma', 40.00, '2026-06-12'),
(10, 4, 'Bandeja', 18.00, '2026-06-15'),
(11, 4, 'Unidade', 22.00, '2026-06-14'),
(12, 1, 'Quilograma', 6.00, '2026-06-11'),
(13, 13, 'Unidade', 15.00, '2026-06-13'),
(14, 1, 'Quilograma', 5.00, '2026-06-12'),
(15, 11, 'Caixa', 120.00, '2026-06-16'),
(16, 2, 'Fardo', 45.00, '2026-06-13'),
(17, 11, 'Garrafa', 35.00, '2026-06-15'),
(18, 2, 'Litro', 25.00, '2026-06-12'),
(19, 12, 'Quilograma', 40.00, '2026-06-14'),
(20, 10, 'Quilograma', 22.00, '2026-06-12'),
(21, 4, 'Quilograma', 26.00, '2026-06-13'),
(22, 3, 'Quilograma', 30.00, '2026-06-11'),
(23, 13, 'Quilograma', 12.00, '2026-06-14'),
(24, 1, 'Quilograma', 8.00, '2026-06-12'),
(25, 4, 'Quilograma', 55.00, '2026-06-15'),
(26, 5, 'Quilograma', 4.00, '2026-06-11'),
(27, 9, 'Frasco', 3.00, '2026-06-16'),
(28, 5, 'Quilograma', 14.00, '2026-06-12'),
(29, 4, 'Quilograma', 20.00, '2026-06-13'),
(30, 14, 'Litro', 18.00, '2026-06-14');

INSERT INTO PEDIDO (id_funcionario, id_mesa, id_reserva, tipo_consumo, status, observacao) VALUES
(3, 1, 1, 'Presencial', 1, 'Sem cebola na salada'),
(4, 3, 2, 'Presencial', 1, 'Rápido por favor'),
(5, 5, 3, 'Presencial', 1, 'Mesa de aniversário'),
(6, 7, 4, 'Presencial', 1, 'N/A'),
(7, 9, 5, 'Presencial', 1, 'Ponto da carne mal passado'),
(8, 11, 6, 'Presencial', 1, 'VIP'),
(9, 13, 7, 'Presencial', 1, 'N/A'),
(10, 15, 8, 'Presencial', 1, 'Cadeira extra'),
(11, 17, 9, 'Presencial', 1, 'N/A'),
(12, 19, 10, 'Presencial', 1, 'Cliente alérgico a camarão'),
(13, 21, 11, 'Presencial', 1, 'N/A'),
(14, 2, 12, 'Presencial', 1, 'N/A'),
(15, 4, 13, 'Presencial', 1, 'N/A'),
(16, 6, 14, 'Presencial', 1, 'Bebida sem gelo'),
(17, 8, 15, 'Presencial', 1, 'N/A'),
(18, 10, 16, 'Presencial', 1, 'N/A'),
(19, 12, 17, 'Presencial', 1, 'Mesa reservada'),
(20, 14, 18, 'Presencial', 1, 'N/A'),
(21, 16, 19, 'Presencial', 1, 'N/A'),
(22, 18, 20, 'Presencial', 1, 'N/A'),
(23, NULL, NULL, 'Delivery', 1, 'Entregar na portaria'),
(24, NULL, NULL, 'Delivery', 1, 'Tocar interfone 42'),
(25, NULL, NULL, 'Delivery', 0, 'Cliente vai retirar no balcão'),
(26, 20, 21, 'Presencial', 1, 'N/A'),
(27, 22, 22, 'Presencial', 1, 'N/A'),
(28, 24, 23, 'Presencial', 1, 'N/A'),
(29, 26, 24, 'Presencial', 1, 'N/A'),
(30, 28, 25, 'Presencial', 1, 'N/A'),
(3, NULL, NULL, 'Delivery', 1, 'Casa de portão preto'),
(4, NULL, NULL, 'Delivery', 0, 'Em rota de preparo');

INSERT INTO ITEM_PEDIDO (id_pedido, id_produto, status, quantidade, preco, observacao) VALUES
(1, 4, 1, 2, 55.00, 'Bem passado'),
(1, 1, 1, 2, 6.00, 'Gelado'),
(2, 6, 1, 1, 38.00, 'N/A'),
(3, 5, 1, 3, 68.00, 'Ponto ao ponto'),
(4, 8, 1, 2, 65.00, 'Sem alcaparras'),
(5, 12, 1, 1, 32.00, 'Molho separado'),
(6, 20, 1, 2, 58.00, 'Massa fina'),
(7, 22, 1, 2, 36.00, 'Sem cebola'),
(8, 10, 1, 4, 14.00, 'Sobremesa'),
(9, 15, 1, 3, 24.00, 'Gelada'),
(10, 18, 1, 2, 20.00, 'Com pouco açúcar'),
(11, 25, 1, 2, 70.00, 'Completa'),
(12, 3, 1, 2, 4.50, 'Sem gás'),
(13, 9, 1, 1, 75.00, 'Quente'),
(14, 11, 1, 2, 22.00, 'Servir após o prato'),
(15, 14, 1, 1, 34.00, 'Molho à parte'),
(16, 17, 1, 2, 26.00, 'Temperatura ambiente'),
(17, 19, 1, 2, 6.50, 'Curto e forte'),
(18, 21, 1, 1, 62.00, 'Borda recheada'),
(19, 23, 1, 1, 25.00, 'Bem cozido'),
(20, 24, 1, 2, 26.00, 'Com pão'),
(21, 22, 1, 1, 36.00, 'Embalagem viagem'),
(22, 20, 1, 1, 58.00, 'Cortada em 8 pedaços'),
(23, 1, 0, 2, 6.00, 'Lata'),
(24, 28, 1, 2, 38.00, 'Vegano'),
(25, 29, 1, 1, 18.00, 'Sem glúten'),
(26, 30, 1, 2, 16.00, 'Sorvete de baunilha'),
(27, 2, 1, 3, 9.00, 'Natural'),
(28, 7, 1, 1, 62.00, 'Extra cremoso'),
(29, 13, 1, 2, 28.00, 'Bem crocante');

INSERT INTO PAGAMENTO (id_pedido, metodo, status, valor_pago) VALUES
(1, 'Cartão de Crédito', 1, 122.00),
(2, 'Pix', 1, 38.00),
(3, 'Cartão de Débito', 1, 204.00),
(4, 'Dinheiro', 1, 130.00),
(5, 'Cartão de Crédito', 1, 32.00),
(6, 'Pix', 1, 116.00),
(7, 'Cartão de Débito', 1, 72.00),
(8, 'Cartão de Crédito', 1, 56.00),
(9, 'Pix', 1, 72.00),
(10, 'Dinheiro', 1, 40.00),
(11, 'Cartão de Crédito', 1, 140.00),
(12, 'Pix', 1, 9.00),
(13, 'Cartão de Débito', 1, 75.00),
(14, 'Cartão de Crédito', 1, 44.00),
(15, 'Dinheiro', 1, 34.00),
(16, 'Pix', 1, 52.00),
(17, 'Cartão de Débito', 1, 13.00),
(18, 'Cartão de Crédito', 1, 62.00),
(19, 'Pix', 1, 25.00),
(20, 'Dinheiro', 1, 52.00),
(21, 'Cartão de Crédito', 1, 36.00),
(22, 'Pix', 1, 58.00),
(23, 'Dinheiro', 0, 12.00),
(24, 'Cartão de Crédito', 1, 76.00),
(25, 'Cartão de Débito', 1, 18.00),
(26, 'Pix', 1, 32.00),
(27, 'Cartão de Crédito', 1, 27.00),
(28, 'Dinheiro', 1, 62.00),
(29, 'Pix', 1, 56.00),
(30, 'Cartão de Crédito', 0, 12.00);

-- 3. Índices
-- Criação de pelo menos um índice (INDEX) em um atributo.
-- Incluir justificativa para a escolha do campo indexado, explicando o benefício em termos de desempenho.

CREATE INDEX IDX_PEDIDO_DATA ON PEDIDO (data);
	/* Justificativa: O índice IDX_PEDIDO_DATA foi criado na coluna DATA da tabela PEDIDO porque, no dia a dia do restaurante, o sistema
	deve realizar consultas frequentes filtrando pedidos por dia ou período. Como essa coluna não é chave primária nem possui restrição 
	UNIQUE, o índice evita que seja feita uma leitura completa da tabela linha por linha, tornando as buscas e ordenações muito mais rápidas.*/

-- 4. Scripts Adicionais (Inserir como Comentários no Arquivo)
-- Inclua os seguintes exemplos no arquivo .sql, comentados, como demonstração de domínio técnico:

-- Exclusão do banco de dados.
-- DROP DATABASE IF EXISTS RESTAURANTE;
	/* Comentário: Apaga o banco RESTAURANTE inteiro e todas as 13 tabelas que estão dentro dele. O IF EXISTS evita erro caso o banco já tenha 
sido apagado ou não exista. */

-- Exclusão de uma tabela.
-- DROP TABLE IF EXISTS FORNECIMENTO;
	/* Comentário: Apaga apenas a tabela FORNECIMENTO e todos os registros dela. Escolhemos essa tabela porque ela é uma tabela associativa e 
	não possui chaves estrangeiras em outras tabelas. */

-- Exclusão de uma coluna de uma tabela.
-- ALTER TABLE CARGO DROP COLUMN descricao;
	/* Comentário: Remove a coluna DESCRICAO na tabela CARGO e mantém as outras colunas.*/

-- Adição de uma nova coluna (não prevista na modelagem inicial).
-- ALTER TABLE CLIENTE ADD COLUMN pontos_fidelidade INT NOT NULL DEFAULT 0;
	/* Comentário: Cria uma coluna nova chamada PONTOS_FIDELIDADE na tabela CLIENTE. Guarda números inteiros, não aceita vazio e 
	todo cliente começa com 0 pontos. */

-- Edição de um registro específico.
-- UPDATE PRODUTO SET preco = 49.90 WHERE id_produto = 4;
	/* Comentário: Atualiza o preço do produto com o ID 4 para 49.90. Se não houvesse o "WHERE" todos os produtos do cardápio seriam mudados. */

-- Exclusão de um registro específico.
 -- DELETE FROM FORNECEDOR WHERE id_fornecedor = 32;
	/* Comentário: Apaga apenas a linha do fornecedor com o ID 32. O ID dele não está vinculado a nenhum produto na tabela FORNECIMENTO, 
	então, não haverá erro de chave estrangeira. */

-- 5. Consultas SQL (Queries)
-- Caso sua modelagem não permita algum dos JOINs solicitados, incluir uma justificativa técnica.
-- As consultas devem utilizar, sempre que possível, os seguintes comandos SQL: WHERE e diferentes tipos de operadores; GROUP BY; HAVING e ORDER BY

-- Duas consultas simples, utilizando apenas uma tabela.
SELECT id_categoria, COUNT(*) AS total_produtos, ROUND(AVG(preco), 2) AS preco_medio
FROM PRODUTO
WHERE disponibilidade = 1 AND (preco BETWEEN 15.00 
	AND 80.00 OR id_categoria IN (3, 7, 8, 12))
GROUP BY id_categoria
HAVING AVG(preco) > 30.00
ORDER BY preco_medio DESC;

SELECT metodo AS forma_pagamento, COUNT(*) AS qtd_transacoes, 
	SUM(valor_pago) AS total_arrecadado, ROUND(AVG(valor_pago), 2) AS ticket_medio
FROM PAGAMENTO
WHERE status = 1 AND metodo IN ('Pix', 'Cartão de Crédito', 'Cartão de Débito')
	AND valor_pago > 20.00
GROUP BY metodo
HAVING SUM(valor_pago) > 150.00
ORDER BY total_arrecadado DESC;

-- Quatro consultas com duas tabelas, utilizando INNER JOIN.
SELECT f.id_funcionario, f.nome AS funcionario, c.nome_cargo AS cargo, c.carga_horaria, c.salario
FROM FUNCIONARIO f
INNER JOIN CARGO c ON f.id_cargo = c.id_cargo
WHERE c.ativo = 1 AND c.salario >= 2500.00 AND f.data_admissao >= '2023-06-01'
ORDER BY c.salario DESC, f.nome ASC;

SELECT cp.id_categoria, cp.tipo AS categoria, COUNT(p.id_produto) AS qtd_produtos,
	ROUND(AVG(p.preco), 2) AS preco_medio
FROM CATEGORIA_PRODUTO cp
INNER JOIN PRODUTO p ON cp.id_categoria = p.id_categoria 
WHERE cp.ativo = 1 AND p.disponibilidade = 1
GROUP BY cp.id_categoria, cp.tipo
HAVING AVG(p.preco) > 25.00
ORDER BY preco_medio DESC;

SELECT f.id_funcionario, f.nome AS funcionario, COUNT(p.id_pedido) AS total_pedidos
FROM FUNCIONARIO f
INNER JOIN PEDIDO p ON f.id_funcionario = p.id_funcionario
WHERE p.status = 1 AND p.tipo_consumo IN ('Presencial', 'Delivery')
GROUP BY f.id_funcionario, f.nome
HAVING COUNT(p.id_pedido) >= 1
ORDER BY total_pedidos DESC, f.nome ASC;

SELECT pr.id_produto, pr.nome AS produto, SUM(ip.quantidade) AS unidades_vendidas,
	SUM(ip.quantidade * ip.preco) AS faturamento_bruto
FROM ITEM_PEDIDO ip
INNER JOIN PRODUTO pr ON ip.id_produto = pr.id_produto
WHERE ip.status = 1 AND ip.preco > 10.00
GROUP BY pr.id_produto, pr.nome
HAVING SUM(ip.quantidade * ip.preco) > 50.00
ORDER BY faturamento_bruto DESC;

-- Uma consulta com LEFT JOIN.
SELECT f.id_fornecedor, f.empresa, f.nome_representante, COUNT(fn.id_produto) AS qtd_produtos_fornecidos,
    COALESCE(MIN(fn.preco_acordado), 0.00) AS menor_preco_acordado
FROM FORNECEDOR f
LEFT JOIN FORNECIMENTO fn ON f.id_fornecedor = fn.id_fornecedor
WHERE fn.id_fornecedor IS NULL OR fn.preco_acordado < 50.00
GROUP BY f.id_fornecedor, f.empresa, f.nome_representante
HAVING COUNT(fn.id_produto) >= 0
ORDER BY qtd_produtos_fornecidos ASC, f.empresa ASC;

-- Uma consulta com RIGHT JOIN.
SELECT cp.id_categoria, cp.tipo AS categoria, COUNT(p.id_produto) AS total_produtos, 
	COALESCE(MAX(p.preco), 0.00) AS maior_preco
FROM PRODUTO p
RIGHT JOIN CATEGORIA_PRODUTO cp ON p.id_categoria = cp.id_categoria
WHERE cp.ativo = 1
GROUP BY cp.id_categoria, cp.tipo
HAVING COUNT(p.id_produto) >= 0
ORDER BY total_produtos ASC, cp.tipo ASC;

-- Uma consulta com três tabelas, utilizando qualquer tipo de JOIN.
SELECT r.id_reserva, c.nome AS cliente, c.telefone, r.data_reserva, r.horario AS horario_agendado, 
	r.qnt_pessoas, mr.id_mesa, mr.hora_chegada, mr.hora_saida
FROM RESERVA r
INNER JOIN CLIENTE c ON r.id_cliente = c.id_cliente
INNER JOIN MESA_RESERVA mr ON r.id_reserva = mr.id_reserva
WHERE r.status = 1 AND r.qnt_pessoas >= 4
ORDER BY r.data_reserva ASC, r.qnt_pessoas DESC;

-- Uma consulta com quatro tabelas, utilizando qualquer tipo de JOIN.
SELECT p.tipo_consumo, pg.metodo AS forma_pagamento, COUNT(DISTINCT p.id_pedido) AS qtd_pedidos,
	COUNT(DISTINCT c.id_cliente) AS qtd_clientes_com_reserva, 
    SUM(pg.valor_pago) AS receita_total
FROM PEDIDO p
LEFT JOIN RESERVA r ON p.id_reserva = r.id_reserva
LEFT JOIN CLIENTE c ON r.id_cliente = c.id_cliente
INNER JOIN PAGAMENTO pg ON p.id_pedido = pg.id_pedido
WHERE p.status = 1 AND pg.status = 1 AND pg.valor_pago > 30.00
GROUP BY p.tipo_consumo, pg.metodo
HAVING SUM(pg.valor_pago) >= 50.00
ORDER BY receita_total DESC;

-- 6. Recursos Adicionais
-- Criação de três VIEWs.

-- VIEW 1: Cardápio disponível (somente produtos disponíveis de categorias ativas)
CREATE OR REPLACE VIEW VW_CARDAPIO_DISPONIVEL AS
SELECT p.id_produto, p.nome AS produto, cp.tipo AS categoria, p.preco, p.descricao
FROM PRODUTO p
INNER JOIN CATEGORIA_PRODUTO cp ON p.id_categoria = cp.id_categoria
WHERE p.disponibilidade = 1 AND cp.ativo = 1;
	/* Comentário: Mostra o cardápio que pode ser oferecido ao cliente. O garçom consulta a VIEW sem precisar
	escrever o JOIN nem lembrar dos filtros de disponibilidade e categoria ativa. */

-- VIEW 2: Resumo financeiro de cada pedido (cliente da reserva, atendente e quanto já foi pago)
CREATE OR REPLACE VIEW VW_RESUMO_PEDIDOS AS
SELECT pe.id_pedido, pe.data, pe.tipo_consumo,
	COALESCE(c.nome, 'Sem reserva') AS cliente_reserva, f.nome AS atendente,
	COALESCE(SUM(CASE WHEN pg.status = 1 THEN pg.valor_pago END), 0.00) AS total_pago,
	CASE WHEN pe.status = 1 THEN 'Concluído' ELSE 'Em aberto' END AS situacao
FROM PEDIDO pe
INNER JOIN FUNCIONARIO f ON pe.id_funcionario = f.id_funcionario
LEFT JOIN RESERVA r ON pe.id_reserva = r.id_reserva
LEFT JOIN CLIENTE c ON r.id_cliente = c.id_cliente
LEFT JOIN PAGAMENTO pg ON pe.id_pedido = pg.id_pedido
GROUP BY pe.id_pedido, pe.data, pe.tipo_consumo, c.nome, f.nome, pe.status;
	/* Comentário: Junta pedido, funcionário e pagamento em uma só consulta. Como PEDIDO não tem mais id_cliente,
	o cliente é encontrado pela reserva (PEDIDO -> RESERVA -> CLIENTE). Os LEFT JOINs garantem que pedidos sem
	reserva (ex.: delivery) e pedidos ainda sem pagamento também apareçam, com 'Sem reserva' e total_pago = 0. */

-- VIEW 3: Agenda de reservas com cliente e mesa
CREATE OR REPLACE VIEW VW_AGENDA_RESERVAS AS
SELECT r.id_reserva, r.data_reserva, r.horario, c.nome AS cliente, c.telefone,
	r.qnt_pessoas, m.numero AS mesa, m.local, m.capacidade
FROM RESERVA r
INNER JOIN CLIENTE c ON r.id_cliente = c.id_cliente
INNER JOIN MESA_RESERVA mr ON r.id_reserva = mr.id_reserva
INNER JOIN MESA m ON mr.id_mesa = m.id_mesa
WHERE r.status = 1;
	/* Comentário: Lista as reservas ativas com os dados do cliente e da mesa reservada. Usada pela recepção/hostess
	para organizar a chegada dos clientes. */

-- Exemplos de uso das VIEWs:
SELECT * FROM VW_CARDAPIO_DISPONIVEL WHERE preco < 30.00 ORDER BY categoria, preco;
SELECT * FROM VW_RESUMO_PEDIDOS WHERE situacao = 'Em aberto';
SELECT * FROM VW_AGENDA_RESERVAS WHERE data_reserva = '2026-06-16' ORDER BY horario;


-- Criação de dois TRIGGERs, sendo:
-- Um BEFORE
DELIMITER $$
CREATE TRIGGER TRG_ITEM_PEDIDO_BEFORE_INSERT
BEFORE INSERT ON ITEM_PEDIDO
FOR EACH ROW
BEGIN
	IF NEW.quantidade <= 0 THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'A quantidade do item deve ser maior que zero.';
	END IF;

	IF NEW.preco IS NULL OR NEW.preco <= 0 THEN
		SET NEW.preco = (SELECT preco FROM PRODUTO WHERE id_produto = NEW.id_produto);
	END IF;
END$$
DELIMITER ;
	/* Comentário: Executa ANTES de inserir um item no pedido. Bloqueia quantidade zero ou negativa (gera erro) e,
	se o preço vier zerado ou vazio, copia automaticamente o preço atual do produto na tabela PRODUTO. */

-- Teste do TRIGGER BEFORE: o item é inserido com preço 0 e o trigger preenche com o preço do Café Expresso (6.00)
INSERT INTO ITEM_PEDIDO (id_pedido, id_produto, status, quantidade, preco, observacao)
VALUES (30, 16, 0, 2, 0, 'Teste do trigger BEFORE');
SELECT * FROM ITEM_PEDIDO WHERE id_pedido = 30;

-- Teste de erro (descomentar para ver a mensagem do trigger):
-- INSERT INTO ITEM_PEDIDO (id_pedido, id_produto, status, quantidade, preco) VALUES (30, 1, 0, 0, 6.00);

-- Um AFTER
DELIMITER $$
CREATE TRIGGER TRG_PAGAMENTO_AFTER_INSERT
AFTER INSERT ON PAGAMENTO
FOR EACH ROW
BEGIN
	IF NEW.status = 1 THEN
		UPDATE PEDIDO SET status = 1 WHERE id_pedido = NEW.id_pedido;
	END IF;
END$$
DELIMITER ;
	/* Comentário: Executa DEPOIS que um pagamento é registrado. Se o pagamento foi confirmado (status = 1),
	o pedido correspondente é marcado automaticamente como concluído (status = 1). */

-- Teste do TRIGGER AFTER: o pedido 23 está com status 0; após o pagamento confirmado ele passa para 1
SELECT id_pedido, status FROM PEDIDO WHERE id_pedido = 23;
INSERT INTO PAGAMENTO (id_pedido, metodo, status, valor_pago) VALUES (23, 'Pix', 1, 12.00);
SELECT id_pedido, status FROM PEDIDO WHERE id_pedido = 23;


-- Criação de três usuários com permissões distintas.
DROP USER IF EXISTS 'gerente'@'localhost', 'garcom'@'localhost', 'caixa'@'localhost';

CREATE USER 'gerente'@'localhost' IDENTIFIED BY 'Gerente@123';
CREATE USER 'garcom'@'localhost' IDENTIFIED BY 'Garcom@123';
CREATE USER 'caixa'@'localhost' IDENTIFIED BY 'Caixa@123';

-- Gerente: acesso total ao banco RESTAURANTE
GRANT ALL PRIVILEGES ON RESTAURANTE.* TO 'gerente'@'localhost';

-- Garçom: consulta cardápio, clientes, mesas e reservas; registra e altera pedidos e itens
GRANT SELECT ON RESTAURANTE.VW_CARDAPIO_DISPONIVEL TO 'garcom'@'localhost';
GRANT SELECT ON RESTAURANTE.CLIENTE TO 'garcom'@'localhost';
GRANT SELECT ON RESTAURANTE.MESA TO 'garcom'@'localhost';
GRANT SELECT ON RESTAURANTE.RESERVA TO 'garcom'@'localhost';
GRANT SELECT, INSERT, UPDATE ON RESTAURANTE.PEDIDO TO 'garcom'@'localhost';
GRANT SELECT, INSERT, UPDATE ON RESTAURANTE.ITEM_PEDIDO TO 'garcom'@'localhost';

-- Caixa: consulta pedidos e o resumo financeiro; registra pagamentos
GRANT SELECT ON RESTAURANTE.PEDIDO TO 'caixa'@'localhost';
GRANT SELECT ON RESTAURANTE.VW_RESUMO_PEDIDOS TO 'caixa'@'localhost';
GRANT SELECT, INSERT ON RESTAURANTE.PAGAMENTO TO 'caixa'@'localhost';

SHOW GRANTS FOR 'garcom'@'localhost';
SHOW GRANTS FOR 'caixa'@'localhost';
	/* Comentário: Cada usuário recebe apenas o que precisa para sua função (princípio do menor privilégio).
	O DROP USER IF EXISTS no início evita erro caso o script seja executado mais de uma vez, já que
	o DROP DATABASE não apaga usuários. */


-- Exclusão de um dos usuários criados.
DROP USER IF EXISTS 'gerente'@'localhost';
	/* Comentário: Remove o usuário gerente e todas as permissões dele. */


-- Revogação de privilégios de dois usuários
REVOKE UPDATE ON RESTAURANTE.PEDIDO FROM 'garcom'@'localhost';
REVOKE INSERT ON RESTAURANTE.PAGAMENTO FROM 'caixa'@'localhost';

SHOW GRANTS FOR 'garcom'@'localhost';
SHOW GRANTS FOR 'caixa'@'localhost';
	/* Comentário: O garçom continua podendo consultar e criar pedidos, mas não pode mais alterá-los.
	O caixa continua consultando pagamentos, mas não pode mais registrar novos. Os demais privilégios são mantidos. */


-- Exemplo de uso dos comandos:
-- COMMIT
START TRANSACTION;
	UPDATE PRODUTO SET preco = preco * 1.10 WHERE id_categoria = 15;
	UPDATE PRODUTO SET disponibilidade = 0 WHERE id_produto = 19;
COMMIT;
SELECT id_produto, nome, preco, disponibilidade FROM PRODUTO WHERE id_categoria = 15 OR id_produto = 19;
	/* Comentário: As duas alterações (reajuste de 10% nos vinhos tintos e retirada do espumante do cardápio)
	são feitas juntas e o COMMIT grava as mudanças definitivamente no banco. */

-- ROLLBACK
START TRANSACTION;
	DELETE FROM ITEM_PEDIDO WHERE id_pedido = 29;
	SELECT * FROM ITEM_PEDIDO WHERE id_pedido = 29; -- dentro da transação o item já não aparece
ROLLBACK;
SELECT * FROM ITEM_PEDIDO WHERE id_pedido = 29; -- depois do ROLLBACK o item volta a existir
	/* Comentário: O DELETE foi feito por engano dentro de uma transação. O ROLLBACK desfaz tudo desde o
	START TRANSACTION, e o item do pedido 29 volta para a tabela como estava antes. */
