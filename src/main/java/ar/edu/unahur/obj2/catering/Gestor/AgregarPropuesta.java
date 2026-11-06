package ar.edu.unahur.obj2.catering.Gestor;

import ar.edu.unahur.obj2.catering.Propuestas.Menu;
import ar.edu.unahur.obj2.catering.Propuestas.Propuesta;

public class AgregarPropuesta implements Modificacion {

    private Menu menu;
    private Propuesta propuesta;

    public AgregarPropuesta(Menu menu, Propuesta propuesta) {
        this.menu = menu;
        this.propuesta = propuesta;
    }

    @Override
    public void aplicar() {
        this.menu.agregarPropuesta(this.propuesta);
    }

    @Override
    public void deshacer() {
        this.menu.eliminarPropuesta(this.propuesta);
    }

}