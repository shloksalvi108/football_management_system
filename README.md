football_management_system
==========================

Java-based Football Management Retrieval System that allows text-based search for player statistics using AWT (Swing), PostgreSQL, JDBC, and a modular DAO architecture.

## How to run

1. Ensure you have Java (JDK 11 recommended) installed and PostgreSQL running locally.
2. Create a database `football_db` and a table:

   ```sql
   CREATE TABLE IF NOT EXISTS players (
       id SERIAL PRIMARY KEY,
       name VARCHAR(100) NOT NULL,
       country VARCHAR(100),
       club VARCHAR(100),
       total_goals INT DEFAULT 0
   );
   ```

3. Update the database URL, user, and password in `AppConfig` class if needed.

   Current defaults in this repo:
   - URL: `jdbc:postgresql://localhost:5432/football_db`
   - User: `postgres`
   - Password: `Shlok999$`
4. Install Maven (recommended). This project includes a `pom.xml` so Maven downloads the PostgreSQL JDBC driver automatically.

5. Run with Maven (from project root):

   ```bash
   mvn -q clean compile exec:java
   ```

## Run without Maven (manual classpath)

If you don't want Maven, you must download the PostgreSQL JDBC driver JAR and add it to `-cp`.

1. Compile:

   ```bash
   javac -d out $(dir /s /b src\\main\\java\\*.java)
   ```

2. Run (replace the jar path):

   ```bash
   java -cp "out;C:\path\to\postgresql.jar" com.fmrs.app.MainApplication
   ```

6. Use the UI to search by:
   - Player name (e.g., "Messi", "Ronaldo")
   - `player with maximum goals`
   - `top 5 scorers` (or any number)



   7. Run Code By this Command
      - javac -d out (Get-ChildItem -Recurse src\main\java\*.java | ForEach-Object { $_.FullName })
      - java -cp "out;lib\postgresql-42.7.4.jar" com.fmrs.app.MainApplication
