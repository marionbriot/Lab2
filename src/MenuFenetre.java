/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: MenuFenetre.java
Date cr√©√©: 2013-05-03
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Patrice Boucher
2013-05-03 Version initiale
 *******************************************************/  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
/**
 * Cr√©e le menu de la fen√™tre de l'application√©
 */
public class MenuFenetre extends JMenuBar{
	private static final long serialVersionUID = 1536336192561843187L;
	private static final int  MENU_DESSIN_ARRETER_TOUCHE_MASK  = ActionEvent.CTRL_MASK;
	private static final char MENU_DESSIN_ARRETER_TOUCHE_RACC  = KeyEvent.VK_A;
	private static final int  MENU_DESSIN_DEMARRER_TOUCHE_MASK = ActionEvent.CTRL_MASK;
	private static final char MENU_DESSIN_DEMARRER_TOUCHE_RACC = KeyEvent.VK_D;
	private static final int  MENU_FICHIER_OBTENIR_FORMES_TOUCHE_MASK = ActionEvent.CTRL_MASK;
	private static final char MENU_FICHIER_OBTENIR_FORMES_TOUCHE_RACC = KeyEvent.VK_A;
	private static final int  MENU_FICHIER_QUITTER_TOUCHE_MASK = ActionEvent.CTRL_MASK;
	private static final char MENU_FICHIER_QUITTER_TOUCHE_RACC = KeyEvent.VK_Q;
	private static final int  MENU_TRIER_VALEURS_TOUCHE_MASK = ActionEvent.CTRL_MASK;
	private static final char MENU_TRIER_VALEURS_TOUCHE_RACC = KeyEvent.VK_R;
	private static final String
	MENU_FICHIER_TITRE = "app.frame.menus.file.title",
	MENU_FICHIER_OBTENIR_FORMES = "app.frame.menus.file.start",
	MENU_FICHIER_QUITTER = "app.frame.menus.file.exit",
	MENU_DESSIN_TITRE = "app.frame.menus.draw.title",
	MENU_DESSIN_DEMARRER = "app.frame.menus.draw.start",
	MENU_DESSIN_ARRETER = "app.frame.menus.draw.stop",
	MENU_AIDE_TITRE = "app.frame.menus.help.title",
	MENU_AIDE_PROPOS = "app.frame.menus.help.about",
	MENU_TRIER_TITRE = "app.frame.menus.sortBy.title",
	MENU_TRIER_VALEURS_PAR_ID_CROISSANTE = "app.frame.menus.sortBy.ID",
	MENU_TRIER_VALEURS_PAR_ID_DECROISSANT = "app.frame.menus.sortBy.InverseID",
	MENU_TRIER_VALEURS_PAR_AIRE_CROISSANT = "app.frame.menus.sortBy.area",
	MENU_TRIER_VALEURS_PAR_AIRE_DECROISSANT = "app.frame.menus.sortBy.inverseArea",
	MENU_TRIER_VALEURS_PAR_FORMES = "app.frame.menus.sortBy.TypeOfShapes",
	//Le triage suivant se fait de la facon suivante : carrÈ -> rectangle -> cercle -> ovale -> ligne
	MENU_TRIER_VALEURS_PAR_FORMES_INVERSE = "app.frame.menus.sortBy.inverseTypeOfShapes",
	//le triage suivant se fait de la facon suivante : ligne -> ovale -> cercle -> rectangle -> carrÈ
	MENU_TRIER_VALEURS_PAR_DISTANCE_MAXIMAL = "app.frame.menus.sortBy.MaxDistance",
	MENU_TRIER_VALEURS_PAR_LARGEUR_CROISSANTE = "app.frame.menus.sortBy.Width",
	MENU_TRIER_VALEURS_PAR_LARGEUR_DECROISSANT = "app.frame.menus.sortBy.inverseWidth",
	MENU_TRIER_VALEURS_PAR_HAUTEUR_CROISSANT = "app.frame.menus.sortBy.Height",
	MENU_TRIER_VALEURS_PAR_HAUTEUR_DECROISSANTE = "app.frame.menus.sortBy.inverseHeight",
	MENU_TRIER_VALEURS_PAR_ORDRE_ORIGINAL = "app.frame.menus.sortBy.OriginalOrder";
	//menu_trier_
	private static final String MESSAGE_DIALOGUE_A_PROPOS = "app.frame.dialog.about";  
	private JMenuItem arreterMenuItem, demarrerMenuItem;
	private JMenu menu, trierMenu;
	private static final int DELAI_QUITTER_MSEC = 200;
	private static JRadioButtonMenuItem sortBySequenceCroissante;
	private JRadioButtonMenuItem sortBySequenceDecroissante;
	private JRadioButtonMenuItem sortByAireFormeCroissante;
	private JRadioButtonMenuItem sortByAireFormeDecroissante;
	private JRadioButtonMenuItem sortByTypeFormes;
	private JRadioButtonMenuItem sortByTypeFormesInverse;
	private JRadioButtonMenuItem sortByDistanceMaximale;
	private JRadioButtonMenuItem sortByLargeurCroissante;
	private JRadioButtonMenuItem sortByLargeurDecroissante;
	private JRadioButtonMenuItem sortByHauteurCroissante;
	private JRadioButtonMenuItem sortByHauteurDecroissante;
	private JRadioButtonMenuItem sortByOrdreServeur;
	private static ButtonGroup groupJRadioButtonMenuItem = new ButtonGroup();

	
	CommBase comm; // Pour activer/d√©sactiver la communication avec le serveur
	ListeChainee liste;
	UtilitaireTrie ut;
	FenetreFormes fenetre;
	
