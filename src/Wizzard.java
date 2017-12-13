import java.util.Scanner;

public class Wizzard {
	public static Karte[] karten = new Karte[52];
	static Karte[] kartenfeld = new Karte[4];
	Spieler[] spieler = new Spieler[4];
	Karte trumpfkarte;
	WB wb;
	static int aktiverSpieler = 0;
	public static int runde = 1;

	public void deckAusfuellen() {
		int x = 0;
		while (x < 52) {
			for (int i = 0; i < 4; i++) {
				for (int j = 1; j < 14; j++) {
					karten[x] = new Karte(Farbe.values()[i], j);
					x++;

				}
			}
		}

	}

	public void spielerErstellen() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Spielername eingeben");
		String name = sc.nextLine();
		while (name.length() < 3) {
			System.out.println("Bitte einen gueltigen Namen eingeben");
			name = sc.nextLine();
		}
		Mensch m = new Mensch(name);
		spieler[0] = m;
		System.out.println(spieler[0]);
		System.out.println("Gegneranzahl eingeben");
		int an = sc.nextInt();
		while (an > 3 || an < 1) {
			System.out.println("Bitte gueltige Gegneranzahl eingeben");
			an = sc.nextInt();
		}
		for (int i = 1; i <= an; i++) {
			spieler[i] = new Bot("Bot" + i);
			System.out.println(spieler[i]);
		}
	}

	public void kartenAusteilen() {
		int trumpf = (int) (Math.random() * 51);
		trumpfkarte = karten[trumpf];
		karten[trumpf] = null;
		for (int i = 0; i < Spieler.getAnzahl(); i++) {
			for (int j = 0; j < runde; j++) {
				int zufall = (int) (Math.random() * 51);
				if (karten[zufall] != null) {
					spieler[i].handKarten[j] = karten[zufall];
					System.out.println(spieler[i] + " " + spieler[i].handKarten[j]);
					karten[zufall] = null;
				} else {
					j--;
				}
			}
		}
	}

	public void start() {
		while (runde < 6) {
			kartenAusteilen();
			trumpfkarte = karten[(int) (Math.random() * 51)];
			System.out.println("trumpfkarte: " + trumpfkarte);
			alleVorhersagen();
			alleKartenlegen();
			runde++;
			if (aktiverSpieler < Spieler.getAnzahl()) {
				aktiverSpieler++;
			} else {
				aktiverSpieler = 0;
			}
		}
	}

	private void alleVorhersagen() {
		int tempaktiv = aktiverSpieler;
		for (int i = 0; i < Spieler.getAnzahl(); i++) {
			if (spieler[tempaktiv] != null) {
				spieler[tempaktiv].setVorhersage(spieler[tempaktiv].vorhersagen());
				if (tempaktiv < Spieler.getAnzahl())
					tempaktiv++;
				else
					tempaktiv = 0;
			}
		}
	}

	private void alleKartenlegen() {
		int tempaktiv = aktiverSpieler;
		int kleineRunde = 1;
		while (kleineRunde <= runde) {
			// karten legen
			tempaktiv = aktiverSpieler;
			for (int i = 0; i < Spieler.getAnzahl(); i++) {

				if (spieler[tempaktiv] != null) {
					kartenfeld[tempaktiv] = spieler[tempaktiv].karteLegen();
				}
				if (tempaktiv < Spieler.getAnzahl()) {
					tempaktiv++;
				} else {
					tempaktiv = 0;
				}
				System.out.println(kartenfeld[i]);
			}
			stechen();
			berechnen();
			feldAufraeumen();
			kleineRunde++;
		}
	}

	private void berechnen() {
		for (int i = 0; i < Spieler.getAnzahl(); i++) {
			if (spieler[i].getStich() == spieler[i].getVorhersage()) {
				spieler[i].setPunkte(spieler[i].getPunkte() + 20 + (spieler[i].getStich() * 10));
			} else {
				spieler[i].setPunkte(
						spieler[i].getPunkte() + Math.abs(spieler[i].getStich() - spieler[i].getVorhersage()) * -10);
			}
		}

	}

	private void stechen() {
		int index = 0;
		int max = 0;
		for (int i = 0; i < Spieler.getAnzahl(); i++) {
			if (kartenfeld[i].getFarbe() == trumpfkarte.getFarbe()) {
				if (kartenfeld[i].getZahl() > max) {
					max = kartenfeld[i].getZahl();
					index = i;

				}
			} else {
				if (kartenfeld[i].getFarbe() == kartenfeld[aktiverSpieler].getFarbe()) {
					if (kartenfeld[i].getZahl() > max) {
						max = kartenfeld[i].getZahl();
						index = i;
					}
				}
			}
			spieler[index].setStich(spieler[index].getStich() + 1);
		}
	}

	private void feldAufraeumen() {
		for (int j = 0; j < kartenfeld.length; j++) {
			kartenfeld[j] = null;
		}
	}

	public Wizzard() {
		deckAusfuellen();
		spielerErstellen();
	}

	public static void main(String[] args) {
		Wizzard w = new Wizzard();
		w.start();
	}
}
