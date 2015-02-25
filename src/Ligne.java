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
 * Cette classe gère les détails d'une ligne 
 * @author Équipe FranQueb
 * @date 2013/05/04
 */

class Ligne extends Forme{
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private int cx;
	private int cy;
	
	private Color couleurLigne= new Color(0,0,0,220);
	
	//contructeurs par défaut
	Ligne(int nseq, String nom, int x1, int y1, int x2, int y2){
		this.nseq = nseq;
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.cx = x1;
		this.cy = y1;
		super.nom = nom;
	}
	
	//mutateurs
	void setX(int x){
		this.cx = x;
	}
	
	void setY(int y){
		this.cy = y;
	}
	
	//accesseurs
	int getX(){
		return x1;
	}
	int getY(){
		return y1;
	}
	
	/**
	 * Dessine une forme, dans ce cas-ci une ligne
	 *
	 * @param g un objet Graphics permettant de dessiner
	 *
	 */
	@Override
	void dessiner(Graphics g) { 
		Graphics2D g3d = (Graphics2D) g;
		g3d.setStroke(new BasicStroke(2));
		g.setColor(couleurLigne);
		if(cx != x1){
			g.drawLine(cx, cy, x2-x1 + cx, y2-y1+cy);
		}
		else{
			g.drawLine(x1, y1, x2, y2);
		}
		
		float pointille[] = {2.0f};
		Graphics2D g2d = (Graphics2D) g;
		BasicStroke bordurePointillee =
		        new BasicStroke(1.0f,
		                        BasicStroke.CAP_BUTT,
		                        BasicStroke.JOIN_MITER,
		                        10.0f, pointille, 0.0f);
		g2d.setStroke(bordurePointillee);
		g2d.setColor(Color.BLACK);
		if (cx != x1) {
			if (x1 > x2) {
				if (y1 > y2) {
					g.drawRect(x2 - x1 + cx, y2 - y1 + cx, x1 - x2, y1 - y2);
				} else {
					g.drawRect(x2 - x1 + cx, cy, x1 - x2, y2 - y1);
				}
			} else {
				if (y1 > y2) {
					g.drawRect(cx, y2 - y1 + cy, x2 - x1, y1 - y2);
				} else {
					g.drawRect(cx, cy, x2 - x1, y2 - y1);
				}
			}
		} else {
			if (x1 > x2) {
				if (y1 > y2) {
					g.drawRect(x2, y2, x1 - x2, y1 - y2);
				} else {
					g.drawRect(x2, y1, x1 - x2, y2 - y1);
				}
			} else {
				if (y1 > y2) {
					g.drawRect(x1, y2, x2 - x1, y1 - y2);
				} else {
					g.drawRect(x1, y1, x2 - x1, y2 - y1);
				}
			}
		}
	}
	
	/**
	 * Calcul l'aire d'une ligne (1 pixel * Longueur)
	 *
	 * @return l'aire de la forme (ligne)
	 *
	 */
	@Override
	double getAire() {
		//distance entre deux points 
		//this.aire = Math.sqrt(Math.pow(x2 - x1, 2)) + (Math.pow(y2 - y1, 2));
		this.aire = getDistance();
		return aire;
	}
	
	/**
	 * Calcul la distance d'une ligne
	 *
	 * @return la distance de la forme (ligne)
	 *
	 */
	@Override
	double getDistance() {
		double xtmp, ytmp;
		xtmp = orientation(x1, x2);
		ytmp = orientation(y1, y2);
		this.distance = Math.sqrt(Math.pow(xtmp, 2) + (Math.pow(ytmp, 2)));
		return distance;
	}
	
	/**
	 * Calcul la longueur d'une ligne
	 *
	 * @return la longueur de la forme (ligne)
	 *
	 */
	double getLongueur(){
		return getDistance();
	}
	
	/**
	 * Calcul la hauteur d'une ligne
	 *
	 * @return la hauteur de la forme (ligne)
	 *
	 */
	double getHauteur(){
		System.out.println("patate");
		return 1;
	}
	double orientation(double d1, double d2){
		double tmp;
		if(d1 > d2){
			tmp = d1 - d2;
		} else {
			tmp = d2 - d1;
		}
		return tmp;
	}
}