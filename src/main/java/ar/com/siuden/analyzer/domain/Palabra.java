/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.siuden.analyzer.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author juliozilman
 */
@Entity
@Table(name = "palabra")
@NamedQueries({
    @NamedQuery(name = "Palabra.findAll", query = "SELECT p FROM Palabra p"),
    @NamedQuery(name = "Palabra.findByIdpalabra", query = "SELECT p FROM Palabra p WHERE p.idPalabra = :idpalabra"),
    @NamedQuery(name = "Palabra.findByNombre", query = "SELECT p FROM Palabra p WHERE p.nombre = :nombre")})
public class Palabra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpalabra")
    private Integer idPalabra;
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "palabra", fetch = FetchType.LAZY)
    private List<DetalleResultado> detalleResultadoList;
    
    @OneToMany(cascade= CascadeType.ALL,mappedBy = "idSeccion", targetEntity = Seccion.class, fetch = FetchType.LAZY)
    private List<Seccion> seccionList;

    public Palabra() {
    }

    public Palabra(Integer idPalabra) {
        this.idPalabra = idPalabra;
    }

    public Integer getIdPalabra() {
        return idPalabra;
    }

    public void setIdPalabra(Integer idPalabra) {
        this.idPalabra = idPalabra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<DetalleResultado> getDetalleResultadoList() {
        return detalleResultadoList;
    }

    public void setDetalleresultadoList(List<DetalleResultado> detalleResultadoList) {
        this.detalleResultadoList = detalleResultadoList;
    }

    /**
     * @return the seccionList
     */
    public List<Seccion> getSeccionList() {
        return seccionList;
    }

    /**
     * @param seccionList the seccionList to set
     */
    public void setSeccionList(List<Seccion> seccionList) {
        this.seccionList = seccionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPalabra != null ? idPalabra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Palabra)) {
            return false;
        }
        Palabra other = (Palabra) object;
        if ((this.idPalabra == null && other.idPalabra != null) || (this.idPalabra != null && !this.idPalabra.equals(other.idPalabra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.siuden.analyzer.domain.Palabra[ idPalabra=" + idPalabra + " ]";
    }
    
}
