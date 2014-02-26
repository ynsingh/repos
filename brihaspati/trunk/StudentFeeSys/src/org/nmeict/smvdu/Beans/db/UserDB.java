/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmeict.smvdu.Beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.nmeict.smvdu.Beans.OrgLoginDetails;
import org.nmeict.smvdu.Beans.RemoteAuthentication.VerifiedLoginPage;

/**
 * @author Shaista Bano
 */
public class UserDB {
    public int validate(String user, String pass, String userCode)  {
        String empCode =null;
        try{
            Connection c = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            
            pst = c.prepareStatement("select user_id,admin_pass,flag from admin_records where user_id='"+user+"' and admin_pass='"+pass+"' and flag='"+1+"'");;
            rst = pst.executeQuery();
            if(rst.next() == true)
            {
                return 2;
            }
            else{
                pst = c.prepareStatement("select  org_code, org_pen_email from  college_pending_status where org_request_status='"+0+"' and org_code='"+userCode+"' and org_pen_email='"+user+"'");;
                rst = pst.executeQuery();
                if(rst.next() == true)
                {
                    return 3;
                }
                else
                    return -1;
            }
                
                
        }
        catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public void validate(String email) {
        try{
            OrgLoginDetails o = new OrgLoginDetails();
            Connection c = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = c.prepareStatement("select user_id,admin_pass,flag from admin_records where user_id='"+email+"' and flag='"+1+"'");;
            rst = pst.executeQuery();
             System.out.print("rst=="+rst);        
            int tempInt =-1;
            //OrgLoginDetails(String orgId, String adminId, String orgPassword) 
            if(rst.next() == true)
            {
                o.setAdminId(rst.getString(1));
                o.setOrgPassword(rst.getString(3));
                tempInt = 2;
            }
            
            pst = c.prepareStatement("select org_id,org_adminemailid, org_master_password from org_profile where org_adminemailid='"+email+"'");;
            //pst = c.prepareStatement("select * from org_profile where org_adminemailid='"+email+"'");;
            rst = pst.executeQuery();
            System.out.print(rst);
            if(rst.next() == true)
            {
                //System.out.println("rst.getString(1)="+rst.getString(1)+"\n rst.getString(2)="+rst.getString(2)+"\nrst.getString(3)=="+rst.getString(3));
                o.setOrgId(rst.getString(1));
                o.setAdminId(rst.getString(2));
                o.setOrgPassword(rst.getString(3));
                
                tempInt = 1;
            }
            //System.out.print("tempInt=="+tempInt);
            new VerifiedLoginPage().validateFromdatabase(o, tempInt);
        }
        catch(Exception ex){ System.out.println("Exception in validate method of UserDB class"+ex.getMessage());
        }
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
