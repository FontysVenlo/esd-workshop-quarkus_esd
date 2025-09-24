# Quarkus Introduction

Welcome to the Quarkus workshop! This document introduces the key concepts, features, and benefits of Quarkus. It will serve as your reference throughout the session.

---

## What is Quarkus?

Quarkus is a **Kubernetes-native Java framework** tailored for building **cloud-native applications**.  
It is optimized for:
- **Fast startup times**
- **Low memory footprint**
- **Developer productivity**

Quarkus combines the best of the **Java ecosystem** (Jakarta EE, MicroProfile, Spring APIs) with modern cloud and container technologies.

---

## Key Concepts

### ⚡ Supersonic, Subatomic Java
- **Startup & memory efficiency:** Ideal for containers, serverless platforms, and microservices.
- Applications start in **milliseconds** with a small memory footprint compared to traditional Java frameworks.

### 🏗 Build-Time Augmentation
- Much of the heavy lifting (like CDI wiring, JAX-RS endpoint scanning, ORM metadata, config loading) is moved from **runtime → build time**.
- This makes runtime execution lean and efficient.

### 😃 Developer Joy
- **Dev Mode:**  
  Run your app in `quarkus:dev` and enjoy **live reload** of code changes.
- **Dev UI:**  
  A web interface (http://localhost:8080/q/dev) for managing extensions, configuration, and runtime info.
- **Dev Services:**  
  Auto-provision services like Kafka, Postgres, or Keycloak using Testcontainers. No manual setup needed in dev.

### 🔄 Two Modes of Execution
- **JVM Mode:**  
  Great for development and fast iteration.  
- **Native Mode (via GraalVM or Mandrel):**  
  Produces an executable with extremely fast startup and minimal memory usage. Perfect for serverless and Kubernetes.

---

## Familiar APIs & Ecosystem

Quarkus reuses and integrates familiar Java APIs so you don’t need to relearn everything:

- **REST & Messaging**
  - JAX-RS with RESTEasy Reactive
  - Reactive Messaging (Kafka, AMQP, MQTT)

- **Dependency Injection**
  - CDI with Arc (Quarkus’ CDI implementation)

- **Persistence**
  - Hibernate ORM with Panache (simplified data access)
  - Flyway & Liquibase support

- **Cloud-Native Essentials**
  - Config (MicroProfile Config)
  - OpenAPI & Swagger UI
  - Health checks
  - Metrics
  - Security (JWT, OIDC, OAuth2)

- **Other Utilities**
  - Scheduler
  - Mailer
  - gRPC
  - Reactive APIs

---

## Why Quarkus?

- **Optimized for containers and Kubernetes**
  - Smaller images
  - Lower resource usage
  - Faster scaling

- **Productivity for developers**
  - Live reload & hot reloading
  - Built-in testing support
  - Simplified configuration

- **Flexibility**
  - Run in **JVM mode** during development
  - Deploy in **native mode** for production

---

## Summary

Quarkus is all about combining:
- **The Java you know** (APIs, libraries, ecosystem)  
- With **the speed and efficiency cloud environments demand**

It empowers you to build **modern microservices** with **supersonic startup times**, **subatomic memory usage**, and **developer-friendly tooling**.

---

👉 Next: Try the [Demo](starter-exercise/README.md) to see Quarkus in action.
