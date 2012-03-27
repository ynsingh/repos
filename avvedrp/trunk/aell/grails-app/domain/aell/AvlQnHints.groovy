package aell

import org.apache.tools.ant.types.selectors.modifiedselector.ModifiedSelector;


class AvlQnHints {
Integer qnId
String hintType
String hintText
String status
Integer createdBy
Date createdDt
Integer modifiedBy
Date modifiedDt
static mapping = {
	   version false
	   }
    static constraints = {
		createdBy(nullable:true)
		createdDt(nullable:true)
		modifiedBy(nullable:true)
		modifiedDt(nullable:true)
		status(nullable:true)
    }
	def beforeInsert(){
		createdDt = new Date()
		status = 'A'
	}
		
}
