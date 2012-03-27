package aell

import grails.converters.JSON

class QuizController {
	def QuizMasterService

	def index = {
		redirect(controller: "editExperiment",action:"index", params:[contentType:'Q', choiceType: "select"])
	}

	def mchoicetxt = { }

	def mchoiceimg = { }

	def cfillblanks = {
		session.realPath = servletContext.getRealPath("/")
		session.contextPath = servletContext.contextPath
		//is Edit=1 => Editing FibQuestion 
		if(Integer.parseInt(params.isEdit) == 1 ){
			QuizMasterService.delQn(Integer.parseInt(params.qnId) as int)
		} 
		QuizMasterService.cFillBlanksQn(params, session)
		redirect(action:index)
	}

	def evaluatefib={
		def qnId=params.qnId as int
		def enteredAnswers=params.enteredAnswers
		List answers = params.enteredAnswers?.split(',')
		def wrongAnsList = QuizMasterService.eFillBlanksQn(qnId,answers,session,params)
		render wrongAnsList as JSON
	}
	
	def rfillblanks = {
		def fbqn = QuizMasterService.rFillBlanksQn(session) 
		render(view:"/contentHome/test", model:[fbqn:fbqn])
	}
	
	// action to create the match the following questions
	def cMatchQnAns = {
		if ((params.dndEdit as int) == 1) {
			QuizMasterService.delQns(params.qnIds.split(/,/).toList())
		}
		def status = QuizMasterService.cMatchQnAns(params,session)
		redirect(controller: "editExperiment",action:"index", params:[contentType:'Q', choiceType: "select"])
	}

	/**
	 * Used by assessment part; for admin part, it is being retrieved from EditExperimentsController 
	 * action to retrieve all match the following questions from the DB
	 */
	def rMatchQnAns = {
		def resp = QuizMasterService.rMatchQnAns(session)
	}
	
	/**
	 * This closure is called via ajax call, keeping the code in line with the mvc guideline
	 * Action to evaluate the matched options and answers
	 */
	
	def eMatchQnAns = {
	    def qaPair = (params['choice']).split(",").sort().inject([:]) {map1, token ->
                token.split(":").with{ map1[it[0] as long] = it[1] as long}
                map1
            }
		def ansIdPairs =  (params['ansIdPairs']).split("~").sort().inject([:]) {map2, token ->
                token.split(":").with{ map2[it[0] as long] = it[1] as String}
                map2
            }
		def a = QuizMasterService.eMatchQnAns(qaPair,ansIdPairs,session,params)
		render a as JSON
	}

	/**
	 * This closure is called via ajax call, keeping the code in line with the mvc guideline
	 * action to delete a match the following question along with its 
	 * options, answers and hint 
	*/
 	def dMatchQnAns = {
			QuizMasterService.delQns(params.qnIds.split(/,/).toList())
			render("1")
	}
	 
	 def cSubSectionHead = {
		 if ((params.subSectionEdit as int) == 1) {
			 QuizMasterService.dSubSectionHead(params.qnId as long)
		 }
		 long qId = QuizMasterService.cSubSectionHead(params,session)
		 render qId
	 }
 
	 def rSubSectionHead = {
		 def resp = QuizMasterService.rSubSectionHead(session)
	 }
	 
	 def dSubSectionHead = {
		QuizMasterService.dSubSectionHead(params.qnId as long)
		 render("1")
	 }
	 
}
