package aell

class AvlAnswerSheet {
	Long qnId
	Long ansId
    static constraints = {
		ansId(nullable:true)
    }
}
