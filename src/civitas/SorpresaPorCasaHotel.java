package civitas;

import java.util.ArrayList;

public class SorpresaPorCasaHotel extends Sorpresa {

    private int valor;

    public SorpresaPorCasaHotel(String texto, int valor) {
        super(texto);
        this.valor = valor;
    }

    void aplicarAJugador(int actual, ArrayList<Jugador> todos) {
        informe(actual, todos);
        todos.get(actual).modificarSaldo(super.getValor() * todos.get(actual).cantidadCasasHoteles());
    }
}
