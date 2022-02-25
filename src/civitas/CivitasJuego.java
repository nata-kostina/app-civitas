package civitas;

import GUI.VistaDado;
import java.util.ArrayList;
import java.util.Collections;

public class CivitasJuego {

    private int indiceJugadorActual;

    private MazoSorpresas mazo;
    private Tablero tablero;
    private ArrayList<Jugador> jugadores;
    private EstadoJuego estado;
    private GestorEstados gestor;

    public CivitasJuego(ArrayList<String> nombres, boolean debug) {
        jugadores = new ArrayList<Jugador>();
        for (String nombre : nombres) {
            Jugador jugador = new Jugador(nombre);
            jugadores.add(jugador);
        }
        gestor = new GestorEstados();
        estado = gestor.estadoInicial();
        VistaDado.getInstance().setDebug(debug);
        indiceJugadorActual = VistaDado.getInstance().quienEmpieza(jugadores.size());
        mazo = new MazoSorpresas(debug);
        tablero = new Tablero();
        inicializaTablero(mazo);
        inicializaMazoSorpresa();
    }

    public void avanzaJugador() {
        Jugador jugadorActual = getJugadorActual();
        int posicionActual = jugadorActual.getCasillaActual();
        int tirada = VistaDado.getInstance().tirar();
        int posicionNueva = tablero.nuevaPosicion(posicionActual, tirada);
        Casilla casilla = tablero.getCasilla(posicionNueva);
        contabilizarPasosPorSalida();
        jugadorActual.moverACasilla(posicionNueva);
        casilla.recibeJugador(indiceJugadorActual, jugadores);
    }

    public boolean comprar() {
        Jugador jugadorActual = getJugadorActual();
        int numCasillaActual = jugadorActual.getCasillaActual();
        Casilla casilla = tablero.getCasilla(numCasillaActual);
        return jugadorActual.comprar((CasillaCalle)casilla);
    }

    public boolean construirCasa(int ip) {
        return jugadores.get(indiceJugadorActual).construirCasa(ip);
    }

    public boolean construirHotel(int ip) {
        return jugadores.get(indiceJugadorActual).construirHotel(ip);
    }

    private void contabilizarPasosPorSalida() {
        if (tablero.computarPasoPorSalida()) {
            jugadores.get(indiceJugadorActual).pasaPorSalida();
        };
    }

    public boolean finalDelJuego() {
        for (Jugador jugador : jugadores) {
            if (jugador.enBancarrota()) {
                return true;
            }
        }
        return false;
    }

    public int getIndiceJugadorActual() {
        return indiceJugadorActual;
    }

    public Jugador getJugadorActual() {
        return jugadores.get(indiceJugadorActual);
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public Tablero getTablero() {
        return tablero;
    }

    private void inicializaMazoSorpresa() {
        SorpresaPagarCobrar s_1 = new SorpresaPagarCobrar("Sorpresa: +100", 100);
        SorpresaPagarCobrar s_2 = new SorpresaPagarCobrar("Sorpresa: -200", -200);
        SorpresaPagarCobrar s_3 = new SorpresaPagarCobrar("Sorpresa: +100", 300);
        SorpresaPorCasaHotel s_4 = new SorpresaPorCasaHotel( "Sorpresa: -400", 400);
        SorpresaPorCasaHotel s_5 = new SorpresaPorCasaHotel("Sorpresa: +500", 500);

        mazo.alMazo(s_1);
        mazo.alMazo(s_2);
        mazo.alMazo(s_3);
        mazo.alMazo(s_4);
        mazo.alMazo(s_5);

    }

    private void inicializaTablero(MazoSorpresas mazo) {
        tablero = new Tablero();
        Casilla c_1 = new CasillaCalle("Calle 1", 100, 200, 300);
        Casilla c_2 = new CasillaCalle("Calle 2", 110, 220, 330);
        Casilla c_3 = new CasillaCalle("Calle 3", 546, 24, 54);
        Casilla c_4 = new CasillaCalle("Calle 4", 23, 233, 674);
        Casilla c_5 = new CasillaCalle("Calle 5", 35, 456, 356);
        Casilla c_6 = new CasillaCalle("Calle 6", 65, 235, 366);
        Casilla d_1 = new Casilla("Descanso 1");
        Casilla d_2 = new Casilla("Descanso 2");
        Casilla d_3 = new Casilla("Descanso 3");
        Casilla d_4 = new Casilla("Descanso 4");
        Casilla d_5 = new Casilla("Descanso 5");
        Casilla d_6 = new Casilla("Descanso 6");
        Casilla s_1 = new CasillaSorpresa("SORPRESA 1", mazo);
        Casilla s_2 = new CasillaSorpresa("SORPRESA 2", mazo);
        Casilla s_3 = new CasillaSorpresa("SORPRESA 3", mazo);
        Casilla s_4 = new CasillaSorpresa("SORPRESA 4", mazo);
        Casilla s_5 = new CasillaSorpresa("SORPRESA 5", mazo);
        Casilla s_6 = new CasillaSorpresa("SORPRESA 6", mazo);

        tablero.anadeCasilla(c_1);
        tablero.anadeCasilla(s_1);
        tablero.anadeCasilla(d_1);
        tablero.anadeCasilla(d_2);
        tablero.anadeCasilla(s_1);
        tablero.anadeCasilla(c_2);
        tablero.anadeCasilla(c_3);
        tablero.anadeCasilla(s_2);
        tablero.anadeCasilla(c_4);
        tablero.anadeCasilla(d_3);
        tablero.anadeCasilla(s_3);
        tablero.anadeCasilla(c_5);
        tablero.anadeCasilla(d_4);
        tablero.anadeCasilla(s_4);
        tablero.anadeCasilla(c_6);
        tablero.anadeCasilla(s_5);
        tablero.anadeCasilla(d_5);
        tablero.anadeCasilla(d_6);
        tablero.anadeCasilla(s_6);
    }

    private void pasarTurno() {
        indiceJugadorActual = (indiceJugadorActual + 1) % jugadores.size();
    }

    public ArrayList<Jugador> ranking() {
        Collections.sort(jugadores);
        return jugadores;
    }

    public OperacionJuego siguientePaso() {
        Jugador jugadorActual = getJugadorActual();
        OperacionJuego operacion = gestor.siguienteOperacion(jugadorActual, estado);
        if (operacion == OperacionJuego.PASAR_TURNO) {
            pasarTurno();
            siguientePasoCompletado(operacion);
        } else if (operacion == OperacionJuego.AVANZAR) {
            avanzaJugador();
            siguientePasoCompletado(operacion);
        }
        return operacion;
    }

    public void siguientePasoCompletado(OperacionJuego operacion) {
        estado = gestor.siguienteEstado(jugadores.get(indiceJugadorActual), estado, operacion);
    }
}
