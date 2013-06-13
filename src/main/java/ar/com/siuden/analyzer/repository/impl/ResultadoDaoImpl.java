package ar.com.siuden.analyzer.repository.impl;

import ar.com.siuden.analyzer.domain.DetalleResultado;
import ar.com.siuden.analyzer.domain.Resultado;
import ar.com.siuden.analyzer.repository.ResultadoDao;
import java.util.*; 
import org.apache.commons.logging.*; 
import org.hibernate.*; 
import org.hibernate.criterion.*; 
import org.hibernate.service.jta.platform.internal.ResinJtaPlatform;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Repository; 

 /**
  *
  * @author juliozilman 
  * @fecha 4-5-2013
  *
  */
@Repository
public class ResultadoDaoImpl implements ResultadoDao { 

   protected final Log logger = LogFactory.getLog(getClass());
   @Autowired
   private SessionFactory sessionFactory;
 

   @Override
   public void save(Resultado resultado) {
    Session session = sessionFactory.getCurrentSession();
    session.save(resultado);
   }
   @Override
   public void update(Resultado resultado) {
    Session session = sessionFactory.getCurrentSession();
    session.update(resultado);
    //session.flush();
   }
   @Override
   public void delete(Resultado resultado) {
    Session session = sessionFactory.getCurrentSession();
    session.delete(resultado);
   }
   /**
   *@param idResultado
   */
   @Override
   public Resultado get(int idResultado) {
    Session session = sessionFactory.getCurrentSession();
    Criteria criteria = session.createCriteria(Resultado.class);
    criteria.add(Restrictions.eq("idResultado", idResultado));
    return (Resultado) criteria.uniqueResult();
   }
   @Override
   public List<Resultado> getAll() {
    Session session = sessionFactory.getCurrentSession();
    Criteria criteria = session.createCriteria(Resultado.class);
    return criteria.list();
   }
   
   public List<Resultado> getByFirt(){
     Session session = sessionFactory.getCurrentSession();
     Criteria criteria = session.createCriteria(Resultado.class);
     criteria.setFirstResult(0);
     criteria.setMaxResults(30);
     criteria.addOrder(Order.desc("idresultado"));        
     return criteria.list();
   }
   
   @Override
   public Resultado getByLinkNota(String linkNota) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Resultado.class);
        criteria.add(Restrictions.like("linknota", "%" +linkNota+ "%"));
        //criteria.setFetchMode("idresultado.iddetalleresultado", FetchMode.JOIN);
        criteria.addOrder(Order.asc("idresultado"));        
        Resultado resultado = (Resultado) criteria.uniqueResult();
        /*
        Criteria criteriaDetalle= session.createCriteria(DetalleResultado.class);
        criteriaDetalle.add(Restrictions.eq("idresultado",resultado));        
        
        List<DetalleResultado> detalleResultadoList = criteriaDetalle.list();
        resultado.setDetalleResultadoList(detalleResultadoList);       */         
        return resultado;
       
   }
   
   @Override
   public boolean existLinkNota(String linkNota) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Resultado.class);
        criteria.add(Restrictions.like("linknota", "%" +linkNota+ "%"));
        criteria.addOrder(Order.asc("idresultado"));        
        criteria.setProjection(Projections.rowCount());       
        Integer totalNumberOfElements = Integer.valueOf(criteria.uniqueResult().toString());        
        return (totalNumberOfElements >0);
   }

    public List<Resultado> getByDateSection(Date fechaDesde, Date fechaHasta, int idSeccion) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Resultado.class);
        criteria.add(Restrictions.eq("idseccion.idseccion",idSeccion));
        criteria.add(Restrictions.between("fechanota",fechaDesde,fechaHasta));
        criteria.addOrder(Order.desc("idresultado"));        
        return criteria.list();
    }
}
