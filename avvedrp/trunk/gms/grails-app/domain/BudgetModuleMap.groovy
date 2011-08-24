
class BudgetModuleMap {

	BudgetMaster budgetMaster;
	String moduleType;
	String moduleTypeId;
	
	char activeYesNo;
	
    static constraints = {
    	 budgetMaster(nullable:true)
  	  	 moduleType(nullable:true)
  	  	 moduleTypeId(nullable:false)
  	 	
    
    }
}
