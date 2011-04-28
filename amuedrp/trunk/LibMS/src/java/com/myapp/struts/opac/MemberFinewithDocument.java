/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import com.myapp.struts.hbm.*;

/**
 *
 * @author System Administrator
 */
public class MemberFinewithDocument implements java.io.Serializable{

    protected DocumentDetails documentDetails;
    protected CirTransactionHistory cirTransactionHistory;
    protected CirCheckin cirCheckin;

    public CirCheckin getCirCheckin() {
        return cirCheckin;
    }

    public void setCirCheckin(CirCheckin cirCheckin) {
        this.cirCheckin = cirCheckin;
    }

    public CirTransactionHistory getCirTransactionHistory() {
        return cirTransactionHistory;
    }

    public void setCirTransactionHistory(CirTransactionHistory cirTransactionHistory) {
        this.cirTransactionHistory = cirTransactionHistory;
    }

    public DocumentDetails getDocumentDetails() {
        return documentDetails;
    }

    public void setDocumentDetails(DocumentDetails documentDetails) {
        this.documentDetails = documentDetails;
    }

    

}
