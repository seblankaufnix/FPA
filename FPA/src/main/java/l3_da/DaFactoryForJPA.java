package l3_da;

import javax.persistence.*;

/**
 * Created by Seby on 03.12.2015.
 */
public class DaFactoryForJPA implements DaFactory {
    private final EntityManagerFactory emf;
    private final EntityManager em;
    private EntityTransaction t;
    private final DaAufgabeImpl da;
    private final DaSchrittImpl ds;
    private final DaVorhabenImpl dv;

    public DaFactoryForJPA() {
        emf = javax.persistence.Persistence.createEntityManagerFactory("aufgabenplaner");
        em = emf.createEntityManager();
        t = em.getTransaction();

        da = new DaAufgabeImpl(em);
        ds = new DaSchrittImpl(em);
        dv = new DaVorhabenImpl(em);
    }

    @Override
    public DaAufgabe getAufgabeDA() {
        return da;
    }

    @Override
    public DaSchritt getSchrittDA() {
        return ds;
    }

    @Override
    public DaVorhaben getVorhabenDA() {
        return dv;
    }

    @Override
    public void beginTransaction() {
        t.begin();
    }

    @Override
    public void endTransaction(boolean ok) {
        if(ok){
            t.commit();
        }else{
            t.rollback();
        }
        em.clear();
        em.close();
    }
}
