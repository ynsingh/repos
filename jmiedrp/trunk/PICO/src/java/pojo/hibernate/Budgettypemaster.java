package pojo.hibernate;
// Generated May 30, 2013 2:31:14 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Budgettypemaster generated by hbm2java
 */
public class Budgettypemaster  implements java.io.Serializable {


     private Byte btmId;
     private String btmName;
     private Set budgetmasters = new HashSet(0);

    public Budgettypemaster() {
    }

	
    public Budgettypemaster(String btmName) {
        this.btmName = btmName;
    }
    public Budgettypemaster(String btmName, Set budgetmasters) {
       this.btmName = btmName;
       this.budgetmasters = budgetmasters;
    }
   
    public Byte getBtmId() {
        return this.btmId;
    }
    
    public void setBtmId(Byte btmId) {
        this.btmId = btmId;
    }
    public String getBtmName() {
        return this.btmName;
    }
    
    public void setBtmName(String btmName) {
        this.btmName = btmName;
    }
    public Set getBudgetmasters() {
        return this.budgetmasters;
    }
    
    public void setBudgetmasters(Set budgetmasters) {
        this.budgetmasters = budgetmasters;
    }




}


