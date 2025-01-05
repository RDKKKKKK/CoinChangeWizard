# CoinChangeWizard

CoinChangeWizard is a simple web application that provides a REST API and a React-based frontend for calculating the minimum number of coins needed to make up a target amount. 

The project is built using **Dropwizard** for the backend and **React** for the frontend, and it is designed to run in a Dockerized environment.

---

## Getting Started

### Prerequisites

Ensure you have installed:

- [Docker](https://www.docker.com/get-started)

---

### Build and Run Instructions

Follow these steps to build and run the backend and frontend containers:

1. **Clone the Repository**

   ```bash
   git clone https://github.com/RDKKKKKK/CoinChangeWizard.git
   cd CoinChangeWizard
   ```

2. **Build DropWizard Backend Docker Image**

   ```bash
   cd backend
   docker build -t coinchange-backend:latest .
   ```
3. **Build React Frontend Docker Image**

   ```bash
   cd ../frontend
   docker build -t coinchange-frontend:latest .
   ```

4. **Create a Docker Network and Run Containers**

   ```bash
   docker network create coinchange-network
   docker run -d --name coinchange-backend --network coinchange-network -p 8080:8080 coinchange-backend:latest
   docker run -d --name coinchange-frontend --network coinchange-network -p 80:80 coinchange-frontend:latest
   ```
5. **Access CoinChangeWizard Application**
   ```text
   http://<your-public-ip>
   ```

---

### API Documentation

1. **Endpoint**

   POST /api/coinchange


2. **Request Body**
   ```json
   {
   "targetAmount": 7.03,
   "denominations": [0.01, 0.5, 1, 5, 10]
   }
   ```

3. **Response**
   ```json
   [
    0.01,
    0.01,
    0.01,
    1,
    1,
    5
   ]
   ```


