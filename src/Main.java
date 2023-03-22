import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    private static int floor = 1;
    private static int suvaline = 0;

    private static Player player;
    private static Koll koll;

    private static final String[] kollid = {"Kratt", "Vanapagan", "Raudmees"};

    private static String playerName;

    public static void väljasta() { // Väljastab mängija seisundi ja vastase seisundi
        System.out.println("========================================================================\n");
        System.out.printf("MÄNGIJA: %s, HP: %d,  LVL: %d, ATK: %d, XP: %d\n\n", playerName, player.getHealth(), player.getLevel(), player.getAttack(), player.getXp());
        System.out.println("------------------------------------------------------------------------\n");
        System.out.printf("KOLETIS: %s, HP: %d, KORRUS: %d, ATK: %d\n\n", kollid[suvaline], koll.getHealth(), floor, koll.getAttack());
        System.out.println("========================================================================\n");
    }

    public static int randInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public static void main(String[] args) throws InterruptedException {
        boolean gaming = true;
        int pAtk;
        int kAtk;

        player = new Player();

        System.out.print("Tere tulemast Death Swordi!\n");
        System.out.print("Sisesta oma nimi: ");

        Scanner input = new Scanner(System.in);
        playerName = input.nextLine();

        System.out.println("Alustame võitlusega");

        koll = new Koll();


        while (gaming) {
            väljasta();
            while (!player.isDead()) {


                pAtk = player.strike();
                koll.damage(pAtk);
                System.out.printf("MÄNGIJA: %s, HP: %d\nDMG: %d\n\n", playerName, player.getHealth(), pAtk);

                TimeUnit.SECONDS.sleep(2);

                if (koll.isDead()) { // Kui koll saab surma
                    suvaline = randInt(0, kollid.length - 1); // varieerib kollide nimesi
                    floor++;

                    koll.setHealth(randInt(floor * floor, floor * floor * 10));
                    koll.setAttack(randInt(floor, floor * 2));
                    koll.setDead(false);

                    player.setXp(randInt(floor * floor, floor * floor * 10));

                    System.out.println("Koll tapetud!\n");

                    väljasta();

                    TimeUnit.SECONDS.sleep(2);
                } else {
                    kAtk = koll.strike();
                    player.damage(kAtk);
                    System.out.printf("KOLETIS: %s, HP: %d\nDMG: %d\n\n", kollid[suvaline], koll.getHealth(), kAtk);
                    TimeUnit.SECONDS.sleep(2);
                }
            }

            System.out.printf("%s sai surma...\nAlustame uuesti? Y/N  ", playerName);
            String sisend = input.nextLine();
            System.out.print("\n");

            if (Objects.equals(sisend, "N") || Objects.equals(sisend, "n")) {
                gaming = false;
            } else {
                floor = 0;
                koll.setHealth(randInt(1, 30));
                koll.setAttack(randInt(1, 2));
                koll.setDead(false);
                player.newStart();
            }
        }
        System.out.println("Aitäh mängimast");
    }


}
