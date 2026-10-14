# Fibonacci Series Generator (Maven + JUnit 5 + Jenkins CI)

A Java application built with Apache Maven to compute and display Fibonacci series, accompanied by a JUnit 5 test suite and continuous integration configuration for Jenkins.

---

## 📁 Project Structure

```
.
├── .gitignore
├── Jenkinsfile                          # Jenkins Pipeline definition
├── pom.xml                              # Maven project configuration
├── README.md                            # Documentation and Jenkins setup guide
└── src
    ├── main
    │   └── java
    │       └── com
    │           └── example
    │               └── fibonacci
    │                   ├── FibonacciGenerator.java  # Core business logic
    │                   └── Main.java                # Application CLI entry point
    └── test
        └── java
            └── com
                └── example
                    └── fibonacci
                        └── FibonacciGeneratorTest.java # JUnit 5 test suite
```

---

## 🚀 Building & Running Locally

### Prerequisites
- **JDK 17** or higher (`java -version`)
- **Apache Maven 3.8+** (`mvn -version`)

### 1. Compile and Run Tests
```bash
mvn clean test
```
This compiles the code and executes all JUnit 5 test cases. Test reports will be generated in `target/surefire-reports/`.

### 2. Package the Application
```bash
mvn clean package
```
This generates the executable JAR file in `target/fibonacci-series-1.0-SNAPSHOT.jar`.

### 3. Run the Program
```bash
# Run with default 10 terms:
java -jar target/fibonacci-series-1.0-SNAPSHOT.jar

# Run with a custom number of terms (e.g., 15):
java -jar target/fibonacci-series-1.0-SNAPSHOT.jar 15
```

---

## 🧪 JUnit 5 Test Suite Details

The test suite in `FibonacciGeneratorTest.java` validates:
- **Zero terms**: Returns an empty list (`[]`).
- **Single term**: Returns `[0]`.
- **Two terms**: Returns `[0, 1]`.
- **First 5 and 10 terms**: Validates correct sequence values (`[0, 1, 1, 2, 3, 5, 8, 13, 21, 34]`).
- **Input validation**: Verifies `IllegalArgumentException` is thrown for negative numbers.
- **Parameterized N-th term tests**: Validates individual sequence values up to $F(10)$.

---

## 🛠️ Jenkins Configuration Guide

You can configure Jenkins to build and test this project using either **Option A: Jenkins Pipeline (Recommended)** or **Option B: Freestyle Project**.

---

### Prerequisites in Jenkins

1. **Install Required Plugins** (under `Manage Jenkins` > `Plugins` > `Available Plugins`):
   - **Git plugin**
   - **Pipeline** (workflow-aggregator)
   - **JUnit plugin**
2. **Configure JDK and Maven Tools** (under `Manage Jenkins` > `Tools`):
   - **JDK**:
     - Name: `JDK-17`
     - Select *Install automatically* OR specify `JAVA_HOME`.
   - **Maven**:
     - Name: `Maven-3.9`
     - Select *Install automatically* (version `3.9.x`) OR specify `MAVEN_HOME`.

---

### Option A: Jenkins Pipeline Job (Using `Jenkinsfile`)

The repository includes a ready-to-run [Jenkinsfile](file:///d:/New%20folder/a/Jenkinsfile).

1. Go to Jenkins Dashboard and click **New Item**.
2. Enter item name: `fibonacci-series-pipeline`.
3. Select **Pipeline** and click **OK**.
4. In the job configuration:
   - Scroll down to the **Pipeline** section.
   - Set **Definition** to: `Pipeline script from SCM`.
   - Set **SCM** to: `Git`.
   - Set **Repository URL** to your Git repository URL (or local repo path).
   - Specify Branch Specifier: `*/main` or `*/master`.
   - Set **Script Path**: `Jenkinsfile`.
5. Click **Save**.
6. Click **Build Now** to run the pipeline.

#### What the Pipeline Does:
1. **Checkout**: Clones the repository.
2. **Verify Environment**: Checks Java and Maven versions.
3. **Compile**: Runs `mvn clean compile`.
4. **Run Unit Tests**: Runs `mvn test`.
5. **Package**: Runs `mvn package -DskipTests`.
6. **Post Actions**:
   - Publishes JUnit test result reports (`target/surefire-reports/*.xml`).
   - Archives the produced `.jar` artifact (`target/*.jar`).

---

### Option B: Jenkins Freestyle Project

If you prefer using a classic Jenkins Freestyle project:

1. Click **New Item** on the Jenkins Dashboard.
2. Enter item name: `fibonacci-series-freestyle`.
3. Select **Freestyle project** and click **OK**.
4. **Source Code Management**:
   - Select **Git** and enter your repository URL.
5. **Build Environment**:
   - Check **Provide Tools** (or select your configured JDK and Maven).
6. **Build Steps**:
   - Click **Add build step** > **Invoke top-level Maven targets**.
   - Maven Version: Select your configured `Maven-3.9`.
   - Goals: `clean test package`
7. **Post-build Actions**:
   - Click **Add post-build action** > **Publish JUnit test result report**.
     - Test report XMLs: `**/target/surefire-reports/*.xml`
   - Click **Add post-build action** > **Archive the artifacts**.
     - Files to archive: `target/*.jar`
8. Click **Save** and then **Build Now**.
