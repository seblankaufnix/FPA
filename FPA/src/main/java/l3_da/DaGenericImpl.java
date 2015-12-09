package l3_da;

import l4_dm.DmAufgabe;
import multex.MultexUtil;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seby on 03.12.2015.
 */
public class DaGenericImpl<E> implements DaGeneric<E> {
    protected final EntityManager em;
    protected final Class<E> c;

    public DaGenericImpl(Class<E> c, EntityManager em) {
        this.c = c;
        this.em = em;
    }

    @Override
    public boolean save(E entity) {
        //falls keine ID, persist(), else merge()
        if(!em.contains(entity)){
            em.persist(entity);
        }
        return em.contains(entity);
    }

    @Override
    public void delete(E entity) {
        em.remove(entity);
    }

    @Override
    public E find(long id) throws IdNotFoundExc {
        E found = em.find(c, id);
        if(found != null){
            return found;
        }else{
            throw MultexUtil.create(IdNotFoundExc.class, c.getSimpleName(), id);
        }
    }

    @Override
    public List<E> findAll() {
        final TypedQuery<E> q = em.createQuery("SELECT t FROM " + c.getSimpleName() + " t ", c);
        return q.getResultList();
    }

    @Override
    public List<E> findByField(String fieldName, Object fieldValue) {
        Query q = em.createQuery("SELECT t FROM " + c.getSimpleName() + " t WHERE t." + fieldName
                + " = " + fieldValue);
        return q.getResultList();
    }

    @Override
    public List<E> findByWhere(String whereClause, Object... args) {
        throw new NotImplementedException();
    }

    @Override
    public List<E> findByExample(E example) {
        List<E> all = findAll();
        List<E> found = null;
        for(E e : all){
            if(e.equals(example)){
                found.add(e);
            }
        }
        return found;
    }
}
