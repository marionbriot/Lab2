import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
/******************************************************
Cours:   LOG121
Session: H2015
Groupe:  2
Projet: Laboratoire #2
Étudiant(e)s: Marion Briot

 *******************************************************
Historique des modifications
 *******************************************************
2013-0X-XX Version initiale (et1)
2013-0X-XX Ajout de la fonction (et2)
 *******************************************************/

/**
 * Cette classe gère les détails d'un cercle ou d'un oval 
 * @author Équipe FranQueb
 * @date 2013/05/04
 */

class Ellipse extends Forme{
	private int centreXorig;
	private int centreYorig;
	private int centreX;
	private int centreY;
	private int rayonH;
	private int rayonV;
	private Color couleurCercle= new Color(255, 120, 120, 171);
	private Color couleurOvale= new Color(0, 175, 255, 171);

	//Constructeur par copie d'attributs
	Ellipse(int nseq, String nom, int centreX, int centreY, int rayonH, int rayonV){
		this.nseq = nseq;
		this.centreX = centreX;
		this.centreY = centreY;
		this.centreXorig = centreX;
		this.centreYorig = centreY;
		this.rayonH = rayonH;
		this.rayonV = rayonV;
		super.nom = nom;
	}

	//mutateurs
	void setX(int x){
		this.centreX = x;
	}
	
	void setY(int y){
		this.centreY = y;
	}
	
	//accesseurs
	int getX(){
		return centreXorig;
	}
	int getY(){
		return centreYorig;
	}
	/**
	 * Dessine une forme, dans ce cas-ci une un cercle ou un oval
	 * ainsi qu'un contoir de forme recntagle en pointillés
	 *
	 * @param g un objet Graphics permettant de dessiner
	 *
	 */
	@Override
	void dessiner(Graphics g) {
		
		//dessine le cercle en temps que tel
		if(rayonH == rayonV){
			g.setColor(couleurCercle);
		} else {
			g.setColor(couleurOvale);
		}
		g.fillOval(centreX, centreY, rayonV * 2, rayonH * 2);
	
		//dessine le contour du cercle de forme rectangulaire (avec pointillés)
		float pointille[] = {3.0f};
		Graphics2D g2d = (Graphics2D) g;
		BasicStroke bordurePointillee =
		        new BasicStroke(1.0f,
		                        BasicStroke.CAP_BUTT,
		                        BasicStroke.JOIN_MITER,
		                        10.0f, pointille, 0.0f);
		g2d.setStroke(bordurePointillee);
		g2d.setColor(Color.BLACK);
		g.drawRect(centreX, centreY, rayonV * 2, rayonH * 2);
	}

	/**
	 * Calcul l'aire d'un cercle ou d'un oval
	 *
	 * @return l'aire de la forme (cercle ou oval)
	 *
	 */
	@Override
	double getAire() {
		if(rayonH == rayonV){
			this.aire = Math.PI * rayonH * rayonH ;
		} else {
			this.aire = Math.PI * rayonH * rayonV;
		}

		return aire;
	}

	/**
	 * Calcul la distance entre les deux extrémitées d'un cercle ou d'un oval
	 *
	 * @return la distance entre les deux extrémitées de la forme (cercle ou oval)
	 *
	 */
	@Override
	double getDistance(){
		if(rayonH > rayonV){
			this.distance = rayonH * 2;
		} else {
			this.distance = rayonV * 2;
		}
		return distance;
	}

	/**
	 * Calcul la longueur d'un cercle ou d'un oval
	 *
	 * @return la longueur de la forme (cercle ou oval)
	 *
	 */
	double getLongueur(){
		if(rayonH > rayonV){
			this.longueur = rayonH * 2;
		} else {
			this.longueur = rayonV * 2;
		}
		return longueur;
	}
	
	/**
	 * Calcul la hauteur d'un cercle ou d'un oval
	 *
	 * @return la hauteur de la forme (cercle ou oval)
	 *
	 */
	double getHauteur(){
		if(rayonH < rayonV){
			this.hauteur = rayonH * 2;
		} else {
			this.hauteur = rayonV * 2;
		}
		return hauteur;
	}


}
