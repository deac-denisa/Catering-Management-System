package business;

import business.model.*;
import data.FileWriter;
import data.Serializator;
import presentation.EmployeeGUI;

import javax.swing.*;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {

    private Map<Order, ArrayList<MenuItem>> ordersMap ;
    private ArrayList<Order> orderList;
    private ArrayList<MenuItem> menuList;
    private ArrayList<BaseProduct> productsList;
    private ArrayList<User> userList;
    private int reportId;

    public DeliveryService() {
        this.ordersMap = new HashMap<>();
        this.orderList = new ArrayList<>();
        this.menuList = new ArrayList<>();
        this.productsList = new ArrayList<>();
        this.reportId = 0;

    }

    public int findNextId(){
        int menuSize = menuList.size()-1;

        if(menuSize == -1)
            return 1;
        int nextId = menuList.get(menuSize).getId() + 1;
        return nextId;
    }

    public MenuItem findById(int id) {
        return menuList.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }

    public MenuItem findByName(String name) {
        for(MenuItem m : menuList){
            if(m.getTitle().equals(name)){
                return m;
            }
        }
        return null;
}

    public int nextOrderIndex(){ return orderList.size(); }

    private double getTotalOrderPrice(Order o, ArrayList<MenuItem> list){
        int totalPrice = 0;
        for(MenuItem m: list){
            totalPrice += m.compuntePrice();
        }

        return totalPrice;
    }

    @Override
    public void importProducts() {

        assert menuList.isEmpty();
        assert productsList.isEmpty();

        Set<String> uniqueSet = new HashSet<>();
        List<String[]> lines = null;

        try {
            lines = Files.lines(Paths.get("products.csv")) .skip(1) .map(line -> line.split(",")) .toList();

            List<String[]> finalLines = lines;
            productsList = (ArrayList<BaseProduct>) lines.stream()
                    .filter(m -> uniqueSet.add(m[0]))
                    .map(m -> new BaseProduct(finalLines.indexOf(m)+1, m[0],Float.parseFloat(m[1]), Integer.parseInt(m[2]),
                            Integer.parseInt(m[3]), Integer.parseInt(m[4]), Integer.parseInt(m[5]), Integer.parseInt(m[6])) )
                    .collect(toList());
            menuList.addAll(productsList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Serializator.serialize(this);
        assert !menuList.isEmpty();
        assert !productsList.isEmpty();
        JOptionPane.showMessageDialog(null, "All products were imported");
    }

    public ArrayList<User> importUsers() {

        assert userList.isEmpty();
        List<String[]> lines = null;

        try {
            lines = Files.lines(Paths.get("users.csv"))
                    .skip(1).map(line -> line.split(",")).toList();

            List<String[]> finalLines = lines;
            userList = (ArrayList<User>) lines.stream()
                    .map(m -> new User(finalLines.indexOf(m)+1, m[0], m[1], m[2]))
                    .collect(toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Serializator.serialize(this);
        assert !userList.isEmpty();
        return userList;
    }

    public ArrayList<User> getUsers(){ return userList;}

    public void addNewUser( User u){
        userList.add(u);
        Serializator.serialize(this);
    }

    @Override
    public void createProductItem(MenuItem item) {
        assert item != null;
        int preSize = menuList.size();

        menuList.add(item);
        Serializator.serialize(this);

        int postSize = menuList.size();
        assert postSize == preSize + 1;
    }

    @Override
    public void editProductItem(MenuItem item, int id) {
        assert item != null;
        int preSize =  menuList.size();

        int nr = menuList.indexOf(findById(id));
        menuList.get(nr).setNewAttributes(item);
        Serializator.serialize(this);

        int postSize = menuList.size();
        assert preSize == postSize;
    }

    @Override
    public void deleteProductItem( int productId ) {
        assert productId != 0;
        int preSize =  menuList.size();
        MenuItem m = findById(productId);

        menuList.remove(m);
        Serializator.serialize(this);

        int postSize = menuList.size();
        assert preSize == postSize - 1;
    }

    @Override
    public void createMenu(String[] dishes, String title, float rating) {
        assert dishes != null;
        int preSize =  menuList.size();

        ArrayList<MenuItem> dishItems = new ArrayList<>();

        for( String dish: dishes){
            MenuItem m = findByName( dish );
            if(m != null) {
                dishItems.add(m);
            }
        }

        int menuId = findNextId();
        CompositeProduct compositeMenu = new CompositeProduct(menuId, title, rating, dishItems);
        compositeMenu.computeMenuDetails();
        compositeMenu.compuntePrice();

        System.out.println(compositeMenu);
        menuList.add(compositeMenu);
        Serializator.serialize(this);

        int postSize = menuList.size();
        assert postSize == preSize + 1;
    }

    @Override
    public void createOrder(Order order, ArrayList<MenuItem> cart, EmployeeGUI em) {
        assert order != null;
        assert !cart.isEmpty();

        int preSize = orderList.size();
        double totalPrice = getTotalOrderPrice(order, cart);

        ArrayList<MenuItem> clonedCart= new ArrayList<>();
        clonedCart.addAll(cart);
        orderList.add(order);
        ordersMap.put(order, clonedCart);
        cart.clear();
        Serializator.serialize(this);

        //noify employee
        StringBuilder s = new StringBuilder();
        s.append("\nNew Ordered Placed: Order ID:  "+ order.getOrderID()+"\nClient ID: "+order.getClientID() +"\n"+"Order Details:\n"+clonedCart);
        setChanged();
        notifyObservers(s.toString());
        em.setVisible(false);

        //create bill
        StringBuilder bill = new StringBuilder();
        bill.append(order.toString()+"\n"+ ordersMap.get(order).toString()+"\nTotal Price: "+totalPrice);
        String billName = "Order"+ order.getOrderID()+".txt";
        FileWriter.write(String.valueOf(bill), billName);

        int postSize = orderList.size();
        assert postSize == preSize + 1;
    }

    @Override
    public void filter1(String startHour, String endHour) {
        assert !startHour.equals("");
        assert !endHour.equals("");

        DateFormat hourFormat = new SimpleDateFormat("hh");
        DateFormat minFormat = new SimpleDateFormat("mm");

        String[] start = startHour.split(":");
        String[] end = endHour.split(":");
        int sh = Integer.parseInt(start[0]);
        int sm = Integer.parseInt(start[1]);
        int eh = Integer.parseInt(end[0]);
        int em = Integer.parseInt(end[1]);

        Map<Order, ArrayList<MenuItem>> filteredMap = new HashMap<>();
        ordersMap.forEach(
                (o, value) -> {
                    if(o != null){
                        if(  ( Integer.parseInt(hourFormat.format(o.getOrderDate())) > sh ||
                                (Integer.parseInt(hourFormat.format(o.getOrderDate())) == sh && Integer.parseInt(minFormat.format(o.getOrderDate())) > sm) )
                                && ( Integer.parseInt(hourFormat.format(o.getOrderDate())) < eh ||
                                (Integer.parseInt(hourFormat.format(o.getOrderDate())) == eh && Integer.parseInt(minFormat.format(o.getOrderDate())) < em)  ) ){
                            filteredMap.put(o, value);
                        }
                    }
                }
        );

        StringBuilder sb = new StringBuilder();
        sb.append("orders performed between " + startHour + " and " + endHour+ " regardless the date\n\n");
        for(Map.Entry<Order, ArrayList<MenuItem>> o: filteredMap.entrySet()){
            sb.append("Order Id: "+ o.getKey().getOrderID()+ " Client Id: "+o.getKey().getClientID()+" Products Ordered:\n");
            ArrayList<MenuItem> orderItems = o.getValue();
            System.out.println("\n"+orderItems.toString()+"\n");
            for(MenuItem m: orderItems){
                sb.append("Id: "+m.getId()+ " Name: "+ m.getTitle()+ "Price: "+m.getPrice()+"\n");
            }
            sb.append("\n");
        }

        reportId++;
        String reportTitle = "Report_1_"+reportId+".txt";
        FileWriter.write(String.valueOf(sb),reportTitle);
        Serializator.serialize(this);
    }

    @Override
    public void filter2( int minValue){
        assert minValue >=0;
        Map<MenuItem,Long> map = null;
        Map<MenuItem,Long> myMap = new HashMap<>();

        for(Map.Entry<Order, ArrayList<MenuItem>> o: ordersMap.entrySet()) {
             map =  o.getValue().stream() .collect(Collectors.groupingBy( Function.identity(),
                                        Collectors.counting()));
             map.entrySet().stream().forEach( pair -> {
                                        if (myMap.get(pair.getKey()) == null) {
                                            myMap.put(pair.getKey(), pair.getValue());
                                        } else {
                                            long old = myMap.get(pair.getKey());
                                            myMap.put(pair.getKey(), pair.getValue() + old);
                                        }
                                    });
            }

        StringBuilder sb = new StringBuilder();
        sb.append("Products ordered more than "+minValue+" times:\n\n");
        for(Map.Entry<MenuItem, Long> p: myMap.entrySet()){
            long nr = p.getValue();
            if( nr > minValue){
                sb.append( p.getKey().getTitle()+ " was ordered "+nr+" times\n");
            }
        }

        reportId++;
        String reportTitle = "Report_2_"+reportId+".txt";
        FileWriter.write(String.valueOf(sb),reportTitle);
        Serializator.serialize(this);
    }

    @Override
    public void filter3( int ord, double value){
        assert ord >= 0;
        assert value >= 0;
        Map<Integer,Long> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        sb.append("Clients that have ordered more than "+ ord+" times so far with the value of the order greater than " + value+"\n\n");


        map = orderList.stream()
                .filter(o -> getTotalOrderPrice(o, ordersMap.get(o)) > value)
                .collect(Collectors.groupingBy(Order::getClientID,
                Collectors.counting()));

        map.entrySet().stream().forEach( pair -> {
            System.out.println("client id: "+pair.getKey() + "   products: "+ pair.getValue()+"\n");
                if( pair.getValue() > ord){
                    sb.append("\nClient with id "+ pair.getKey()+ " has ordered "+pair.getValue()+"\n");
                }
        });
        reportId++;
        String reportTitle = "Report_3_"+reportId+".txt";
        FileWriter.write(String.valueOf(sb),reportTitle);
        Serializator.serialize(this);
    }

    @Override
    public void filter4( String day){
        assert day !=  null;
        DateFormat dayFormat = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();
        sb.append("Products ordered within a the day"+day +" with the number of times they have been ordered:\n\n");

        Map<MenuItem,Long> map = null;
        Map<MenuItem,Long> myMap = new HashMap<>();

        for(Map.Entry<Order, ArrayList<MenuItem>> o: ordersMap.entrySet()) {
            if (dayFormat.format(o.getKey().getOrderDate()).equals(day)) {
                map = o.getValue().stream().collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()));
                map.entrySet().stream().forEach(pair -> {
                    if (myMap.get(pair.getKey()) == null) {
                        myMap.put(pair.getKey(), pair.getValue());
                    } else {
                        long old = myMap.get(pair.getKey());
                        myMap.put(pair.getKey(), pair.getValue() + old);
                    }
                });
            }
        }

        for(Map.Entry<MenuItem, Long> p: myMap.entrySet()){
            sb.append( p.getKey().getTitle()+ " was ordered "+ p.getValue()+" times\n");
        }

        reportId++;
        String reportTitle = "Report_4_"+reportId+".txt";
        FileWriter.write(String.valueOf(sb),reportTitle);
        Serializator.serialize(this);

    }

    @Override
    public List<MenuItem> filterByName( String filter,List<MenuItem> list ){
        if(filter == null)
        {
            return list;
        }
        return list.stream().filter(m -> m.getTitle().equals(filter)).collect(toList());
    }

    @Override
    public List<MenuItem> filterByRating( String filter,List<MenuItem> list ){
        if(filter == null)
        {
            return list;
        }
        return list.stream().filter(m -> m.getRating() == Float.parseFloat(filter)).collect(toList());
    }

    @Override
    public List<MenuItem> filterByCalories( String filter, List<MenuItem> list) {
        if(filter == null)
        {
            return list;
        }
        return  list.stream().filter(m -> m.getCalories() == Integer.parseInt(filter)).collect(toList());
    }

    @Override
    public List<MenuItem> filterByProtein( String filter,List<MenuItem> list ){

        if(filter == null)
        {
            return list;
        }
        return list.stream().filter(m -> m.getProtein() == Integer.parseInt(filter)).collect(toList());
    }

    @Override
    public List<MenuItem> filterBySodium( String filter,List<MenuItem> list ){
        if(filter == null)
        {
            return list;
        }
        return list.stream().filter(m -> m.getSodium() == Integer.parseInt(filter)).collect(toList());
    }

    @Override
    public List<MenuItem> filterByFat( String filter,List<MenuItem> list ){
        if(filter == null)
        {
            return list;
        }
        return list.stream().filter(m -> m.getFat() == Integer.parseInt(filter)).collect(toList());
    }

    @Override
    public List<MenuItem> filterByPrice( String filter,List<MenuItem> list ){
        if(filter == null)
        {
            return list;
        }
        return list.stream().filter(m -> m.getPrice() == Double.parseDouble(filter)).collect(toList());
    }


    @Override
    public JTable viewAllProducts() {
        return createTable(menuList);
    }

    public JTable createTable( List<MenuItem> menu){

        String[] col = {"Id","Name", "Rating", "Calories", "Proteins", "Fat", "Sodium", "Price"};
        Object[][] data = new Object[menu.size()][8];
        int r = 0;

        for(MenuItem m: menu){
            data[r][0] = m.getId();
            data[r][1] = m.getTitle();
            data[r][2] = m.getRating();
            data[r][3] = m.getCalories();
            data[r][4] = m.getProtein();
            data[r][5] = m.getFat();
            data[r][6] = m.getSodium();
            data[r][7] = m.getPrice();
            r++;
        }

        JTable table = new JTable(data,col);
        return table;
    }

    public ArrayList<MenuItem> getMenuList() {
        return menuList;
    }
}


