# Sistema de Ordens de Serviço para Manutenção de Baterias

Projeto final da disciplina de Programação Orientada a Objetos 2 — UTFPR Cornélio Procópio.

Integrantes: Marcos Soares Amado, José Lucas Nobrega Trautwein e João Guilherme Nobrega Trautwein.

## Tecnologias

- Java 17
- Swing (interface gráfica)
- PostgreSQL com JDBC
- Maven
- IDE: NetBeans

## Como executar

1. Criar no PostgreSQL um banco chamado `os_baterias`.
2. Executar o script `sql/banco.sql` dentro desse banco.
3. Conferir usuário e senha do PostgreSQL em `src/main/java/dao/Conexao.java`.
4. Abrir o projeto no NetBeans (File > Open Project) e executar (F6). A classe principal é `app.Principal`.

## Usuários de teste

| Login  | Senha | Cargo     |
|--------|-------|-----------|
| admin  | admin | Gerente   |
| ana    | 123   | Atendente |
| carlos | 123   | Técnico   |
| rafael | 123   | Técnico   |

## Organização dos pacotes

- `model` — classes de entidade do diagrama de classes
- `dao` — conexão e comandos SQL
- `controller` — validações e regras
- `view` — telas (JFrame com arquivo .form, editáveis na aba Design do NetBeans)
- `util` — conversões de datas e valores
- `app` — classe principal e teste de conexão
