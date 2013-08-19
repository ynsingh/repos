/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.TaxGender;
import java.sql.*;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.module.attendance.LoggedEmployee;
/**
 *
 * @author ERP
 */
public class GenderDB {
    private int orgCode;
    public  GenderDB()
    {
                  LoggedEmployee le = (LoggedEmployee) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
        if(le==null)
        {
            UserInfo uf = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            orgCode = uf.getUserOrgCode();
        }
        else
        {
            orgCode = le.getUserOrgCode();
        }
    }

    public boolean saveGender(Gender gen)
    {
        try
        {
            Connection cn;
            cn = new CommonDB().getConnection();
            PreparedStatement pst;
            pst = cn.prepareStatement("insert into ts_gender(gender_name,orgCode) values('"+gen.getGenderName()+"','"+orgCode+"')");
            pst.executeUpdate();
            pst.close();
            cn.close();
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }


    public ArrayList<Gender> loadGenderDetail(){
        try
        {
            ArrayList<Gender> genDetail = new ArrayList<Gender>();
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            pst  = cn.prepareStatement("select ts_seq,gender_Name from ts_gender where orgCode = '"+orgCode+"' order by ts_seq asc");
            ResultSet rst;
            rst = pst.executeQuery();
            while(rst.next())
            {
                Gender gen = new Gender();
                gen.setGenderCode(rst.getInt(1));
                gen.setGenderName(rst.getString(2));
                genDetail.add(gen);
            }
            rst.close();
            pst.close();
            cn.close();
            return genDetail;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }

    }
}
