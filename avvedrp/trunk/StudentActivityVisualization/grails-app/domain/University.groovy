
class University{

	String univ_name
	String univ_address
	String univ_email
	static constraints = {             
                 univ_name(blank:false)
                 univ_address(blank:false) 
			     univ_email(blank:false)                    
	}
}
