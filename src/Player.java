public class Player extends Olend{

    private int level;
    private int xp;


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


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int x){
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




}
