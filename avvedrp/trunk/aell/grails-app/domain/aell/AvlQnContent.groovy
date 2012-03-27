package aell
/**
 * Supported types of questions:
 * multiple choice
 * 	text
 * 	image
 * match the following (drag & drop)
 * 	text
 * 	image
 * Fill in the blanks
 * 	text
 * 
 * 	
 */
import java.util.Date;

class AvlQnContent {
	Long qnId			// sequence got from the table; not an autogen
	String text
	String imgPath 			//
	String type 		 	//options are (Q)uestion or (A)nswer option or (H)int text or (S)ection head
	long parentQnId		// is this option the correct answer? TODO: Move this to the answer sheet table
	String status			// published?	
	Integer difficultyLvl
	String dispOrder		// if textType=Question, then this field should have a number assigned to it
	// The following constitute the logging elements
	Integer modifiedBy
	Date modifiedDt

	static constraints = {
		difficultyLvl(nullable:true)
		status(nullable:true)
		modifiedBy(nullable:true)
		modifiedDt(nullable:true)
		dispOrder(nullable:true)
		imgPath(nullable:true)
		text(nullable:true)
    }
	
	static mapping = {
		text type: 'text'
	}
	
	def beforeInsert(){
		difficultyLvl = 1
		status = 'A'
		modifiedDt = new Date()
		dispOrder = dispOrder ? dispOrder : 'Sequential'
	}

	def beforeUpdate(){
		modifiedDt = new Date()
	}

}

