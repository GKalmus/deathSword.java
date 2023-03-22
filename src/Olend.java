public class Olend {

    // Isendiväljad
    protected int health;
    protected int attack;
    protected boolean dead;

    // Isendiväljade get- ja set-
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }


    // Funktsioonid
    public static double randInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public void damage(int damage) {
        this.health -= damage;
        this.dead = this.health <= 0;
    }

    public int strike() {
        return (int) (this.attack * randInt(1, 4));
    }
}
