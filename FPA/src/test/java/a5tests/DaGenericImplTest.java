package a5tests;

import static org.junit.Assert.*;

import l3_da.*;
import l4_dm.DmSchritt;
import org.junit.Test;
import org.junit.runners.Parameterized;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DaGenericImplTest {
    private static final DaFactory fac = new DaFactoryForJPA();
    protected final DaGeneric<DmSchritt> generic = fac.getSchrittDA();
    protected DmSchritt schritt = new DmSchritt();


    //nur DA schicht verwenden!
    public DaGenericImplTest() {
        schritt.setTitel("title");
        fac.beginTransaction();
    }

    @Test
    public void testSave() throws Exception {
        generic.save(schritt);
        assertEquals(schritt, generic.find(schritt.getId()));
        fac.endTransaction(true);
    }

    @Test
    public void testDelete() throws Exception {
        generic.save(schritt);
        generic.delete(schritt);
        try {
            // should throw an IdNotFoundExc
            generic.find(schritt.getId());
            // fails if IdNotFoundExc not thrown
            fail();
        } catch (DaGeneric.IdNotFoundExc e) {
            // test passed, if IdNotFoundExc catched
            assert(true);
        } finally {
            fac.endTransaction(true);
        }
    }

    @Test
    public void testFind() throws Exception {
        generic.save(schritt);
        assertEquals(schritt, generic.find(schritt.getId()));
        DmSchritt deleted = new DmSchritt();
        deleted.setTitel("another title");
        generic.save(deleted);
        Long deletedId = deleted.getId();
        generic.delete(deleted);
        try {
            generic.find(deletedId);
            fail();
        } catch (DaGeneric.IdNotFoundExc e) {
            assert(true);
        } finally {
            fac.endTransaction(true);
        }
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
        assertEquals(list.size(),found.size());
        for(DmSchritt s : found){
            if(!found.contains(s)) fail();
        }
        fac.endTransaction(true);
    }

//    @Test
//    public void testFindByField() throws Exception {
//        List<DmSchritt> list = getDmSchrittList(10);
//        List<DmSchritt> found = generic.findByField("id", 5);
//        assertTrue(list.get(4).equals(found.get(0)));
//        assertEquals(list,generic.findByField("istStunden",10));
//    }
//
//    @Test
//    public void testFindByWhere() throws Exception {
//
//    }
//
//    @Test
//    public void testFindByExample() throws Exception {
////        List<DmSchritt> list = getDmSchrittList(10);
////        generic.save(schritt);
////        List<DmSchritt> found = generic.findByExample(schritt);
////        for(DmSchritt s : found){
////            assertEquals(s,schritt);
////        }
//
//    }
}
