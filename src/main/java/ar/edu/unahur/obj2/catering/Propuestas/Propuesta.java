package ar.edu.unahur.obj2.catering.Propuestas;

public interface Propuesta {
    public String descripcion();
    public double calcularPrecio();
    public Integer cantidadDeServicios();
    public Boolean incluyeServicio(String nombre);
}
