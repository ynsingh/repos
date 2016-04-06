/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.composite.SessionController;
import org.smvdu.payroll.beans.setup.InvestmentHead;

/**
 *
 *  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or
*  without modification, are permitted provided that the following
*  conditions are met:
**  Redistributions of source code must retain the above copyright
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
*  Contributors: Members of ERP Team @ SMVDU, Katra
*
 */
public class InvestmentHeadDB {

    private PreparedStatement ps;
    private ResultSet rs;
   
      private UserInfo userBean;
    
   SessionController sessionId = new SessionController();

   public InvestmentHeadDB()
    {
        userBean = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
    }
    
    public ArrayList<InvestmentHead> loadHeads()   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select ih_id,ih_name,ih_benefit,ih_details,ic_id,ic_name "
                    + " from investment_heads left join investment_category_master on ic_id = ih_under where ih_org_id='"+userBean.getUserOrgCode()+"'");
            rs=ps.executeQuery();
            ArrayList<InvestmentHead> data = new ArrayList<InvestmentHead>();
            while(rs.next())
            {
                InvestmentHead dept = new InvestmentHead();
                dept.setCode(rs.getInt(1));
                dept.setName(rs.getString(2));
                dept.setBenefit(rs.getBoolean(3));
                dept.setDetails(rs.getString(4));
                dept.setUnderGroupCode(rs.getInt(5));
                dept.setUnderGroupName(rs.getString(6));
                data.add(dept);
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

                                   //  add here update command
        public Exception update(ArrayList<InvestmentHead> ih)   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("update investment_heads set ih_name=?, ih_benefit= ?,"
                   + " ih_details= ? where ih_id = ? and ih_org_id='"+userBean.getUserOrgCode()+"' ");
             for(InvestmentHead investmentHead : ih)
             {
                 ps.setString(1, investmentHead.getName().toUpperCase());
                 ps.setBoolean(2, investmentHead.isBenefit());
                 ps.setString(3, investmentHead.getDetails());
                 ps.setInt(4, investmentHead.getCode());
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
    public Exception save(InvestmentHead ih){
	if(check(ih)==true){
         	try
        	{
            		Connection c = new CommonDB().getConnection();
            		ps=c.prepareStatement("insert into investment_heads(ih_name,ih_benefit,"
                    		+ "ih_details,ih_under,ih_org_id) values(?,?,?,?,?)",1);
            		ps.setString(1, ih.getName().toUpperCase());
            		ps.setBoolean(2, ih.isBenefit());
            		ps.setString(3, ih.getDetails());
            		ps.setInt(4, ih.getUnderGroupCode());
            		ps.setInt(5, userBean.getUserOrgCode());
            		ps.executeUpdate();
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
	 //}
        else
        {
          FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Investment head already added", ""));
          return null;
        }


    }

	public boolean check(InvestmentHead ih)   {
        boolean flag=true;
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select ih_name,ih_details,ih_under"
                    + " from investment_heads where ih_org_id='"+userBean.getUserOrgCode()+"'");
            rs=ps.executeQuery();
            ArrayList<InvestmentHead> data = new ArrayList<InvestmentHead>();
            while(rs.next())
            {
                if((rs.getString(1).equalsIgnoreCase(ih.getName())) && (rs.getString(2).equalsIgnoreCase(ih.getDetails())) && (rs.getInt(3)==ih.getUnderGroupCode()))
                {
                 flag=false;
                 break;
                }
            }
            rs.close();
            ps.close();
            c.close();
            return flag;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

	
}
