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
public class EmployeeSlabDetailDB {

    private int orgCode;
    public EmployeeSlabDetailDB()
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
    public ArrayList<EmployeeSlabDetail> loadGenderDetail(){
        try
        {
            ArrayList<EmployeeSlabDetail> genDetail = new ArrayList<EmployeeSlabDetail>();
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            pst  = cn.prepareStatement("select ts_seq,gender_Name from ts_gender where orgCode = '"+orgCode+"' order by ts_seq asc");
            ResultSet rst;
            rst = pst.executeQuery();
            while(rst.next())
            {
                EmployeeSlabDetail gen = new EmployeeSlabDetail();
                gen.setCode(rst.getInt(1));
                gen.setName(rst.getString(2));
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
    
    public Exception save(ArrayList<EmployeeSlabDetail> empSlabDetails, int genCode)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            pst = cn.prepareStatement("delete from emp_slab_head_master where emp_gen_code = '"+genCode+"' and emp_slab_orgCode = '"+orgCode+"'");
            pst.executeUpdate();
            pst.close();
            for(EmployeeSlabDetail empsd : empSlabDetails)
            {
                System.out.println("DAta Should Be Write Here : "+genCode+" : "+empsd.getSlabHeadCode());
                pst = cn.prepareStatement("insert into emp_slab_head_master(emp_gen_code,emp_slab_code,emp_slab_orgCode) values('"+genCode+"','"+empsd.getSlabHeadCode()+"','"+orgCode+"')");
                pst.executeUpdate();
                pst.clearParameters();
            }
            pst.close();
            cn.close();
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex;
        }
    }
    
    public ArrayList<EmployeeSlabDetail> loadEmployeeSlabDetails(int genCode)
    {
        try
        {
            
            ArrayList<EmployeeSlabDetail> emplSlabDetails = new ArrayList<EmployeeSlabDetail>();
            ArrayList<TaxSlabHeadBean> taxSlabHeadBean = new TaxSlabHeadDB().loadGenderSlabInfo(genCode);
            for(TaxSlabHeadBean ta : taxSlabHeadBean)
            {
                System.out.println("In Tax Slab Head bean : "+ta.getSlabHeadCode());
                System.out.println("In Tax Slab Head bean : "+ta.getSlabName());
                
            }
            for(TaxSlabHeadBean taxSlabHead : new TaxSlabHeadDB().loadSlabInfo())
            {
                EmployeeSlabDetail empsd = new EmployeeSlabDetail();
                empsd.setSlabHeadCode(taxSlabHead.getSlabHeadCode());
                empsd.setSlabName(taxSlabHead.getSlabName());
                
                if(taxSlabHeadBean.contains(empsd) == true)
                {
                    System.out.println("Data Should Be Write Here .. hi");
                    empsd.setSlabSelected(true);
                }
                emplSlabDetails.add(empsd);
            }
            return emplSlabDetails;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}
