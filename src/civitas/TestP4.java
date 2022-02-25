package civitas;

public class TestP4 {

    public static void main(String[] args) {
        Jugador jugador = new Jugador("Anna");  // create new jugador
        CasillaCalle calle = new CasillaCalle("Calle 1", 100, 200, 300); // create casilla
        jugador.puedeComprarCasilla();
        jugador.comprar(calle); // add calle to player's propiedades
        System.out.println(jugador);    // output information about jugador
        JugadorEspeculador jugadorEspeculador = jugador.convertir(jugador);     // convert jugador to jugador especulador
        System.out.println(jugadorEspeculador);    // output information about jugador especulador
        // convert Jugador Especulador back to Jugador
        Jugador jugadorBack = jugadorEspeculador.convertir(jugadorEspeculador);     // convert jugador to jugador especulador
        System.out.println(jugadorBack);    // output information about jugador
    }
}
