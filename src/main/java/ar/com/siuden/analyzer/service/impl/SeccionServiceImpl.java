package ar.com.siuden.analyzer.service.impl;

import ar.com.siuden.analyzer.domain.Seccion;
import ar.com.siuden.analyzer.repository.SeccionDao;
import ar.com.siuden.analyzer.service.SeccionService;
import java.util.*;
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
@Service("seccionService")
@Transactional
public class SeccionServiceImpl implements SeccionService {

    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private SeccionDao seccionDao;

    @Override
    public void save(Seccion seccion) {
        try {
            seccionDao.save(seccion);
        } catch (RuntimeException ex) {
            logger.error(getClass().getName() + "Metodo save " + ex);
            throw ex;
        }
    }

    @Override
    public void update(Seccion seccion) {
        try {
            seccionDao.update(seccion);
        } catch (RuntimeException ex) {
            logger.error(getClass().getName() + "Metodo upate " + ex);
            throw ex;
        }
    }

    @Override
    public void delete(Seccion seccion) {
        try {
            seccionDao.delete(seccion);
        } catch (RuntimeException ex) {
            logger.error(getClass().getName() + "Metodo delete " + ex);
            throw ex;
        }
    }

    /**
     * @param idSeccion
     */
    public Seccion get(int idSeccion) {
        try {
            return seccionDao.get(idSeccion);
        } catch (RuntimeException ex) {
            logger.error(getClass().getName() + "Metodo get " + ex);
            throw ex;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Seccion> getAll() {
        try {
            return seccionDao.getAll();
        } catch (RuntimeException ex) {
            logger.error(getClass().getName() + "Metodo getAll() " + ex);
            throw ex;
        }
    }
}
