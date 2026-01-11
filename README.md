# 📝 Equipe Sentimetry: SentimentAPI

## 🔗 Links Importantes
- [Trello](https://trello.com/invite/b/693af0ad27a996b1ca20f340/ATTI5bada56f48ed70c7a4a4ee0aa7209420E86A3693/hackathon-one-ii-brasil-h12-25-b-equipo-13-data-science)
- [GitHub](https://github.com/SentimentONE)

---

## 📌 Descrição do Projeto

Solução completa para análise de sentimentos em textos através de comentários e feedback de clientes. O modelo desenvolvido pela equipe de **Data Science** foi integrado a uma **API REST**, permitindo que outras aplicações consumam automaticamente a predição.

**Classificação binária**: POSITIVO / NEGATIVO

---

## 🏗️ Arquitetura

### Backend (Spring Boot)
- Spring Boot 3.4.1 | Java 21
- Modelo ML: ONNX Runtime
- Banco: PostgreSQL (produção) / H2 (desenvolvimento)
- Porta: 8080

### Frontend (React)
- React 18 | Vite | Tailwind CSS
- Porta: 3000

---

## 👥 Membros da Equipe

- **Andreia Semedo** – Desenvolvedor Backend / Frontend
- **Pedro Wandrey Barbosa Xavier** – Desenvolvedor Backend
- **Patricia Starck Bernardi** - Desenvolvedora Backend
- **Roberto Gonçalves Conceição Filho** -  Desenvolvedor Backend

---

## 🛠️ Tecnologias Utilizadas

**Backend**: Spring Boot, Java 21, ONNX Runtime, PostgreSQL/H2, Swagger  
**Frontend**: React 18, Vite, Tailwind CSS, Axios  
**Ferramentas**: Git/GitHub, Docker

---

## 🎯 Entregáveis


### Back-End
✅ API Spring Boot | ✅ Endpoints `/sentiment` e `/sentiment/batch` | ✅ Integração ONNX | ✅ Swagger

### Front-End
✅ Interface React | ✅ Análise em tempo real | ✅ Histórico | ✅ Gráficos | ✅ Multi-idioma | ✅ Modo claro/escuro

---

## 📡 Endpoints da API

### POST /sentiment
Analisa sentimento de um texto único.

**Request:**
```json
{ "text": "Este produto é incrível!" }
```

**Response:**
```json
{
  "sentiment": "POSITIVO",
  "score": 0.95,
  "text": "Este produto é incrível!"
}
```

### POST /sentiment/batch
Analisa múltiplos textos de um arquivo CSV.

**Request:** `file` (CSV), `textColumn` (opcional)

---

## 📦 Instalação e Execução

### Pré-requisitos
- Java 21, Maven 3.8+, Node.js 18+
- PostgreSQL (produção) ou H2 (desenvolvimento)

### Backend
```bash
cd sentimentAPI
./mvnw spring-boot:run
# ou docker-compose up
```
API: `http://localhost:8080`

### Frontend
```bash
cd frontend
npm install
npm run dev
```
Frontend: `http://localhost:3000`

### Configuração
- Backend: `sentimentAPI/src/main/resources/application*.properties`
- Frontend: `.env` → `VITE_API_URL=http://localhost:8080`

---

## 📊 Modelo ML

**Localização**: `sentimentAPI/src/main/resources/models/sentiment_model.onnx`  
**Fallback**: Análise baseada em palavras-chave (se modelo não disponível)

---

## 📝 Validações

- Tamanho: 5 a 5000 caracteres
- Formato: Apenas texto (sem HTML)
- Classificação: Binária (POSITIVO/NEGATIVO)

---

## 📚 Documentação

Swagger: `http://localhost:8080/swagger-ui.html`

---

## 📅 Status Atual

### Back End
✅ API | ✅ Endpoints | ✅ ONNX | ✅ Swagger | ✅ Validações | ✅ Tratamento de erros

### Front End
✅ Interface | ✅ Integração API | ✅ Histórico | ✅ Gráficos | ✅ Multi-idioma | ✅ Modo claro/escuro

---

## 📌 Próximos Passos

**Back End**: Testes unitários, otimização, cache, monitoramento  
**Front End**: Testes automatizados, otimização, melhorias UX, PWA

---

## 🧪 Exemplo de Uso

```bash
curl -X POST http://localhost:8080/sentiment \
  -H "Content-Type: application/json" \
  -d '{"text": "Este produto é excelente!"}'
```

---

**Nota**: Classificação binária (Positivo/Negativo). Valores neutros são convertidos para Positivo.

---

**Desenvolvido como parte do Hackathon**
