package ar.com.siuden.analyzer.service.impl;

import ar.com.siuden.analyzer.domain.Palabra;
import ar.com.siuden.analyzer.repository.PalabraDao;
import ar.com.siuden.analyzer.service.PalabraService;
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
@Service("palabraService")
@Transactional
public class PalabraServiceImpl implements PalabraService {
    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private PalabraDao palabraDao;    

    @Override
    public void save(Palabra palabra) {
        try {
            palabraDao.save(palabra);
        } catch (RuntimeException ex) {
            logger.error(getClass().getName() + "Metodo save " + ex);
            throw ex;
        }
    }

    @Override
    public void update(Palabra palabra) {
        try {
            palabraDao.update(palabra);
        } catch (RuntimeException ex) {
            logger.error(getClass().getName() + "Metodo upate " + ex);
            throw ex;
        }
    }

    @Override
    public void delete(Palabra palabra) {
        try {
            palabraDao.delete(palabra);
        } catch (RuntimeException ex) {
            logger.error(getClass().getName() + "Metodo delete " + ex);
            throw ex;
        }
    }

    /**
     * @param idPalabra
     */
    public Palabra get(int idPalabra) {
        try {
            return palabraDao.get(idPalabra);
        } catch (RuntimeException ex) {
            logger.error(getClass().getName() + "Metodo get " + ex);
            throw ex;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Palabra> getAll() {
        try {
            return palabraDao.getAll();
        } catch (RuntimeException ex) {
            logger.error(getClass().getName() + "Metodo getAll() " + ex);
            throw ex;
        }
    }
}
