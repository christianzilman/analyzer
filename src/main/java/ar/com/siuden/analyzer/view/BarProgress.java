package ar.com.siuden.analyzer.view;

import ar.com.siuden.analyzer.controller.GacetaController;
import ar.com.siuden.analyzer.controller.NacionController;
import java.util.ArrayList;
import java.util.Date;

public class BarProgress extends javax.swing.JFrame {

    private GacetaController gacetaController;
    private NacionController nacionController;
    private String esProceso;

    public BarProgress(GacetaController gacetaController, NacionController nacionController) {
        this.gacetaController = gacetaController;
        this.nacionController = nacionController;
    }
    // Constructor

    public BarProgress() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    public void iniciar() {
        initComponents();
        this.setLocationRelativeTo(null);
        nacionController.setProgBar(jProgressBar1);
        nacionController.setJlabelDescripcion(jlabelDescripcion);
        gacetaController.setProgBar(jProgressBar1);
        gacetaController.setJlabelDescripcion(jlabelDescripcion);
    }

    public void hilo() {

        if (esProceso.equals("NACION")) {
            nacionController.getJlabelDescripcion().setText("Esperando iniciar la operación..");
            nacionController.getProgBar().setValue(0);
            nacionController.cmd(0);
            nacionController.setPararBusqueda(true);
            nacionController.setPararProceso(false);
            nacionController.setPararExportar(false);
            nacionController.setLinkNoticias(new ArrayList<String>());
            nacionController.setFechaNoticia(new ArrayList<Date>());
            nacionController.setCantidadLink(0);
            nacionController.setPaginador(1);
            nacionController.setCantidadRegExportar(0);
        }
        if (esProceso.equals("GACETA")) {
            gacetaController.getJlabelDescripcion().setText("Esperando iniciar la operación..");
            gacetaController.getProgBar().setValue(0);
            gacetaController.cmd(0);
            gacetaController.setPararBusqueda(true);
            gacetaController.setPararProceso(false);
            gacetaController.setPararExportar(false);
            gacetaController.setLinkNoticias(new ArrayList<String>());
            gacetaController.setCantidadLink(0);
            gacetaController.setPaginador(1);
            gacetaController.setCantidadRegExportar(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnIniciar = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        btnParar = new javax.swing.JButton();
        jlabelDescripcion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        btnIniciar.setText("Iniciar");
        btnIniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnIniciarMouseClicked(evt);
            }
        });
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        btnParar.setText("Parar");
        btnParar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPararMouseClicked(evt);
            }
        });
        btnParar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPararActionPerformed(evt);
            }
        });

        jlabelDescripcion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnIniciar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 260, Short.MAX_VALUE)
                        .addComponent(btnParar))
                    .addComponent(jlabelDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlabelDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnIniciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnParar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //this.pbHnd = new pbHandler(this.getJProgressBar1());        
        System.out.println("mainWin loaded: all components good!");
    }//GEN-LAST:event_formWindowOpened
    private void btnIniciarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIniciarMouseClicked
        procesar();
    }//GEN-LAST:event_btnIniciarMouseClicked
    private void btnPararMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPararMouseClicked
        parar();
    }//GEN-LAST:event_btnPararMouseClicked

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        // TODO add your handling code here:
        procesar();
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnPararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPararActionPerformed
        // TODO add your handling code here:
        parar();
    }//GEN-LAST:event_btnPararActionPerformed

    public void procesar() {
        if (esProceso.equals("NACION")) {
            try {
                nacionController.setLife(true);
                nacionController.start(); // llama a run()
            } catch (Exception e) {
                System.out.println("- Warning: allready running..");
            }
            try {
                nacionController.cmd(1);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("\nStarting progressBar work: problem detected...\n");
            }
        }

        if (esProceso.equals("GACETA")) {
            try {
                gacetaController.setLife(true);
                gacetaController.start(); // llama a run()
            } catch (Exception e) {
                System.out.println("- Warning: allready running..");
            }
            try {
                gacetaController.cmd(1);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("\nStarting progressBar work: problem detected...\n");
            }
        }
    }

    public void parar() {
        if (esProceso.equals("NACION")) {
            try {
                nacionController.cmd(0);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("\nStarting progressBar work: problem detected...\n");
            }
        }

        if (esProceso.equals("GACETA")) {
            try {
                gacetaController.cmd(0);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("\nStarting progressBar work: problem detected...\n");
            }
        }

    }
    // Methods

    public Object getJProgressBar1() { // Getter
        return this.jProgressBar1;
    }
    
    public void setEsProceso(String esProceso){
        this.esProceso = esProceso;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnParar;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel jlabelDescripcion;
    // End of variables declaration//GEN-END:variables
    //private pbHandler pbHnd ;
}
