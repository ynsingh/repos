package aell

class AvlMchoiceAns {
	String choices
	Integer qnId
	String status
	Integer createdBy
	Date createdDt
	Integer modifiedBy
	Date modifiedDt
	Integer id
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
