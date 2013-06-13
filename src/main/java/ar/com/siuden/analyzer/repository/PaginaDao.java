package ar.com.siuden.analyzer.repository;

import ar.com.siuden.analyzer.domain.Pagina;
import java.util.List;

/**
 *
 * @author juliozilman
 */
public interface PaginaDao {
	/**
	* @param pagina
	*/
	void save(Pagina pagina);
	/**
	* @param pagina
	*/
	void update(Pagina pagina);
	/**
	* @param pagina
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
