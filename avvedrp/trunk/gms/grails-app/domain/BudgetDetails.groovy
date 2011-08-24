

class BudgetDetails {
  		BudgetMaster budgetMaster;
    	AccountHeads accountHeads;
    	double allocatedAmount;
    	String remarks;
    	char activeYesNo;
  

    static constraints = {
   		 budgetMaster(nullable:true)
  	  	 accountHeads(nullable:true)
  	  	 allocatedAmount(nullable:false)
  	 	 remarks(nullable:true)
    
    }
  
}
