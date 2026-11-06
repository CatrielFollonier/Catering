package ar.edu.unahur.obj2.catering;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.catering.Decorator.MozosAdicionales;
import ar.edu.unahur.obj2.catering.Decorator.ServicioNocturno;
import ar.edu.unahur.obj2.catering.Decorator.VajillaPremium;
import ar.edu.unahur.obj2.catering.Propuestas.Adicional;
import ar.edu.unahur.obj2.catering.Propuestas.Menu;
import ar.edu.unahur.obj2.catering.Propuestas.Servicio;

public class AdicionalTest {
    @Test
    public void dadoUnMenuParrillero_conMozosAdicionales_retornaPrecioConAdicional() {
        Menu menu = new Menu("Menu Parrillero", 10.0);
        Servicio servicio1 = new Servicio("Asado", 500.0);
        Servicio servicio2 = new Servicio("Ensalada", 200.0);

        menu.agregarPropuesta(servicio1);
        menu.agregarPropuesta(servicio2);

        Adicional mozosAdicionales = new MozosAdicionales(menu);

        double precioTotalConAdicional = mozosAdicionales.calcularPrecio();

            assertEquals(18630.0, precioTotalConAdicional, 0.01);
    }

    @Test
    public void dadoUnMenu_conMozosAdicionales_retornaDescripcionConAdicional() {
        Menu menu = new Menu("Menu Parrillero", 10.0);

        Adicional mozosAdicionales = new MozosAdicionales(menu);

        assertEquals( "Menu Parrillero + Mozos adicionales", mozosAdicionales.descripcion());
    }

    @Test
        public void dadoUnBrindis_conVajillaPremium_retornaPrecioConAdicional() {
        Servicio brindis = new Servicio("Brindis de bienvenida", 50000.0);

        Adicional vajilla = new VajillaPremium(brindis);

        assertEquals(62000.0, vajilla.calcularPrecio(), 0.01);
    }

    @Test
    public void dadoUnMenu_conServicioNocturno_retornaPrecioConRecargo() {
        Servicio servicio = new Servicio("Mesa dulce", 60000.0);

        Adicional nocturno = new ServicioNocturno(servicio);

        assertEquals(72000.0, nocturno.calcularPrecio(), 0.01);
    }
}   
