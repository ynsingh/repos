/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.BankDetails;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;

/**
 *
 * @author ERP
 */
public class BankConttroler {

    private UIData dataGrid;
    private ArrayList<BankProfileDetails> bankprofiledetails = new ArrayList<BankProfileDetails>();

    public ArrayList<BankProfileDetails> getBankprofiledetails() {
        
        //dataGrid.setValue(bankprofiledetails);
        return bankprofiledetails;
    }

    public void setBankprofiledetails(ArrayList<BankProfileDetails> bankprofiledetails) {
        this.bankprofiledetails = bankprofiledetails;
    }            
    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }
    /** Creates a new instance of BankConttroler */
    public BankConttroler() {
        bankprofiledetails = new SaveBankDetails().getCompleteBankInfo();
    }

    public void updateBankRecords()
    {
        try
        {
            ArrayList<BankProfileDetails> bpd = (ArrayList<BankProfileDetails>) dataGrid.getValue();
            boolean b = new SaveBankDetails().update(bpd);
            if(b == true)
            {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Bank Detail Updated Successfully", ""));
            }
            else 
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bank Details Already Exist", ""));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
