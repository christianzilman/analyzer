package ar.com.siuden.analyzer.repository.impl;

import ar.com.siuden.analyzer.domain.Seccion;
import ar.com.siuden.analyzer.repository.SeccionDao;
import java.util.*; 
import javax.swing.JOptionPane; 
import org.apache.commons.logging.*; 
import org.hibernate.*; 
import org.hibernate.criterion.*; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Repository; 



 /**
  *
  * @author juliozilman 
  * @fecha 4-5-2013
  *
  */
@Repository
public class SeccionDaoImpl implements SeccionDao { 

   protected final Log logger = LogFactory.getLog(getClass());
   @Autowired
   private SessionFactory sessionFactory;
   
   @Override
   public void save(Seccion seccion) {
    Session session = sessionFactory.getCurrentSession();
    session.save(seccion);
   }
   @Override
   public void update(Seccion seccion) {
    Session session = sessionFactory.getCurrentSession();
    session.update(seccion);
   }
   @Override
   public void delete(Seccion seccion) {
    Session session = sessionFactory.getCurrentSession();
    session.delete(seccion);
   }
   /**
   *@param idSeccion
   */
   @Override
   public Seccion get(int idSeccion) {
    Session session = sessionFactory.getCurrentSession();
    Criteria criteria = session.createCriteria(Seccion.class);
    criteria.add(Restrictions.eq("idseccion", idSeccion));
    return (Seccion) criteria.uniqueResult();
   }
   @Override
   public List<Seccion> getAll() {
    Session session = sessionFactory.getCurrentSession();
    Criteria criteria = session.createCriteria(Seccion.class);
    return criteria.list();
   } 
}
