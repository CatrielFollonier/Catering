package ar.edu.unahur.obj2.catering.Propuestas;

import ar.edu.unahur.obj2.catering.Excepciones.PrecioNegativoException;

public class Servicio implements Propuesta {

    private String nombre;
    private Double precio;

    public Servicio(String nombre, Double precio) {
        this.nombre = nombre;
        if (precio < 0) {
            throw new PrecioNegativoException("El precio no puede ser negativo");
        }
        this.precio = precio;
    }

    public double getPrecio() {
        return this.precio;
    }

    @Override
    public String descripcion() {
        return this.nombre;
    }

    @Override
    public double calcularPrecio() {
        return this.precio;
    }

    @Override
    public Integer cantidadDeServicios() {
        return 1;
    }

    @Override
    public Boolean incluyeServicio(String nombre) {
        return this.nombre.equals(nombre);
    }

    public void setPrecio(double precioNuevo) {
        if (precioNuevo < 0) {
            throw new PrecioNegativoException("El precio no puede ser negativo");
        }
        this.precio = precioNuevo;
    }
}