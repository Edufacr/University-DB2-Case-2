package view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class LogInPanel extends JPanel {

    private final String emailLabelString= "Email:";
    private final String passwordLabelString = "Password:";
    private final String singInButtonString = "Sing In:";
    private final int textFieldSize = 20;

    private JButton singInButton;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;

    LogInPanel(){
        createComponents();
        addComponents();
        setComponentsBounds();
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

    private void setComponentsBounds() {
    }
}
