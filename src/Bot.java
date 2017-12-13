
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
		for (int i = 0; i < Wizzard.runde; i++) {
			if (handKarten[i].getFarbe() == Wizzard.kartenfeld[Wizzard.aktiverSpieler].getFarbe()
					&& Wizzard.kartenfeld[Wizzard.aktiverSpieler] != null) {
				tempKarte = handKarten[i];
				handKarten[i] = null;
				System.out.println(this.getName()+" "+tempKarte);
				return tempKarte;

			}
		}
		for (int i = 0; i < Wizzard.runde; i++) {
			if (handKarten[i] != null) {
				tempKarte = handKarten[i];
				handKarten[i] = null;
				return tempKarte;
			}
		}
		System.out.println("meine karte: " + tempKarte);
		return tempKarte;
	}
}