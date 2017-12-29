
public interface iBediener {
	public void spielerErstellen();
	public void start();
	public Karte[] getKartenfeld();
	public Spieler[] getSpieler();
	public boolean gewinner();
	public void wahrheitsblockZeigen();
}
