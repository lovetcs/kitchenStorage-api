# KitchenStorage API
KitchenStorage API is a Java Spring Boot application designed to manage kitchen inventory. The application is deployed on an AWS EC2 instance and utilizes Amazon DynamoDB for data storage. Continuous integration and deployment are automated using GitHub Actions.

## 🚀 Features
- **Inventory Management**: Efficiently track and manage kitchen items.
- **RESTful API**: Provides endpoints for CRUD operations on kitchen inventory items.
- **Expiration Tracking**: Monitor and notify about items nearing expiration.
- **Category Management**: Organize items by custom categories.

## 🏗️ Architecture Overview
The application follows a microservices architecture with the following components:
- **Spring Boot Application**: Handles business logic and exposes RESTful endpoints.
- **Amazon DynamoDB**: NoSQL database for scalable and flexible data storage.
- **AWS EC2 Instance**: Hosts the Spring Boot application, ensuring high availability.
- **GitHub Actions**: Automates the build, test, and deployment processes.

## ✅ Prerequisites
Before setting up the project, ensure you have the following:
- **Java Development Kit (JDK)**: Version 11 or higher
- **AWS Account**: Access to AWS services like EC2 and DynamoDB
- **GitHub Repository**: Fork or clone the [KitchenStorage API repository](https://github.com/lovetcs/kitchenStorage-api)

## ⚙️ Setup Instructions
### 1. Clone the Repository
```bash
git clone https://github.com/lovetcs/kitchenStorage-api.git
cd kitchenStorage-api
```
## 2. Configure AWS Credentials
Create an AWS IAM user with appropriate permissions and configure credentials:
```bash
aws configure
Enter your AWS Access Key ID, Secret Access Key, default region, and output format when prompted.
```
## 3. Configure Application Properties
Copy the sample properties file and update with your configuration:
```bash
cp src/main/resources/application.properties.sample src/main/resources/application.properties
```
Edit application.properties to include your specific AWS region, DynamoDB table names, and other settings.
## 4. Build the Application
Use Maven to build the application:
```bash
./mvnw clean package
```
## 5. Run Locally
Start the application locally for development:
```bash
./mvnw spring-boot:run
```
The API will be available at http://localhost:8080.
## 🚢 Deployment
Automated Deployment with GitHub Actions

Fork the repository to your GitHub account.
Add the following secrets to your repository settings:

AWS_ACCESS_KEY_ID
AWS_SECRET_ACCESS_KEY
EC2_HOST
EC2_USERNAME
EC2_SSH_KEY


Push changes to the main branch to trigger automatic deployment.

Manual Deployment to EC2

Build the JAR file:
bash./mvnw clean package

Transfer the JAR to your EC2 instance:
bashscp -i your-key.pem target/kitchenStorage-api-0.1.0.jar ec2-user@your-ec2-instance:~/

SSH into your EC2 instance and run the application:
bashssh -i your-key.pem ec2-user@your-ec2-instance
java -jar kitchenStorage-api-0.1.0.jar


## 📚 API Documentation
Base URL
When deployed: http://your-ec2-instance-ip:8080/api
Local development: http://localhost:8080/api
Endpoints
Items

GET /items - Retrieve all items
GET /items/{id} - Retrieve a specific item
POST /items - Create a new item
PUT /items/{id} - Update an existing item
DELETE /items/{id} - Delete an item


Example request for creating an item:
jsonPOST /api/items
{
  "name": "Milk",
  "quantity": 2,
  "expirationDate": "2025-04-15",
  "category": "Dairy",
  "location": "Refrigerator"
}
 ##🧪 Testing
Run the test suite with:
bash./mvnw test
For integration tests that interact with AWS services:
bash./mvnw verify -P integration-test
🧩 E-Paper Display Setup
For information on setting up and connecting the e-paper display hardware, refer to the E-Paper Display Integration Guide.
## 🤝 Contributing

Fork the repository
Create a feature branch: git checkout -b feature/amazing-feature
Commit your changes: git commit -m 'Add amazing feature'
Push to the branch: git push origin feature/amazing-feature
Open a Pull Request

📄 License
This project is licensed under the MIT License - see the LICENSE file for details.
📬 Contact
Project Link: https://github.com/lovetcs/kitchenStorage-api
