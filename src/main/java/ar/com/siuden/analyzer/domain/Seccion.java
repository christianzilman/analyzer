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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author juliozilman
 */
@Entity
@Table(name = "seccion")
@NamedQueries({
    @NamedQuery(name = "Seccion.findAll", query = "SELECT s FROM Seccion s"),
    @NamedQuery(name = "Seccion.findByIdseccion", query = "SELECT s FROM Seccion s WHERE s.idseccion = :idseccion"),
    @NamedQuery(name = "Seccion.findByNombre", query = "SELECT s FROM Seccion s WHERE s.nombre = :nombre")})
public class Seccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idseccion")
    private Integer idseccion;
    @Column(name = "nombre")
    private String nombre;
    
    @OneToMany(mappedBy = "idseccion",targetEntity=Resultado.class)
    private List<Resultado> resultadoList;
    
    @JoinColumn(name = "idpagina", referencedColumnName = "idpagina")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)    
    private Pagina idpagina;

    public Seccion() {
    }

    public Seccion(Integer idseccion) {
        this.idseccion = idseccion;
    }

    public Integer getIdseccion() {
        return idseccion;
    }

    public void setIdseccion(Integer idseccion) {
        this.idseccion = idseccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Resultado> getResultadoList() {
        return resultadoList;
    }

    public void setResultadoList(List<Resultado> resultadoList) {
        this.resultadoList = resultadoList;
    }

    public Pagina getIdpagina() {
        return idpagina;
    }

    public void setIdpagina(Pagina idpagina) {
        this.idpagina = idpagina;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idseccion != null ? idseccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seccion)) {
            return false;
        }
        Seccion other = (Seccion) object;
        if ((this.idseccion == null && other.idseccion != null) || (this.idseccion != null && !this.idseccion.equals(other.idseccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.siuden.analyzer.domain.Seccion[ idseccion=" + idseccion + " ]";
    }
    
}
