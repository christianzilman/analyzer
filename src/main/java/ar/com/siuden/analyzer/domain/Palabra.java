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
    @NamedQuery(name = "Palabra.findByIdpalabra", query = "SELECT p FROM Palabra p WHERE p.idpalabra = :idpalabra"),
    @NamedQuery(name = "Palabra.findByNombre", query = "SELECT p FROM Palabra p WHERE p.nombre = :nombre")})
public class Palabra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpalabra")
    private Integer idpalabra;
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "palabra", fetch = FetchType.LAZY)
    private List<DetalleResultado> detalleresultadoList;

    public Palabra() {
    }

    public Palabra(Integer idpalabra) {
        this.idpalabra = idpalabra;
    }

    public Integer getIdPalabra() {
        return idpalabra;
    }

    public void setIdPalabra(Integer idpalabra) {
        this.idpalabra = idpalabra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<DetalleResultado> getDetalleresultadoList() {
        return detalleresultadoList;
    }

    public void setDetalleresultadoList(List<DetalleResultado> detalleresultadoList) {
        this.detalleresultadoList = detalleresultadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpalabra != null ? idpalabra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Palabra)) {
            return false;
        }
        Palabra other = (Palabra) object;
        if ((this.idpalabra == null && other.idpalabra != null) || (this.idpalabra != null && !this.idpalabra.equals(other.idpalabra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.siuden.analyzer.domain.Palabra[ idpalabra=" + idpalabra + " ]";
    }
    
}
