import javax.swing.*;
import java.awt.*;


public class GameStartGUI extends JFrame {
    private final JTextField nimi;

    public GameStartGUI() {
        // Jframe, ÄRA NÄPI MUIDU MAAILMALÕPP
        setTitle("Deathsword");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        getContentPane().setBackground(Color.BLACK);
        setLayout(new GridBagLayout());

        // Pealkiri
        JLabel pealkiri = new JLabel("Deathsword");
        pealkiri.setForeground(Color.WHITE);
        pealkiri.setFont(new Font("Arial", Font.BOLD, 24));

        // Tekstiväli
        nimi = new JTextField(20);
        nimi.setForeground(Color.WHITE);
        nimi.setBackground(Color.GRAY);

        // Algus nupp
        JButton algusNupp = new JButton("Alusta");
        algusNupp.setForeground(Color.WHITE);
        algusNupp.setBackground(Color.BLACK);
        algusNupp.addActionListener(e -> {
            // Mängija -- siia saab panna save faili
            Player player = new Player();

            // Vaatab, kas inimene sisestas nime
            if (nimi.getText().isEmpty()){
                nimi.setText("Kalevipoeg");
            }

            // Alustab mängu
            GameGUI gameGUI = new GameGUI(nimi.getText(), player);
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
        constraints.weighty = 0.1;
        add(nimi, constraints);

        constraints.gridy = 2;
        constraints.weighty = 0.6;
        add(algusNupp, constraints);
    }

}
