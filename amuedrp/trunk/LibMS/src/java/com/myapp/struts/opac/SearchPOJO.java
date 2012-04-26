/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.BibliographicDetailsLang;
import com.myapp.struts.hbm.DocumentDetails;

public class SearchPOJO implements java.io.Serializable  {
    
  BibliographicDetailsLang bibliographicDetailsLang;
  DocumentDetails documentDetails;

    public BibliographicDetails getBibliographicDetails() {
        return bibliographicDetails;
    }

    public void setBibliographicDetails(BibliographicDetails bibliographicDetails) {
        this.bibliographicDetails = bibliographicDetails;
    }
  BibliographicDetails bibliographicDetails;

    public DocumentDetails getDocumentDetails() {
        return documentDetails;
    }

    public void setDocumentDetails(DocumentDetails documentDetails) {
        this.documentDetails = documentDetails;
    }

    
    public BibliographicDetailsLang getBibliographicDetailsLang() {
        return bibliographicDetailsLang;
    }

    public void setBibliographicDetailsLang(BibliographicDetailsLang bibliographicDetailsLang) {
        this.bibliographicDetailsLang = bibliographicDetailsLang;
    }

   
  
}
