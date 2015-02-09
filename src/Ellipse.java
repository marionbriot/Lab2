import java.awt.Color;
import java.awt.Graphics;
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

class Ellipse extends Forme{
	private int centreX;
	private int centreY;
	private int rayonH;
	private int rayonV;
	private Color couleurCercle= Color.pink;
	private Color couleurOvale= Color.cyan;
	
	Ellipse(int nseq, int centreX, int centreY, int rayonH, int rayonV){
		this.nseq = nseq;
		this.centreX = centreX;
		this.centreY = centreY;
		this.rayonH = rayonH;
		this.rayonV = rayonV;
		
		nom = "Ellipse";
	}
	
	/**
	 * Déssine un cercle ou un oval de couleur différente
	 */
	@Override
	void dessiner(Graphics g) {
		if(rayonH == rayonV){
			g.setColor(couleurCercle);
		} else {
			g.setColor(couleurOvale);
		}
		g.fillOval(centreX, centreY, rayonV, rayonH);
	}
	
}
