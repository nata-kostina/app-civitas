package civitas;

import java.util.ArrayList;

public class Tablero {

    private ArrayList<Casilla> casillas;
    private boolean porSalida;

    public Tablero() {
        casillas = new ArrayList<Casilla>();       
        casillas.add(new Casilla("Salida"));
        porSalida = false;
    }

    private boolean correcto(int numCasilla) {
        return (numCasilla >= 0) && (numCasilla < casillas.size());
    }

    public boolean computarPasoPorSalida() {
        boolean temp = porSalida;
        porSalida = false;
        return temp;
    }

    void anadeCasilla(Casilla casilla) {
        casillas.add(casilla);
    }

    public Casilla getCasilla(int numCasilla) {
        if (correcto(numCasilla)) {
            return casillas.get(numCasilla);
        } else {
            return null;
        }
    }

    int nuevaPosicion(int actual, int tirada) {
        if ((actual + tirada) / casillas.size() > 0) {
            porSalida = true;
        }
        return (actual + tirada) % casillas.size();
    }
    
    public ArrayList<Casilla> getCasillas() {
        return casillas;
    }
}
