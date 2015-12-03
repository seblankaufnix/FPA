package l3_da;

import l4_dm.DmSchritt;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Seby on 03.12.2015.
 */
public class DaSchrittImpl implements DaSchritt {

    protected DaGeneric<DmSchritt> gen;

    public DaSchrittImpl(EntityManager em) {
        gen = new DaGenericImpl<>(DmSchritt.class, em);
    }

    @Override
    public boolean save(DmSchritt entity) {
        return gen.save(entity);
    }

    @Override
    public void delete(DmSchritt entity) {
        gen.delete(entity);
    }

    @Override
    public DmSchritt find(long id) throws IdNotFoundExc {
        return gen.find(id);
    }

    @Override
    public List<DmSchritt> findAll() {
        return gen.findAll();
    }

    @Override
    public List<DmSchritt> findByField(String fieldName, Object fieldValue) {
        return findByField(fieldName, fieldValue);
    }

    @Override
    public List<DmSchritt> findByWhere(String whereClause, Object... args) {
        return findByWhere(whereClause, args);
    }

    @Override
    public List<DmSchritt> findByExample(DmSchritt example) {
        return findByExample(example);
    }
}
