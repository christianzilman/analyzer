/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.siuden.analyzer.controller;

import ar.com.siuden.analyzer.domain.Palabra;
import ar.com.siuden.analyzer.domain.Resultado;
import ar.com.siuden.analyzer.library.Funciones;
import ar.com.siuden.analyzer.service.DetalleResultadoService;
import ar.com.siuden.analyzer.service.PalabraService;
import ar.com.siuden.analyzer.service.ResultadoService;
import ar.com.siuden.analyzer.service.SeccionService;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author juliozilman
 */
@Component
public class ClarinController {
    // Variables

    private boolean life;
    private int order;
    private JProgressBar progBar;
    private JLabel jlabelDescripcion;
    private int cantidadPagina = 1;
    private List<String> linkNoticias;
    private List<Date> fechaNoticia;
    @Autowired
    private ResultadoService resultadoService;
    @Autowired
    private DetalleResultadoService detalleResultadoService;
    private Resultado resultado;
    @Autowired
    private PalabraService palabraService;
    @Autowired
    private SeccionService seccionService;
    private Date fechaDesde;
    private Date fechaHasta;
    private boolean pararBusqueda;
    private boolean pararProceso;
    private boolean pararExportar;
    private int cantidadLink = 0;
    private int paginador = 1;
    private int cantidadRegExportar = 0;
    // Exportar excel
    private List<Resultado> listResultado;
    private HSSFWorkbook workbook;
    private HSSFSheet sheet;
    private int rownum;
    private List<Palabra> listPalabra;
    private HashMap<Integer, Integer> listTotales;
    private boolean reprocesar;
    private long cantidadDias;
    private int contadorDias;
    private Gson gson;

    public void iniciar() {
        this.setLife(true);
        gson = new Gson();
    }

    // Methods
    public void kill() {
        this.setLife(false);
    }

    public void cmd(int in) { // Setter
        this.order = in;
    }

    public void run() {
        setPararBusqueda(true);
        setPararProceso(false);
        setPararExportar(false);
        linkNoticias = new ArrayList<String>();
        fechaNoticia = new ArrayList<Date>();
        while (isLife()) {
            if (order == 1) {
                if (pararBusqueda == true) {
                    if (cantidadDias > contadorDias) {
                        //Falta por hacer
                        String datos = Funciones.postGetRequest("http://www.clarin.com.ar",
                                "GET", "");

                        contadorDias++;
                        jlabelDescripcion.setText("Buscando link de página " + contadorDias + " de " + cantidadDias);
                        getProgBar().setValue(cantidadLink);
                    }

                    if (pararBusqueda == false) {
                        getProgBar().setValue(0);
                        getProgBar().setMaximum(linkNoticias.size());
                        setPararProceso(true);
                    }                 
                    paginador = paginador + 1;
                }
                if (isPararProceso() == true) {
                    if (linkNoticias.size() > cantidadLink) {
                        //procesarLink(linkNoticias.get(cantidadLink));
                        cantidadLink++;
                        jlabelDescripcion.setText("Procasando link de página " + cantidadLink + " de " + linkNoticias.size());
                        getProgBar().setValue(cantidadLink);
                    } else {
                        setPararProceso(false);
                        setPararExportar(true);
                        //exportarExcelPorFechaParteUno();
                        getProgBar().setValue(0);
                        getProgBar().setMaximum(listResultado.size());
                    }
                }
                if (isPararExportar() == true) {
                    if (listResultado.size() > cantidadRegExportar) {
                        //exportarExcelPorFechaParteDos(listResultado.get(cantidadRegExportar));
                        cantidadRegExportar++;
                        jlabelDescripcion.setText("Exportando registros de " + cantidadRegExportar + " de " + listResultado.size());
                        getProgBar().setValue(cantidadRegExportar);
                    } else {
                        setPararExportar(false);
                        //generarArchivoExcel();
                        cmd(0);
                    }
                }
            }
            try {
                Thread.sleep(10); // 125 millisegundos
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Thread->Sleep problem:");
            }
        }
    }

    public void calcularCantidadDia(Date fechaDesde, Date fechaHasta) {
        cantidadDias = Funciones.calcularCantidadDia(fechaDesde, fechaHasta);
        contadorDias = 0;
        getProgBar().setMaximum(cantidadLink);
        getProgBar().setValue(contadorDias);
    }

