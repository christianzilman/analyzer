/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.siuden.analyzer.controller;

import ar.com.siuden.analyzer.Peticion;
import ar.com.siuden.analyzer.domain.DetalleResultado;
import ar.com.siuden.analyzer.domain.Palabra;
import ar.com.siuden.analyzer.domain.Resultado;
import ar.com.siuden.analyzer.library.Funciones;
import ar.com.siuden.analyzer.service.DetalleResultadoService;
import ar.com.siuden.analyzer.service.PalabraService;
import ar.com.siuden.analyzer.service.ResultadoService;
import ar.com.siuden.analyzer.service.SeccionService;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author juliozilman
 */
@Component
public class GacetaController extends Thread {

    // Variables
    private boolean life;
    private int order;
    private JProgressBar progBar;
    private JLabel jlabelDescripcion;
    private int cantidadPagina = 1;
    private List<String> linkNoticias;
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

    public GacetaController() {
        //setTitle("Progress Bar Application");        
    }

    public void iniciar() {
        this.setLife(true);
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

        while (isLife()) {
            if (order == 1) {
                if (pararBusqueda == true) {
                    // boolean parar = true;

                    String datos = Peticion.postRequest("http://www.lagaceta.com.ar/tags/listado_nuevo_anteriores",
                            "POST",
                            "tag_id=11&start=" + String.valueOf(paginador).trim());
                    Document doc = Jsoup.parse(datos);
                    //Element content = doc,;
                    Elements links = doc.getElementsByTag("article");

                    getProgBar().setMaximum(links.size());
                    jlabelDescripcion.setText("Buscando link de página 0" + " de " + links.size());

                    procesarDatosLink(links);
                    if (pararBusqueda == false) {
                        getProgBar().setValue(0);
                        getProgBar().setMaximum(linkNoticias.size());
                        setPararProceso(true);
                    }
                    paginador = paginador + 30;
                }
                if (isPararProceso() == true) {
                    if (linkNoticias.size() > cantidadLink) {
                        procesarLink(linkNoticias.get(cantidadLink));
                        cantidadLink++;
                        jlabelDescripcion.setText("Procasando link de página " + cantidadLink + " de " + linkNoticias.size());
                        getProgBar().setValue(cantidadLink);
                    } else {
                        setPararProceso(false);
                        setPararExportar(true);
                        exportarExcelPorFechaParteUno();
                        getProgBar().setValue(0);
                        getProgBar().setMaximum(listResultado.size());

                    }
                }
                if (isPararExportar() == true) {
                    if (listResultado.size() > cantidadRegExportar) {
                        exportarExcelPorFechaParteDos(listResultado.get(cantidadRegExportar));
                        cantidadRegExportar++;
                        jlabelDescripcion.setText("Exportando registros de " + cantidadRegExportar + " de " + listResultado.size());
                        getProgBar().setValue(cantidadRegExportar);
                    } else {
                        setPararExportar(false);
                        generarArchivoExcel();
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

    /**
     *
     * @param links
     */
    public void procesarDatosLink(Elements links) {
        int contador = 0;
        for (Element link : links) {
            String linkText = link.text();

            Document doc2 = Jsoup.parse(link.toString());
            Elements linksNivel2 = doc2.getElementsByTag("h1");

            for (Element link2 : linksNivel2) {

                String linkText2 = (link2).toString();

                try {
                    Document doctest = Jsoup.parse(linkText2);
                    Element linktest = doctest.select("a").first();

                    String text = doctest.body().text(); // "An example link"
                    String linkHrefTest = linktest.attr("href"); // "http://example.com/"

                    // por fecha 
                    Document documento = Jsoup.connect(linkHrefTest).get();
                    Elements fecha = documento.getElementsByTag("time");
                    String[] fechaDatos = fecha.toString().split("\"");
                    String[] fechaPartes = (fechaDatos[3]).split("-");
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Integer.valueOf(fechaPartes[2]), Integer.valueOf(fechaPartes[1]) - 1, Integer.valueOf(fechaPartes[0]), 0, 0, 0);
                    System.out.println(fecha);
                    System.out.println(fechaDesde);
                    System.out.println(fechaHasta);
                    System.out.println(calendar.getTime());
                    if (Funciones.perteneceFecha(fechaDesde, fechaHasta, calendar.getTime())) {
                        linkNoticias.add(linkHrefTest);
                    } else {

                        if (!Funciones.esMenorA(fechaDesde, calendar.getTime())) {
                            setPararBusqueda(false);
                            break;
                        }
                    }
                } catch (Exception ex) {
                    // ex.printStackTrace();
                }
                break;
            }
            contador++;
            jlabelDescripcion.setText("Buscando link de página " + contador + " de " + links.size());
            getProgBar().setValue(contador);
            if (isPararBusqueda() == false) {
                break;
            }
        }
    }

    /**
     *
     * @param link
     */
    public void procesarLink(String link) {
        try {

            boolean existeRegistro = resultadoService.existLinkNota(link);
            if (existeRegistro) {
                if (reprocesar) {
                    resultado = resultadoService.getByLinkNota(link);
                }
            } else {
                resultado = new Resultado();
            }

            if ((!existeRegistro) || reprocesar) {
                Document documento = Jsoup.connect(link).get();
                Element nota = documento.getElementById("texto_nota");
                String textoNota = nota.text();
                System.out.println(textoNota);
                resultado.setTexto(textoNota);
                resultado.setSeccion(seccionService.get(1));
                resultado.setLinkNota(link);
                Elements fecha = documento.getElementsByTag("time");

                System.out.println(documento.getElementsByAttributeStarting("datetime"));
                String[] fechaDatos = fecha.toString().split("\"");
                String[] fechaPartes = (fechaDatos[3]).split("-");

                Calendar calendar = Calendar.getInstance();
                calendar.set(Integer.valueOf(fechaPartes[2]), Integer.valueOf(fechaPartes[1]) - 1, Integer.valueOf(fechaPartes[0]));

                resultado.setFechaNota(calendar.getTime());
                resultado.setFechaCalculo(new Date());

                //Element titulo = documento.select("h1").first();
                Element titulo = documento.select("h1.h40").first();
                String linkText = titulo.text();
                resultado.setTituloNota(linkText);

                try {
                    Element subTituloElement = documento.select("div.bij").first();
                    String subTitulo = subTituloElement.text();
                    resultado.setSubTituloNota(subTitulo);
                } catch (Exception ex) {
                    resultado.setSubTituloNota("");
                }
                //System.out.println(linkText);
                boolean nuevoDetalleResultado;
                int cantidadOcurrencia;


                for (Palabra palabra : palabraService.getAll()) {
                    nuevoDetalleResultado = true;
                    String palabraBuscar = palabra.getNombre();
                    cantidadOcurrencia = Funciones.contadorDePalabrasPersonalizado(textoNota, palabraBuscar);

                    //Existe el resultado actualizará los item 
                    if (existeRegistro) {
                        if (detalleResultadoService.existResultadoPalabra(resultado.getIdResultado(), palabra.getIdPalabra())) {
                            nuevoDetalleResultado = false;
                            DetalleResultado detalleResultado = detalleResultadoService.getByResultadoPalabra(resultado.getIdResultado(), palabra.getIdPalabra());
                            detalleResultadoService.update(detalleResultado);
                        }
                    }
                    if (nuevoDetalleResultado) {
                        if (existeRegistro) {
                            DetalleResultado detalleResultado = new DetalleResultado();
                            detalleResultado.setPalabra(palabra);
                            detalleResultado.setResultado(resultado);
                            detalleResultado.setCantidad(cantidadOcurrencia);
                            detalleResultadoService.save(detalleResultado);
                        } else {
                            DetalleResultado detalleResultado = new DetalleResultado();
                            detalleResultado.setPalabra(palabra);
                            detalleResultado.setResultado(resultado);
                            detalleResultado.setCantidad(cantidadOcurrencia);
                            resultado.getDetalleresultadoList().add(detalleResultado);
                        }
                    }

                }
                if (existeRegistro) {
                    resultadoService.update(resultado);
                } else {
                    resultadoService.save(resultado);
                }

            }
        } catch (IOException ex) {
            //Logger.getLogger(GacetaController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al procesar uno de los link", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     *
     */
    public void exportarExcelPorFechaParteUno() {
        listTotales = new HashMap<Integer, Integer>();
        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet("La Gaceta");
        rownum = 0;
        Row rowFisrt = sheet.createRow(rownum++);
        Cell cellFecha = rowFisrt.createCell(0);
        cellFecha.setCellValue("Fecha");

        Cell cellGaceta = rowFisrt.createCell(1);
        cellGaceta.setCellValue("La Gaceta");


        Row rowSecond = sheet.createRow(rownum++);

        Cell cellF = rowSecond.createCell(0);
        cellF.setCellValue("");
        int column = 1;

        listPalabra = palabraService.getAll();
        for (Palabra palabra : listPalabra) {
            Cell cellPalabra = rowSecond.createCell(column);
            cellPalabra.setCellValue(palabra.getNombre());
            listTotales.put(palabra.getIdPalabra(), 0);
            column++;
        }
        Cell cellTituloNota = rowSecond.createCell(column);
        cellTituloNota.setCellValue("Titulo de la nota");
        column++;
        Cell cellLinkNota = rowSecond.createCell(column);
        cellLinkNota.setCellValue("Link de la nota");

        listResultado = new ArrayList<Resultado>();

        listResultado = resultadoService.getByDateSection(fechaDesde, fechaHasta, 1);

    }

    /**
     *
     * @param resultado
     */
    public void exportarExcelPorFechaParteDos(Resultado resultado) {
        int column = 0;
        Row rowRegistros = sheet.createRow(rownum++);
        column = 0;
        Cell cellFechaNota = rowRegistros.createCell(column);
        String patron = "dd/MM/yyyy";
        SimpleDateFormat formato = new SimpleDateFormat(patron);
        cellFechaNota.setCellValue(formato.format(resultado.getFechaNota()));
        column++;
        for (Palabra palabra : listPalabra) {
            Cell cellPalabra = rowRegistros.createCell(column);
            try {
                DetalleResultado detalleResultado = detalleResultadoService.getByResultadoPalabra(resultado.getIdResultado(), palabra.getIdPalabra());
                cellPalabra.setCellValue(detalleResultado.getCantidad());
                listTotales.put(palabra.getIdPalabra(), (listTotales.get(palabra.getIdPalabra()) + detalleResultado.getCantidad()));
            } catch (Exception ex) {
                cellPalabra.setCellValue("0");
            }
            column++;
        }
        Cell cellTituloNotaDatos = rowRegistros.createCell(column);
        cellTituloNotaDatos.setCellValue(resultado.getTituloNota());
        column++;
        Cell cellLinkNotaDatos = rowRegistros.createCell(column);
        cellLinkNotaDatos.setCellValue(resultado.getLinkNota());
    }

    public void generarArchivoExcel() {
        int column = 0;
        Row rowRegistros = sheet.createRow(rownum++);
        column = 0;
        Cell cellTotal = rowRegistros.createCell(column);
        cellTotal.setCellValue("Total:");
        column++;
        for (Palabra palabra : listPalabra) {
            Cell cellPalabra = rowRegistros.createCell(column);
            try {
                cellPalabra.setCellValue(listTotales.get(palabra.getIdPalabra()));
            } catch (Exception ex) {
                cellPalabra.setCellValue("0");
            }
            column++;
        }

        try {
            String patron = "dd-MM-yyyy-HH-mm";
            SimpleDateFormat formato = new SimpleDateFormat(patron);
            FileOutputStream out =
                    new FileOutputStream(new File("lagaceta/lagaceta" + formato.format(new Date()) + ".xls"));
            workbook.write(out);
            out.close();
            System.out.println("Excel written successfully..");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
