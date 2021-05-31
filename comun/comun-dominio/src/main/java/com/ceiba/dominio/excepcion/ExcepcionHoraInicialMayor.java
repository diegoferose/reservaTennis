package com.ceiba.dominio.excepcion;

public class ExcepcionHoraInicialMayor extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ExcepcionHoraInicialMayor(String mensaje) {
        super(mensaje);
    }
}
