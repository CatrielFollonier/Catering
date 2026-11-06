package ar.edu.unahur.obj2.catering;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.catering.Excepciones.DescuentoInvalidoException;
import ar.edu.unahur.obj2.catering.Propuestas.Menu;
import ar.edu.unahur.obj2.catering.Propuestas.Servicio;

public class MenuTest {
    @Test 
    public void dadoUnMenu_alConsultarSuNombre_retornaSuNombreYDescuento() {
        
        Menu menu = new Menu("Menu Parrillero", 10.0);

        String nombre = menu.descripcion();
        Double descuento = menu.getDescuento();

        assertEquals("Menu Parrillero", nombre);
            assertEquals(10.0, descuento, 0.001);
    }

    @Test
    public void dadoUnMenu_alAgregarUnServicio_seAgregaCorrectamente() {
        Menu menu = new Menu("Menu Parrillero", 10.0);
        Servicio servicio = new Servicio("Asado", 500.0);

        menu.agregarPropuesta(servicio);

        assertEquals(1, menu.cantidadDeServicios());
    }

    @Test
    public void dadoUnMenu_alCalcularSuPrecio_retornaElPrecioConDescuento() {
        Menu menu = new Menu("Menu Parrillero", 10.0);
        Servicio servicio1 = new Servicio("Asado", 500.0);
        Servicio servicio2 = new Servicio("Ensalada", 200.0);

        menu.agregarPropuesta(servicio1);
        menu.agregarPropuesta(servicio2);

        Double precioTotal = menu.calcularPrecio();

        assertEquals(630.0, precioTotal, 0.001);
    }

    @Test
    public void dadoUnMenu_conDosServicios_alConsultarCantidadDeServicios_retornaLaCantidadCorrecta() {
        Menu menu = new Menu("Menu Parrillero", 10.0);
        Servicio servicio1 = new Servicio("Asado", 500.0);
        Servicio servicio2 = new Servicio("Ensalada", 200.0);

        menu.agregarPropuesta(servicio1);
        menu.agregarPropuesta(servicio2);

        Integer cantidadServicios = menu.cantidadDeServicios();

        assertEquals(2, cantidadServicios);
    }

    @Test
    public void dadoUnMenu_queContieneUnServicio_alConsultarSiLoIncluye_retornaTrue() {

        Menu menu = new Menu("Menu Parrillero", 10.0);
        Servicio servicio = new Servicio("Asado", 500.0);

        menu.agregarPropuesta(servicio);
        Boolean incluye = menu.incluyeServicio("Asado");

        assertTrue(incluye);
    }

    @Test
    public void dadoUnMenu_queNoContieneUnServicio_alConsultarSiLoIncluye_retornaFalse() {
        Menu menu = new Menu("Menu Parrillero", 10.0);
        Servicio servicio = new Servicio("Asado", 500.0);
  
        menu.agregarPropuesta(servicio);
        Boolean incluye = menu.incluyeServicio("Pizza");

        assertFalse(incluye);
    }

    @Test 
    public void dadoUnMenu_alCrearUnMenu_conDescuentoNegativo_retornaExcepcion() {
        assertThrows(DescuentoInvalidoException.class, () -> {
            new Menu("Menu con descuento negativo", -10.0);
        });
    }

    @Test
    public void dadoUnMenuQueContieneOtroMenu_alCalcularCantidadDeServicios_retornaLaCantidadTotal() {
        Menu menuPrincipal = new Menu("Evento Completo", 20.0);
        Menu menuInterno = new Menu("Menu Parrillero", 10.0);

        menuInterno.agregarPropuesta(new Servicio("Asado", 500.0));
        menuInterno.agregarPropuesta(new Servicio("Ensalada", 200.0));

        menuPrincipal.agregarPropuesta(menuInterno);

        assertEquals(2, menuPrincipal.cantidadDeServicios());
    }
}