package ar.edu.unahur.obj2.catering.Gestor;

import ar.edu.unahur.obj2.catering.Propuestas.Servicio;

public class ActualizarPrecio implements Modificacion {
    private Servicio servicio;
    private double precioAnterior;
    private double precioNuevo;

    public ActualizarPrecio(Servicio servicio, double precioNuevo) {
        this.servicio = servicio;
        this.precioNuevo = precioNuevo;
    }

    @Override
    public void aplicar() {
        this.precioAnterior = servicio.getPrecio();
        servicio.setPrecio(precioNuevo);
    }

    @Override
    public void deshacer() {
        servicio.setPrecio(precioAnterior);
    }
}
