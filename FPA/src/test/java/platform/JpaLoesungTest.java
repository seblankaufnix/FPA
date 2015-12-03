package platform;

import l4_dm.DmAufgabe;
import l4_dm.DmSchritt;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Logger;

/**
 * Created by Sebastian on 20.11.2015.
 */
public class JpaLoesungTest {

    private final String persistenceUnitName = "aufgabenplaner";
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Test
    public void testPersist() throws Exception{
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        DmSchritt schritt = new DmSchritt();
        schritt.setTitel("Post abholen");
        entityManager.persist(schritt);
        assertEquals(true, entityManager.contains(schritt));
        transaction.commit();
        entityManager.close();
    }

    @Test
    public void testRollback() throws Exception{
        final String title = "Ein problematischer Schritt";
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        final DmSchritt schritt = new DmSchritt();
        schritt.setTitel(title);
        entityManager.persist(schritt);
        final Long idOfFailed = schritt.getId();
        transaction.rollback();
        entityManager.close();
        final EMTransaction tx = new EMTransaction();
        final DmSchritt schritt2 = tx.em.find(DmSchritt.class, idOfFailed);
        assertNull(schritt2);
        tx.close(true);
    }

    @Test
    public void testChange() throws Exception{
        final String title = "title";
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        final DmSchritt schritt = new DmSchritt();
        schritt.setTitel(title);
        entityManager.persist(schritt);
        schritt.setRestStunden(0);
        schritt.setIstStunden(2);
        transaction.commit();
        entityManager.close();
        final long schrittId = schritt.getId();
        schritt.setIstStunden(1000);
        final EMTransaction tx = new EMTransaction();
        final DmSchritt schrittNew = tx.em.find(DmSchritt.class, schrittId);
        assertEquals(title, schrittNew.getTitel());
        assertEquals(0, schrittNew.getRestStunden());
        assertEquals(2, schrittNew.getIstStunden());
    }

    @Test
    public void testMergeAndDetach() throws Exception{
        final DmSchritt schritt;
        final String title = "title";
        final EMTransaction transaction = new EMTransaction();
        final DmSchritt schrittNew = new DmSchritt();
        schrittNew.setTitel(title);
        transaction.em.persist(schrittNew);
        transaction.close(true);
        final Long id = schrittNew.getId();
        {
            final EMTransaction tx = new EMTransaction();
            schritt = tx.em.find(DmSchritt.class, id);
            tx.close(true);
        }
        schritt.setRestStunden(50); //wird nicht mehr gespeichert, weil detached
        {
            final EMTransaction tx = new EMTransaction();
            final DmSchritt schritt1 = tx.em.find(DmSchritt.class, id);
            assertNotEquals(50, schritt1.getRestStunden());
        }
        {
            final EMTransaction tx = new EMTransaction();
            tx.em.merge(schritt);
            tx.close(true);
        }
        {
            final EMTransaction tx = new EMTransaction();
            final DmSchritt schritt1 = tx.em.find(DmSchritt.class,id);
            assertEquals(50, schritt1.getRestStunden());
        }
    }

    @Test
    public void testDelete() throws Exception{
        String title = "title";
        final EMTransaction tx = new EMTransaction();
        final DmSchritt schritt = new DmSchritt();
        schritt.setTitel(title);
        tx.em.persist(schritt);
        tx.close(true);
        long schrittID = schritt.getId();
        final EMTransaction tx1 = new EMTransaction();
        DmSchritt schritt1 = tx1.em.find(DmSchritt.class,schrittID);
        tx1.em.remove(schritt1);
        assertEquals(false, tx1.em.contains(schritt));
        tx1.close(true);
    }

    /**Kapselt einen {@link EntityManager} mit einem "transaction-scoped persistence context" und einer {@link EntityTransaction} und startet diese Transaktion.
     * Zum Vorgehen bei manueller Transaktionsverwaltung siehe https://docs.jboss.org/hibernate/entitymanager/3.5/reference/en/html/transactions.html#transactions-demarcation-nonmanaged
     */
    private class EMTransaction {
        /**Der {@link EntityManager} mit Transaction Scope f?r die weiteren Datenzugriffsoperationen.*/
        public final EntityManager em = entityManagerFactory.createEntityManager();
        private final EntityTransaction transaction = em.getTransaction();
        {
            transaction.begin();
        }
        /**Schlie?t die {@link EMTransaction} mit entweder commit oder rollback.
         * @param ok die Transaktion war bis zum Ende fehlerfrei => commit, andernfalls rollback.
         */
        public void close(final boolean ok){
            try{
                if(!transaction.isActive())return;
                if(ok){
                    transaction.commit();
                } else {
                    transaction.rollback();
                }
            }finally{ //wird auch bei Erfolg oder return ausgef?hrt!
                em.close();
            }
        }
    }
}
