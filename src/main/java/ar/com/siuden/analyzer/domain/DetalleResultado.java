/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.siuden.analyzer.domain;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;

/**
 *
 * @author juliozilman
 */
@Entity
@Table(name = "detalleresultado")
public class DetalleResultado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetalleresultado")
    private Integer iddetalleresultado;
    @Column(name = "cantidad")
    private Integer cantidad;
    @JoinColumn(name = "idpalabra")
    @ManyToOne(targetEntity=Palabra.class)    
    private Palabra palabra;
    //@JoinColumn(name = "idresultado", referencedColumnName = "idresultado")
    //@ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idresultado")
    @ManyToOne(targetEntity=Resultado.class)
    private Resultado idresultado;

    public DetalleResultado() {
    }

    public DetalleResultado(Integer iddetalleresultado) {
        this.iddetalleresultado = iddetalleresultado;
    }

    public Integer getIddetalleresultado() {
        return iddetalleresultado;
    }

    public void setIddetalleresultado(Integer iddetalleresultado) {
        this.iddetalleresultado = iddetalleresultado;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Palabra getPalabra() {
        return palabra;
    }

    public void setPalabra(Palabra palabra) {
        this.palabra = palabra;
    }

    public Resultado getResultado() {
        return idresultado;
    }

    public void setResultado(Resultado idresultado) {
        this.idresultado = idresultado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalleresultado != null ? iddetalleresultado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleResultado)) {
            return false;
        }
        DetalleResultado other = (DetalleResultado) object;
        if ((this.iddetalleresultado == null && other.iddetalleresultado != null) || (this.iddetalleresultado != null && !this.iddetalleresultado.equals(other.iddetalleresultado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.siuden.analyzer.domain.Detalleresultado[ iddetalleresultado=" + iddetalleresultado + " ]";
    }
    
}
