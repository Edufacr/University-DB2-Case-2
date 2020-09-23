package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import controller.LogInController;

public class LogInPanel extends JPanel {

    private final String emailLabelString = "Email:";
    private final String passwordLabelString = "Password:";
    private final String singInButtonString = "Sign In:";
    private final String singInErrorString = "Incorrect user or password";
    private final int textFieldSize = 20;

    private LogInController controller;

    private JButton singInButton;
    private ActionListener singInButtonActionListener;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;

    LogInPanel() {
        controller = new LogInController();
        createComponents();
        addComponents();
        createActionListeners();
        singInButton.addActionListener(singInButtonActionListener);
    }

    private void singIn(){
        ArrayList<Integer> permissions = controller.SignIn(emailField.getText(), passwordField.getText());
        if (permissions != null) {
            SwingUtilities.getWindowAncestor(this).setVisible(false);
            MainFrame frame = new MainFrame(new InfoPanel(permissions));
            return;
        }
        JOptionPane.showMessageDialog(null, singInErrorString);

    }

    private void createComponents(){
        singInButton = new JButton(singInButtonString);
        emailField = new JTextField(textFieldSize);
        passwordField = new JPasswordField(textFieldSize);
        emailLabel = new JLabel(emailLabelString);
        passwordLabel = new JLabel(passwordLabelString);
    }

    private void addComponents() {
        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(singInButton);
    } 

    private void createActionListeners(){
        singInButtonActionListener = new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                singIn();
            } 
        };
    }
}
