# 🍽️ Sistema de Gestión de Donaciones - Banco de Alimentos

## 📋 Descripción
Sistema backend para la gestión de donaciones de alimentos, desarrollado con Spring Boot 3.5.3 y GraphQL. Permite administrar donantes, receptores, donaciones y seguimiento de entregas.

## 🚀 Características Principales
- API GraphQL para consultas flexibles
- Autenticación básica
- Integración con PostgreSQL
- Documentación interactiva con GraphiQL
- Pool de conexiones configurable

## 🛠️ Requisitos Previos
- Java 17 o superior
- Maven 3.6+
- PostgreSQL 12+
- Git

## 🏗️ Estructura del Proyecto
Donaciones/ ├── src/ │ ├── main/ │ │ ├── java/com/bancodealimentos/ │ │ │ ├── config/ # Configuraciones (seguridad, GraphQL, etc.) │ │ │ ├── controller/ # Controladores REST │ │ │ ├── graphql/ # Resolvers y lógica GraphQL │ │ │ ├── model/ # Entidades JPA │ │ │ ├── repository/ # Repositorios de datos │ │ │ └── service/ # Lógica de negocio │ │ └── resources/ │ │ ├── graphql/ # Esquemas GraphQL │ │ └── application.properties │ └── test/ # Pruebas └── pom.xml


## ⚙️ Configuración Inicial

### 1. Clonar el repositorio
```bash
git clone [URL_DEL_REPOSITORIO]
cd Donaciones
2. Configuración de la Base de Datos
Crear una base de datos PostgreSQL
Configurar las credenciales en 
src/main/resources/application.properties
:
properties
spring.datasource.url=jdbc:postgresql://localhost:5432/tu_base_de_datos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
3. Variables de Entorno Recomendadas
Para mayor seguridad, se recomienda usar variables de entorno:

bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/tu_base_de_datos
export SPRING_DATASOURCE_USERNAME=tu_usuario
export SPRING_DATASOURCE_PASSWORD=tu_contraseña
🚀 Ejecución del Proyecto
Modo Desarrollo
bash
mvn spring-boot:run
Construir y Ejecutar
bash
mvn clean package
java -jar target/Donaciones-0.0.1-SNAPSHOT.jar
🌐 Acceso a la Aplicación
Aplicación: http://localhost:8080
GraphiQL: http://localhost:8080/graphiql
Usuario: Nathalia
Contraseña: aracely123321
📚 Documentación de la API
Autenticación
La API usa autenticación HTTP básica. Incluye las credenciales en cada solicitud:

Usuario: Nathalia
Contraseña: aracely123321
Ejemplos de Consultas GraphQL
Obtener todas las donaciones:

graphql
query {
  donaciones {
    id
    tipoDonacion
    descripcion
    donante { nombre }
  }
}
Crear una donación:

graphql
mutation {
  crearDonacionConInput(input: {
    fechaDonacion: "2025-07-27",
    tipoDonacion: "Alimentos",
    descripcion: "Ejemplo de donación",
    cantidad: 5.0,
    unidadMedida: "kg",
    estado: PENDIENTE
  }) {
    id
    tipoDonacion
  }
}
🔒 Seguridad
La aplicación incluye autenticación básica
Las contraseñas nunca se almacenan en texto plano
Se recomienda usar HTTPS en producción
🛠️ Configuración Avanzada
Pool de Conexiones
properties
# Tamaño del pool de conexiones
spring.datasource.hikari.maximum-pool-size=5
spring.datasource.hikari.minimum-idle=2

# Timeouts
spring.datasource.hikari.connection-timeout=30000
spring.datasource.hikari.idle-timeout=600000
JPA/Hibernate
properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
🤝 Contribución
Haz un fork del proyecto
Crea una rama: git checkout -b feature/nueva-funcionalidad
Haz commit: git commit -m 'Añadir nueva funcionalidad'
Haz push: git push origin feature/nueva-funcionalidad
Abre un Pull Request