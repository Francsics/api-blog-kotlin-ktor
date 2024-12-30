# 📄 Blogueiro - API REST com Ktor e Kotlin  

**Bem-vindo à Blogueiro API**, uma aplicação desenvolvida com Kotlin e Ktor, conectada a uma base de dados Firebase.  
O objetivo deste projeto é fornecer endpoints para a gestão de posts e utilizadores, permitindo operações de criação, leitura, atualização e eliminação (CRUD), bem como autenticação de utilizadores.  

---

## 📑 Índice
1. [Visão Geral](#visão-geral)  
2. [Recursos](#recursos)  
3. [Estrutura do Projeto](#estrutura-do-projeto)  
4. [Instalação](#instalação)  
   - [Pré-requisitos](#pré-requisitos)  
   - [Configuração da Base de Dados](#configuração-da-base-de-dados)  
   - [Executar a API](#executar-a-api)  
   - [Endpoints Disponíveis](#endpoints-disponíveis)  
   - [Exemplos com cURL](#exemplos-com-curl)  
5. [Testar a API](#testar-a-api)  
6. [Contacto](#contacto)  

---

## 📜 Visão Geral  
A Blogueiro API é uma aplicação de blog que permite a criação e gestão de posts e utilizadores.  
Com autenticação baseada em JWT e persistência de dados em Firebase, a API foi desenvolvida para fornecer segurança e flexibilidade na gestão de conteúdos digitais.  

---

## 🔧 Recursos  
- **CRUD de Utilizadores e Posts** – Criar, listar, atualizar e eliminar.  
- **Autenticação** – Registo e login de utilizadores com JWT.  
- **Base de Dados** – Integração com Firebase para armazenamento e manipulação de dados.  
- **Rotas Protegidas** – Autorização em endpoints sensíveis.  
- **Gestão de Logs** – Monitorização detalhada com `logback.xml`.  

---

## 📂 Estrutura do Projeto  
```
Blogueiro/
│
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   └── com/example/
│   │   │       ├── model/              # Modelos de domínio (User, Posts)
│   │   │       ├── mysql/              # Conexão à base de dados e entidades
│   │   │       │   ├── DbConnection.kt
│   │   │       │   └── entity/
│   │   │       ├── plugins/            # Configuração de roteamento e serialização
│   │   │       │   ├── Routing.kt
│   │   │       │   └── Serialization.kt
│   │   │       ├── route/              # Rotas (Login e utilizadores)
│   │   │       │   ├── RouteUser.kt
│   │   │       │   └── LoginRequest.kt
│   │   │       └── Application.kt      # Ponto de entrada da aplicação
│   │   └── resources/
│   │       └── logback.xml             # Configuração de logs
│   └── test/
│       └── com/example/ApplicationTest.kt
│
├── build.gradle.kts                    # Configuração do Gradle
└── settings.gradle.kts                 # Configuração do projeto Gradle
```

---

## 🛠️ Instalação  

### 🔗 Pré-requisitos  
- [Kotlin](https://kotlinlang.org/)  
- [IntelliJ IDEA](https://www.jetbrains.com/idea/)  
- [Gradle](https://gradle.org/)  
- **Firebase** (conta configurada e base de dados criada)  

---

### ⚙️ Configuração da Base de Dados  
1. No Firebase, cria uma base de dados em tempo real ou Firestore.  
2. Atualiza o ficheiro `DbConnection.kt` com as credenciais do teu projeto Firebase:  
   ```kotlin
   val databaseUrl = "https://SEU_PROJETO.firebaseio.com"
   val apiKey = "SUA_API_KEY"
   ```
3. Certifica-te de que o Firebase permite conexões da origem correta (localhost para desenvolvimento).  

---

### ▶️ Executar a API  
1. Clonar este repositório:  
   ```bash
   git clone <URL_DO_SEU_REPOSITORIO>
   cd Blogueiro
   ```
2. Executar o projeto com Gradle:  
   ```bash
   ./gradlew run
   ```
3. A API estará disponível em `http://localhost:8080`.  

---

## 🌐 Endpoints Disponíveis  

### 🧑‍💻 Utilizador  
- **POST** `/user/register` – Registar um novo utilizador  
- **POST** `/user/login` – Autenticar o utilizador e devolver um token JWT  

### 📝 Posts  
- **GET** `/posts` – Listar todos os posts  
- **POST** `/posts` – Criar um novo post  
- **PUT** `/posts/{id}` – Atualizar um post  
- **DELETE** `/posts/{id}` – Eliminar um post  

---

## 🛠️ Exemplos com cURL  

### Criar um Novo Utilizador  
```bash
curl -X POST http://localhost:8080/user/register \
-H "Content-Type: application/json" \
-d '{
  "username": "joao",
  "password": "12345"
}'
```

### Login e Obter Token  
```bash
curl -X POST http://localhost:8080/user/login \
-H "Content-Type: application/json" \
-d '{
  "username": "joao",
  "password": "12345"
}'
```

---

## ✅ Testar a API  
1. Após executar a API, utiliza o Postman ou Insomnia para testar os endpoints.  
2. Certifica-te de adicionar o token JWT nos endpoints protegidos, utilizando o cabeçalho:  
   ```
   Authorization: Bearer SEU_TOKEN
   ```
3. Executa testes unitários com:  
   ```bash
   ./gradlew test
   ```  

---

## 📧 Contacto  
- **Desenvolvedores:** Francsico Santo / Gonçalo Landeiro  
- **Email:** francisco.santo@my.istec.pt / goncalo.landeiro@my.istec.pt  

