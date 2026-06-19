# API de Gestión de Franquicias — Prueba Técnica Backend
> **Postulante:** Paola Ramírez 

---

## 📑 Resumen de la Solución

Esta es una API REST desarrollada con **Java 17**, **Spring Boot 3** y **MongoDB**, diseñada para modelar de forma eficiente el negocio de franquicias, sucursales y control de inventario de productos. 

Para resolver el reto, aproveché la naturaleza jerárquica de los datos mediante documentos anidados en MongoDB y optimicé el procesamiento de la lógica de negocio usando **programación funcional con Java Streams**. Toda la aplicación está lista para desplegarse localmente con un solo comando gracias a **Docker**.

---

## 🛠️ Decisiones de Diseño y Stack Tecnológico

* **MongoDB (NoSQL):** Elegí este motor porque la estructura del negocio es puramente jerárquica (`Franquicia` ➔ `Sucursales` ➔ `Productos`). Guardar la franquicia como un documento raíz con subdocumentos anidados elimina la necesidad de hacer uniones complejas (`JOINs`) que ralentizarían una base de datos relacional tradicional, acelerando las consultas de inventario.
* **Programación Funcional (Java Streams):** Toda la lógica de filtros, modificaciones de stock y búsquedas avanzadas se maneja con flujos funcionales. Esto hace que el código sea más limpio, fácil de leer y eficiente en el uso de memoria.
* **Lombok:** Utilizado para reducir el código repetitivo (*boilerplate*) de los modelos y DTOs, manteniendo los archivos limpios y enfocados en la lógica.
* **Docker & Docker Compose:** Containerización completa de la API y la base de datos para asegurar que el entorno funcione exactamente igual en cualquier máquina sin necesidad de instalaciones previas.

---

## 🚀 Instrucciones de Ejecución Local (Docker)

No es necesario que tengas instalado Java ni MongoDB de forma local. El único requisito es tener **Docker Desktop** corriendo en tu máquina.

1. Abre la terminal en la raíz del proyecto (donde está el archivo `docker-compose.yml`).
2. Ejecuta el siguiente comando para compilar la app y levantar los contenedores:

```bash
docker-compose up --build

La API estará disponible y lista para recibir peticiones en: http://localhost:8080

🛣️ Endpoints de la API (Cumplimiento de Criterios)
A continuación se detallan los endpoints implementados para cubrir los requerimientos base y los puntos extra (Plus):

Gestión Base
POST /api/franquicias -> Crea una nueva franquicia en el sistema.

POST /api/franquicias/{franquiciaId}/sucursales -> Agrega una sucursal a una franquicia específica.

POST /api/sucursales/{sucursalId}/productos -> Añade un nuevo producto con su stock inicial a una sucursal.

DELETE /api/sucursales/{sucursalId}/productos/{productoId} -> Elimina un producto de una sucursal.

PUT /api/productos/{productoId}/stock -> Modifica/actualiza el stock disponible de un producto.

📊 Consulta Requerida (Máximo Stock por Sucursal)
GET /api/franquicias/{franquiciaId}/productos/max-stock

Descripción: Este endpoint cumple con el criterio de aceptación #7. Utiliza Java Streams para recorrer las sucursales de una franquicia puntual, identificar el producto con el mayor stock en cada una de ellas y retornar un listado detallado indicando a qué sucursal pertenece cada producto.

Endpoints Adicionales (Puntos Plus Implementados)

PUT /api/franquicias/{id}/nombre -> Permite actualizar el nombre de una franquicia.

PUT /api/sucursales/{id}/nombre -> Permite actualizar el nombre de una sucursal.

PUT /api/productos/{id}/nombre -> Permite actualizar el nombre de un producto.

📦 Control de Versiones (Estrategia Git)
El repositorio se gestionó mediante commits incrementales para reflejar de forma transparente el proceso de desarrollo y la evolución del código:

chore: inicialización del proyecto con Spring Boot y dependencias de Mongo

feat: creación de modelos de datos (Franquicia, Sucursal, Producto) y repositorios

feat: implementación de endpoints de creación y eliminación (CRUD base)

feat: desarrollo de la lógica de negocio con Streams para obtener el producto con mayor stock

feat: agregados endpoints adicionales para la actualización de nombres (Plus)

chore: configuración de Dockerfile, Docker Compose y documentación final