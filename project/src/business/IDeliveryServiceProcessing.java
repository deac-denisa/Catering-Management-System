package business;

import business.model.MenuItem;
import business.model.Order;
import presentation.EmployeeGUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public interface IDeliveryServiceProcessing {

        /**
         * @pre menuList.isEmpty()
         * @pre productList.isEmpty()
         * @post !menuList.isEmpty()
         * @post !productList.isEmpty()
         */
        public  void importProducts();

        /**
         * @pre list.isEmpty()
         *
         * @post !list.isEmpty()
         */
        public ArrayList importUsers();

        /**
         * @param item
         * @pre item != null
         * @post list.size@post == list.size@pre + 1
         */
        public void createProductItem(MenuItem item);

        /**
         * @preitem != null
         * @post list.size@pre == list.size@post
         */
        public void editProductItem(MenuItem item, int id);

        /**
         * @pre productId != 0
         * @post list.size@pre == list.size@post - 1
         */
        public void deleteProductItem(int productId);


        /**
         *
         * @param dishes- all the dishes needed to be added to create a menu
         * @pre dishes != null
         * @post list.size@post == list.size@pre + 1
         */
        public void createMenu( String[] dishes, String title, float rating);

        /**
         * @param order - details about the order
         * @param list - list with products ordered by client
         * @pre order!=null;
         * @pre !list.isEmpty();
         * @post orderListSize@post == orderListSize@pre + 1
         */
        public void createOrder(Order order, ArrayList<MenuItem> list, EmployeeGUI em);

        /**
         * Filters the orders made in a certain interval with given end and start time
         * @param startHour
         * @param endHour
         * @pre !startHour.equals("")
         * @pre !endHour.equals("")
         */
        public void filter1(String startHour, String endHour);

        /**
         * @param minValue - the min value of the price for the orders that have to be displayed
         * @pre minVal >= 0
         */
        public void filter2( int minValue);

        /**
         *
         * @param ord - number of minimum orders per client
         * @param value - min price for orders
         * @pre ord >= 0
         * @pre value >= 0
         */
        public void filter3( int ord, double value);

        /**
         *
         * @param day - the day in which the orders are made
         * @pre day != null
         */
        public void filter4( String day);

        /**
         *
         * @param filter - value from GUI
         * @param list - list with products that have to be filtered
         * @return filtered list
         */
        public List<MenuItem> filterByName( String filter,List<MenuItem> list );

        /**
         *
         * @param filter - value from GUI
         * @param list - list with products that have to be filtered
         * @return filtered list
         */
        public List<MenuItem> filterByRating( String filter,List<MenuItem> list );

        /**
         *
         * @param filter - value from GUI
         * @param list - list with products that have to be filtered
         * @return filtered list
         */
        public List<MenuItem> filterByCalories( String filter, List<MenuItem> list);

        /**
         *
         * @param filter - value from GUI
         * @param list - list with products that have to be filtered
         * @return filtered list
         */
        public List<MenuItem> filterByProtein( String filter,List<MenuItem> list );

        /**
         *
         * @param filter - value from GUI
         * @param list - list with products that have to be filtered
         * @return filtered list
         */
        public List<MenuItem> filterBySodium( String filter,List<MenuItem> list);

        /**
         *
         * @param filter - value from GUI
         * @param list - list with products that have to be filtered
         * @return filtered list
         */
        public List<MenuItem> filterByFat( String filter,List<MenuItem> list );

        /**
         *
         * @param filter - value from GUI
         * @param list - list with products that have to be filtered
         * @return filtered list
         */
        public List<MenuItem> filterByPrice( String filter,List<MenuItem> list );

        /**
         * calls the method that creates the table
         * @return the created table with the data from the list with all products
         */
        public JTable viewAllProducts();



}
