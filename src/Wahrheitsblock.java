
public class Wahrheitsblock {
	private String[][] wb = new String[6][5];

	public Wahrheitsblock() {
		
	}
	
	public String[][] getWb() {
		return wb;
	}
	
	public void wahrheitsblockZeigen() {
		for (String i[] : wb) {
			for (String j : i) {
				System.out.printf(j + "\t");
			}
			System.out.println();
		}
	}

	public void wahrheitsblockErstellen(Spieler[] spieler) {
		for (int i = 0; i < wb.length; i++) {
			for (int j = 0; j < 5; j++) {
				wb[i][j] = "\t";
			}
		}
		for (int i = 1; i <= Spieler.getAnzahl(); i++) { // Spieler an den wb eingeben
			wb[0][i] = spieler[i - 1].getName() + "\t";
		}
		for (int i = 1; i <= 5; i++) { // die Runden von 1 bis 5 eingeben
			wb[i][0] = i + ". Runde";
		}
	}
}
