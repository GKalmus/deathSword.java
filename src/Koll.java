public class Koll {
    public static double randInt(int min, int max){
        return (int) (Math.random() * (max - min) + min) ;
    }

    private int attack;
    private int health;
    private boolean dead;


    Koll() {
        this.attack = 1;
        this.health = 10;
        this.dead = false;
    }

    public void reset(){
        this.attack = 1;
        this.health = 10;
        this.dead = false;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public int strike(){
        return (int) (this.attack*randInt(1, 4));
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void damage(int damage){
        this.health -= damage;
        this.dead = this.health <= 0;
    }
}
