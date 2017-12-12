import java.util.Scanner;

public class Mensch extends Spieler {
Scanner sc = new Scanner(System.in);
	public Mensch(String name) {
		super(name);
	}

	@Override
	public int vorhersagen() {
		
		int vorhersage = sc.nextInt();
		return vorhersage;
	}
	@Override 
	public Karte karteLegen(Karte k) {
		int kartenNr= sc.nextInt();
		return handKarten[kartenNr];
	}
}
