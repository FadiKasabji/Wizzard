import java.util.Scanner;

public class Wizzard {
	static Karte[] karten = new Karte[52];
	static int aktiverSpieler = 0;
	static int runde = 1;
	static Karte[] kartenfeld = new Karte[4];
	static Spieler[] spieler = new Spieler[4];
	private Karte trumpfkarte;
	private String[][] wb = new String [6][5];

	public Wizzard() {
		spielerErstellen();
		wahrheitsblockErstellen();
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
		System.out.println("Gegneranzahl eingeben");
		int an = sc.nextInt();
		while (an > 3 || an < 1) {
			System.out.println("Bitte gueltige Gegneranzahl eingeben");
			an = sc.nextInt();
		}
		for (int i = 1; i <= an; i++) {
			spieler[i] = new Bot("Bot" + i);

		}
	}

	public void start() {
		while (runde < 6) {
			System.out.println("*-----------------" + runde + ". Runde -----------------*");
			deckAusfuellen();
			kartenAusteilen();
			alleVorhersagen();
			alleKartenlegen();
			berechnen();
			wahrheitsblockZeigen();
			resetStiche();
			runde++;
			if (aktiverSpieler < Spieler.getAnzahl() - 1) {
				aktiverSpieler++;
			} else {
				aktiverSpieler = 0;
			}
		}
		gewinner();
	}

	private void gewinner() {
		int max = 0;
		int index = 0;
		for (int i = 0; i < Spieler.getAnzahl(); i++) {
			if(spieler[i].getPunkte() > max) {
				max = spieler[i].getPunkte();
				index = i;
			}
		}
		System.out.println(spieler[index].getName() + " hat gewonnen");
	}

	private void deckAusfuellen() {
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

	private void kartenAusteilen() {
		trumpfkarte = karten[(int) (Math.random() * 51)];
		System.out.println("Trumpfkarte ist: "+ trumpfkarte);
		System.out.println();
		for (int i = 0; i < Spieler.getAnzahl(); i++) {
			System.out.print(spieler[i].getName() + " hat die Karte: ");
			for (int j = 0; j < runde; j++) {
				int zufall = (int) (Math.random() * 51);
				if (karten[zufall] != null) {
					spieler[i].getHandKarten()[j] = karten[zufall];
					System.out.print(spieler[i].getHandKarten()[j] + "     ");
					karten[zufall] = null;
				} else {
					j--;
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	private void alleVorhersagen() {
		int tempaktiv = aktiverSpieler;
		int sum = 0;
		for (int i = 0; i < Spieler.getAnzahl(); i++) {
			if (i == Spieler.getAnzahl() - 1) {
				spieler[tempaktiv].setVorhersage(spieler[tempaktiv].vorhersagen());
				while (spieler[tempaktiv].getVorhersage() + sum == runde) {
					spieler[tempaktiv].setVorhersage(spieler[tempaktiv].vorhersagen());
				}
			} else {
				spieler[tempaktiv].setVorhersage(spieler[tempaktiv].vorhersagen());
				sum += spieler[tempaktiv].getVorhersage();
			}
			if (tempaktiv < Spieler.getAnzahl() - 1)
				tempaktiv++;
			else
				tempaktiv = 0;
		}
		System.out.println();
	}

	private void alleKartenlegen() {
		int tempaktiv = aktiverSpieler;
		int kleineRunde = 1;
		while (kleineRunde <= runde) {
			// karten legen
			tempaktiv = aktiverSpieler;
			for (int i = 0; i < Spieler.getAnzahl(); i++) {
				kartenfeld[tempaktiv] = spieler[tempaktiv].karteLegen();


				if (tempaktiv < Spieler.getAnzahl() - 1) {
					tempaktiv++;
				} else {
					tempaktiv = 0;
				}

			}
			System.out.print("Kartenfeld: ");
			for (int i = 0; i < Spieler.getAnzahl(); i++) {
				System.out.print(  kartenfeld[i] + "\t");
			}
			System.out.println();
			stechen();
			feldAufraeumen();
			kleineRunde++;
		}
		System.out.println();
	}

	private void berechnen() {
		for (int i = 0; i < Spieler.getAnzahl(); i++) {
			if (spieler[i].getStich() == spieler[i].getVorhersage()) { //wenn die Vorhersage gleich Stiche ist, bekommt man 20 bonus
				spieler[i].setPunkte(spieler[i].getPunkte() + 20 + (spieler[i].getStich() * 10));
			} else {
				spieler[i].setPunkte(
						spieler[i].getPunkte()+Math.abs(spieler[i].getStich() - spieler[i].getVorhersage()) * -10);
			}
			wb[runde][i+1] =  "\t" + spieler[i].getPunkte() + "|" + spieler[i].getStich(); //die Punkte an den wb eingeben
		}
	}
	public void wahrheitsblockZeigen() {
		for (String i[] : wb ) {
			for (String j : i) {
				System.out.printf(j + "\t");
			}
			System.out.println();
		}
	}
	private void wahrheitsblockErstellen() {
		for(int i = 0; i < wb.length; i++) {
			for ( int j = 0; j < 5; j ++) { 
				wb[i][j] = "\t";
			}
		}
		for ( int i = 1; i <= Spieler.getAnzahl(); i++) { //Spieler an den wb eingeben
			wb[0][i] = spieler [i-1].getName() + "\t";
		}
		for(int i = 1; i <= 5; i++) { //die Runden von 1 bis 5 eingeben
			wb[i][0] = i + ". Runde";
		}
	}

	private void resetStiche() {
		for (int i = 0; i < Spieler.getAnzahl(); i++) {
			spieler[i].setStich(0);
		}
	}

	private void stechen() {
		int index = 0;
		int max = 0;
		boolean b = false; //die Karte, die die gleiche Farbe wie die Trumpfkarte hat, wird auf true gesetzt
		for (int i = 0; i < Spieler.getAnzahl(); i++) {
			if (kartenfeld[i].getFarbe() == trumpfkarte.getFarbe()) {
				if (kartenfeld[i].getZahl() > max) {
					max = kartenfeld[i].getZahl();
					index = i;
					b = true;
				}
			}
		}
		if (b == false) {
			for (int i = 0; i < Spieler.getAnzahl(); i++) {
				if (kartenfeld[i].getFarbe() == kartenfeld[aktiverSpieler].getFarbe()) {
					if (kartenfeld[i].getZahl() > max) {
						max = kartenfeld[i].getZahl();
						index = i;
					}
				}

			}
		}
		spieler[index].setStich(spieler[index].getStich() + 1); //der Spieler bekommt ein Stich wenn er die Runde gewinnt
		System.out.println(spieler[index].getName() + " hat gestochen!");
	}

	private void feldAufraeumen() {
		for (int j = 0; j < kartenfeld.length; j++) {
			kartenfeld[j] = null;
		}
	}

	public static void main(String[] args) {
		Wizzard w = new Wizzard();
		w.start();
	}
}
