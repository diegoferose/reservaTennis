package com.ceiba.dominio.excepcion;

public class ExcepcionReservaNoEncontrada extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ExcepcionReservaNoEncontrada(String mensaje) {
        super(mensaje);
    }
}
