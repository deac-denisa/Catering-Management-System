package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Class representing the graphical user interface for the Client to create a new account
 */
public class SignInGUI extends JFrame {

    private final JLabel title = new JLabel("CREATE ACCOUNT");
    private final JLabel introduceU = new JLabel("Username:");
    private final JLabel introduceP = new JLabel("Password");
    private final JTextField username = new JTextField("");
    private final JPasswordField password = new JPasswordField("");
    private static final JLabel image = new JLabel();
    private static final ImageIcon imageIcon = new ImageIcon("ICON.png");
    private static final JButton signIn = new JButton("SIGN IN");
    private final JButton back = new JButton("BACK");



    public SignInGUI(){
      init();
      addComponents();
    }

    private void init(){
        setTitle("Sign In");
        setSize(500, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void addComponents(){
        addTitle();
        addImage();
        addUsername();
        addPassword();
        addButtons();
    }

    private void addTitle(){
        title.setBounds(30,20,440,30);
        title.setVisible(true);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 20));
        add(title);
    }

    private void addImage(){
        image.setBounds(165, 30, 440, 220);
        image.setVisible(true);
        image.setText("");
        image.setIcon(imageIcon);
        add(image);
    }
    private void addUsername(){
        introduceU.setBounds(150, 250, 100, 30);
        introduceU.setVisible(true);
        introduceU.setFont(new Font("Roboto", Font.BOLD, 15));
        add(introduceU);

        username.setBounds(150,290, 200, 30);
        username.setHorizontalAlignment(JTextField.CENTER);
        username.setFont(new Font("Roboto", Font.BOLD, 15));
        add(username);
    }

    private void addPassword(){
        introduceP.setBounds(150, 320, 100, 30);
        introduceP.setVisible(true);
        introduceP.setFont(new Font("Roboto", Font.BOLD, 15));
        add(introduceP);

        password.setBounds(150,360, 200, 30);
        password.setHorizontalAlignment(JTextField.CENTER);
        password.setFont(new Font("Roboto", Font.BOLD, 15));
        add(password);
    }

    private void addButtons(){
        //sign-in
        signIn.setBounds(265,450, 80, 40);
        signIn.setFont(new Font("Roboto", Font.BOLD, 12));
        signIn.setVisible(true);
        signIn.setFocusable(false);
        add(signIn);

        //back
        back.setBounds(150, 450, 80,40);
        back.setVisible(true);
        back.setFocusable(false);
        this.add(back);


    }

    public String getUsername() {

        return username.getText();
    }

    public String getPassword() {

        return new String(password.getPassword());
    }

    public void addListeners(ActionListener listener){
        signIn.addActionListener(listener);
        back.addActionListener(listener);
    }

    public JButton getSignIn() {
        return signIn;
    }

    public JButton getBack() {
        return back;
    }
}
