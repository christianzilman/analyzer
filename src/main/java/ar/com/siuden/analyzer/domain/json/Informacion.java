/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.siuden.analyzer.domain.json;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author juliozilman
 */
public class Informacion {
    @SerializedName("clase")
    private String clase;
    @SerializedName("id")
    private String  id;
    @SerializedName("url")
    private String url;
    @SerializedName("ocultarFecha")
    private String ocultarFecha;
    @SerializedName("fecha_actualizacion")
    private String fecha_actualizacion;
    @SerializedName("dominio")
    private String dominio;
    @SerializedName("titulo")
    private String titulo;
    @SerializedName("bajada")
    private String bajada;
    @SerializedName("categoria_descripcion")
    private String categoria_descripcion;
    @SerializedName("imagen")
    private String imagen;
    @SerializedName("fecha")
    private String fecha;
    @SerializedName("hora")
    private String hora;
    @SerializedName("cantComentarios")
    private String cantComentarios;
    @SerializedName("sitio")
    private String sitio;
    @SerializedName("temaUrl")
    private String temaUrl;
    @SerializedName("tema")
    private String tema;
    @SerializedName("autor1")
    private String autor1;
    @SerializedName("autor1_tipo")
    private String autor1_tipo;
    @SerializedName("autor2")
    private String autor2;
    @SerializedName("autor2_tipo")
    private String autor2_tipo;
    @SerializedName("autor1Url")
    private String autor1Url;
    @SerializedName("autor2Url")
    private String autor2Url;
    @SerializedName("alt")
    private String alt;
    @SerializedName("rel")
    private String rel;
    @SerializedName("autorExterno")
    private String autorExterno;
    @SerializedName("autorMedio")
    private String autorMedio;
    @SerializedName("autorUrl")
    private String autorUrl;
    @SerializedName("autorPermanente")
    private String autorPermanente;
    @SerializedName("fecha_movil")
    private String fecha_movil;
    public Informacion() {
    }

    
    /**
     * @return the clase
     */
    public String getClase() {
        return clase;
    }

    /**
     * @param clase the clase to set
     */
    public void setClase(String clase) {
        this.clase = clase;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the ocultarFecha
     */
    public String getOcultarFecha() {
        return ocultarFecha;
    }

    /**
     * @param ocultarFecha the ocultarFecha to set
     */
    public void setOcultarFecha(String ocultarFecha) {
        this.ocultarFecha = ocultarFecha;
    }

    /**
     * @return the fecha_actualizacion
     */
    public String getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    /**
     * @param fecha_actualizacion the fecha_actualizacion to set
     */
    public void setFecha_actualizacion(String fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }

    /**
     * @return the dominio
     */
    public String getDominio() {
        return dominio;
    }

    /**
     * @param dominio the dominio to set
     */
    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the bajada
     */
    public String getBajada() {
        return bajada;
    }

    /**
     * @param bajada the bajada to set
     */
    public void setBajada(String bajada) {
        this.bajada = bajada;
    }

    /**
     * @return the categoria_descripcion
     */
    public String getCategoria_descripcion() {
        return categoria_descripcion;
    }

    /**
     * @param categoria_descripcion the categoria_descripcion to set
     */
    public void setCategoria_descripcion(String categoria_descripcion) {
        this.categoria_descripcion = categoria_descripcion;
    }

    /**
     * @return the imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * @return the cantComentarios
     */
    public String getCantComentarios() {
        return cantComentarios;
    }

    /**
     * @param cantComentarios the cantComentarios to set
     */
    public void setCantComentarios(String cantComentarios) {
        this.cantComentarios = cantComentarios;
    }

    /**
     * @return the sitio
     */
    public String getSitio() {
        return sitio;
    }

    /**
     * @param sitio the sitio to set
     */
    public void setSitio(String sitio) {
        this.sitio = sitio;
    }

    /**
     * @return the temaUrl
     */
    public String getTemaUrl() {
        return temaUrl;
    }

    /**
     * @param temaUrl the temaUrl to set
     */
    public void setTemaUrl(String temaUrl) {
        this.temaUrl = temaUrl;
    }

    /**
     * @return the tema
     */
    public String getTema() {
        return tema;
    }

    /**
     * @param tema the tema to set
     */
    public void setTema(String tema) {
        this.tema = tema;
    }

    /**
     * @return the autor1
     */
    public String getAutor1() {
        return autor1;
    }

    /**
     * @param autor1 the autor1 to set
     */
    public void setAutor1(String autor1) {
        this.autor1 = autor1;
    }

    /**
     * @return the autor1_tipo
     */
    public String getAutor1_tipo() {
        return autor1_tipo;
    }

    /**
     * @param autor1_tipo the autor1_tipo to set
     */
    public void setAutor1_tipo(String autor1_tipo) {
        this.autor1_tipo = autor1_tipo;
    }

    /**
     * @return the autor2
     */
    public String getAutor2() {
        return autor2;
    }

    /**
     * @param autor2 the autor2 to set
     */
    public void setAutor2(String autor2) {
        this.autor2 = autor2;
    }

    /**
     * @return the autor2_tipo
     */
    public String getAutor2_tipo() {
        return autor2_tipo;
    }

    /**
     * @param autor2_tipo the autor2_tipo to set
     */
    public void setAutor2_tipo(String autor2_tipo) {
        this.autor2_tipo = autor2_tipo;
    }

    /**
     * @return the autor1Url
     */
    public String getAutor1Url() {
        return autor1Url;
    }

    /**
     * @param autor1Url the autor1Url to set
     */
    public void setAutor1Url(String autor1Url) {
        this.autor1Url = autor1Url;
    }

    /**
     * @return the autor2Url
     */
    public String getAutor2Url() {
        return autor2Url;
    }

    /**
     * @param autor2Url the autor2Url to set
     */
    public void setAutor2Url(String autor2Url) {
        this.autor2Url = autor2Url;
    }

    /**
     * @return the alt
     */
    public String getAlt() {
        return alt;
    }

    /**
     * @param alt the alt to set
     */
    public void setAlt(String alt) {
        this.alt = alt;
    }

    /**
     * @return the rel
     */
    public String getRel() {
        return rel;
    }

    /**
     * @param rel the rel to set
     */
    public void setRel(String rel) {
        this.rel = rel;
    }

    /**
     * @return the autorExterno
     */
    public String getAutorExterno() {
        return autorExterno;
    }

    /**
     * @param autorExterno the autorExterno to set
     */
    public void setAutorExterno(String autorExterno) {
        this.autorExterno = autorExterno;
    }

    /**
     * @return the autorMedio
     */
    public String getAutorMedio() {
        return autorMedio;
    }

    /**
     * @param autorMedio the autorMedio to set
     */
    public void setAutorMedio(String autorMedio) {
        this.autorMedio = autorMedio;
    }

    /**
     * @return the autorUrl
     */
    public String getAutorUrl() {
        return autorUrl;
    }

    /**
     * @param autorUrl the autorUrl to set
     */
    public void setAutorUrl(String autorUrl) {
        this.autorUrl = autorUrl;
    }

    /**
     * @return the autorPermanente
     */
    public String getAutorPermanente() {
        return autorPermanente;
    }

    /**
     * @param autorPermanente the autorPermanente to set
     */
    public void setAutorPermanente(String autorPermanente) {
        this.autorPermanente = autorPermanente;
    }

    /**
     * @return the fecha_movil
     */
    public String getFecha_movil() {
        return fecha_movil;
    }

    /**
     * @param fecha_movil the fecha_movil to set
     */
    public void setFecha_movil(String fecha_movil) {
        this.fecha_movil = fecha_movil;
    }
}
