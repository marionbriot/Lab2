public class ListeChainee {
	// Création d'une liste doublement chaînée
	private Noeud debutListe;
	private Noeud finListe;
	private int nbElement;
	private Noeud positionCourante;

	/*
	 * NOEUD //////////////////////////
	 */
	private class Noeud {
		private Object element;
		private Noeud suivant;
		private Noeud precedent;

		public Noeud(Object element, Noeud lienSuivant, Noeud lienPrecedent) {
			this.element = element;
			this.suivant = lienSuivant;
			this.precedent = lienPrecedent;
		}
		
	}

	// /////////////////////////////////////
	public ListeChainee() {

	}

	public Noeud getPositionCourante() {
		return positionCourante;
	}

	public Noeud getDebutListe() {
		return debutListe;
	}

	public void setPositionCourante(Noeud positionCourante) {
		this.positionCourante = positionCourante;
	}

	public int getNbElement() {
		return nbElement;
	}

	/*
	 * 
	 * Avec l'élément à inserer et la position courante de la liste, nous allons
	 * déterminer sa position dans la liste et l'ajouter avant la PC
	 */
	public void insererAvant(Object element) {
		Noeud nInserer;

		if (estVide()) { // si la liste est vide
			nInserer = new Noeud(element, null, null); // pas suiv/prec
			debutListe = nInserer; // debut = nouveau noeud
			finListe = debutListe; // fin = debut
			setPositionCourante(debutListe); // entre dans la liste
		}

		else { // si la liste n'est pas vide
			if (getNbElement() == 1) { // si 1 seul element
				// le debut devient nouveau noeud
				// le noeud precedent de la fin devient le debut
				nInserer = new Noeud(element, positionCourante, null);
				debutListe = nInserer;
				finListe.precedent = debutListe;
			} else {
				// si nbElements += 2
				// creer le noeud avant la PC
				nInserer = new Noeud(element, positionCourante,
						positionCourante.precedent);

				if (positionCourante == finListe) {// si sur fin liste
					finListe.precedent = nInserer; // change le precedent de fin

				}
				if (positionCourante.precedent == debutListe) {
					// si coller sur debut. chage le suivant du debut
					// aéquatement
					positionCourante.precedent = nInserer;
					debutListe.suivant = nInserer;
				}
				// noeud tmp pour insertion
				Noeud tmp = nInserer.precedent;
				// insert nInserer
				tmp.suivant = nInserer;
				nInserer.suivant = positionCourante;
				positionCourante.precedent = nInserer;
			}
		}
		// incrémente nb élément
		nbElement++;
	}

	/*
	 * 
	 * Avec l'élément à inserer et la position courante de la liste, nous allons
	 * déterminer sa position dans la liste et l'ajouter apres la PC
	 */
	public void insererApres(Object element) {
		Noeud nInserer;
		
		if (estVide()) {	// si liste vide, créer le premier element
			nInserer = new Noeud(element, null, null);
			debutListe = nInserer;
			finListe = debutListe;
			setPositionCourante(debutListe);
		}

		else {
			
			// si la liste n'est pas vite
			if (getNbElement() == 1) {
				// fin liste devient nouveau noeud et suivant du début = fin
				nInserer = new Noeud(element, null, positionCourante);
				finListe = nInserer;
				debutListe.suivant = finListe;
			} else {
				// si PC = liste, nouvelle fin devient nInserer
				if (positionCourante == finListe) {
					nInserer = new Noeud(element, null, positionCourante);
					positionCourante.suivant = nInserer;
					
				}
				else {
					// inserer le nouveau noeud apres la PC
					nInserer = new Noeud(element, positionCourante.suivant,
							positionCourante);
					positionCourante.suivant = nInserer;
					
				}

			}
		}
		nbElement++;
	}

	public void supprimerPC() {
		// seulement si la liste n'est pas vide
		if (!(estVide())) {
			if (getNbElement() == 1) { // Si 1 seul element dans la liste,
										// vide la liste completement
				debutListe = null;
				finListe = debutListe;
				positionCourante = null;
			} else {
				// si PC = fin, recule la fin de 1
				if (positionCourante == finListe) {
					positionCourante.precedent.suivant = null;
					finListe = positionCourante.precedent;
					positionCourantePrecedent();
				}
				// si PC = debut, avance le debut de 1
				else if (positionCourante == debutListe) {
					positionCourante.suivant.precedent = null;
					debutListe = positionCourante.suivant;
					positionCouranteSuivant();
				}
				// sinon, effectue la suppression normalement
				else {
					positionCourante.precedent.suivant = positionCourante.suivant;
					positionCourante.suivant.precedent = positionCourante.precedent;
					positionCouranteSuivant();
				}
			}
		}
		nbElement--;
	}

	// Avance la position courante à la prochaine de façon circulaire
	public void positionCouranteSuivant() {
		if (positionCourante.suivant == null) {
			positionCourante = debutListe;
		} else {
			positionCourante = positionCourante.suivant;
		}
	}

	// Recule la position courante à la précédente de façon circulaire
	public void positionCourantePrecedent() {
		if (positionCourante != debutListe) {
			positionCourante = positionCourante.precedent;
		} else {
			positionCourante = finListe;
		}
	}
	public void positionCouranteDebut(){
		positionCourante = debutListe;
	}
	public void positionCouranteFin(){
		positionCourante = finListe;
	}
	// retourne la valeur de la position courante
	public Object affichePositionCourante() {
		return positionCourante.element;
	}
	// retourne si la liste est vide
	public boolean estVide() {
		return nbElement <= 0;
	}

	public void setElement(Object element){
		positionCourante.element = element;
	}
}
