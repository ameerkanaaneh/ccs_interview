# Ameer Ahmed

# Game Server - Deployment Guide

This guide will help you deploy and run the application on your system or server. Follow the steps carefully to ensure that everything is set up correctly.

---

## Prerequisites

Before you begin, ensure that the following software is installed on your system:

1. **Docker**  
   Docker allows you to run your application in an isolated environment.

   - **Install Docker**: [Docker Installation Guide](https://docs.docker.com/get-docker/)

2. **Git** (Optional, for cloning the repository)  
   Git is required to clone the repository containing the application.

   - **Install Git**: [Git Installation Guide](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)

3. **Java 17+** (If you are running the application outside Docker)  
   Java is required to compile and run the application.

   - **Install Java**: [Java Installation Guide](https://adoptopenjdk.net/)

4. **Maven** (If building the project manually outside Docker)  
   Maven is required to manage dependencies and build the project.
   - **Install Maven**: [Maven Installation Guide](https://maven.apache.org/install.html)

---

## Setting Up the Application

### Option 1: Deploying via Docker (Recommended)

1. **Clone the Repository (Optional)**  
   If you don't have the project files, clone the repository:

   ```bash
   git clone https://github.com/ameerkanaaneh/ccs_interview.git
   cd ccs_interview

   ```

2. **Build the Docker Image**  
    Once inside the project directory, you can build the Docker image by running:

   ```bash
   docker build -t game-server .

   ```

3. **Run the Docker Container**  
   After building the Docker image, run the container:

   ```bash
   docker run -p 8080:8080 game-server

   ```

4. **Access the Application**  
   After running the container, you should be able to access the application via any terminal connected to the container.
