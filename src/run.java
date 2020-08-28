import com.appmanagerstudent.hieu.App;

import javax.swing.*;

public class run {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
    }
    private static void createGUI()
    {
        App ui = new App();
        JPanel root = ui.getRootPanel();
        JFrame frame = new JFrame("Manager Student");
        frame.setContentPane(root);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("src/icon/education.png").getImage());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
