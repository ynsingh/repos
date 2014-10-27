/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.BankDetails;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


/**
 *
 * @author ERP
 * Modified Date: 22OCT 2014, IITK (palseema30@gmail.com, kishore.shuklak@gmail.com)
 */
public class BankProfileDetails{

    /** Creates a new instance of BankProfileDetails */
    public BankProfileDetails() {
    }
    private String bifsc = new String();

    public String getBifsc() {
        //System.out.println("DAta Should Be Write Here klop cv: "+bifsc);
        return bifsc;
    }

    public void setBifsc(String bifsc) {
        //System.out.println("DAta Should Be Write Here klop : "+bifsc);
        this.bifsc = bifsc;
    }
    private String bankName = new String();
    private String bankIFSCCode = new String();
    private String bankAddress = new String();
    private String bankBranch = new String();
    private int accountNumber;
    private String accountType;
    private String panNumber;
    private String tanNumber;
    private String accountName;
    private int seqno;
    private SelectItem[] arrayAsItem;

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getBankIFSCCode() {
        return bankIFSCCode;
    }

    public void setBankIFSCCode(String bankIFSCCode) {
        this.bankIFSCCode = bankIFSCCode;
    }

    public String getBankName() {
        return bankName;
    }
    private String name;

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    
    
    public int getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    
    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }
    
    public String getTanNumber() {
        return tanNumber;
    }

    public void setTanNumber(String tanNumber) {
        this.tanNumber = tanNumber;
    }
    
     public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    
    public int getSeqId() {
        return seqno;
    }
    public void setSeqId(int seqno) {
        this.seqno = seqno;
    }

    public SelectItem[] getArrayAsItem()
    {
        ArrayList<BankProfileDetails> bankpro = new ArrayList<BankProfileDetails>();
        arrayAsItem = new SelectItem[bankpro.size()];
        BankProfileDetails bp=null;
        for(int i = 0; i < bankpro.size() ; i++)
        {
            bp = bankpro.get(i);
            SelectItem si = new SelectItem(bp.getBankName());
            arrayAsItem[i] = si;
        }
        return arrayAsItem;
    }

    public void setArrayAsItem(SelectItem[] arrayAsItem) {
        this.arrayAsItem = arrayAsItem;
    }



    public void saveData()
    {
        try
        {
            FacesContext fc=FacesContext.getCurrentInstance();
            if(this.getBankName().matches("^[a-zA-Z\\s]*$") == false)
            {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter Correct Bank Name ");
            //message.setDetail("First Name Must Be At Least Three Charecter ");
               fc.addMessage("", message);
               return;
            }
            if(this.getBankBranch().matches("^[a-zA-Z\\s]*$") == false)
            {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter Correct Branch Name ");
            //message.setDetail("First Name Must Be At Least Three Charecter ");
               fc.addMessage("", message);
               return;
            }
            if(this.getAccountName().matches("^[a-zA-Z\\s]*$") == false)
            {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter Correct Account Name ");
                fc.addMessage("", message);
               return;
            }
            if(this.getAccountType().matches("^[a-zA-Z\\s]*$") == false)
            {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter Correct Account Type");
                fc.addMessage("", message);
               return;
            }
            
            if(this.getBankIFSCCode().matches("[a-zA-Z]{4}[0-9]{7}") == false)
            {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter valid bank ifsc code number");
                fc.addMessage("", message);
               return;
            }
            
            if(this.getPanNumber().matches("[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}") == false)
            {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter valid pan number");
                fc.addMessage("", message);
               return;
            }
            
            if(this.getTanNumber().matches("[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}") == false)
            {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter valid tan number");
                fc.addMessage("", message);
               return;
            }
            
            boolean ex = new SaveBankDetails().save(this) ;
            if(ex == true)
            {
                name = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bank Profile Saved",""));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private ArrayList<BankProfileDetails> getdatils = new ArrayList<BankProfileDetails>();

    public ArrayList<BankProfileDetails> getCompleteBankInfo() {
        getdatils = new BankProfileDetails().getCompleteBankInfo();
        return getdatils;
    }
}
