
public class Bot extends Spieler {
	
	public Bot(String name) {
		super(name);
	}

	@Override
	public int vorhersagen() {// Zufall <= Handkarten ansonsten ist es unlogisch
		int zuf = (int) (Math.round(Math.random() * Wizzard.runde));
		int vorhersage = zuf;
		return vorhersage;
	}

	@Override
	public Karte karteLegen() {
		Karte tempKarte = null;
		if (this == Wizzard.spieler[Wizzard.aktiverSpieler]) { //wenn Bot vor den anderen Spielern eine Karte legt
			for (int i = 0; i < Wizzard.runde; i++) {
				if (getHandKarten()[i] != null) {
					tempKarte = getHandKarten()[i];
					getHandKarten()[i] = null;
					System.out.println(this.getName() + " legt " + tempKarte);
					return tempKarte;
				}
			}
		} else {
			for (int i = 0; i < Wizzard.runde; i++) { //der Bot muss wenn moeglich eine Karte mit der gleichen Farbe wie die erstgelegte Karte legen
				if (getHandKarten()[i] != null) {
					if (getHandKarten()[i].getFarbe() == Wizzard.kartenfeld[Wizzard.aktiverSpieler].getFarbe()) {
						tempKarte = getHandKarten()[i];
						getHandKarten()[i] = null;
						System.out.println(this.getName() + " legt " + tempKarte);
						return tempKarte;
					} else {
						tempKarte = getHandKarten()[i];
						getHandKarten()[i] = null;
						System.out.println(this.getName() +" legt " + tempKarte);
						return tempKarte;
					}
				}
			}
		}
		return tempKarte;
	}
}