package com.techlab.servicios;

import com.techlab.excepciones.StockInsuficienteException;
import com.techlab.pedidos.LineaPedido;
import com.techlab.pedidos.Pedido;
import com.techlab.productos.Producto;

import java.util.*;

public class PedidoService {
    private final ProductoService productoService;
    private final List<Pedido> pedidos = new ArrayList<>();

    public PedidoService(ProductoService productoService) {
        this.productoService = productoService;
    }

    public Pedido crearPedido(Map<Integer, Integer> items) {
        // 1) Validaciones
        for (Map.Entry<Integer,Integer> e : items.entrySet()) {
            int id = e.getKey();
            int cant = e.getValue();

            Producto p = productoService.buscarPorId(id)
                    .orElseThrow(() -> new IllegalArgumentException("Producto ID " + id + " no existe"));
            if (cant <= 0) {
                throw new IllegalArgumentException("Cantidad debe ser positiva para producto " + p.getNombre());
            }
            if (p.getStock() < cant) {
                throw new StockInsuficienteException("Stock insuficiente para " + p.getNombre());
            }
        }

        // 2) Construcción del pedido
        Pedido pedido = new Pedido();
        for (Map.Entry<Integer,Integer> e : items.entrySet()) {
            Producto p = productoService.buscarPorId(e.getKey()).get(); // seguro: ya validado
            int cant = e.getValue();
            pedido.agregarLinea(new LineaPedido(p, cant));
            // 3) Descontar stock
            productoService.actualizarStock(p.getId(), p.getStock() - cant);
        }

        pedidos.add(pedido);
        return pedido;
    }

    public List<Pedido> listarPedidos() {
        return Collections.unmodifiableList(pedidos);
    }
}
