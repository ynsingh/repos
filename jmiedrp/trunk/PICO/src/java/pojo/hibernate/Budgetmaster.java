package pojo.hibernate;
// Generated May 30, 2013 2:31:14 PM by Hibernate Tools 3.2.1.GA



/**
 * Budgetmaster generated by hbm2java
 */
public class Budgetmaster  implements java.io.Serializable {


     private Integer bmId;
     private Subinstitutionmaster subinstitutionmaster;
     private Budgettypemaster budgettypemaster;
     private Financialyearmaster financialyearmaster;
     private Institutionmaster institutionmaster;
     private Departmentmaster departmentmaster;
     private String bmLapsable;
     private String bmBudgetName;
     private int bmAllocatedAmount;

    public Budgetmaster() {
    }

    public Budgetmaster(Subinstitutionmaster subinstitutionmaster, Budgettypemaster budgettypemaster, Financialyearmaster financialyearmaster, Institutionmaster institutionmaster, Departmentmaster departmentmaster, String bmLapsable, String bmBudgetName, int bmAllocatedAmount) {
       this.subinstitutionmaster = subinstitutionmaster;
       this.budgettypemaster = budgettypemaster;
       this.financialyearmaster = financialyearmaster;
       this.institutionmaster = institutionmaster;
       this.departmentmaster = departmentmaster;
       this.bmLapsable = bmLapsable;
       this.bmBudgetName = bmBudgetName;
       this.bmAllocatedAmount = bmAllocatedAmount;
    }
   
    public Integer getBmId() {
        return this.bmId;
    }
    
    public void setBmId(Integer bmId) {
        this.bmId = bmId;
    }
    public Subinstitutionmaster getSubinstitutionmaster() {
        return this.subinstitutionmaster;
    }
    
    public void setSubinstitutionmaster(Subinstitutionmaster subinstitutionmaster) {
        this.subinstitutionmaster = subinstitutionmaster;
    }
    public Budgettypemaster getBudgettypemaster() {
        return this.budgettypemaster;
    }
    
    public void setBudgettypemaster(Budgettypemaster budgettypemaster) {
        this.budgettypemaster = budgettypemaster;
    }
    public Financialyearmaster getFinancialyearmaster() {
        return this.financialyearmaster;
    }
    
    public void setFinancialyearmaster(Financialyearmaster financialyearmaster) {
        this.financialyearmaster = financialyearmaster;
    }
    public Institutionmaster getInstitutionmaster() {
        return this.institutionmaster;
    }
    
    public void setInstitutionmaster(Institutionmaster institutionmaster) {
        this.institutionmaster = institutionmaster;
    }
    public Departmentmaster getDepartmentmaster() {
        return this.departmentmaster;
    }
    
    public void setDepartmentmaster(Departmentmaster departmentmaster) {
        this.departmentmaster = departmentmaster;
    }
    public String getBmLapsable() {
        return this.bmLapsable;
    }
    
    public void setBmLapsable(String bmLapsable) {
        this.bmLapsable = bmLapsable;
    }
    public String getBmBudgetName() {
        return this.bmBudgetName;
    }
    
    public void setBmBudgetName(String bmBudgetName) {
        this.bmBudgetName = bmBudgetName;
    }
    public int getBmAllocatedAmount() {
        return this.bmAllocatedAmount;
    }
    
    public void setBmAllocatedAmount(int bmAllocatedAmount) {
        this.bmAllocatedAmount = bmAllocatedAmount;
    }




}


