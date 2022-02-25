package civitas;

import java.util.ArrayList;

public class Casilla {

    private String nombre;

    Casilla(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    void informe(int iactual, ArrayList<Jugador> todos) {
        Diario diario = Diario.getInstance();
        Jugador jugador = todos.get(iactual);
        diario.ocurreEvento("\n" + jugador.getNombre() + " ha caido en la casilla:\n" + this.toString());
    }

    void recibeJugador(int iactual, ArrayList<Jugador> todos) {
        informe(iactual, todos);
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public String toString() {
        String result = "";
        result = "\n\tTipo: DESCANSO\n\tNombre: " + this.getNombre();
        return result;
    }
}
