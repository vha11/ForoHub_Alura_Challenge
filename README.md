# ForoHub Alura Challenge Backend

## Desafío Alura Latam - Oracle One

Bienvenido al desafío **ForoHub Alura**. En este desafío, nos enfocaremos en desarrollar una API REST utilizando **Java** y **Spring Boot** para la implementación de un foro en el backend. Este foro permitirá a los usuarios crear, listar, actualizar y eliminar tópicos, así como gestionar usuarios y autenticación.

## Funcionalidades de la API

La API debe permitir a los usuarios:

- Crear un nuevo tópico
- Mostrar todos los tópicos creados
- Mostrar un tópico específico
- Actualizar un tópico
- Eliminar un tópico

Adicionalmente, se implementarán funciones para:

- Crear un nuevo usuario
- Iniciar sesión (login)
- Modificar datos del usuario
- Realizar eliminación lógica de usuario

## Objetivos del Challenge

- Implementar una API REST con rutas y funcionalidades siguiendo las mejores prácticas del modelo REST.
- Realizar validaciones de acuerdo a las reglas de negocio.
- Implementar una base de datos relacional para la persistencia de la información.
- Desarrollar un servicio de autenticación y autorización para restringir el acceso a la información.

## Tecnologías Utilizadas

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Validation
- MySQL
- Flyway Migration
- GitHub
- IntelliJ IDEA
- Trello
- Postman
- Insomnia

## Tablas Utilizadas

- **Usuario**: Datos de los usuarios que interactúan en el foro.
- **Tópico**: Publicaciones en el foro con título, mensaje y fecha de publicación.
- **Respuesta**: Respuestas a los tópicos.
- **Curso**: Cursos a los que pertenecen los tópicos.
- **Perfil**: Posible tabla futura para la gestión de perfiles.

## Configuración

1. Clona o descarga este repositorio.
2. Crea una base de datos en MySQL llamada `alura_foro_api`.
3. Configura las siguientes variables de entorno:

   - `${DB_HOST}`: Por defecto `localhost`
   - `${DB_NAME}`: Nombre de la base de datos
   - `${DB_USER}`: Usuario definido en MySQL
   - `${DB_PASS}`: Contraseña definida en MySQL

## Desarrollo de cada Controlador

### Controladores de Usuarios

- **Registrar un nuevo usuario**:
  - Método: `POST`
  - URL: `localhost:8080/user`
  - Cuerpo del JSON:
    ```json
    {
      "nombre": "Nombre del Usuario",
      "email": "email@example.com",
      "password": "Contraseña123!"
    }
    ```
  - Validaciones: La contraseña debe tener entre 8 y 12 caracteres e incluir al menos un número, una letra mayúscula y una letra minúscula.

- **Login**:
  - Método: `POST`
  - URL: `localhost:8080/login`
  - Cuerpo del JSON:
    ```json
    {
      "email": "email@example.com",
      "password": "Contraseña123!"
    }
    ```
  - Genera un token de autenticación (Bearer Token) que debe usarse para solicitudes autenticadas.

- **Modificación de Contraseña**:
  - La validación de login se hace mediante el token. Permite modificar la contraseña si el token es válido.

### Controladores de Tópicos

- **Crear un tópico**:
  - Método: `POST`
  - URL: `localhost:8080/topicos`
  - Cuerpo del JSON:
    ```json
    {
      "titulo": "Título del Tópico",
      "mensaje": "Mensaje del Tópico",
      "autor": "Nombre del Autor",
      "curso": "Nombre del Curso"
    }
    ```
  - Todos los campos son obligatorios. El estado se asigna por defecto a `false`.

- **Listar todos los tópicos**:
  - Método: `GET`
  - URL: `localhost:8080/topicos`
  - Devuelve una lista de tópicos ordenada por fecha de creación en orden ascendente.

- **Mostrar un tópico específico**:
  - Método: `GET`
  - URL: `localhost:8080/topico/{id}`

- **Mostrar tópicos de un curso específico**:
  - Método: `GET`
  - URL: `localhost:8080/topico/curso/{curso_id}`

- **Mostrar tópicos de un año específico**:
  - Método: `GET`
  - URL: `localhost:8080/topico/year/{year}`

- **Actualizar un tópico**:
  - Método: `PUT`
  - URL: `localhost:8080/topico`
  - Cuerpo del JSON:
    ```json
    {
      "id": "ID del Tópico",
      "titulo": "Nuevo Título",
      "mensaje": "Nuevo Mensaje"
    }
    ```
  - Permite modificar el título y/o el mensaje del tópico.

- **Eliminar un tópico**:
  - Método: `DELETE`
  - URL: `localhost:8080/topico/{id}`

## Cómo Empezar

1. **Clonar el Repositorio**:
   ```bash
   git clone https://github.com/tu-usuario/forohub-alura-challenge-backend.git
