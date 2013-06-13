package ar.com.siuden.analyzer.service.impl;

import ar.com.siuden.analyzer.domain.Resultado;
import ar.com.siuden.analyzer.repository.ResultadoDao;
import ar.com.siuden.analyzer.service.ResultadoService;
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
@Service("resultadoService")
@Transactional
public class ResultadoServiceImpl implements ResultadoService {

    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private ResultadoDao resultadoDao;

    @Override
    public void save(Resultado resultado) {
        try {
            resultadoDao.save(resultado);
        } catch (RuntimeException ex) {
            logger.error(getClass().getName() + "Metodo save " + ex);
            throw ex;
        }
    }

    @Override
    public void update(Resultado resultado) {
        try {
            resultadoDao.update(resultado);
        } catch (RuntimeException ex) {
            logger.error(getClass().getName() + "Metodo upate " + ex);
            throw ex;
        }
    }

    @Override
    public void delete(Resultado resultado) {
        try {
            resultadoDao.delete(resultado);
        } catch (RuntimeException ex) {
            logger.error(getClass().getName() + "Metodo delete " + ex);
            throw ex;
        }
    }

    /**
     * @param idResultado
     */
    public Resultado get(int idResultado) {
        try {
            return resultadoDao.get(idResultado);
        } catch (RuntimeException ex) {
            logger.error(getClass().getName() + "Metodo get " + ex);
            throw ex;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Resultado> getAll() {
        try {
            return resultadoDao.getAll();
        } catch (RuntimeException ex) {
            logger.error(getClass().getName() + "Metodo getAll() " + ex);
            throw ex;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Resultado getByLinkNota(String linkNota) {
        try {
            return resultadoDao.getByLinkNota(linkNota);
        } catch (RuntimeException ex) {
            logger.error(getClass().getName() + "Metodo getByLinkNota(String linkNota) " + ex);
            throw ex;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existLinkNota(String linkNota) {
        try {
            return resultadoDao.existLinkNota(linkNota);
        } catch (RuntimeException ex) {
            logger.error(getClass().getName() + "Metodo getByLinkNota(String linkNota) " + ex);
            throw ex;
        }
    }

    public List<Resultado> getByFirt() {
        try {
            return resultadoDao.getByFirt();
        } catch (RuntimeException ex) {
            logger.error(getClass().getName() + "Metodo getAll() " + ex);
            throw ex;
        }
    }

    public List<Resultado> getByDateSection(Date fechaDesde, Date fechaHasta, int idSeccion) {
        try {
            return resultadoDao.getByDateSection(fechaDesde,fechaHasta,idSeccion);
        } catch (RuntimeException ex) {
            logger.error(getClass().getName() + "Metodo getAll() " + ex);
            throw ex;
        }
    }
}
