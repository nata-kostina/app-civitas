package civitas;

import java.util.ArrayList;

public class SorpresaPagarCobrar extends Sorpresa {

    private int valor;

    public SorpresaPagarCobrar(String texto, int valor) {
        super(texto);
        this.valor = valor;
    }

    void aplicarAJugador(int actual, ArrayList<Jugador> todos) {
        super.informe(actual, todos);
        todos.get(actual).modificarSaldo(super.getValor());
    }
}
