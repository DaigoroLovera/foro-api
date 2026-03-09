# Foro API - Backend

API REST para la gestión de un foro desarrollada con **Spring Boot**.
Este proyecto implementa autenticación segura mediante **JWT (JSON Web Token)** y acceso a base de datos con **JPA/Hibernate** y **MySQL**.

---

# Descripción

Esta API permite gestionar usuarios y autenticación dentro de un sistema de foro.
La autenticación se realiza mediante tokens JWT, permitiendo un sistema **stateless** seguro para proteger los endpoints de la aplicación.

El proyecto forma parte del desafío **Foro Hub** del programa de formación en backend con Java.

---

# Tecnologías utilizadas

* Java 17
* Spring Boot
* Spring Security
* JWT (JSON Web Token)
* Spring Data JPA
* Hibernate
* MySQL
* Maven

---

# Arquitectura del proyecto

El proyecto sigue una arquitectura en capas:

```
controller
service
repository
model
config
dto
```

**Controller**
Gestiona las solicitudes HTTP.

**Service**
Contiene la lógica de negocio.

**Repository**
Acceso a la base de datos mediante Spring Data JPA.

**Model**
Entidades del sistema.

**Config**
Configuración de seguridad y filtros JWT.

**DTO**
Objetos de transferencia de datos.

---

# Autenticación

La API utiliza **JWT** para la autenticación.

Flujo de autenticación:

1. El usuario envía email y contraseña al endpoint `/login`.
2. El servidor valida las credenciales.
3. Se genera un **token JWT**.
4. El cliente debe enviar el token en el header:

```
Authorization: Bearer TOKEN
```

---

# Endpoints principales

## Login

POST `/login`

Ejemplo de request:

```json
{
  "email": "usuario@email.com",
  "password": "123456"
}
```

Respuesta:

```
TOKEN_JWT
```

---

# Configuración del proyecto

Configurar base de datos en `application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/foro
spring.datasource.username=root
spring.datasource.password=tu_password
```

Configuración JWT:

```
jwt.secret=tu_clave_secreta
jwt.expiration=3600000
```

---

# Ejecutar el proyecto

1. Clonar el repositorio

```
git clone https://github.com/tuusuario/foro-api.git
```

2. Entrar al proyecto

```
cd foro-api
```

3. Ejecutar

```
mvn spring-boot:run
```

El servidor se iniciará en:

```
http://localhost:8080
```

---

# Seguridad

La API utiliza:

* Autenticación basada en JWT
* Spring Security
* Filtros de autorización para proteger endpoints
* Sesiones stateless

---

# Autor

Proyecto desarrollado como parte de la formación en backend con Java.

---
