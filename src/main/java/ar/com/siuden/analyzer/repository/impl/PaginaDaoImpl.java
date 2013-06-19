package ar.com.siuden.analyzer.repository.impl;

import ar.com.siuden.analyzer.domain.Pagina;
import ar.com.siuden.analyzer.repository.PaginaDao;
import java.util.*; 
import org.apache.commons.logging.*; 
import org.hibernate.*; 
import org.hibernate.criterion.*; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Repository; 
/**
 *
 * @author juliozilman
 */
@Repository
public class PaginaDaoImpl implements PaginaDao { 

   protected final Log logger = LogFactory.getLog(getClass());
   @Autowired
   private SessionFactory sessionFactory;
   

   @Override
   public void save(Pagina pagina) {
    Session session = sessionFactory.getCurrentSession();
    session.save(pagina);
   }
   public @Override
   void update(Pagina pagina) {
    Session session = sessionFactory.getCurrentSession();
    session.update(pagina);
   }
   
   public void delete(Pagina pagina) {
    Session session = sessionFactory.getCurrentSession();
    session.delete(pagina);
   }
   /**
   *@param idPagina
   */
   public Pagina get(int idPagina) {
    Session session = sessionFactory.getCurrentSession();
    Criteria criteria = session.createCriteria(Pagina.class);
    criteria.add(Restrictions.eq("idPagina", idPagina));
    return (Pagina) criteria.uniqueResult();
   }
   
   public List<Pagina> getAll() {
    Session session = sessionFactory.getCurrentSession();
    Criteria criteria = session.createCriteria(Pagina.class);
    return criteria.list();
   }
 
}
