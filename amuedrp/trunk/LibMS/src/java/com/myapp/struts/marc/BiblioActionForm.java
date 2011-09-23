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
public class BiblioActionForm extends org.apache.struts.action.ActionForm {
    
    private String title,isbn,btn;

    public String getBtn() {
        return this.btn;
    }

    public void setBtn(String btn) {
        this.btn = btn;
    }


    /**
     *
     */
    public BiblioActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        
        return null;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
     String name;
Character in0202,in0222,in0412,in0432,in0822,in0201,in0221,in0411,in0431,in0821;

    public Character getIn0201() {
        return in0201;
    }

    public Character getIn0202() {
        return in0202;
    }

    public void setIn0202(Character in0202) {
        this.in0202 = in0202;
    }

    public Character getIn0222() {
        return in0222;
    }

    public void setIn0222(Character in0222) {
        this.in0222 = in0222;
    }

    public Character getIn0412() {
        return in0412;
    }

    public void setIn0412(Character in0412) {
        this.in0412 = in0412;
    }

    public Character getIn0432() {
        return in0432;
    }

    public void setIn0432(Character in0432) {
        this.in0432 = in0432;
    }

    public Character getIn0822() {
        return in0822;
    }

    public void setIn0822(Character in0822) {
        this.in0822 = in0822;
    }

    public void setIn0201(Character in0201) {
        this.in0201 = in0201;
    }

    public Character getIn0221() {
        return in0221;
    }

    public void setIn0221(Character in0221) {
        this.in0221 = in0221;
    }

    public Character getIn0411() {
        return in0411;
    }

    public void setIn0411(Character in0411) {
        this.in0411 = in0411;
    }

    public Character getIn0431() {
        return in0431;
    }

    public void setIn0431(Character in0431) {
        this.in0431 = in0431;
    }

    public Character getIn0821() {
        return in0821;
    }

    public void setIn0821(Character in0821) {
        this.in0821 = in0821;
    }

    private String z020,z020c,z020z,z022,z022y,z022z,z041,z041b,z041d,z043,z082,z082b,z0822;

    public String getZ041() {
        return z041;
    }

    public void setZ041(String z041) {
        this.z041 = z041;
    }

    public String getZ022() {
        return z022;
    }

    public void setZ022(String z022) {
        this.z022 = z022;
    }

    private String zclick;

    public String getZclick() {
        return zclick;
    }

    public void setZclick(String zclick) {
        this.zclick = zclick;
    }

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
}
