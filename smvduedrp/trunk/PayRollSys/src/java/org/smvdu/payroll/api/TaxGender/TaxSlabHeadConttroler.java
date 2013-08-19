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
public class TaxSlabHeadConttroler {

    /** Creates a new instance of TaxSlabHeadConttroler */
    private int orgCode;
    public TaxSlabHeadConttroler() {
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

    public ArrayList<TaxSlabHeadBean> loadSlabHead()
    {
        try
        {
            ArrayList<TaxSlabHeadBean> taxHeadValue = new ArrayList<TaxSlabHeadBean>();
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            pst = cn.prepareStatement("select * from slab_head where sl_orgCode = '"+orgCode+"' order by sl_head_code asc,sl_start_value asc,sl_end_value asc");
            ResultSet rs;
            rs = pst.executeQuery();
            while(rs.next())
            {
                TaxSlabHeadBean taxHeadBean = new TaxSlabHeadBean();
                taxHeadBean.setSlabHeadCode(rs.getInt(1));
                taxHeadBean.setSlabName(rs.getString(2));
                taxHeadBean.setStartSlabValue(rs.getFloat(3));
                taxHeadBean.setEndSlabValue(rs.getFloat(4));
                taxHeadBean.setPercent(rs.getFloat(5));
                taxHeadValue.add(taxHeadBean); 

            }
            return taxHeadValue;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }

    }
}
