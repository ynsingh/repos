/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.marc;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author zeeshan
 */
public class CatActionForm4 extends org.apache.struts.action.ActionForm {
    
    private String name,z490a,z490v,z490x,z4903,zclick;

    public String getZclick() {
        return zclick;
    }

    public void setZclick(String zclick) {
        this.zclick = zclick;
    }
    private Character in4901,in4902;

    public Character getIn4901() {
        return in4901;
    }

    public void setIn4901(Character in4901) {
        this.in4901 = in4901;
    }

    public Character getIn4902() {
        return in4902;
    }

    public void setIn4902(Character in4902) {
        this.in4902 = in4902;
    }

    public String getZ4903() {
        return z4903;
    }

    public void setZ4903(String z4903) {
        this.z4903 = z4903;
    }

    public String getZ490a() {
        return z490a;
    }

    public void setZ490a(String z490a) {
        this.z490a = z490a;
    }

    public String getZ490v() {
        return z490v;
    }

    public void setZ490v(String z490v) {
        this.z490v = z490v;
    }

    public String getZ490x() {
        return z490x;
    }

    public void setZ490x(String z490x) {
        this.z490x = z490x;
    }
    private int number;

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
    public CatActionForm4() {
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
