package ar.edu.unahur.obj2.catering;

public class MozosAdicionales extends Adicional {

    public MozosAdicionales(Propuesta propuesta) {
        super(propuesta);
    }

    @Override
    public double calcularPrecio() {
        return this.propuesta.calcularPrecio() + 18000;
    }

    @Override
    public String descripcion() {
        return this.propuesta.descripcion() + " + Mozos adicionales";
    }
}
