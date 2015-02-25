/******************************************************
 Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: FenetrePrincipale.java
Date créé: 2013-05-03
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Patrice Boucher
2013-05-03 Version initiale
 *******************************************************/  

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import ca.etsmtl.log.util.IDLogger;

import javax.swing.JFrame;

/**
 * Cette classe représente la fenêtre principale de l'application 
 * @author Patrice Boucher
 * @date 2013/05/04
 */
public class FenetrePrincipale extends JFrame implements PropertyChangeListener{

	private static final long serialVersionUID = -1210804336046370508L;
	private CreateurFormes createurFormes = null;
	private FenetreFormes fenetreFormes;
	private ListeChainee liste;
	private CommBase comm;
	private IDLogger logger = IDLogger.getInstance(); //M�thode statique
	

	/**
	 * Constructeur
	 */
	public FenetrePrincipale(final CommBase comm){
		liste = new ListeChainee();
		fenetreFormes = new FenetreFormes(liste);
		this.comm = comm;
		MenuFenetre menu = new MenuFenetre(this.comm, liste, fenetreFormes);
		this.setLayout(new BorderLayout());
		this.add(menu, BorderLayout.NORTH); 
		this.add(fenetreFormes, BorderLayout.CENTER); // Ajoute la fenêtre de forme à la fenètre principale
		this.pack(); // Ajuste la dimension de la fenêtre principale selon celle de ses composants
		this.setVisible(true); // Rend la fenêtre principale visible.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //... à réviser selon le comportement que vous désirez ...
		this.createurFormes = new CreateurFormes();
		
		//permet d'ajouter une fonction au bouton quitter du JFrame
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				comm.stop();
			}
			
		});
	}

	
	
	// Appelé lorsque le sujet lance "firePropertyChanger"
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {

		if(arg0.getPropertyName().equals("ligne")){
			System.out.println((String) arg0.getNewValue());
			if (this.createurFormes != null){
				Forme forme = this.createurFormes.creerForme((String) arg0.getNewValue());
				if(forme != null){
					//on ajoute la forme dans le tableau de forme 
					fenetreFormes.ajouter(forme);
					//on logue le  num�ro de s�quence dans le fichier de log
					logger.logID(forme.getNseq());
					//On notifie swing que le fenetre a besoin de se faire redessiner
					this.repaint();
				}
			}
		}
	}
}
