package civitas;

import java.util.ArrayList;
import java.util.Collections;

public class Civitas {

  /*  public static void main(String[] args) {
        // 1
        System.out.println("Exercise 1.\n");
        ArrayList<Integer> jugadores = new ArrayList<Integer>();
        int num = 0;
        final int jugadoresNum = 4;
        for (int i = 0; i < 100; i++) {
            num = Dado.getInstance().quienEmpieza(jugadoresNum);
            jugadores.add(num);
        }
        for (int i = 1; i <= jugadoresNum; i++) {
            System.out.println("Freq of " + i + ": " + Collections.frequency(jugadores, i));
        }

        // 2
        System.out.println("\nExercise 2.");
        Dado dado = Dado.getInstance();
        dado.setDebug(false);
        System.out.println("\nDebug is false:");
        System.out.println(dado.tirar());
        System.out.println(dado.tirar());
        System.out.println(dado.tirar());
        dado.setDebug(true);
        System.out.println("Debug is true:");
        System.out.println(dado.tirar());
        System.out.println(dado.tirar());
        System.out.println(dado.tirar());

        // 3
        System.out.println("\nExercise 3.\n");
        System.out.println("Debug is true:");
        System.out.println(dado.tirar());
        System.out.println("Result of getUltimoResultado(), debug = true:");
        System.out.println(dado.getUltimoResultado());
        dado.setDebug(false);
        System.out.println("Debug is false:");
        System.out.println(dado.tirar());
        System.out.println("Result of getUltimoResultado(), debug = false:");
        System.out.println(dado.getUltimoResultado());

        // 4
        System.out.println("\nExercise 4.\n");
        System.out.println(TipoCasilla.CALLE);
        System.out.println(TipoSorpresa.PAGARCOBRAR);
        System.out.println(EstadoJuego.DESPUES_AVANZAR);

        // 5
        System.out.println("\nExercise 5.\n");
        Tablero tablero = new Tablero();
        Casilla c_1 = new Casilla(TipoCasilla.CALLE, "Calle 1", 100, 200, 300);
        Casilla c_2 = new Casilla(TipoCasilla.CALLE, "Calle 2", 110, 220, 330);
        tablero.anadeCasilla(c_1);
        tablero.anadeCasilla(c_2);
        System.out.println(tablero.getCasilla(1).toString());
        System.out.println();
        System.out.println(tablero.getCasilla(2).toString());

        // 6
        System.out.println("\nExercise 6.\n");
        Casilla c_3 = new Casilla(TipoCasilla.CALLE, "Calle 3", 546, 24, 54);
        Casilla c_4 = new Casilla(TipoCasilla.CALLE, "Calle 4", 23, 233, 674);
        Casilla c_5 = new Casilla(TipoCasilla.CALLE, "Calle 5", 35, 456, 356);
        Casilla c_6 = new Casilla(TipoCasilla.CALLE, "Calle 6", 65, 235, 366);
        tablero.anadeCasilla(c_3);
        tablero.anadeCasilla(c_4);
        tablero.anadeCasilla(c_5);
        tablero.anadeCasilla(c_6);

        float minPrecio = Float.MAX_VALUE;
        float maxPrecio = 0;

        int indexMin = 0;
        int indexMax = 0;

        int calleNum = 0;
        float sum = 0;
        float precioMedio = 0;

        for (int i = 1; i < tablero.getCasillas().size(); i++) {
            if (tablero.getCasilla(i).getTipo() == TipoCasilla.CALLE) {
                Casilla c = tablero.getCasilla(i);
                if (c.getPrecioCompra() < minPrecio) {
                    minPrecio = c.getPrecioCompra();
                    indexMin = i;
                }

                if (c.getPrecioCompra() > maxPrecio) {
                    maxPrecio = c.getPrecioCompra();
                    indexMax = i;
                }
                sum += c.getPrecioCompra();
                calleNum++;
            }
            precioMedio = sum / calleNum;
        }
        System.out.println("\nLa calle más cara:\n--------------------------");
        System.out.println(tablero.getCasilla(indexMax).toString());
        System.out.println("\nLa calle más barata:\n--------------------------");
        System.out.println(tablero.getCasilla(indexMin).toString());
        System.out.println("\nPrecio medio de las calles:  " + precioMedio);

        // 7
        System.out.println("\nExercise 7.\n");
        Diario diario = Diario.getInstance();
        ArrayList<String> eventos = diario.getEventos();
        diario.ocurreEvento("Test 1");
        System.out.println(eventos);
        diario.ocurreEvento("Test 2");
        System.out.println(diario.leerEvento());

        // 8
        System.out.println("\nExercise 8.\n");
        System.out.println("Casillas size: " + tablero.getCasillas().size());
        int start = 1;
        dado.tirar();
        System.out.println("Ultimo Resultado: " + dado.getUltimoResultado());
        System.out.println("Nueva Posicion: " + start + "+" + dado.getUltimoResultado() + "=" + tablero.nuevaPosicion(start, dado.getUltimoResultado()));

        start = 7;
        dado.tirar();
        System.out.println("Ultimo Resultado: " + dado.getUltimoResultado());
        System.out.println("Nueva Posicion: " + start + "+" + dado.getUltimoResultado() + "=" + tablero.nuevaPosicion(start, dado.getUltimoResultado()));
    }*/
}
