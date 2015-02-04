
/******************************************************
Cours:   LOG121
Session: H2015
Groupe:  2
Projet: Laboratoire #1
Étudiant(e)s: Marion Briot
              
*******************************************************
Historique des modifications
*******************************************************
2015-01-28 Version initiale (et1)
2013-01-29 Ajout de la fonction ajouter (et2)
*******************************************************/


public class Tableau {
	private Forme tableauForme[];
	private int positionCourante = 0;
	private final int TAILLE_DEFAUT = 10;
	
	//constructeurs
	//on ne vérifie pas si la taille est invalide
	public Tableau(final int taille){
		tableauForme = new Forme[taille];
	}
	//par défaut le tableau à 10 cases
	public Tableau(){
		tableauForme = new Forme[TAILLE_DEFAUT];
	}
	
	/**
	 * fonction qui remplit le tableau contenant les formes a afficher
	 * @author Marion Briot 
	 * @param f de type forme 
	 * @date 2015/01/29
	 */
	public void ajouter(final Forme f){
		if (positionCourante >= tableauForme.length){
			//le tableau est remplit, il faut décaler les formes vers la gauche pour en insérer une nouvelle
			Forme tmp[] = new Forme[tableauForme.length];
			for (int i = 1; i < tableauForme.length; i++) {
				tmp[i-1] = tableauForme[i];
			}
			tmp[tableauForme.length - 1] = f;
			tableauForme = tmp;
				
		} else {
			tableauForme[positionCourante] = f;
			positionCourante ++;
		}
	}

	public Forme get(final int i){
		return this.tableauForme[i];
	}

	public int getLength(){
		return tableauForme.length;
	}
	
	/**
	 * affiche en console le contenu du tableau
	 */
	public void print(){
		System.out.print("[");
		int tmp = 0;
		for(int i = 0; i < tableauForme.length - 1; i++){
			tmp = i;
			if(tableauForme[i] != null){
				System.out.print(tableauForme[i].getNom() + ", ");
			} else {
				System.out.print("null, ");
			}
		}
		if(tableauForme[tmp + 1] != null){
			System.out.println(tableauForme[tmp + 1].getNom() + "]");
		} else {
			System.out.println("null]");
		}
	}
}
