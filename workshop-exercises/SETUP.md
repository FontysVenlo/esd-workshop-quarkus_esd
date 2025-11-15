# Workshop Setup Guide

This guide will help you set up your environment for the Quarkus workshop.

## Prerequisites

### Required Software

1. **Java Development Kit (JDK) 17 or higher**
   - Download from [OpenJDK](https://adoptium.net/) or [Oracle](https://www.oracle.com/java/technologies/downloads/)
   - Verify installation:
     ```bash
     java -version
     ```
   - Should show version 17 or higher

2. **Apache Maven 3.8+**
   - Download from [Maven](https://maven.apache.org/download.cgi)
   - Verify installation:
     ```bash
     mvn -version
     ```
   - Should show Maven 3.8.x or higher

3. **IDE (Choose one)**
   - IntelliJ IDEA (Community or Ultimate)
   - Visual Studio Code with Java extensions
   - Eclipse with Java EE support

### Optional but Recommended

1. **Quarkus CLI**

   **For Linux, macOS, and Windows (WSL/bash):**
   ```bash
   curl -Ls https://sh.jbang.dev | bash -s - trust add https://repo1.maven.org/maven2/io/quarkus/quarkus-cli/
   curl -Ls https://sh.jbang.dev | bash -s - app install --fresh --force quarkus@quarkusio
   ```

   **For Windows PowerShell:**
   ```powershell
   iex "& { $(iwr https://ps.jbang.dev) } trust add https://repo1.maven.org/maven2/io/quarkus/quarkus-cli/"
   iex "& { $(iwr https://ps.jbang.dev) } app install --fresh --force quarkus@quarkusio"
   ```

   **Using SDKMAN:**
   ```bash
   sdk install quarkus
   ```

   **Verify installation:**
   ```bash
   quarkus version
   ```

2. **cURL or HTTPie**
   - For testing REST APIs from command line
   - Most systems have cURL pre-installed
   - Verify: `curl --version`

3. **Git**
   - For version control
   - Download from [git-scm.com](https://git-scm.com/)

## Verify Your Setup

### 1. Check Java
```bash
java -version
```
Expected output (version may vary):
```
openjdk version "17.0.x" 2023-xx-xx
```

### 2. Check Maven
```bash
mvn -version
```
Expected output should show Maven 3.8+ and Java 17+

### 3. Test Maven Build
Navigate to any exercise starter folder and run:
```bash
cd workshop-exercises/exercise-1/starter
mvn clean compile
```
This should download dependencies and compile successfully.

## IDE Setup

### IntelliJ IDEA

1. **Install Quarkus Plugin (Optional but recommended)**
   - Go to `File > Settings > Plugins`
   - Search for "Quarkus"
   - Install and restart

2. **Import Project**
   - `File > Open`
   - Select the exercise folder (containing pom.xml)
   - Wait for Maven to import dependencies

### Visual Studio Code

1. **Install Extensions**
   - Java Extension Pack (by Microsoft)
   - Quarkus (by Red Hat)
   - REST Client (optional, for testing)

2. **Open Project**
   - `File > Open Folder`
   - Select the exercise folder

### Eclipse

1. **Install Quarkus Tools (Optional)**
   - `Help > Eclipse Marketplace`
   - Search for "Quarkus"
   - Install Quarkus Tools

2. **Import Project**
   - `File > Import > Maven > Existing Maven Projects`
   - Select the exercise folder

## Running Your First Quarkus Application

1. Navigate to exercise-1/starter:
   ```bash
   cd workshop-exercises/exercise-1/starter
   ```

2. Run in dev mode:
   ```bash
   mvn quarkus:dev
   ```

3. You should see:
   ```
   Listening for transport dt_socket at address: 5005
   __  ____  __  _____   ___  __ ____  ______
    --/ __ \/ / / / _ | / _ \/ //_/ / / / __/
    -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \
   --\___\_\____/_/ |_/_/|_/_/|_|\____/___/
   INFO  [io.quarkus] (Quarkus Main Thread) quarkus-workshop 1.0.0-SNAPSHOT on JVM ...
   INFO  [io.quarkus] (Quarkus Main Thread) Installed features: [...]
   ```

4. Access the application:
   - Application: http://localhost:8080
   - Dev UI: http://localhost:8080/q/dev
   - Health: http://localhost:8080/q/health

5. Stop the application:
   - Press `Ctrl+C` or type `q` and press Enter

## Running Tests

```bash
mvn test
```

Or run continuously:
```bash
mvn quarkus:test
```

## Common Issues and Solutions

### Issue: Port 8080 already in use
**Solution:**
- Stop the process using port 8080, or
- Change the port in `src/main/resources/application.properties`:
  ```properties
  quarkus.http.port=8081
  ```

### Issue: Maven dependencies not downloading
**Solution:**
- Check your internet connection
- Clear Maven cache: `rm -rf ~/.m2/repository`
- Try again: `mvn clean install`

### Issue: Java version mismatch
**Solution:**
- Ensure JAVA_HOME points to JDK 17+
- Check with: `echo $JAVA_HOME` (Linux/Mac) or `echo %JAVA_HOME%` (Windows)
- Set JAVA_HOME if needed

### Issue: Tests failing with "Connection refused"
**Solution:**
- This is normal if the app isn't running
- Tests use a different test profile and should work standalone
- Try: `mvn clean test`

## Useful Commands

| Command | Description |
|---------|-------------|
| `mvn quarkus:dev` | Run in development mode with live reload |
| `mvn test` | Run all tests |
| `mvn clean package` | Build the application |
| `mvn quarkus:test` | Run tests in continuous mode |
| `./mvnw quarkus:dev` | Use Maven wrapper (if available) |

## Next Steps

Once your setup is complete:
1. Navigate to [Exercise 1](exercise-1/README.md) to begin
2. Open the starter project in your IDE
3. Follow the exercise instructions

## Need Help?

If you encounter issues:
1. Check the Quarkus documentation: https://quarkus.io/guides/
2. Verify all prerequisites are correctly installed
3. Ask the workshop instructor

Ready to start? Head to [Exercise 1](exercise-1/README.md)! 🚀
