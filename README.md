# \# User Management Application

# 

# Questa è una web application full-stack per la gestione di utenti, composta da:

# 

# \- Backend: Java Spring Boot con API REST

# \- Frontend: Angular (con Angular Material)

# \- Database: PostgreSQL (opzionale con Docker)

# \- Documentazione API: Swagger UI

# 

# \## 🧱 Struttura del progetto

# 

# ```

# .

# ├── backend/         # Spring Boot Application

# ├── frontend/        # Angular Application

# ├── docker-compose.yml

# ├── README.md

# ```

# 

# ---

# 

# \## 🚀 Avvio rapido

# 

# \### 🔧 Requisiti

# 

# \- Java 17+

# \- Node.js 18+ e npm

# \- Docker (consigliato per DB)

# \- Angular CLI (`npm install -g @angular/cli`)

# 

# ---

# 

# \## ▶️ Come eseguire il backend (Spring Boot)

# 

# 1\. Vai nella cartella `backend/`:

# 

# &nbsp;  ```bash

# &nbsp;  cd backend

# &nbsp;  ```

# 

# 2\. Costruisci ed esegui l'app:

# 

# &nbsp;  ```bash

# &nbsp;  ./mvnw spring-boot:run

# &nbsp;  ```

# 

# &nbsp;  Oppure:

# 

# &nbsp;  ```bash

# &nbsp;  mvn clean install

# &nbsp;  java -jar target/backend-0.0.1-SNAPSHOT.jar

# &nbsp;  ```

# 

# 3\. Il backend sarà disponibile su:

# 

# &nbsp;  ```

# &nbsp;  http://localhost:8080

# &nbsp;  ```

# 

# ---

# 

# \## 📒 Come testare il backend con Swagger

# 

# Swagger UI sarà disponibile all'indirizzo:

# 

# ```

# http://localhost:8080/swagger-ui/index.html

# ```

# 

# Da qui potrai eseguire tutte le richieste HTTP (GET, POST, PUT, DELETE) sugli endpoint `/api/users`.

# 

# ---

# 

# \## 🧪 Come testare le API da terminale

# 

# Esempio di creazione utente:

# 

# ```bash

# curl -X POST http://localhost:8080/api/users \\

# &nbsp; -H "Content-Type: application/json" \\

# &nbsp; -d '{"name":"Mario","surname":"Rossi","email":"mario.rossi@example.com","address":"Viale Roma 10 00185 Roma"}'

# ```

# 

# ---

# 

# \## 💻 Come eseguire il frontend (Angular)

# 

# 1\. Vai nella cartella `frontend/`:

# 

# &nbsp;  ```bash

# &nbsp;  cd frontend

# &nbsp;  ```

# 

# 2\. Installa le dipendenze:

# 

# &nbsp;  ```bash

# &nbsp;  npm install

# &nbsp;  ```

# 

# 3\. Avvia l'app Angular:

# 

# &nbsp;  ```bash

# &nbsp;  ng serve

# &nbsp;  ```

# 

# 4\. Apri il browser su:

# 

# &nbsp;  ```

# &nbsp;  http://localhost:4200

# &nbsp;  ```

# 

# ---

# 

# \## 🐳 Avvio con Docker (PostgreSQL)

# 

# Per far partire il database PostgreSQL:

# 

# ```bash

# docker-compose up -d

# ```

# 

# Verifica che il DB sia disponibile su `localhost:5432`.

# 

# ---

# 

# \## 🔗 Endpoints principali (API)

# 

# \- `GET /api/users`: Lista utenti

# \- `POST /api/users`: Crea nuovo utente

# \- `GET /api/users/{id}`: Ottiene un utente per ID

# \- `PUT /api/users/{id}`: Modifica utente

# \- `DELETE /api/users/{id}`: Elimina utente

# 

# ---

# 

# \## 📂 Import CSV (se previsto)

# 

# L'endpoint per l'import può essere disponibile su:

# 

# ```http

# POST /api/users/import

# ```

# 

# Con un file CSV come input (es. `users.csv` con i campi: `name,surname,email,address`).

# 

# ---

# 

# \## ✅ Test finali

# 

# \- Swagger: `http://localhost:8080/swagger-ui/index.html`

# \- Angular: `http://localhost:4200`

# \- API REST funzionanti: `curl` o Postman

# \- DB su Docker (PostgreSQL): `localhost:5432`

# 

# ---

# 

# \## 👨‍💻 Autore

# 

# Andrea D'Angiolillo\\

# Contatti: andrea\\@example.com

# 



