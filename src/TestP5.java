
import GUI.CapturaNombres;
import GUI.CivitasView;
import GUI.VistaDado;
import civitas.CivitasJuego;
import controladorCivitas.Controlador;
import java.util.ArrayList;


public class TestP5 {
     public static void main(String[] args) {
         CivitasView view = new CivitasView();
         VistaDado.createInstance(view);
         CapturaNombres capturaNombres = new CapturaNombres(view, true);
         ArrayList<String> nombres = new ArrayList<>();
         nombres = capturaNombres.getNombres();
         CivitasJuego juego = new CivitasJuego(nombres, true);
         Controlador controlador = new Controlador(juego, view);
         view.setCivitasJuego(juego);
         controlador.juega();

         //VistaDado.getInstance().tirar();
     }
}
