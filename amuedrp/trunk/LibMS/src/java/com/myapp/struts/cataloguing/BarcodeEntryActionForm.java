/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.cataloguing;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author edrp02
 */
public class BarcodeEntryActionForm extends org.apache.struts.action.ActionForm {
    
    private String accession_no;
    private String list;
    private String maincard;
    private String titlecard;
    private String authorcard;
    private String subjectcard;
    private String statementrescard;
    private String allcard;
    private String card;

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getAllcard() {
        return allcard;
    }

    public void setAllcard(String allcard) {
        this.allcard = allcard;
    }

    public String getAuthorcard() {
        return authorcard;
    }

    public void setAuthorcard(String authorcard) {
        this.authorcard = authorcard;
    }

    public String getMaincard() {
        return maincard;
    }

    public void setMaincard(String maincard) {
        this.maincard = maincard;
    }

    public String getStatementrescard() {
        return statementrescard;
    }

    public void setStatementrescard(String statementrescard) {
        this.statementrescard = statementrescard;
    }

    public String getSubjectcard() {
        return subjectcard;
    }

    public void setSubjectcard(String subjectcard) {
        this.subjectcard = subjectcard;
    }

    public String getTitlecard() {
        return titlecard;
    }

    public void setTitlecard(String titlecard) {
        this.titlecard = titlecard;
    }

    

    public String getAccession_no() {
        return accession_no;
    }

    public void setAccession_no(String accession_no) {
        this.accession_no = accession_no;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    
   
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
       
        return null;
    }
}
