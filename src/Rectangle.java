import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Color;
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
 * Cette classe gère les détails d'un rectangle 
 * @author Équipe FranQueb
 * @date 2013/05/04
 */
class Rectangle extends Forme{

	private int x1;
	private int x2;
	private int y1;
	private int y2;
	
	private int c1;
	private int c2;

	int largeur;
	int longeur;

	private Color couleurCarre= new Color(86,255,86,171);
	private Color couleurRect= new Color(255,0,0,171);
	
	
	//constructeur par défaut
	Rectangle( int nseq, String nom, int x1, int y1, int x2, int y2){
		super.nom = nom;
		this.nseq = nseq;
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.c1 = x1;
		this.c2 = y1;

	}
	
	//mutateurs
	void setX(int x){
		this.c1 = x;
	}
	
	void setY(int y){
		this.c2 = y;
	}
	
	//accesseurs
	int getX(){
		return x1;
	}
	int getY(){
		return y1;
	}
	
	/**
	 * Dessine un rectangle ou un carré de couleur différente
	 * avec pour cohntour un recntalg en pointillés
	 *
	 * @param g un objet Graphics permettant de dessiner
	 *
	 */
	@Override
	void dessiner(final Graphics g) {
		longeur = x2 - x1;
		largeur = y2 - y1;
		if(longeur < largeur){
			int tmp;
			tmp = longeur;
			longeur = largeur;
			largeur = tmp;
		}
		if(longeur == largeur){
			g.setColor(couleurCarre);
		} else {
			g.setColor(couleurRect);
		}
		g.fillRect(c1, c2, largeur, longeur);
		
		float pointille[] = {2.0f};
		Graphics2D g2d = (Graphics2D) g;
		BasicStroke bordurePointillee =
		        new BasicStroke(1.0f,
		                        BasicStroke.CAP_BUTT,
		                        BasicStroke.JOIN_MITER,
		                        10.0f, pointille, 0.0f);
		g2d.setStroke(bordurePointillee);
		g2d.setColor(Color.BLACK);
		g.drawRect(c1, c2, largeur, longeur);

	}
	
	/**
	 * Calcul l'aire d'une forme (rectangle ou carré)
	 *
	 * @return l'aire de la forme (rectangle ou carré)
	 *
	 */
	@Override
	double getAire() {
		this.aire = longeur * largeur;
		return aire;
	}
	
	/**
	 * Calcul la distance d'une forme (rectangle ou carré)
	 *
	 * @return la distance de la forme (rectangle ou carré)
	 *
	 */
	@Override
	double getDistance() {
		this.distance = Math.sqrt((largeur * largeur) + (longeur * longeur));
		return distance;
	}
	
	/**
	 * Calcul la longueur d'une forme (rectangle ou carré)
	 *
	 * @return la longueur de la forme (rectangle ou carré)
	 *
	 */
	double getLongueur(){
		if(largeur < longeur){
			this.longueur = longeur;
		} else {
			this.longueur  = largeur;
		}
		return longueur;
		
		
	}
	
	/**
	 * Calcul l'hauteur d'une forme (rectangle ou carré)
	 *
	 * @return l'hauteur de la forme (rectangle ou carré)
	 *
	 */
	double getHauteur(){
		if(largeur > longeur){
			this.hauteur = longeur;
		} else {
			this.hauteur = largeur;
		}
		return hauteur;
	}
}