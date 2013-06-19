package ar.com.siuden.analyzer.repository.impl;

import ar.com.siuden.analyzer.domain.Palabra;
import ar.com.siuden.analyzer.repository.PalabraDao;
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
public class PalabraDaoImpl implements PalabraDao { 

   protected final Log logger = LogFactory.getLog(getClass());
   @Autowired
   private SessionFactory sessionFactory;
 

   @Override
   public void save(Palabra palabra) {
    Session session = sessionFactory.getCurrentSession();
    session.save(palabra);
   }
   @Override
   public void update(Palabra palabra) {
    Session session = sessionFactory.getCurrentSession();
    session.update(palabra);
   }
   @Override
   public void delete(Palabra palabra) {
    Session session = sessionFactory.getCurrentSession();
    session.delete(palabra);
   }
   /**
   *@param idPalabra
   */
   @Override
   public Palabra get(int idPalabra) {
    Session session = sessionFactory.getCurrentSession();
    Criteria criteria = session.createCriteria(Palabra.class);
    criteria.add(Restrictions.eq("idPalabra", idPalabra));
    return (Palabra) criteria.uniqueResult();
   }
   @Override
   public List<Palabra> getAll() {
    Session session = sessionFactory.getCurrentSession();
    Criteria criteria = session.createCriteria(Palabra.class);
    return criteria.list();
   } 
}
