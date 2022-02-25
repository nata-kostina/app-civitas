package civitas;

import java.util.ArrayList;
import java.util.Collections;

public class MazoSorpresas {

    private boolean barajada;
    private int usadas;
    private boolean debug;

    ArrayList<Sorpresa> sorpresas;

    void init() {
        sorpresas = new ArrayList<>();
        barajada = false;
        usadas = 0;
    }

    MazoSorpresas() {
        init();
        debug = false;
    }

    MazoSorpresas(boolean d) {
        debug = d;
        init();
        if (debug) {
            Diario.getInstance().ocurreEvento("El modo debug está activado.");
        }
    }

    void alMazo(Sorpresa s) {
        if (!barajada) {
            sorpresas.add(s);
        }
    }

    Sorpresa siguiente() {
        if ((!barajada)
                || (usadas == sorpresas.size())) {
            if (!debug) {
                Collections.shuffle(sorpresas);
            }
                usadas = 0;
                barajada = true;            
        }
        usadas++;
        Sorpresa temp = sorpresas.get(0);
        sorpresas.remove(0);
        sorpresas.add(temp);
        return temp;
    }
}
