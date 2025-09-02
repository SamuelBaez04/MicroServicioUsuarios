# 👥 FarmaSync - Microservicio de Usuarios

🚀 **Descripción**  
Este proyecto es el **Microservicio de Usuarios** dentro del sistema de gestión farmacéutica **FarmaSync**.  
Su responsabilidad principal es la **gestión de usuarios**, ofreciendo endpoints REST para el registro, consulta, actualización y eliminación de usuarios.  
Está diseñado con **Spring Boot** bajo una arquitectura en capas y expone documentación con **Swagger/OpenAPI**.

---

## 🚀 MICROSERVICIO (Puerto 8013)

📌 **Endpoints principales**
- 📨 `/farmasync/usuarios` → Gestión completa de usuarios  

---

## 🏥 MICROSERVICIOS RELACIONADOS
- 👥 **Usuarios Service** 
- 📦 **Pedidos Service** 
- 💰 **Ventas Service**   
- 📊 **Inventario Service**   

*(Todos orquestados mediante el API Gateway )*

---

## 🔧 Tecnologías

- Java 17  
- Spring Boot 3.3.4  
- Spring Web  
- Springdoc OpenAPI 2.6.0 (Swagger UI)  
- Maven  
- Arquitectura por capas (Controllers, Services, Repositories, Models)  
- Simulación de BD con `ArrayList` (sin conexión a DB en esta versión)

---

## 🔌 Endpoints del Microservicio

Base URL: `http://localhost:8013/farmaclick/usuarios`

| Método   | Endpoint          | Descripción                         | Códigos de estado posibles |
|----------|-------------------|-------------------------------------|----------------------------|
| **POST**   | `/`               | Crear un nuevo usuario              | `201 Created`, `400 Bad Request`, `409 Conflict` |
| **GET**    | `/`               | Listar todos los usuarios           | `200 OK` |
| **GET**    | `/{id}`           | Obtener un usuario por ID           | `200 OK`, `404 Not Found` |
| **PUT**    | `/{id}`           | Actualizar usuario (reemplazo total)| `200 OK`, `400 Bad Request`, `404 Not Found` |
| **PATCH**  | `/{id}`           | Actualización parcial de usuario    | `200 OK`, `400 Bad Request`, `404 Not Found` |
| **DELETE** | `/{id}`           | Eliminar un usuario                 | `204 No Content`, `404 Not Found` |

---

## 📚 Documentación Swagger

Accede a la documentación interactiva en:

http://localhost:8013/swagger-ui.html

**📝 Características implementadas**

✅ Gestión completa de usuarios (CRUD)
✅ Manejo de estados HTTP apropiados
✅ Simulación de base de datos en memoria con ArrayList
✅ Documentación automática con Swagger/OpenAPI
✅ Arquitectura en capas para mejor mantenimiento