	/**
	 * Constructeur
	 */
	public MenuFenetre(CommBase comm, ListeChainee liste, FenetreFormes fenetre) {
		this.fenetre = fenetre;
		ut = new UtilitaireTrie();
		this.liste = liste;
		this.comm = comm;
		addMenuFichier();
		addMenuTrierOptions();
		addMenuAide();
	}
	/**
	 *  Cr√©er le menu "Draw". 
	 */
	protected void addMenuDessiner() {
		JMenu menu = creerMenu(MENU_DESSIN_TITRE,new String[] { MENU_DESSIN_DEMARRER, MENU_DESSIN_ARRETER });
		demarrerMenuItem = menu.getItem(0);
		demarrerMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				comm.start();
				rafraichirMenus();
			}
		});
		demarrerMenuItem.setAccelerator(KeyStroke.getKeyStroke(
				MENU_DESSIN_DEMARRER_TOUCHE_RACC,
				MENU_DESSIN_DEMARRER_TOUCHE_MASK));
		arreterMenuItem = menu.getItem(1);
		arreterMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				comm.stop();
				rafraichirMenus();
			}
		});
		arreterMenuItem.setAccelerator(KeyStroke.getKeyStroke(
				MENU_DESSIN_ARRETER_TOUCHE_RACC,
				MENU_DESSIN_ARRETER_TOUCHE_MASK));
		add(menu);
	}
	/** 
	 * Cr√©er le menu "File". 
	 */
	protected void addMenuFichier() {
		JMenu menu = creerMenu(MENU_FICHIER_TITRE, new String[] {MENU_FICHIER_OBTENIR_FORMES, MENU_FICHIER_QUITTER });
		// Bouton Obtenir Forme
		menu.getItem(0).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comm.start();
				rafraichirMenus();
			}
		});
		// Bouton Quitter
		menu.getItem(1).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comm.stop();
				try {
					Thread.sleep(DELAI_QUITTER_MSEC);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.exit(0);
			}
		});
		menu.getItem(0).setAccelerator(
				KeyStroke.getKeyStroke(MENU_FICHIER_OBTENIR_FORMES_TOUCHE_RACC,
						MENU_FICHIER_OBTENIR_FORMES_TOUCHE_MASK));
		menu.getItem(1).setAccelerator(
				KeyStroke.getKeyStroke(MENU_FICHIER_QUITTER_TOUCHE_RACC,
						MENU_FICHIER_QUITTER_TOUCHE_MASK));
		add(menu);
	}
	/**
	 *  Cr√©er le menu "Help". 
	 */
	private void addMenuAide() {
		JMenu menu = creerMenu(MENU_AIDE_TITRE, new String[] { MENU_AIDE_PROPOS });
		menu.getItem(0).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,  LangueConfig.getResource(MESSAGE_DIALOGUE_A_PROPOS), 
						LangueConfig.getResource(MENU_AIDE_PROPOS), JOptionPane.INFORMATION_MESSAGE);
			}
		});
		add(menu);
	}
	/**
	 *  Cr√©er le menu "Trier". 
	 */
	private void addMenuTrierOptions() {
		sortBySequenceCroissante = new JRadioButtonMenuItem(MENU_TRIER_VALEURS_PAR_ID_CROISSANTE);
		sortBySequenceDecroissante = new JRadioButtonMenuItem(MENU_TRIER_VALEURS_PAR_ID_DECROISSANT);
		sortByAireFormeCroissante = new JRadioButtonMenuItem(MENU_TRIER_VALEURS_PAR_AIRE_CROISSANT);
		sortByAireFormeDecroissante = new JRadioButtonMenuItem(MENU_TRIER_VALEURS_PAR_AIRE_DECROISSANT);
		sortByTypeFormes = new JRadioButtonMenuItem(MENU_TRIER_VALEURS_PAR_FORMES);
		sortByTypeFormesInverse = new JRadioButtonMenuItem(MENU_TRIER_VALEURS_PAR_FORMES_INVERSE);
		sortByDistanceMaximale = new JRadioButtonMenuItem(MENU_TRIER_VALEURS_PAR_DISTANCE_MAXIMAL);
		sortByLargeurCroissante = new JRadioButtonMenuItem(MENU_TRIER_VALEURS_PAR_LARGEUR_CROISSANTE);
		sortByLargeurDecroissante = new JRadioButtonMenuItem(MENU_TRIER_VALEURS_PAR_LARGEUR_DECROISSANT);
		sortByHauteurCroissante = new JRadioButtonMenuItem(MENU_TRIER_VALEURS_PAR_HAUTEUR_CROISSANT);
		sortByHauteurDecroissante = new JRadioButtonMenuItem(MENU_TRIER_VALEURS_PAR_HAUTEUR_DECROISSANTE);
		sortByOrdreServeur = new JRadioButtonMenuItem(MENU_TRIER_VALEURS_PAR_ORDRE_ORIGINAL);
		groupJRadioButtonMenuItem = new ButtonGroup();		
		
		trierMenu = creerMenu (MENU_TRIER_TITRE, new JRadioButtonMenuItem[] { sortBySequenceCroissante, sortBySequenceDecroissante, sortByAireFormeCroissante,sortByAireFormeDecroissante,sortByTypeFormes,sortByTypeFormesInverse, sortByDistanceMaximale,sortByLargeurCroissante, sortByLargeurDecroissante,  sortByHauteurCroissante, sortByHauteurDecroissante, sortByOrdreServeur });
		trierMenu.getItem(0).addActionListener(new ActionListener() {
			//sÈquence croissante
			public void actionPerformed(ActionEvent arg0) {
				ut.trieGeneral(liste, true, 1);
				fenetre.repaint();
				clearSelections(0);
			}
		});
		//sÈquence dÈcroissante
		trierMenu.getItem(1).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ut.trieGeneral(liste, false, 1);
				fenetre.repaint();
				clearSelections(1);
			}
		});
		//aire croissante
		trierMenu.getItem(2).addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	ut.trieGeneral(liste, true, 2);
		    	fenetre.repaint();
		    	clearSelections(2);
			}
		});
		//aire dÈcroissante
    	trierMenu.getItem(3).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ut.trieGeneral(liste, false, 2);
				fenetre.repaint();
				clearSelections(3);
			}
		});
    	//type de forme croissante
		trierMenu.getItem(4).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ut.trieGeneral(liste, true, 4);
				fenetre.repaint();
				clearSelections(4);
			}
		});
		//type de forme dÈcroissante
		trierMenu.getItem(5).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ut.trieGeneral(liste, false, 4);
				fenetre.repaint();
				clearSelections(5);
			}
		});
		//distance maximale
		trierMenu.getItem(6).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ut.trieGeneral(liste, true, 3);
				fenetre.repaint();
				clearSelections(6);
			}
		});
		//largeur croissance
		trierMenu.getItem(7).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ut.trieGeneral(liste, true, 5);
				fenetre.repaint();
				clearSelections(7);
			}
		});
		//largeur dÈcroissante
		trierMenu.getItem(8).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ut.trieGeneral(liste, false, 5);
				fenetre.repaint();
				clearSelections(8);
			}
		});
		//grandeur croissante
		trierMenu.getItem(9).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ut.trieGeneral(liste, true, 6);
				fenetre.repaint();
				clearSelections(9);
			}
		});
		//grandeur dÈcroissante
		trierMenu.getItem(10).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ut.trieGeneral(liste, false, 6);
				fenetre.repaint();
				clearSelections(10);
			}
		});
		//forme originale
		trierMenu.getItem(11).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ut.trieGeneral(liste, false, 7);
				fenetre.repaint();
				clearSelections(11);
			}
		});
		KeyStroke.getKeyStroke(MENU_TRIER_VALEURS_TOUCHE_MASK,
				MENU_TRIER_VALEURS_TOUCHE_RACC);
		trierMenu.setEnabled(false);
		add(trierMenu);
	}
	/**
	 *  Activer ou d√©sactiver les items du menu selon la s√©lection. 
	 */
	private void rafraichirMenus() {
		trierMenu.setEnabled(true);
	}
	/**
	 * Cr√©er un √©l√©ment de menu √  partir d'un champs principal et ses √©l√©ments
	 * @param titleKey champs principal
	 * @param itemKeys √©l√©ments
	 * @return le menu
	 */
	
	private static JMenu creerMenu(String titleKey,String[] itemKeys) {
		JMenu menu = new JMenu(LangueConfig.getResource(titleKey));
		for(int i=0; i < itemKeys.length; ++i) {
			menu.add(new JMenuItem(LangueConfig.getResource(itemKeys[i])));
		}
		return menu;
	}
	
	private static JMenu creerMenu(String titleKey,JRadioButtonMenuItem[] itemKeys) {
		JMenu menu = new JMenu(LangueConfig.getResource(titleKey));
		for(int i=0; i < itemKeys.length; ++i) {
			menu.add(new JRadioButtonMenuItem(LangueConfig.getResource(itemKeys[i].getText())));
			groupJRadioButtonMenuItem.add(itemKeys[i]);
		}
		return menu;
	}
	/**
	 * DÈsÈlectionne tous les radiobuttons sauf celui cochÈ
	 * @param currentSelection le radiobutton sÈlectionnÈ
	 */
	private void clearSelections(int currentSelection){
		for(int i = 0; i< trierMenu.getItemCount(); i++){
			trierMenu.getItem(i).setSelected(false);
		}
		trierMenu.getItem(currentSelection).setSelected(true);
	}
	
}