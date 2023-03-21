public class Player{
    private boolean dead;
    private int floor;
    private int level;
    private int xp;
    private int health;
    private int attack;



    Player(){
        this.level = 1;
        this.xp = 0;
        this.health = 100;
        this.attack = 2;
    }

    Player(int l, int x, int h, int a) {
        this.level = l;
        this.xp = x;
        this.health = h;
        this.attack = a;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
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

    public void addAttack(int a){
        this.attack += a;
    }

    public void maxHealth(){
        this.health = level*level*2;
    }

    public void newStart(){
        levelUp();
        maxHealth();
        this.floor = 1;
    }

    public void damage(int dmg){
        this.health -= dmg;
        if (health <= 0) {
            dead = true;
            newStart();
        }
    }
    public boolean getDead() {
        return dead;
    }

    public void setDead() {
        this.dead = false;
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }
}
