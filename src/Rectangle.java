import java.awt.Graphics;
import java.awt.Color;

/******************************************************
Cours:   LOG121
Session: H2015
Groupe:  2
Projet: Laboratoire #1
Étudiant(e)s: Marion Briot

 *******************************************************
Historique des modifications
 *******************************************************
2013-0X-XX Version initiale (et1)
2013-0X-XX Ajout de la fonction (et2)
 *******************************************************/

class Rectangle extends Forme{

	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private Color couleurCarre= Color.blue;
	private Color couleurRect= Color.red;

	Rectangle( int nseq, int x1, int x2, int y1, int y2){
		nom = "Rectangle";
		this.nseq = nseq;
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;

	}
	/**
	 * Déssine un carré ou un rectangle de couleur différente
	 */
	@Override
	void dessiner(final Graphics g) {
		int largeur = x2 - x1;
		int longeur = y2 - y1;
		if(longeur == largeur){
			g.setColor(couleurCarre);
		} else {
			g.setColor(couleurRect);
		}
		g.fillRect(x1, y1, largeur, longeur);
	}
}