# API Gateway - Módulo de Donaciones

Este módulo implementa un API Gateway para el sistema de donaciones del Banco de Alimentos. Proporciona un punto de entrada único para todos los microservicios, manejo de autenticación, balanceo de carga y más.

## Características Principales

- **Enrutamiento dinámico** de solicitudes a los microservicios correspondientes
- **Autenticación JWT** para proteger los endpoints
- **Balanceo de carga** entre instancias de servicios
- **Circuit Breaker** para manejo de fallos
- **Rate Limiting** para prevenir abuso
- **Manejo centralizado de errores**
- **CORS** configurado globalmente

## Requisitos Previos

- Java 17 o superior
- Maven 3.6 o superior
- PostgreSQL 13 o superior
- Spring Boot 3.5.3

## Configuración

### Variables de Entorno

Crea un archivo `.env` en la raíz del proyecto con las siguientes variables:

```env
# Base de datos
SPRING_DATASOURCE_URL=jdbc:postgresql://tu_servidor:5432/tu_basededatos
SPRING_DATASOURCE_USERNAME=tu_usuario
SPRING_DATASOURCE_PASSWORD=tu_contraseña

# JWT
JWT_SECRET=tu_clave_secreta_segura
JWT_EXPIRATION_MS=86400000
```

### Propiedades de la Aplicación

Las configuraciones principales se encuentran en `src/main/resources/application.properties`:

- Puerto del servidor: `server.port=8080`
- Configuración de base de datos
- Configuración de JWT
- Timeouts y reintentos
- Configuración de Circuit Breaker

## Endpoints

### Autenticación (Público)

- `POST /api/auth/token`
  - Descripción: Obtener token JWT
  - Body: `{"username": "admin", "password": "admin123"}`
  - Respuesta exitosa (200): `{"token": "jwt.token.here"}`

### Donaciones (Protegido)

- `GET /api/donaciones`
  - Descripción: Obtener lista de donaciones
  - Headers: `Authorization: Bearer <token>`

- `POST /api/donaciones`
  - Descripción: Crear una nueva donación
  - Headers: `Authorization: Bearer <token>`
  - Body: `{"fecha": "2023-01-01", "donanteId": 1, ...}`

### Donantes (Protegido)

- `GET /api/donantes`
  - Descripción: Obtener lista de donantes
  - Headers: `Authorization: Bearer <token>`

### Productos (Protegido)

- `GET /api/productos`
  - Descripción: Obtener lista de productos
  - Headers: `Authorization: Bearer <token>`

## Configuración Avanzada

### Circuit Breaker

El API Gateway incluye Circuit Breaker configurado para manejar fallos en los servicios. La configuración predeterminada es:

- Umbral de fallos: 50%
- Número mínimo de llamadas: 5
- Tiempo de espera en estado abierto: 5 segundos

### Rate Limiting

Se ha implementado rate limiting basado en IP con las siguientes configuraciones:

- Límite de solicitudes: 100 por minuto
- Tiempo de espera: 1 minuto

## Despliegue

### Construir la Aplicación

```bash
mvn clean package
```

### Ejecutar la Aplicación

```bash
java -jar target/Donaciones-0.0.1-SNAPSHOT.jar
```

### Docker

Se puede crear una imagen de Docker con el siguiente comando:

```bash
docker build -t banco-alimentos/gateway .
```

Y ejecutarla con:

```bash
docker run -p 8080:8080 --env-file .env banco-alimentos/gateway
```

## Pruebas

Para ejecutar las pruebas automatizadas:

```bash
mvn test
```

Las pruebas incluyen:
- Pruebas de endpoints públicos
- Pruebas de autenticación
- Pruebas de rate limiting
- Pruebas de manejo de errores

## Seguridad

- Todos los endpoints (excepto `/api/auth/**`) requieren autenticación JWT
- Los tokens JWT tienen una caducidad configurable (por defecto 24 horas)
- Se recomienda usar HTTPS en producción
- Las contraseñas nunca se almacenan en texto plano

## Monitoreo y Métricas

El API Gateway expone endpoints de Actuator para monitoreo:

- `/actuator/health`: Estado de salud de la aplicación
- `/actuator/metrics`: Métricas de la aplicación
- `/actuator/circuitbreakerevents`: Eventos de Circuit Breaker

## Solución de Problemas

### Errores Comunes

- **401 Unauthorized**: Token no proporcionado o inválido
- **429 Too Many Requests**: Se ha excedido el límite de solicitudes
- **503 Service Unavailable**: Servicio temporalmente no disponible (posiblemente en estado de Circuit Breaker abierto)

### Registros

Los registros detallados están disponibles en `logs/application.log`. Se puede ajustar el nivel de registro en `application.properties`.

## Contribución

1. Haz un fork del repositorio
2. Crea una rama para tu característica (`git checkout -b feature/nueva-caracteristica`)
3. Haz commit de tus cambios (`git commit -am 'Añade alguna característica'`)
4. Haz push a la rama (`git push origin feature/nueva-caracteristica`)
5. Abre un Pull Request

## Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.
