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
@Table(name = "pagina")
@NamedQueries({
    @NamedQuery(name = "Pagina.findAll", query = "SELECT p FROM Pagina p"),
    @NamedQuery(name = "Pagina.findByIdpagina", query = "SELECT p FROM Pagina p WHERE p.idpagina = :idpagina"),
    @NamedQuery(name = "Pagina.findByLink", query = "SELECT p FROM Pagina p WHERE p.link = :link"),
    @NamedQuery(name = "Pagina.findByDescripcion", query = "SELECT p FROM Pagina p WHERE p.descripcion = :descripcion")})
public class Pagina implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpagina")
    private Integer idpagina;
    @Column(name = "link")
    private String link;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpagina", fetch = FetchType.LAZY)
    private List<Seccion> seccionList;

    public Pagina() {
    }

    public Pagina(Integer idpagina) {
        this.idpagina = idpagina;
    }

    public Integer getIdpagina() {
        return idpagina;
    }

    public void setIdpagina(Integer idpagina) {
        this.idpagina = idpagina;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Seccion> getSeccionList() {
        return seccionList;
    }

    public void setSeccionList(List<Seccion> seccionList) {
        this.seccionList = seccionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpagina != null ? idpagina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagina)) {
            return false;
        }
        Pagina other = (Pagina) object;
        if ((this.idpagina == null && other.idpagina != null) || (this.idpagina != null && !this.idpagina.equals(other.idpagina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.siuden.analyzer.domain.Pagina[ idpagina=" + idpagina + " ]";
    }
    
}
