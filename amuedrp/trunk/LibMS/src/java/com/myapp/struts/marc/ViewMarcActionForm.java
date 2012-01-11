/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.marc;



/**
 *
 * @author zeeshan
 */
public class ViewMarcActionForm extends org.apache.struts.action.ActionForm {
    
    private String name;

    private int number;

    private String bib_id;

    public String getBib_id() {
        return bib_id;
    }

    public void setBib_id(String bib_id) {
        this.bib_id = bib_id;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @param string
     */
    public void setName(String string) {
        name = string;
    }

    /**
     * @return
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param i
     */
    public void setNumber(int i) {
        number = i;
    }

    /**
     *
     */
    public ViewMarcActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    
    
}
