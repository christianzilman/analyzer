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
    @NamedQuery(name = "Resultado.findByIdresultado", query = "SELECT r FROM Resultado r WHERE r.idResultado = :idResultado"),
    @NamedQuery(name = "Resultado.findByLinknota", query = "SELECT r FROM Resultado r WHERE r.linkNota = :linknota"),
    @NamedQuery(name = "Resultado.findByTitulonota", query = "SELECT r FROM Resultado r WHERE r.tituloNota = :titulonota"),
    @NamedQuery(name = "Resultado.findByFechanota", query = "SELECT r FROM Resultado r WHERE r.fechaNota = :fechanota"),
    @NamedQuery(name = "Resultado.findByFechacalculo", query = "SELECT r FROM Resultado r WHERE r.fechaCalculo = :fechacalculo")})
public class Resultado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idresultado")
    private Integer idResultado;
    @Column(name = "linknota")
    private String linkNota;
    @Column(name = "titulonota")
    private String tituloNota;
    @Column(name = "subtitulonota")
    private String subTituloNota;
    @Column(name = "texto")
    private String texto;
    @Column(name = "fechanota")
    @Temporal(TemporalType.DATE)
    private Date fechaNota;
    @Column(name = "fechacalculo")
    @Temporal(TemporalType.DATE)
    private Date fechaCalculo;
    @OneToMany(cascade= CascadeType.ALL,mappedBy = "resultado", targetEntity = DetalleResultado.class, fetch = FetchType.LAZY)
    private List<DetalleResultado> detalleResultadoList;
    @JoinColumn(name = "idseccion")
    @ManyToOne(targetEntity = Seccion.class)
    private Seccion seccion;

    public Resultado() {
        detalleResultadoList = new ArrayList<DetalleResultado>();
    }

    public Resultado(Integer idResultado) {
        this.idResultado = idResultado;
    }

    public Integer getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(Integer idResultado) {
        this.idResultado = idResultado;
    }

    public String getLinkNota() {
        return linkNota;
    }

    public void setLinkNota(String linkNota) {
        this.linkNota = linkNota;
    }

    public String getTituloNota() {
        return tituloNota;
    }

    public void setTituloNota(String tituloNota) {
        this.tituloNota = tituloNota;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getFechaNota() {
        return fechaNota;
    }

    public void setFechaNota(Date fechaNota) {
        this.fechaNota = fechaNota;
    }

    public Date getFechaCalculo() {
        return fechaCalculo;
    }

    public void setFechaCalculo(Date fechaCalculo) {
        this.fechaCalculo = fechaCalculo;
    }

    public List<DetalleResultado> getDetalleResultadoList() {
        return detalleResultadoList;
    }

    public void setDetalleResultadoList(List<DetalleResultado> detalleResultadoList) {
        this.detalleResultadoList = detalleResultadoList;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
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
        hash += (idResultado != null ? idResultado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resultado)) {
            return false;
        }
        Resultado other = (Resultado) object;
        if ((this.idResultado == null && other.idResultado != null) || (this.idResultado != null && !this.idResultado.equals(other.idResultado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.siuden.analyzer.domain.Resultado[ idResultado=" + idResultado + " ]";
    }
}
