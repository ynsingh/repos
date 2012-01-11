/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import com.myapp.struts.hbm.CirCheckin;
import com.myapp.struts.hbm.CirTransactionHistory;
import com.myapp.struts.hbm.DocumentDetails;

/**
 *
 * @author faraz
 */

public class CheckInDocumentDetails implements java.io.Serializable{
private CirCheckin cirCheckin;
private CirTransactionHistory cirTransactionHistory;

    public CirTransactionHistory getCirTransactionHistory() {
        return cirTransactionHistory;
    }

    public void setCirTransactionHistory(CirTransactionHistory cirTransactionHistory) {
        this.cirTransactionHistory = cirTransactionHistory;
    }

    public CirCheckin getCirCheckin() {
        return cirCheckin;
    }

    public void setCirCheckin(CirCheckin cirCheckin) {
        this.cirCheckin = cirCheckin;
    }
private DocumentDetails documentDetails;
int fine;

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    
    public DocumentDetails getDocumentDetails() {
        return documentDetails;
    }

    public void setDocumentDetails(DocumentDetails documentDetails) {
        this.documentDetails = documentDetails;
    }


}
