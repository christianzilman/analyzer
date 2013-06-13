/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.siuden.analyzer.view;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author juliozilman
 */
public class PeriodoBusqueda extends JDialog {
    private int returnStatus = 9;
    final int RET_CANCEL = 0;
    private JDateChooser txtFechaDesde;
    private JDateChooser txtFechaHasta;
    private Date fechaHasta;
    private Date fechaDesde;    
    final int RET_OK     = 1;
 
    private ContadorDePalabras contadorPalabra;
    public PeriodoBusqueda() {        
        setTitle("Perido de Busqueda");        
        iniciar();
     // end DlgParametros()
    }
    public PeriodoBusqueda(ContadorDePalabras contadorPalabra) {        
        super(contadorPalabra);
        setTitle("Perido de Busqueda");    
        this.contadorPalabra = contadorPalabra;
    }
    
    private void iniciar() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(crearPanelDatos(), BorderLayout.CENTER);
        getContentPane().add(crearPanelBotones(), BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);  // center the window
    } // end initComponents()

    private JPanel crearPanelDatos() {
        JPanel panelDatos = new JPanel(new GridLayout(0,1));
        panelDatos.setBorder(BorderFactory.createTitledBorder("Parametros"));
        populate(panelDatos);
        return panelDatos;
    } // end crearPanelDatos()
    
    protected int populate(JPanel panel) {
        int ancho;
        int j = 0;
        int k = 0;
        String rotulo;
        JPanel linea = new JPanel(new MigLayout());
        JLabel label = new JLabel("Fecha Desde" + ": ", null, JLabel.LEFT);
        label.setBounds(1,1, 100, 18);
        linea.add(label);
        txtFechaDesde = new JDateChooser();
        linea.add(txtFechaDesde);
        panel.add(linea);
        
        
        JPanel lineaDos = new JPanel(new MigLayout());
        JLabel labelHasta = new JLabel("Fecha Hasta" + ": ", null, JLabel.LEFT);
        labelHasta.setBounds(1,1, 100, 18);
        lineaDos.add(labelHasta);
        txtFechaHasta = new JDateChooser();
        lineaDos.add(txtFechaHasta);
        panel.add(lineaDos);
        
        
        return k;
    } // end populate()

    /**
     * @return the returnStatus
     */
    public int getReturnStatus() {
        return returnStatus;
    }

    /**
     * @param returnStatus the returnStatus to set
     */
    public void setReturnStatus(int returnStatus) {
        this.returnStatus = returnStatus;
    }

    /**
     * @return the fechaHasta
     */
    public Date getFechaHasta() {
        return fechaHasta;
    }

    /**
     * @param fechaHasta the fechaHasta to set
     */
    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    /**
     * @return the fechaDesde
     */
    public Date getFechaDesde() {
        return fechaDesde;
    }

    /**
     * @param fechaDesde the fechaDesde to set
     */
    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }
    
    private JPanel crearPanelBotones() {
        JPanel panelBotones = new JPanel();
        JButton btnProcesar = new JButton("Aceptar");
        btnProcesar.addActionListener(new ActionListener() {        
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnProcesarActionPerformed(evt);
            }
        });
        panelBotones.add(btnProcesar);

        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        panelBotones.add(btnSalir);
        return panelBotones;
    } // end crearPanelBotones()
    
    private void btnProcesarActionPerformed(ActionEvent evt) {
        procesar();          
        doClose(RET_OK);
        contadorPalabra.procesarPeriodo();
    } // end btnProcesarActionPerformed()
    private void btnSalirActionPerformed(ActionEvent evt) {
        doClose(RET_CANCEL);
    } // end btnSalirActionPerformed()
    
    public void procesar() {
        System.out.println(txtFechaDesde.getDate());
        System.out.println(txtFechaHasta.getDate());        
        setFechaDesde(txtFechaDesde.getDate());
        setFechaHasta(txtFechaHasta.getDate());           
        
    } // end procesar()
    private void formWindowClosing(WindowEvent evt) {
        doClose(RET_CANCEL);
    } // end formWindowClosing()
    public void doClose(int status) {
        setReturnStatus(status);
        setVisible(false);
    } // end doClose()
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ContadorDePalabras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ContadorDePalabras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ContadorDePalabras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ContadorDePalabras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PeriodoBusqueda().setVisible(true);
            }
        });
    }
}
