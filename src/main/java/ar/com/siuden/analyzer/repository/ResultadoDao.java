package ar.com.siuden.analyzer.repository;

import ar.com.siuden.analyzer.domain.Resultado;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

 /**
  *
  * @author juliozilman 
  * @fecha 4-5-2013
  *
  */
public interface ResultadoDao {
	/**
	* @param resultado
	*/
	void save(Resultado resultado);
	/**
	* @param resultado
	*/
	void update(Resultado resultado);
	/**
	* @param resultado
	*/
	void delete(Resultado resultado);
	/**
	* @param idResultado
	*/
	Resultado get(int idResultado);
	/**
	* return List<Resultado>
	*/
	List<Resultado> getAll();	
        
        /**
         * 
         * @param linkNota
         * @return 
         */
        Resultado getByLinkNota(String linkNota);

        List<Resultado> getByFirt();

        /**
         * 
         * @param fechaDesde
         * @param fechaHasta
         * @param idSeccion
         * @return 
         */
        List<Resultado> getByDateSection(Date fechaDesde, Date fechaHasta, int idSeccion);
        
        /**
         * 
         * @param linkNota
         * @return 
         */
        boolean existLinkNota(String linkNota);
}
