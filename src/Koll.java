// Plaan on hiljem kolliga veel midagi teha
public class Koll extends Olend {

    // Konstruktor
    Koll() {
        this.attack = 1;
        this.health = 10;
        this.dead = false;
    }

    @Override
    public String toString() {
        return (String.format("<html><DIV align=\"right\">%s :HP <br>%s :ATK</DIV></html>", this.health, this.getAttack()));
    }

}
