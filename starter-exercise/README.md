# Quarkus Workshop Exercise — “Coffee API”

Goal: Build a small CRUD REST API for managing coffees using Quarkus. You’ll learn project bootstrapping, RESTEasy Reactive, Panache with Hibernate ORM, configuration, health checks, OpenAPI, and tests.

## What you’ll build

Endpoints:

`GET /api/coffees` — list all

`GET /api/coffees/{id}` — get one

`POST /api/coffees` — create

`PUT /api/coffees/{id}` — update

`DELETE /api/coffees/{id}` — delete

Entity: Coffee(id, name, origin, price)

DB: in-memory H2 (dev/test)

Extras: Health, OpenAPI UI, Dev UI, tests with RestAssured
