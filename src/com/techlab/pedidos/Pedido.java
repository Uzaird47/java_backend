package com.techlab.pedidos;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int SECUENCIA = 1;
    private final int id;
    private final List<LineaPedido> lineas = new ArrayList<>();

    public Pedido() { this.id = SECUENCIA++; }

    public int getId() { return id; }
    public List<LineaPedido> getLineas() { return lineas; }
    public void agregarLinea(LineaPedido lp) { lineas.add(lp); }
    public double total() { return lineas.stream().mapToDouble(LineaPedido::subtotal).sum(); }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder("Pedido #" + id + "\n");
        for (LineaPedido lp : lineas) sb.append("  - ").append(lp).append("\n");
        sb.append(String.format("TOTAL: $%.2f", total()));
        return sb.toString();
    }
}
