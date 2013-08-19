/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.UserTask;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.module.attendance.LoggedEmployee;
/**
 *
 * @author ERP
 */
public class UserTaskDB {

    /** Creates a new instance of UserTaskDB */
    private String userName;
    private int orgCode;
    public UserTaskDB() {
        try {
            LoggedEmployee le = (LoggedEmployee) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
            if (le == null) {
                UserInfo uf = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
                orgCode = uf.getUserOrgCode();
                userName = uf.getUserName();
            } else {
               
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<UserTaskBeans> getUserTaskDetails()
    {
        try
        {
            ArrayList<UserTaskBeans> userTask = new ArrayList<UserTaskBeans>();
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst = cn.prepareStatement("select * from user_task_list");
            ResultSet rst = pst.executeQuery();
            while(rst.next())
            {
                UserTaskBeans ust = new UserTaskBeans();
                ust.setTaskId(rst.getString(3));
                ust.setCheck(rst.getBoolean(4));
                userTask.add(ust);
            }
            return userTask;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        
    }

    public void insertNewTaskList()
    {
        try
        {
            
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst = null;
            pst = cn.prepareStatement("delete from task_list");
            pst.executeUpdate();
            ArrayList<String> s2 = new ArrayList<String>();
            Properties prop = new Properties();
            Properties prop1 = new Properties();
            ArrayList<String> keyValue = new ArrayList<String>();
            ArrayList<String> keyValue1 = new ArrayList<String>();
            FacesContext cont = FacesContext.getCurrentInstance();
            ExternalContext econtext = cont.getExternalContext();
            String path = econtext.getRealPath("/");
            String filePath = path + File.separator + "Propertie/Tree.properties";
            String filePath1 = path + File.separator + "Propertie/value2.properties";
            FileInputStream f = new FileInputStream(new File(filePath));
            FileInputStream f1 = new FileInputStream(new File(filePath1));
            prop.load(f); 
            prop1.load(f1); 
            Enumeration key = prop.keys();
            Enumeration key1 = prop1.keys();
            
            while(key.hasMoreElements() && key1.hasMoreElements())
            {
                String keys = key.nextElement().toString();
                keyValue1.add(keys); 
                if(keys.matches("^[a-zA-Z]*$") == true)
                {
                    keyValue.add(keys);
             //       System.out.println(prop.getProperty(keys)+" : "+prop1.getProperty(keys)); 
                }
            }
            for(String k : keyValue)
            {
                for(String k1 : keyValue1)
                {
                    if(k1.matches("["+k+"0-9]*$") == true)
                    {
                        s2.add(k1+"-"+prop.getProperty(k1)+"-"+prop1.getProperty(k1));
                        System.out.println(prop.getProperty(k1) +" : "+prop1.getProperty(k1)); 
                    }
                }
            }
            String keyId;
            String keyIdValue;
            String keyIdMenuValue;
            for(String k : s2)
            {
                System.out.println(k);
            }
            for(String j : s2)
            {
                keyId = new String();
                keyIdValue = new String();
                keyIdMenuValue = new String();
                String[] arrayValue = j.split("-");
                keyId = arrayValue[0];
                keyIdValue = arrayValue[1];
                keyIdMenuValue = arrayValue[2];
                pst = cn.prepareStatement("insert into task_list(task_id,task_name,task_menu_name) values(?,?,?)");
                pst.setString(1, keyId);
                pst.setString(2, keyIdValue);
                pst.setString(3, keyIdMenuValue);
                pst.executeUpdate();
                pst.clearParameters();
            }
            f.close();
            f1.close();
            cn.close();
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    
    public void insertTaskList()
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst = null;
            pst = cn.prepareStatement("delete from task_list");
            pst.executeUpdate();
            ArrayList<String> key = new ArrayList<String>();
            Properties p = new Properties();
            FacesContext cont = FacesContext.getCurrentInstance();
            ArrayList<String> str = new ArrayList<String>();
            ExternalContext econtext = cont.getExternalContext();
            String path = econtext.getRealPath("/");
            String filePath = path + File.separator + "Propertie/Tree.properties";
            FileInputStream f = new FileInputStream(new File(filePath));
            p.load(f);
            Enumeration en = p.keys();
            while(en.hasMoreElements())
            {
                key.add((String) en.nextElement());
            }
            for(String keyId : key)
            {
                pst = cn.prepareStatement("insert into task_list(task_id,task_name) values('"+keyId+"','"+p.getProperty(keyId)+"')");
                pst.executeUpdate();
                pst.clearParameters();
            }
            pst.close();
            cn.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public ArrayList<UserTaskBeans> taskList()
    {
        try
        {
            ArrayList<UserTaskBeans> task = new ArrayList<UserTaskBeans>();
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            pst = cn.prepareStatement("select * from task_list");
            ResultSet rst;
            rst = pst.executeQuery();
            while(rst.next())
            {
                UserTaskBeans utb = new UserTaskBeans();
                utb.setTaskId(rst.getString(2));
                utb.setTaskName(rst.getString(3));
                utb.setTaskValue(rst.getString(4)); 
                task.add(utb);
            }
            rst.close();
            pst.close();
            cn.close();
            return task;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    public Exception saveUserTaskList(ArrayList<UserTaskBeans> utb)
    {
        try
        {
            Connection cn;
            cn = new CommonDB().getConnection();
            PreparedStatement pst = null;
            PreparedStatement pst1 = null;
            ResultSet rst;
            int status;
            for(UserTaskBeans u : utb)
            {
                status = 0;
                if(u.isCheck() == true)
                {
                    status = 1;
                }
                else
                {
                    status = 0;
                }
                pst1 = cn.prepareStatement("select task_id from user_task_list where user_id = '"+userName+"' and task_id = '"+u.getTaskId()+"' and org_code = '"+orgCode+"'");
                rst = pst1.executeQuery();
                if(rst.next() == false)
                {
                    pst = cn.prepareStatement("insert into user_task_list(user_id,task_id,org_code,flag) values('"+userName+"','"+u.getTaskId()+"','"+orgCode+"','"+status+"')");
                    pst.executeUpdate();
                    pst.clearParameters();
                }
                else
                {
                    pst = cn.prepareStatement("update user_task_list set task_id = '"+u.getTaskId()+"' where user_id = '"+userName+"'");
                }
            }
            pst.close();
            cn.close();
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex;
        }
    }
}
