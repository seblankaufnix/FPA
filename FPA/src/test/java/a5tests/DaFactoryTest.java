package a5tests;


import l3_da.DaAufgabe;
import l3_da.DaFactoryForJPA;
import l3_da.DaSchritt;
import l3_da.DaVorhaben;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mio on 02/12/2015.
 */
public class DaFactoryTest {

    protected DaFactoryForJPA dff = new DaFactoryForJPA();

    @Test
    public void testGetAufgabeDA() throws Exception {
        DaAufgabe a = dff.getAufgabeDA();
        DaAufgabe b = dff.getAufgabeDA();
        assertTrue(a.equals(b));
    }

    @Test
    public void testGetSchrittDA() throws Exception {
        DaSchritt a = dff.getSchrittDA();
        DaSchritt b = dff.getSchrittDA();
        assertTrue(a.equals(b));
    }

    @Test
    public void testGetVorhabenDA() throws Exception {
        DaVorhaben a = dff.getVorhabenDA();
        DaVorhaben b = dff.getVorhabenDA();
        assertTrue(a.equals(b));
    }
}