import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    // Isendiväljad
    private static int floor = 1;
    private static int suvaline = 0;

    private static Player player;
    private static Koll koll;

    private static final String[] kollid = {"Kratt", "Vanapagan", "Raudmees"};

    private static String playerName;

    private static String gameslot = "Slot1"; //gamesloti jaoks kui on soov implementeerida

    // Väljastab mängija seisundi ja vastase seisundi
    public static void väljasta() {
        System.out.println("========================================================================\n");
        System.out.printf("MÄNGIJA: %s, HP: %d,  LVL: %d, ATK: %d, XP: %d\n\n", playerName, player.getHealth(), player.getLevel(), player.getAttack(), player.getXp());
        System.out.println("------------------------------------------------------------------------\n");
        System.out.printf("KOLETIS: %s, HP: %d, KORRUS: %d, ATK: %d\n\n", kollid[suvaline], koll.getHealth(), floor, koll.getAttack());
        System.out.println("========================================================================\n");
    }

    // Suvaliste arvude arvutamiseks
    public static int randInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    //mängija info salvestamiseks
    public static void savePlayerInfo() throws IOException {
        String gameslot = Main.gameslot;
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(gameslot+".dat"))){
            dos.writeUTF(Main.playerName);
            dos.writeInt(player.getLevel());
            dos.writeInt(player.getXp());
            dos.writeInt(player.getHealth());
            dos.writeInt(player.getAttack());
        }
    }
    //mängija info lugemiseks
    public static void loadPlayerInfo() throws IOException{
        String gameslot = Main.gameslot;
        try (DataInputStream dis = new DataInputStream(new FileInputStream(gameslot+".dat"))){
            Main.playerName = dis.readUTF();
            player.setLevel(dis.readInt());
            player.setXp(dis.readInt());
            player.setHealth(dis.readInt());
            player.setAttack(dis.readInt());
        }
    }
    public static void main(String[] args) throws InterruptedException, IOException {
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

        // Mäng
        while (gaming) {
            // Väljastab järgmise vastase ja mängija algse seisundi
            väljasta();

            // Vaatab kas mängija on surnud
            while (!player.isDead()) {
                // Mängija ründab kolli
                pAtk = player.strike();
                koll.damage(pAtk);

                // Näitab mängija seisundit ning näitab, kui haiget mängija kollile tegi
                System.out.printf("MÄNGIJA: %s, HP: %d\nDMG: %d\n\n", playerName, player.getHealth(), pAtk);

                TimeUnit.SECONDS.sleep(2);

                // Vaatab, kas koll sai surma
                if (koll.isDead()) {
                    // varieerib kollide nimesi
                    suvaline = randInt(0, kollid.length - 1);
                    floor++;

                    // Uued seisundid järgmisele kollile
                    koll.setHealth(randInt(floor * floor, floor * floor * 10));
                    koll.setAttack(randInt(floor, floor * 2));
                    koll.setDead(false);

                    // Lisab XP-d mängijale
                    player.setXp(randInt(floor * floor, floor * floor * 10));

                    System.out.println("Koll tapetud!\n");

                    // väljastab mängija ja järgmise kolli seisundit
                    väljasta();

                    TimeUnit.SECONDS.sleep(2);

                } else {
                    // Koll ründab mängijat
                    kAtk = koll.strike();
                    player.damage(kAtk);
                    //playeri strikeCounti suurendamine 1-võrra;
                    player.addStrikeCount();

                    // Näitab kolli seisundit ning väljastab ka, kui palju haiget tegi koll mängijale
                    System.out.printf("KOLETIS: %s, HP: %d\nDMG: %d\n\n", kollid[suvaline], koll.getHealth(), kAtk);

                    TimeUnit.SECONDS.sleep(2);

                }
            }

            // Mängija sai surma ning küsitakse, kas soovib edasi mängida
            System.out.printf("%s sai surma...\nAlustame uuesti? [Y/N]  ", playerName);
            String sisend = input.nextLine();
            System.out.print("\n");


            if (Objects.equals(sisend, "N") || Objects.equals(sisend, "n")) {
                gaming = false;
            } else {
                // Kui mängija sai surma siis paneb korruse arvu tagasi üheks
                floor = 1;

                // Paneb järgmise kolli seisundi paika
                koll.setHealth(randInt(1, 10));
                koll.setAttack(randInt(1, 2));
                koll.setDead(false);

                // Mängija seisundid paika
                player.newStart();
            }
        }
        System.out.println("Aitäh mängimast");
    }

}//Main
