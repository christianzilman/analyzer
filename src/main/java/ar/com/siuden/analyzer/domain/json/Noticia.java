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
public class Noticia {
    @SerializedName("notas")
    private List<Nota> notas;

    public Noticia() {
    }

    /**
     * @return the notas
     */
    public List<Nota> getNotas() {
        return notas;
    }

    /**
     * @param notas the notas to set
     */
    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }
}
