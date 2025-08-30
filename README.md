# MicroServicioUsuarios

# 🧑‍💻 Microservicio de Usuarios - FarmaClick

Este repositorio contiene el microservicio de **Usuarios** para el sistema **FarmaClick**, desarrollado en **Spring Boot** siguiendo la arquitectura multicapa y el flujo de trabajo con **Gitflow**.  

## 📌 Descripción
El microservicio gestiona la información relacionada con los **usuarios** del sistema, permitiendo:
- Registrar usuarios.
- Consultar usuarios.
- Actualizar datos.
- Eliminar usuarios.

> ⚠️ En esta versión, la "base de datos" se simula con listas en memoria (`ArrayList`), cumpliendo los requisitos del entregable.

---

## 🏗️ Tecnologías
- **Java 17**
- **Spring Boot 3.4.3**
- **Maven**
- **Lombok**
- **Swagger (Springdoc OpenAPI)**

---

## 📂 Estructura del proyecto

src/main/java/com/farmaclick/usuarios
├── controllers     # Controladores REST (API)
├── models          # Entidades o clases de dominio
├── repositories    # Acceso a datos (simulado con ArrayList)
└── services        # Lógica de negocio
