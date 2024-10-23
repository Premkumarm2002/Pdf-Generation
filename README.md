Here’s a comprehensive README file for your GitHub repository based on the Spring Boot PDF generation application:

```markdown
# Dynamic PDF Generation with Spring Boot

## Overview

This project is a Spring Boot application that provides a REST API for dynamically generating PDFs using Thymeleaf as the template engine. The application takes structured input and generates a PDF containing seller, buyer, and item details.

## Features

- Accepts JSON input to generate a PDF.
- Allows users to download the generated PDF.
- Stores PDFs locally to prevent re-generation for identical input.
- Produces a well-structured single-page PDF with seller, buyer, and item information.

## Technology Stack

- **Java**: 17
- **Spring Boot**: 3.1.0
- **Thymeleaf**: For HTML template processing
- **iText**: For PDF generation
- **Maven**: For dependency management


## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- An IDE (e.g., Spring tool suite, Eclipse)

### Installation

1. Clone the repository:
   ```bash
   git clone https:https://github.com/Premkumarm2002/Pdf-Generation
   cd Pdf-Generation
   ```

2. Build the project using Maven:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

### Application Properties

Configure your application by adding the following properties to `src/main/resources/application.properties`:

```properties
# Server port
server.port=8080
```

### API Endpoints

**POST** `/api/pdf/generate`

- **Request Body**:
```json
{
    "seller": "XYZ Pvt. Ltd.",
    "sellerGstin": "29AABBCCDD121ZD",
    "sellerAddress": "New Delhi, India",
    "buyer": "Vedant Computers",
    "buyerGstin": "29AABBCCDD131ZD",
    "buyerAddress": "New Delhi, India",
    "items": [
        {
            "name": "Product 1",
            "quantity": "12 Nos",
            "rate": 123.00,
            "amount": 1476.00
        }
    ]
}
```

- **Response**: Returns the filename of the generated PDF.

**GET** `/api/pdf/download/{fileName:.+}`

- **Description**: Download the generated PDF file by filename.

### Testing with Postman

1. Open Postman.
2. Set the request type to `POST`.
3. Enter the URL: `http://localhost:8080/api/pdf/generate`.
4. In the Body tab, select `raw` and `JSON`, then paste the request body.
5. Send the request to generate the PDF.

**To download the PDF:**
1. Set the request type to `GET`.
2. Enter the URL: `http://localhost:8080/api/pdf/download/{fileName}`.
3. Replace `{fileName}` with the actual name of the generated PDF (e.g., `XYZ_Pvt._Ltd..pdf`).

### Unit Testing

You can create unit tests for your service and controller using JUnit and Mockito. Basic tests can be added in the `src/test/java/com/example/pdfgenerator/service/PdfServiceTest.java` file.

