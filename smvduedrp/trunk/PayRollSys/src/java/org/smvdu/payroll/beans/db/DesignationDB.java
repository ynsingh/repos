/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.setup.Designation;

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
public class DesignationDB {

    private PreparedStatement ps;
    private ResultSet rs;

    private int orgCode;

    public DesignationDB()
    {
        UserInfo uf = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
        orgCode = uf.getUserOrgCode();
    }

    public Designation convert(String code)    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select desig_code,desig_name from designation_master where desig_name=?");
            ps.setString(1, code);
            rs =ps.executeQuery();
            rs.next();
            Designation d = new Designation();
            d.setCode(rs.getInt(1));
            d.setName(rs.getString(2));
            rs.close();
            ps.close();
            c.close();
            return d;

        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public void update(ArrayList<Designation> depts)    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("update designation_master set desig_name=? where desig_code=? and d_org_id='"+orgCode+"'");
            for(Designation dp : depts)
            {
                ps.setString(1, dp.getName().toUpperCase());
                ps.setInt(2, dp.getCode());
                ps.executeUpdate();
                ps.clearParameters();
            }
            ps.close();
            c.close();
        }
        catch(Exception e)
        {
            //Logger.getAnonymousLogger().log(Log., e.getMessage());
        }
    }
    public ArrayList<Designation> loadDesignations()   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select * from designation_master where d_org_id = '"+orgCode+"'");
            rs=ps.executeQuery();
            ArrayList<Designation> data = new ArrayList<Designation>();
            while(rs.next())
            {
                Designation dept = new Designation();
                dept.setCode(rs.getInt(1));
                dept.setName(rs.getString(2));
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
    public Exception save(String desigName)   {
        try
        {

            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into designation_master(desig_name,d_org_id) values(?,?) ");
            ps.setString(1, desigName.toUpperCase());
            ps.setInt(2, orgCode);
            ps.executeUpdate();
            //rs=ps.getGeneratedKeys();
            //rs.next();
            //int code = rs.getInt(1);
            //rs.close();
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

}
