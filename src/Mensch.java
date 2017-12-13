import java.util.Scanner;

public class Mensch extends Spieler {
	Scanner sc = new Scanner(System.in);

	public Mensch(String name) {
		super(name);
	}

	@Override
	public int vorhersagen() {
		System.out.println("Bitte vorhersagen");
		int vorhersage = sc.nextInt();
		return vorhersage;
	}

	@Override
	public Karte karteLegen() {
		System.out.println("Bitte legen Sie eine Karte");
		int kartenNr = sc.nextInt();
		Karte tempKarte = handKarten[kartenNr];
		handKarten[kartenNr] = null;
		System.out.println(this.getName()+" "+handKarten[kartenNr]);
		return tempKarte;
	}
}
