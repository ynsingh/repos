package aell

class AvlUniversityMaster {
	String universityName
	String universityPlace
	Integer themeId
	String urlOfSite
	Integer id
	static mapping = {
		 id column: "university_id"
		version false
		}
    static constraints = {  
	   universityName blank: false
	   themeId blank:false
	   urlOfSite blank:false
    }
}

