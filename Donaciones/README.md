# Módulo de Donaciones - Banco de Alimentos

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-6DB33F.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.6+-C71A36.svg)](https://maven.apache.org/)

## 📋 Tabla de Contenidos
- [Descripción](#-descripción)
- [Características Principales](#-características-principales)
- [Requisitos Técnicos](#-requisitos-técnicos)
- [Instalación](#-instalación)
- [Configuración](#-configuración)
- [Uso](#-uso)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Documentación de la API](#-documentación-de-la-api)
- [Contribución](#-contribución)
- [Licencia](#-licencia)
- [Contacto](#-contacto)

## 📋 Descripción
Módulo de gestión de donaciones para el Banco de Alimentos, desarrollado con Spring Boot 3.5.3 y Java 17. Este sistema permite administrar el ciclo completo de donaciones, desde el registro de donantes hasta el seguimiento de entregas.

## ✨ Características Principales
- **Gestión de Donaciones**: Registro y seguimiento de donaciones
- **Administración de Donantes**: Control de información de donantes
- **Inventario de Productos**: Seguimiento de productos donados
- **Control de Receptores**: Gestión de organizaciones receptoras
- **Registro de Entregas**: Seguimiento detallado de entregas
- **API GraphQL**: Interfaz moderna para consultas flexibles

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
Configurar la base de datos
Crea una base de datos MySQL llamada bancodealimentos
Edita el archivo src/main/resources/application.properties:
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
Interfaz GraphiQL: http://localhost:8080/graphiql
Consola H2 (si está habilitada): http://localhost:8080/h2-console
🔧 Configuración
El archivo principal de configuración es src/main/resources/application.properties:

properties
# Puerto del servidor
server.port=8080

# Configuración de base de datos (MySQL)
spring.datasource.url=jdbc:mysql://localhost:3306/bancodealimentos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# H2 Database (para desarrollo)
#spring.datasource.url=jdbc:h2:mem:testdb
#spring.h2.console.enabled=true
#spring.h2.console.path=/h2-console
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
📚 Documentación de la API
La aplicación proporciona una interfaz gráfica de GraphiQL para probar las consultas GraphQL:

GraphiQL UI: http://localhost:8080/graphiql
Ejemplos de consultas GraphQL
Obtener todas las donaciones:
graphql
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
Crear una nueva donación:
graphql
mutation {
  crearDonacion(donacion: {
    fechaDonacion: "2025-07-27",
    donanteId: 1,
    productos: [
      { productoId: 1, cantidad: 5 },
      { productoId: 2, cantidad: 3 }
    ]
  }) {
    id
    mensaje
  }
}
🤝 Contribución
Haz un fork del proyecto
Crea una rama para tu feature (git checkout -b feature/AmazingFeature)
Haz commit de tus cambios (git commit -m 'Add some AmazingFeature')
Haz push a la rama (git push origin feature/AmazingFeature)
Abre un Pull Request
📄 Licencia
Este proyecto está bajo la Licencia MIT. Ver el archivo LICENSE para más detalles.

📧 Contacto
Para más información, por favor contacta a [tu correo/equipo].


### Mejoras realizadas:

1. **Tabla de contenidos** para facilitar la navegación.
2. **Instrucciones más claras** en la sección de instalación.
3. **Sección de uso** con ejemplos prácticos de consultas GraphQL.
4. **Formato consistente** en todo el documento.
5. **Enlaces clickeables** para acceder a las interfaces.
6. **Mejor organización** de las secciones.
7. **Ejemplos completos** de mutacione