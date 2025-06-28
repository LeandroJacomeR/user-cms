# 👥 User

Login con arquitectura Hexagonal encargado de la gestión de usuarios y autenticación.

## 🧰 Tecnologías

- Java 17 + Spring Boot
- PostgreSQL + JPA
- Spring Security + JWT
- OpenAPI (Swagger)

## 📦 Endpoints

### 🔐 AuthController (`/auth`)
| Método | Endpoint         | Descripción                   | Seguridad |
|--------|------------------|-------------------------------|-----------|
| POST   | `/auth/login`    | Iniciar sesión                | ❌        |
| POST   | `/auth/register` | Registro de nuevo usuario     | ❌        |
| POST   | `/auth/refresh`  | Refrescar token JWT           | ✅        |

### 👤 UserController (`/users`)
| Método | Endpoint         | Descripción                       | Seguridad |
|--------|------------------|-----------------------------------|-----------|
| GET    | `/users`         | Listar todos los usuarios         | ✅        |
| GET    | `/users/{id}`    | Obtener usuario por ID            | ✅        |
| POST   | `/users`         | Crear nuevo usuario               | ✅        |
| PUT    | `/users/{id}`    | Actualizar usuario existente      | ✅        |
| DELETE | `/users/{id}`    | Eliminar usuario por ID           | ✅        |

✅ = Requiere JWT  
❌ = No requiere autenticación

## 🧪 Modelos principales

### User
- `id`: Long  
- `name`: String  
- `email`: String  
- `password`: String  
- `role`: Role  

### Role
- `id`: Long  
- `name`: String  
- `permissions`: List\<Permission\>

### Permission
- `id`: Long  
- `action`: String  
- `resource`: String  
