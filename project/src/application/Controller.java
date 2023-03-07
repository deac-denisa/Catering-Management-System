package application;

import business.DeliveryService;
import business.model.*;
import data.Serializator;
import presentation.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controller {

    private DeliveryService deliveryService;
    private StartGUI startGUI ;
    private ArrayList<User> users  ;
    private ArrayList<MenuItem> cart = new ArrayList<>() ;
    private String[] filterFields = new String[7];
    private List<MenuItem> filtered = new ArrayList<>();
    private int firstFilter;
    private int currentUserId;
    private AdministratorGUI adm;
    private  EmployeeGUI em;
    private SignInGUI signInGUI;

    public Controller( ) {

        //deliveryService = new DeliveryService();
       deliveryService = Serializator.deserialize();

        startGUI = new StartGUI();
        startGUI.setVisible(true);

        adm = new AdministratorGUI();
        em = new EmployeeGUI();
        signInGUI = new SignInGUI();

        //users = deliveryService.importUsers();
        users = deliveryService.getUsers();
        em.setVisible(false);
        deliveryService.addObserver(em);
        firstFilter = 0;
        addStartListeners();
    }

    private User findUser(String userType){

        for(User p: users) {
            if (userType.equals(p.getUserType()) && startGUI.getUsername().equals(p.getUsername()) && startGUI.getPassword().equals(p.getPassword())) {
                return p;
            }
        }
        return null;
    }

    public void addStartListeners(){
        startGUI.addListener( e ->{
            if(e.getSource() == startGUI.getAdministrator()){
                User u = findUser("administrator");
                if(u != null) {

                    adm.setVisible(true);
                    startGUI.setVisible(false);
                //    startGUI.dispose();
                    addAdministratorListeners(adm);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Incorrect Credentials! Try again.","Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if(e.getSource() == startGUI.getEmployee()){
                User u  = findUser("employee");
                if( u != null) {

                    em.setVisible(true);
                    startGUI.setVisible(false);
                    addEmployeeListener(em);
                }
                else{JOptionPane.showMessageDialog(null, "Incorrect Credentials! Try again.","Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if(e.getSource() == startGUI.getClient()){
                User u  = findUser("client");
                if(u != null) {
                    currentUserId = u.getId();
                    ClientGUI c = new ClientGUI();
                    c.setVisible(true);
                    startGUI.setVisible(false);
                    addClientListeners(c);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Incorrect Credentials! Try again.","Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if(e.getSource() == startGUI.getSignIn()){

                signInGUI.setVisible(true);
                startGUI.setVisible(false);
                //startGUI.dispose();
                addSignInListener(signInGUI);
            }

        });
    }

    public void addAdministratorListeners(AdministratorGUI administratorGUI){

        administratorGUI.addListener( e->{
            if(e.getSource() == administratorGUI.getImportButton()) {
                deliveryService.importProducts();
            }
            else if(e.getSource() == administratorGUI.getCreateButton()){
                String[] f = administratorGUI.getProductFields();
                int id = deliveryService.findNextId();
                BaseProduct b = new BaseProduct(id, f[0], Float.parseFloat(f[1]), Integer.parseInt(f[2]), Integer.parseInt(f[3]), Integer.parseInt(f[4]), Integer.parseInt(f[5]), Double.parseDouble(f[6]));
                deliveryService.createProductItem(b);
            }
            else if(e.getSource() == administratorGUI.getEditButton()){
                String[] f = administratorGUI.getProductFields();
                int id = administratorGUI.getProductId();
                BaseProduct b =  new BaseProduct(id, f[0], Float.parseFloat(f[1]), Integer.parseInt(f[2]), Integer.parseInt(f[3]), Integer.parseInt(f[4]), Integer.parseInt(f[5]), Double.parseDouble(f[6]));
                deliveryService.editProductItem(b, id);
            }
            else if(e.getSource() == administratorGUI.getDeleteButton()){
                int id = administratorGUI.getProductId();
                deliveryService.deleteProductItem(id);
            }
            else if( e.getSource() == administratorGUI.getCreateMenuButton()){
                String[] dishes = administratorGUI.getDishes();
                String name = administratorGUI.getMenuTitle();
                float rating = administratorGUI.getMenuRating();
                deliveryService.createMenu(dishes, name, rating);
            }
            else if(e.getSource() == administratorGUI.getFilterButton1()){
                String[] hours = administratorGUI.getFilterFields1();
                deliveryService.filter1(hours[0], hours[1]);
            }
            else if(e.getSource() == administratorGUI.getFilterButton2()){
                deliveryService.filter2(administratorGUI.getFilterField2());
            }
            else if(e.getSource() == administratorGUI.getFilterButton3()){
                String[] s = administratorGUI.getFilterField3();
                deliveryService.filter3( Integer.parseInt(s[0]), Double.parseDouble(s[1]) );
            }
            else if(e.getSource() == administratorGUI.getFilterButton4()){
                deliveryService.filter4(administratorGUI.getFilterFields4());
            }
            else if(e.getSource() == administratorGUI.getBack()){
                startGUI.setVisible(true);
                administratorGUI.setVisible(false);
            }
        });
    }

    public void addClientListeners(ClientGUI clientGUI){
        clientGUI.addListeners( e ->{
            if(e.getSource() == clientGUI.getAddCart()){
                int id = clientGUI.getProductId();
                MenuItem m = deliveryService.findById(id);
                cart.add(m);
            }
            else if(e.getSource() == clientGUI.getNewOrder()){

                int orderId = deliveryService.nextOrderIndex();
                Order order = new Order(orderId, currentUserId, new Date());
                deliveryService.createOrder(order, cart, em);
            }
            else if(e.getSource() == clientGUI.getNameB()){
                filterFields[0] = clientGUI.getNameF();
            }
            else if(e.getSource() == clientGUI.getRatingB()){
                filterFields[1] = clientGUI.getRatingF();
            }
            else if(e.getSource() == clientGUI.getCaloriesB()){
               filterFields[2] = clientGUI.getCaloriesF();
            }
            else if(e.getSource() == clientGUI.getProteinB()){
                filterFields[3] = clientGUI.getProteinF();;
            }
            else if(e.getSource() == clientGUI.getSodiumB()){
                filterFields[4] = clientGUI.getSodiumF();
            }
            else if(e.getSource() == clientGUI.getFatB()){
                filterFields[5] = clientGUI.getFatF();
            }
            else if(e.getSource() == clientGUI.getPriceB()){
                filterFields[6] = clientGUI.getPriceF();
            }
            else if(e.getSource() == clientGUI.getFilterProducts()){
                /*JTable table = deliveryService.filter5(filterFields);
                clientGUI.addFilteredTable(table);*/

               /* filtered.addAll( deliveryService.getMenuList() );
               // List<MenuItem> aux = deliveryService.filterByName(filterFields[0], filtered) ;
                List<MenuItem> aux1 = deliveryService.filterByRating(filterFields[1], filtered);
                filtered = deliveryService.filterByCalories(filterFields[2], filtered);
                filtered = deliveryService.filterByProtein(filterFields[3], filtered);
                filtered = deliveryService.filterBySodium(filterFields[4], filtered);
                filtered = deliveryService.filterByFat(filterFields[5], filtered);
                filtered = deliveryService.filterByPrice(filterFields[6], filtered);*/

                filtered.addAll( deliveryService.getMenuList() );
                List<MenuItem> aux = deliveryService.filterByName(filterFields[0], filtered) ;
                List<MenuItem> aux1 = deliveryService.filterByRating(filterFields[1], aux);
                List<MenuItem> aux2 =  deliveryService.filterByCalories(filterFields[2], aux1);
                List<MenuItem> aux3 = deliveryService.filterByProtein(filterFields[3], aux2);
                List<MenuItem> aux4 = deliveryService.filterBySodium(filterFields[4], aux3);
                List<MenuItem> aux5 = deliveryService.filterByFat(filterFields[5], aux4);
                List<MenuItem> aux6 = deliveryService.filterByPrice(filterFields[6], aux5);

                JTable table = deliveryService.createTable(aux6);
                clientGUI.addFilteredTable(table);
                //clear all
                aux.clear(); aux1.clear(); aux2.clear(); aux3.clear(); aux4.clear(); aux5.clear(); aux6.clear();
            }
            else if(e.getSource() == clientGUI.getView()){
                JTable table = deliveryService.viewAllProducts();
                clientGUI.addProductTable(table);
                clientGUI.setTableView();
            }
            else if(e.getSource() == clientGUI.getBack()){
              //  StartGUI startGUI = new StartGUI();
                startGUI.setVisible(true);
            //   addStartListeners();
                clientGUI.setVisible(false);
          //      clientGUI.dispose();
            }

        });
    }

    public void addSignInListener(SignInGUI signInGUI){
        signInGUI.addListeners(e-> {
            if(e.getSource() == signInGUI.getSignIn()){
                int id = users.size()+1;
                User p = new User(id, signInGUI.getUsername(), signInGUI.getPassword(), "client");
                users.add(p);
                deliveryService.addNewUser(p);
                JOptionPane.showMessageDialog(null, "Account Created with Success");

                ClientGUI clientGUI = new ClientGUI();
                clientGUI.setVisible(true);
                addClientListeners(clientGUI);
                signInGUI.setVisible(false);
            }
            if(e.getSource() == signInGUI.getBack()){
                startGUI.setVisible(true);
                signInGUI.setVisible(false);
            }
        });
    }

    public void addEmployeeListener(EmployeeGUI employeeGUI){
       employeeGUI.addListener(e-> {
           if(e.getSource() == employeeGUI.getBack())
           startGUI.setVisible(true);
           employeeGUI.setVisible(false);
       } );
    }

}
