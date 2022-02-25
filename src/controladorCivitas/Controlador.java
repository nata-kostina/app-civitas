package controladorCivitas;

import GUI.CivitasView;
import civitas.CivitasJuego;
import civitas.GestionInmobiliaria;
import civitas.OperacionInmobiliaria;
import civitas.OperacionJuego;
import GUI.VistaTextual;

public class Controlador {

    private CivitasJuego juego;
    //private VistaTextual vista;
    private CivitasView vista;

    public Controlador(CivitasJuego juego, CivitasView vista) {
        this.juego = juego;
        this.vista = vista;
    }

    public void juega() {
        while (!juego.finalDelJuego()) {
            vista.actualiza();
            vista.pausa();
            OperacionJuego operacion = juego.siguientePaso();
            vista.mostrarSiguienteOperacion(operacion);
            if (operacion != OperacionJuego.PASAR_TURNO) {
                vista.mostrarEventos();
            }
            if (!juego.finalDelJuego()) {
                switch (operacion) {
                    case COMPRAR:
                        Respuesta respuesta = vista.comprar();
                        if (respuesta == Respuesta.SI) {
                            juego.comprar();
                            juego.siguientePasoCompletado(operacion);
                        }
                        break;

                    case GESTIONAR:
                        OperacionInmobiliaria op = vista.elegirOperacion();
                        if (op != OperacionInmobiliaria.TERMINAR) {
                            int choice = vista.elegirPropiedad();
                            GestionInmobiliaria gest = new GestionInmobiliaria(op, choice);
                            if (op == OperacionInmobiliaria.CONSTRUIR_CASA) {
                                juego.construirCasa(choice);
                            } else if (op == OperacionInmobiliaria.CONSTRUIR_HOTEL) {
                                juego.construirHotel(choice);
                            }
                        } else {
                            juego.siguientePasoCompletado(operacion);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        juego.ranking();
        vista.actualiza();
    }
}
