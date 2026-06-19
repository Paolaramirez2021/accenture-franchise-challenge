# 🚀 Accenture Backend Challenge - API de Gestión de Franquicias

Esta es una API REST robusta desarrollada en **Java 17** con **Spring Boot 3** y **MongoDB**, diseñada para gestionar el modelo de negocio de franquicias, sucursales y productos. 

El diseño de la aplicación prioriza la simplicidad arquitectónica, el alto rendimiento mediante programación funcional (**Java Streams**) y la portabilidad absoluta mediante **Docker**.

---

## 🛠️ Stack Tecnológico & Decisiones de Diseño

* **Java 17 & Spring Boot 3:** Uso de las características estables más recientes para la creación de microservicios rápidos y mantenibles.
* **MongoDB:** Se seleccionó un motor NoSQL orientado a documentos debido a que el modelo de datos de Franquicias es altamente jerárquico (Franquicia ➔ Sucursales ➔ Productos). Almacenar esto como un documento anidado elimina la sobrecarga de uniones SQL (`JOINs`), simplifica las transacciones y optimiza las consultas.
* **Lombok:** Para mantener el código limpio (`Clean Code`) eliminando el ruido visual de Getters, Setters y Constructores.
* **Docker & Docker Compose:** Todo el entorno (API + Base de datos) está completamente contenedorizado para garantizar que funcione idénticamente en entornos locales, QA o Producción con un único comando.

---

## 🚀 Instrucciones de Ejecución Rápida (Docker)

No necesitas instalar Java ni MongoDB en tu máquina local. Solo asegúrate de tener **Docker** corriendo y ejecuta el siguiente comando en la raíz del proyecto:

```bash
docker-compose up --build