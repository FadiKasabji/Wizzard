import java.util.Scanner;

public class Mensch extends Spieler {
	Scanner sc = new Scanner(System.in);

	public Mensch(String name) {
		super(name);
	}

	@Override
	public int vorhersagen() {
		System.out.println(this.getName() + " bitte vorhersagen");
		int vorhersage = sc.nextInt();
		return vorhersage;
	}

	@Override
	public Karte karteLegen() {
		System.out.println("Bitte legen Sie eine Karte");
		int kartenNr = sc.nextInt();
		while (handKarten[kartenNr] == null) {
			System.out.println("Bitte gueltige Karte legen");
			kartenNr = sc.nextInt();
		}
		Karte tempKarte = handKarten[kartenNr];
		handKarten[kartenNr] = null;
		System.out.println(this.getName() + " legt " + tempKarte);
		return tempKarte;
	}
}
