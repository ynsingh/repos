/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.BankDetails;

import java.util.ArrayList;
import javax.faces.model.SelectItem;


/**
 *
 * @author ERP
 */
public class BankEmployeeList {

    /** Creates a new instance of BankEmployeeList */
    public BankEmployeeList() {
    }
      private SelectItem[] bankItems;

      private String bankName = new String();

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
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

}
