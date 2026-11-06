package ar.edu.unahur.obj2.catering.Decorator;

import ar.edu.unahur.obj2.catering.Propuestas.Adicional;
import ar.edu.unahur.obj2.catering.Propuestas.Propuesta;

public class ServicioNocturno extends Adicional {

    public ServicioNocturno(Propuesta propuesta) {
        super(propuesta);
    }

    @Override
    public String descripcion() {
        return this.propuesta.descripcion() + " + Servicio nocturno";
    }

    @Override
    public double calcularPrecio() {
        return this.propuesta.calcularPrecio() * 1.20;
    }
}
