package data;

import javax.swing.*;
import java.io.IOException;

public class FileWriter {

    /**
     * Method that write into a file or creates a new one and writes the given string. Used for reports and bills
     * @param s - string needed to be written
     * @param fileName - the name of the file in which the information will be placed
     */
    public static void write(String s, String fileName){

        try {
            java.io.FileWriter file = new java.io.FileWriter(fileName);
            file.write(s);
            file.close();
            JOptionPane.showMessageDialog(null, fileName+" Generated With Success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
