# Ecommerce cart application
Author: [Andree Anchi](https://www.linkedin.com/in/andree-anchi-dueñas/)
## Dependencies
- Java 17
- Maven 3.8.2
- H2 Database
- Spring Boot 2.5.5
- Spring Data JPA
- Spring Web
- Spring Validation
- Spring Test

## How to run
- Clone this repository
- Run `mvn clean install` to build the project
- Run `mvn spring-boot:run` to run the project
- The application will be available at `http://localhost:8080/v1`
- The H2 database will be available at `http://localhost:8080/v1/h2-console`
    - JDBC URL: `jdbc:h2:mem:testdb`
    - User Name: `sa`
    - Password: `sa`
- The Swagger documentation will be available at `http://localhost:8080/v1/swagger-ui.html`

## How to use

### Products
- `GET /product` - Get all products

### Carts
- `GET /cart` - Get all carts
- `GET /cart/{id}` - Get cart by id
- `POST /cart` - Create a new cart
- `DELETE /cart/{id}` - Delete cart by id
- `POST /cart/{id}/products` - Add product to cart
- `DELETE /cart/{id}/products/{productId}` - Remove product from cart [TODO]
- `PUT /cart/{id}/products/{productId}` - Update product quantity in cart [TODO]