package ar.edu.unahur.obj2.catering;

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
