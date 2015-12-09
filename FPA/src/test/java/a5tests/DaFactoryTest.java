package a5tests;


import l3_da.DaAufgabe;
import l3_da.DaFactoryForJPA;
import l3_da.DaSchritt;
import l3_da.DaVorhaben;
import org.junit.Test;

import static org.junit.Assert.*;

public class DaFactoryTest {

    //transaktionenn fehlen!

    protected static DaFactoryForJPA fac = new DaFactoryForJPA();

    @Test
    public void testTransactionStates() throws Exception {
        assertFalse(fac.getTransactionState());
        fac.beginTransaction();
        assertTrue(fac.getTransactionState());
        fac.endTransaction(true);
        assertFalse(fac.getTransactionState());
    }

    @Test
    public void testGetAufgabeDA() throws Exception {
        DaAufgabe a = fac.getAufgabeDA();
        DaAufgabe b = fac.getAufgabeDA();
        assertNotNull(a);
        assertTrue(a.equals(b));
    }

    @Test
    public void testGetSchrittDA() throws Exception {
        DaSchritt a = fac.getSchrittDA();
        DaSchritt b = fac.getSchrittDA();
        assertNotNull(a);
        assertTrue(a.equals(b));
    }

    @Test
    public void testGetVorhabenDA() throws Exception {
        DaVorhaben a = fac.getVorhabenDA();
        DaVorhaben b = fac.getVorhabenDA();
        assertNotNull(a);
        assertTrue(a.equals(b));
    }
}