package civitas;

import java.util.ArrayList;

public class CasillaSorpresa extends Casilla {

    private MazoSorpresas mazo;

    public CasillaSorpresa(String nombre, MazoSorpresas mazo) {
        super(nombre);
        this.mazo = mazo;
    }

    void recibeJugador(int iactual, ArrayList<Jugador> todos) {
        super.recibeJugador(iactual, todos);
        Sorpresa sorpresa = mazo.siguiente();
        sorpresa.aplicarAJugador(iactual, todos);
    }

    public MazoSorpresas getMazoSorpresas() {
        return mazo;
    }

    public String toString() {
        String result = "";
        result = "\n\tTipo: SORPRESA\n\tNombre: " + this.getNombre();
        return result;
    }
}
