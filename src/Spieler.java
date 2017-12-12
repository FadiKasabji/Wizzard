
public class Spieler {
	private String name;
	private static int anzahl = 0;
	private int punkte;
	Karte handKarten []= new Karte[5];

	public Spieler(String name) {
		setName(name);
		incAnzahl();
	}

	public static int getAnzahl() {
		return anzahl;
	}

	public static void incAnzahl() {
		anzahl++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int vorhersagen() {
		
		return 0;
	}

	public Karte karteLegen(Karte k) {
		return null;
	}

	public String toString() {
		return name;
	}
}
