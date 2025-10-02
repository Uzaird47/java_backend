package com.techlab.productos;

public class Bebida extends Producto {
    private double litros;

    public Bebida(String nombre, double precio, int stock, double litros) {
        super(nombre, precio, stock);
        this.litros = litros;
    }

    public double getLitros() { return litros; }
    public void setLitros(double litros) { this.litros = litros; }

    // Polimorfismo: las bebidas tienen precio final distinto (ej: +21%)
    @Override
    public double precioFinal() {
        return getPrecio() * 1.21;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | %.2f L (Bebida)", litros);
    }
}
