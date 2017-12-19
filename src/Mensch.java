import java.util.Scanner;

public class Mensch extends Spieler {
	Scanner sc = new Scanner(System.in);

	public Mensch(String name) {
		super(name);
		mensch=true;
	}

	@Override
	public int vorhersagen() {
		System.out.println(this.getName() + " bitte vorhersagen");
		while (!sc.hasNextInt()) {
			System.out.println("Bitte eine Zahl eingeben!");
			sc.nextLine();
		}
		int vorhersage = sc.nextInt();
		return vorhersage;
	}

	@Override
	public Karte karteLegen() {// Karte legen
		System.out.println("Bitte legen Sie eine Karte");
		while (!sc.hasNextInt()) {
			System.out.println("Bitte gueltige Karte legen 0");
			sc.nextLine();
		}
		int kartenNr = sc.nextInt();
		
		boolean b = false;
		while (b != true) {
			while (kartenNr > 4 || kartenNr < 0) { // wenn die ausgewaehlte KartenNr >5 oder <0, noch mal waehlen
				System.out.println("Bitte gueltige Karte legen 1");
				kartenNr = sc.nextInt();
			}
			if (getHandKarten()[kartenNr] == null) { // wenn es keine Karte mit der ausgewaehlten KartenNr gibt, nochmal waehlen
				System.out.println("Bitte gueltige Karte legen 2");
				kartenNr = sc.nextInt();
			} else {
				b = true;
			}

		}
		Karte tempKarte = getHandKarten()[kartenNr];
		getHandKarten()[kartenNr] = null; // die gespielte Karte nicht mehr in der Hand
		System.out.println(this.getName() + " legt " + tempKarte);
		return tempKarte;
	}
}
