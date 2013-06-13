package ar.com.siuden.analyzer.service;

import ar.com.siuden.analyzer.domain.Resultado;
import java.util.*; 

 /**
  *
  * @author juliozilman 
  * @fecha 4-5-2013
  *
  */
public interface ResultadoService {
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

        List<Resultado> getByDateSection(Date fechaDesde, Date fechaHasta, int idSeccion);

        boolean existLinkNota(String link);
}
