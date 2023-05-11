import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameGUI extends JFrame {
    // Meie graafilise-liidese asjad
    private final JLabel korrusLabel;
    private final JLabel playerLabel;
    private final JLabel kollLabel;

    private final JLabel playerImageLabel; //mängija pilt
    private final JLabel kollImageLabel; //Kolli pilt

    private final JPanel playerPanel;
    private final JPanel kollPanel;

    private final List<ImageIcon> kollImages;

    // Korrus
    private int floor = 1;

    // Mängija
    public int playerHP;
    public int kollHP;

    private final Player player;
    private final Koll koll;

    private final String playerName;
    private String kollName;

    private static final String[] kollid = {"Kratt", "Vanapagan", "Sarvik", "Katku kutsar", "Lendav ollus"}; // Kolme teega ristil, keskööl

    public static int randInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }


    public GameGUI(String playerNameA, Player playerA) {
        this.player = playerA;
        this.playerName = playerNameA; // My namea Jeff

        koll = new Koll();

        playerHP = player.getHealth();
        kollHP = koll.getHealth();

        kollName = kollid[0];

        //Pildid
        ImageIcon playerImageIcon = new ImageIcon("src/images/kalevipoeg.png");
        kollImages = new ArrayList<>();
        kollImages.add(new ImageIcon("src/images/kratt.png"));
        kollImages.add(new ImageIcon("src/images/vanapagan.png"));
        kollImages.add(new ImageIcon("src/images/sarvik.png"));
        kollImages.add(new ImageIcon("src/images/KatkuKutsar.png"));
        kollImages.add(new ImageIcon("src/images/lendav ollus.png"));

        //Mängija pilt
        playerImageLabel = new JLabel(playerImageIcon);
        kollImageLabel = new JLabel(kollImages.get(0));


        // Jframe
        setTitle("Deathsword");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        getContentPane().setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        // Korrus
        korrusLabel = new JLabel(String.format("<html>Korrus: %s</html>", floor));
        korrusLabel.setForeground(Color.WHITE);
        korrusLabel.setFont(new Font("Arial", Font.BOLD, 24));
        korrusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(korrusLabel, BorderLayout.NORTH); // Tõelised õed-vennad elavad ikka Põhja Nabal

        // Mängija silt
        playerLabel = new JLabel(String.format("<html>%s: <br>%s</html>", playerName, player));
        playerLabel.setForeground(Color.WHITE);
        playerLabel.setHorizontalAlignment(SwingConstants.LEFT);
        playerLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        add(playerLabel, BorderLayout.WEST);

        //Mängija paneel
        playerPanel = new JPanel(new BorderLayout());
        playerPanel.setBackground(Color.BLACK);
        playerPanel.add(playerImageLabel, BorderLayout.WEST);
        playerPanel.add(playerLabel, BorderLayout.CENTER);

        // Kolli silt
        kollLabel = new JLabel(String.format("<html><DIV align=\"right\">%s: <br>%s</DIV></html>", kollName, koll));
        kollLabel.setForeground(Color.WHITE);
        kollLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        kollLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        add(kollLabel, BorderLayout.EAST);

        //Kolli paneel
        kollPanel = new JPanel(new BorderLayout());
        kollPanel.setBackground(Color.BLACK);
        kollPanel.add(kollImageLabel, BorderLayout.EAST);
        kollPanel.add(kollLabel, BorderLayout.CENTER);

        // Mängija ja kolli paneel koos aknasse panemine
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(Color.BLACK);
        infoPanel.add(playerPanel, BorderLayout.WEST); // Lääne versus
        infoPanel.add(kollPanel, BorderLayout.EAST); // Ida, kelle poolt oled???
        add(infoPanel, BorderLayout.CENTER);

        // Ründamise nupp
        JButton attackButton = new JButton("RÜNDA!"); // RÜNDA, MIDA SA PASSID
        attackButton.setForeground(Color.WHITE);
        attackButton.setBackground(Color.BLACK);
        attackButton.addActionListener(e -> nupuVajutus());
        add(attackButton, BorderLayout.SOUTH);

        pack(); //paigutab komponendid
        setLocationRelativeTo(null); //tõstab keskele
    }


    public void nupuVajutus() { // Ma leiutasin Clickin simulaatori, onju?
        uuenda();// Ära puutu

        // Mängija ründab kolli
        int pAtk = player.strike();
        koll.damage(pAtk);
        uuenda();// Ära puutu

        // Koll ründab mängijat
        int kAtk = koll.strike();
        player.damage(kAtk);
        uuenda();// Ära puutu

        try{
            GameStartGUI.savePlayerInfo(player,GameStartGUI.getGameslot());
        }catch (IOException e){
            System.out.println(e); //ei tee midagi erindiga
        }
    }


    // Funktsioon, mis uuendab laabeli tekstid
    public void uuenda() { // Ära puutu
        if (player.isDead()) {
            // Kui mängija sai surma siis paneb korruse arvu tagasi üheks
            floor = 1;
            korrusLabel.setText(String.format("<html>Korrus: %s</html>", floor));

            // Paneb järgmise kolli seisundi paika
            koll.setHealth(randInt(1, 10)); // Ma tunnen ennast jumalana
            koll.setAttack(randInt(1, 2)); // Nagu viskaks täringut
            koll.setDead(false); // Frankensteini looja ka nüüd

            // Mängija seisundid paika
            player.newStart(); // Ai pasaa, jumalatapja isiklikult
        }
        if (koll.isDead()) {
            // varieerib kollide nimesi
            int suvaline = randInt(0, kollid.length - 1);
            kollName = kollid[suvaline];
            kollImageLabel.setIcon(kollImages.get(suvaline));
            floor++;
            korrusLabel.setText(String.format("<html>Korrus: %s</html>", floor));

            // Uued seisundid järgmisele kollile
            koll.setHealth(randInt(floor * floor, floor * floor * 10));
            koll.setAttack(randInt(floor, floor * 2));
            koll.setDead(false);

            // Lisab XP-d mängijale
            player.setXp(randInt(floor * floor, floor * floor * 10)); // Ära küsi, kuidas ma nende valemiteni tulin
        }
        kollHP = koll.getHealth();// Ära puutu
        playerHP = player.getHealth();// Ära puutu
        // Ära puutu
        playerLabel.setText(String.format("<html>%s: <br>%s</html>", playerName, player));// Ära puutu
        kollLabel.setText(String.format("<html><DIV align=\"right\">%s: <br>%s</DIV></html>", kollName, koll));// Ära puutu
    }


}


