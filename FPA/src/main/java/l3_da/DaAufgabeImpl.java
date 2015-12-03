package l3_da;

import l4_dm.DmAufgabe;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Seby on 03.12.2015.
 */
public class DaAufgabeImpl implements DaAufgabe {
    protected DaGeneric<DmAufgabe> gen;

    public DaAufgabeImpl(EntityManager em) {
        gen = new DaGenericImpl<>(DmAufgabe.class, em);
    }

    @Override
    public boolean save(DmAufgabe entity) {
        return gen.save(entity);
    }

    @Override
    public void delete(DmAufgabe entity) {
        gen.delete(entity);
    }

    @Override
    public DmAufgabe find(long id) throws DaGeneric.IdNotFoundExc {
        return gen.find(id);
    }

    @Override
    public List<DmAufgabe> findAll() {
        return gen.findAll();
    }

    @Override
    public List<DmAufgabe> findByField(String fieldName, Object fieldValue) {
        return findByField(fieldName, fieldValue);
    }

    @Override
    public List<DmAufgabe> findByWhere(String whereClause, Object... args) {
        return findByWhere(whereClause, args);
    }

    @Override
    public List<DmAufgabe> findByExample(DmAufgabe example) {
        return findByExample(example);
    }
}
