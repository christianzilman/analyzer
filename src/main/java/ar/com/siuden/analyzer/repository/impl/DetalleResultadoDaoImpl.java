package ar.com.siuden.analyzer.repository.impl;

import ar.com.siuden.analyzer.domain.DetalleResultado;
import ar.com.siuden.analyzer.repository.DetalleResultadoDao;
import java.util.*; 
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
public class DetalleResultadoDaoImpl implements DetalleResultadoDao { 

   protected final Log logger = LogFactory.getLog(getClass());
   @Autowired
   private SessionFactory sessionFactory;   

   @Override
   public void save(DetalleResultado detalleresultado) {
    Session session = sessionFactory.getCurrentSession();
    session.save(detalleresultado);
   }
   @Override
   public void update(DetalleResultado detalleresultado) {
    Session session = sessionFactory.getCurrentSession();
    session.update(detalleresultado);
   }
   @Override
   public void delete(DetalleResultado detalleresultado) {
    Session session = sessionFactory.getCurrentSession();
    session.delete(detalleresultado);
   }
   /**
   *@param idDetalleResultado
   */
   @Override
   public DetalleResultado get(int idDetalleResultado) {
    Session session = sessionFactory.getCurrentSession();
    Criteria criteria = session.createCriteria(DetalleResultado.class);
    criteria.add(Restrictions.eq("iddetalleresultado", idDetalleResultado));
    return (DetalleResultado) criteria.uniqueResult();
   }
   @Override
   public List<DetalleResultado> getAll() {
    Session session = sessionFactory.getCurrentSession();
    Criteria criteria = session.createCriteria(DetalleResultado.class);
   //criteria.addOrder(Order.desc(""))
    return criteria.list();
   } 

    public DetalleResultado getByResultadoPalabra(Integer idResultado, Integer idPalabra) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(DetalleResultado.class);
        criteria.add(Restrictions.eq("idresultado.idresultado", idResultado));
        criteria.add(Restrictions.eq("palabra.idpalabra", idPalabra));
        return (DetalleResultado) criteria.uniqueResult();
    }

    @Override
    public boolean existResultadoPalabra(Integer idResultado, Integer idPalabra) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(DetalleResultado.class);
        criteria.add(Restrictions.eq("idresultado.idresultado", idResultado));
        criteria.add(Restrictions.eq("palabra.idpalabra", idPalabra));
        criteria.setProjection(Projections.rowCount());       
        Integer totalNumberOfElements = Integer.valueOf(criteria.uniqueResult().toString());        
        return (totalNumberOfElements >0);        
    }
}
