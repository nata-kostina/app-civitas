package civitas;

import java.util.ArrayList;

public class CasillaCalle extends Casilla {

    static float FACTORALQUILERCALLE = 1.0f;
    static float FACTORALQUILERCASA = 1.0f;
    static float FACTORALQUILERHOTEL = 4.0f;

    private float precioCompra, precioEdificar, precioBaseAlquiler;
    private int numCasas, numHoteles;

    private Jugador propietario;

    public CasillaCalle(String titulo, float precioCompra, float precioEdificar, float precioBaseAlquiler) {
        super(titulo);
        this.precioCompra = precioCompra;
        this.precioEdificar = precioEdificar;
        this.precioBaseAlquiler = precioBaseAlquiler;
    }

    public int cantidadCasaHoteles() {
        return numCasas + numHoteles;
    }

    boolean comprar(Jugador jugador) {
        propietario = jugador;
        return propietario.paga(precioCompra);
    }

    boolean construirCasa(Jugador jugador) {
        jugador.paga(precioEdificar);
        numCasas++;
        return true;
    }

    boolean construirHotel(Jugador jugador) {
        jugador.paga(precioEdificar);
        numHoteles++;
        return true;
    }

    boolean derruirCasas(int numero, Jugador jugador) {
        if (esEsteElPropietario(jugador) && numCasas >= numero) {
            numCasas -= numero;
            return true;
        }
        return false;
    }

    public boolean esEsteElPropietario(Jugador jugador) {
        if (propietario == jugador) {
            return true;
        } else {
            return false;
        }
    }

    public int getNumCasas() {
        return numCasas;
    }

    public int getNumHoteles() {
        return numHoteles;
    }

    public float getPrecioAlquilerCompleto() {
        float PrecioAlquilerCompleto = precioBaseAlquiler * (FACTORALQUILERCALLE + numCasas * FACTORALQUILERCASA + numHoteles * FACTORALQUILERHOTEL);
        return PrecioAlquilerCompleto;
    }

    public float getPrecioCompra() {
        return precioCompra;
    }

    public float getPrecioEdificar() {
        return precioEdificar;
    }

    void init() {
        precioCompra = 0;
        precioEdificar = 0;
        precioBaseAlquiler = 0;
        numCasas = 0;
        numHoteles = 0;
    }

    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }

    public void setPrecioEdificar(float precioEdificar) {
        this.precioEdificar = precioEdificar;
    }

    public float getPrecioBaseAlquiler() {
        return precioBaseAlquiler;
    }

    public void setPrecioBaseAlquiler(float precioBaseAlquiler) {
        this.precioBaseAlquiler = precioBaseAlquiler;
    }

    public void setNumCasas(int numCasas) {
        this.numCasas = numCasas;
    }

    public void setNumHoteles(int numHoteles) {
        this.numHoteles = numHoteles;
    }
        
    public void actualizaPropietarioPorConversion(Jugador propietario) {
     this.propietario = propietario;
    }

    public String toString() {
        String result = "";
        result = "\tTipo: CALLE\n\tNombre: " + this.getNombre()
                + "\n\tPrecio base alquiler: " + this.getPrecioBaseAlquiler()
                + "\n\tPrecio Compra: " + this.getPrecioCompra()
                + ".\n\tCasas: " + this.getNumCasas() + ".\n\tHoteles: " + this.getNumHoteles() + "."
                + "\n\tPrecio Alquiler Completo: " + this.getPrecioAlquilerCompleto();
        if (tienePropietario()) {
            result = result + ".\n\tPropietario: " + propietario.getNombre();
        } else {
            result = result + ".\n\tPropietario: None";
        }
        return result;
    }

    public void tramitarAlquiler(Jugador jugador) {
        if (this.tienePropietario() && !this.esEsteElPropietario(jugador)) {
            jugador.pagaAlquiler(getPrecioAlquilerCompleto());
            propietario.recibe(getPrecioAlquilerCompleto());
            Diario.getInstance().ocurreEvento("\nEl jugador " + jugador.getNombre() + " paga precio alquiler a propietario " + propietario.getNombre() + ".");
        }
    }

    void recibeJugador(int iactual, ArrayList<Jugador> todos) {
        super.recibeJugador(iactual, todos);
        Jugador jugador = todos.get(iactual);
        if (!tienePropietario()) {
            jugador.puedeComprarCasilla();
        } else {
            tramitarAlquiler(jugador);
        }
    }

    public boolean tienePropietario() {
        if (propietario != null) {
            return true;
        } else {
            return false;
        }
    }
}
