import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Thread.sleep;

/**
 * @author Tibet Türkmen, Muhammed Yunus Gülbasar, Can Avsar, Samet Araz
 */

//Die Klasse repräsentiert das Spielfeld und enthält die Logik des Spiels sowie Informationen über die Spieler.


public class Spielfeld {
    private final int zeile = 8;
    private final int spalte = 8;
    private Feld[][] felder = new Feld[zeile][spalte];

    private Spieler SpielerB;
    private Spieler SpielerW;

    private Spieler aktuell;

    private Random r = new Random();




    //Konstruktor des Spielfelds startet die Spieler und stellt sicher, dass sie nicht auf derselben Position sind.
    public Spielfeld() {
        this.SpielerB = spieler1Hinzufügen();
        this.SpielerW = spieler2Hinzufügen();
    }



    public Spieler getSpieler(){
        return aktuell;
    }


    //Methode zum Hinzufügen von Spieler1 mit festen Werten
    public Spieler spieler1Hinzufügen() {
        Fraction fraction = new Fraction(3,3,BigInteger.ZERO,BigInteger.ONE);
        Spieler spieler = new Spieler(new Feld(3,3),"B",fraction);
        return spieler;

    }
    //Methode zum Hinzufügen von Spieler2 mit festen Werten
    public Spieler spieler2Hinzufügen() {
        Fraction fraction = new Fraction(4,4,BigInteger.ZERO,BigInteger.ONE);
        Spieler spieler = new Spieler( new Feld(4,4),"W",fraction);
        return spieler;

    }
    private boolean grenze(Fraction f) {
        if (f.getNumerator().intValue() >= 10 && f.getNumerator().intValue() <= 999
                && f.getDenominator().intValue() >= 10 && f.getDenominator().intValue() <= 999) {

            double value = (double) f.getNumerator().intValue() / f.getDenominator().intValue();
            if (value > 1 && value < 2) {
                return true;
            }
        }
        return false;
    }



