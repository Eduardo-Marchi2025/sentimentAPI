# SentimentAPI

> API REST inteligente para análise automática de sentimentos em feedbacks de clientes. Integração robusta de Java Spring Boot com ONNX Runtime para classificação de avaliações.

[![Status do Projeto](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)](https://github.com/Matheus-es/sentimentAPI)
[![License: MIT](https://img.shields.io/badge/license-MIT-blue)](LICENSE)
[![Java CI](https://img.shields.io/badge/Java-21-orange)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.1-brightgreen)](https://spring.io/projects/spring-boot)

---

## 📋 Sumário

- [Sobre](#-sobre)
- [Funcionalidades](#-funcionalidades)
- [Tecnologias](#-tecnologias)
- [Pré-requisitos](#-pré-requisitos)
- [Instalação e Execução Local](#-instalação-e-execução-local)
- [Docker](#-docker)
- [Documentação da API](#-documentação-da-api)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Contribuição](#-contribuição)
- [Licença](#-licença)

---

## 📖 Sobre

**SentimentAPI** é uma solução backend para processamento de linguagem natural (NLP) focada em classificar textos de feedback como **POSITIVO** ou **NEGATIVO**. O projeto encapsula um modelo de Machine Learning (formato ONNX) em uma API Java de alta performance, pronta para integração com frontends de dashboards, sistemas de CRM ou pipelines de dados.

## 🚀 Funcionalidades

- **Análise em Tempo Real:** Endpoint para classificação unitária de textos.
- **Processamento em Lote:** Upload de arquivos CSV para análise massiva de feedbacks.
- **Dashboard de Métricas:** Endpoints para estatísticas agregadas e histórico de análises.
- **Alta Compatibilidade:** Documentação automática via Swagger/OpenAPI.
- **Containerização:** Imagem Docker otimizada para produção.

## 🛠 Tecnologias

- **Java 21**
- **Spring Boot 3.4.1**
- **ONNX Runtime** (Inferência de ML)
- **Maven** (Gerenciamento de dependências)
- **H2 Database** (Banco em memória para dev/testes)
- **PostgreSQL** (Suportado para produção)
- **Docker & Docker Compose**

## ✅ Pré-requisitos

- Java JDK 21 instalado
- Maven instalado (ou utilizar o wrapper `./mvnw`)
- Git

## 💻 Instalação e Execução Local

### 1. Clonar o repositório

```bash
git clone https://github.com/Matheus-es/sentimentAPI.git
cd sentimentAPI
```

### 2. Baixar o Modelo ONNX (Obrigatório para execução local)

Se você **não** utilizar Docker, é necessário baixar o modelo de Machine Learning manualmente.

1. Acesse: [SentimentONE Models](https://github.com/SentimentONE/sentimentIA/tree/main/03-models)
2. Baixe o arquivo do modelo (`.onnx`).
3. Coloque o arquivo em um diretório acessível ou na raiz do projeto.
4. Configure o caminho do modelo na variável de ambiente `SENTIMENT_MODEL_PATH` (veja abaixo).

### 3. Configurar Variáveis de Ambiente (Opcional)

Para configurações personalizadas, crie um arquivo `.env` na raiz ou configure as variáveis no seu sistema.

```properties
# Exemplo
SENTIMENT_MODEL_PATH=/caminho/para/seu_modelo.onnx
SPRING_DATASOURCE_URL=jdbc:h2:mem:sentimentdb
```

> **Nota:** Se não configurado, a aplicação buscará o modelo em caminhos padrão definidos no `application.properties`.

### 4. Build e Execução

Utilize o Maven Wrapper para garantir a versão correta do Maven:

```bash
# Limpar e construir o projeto (ignorando testes para agilizar)
./mvnw clean package -DskipTests

# Executar a aplicação
java -jar target/*.jar
```

Ou execute diretamente com o plugin do Spring Boot:

```bash
./mvnw spring-boot:run
```

Acesse a API em: `http://localhost:8080`

## 🐳 Docker

> **Nota:** Ao utilizar Docker, **não é necessário baixar o modelo manualmente**. O processo de build do Docker já cuida disso automaticamente para você.

Para rodar a aplicação em containers, certifique-se de ter o Docker e Docker Compose instalados.

### Usando Docker Compose (Recomendado)

O comando abaixo irá construir a imagem e subir o container da aplicação:

```bash
docker-compose up --build
```

### Build Manual da Imagem

```bash
docker build -t sentiment-api .
docker run -p 8080:8080 sentiment-api
```

## 📚 Documentação da API

A documentação interativa (Swagger UI) pode ser acessada em:

- **Swagger UI:** `http://localhost:8080/swagger-ui.html`
- **OpenAPI Docs:** `http://localhost:8080/v3/api-docs`

### Principais Endpoints

#### 1. Analisar Texto (Unitário)

**POST** `/sentiment`

**Body:**

```json
{
  "text": "O atendimento foi excelente e o produto chegou rápido!"
}
```

**Response (200 OK):**

```json
{
  "sentiment": "POSITIVE",
  "score": 0.98,
  "text": "O atendimento foi excelente e o produto chegou rápido!"
}
```

#### 2. Processar CSV (Batch)

**POST** `/sentiment/batch`

- **Multipart File:** `file` (arquivo .csv)
- **Query Param:** `textColumn` (opcional, nome da coluna com os textos)

#### 3. Estatísticas

**GET** `/sentiment/statistics`

**Response (200 OK):**

```json
{
  "totalAnalyzed": 150,
  "positiveCount": 120,
  "negativeCount": 30,
  "positivePercentage": 80.0
}
```

#### 4. Histórico Recente

**GET** `/sentiment/history`
Retorna as últimas 100 análises realizadas.

## 📂 Estrutura do Projeto

```
src/main/java/com/hackaton_one/sentiment_api/
├── SentimentApiApplication.java    # Classe Main
├── api/
│   ├── controller/                 # Controladores REST
│   └── dto/                        # Objetos de Transferência de Dados
├── config/                         # Configurações (CORS, etc.)
├── exceptions/                     # Tratamento de Exceções Global
├── model/                          # Entidades JPA
├── repository/                     # Repositórios (Acesso a Dados)
└── service/                        # Regras de Negócio e Serviços
```

## 🤝 Contribuição

1. Faça um **fork** do projeto.
2. Crie uma nova branch com suas alterações: `git checkout -b feature/minha-feature`
3. Salve as alterações e crie uma mensagem de commit contando o que você fez: `git commit -m "feat: Minha nova feature"`
4. Envie as suas alterações: `git push origin feature/minha-feature`
5. Abra um **Pull Request**.

## 📝 Licença

Este projeto está sob a licença [MIT](./LICENSE).

---

Desenvolvido com 💙 por [SentimentryTeam](https://github.com/SentimentONE)
