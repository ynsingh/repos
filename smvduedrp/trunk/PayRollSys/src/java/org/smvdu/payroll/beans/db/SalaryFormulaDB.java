/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.faces.component.UIData;
import org.smvdu.payroll.beans.SalaryFormula;

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
public class SalaryFormulaDB {
    private PreparedStatement ps;
    private ResultSet rs;


    public String loadFormula(int code)    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select sf_sal_formula from salary_formula "
                    + "left join salary_head_master on sh_id = sf_sal_id where sf_sal_id=?");
            ps.setInt(1, code);
            rs=ps.executeQuery();
            rs.next();
            String formula = rs.getString(1);
            rs.close();
            ps.close();
            c.close();
            return formula;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "";
        }
    }
    public ArrayList<SalaryFormula> loadFormula()    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select sh_id,sh_name,sf_sal_formula from  salary_head_master "
                    + "left join  salary_formula on sf_sal_id = sh_id where sh_calc_type=1");
            rs=ps.executeQuery();
            ArrayList<SalaryFormula> data = new ArrayList<SalaryFormula>();
            while(rs.next())
            {
                SalaryFormula sf = new SalaryFormula();
                sf.setSalCode(rs.getInt(1));
                sf.setName(rs.getString(2));
                sf.setFormula(rs.getString(3));
                data.add(sf);
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
    public boolean save(UIData data)    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("delete from salary_formula ");
            ps.executeUpdate();
            ps.close();
            ps  = c.prepareStatement("insert into salary_formula values(?,?)");
            ArrayList<SalaryFormula> sdata = (ArrayList<SalaryFormula>) data.getValue();
            for(SalaryFormula sf : sdata)
            {
                ps.setInt(1, sf.getSalCode());
                ps.setString(2, sf.getFormula());
                ps.executeUpdate();
                ps.clearParameters();
            }
            ps.close();
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

}
