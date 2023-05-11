import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameStartGUI startGUI = new GameStartGUI();
            startGUI.setVisible(true);
        });
    }
}





