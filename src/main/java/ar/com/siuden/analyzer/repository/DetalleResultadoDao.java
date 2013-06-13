package ar.com.siuden.analyzer.repository;

import ar.com.siuden.analyzer.domain.DetalleResultado;
import java.util.List;

/**
 *
 * @author juliozilman
 * @fecha 4-5-2013
 *
 */
public interface DetalleResultadoDao {

    /**
     * @param detalleresultado
     */
    void save(DetalleResultado detalleresultado);

    /**
     * @param detalleresultado
     */
    void update(DetalleResultado detalleresultado);

    /**
     * @param detalleresultado
     */
    void delete(DetalleResultado detalleresultado);

    /**
     * @param idDetalleResultado
     */
    DetalleResultado get(int idDetalleResultado);

    /**
     * return List<DetalleResultado>
     */
    List<DetalleResultado> getAll();

    DetalleResultado getByResultadoPalabra(Integer idResultado, Integer idPalabra);

    boolean existResultadoPalabra(Integer idResultado, Integer idPalabra);
}
