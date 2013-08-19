/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.Brihaspati;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;


/**
 *
 * @author smvdu
 */
public class BrihaspatiProFile {

    /** Creates a new instance of BrihaspatiProFile */
    public BrihaspatiProFile() {
    }

    public String sourceId() {
        try {
            //ArrayList<String> op = new ArrayList<String>();
            String op = null;
            int check = 0;
            while (check == 0) {
                check = 1;
                BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
               // System.out.print("Enter file name which has properties extension :");
               // str = bf.readLine();
                File f = new File(System.getProperty("user.home")+ File.separator+"remote_auth"+File.separator +"brihaspati3-remote-access.properties");
                if (f.exists())
                {
                    Properties pro = new Properties();
                    FileInputStream in = new FileInputStream(f);
                    pro.load(in);
                    String proKey = "source_id";
                    op = pro.getProperty(proKey);
                    in.close();
                    
                }
            }

            return op;
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public String propertieDataUrl() {
        try {
            //ArrayList<String> op = new ArrayList<String>();
            String op = null;
            int check = 0;
            while (check == 0) {
                check = 1;
                BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
               // System.out.print("Enter file name which has properties extension :");
               // str = bf.readLine();
                File f = new File(System.getProperty("user.home")+ File.separator+"remote_auth"+File.separator +"brihaspati3-remote-access.properties");
                if (f.exists())
                {
                    Properties pro = new Properties();
                    FileInputStream in = new FileInputStream(f);
                    pro.load(in);
                    String proKey = "server_url";
                    op = pro.getProperty(proKey);
                    in.close();

                }
            }

            return op;
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public String propertieDataSeqKey() {
        try {
            //ArrayList<String> op = new ArrayList<String>();
            String op = null;
            int check = 0;
            while (check == 0) {
                check = 1;
                BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
               // System.out.print("Enter file name which has properties extension :");
               // str = bf.readLine();
                File f = new File(System.getProperty("user.home")+ File.separator+"remote_auth"+File.separator +"brihaspati3-remote-access.properties");
                if (f.exists())
                {
                    Properties pro = new Properties();
                    FileInputStream in = new FileInputStream(f);
                    pro.load(in);
                    String proKey = "security_key";
                    op = pro.getProperty(proKey);
                    in.close();

                }
            }

            return op;
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
