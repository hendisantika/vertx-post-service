# Vert.x Post Service

[![Java CI with Maven](https://github.com/hendisantika/vertx-post-service/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/vertx-post-service/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange.svg)
[![Vert.x](https://img.shields.io/badge/vert.x-5.2.2-purple.svg)](https://vertx.io)
![Dependabot](https://img.shields.io/badge/dependabot-enabled-025e8c?logo=dependabot)

A reactive REST API for managing blog posts, built with [Eclipse Vert.x 5](https://vertx.io) and the
reactive PostgreSQL client.

## Tech Stack

| Component        | Version                     |
|------------------|-----------------------------|
| Java (JDK)       | 25                          |
| Eclipse Vert.x   | 5.2.2 (`vertx-stack-depchain`) |
| Vert.x Web       | routing, body handling      |
| Vert.x PG Client | reactive PostgreSQL access  |
| Jackson          | 2.22.x (JSR-310 support)    |
| JUnit Jupiter    | 6.x + `vertx-junit5`        |
| Maven            | 3.10.0 (via Maven Wrapper)  |

## Prerequisites

- **JDK 25** (e.g. [Eclipse Temurin](https://adoptium.net/))
- **PostgreSQL** running on `localhost:5432`
- Docker (optional, for running PostgreSQL)

## Database Setup

The connection settings are configured in `MainVerticle#pgPool()`:

| Property | Value       |
|----------|-------------|
| Host     | `localhost` |
| Port     | `5432`      |
| Database | `blog_db`   |
| User     | `yu71`      |
| Password | `53cret`    |

Start PostgreSQL with Docker:

```bash
docker run -d --name blog-db \
  -e POSTGRES_DB=blog_db \
  -e POSTGRES_USER=yu71 \
  -e POSTGRES_PASSWORD=53cret \
  -p 5432:5432 \
  postgres:17
```

Create the `posts` table:

```bash
docker exec -i blog-db psql -U yu71 -d blog_db <<'SQL'
CREATE TABLE IF NOT EXISTS posts
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title      VARCHAR(255) NOT NULL,
    content    TEXT         NOT NULL,
    created_at TIMESTAMP             DEFAULT NOW()
);
SQL
```

On startup, `DataInitializer` clears the `posts` table and inserts two sample posts.

## Build & Run

Run the tests:

```bash
./mvnw clean test
```

Package the application (produces `target/vertx-post-service-1.0.0-SNAPSHOT-fat.jar`):

```bash
./mvnw clean package
```

Run the application from Maven:

```bash
./mvnw clean compile exec:java
```

Or run the fat jar (launched by `io.vertx.launcher.application.VertxApplication`):

```bash
java -jar target/vertx-post-service-1.0.0-SNAPSHOT-fat.jar
```

The HTTP server listens on **http://localhost:8888**.

## API Endpoints

| Method   | Path          | Description             | Success response                   |
|----------|---------------|-------------------------|------------------------------------|
| `GET`    | `/hello`      | Health/hello route      | `200` – `Hello from my route`      |
| `GET`    | `/posts`      | List all posts          | `200` – JSON array                 |
| `GET`    | `/posts/{id}` | Get a post by UUID      | `200` – JSON object / `404`        |
| `POST`   | `/posts`      | Create a post           | `201` + `Location: /posts/{id}`    |
| `PUT`    | `/posts/{id}` | Update a post           | `204` / `404`                      |
| `DELETE` | `/posts/{id}` | Delete a post           | `204` / `404`                      |

### Examples

```bash
# List posts
curl http://localhost:8888/posts

# Create a post
curl -i -X POST http://localhost:8888/posts \
  -H "Content-Type: application/json" \
  -d '{"title":"Hello Vert.x","content":"My first Vert.x 5 post"}'

# Get a post
curl http://localhost:8888/posts/<id>

# Update a post
curl -i -X PUT http://localhost:8888/posts/<id> \
  -H "Content-Type: application/json" \
  -d '{"title":"Updated title","content":"Updated content"}'

# Delete a post
curl -i -X DELETE http://localhost:8888/posts/<id>
```

## Project Structure

```
src/main/java/id/my/hendisantika/vertx_post_service
├── MainVerticle.java          # HTTP server, routes, PG pool, Jackson config
├── PostsHandler.java          # Route handlers
├── PostRepository.java        # Reactive SQL queries
├── DataInitializer.java       # Sample data on startup
├── Post.java                  # Entity
├── CreatePostCommand.java     # Request payload
└── PostNotFoundException.java
```

## CI/CD

GitHub Actions ([`.github/workflows/maven.yml`](.github/workflows/maven.yml)) runs on every push and pull request
to `main`:

1. Checks out the code
2. Sets up **Temurin JDK 25** with Maven caching
3. Runs `./mvnw -B verify`
4. Submits the dependency graph so Dependabot alerts stay accurate

## Dependency Management

[Dependabot](.github/dependabot.yml) checks for updates daily at 05:00 (Asia/Jakarta):

- **Maven** – updates are grouped (`vertx`, `junit`, `jackson`, `maven-plugins`, `test-libraries`),
  labelled `dependencies` + `java`, and committed with the `build(deps)` prefix. Vert.x major upgrades are
  ignored because they require a manual migration.
- **GitHub Actions** – all action updates are grouped into one PR, labelled `dependencies` + `github_actions`,
  and committed with the `ci(deps)` prefix.

## Help

- [Vert.x Documentation](https://vertx.io/docs/)
- [Vert.x 5 Migration Guide](https://vertx.io/docs/guides/vertx-5-migration-guide/)
- [Vert.x Stack Overflow](https://stackoverflow.com/questions/tagged/vert.x?sort=newest&pageSize=15)
- [Vert.x Discord](https://discord.gg/6ry7aqPWXy)

## Author

**Hendi Santika** – [s.id/hendisantika](https://s.id/hendisantika) · Telegram [@hendisantika34](https://t.me/hendisantika34)
