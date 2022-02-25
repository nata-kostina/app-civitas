package civitas;

import java.util.ArrayList;

abstract class Sorpresa {

    private String texto;
    private int valor;

    Sorpresa(String texto) {
        this.texto = texto;
       
    }

    abstract void aplicarAJugador(int actual, ArrayList<Jugador> todos);
    
    void informe(int actual, ArrayList<Jugador> todos) {
        Diario diario = Diario.getInstance();
        diario.ocurreEvento("\nUna sorpresa está aplicando a jugador " + todos.get(actual).getNombre());
    }
    
    public int getValor(){
        return valor;
    }
    
    public String toString() {
        return "Texto: " + this.texto;
    }
}
