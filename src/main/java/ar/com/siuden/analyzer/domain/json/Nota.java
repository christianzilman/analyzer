/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.siuden.analyzer.domain.json;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 *
 * @author juliozilman
 */
public class Nota {
    @SerializedName("nota")
    private List<Informacion> nota;
    
    public Nota() {
    }

    /**
     * @return the nota
     */
    public List<Informacion> getNota() {
        return nota;
    }

    /**
     * @param nota the nota to set
     */
    public void setNota(List<Informacion> nota) {
        this.nota = nota;
    }

  
}
