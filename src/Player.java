

public class Player extends Olend {
    // Isendi väljad
    private int level;
    private int xp;

    // Konstrukctorid
    Player() {
        this.level = 1;
        this.xp = 0;
        this.health = 20;
        this.attack = 2;
    }

    // Isendi väljade get- ja set-
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }


    public int getXp() {
        return xp;
    }

    public void setXp(int x) {
        this.xp += x;
    }

    // funktsioonid
    public void levelUp() {
        while (this.xp >= (this.level * this.level * 10)) {
            this.xp -= this.level * this.level * 10;
            this.level += 1;
            this.attack += 1;
        }
    }

    public void maxHealth() {
        this.health = level * level * 20;
    }

    public void newStart() {
        levelUp();
        maxHealth();
        this.dead = false;
    }


}
