package a5tests;


import l3_da.DaAufgabe;
import l3_da.DaFactoryForJPA;
import l3_da.DaSchritt;
import l3_da.DaVorhaben;
import org.junit.Test;

import static org.junit.Assert.*;

public class DaFactoryTest {

    //transaktionenn fehlen!

    protected static final DaFactoryForJPA dff = new DaFactoryForJPA();

    @Test
    public void testGetAufgabeDA() throws Exception {
        DaAufgabe a = dff.getAufgabeDA();
        DaAufgabe b = dff.getAufgabeDA();
        assertNotNull(a);
        assertTrue(a.equals(b));
    }

    @Test
    public void testGetSchrittDA() throws Exception {
        DaSchritt a = dff.getSchrittDA();
        DaSchritt b = dff.getSchrittDA();
        assertNotNull(a);
        assertTrue(a.equals(b));
    }

    @Test
    public void testGetVorhabenDA() throws Exception {
        DaVorhaben a = dff.getVorhabenDA();
        DaVorhaben b = dff.getVorhabenDA();
        assertNotNull(a);
        assertTrue(a.equals(b));
    }
}