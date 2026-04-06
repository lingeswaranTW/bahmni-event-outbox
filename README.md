# Bahmni Event Outbox

An OpenMRS module that implements the **Transactional Outbox Pattern** for reliable event publishing in the Bahmni healthcare system.

---

## What It Does

When a business operation occurs (e.g., a patient is registered or an order is placed), this module captures the corresponding event in the same database transaction. This guarantees that events are never lost — even if the downstream message broker or external system is temporarily unavailable.

```
Business Operation
      ↓
  EMREvent published
      ↓
  EMREventListener (Spring @EventListener)
      ↓
  OutboxEventService.save()
      ↓
  event_records_queue1 table (same DB transaction)
```

---

## Architecture

```
org.bahmni.module.eventoutbox
├── EMREvent<T>              # Generic Spring ApplicationEvent for any domain object
├── eventListener/
│   └── EMREventListener     # Catches EMREvents and persists them to the outbox
├── model/
│   └── OutboxEvent          # JPA entity mapped to event_records_queue1
├── service/
│   ├── OutboxEventService   # Service interface
│   └── impl/
│       └── OutboxEventServiceImpl  # Transactional service implementation
└── dao/
    ├── OutboxEventDao       # DAO interface
    └── impl/
        └── OutboxEventDaoImpl      # Hibernate-based persistence
```

---

## Database Schema

The module creates an `event_records_queue1` table via Liquibase on startup.

| Column      | Type           | Description                        |
|-------------|----------------|------------------------------------|
| `id`        | INT (PK)       | Auto-incremented primary key       |
| `uuid`      | VARCHAR(40)    | Unique event identifier (UUID v4)  |
| `title`     | VARCHAR(255)   | Event title/name                   |
| `timestamp` | TIMESTAMP      | Event creation time (auto-set)     |
| `uri`       | VARCHAR(255)   | Resource URI reference             |
| `object`    | VARCHAR(1000)  | Event payload/content              |
| `category`  | VARCHAR(255)   | Event category or type             |
| `tags`      | VARCHAR(255)   | Tags for filtering (defaults to category) |

---

## Publishing an Event

Inject the Spring `ApplicationEventPublisher` and publish an `EMREvent` anywhere in your business logic:

```java
@Autowired
private ApplicationEventPublisher eventPublisher;

public void registerPatient(Patient patient) {
    // ... save patient ...
    eventPublisher.publishEvent(new EMREvent<>(patient, "PATIENT_CREATED"));
}
```

The `EMREventListener` will automatically persist the event to the outbox table within the same transaction.

---

## Requirements

- OpenMRS 2.6.15 or higher
- Java 8
- Maven

---

## Build

```bash
mvn clean install
```

This produces an `.omod` file under `omod/target/` which can be deployed to OpenMRS.

---

## Module Info

| Property       | Value                          |
|----------------|-------------------------------|
| Module ID      | `eventoutbox`                 |
| Version        | 1.0.0-SNAPSHOT                |
| Organization   | Bahmni (https://www.bahmni.org) |
| OpenMRS Min    | 2.6.15                        |
