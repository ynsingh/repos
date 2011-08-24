

class BudgetMaster {
     FinancialYear financialYear;
     Party party;
     String budgetCode;
     String budgetDescription;
     String title;
     double totalBudgetAmount;
     char activeYesNo;
    

    static constraints = {
    	budgetCode(nullable:false,blank:false)
    	totalBudgetAmount(nullable:false)
    	title(nullable:false)
    }
}
