package ar.edu.unahur.obj2.catering;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class ContratoTest {
    private Menu crearMenuParrillero() {
    Menu menu = new Menu("Menu Parrillero", 10.0);

    menu.agregarPropuesta(new Servicio("Mesa de entradas", 80000.0));
    menu.agregarPropuesta(new Servicio("Parrillada completa", 240000.0));
    menu.agregarPropuesta(new Servicio("Mesa dulce", 60000.0));

    return menu;
}

    @Test
    public void dadoUnContratoDeLucia_sobreMenuParrilleroConMozos_retorna360000() {
        Menu menu = crearMenuParrillero();

        Propuesta propuesta = new MozosAdicionales(menu);

        Contrato contrato = new Contrato("Lucía", propuesta);

        assertEquals(360000.0, contrato.getImporte(), 0.01);
    }

    @Test
    public void dadoUnContratoDeLucia_sobreMenuParrilleroConMozos_retornaDescripcion() {
        Menu menu = crearMenuParrillero();
        Propuesta propuesta = new MozosAdicionales(menu);

        Contrato contrato = new Contrato("Lucía", propuesta);

        assertEquals("Menu Parrillero + Mozos adicionales", contrato.getDescripcion());
    }

}
