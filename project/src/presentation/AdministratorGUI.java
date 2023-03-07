package presentation;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Class representing the graphical user interface for the Administrator operations
 */
public class AdministratorGUI extends JFrame {

    private final JLabel title = new JLabel("ADMINISTRATOR");
    //manage products
  //  String iText = "For Delete and Edit\n introduce ID";
    private final JLabel idL = new JLabel("For Delete and Edit, introduce ID");
    private final JLabel nameL = new JLabel("Name:");
    private final JLabel ratingL = new JLabel("Rating:");
    private final JLabel caloriesL = new JLabel("Calories:");
    private final JLabel proteinL = new JLabel("Protein:");
    private final JLabel fatL = new JLabel("Fat:");
    private final JLabel sodiumL = new JLabel("Sodium:");
    private final JLabel priceL = new JLabel("Price:");

    private final JTextField idF = new JTextField();
    private final JTextField nameF = new JTextField();
    private final JTextField ratingF = new JTextField();
    private final JTextField caloriesF = new JTextField();
    private final JTextField proteinF = new JTextField();
    private final JTextField fatF = new JTextField();
    private final JTextField sodiumF = new JTextField();
    private final JTextField priceF = new JTextField();

    //create menu
    private final JLabel menuL = new JLabel("Menu Name:");
    private final JLabel dishL1 = new JLabel("Dish 1");
    private final JLabel dishL2 = new JLabel("Dish 2:");
    private final JLabel dishL3 = new JLabel("Dish 3:");
    private final JLabel dishL4 = new JLabel("Dish 4:");
    private final JLabel newRatingL = new JLabel("Rating: ");

    private final JTextField menuF = new JTextField();
    private final JTextField dishF1 = new JTextField();
    private final JTextField dishF2 = new JTextField();
    private final JTextField dishF3 = new JTextField();
    private final JTextField dishF4 = new JTextField();
    private final JTextField newRatingF = new JTextField();

    //filters
    private final JLabel startTimeL = new JLabel("Start Time:");
    private final JLabel endTimeL = new JLabel("End Time:");
    private final JLabel prodNrL = new JLabel("Minimum number of times:");
    private final JLabel ordNrL = new JLabel("Orders min number:");
    private final JLabel valueL = new JLabel("Minimum value:");
    private final JLabel dayL = new JLabel("Day of the orders:");

    private final JTextField starTimeF = new JTextField();
    private final JTextField endTimeF = new JTextField();
    private final JTextField prodNrF = new JTextField();
    private final JTextField ordNrF = new JTextField();
    private final JTextField valueF = new JTextField();
    private final JTextField dayF = new JTextField();


    private final JButton importButton = new JButton("IMPORT PRODUCTS");
    private final JButton back = new JButton("BACK");
    private final JButton create = new JButton("CREATE");
    private final JButton edit = new JButton("EDIT");
    private final JButton delete = new JButton("DELETE");
    private final JButton createMenu = new JButton("CREATE MENU");
    private final JButton filterButton1 = new JButton("GENERATE REPORT");
    private final JButton filterButton2 = new JButton("GENERATE REPORT");
    private final JButton filterButton3 = new JButton("GENERATE REPORT");
    private final JButton filterButton4 = new JButton("GENERATE REPORT");

    private final JPanel managePanel = new JPanel();
    private final JPanel createPanel = new JPanel();
    private final JPanel filterPanel1 = new JPanel();
    private final JPanel filterPanel2 = new JPanel();
    private final JPanel filterPanel3 = new JPanel();
    private final JPanel filterPanel4 = new JPanel();

    public AdministratorGUI(){
        init();
        addComponents();

    }

    private void addComponents(){
        addTitle();
        addButtons();
        addCreatePanel();
        addManagePanel();
        addFilterPanel1();
        addFilterPanel2();
        addFilterPanel3();
        addFilterPanel4();
    }

