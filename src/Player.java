

public class Player extends Olend {
    // Isendi väljad
    private int level;
    private int xp;

    private int strikeCount;

    // Konstrukctorid
    Player() {
        this.level = 1;
        this.xp = 0;
        this.health = 20;
        this.attack = 2;
        this.strikeCount=10;
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

    public int getStrikeCount() {
        return strikeCount;
    }

    public void addStrikeCount() {
        this.strikeCount++;
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

    public int heavyAttack(){
        if (strikeCount>=3){
            strikeCount-=3;
            return (int) (this.attack * randInt(2, 7));
        }else
            return 0; //võib ka mingi erindi väljastada vms
    }

    public int swingAttack(){
        if (strikeCount>=3){
            strikeCount-=2;
            return (int) (this.attack * randInt(1, 5));
        }else
            return 0;
    }

    public int noAttack(){
        return 0;
    }



}
