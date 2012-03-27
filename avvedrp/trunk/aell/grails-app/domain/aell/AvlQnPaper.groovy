package aell

import java.util.Date;

class AvlQnPaper {
	Integer contentId
	Integer qnPaperId
	Long qnId			// sequence got from the table; not an autogen
	String qnType

	Integer createdBy
	Date createDt
	Integer modifiedBy
	Date modifiedDt
	Integer qnSequence
	
    static constraints = {
		createDt(nullable:true)
		createdBy(nullable:true)
		modifiedDt(nullable:true)
		modifiedBy(nullable:true)
		
    }
	def beforeInsert(){
		createDt = new Date()
		modifiedDt = createDt
		createdBy = 201
		modifiedBy = createdBy
	}

	def beforeUpdate(){
		modifiedDt = new Date()
	}

}
