package com.techlab.ui;

import com.techlab.excepciones.StockInsuficienteException;
import com.techlab.pedidos.Pedido;
import com.techlab.servicios.PedidoService;
import com.techlab.servicios.ProductoService;
import com.techlab.productos.Bebida;

import java.util.*;

public class MainApp {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ProductoService productoService = new ProductoService();
        PedidoService pedidoService = new PedidoService(productoService);

        // Datos de ejemplo
        productoService.agregar("Café Premium", 4500.0, 50);
        productoService.agregar("Té Verde", 3200.0, 75);
        productoService.agregar("Galletas Choco", 2100.0, 80);
        productoService.agregar("Yerba Mate Selección", 5200.0, 25);
        productoService.agregar("Azúcar Orgánica 1kg", 3100.0, 40);
        productoService.agregar("Leche Almendras 1L", 4200.0, 18);
        productoService.agregar("Mermelada Frutilla 450g", 2900.0, 35);
        productoService.agregar("Granola Mix 500g", 6100.0, 12);
        // Herencia + polimorfismo
        productoService.agregarDirecto(new Bebida("Agua Mineral", 1200.0, 50, 1.5));

        int opcion;
        do {
            menu();
            opcion = leerEntero("Elija una opción: ");
            try {
                switch (opcion) {
                    case 1 -> agregarProducto(productoService);
                    case 2 -> listarProductos(productoService);
                    case 3 -> buscarActualizar(productoService);
                    case 4 -> eliminarProducto(productoService);
                    case 5 -> crearPedido(productoService, pedidoService);
                    case 6 -> listarPedidos(pedidoService);
                    case 7 -> System.out.println("Saliendo... ¡Gracias!");
                    default -> System.out.println("Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: se esperaba un número.");
            } catch (StockInsuficienteException e) {
                System.out.println("Error de stock: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        } while (opcion != 7);
    }

    private static void menu() {
        System.out.println("=================================== SISTEMA DE GESTIÓN - TECHLAB ==================================");
        System.out.println("1) Agregar producto");
        System.out.println("2) Listar productos");
        System.out.println("3) Buscar/Actualizar producto");
        System.out.println("4) Eliminar producto");
        System.out.println("5) Crear un pedido");
        System.out.println("6) Listar pedidos");
        System.out.println("7) Salir");
    }

    // --- Acciones ---
    private static void agregarProducto(ProductoService ps) {
        System.out.println("=== Agregar Producto ===");
        String nombre = leerLinea("Nombre: ");
        double precio = leerDouble("Precio: ");
        int stock = leerEntero("Stock: ");
        var p = ps.agregar(nombre, precio, stock);
        System.out.println("Agregado: " + p);
    }
    private static void listarProductos(ProductoService ps) {
        System.out.println("=== Listado de Productos ===");
        var lista = ps.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay productos cargados.");
            return;
        }
        lista.forEach(System.out::println);

        // --- métricas de inventario ---
        System.out.println("----------------------------------------");
        System.out.println("Unidades totales en stock: " + ps.totalUnidadesEnStock());
        System.out.printf("Valor inventario: $%.2f%n", ps.totalValorizadoInventario());
    }




    private static void buscarActualizar(ProductoService ps) {
        System.out.println("=== Buscar/Actualizar ===");
        String tipo = leerLinea("Buscar por (id/nombre): ").trim().toLowerCase();

        Optional<com.techlab.productos.Producto> opt =
                tipo.equals("id") ? ps.buscarPorId(leerEntero("ID: "))
                        : tipo.equals("nombre") ? ps.buscarPorNombre(leerLinea("Nombre: "))
                        : Optional.empty();

        opt.ifPresentOrElse(p -> {
            System.out.println("Encontrado: " + p);
            String acc = leerLinea("¿Actualizar precio (p) o stock (s) o nada (n)? ").trim().toLowerCase();
            switch (acc) {
                case "p" -> {
                    double nuevo = leerDouble("Nuevo precio: ");
                    if (ps.actualizarPrecio(p.getId(), nuevo)) System.out.println("Precio actualizado.");
                    else System.out.println("No se pudo actualizar.");
                }
                case "s" -> {
                    int ns = leerEntero("Nuevo stock (>=0): ");
                    if (ns < 0 || !ps.actualizarStock(p.getId(), ns)) System.out.println("Valor inválido.");
                    else System.out.println("Stock actualizado.");
                }
                default -> System.out.println("Sin cambios.");
            }
        }, () -> System.out.println("No encontrado."));
    }

    private static void eliminarProducto(ProductoService ps) {
        System.out.println("=== Eliminar Producto ===");
        int id = leerEntero("ID: ");
        String conf = leerLinea("Confirmar (s/n): ").trim().toLowerCase();
        if ("s".equals(conf)) {
            boolean ok = ps.eliminar(id); // <- asegurate de tener este método en ProductoService
            System.out.println(ok ? "Eliminado." : "No existe ese ID.");
        } else {
            System.out.println("Cancelado.");
        }
    }

    private static void crearPedido(ProductoService ps, PedidoService pedSrv) {
        System.out.println("=== Crear Pedido ===");
        // Mostrar catálogo para elegir
        var productos = ps.listar();
        if (productos.isEmpty()) { System.out.println("No hay productos cargados."); return; }
        productos.forEach(System.out::println);

        Map<Integer,Integer> items = new LinkedHashMap<>();
        while (true) {
            int id = leerEntero("ID de producto (0 para terminar): ");
            if (id == 0) break;
            int cant = leerEntero("Cantidad: ");
            items.put(id, items.getOrDefault(id, 0) + cant);
        }
        if (items.isEmpty()) { System.out.println("Sin productos seleccionados."); return; }

        Pedido pedido = pedSrv.crearPedido(items);
        System.out.println("Pedido creado:\n" + pedido);
    }

    private static void listarPedidos(PedidoService pedSrv) {
        System.out.println("=== Pedidos ===");
        var lista = pedSrv.listarPedidos();
        System.out.println("Total de pedidos: " + lista.size());
        if (lista.isEmpty()) System.out.println("Sin pedidos.");
        else lista.forEach(p -> System.out.println(p + "\n"));
    }

    // --- Lectura segura ---
    private static int leerEntero(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine();
            try { return Integer.parseInt(s.trim()); }
            catch (NumberFormatException e) { System.out.println("Ingrese un entero válido."); }
        }
    }
    private static double leerDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine();
            try { return Double.parseDouble(s.trim()); }
            catch (NumberFormatException e) { System.out.println("Ingrese un decimal válido (use punto)."); }
        }
    }
    private static String leerLinea(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }
}
