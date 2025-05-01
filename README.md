# Estech Back

Estech Back is the **backend** system for the Estech platform — a digital solution for weight-loss and aesthetic clinics. This backend service is responsible for providing a RESTful API used by the Estech Frontend (currently located at `https://github.com/francieli-geraldo/estech-front`).

It handles core business logic, database interactions, user/contract management, and serves all data visualizations consumed by the frontend dashboard.

> 🔗 **Frontend repository**: https://github.com/francieli-geraldo/estech-front

---

## 🩺 Product Overview

### 🔹 Core Features

- **📊 Dashboard Overview**  
  Visual dashboards showing key metrics such as kilos lost, contract statistics, and group progress.

- **📈 Weight Tracking & Performance Charts**  
  Patient evolution is monitored through weekly check-ins and presented via intuitive graphs (e.g., weight trend lines and daily release distributions).

- **📃 Smart Reports**  
  Exportable reports in PDF or image format, including:
  - % posts completed  
  - Evolution progress  
  - Balance and goal tracking  
  - Contractual adherence

- **📋 Contract Management**  
  Track individual contracts with:
  - Initial and goal weights  
  - Target weight loss  
  - Start and end dates  
  - Status: active, expired, overdue, terminated  

- **👥 Patient Management**  
  Manage detailed patient records:
  - Name, gender, birthdate, contact info  
  - Assigned program and group  
  - Contract history and progress reports  

- **🏷️ Program Configuration**  
  Customizable treatment plans (e.g., "INTENSIVE", "ADVANCED", "MOM", "FAST", "TURBO").

- **🧑‍🤝‍🧑 Group Structuring**  
  Patients organized into configurable groups for comparative analysis and batch reporting.

- **📦 Exports & Printing**  
  Reports and charts can be printed or exported for documentation and review.

---

## 🚀 Technologies Used

- Java 17
- Spring Boot
- Gradle
- Docker
- GitLab CI/CD

## 📋 Requirements

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Gradle](https://gradle.org/install/)
- [Docker](https://www.docker.com/get-started)
- [Git](https://git-scm.com)

## ⚙️ Getting Started

```bash
# Clone this repository
git clone https://github.com/francieli-geraldo/estech-back.git

cd estech-back

# Build the project
./gradlew build
