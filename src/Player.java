public class Player{
    public static double randInt(int min, int max){
        return Math.random() * (max - min) + min ;
    }

    private boolean dead;
    private int level;
    private int xp;
    private int health;
    private int attack;


    Player(){
        this.level = 1;
        this.xp = 0;
        this.health = 20;
        this.attack = 2;
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
        while (this.xp >= (this.level*this.level*10)){
            this.xp -= this.level*this.level*10;
            this.level += 1;
            this.attack += 1;
        }
    }

    public void maxHealth(){
        this.health = level*level*20;
    }

    public void newStart(){
        levelUp();
        maxHealth();
        this.dead = false;
    }

    public void damage(int dmg){
        this.health -= dmg;
        if (health <= 0) {
            this.dead = true;
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int strike(){
        return (int) (this.attack*randInt(1, 4));
    }

    public int getXp() {
        return xp;
    }

    public boolean isDead() {
        return dead;
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }
}
