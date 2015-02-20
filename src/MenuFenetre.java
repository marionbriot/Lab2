/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: MenuFenetre.java
Date créé: 2013-05-03
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Patrice Boucher
2013-05-03 Version initiale
 *******************************************************/  

/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: MenuFenetre.java
Date créé: 2013-05-03
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
 * Crée le menu de la fenêtre de l'applicationé
 */
public class MenuFenetre extends JMenuBar{
	private static final long serialVersionUID = 1536336192561843187L;
	private static final int  MENU_DESSIN_ARRETER_TOUCHE_MASK  = ActionEvent.CTRL_MASK;
	private static final char MENU_DESSIN_ARRETER_TOUCHE_RACC  = KeyEvent.VK_A;
	private static final int  MENU_DESSIN_DEMARRER_TOUCHE_MASK = ActionEvent.CTRL_MASK;
	private static final char MENU_DESSIN_DEMARRER_TOUCHE_RACC = KeyEvent.VK_D;
	private static final int  MENU_FICHIER_QUITTER_TOUCHE_MASK = ActionEvent.CTRL_MASK;
	private static final char MENU_FICHIER_QUITTER_TOUCHE_RACC = KeyEvent.VK_Q;
	private static final int  MENU_TRIER_VALEURS_TOUCHE_MASK = ActionEvent.CTRL_MASK;
	private static final char MENU_TRIER_VALEURS_TOUCHE_RACC = KeyEvent.VK_R;
	private static final String
	MENU_FICHIER_TITRE = "app.frame.menus.file.title",
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
	//Le triage suivant se fait de la facon suivante : carr� -> rectangle -> cercle -> ovale -> ligne
	MENU_TRIER_VALEURS_PAR_FORMES_INVERSE = "app.frame.menus.sortBy.inverseTypeOfShapes",
	//le triage suivant se fait de la facon suivante : ligne -> ovale -> cercle -> rectangle -> carr�
	MENU_TRIER_VALEURS_PAR_DISTANCE_MAXIMAL = "app.frame.menus.sortBy.MaxDistance",
	MENU_TRIER_VALEURS_PAR_LARGEUR_CROISSANTE = "app.frame.menus.sortBy.Width",
	MENU_TRIER_VALEURS_PAR_LARGEUR_DECROISSANT = "app.frame.menus.sortBy.inverseWidth",
	MENU_TRIER_VALEURS_PAR_HAUTEUR_CROISSANT = "app.frame.menus.sortBy.Height",
	MENU_TRIER_VALEURS_PAR_HAUTEUR_DECROISSANTE = "app.frame.menus.sortBy.inverseHeight",
	MENU_TRIER_VALEURS_PAR_ORDRE_ORIGINAL = "app.frame.menus.sortBy.OriginalOrder";
	//menu_trier_
	private static final String MESSAGE_DIALOGUE_A_PROPOS = "app.frame.dialog.about";  
	private JMenuItem arreterMenuItem, demarrerMenuItem;
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
	
	CommBase comm; // Pour activer/désactiver la communication avec le serveur
	/**
	 * Constructeur
	 */
	public MenuFenetre(CommBase comm) {
		this.comm = comm;
		addMenuDessiner();
		addMenuFichier();
		addMenuTrierOptions();
		addMenuAide();
	}
	/**
	 *  Créer le menu "Draw". 
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
	 * Créer le menu "File". 
	 */
	protected void addMenuFichier() {
		JMenu menu = creerMenu(MENU_FICHIER_TITRE, new String[] { MENU_FICHIER_QUITTER });
		menu.getItem(0).addActionListener(new ActionListener() {
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
				KeyStroke.getKeyStroke(MENU_FICHIER_QUITTER_TOUCHE_RACC,
						MENU_FICHIER_QUITTER_TOUCHE_MASK));
		add(menu);
	}
	/**
	 *  Créer le menu "Help". 
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
	 *  Créer le menu "Trier". 
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
				
		
		JMenu menu = creerMenu (MENU_TRIER_TITRE, new JRadioButtonMenuItem[] { sortBySequenceCroissante, sortBySequenceDecroissante, sortByAireFormeCroissante,sortByAireFormeDecroissante,sortByTypeFormes,sortByTypeFormesInverse, sortByDistanceMaximale,sortByLargeurCroissante, sortByLargeurDecroissante,  sortByHauteurCroissante, sortByHauteurDecroissante, sortByOrdreServeur });
		menu.getItem(0).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			System.out.println("Test 1er objet");
			
			}
		});
		menu.getItem(1).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			System.out.println("Test 2e objet");
			groupJRadioButtonMenuItem.clearSelection();
			}
		});
    	menu.getItem(2).addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
			System.out.println("Test 3e objet");
			groupJRadioButtonMenuItem.clearSelection();
			}
		});
		menu.getItem(3).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			System.out.println("Test 4e objet");
			}
		});
		menu.getItem(4).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			System.out.println("Test 5e objet");
			}
		});
		menu.getItem(5).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			System.out.println("Test 6e objet");
			}
		});
		menu.getItem(6).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			System.out.println("Test 7e objet");
			}
		});
		menu.getItem(7).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			System.out.println("Test 8e objet");
			}
		});
		menu.getItem(8).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			System.out.println("Test 9e objet");
			}
		});
		menu.getItem(9).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			System.out.println("Test 10e objet");
			}
		});
		menu.getItem(10).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			System.out.println("Test 11e objet");
			}
		});
		menu.getItem(11).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			System.out.println("Test 12e objet");
			}
		});
		KeyStroke.getKeyStroke(MENU_TRIER_VALEURS_TOUCHE_MASK,
				MENU_TRIER_VALEURS_TOUCHE_RACC);
		add(menu);
	}
	/**
	 *  Activer ou désactiver les items du menu selon la sélection. 
	 */
	private void rafraichirMenus() {
		demarrerMenuItem.setEnabled(!comm.isActif());
		arreterMenuItem.setEnabled(comm.isActif());
	}
	/**
	 * Créer un élément de menu �  partir d'un champs principal et ses éléments
	 * @param titleKey champs principal
	 * @param itemKeys éléments
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
	
}