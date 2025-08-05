# Appointment System Backend

This is a backend application built with **Java**, **Spring Boot**, and **SQL** to manage users, services, availabilities, and appointments for an appointment booking system.

---

##  Tech Stack

- Java 17+  
- Spring Boot 3.x  
- Spring Data JPA (with SQL database)  
- Spring Security (for password encoding)  
- Maven (build tool)

---

##  Getting Started

### Prerequisites

- Java JDK 17 or higher installed  
- Maven installed  
- SQL database configured (MySQL)

### Build and Run

1. Clone the repository:

   ```bash
   git clone https://github.com/SomayaSuhaiby/Appointment_System_REST.git
   cd appointmentsystem-REST
   


###  API Endpoints
### User Management

| Method | Endpoint              | Description                | Request Body            |
|--------|-----------------------|----------------------------|-------------------------|
| POST   | `/api/users/register` | Register a new user        | `email`, `password`, etc.|
| POST   | `/api/users/login`    | Login with credentials     | `email`, `password`      |

###  Service Management

| Method | Endpoint                     | Description                                  | Request Body (JSON)                 |
|--------|------------------------------|----------------------------------------------|-------------------------------------|
| GET    | `/api/services/{providerId}` | Get all services for a specific provider     | None                              |
| POST   | `/api/services/create`       | Create a new service (provider required)     | `name`, `description`, `price`, `serviceProviderId` |

###  Availability Management

| Method | Endpoint                   | Description                              | Request Body (JSON)                                  |
|--------|----------------------------|------------------------------------------|-----------------------------------------------------|
| GET    | `/api/availability/list`   | Get all available service time slots     | None                                                |
| POST   | `/api/availability/create` | Create a new availability for a service  | `serviceProviderId`, `serviceId`, `start_time`, `end_time` |

###  Appointment Management

| Method | Endpoint                         | Description                            | Request Body (JSON)                                      |
|--------|---------------------------------|----------------------------------------|---------------------------------------------------------|
| POST   | `/api/appointment/book`          | Book a new appointment                 | `userId`, `serviceId`, `appointment_time`, `status`     |
| GET    | `/api/appointment/user/{userId}` | Get all appointments for a user       | None                                                    |
| GET    | `/api/appointment/service/{serviceId}` | Get all appointments for a service | None                                                    |
| PUT    | `/api/appointment/update/{id}`   | Update appointment status (confirmed, cancelled) | Query param: `status` (e.g., confirmed, cancelled)     |



##  Sample Request Bodies

### Booking an appointment Example

```json
{
  "userId": 1,
  "serviceId": 2,
  "appointment_time": "2025-08-10T15:00:00",
  "status": "CONFIRMED"
}
### create a new service
{
    "serviceProviderId": 6,
    "name": "hear test",
     "description": "entirly hear test",
    "price": 6000
}
 ### Create an availble service 
 {
  "serviceProviderId": 6,
  "serviceId": 3,
  "start_time": "2025-08-02T10:00:00",
  "end_time": "2025-08-02T11:00:00"
}
 
 ### Register a new user
{
    
    "username":"owais11",
     "email":"owais11@gmail.com",
     "password":"owais",
     "roleId":1
}

### log in Example
{
    
    "username":"owais2",
     "email":"owais2@gmail.com",
     "password":"owais",
     "rolesId":2
}