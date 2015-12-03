package l3_da;

/**Eine Factory f�r typisierte Data Access Objects. Teuer zu erzeugen. Kapselt z.B. eine JPA-EntityManagerFactory.*/
public interface DaFactory {
	
	/**Liefert ein Data Access Object f�r polymorphen Zugriff auf Aufgaben.*/
	DaAufgabe getAufgabeDA();	

	/**Liefert ein Data Access Object f�r Schritte.*/
	DaSchritt getSchrittDA();

	/**Liefert ein Data Access Object f�r Schritte.*/
	DaVorhaben getVorhabenDA();
	
	/**Startet eine Datenbanktransaktion. Es d�rfen nicht mehrere Transaktionen in derselben DaFactory offen sein.*/
	void beginTransaction();

	/**Beendet die in dieser DaFactory laufende Datenbanktransaktion. Wenn true �bergeben wird, wird commit, andernfalls rollback ausgef�hrt.*/
	void endTransaction(boolean ok);
	
}