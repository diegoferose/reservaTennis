package com.ceiba.dominio.excepcion;

public class ExcepcionHoraDiferenteDIa extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ExcepcionHoraDiferenteDIa(String mensaje) {
        super(mensaje);
    }
}
