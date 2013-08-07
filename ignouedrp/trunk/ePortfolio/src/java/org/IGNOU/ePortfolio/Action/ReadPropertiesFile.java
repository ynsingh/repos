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
    private static String propertiesFilePath, TomcateConfig = System.getProperty("catalina.home");
    private static String OS = System.getProperty("os.name").toLowerCase();

    public static String ReadPropertyFile(String str) {
        if (isWindows()) {
            propertiesFilePath = TomcateConfig + "\\conf\\eportfolio.properties";
            // System.out.println("This is Windows");
        } else if (isMac()) {
            System.out.println("This is Mac");
        } else if (isUnix()) {
            propertiesFilePath = TomcateConfig + "/conf/eportfolio.properties";
            //System.out.println("This is Unix or Linux");
        } else if (isSolaris()) {
            // System.out.println("This is Solaris");
        } else {
            // System.out.println("Your OS is not support!!");
        }
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

    public static boolean isWindows() {

        return (OS.indexOf("win") >= 0);

    }

    public static boolean isMac() {

        return (OS.indexOf("mac") >= 0);

    }

    public static boolean isUnix() {

        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);

    }

    public static boolean isSolaris() {

        return (OS.indexOf("sunos") >= 0);

    }
}
