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
public class TaxSlabHeadDB {

    private int orgCode;
    public TaxSlabHeadDB()
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

    public Exception saveSlabHead(TaxSlabHeadBean taxHeadBean)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            pst = cn.prepareStatement("insert into slab_head(slab_head_name,sl_start_value,sl_end_value,sl_percent,sl_orgCode) values('"+taxHeadBean.getSlabName()+"','"+taxHeadBean.getStartSlabValue()+"','"+taxHeadBean.getEndSlabValue()+"','"+taxHeadBean.getPercent()+"','"+orgCode+"')");
            pst.executeUpdate();
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
    public ArrayList<TaxSlabHeadBean> loadSlabInfo()
    {
        try
        {
            ArrayList<TaxSlabHeadBean> taxSlabInfo = new ArrayList<TaxSlabHeadBean>();
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = cn.prepareStatement("select sl_head_code,slab_head_name,sl_start_value,sl_end_value,sl_percent from slab_head where sl_orgCode = '"+orgCode+"'");
            rst = pst.executeQuery();
            while(rst.next())
            {
                TaxSlabHeadBean taxHeadBean = new TaxSlabHeadBean();
                taxHeadBean.setSlabHeadCode(rst.getInt(1));
                taxHeadBean.setSlabName(rst.getString(2));
               
                System.out.println("Tax Heead Bean : "+taxHeadBean.getSlabHeadCode());
                System.out.println("Tax Heead Bean : "+taxHeadBean.getSlabName());
               
                taxSlabInfo.add(taxHeadBean); 
            }
            return taxSlabInfo;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    public ArrayList<TaxSlabHeadBean> loadGenderSlabInfo(int genCode)
    {
        try
        {
            ArrayList<TaxSlabHeadBean> taxSlabInfo = new ArrayList<TaxSlabHeadBean>();
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            System.out.println("Gen code : "+genCode);
            pst = cn.prepareStatement("select sl_head_code,slab_head_name,sl_start_value,sl_end_value,sl_percent "
                    + "from emp_slab_head_master left join slab_head on "
                    + "sl_head_code = emp_slab_code where emp_gen_code = '"+genCode+"' and emp_slab_orgCode = '"+orgCode+"' order by emp_slab_code");
            rst = pst.executeQuery();
            while(rst.next())
            {
                TaxSlabHeadBean taxHeadBean = new TaxSlabHeadBean();
                taxHeadBean.setSlabHeadCode(rst.getInt(1));
                taxHeadBean.setSlabName(rst.getString(2));
                
                //empsd.setSlabSelected(true);
                System.out.println("SLab Head Code : "+taxHeadBean.getSlabHeadCode());
                System.out.println("Slab Name : "+taxHeadBean.getSlabName());
               
                taxSlabInfo.add(taxHeadBean);
            }
            return taxSlabInfo;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}
