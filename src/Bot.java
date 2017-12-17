
public class Bot extends Spieler {

	public Bot(String name) {
		super(name);
	}

	@Override
	public int vorhersagen() {
		int zuf = (int) (Math.round(Math.random() * Wizard.runde));
		int vorhersage = zuf;
		System.out.println(this.getName() + " hat die Vorhersage:" + vorhersage);
		return vorhersage;
	}

	@Override
	public Karte karteLegen() {
		Karte tempKarte = null;
		if (this == Wizard.spieler[Wizard.aktiverSpieler]) { //wenn Bot vor den anderen Spielern eine Karte legt
			for (int i = 0; i < Wizard.runde; i++) {
				if (getHandKarten()[i] != null) {
					tempKarte = getHandKarten()[i];
					getHandKarten()[i] = null;
					System.out.println(this.getName() + " legt " + tempKarte);
					return tempKarte;
				}
			}
		} else {
			for (int i = 0; i < Wizard.runde; i++) { //der Bot muss wenn moeglich eine Karte mit der gleichen Farbe wie die erstgelegte Karte legen
				if (getHandKarten()[i] != null) {
					if (getHandKarten()[i].getFarbe() == Wizard.kartenfeld[Wizard.aktiverSpieler].getFarbe()) {
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