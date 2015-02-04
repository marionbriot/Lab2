import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

public class DecompositionChaineCarac{
	
	private String chaineCarac;
	private String nseq = null;
	private String nom = null; 
	private String coord = null;
	
	public String getNseq() {
		return nseq;
	}
	public String getNom() {
		return nom;
	}
	public String getCoord() {
		return coord;
	}
	
	public DecompositionChaineCarac(String chaineCarac){
		this.chaineCarac = chaineCarac;
		this.decomposer();
	}
	/**
	 * Décompose la chaine de caractère provenant du serveur
	 */
	private void decomposer() {
		//on décompose la chaine de caractère en trois : le numéro de séquence, le nom et les coordonnées
		final String pattern = "(?<nseq>\\d+) <(?<nom>.*)> (?<coord>.*) </\\2>";
		final Pattern p = Pattern.compile(pattern);
		final Matcher m = p.matcher(this.chaineCarac);

		if(m.matches()){
			this.nseq = m.group("nseq");
			this.nom = m.group("nom");
			this.coord = m.group("coord");
		}
	}
}
