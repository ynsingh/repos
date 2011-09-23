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
public class CatActionForm3 extends org.apache.struts.action.ActionForm {

    private Character in3001,in3002,in3061,in3062,in3361,in3362;
    private String name,z300a,z300b,z300c,z300e,z300f,z300g,z3003, z306a, z336a,z336b,z3362,z3363 ,zclick;

    public Character getIn3001() {
        return in3001;
    }

    public void setIn3001(Character in3001) {
        this.in3001 = in3001;
    }

    public Character getIn3002() {
        return in3002;
    }

    public void setIn3002(Character in3002) {
        this.in3002 = in3002;
    }

    public Character getIn3061() {
        return in3061;
    }

    public void setIn3061(Character in3061) {
        this.in3061 = in3061;
    }

    public Character getIn3062() {
        return in3062;
    }

    public void setIn3062(Character in3062) {
        this.in3062 = in3062;
    }

    public Character getIn3361() {
        return in3361;
    }

    public void setIn3361(Character in3361) {
        this.in3361 = in3361;
    }

    public Character getIn3362() {
        return in3362;
    }

    public void setIn3362(Character in3362) {
        this.in3362 = in3362;
    }

    public String getZ3003() {
        return z3003;
    }

    public void setZ3003(String z3003) {
        this.z3003 = z3003;
    }

    public String getZ300a() {
        return z300a;
    }

    public void setZ300a(String z300a) {
        this.z300a = z300a;
    }

    public String getZ300b() {
        return z300b;
    }

    public void setZ300b(String z300b) {
        this.z300b = z300b;
    }

    public String getZ300c() {
        return z300c;
    }

    public void setZ300c(String z300c) {
        this.z300c = z300c;
    }

    public String getZ300e() {
        return z300e;
    }

    public void setZ300e(String z300e) {
        this.z300e = z300e;
    }

    public String getZ300f() {
        return z300f;
    }

    public void setZ300f(String z300f) {
        this.z300f = z300f;
    }

    public String getZ300g() {
        return z300g;
    }

    public void setZ300g(String z300g) {
        this.z300g = z300g;
    }

    public String getZ306a() {
        return z306a;
    }

    public void setZ306a(String z306a) {
        this.z306a = z306a;
    }

    public String getZ3362() {
        return z3362;
    }

    public void setZ3362(String z3362) {
        this.z3362 = z3362;
    }

    public String getZ3363() {
        return z3363;
    }

    public void setZ3363(String z3363) {
        this.z3363 = z3363;
    }

    public String getZ336a() {
        return z336a;
    }

    public void setZ336a(String z336a) {
        this.z336a = z336a;
    }

    public String getZ336b() {
        return z336b;
    }

    public void setZ336b(String z336b) {
        this.z336b = z336b;
    }

    public String getZclick() {
        return zclick;
    }

    public void setZclick(String zclick) {
        this.zclick = zclick;
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
   

    public CatActionForm3() {
        super();
        // TODO Auto-generated constructor stub
    }

    
}
