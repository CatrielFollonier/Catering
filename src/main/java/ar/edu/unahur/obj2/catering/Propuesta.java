package ar.edu.unahur.obj2.catering;

public interface Propuesta {
    public String descripcion();
    public double calcularPrecio();
    public Integer cantidadDeServicios();
    public Boolean incluyeServicio(String nombre);
}
