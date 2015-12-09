package l2_lg;

import l3_da.DaAufgabe;
import l3_da.DaFactory;
import l3_da.DaSchritt;
import l3_da.DaVorhaben;
import l4_dm.DmAufgabe;
import l4_dm.DmSchritt;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Seby on 09.12.2015.
 */
public class LgSessionImpl implements LgSession {

    protected final DaFactory factory;
    protected final DaSchritt schritt;
    protected final DaAufgabe aufgabe;
    protected final DaVorhaben vorhaben;

    public LgSessionImpl(DaFactory factory) {
        this.factory = factory;
        schritt = factory.getSchrittDA();
        aufgabe = factory.getAufgabeDA();
        vorhaben = factory.getVorhabenDA();
    }


    @Override
    public <A extends DmAufgabe> A speichern(A aufgabe) throws TitelExc, RestStundenExc, IstStundenExc, EndTerminExc, VorhabenRekursionExc {
        factory.getAufgabeDA().save(aufgabe);
        return aufgabe;
    }

    @Override
    public DmSchritt schrittErledigen(DmSchritt schritt) throws TitelExc, IstStundenExc {
        schritt.setRestStunden(0);
        schritt.setErledigtZeitpunkt(new Date(Calendar.getInstance().getTimeInMillis()));
        aufgabe.save(schritt);
        return schritt;
    }

    @Override
    public List<DmAufgabe> alleOberstenAufgabenLiefern() {
        return null;
    }
}
