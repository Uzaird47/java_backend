package com.techlab.servicios;

import com.techlab.productos.Producto;

import java.util.*;

public class ProductoService {
    private final List<Producto> productos = new ArrayList<>();

    public Producto agregar(String nombre, double precio, int stock) {
        Producto p = new Producto(nombre, precio, stock);
        productos.add(p);
        return p;
    }

    // Acepta subclases (Bebida, Comida, etc.)
    public Producto agregarDirecto(Producto p) {
        productos.add(p);
        return p;
    }

    public List<Producto> listar() {
        return Collections.unmodifiableList(productos);
    }

    public Optional<Producto> buscarPorId(int id) {
        return productos.stream().filter(p -> p.getId() == id).findFirst();
    }

    public Optional<Producto> buscarPorNombre(String nombre) {
        return productos.stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
    }

    public boolean actualizarPrecio(int id, double nuevoPrecio) {
        return buscarPorId(id)
                .map(p -> { p.setPrecio(nuevoPrecio); return true; })
                .orElse(false);
    }

    public boolean actualizarStock(int id, int nuevoStock) {
        if (nuevoStock < 0) return false;
        return buscarPorId(id)
                .map(p -> { p.setStock(nuevoStock); return true; })
                .orElse(false);
    }

    // *** FALTABA: eliminar por ID ***
    public boolean eliminar(int id) {
        return productos.removeIf(p -> p.getId() == id);
    }

    // (opcionales para resúmenes)
    public int totalUnidadesEnStock() {
        return productos.stream().mapToInt(Producto::getStock).sum();
    }
    public double totalValorizadoInventario() {
        return productos.stream().mapToDouble(p -> p.getPrecio() * p.getStock()).sum();
    }
}
