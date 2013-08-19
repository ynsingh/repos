/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api.BankDetails;

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
public class SaveBankDetails {

    /** Creates a new instance of SaveBankDetails */
    private int orgCode;
    private ArrayList<BankProfileDetails> completeBankInfo = new ArrayList<BankProfileDetails>();

    public ArrayList<BankProfileDetails> getCompleteBankInfo() {
        
        return completeBankInfo;
    }

    public SaveBankDetails() {
        try {
            completeBankInfo = new BankDetailsSearch().bankDetails();
            LoggedEmployee le = (LoggedEmployee) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
            if (le == null) {
                UserInfo uf = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
                orgCode = uf.getUserOrgCode();
                System.out.println("DAta Should Be Write Here : 3214 : --" + orgCode);
            } else {
                orgCode = le.getUserOrgCode();
                System.out.println("DAta Should Be Write Here : 32142323 : " + orgCode);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void saveBankdetails() {
        
    }

    /**
     * 
     * Insert Bank Profile Master Data
     * @param bankDetails
     * @return 
     */
    
    public boolean save(BankProfileDetails bankDetails) {
        try {
            Connection cn;
            cn = new CommonDB().getConnection();
            PreparedStatement pst;
            pst = cn.prepareStatement("insert into bankprofile(bank_name,bank_address,bank_ifsc_code,branch_name,org_code) values('" + bankDetails.getBankName() + "','" + bankDetails.getBankAddress() + "','" + bankDetails.getBankIFSCCode() + "','" + bankDetails.getBankBranch() + "','" + orgCode + "')");
            pst.executeUpdate();
            System.out.println("Hello World" +orgCode );
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Load Bank Profile All Details
     * @return 
     * 
     */
    
    public ArrayList<BankProfileDetails> loadBankProfile() {
        try {
            ArrayList<BankProfileDetails> bankProfile = new ArrayList<BankProfileDetails>();
            BankProfileDetails bp = new BankProfileDetails();
            Connection cn;
            cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = cn.prepareStatement("select * from bankprofile");
            rst = pst.executeQuery();
            while (rst.next()) {
                bp.setBankName(rst.getString(2));
                //bp.setBankIFSCCode(rst.getString(4));
                bankProfile.add(bp);
            }
            pst.close();
            cn.close();
            return bankProfile;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 
     * Update the Bank Profile
     * @param bankProfileDetails
     * @return 
     */
    public boolean update(ArrayList<BankProfileDetails> bankProfileDetails) {
        try {
            Connection cn;
            PreparedStatement pst;
            cn = new CommonDB().getConnection();
            for (BankProfileDetails bpd : bankProfileDetails) {
                //System.out.println("update bankprofile set bank_name = '"+bpd.getBankName()+"',branch_name = '"+bpd.getBankBranch()+"' where bank_ifsc_code = '"+bpd.getBankIFSCCode()+"'");
                pst = cn.prepareStatement("update bankprofile set bank_name = '" + bpd.getBankName() + "',branch_name = '" + bpd.getBankBranch() + "' where bank_ifsc_code = '" + bpd.getBankIFSCCode() + "' and org_code = '"+orgCode+"'");
                pst.executeUpdate();
                pst.clearParameters();
            }
            cn.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
