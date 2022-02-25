package GUI;

import civitas.Casilla;
import civitas.CivitasJuego;
import civitas.CivitasJuego;
import civitas.Diario;
import civitas.OperacionJuego;
import controladorCivitas.Respuesta;
import civitas.OperacionInmobiliaria;
import civitas.Jugador;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class VistaTextual implements Vista {

    private static String separador = "=====================";

    private Scanner in;
    private int iPropiedad;
    private int iGestion;

    CivitasJuego juegoModel;

    public VistaTextual(CivitasJuego juegoModel) {
        in = new Scanner(System.in);
        this.juegoModel = juegoModel;
    }

    public void pausa() {
        System.out.print("\nPulsa una tecla");
        in.nextLine();
    }

    int leeEntero(int max, String msg1, String msg2) {
        Boolean ok;
        String cadena;
        int numero = -1;
        do {
            System.out.print(msg1);
            cadena = in.nextLine();
            try {
                numero = Integer.parseInt(cadena);
                ok = true;
            } catch (NumberFormatException e) { // No se ha introducido un entero
                System.out.println(msg2);
                ok = false;
            }
            if (ok && (numero < 0 || numero >= max)) {
                System.out.println(msg2);
                ok = false;
            }
        } while (!ok);

        return numero;
    }

    int menu(String titulo, ArrayList<String> lista) {
        String tab = "  ";
        int opcion;
        System.out.println(titulo);
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(tab + i + "-" + lista.get(i));
        }

        opcion = leeEntero(lista.size(),
                "\n" + tab + "Elige una opción: ",
                tab + "Valor erróneo");
        return opcion;
    }

    public void actualiza() {
        Jugador jugadorActual = juegoModel.getJugadorActual();
        System.out.println("\nJugador Actual:\n\n" + jugadorActual.toString());
        if (juegoModel.finalDelJuego()) {
            ArrayList<Jugador> jugadores = juegoModel.getJugadores();
            Collections.sort(jugadores);
            for (int i = 1; i <= jugadores.size(); i++) {
                System.out.println(i + ". " + jugadores.get(i));
            }
        }
    }

    public Respuesta comprar() {
        System.out.println("Se desea comprar la calle? SI/NO");
        String respuesta;
        respuesta = in.nextLine();
        if (respuesta.equals("si") || respuesta.equals("Si") || respuesta.equals("SI")) {
            return Respuesta.values()[1];
        } else {
            return Respuesta.values()[0];
        }
    }

    public OperacionInmobiliaria elegirOperacion() {
        ArrayList<String> lista = new ArrayList<String>();
        for (OperacionInmobiliaria op : OperacionInmobiliaria.values()) {
            lista.add(op.toString());
        }
        int opcion = menu("Elegir Operacion Inmobiliaria", lista);
        return OperacionInmobiliaria.values()[opcion];
    }

    public int elegirPropiedad() {
        ArrayList<String> lista = new ArrayList<String>();
        Jugador jugadorActual = juegoModel.getJugadorActual();
        for (Casilla c : jugadorActual.getPropiedades()) {
            lista.add(c.getNombre());
        }
        int opcion = menu("Elegir Propiedad", lista);
        return opcion;
    }

    public void mostrarSiguienteOperacion(OperacionJuego operacion) {
        System.out.println("\nSiguiente Operacion: "+operacion.toString()+"\n");
    }

    public void mostrarEventos() {
        Diario diario = Diario.getInstance();
        while (diario.eventosPendientes())
        {
            System.out.println(diario.leerEvento());
        }
    }
}
