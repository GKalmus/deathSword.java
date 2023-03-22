import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static int randInt(int min, int max){
        return (int) (Math.random() * (max - min) + min) ;
    }

    public static void main(String[] args) throws InterruptedException {
        boolean gaming = true;
        int floor = 1;
        int suvaline = 0;
        int pAtk;
        int kAtk;

        Player player = new Player();

        System.out.print("Tere tulemast Death Swordi!\n");
        System.out.print("Sisesta oma nimi: ");

        Scanner input = new Scanner(System.in);
        String playerName = input.nextLine();

        System.out.println("Alustame võitlusega");

        Koll koll = new Koll();
        String[] kollid = {"Kratt", "Vanapagan", "Raudmees"};

        while (gaming){
            System.out.printf("MÄNGIJA: %s, HP: %d,  LVL: %d, ATK: %d, XP: %d\n", playerName, player.getHealth(), player.getLevel(), player.getAttack(), player.getXp());
            System.out.printf("------------------------------------\nKOLETIS: %s,\t HP: %d,\t KORRUS: %d,\t ATK: %d\n\n\n", kollid[suvaline], koll.getHealth(), floor, koll.getAttack());
            while(!player.isDead()){
                

                pAtk = player.strike();
                koll.damage(pAtk);
                System.out.printf("MÄNGIJA: %s,\t HP: %d\nDMG: %d\n\n", playerName, player.getHealth(), pAtk);

                TimeUnit.SECONDS.sleep(2);

                if (koll.isDead()){
                    suvaline = randInt(0 ,kollid.length -1); // varieerib kollide nimesi
                    floor++;
                    koll.setHealth(randInt(floor*floor, floor*floor*10));
                    koll.setAttack(randInt(floor, floor*2));
                    koll.setDead(false);

                    player.addXp(randInt(floor*floor, floor*floor*10));

                    System.out.println("Koll tapetud!\n");
                    System.out.printf("MÄNGIJA: %s, HP: %d,  LVL: %d, ATK: %d, XP: %d\n", playerName, player.getHealth(), player.getLevel(), player.getAttack(), player.getXp());
                    System.out.printf("------------------------------------\nKOLETIS: %s,\t HP: %d,\t KORRUS: %d,\t ATK: %d\n\n", kollid[suvaline], koll.getHealth(), floor, koll.getAttack());
                    TimeUnit.SECONDS.sleep(2);
                }else {
                    kAtk = koll.strike();
                    player.damage(kAtk);
                    System.out.printf("KOLETIS: %s,\t HP: %d\nDMG: %d\n\n", kollid[suvaline], koll.getHealth(), kAtk);
                    TimeUnit.SECONDS.sleep(2);
                }
            }
            System.out.printf("%s sai surma...\nAlustame uuesti? Y/N  ", playerName);
            String sisend = input.nextLine();
            if (Objects.equals(sisend, "N") || Objects.equals(sisend, "n")){
                gaming = false;
            } else {
                floor = 0;
                player.newStart();
            }
        }
        System.out.println("Aitäh mängimast");
    }


}
