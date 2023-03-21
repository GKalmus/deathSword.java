public class Player{
    private boolean dead;
    private int level;
    private int xp;
    private int health;
    private int attack;


    Player(){
        this.level = 1;
        this.xp = 0;
        this.health = 100;
        this.attack = 1;
    }

    Player(int l, int x, int h, int a) {
        this.level = l;
        this.xp = x;
        this.health = h;
        this.attack = a;
    }

    public void addXp(int x){
        this.xp += x;
    }

    public void levelUp(){
        while (this.xp >= (this.level*this.level*100)){
            this.xp -= this.level*this.level*100;
            this.level += 1;
        }
    }

    public void maxHealth(){

    }

    public void newStart(){
        levelUp();
        maxHealth();
    }

    public void damage(int dmg){
        this.health -= dmg;
        if (health <= 0) {
            dead = true;
            newStart();
        }
    }
}
