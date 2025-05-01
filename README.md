# Estech Back

Estech Back is the **backend application** of the Estech platform — a comprehensive system designed for weight-loss and aesthetic clinics. It provides a RESTful API that powers the [Estech Frontend](https://github.com/francieli-geraldo/estech-front), enabling real-time management of patients, contracts, programs, and performance reports.

> 🔗 Frontend repository: [https://github.com/francieli-geraldo/estech-front](https://github.com/francieli-geraldo/estech-front)

---

## 🩺 Product Overview

### 🔹 Core Features

- **📊 Dashboard Overview**  
  Aggregated statistics and visual charts on patient progress, releases, and contract activity.

- **📈 Weight Tracking & Performance**  
  Individual and group-based evolution charts, weight history, and adherence indicators.

- **📃 Smart Reports**  
  Exportable reports with performance indicators, grouped metrics, and customizable filters.

- **📋 Contract Management**  
  Control over contract lifecycle: start/end dates, status, goals, progress, and renewal.

- **👥 Patient Management**  
  Store and manage patient details, group associations, and personal progress.

- **🏷️ Program Configuration**  
  Multiple treatment plans (INTENSIVE, ADVANCED, MOM, TURBO, etc.) with customizable descriptions.

- **🧑‍🤝‍🧑 Group Management**  
  Organize patients into dynamic groups to track collective progress and generate reports.

- **📦 Exports**  
  PDF/image generation for print-friendly documentation.

---

## 🚀 Technologies Used

- Java 17
- Spring Boot
- Gradle
- Docker
- PostgreSQL (expected)
- GitLab CI/CD

---

## 📋 Requirements

Make sure the following are installed:

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Gradle](https://gradle.org/install/)
- [Docker](https://www.docker.com/)
- [Git](https://git-scm.com/)

---

## ⚙️ Setup & Installation

```bash
# Clone the repository
git clone https://github.com/francieli-geraldo/estech-back.git
cd estech-back

# Build the application
./gradlew build
```

---

## ▶️ Running the Application

### Locally with Gradle

```bash
./gradlew bootRun
```

The API will be available at `http://localhost:8080`.

### With Docker

```bash
docker build -t estech-back .
docker run -p 8080:8080 estech-back
```

---

## 🌐 API Usage

This application serves a RESTful API consumed by the [Estech Frontend](https://github.com/francieli-geraldo/estech-front).

> ⚠️ **Important**: Update the `environment.ts` file in the frontend (`src/environments/`) to point to the URL where this backend is deployed.

```ts
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api' // ← adjust this for local or deployed backend
};
```

---

## 📁 Project Structure

```
├── src
│   ├── main
│   │   ├── java
│   │   └── resources
├── build.gradle
├── Dockerfile
├── .gitlab-ci.yml
└── README.md
```
