package ar.edu.unahur.obj2.catering;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.catering.Excepciones.NadaParaRevertirException;
import ar.edu.unahur.obj2.catering.Excepciones.PropuestaNoIncluidaException;
import ar.edu.unahur.obj2.catering.Gestor.ActualizarPrecio;
import ar.edu.unahur.obj2.catering.Gestor.AgregarPropuesta;
import ar.edu.unahur.obj2.catering.Gestor.GestorCarta;
import ar.edu.unahur.obj2.catering.Gestor.Modificacion;
import ar.edu.unahur.obj2.catering.Gestor.QuitarPropuesta;
import ar.edu.unahur.obj2.catering.Propuestas.Menu;
import ar.edu.unahur.obj2.catering.Propuestas.Servicio;

public class GestorCartaTest {
    @Test
    public void dadoUnMenu_alAgregarUnaPropuesta_aumentaLaCantidadDeServicios() {
        Menu menu = new Menu("Menu Parrillero", 10.0);
        Servicio fotografo = new Servicio("Fotógrafo", 100000.0);

        GestorCarta gestor = new GestorCarta();
        Modificacion modificacion = new AgregarPropuesta(menu, fotografo);

        gestor.aplicar(modificacion);

        assertEquals(90000.0, menu.calcularPrecio(), 0.01);
    }

    @Test
    public void dadoUnMenu_conUnaPropuestaAgregada_alDeshacer_vuelveAlPrecioOriginal() {
        Menu menu = new Menu("Menu Parrillero", 10.0);
        Servicio fotografo = new Servicio("Fotógrafo", 100000.0);

        GestorCarta gestor = new GestorCarta();
        Modificacion modificacion = new AgregarPropuesta(menu, fotografo);

        gestor.aplicar(modificacion);
        gestor.deshacer();

        assertEquals(0.0, menu.calcularPrecio(), 0.01);
    }

    @Test
    public void dadoUnHistorialVacio_alDeshacer_lanzaNadaParaRevertirException() {
        GestorCarta gestor = new GestorCarta();

        assertThrows(NadaParaRevertirException.class, () -> gestor.deshacer());
    }

    @Test
    public void dadoUnMenu_conDosPropuestasAgregadas_alDeshacerDosVeces_eliminaEnOrdenInverso() {
        Menu menu = new Menu("Menu", 0.0);
        Servicio fotografo = new Servicio("Fotógrafo", 100000.0);
        Servicio musica = new Servicio("Música", 50000.0);

        GestorCarta gestor = new GestorCarta();

        gestor.aplicar(new AgregarPropuesta(menu, fotografo));
        gestor.aplicar(new AgregarPropuesta(menu, musica));

        gestor.deshacer();

        assertEquals(100000.0, menu.calcularPrecio(), 0.01);

        gestor.deshacer();

        assertEquals(0.0, menu.calcularPrecio(), 0.01);
    }

    @Test
    public void dadoUnServicio_alActualizarSuPrecio_cambiaSuPrecio() {
        Servicio parrillada = new Servicio("Parrillada completa", 240000.0);

        GestorCarta gestor = new GestorCarta();
        Modificacion modificacion = new ActualizarPrecio(parrillada, 260000.0);

        gestor.aplicar(modificacion);

        assertEquals(260000.0, parrillada.calcularPrecio(), 0.01);
    }

    @Test
    public void dadoUnServicio_conDosActualizaciones_alDeshacerDosVeces_recuperaLosPreciosAnteriores() {
        Servicio parrillada = new Servicio("Parrillada completa", 240000.0);

        GestorCarta gestor = new GestorCarta();

        Modificacion modificacion1 = new ActualizarPrecio(parrillada, 260000.0);
        Modificacion modificacion2 = new ActualizarPrecio(parrillada, 300000.0);

        gestor.aplicar(modificacion1);
        gestor.aplicar(modificacion2);

        assertEquals(300000.0, parrillada.calcularPrecio(), 0.01);

        gestor.deshacer();
        assertEquals(260000.0, parrillada.calcularPrecio(), 0.01);

        gestor.deshacer();
        assertEquals(240000.0, parrillada.calcularPrecio(), 0.01);
    }

    @Test
    public void dadoUnMenu_conUnaPropuesta_alQuitarla_dejaDeFormarParteDelMenu() {
        Menu menu = new Menu("Menu", 0.0);
        Servicio fotografo = new Servicio("Fotógrafo", 100000.0);

        menu.agregarPropuesta(fotografo);

        GestorCarta gestor = new GestorCarta();
        Modificacion modificacion = new QuitarPropuesta(menu, fotografo);

        gestor.aplicar(modificacion);

        assertEquals(0.0, menu.calcularPrecio(), 0.01);
    }

    @Test
    public void dadoUnLoteDeModificaciones_alAplicarlo_ejecutaTodasEnOrden() {
        Menu menu = new Menu("Menu", 0.0);

        Servicio fotografo = new Servicio("Fotógrafo", 100000.0);
        Servicio musica = new Servicio("Música", 50000.0);

        GestorCarta gestor = new GestorCarta();

        List<Modificacion> modificaciones = List.of(
            new AgregarPropuesta(menu, fotografo),
            new AgregarPropuesta(menu, musica)
        );

        gestor.aplicarLote(modificaciones);

        assertEquals(150000.0, menu.calcularPrecio(), 0.01);

        gestor.deshacer();
        assertEquals(100000.0, menu.calcularPrecio(), 0.01);

        gestor.deshacer();
        assertEquals(0.0, menu.calcularPrecio(), 0.01);
    }

    @Test
    public void dadoUnLoteConUnaModificacionInvalida_alAplicarlo_dejaElCatalogoComoEstaba() {
        Menu menu = new Menu("Menu", 0.0);

        Servicio fotografo = new Servicio("Fotógrafo", 100000.0);
        Servicio musica = new Servicio("Música", 50000.0);
        Servicio inexistente = new Servicio("Barra de tragos", 80000.0);

        menu.agregarPropuesta(fotografo);

        GestorCarta gestor = new GestorCarta();

        List<Modificacion> modificaciones = List.of(
            new AgregarPropuesta(menu, musica),
            new QuitarPropuesta(menu, inexistente)
        );

        assertThrows(PropuestaNoIncluidaException.class,() -> gestor.aplicarLote(modificaciones));

        assertEquals(100000.0, menu.calcularPrecio(), 0.01);
    }
}