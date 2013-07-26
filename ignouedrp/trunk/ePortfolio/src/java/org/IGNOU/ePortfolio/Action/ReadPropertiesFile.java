/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Action;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Vinay
 * @version 1
 * @since 07-12-2012
 */
public class ReadPropertiesFile {

    private static Properties prop = new Properties();
    private static String getValue;
    private static String propertiesFilePath = System.getProperty("catalina.home") + "\\conf\\eportfolio.properties";

    public static String ReadPropertyFile(String str) {
        try {
            prop.load(new FileInputStream(propertiesFilePath));
            setGetValue(prop.getProperty(str));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return getGetValue();
    }

    /**
     * @return the getValue
     */
    public static String getGetValue() {
        return getValue;
    }

    /**
     * @param aGetValue the getValue to set
     */
    public static void setGetValue(String aGetValue) {
        getValue = aGetValue;
    }
}
