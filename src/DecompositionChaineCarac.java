import java.util.regex.Matcher;
import java.util.regex.Pattern;
/******************************************************
Cours:   LOG121
Session: H2015
Groupe:  2
Projet: Laboratoire #2
�tudiant(e)s: Marion Briot

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
	 * D�compose la chaine de caract�re provenant du serveur
	 */
	private void decomposer() {
		//on d�compose la chaine de caract�re en trois : le num�ro de s�quence, le nom et les coordonn�es
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
