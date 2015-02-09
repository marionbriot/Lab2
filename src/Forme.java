import java.awt.Graphics;

/******************************************************
Cours:   LOG121
Session: H2015
Groupe:  2
Projet: Laboratoire #1
�tudiant(e)s: Marion Briot
              
*******************************************************
Historique des modifications
*******************************************************
2013-01-21 Version initiale (et1)
*******************************************************/

public abstract class Forme{
	
	protected String nom;
	protected int nseq;

	abstract void dessiner(Graphics g);

	public int getNseq(){
		return nseq;
	}
	public String getNom() {
		return nom;
	}
}


