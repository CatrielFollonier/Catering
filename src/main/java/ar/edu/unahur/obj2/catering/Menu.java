package ar.edu.unahur.obj2.catering;

import java.util.List;

import ar.edu.unahur.obj2.catering.Excepciones.DescuentoInvalidoException;

import java.util.ArrayList;

public class Menu implements Propuesta {
    private String nombre;
    private Double descuento;
    private List<Propuesta> propuestas;

    public Menu(String nombre, Double descuento) {
        this.nombre = nombre;
        if (descuento < 0 || descuento > 100) {
            throw new DescuentoInvalidoException("El descuento debe estar entre 0 y 100");
        }
        this.descuento = descuento;
        this.propuestas = new ArrayList<>();
    }

    public Double getDescuento() {
        return this.descuento;
    }

    public void agregarPropuesta(Propuesta propuesta) {
        this.propuestas.add(propuesta);
    }

    public double calcularPrecio() {
        Double precioTotal = 0.0;

        for (Propuesta propuesta : this.propuestas) {
            precioTotal += propuesta.calcularPrecio();
        }

        return precioTotal - (precioTotal * this.descuento / 100);
    }

    public Integer cantidadDeServicios() {
        Integer cantidad = 0;

        for (Propuesta propuesta : this.propuestas) {
            cantidad += propuesta.cantidadDeServicios();
        }

        return cantidad;
    }

    public Boolean incluyeServicio(String nombre) {
        for (Propuesta propuesta : this.propuestas) {
            if (propuesta.incluyeServicio(nombre)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String descripcion() {
        return this.nombre;
    }
}
