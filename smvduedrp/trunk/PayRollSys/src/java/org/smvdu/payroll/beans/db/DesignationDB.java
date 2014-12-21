/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.setup.Department;
import org.smvdu.payroll.beans.setup.Designation;
import org.smvdu.payroll.beans.upload.UploadFile;

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
    public Exception update(ArrayList<Designation> depts)    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("update designation_master set desig_dcode=?, desig_name=?, desig_nickname=?" 
                                +" where desig_code=? and d_org_id='"+orgCode+"'");
            for(Designation dp : depts)
            {
                ps.setString(1, dp.getDCode());
                ps.setString(2, dp.getName().toUpperCase());
                ps.setString(3, dp.getNickName().toUpperCase());
                ps.setInt(4, dp.getCode());
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
            //Logger.getAnonymousLogger().log(Log., e.getMessage());
        }
    }
    public ArrayList<Designation> loadDesignations()   {
        try
        {
            Connection c = new CommonDB().getConnection();
            //ps=c.prepareStatement("select * from designation_master where d_org_id = '"+orgCode+"'");
            //modify 16oct 2014 for dcode and nickname
            ps=c.prepareStatement("select * from designation_master where d_org_id = '"+orgCode+"'");
            rs=ps.executeQuery();
            ArrayList<Designation> data = new ArrayList<Designation>();
            while(rs.next())
            {
                Designation dept = new Designation();
                dept.setCode(rs.getInt(1));
                dept.setDCode(rs.getString(2));
                dept.setName(rs.getString(3));
                dept.setNickName(rs.getString(4));
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
    //public Exception save(String desigName)   {
    public Exception save(Designation desig)   {
        try
        {

            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into designation_master(desig_dcode, desig_name, desig_nickname, d_org_id) values(?,?,?,?)");
            ps.setString(1, desig.getDCode());
            ps.setString(2, desig.getName().toUpperCase());
            ps.setString(3, desig.getNickName().toUpperCase());
            ps.setInt(4, orgCode);
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
    
     public Exception saveFile(UploadFile file)   {
        try
        {
            Connection c = new CommonDB().getConnection();
            
            ps=c.prepareStatement("insert into designation_master(desig_dcode, desig_name, desig_nickname, d_org_id) values(?,?,?,?)");
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/tmp");
            CSVReader reader = new CSVReader(new FileReader(path+"/"+file.getName()), ',', '\"', 1);
            ColumnPositionMappingStrategy<Designation> mappingStrategy 
                                 = new ColumnPositionMappingStrategy<Designation>();
            mappingStrategy.setType(Designation.class);
            String[] columns = new String[] {"DCode","Name","NickName"};
            mappingStrategy.setColumnMapping(columns);
        
            CsvToBean<Designation> csv = new CsvToBean<Designation>();
            List<Designation> DesignationList = csv.parse(mappingStrategy, reader);

        
            for (int i = 0; i < DesignationList.size(); i++) 
            {
                
                Designation DDetail = DesignationList.get(i);
                // display CSV values
                
                ps.setString(1,DDetail.getDCode());
                ps.setString(2,DDetail.getName().toUpperCase());
                ps.setString(3, DDetail.getNickName().toUpperCase());
                ps.setInt(4, orgCode);
                ps.executeUpdate(); 
                           

            }
            reader.close();
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
