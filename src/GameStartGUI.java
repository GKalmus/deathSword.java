import javax.swing.*;
import java.awt.*;
import java.io.*;


public class GameStartGUI extends JFrame {
    private final JTextField nimi;
    private static String gameslot;

    public static String getGameslot() {
        return gameslot;
    }

    public GameStartGUI() {
        // Jframe, ÄRA NÄPI MUIDU MAAILMALÕPP
        setTitle("Deathsword");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setMinimumSize(new Dimension(600, 300));
        getContentPane().setBackground(Color.BLACK);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

        // Pealkiri
        JLabel pealkiri = new JLabel("Deathsword");
        pealkiri.setForeground(Color.WHITE);
        pealkiri.setFont(new Font("Arial", Font.BOLD, 24));

        // Tekstiväli
        JLabel sisestus = new JLabel("Sisesta oma nimi:");
        sisestus.setForeground(Color.WHITE);
        sisestus.setFont(new Font("Arial", Font.ITALIC, 16));
        nimi = new JTextField(20);
        nimi.setForeground(Color.WHITE);
        nimi.setBackground(Color.GRAY);

        // Algus nupp
        JButton algusNupp = new JButton("Alusta");
        algusNupp.setForeground(Color.WHITE);
        algusNupp.setBackground(Color.BLACK);
        algusNupp.addActionListener(e -> {
            // Vaatab, kas inimene sisestas nime
            try{
                if (nimi.getText().isEmpty()) {
                    throw new NimipuuduErind("Nimi puudu!");
                }
            } catch (NimipuuduErind ex) {
                //kui kasutaja unustas nime sisestada, paneme default nime
                System.out.println(ex.getMessage());
                nimi.setText("Kalevipoeg");
            }
            Player player = new Player();
            try{
                loadPlayerInfo(player,nimi.getText());
            }catch (IOException ex){
                System.out.println(ex.getMessage());
                System.out.println("Ei ole save faili");
            }

            // Alustab mängu
            GameGUI gameGUI = new GameGUI(nimi.getText(), player);
            gameslot = nimi.getText();
            gameGUI.setVisible(true);
            dispose(); // Sulgeb alguse
        });

        // Et näeks ilus välja, mitte puutuda
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 0.3;
        constraints.insets = new Insets(10, 10, 10, 10);
        add(pealkiri, constraints);

        constraints.gridy = 1;
        constraints.weighty = 0.6;
        add(sisestus, constraints);

        constraints.gridy = 2;
        constraints.weighty = 0.1;
        add(nimi, constraints);

        constraints.gridy = 3;
        constraints.weighty = 0.6;
        add(algusNupp, constraints);
        }

    public static void savePlayerInfo(Player player,String gameslot) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("src/savefiles/"+gameslot+".dat"))){
            dos.writeInt(player.getLevel());
            dos.writeInt(player.getXp());
            dos.writeInt(player.getHealth());
            dos.writeInt(player.getAttack());
        }
    }

    public static void loadPlayerInfo(Player player,String gameslot) throws IOException{
        try (DataInputStream dis = new DataInputStream(new FileInputStream("src/savefiles/"+gameslot+".dat"))){
            player.setLevel(dis.readInt());
            player.setXp(dis.readInt());
            player.setHealth(dis.readInt());
            player.setAttack(dis.readInt());
        }
    }

    }//GameStartGUI
