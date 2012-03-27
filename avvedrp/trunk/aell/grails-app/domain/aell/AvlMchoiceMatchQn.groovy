package aell

class AvlMchoiceMatchQn {
	String qnText
	String qnImage
	Integer qnTypeId
	String qnDifficultyLevel
	String ansType
	String ansCorrect
	String ansDisplayOrder
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
		qnImage(blank:true, nullable: true)
		ansCorrect(nullable:true)
		qnDifficultyLevel(nullable:true)
		status(nullable:true)

		createdDt(nullable:true)
		createdBy(nullable:true)
		modifiedDt(nullable:true)
		modifiedBy(nullable:true)
    }
	def beforeInsert(){
		createdDt = new Date()
		qnDifficultyLevel = 1
		status = 'A'
		ansDisplayOrder = ansDisplayOrder ? 'Sequential' : 'Other'
	}
	
}
