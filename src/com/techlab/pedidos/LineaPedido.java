package com.techlab.pedidos;

import com.techlab.productos.Producto;

public class LineaPedido {
    private final Producto producto;
    private final int cantidad;

    public LineaPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }

    // Usa polimorfismo: si el producto es Bebida/Comida, se aplica su precioFinal() override
    public double subtotal() {
        return producto.precioFinal() * cantidad;
    }

    @Override
    public String toString() {
        return String.format("%s x %d = $%.2f", producto.getNombre(), cantidad, subtotal());
    }
}



