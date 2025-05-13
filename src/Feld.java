import java.util.Random;



/**
 * @author Tibet Türkmen, Muhammed Yunus Gülbasar, Can Avsar, Samet Araz
 */
//Ein zweidimensionales Feld mit Koordinaten (x, y) wird in der Klasse dargestellt.
public class Feld{


    private int x; //Zeile

    private int y; //Spalte



//Initialisiert das Feld mit den x- und y-Koordinaten, die übergeben wurden.
    public Feld(int x, int y){
        this.x = x;
        this.y = y;
    }

//Getter-Methode für x: Gibt die x-Koordinate des Feldes zurück.
    public int getX() {
        return x;
    }

//Setter-Methode für x: Setzt die x-Koordinate des Feldes.
    public void setX(int x) {
        this.x = x;
    }
//Getter-Methode für y: Gibt die y-Koordinate des Feldes zurück.
    public int getY() {
        return y;
    }
//Setter-Methode für y: Setzt die y-Koordinate des Feldes.
    public void setY(int y) {
        this.y = y;
    }
//Die toString-Methode gibt eine Darstellung des Feldes als Zeichenkette zurück.
    public String toString() {
        return "(" + getX() + ", " + getY() + ")";
    }



}
