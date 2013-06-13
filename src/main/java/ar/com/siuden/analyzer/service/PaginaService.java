package ar.com.siuden.analyzer.service;

import ar.com.siuden.analyzer.domain.Pagina;
import java.util.*; 
/**
 *
 * @author juliozilman
 */
public interface PaginaService {
	/**
	* @param pagina
	*/
	void save(Pagina pagina);
	/**
	* @param pagina
	*/
	void update(Pagina pagina);
        /**
	* @param palabra
	*/
	void delete(Pagina pagina);
	/**
	* @param idPagina
	*/
	Pagina get(int idPagina);
	/**
	* return List<Pagina>
	*/
	List<Pagina> getAll();
}