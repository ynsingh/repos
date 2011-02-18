/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 *
 * @author Algox
 */
public class Config {


    public Exception saveConf(Properties ps)
    {
        try
        {
            FileOutputStream fout = new FileOutputStream("conf.xml");
            ps.storeToXML(fout, "Database Configuration");
            fout.close();
            return null;
        }
        catch(Exception e)
        {
            return e;
        }
    }
    public void readConf()
    {
        try
        {
            FileInputStream fin = new FileInputStream("./config.xml");
            Properties ps = new Properties();
            ps.loadFromXML(fin);
            fin.close();
            CommonDB.setHostName(ps.getProperty("host"));
            CommonDB.setPortNo(ps.getProperty("post"));
            CommonDB.setUsername(ps.getProperty("user"));
            CommonDB.setPassword(ps.getProperty("password"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