    /**
     * @return the cantidadPagina
     */
    public int getCantidadPagina() {
        return cantidadPagina;
    }

    /**
     * @param cantidadPagina the cantidadPagina to set
     */
    public void setCantidadPagina(int cantidadPagina) {
        this.cantidadPagina = cantidadPagina;
    }

    /**
     * @return the linkNoticias
     */
    public List<String> getLinkNoticias() {
        return linkNoticias;
    }

    /**
     * @param linkNoticias the linkNoticias to set
     */
    public void setLinkNoticias(List<String> linkNoticias) {
        this.linkNoticias = linkNoticias;
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
     * @return the progBar
     */
    public JProgressBar getProgBar() {
        return progBar;
    }

    /**
     * @param progBar the progBar to set
     */
    public void setProgBar(JProgressBar progBar) {
        this.progBar = progBar;
    }

    /**
     * @return the jlabelDescripcion
     */
    public JLabel getJlabelDescripcion() {
        return jlabelDescripcion;
    }

    /**
     * @param jlabelDescripcion the jlabelDescripcion to set
     */
    public void setJlabelDescripcion(JLabel jlabelDescripcion) {
        this.jlabelDescripcion = jlabelDescripcion;
    }

    /**
     * @return the pararBusqueda
     */
    public boolean isPararBusqueda() {
        return pararBusqueda;
    }

    /**
     * @param pararBusqueda the pararBusqueda to set
     */
    public void setPararBusqueda(boolean pararBusqueda) {
        this.pararBusqueda = pararBusqueda;
    }

    /**
     * @return the pararProceso
     */
    public boolean isPararProceso() {
        return pararProceso;
    }

    /**
     * @param pararProceso the pararProceso to set
     */
    public void setPararProceso(boolean pararProceso) {
        this.pararProceso = pararProceso;
    }

    /**
     * @return the pararExportar
     */
    public boolean isPararExportar() {
        return pararExportar;
    }

    /**
     * @param pararExportar the pararExportar to set
     */
    public void setPararExportar(boolean pararExportar) {
        this.pararExportar = pararExportar;
    }

    /**
     * @return the life
     */
    public boolean isLife() {
        return life;
    }

    /**
     * @param life the life to set
     */
    public void setLife(boolean life) {
        this.life = life;
    }

    /**
     * @return the cantidadLink
     */
    public int getCantidadLink() {
        return cantidadLink;
    }

    /**
     * @param cantidadLink the cantidadLink to set
     */
    public void setCantidadLink(int cantidadLink) {
        this.cantidadLink = cantidadLink;
    }

    /**
     * @return the paginador
     */
    public int getPaginador() {
        return paginador;
    }

    /**
     * @param paginador the paginador to set
     */
    public void setPaginador(int paginador) {
        this.paginador = paginador;
    }

    /**
     * @return the cantidadRegExportar
     */
    public int getCantidadRegExportar() {
        return cantidadRegExportar;
    }

    /**
     * @param cantidadRegExportar the cantidadRegExportar to set
     */
    public void setCantidadRegExportar(int cantidadRegExportar) {
        this.cantidadRegExportar = cantidadRegExportar;
    }

    /**
     * @return the reprocesar
     */
    public boolean isReprocesar() {
        return reprocesar;
    }

    /**
     * @param reprocesar the reprocesar to set
     */
    public void setReprocesar(boolean reprocesar) {
        this.reprocesar = reprocesar;
    }

    /**
     * @return the fechaNoticia
     */
    public List<Date> getFechaNoticia() {
        return fechaNoticia;
    }

    /**
     * @param fechaNoticia the fechaNoticia to set
     */
    public void setFechaNoticia(List<Date> fechaNoticia) {
        this.fechaNoticia = fechaNoticia;
    }

    /**
     * @return the cantidadDias
     */
    public long getCantidadDias() {
        return cantidadDias;
    }

    /**
     * @param cantidadDias the cantidadDias to set
     */
    public void setCantidadDias(long cantidadDias) {
        this.cantidadDias = cantidadDias;
    }

    /**
     * @return the contadorDias
     */
    public int getContadorDias() {
        return contadorDias;
    }

    /**
     * @param contadorDias the contadorDias to set
     */
    public void setContadorDias(int contadorDias) {
        this.contadorDias = contadorDias;
    }
}
