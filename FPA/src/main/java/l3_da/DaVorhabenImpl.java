package l3_da;

import l4_dm.DmVorhaben;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Seby on 03.12.2015.
 */
public class DaVorhabenImpl implements DaVorhaben {
    protected DaGeneric<DmVorhaben> gen;

    public DaVorhabenImpl(EntityManager em) {
        gen = new DaGenericImpl<DmVorhaben>(DmVorhaben.class, em);
    }

    @Override
    public boolean save(DmVorhaben entity) {
        return gen.save(entity);
    }

    @Override
    public void delete(DmVorhaben entity) {
        gen.delete(entity);
    }

    @Override
    public DmVorhaben find(long id) throws IdNotFoundExc {
        return gen.find(id);
    }

    @Override
    public List<DmVorhaben> findAll() {
        return gen.findAll();
    }

    @Override
    public List<DmVorhaben> findByField(String fieldName, Object fieldValue) {
        return findByField(fieldName, fieldValue);
    }

    @Override
    public List<DmVorhaben> findByWhere(String whereClause, Object... args) {
        return findByWhere(whereClause, args);
    }

    @Override
    public List<DmVorhaben> findByExample(DmVorhaben example) {
        return findByExample(example);
    }
}
