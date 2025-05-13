import java.util.Scanner;
/**
 * @author Tibet Türkmen, Muhammed Yunus Gülbasar, Can Avsar, Samet Araz
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {
//Eine Instanz der Spielfeldklasse wird erzeugt.
        Spielfeld spielfeld = new Spielfeld();
//Zeigt die Spielanleitung an.
        spielfeld.SpielAnleitung();
//Erzeugt eine Scanner-Objekte für Benutzereingabe.
        Scanner scanner = new Scanner(System.in);
//Erste Benutzereingabe
        String x = scanner.nextLine();
//Zeigt an, ob das Spiel gestartet ist.
        boolean spielGestartet = false;


        // Hauptspielschleife
        while (!spielGestartet) {
            //Verarbeiten Sie die Eingabe des Benutzers basierend auf der ausgewählten Option.
            switch (x.toLowerCase()) {
                case "r":
                    //Starten Sie das Spiel, indem Sie Spielfelder erstellen und den Spieler bewegen.
                    spielfeld.feldererstellen();
                    spielfeld.Spielerbewegen();
                    System.exit(0);

//Beende das Spiel und beende das Programm.
                case "a":
                    System.out.println("Das Spiel ist beendet");
                    System.exit(0);

//Behandle ungültige Eingabe: Zeige eine Nachricht und die Spielanleitung an.
                default:
                    System.out.println("Ungültige Eingabe.");
                    spielfeld.SpielAnleitung();
                    x = scanner.nextLine();

            }


        }

    }
}
