/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.composite.SessionController;
import org.smvdu.payroll.beans.setup.TaxCalculationType;

/**
 *
 * @author shikhar
 */
public class TaxCalculationTypeDB {
    

    //private PreparedStatement ps;
    //private Statement stmt;
    //private ResultSet rs;
    private UserInfo userBean;
    
    SessionController sessionId = new SessionController();
     public TaxCalculationTypeDB()
    {
        userBean = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
    }
   
     public Exception update(TaxCalculationType taxcalctype)    {
        try{
            
            Connection c = new CommonDB().getConnection();
            PreparedStatement ps;
            ResultSet rst;
            if(this.check()){
           // System.out.println("this check===="+this.check());   
            ps=c.prepareStatement("update org_tax_calc_type set ct_calctype=? where ct_org_code="+userBean.getUserOrgCode()+" and ct_sess_id= "+sessionId.getCurrentSession());
            ps.setString(1, taxcalctype.getCalcType().toUpperCase());
            ps.executeUpdate();
            ps.clearParameters();
            }
            else{
            ps=c.prepareStatement("insert into org_tax_calc_type(ct_calctype,ct_org_code,ct_sess_id) values(?,?,?)");
            ps.setString(1, taxcalctype.getCalcType().toUpperCase());
            ps.setInt(2,userBean.getUserOrgCode()); 
            ps.setInt(3,sessionId.getCurrentSession());
            ps.executeUpdate();
            ps.clearParameters();    
            }
            ps.close();
            c.close();
            return null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return e;
        }
      }
     public TaxCalculationType loadCalculationType()   {
        try
        {   
           TaxCalculationType data = new TaxCalculationType();
           /* data.setCalcType("QUATERLY");
            data.setOrgCode(userBean.getUserOrgCode());
            data.setSession(sessionId.getCurrentSession()); */
            PreparedStatement ps;
            ResultSet rs;
            Connection c = new CommonDB().getConnection();
            // if(this.check()){
            ps=c.prepareStatement("select ct_calctype from org_tax_calc_type where ct_org_code= "+userBean.getUserOrgCode()+" and ct_sess_id= "+sessionId.getCurrentSession());
            rs=ps.executeQuery();
            while(rs.next())
            {
                // System.out.println("this check===="+check()+"calctype===="+rs.getString(1)+"radiobutton===="+get.);  
                data.setCalcType(rs.getString(1));
                data.setOrgCode(userBean.getUserOrgCode());
                data.setSession(sessionId.getCurrentSession());
                data.setActiveRadio(true);
               // System.out.println("this check===="+check()+"calctype===="+rs.getString(1)
                       // +"Calctype===="+data.getCalcType()+"radiobutton===="+data.isActiveRadio());
               
            }
            rs.close();
            ps.close();
            c.close();
            return data;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
     
     public boolean check(){
      boolean c=false;
        try
        {  
            PreparedStatement ps;
            ResultSet rs;
            Connection cn = new CommonDB().getConnection();  
            ps=cn.prepareStatement("select exists(select ct_calctype from org_tax_calc_type where ct_org_code= "+userBean.getUserOrgCode()+" and ct_sess_id= "+sessionId.getCurrentSession()+")");
            rs=ps.executeQuery();   
            if(rs.next())
            {
             if(rs.getBoolean(1)){
                 c=true;
             }
            }
            rs.close();
            ps.close();
            cn.close();
            return c;
        }
         catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
     }

 }