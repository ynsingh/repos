package aell

class AvlQuizLogQuestion {
	String questionType
	Integer quizLogQpId
	 Integer questionId
	 String hintUsed
	 String ansExpectedText
	 String ansSelectedText
	 String ansCorrect
	static mapping = {
		version false
		}
    static constraints = {
    }
}