    // Methode zum Erstellen des Spielfelds und Platzieren von Spielern und Brüchen
    public void feldererstellen()throws InterruptedException {
        for (int i = 0; i < felder.length; i++) {
            for (int j = 0; j < felder[i].length; j++) {
                // Platzieren der Spieler
                if (SpielerB.getLocation().getX() == i && SpielerB.getLocation().getY() == j) {
                    felder[i][j] = SpielerB.getLocation();
                } else if (SpielerW.getLocation().getX() == i && SpielerW.getLocation().getY() == j) {
                    felder[i][j] = SpielerW.getLocation();
                } else {
                    Fraction fraction;
                    while (true) {
                        fraction = new Fraction(i, j, BigInteger.valueOf(10 + r.nextLong(990)), BigInteger.valueOf(10 + r.nextLong(990)));
                        if (grenze(fraction)) {
                            felder[i][j] = fraction;
                            if (fraction != null) {
                                felder[i][j] = fraction;
                            } 
                            else {
                                // Hier könntest du logische Werte oder einen Platzhalter setzen, je nach Bedarf
                                felder[i][j] = null;
                            }
                            break;
                        }
                    }
                }
            }
            //System.out.println();
        }
        felderschreiben();
    }
    public void felderschreiben() throws InterruptedException {
        // ANSI escape codes
        String ANSI_RESET = "\u001B[0m";
        String ANSI_BLACK = "\u001B[30m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_PURPLE = "\u001B[35m";
        String ANSI_CYAN = "\u001B[36m";
        String ANSI_WHITE = "\u001B[37m";

        // Spielerscores anzeigen als double-Wert
        System.out.println(ANSI_CYAN + "SCORES:" + ANSI_RESET);
        Thread.sleep(300);
        System.out.printf("Spieler %s: %.2f%n", ANSI_RED + SpielerB.getName() + ANSI_RESET, SpielerB.getSumme().toDouble());
        Thread.sleep(300);
        System.out.printf("Spieler %s: %.2f%n", ANSI_GREEN + SpielerW.getName() + ANSI_RESET, SpielerW.getSumme().toDouble());
        Thread.sleep(300);

        for (int i = 0; i < felder.length; i++) {
            System.out.print("--------");
        }
        System.out.println();

        for (int i = 0; i < felder.length; i++) {
            for (int j = 0; j < felder[i].length; j++) {
                // Anzeigen der Spielerpositionen
                if (SpielerB.getLocation().getX() == i && SpielerB.getLocation().getY() == j) {
                    System.out.print(  ANSI_RED + SpielerB.getName() + ANSI_RESET + "    ");
                } else if (SpielerW.getLocation().getX() == i && SpielerW.getLocation().getY() == j) {
                    System.out.print(  ANSI_GREEN + SpielerW.getName() + ANSI_RESET + "    ");
                } else {
                    // Anzeigen der Brüche
                    if (felder[i][j] != null) {
                        System.out.printf("%-5s", felder[i][j]);
                    } else {
                        // Hier könntest du logische Werte oder einen Platzhalter setzen, je nach Bedarf
                        System.out.print(ANSI_BLUE + "0   " + ANSI_RESET);
                    }
                }
                System.out.print("\t");

            }
            System.out.println();

        }

        for (int i = 0; i < felder.length; i++) {
            System.out.print("--------");
        }
        System.out.println();
        Thread.sleep(300);
    }










    public void go(int x, int y) {
        int neueX = aktuell.getLocation().getX() + x;
        int neueY = aktuell.getLocation().getY() + y;

        // Überprüfen, ob die neue Position gültig ist, um IndexOutOfBounds zu vermeiden
        if (istGültigePosition(neueX, neueY)) {
            // Wenn sich in dem Bereich, wohin unser Spieler sich bewegt hat, eine Fraction befindet,
            // fügen wir das summe(Fraction) Variable, die sich in der Spieler Klasse befindet
            if (felder[neueX][neueY] instanceof Fraction) {
                aktuell.addSumme((Fraction) felder[neueX][neueY]);
            }

            // Array felder wird aktualisiert, die Position des Spielers wird aktualisiert
            felder[neueX][neueY] = aktuell.getLocation();

            // Die alte Position des Spielers wird auf ein Werte-Objekt gesetzt
            felder[aktuell.getLocation().getX()][aktuell.getLocation().getY()] = null;

            // Spielerobjekt wird auf die neue Position aktualisiert
            aktuell.getLocation().setX(neueX);
            aktuell.getLocation().setY(neueY);
        }
    }

    // ... (weiterer Code)

    //Um IndexOutOfBounds zu vermeiden, prüfen Sie, ob die neue Position gültig ist.
    private boolean istGültigePosition(int x, int y) {
        return x >= 0 && x < zeile && y >= 0 && y < spalte;
    }


    // Methode zur Überprüfung, ob der Spieler nach Norden bewegen kann
    public boolean kannnord(){
        Spieler SpielerAndere = getSpieler() == SpielerB ? SpielerW : SpielerB;
        if (getSpieler().getLocation().getX() == 0){
            return false;
        }
        // Überprüfen, ob sich der Spieler auf demselben Feld wie Spieler1 oder Spieler2 befindet
        
        else if (getSpieler().getLocation().getX() - 1 == SpielerAndere.getLocation().getX() && getSpieler().getLocation().getY() == SpielerAndere.getLocation().getY()) {
            return false;

        }

        else {
            return true;
        }
    }

    // Methode zur Überprüfung, ob der Spieler nach Süden bewegen kann
    public boolean kannsüd(){
        Spieler SpielerAndere = getSpieler() == SpielerB ? SpielerW : SpielerB;
        if (getSpieler().getLocation().getX() == felder.length - 1){

            return false;

        }
        else if (getSpieler().getLocation().getX() + 1 == SpielerAndere.getLocation().getX() && getSpieler().getLocation().getY() == SpielerAndere.getLocation().getY()) {
            return false;

        }
        else {
            return true;
        }

    }

    // Methode zur Überprüfung, ob der Spieler nach Osten bewegen kann
    public boolean kannost(){
        //der andere
        Spieler SpielerAndere = getSpieler() == SpielerB ? SpielerW : SpielerB;
        if(getSpieler().getLocation().getY() == felder.length - 1){
            return false;
        }
        else if (getSpieler().getLocation().getX()  == SpielerAndere.getLocation().getX() && getSpieler().getLocation().getY() + 1 == SpielerAndere.getLocation().getY()) {
            return false;

        }
        else {
            return true;
        }

    }
    // Methode zur Überprüfung, ob der Spieler nach Westen bewegen kann
    public boolean kannwest(){
        //der andere
        Spieler SpielerAndere = getSpieler() == SpielerB ? SpielerW : SpielerB;
        if (getSpieler().getLocation().getY() == 0){
            return false;
        }
        else if (getSpieler().getLocation().getX()  == SpielerAndere.getLocation().getX() && getSpieler().getLocation().getY() - 1 == SpielerAndere.getLocation().getY()) {
            return false;

        }
        else {
            return true;
        }
    }
    // Methode für die Bewegung des Spielers nach Norden
    public void bewegungNord() {

            go(-1,0);



    }

    // Methode für die Bewegung des Spielers nach Süden
    public void bewegungSued() {
            go(1,0);
    }
    // Methode für die Bewegung des Spielers nach Osten
    public void bewegungOst() {

            go(0,1);
    }

    // Methode für die Bewegung des Spielers nach westen
    public void bewegungWest() {

            go(0,-1);
    }



    // Methode für die Bewegung des Spielers nach Nordosten
    public void bewegungNordOst() {

            go(-1,1);
    }

    // Methode für die Bewegung des Spielers nach Südwesten
    public void bewegungSuedWest()
    {

            go(1,-1);


    }
    // Methode zur Ausgabe der Spielanleitung
    public void SpielAnleitung(){
        System.out.println("*************************");
        System.out.println("Willkommen zum Spiel");
        System.out.println("*************************");
        System.out.println("Um das Spiel zu beginnen drücken Sie bitte r");
        System.out.println("Um das Spiel zu beenden drücken Sie bitte a");
    }

    public void Spielerbewegen() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        
        String west = "w";
        String West = "W";
        String ost = "o";
        String Ost = "O";
        String nord = "n";
        String Nord = "N";
        String sued = "s";
        String Sued = "S";
        String nordost = "no";
        String Nordost = "NO";
        String südwest = "sw";
        String Südwest = "SW";


        // Schleife, die so lange läuft, bis die Summe eines der Spieler >= 47 ist
        for (int zug = 1; SpielerB.getSumme().toDouble() < 47 && SpielerW.getSumme().toDouble() < 47; zug++) {

            // Abwechselnde Züge für Spieler1 und Spieler2
            if (zug % 2 == 1) {
                
                aktuell = SpielerB; // Aktuellen Spieler auf Spieler1 setzen
                // Printf den Namen des Spielers 
                System.out.printf("Spieler %s ist an der Reihe. Bitte geben Sie eine Richtung ein ([N],[O],[S],[W],[NO]): ", aktuell.getName());
                String wort = scanner.nextLine();

                if (wort.equals(west) || wort.equals(West)) {
                    if (kannwest()){
                        bewegungWest();
                        //System.out.println(Spieler1.getSumme());
                    }
                    else {
                        System.out.println("West ist nicht offen");
                        zug--;
                    }

                } else if (wort.equals(ost) || wort.equals(Ost)) {
                    if (kannost()){
                        bewegungOst();
                        //System.out.println(Spieler1.getSumme());
                    }
                    else {
                        System.out.println("Ost ist nicht offen");
                        zug--;
                    }
                } else if (wort.equals(nord) || wort.equals(Nord)) {
                    if (kannnord()){
                        bewegungNord();
                        //System.out.println(Spieler1.getSumme());
                    }
                    else {
                        System.out.println("Nord ist nicht offen");
                        zug --;
                    }
                } else if (wort.equals(sued) || wort.equals(Sued)) {
                    if (kannsüd()){
                        bewegungSued();
                    }
                    else {
                        System.out.println("Süd ist nicht offen");
                        zug --;
                    }

                } else if (wort.equals(nordost) || wort.equals(Nordost)) {
                    if (kannnord() && kannost()){
                        //System.out.println(Spieler1.getSumme());
                        bewegungNordOst();
                    }
                    else {
                        System.out.println("Nordost ist nicht offen");
                        zug --;
                    }

                } else if (wort.equals(südwest) || wort.equals(Südwest) ) {
                    System.out.println("Schwarz kann nicht nach unten links (SW). Bitte erneut versuchen");
                    zug --;

                } else if (wort.equals("a")) {
                    System.out.println("Das Spiel ist beendet");
                    break;

                } else {
                    System.out.println("Ungültige Eingabe. Bitte erneut eingeben.");
                    zug --;
                }


            } else {
                aktuell = SpielerW; // Aktuellen Spieler auf Spieler2 setzen
                // Printf den Namen des Spielers 
                System.out.printf("Spieler %s ist an der Reihe. Bitte geben Sie eine Richtung ein ([N],[O],[S],[W],[SW]): ", aktuell.getName());
                String wort = scanner.nextLine();

                if (wort.equals(west) || wort.equals(West)) {
                    if (kannwest()){
                        bewegungWest();
                    }
                    else {
                        System.out.println("West ist nicht offen");
                        zug --;
                    }
                } else if (wort.equals(ost) || wort.equals(Ost)) {
                    if (kannost()){
                        bewegungOst();
                    }
                    else {
                        System.out.println("Ost ist nicht offen");
                        zug --;
                    }
                } else if (wort.equals(nord) || wort.equals(Nord)) {
                    if (kannnord()){
                        bewegungNord();
                    }
                    else {
                        System.out.println("Nord ist nicht offen");
                        zug --;
                    }
                } else if (wort.equals(sued) || wort.equals(Sued)) {
                    if (kannsüd()){
                        bewegungSued();
                    }
                    else {
                        System.out.println("Süd ist nicht offen");
                        zug --;
                    }

                }
                else if (wort.equals(nordost) || wort.equals(Nordost)) {
                    System.out.println("Weiß kann nicht nach oben rechts (NO). Bitte erneut versuchen");
                    zug--;

                } else if (wort.equals(südwest) || wort.equals(Südwest) ) {
                    if (kannsüd() && kannwest()){
                        bewegungSuedWest();
                    }
                    else {
                        System.out.println("Süd ist nicht offen");
                        zug--;
                    }


                }
                else if (wort.equals("a")){
                    System.out.println("Das Spiel ist beendet");
                    break;

                }
                else {
                    System.out.println("Ungültige Eingabe. Bitte erneut eingeben.");
                    zug --;
                }

            }

            // Spielfeld nach jedem Zug ausgeben (optional, je nach Bedarf)
            felderschreiben();
        }
        scanner.close();
        // Spielende anzeigen
        if (SpielerB.getSumme().toDouble() > 47) {
            System.out.println("Spieler B hat mit " + SpielerB.getSumme().toDouble() + " Punkten gewonnen");
            System.exit(0);
        } else if (SpielerW.getSumme().toDouble() > 47) {
            System.out.println("Spieler W hat mit " + SpielerW.getSumme().toDouble() + " Punkten gewonnen");
            System.exit(0);

        }
    }

}














