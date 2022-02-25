package civitas;

import java.util.ArrayList;

public class JugadorEspeculador extends Jugador {

    protected static int FactorEspeculador = 2;
    protected static int CasasMax = FactorEspeculador * 4;
    protected static int HotelesMax = FactorEspeculador * 4;

    public JugadorEspeculador(Jugador jugador) {
        super(jugador);
        this.isEspeculador = true;
    }

    void actualizaPropiedadesPorConversion(Jugador jugador) {
        ArrayList<CasillaCalle> propiedades = jugador.getPropiedades();
        for (CasillaCalle p : propiedades) {
            if (p.tienePropietario()) {
                p.actualizaPropietarioPorConversion(jugador);
            }
        }
    }

    boolean paga(float cantidad) {
        return modificarSaldo((cantidad * (-1)) / FactorEspeculador);
    }

    boolean pagaAlquiler(float cantidad) {
        return paga(cantidad / FactorEspeculador);
    }

    private boolean puedoEdificarCasa(CasillaCalle propiedad) {
        if (super.puedoGastar(propiedad.getPrecioEdificar()) && propiedad.getNumCasas() < getCasasMax()) {
            return true;
        }
        return false;
    }

    private boolean puedoEdificarHotel(CasillaCalle propiedad) {
        if (super.puedoGastar(propiedad.getPrecioEdificar()) && propiedad.getNumHoteles() < getHotelesMax()
                && propiedad.getNumCasas() >= getCasasPorHotel()) {
            return true;
        }
        return false;
    }

    int getCasasMax() {
        return CasasMax;
    }

    private int getHotelesMax() {
        return HotelesMax;
    }

    // convert JugadorEspeculador to Jugador
    public Jugador convertir(JugadorEspeculador jug) {
        Jugador jugador = new Jugador(jug);
        actualizaPropiedadesPorConversion(jugador);
        return jugador;
    }

    public String toString() {
        String result = "Tipo: Jugador Especulador\n";
        result += super.toString();
        return result;
    }

}
