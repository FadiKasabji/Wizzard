
public class Bot extends Spieler {
//bla 
	public Bot(String name) {
		super(name);
	}

	@Override
	public int vorhersagen() {
		int zuf = (int) (Math.random() * Wizzard.runde);
		int vorhersage = zuf;
		return vorhersage;
	}

	@Override 
	public Karte karteLegen(Karte k) {
		for(int i=0; i<Wizzard.runde;i++) {
			if(handKarten[i].getFarbe()==Wizzard.kartenfeld[0].getFarbe()&&Wizzard.kartenfeld[0]!=null){
				return handKarten[i];
				
			}
		}return handKarten[0];
	}
}