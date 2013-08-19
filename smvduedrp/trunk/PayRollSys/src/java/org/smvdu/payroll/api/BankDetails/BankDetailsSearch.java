/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.BankDetails;
import java.sql.*;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.module.attendance.LoggedEmployee;

/**
 *
 * @author ERP
 */
public class BankDetailsSearch {

    /** Creates a new instance of BankDetailsSearch */
    private int orgCode ;
    public BankDetailsSearch() 
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
    private String bankName = new String();

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    private ArrayList<BankProfileDetails> bankSearchReport;

    public ArrayList<BankProfileDetails> getBankSearchReport() {
        return bankSearchReport;
    }

    public void setBankSearchReport(ArrayList<BankProfileDetails> bankSearchReport) {
        this.bankSearchReport = bankSearchReport;
    }
    public int getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(int orgCode) {
        this.orgCode = orgCode;
    }

    private String bIFSCCode = new String();

    public void setbIFSCCode(String bIFSCCode) {
        this.bIFSCCode = bIFSCCode;
    }
    
    public String getbIFSCCode() {
        return bIFSCCode;
    }
   
    /**
     * 
     * Search Bank Details
     * @param obj
     * @return 
     * 
     */
    
    public ArrayList<BankProfileDetails> bankSearch(Object obj)
    {
        try
        {
            ArrayList<BankProfileDetails> bankDetails = new ArrayList<BankProfileDetails>();
            Connection cn;
            cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = cn.prepareStatement("select bank_name,bank_ifsc_code,branch_name from bankprofile where bank_name like '"+obj.toString()+"%' and org_code = '"+orgCode+"'");
            rst = pst.executeQuery();
            while(rst.next())
            {
                System.out.println("In Bank Name List ..");
                BankProfileDetails bpd = new BankProfileDetails();
                bpd.setBankName(rst.getString(1));
                bpd.setBankIFSCCode(rst.getString(2));
                bpd.setBifsc(rst.getString(2));
                bpd.setBankBranch(rst.getString(3));
                bankDetails.add(bpd);
            }
            return bankDetails;

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }


   

/**
 * Load Bank Profile for Report
 * @return 
 * 
 */

    public ArrayList<BankProfileDetails> bankSearchEmployeeReport()
    {
        try
        {
            ArrayList<BankProfileDetails> bankDetails = new ArrayList<BankProfileDetails>();
            Connection cn;
            cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = cn.prepareStatement("select * from bankprofile where org_code = '"+orgCode+"'");
            rst = pst.executeQuery();
            BankProfileDetails bpd = new BankProfileDetails();
            while(rst.next())
            {
                bpd.setBankName(rst.getString(1));
                bpd.setBankIFSCCode(rst.getString(2));
                bpd.setBifsc(rst.getString(2));
                bpd.setBankBranch(rst.getString(3));
                bankDetails.add(bpd);
            }
            return bankDetails;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
    /**
     * 
     * Load Bank Details
     * @return 
     */
    
    public ArrayList<BankProfileDetails> bankDetails()
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            ArrayList<BankProfileDetails> bankdetails = new ArrayList<BankProfileDetails>();
            pst = cn.prepareStatement("select * from bankprofile where org_code = '"+orgCode+"'");
            rst = pst.executeQuery();
            while(rst.next())
            {
                BankProfileDetails bpd = new BankProfileDetails();
                bpd.setBankName(rst.getString(2));
                bpd.setBankBranch(rst.getString(5));
                bpd.setBankIFSCCode(rst.getString(4));
                bankdetails.add(bpd);
            }
            return bankdetails;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
  
    /**
     * 
     * Load Bank IFSC Details
     * @return 
     */
    
    public ArrayList<BankProfileDetails> bankIFSCDetails()
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            ArrayList<BankProfileDetails> bankdetails = new ArrayList<BankProfileDetails>();
            pst = cn.prepareStatement("select * from bankprofile where org_code = '"+orgCode+"'");
            rst = pst.executeQuery();
            while(rst.next())
            {
                BankProfileDetails bpd = new BankProfileDetails();
                bpd.setBankName(rst.getString(2));
                bpd.setBankBranch(rst.getString(5));
                bpd.setBankIFSCCode(rst.getString(4));
                bankdetails.add(bpd);
            }
            return bankdetails;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

  /**
   * 
   * Load Particular Bank Detail
   * @param bankIfsc
   * @return 
   */
    
    public BankProfileDetails particularBankDetail(String bankIfsc)
    {
        try
        {
            BankProfileDetails bpd = new BankProfileDetails();
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = cn.prepareStatement("select bank_name,bank_ifsc_code,branch_name from bankprofile where bank_ifsc_code = '"+bankIfsc+"'");
            rst = pst.executeQuery();
            if(rst.next())
            {
                bpd.setBankName(rst.getString(1));
                bpd.setBankIFSCCode(rst.getString(2));
                bpd.setBankBranch(rst.getString(3));
            }
            return bpd;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
}
