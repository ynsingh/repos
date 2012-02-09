/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Candidate;

import org.apache.struts.upload.FormFile;

/**
 *
 * @author faraz
 */
public class CandidateMenifestoActionForm extends org.apache.struts.action.ActionForm {
    
   private FormFile menifesto;

    public FormFile getMenifesto() {
        return menifesto;
    }

    public void setMenifesto(FormFile menifesto) {
        this.menifesto = menifesto;
    }

   
    /**
     *
     */
    public CandidateMenifestoActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

   
   
}
