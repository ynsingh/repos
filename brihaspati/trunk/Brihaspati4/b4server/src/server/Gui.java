package server ;

//package com.ehelpy.brihaspati4.gui;


/* License -

* Design - YNSingh (2016)
* Implementation - Chetna(2016),Seemanti(2016), Vijay(2016).
* Date, Change description - Create GUI for Peer Client.
* Copyright 2016 : YNSingh
*/

//import com.ehelpy.brihaspati4.utils.Session;
//import com.ehelpy.brihaspati4.utils.Session;
/*import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.security.KeyPair;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;*/
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPasswordField;

public class Gui {


    //private static String passwordValue,keystorealias;

    public static String getkeystorepass() {

        JLabel jPassword = new JLabel("Please enter your keystore password.");
        JPasswordField password = new JPasswordField();
        Object[] ob = {jPassword, password};
        int result = JOptionPane.showConfirmDialog(null, ob, "Keystore password ", JOptionPane.OK_CANCEL_OPTION);

        String passwordValue = null;
        if (result == JOptionPane.OK_OPTION) {
            char[] passwordValues = password.getPassword();
            //Here is some validation code
            passwordValue=String.valueOf(passwordValues);

        }
        return passwordValue;
    }

    public static String getemailid() {
        // create a jframe
        JLabel frame = new JLabel("EmailID");

        // prompt the user to enter their email id as common name.
        //email id is extracted later at server and used to send OTP for verification.
        String commonname = JOptionPane.showInputDialog(frame, "Please enter your Email-Id");
        return commonname;

    }

    public static String getorganizationalunit() {
        // create a jframe
        JFrame frame = new JFrame("Enter Org Unit");

        // prompt the user to enter their org unit
        String organizationalunit = JOptionPane.showInputDialog(frame, "Please enter your organizational unit");
        return organizationalunit;

    }

    public static String getorganization() {
        // create a jframe
        JFrame frame = new JFrame("Enter Organization");

        // prompt the user to enter their organisation
        String organization = JOptionPane.showInputDialog(frame, "Please enter your organization");
        return organization;

    }

    public static String getcity() {
        // create a jframe
        JFrame frame = new JFrame("Enter City");
        // prompt the user to enter their city
        String city = JOptionPane.showInputDialog(frame, "Please enter your city");
        return city;
    }

    public static String getstate() {
        // create a jframe
        JFrame frame = new JFrame("Enter State");
        // prompt the user to enter their city
        String state = JOptionPane.showInputDialog(frame, "Please enter your state");
        return state;
    }

    public static String getcountry() {
        // create a jframe
        JFrame frame = new JFrame("Enter Country");
        // prompt the user to enter their country
        String country = JOptionPane.showInputDialog(frame, "Please enter your country");
        return country;
    }

    /*public static String getemailid(){
    	  // create a jframe
    	  JFrame frame = new JFrame("Enter emailid");
    	  // prompt the user to enter their country
    	  String emailid = JOptionPane.showInputDialog(frame, "Please enter your emailid");
    	  return emailid ;
    }*/

// new modified method to take keystore location from user,inspite of taking from config
    public static String getkeystorelocation() {


        // create a jframe
        JFrame frame = new JFrame("Enter keystore location");

        // prompt the user to enter their keystore location
        String keystorelocation = JOptionPane.showInputDialog(frame, "Please enter your keystore location");
        System.out.println(keystorelocation);
        return keystorelocation;

    }

    public static String getaliasname() {

        // create a jframe
        JFrame frame = new JFrame("Alias name");

        // prompt the user to enter their keystorepass
        String keystorealias = JOptionPane.showInputDialog(frame, "Please enter new alias name");
        return keystorealias;
    }

    public static void showMessageDialogBox(String msg) {

// create a jframe
        JFrame frame = new JFrame("Message");

//show a joptionpane dialog using showMessageDialog
        JOptionPane.showMessageDialog(frame,msg);
    }

    /*public static String getemailid(){
    //get emailid from user
    String usr_name = javax.swing.JOptionPane.showInputDialog("Enter your emailid");
    if(!usr_name.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")) {
    	JOptionPane.showMessageDialog(null,"Emailid is not valid");
        //return;
        return null;
    }
        return usr_name;
    }*/
}


