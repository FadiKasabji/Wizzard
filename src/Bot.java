
public class Bot extends Spieler {

	public Bot(String name) {
		super(name);
	}

	@Override
	public int vorhersagen() {
		int zuf = (int) (Math.random() * Wizzard.runde);
		int vorhersage = zuf;
		System.out.println(this.getName()+"meine vorhersage:" + vorhersage);
		return vorhersage;
	}

	@Override
	public Karte karteLegen() {
		Karte tempKarte = null;
		if (this == Wizzard.spieler[Wizzard.aktiverSpieler]) {
			for (int i = 0; i < Wizzard.runde; i++) {
				if (handKarten[i] != null) {
					tempKarte = handKarten[i];
					handKarten[i] = null;
					System.out.println(this.getName()+" legt "+tempKarte);
					return tempKarte;
				}
			}
		} else {
			for (int i = 0; i < Wizzard.runde; i++) {
				if (handKarten[i] != null) {
					if (handKarten[i].getFarbe() == Wizzard.kartenfeld[Wizzard.aktiverSpieler].getFarbe()) {
						tempKarte = handKarten[i];
						handKarten[i] = null;
						System.out.println(this.getName()+" legt "+tempKarte);
						return tempKarte;
					} else {
						tempKarte = handKarten[i];
						handKarten[i] = null;
						System.out.println(this.getName()+" legt "+tempKarte);
						return tempKarte;
					}
				}
			}
		}
		return tempKarte;
	}
}