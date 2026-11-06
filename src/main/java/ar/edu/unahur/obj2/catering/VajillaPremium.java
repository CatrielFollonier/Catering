package ar.edu.unahur.obj2.catering;

public class VajillaPremium extends Adicional {

    public VajillaPremium(Propuesta propuesta) {
        super(propuesta);
    }

    @Override
    public String descripcion() {
        return this.propuesta.descripcion() + " + Vajilla premium";
    }

    @Override
    public double calcularPrecio() {
        return this.propuesta.calcularPrecio() + 12000;
    }
}
