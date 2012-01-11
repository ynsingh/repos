/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.marc;


/**
 *
 * @author zeeshan
 */
public class CatActionForm2 extends org.apache.struts.action.ActionForm {
    
    private String name,zclick;

    private Character in2101,in2102,in2451,in2452,in2501,in2502,in2561,in2562,in2601,in2602,in2631,in2632;

     private String z210a,z210b,z2102, z245a,z245b,z245c,z245n,z245k, z250a,z250b, z256a, z260a,z260b,z260c,z260e,z260f, z263a;

    public Character getIn2101() {
        return in2101;
    }

    public void setIn2101(Character in2101) {
       
        this.in2101 = in2101;
    }

    public Character getIn2102() {
        return in2102;
    }

    public void setIn2102(Character in2102) {
       
        this.in2102 = in2102;
    }

    public Character getIn2451() {
        return in2451;
    }

    public void setIn2451(Character in2451) {


        this.in2451 = in2451;
    }

    public Character getIn2452() {
         if(in2452==null) return null;
        return in2452;
    }

    public void setIn2452(Character in2452) {
        
        this.in2452 = in2452;
    }

    public Character getIn2501() {
        return in2501;
    }

    public void setIn2501(Character in2501) {
        
        this.in2501 = in2501;
    }

    public Character getIn2502() {
        return in2502;
    }

    public void setIn2502(Character in2502) {
        
        this.in2502 = in2502;
    }

    public Character getIn2561() {
        return in2561;
    }

    public void setIn2561(Character in2561) {
        
        this.in2561 = in2561;
    }

    public Character getIn2562() {
        return in2562;
    }

    public void setIn2562(Character in2562) {
        
        this.in2562 = in2562;
    }

    public Character getIn2601() {
        return in2601;
    }

    public void setIn2601(Character in2601) {
        
        this.in2601 = in2601;
    }

    public Character getIn2602() {
        return in2602;
    }

    public void setIn2602(Character in2602) {
        
        this.in2602 = in2602;
    }

    public Character getIn2631() {
        return in2631;
    }

    public void setIn2631(Character in2631) {
        
        this.in2631 = in2631;
    }

    public Character getIn2632() {
        return in2632;
    }

    public void setIn2632(Character in2632) {
        
        this.in2632 = in2632;
    }

    public String getZ2102() {
        return z2102;
    }

    public void setZ2102(String z2102) {
        
        this.z2102 = z2102;
    }

    public String getZ210a() {
        return z210a;
    }

    public void setZ210a(String z210a) {
        
        this.z210a = z210a;
    }

    public String getZ210b() {
        return z210b;
    }

    public void setZ210b(String z210b) {
        
        this.z210b = z210b;
    }

    public String getZ245a() {
        return z245a;
    }

    public void setZ245a(String z245a) {
        
        this.z245a = z245a;
    }

    public String getZ245b() {
        return z245b;
    }

    public void setZ245b(String z245b) {
        
        this.z245b = z245b;
    }

    public String getZ245c() {
        return z245c;
    }

    public void setZ245c(String z245c) {
        
        this.z245c = z245c;
    }

    public String getZ245k() {
        return z245k;
    }

    public void setZ245k(String z245k) {
        
        this.z245k = z245k;
    }

    public String getZ245n() {
        return z245n;
    }

    public void setZ245n(String z245n) {
        
        this.z245n = z245n;
    }

    public String getZ250a() {
        return z250a;
    }

    public void setZ250a(String z250a) {
        
        this.z250a = z250a;
    }

    public String getZ250b() {
        return z250b;
    }

    public void setZ250b(String z250b) {
        
        this.z250b = z250b;
    }

    public String getZ256a() {
        return z256a;
    }

    public void setZ256a(String z256a) {
        
        this.z256a = z256a;
    }

    public String getZ260a() {
        return z260a;
    }

    public void setZ260a(String z260a) {
        
        this.z260a = z260a;
    }

    public String getZ260b() {
        return z260b;
    }

    public void setZ260b(String z260b) {
        
        this.z260b = z260b;
    }

    public String getZ260c() {
        return z260c;
    }

    public void setZ260c(String z260c) {
        
        this.z260c = z260c;
    }

    public String getZ260e() {
        return z260e;
    }

    public void setZ260e(String z260e) {
        
        this.z260e = z260e;
    }

    public String getZ260f() {
        return z260f;
    }

    public void setZ260f(String z260f) {
        
        this.z260f = z260f;
    }

    public String getZ263a() {
        return z263a;
    }

    public void setZ263a(String z263a) {
        
        this.z263a = z263a;
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


    public String getZclick() {
        return zclick;
    }

    public void setZclick(String zclick) {
        this.zclick = zclick;
    }
    /**
     * @return
     */
   
    public CatActionForm2() {
        super();
        // TODO Auto-generated constructor stub
    }

  
}
