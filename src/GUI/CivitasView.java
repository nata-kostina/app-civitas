/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import civitas.Casilla;
import civitas.CivitasJuego;
import civitas.Diario;
import civitas.Jugador;
import civitas.OperacionInmobiliaria;
import civitas.OperacionJuego;
import controladorCivitas.Respuesta;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CivitasView extends javax.swing.JFrame implements Vista {

    CivitasJuego juego;

    public CivitasView() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jugadorPanel1 = new GUI.JugadorPanel();
        jTextFieldCasillaActual = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabelSigOperacion = new javax.swing.JLabel();
        jTextFieldSigOperacion = new javax.swing.JTextField();
        jLabelRanking = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaRanking = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Civitas");

        jTextFieldCasillaActual.setEditable(false);

        jLabel2.setText("Casilla Actual:");

        jLabelSigOperacion.setText("Siguiente Operacion:");

        jTextFieldSigOperacion.setEditable(false);

        jLabelRanking.setText("Ranking:");

        jTextAreaRanking.setEditable(false);
        jTextAreaRanking.setColumns(20);
        jTextAreaRanking.setRows(5);
        jScrollPane1.setViewportView(jTextAreaRanking);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelSigOperacion)
                            .addComponent(jLabel2)
                            .addComponent(jLabelRanking))
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldCasillaActual)
                            .addComponent(jTextFieldSigOperacion)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jugadorPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 862, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(411, 411, 411)
                        .addComponent(jLabel1)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jugadorPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCasillaActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSigOperacion)
                    .addComponent(jTextFieldSigOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelRanking)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(115, 115, 115))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setCivitasJuego(CivitasJuego juego) {
        this.juego = juego;
        setVisible(true);
    }

    @Override
    public void actualiza() {
        int casillaActual = juego.getJugadorActual().getCasillaActual();
        Casilla casilla = juego.getTablero().getCasilla(casillaActual);
        jugadorPanel1.setJugador(juego.getJugadorActual());
        jTextFieldCasillaActual.setText(casilla.toString());
        jLabelRanking.setVisible(false);
        jTextAreaRanking.setVisible(false);
        if (juego.finalDelJuego()) {
            jLabelRanking.setVisible(true);
            jTextAreaRanking.setVisible(true);
            ArrayList<Jugador> jugadores = juego.ranking();
            jTextAreaRanking.setText(jugadores.toString());
        }
        repaint();
        revalidate();
    }

    @Override
    public void pausa() {
        int val = JOptionPane.showConfirmDialog(null, "¿Continuar?",
                "Siguiente paso", JOptionPane.YES_NO_OPTION);
        if (val == 1) {
            System.exit(0);
        }
    }

    @Override
    public Respuesta comprar() {
        int opcion= 1-JOptionPane.showConfirmDialog(null, "¿Quieres comprar la calle actual?", "Compra", JOptionPane.YES_NO_OPTION);
        if (opcion == 0)
            return Respuesta.NO;
        else
            return Respuesta.SI;
    }

    @Override
    public OperacionInmobiliaria elegirOperacion() {
        GestionarDialog gestionarDialog = new GestionarDialog(this, true);
        int gestionElegida = gestionarDialog.getGestionElegida();
        return OperacionInmobiliaria.values()[gestionElegida];
    }

    @Override
    public int elegirPropiedad() {
        PropiedadDialog propiedadDialog = new PropiedadDialog(this,true, juego.getJugadorActual());
        int propiedadElegida = propiedadDialog.getPropiedadElegida();
        return propiedadElegida;
    }

    @Override
    public void mostrarSiguienteOperacion(OperacionJuego operacion) {
        jTextFieldSigOperacion.setText(operacion.toString());
        if (operacion == OperacionJuego.AVANZAR) {
            VistaDado.createInstance(this);
        }
        repaint();
    }

    @Override
    public void mostrarEventos() {
        if (!Diario.getInstance().getEventos().isEmpty()) {
            DiarioDialog diarioD = new DiarioDialog(this, true);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelRanking;
    private javax.swing.JLabel jLabelSigOperacion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaRanking;
    private javax.swing.JTextField jTextFieldCasillaActual;
    private javax.swing.JTextField jTextFieldSigOperacion;
    private GUI.JugadorPanel jugadorPanel1;
    // End of variables declaration//GEN-END:variables
}
