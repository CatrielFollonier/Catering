package ar.edu.unahur.obj2.catering;

public class Contrato {
    private String nombreCliente;
    private double importe;
    private String descripcion;

    public Contrato(String nombreCliente, Propuesta propuesta) {
        this.nombreCliente = nombreCliente;
        this.importe = propuesta.calcularPrecio();
        this.descripcion = propuesta.descripcion();
    }

    public double getImporte() {
        return this.importe;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public String getNombreCliente() {
        return this.nombreCliente;
    }

}
