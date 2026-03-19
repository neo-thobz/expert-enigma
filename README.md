# 🧠 Expert Enigma – Chat With My Resume

This project is a **Retrieval-Augmented Generation (RAG) API** that allows recruiters (or anyone) to **chat directly with my CV**.

Instead of reading through a static document, you can ask questions like:

* “What experience do you have with microservices?”
* “Have you led any teams?”
* “What technologies have you worked with?”

The system retrieves relevant parts of my CV and uses a local LLM to generate accurate, context-aware answers.

---

# 🚀 Why This Project?

Traditional CVs are static. This project demonstrates:

* Turning a CV into an **interactive experience**
* Applying **AI + backend engineering**
* Designing **production-ready cloud architecture**
* Building **real-world RAG systems**

This is not just a demo — it reflects how modern applications integrate AI into real products.

---

# 🏗️ Architecture Overview

```
Frontend (React - S3 + CloudFront)
        ↓
Spring Boot API (ECS Fargate)
        ↓
RAG Layer
   ├── Vector Search (PostgreSQL + pgvector)
   ├── Embeddings (local model)
   └── LLM (Ollama - Mistral)
```

---

# 🧰 Tech Stack

## Backend

* Java 21
* Spring Boot
* Spring Data JPA
* Lombok

## AI / RAG

* Ollama (local LLM runtime)
* Mistral model (text generation)
* nomic-embed-text (embeddings)

## Database

* PostgreSQL
* pgvector (vector similarity search)

## DevOps

* Docker
* Docker Compose

## Cloud (Planned Deployment)

* ECS Fargate (API)
* EC2 (LLM hosting)
* RDS PostgreSQL
* S3 + CloudFront (Front-End)

---

# 🧠 How It Works

1. **CV is split into chunks**
2. Each chunk is converted into an **embedding vector**
3. Stored in PostgreSQL using **pgvector**
4. When a question is asked:

   * The question is embedded
   * Similar CV chunks are retrieved
   * A prompt is built with this context
5. The LLM generates an answer **strictly based on CV data**

---

# 🔒 Guardrails

The system is designed to:

* ✅ Only answer using CV content
* ❌ Avoid hallucinations
* ❌ Not invent experience

If information is not found, it responds with:

> “That information is not in the CV.”

---

# 🛠️ Running Locally

## Prerequisites

* Docker
* Docker Compose

---

## 1️⃣ Clone the repo

```bash
git clone https://github.com/neo-thobz/expert-enigma
cd expert-enigma
```

---

## 2️⃣ Create `.env` file

```env
SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/cvdb
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=password
OLLAMA_BASE_URL=http://ollama:11434
SERVER_PORT=8080
```

---

## 3️⃣ Start the application

```bash
docker-compose up --build
```

This will start:

* Spring Boot API → http://localhost:8080
* Ollama LLM → http://localhost:11434
* PostgreSQL DB → localhost:5432

---

## 4️⃣ Pull required models (first time only)

```bash
docker exec -it ollama ollama pull mistral
docker exec -it ollama ollama pull nomic-embed-text
```

---

## 5️⃣ Test the API

```bash
POST http://localhost:8080/chat
Content-Type: application/json

{
  "message": "What experience do you have with Spring Boot?"
}
```

---

# 📦 Project Structure

```
expert-enigma/
├── docker-compose.yml
├── expert-enigma-api/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── model/
│   ├── config/
│   └── util/
```

---

# ☁️ Deployment Plan (AWS)

This project is designed with cloud deployment in mind:

* **API** → ECS Fargate (stateless service)
* **LLM (Ollama)** → EC2 (memory-intensive workload)
* **Database** → RDS PostgreSQL with pgvector
* **Frontend** → S3 + CloudFront

This separation ensures:

* Scalability
* Cost efficiency
* Production readiness

---

# 🔮 Future Improvements

* Chat history / conversation memory
* Authentication & recruiter dashboard
* CV upload & dynamic indexing
* Streaming responses
* Model optimization / smaller LLMs

---

# 👨‍💻 Author

Built by **Neo Thobz**

This project reflects my experience in:

* Backend engineering
* Microservices architecture
* Cloud deployment
* AI integration

---

# ⭐ Final Note

This project demonstrates how software engineering and AI can be combined to create **interactive, intelligent systems**.

Instead of telling you what I’ve done —
you can now **ask my CV directly**.
