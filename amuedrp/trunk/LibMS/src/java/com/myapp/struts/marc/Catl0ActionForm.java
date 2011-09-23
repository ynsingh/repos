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
public class Catl0ActionForm extends org.apache.struts.validator.ValidatorForm {
    
    private String name,in0201,in0202,in0221,in0222,in0411,in0412,in0431,in0432,in0821,in0822;

    private String z020,z020c,z020z,z022y,z022z,z041b,z041d,z043,z082,z082b,z0822;

    public String getZ020() {
        return z020;
    }

    public void setZ020(String z020) {
        this.z020 = z020;
    }

    public String getZ020c() {
        return z020c;
    }

    public void setZ020c(String z020c) {
        this.z020c = z020c;
    }

    public String getZ020z() {
        return z020z;
    }

    public void setZ020z(String z020z) {
        this.z020z = z020z;
    }

    public String getZ022y() {
        return z022y;
    }

    public void setZ022y(String z022y) {
        this.z022y = z022y;
    }

    public String getZ022z() {
        return z022z;
    }

    public void setZ022z(String z022z) {
        this.z022z = z022z;
    }

    public String getZ041b() {
        return z041b;
    }

    public void setZ041b(String z041b) {
        this.z041b = z041b;
    }

    public String getZ041d() {
        return z041d;
    }

    public void setZ041d(String z041d) {
        this.z041d = z041d;
    }

    public String getZ043() {
        return z043;
    }

    public void setZ043(String z043) {
        this.z043 = z043;
    }

    public String getZ082() {
        return z082;
    }

    public void setZ082(String z082) {
        this.z082 = z082;
    }

    public String getZ0822() {
        return z0822;
    }

    public void setZ0822(String z0822) {
        this.z0822 = z0822;
    }

    public String getZ082b() {
        return z082b;
    }

    public void setZ082b(String z082b) {
        this.z082b = z082b;
    }

    public String getIn0201() {
        return in0201;
    }

    public void setIn0201(String in0201) {
        this.in0201 = in0201;
    }

    public String getIn0202() {
        return in0202;
    }

    public void setIn0202(String in0202) {
        this.in0202 = in0202;
    }

    public String getIn0221() {
        return in0221;
    }

    public void setIn0221(String in0221) {
        this.in0221 = in0221;
    }

    public String getIn0222() {
        return in0222;
    }

    public void setIn0222(String in0222) {
        this.in0222 = in0222;
    }

    public String getIn0411() {
        return in0411;
    }

    public void setIn0411(String in0411) {
        this.in0411 = in0411;
    }

    public String getIn0412() {
        return in0412;
    }

    public void setIn0412(String in0412) {
        this.in0412 = in0412;
    }

    public String getIn0431() {
        return in0431;
    }

    public void setIn0431(String in0431) {
        this.in0431 = in0431;
    }

    public String getIn0432() {
        return in0432;
    }

    public void setIn0432(String in0432) {
        this.in0432 = in0432;
    }

    public String getIn0821() {
        return in0821;
    }

    public void setIn0821(String in0821) {
        this.in0821 = in0821;
    }

    public String getIn0822() {
        return in0822;
    }

    public void setIn0822(String in0822) {
        this.in0822 = in0822;
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
    public Catl0ActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

}
