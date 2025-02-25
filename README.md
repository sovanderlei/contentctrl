# Contentctrl - Spring Boot API with JWT, Swagger, JUnit, Mockito, Docker, Kubernete 

# Contentctrl - FrontEnd React  


# 📌 Conteúdo  

## **Backend - Spring Boot API**  
1. [Descrição do Projeto](#project-description)  
2. [Funcionalidades](#features)  
3. [Tecnologias Utilizadas](#technologies-used)  
4. [Estrutura do Projeto](#project-structure)  
5. [Dependências](#dependencies)  
6. [Como Executar](#running-the-project)  
7. [Exemplos de Uso](#usage-examples)  
8. [Testes](#testing)  
9. [Documentação com Swagger](#swagger-documentation)  
10. [Execução com Docker e Kubernetes](#docker-kubernetes)  

## **Frontend - React**  
1. [Descrição do Projeto](#frontend---crud-dinâmico-com-react-e-jwt)  
2. [Tecnologias Utilizadas](#🚀-tecnologias-utilizadas)  
3. [Como Baixar e Executar](#📥-como-baixar-e-executar-o-projeto)  
4. [Autenticação JWT](#🔑-autenticação-jwt)  
5. [Estrutura do Projeto](#🛠-estrutura-do-projeto)  
6. [Endpoints Utilizados](#📄-endpoints-utilizados)  
7. [Autor](#📌-autor)  





## Project Description

**Contentctrl** is a backend system built using **Spring Boot**, designed to provide a RESTful API that implements authentication via **JWT** (JSON Web Tokens). The application also features automatic API documentation using **Swagger** and code documentation via **Javadoc**.

The application is created to manage users and provide a secure system to control access to resources based on authentication and authorization. With JWT implementation, the system ensures that only authenticated users can access certain protected endpoints.

## Features

- **JWT Authentication**: Login implementation and JWT token generation for user authentication.
- **API Security**: Uses Spring Security to ensure route protection, allowing only authenticated users to access certain resources.
- **Swagger Documentation**: Automatically documents the API, providing an easy-to-use interface for navigating and testing endpoints.
- **User Management**: Creation and management of users, including login and registration operations.

## Technologies Used

- **Spring Boot**: The main framework used to build the RESTful API.
- **Spring Security**: For security management, including authentication and authorization.
- **JWT (JSON Web Token)**: For generating authentication tokens.
- **Swagger**: For automatic API documentation.
- **Javadoc**: For generating code documentation.

## Project Structure

The basic project structure is as follows:


### Explanation of Folders:
  ```
- src/
- │ 
- ├── main/
- │ ├── java/ 
- │ │ └── com/ 
- │ │ └── api/ 
- │ │ └── contentctrl/ 
- │ │ ├── ContentctrlApplication.java # Main class of the Spring Boot application 
- │ │ ├── config/ # Security, JWT, and Swagger configurations 
- │ │ ├── controller/ # REST API controllers 
- │ │ ├── model/ # Data models, such as the User 
- │ │ └── service/ # Business logic, such as user manipulation 
- │ ├── resources/ 
- │ │ ├── application.properties # Spring Boot configuration 
- │ │ └── static/ # Static files (if necessary) 
- │ └── test/ 
- │ └── java/ 
- │ └── com/ 
- │ └── api/ 
- │ └── contentctrl/ 
- │ ├── service/ # Unit tests for services 
- │ └── controller/ # Tests for the API controllers
```
 

### Key Components:

- **ContentctrlApplication.java**: The main class that starts the Spring Boot application.
- **SecurityConfig.java**: Spring Security configurations, including JWT setup.
- **UserService.java**: The service that contains business logic for user operations like login and registration.
- **UserController.java**: The REST controller that handles user-related requests such as login, registration, etc.
- **JwtTokenUtil.java**: A utility class for generating and validating JWT tokens.
- **SwaggerConfig.java**: The Swagger configuration to generate automatic API documentation.

### Dependencies

The key dependencies in this project are:

- **Spring Boot**: The foundation for the project development.
- **Spring Security**: For security control.
- **JWT (Java JWT)**: For implementing JWT tokens.
- **Swagger (Springfox)**: For API documentation.

The dependencies can be found in the `pom.xml` file.

### Running the Project

To run the project locally, follow these steps:

1. **Clone the repository**:
  ```  bash
   git clone https://github.com/your-username/contentctrl.git
```
2. **Install the dependencies (if needed, run Maven or Gradle):**:
  ``` mvn install```

3. **Run the application: To run the application locally:**:
  ```mvn spring-boot:run```

### Alternatively, you can run the ContentctrlApplication class directly from your IDE.

4. **Access the application:**:
5. The API will be available at http://localhost:8080. 

6. **Test the API:**: 
- **Access the Swagger API documentation at: http://localhost:8080/swagger-ui/.**: 
- **The Swagger interface allows you to interact with the API directly from the browser and test the endpoints.**: 

### Usage Examples
- **Endpoint: Login**: 
- **Method: POST**: 
- **URL: /api/auth/login**: 
- **Description: Logs the user in and returns a JWT token if authentication is successful.**: 
- **Request Body:**: 

  ``` json 
   {
     "username": "user",
     "password": "password"
   }
   ```
   
- **Response:

  ``` json
   {
     "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
   }
  ```
  
- **Endpoint: Register User**: 
- **Method: POST**: 
- **URL: /api/auth/register**: 
- **Description: Registers a new user in the system.**: 
- **Request Body:**: 
 ```  json 
   {
     "username": "new_user",
     "password": "new_password"
   }
   ```
- **Protected Endpoint**: 
- **Method: GET**: 
- **URL: /api/protected**: 
- **Description: A protected endpoint that can only be accessed with a valid JWT token.**: 
- **Headers:**: 
- **makefile**: 
 
### Authorization: Bearer <token>
- **Swagger Documentation**: 
- **Swagger is integrated into the project to provide an interactive interface for testing the API endpoints.**: 

Access the Swagger interface at: http://localhost:8080/swagger-ui/.
Contributing
If you want to contribute to this project, follow these steps:

### Fork this repository.
- **Create a new branch: git checkout -b my-contribution.**: 
- **Make the desired changes.**: 
- **Commit and push to your branch: git push origin my-contribution.**: 
- **Open a pull request for review.**: 
- **License**: 
- **This project is licensed under the MIT License. See the LICENSE file for more details.**: 

### Javadoc
- **To generate the Javadoc documentation, use the following command:**:  
``` mvn javadoc:javadoc ```
- **You can then access the generated documentation in the target/site/apidocs directory.**: 

### See images of the project below
- **Swagger**: 
![Texto alternativo](https://github.com/sovanderlei/contentctrl/blob/main/images/swagger001.png)
![Texto alternativo](https://github.com/sovanderlei/contentctrl/blob/main/images/swagger002.png)

## Testing

The project uses **JUnit** and **Mockito** for unit testing and mocking dependencies in the controller layer.

- **JUnit** is used for writing and running unit tests for the application's API controllers and services.
- **Mockito** is used to mock service layer dependencies, allowing the controller logic to be tested in isolation without relying on actual database interactions or other services.

### Testing Libraries Used:
- **JUnit 5**: For writing and running unit tests.
- **Mockito**: For mocking the behavior of dependencies (e.g., services) during the tests.
- **MockMvc**: To simulate HTTP requests and test the controller endpoints in isolation.

### Key Test Scenarios:
- **Controller Tests**: Tests are written for each endpoint in the controllers (e.g., creating, retrieving, updating, and deleting resources).
- **Mocking Services**: The service layer is mocked using Mockito to isolate controller logic during testing.
- **API Response Validation**: Tests ensure that the correct HTTP status codes and response bodies are returned for each endpoint.

### Example Test:

```java
@Test
void testGetAllBranches() throws Exception {
    // Mocking the service response
    List<Branch> branchList = Arrays.asList(new Branch("Branch 1"), new Branch("Branch 2"));
    when(branchService.getAllBranches()).thenReturn(branchList);

    // Simulating the GET request and validating the response
    mockMvc.perform(get("/contentctrl/branches"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$[0].name").value("Branch 1"))
           .andExpect(jsonPath("$[1].name").value("Branch 2"));
}
```


---
---


# Docker Kubernetes

This project is a **Spring Boot**, **MySQL**, and **Tomcat** application, with support for execution via **Docker** and **Kubernetes**.

## 🛠️ Prerequisites

Before you begin, ensure you have the following installed:

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/install/)
- [Kubectl](https://kubernetes.io/docs/tasks/tools/)
- [Minikube](https://minikube.sigs.k8s.io/docs/start/) (or another Kubernetes cluster)

---

## 🚀 Running with Docker

1. **Clone the repository:**
   ```sh
   git clone https://github.com/sovanderlei/contentctrl.git
   cd contentctrl  ```
   
2. **Build the project using Maven:**
 
```mvn clean package```

3. **Build and run the containers with Docker Compose:**
 
```docker-compose up --build -d```

4. **Check if the containers are running:**
 
```docker ps```

5. **Access the application in your browser:**

Spring Boot API: http://localhost:8082
Tomcat: http://localhost:8080
MySQL: Running on port 3307 (accessible via a MySQL client)
Stop the containers:
 
```docker-compose down```


## ☸️  Running with Kubernetes 
## 1 ️Start the Kubernetes Cluster

**If using Minikube, start the cluster:**
 
```minikube start```

## 2 Deploying the Kubernetes Resources 

**Apply the Kubernetes manifests:**

```kubectl apply -f kubernetes/```

**Check running pods:**

```kubectl get pods```

**Check running services:**

```kubectl get svc```

**Access the application via Kubernetes**

**To expose the application in Minikube, run:**

```minikube service contentctrl-service```

**To remove the Kubernetes resources:**

```kubectl delete -f kubernetes/```


## 📜 Project Structure
 
```
contentctrl/
	│── src/                     # Application source code
	│── kubernetes/              # Kubernetes configuration files
	│── docker-compose.yml       # Docker Compose configuration
	│── Dockerfile               # Application container configuration
	│── README.md                # This file
```

## 🛠️ Technologies Used

**Spring Boot**

- **MySQL**
- **Docker**
- **Kubernetes**
- **Tomcat**
  
This ensures that anyone can easily run the project using **Docker** or **Kubernetes**! 🚀


---
---

# 📌 Frontend - Dynamic CRUD with React and JWT

This is a **frontend** developed in **React** to consume a REST API with **JWT authentication**. It allows you to perform **CRUD operations dynamically** for different endpoints, such as `users`, `companies` and `branches`. In addition, it manages the authentication token and redirects the user to the login screen if the token expires.

---

## 🚀 Technologies Used

- **React** (Vite)
- **React Router Dom** (Route management)
- **Axios** (API consumption)
- **Tailwind CSS** (Styling)
- **Context API** (Authentication management)
- **LocalStorage** (JWT token storage)

---

## 📥 How to download and run the project

### 1️⃣ Clone the repository:
```sh
git clone https://github.com/your-username/your-repository.git
```

2️ Access the project folder:
```
cd project-name
```

3️ Install the dependencies:
```
npm install
```

4️ Configure the API URL:
Edit the .env file and set the environment variable:
 ```
VITE_API_BASE_URL=http://localhost:8080/contentctrl
```
5️ Start the development server:
 ```
npm run dev
```
The project will be available at http://localhost:5173.


## 🔑 JWT Authentication
The user logs in and receives a JWT token, which is stored in LocalStorage.
The token is automatically sent on every request to protected endpoints.
If the token expires, the user is redirected to the login screen.

✨Main Features

✅ Login and Logout with JWT
✅ Dynamic CRUD for multiple endpoints (users, companies, branches)
✅ Route Protection (unauthenticated users are redirected)
✅ Token Storage and automatic expiration check

## 🛠 Project Structure
```
📦src
┣ 📂 components # Reusable components
┣ 📂 pages # Main pages (Login, Dashboard, CRUDs)
┣ 📂 services # Services for API calls
┣ 📂 context # Authentication context
┣ 📂 hooks # Custom hooks
┣ 📂 styles # Global styles
┣ 📜 main.jsx # App entry point
┗ 📜 App.jsx # Route configuration
```


## 📄 Endpoints Used
- **Authentication:** /users/login
- **Users:** /users
- **Companies:** /companies
- **Branches:** /branches

## 📌 Autor
Made by Your Name 💙🚀
If you liked the project, ⭐️ give the repository a star!
 
```
Esse README cobre **descrição, instalação, execução, autenticação JWT e estrutura do projeto**. Caso precise de ajustes, me avise! 🚀
```

### See images of the project below
- **FrontEnd React**: 
![Texto alternativo](https://github.com/sovanderlei/contentctrl/blob/main/images/reactproject01.png)
![Texto alternativo](https://github.com/sovanderlei/contentctrl/blob/main/images/reactproject02.png)









