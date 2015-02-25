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
 * Cette classe gère la liste chaînée
 * @author Équipe FranQueb
 * @date 2013/05/04
 */
public class ListeChainee {
	
	
	private Noeud debut;
	private Noeud fin;
	private Noeud positionCourante;
	
	private int nbElements;

	/**
	 * La classe noeud, qui représente les éléments de la liste.
	 */
	private class Noeud {
		
		private Object element;
		private Noeud suivant;
		private Noeud precedent;
		
		/**
		 * Créer un nouveau noeud
		 *
		 * @param element Object à stocker dans la liste
		 * @param suivant le noeud Suivant
		 * @param precedent le noeud Précedent
		 */
		public Noeud(Object element, Noeud suivant, Noeud precedent) {
			this.element = element;
			this.suivant = suivant;
			this.precedent = precedent;
		}
	}
	/////
	// MUTATEURS
	/////
	/**
	 * Change la positioncourante.
	 *
	 * @param positionCourante nouveau noeud de la position courante
	 */
	public void setPositionCourante(Noeud positionCourante) {
		this.positionCourante = positionCourante;
	}
	
	/**
	 * Recule la position courante de 1.
	 */
	public void positionCourantePrecedent(){
		if (positionCourante != debut) {
			positionCourante = positionCourante.precedent;
		} else {
			positionCourante = fin;
		}
	}
	
	/**
	 * Avance la position courante de 1
	 */
	public void positionCouranteSuivant(){
		if (positionCourante.suivant == null) {
			positionCourante = debut;
		} else {
			positionCourante = positionCourante.suivant;
		}
	}
	
	/**
	 * Met la position courante au début
	 */
	public void setPositionCouranteDebut(){
		positionCourante = debut;
	}
	
	/////
	// ACCESSEURS
	/////
	
	/**
	 * Retourne le nb d'éléments de la liste
	 *
	 * @return nb d'élements
	 */
	public int getNbElements(){
		return nbElements;
	}
	
	/**
	 * retourne l'élément de la position courante
	 *
	 * @return l'Object de la positionCourante
	 */
	public Object getPositionCourante(){
		return positionCourante.element;
	}
	
	/**
	 * Vérifie si la liste est vide.
	 *
	 * @return vrai si liste est vide
	 */
	public boolean estVide() {
		return nbElements <= 0;
	}
	
	/////
	// MÉTHODES UTILITAIRES
	/////
	
	/**
	 * Vider liste.
	 */
	public void viderListe(){
		while(getNbElements() > 0){
			setPositionCouranteDebut();
			supprimerPositionCourante();
		}
	}
	
	/**
	 * Inserer un noeud après la position courante.
	 *
	 * @param element élément à insérer
	 */
	public void insererApres(Object element) {
		// Noeud à inserer
		Noeud noeudInserer;
		
		// Si la liste est pleine (10 ou +) ou la vide avant d'insérer
		if(getNbElements() >= 10){
			viderListe();
		}
		
		// Si liste est vide, créer noeud de début
		if (estVide()) {
			noeudInserer = new Noeud(element, null, null);
			debut = noeudInserer;
			fin = debut;
			setPositionCourante(debut);
		}
		
		else {
			// Si nb d'éléments = 1, sépare début et fin
			if (nbElements == 1) {
				noeudInserer = new Noeud(element, null, positionCourante);
				fin = noeudInserer;
				debut.suivant = fin;
			}
			// Si insère après la fin, déplace la fin vers nouveau noeud
			else {
				if (positionCourante == fin) {
					noeudInserer = new Noeud(element, null, positionCourante);
					positionCourante.suivant = noeudInserer;
					fin = noeudInserer;
				}
				// Sinon, insère normalement
				else {
					noeudInserer = new Noeud(element, positionCourante.suivant,
							positionCourante);
					positionCourante.suivant = noeudInserer;
				}

			}
		}
		nbElements++;
	}
	
	/**
	 * Supprimer le noeud à la position courante
	 */
	public void supprimerPositionCourante() {
		if(!estVide()){
			if(nbElements > 1){
				// Si supprime la fin
				if(positionCourante == fin){
					positionCourante.precedent.suivant = null;
					fin = positionCourante.precedent;
					positionCourantePrecedent();
				}
				// Si supprime le début
				else if(positionCourante == debut){
					positionCourante.suivant.precedent = null;
					debut = positionCourante.suivant;
					positionCouranteSuivant();
				}
				// Supprime normalement
				else{
					positionCourante.precedent.suivant = positionCourante.suivant;
					positionCourante.suivant.precedent = positionCourante.precedent;
					positionCouranteSuivant();
				}
			}
			// nb d'éléments = 1
			else{
				debut = null;
				fin = debut;
				positionCourante = null;
			}
		}
		nbElements--;
	}
	/**
	 * Echanger elements.
	 */
	public void echangerElements(){
		Object noeudTmp = null;
		noeudTmp = positionCourante.element;
		positionCourante.element = positionCourante.suivant.element;
		positionCourante.suivant.element = noeudTmp;
	}
}
