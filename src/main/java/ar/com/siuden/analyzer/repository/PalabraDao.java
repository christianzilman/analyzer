package ar.com.siuden.analyzer.repository;

import ar.com.siuden.analyzer.domain.Palabra;
import java.util.List;
/**
 * @author juliozilman
 * @fecha 4-5-2013
 *
 */
public interface PalabraDao {

    /**
     * @param palabra
     */
    void save(Palabra palabra);

    /**
     * @param palabra
     */
    void update(Palabra palabra);

    /**
     * @param palabra
     */
    void delete(Palabra palabra);

    /**
     * @param idPalabra
     */
    Palabra get(int idPalabra);

    /**
     * return List<Palabra>
     */
    List<Palabra> getAll();
   
}
