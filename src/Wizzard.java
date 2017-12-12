
public class Wizzard {
	public static Karte[] karten = new Karte[52];
	private Spieler[] spieler = new Spieler[Spieler.getAnzahl()];
	public static int runde=2;
	Karte trumpfkarte;
	public Wizzard() {
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
	}public static void main(String[] args) {
Wizzard w = new Wizzard();
}}
