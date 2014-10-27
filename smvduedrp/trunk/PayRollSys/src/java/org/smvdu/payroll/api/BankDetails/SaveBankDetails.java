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
 * Modified Date: 22OCT 2014, IITK (palseema30@gmail.com, kishore.shuklak@gmail.com)
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
            } else {
                orgCode = le.getUserOrgCode();
                //System.out.println("DAta Should Be Write Here : 32142323 : " + orgCode);
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
            //pst = cn.prepareStatement("insert into bankprofile(bank_name,bank_address,bank_ifsc_code,branch_name,org_code) values('" + bankDetails.getBankName() + "','" + bankDetails.getBankAddress() + "','" + bankDetails.getBankIFSCCode() + "','" + bankDetails.getBankBranch() + "','" + orgCode + "')");
            pst = cn.prepareStatement("insert into bankprofile(bank_name, bank_address, bank_ifsc_code, branch_name, account_number,"
                                    + "account_type, pan_number, tan_number, account_name, org_code) values(?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, bankDetails.getBankName());
            pst.setString(2, bankDetails.getBankAddress());
            pst.setString(3, bankDetails.getBankIFSCCode().toUpperCase());
            pst.setString(4, bankDetails.getBankBranch());
            pst.setInt(5, bankDetails.getAccountNumber());
            pst.setString(6, bankDetails.getAccountType());
            pst.setString(7, bankDetails.getPanNumber().toUpperCase());
            pst.setString(8, bankDetails.getTanNumber().toUpperCase());
            pst.setString(9, bankDetails.getAccountName());
            pst.setInt(10, orgCode);
            pst.executeUpdate();
            //System.out.println("Hello World" +orgCode );
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
           // System.out.println("Hello World 20OCT 2014" +orgCode );
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
    private PreparedStatement pst;
    public boolean update(ArrayList<BankProfileDetails> bankProfileDetails) {
        
        try {
            Connection cn;
            
             cn = new CommonDB().getConnection();
             pst=cn.prepareStatement("update bankprofile set bank_name=?, bank_address=?, branch_name=?," 
                        + " account_number=?, account_type=?,  account_name=? where seq_no=? and org_code =?" );
            for (BankProfileDetails bpd : bankProfileDetails) {
                //System.out.println("update bankprofile set bank_name = '"+bpd.getBankName()+"',branch_name = '"+bpd.getBankBranch()+"' where bank_ifsc_code = '"+bpd.getBankIFSCCode()+"'");
                //pst = cn.prepareStatement("update bankprofile set bank_name = '" + bpd.getBankName() + "',branch_name = '" + bpd.getBankBranch() + "' where bank_ifsc_code = '" + bpd.getBankIFSCCode() + "' and org_code = '"+orgCode+"'");
                pst.setString(1, bpd.getBankName());
                pst.setString(2, bpd.getBankAddress());
                pst.setString(3, bpd.getBankBranch());
                pst.setInt(4, bpd.getAccountNumber());
                pst.setString(5, bpd.getAccountType());
                pst.setString(6, bpd.getAccountName());
                pst.setInt(7, bpd.getSeqId());
                pst.setInt(8, orgCode);
                pst.executeUpdate();
                pst.clearParameters();
            }
            pst.close();
            cn.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
