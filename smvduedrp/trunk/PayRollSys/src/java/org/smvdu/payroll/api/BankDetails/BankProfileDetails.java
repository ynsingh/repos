/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.BankDetails;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


/**
 *
 * @author ERP
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
