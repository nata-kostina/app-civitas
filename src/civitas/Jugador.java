package civitas;

import java.util.ArrayList;

public class Jugador implements Comparable<Jugador> {

    protected static int CasasMax = 4;
    protected static int CasasPorHotel = 4;

    private int casillaActual;
    protected static int HotelesMax = 4;
    private String nombre;
    protected static float PasoPorSalida = 1000;
    private boolean puedeComprar;
    private float saldo;
    private static float SaldoInicial = 7500;
    protected boolean isEspeculador;

    private ArrayList<CasillaCalle> propiedades = null;

    Jugador(String nombre) {
        this.nombre = nombre;
        casillaActual = 0;
        saldo = SaldoInicial;
        puedeComprar = false;
        this.propiedades = new ArrayList<CasillaCalle>();
        isEspeculador = false;
    }

    protected Jugador(Jugador otro) {
        this.nombre = otro.nombre;
        this.casillaActual = otro.casillaActual;
        this.puedeComprar = otro.puedeComprar;
        this.saldo = otro.saldo;
        this.propiedades = new ArrayList<CasillaCalle>();
        this.propiedades = otro.getPropiedades();
        this.isEspeculador = otro.isEspeculador();
    }
// convert Jugador to JugadorEspeculador

    public JugadorEspeculador convertir(Jugador jugador) {
        JugadorEspeculador jugadorEspeculador = new JugadorEspeculador(jugador);
        jugadorEspeculador.actualizaPropiedadesPorConversion(jugadorEspeculador);
        return jugadorEspeculador;
    }

    int cantidadCasasHoteles() {
        int sum = 0;
        for (CasillaCalle casilla : propiedades) {
            sum += casilla.getNumCasas() + casilla.getNumHoteles();
        }
        return sum;
    }

    public int compareTo(Jugador otro) {
        return Float.compare(this.saldo, otro.getSaldo());
    }

    boolean comprar(CasillaCalle titulo) {
        boolean result = false;
        if (puedeComprar) {
            float precio = titulo.getPrecioCompra();
            if (puedoGastar(precio)) {
                result = titulo.comprar(this);
                propiedades.add(titulo);
                Diario.getInstance().ocurreEvento("El jugador " + nombre + " compra la propiedad " + titulo.getNombre());
                puedeComprar = false;
            } else {
                Diario.getInstance().ocurreEvento("El jugador " + nombre + " no tiene saldo para comprar la propiedad " + titulo.getNombre());
            }
        }
        return result;
    }

    boolean construirCasa(int ip) {
        boolean result = false;
        if (existeLaPropiedad(ip)) {
            CasillaCalle propiedad = propiedades.get(ip);
            if (puedoEdificarCasa(propiedad)) {
                result = propiedad.construirCasa(this);
                Diario diario = Diario.getInstance();
                diario.ocurreEvento("El jugador " + nombre + " construye casa en la propiedad " + propiedad.getNombre());
            };
        };
        return result;
    }

    boolean construirHotel(int ip) {
        boolean result = false;
        if (existeLaPropiedad(ip)) {
            CasillaCalle propiedad = propiedades.get(ip);
            if (puedoEdificarHotel(propiedad)) {
                result = propiedad.construirHotel(this);
                propiedad.derruirCasas(CasasPorHotel, this);
                Diario.getInstance().ocurreEvento("El jugador " + nombre + " construye hotel en la propiedad " + propiedad.getNombre());
            } else {
                Diario.getInstance().ocurreEvento("El jugador " + nombre + " no puede construir hotel en la propiedad " + propiedad.getNombre());
            }
        }
        return result;
    }

    boolean enBancarrota() {
        return saldo < 0;
    }

    private boolean existeLaPropiedad(int ip) {
        return ip >= 0 && ip < propiedades.size();
    }

    int getCasasMax() {
        return CasasMax;
    }

    int getCasasPorHotel() {
        return CasasPorHotel;
    }

    public int getCasillaActual() {
        return casillaActual;
    }

    private int getHotelesMax() {
        return HotelesMax;
    }

    public String getNombre() {
        return nombre;
    }

    private float getPremioPasoSalida() {
        return PasoPorSalida;
    }

    public ArrayList<CasillaCalle> getPropiedades() {
        return propiedades;
    }

    public boolean getPuedeComprar() {
        return puedeComprar;
    }

    public float getSaldo() {
        return saldo;
    }
    
    public boolean isEspeculador() {
        return isEspeculador;
    }

    boolean modificarSaldo(float cantidad) {
        saldo += cantidad;
        Diario diario = Diario.getInstance();
        diario.ocurreEvento("\nSaldo está modificado.");
        return true;
    }

    boolean moverACasilla(int numCasilla) {
        casillaActual = numCasilla;
        puedeComprar = false;
        Diario.getInstance().ocurreEvento("\nJugador " + nombre + " está en la casilla " + numCasilla + ".");
        return true;
    }

    boolean paga(float cantidad) {
        return modificarSaldo(cantidad * (-1));
    }

    boolean pagaAlquiler(float cantidad) {
        return paga(cantidad);
    }

    boolean pasaPorSalida() {
        recibe(PasoPorSalida);
        Diario diario = Diario.getInstance();
        diario.ocurreEvento("Jugador " + nombre + " se recibe dinero por pasar por la salida.");
        return true;
    }

    boolean puedeComprarCasilla() {
        puedeComprar = true;
        return puedeComprar;
    }

    private boolean puedoEdificarCasa(CasillaCalle propiedad) {
        if (puedoGastar(propiedad.getPrecioEdificar()) && propiedad.getNumCasas() < getCasasMax()) {
            return true;
        }
        return false;
    }

    private boolean puedoEdificarHotel(CasillaCalle propiedad) {
        if (puedoGastar(propiedad.getPrecioEdificar()) && propiedad.getNumHoteles() < getHotelesMax()
                && propiedad.getNumCasas() >= getCasasPorHotel()) {
            return true;
        }
        return false;
    }

    boolean puedoGastar(float precio) {
        return saldo >= precio;
    }

    boolean recibe(float cantidad) {
        return modificarSaldo(cantidad);
    }

    boolean tieneAlgoQueGestionar() {
        return propiedades.size() > 0;
    }

    public String toString() {
        String result = "Nombre: " + nombre + "\nSaldo: " + saldo;
        if (propiedades.size() != 0) {
            result += "\nPropiedades:\n";
            for (Casilla c : propiedades) {
                result += c + "\n\t---------------------------------";
            }
        } else {
            result += "\nEl jugador no tiene propiedades.";
        }
        result += "\nLa casilla actual: " + casillaActual;
        return result;
    }
}
