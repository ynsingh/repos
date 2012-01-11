/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.marc;

/**
 * 14/04/2011 
 * @author zeeshan
 */
public class NewMarcActionForm extends org.apache.struts.action.ActionForm {

    private int tagnumber[];
    private Character subsymbol[];
    private String tagname[];
    private String subdesc[];
    private String btn;

    public String getBtn() {
        return btn;
    }

    public void setBtn(String btn) {
        this.btn = btn;
    }

    public String[] getSubdesc() {
        return subdesc;
    }

    public void setSubdesc(String[] subdesc) {
        this.subdesc = subdesc;
    }

    public Character[] getSubsymbol() {
        return subsymbol;
    }

    public void setSubsymbol(Character[] subsymbol) {
        this.subsymbol = subsymbol;
    }

    public String[] getTagname() {
        return tagname;
    }

    public void setTagname(String[] tagname) {
        this.tagname = tagname;
    }

    public int[] getTagnumber() {
        return tagnumber;
    }

    public void setTagnumber(int[] tagnumber) {
        this.tagnumber = tagnumber;
    }


}
