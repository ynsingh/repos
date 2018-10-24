package server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class properties_access {
    @SuppressWarnings("finally")
    public static String read_property(String File_name, String Property_name) {

        System.out.println(File_name);
        Properties prop = new Properties();
        InputStream input = null;
        String Return_str = null;
        try {

            input = new FileInputStream(File_name);

            System.out.println(input.toString());
            // load a properties file
            prop.load(input);
            Return_str = prop.getProperty(Property_name);
            // get the property value and print it out
            //System.out.println(prop.getProperty("database"));
            //System.out.println(prop.getProperty("dbuser"));
            //System.out.println(prop.getProperty("dbpassword"));

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return Return_str;
        }
    }
}
