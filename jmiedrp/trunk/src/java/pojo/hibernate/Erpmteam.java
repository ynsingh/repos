package pojo.hibernate;
// Generated May 29, 2011 11:33:37 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Erpmteam generated by hbm2java
 */
public class Erpmteam  implements java.io.Serializable {


     private Byte erpmtId;
     private String erpmtName;
     private String ermtPiName;
     private String erpmtCopiName;
     private String erpmtPiEmail;
     private String erpmtCopiEmail;
     private Set erpmmodules = new HashSet(0);

    public Erpmteam() {
    }

	
    public Erpmteam(String erpmtName, String ermtPiName, String erpmtPiEmail) {
        this.erpmtName = erpmtName;
        this.ermtPiName = ermtPiName;
        this.erpmtPiEmail = erpmtPiEmail;
    }
    public Erpmteam(String erpmtName, String ermtPiName, String erpmtCopiName, String erpmtPiEmail, String erpmtCopiEmail, Set erpmmodules) {
       this.erpmtName = erpmtName;
       this.ermtPiName = ermtPiName;
       this.erpmtCopiName = erpmtCopiName;
       this.erpmtPiEmail = erpmtPiEmail;
       this.erpmtCopiEmail = erpmtCopiEmail;
       this.erpmmodules = erpmmodules;
    }
   
    public Byte getErpmtId() {
        return this.erpmtId;
    }
    
    public void setErpmtId(Byte erpmtId) {
        this.erpmtId = erpmtId;
    }
    public String getErpmtName() {
        return this.erpmtName;
    }
    
    public void setErpmtName(String erpmtName) {
        this.erpmtName = erpmtName;
    }
    public String getErmtPiName() {
        return this.ermtPiName;
    }
    
    public void setErmtPiName(String ermtPiName) {
        this.ermtPiName = ermtPiName;
    }
    public String getErpmtCopiName() {
        return this.erpmtCopiName;
    }
    
    public void setErpmtCopiName(String erpmtCopiName) {
        this.erpmtCopiName = erpmtCopiName;
    }
    public String getErpmtPiEmail() {
        return this.erpmtPiEmail;
    }
    
    public void setErpmtPiEmail(String erpmtPiEmail) {
        this.erpmtPiEmail = erpmtPiEmail;
    }
    public String getErpmtCopiEmail() {
        return this.erpmtCopiEmail;
    }
    
    public void setErpmtCopiEmail(String erpmtCopiEmail) {
        this.erpmtCopiEmail = erpmtCopiEmail;
    }
    public Set getErpmmodules() {
        return this.erpmmodules;
    }
    
    public void setErpmmodules(Set erpmmodules) {
        this.erpmmodules = erpmmodules;
    }




}


