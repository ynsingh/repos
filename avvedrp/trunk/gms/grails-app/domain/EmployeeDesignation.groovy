

class EmployeeDesignation {
	String Designation
	char activeYesNo; //15-11-2010
    static constraints = {
		  Designation(blank:false)
	}	  
		  String saveMode;
		
	static transients = [ "saveMode" ]

}
