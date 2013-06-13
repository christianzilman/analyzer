/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.siuden.analyzer.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.stereotype.Component;

/**
 *
 * @author juliozilman
 */
@Component
@Entity
@Table(name = "resultado")
@NamedQueries({
    @NamedQuery(name = "Resultado.findAll", query = "SELECT r FROM Resultado r"),
    @NamedQuery(name = "Resultado.findByIdresultado", query = "SELECT r FROM Resultado r WHERE r.idresultado = :idresultado"),
    @NamedQuery(name = "Resultado.findByLinknota", query = "SELECT r FROM Resultado r WHERE r.linknota = :linknota"),
    @NamedQuery(name = "Resultado.findByTitulonota", query = "SELECT r FROM Resultado r WHERE r.titulonota = :titulonota"),
    @NamedQuery(name = "Resultado.findByFechanota", query = "SELECT r FROM Resultado r WHERE r.fechanota = :fechanota"),
    @NamedQuery(name = "Resultado.findByFechacalculo", query = "SELECT r FROM Resultado r WHERE r.fechacalculo = :fechacalculo")})
public class Resultado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idresultado")
    private Integer idresultado;
    @Column(name = "linknota")
    private String linknota;
    @Column(name = "titulonota")
    private String titulonota;
    @Column(name = "subtitulonota")
    private String subTituloNota;
    @Column(name = "texto")
    private String texto;
    @Column(name = "fechanota")
    @Temporal(TemporalType.DATE)
    private Date fechanota;
    @Column(name = "fechacalculo")
    @Temporal(TemporalType.DATE)
    private Date fechacalculo;
    @OneToMany(cascade= CascadeType.ALL,mappedBy = "idresultado", targetEntity = DetalleResultado.class, fetch = FetchType.LAZY)
    private List<DetalleResultado> detalleresultadoList;
    @JoinColumn(name = "idseccion")
    @ManyToOne(targetEntity = Seccion.class)
    private Seccion idseccion;

    public Resultado() {
        detalleresultadoList = new ArrayList<DetalleResultado>();
    }

    public Resultado(Integer idresultado) {
        this.idresultado = idresultado;
    }

    public Integer getIdResultado() {
        return idresultado;
    }

    public void setIdResultado(Integer idresultado) {
        this.idresultado = idresultado;
    }

    public String getLinkNota() {
        return linknota;
    }

    public void setLinkNota(String linknota) {
        this.linknota = linknota;
    }

    public String getTituloNota() {
        return titulonota;
    }

    public void setTituloNota(String titulonota) {
        this.titulonota = titulonota;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getFechaNota() {
        return fechanota;
    }

    public void setFechaNota(Date fechanota) {
        this.fechanota = fechanota;
    }

    public Date getFechaCalculo() {
        return fechacalculo;
    }

    public void setFechaCalculo(Date fechacalculo) {
        this.fechacalculo = fechacalculo;
    }

    public List<DetalleResultado> getDetalleresultadoList() {
        return detalleresultadoList;
    }

    public void setDetalleResultadoList(List<DetalleResultado> detalleresultadoList) {
        this.detalleresultadoList = detalleresultadoList;
    }

    public Seccion getSeccion() {
        return idseccion;
    }

    public void setSeccion(Seccion idseccion) {
        this.idseccion = idseccion;
    }

    /**
     * @return the subTituloNota
     */
    public String getSubTituloNota() {
        return subTituloNota;
    }

    /**
     * @param subTituloNota the subTituloNota to set
     */
    public void setSubTituloNota(String subTituloNota) {
        this.subTituloNota = subTituloNota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idresultado != null ? idresultado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resultado)) {
            return false;
        }
        Resultado other = (Resultado) object;
        if ((this.idresultado == null && other.idresultado != null) || (this.idresultado != null && !this.idresultado.equals(other.idresultado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.siuden.analyzer.domain.Resultado[ idresultado=" + idresultado + " ]";
    }
}
