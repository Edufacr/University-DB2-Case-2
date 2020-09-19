package view;

import model.LogInModel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final String frameTitle = "Sucursales";

    public MainFrame(JPanel pJPanel) {
        super(frameTitle);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(pJPanel);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        //MainFrame frame = new MainFrame(new LogInPanel());
        LogInModel lm = new LogInModel();
        ArrayList<Integer> r = lm.SignIn("mabo.daniel","pass");
        if (r != null) {
            for (int prs : r) {
                System.out.println(prs);
            }
        } else {
            System.out.println("failed login");
        }
    }
}