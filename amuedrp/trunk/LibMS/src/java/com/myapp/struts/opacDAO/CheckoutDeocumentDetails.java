/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opacDAO;

import com.myapp.struts.hbm.CirCheckout;
import com.myapp.struts.hbm.DocumentDetails;

/**
 *
 * @author faraz
 */

public class CheckoutDeocumentDetails implements java.io.Serializable{
private CirCheckout cirCheckout;
private DocumentDetails documentDetails;
int fine;

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public CirCheckout getCirCheckout() {
        return cirCheckout;
    }

    public void setCirCheckout(CirCheckout cirCheckout) {
        this.cirCheckout = cirCheckout;
    }

    public DocumentDetails getDocumentDetails() {
        return documentDetails;
    }

    public void setDocumentDetails(DocumentDetails documentDetails) {
        this.documentDetails = documentDetails;
    }


}
