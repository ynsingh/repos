package aell
import org.apache.log4j.*


class MigrationController {
	public static String PATTERN = "%d{ABSOLUTE} %-5p [%c{1}] %m%n"
	def quizMasterService
    def index = { }
	
	def migrateOld = {
		// get list of all tabs / content id that are 'Active'
		def cid = AvlContentDetails.findAllByContentStatus('A')
		def tabs = AvlContentDescriptionVersion.withCriteria{
			'in' ('content', cid)
			'in' ('contentMode', 'Q')
			}
		println "====================got all tabs" + tabs
		def oldQns = []
		def count = 0
		tabs.contentId.each{
			session.selectedContentId = it
			def m = [contentId : it , qn : quizMasterService.getOldQuizQuestion(session)]
			count += m.qn.size()
			oldQns.add(m)
		}
		println "====================got all questions " + count 
		def totalQns = 0
		oldQns.each{ i ->										 // iterate over each tab
			def qnListOnTab = i.qn
			session.selectedContentId = i.contentId
			qnListOnTab.each { j -> 		
				totalQns ++				// iterate over questions
				params.seq = j.seq ?: 0
				params.oldQnId = j.qnId
				if (j.type == 1) { 								// for text
					params.ansType = "Text" 				// based on j.type
					params.imgValue0 = j.image 			// depends on j.type
				} else if(j.type == 2) { 						// for image
					params.ansType = "Image"				// based on j.type
					params.qnimgValue0 = j.image 		// depends on j.type
				}else { 											// type = 3 : section head
				}
				params.qnText = j.qnText
				params.hintText = j.qnHint
				params.ansVal = j.answers?.size() ?: 0
				params.ansVal?.times { k -> 				// iterate over options
					def temp = k + 1
					if (j.type == 1) { 							// for text
						params."choice$temp" = j.answers[k].text
					} else if (j.type == 2){
						params."imgValue$temp" = j.answers[k].text
					}
					if (j.correct as int == j.answers[k].key) params.correctAns = temp     
				}
				quizMasterService.setMigrateNewQuizQuestion(session, params)
			}
		}
		println " migrated " + totalQns + " questions"  
		render (oldQns)
	}
}
