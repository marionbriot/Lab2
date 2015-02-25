import java.awt.Graphics;

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
 * Cette classe gère les formes
 * @author Équipe FranQueb
 * @date 2013/05/04
 */

public abstract class Forme{
	protected double aire;
	protected double distance;
	protected double longueur;
	protected double hauteur;
	protected String nom;
	protected int nseq;

	/*
	 * Liste des méthodes qui devront se retrouver dans les
	 * sous-classes de Forme
	 */

	abstract void dessiner(Graphics g);

	abstract double getAire();
	
	abstract double getDistance();

	abstract double getLongueur();
	
	abstract double getHauteur();
	
	abstract int getX();
	
	abstract int getY();
	
	abstract void setX(int x);
	
	abstract void setY(int y);
	
	
	//accesseurs
	public int getNseq(){
		return nseq;
	}
	
	public String getNom(){
		return nom;
	}
	
	public int getCode() {
		int code = 0;
		switch (nom) {
			case "CAREE":
				code = 0;
				break;
			case "RECTANGLE":
				code = 1;
				break;
			case "CERCLE":
				code = 2;
				break;
			case "OVALE":
				code = 3;
				break;
			case "LIGNE":
				code = 4;
				break;
		}
		return code;
	}
}



