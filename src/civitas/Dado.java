package civitas;

import java.util.Random;

public class Dado {

    static final private Dado instance = new Dado();
    private Random random = new Random();
    int ultimoResultado;
    boolean debug;

    private Dado() {
        debug = false;
    }

    static Dado getInstance() {
        return instance;
    }

    int tirar() {
        if (!debug) {
            ultimoResultado = random.nextInt(5) + 1;
            return ultimoResultado;
        } else {
            return 1;
        }
    }

    int quienEmpieza(int n) {
        return random.nextInt(n);
    }
    
    void setDebug (boolean d) {
        debug = d;
    }
    
    int getUltimoResultado() {
        return ultimoResultado;
    }
}
