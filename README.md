# 💳 CoreBank

**CoreBank** é um sistema bancário baseado em uma arquitetura de **microserviços** utilizando **Spring Boot 3**, **Kafka
**, **Eureka** e **MySQL**.
O projeto foi criado como uma aplicação prática para consolidar conhecimentos sobre **microserviços**, **event-driven
architecture** e **Kafka**, explorando também aspectos de **descoberta de serviços** com **Eureka**.

---

## 📚 **Objetivo do Projeto**

O **CoreBank** foi desenvolvido como uma oportunidade prática para consolidar e aplicar conhecimentos adquiridos durante
meus estudos sobre:

- Arquitetura de **Microserviços**
- **Comunicação assíncrona** com **Kafka**
- **Service Discovery** com **Eureka**

Além disso, o projeto visa explorar boas práticas no desenvolvimento de software, como a documentação com Swagger e a
implementação de testes automatizados.

---

## 🚀 Tecnologias

- Spring Boot 3
- MySQL
- Kafka
- Docker Compose
- Swagger (SpringDoc OpenAPI)
- JUnit

---

## 🏗️ **Arquitetura**

O CoreBank segue uma arquitetura baseada em **microserviços** e **event-driven architecture**:

- **Microserviços**: O sistema é dividido em três microserviços independentes: **Account Service**, **Transaction
  Service** e **Notification Service**, cada um responsável por uma parte específica da funcionalidade do sistema
  bancário.
- **Kafka**: É utilizado como **message broker** para a comunicação assíncrona entre os microserviços. O **Transaction
  Service** emite eventos de transações, e o **Account Service** consome essas mensagens para atualizar os saldos das
  contas. O **Notification Service** também consome eventos para enviar notificações de transações.
- **Eureka**: O **Eureka** serve como o **service discovery** para registrar os microserviços e permitir que eles se
  comuniquem sem a necessidade de configuração estática de IPs ou portas, facilitando a escalabilidade e a integração
  entre os serviços.
- **Swagger**: Toda a documentação das APIs está disponível através do Swagger UI, centralizada no **API Gateway**.
- **Banco de dados**: Cada microserviço possui seu próprio banco de dados MySQL, garantindo o princípio de **banco de
  dados por
  microserviço**.

---

## ✅ TODO List

### Implementado

- Operações básicas para contas, transações e notificações

### Em Progresso

- Marcar notificações como lidas
- Documentação com Swagger

### Planejado

- Autenticação com JWT
- Documentação com Swagger
- Comunicação assíncrona via Kafka

---

## 📖 **Endpoints**

### **Account Service**

| Método | Endpoint         | Descrição       |
|--------|------------------|-----------------|
| `POST` | `/accounts`      | Criar conta     |
| `GET`  | `/accounts`      | Listar contas   |
| `GET`  | `/accounts/{id}` | Consultar saldo |

### **Transaction Service**

| Método | Endpoint             | Descrição           |
|--------|----------------------|---------------------|
| `POST` | `/transactions`      | Criar transação     |
| `GET`  | `/transactions`      | Listar transações   |
| `GET`  | `/transactions/{id}` | Consultar transação |

### **Notification Service**

| Método | Endpoint              | Descrição                    |
|--------|-----------------------|------------------------------|
| `GET`  | `/notifications`      | Listar notificações          |
| `PUT`  | `/notifications/read` | Marcar notificação como lida |

---

## 📚 **Como Executar**

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/corebank.git
   cd corebank
   ```  
2. Suba os serviços com Docker Compose:
   ```bash
   docker compose up
   ```  
3. Acesse os serviços:
    - **Documentação do Swagger**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 🧪 **Testes Automatizados**

Os testes incluem:

- Testes de unidade usando JUnit
- Testes de integração com Spring Kafka Test
- Testes end-to-end com Testcontainers

Para executar os testes:

```bash
./mvnw test
```