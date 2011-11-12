/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.smvdu.payroll.beans.setup.InvestmentType;

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
public class InvestmentTypeDB {

     private PreparedStatement ps;
     private ResultSet rs;

     public Exception update(ArrayList<InvestmentType> data)
     {
         try
         {
             Connection c = new CommonDB().getConnection();
             ps=c.prepareStatement("update investment_category_master set ic_name=?,"
                     + "ic_max_limit=?,ic_deduction=? where ic_id=?");
             for(InvestmentType it : data)
             {
                 ps.setString(1, it.getName());
                 ps.setFloat(2, it.getMaxLimit());
                 ps.setFloat(3, it.getEffectivePercentage());
                 ps.setInt(4, it.getCode());
                 ps.executeUpdate();
                 ps.clearParameters();
             }
             ps.close();
             c.close();
             return null;
         }
         catch(Exception e)
         {
             return e;
         }
     }

     public ArrayList<InvestmentType> load() {
         try
         {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select * from investment_category_master");
            rs=ps.executeQuery();
            ArrayList<InvestmentType> data = new ArrayList<InvestmentType>();
            while(rs.next())
            {
                InvestmentType it = new InvestmentType();
                it.setCode(rs.getInt(1));
                it.setName(rs.getString(2));
                it.setMaxLimit(rs.getFloat(3));
                it.setEffectivePercentage(rs.getFloat(4));
                data.add(it);
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
     public Exception save(InvestmentType it)  {
         try
         {
             Connection c = new CommonDB().getConnection();
             ps=c.prepareStatement("insert into investment_category_master(ic_name,"
                     + "ic_max_limit,ic_deduction) values(?,?,?)");
             ps.setString(1, it.getName().toUpperCase());
             ps.setFloat(2, it.getMaxLimit());
             ps.setFloat(3, it.getEffectivePercentage());
             ps.executeUpdate();
             ps.close();
             c.close();
             return null;
         }
         catch(Exception e)
         {
             return e;
         }
     }

}
