/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.BankDetails;

import java.util.ArrayList;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import javax.faces.model.SelectItem;


/**
 *
 * @author ERP
 */
public class FetchingBankDetails {

    /** Creates a new instance of FetchingBankDetails */
    public FetchingBankDetails() {
    }

    private String bankName = new String();
    private String branchName = new String();
    private String bankIFSCCode = new String();
    private SelectItem[] bankItems;
    private SelectItem[] bankIFSCItems;

    public SelectItem[] getBankIFSCItems() {
        ArrayList<BankProfileDetails> bankProfileDetails = new BankDetailsSearch().bankIFSCDetails();
        bankIFSCItems = new SelectItem[bankProfileDetails.size()];
        for(int i = 0;i<bankProfileDetails.size();i++)
        {
            BankProfileDetails bp = bankProfileDetails.get(i);
            SelectItem si = new SelectItem(bp.getBankIFSCCode());
            bankIFSCItems[i] = si;
        }
        return bankIFSCItems;
    }

    public void setBankIFSCItems(SelectItem[] bankIFSCItems) {
        this.bankIFSCItems = bankIFSCItems;
    }

    public SelectItem[] getBankItems() {
        ArrayList<BankProfileDetails> bankProfileDetails = new BankDetailsSearch().bankDetails();
        bankItems = new SelectItem[bankProfileDetails.size()];
        for(int i = 0;i<bankProfileDetails.size();i++)
        {
            BankProfileDetails bp = bankProfileDetails.get(i);
            SelectItem si = new SelectItem(bp.getBankName());
            bankItems[i] = si;
        }
        return bankItems;
    }

    public void setBankItems(SelectItem[] bankItems) {
        this.bankItems = bankItems;
    }
    public String getBankIFSCCode() {
        System.out.println("Hello World in get : "+bankIFSCCode);
        return bankIFSCCode;
    }

    public void setBankIFSCCode(String bankIFSCCode) {
        System.out.println("Hello World : "+bankIFSCCode);
        this.bankIFSCCode = bankIFSCCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public void banknameBranch(ValueChangeEvent event)
    {
         BankProfileDetails bpd = new BankProfileDetails();
         bpd = new BankDetailsSearch().particularBankDetail(event.getNewValue().toString());
         FacesContext.getCurrentInstance().renderResponse();    
    }
}
