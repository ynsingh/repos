/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.pf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.composite.SessionController;
import org.smvdu.payroll.beans.db.CommonDB;

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
public class PFTransactionDB {

    private PreparedStatement ps;
    private ResultSet rs;


    private int orgCode=0;
    java.util.Date date;
    DateFormat dateFormat;
    java.sql.Date da;
    UserInfo ap;
    public PFTransactionDB()
    {
        ap = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
        orgCode = ap.getUserOrgCode();
    }
    public ArrayList<PFTransaction> load()
    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select emp_code,emp_name,pf_amount,pf_ac_date from pf_account_master left join "
                 //   + "employee_master on emp_id = pf_ac_id where emp_org_code= ?");
		+ "employee_master on emp_id = pf_ac_id where emp_org_code= ? and pf_org_id='"+orgCode+"'");
            ps.setInt(1, orgCode);
            rs= ps.executeQuery();
            ArrayList<PFTransaction> data=  new ArrayList<PFTransaction>();
            while(rs.next())
            {
                PFTransaction pf = new PFTransaction();
                pf.setEmpCode(rs.getString(1));
                pf.setEmployee(rs.getString(2));
                pf.setAmount(rs.getInt(3));
                pf.setDate(rs.getString(4));
                data.add(pf);
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

    




    public void save(PFTransaction pft)
    {
        try
        {
            SessionController session = new SessionController();
            Connection c = new CommonDB().getConnection();
            dateFormat = new SimpleDateFormat("yy-MM-dd");
            date = (java.util.Date) dateFormat.parse(ap.getCurrentDate());
            ps=c.prepareStatement("insert into pf_account_master(pf_ac_id,pf_amount,"
                 //+ "pf_sess_id,pf_type,pf_ac_date) values(?,?,?,?,'"+new java.sql.Date(date.getTime())+"')");
		+ "pf_sess_id,pf_type,pf_ac_date,pf_org_id) values(?,?,?,?,'"+new java.sql.Date(date.getTime())+"','"+orgCode+"')");
            ps.setInt(1, pft.getEmpId());
            ps.setInt(2, pft.getAmount());
            ps.setInt(3, session.getCurrentSession());
            ps.setBoolean(4,true);
            ps.executeUpdate();
            ps.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
