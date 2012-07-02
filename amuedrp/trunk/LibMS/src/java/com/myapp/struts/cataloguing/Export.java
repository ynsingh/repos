/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.cataloguing;
import com.myapp.struts.hbm.*;


import java.io.Serializable;

public class Export  implements Serializable{
    BibliographicDetails bibliographicDetails;
    AccessionRegister accessionRegister=new AccessionRegister();

    public AccessionRegister getAccessionRegister() {
        return accessionRegister;
    }

    public void setAccessionRegister(AccessionRegister accessionRegister) {
        this.accessionRegister = accessionRegister;
    }
    DocumentDetails documentDetails=new DocumentDetails();

    public DocumentDetails getDocumentDetails() {
        return documentDetails;
    }

    public void setDocumentDetails(DocumentDetails documentDetails) {
        this.documentDetails = documentDetails;
    }

 
  

    public BibliographicDetails getBibliographicDetails() {
        return bibliographicDetails;
    }

    public void setBibliographicDetails(BibliographicDetails bibliographicDetails) {
        this.bibliographicDetails = bibliographicDetails;
    }

  
}
