package ar.edu.unahur.obj2.catering.Gestor;

import ar.edu.unahur.obj2.catering.Propuestas.Menu;
import ar.edu.unahur.obj2.catering.Propuestas.Propuesta;

public class QuitarPropuesta implements Modificacion {
    private Menu menu;
    private Propuesta propuesta;

    public QuitarPropuesta(Menu menu, Propuesta propuesta) {
        this.menu = menu;
        this.propuesta = propuesta;
    }

    @Override
    public void aplicar() {
        menu.eliminarPropuesta(propuesta);
    }

    @Override
    public void deshacer() {
        menu.agregarPropuesta(propuesta);
    }
}

