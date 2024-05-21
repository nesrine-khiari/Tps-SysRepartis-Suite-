package tp6;
import java.io.*;
import java.net.*;
import java.util.Scanner;

class SocketClient {
    public static void main(String argv[]) {
        int port = 1234;
        String host = "";
        Scanner keyb = new Scanner(System.in);
        // .............................................................
        // Demander à l'utilisateur le nom du serveur
        System.out.print("Nom du serveur : ");
        host = keyb.next();

        // Demander à l'utilisateur le port du serveur
        System.out.print("Port du serveur : ");

        try {
            // Lire le port saisi par l'utilisateur
            port = keyb.nextInt();
        } catch (NumberFormatException e) {
            // Gérer l'exception si l'entrée n'est pas un entier
            System.err.println("Le second paramètre n'est pas un entier.");
            System.exit(-1);
        }
        // .............................................................
        try {
            // Résoudre l'adresse IP du serveur à partir du nom de l'hôte
            InetAddress adr = InetAddress.getByName(host);
            // .............................................................
            
            // Établir une connexion avec le serveur en utilisant l'adresse et le port spécifiés
            Socket socket = new Socket(adr, port);
            // .............................................................
            
            // Créer des flux d'objet pour la communication avec le serveur
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            
            // Envoyer une chaîne au serveur via la sortie d'objet
            output.writeObject(new String("ma première socket"));
            
            // Lire la réponse du serveur depuis l'entrée d'objet
            String chaine = (String) input.readObject();
            System.out.println(" recu du serveur : " + chaine);
        } catch (Exception e) {
            // Gérer toute exception survenue pendant l'exécution
            System.err.println("Erreur : " + e);
        }
    }
}
