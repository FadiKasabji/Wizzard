
public abstract class Spieler {
	private String name;
	private static int anzahl = 0;
	private int punkte = 0;
	private int vorhersage = 0;
	private int stich = 0;
	private Karte[] handKarten = new Karte[5];
	
	public int getVorhersage() {
		return vorhersage;
	}

	public void setVorhersage(int vorhersage) {
		this.vorhersage = vorhersage;
	}

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

	public Karte karteLegen() {
		return null;
	}

	public String toString() {
		return name;
	}

	public int getPunkte() {
		return punkte;
	}

	public void setPunkte(int punkte) {
		this.punkte = punkte;
	}

	public int getStich() {
		return stich;
	}

	public void setStich(int stich) {
		this.stich = stich;
	}

	public Karte[] getHandKarten() {
		return handKarten;
	}

	public void setHandKarten(Karte handKarten[]) {
		this.handKarten = handKarten;
	}
}
