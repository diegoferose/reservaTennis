package com.ceiba.dominio.excepcion;

public class ExcepcionReservaActiva extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ExcepcionReservaActiva(String mensaje) {
        super(mensaje);
    }
}
