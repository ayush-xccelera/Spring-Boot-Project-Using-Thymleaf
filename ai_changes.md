COMMIT_MESSAGE: Add JWT authentication with BCrypt user credentials

## Features Added
- JWT (HS256) authentication endpoints under `/api/v1/auth` for registration, login, and logout.
- BCrypt password hashing for newly registered users.
- One-hour JWT expiry configured through application properties.
- CORS support and an exposed actuator health endpoint.

## Files Modified
- pom.xml — added Spring Security, Actuator, and JJWT dependencies.
- src/main/java/Property/Property/entity/User.java — added persisted password and unique email constraints.
- src/main/java/Property/Property/jpa/UserRepository.java — added email lookup and duplicate-email checks.
- src/main/resources/application.properties — set port, configured JWT secret/expiry, health exposure, and resolved JDBC URL.
- bin/src/main/resources/application.properties — updated the tracked JDBC URL copy.

## Files Added
- src/main/java/Property/Property/config/SecurityConfig.java — BCrypt password encoder and CORS/security configuration.
- src/main/java/Property/Property/controller/AuthController.java — REST authentication endpoints.
- src/main/java/Property/Property/dto/RegisterRequest.java — validated registration request payload.
- src/main/java/Property/Property/dto/LoginRequest.java — validated login request payload.
- src/main/java/Property/Property/dto/AuthResponse.java — JWT authentication response payload.
- src/main/java/Property/Property/service/AuthService.java — registration and login workflows.
- src/main/java/Property/Property/service/JwtService.java — HS256 JWT creation with configured expiry.

## Secrets Moved
- JWT signing key -> app.secret.jwt

## DB URLs Resolved
- jdbc:mysql://localhost:3306/propertymanager?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC -> jdbc:mysql://localhost:3306/gen_2e4c945bd671

## Compilation Result
PASSED — `mvn compile -q` and `mvn package -DskipTests -q` completed successfully using OpenJDK 21.0.12.1.
