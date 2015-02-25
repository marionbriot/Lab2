/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: CommBase.java
Date cr����: 2013-05-03
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
 * Base d'une communication via un fil d'ex��cution parall��le.
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
  * Attributs n�cessaires � la gestion du serveur (envoie de commande, retour
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
  * D�finir le r�cepteur de l'information re�ue dans la communication
  * avec le serveur
  * 
  * @param listener
  *            sera alert�� lors de l'appel de "firePropertyChanger" par
  *            le SwingWorker
  */
 public void setPropertyChangeListener(PropertyChangeListener listener) {
  this.listener = listener;
 }

 /**
  * D��marre la communication
  */
 public void start() {
  creerCommunication();
 }

 /**
  * Arr��te la communication
  */
 public void stop() {
  
  fermerConnexion();
  if(threadComm!=null)
   threadComm.cancel(true); 
  isActif = false;
 }

 /**
  * Cr��er le n��cessaire pour la communication avec le serveur
  */
 protected void creerCommunication() {

  String infoConn = JOptionPane
    .showInputDialog("Quel est le nom d'h�te et le port du serveur de formes?", defaultServeurInfo);
  // s�pare les informations entr�es par le client (hote et port)
  String[] infos = infoConn.split(":");
  hote = infos[0];
  port = Integer.parseInt(infos[1]);

  try {

   // initialisation d'un socket
   socket = new Socket(hote, port);

   // initilisation des attributs n�cessaires � la gestion du serveur
   sendCommand = new PrintStream(socket.getOutputStream());
   reponseCommand = new DataInputStream(socket.getInputStream());
   inputLine = new DataInputStream(new BufferedInputStream(System.in));

  }

  catch (UnknownHostException  e) {
   JOptionPane.showMessageDialog(null, "L'h�te auquel vous tentez de vous connecter n'existe pas !");
   return;
  } catch (IOException e) {
   JOptionPane.showMessageDialog(null, "Le serveur " + hote + " ne r�pond pas sur le port " + port + " .");
   return;
  }

  // Cr��e un fil d'ex��cusion parall��le au fil courant,
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
     // Store la r�ponse du serveur dans un String
     String reponse = reponseCommand.readLine();
     reponse = reponseCommand.readLine();
     if (listener != null)
      // on envoie la reponse � la classe FenetrePrincipale
      firePropertyChange("ligne", null, reponse);
    }
     if(compteur >= 10){
      stop();
     }
     return null;
     // La m��thode suivante alerte l'observateur
   
    // return null;
   }
  };
  if (listener != null)
   threadComm.addPropertyChangeListener(listener); // La m��thode
               // "propertyChange"
               // de
               // ApplicationFormes
               // sera donc
               // appel��e
               // lorsque le
               // SwinkWorker
               // invoquera la
               // m��thode
               // "firePropertyChanger"
  threadComm.execute(); // Lance le fil d'ex��cution parall��le.
  isActif = true;
 }

 /**
  * @return si le fil d'ex��cution parall��le est actif
  */
 public boolean isActif() {
  return isActif;
 }
 
 public void fermerConnexion(){
  //On notifie le serveur d'arreter la connexion
  try {
   sendCommand.println("END");
   if(isActif()){
	    JOptionPane.showMessageDialog(null, "Vous �tes maintenant d�connect� du serveur : " + hote);
	   }
  } 
  catch (NullPointerException e1){
      JOptionPane.showMessageDialog(null, "� la prochaine!");
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