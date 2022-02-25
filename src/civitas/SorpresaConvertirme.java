package civitas;

import java.util.ArrayList;

public class SorpresaConvertirme extends Sorpresa {

    public SorpresaConvertirme(String texto) {
        super(texto);
    }

    void aplicarAJugador(int actual, ArrayList<Jugador> todos) {
        super.informe(actual, todos);
        Jugador jugador = todos.get(actual);
        JugadorEspeculador jugadorEspeculador = jugador.convertir(jugador);
        todos.set(actual, jugadorEspeculador);
    }
}
