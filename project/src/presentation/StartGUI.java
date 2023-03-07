package presentation;

import application.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Class representing the graphical user interface for the user to log-in with their credentials
 */
public class StartGUI extends JFrame {


    private final JLabel titleLabel = new JLabel("LOG-IN ");
    private final JLabel instructions = new JLabel("Please introduce your credentials ");
    private final JLabel instructions2 = new JLabel("New Client? Create a new account!");
    private final JLabel introduceU = new JLabel("Username:");
    private final JLabel introduceP = new JLabel("Password");
    private static final JLabel image = new JLabel();
    private static final ImageIcon imageIcon = new ImageIcon("ICON.png");

    private final JTextField username = new JTextField("");
    private final JPasswordField password = new JPasswordField("");

    private static final JButton signIn = new JButton("SIGN-IN");
    private final JButton administrator = new JButton("ADMINISTRATOR");
    private final JButton employee = new JButton("EMPLOYEE");
    private final JButton client = new JButton("CLIENT");


    public StartGUI(){
        init();
        addComponents();
    }

    private void init(){
        setTitle("Log In");
        setSize(500, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void addComponents(){
        addInstructions();
        addUsername();
        addPassword();
        addButtons();
    }

    private void addInstructions(){
        //title
        titleLabel.setBounds(100,15,300,50);
        titleLabel.setVisible(true);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 20));
        this.add(titleLabel);

        //instruction
        instructions.setBounds(20,70,450,30);
        instructions.setVisible(true);
        instructions.setHorizontalAlignment(SwingConstants.CENTER);
        instructions.setFont(new Font("Serif", Font.ITALIC, 16));
        this.add(instructions);

        instructions2.setBounds(30,400,450,30);
        instructions2.setVisible(true);
        instructions2.setHorizontalAlignment(SwingConstants.CENTER);
        instructions2.setFont(new Font("Serif", Font.ITALIC, 16));
        this.add(instructions2);

    }

    
    private void addUsername(){
        introduceU.setBounds(150, 120, 100, 30);
        introduceU.setVisible(true);
        introduceU.setFont(new Font("Roboto", Font.BOLD, 15));
        add(introduceU);

        username.setBounds(150,160, 200, 30);
        username.setHorizontalAlignment(JTextField.CENTER);
        username.setFont(new Font("Roboto", Font.BOLD, 15));
        add(username);
    }

    private void addPassword(){
        introduceP.setBounds(150, 190, 100, 30);
        introduceP.setVisible(true);
        introduceP.setFont(new Font("Roboto", Font.BOLD, 15));
        add(introduceP);

        password.setBounds(150,230, 200, 30);
        password.setHorizontalAlignment(JTextField.CENTER);
        password.setFont(new Font("Roboto", Font.BOLD, 15));
        add(password);
    }

    private void addButtons(){
        //administrator
        administrator.setBounds(20,310, 140, 40);
        administrator.setFont(new Font("Roboto", Font.BOLD, 12));
        administrator.setVisible(true);
        administrator.setFocusable(false);
        add(administrator);

        //employee
        employee.setBounds(175,310, 140, 40);
        employee.setFont(new Font("Roboto", Font.BOLD, 12));
        employee.setVisible(true);
        employee.setFocusable(false);
        add(employee);

        //client
        client.setBounds(330,310, 140, 40);
        client.setFont(new Font("Roboto", Font.BOLD, 12));
        client.setVisible(true);
        client.setFocusable(false);
        add(client);

        //sign-in
        signIn.setBounds(175,450, 140, 40);
        signIn.setFont(new Font("Roboto", Font.BOLD, 12));
        signIn.setVisible(true);
        signIn.setFocusable(false);
        add(signIn);

    }

    public String getUsername() {

        return username.getText();
    }

    public String getPassword() {

        return new String(password.getPassword());
    }

    public void addListener(ActionListener listener){
        employee.addActionListener(listener);
        administrator.addActionListener(listener);
        client.addActionListener(listener);
        signIn.addActionListener(listener);
    }

    public JButton getAdministrator() {
        return administrator;
    }

    public JButton getClient() {
        return client;
    }

    public JButton getEmployee() {
        return employee;
    }

    public JButton getSignIn() {
        return signIn;
    }
}