    private void init(){
        this.setSize(1200, 700);
        this.setTitle("Administrator Window");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void addTitle(){
        title.setBounds(400,15,300,50);
        title.setVisible(true);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 20));
        this.add(title);
    }

    private void addButtons(){
        //import
        importButton.setBounds( 100,80, 170, 40);
        importButton.setFont(new Font("Roboto", Font.BOLD, 12));
        importButton.setVisible(true);
        importButton.setFocusable(false);
        this.add(importButton);
       // importButton.addActionListener();

        //back
        back.setBounds(30, 615, 80,30);
        back.setVisible(true);
        back.setFocusable(false);
        this.add(back);
    }
    

    private void addManagePanel(){
        managePanel.setBounds(30,150,350,450);
        //      managePanel.setBackground(new Color(  201,	227,	204));
        Border blackline = BorderFactory.createLineBorder(Color.black);
        TitledBorder t = BorderFactory.createTitledBorder( blackline, "Manage Products");
        t.setTitleJustification(TitledBorder.CENTER);
        managePanel.setBorder(t);
        managePanel.setLayout(null);
        managePanel.setVisible(true);
        this.add(managePanel);

        //components
        nameL.setBounds(55,30,50,20);
        nameL.setVisible(true);
        managePanel.add(nameL);

        nameF.setBounds(140,30,100,25);
        nameF.setVisible(true);
        managePanel.add(nameF);

        ratingL.setBounds(55,80,50,20);
        ratingL.setVisible(true);
        managePanel.add(ratingL);

        ratingF.setBounds(140,80,100,25);
        ratingF.setVisible(true);
        managePanel.add(ratingF);

        caloriesL.setBounds(55,130,50,20);
        caloriesL.setVisible(true);
        managePanel.add(caloriesL);

        caloriesF.setBounds(140,130,100,25);
        caloriesF.setVisible(true);
        managePanel.add(caloriesF);

        proteinL.setBounds(55,180,50,20);
        proteinL.setVisible(true);
        managePanel.add(proteinL);

        proteinF.setBounds(140,180,100,25);
        proteinF.setVisible(true);
        managePanel.add(proteinF);

        fatL.setBounds(55,230,50,20);
        fatL.setVisible(true);
        managePanel.add(fatL);

        fatF.setBounds(140,230,100,25);
        fatF.setVisible(true);
        managePanel.add(fatF);

        sodiumL.setBounds(55,280,50,20);
        sodiumL.setVisible(true);
        managePanel.add(sodiumL);

        sodiumF.setBounds(140,280,100,25);
        sodiumF.setVisible(true);
        managePanel.add(sodiumF);

        priceL.setBounds(55,330,50,20);
        priceL.setVisible(true);
        managePanel.add(priceL);

        priceF.setBounds(140,330,100,25);
        priceF.setVisible(true);
        managePanel.add(priceF);

        //buttons and instructions
        idL.setBounds(35,370,200,25);
        idL.setVisible(true);
        managePanel.add(idL);

        idF.setBounds(250,370,50,25);
        idF.setVisible(true);
        managePanel.add(idF);

        create.setBounds(20,410,100,25);
        managePanel.setVisible(true);
        managePanel.add(create);

        edit.setBounds(130,410,100,25);
        edit.setVisible(true);
        managePanel.add(edit);

        delete.setBounds(240,410,100,25);
        delete.setVisible(true);
        managePanel.add(delete);


    }

    private void addCreatePanel(){
        createPanel.setBounds(420,150,300,450);
        //    managePanel.setBackground(new Color(  201,	227,	204));
        Border blackline = BorderFactory.createLineBorder(Color.black);
        TitledBorder t = BorderFactory.createTitledBorder( blackline, "Create Menu");
        t.setTitleJustification(TitledBorder.CENTER);
        createPanel.setBorder(t);
        createPanel.setLayout(null);
        createPanel.setVisible(true);
        this.add(createPanel);

        //components
        menuL.setBounds(35,30,80,20);
        menuL.setVisible(true);
        createPanel.add(menuL);

        menuF.setBounds(120,30,100,25);
        menuF.setVisible(true);
        createPanel.add(menuF);

        //components
        dishL1.setBounds(35,80,50,20);
        dishL1.setVisible(true);
        createPanel.add(dishL1);

        dishF1.setBounds(120,80,100,25);
        dishF1.setVisible(true);
        createPanel.add(dishF1);

        dishL2.setBounds(35,130,50,20);
        dishL2.setVisible(true);
        createPanel.add(dishL2);

        dishF2.setBounds(120,130,100,25);
        dishF2.setVisible(true);
        createPanel.add(dishF2);

        dishL3.setBounds(35,180,50,20);
        dishL3.setVisible(true);
        createPanel.add(dishL3);

        dishF3.setBounds(120,180,100,25);
        dishF3.setVisible(true);
        createPanel.add(dishF3);

        dishL4.setBounds(35,230,50,20);
        dishL4.setVisible(true);
        createPanel.add(dishL4);

        dishF4.setBounds(120,230,100,25);
        dishF4.setVisible(true);
        createPanel.add(dishF4);

        newRatingL.setBounds(35,280,80,20);
        newRatingL.setVisible(true);
        createPanel.add(newRatingL);

        newRatingF.setBounds(120,280,100,25);
        newRatingF.setVisible(true);
        createPanel.add(newRatingF);

        createMenu.setBounds(60,380,170,25);
        createPanel.setVisible(true);
        createPanel.add(createMenu);
    }

    private void addFilterPanel1(){
        filterPanel1.setBounds(750,150,400,100);
        //      filterPanel1.setBackground(new Color(  201,	227,	204));
        Border blackline = BorderFactory.createLineBorder(Color.black);
        TitledBorder t = BorderFactory.createTitledBorder( blackline, "Time interval of the orders");
        t.setTitleJustification(TitledBorder.CENTER);
        filterPanel1.setBorder(t);
        filterPanel1.setLayout(null);
        filterPanel1.setVisible(true);
        this.add(filterPanel1);

        startTimeL.setBounds(30,30,100,20);
        startTimeL.setVisible(true);
        filterPanel1.add(startTimeL);

        starTimeF.setBounds(20,60,100,25);
        starTimeF.setVisible(true);
        filterPanel1.add(starTimeF);

        endTimeL.setBounds(140,30,100,20);
        endTimeL.setVisible(true);
        filterPanel1.add(endTimeL);

        endTimeF.setBounds(130,60,100,25);
        endTimeF.setVisible(true);
        filterPanel1.add(endTimeF);

        filterButton1.setBounds(240,60,150,25);
        filterButton1.setVisible(true);
        filterButton1.setFont(new Font("Roboto", Font.BOLD, 11));
        filterPanel1.add(filterButton1);
    }

    private void addFilterPanel2(){
        filterPanel2.setBounds(750,270,400,100);
        //      filterPanel1.setBackground(new Color(  201,	227,	204));
        Border blackline = BorderFactory.createLineBorder(Color.black);
        TitledBorder t = BorderFactory.createTitledBorder( blackline, "Products ordered more than a specified number of times");
        t.setTitleJustification(TitledBorder.CENTER);
        filterPanel2.setBorder(t);
        filterPanel2.setLayout(null);
        filterPanel2.setVisible(true);
        this.add(filterPanel2);

        prodNrL.setBounds(30, 40,150,25);
        prodNrL.setVisible(true);
        filterPanel2.add(prodNrL);

        prodNrF.setBounds(190,40,40,25);
        prodNrF.setVisible(true);
        filterPanel2.add(prodNrF);

        filterButton2.setBounds(240,40,150,25);
        filterButton2.setVisible(true);
        filterButton2.setFont(new Font("Roboto", Font.BOLD, 11));
        filterPanel2.add(filterButton2);


    }

    private void addFilterPanel3(){
        filterPanel3.setBounds(750,390,400,100);
        //      filterPanel1.setBackground(new Color(  201,	227,	204));
        Border blackline = BorderFactory.createLineBorder(Color.black);
        TitledBorder t = BorderFactory.createTitledBorder( blackline, "Clients with specified number orders having a minimum price");
        t.setTitleJustification(TitledBorder.CENTER);
        filterPanel3.setBorder(t);
        filterPanel3.setLayout(null);
        filterPanel3.setVisible(true);
        this.add(filterPanel3);

        ordNrL.setBounds(20,30,120,20);
        ordNrL.setVisible(true);
        filterPanel3.add(ordNrL);

        ordNrF.setBounds(20,60,80,25);
        ordNrF.setVisible(true);
        filterPanel3.add(ordNrF);

        valueL.setBounds(150,30,120,20);
        valueL.setVisible(true);
        filterPanel3.add(valueL);

        valueF.setBounds(150,60,80,25);
        valueF.setVisible(true);
        filterPanel3.add(valueF);

        filterButton3.setBounds(240,60,150,25);
        filterButton3.setVisible(true);
        filterButton3.setFont(new Font("Roboto", Font.BOLD, 11));
        filterPanel3.add(filterButton3);
    }

    private void addFilterPanel4(){
        filterPanel4.setBounds(750,500,400,100);
        //      filterPanel1.setBackground(new Color(  201,	227,	204));
        Border blackline = BorderFactory.createLineBorder(Color.black);
        TitledBorder t = BorderFactory.createTitledBorder( blackline, "Products ordered within a specified day");
        t.setTitleJustification(TitledBorder.CENTER);
        filterPanel4.setBorder(t);
        filterPanel4.setLayout(null);
        filterPanel4.setVisible(true);
        this.add(filterPanel4);

        dayL.setBounds(60,30,120,20);
        dayL.setVisible(true);
        filterPanel4.add(dayL);

        dayF.setBounds(60,60,120,25);
        dayF.setVisible(true);
        filterPanel4.add(dayF);


        filterButton4.setBounds(240,50,150,25);
        filterButton4.setVisible(true);
        filterButton4.setFont(new Font("Roboto", Font.BOLD, 11));
        filterPanel4.add(filterButton4);

    }

    //listeners
    public void addListener(ActionListener listener) {
        importButton.addActionListener(listener);
        create.addActionListener(listener);
        edit.addActionListener(listener);
        delete.addActionListener(listener);
        createMenu.addActionListener(listener);
        filterButton1.addActionListener(listener);
        filterButton2.addActionListener(listener);
        filterButton3.addActionListener(listener);
        filterButton4.addActionListener(listener);
        back.addActionListener(listener);
    }


    //getters
    public String[] getProductFields(){
        String[] s = new String[7];
        s[0] = nameF.getText();
        s[1] = ratingF.getText();
        s[2] = caloriesF.getText();
        s[3] = proteinF.getText();
        s[4] = fatF.getText();
        s[5] = sodiumF.getText();
        s[6] = priceF.getText();

        return s;
    }

    public int getProductId(){
        return Integer.parseInt(idF.getText());
    }

    public String[] getDishes(){
        String[] s = new String[4];

        s[0] = dishF1.getText();
        s[1] = dishF2.getText();
        s[2] = dishF3.getText();
        s[3] = dishF4.getText();

        return s;
    }

    public String getMenuTitle(){
        return menuF.getText();
    }

    public String[] getFilterFields1(){
        String[] f = new String[2];
        f[0] = starTimeF.getText();
        f[1] = endTimeF.getText();
        return f;
    }

    public int getFilterField2(){ return Integer.parseInt(prodNrF.getText());}

    public String[] getFilterField3(){
        String[] s = new String[2];
        s[0] =  ordNrF.getText();
        s[1] = valueF.getText();
        return s;
    }

    public String getFilterFields4(){
          return dayF.getText();
    }

    public float getMenuRating(){

        return Float.parseFloat((newRatingF.getText()));
    }

    public  JButton getImportButton(){ return  importButton; }

    public JButton getCreateButton(){ return  create; }

    public JButton getEditButton(){ return  edit; }

    public JButton getDeleteButton(){ return delete; }

    public JButton getCreateMenuButton() {
        return createMenu;
    }

    public JButton getFilterButton1() {
        return filterButton1;
    }

    public JButton getFilterButton2() {
        return filterButton2;
    }

    public JButton getFilterButton3() {
        return filterButton3;
    }

    public JButton getFilterButton4() {
        return filterButton4;
    }

    public JButton getBack() {
        return back;
    }
}
