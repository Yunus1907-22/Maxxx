/**
 * @author Tibet Türkmen, Muhammed Yunus Gülbasar, Can Avsar, Samet Araz
 */



//Ein Spieler wird in der Klasse „Spieler“ dargestellt.
public class Spieler  {
    //Die aktuelle Position des Spielers auf dem Spielfeld wird im Feld "Location" gespeichert.
    protected Feld location;
    //Der Name des Spielers.
    private String name;
    //Die Summe (Betrag) des Spielers wird als Fractional (Bruch) dargestellt.
    private Fraction summe;



    // Getter-Methode für den Namen des Spielers.
    public String getName() {
        return name;
    }
    //Setter-Methode für den Namen des Spielers.
    public void setName(String name) {
        this.name = name;
    }
    //Methode zum Hinzufügen eines Bruchs zur Summe des Spielers.
    public void addSumme(Fraction f) {
        summe = summe.addQuadriertes(f);

    }
    // Getter-Methode für die Summe des Spielers.
    public Fraction getSumme() {
        return summe;
    }

    // Setter-Methode für die Summe des Spielers
    public void setSumme(Fraction summe) {
        this.summe = summe;
    }



   //Konstrukteur der Spielerklasse.
   //Beginnt die Spielerinstanz mit einer Startposition, einem Namen und einer Summe.

    public Spieler(Feld location,String name, Fraction summe) {
        this.location = location;
        this.name = name;
        this.summe = summe;


    }

    // Getter-Methode für die aktuelle Position des Spielers.
    public Feld getLocation() {
        return location;
    }
    // Setter-Methode für die aktuelle Position des Spielers.
    public void setLocation(Feld location) {
        this.location = location;
    }




}
