package pojo.hibernate;
// Generated May 29, 2011 11:33:37 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Financialyearmaster generated by hbm2java
 */
public class Financialyearmaster  implements java.io.Serializable {


     private Byte fymFyId;
     private short fymFromYear;
     private short fymToYear;
     private Set budgetmasters = new HashSet(0);

    public Financialyearmaster() {
    }

	
    public Financialyearmaster(short fymFromYear, short fymToYear) {
        this.fymFromYear = fymFromYear;
        this.fymToYear = fymToYear;
    }
    public Financialyearmaster(short fymFromYear, short fymToYear, Set budgetmasters) {
       this.fymFromYear = fymFromYear;
       this.fymToYear = fymToYear;
       this.budgetmasters = budgetmasters;
    }
   
    public Byte getFymFyId() {
        return this.fymFyId;
    }
    
    public void setFymFyId(Byte fymFyId) {
        this.fymFyId = fymFyId;
    }
    public short getFymFromYear() {
        return this.fymFromYear;
    }
    
    public void setFymFromYear(short fymFromYear) {
        this.fymFromYear = fymFromYear;
    }
    public short getFymToYear() {
        return this.fymToYear;
    }
    
    public void setFymToYear(short fymToYear) {
        this.fymToYear = fymToYear;
    }
    public Set getBudgetmasters() {
        return this.budgetmasters;
    }
    
    public void setBudgetmasters(Set budgetmasters) {
        this.budgetmasters = budgetmasters;
    }




}


