public class CreateurFormes {

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
	 * Crée une nouvelle forme. Cette méthode reçoit la chaîne de
	 * caractères provenant du serveur de formes, elle détermine de quelle
	 * forme il s'agit et applique l'opérateur new sur le constructeur de
	 * la forme désirée.
	 *
	 * @param chaineForme un objet String contenant la chaîne de caractères
	 *        qui décrit une forme et provenant du serveur de
	 *        formes.
	 *
	 * @return une instance d'une des sous-classes de la classe abstraite
	 *         Forme avec les paramètres passés par la chaîne d'entrée.
	 */
	
	public Forme creerForme(String chaineForme) {
		DecompositionChaineCarac decomposition = new DecompositionChaineCarac(chaineForme);

		if(decomposition.getNom() != null){
			switch (decomposition.getNom()) {
			case "OVALE":
				String coordOva[] = decomposition.getCoord().split(" ");
				Ellipse ovale = new Ellipse(Integer.parseInt(decomposition.getNseq()), decomposition.getNom(), Integer.parseInt(coordOva[0]), Integer.parseInt(coordOva[1]), Integer.parseInt(coordOva[2]), Integer.parseInt(coordOva[3]));
				return ovale;
			case "CERCLE":
				String coordCer[] = decomposition.getCoord().split(" ");
				Ellipse cercle = new Ellipse(Integer.parseInt(decomposition.getNseq()), decomposition.getNom(), Integer.parseInt(coordCer[0]), Integer.parseInt(coordCer[1]), Integer.parseInt(coordCer[2]), Integer.parseInt(coordCer[2]));
				return cercle;
			case "RECTANGLE":
				String coordRec[] = decomposition.getCoord().split(" ");
				Rectangle r = new Rectangle(Integer.parseInt(decomposition.getNseq()), decomposition.getNom(), Integer.parseInt(coordRec[0]), Integer.parseInt(coordRec[1]), Integer.parseInt(coordRec[2]), Integer.parseInt(coordRec[3]));
				return r;
			case "CARRE":
				String coordCar[] = decomposition.getCoord().split(" ");
				Rectangle carre = new Rectangle(Integer.parseInt(decomposition.getNseq()), decomposition.getNom(), Integer.parseInt(coordCar[0]), Integer.parseInt(coordCar[1]), Integer.parseInt(coordCar[2]), Integer.parseInt(coordCar[3]));
				return carre;
			case "LIGNE":
				String coordLig[] = decomposition.getCoord().split(" ");
				Ligne ligne = new Ligne(Integer.parseInt(decomposition.getNseq()), decomposition.getNom(), Integer.parseInt(coordLig[0]), Integer.parseInt(coordLig[1]), Integer.parseInt(coordLig[2]), Integer.parseInt(coordLig[3]));
				return ligne;
			default:
				return null;
			}
		} else {
			return null;
		}
	}
}