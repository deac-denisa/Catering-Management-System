package business.model;


import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private int orderID;
    private int clientID;
    private Date orderDate;


    public Order(int orderID, int clientID, Date orderDate) {
        this.orderID = orderID;
        this.clientID = clientID;
        this.orderDate = orderDate;
    }

    @Override
    public int hashCode()
    {
        int hashcode = 7;
        hashcode += orderID* 17L + clientID* 11L + orderDate.getDay()*7 + orderDate.getMonth()  ;
        return hashcode;

    }

    @Override
    public String toString() {
        return "order ID=" + orderID +
                ", clientID=" + clientID +
                ", orderDate=" + orderDate +
                '}';
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }


}
