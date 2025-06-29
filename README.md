# Base CRUD App

Questa è una semplice applicazione full-stack per la **gestione CRUD degli utenti**.

La struttura prevede:

- ✅ Backend in **Spring Boot** con API REST
- ✅ Frontend in **Angular** con **Material UI** e gestione stato (Signal Store)
- ✅ Importazione utenti da file `.csv`
- ✅ Documentazione tramite **Swagger**
- ✅ **Docker** per l'ambiente database (PostgreSQL)

## 🐳 Avvio completo con Docker Compose

Puoi avviare l’intera applicazione (backend + frontend + database PostgreSQL) con un solo comando:

```bash
docker-compose up --build
```

## 🧭 Esperienza di utilizzo

Una volta avviata l'applicazione, si può interagire con il sistema in due modi:

### 🔹 1. Interfaccia Web (Frontend)

Accedere tramite browser a: http://localhost:4200


Da qui puoi:
- 👀 Visualizzare la lista degli utenti
- 🔍 Cercare utenti per nome o cognome
- ➕ Aggiungere un nuovo utente
- ✏️ Modificare un utente esistente
- 🗑️ Eliminare un utente
- 📁 Importare utenti da file `.csv`

### 🔹 2. Interfaccia Swagger

Accedere alla documentazione su: http://localhost:8080/swagger-ui/index.html


Da qui si può eseguire tutte le operazioni sulle API:

- `GET /api/users` → Elenco utenti
- `GET /api/users/{id}` → Dettaglio utente
- `POST /api/users` → Crea utente
- `PUT /api/users/{id}` → Modifica utente
- `DELETE /api/users/{id}` → Elimina utente
- `GET /api/users/search?name=...&surname=...` → Ricerca utenti
- `POST /api/users/upload` → Import da CSV

📎 **Nota:** nella root del progetto è presente un file `users.csv` di esempio per l’import, con intestazioni: name,surname,email,address

---

## 👨‍💻 Autore

**Andrea D'Angiolillo**

📫 Per info: [andrea.dangiolillo@gmail.com](mailto:andrea.dangiolillo@gmail.com)
