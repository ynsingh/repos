/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.TreeGen;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


/**
 *
 * @author ERP
 */
public class TreeBuilderBean {

    /** Creates a new instance of TreeBuilderBean */
    private ArrayList<TreeBeanImpl> rootNode;
    public TreeBuilderBean() {
    }
    
    public void initNode()
    {
        try
        {
            FacesContext cont = FacesContext.getCurrentInstance();
            ExternalContext econtext = cont.getExternalContext();
            String path = econtext.getRealPath("/");
            String filePath = path + File.separator + "Propertie/Tree.properties";
            FileInputStream f = new FileInputStream(new File(filePath));    
            //InputStream i=econtext.getResourceAsStream(filePath);
            Properties p = new Properties();
            p.load(f);
            Enumeration em = p.keys();
            while(em.hasMoreElements())
            {

            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public ArrayList<TreeBeanImpl> getRootNode() {
        return rootNode;
    }

    public void setRootNode(ArrayList<TreeBeanImpl> rootNode) {
        this.rootNode = rootNode;
    }
}
