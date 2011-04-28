/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import com.myapp.struts.hbm.BookCategory;
import com.myapp.struts.hbm.CirCheckout;
import com.myapp.struts.hbm.CirMemberAccount;
import com.myapp.struts.hbm.DocumentCategory;
import com.myapp.struts.hbm.DocumentDetails;

/**
 *
 * @author faraz
 */
public class MemberFineWithCheckoutDetails {
public DocumentDetails documentDetails;
public CirCheckout cirCheckout;
public BookCategory bookCategory;
public CirMemberAccount cirMemberAccount;

    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }

   

   

    public CirCheckout getCirCheckout() {
        return cirCheckout;
    }

    public void setCirCheckout(CirCheckout cirCheckout) {
        this.cirCheckout = cirCheckout;
    }

    public CirMemberAccount getCirMemberAccount() {
        return cirMemberAccount;
    }

    public void setCirMemberAccount(CirMemberAccount cirMemberAccount) {
        this.cirMemberAccount = cirMemberAccount;
    }

    public DocumentDetails getDocumentDetails() {
        return documentDetails;
    }

    public void setDocumentDetails(DocumentDetails documentDetails) {
        this.documentDetails = documentDetails;
    }


}
