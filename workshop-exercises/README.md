# Quarkus Workshop - Task Management API

Welcome to the Quarkus Workshop! This workshop is designed for Java developers who want to learn Quarkus, a Kubernetes-native Java framework tailored for GraalVM and OpenJDK HotSpot.

## Workshop Overview

**Duration:** 1 hour 30 minutes

**Target Audience:** Developers proficient in Java

**Prerequisites:**
- Java 17 or higher
- Maven 3.8+
- IDE (IntelliJ IDEA, VS Code, or Eclipse)
- Basic understanding of REST APIs and Java

## What You'll Learn

- Building RESTful services with Quarkus and JAX-RS
- Using Hibernate ORM with Panache for database operations
- Configuration management in Quarkus
- Testing Quarkus applications
- Quarkus Dev Mode and live reload capabilities

## Workshop Structure

This workshop consists of 3 independent exercises. Each exercise is self-contained, so you can complete them at your own pace without depending on previous exercises.

### Exercise 1: Basic REST API (20 minutes)
Create a simple REST API for managing tasks with basic CRUD operations.

**Key Topics:**
- JAX-RS resources
- Request/response handling
- Basic validation
- REST Assured testing

### Exercise 2: Database Integration with Panache (30 minutes)
Extend the task management system with database persistence using Hibernate ORM and Panache.

**Key Topics:**
- Panache entities and repositories
- Database operations (CRUD)
- Transaction management
- H2 in-memory database
- Testing with database

### Exercise 3: Configuration & Advanced Features (20 minutes)
Add configuration management and advanced Quarkus features.

**Key Topics:**
- Configuration with @ConfigProperty
- Custom business logic
- Health checks
- OpenAPI/Swagger documentation

## Getting Started

1. **Setup your environment:** Follow the [SETUP.md](SETUP.md) guide
2. **Choose an exercise:** Each exercise folder contains:
   - `README.md` - Exercise instructions and tasks
   - `starter/` - Starter project with TODOs
   - `solution/` - Complete working solution
3. **Work on the exercise:** Follow the README instructions
4. **Run tests:** Verify your implementation with `mvn test`
5. **Compare with solution:** Check the solution folder if needed

## Exercise Structure

Each exercise follows this structure:

```
exercise-X/
├── README.md          # Exercise instructions
├── starter/           # Your working directory
│   ├── src/
│   │   ├── main/java/
│   │   └── test/java/
│   └── pom.xml
└── solution/          # Reference solution
    ├── src/
    │   ├── main/java/
    │   └── test/java/
    └── pom.xml
```

## Tips for Success

1. **Use Quarkus Dev Mode:** Run `mvn quarkus:dev` for live reload
2. **Read the TODOs:** Starter code contains helpful TODO comments
3. **Run tests frequently:** Use `mvn test` to verify your implementation
4. **Check the Dev UI:** Access http://localhost:8080/q/dev when in dev mode
5. **Explore OpenAPI:** Visit http://localhost:8080/q/swagger-ui when the app is running

## Additional Resources

- [Quarkus Official Documentation](https://quarkus.io/guides/)
- [Quarkus Getting Started](https://quarkus.io/get-started/)
- [Panache Guide](https://quarkus.io/guides/hibernate-orm-panache)
- [REST Client Guide](https://quarkus.io/guides/rest-client)

## Need Help?

- Check the solution folder for reference
- Review the Quarkus documentation
- Ask the workshop instructor

Happy coding with Quarkus! 🚀
