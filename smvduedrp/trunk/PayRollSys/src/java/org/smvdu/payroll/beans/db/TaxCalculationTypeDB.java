/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.composite.SessionController;
import org.smvdu.payroll.beans.setup.TaxCalculationType;

/**
*  Copyright (c) 2010, 2011, 2014 SMVDU Katra.
*  Copyright (c) 2015, 2016 ETRG, IITK.
*  All Rights Reserved.
** Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
** Redistributions of source code must retain the above copyright 
*  notice, this  list of conditions and the following disclaimer. 
* 
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in 
*  the documentation and/or other materials provided with the 
*  distribution. 
* 
* 
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
*  DISCLAIMED.  IN NO EVENT SHALL SMVDU OR ITS CONTRIBUTORS BE LIABLE 
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
*
* @author shikhar, Manorama Pal- seemapal30@gmail.com
*/
public class TaxCalculationTypeDB {
    

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
            ps=c.prepareStatement("select ct_calctype from org_tax_calc_type where ct_org_code= "+userBean.getUserOrgCode()+" and ct_sess_id= "+sessionId.getCurrentSession());
            rs=ps.executeQuery();
            while(rs.next())
            {
                data.setCalcType(rs.getString(1));
                data.setOrgCode(userBean.getUserOrgCode());
                data.setSession(sessionId.getCurrentSession());
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
     public String TaxCalculationMode(){
         
        try{
            PreparedStatement ps;
            ResultSet rs;
            Connection cn = new CommonDB().getConnection();  
            ps=cn.prepareStatement("select ct_calctype from org_tax_calc_type where ct_sess_id=? and ct_org_code=?");
            ps.setInt(1, sessionId.getCurrentSession());
            ps.setInt(2, userBean.getUserOrgCode());
            rs=ps.executeQuery(); 
            rs.next();
            String  taxcalcmode=rs.getString(1);
            rs.close();
            ps.close();
            cn.close();
            return taxcalcmode;
               
         }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
     }

 }
