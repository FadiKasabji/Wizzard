import java.util.Scanner;

public class Mensch extends Spieler {
	Scanner sc = new Scanner(System.in);

	public Mensch(String name) {
		super(name);
	}

	@Override
	public int vorhersagen() {
		System.out.println(this.getName() + " bitte vorhersagen");
		boolean b = false;
		int vorhersage = 0;
		while (b == false) {
			while (!sc.hasNextInt()) {
				System.out.println("Bitte eine Zahl eingeben!");
				sc.nextLine();
			}
			vorhersage = sc.nextInt();
			if (vorhersage > -1) {
				b = true;
			} else {
				System.out.println("Bitte eine gueltige Zahl eingeben!");
			}
		}
		return vorhersage;
	}

	@Override
	public Karte karteLegen() {// Karte legen
		System.out.println("Bitte legen Sie eine Karte");
		boolean b = false;
		int kartenNr = 0;
		while (b != true) {
			while (!sc.hasNextInt()) {
				System.out.println("Bitte gueltige Karte legen ");
				sc.nextLine();
			}
			kartenNr = sc.nextInt();
			if (kartenNr > 4 || kartenNr < 0) { // wenn die ausgewaehlte KartenNr >5 oder <0, noch mal waehlen
				System.out.println("Bitte gueltige Karte legen ");
			} else if (getHandKarten()[kartenNr] == null) { // wenn es keine Karte mit der ausgewaehlten KartenNr gibt,
															// nochmal waehlen
				System.out.println("Bitte gueltige Karte legen ");
			} else {
				if (Wizzard.aktiverSpieler != 0) {
					boolean bo = false;
					for (int i = 0; i < Wizzard.runde; i++) { // der Spieler muss eine Karte mit der gleichen Farbe wie
																// die
																// erstgelegte Karte legen, falls er Solche besitzt
						if (getHandKarten()[i] != null) {
							if (getHandKarten()[i].getFarbe() == Wizzard.kartenfeld[Wizzard.aktiverSpieler]
									.getFarbe()) {
								bo = true;
								if (kartenNr == i) {
									b = true;
								} else {
									System.out.println(
											"Sie haben schon eine Karte, deren Farbe gleich die erstgelegte Karte ist");
								}
							}
						}
					}
					if (bo == false) {
						b = true;
					}
				} else {
					b = true;
				}
			}
		}
		Karte tempKarte = getHandKarten()[kartenNr];
		getHandKarten()[kartenNr] = null; // die gespielte Karte nicht mehr in der Hand
		System.out.println(this.getName() + " legt " + tempKarte);
		return tempKarte;
	}
}
