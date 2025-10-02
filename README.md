# Java Backend – Gestión de productos y pedidos

Primer proyecto del curso **Java Backend**. App de consola para **registrar, listar, buscar, actualizar y eliminar productos**, y **crear pedidos** con cálculo de totales y validación de stock.  
Incluye **POO**, **colecciones**, **excepciones**, **paquetes**, y **menú interactivo**. También **herencia y polimorfismo** (p. ej. `Bebida` extiende `Producto` y redefine `precioFinal()`).

## Contenidos del curso que practica este proyecto
- **Tipos y variables**: `int`, `double`, `String`, `boolean`.
- **Operadores**: aritméticos, lógicos y relacionales en validaciones y cálculos.
- **Colecciones**: `ArrayList`, `Map`, iteraciones, `Optional`, `Streams` básicos.
- **POO**: clases, objetos, encapsulamiento, getters/setters.
- **Herencia y polimorfismo**: `Producto` ↔ `Bebida` (override de `precioFinal()`).
- **Excepciones**: `try/catch`, validaciones, excepción propia `StockInsuficienteException`.
- **Organización en paquetes**: `productos/`, `pedidos/`, `servicios/`, `excepciones/`, `ui/`.
- **Menú de consola**: flujo completo CRUD + creación/listado de pedidos.
- **Buenas prácticas**: separación de responsabilidades con `ProductoService` y `PedidoService`.
- **Git/GitHub**: versionado y publicación del repositorio.

## Tecnologías
- Java 17+ (probado con JDK 24)
- IntelliJ IDEA

## Ejecutar
1. Abrí el proyecto en IntelliJ.
2. Ejecutá `com.techlab.ui.MainApp` (Run ▶).
3. Usá el menú en la consola.

## Estructura
com.techlab.productos -> Producto, Bebida
com.techlab.pedidos -> Pedido, LineaPedido
com.techlab.servicios -> ProductoService, PedidoService
com.techlab.excepciones -> StockInsuficienteException
com.techlab.ui -> MainApp

## Próximos pasos
- Persistencia (archivos/DB H2/MySQL)
- Tests con JUnit
- API REST con Spring Boot
- Docker + CI/CD

## Licencia
MIT (opcional). Añadí un archivo `LICENSE` si lo deseás.

