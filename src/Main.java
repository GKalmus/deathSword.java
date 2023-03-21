import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        boolean gaming = true;
        Scanner input = new Scanner(System.in);
        System.out.println("Tere tulemast Death Swordi!");
        System.out.println("Sisesta oma nimi:");
        String playerName = input.nextLine();
        Player player = new Player();
        System.out.println("Alustame võitlusega");
        int kollcounter = 1;
        Koll koll = new Koll(player);

        while (gaming){
            System.out.println(player.getAttack());
            System.out.println(player.getHealth());
            while(!player.getDead()){
                System.out.println("Ilmub koll nr " + kollcounter);
                System.out.println(playerName +" ründab, tegi: " + player.getAttack() + " punkti kahju kollile");
                koll.setKollHealt(player.getAttack());
                TimeUnit.SECONDS.sleep(2);
                if (koll.getKollHealt()<=0){
                    koll.resetKoll(player);
                    kollcounter++;
                    System.out.println("Koll tapetud!");
                    TimeUnit.SECONDS.sleep(2);
                }else {
                    player.damage(koll.getKollDamage());
                    System.out.println("Koll tegi " + koll.getKollDamage() + " punkti kahju mängijale.");
                    TimeUnit.SECONDS.sleep(2);
                }
            }
            System.out.println(playerName + " sai surma, alustame uuesti?Y/N");
            String sisend = input.nextLine();
            if (sisend.equals("N") || sisend.equals("n"))
                gaming = false;
            player.setDead();
            player.addXp(kollcounter*10);
            kollcounter = 0;

        }

        System.out.println("Aitäh mängimast");
    }

    public static int randInt(int min, int max){
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }

}
