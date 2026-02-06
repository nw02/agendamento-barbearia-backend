# Barbearia API - Sistema de Agendamento

Esta é uma API REST desenvolvida com **Spring Boot** para gerenciamento de agendamentos em uma barbearia. O projeto foca em boas práticas de desenvolvimento, validações rigorosas de regras de negócio e um sistema global de tratamento de exceções.

## Tecnologias Utilizadas

* **Java 21**
* **Spring Boot**
* **Spring Data JDBC**
* **H2 Database**
* **Jakarta Validation**
* **Maven**

## 🛠️ Destaques Técnicos

### 1. Tratamento Global de Erros (Global Exception Handler)
Utilizei `@ControllerAdvice` para centralizar o tratamento de erros da aplicação.

### 2. Validações de Regra de Negócio
O sistema possui regras automáticas para garantir a integridade da agenda:
* **Horário Comercial:** Agendamentos permitidos apenas entre 09:00 e 19:00.
* **Intervalos Padronizados:** Validação para permitir apenas agendamentos em "horas cheias" (ex: 10:00, 11:00).
* **Datas Futuras:** Uso de `@Future` para impedir agendamentos no passado.
* **Unicidade:** Bloqueio de cadastros duplicados (E-mail ou Telefone).

## Como Executar o Projeto

1.  **Clonar o repositório:**
    ```bash
    git clone [https://github.com/nw02/agendamento-barbearia-backend.git](https://github.com/nw02/agendamento-barbearia-backend.git)
    ```
2.  **Entrar na pasta do projeto:**
    ```bash
    cd agendamento-barbearia-backend
    ```
3.  **Executar a aplicação:**
    ```bash
    ./mvnw spring-boot:run
    ```
A API estará disponível em `http://localhost:8080`.

## Exemplos de Endpoints

### Criar Agendamento (`POST /agendamentos`)
```json
{
  "servico": "CABELO",
  "prestadorId": 1,
  "clienteId": 1,
  "dataHora": "2026-05-20T14:00:00"
}
