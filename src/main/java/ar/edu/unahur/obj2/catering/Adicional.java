package ar.edu.unahur.obj2.catering;


public abstract class Adicional implements Propuesta {

    protected Propuesta propuesta;

    public Adicional(Propuesta propuesta) {
        this.propuesta = propuesta;
    }

    @Override
    public Integer cantidadDeServicios() {
        return this.propuesta.cantidadDeServicios();
    }

    @Override
    public Boolean incluyeServicio(String nombre) {
        return this.propuesta.incluyeServicio(nombre);
    }
}