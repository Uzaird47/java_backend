package com.techlab.productos;

public class Producto {
    private static int SECUENCIA = 1;
    private final int id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(String nombre, double precio, int stock) {
        this.id = SECUENCIA++;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public void setNombre(String n) { this.nombre = n; }
    public void setPrecio(double p) { this.precio = p; }
    public void setStock(int s) { this.stock = s; }

    // <<--- agregado
    public double precioFinal() {
        return getPrecio();
    }

    @Override public String toString() {
        return String.format("#%d | %s | $%.2f | stock: %d", id, nombre, precio, stock);
    }
}

