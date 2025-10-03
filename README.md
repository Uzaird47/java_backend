# Backend Java – Gestión de productos y pedidos

Primer proyecto del curso **Java Backend**. Aplicación de consola para **registrar, listar, buscar, actualizar y eliminar productos**, y **crear pedidos** con cálculo de totales y validación de stock.  
Incluye **POO**, **colecciones**, **excepciones**, **paquetes** y **menú interactivo**. También **herencia y polimorfismo** (p. ej., `Bebida` extiende `Producto` y redefine `precioFinal()`).

---

## Contenidos del curso practicados

- **Tipos y variables:** `int`, `double`, `String`, `boolean`.
- **Operadores:** aritméticos, lógicos y relacionales (validaciones y cálculos).
- **Colecciones:** `ArrayList`, `Map`, iteraciones, `Optional`, `Stream` básicos.
- **POO:** clases, objetos, encapsulamiento, getters/setters.
- **Herencia y polimorfismo:** `Producto` ↔ `Bebida` (override de `precioFinal()`).
- **Excepciones:** `try/catch` en entradas; excepción propia `StockInsuficienteException`.
- **Organización en paquetes:** `productos/`, `pedidos/`, `servicios/`, `excepciones/`, `ui/`.
- **Menú de consola:** flujo CRUD completo + creación/listado de pedidos.
- **Buenas prácticas:** separación por capas con `ProductoService` y `PedidoService`.
- **Git/GitHub:** versionado y publicación del repositorio.

---

## Tecnologías

- Java 17+ (probado con JDK 24)
- IntelliJ IDEA (recomendado)

---

## Cómo ejecutar

### IntelliJ IDEA (recomendado)
1. Abrí el proyecto.
2. Ejecutá la clase `com.techlab.ui.MainApp` (Run ▶).
3. Usá el menú en la consola.

### Consola (alternativa)
```bash
# desde la raíz del proyecto
javac -d out src/com/techlab/**/**/*.java
java -cp out com.techlab.ui.MainApp
src/
└─ com/techlab
   ├─ productos/
   │  ├─ Producto.java
   │  └─ Bebida.java          # hereda de Producto y redefine precioFinal()
   ├─ pedidos/
   │  ├─ LineaPedido.java
   │  └─ Pedido.java
   ├─ servicios/
   │  ├─ ProductoService.java
   │  └─ PedidoService.java
   ├─ excepciones/
   │  └─ StockInsuficienteException.java
   └─ ui/
      └─ MainApp.java         # menú interactivo

---

## 📸 Capturas

> Algunas pantallas sugeridas para mostrar el flujo completo:
> - Listado de productos (con métricas)
> - Agregar producto
> - Buscar/Actualizar producto
> - Crear pedido (con bebida y producto base)
> - Listar pedidos

### Ejemplo de inserción de imágenes en Markdown

<!-- Subí tus imágenes a la carpeta docs/ o al propio repo y referencialas así: -->
<p align="center">
  <img src="docs/listado_productos.png" alt="Listado de productos" width="700">
</p>

<p align="center">
  <img src="docs/crear_pedido.png" alt="Crear pedido" width="700">
</p>

> Para capturas desde IntelliJ/terminal:
> - Windows: `Win + Shift + S`
> - macOS: `Shift + Cmd + 4`
> - Linux: herramienta de capturas de tu distro
![Java](https://img.shields.io/badge/Java-17%2B-red)
![Build](https://img.shields.io/badge/Build-CLI-blue)
![License: MIT](https://img.shields.io/badge/License-MIT-green)



