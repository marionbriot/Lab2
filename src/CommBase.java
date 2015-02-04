/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: CommBase.java
Date crÃ©Ã©: 2013-05-03
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Patrice Boucher
2013-05-03 Version initiale
 *******************************************************/  

import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 * Base d'une communication via un fil d'exÃ©cution parallÃ¨le.
 */


	public class CommBase {
	public String formeInfo;
	private final String defaultServeurInfo = "localhost:10000";
	private Socket socket;//Ce qui contient la connexion
	private final int DELAI = 1000;
	private SwingWorker threadComm =null;
	private PropertyChangeListener listener = null;
	private boolean isActif = false;

	/**
	 * Constructeur
	 */
	public CommBase(){
	}

	/**
	 * Définir le récepteur de l'information reçue dans la communication avec le serveur
	 * @param listener sera alertÃ© lors de l'appel de "firePropertyChanger" par le SwingWorker
	 */
	public void setPropertyChangeListener(PropertyChangeListener listener){
		this.listener = listener;
	}

	/**
	 * DÃ©marre la communication
	 */
	public void start(){
		creerCommunication();
	}

	/**
	 * ArrÃªte la communication
	 */
	public void stop(){
		fermerConnexion();
		if(threadComm!=null)
			threadComm.cancel(true); 
		isActif = false;
	}
	/**
	 * Cette méthode permet de se connecter au serveur 
	 */
	protected void connecteAuServeur(){
		// On se connecte au serveur ici
		// Tant que la connexion n'est pas valide 
		while(true){
			//Affichage de la boite de dialogue pour initialiser la connection au serveur de forme
			//Utilise la valeur par default definie en global
			String serveurInfo = JOptionPane.showInputDialog(null,
					"Quel est le nom d'hote et le port du serveur de formes?",
					defaultServeurInfo);

			System.out.println("L'information du serveur de forme est: \"" + serveurInfo + "\"");

			//On sépare en deux le texte écrit dans la fenetre 
			String[] partiesInfo = serveurInfo.split(":");

			String hote = partiesInfo[0];
			//Parsage en int pour mettre dans le socket
			int port = Integer.parseInt(partiesInfo[1]);

			System.out.println("Hote: " + hote);
			System.out.println("Port: " + port);

			try {
				//Se connecte au serveur et arrete la boucle si il y a connexion
				socket = new Socket(InetAddress.getByName(hote), port);
				break;
			}catch (UnknownHostException e) {
				//e.printStackTrace();
				//affiche un message d'erreur si la connexion n'a pas été éffectuée 
				JOptionPane.showMessageDialog(null, "L'hôte " + hote + " n'est pas accessible : " );

			}catch (IOException e) {
				//e.printStackTrace();
				//affiche un message d'erreur si la connexion n'a pas été éffectuée 
				JOptionPane.showMessageDialog(null, "Le port " + port + " n'est pas accessible" );
			}
		}
	}

	/**
	 * Méthode qui créer la communication avec le serveur
	 */
	protected void creerCommunication(){		
		// Cree un fil d'execusion parallele au fil courant
		threadComm = new SwingWorker(){
			@Override
			protected Object doInBackground() throws Exception {
				System.out.println("Le fils d'execution parallele est lance");

				// On se connnecte au serveur
				connecteAuServeur();

				while(true){
					Thread.sleep(DELAI);

					// C'EST DANS CETTE BOUCLE QU'ON COMMUNIQUE AVEC LE SERVEUR
					formeInfo = getLigneServeur();

					//La methode suivante alerte l'observateur
					if(listener!=null)
						firePropertyChange("ligne", null, (Object) formeInfo);
				}
				//return null;
			}
		};
		if(listener!=null)
			threadComm.addPropertyChangeListener(listener); // La mÃ©thode "propertyChange" de ApplicationFormes sera donc appelÃ©e lorsque le SwinkWorker invoquera la mÃ©thode "firePropertyChanger" 		
		threadComm.execute(); // Lance le fil d'exÃ©cution parallÃ¨le.
		isActif = true;
	}

	/**
	 * @return si le fil d'exÃ©cution parallÃ¨le est actif
	 */
	public boolean isActif(){
		return isActif;
	}
	/**
	 * Méthode qui permet d'arreter la connexion avec le serveur 
	 */
	public void fermerConnexion(){
		//On notifie le serveur d'arreter la connexion
		try {
			BufferedWriter wr;
			wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
			wr.write("END\n");
			wr.flush();

		} catch (UnsupportedEncodingException e1) {
			//e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Vous êtes maintenant déconnecté du serveur  ");
		} catch (IOException e1) {
			//e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Vous êtes maintenant déconnecté du serveur  ");
		}
		//On met un temps d'attente entre la commande END et la fermeture du socket 
		try {
			Thread.sleep(DELAI);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Méthode qui permet d'avoir les lignes en provenance du serveur
	 * @return la ligne du serveur
	 */
	public String getLigneServeur(){
		//On créer un écriveur qui écrit sur le socket
		BufferedWriter wr;
		try {
			wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
			//On envoit la commande GET au serveur
			wr.write("GET\n");
			//On nétoie l'écriveur
			wr.flush();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//On créer un lecteur qui lit le socket
		BufferedReader rd;
		String tmp = null;
		try {
			rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//On lit la premiere ligne du lecteur 
			tmp = rd.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tmp;
	}
}
