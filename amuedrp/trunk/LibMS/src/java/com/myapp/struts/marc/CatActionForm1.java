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
public class CatActionForm1 extends org.apache.struts.action.ActionForm {

     private Character in1001,in1002,in1101,in1102,in1301,in1302;

     private String z100,z100c,z100d,z100q,z100j,z100u,z1000,z110,z110b,z110c,z110d,z110k,z110n,z110p,z1100,z110t,z110u,z1104;
      private String z130,z130d,z130f,z130h,z130k,z130m,z130n,z130p,z130l,z130r,z130s,zclick,button;

    public Character getIn1001() {
        return in1001;
    }

    public void setIn1001(Character in1001) {
        this.in1001 = in1001;
    }

    public Character getIn1002() {
        return in1002;
    }

    public void setIn1002(Character in1002) {
        this.in1002 = in1002;
    }

    public Character getIn1101() {
        return in1101;
    }

    public void setIn1101(Character in1101) {
        this.in1101 = in1101;
    }

    public Character getIn1102() {
        return in1102;
    }

    public void setIn1102(Character in1102) {
        this.in1102 = in1102;
    }

    public Character getIn1301() {
        return in1301;
    }

    public void setIn1301(Character in1301) {
        this.in1301 = in1301;
    }

    public Character getIn1302() {
        return in1302;
    }

    public void setIn1302(Character in1302) {
        this.in1302 = in1302;
    }



    public String getZclick() {
        return zclick;
    }

    public void setZclick(String zclick) {
        this.zclick = zclick;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    

    public String getZ100() {
        return z100;
    }

    public void setZ100(String z100) {
        this.z100 = z100;
    }

    public String getZ1000() {
        return z1000;
    }

    public void setZ1000(String z1000) {
        this.z1000 = z1000;
    }

    public String getZ100c() {
        return z100c;
    }

    public void setZ100c(String z100c) {
        this.z100c = z100c;
    }

    public String getZ100d() {
        return z100d;
    }

    public void setZ100d(String z100d) {
        this.z100d = z100d;
    }

    public String getZ100j() {
        return z100j;
    }

    public void setZ100j(String z100j) {
        this.z100j = z100j;
    }

    public String getZ100q() {
        return z100q;
    }

    public void setZ100q(String z100q) {
        this.z100q = z100q;
    }

    public String getZ100u() {
        return z100u;
    }

    public void setZ100u(String z100u) {
        this.z100u = z100u;
    }

    public String getZ110() {
        return z110;
    }

    public void setZ110(String z110) {
        this.z110 = z110;
    }

    public String getZ1100() {
        return z1100;
    }

    public void setZ1100(String z1100) {
        this.z1100 = z1100;
    }

    public String getZ1104() {
        return z1104;
    }

    public void setZ1104(String z1104) {
        this.z1104 = z1104;
    }

    public String getZ110b() {
        return z110b;
    }

    public void setZ110b(String z110b) {
        this.z110b = z110b;
    }

    public String getZ110c() {
        return z110c;
    }

    public void setZ110c(String z110c) {
        this.z110c = z110c;
    }

    public String getZ110d() {
        return z110d;
    }

    public void setZ110d(String z110d) {
        this.z110d = z110d;
    }

    public String getZ110k() {
        return z110k;
    }

    public void setZ110k(String z110k) {
        this.z110k = z110k;
    }

    public String getZ110n() {
        return z110n;
    }

    public void setZ110n(String z110n) {
        this.z110n = z110n;
    }

    public String getZ110p() {
        return z110p;
    }

    public void setZ110p(String z110p) {
        this.z110p = z110p;
    }

    public String getZ110t() {
        return z110t;
    }

    public void setZ110t(String z110t) {
        this.z110t = z110t;
    }

    public String getZ110u() {
        return z110u;
    }

    public void setZ110u(String z110u) {
        this.z110u = z110u;
    }

    public String getZ130() {
        return z130;
    }

    public void setZ130(String z130) {
        this.z130 = z130;
    }

    public String getZ130d() {
        return z130d;
    }

    public void setZ130d(String z130d) {
        this.z130d = z130d;
    }

    public String getZ130f() {
        return z130f;
    }

    public void setZ130f(String z130f) {
        this.z130f = z130f;
    }

    public String getZ130h() {
        return z130h;
    }

    public void setZ130h(String z130h) {
        this.z130h = z130h;
    }

    public String getZ130k() {
        return z130k;
    }

    public void setZ130k(String z130k) {
        this.z130k = z130k;
    }

    public String getZ130l() {
        return z130l;
    }

    public void setZ130l(String z130l) {
        this.z130l = z130l;
    }

    public String getZ130m() {
        return z130m;
    }

    public void setZ130m(String z130m) {
        this.z130m = z130m;
    }

    public String getZ130n() {
        return z130n;
    }

    public void setZ130n(String z130n) {
        this.z130n = z130n;
    }

    public String getZ130p() {
        return z130p;
    }

    public void setZ130p(String z130p) {
        this.z130p = z130p;
    }

    public String getZ130r() {
        return z130r;
    }

    public void setZ130r(String z130r) {
        this.z130r = z130r;
    }

    public String getZ130s() {
        return z130s;
    }

    public void setZ130s(String z130s) {
        this.z130s = z130s;
    }
   

    /**
     * @return
     */
   

    /**
     *
     */
    public CatActionForm1() {
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
}
