package ar.com.siuden.analyzer.service.impl;

import ar.com.siuden.analyzer.domain.Pagina;
import ar.com.siuden.analyzer.repository.PaginaDao;
import ar.com.siuden.analyzer.service.PaginaService;
import java.util.*; 
import org.apache.commons.logging.*; 
import org.hibernate.*; 
import org.hibernate.criterion.*; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 

@Service("paginaService")
@Transactional
public class PaginaServiceImpl implements PaginaService { 

   protected final Log logger = LogFactory.getLog(getClass());
   @Autowired
   private PaginaDao paginaDao; 

   @Override
   public void save(Pagina pagina) {
     try{
      paginaDao.save(pagina);
     }
     catch(RuntimeException ex){
      logger.error(getClass().getName()+ "Metodo save " + ex);
      throw ex;
     }
   }
   @Override
   public void update(Pagina pagina) {
     try{
      paginaDao.update(pagina);
     }
     catch(RuntimeException ex){
      logger.error(getClass().getName()+ "Metodo upate " + ex);
      throw ex;
     }
   }
   @Override
   public void delete(Pagina pagina) {
     try{
      paginaDao.delete(pagina);
     }
     catch(RuntimeException ex){
      logger.error(getClass().getName()+ "Metodo delete " + ex);
      throw ex;
     }
   }
   /**
   *@param idPagina
   */
   public Pagina get(int idPagina) {
     try{
      return paginaDao.get(idPagina);
     }
     catch(RuntimeException ex){
      logger.error(getClass().getName()+ "Metodo get " + ex);
      throw ex;
     }
    }
   @Transactional(readOnly=true)
   @Override
   public List<Pagina> getAll() {
     try{
      return paginaDao.getAll();
     }
     catch(RuntimeException ex){
      logger.error(getClass().getName()+ "Metodo getAll() " + ex);
      throw ex;
     }
   }  
}
