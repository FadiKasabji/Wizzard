import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI implements ActionListener  {
	iBediener spiel = new Wizzard();
	private JFrame jf;
	private JButton[] handKartenBtn = new JButton[5];
	private JButton[] vorhersageBtn = new JButton[6];
	private JLabel[] karten = new JLabel[4];
	private JLabel gewinner= new JLabel("Gewinner: ");

	//TEST
	Karte eins = new Karte(Farbe.blau, 2);
	Karte zwei = new Karte(Farbe.gruen, 10);
	Karte drei = new Karte(Farbe.gruen, 4);
	Karte vier = new Karte(Farbe.blau, 6);
	Karte[] testKarten = {eins, zwei, drei, vier}; 

	public GUI(String title) {
		jf = new JFrame(title);
		JPanel godPanel = new JPanel();
		JPanel gewinnerPanel = new JPanel();
		JPanel kartenFeldPanel = new JPanel();
		JPanel kartenPanel = new JPanel();
		JPanel vorhersagePanel = new JPanel();

		gewinnerPanel.add(gewinner);

		for (int i = 0; i < karten.length; i++) {
			karten[i] = new JLabel(testKarten[i].toString());
			kartenPanel.add(karten[i]);
		}
		for (int i = 0; i < spiel.getSpieler()[0].getHandKarten().length; i++) {
			if (spiel.getSpieler()[0].getHandKarten()[i] != null) {
				handKartenBtn[i] = new JButton(spiel.getSpieler()[0].getHandKarten()[i].toString());
				handKartenBtn[i].addActionListener(this);
				kartenPanel.add(handKartenBtn[i]);
			}
		}
		for (int i = 0; i < vorhersageBtn.length; i++) {
			vorhersageBtn[i] = new JButton("" + (i));
			vorhersageBtn[i].addActionListener(this);
			vorhersagePanel.add(vorhersageBtn[i]);
			
		}

		godPanel.add(gewinnerPanel); godPanel.add(kartenFeldPanel); godPanel.add(kartenPanel); godPanel.add(vorhersagePanel);
		jf.add(godPanel);
		jf.getContentPane();
		jf.setVisible(true);
		jf.setSize(500, 500);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void start() {
		
	}

	public static void main(String[] args) {
		new GUI("Wizzard");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		for (int i = 0; i < vorhersageBtn.length; i++) {
			if (vorhersageBtn[i] == o) {
				spiel.getSpieler()[0].setVorhersage(i);
			} 
		}
		
		//refresh
		if (spiel.gewinner() == false) {
			
		}
	}
}
