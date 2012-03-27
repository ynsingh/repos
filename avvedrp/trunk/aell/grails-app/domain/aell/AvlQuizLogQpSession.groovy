package aell

class AvlQuizLogQpSession {
	 Integer userId
	 Integer questionPaperId
	 Date dateTimePresented
	 Integer timeSpend
	 String sessionId
	 Integer sessionCount
	 Integer attemptNumber
	 String quizType
	static mapping = {
		version false
	 }
    static constraints =
	{
    	sessionCount nullable:true
		attemptNumber nullable:true
	}
}
