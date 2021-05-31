package com.ceiba.dominio.excepcion;

public class ExcepcionHoraReservaNoValida extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ExcepcionHoraReservaNoValida(String mensaje) {
        super(mensaje);
    }
}
