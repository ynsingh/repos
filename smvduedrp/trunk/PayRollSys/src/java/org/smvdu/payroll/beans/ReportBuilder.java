/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.db.SalaryHeadDB;
import org.smvdu.payroll.beans.setup.SalaryHead;

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
public class ReportBuilder {

    private PreparedStatement ps;
    private ResultSet rs;

    
    
    
    public void copyData()
    {
        UserInfo user = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
        try
        {
            Connection c = new CommonDB().getConnection();
           ps=c.prepareStatement("insert into temp_sal_data(temp_code) "
                   + "(select distinct sd_emp_code from salary_data where month(sd_date)=? and year(sd_date)=? )"); 
           ps.setInt(1, user.getCurrentMonth());
           ps.setInt(2, user.getCurrentYear());
           ps.executeUpdate();
           ps.close();
            for(SalaryHead sh : new SalaryHeadDB().loadAllHeads())
            {
                ps=c.prepareStatement("update temp_sal_data left join salary_data on "
                        + "sd_emp_code = temp_code set "+sh.getAlias()+" = sd_amount where sd_head_code=?  "
                        + "and month(sd_date)=? and year(sd_date)=?");
                ps.setInt(1, sh.getNumber());
                ps.setInt(2, user.getCurrentMonth());
                ps.setInt(3, user.getCurrentYear());
                ps.executeUpdate();
                ps.close();
                System.err.println(sh.getNumber()+">>>>> "+sh.getAlias()+" Copied ");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void refreshReport() {

        try {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("DROP TABLE IF EXISTS temp_sal_data");
            ps.executeUpdate();
            ps.close();
            String q = "create table temp_sal_data (temp_code varchar(40) ";
            for (SalaryHead sh : new SalaryHeadDB().loadAllHeads()) {
                q += " , " + sh.getAlias()+ " float ";

            }
            q+=");";
            System.out.print("Build "+q);
            ps= c.prepareStatement(q);
            ps.executeUpdate();
            ps.close();
            c.close();
            System.out.print("Table Built "+q);
            copyData();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
