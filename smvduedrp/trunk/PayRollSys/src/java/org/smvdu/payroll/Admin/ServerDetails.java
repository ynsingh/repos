/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.Admin;

import org.smvdu.payroll.beans.db.CommonDB;
import java.sql.*;
import java.util.ArrayList;
import org.smvdu.payroll.beans.setup.Org;
/**
 *
 * @author KESU
 */
public class ServerDetails {
    
  /**
   * 
   * Save Server Details Ip Address, Port No, Client
   * @param org
   * @param code
   * @return 
   */
    
    public boolean saveServerDetail(Org org,int code)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            cn.setAutoCommit(false); 
            PreparedStatement pst = cn.prepareStatement("insert into client_details(ip_address,port,client_org_code,flag) values('"+org.getIpAddress()+"','"+org.getPort()+"','"+code+"','"+1+"')");
            pst.executeUpdate();
            cn.commit();
            cn.close();
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
    
    /**
     * 
     * Load Server Details Org Name, Org email, Ip Address, Port
     * @return 
     */
    
    public ArrayList<Org> getServerDetails()
    {
        try
        {
            ArrayList<Org> o = new ArrayList<Org>();
            Connection cn = new CommonDB().getConnection();
            cn.setAutoCommit(false); 
            PreparedStatement pst = cn.prepareStatement("select org_id,org_name,org_email,org_web,ip_address,port,flag from client_details inner join org_profile on org_id = client_org_code ");
            ResultSet rst;
            rst = pst.executeQuery();
            while(rst.next())
            {
                Org org = new Org();
                org.setName(rst.getString(2)); 
                org.setEmail(rst.getString(3)); 
                org.setWeb(rst.getString(4)); 
                org.setIpAddress(rst.getString(5)); 
                org.setPort(rst.getString(6)); 
                if(rst.getInt(7) == 1)
                {
                    org.setImgUrl("Active.png");
                    org.setStatus(true); 
                }
                else
                {
                    org.setImgUrl("InActive.png"); 
                    org.setStatus(false);  
                }
                o.add(org); 
            }
            rst.close();
            pst.close();
            cn.close();
            return o;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
   
    /**
     * 
     * Load All Server Details
     * @return 
     */
    public ArrayList<Org> getIpServerDetails()
    {
        try
        {
            ArrayList<Org> o = new ArrayList<Org>();
            Connection cn = new CommonDB().getConnection();
            cn.setAutoCommit(false); 
            PreparedStatement pst = cn.prepareStatement("select * from client_details ");
            ResultSet rst;
            rst = pst.executeQuery();
            while(rst.next())
            {
                Org org = new Org();
                org.setIpAddress(rst.getString(2)); 
                org.setPort(rst.getString(3)); 
                if(rst.getInt(5) == 1)
                {
                    org.setImgUrl("Active.png");
                    org.setIpStatus(true); 
                }
                else
                {
                    org.setImgUrl("InActive.png"); 
                    org.setIpStatus(false); 
                }
                o.add(org); 
            }
            rst.close();
            pst.close();
            cn.close();
            return o;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
    public boolean getIpList(String ipAddress)
    {
        try
        {
            boolean flag;
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = cn.prepareStatement("select ip_address from client_details where flag = '"+0+"' and ip_address = '"+ipAddress+"'");
            rst = pst.executeQuery();
            if(rst.next())
            {
                flag = true;
            }
            else
            {
                flag = false;
            }
            rst.close();
            pst.close();
            cn.close();
            return flag;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
}
