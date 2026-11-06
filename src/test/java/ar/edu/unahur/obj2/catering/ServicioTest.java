package ar.edu.unahur.obj2.catering;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.catering.Excepciones.PrecioNegativoException;
import ar.edu.unahur.obj2.catering.Propuestas.Servicio;

public class ServicioTest {
    @Test 
    public void dadoUnServicio_alConsultarSuNombre_retornaSuDescripcionYSuPrecio() {
        
        Servicio servicio = new Servicio("Mesa dulce", 60000.0);

        String nombre = servicio.descripcion();
        double precio = servicio.getPrecio();

        assertEquals("Mesa dulce", nombre);
        assertEquals(60000.0, precio, 0.01);
    }

    @Test 
    public void dadoUnServicio_alConsultarCantidadDeServicios_retornaUno() {
        
        Servicio servicio = new Servicio("Mesa dulce", 60000.0);

        Integer cantidadServicios = servicio.cantidadDeServicios();

        assertEquals(1, cantidadServicios);
    }

    @Test
    public void dadoUnPrecioNegativo_alCrearUnServicio_retornaExcepcion() {
        assertThrows(PrecioNegativoException.class, () -> {
            new Servicio("Servicio con precio negativo", -100.0);
        });
    }
}
