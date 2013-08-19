/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.UserOperationBeans;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author ERP
 */
public class TaskKeyValue {


     public void addNodes(String path, Properties properties) {
        boolean end = false;
        int counter = 1;

        while (!end)
        {
            String key = path != null ? path + "." + counter : String.valueOf(counter);
            String value = properties.getProperty(key);
            if (value != null)
            {
                System.out.println("Key : "+key + " Value : "+properties.getProperty(key));
                addNodes(key,properties);
                counter++;
            }
            else
            {
                end = true;
            }
        }
    }
    public void loadFile(FileInputStream input,Properties p)
    {
        try
        {
            p.load(input);
            addNodes(null, p);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
