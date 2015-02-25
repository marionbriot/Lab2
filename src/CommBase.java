/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: CommBase.java
Date crï¿½ï¿½ï¿½ï¿½: 2013-05-03
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Patrice Boucher
2013-05-03 Version initiale
 *******************************************************/

import java.beans.PropertyChangeListener;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 * Base d'une communication via un fil d'exï¿½ï¿½cution parallï¿½ï¿½le.
 */
public class CommBase {

 private final int DELAI = 1000;
 private SwingWorker threadComm = null;
 private PropertyChangeListener listener = null;
 private boolean isActif = false;
 private String hote;
 private int port;
 Socket socket;
 private final String defaultServeurInfo = "localhost:10000";


 /**
  * Attributs nécessaires à la gestion du serveur (envoie de commande, retour
  * de commande, etc)
  */
 PrintStream sendCommand = null;
 DataInputStream reponseCommand = null;
 DataInputStream inputLine = null;

 /**
  * Constructeur
  */
 public CommBase() {
 }

 /**
  * Dï¿½finir le rï¿½cepteur de l'information reï¿½ue dans la communication
  * avec le serveur
  * 
  * @param listener
  *            sera alertï¿½ï¿½ lors de l'appel de "firePropertyChanger" par
  *            le SwingWorker
  */
 public void setPropertyChangeListener(PropertyChangeListener listener) {
  this.listener = listener;
 }

 /**
  * Dï¿½ï¿½marre la communication
  */
 public void start() {
  creerCommunication();
 }

 /**
  * Arrï¿½ï¿½te la communication
  */
 public void stop() {
  
  fermerConnexion();
  if(threadComm!=null)
   threadComm.cancel(true); 
  isActif = false;
 }

 /**
  * Crï¿½ï¿½er le nï¿½ï¿½cessaire pour la communication avec le serveur
  */
 protected void creerCommunication() {

  String infoConn = JOptionPane
    .showInputDialog("Quel est le nom d'hôte et le port du serveur de formes?", defaultServeurInfo);
  // sépare les informations entrées par le client (hote et port)
  String[] infos = infoConn.split(":");
  hote = infos[0];
  port = Integer.parseInt(infos[1]);

  try {

   // initialisation d'un socket
   socket = new Socket(hote, port);

   // initilisation des attributs nécessaires à la gestion du serveur
   sendCommand = new PrintStream(socket.getOutputStream());
   reponseCommand = new DataInputStream(socket.getInputStream());
   inputLine = new DataInputStream(new BufferedInputStream(System.in));

  }

  catch (UnknownHostException  e) {
   JOptionPane.showMessageDialog(null, "L'hôte auquel vous tentez de vous connecter n'existe pas !");
   return;
  } catch (IOException e) {
   JOptionPane.showMessageDialog(null, "Le serveur " + hote + " ne répond pas sur le port " + port + " .");
   return;
  }

  // Crï¿½ï¿½e un fil d'exï¿½ï¿½cusion parallï¿½ï¿½le au fil courant,
  threadComm = new SwingWorker() {
   @Override
   protected Object doInBackground() throws Exception {

    System.out.println("Le fils d'execution parallele est lance");

     int compteur = 0;
     while(compteur < 10){
     
     compteur ++;
     Thread.sleep(DELAI);

     // C'EST DANS CETTE BOUCLE QU'ON COMMUNIQUE AVEC LE SERVEUR
     // envoie de la commande "GET" au serveur
     sendCommand.println("GET");
     // Store la réponse du serveur dans un String
     String reponse = reponseCommand.readLine();
     reponse = reponseCommand.readLine();
     if (listener != null)
      // on envoie la reponse à la classe FenetrePrincipale
      firePropertyChange("ligne", null, reponse);
    }
     if(compteur >= 10){
      stop();
     }
     return null;
     // La mï¿½ï¿½thode suivante alerte l'observateur
   
    // return null;
   }
  };
  if (listener != null)
   threadComm.addPropertyChangeListener(listener); // La mï¿½ï¿½thode
               // "propertyChange"
               // de
               // ApplicationFormes
               // sera donc
               // appelï¿½ï¿½e
               // lorsque le
               // SwinkWorker
               // invoquera la
               // mï¿½ï¿½thode
               // "firePropertyChanger"
  threadComm.execute(); // Lance le fil d'exï¿½ï¿½cution parallï¿½ï¿½le.
  isActif = true;
 }

 /**
  * @return si le fil d'exï¿½ï¿½cution parallï¿½ï¿½le est actif
  */
 public boolean isActif() {
  return isActif;
 }
 
 public void fermerConnexion(){
  //On notifie le serveur d'arreter la connexion
  try {
   sendCommand.println("END");
   if(isActif()){
	    JOptionPane.showMessageDialog(null, "Vous êtes maintenant déconnecté du serveur : " + hote);
	   }
  } 
  catch (NullPointerException e1){
      JOptionPane.showMessageDialog(null, "À la prochaine!");
      System.exit(0);
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
}