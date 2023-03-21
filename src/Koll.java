public class Koll {

    private int kollDamage;

    private int kollHealt;

    public Koll(Player player) {
        this.kollDamage = Main.randInt(1, 2*player.getFloor()+10);
        this.kollHealt = Main.randInt(1, 2*player.getFloor()+10);
    }

    public void resetKoll(Player player){
        this.kollDamage = Main.randInt(1, 2*player.getFloor());
        this.kollHealt = Main.randInt(1, 2*player.getFloor());
    }


    public int getKollDamage() {
        return kollDamage;
    }

    public int getKollHealt() {
        return kollHealt;
    }

    public void setKollHealt(int damage) {
        this.kollHealt -= damage;
    }
}
