package a5tests;

import static org.junit.Assert.*;

import l3_da.DaGeneric;
import l3_da.DaGenericImpl;
import l4_dm.DmSchritt;
import org.junit.Test;
import org.junit.runners.Parameterized;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DaGenericImplTest {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("aufgabenplaner");
    private static final EntityManager entityManager = entityManagerFactory.createEntityManager();
    protected final DaGenericImpl<DmSchritt> generic = new DaGenericImpl<>(DmSchritt.class, entityManager);
    protected EntityTransaction transaction = entityManager.getTransaction();
    protected DmSchritt schritt = new DmSchritt();

    public DaGenericImplTest() {
        schritt.setTitel("title");
        transaction.begin();
    }

    @Test
    public void testSave() throws Exception {
        generic.save(schritt);
        assertTrue(entityManager.contains(schritt));
    }

    @Test
    public void testDelete() throws Exception {
        generic.save(schritt);
        assertTrue(entityManager.contains(schritt));
        generic.delete(schritt);
        assertFalse(entityManager.contains(schritt));
    }

    @Test
    public void testFind() throws Exception {
        generic.save(schritt);
        assertEquals(schritt, generic.find(schritt.getId()));
    }

    public List<DmSchritt> getDmSchrittList(int count) {
        List<DmSchritt> list = new ArrayList<DmSchritt>();
        for(int i = 0 ; i < count; i++) {
            DmSchritt schritt = new DmSchritt();
            schritt.setIstStunden(10);
            schritt.setTitel("shizzle" + i);
            generic.save(schritt);
            list.add(schritt);
//            System.out.println(i + ": " + schritt.getTitel());
        }
        return list;
    }

    @Test
    public void testFindAll() throws Exception {
        List<DmSchritt> list = getDmSchrittList(10);
        List<DmSchritt> found = generic.findAll();
//        for(DmSchritt f : found){
//            System.out.println( f.getTitel());
//        }
        assertEquals(list, found);
    }

    @Test
    public void testFindByField() throws Exception {
        List<DmSchritt> list = getDmSchrittList(10);
        List<DmSchritt> found = generic.findByField("id", 5);
        assertEquals(list.get(4), found.get(0));
        assertEquals(list,generic.findByField("istStunden",10));
    }

    @Test
    public void testFindByWhere() throws Exception {

    }

    @Test
    public void testFindByExample() throws Exception {
//        List<DmSchritt> list = getDmSchrittList(10);
//        generic.save(schritt);
//        List<DmSchritt> found = generic.findByExample(schritt);
//        for(DmSchritt s : found){
//            assertEquals(s,schritt);
//        }

    }
}
