package ar.edu.unahur.obj2.catering.Gestor;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import ar.edu.unahur.obj2.catering.Excepciones.NadaParaRevertirException;

public class GestorCarta {
    private Stack<Modificacion> historial = new Stack<>();

    public void aplicar(Modificacion modificacion) {
        modificacion.aplicar();
        historial.push(modificacion);
    }
    
    public void deshacer() {
        if (historial.isEmpty()) {
            throw new NadaParaRevertirException("No hay modificaciones para revertir.");
        }
        Modificacion ultimaModificacion = this.historial.pop();
        ultimaModificacion.deshacer();
    }

    public void aplicarLote(List<Modificacion> modificaciones) {
        List<Modificacion> aplicadas = new ArrayList<>();

        try {
            for (Modificacion modificacion : modificaciones) {
                this.aplicar(modificacion);
                aplicadas.add(modificacion);
            }
        } catch (RuntimeException excepcion) {
            for (int i = aplicadas.size() - 1; i >= 0; i--) {
                Modificacion modificacion = aplicadas.get(i);
                modificacion.deshacer();
                this.historial.pop();
            }

            throw excepcion;
        }
    }
}
