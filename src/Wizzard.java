import java.util.Scanner;

public class Wizzard {
	public static Karte[] karten = new Karte[52];
	static Karte[] kartenfeld = new Karte[4];
	Spieler[] spieler = new Spieler[4];
	Karte trumpfkarte;
	WB wb;
	public static int runde = 1;

	public void deckAusfuellen() {
		int x = 0;
		while (x < 52) {
			for (int i = 0; i < 4; i++) {
				for (int j = 1; j < 14; j++) {
					karten[x] = new Karte(Farbe.values()[i], j);
					System.out.println(karten[x]);
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

			runde++;
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
