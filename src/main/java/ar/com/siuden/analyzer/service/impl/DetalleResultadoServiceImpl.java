package ar.com.siuden.analyzer.service.impl;

import ar.com.siuden.analyzer.domain.DetalleResultado;
import ar.com.siuden.analyzer.repository.DetalleResultadoDao;
import ar.com.siuden.analyzer.service.DetalleResultadoService;
import java.util.*;
import javax.swing.JOptionPane;
import org.apache.commons.logging.*;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author juliozilman
 * @fecha 4-5-2013
 *
 */
@Service("detalleresultadoService")
@Transactional
public class DetalleResultadoServiceImpl implements DetalleResultadoService {

    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private DetalleResultadoDao detalleresultadoDao;

    @Override
    public void save(DetalleResultado detalleresultado) {
        try {
            detalleresultadoDao.save(detalleresultado);
        } catch (RuntimeException ex) {
            logger.error(getClass().getName() + "Metodo save " + ex);
            throw ex;
        }
    }

    @Override
    public void update(DetalleResultado detalleresultado) {
        try {
            detalleresultadoDao.update(detalleresultado);
        } catch (RuntimeException ex) {
            logger.error(getClass().getName() + "Metodo upate " + ex);
            throw ex;
        }
    }

    @Override
    public void delete(DetalleResultado detalleresultado) {
        try {
            detalleresultadoDao.delete(detalleresultado);
        } catch (RuntimeException ex) {
            logger.error(getClass().getName() + "Metodo delete " + ex);
            throw ex;
        }
    }

    /**
     * @param idDetalleResultado
     */
    public DetalleResultado get(int idDetalleResultado) {
        try {
            return detalleresultadoDao.get(idDetalleResultado);
        } catch (RuntimeException ex) {
            logger.error(getClass().getName() + "Metodo get " + ex);
            throw ex;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<DetalleResultado> getAll() {
        try {
            return detalleresultadoDao.getAll();
        } catch (RuntimeException ex) {
            logger.error(getClass().getName() + "Metodo getAll() " + ex);
            throw ex;
        }
    }

    public DetalleResultado getByResultadoPalabra(Integer idResultado, Integer idPalabra) {
        try {
            return detalleresultadoDao.getByResultadoPalabra(idResultado,idPalabra);
        } catch (RuntimeException ex) {
            logger.error(getClass().getName() + "Metodo get " + ex);
            throw ex;
        }
    }

    @Override
    public boolean existResultadoPalabra(Integer idResultado, Integer idPalabra) {
         try {
            return detalleresultadoDao.existResultadoPalabra(idResultado,idPalabra);
        } catch (RuntimeException ex) {
            logger.error(getClass().getName() + "Metodo existResultadoPalabra " + ex);
            throw ex;
        }
    }
}
