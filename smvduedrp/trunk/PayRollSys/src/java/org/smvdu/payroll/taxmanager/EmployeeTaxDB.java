/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.taxmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class EmployeeTaxDB {

    private double totaltaxableamount;
    SessionController sessionId = new SessionController();

    public EmployeeTaxDB() {
        userBean = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
    }
    private PreparedStatement ps;
    private ResultSet rs;
    private UserInfo userBean;
    private float saving;

    public float getSaving() {
        System.out.println("Employee DB 2 : " + saving);
        return saving;
    }

    public void setSaving(float saving) {
        System.out.println("Employee DB 1 : " + saving);
        this.saving = saving;
    }

    public float getMonthlyInstallment(String empCode) {
        float amount = 0;
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("select tr_amount from tax_rep_master  where tr_emp_code=? and tr_month_code=?");
            ps.setString(1, empCode);
            ps.setInt(2, userBean.getCurrentMonth());
            rs = ps.executeQuery();
            if (rs.next()) {
                amount = rs.getInt(1);

            }

            rs.close();
            ps.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return amount;
    }

    public void saveMonthlyInstallment(String empCode, float amount) {
        System.err.println("Updating TDS Installment : " + amount);
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("delete from tds_installment_master where ti_emp_id=? and ti_year=?");
            ps.setString(1, empCode);
            ps.setInt(2, userBean.getCurrentYear());
            ps.executeUpdate();
            ps.close();
            ps = c.prepareStatement("insert into tds_installment_master values(?,?,?)");
            ps.setString(1, empCode);
            ps.setInt(2, userBean.getCurrentYear());
            ps.setFloat(3, amount);
            ps.executeUpdate();
            ps.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean updateMarkedMonths(ArrayList<ExtendedMonth> months, String empCode) {
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("delete from tax_rep_master where tr_emp_code=?");
            ps.setString(1, empCode);
            ps.executeUpdate();
            ps.close();
            ps = c.prepareStatement("insert into tax_rep_master(tr_month_code,tr_emp_code,tr_amount) values(?,?,?)");
            for (ExtendedMonth em : months) {
                if (em.getAmount() == 0) {
                    continue;
                }
                ps.setInt(1, em.getCode());
                ps.setString(2, empCode);
                ps.setFloat(3, em.getAmount());
                ps.executeUpdate();
                ps.clearParameters();
            }
            ps.close();
            c.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<ExtendedMonth> getMarkedMonths(String empCode) {

        ArrayList<ExtendedMonth> data = new ArrayList<ExtendedMonth>();
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("select tr_month_code,tr_amount "
                    + " from tax_rep_master where tr_emp_code=?");
            ps.setString(1, empCode);
            rs = ps.executeQuery();
            System.out.println("Code " + empCode);
            while (rs.next()) {
                ExtendedMonth em = new ExtendedMonth();
                em.setCode(rs.getInt(1));
                em.setAmount(rs.getFloat(2));
                System.out.println("Code : " + rs.getInt(1) + "----Value : " + rs.getInt(2));
                data.add(em);
            }
            rs.close();
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (ExtendedMonth k : data) {
            System.out.println(k.getCode());
        }
        System.out.println("************************** " + data.size());
        return data;
    }

   /* public float getTaxAmount(String empCode) {
        try {
            System.out.println("Code ET : " + empCode);
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("select et_amount from emp_tax_master where "
                    + "et_emp_id=? and et_year=? ");
            ps.setString(1, empCode);
            ps.setInt(2, userBean.getCurrentYear());
            rs = ps.executeQuery();
            float amount = 0;
            if (rs.next()) {
                amount = rs.getFloat(1);
            }

            rs.close();
            ps.close();
            c.close();
            return amount;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }*/

    // integration of tax of every month
    public boolean updateTDSSalryData(ArrayList<ExtendedMonth> months, String empCode) {
        try {
            Connection cn = new CommonDB().getConnection();
            SessionController sessionId = new SessionController();
            for (ExtendedMonth em : months) {
                ps = cn.prepareStatement("update salary_data left join tax_rep_master on  month(sd_date) = tr_month_code "
                        + "set sd_amount = (select tr_amount from tax_rep_master where tr_emp_code='" + empCode + "' and tr_sess_id='" + sessionId.getCurrentSession() + "' "
                        + "and tr_month_code='" + em.getCode() + "') "
                        + "where sd_emp_code='" + empCode + "' and sd_head_code = 13 and sd_sess_id = '" + sessionId.getCurrentSession() + "'");
                ps.executeUpdate();
                ps.clearParameters();
            }
            ps.close();
            cn.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /*public boolean save(EmployeeTax et, TaxController tac) {
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("delete from emp_tax_master where et_emp_id=? and et_year = ? and et_sess_id='" + sessionId.getCurrentSession() + "'");
            ps.setString(1, et.getName());
            ps.setInt(2, userBean.getCurrentYear());
            ps.executeUpdate();
            ps.close();
            ps = c.prepareStatement("insert into emp_tax_master(et_emp_id,et_year, "
                    + "et_amount,et_effective,et_percent,et_educess,et_sess_id,et_org_code) values(?,?,?,?,?,?,?,?)");   //, et_savings del and et_sess & et_org added;
            savingTr((int) et.getAmount(), et.getName());
            ps.setString(1, et.getName());
            ps.setInt(2, userBean.getCurrentYear());
            System.out.println("Total Value a : " + et.getAmount());
            ps.setFloat(3, et.getAmount());
            // System.out.println("Total Value : "+et.getSaving());
            //   ps.setFloat(4, et.getSaving());
            ps.setFloat(4, et.getEffectiveIe());
            ps.setDouble(5, tac.getTaxPercent());
            ps.setFloat(6, et.getEducesse());
            ps.setInt(7, sessionId.getCurrentSession());
            ps.setInt(8, userBean.getUserOrgCode());
            ps.executeUpdate();
            ps.close();
            c.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }*/

    public void savingTr(int amount, String empCode) // add saving function
    {
        try {
            Connection c = new CommonDB().getConnection();
            PreparedStatement pst = null;
            ResultSet rst;
            PreparedStatement pst1 = null;
            float finalAmount = 0;
            finalAmount = amount / 12;
            for (int i = 0; i < 12; i++) {
                pst1 = c.prepareStatement("select tr_emp_code,tr_amount,tr_month_code from tax_rep_master where tr_month_code = '" + i + "' and tr_emp_code = '" + empCode + "' and tr_amount = '" + finalAmount + "'");
                rst = pst1.executeQuery();
                if (rst.next() == true) {
                    pst1 = c.prepareStatement("update tax_rep_master set tr_month_code = '" + i + "',tr_emp_code = '" + empCode + "',tr_amount  = '" + finalAmount + "'");
                    pst1.executeUpdate();
                } else {
                    pst = c.prepareStatement("insert into tax_rep_master(tr_month_code,tr_emp_code,tr_amount) values('" + i + "','" + empCode + "','" + finalAmount + "')");
                    pst.executeUpdate();
                }
            }
            pst.close();
            c.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean add(EmployeeTax et, TaxController tac) {
        boolean exist=false;
        try {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select exists(select * from emp_tax_master where et_emp_id=? and et_sess_id=? and et_org_code=? and et_quater=?)");
            ps.setString(1, et.getName());
            ps.setInt(2,et.getSession());
            ps.setInt(3,userBean.getUserOrgCode());
            ps.setInt(4,et.getQuater());
            rs=ps.executeQuery();
            if(rs.next())
            {
             exist=rs.getBoolean(1);
            }
            ps.close();
            rs.close();
            if(exist){
            ps = c.prepareStatement("delete from emp_tax_master where et_emp_id=? and et_sess_id=? and et_org_code=? and et_quater=?");
            ps.setString(1, et.getName());
            ps.setInt(2,et.getSession());
            ps.setInt(3,userBean.getUserOrgCode());
            ps.setInt(4,et.getQuater());
            ps.executeUpdate();
            ps.close();
            }

            ps = c.prepareStatement("insert into emp_tax_master(et_emp_id,et_year, "
                    + "et_amount,et_educess,et_sess_id,et_org_code,et_quater,et_effective,et_percent) values(?,?,?,?,?,?,?,?,?)");
            ps.setString(1, et.getName());
            ps.setInt(2, userBean.getCurrentYear());
            ps.setDouble(3, et.getTaxAmount());
            ps.setFloat(4, et.getEducess());
            ps.setInt(5, et.getSession());
            ps.setInt(6, userBean.getUserOrgCode());
            ps.setInt(7, et.getQuater());
            ps.setInt(8, 0);
            ps.setInt(9, 0);
            ps.executeUpdate();

            ps.close();
            c.close();
            return true;
	 }
            catch (Exception e) {
            e.printStackTrace();
            return false;
           }
    }

    public float getTaxAmount(String empCode) {
        try {
            System.out.println("Code ET : " + empCode);
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("select et_amount from emp_tax_master where "
                    + "et_emp_id=? and et_year=? ");
            ps.setString(1, empCode);
            ps.setInt(2, userBean.getCurrentYear());
            rs = ps.executeQuery();
            float amount = 0;
            if (rs.next()) {
                amount = rs.getFloat(1);
            }

            rs.close();
            ps.close();
            c.close();
            return amount;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
	

}
