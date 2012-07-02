/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import com.myapp.struts.hbm.AccessionRegister;
import com.myapp.struts.hbm.BibliographicDetailsLang;
import java.io.Serializable;

public class MixAccessionRecord  implements Serializable
{
    
  BibliographicDetailsLang bibliographicDetailsLang;
  AccessionRegister accessionRegister;

    public AccessionRegister getAccessionRegister() {
        return accessionRegister;
    }

    public void setAccessionRegister(AccessionRegister accessionRegister) {
        this.accessionRegister = accessionRegister;
    }
  
    public BibliographicDetailsLang getBibliographicDetailsLang() {
        return bibliographicDetailsLang;
    }

    public void setBibliographicDetailsLang(BibliographicDetailsLang bibliographicDetailsLang) {
        this.bibliographicDetailsLang = bibliographicDetailsLang;
    }

   
  
}
