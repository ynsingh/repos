

class FinancialYear {
	String financialPeriod
	Date financialStartDate
	Date financialEndDate
	String description;
	char activeYesNo;

    static constraints = {
    	financialPeriod(nullable:true)
    	financialStartDate(nullable:true)
		financialEndDate(nullable:true)
    	
    	
    }
}
