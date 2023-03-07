package presentation;

import business.DeliveryService;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 *Class representing the graphical user interface for the Employee operations
 */
public class EmployeeGUI extends JFrame implements Observer {

    private final JLabel titleLabel = new JLabel(" EMPLOYEE ");
    private static JTextArea result = new JTextArea();
    private static JScrollPane scrollBar;
    private final JButton back = new JButton("BACK");


    public EmployeeGUI(){
        init();
        addComponents();
        DeliveryService deliveryService = new DeliveryService();
       // deliveryService.addObserver(this);
    }

    private void init(){
        setTitle("Employee Window");
        setSize(500, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void addComponents(){
      addTitle();
      addTitle();
      addTextArea();
      addBackButton();
}

    private void addTitle() {
        titleLabel.setBounds(100, 15, 300, 50);
        titleLabel.setVisible(true);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 20));
        this.add(titleLabel);
    }

    private void addBackButton(){
        back.setBounds(30, 460, 80,30);
        back.setVisible(true);
        back.setFocusable(false);
        this.add(back);
    }

    private void addTextArea(){
        result.setBounds(30, 50, 400, 360);
        result.setEditable(false);
        result.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Result"), result.getBorder()));
        result.setFont(new Font("Times New Roman", Font.BOLD , 15));
        result.append("NOTIFICATIONS");

        scrollBar = new JScrollPane(result);
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBar.setBounds(30, 60, 420, 364);
        getContentPane().add(scrollBar);
        setVisible(true);
    }

    public void addListener(ActionListener listener){
        back.addActionListener(listener);
    }

    public JButton getBack(){
        return back;
    }
    @Override
    public void update(Observable o, Object arg) {

        this.setVisible(true);
        System.out.println( " ok ");
        result.append(arg.toString());

    }
}
