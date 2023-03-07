package data;

import business.DeliveryService;

import java.io.*;

public class Serializator {

    /**
     * Metod that serializes all the data from the DeliveryService class into a file Info.ser
     * @param deliveryService - the class from which the data will be serialized
     */
    public static void serialize(DeliveryService deliveryService) {
        try {
            FileOutputStream fileOutput = new FileOutputStream("Info.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOutput);
            out.writeObject(deliveryService);
            out.close();
            fileOutput.close();
            System.out.printf("Serialized data is saved in Info.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     *  Metod that deserializes all the data from the Info.ser and loads it into DeliveryService by returning it
     * @return the deserialized data
     */
    public static DeliveryService deserialize() {
        DeliveryService deliveryService = null;

        try {
            FileInputStream fileIn = new FileInputStream("Info.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            deliveryService = (DeliveryService) in.readObject();
            in.close();
            fileIn.close();
            return deliveryService;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return null;
        }
    }

}
