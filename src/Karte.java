
public class Karte {
	private Farbe farbe;
	private int zahl;
	public Karte(Farbe farbe, int zahl) {
		setFarbe(farbe);
		setZahl(zahl);
	}

	@Override
	public String toString() {
		return "" + getFarbe() + " " + getZahl();
	}

	public Farbe getFarbe() {
		return farbe;
	}

	public void setFarbe(Farbe farbe) {
		this.farbe = farbe;
	}

	public int getZahl() {
		return zahl;
	}

	public void setZahl(int zahl) {
		this.zahl = zahl;
	}
}
