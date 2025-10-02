package com.techlab.excepciones;

public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(String msg) { super(msg); }
}
