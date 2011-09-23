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

    private int tagnumber;
    private Character subsymbol,subsymbol1,subsymbol2,subsymbol3,subsymbol4;
    private String tagname, subdesc, subdesc1, subdesc2, subdesc3, subdesc4,btn;



    public String getBtn() {
        return btn;
    }

    public void setBtn(String btn) {
        this.btn = btn;
    }

 private Boolean repeatable1,repeatable11,repeatable12,repeatable13,repeatable14;

    public Boolean getRepeatable11() {
        return repeatable11;
    }

    public void setRepeatable11(Boolean repeatable11) {
        this.repeatable11 = repeatable11;
    }

    public Boolean getRepeatable12() {
        return repeatable12;
    }

    public void setRepeatable12(Boolean repeatable12) {
        this.repeatable12 = repeatable12;
    }

    public Boolean getRepeatable13() {
        return repeatable13;
    }

    public void setRepeatable13(Boolean repeatable13) {
        this.repeatable13 = repeatable13;
    }

    public Boolean getRepeatable14() {
        return repeatable14;
    }

    public void setRepeatable14(Boolean repeatable14) {
        this.repeatable14 = repeatable14;
    }
    public Boolean getRepeatable1() {
        return repeatable1;
    }

    public void setRepeatable1(Boolean repeatable1) {
        this.repeatable1 = repeatable1;
    }
   

    public String getSubdesc() {
        return subdesc;
    }

    public void setSubdesc(String subdesc) {
        this.subdesc = subdesc;
    }

    public String getSubdesc1() {
        return subdesc1;
    }

    public void setSubdesc1(String subdesc1) {
        this.subdesc1 = subdesc1;
    }

    public String getSubdesc2() {
        return subdesc2;
    }

    public void setSubdesc2(String subdesc2) {
        this.subdesc2 = subdesc2;
    }

    public String getSubdesc3() {
        return subdesc3;
    }

    public void setSubdesc3(String subdesc3) {
        this.subdesc3 = subdesc3;
    }

    public String getSubdesc4() {
        return subdesc4;
    }

    public void setSubdesc4(String subdesc4) {
        this.subdesc4 = subdesc4;
    }

    public Character getSubsymbol() {
        return subsymbol;
    }

    public void setSubsymbol(Character subsymbol) {
        this.subsymbol = subsymbol;
    }

    public Character getSubsymbol1() {
        return subsymbol1;
    }

    public void setSubsymbol1(Character subsymbol1) {
        this.subsymbol1 = subsymbol1;
    }

    public Character getSubsymbol2() {
        return subsymbol2;
    }

    public void setSubsymbol2(Character subsymbol2) {
        this.subsymbol2 = subsymbol2;
    }

    public Character getSubsymbol3() {
        return subsymbol3;
    }

    public void setSubsymbol3(Character subsymbol3) {
        this.subsymbol3 = subsymbol3;
    }

    public Character getSubsymbol4() {
        return subsymbol4;
    }

    public void setSubsymbol4(Character subsymbol4) {
        this.subsymbol4 = subsymbol4;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    public int getTagnumber() {
        return tagnumber;
    }

    public void setTagnumber(int tagnumber) {
        this.tagnumber = tagnumber;
    }
    

    /**
     *
     */
    public NewMarcActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
//    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
//        ActionErrors errors = new ActionErrors();
//        if (getName() == null || getName().length() < 1) {
//            errors.add("name", new ActionMessage("error.name.required"));
//            // TODO: add 'error.name.required' key to your resources
//        }
//        return errors;
//    }
}
