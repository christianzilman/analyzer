package ar.com.siuden.analyzer.service;

import ar.com.siuden.analyzer.domain.Seccion;
import java.util.*; 
 /**
  *
  * @author juliozilman 
  * @fecha 4-5-2013
  *
  */
public interface SeccionService {
	/**
	* @param seccion
	*/
	void save(Seccion seccion);
	/**
	* @param seccion
	*/
	void update(Seccion seccion);
	/**
	* @param seccion
	*/
	void delete(Seccion seccion);
	/**
	* @param idSeccion
	*/
	Seccion get(int idSeccion);
	/**
	* return List<Seccion>
	*/
	List<Seccion> getAll();		
}
