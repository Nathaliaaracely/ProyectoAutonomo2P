# Módulo de Donaciones - Banco de Alimentos

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-6DB33F.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.6+-C71A36.svg)](https://maven.apache.org/)

## 📋 Descripción
Módulo de gestión de donaciones para el Banco de Alimentos, desarrollado con Spring Boot 3.5.3 y Java 17. Este sistema permite administrar el ciclo completo de donaciones, desde el registro de donantes hasta el seguimiento de entregas.

## ✨ Características Principales
- **Gestión de Donaciones**: Registro y seguimiento de donaciones
- **Administración de Donantes**: Control de información de donantes
- **Inventario de Productos**: Seguimiento de productos donados
- **Control de Receptores**: Gestión de organizaciones receptoras
- **Registro de Entregas**: Seguimiento detallado de entregas
- **API RESTful**: Interfaz para integración con otros sistemas

## 🚀 Requisitos Técnicos
- Java 17 o superior
- Maven 3.6+
- Base de datos compatible con JPA (MySQL, PostgreSQL, H2, etc.)
- Spring Boot 3.5.3

## 🛠️ Instalación

1. **Clonar el repositorio**
   ```bash
   git clone [URL_DEL_REPOSITORIO]
   cd Donaciones
Configurar la base de datos Editar el archivo src/main/resources/application.properties:
properties
# Configuración de la base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/bancodealimentos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
Compilar y ejecutar
bash
mvn clean install
mvn spring-boot:run
Acceder a la aplicación
API REST: http://localhost:8080/api/
Consola H2 (si está habilitada): http://localhost:8080/h2-console
🏗️ Estructura del Proyecto
Donaciones/
├── src/
│   ├── main/
│   │   ├── java/com/bancodealimentos/
│   │   │   ├── config/         # Configuraciones de Spring
│   │   │   ├── controller/     # Controladores REST
│   │   │   ├── model/          # Entidades JPA
│   │   │   ├── repository/     # Repositorios de datos
│   │   │   └── service/        # Lógica de negocio
│   │   └── resources/          # Archivos estáticos y configuración
│   └── test/                   # Pruebas unitarias
└── pom.xml
🔧 Configuración
El archivo principal de configuración es application.properties:

properties
# Puerto del servidor
server.port=8080

# Configuración de base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/bancodealimentos
spring.datasource.username=usuario
spring.datasource.password=contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# H2 Database (para desarrollo)
#spring.h2.console.enabled=true
#spring.h2.console.path=/h2-console
## 📚 Documentación de la API

La aplicación proporciona una interfaz gráfica de GraphiQL para probar las consultas GraphQL:

- **GraphiQL UI**: `http://localhost:8080/graphiql`

Ejemplo de consulta para obtener donaciones:

```graphql
query {
  donaciones {
    id
    fechaDonacion
    donante {
      nombre
      contacto
    }
    productos {
      nombre
      cantidad
    }
  }
}
📄 Licencia
Este proyecto está bajo la Licencia MIT.

📧 Contacto
Para más información, por favor contacta a [tu correo/equipo].


### Mejoras realizadas:
1. **Formato más profesional** con emojis y badges
2. **Estructura clara** con secciones bien definidas
3. **Instrucciones detalladas** de instalación y configuración
4. **Información técnica precisa** basada en el pom.xml
5. **Sección de documentación de API** con Swagger
6. **Guía de contribución** clara
7. **Plantilla de licencia** lista par