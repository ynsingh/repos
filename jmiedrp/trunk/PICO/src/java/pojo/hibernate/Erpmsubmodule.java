package pojo.hibernate;
// Generated May 30, 2013 2:31:14 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Erpmsubmodule generated by hbm2java
 */
public class Erpmsubmodule  implements java.io.Serializable {


     private Integer erpmSubModuleId;
     private Erpmmodule erpmmodule;
     private String esmName;
     private String esmNameHindi;
     private Byte esmOrder;
     private String esmHref;
     private String esmEnvVariable;
     private Set erpmprograms = new HashSet(0);
     private Set genericroleprivilegeses = new HashSet(0);
     private Set institutionroleprivilegeses = new HashSet(0);

    public Erpmsubmodule() {
    }

    public Erpmsubmodule(Erpmmodule erpmmodule, String esmName, Byte esmOrder, String esmHref, String esmNameHindi, String esmEnvVariable, Set erpmprograms, Set genericroleprivilegeses, Set institutionroleprivilegeses) {
       this.erpmmodule = erpmmodule;
       this.esmName = esmName;
       this.esmOrder = esmOrder;
       this.esmHref = esmHref;
       this.esmEnvVariable = esmEnvVariable;
       this.erpmprograms = erpmprograms;
       this.genericroleprivilegeses = genericroleprivilegeses;
       this.institutionroleprivilegeses = institutionroleprivilegeses;
       this.esmNameHindi = esmNameHindi;
    }
   
    public Integer getErpmSubModuleId() {
        return this.erpmSubModuleId;
    }
    
    public void setErpmSubModuleId(Integer erpmSubModuleId) {
        this.erpmSubModuleId = erpmSubModuleId;
    }
    public Erpmmodule getErpmmodule() {
        return this.erpmmodule;
    }
    
    public void setErpmmodule(Erpmmodule erpmmodule) {
        this.erpmmodule = erpmmodule;
    }
    public String getEsmName() {
        return this.esmName;
    }
    
    public void setEsmName(String esmName) {
        this.esmName = esmName;
    }

    public String getEsmNameHindi() {
        return esmNameHindi;
    }

    public void setEsmNameHindi(String esmNameHindi) {
        this.esmNameHindi = esmNameHindi;
    }

    public Byte getEsmOrder() {
        return this.esmOrder;
    }
    
    public void setEsmOrder(Byte esmOrder) {
        this.esmOrder = esmOrder;
    }
    public String getEsmHref() {
        return this.esmHref;
    }
    
    public void setEsmHref(String esmHref) {
        this.esmHref = esmHref;
    }
    public String getEsmEnvVariable() {
        return this.esmEnvVariable;
    }
    
    public void setEsmEnvVariable(String esmEnvVariable) {
        this.esmEnvVariable = esmEnvVariable;
    }
    public Set getErpmprograms() {
        return this.erpmprograms;
    }
    
    public void setErpmprograms(Set erpmprograms) {
        this.erpmprograms = erpmprograms;
    }
    public Set getGenericroleprivilegeses() {
        return this.genericroleprivilegeses;
    }
    
    public void setGenericroleprivilegeses(Set genericroleprivilegeses) {
        this.genericroleprivilegeses = genericroleprivilegeses;
    }
    public Set getInstitutionroleprivilegeses() {
        return this.institutionroleprivilegeses;
    }
    
    public void setInstitutionroleprivilegeses(Set institutionroleprivilegeses) {
        this.institutionroleprivilegeses = institutionroleprivilegeses;
    }




}


