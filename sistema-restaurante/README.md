# 🍽️ Sistema de Restaurante

Sistema desktop para gestão de um restaurante, feito em **Java (Swing)** com banco **MySQL**.
Projeto acadêmico em equipe do curso de Engenharia de Software da Universidade Católica de Brasília (UCB), feito em 2026 nas disciplinas de **Programação Orientada a Objetos** e **Laboratório de Banco de Dados**.

O sistema cadastra clientes, funcionários, cargos, mesas, reservas e pedidos, e gera um relatório de pedidos.

---

## 👩‍💻 Minha parte no projeto

### Aplicação Java: CRUDs de Mesas e Reservas
- Telas de cadastro, edição, exclusão e pesquisa de **mesas** e **reservas** (`MesaView`, `ReservaView`).
- Classes de acesso ao banco com `PreparedStatement` (`ManterMesa`, `ManterReserva`).
- **Validação de datas e horários:** a data é digitada no formato brasileiro (dd/mm/aaaa) e conferida de forma estrita, então datas como 31/02 são recusadas. Depois é convertida para o formato do banco. Reservas com data no passado são bloqueadas.

### Banco de dados: recursos adicionais (MySQL)
- **3 views:** cardápio disponível, resumo financeiro de cada pedido e agenda de reservas com cliente e mesa.
- **2 triggers:**
  - `BEFORE INSERT` em itens do pedido: bloqueia quantidade zero ou negativa e, se o preço vier vazio, copia o preço atual do produto.
  - `AFTER INSERT` em pagamentos: quando o pagamento é confirmado, o pedido é marcado como concluído.
- **Usuários com permissões diferentes** (gerente, garçom e caixa), seguindo o princípio do menor privilégio, com exemplos de `REVOKE` e `DROP USER`.
- **Transações:** exemplos de `COMMIT` e `ROLLBACK`.

Tudo isso está na seção 6 do arquivo [`banco/restaurante.sql`](banco/restaurante.sql).

---

## 🛠️ Tecnologias

`Java` `Java Swing` `MySQL` `JDBC` `NetBeans` `FlatLaf`

---

## 📁 Estrutura

```
├── banco/
│   └── restaurante.sql       # criação do banco, dados, consultas, views, triggers, usuários e transações
├── src/br/com/
│   ├── controle/             # classes de modelo (Mesa, Reserva, Cliente, Pedido...)
│   ├── entidade/             # acesso ao banco (DAO e classes Manter...)
│   └── visao/                # telas em Java Swing
├── lib/                      # bibliotecas .jar (baixar, veja abaixo)
├── nbproject/                # configuração do projeto NetBeans
└── build.xml
```

---

## ▶️ Como rodar

1. **Banco:** no MySQL, execute o arquivo `banco/restaurante.sql`.
2. **Bibliotecas:** baixe e coloque na pasta `lib/`:
   - [MySQL Connector/J 9.3.0](https://dev.mysql.com/downloads/connector/j/) → `mysql-connector-j-9.3.0.jar`
   - [FlatLaf 3.8](https://central.sonatype.com/artifact/com.formdev/flatlaf) → `flatlaf-3.8.jar`
3. **Conexão:** se o usuário ou a senha do seu MySQL forem diferentes, ajuste em `src/br/com/entidade/DAO.java`.
4. **Executar:** abra a pasta no **Apache NetBeans**, clique com o botão direito em `src/br/com/visao/ViewPrincipal.java` e escolha **Run File**.

---

Desenvolvido em equipe · parte de **Luana Almeida Neves** · [LinkedIn](https://www.linkedin.com/in/luana-neves-15767a447)
