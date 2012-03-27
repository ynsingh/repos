package aell
/**
 * This service creates and gets various types of questions. The method convention is as follows:
 * 	Prefix "public" functions with the following
 * 		c = create question or insert question into database
 * 		r = retrieve question from the database (or cache)
 * 		u = update a particular question type in the database
 * 		d = delete a particular question
 * 		e = evaluate a question against provided answers
 * 
 * Other abbreviations that are used consistently:
 * 		1) Qn: question 
 * 
 * The in/out of each method should be mentioned separately in documentation
 * 			of each method
 * 
 */
import java.util.Formatter.DateTime;
import java.util.List;
import java.util.Hashtable.ValueCollection;


import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import org.apache.commons.io.FileUtils

import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession;
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap;

class QuizMasterService {

	boolean transactional = true
	def utilService
	def myConstants = [question: "Q", answer: "A", hint: "H", fillBlanks: "FB", matchFollowing: "MF", multipleChoiceText : "MC", multipleChoiceImage : "MCI", sectionHead: "SH", published: "P", accepted: "A", seqName: "quiz", subSection: "SS" , dndText : "DND"]

	def serviceMethod() {
	}


	// do not call this function directly. Only "getQuestionPaperId" can call this function
	private int createQuestionPaper (GrailsHttpSession session){
		def qnPaperId
		try{
			def temp = new AvlQnPaperMaster()
			temp.Name = "questionPaper" + new Date()
			temp.save(flush:true)
			qnPaperId = temp.id

			def temp1 = new AvlContentDescriptionVersion(versionStatus : myConstants.published, dateTime : new Date(), userId : session.userId, contentDescription : qnPaperId, contentMode:'Q')
			temp1.content = AvlContentDetails.findById(session.selectedContentId)
			temp1.save(flush:true)

			def temp2 = new AvlContentDetails(versionId : temp1.id, contentStatus : myConstants.accepted, authenticationNeeded : 'N')
			temp2.experiment = AvlExperimentMaster.findById(session.selectedExperimentId)
			temp2.save()

			def avlContentDetailsInstance = AvlContentDetails.find("from AvlContentDetails where content_id=?",[
				session.selectedContentId as int
			])
			if(avlContentDetailsInstance){
				avlContentDetailsInstance.versionId = temp1.id
				avlContentDetailsInstance.save()
			}
		}catch (Exception e) {
			println("Exception in creating a question paper\n" + e.stackTrace)
		}
		return qnPaperId
	}

	/**
	 * method overloading
	 * @param:in selectedContentId
	 * @return: question paper id as int
	 * Dont use this method: DEPRICATED
	 */
	private int getQuestionPaperId(int selectedContentId){
		getQuestionPaperId(selectedContentId, null)
	}
	/**
	 * method overloading to simplyfy the api
	 * @param session
	 * @return
	 */
	private int parseQnPaperFromSession(GrailsHttpSession session){
		return getQuestionPaperId(session.selectedContentId as int, session) // get question paper id
	}
	private int getQuestionPaperId(int selectedContentId, GrailsHttpSession session){
		try{
			def recCriteria = AvlContentDetails.createCriteria()
			def contentRow = recCriteria.get {
				and{
					'in' ('contentStatus', ['A', 'E'])
					eq ("id", selectedContentId)
				}
			}
			def resultSet = AvlContentDescriptionVersion.findByContentModeAndContent('Q', contentRow)
			return resultSet ? resultSet.contentDescription  as int : createQuestionPaper(session)
		}catch (Exception e) {
		}
	}
	private Map parseQnFillBlanks(String qnText){
		def reg = /\[|\]/ 	// expecting the FIB words inside square brackets
		List splitText = qnText.split(reg)
		List blanks = []
		def text = ""
		(splitText.size()).times{
			if ((it % 2 ) != 0){
				blanks.add(splitText[it])
				text+='[]'
			}
			else  text += (splitText[it])
		}
		return [qntext:text, blanks: blanks]
	}

	void setNewQuizQuestion(GrailsHttpSession session, GrailsParameterMap params){ // add & edit
		def quesType = myConstants.multipleChoiceText
		def imgStatus = false
		try{
			long questionId = utilService.getSequence(myConstants.seqName)

			if(params.ansType.equals("Image")){
				quesType=myConstants.multipleChoiceImage
				imgStatus = true
			}
			// Question
			AvlQnContent aqcQn = new AvlQnContent(qnId: questionId, text: params.qnText?.toString(), type: myConstants.question, isCorrect:false)
			if (params.imgValue0 != null && !(params.imgValue0.isEmpty())) {
				aqcQn.imgPath = utilService.saveImage(params.imgValue0, session.realPath, session.contextPath) as String;
			}
			if (params.qnimgValue0 != null && !(params.qnimgValue0.isEmpty())) {
				aqcQn.imgPath = utilService.saveImage(params.qnimgValue0, session.realPath, session.contextPath) as String;
			}
			aqcQn.save()

			//Hint
			AvlQnContent hint = new AvlQnContent(qnId: questionId, text: params.hintText?.toString(), type: myConstants.hint, isCorrect: false)
			hint.save()
			//Choices Text/Image
			(params.ansVal?.toInteger()).times {
				def i = it+1
				AvlQnContent aqcAns = null
				aqcAns = (params.ansType.equals("Image")) ?
						new AvlQnContent(qnId: questionId, type: myConstants.answer,
						imgPath: utilService.saveImage(params."imgValue$i", session.realPath, session.contextPath) as String,isCorrect: false) :
						new AvlQnContent(qnId: questionId, text: params."choice$i".toString(), type: myConstants.answer, isCorrect: false)
				aqcAns.save(flush:true)
				// adding answer details
				if(i == params.correctAns?.toInteger()){
					AvlAnswerSheet ansS = new AvlAnswerSheet(qnId: questionId, ansId: aqcAns.id);
					ansS.save()
				}
			}

			//adding to ques paper
			def qnPaperId = this.parseQnPaperFromSession(session)
			def maxSeqValue = AvlQnPaper.executeQuery("select  max(qnSequence) from AvlQnPaper d");
			AvlQnPaper qnPaper = new AvlQnPaper(contentId: session.selectedContentId , qnPaperId: qnPaperId, qnId: questionId, qnType:quesType, qnSequence: maxSeqValue[0]? ((maxSeqValue[0] as int)+1) : 1 )
			qnPaper.save()

		}catch (Exception e){
			println("Exception in adding a new question" + e.stackTrace)
		}
	}
	long setNewSectionHead(GrailsHttpSession session, GrailsParameterMap params){ // add & edit
		def quesType = myConstants.sectionHead
		long questionId
		try{
			questionId = utilService.getSequence(myConstants.seqName)

			// Question
			AvlQnContent aqcQn = new AvlQnContent(qnId: questionId, text: params.qnText?.toString(), type: quesType, isCorrect:false)
			aqcQn.save()

			//adding to ques paper
			def qnPaperId = this.parseQnPaperFromSession(session)
			def maxSeqValue = 0
			AvlQnPaper qnPaper = new AvlQnPaper(contentId: session.selectedContentId , qnPaperId: qnPaperId, qnId: questionId, qnType:quesType, qnSequence: maxSeqValue )
			qnPaper.save()

		}catch (Exception e){
			println("Exception in adding a new section head" + e.stackTrace)
		}
		return questionId
	}

	List getAllQuizQuestion(GrailsHttpSession session){
		List ret = []
		try{
			def res = [:]
			Map tra = [Qid: "qnId", Q: "qnText", A:"answers", CA:"correct", H: "qnHint", IQ: "image", T: "type"]
			Map tra2 = [ A: "key", T: "text", IA: "ansImg"]
			def qnPaperId = this.parseQnPaperFromSession(session)
			def qp = AvlQnPaper.createCriteria()
			def qns = qp.list {
				and{
					'in'("qnType", ["MC", "MCI", "SH"])
					'eq'("qnPaperId", qnPaperId)
				}
			}
			def qnRecords = qns.size() > 0 ? AvlQnContent.withCriteria{'in' ('qnId', qns.qnId)} : null
			def qnRecordsAns = qns.size() > 0 ? AvlAnswerSheet.withCriteria{'in' ('qnId', qns.qnId)} : null
			//adding question, hint and options for text and img choice
			qnRecords?.each() {
				if (res.(it.qnId) == null ) {
					res.(it.qnId) = [:]
					res.(it.qnId).(tra.A) = []

				}
				if((it.type).equals("SH")) // for section head of multiple choice
					res.(it.qnId) = [(tra.("Qid")):(it.qnId), (tra.Q):(it.text)]
				else if(it.type.equals("Q")){
					res.(it.qnId).(tra.("Qid"))=(it.qnId)
					res.(it.qnId).(tra.(it.type))=(it.text)
					res.(it.qnId).(tra.IQ)= (it.imgPath)
				}else if(it.type.equals("A"))
					res.(it.qnId).(tra.(it.type)).add([ (tra2.(it.type)): it.id, (tra2.T): (it.text), (tra2.IA): (it.imgPath) ])
				else
					res.(it.qnId).(tra.(it.type))=(it.text)
			}
			//adding correct answers for each question
			qnRecordsAns?.each(){
				res.(it.qnId).(tra.CA) = it.ansId
			}
			// adding type and refractoring answers
			qns?.each(){ i ->
				res.(i.qnId).(tra.T) = i.qnType.equals(myConstants.multipleChoiceText) ? 1 : 2
				if(i.qnType.equals("SH"))
					res.(i.qnId).(tra.T) = 3
				res.(i.qnId).qnSeq = i.qnSequence
				res.(i.qnId).(tra.A).each(){  j ->
					if(j.text.equals(null)){
						j.text = j.ansImg
					}
					j.remove(tra2.IA)
				}
				ret.add(res."$i.qnId")
			}
		} catch (Exception e) {
			e.printStackTrace()
		}
		return ret.sort{k -> k.qnSeq}
	}

	def saveQuizLog(uId,qpId,duration,sssnId,type){
		def userLogInstanceDetails=AvlUserLoginDetails.find("from AvlUserLoginDetails where logoutStatus='E' and userId='"+uId+"'")
		def sessnCnt=userLogInstanceDetails.sessionCount
		def avlQuizLogQpSessionInstance=new AvlQuizLogQpSession(userId:uId,questionPaperId:qpId,dateTimePresented:new Date(),timeSpend:duration,sessionId:sssnId,sessionCount:sessnCnt,quizType:type,attemptNumber:0)
		if(avlQuizLogQpSessionInstance.save(flush:true))
			return avlQuizLogQpSessionInstance

	}

	def getSelectedAnswer(qnId){
		def avlQnContentInstance=AvlQnContent.get(qnId)
		if(avlQnContentInstance)
			return avlQnContentInstance.text
	}

	void saveQuizLogDetails(qnId,hntUsd,ansExptdText,ansSlctdTxt,ansCrrct,qzLogQpId,type)
	{
		if(hntUsd==null)
			hntUsd='N'
		AvlQuizLogQuestion avlQuizLogQuestionInstance = new AvlQuizLogQuestion(questionType:type, quizLogQpId: qzLogQpId, questionId: qnId, hintUsed: hntUsd, ansExpectedText: ansExptdText, ansSelectedText: ansSlctdTxt, ansCorrect: ansCrrct)
		avlQuizLogQuestionInstance.save(flush:true)
	}
	
	void addLogAttemptNoAndQuizType(qzLogQpId,type,userId,sessnId)
	{
		def maxAttemptValue = AvlQuizLogQpSession.executeQuery("select  max(a.attemptNumber) from AvlQuizLogQpSession a where userId="+userId+" and sessionId ='"+sessnId+"' and quizType='"+type+"'");
		def quizLogInstance=AvlQuizLogQpSession.find("from AvlQuizLogQpSession a where a.id='"+qzLogQpId+"' and userId="+userId+" and sessionId ='"+sessnId+"'")
		if(quizLogInstance){
			quizLogInstance.attemptNumber=(maxAttemptValue.get(0).equals("0") || maxAttemptValue.get(0)==null)?1:(new Integer(maxAttemptValue.get(0))+1)
		}
	}
	
	/**
	 * 
	 * Deletes all question related information from the tables
	 * @param qId
	 */
	void delQn(int qId) {
		[
			AvlQnPaperQnRelation,
			AvlQnHints,
			AvlMchoiceAns,
			AvlQnContent,
			AvlQnPaper,
			AvlAnswerSheet
		].each{
			it.executeUpdate("delete " + it.toString().replaceAll("class ","") + " where qnId=${qId}")
		}
		def obj = AvlMchoiceMatchQn.get(qId)
		if (obj) obj.delete(flush:true)
	}
	def getEditQuestionDetails(qnId){
		def questionDetailsList = []
		def questionDetails=AvlMchoiceMatchQn.get(qnId)
		def answers = AvlMchoiceAns.findAll("from AvlMchoiceAns a where a.qnId=?",[qnId])
		def hint=AvlQnHints.find("from AvlQnHints a where a.qnId=?",[qnId])
		if(questionDetails)
			questionDetailsList = [questionDetails:questionDetails,answers:answers,hint:hint]
		return questionDetailsList
	}
	def updateQuizQuestion(params,userId)
	{
		def avlQnPaperQnRelationInstance=AvlQnPaperQnRelation.findByQnId(params.questionId)
		def avlContentDescriptionVersionInstance=AvlContentDescriptionVersion.findByContentDescription(avlQnPaperQnRelationInstance.qnPaperId)
		avlContentDescriptionVersionInstance.dateTime=new Date()
		avlContentDescriptionVersionInstance.save()
		for (int i=0;i<params.choiceId.size();i++){
			def avlMchoiceAnsInstance= AvlMchoiceAns.find("from AvlMchoiceAns a where a.id=?",[params.choiceId[i] as int])
			if(avlMchoiceAnsInstance){
				if(params.qnTypeId=="2")
					avlMchoiceAnsInstance.choices=params."imgHidVal$i".toString()
				else
					avlMchoiceAnsInstance.choices=params.choices[i]
				avlMchoiceAnsInstance.modifiedBy=userId
				avlMchoiceAnsInstance.modifiedDt=new Date()
				avlMchoiceAnsInstance.save()
			}
		}

		def avlMchoiceMatchQnInstnce=AvlMchoiceMatchQn.get(params.questionId as int)
		if(avlMchoiceMatchQnInstnce){
			avlMchoiceMatchQnInstnce.qnText=params.questTxt
			if(params.editImgValue4)
				avlMchoiceMatchQnInstnce.qnImage=params.editImgValue4
			def avlMchoiceAns=AvlMchoiceAns.find("from AvlMchoiceAns a where a.choices=? and a.qnId=?",[
				params.checkAns,
				params.questionId as int
			])
			if(avlMchoiceAns)
				avlMchoiceMatchQnInstnce.ansCorrect=avlMchoiceAns.id
			avlMchoiceMatchQnInstnce.modifiedBy=userId
			avlMchoiceMatchQnInstnce.modifiedDt=new Date()
			avlMchoiceMatchQnInstnce.save()
		}
		if(params.hintText)
		{
			def hintInstance=AvlQnHints.find("from AvlQnHints a where a.qnId=?",[params.questionId as int])
			if(hintInstance){
				hintInstance.hintText=params.hintText
				hintInstance.modifiedBy=userId
				hintInstance.modifiedDt=new Date()
				hintInstance.save()
			}
			else{
				def obj2 = new AvlQnHints()
				/*Adding hint*/
				obj2.qnId = params.questionId as int
				obj2.hintType="Text"
				obj2.hintText=params.hintText
				obj2.createdBy =userId
				obj2.createdDt=new Date()
				obj2.save()
			}

		}
		return
	}
	// this is to update section head of quiz module
	void updateQuizSectionHead(session,params){
		def avlMchoiceMatchQnInstance=AvlMchoiceMatchQn.get(params.qnId)
		avlMchoiceMatchQnInstance.qnText=params.qnText
		avlMchoiceMatchQnInstance.save()
	}

	def getFileName(path,fileName,uploadedFile,userDir){
		Random random = new Random()
		def rand=random.nextInt(200+1)
		int dotInd = fileName.lastIndexOf('.');
		def extension=(dotInd > 0 && dotInd < fileName.length()) ? fileName.substring(dotInd + 1) : null
		def name =fileName.substring(0, ((fileName.length())-4))
		fileName=name+rand+"."+extension
		uploadedFile.transferTo( new File( userDir,fileName))
		return fileName
	}
	def getFilePathAfterCrop(webRootDir,x1,y1,x2,y2,hostname,serverUploadPath,filename,quizPath){
		def path=webRootDir+"/"+serverUploadPath+"/"+quizPath+"/"+filename
		File tempPicture = new File(path)
		BufferedImage image = ImageIO.read(tempPicture)
		BufferedImage croppedImage = image.getSubimage(x1, y1, x2-x1, y2-y1)
		def newPath=webRootDir+"/"+serverUploadPath+"/"+quizPath+"/crop/"+filename
		File profilePicture = new File(newPath)
		profilePicture.mkdirs()
		ImageIO.write(croppedImage, "jpg", profilePicture)
		FileUtils.deleteQuietly(tempPicture)
		def filePath=hostname+"/"+serverUploadPath+"/"+quizPath+"/crop/"+filename
		return filePath


	}
	// drag and drop facility for text and image questions
	void updateSequence(String sequenceOrder){
		List ids = sequenceOrder.split(",")
		List lArray = []
		ids.each{ it->
			lArray.add(it as long)
		}
		def avlqnpaper = ids.size() > 0 ? AvlQnPaper.withCriteria{'in' ('qnId', lArray)} : null

		avlqnpaper.size().times{
			def index = lArray.indexOf(avlqnpaper[it].qnId)
			avlqnpaper[it].qnSequence = index + 1
			avlqnpaper[it].save()
		}

	}


	// drag and drop facility for Fill in the blank  questions
	void updateSequenceQuiz(String sequenceOrder){
		String[] ids = sequenceOrder.split(",")
		for(int i=0;i<ids.length;i++)
		{
			def avlQnPaperInstance = AvlQnPaper.findByQnId(ids[i] as int)
			if(avlQnPaperInstance){
				avlQnPaperInstance.qnSequence = i+1;
				avlQnPaperInstance.save();
			}
		}
	}

	/**
	 * Fill in the blanks, Add question
	 * @param params
	 * @param session	
	 */
	void cFillBlanksQn(GrailsParameterMap params, GrailsHttpSession session) {
		try{
			def type
			if (params.ansType.equals("fib"))
				type =  myConstants.fillBlanks
			else
				type =  myConstants.dndText
				
			def parsedQn = 	parseQnFillBlanks(params.qnText)  																						 							// returns map containing "qntext" & "blanks"
			def blanksInQn = parsedQn.blanks
			if (blanksInQn.size() == 0 ) return
			def seq = utilService.getSequence(myConstants.seqName)
			AvlQnContent qn = new AvlQnContent(type: myConstants.question, text:  parsedQn?.qntext?.toString(), qnId: seq, isCorrect: false)		//insert question  in QnContent table
			AvlQnContent hint = new AvlQnContent(type: myConstants.hint, text: params.hintText.toString(), qnId: seq, isCorrect: false)				// insert hint  in QnContent table
			qn.save()
			hint.save()

			(blanksInQn.size()).times{
				AvlQnContent blank = new AvlQnContent(type:myConstants.answer + it, text: blanksInQn[it].toString(), qnId: seq, isCorrect: true, imgPath: null)
				blank.save(flsuh:true)																																									// insert blanks in QnContent table
				AvlAnswerSheet ansS = new AvlAnswerSheet(qnId: seq, ansId: blank.id)																				// add entry to AnswerSheet table
				ansS.save();
			}
			def qnPaperId = this.parseQnPaperFromSession(session) 																											// get question paper id
			AvlQnPaper qnPaper = new AvlQnPaper(contentId: session.selectedContentId , qnPaperId: qnPaperId, qnId: seq, qnType: type)    		// add the questions reference to QnPaper table
			def  results = AvlQnPaper.findAllByQnPaperId(qnPaperId)
			def lastQnMaster
			if(results.size>0){
				lastQnMaster = results.last()
			}
			def seqnce
			if(lastQnMaster != null){
				seqnce= lastQnMaster.qnSequence + 1;
			}else {
				seqnce = 1
			}
			qnPaper.qnSequence=seqnce
			qnPaper.save()
		}catch (Exception e){
			e.printStackTrace()
		}
	}

	/**
	 * Returns all Fill in the blanks questions for a question paper
	 * Question paper is derived from the session (using session.contentId)
	 * return format: 
	 * id =>{ q  -> qnTxt,
	 * 		  h  -> hintTxt,
	 * 		  A0 -> answer Txt,
	 * 		  A1 -> answer Txt
	 * 		  ...
	 * 		}, ...
	 */
	Map rFillBlanksQn(session,qnType){
		Map res = [:]
		Map res2 = [:]
		try {
			def qnPaperId = this.parseQnPaperFromSession(session)																								// get question paper id based on session information
			def qns = AvlQnPaper.findAllByQnPaperIdAndQnType(qnPaperId, qnType,[sort:"qnSequence"])
			// get all questions from QnPaper table
			def qnRecords = qns.size() > 0 ? AvlQnContent.withCriteria{ 'in' ('qnId' , qns.qnId)} :  null
			qnRecords?.each(){
				if(res.(it.qnId) == null )
					res.(it.qnId) = [(it.type):(it.text)]
				else
					res.(it.qnId).(it.type) = (it.text)
			}
			// reordering res according to qnSequence order
			qns.qnId.each(){
				res2.("$it") = res."$it"
			}

		}catch (Exception e ) {
			e.printStackTrace()
		}
		return res2
	}


	/**
	 * Fill in the blanks, evaluate answers for fill in the blanks
	 * @param qnId : the qn id that is being evaluated
	 * @param enteredAnswers: list of entered answers
	 * @return Fill in the blanks, evaluate answers for fill in the blanks
	 */
	Map eFillBlanksQn(int qnId, List enteredAnswers,session,params){
		def avlQuizLogQpSessionInstance=null, ansCorrect, ansSelectedText, qnPaperId, duration
		QuizMasterService qms = new QuizMasterService()
		LoginService ls = new LoginService()
		qnPaperId=qms.getQuestionPaperId( session.selectedContentId as int, null)
		duration=ls.getTimeDuration(session.quizStartTime,new Date())
		
		if(((params.enterValue.equals("1")) && (session.user)))
			avlQuizLogQpSessionInstance=qms.saveQuizLog(session.user.id,qnPaperId,duration,session.sessionid,params.qnType)
		else
			avlQuizLogQpSessionInstance= AvlQuizLogQpSession.find("from AvlQuizLogQpSession where questionPaperId='"+qnPaperId+"' and userId='"+session.user.id+"' and sessionId='"+session.sessionid+"' and quizType = '"+params.qnType+"' order by id desc ")
			
		Map resp = [numWrong : 0 , wrongIndex : []]
		List storedAns = AvlQnContent.withCriteria{
			and{
				'in' ('qnId' , qnId as long)
				'like' ('type', "A%")
			}
		}
		storedAns.sort{k -> k.id}
		enteredAnswers.size().times{
			ansCorrect='Y'
			if(enteredAnswers[it] != storedAns?.text[it]){
				resp.numWrong++
				resp.wrongIndex.add(it)
				ansCorrect='N'
			}
			def ansExpectedText = storedAns[it].text
			ansSelectedText=((enteredAnswers[it]).equals('1'))?"Not Entered":enteredAnswers[it]
			if(avlQuizLogQpSessionInstance)
				qms.saveQuizLogDetails(storedAns[it].qnId,params.hntUsed,ansExpectedText,ansSelectedText,ansCorrect,avlQuizLogQpSessionInstance?.id,params.qnType)
		}
		if(params.sbmitFlg=="1")
			qms.addLogAttemptNoAndQuizType(avlQuizLogQpSessionInstance?.id, params.qnType,session.user.id,session.sessionid)
		return resp
	}

	/**
	 * create match the following questions
	 *  The following should be passed in the params object to this method
	 * 	 Q = parent question
	 *  H = hint for the question 
	 * 	 nOptions = int value denoting the no:of options
	 * 	 options and answers named as option1:answer1
	 */
	String cMatchQnAns(GrailsParameterMap params, GrailsHttpSession session){
		Boolean status = false
		try{
			long parentId = utilService.getSequence(myConstants.seqName)

			AvlQnContent aqcQn = new AvlQnContent(parentQnId: parentId, qnId: parentId, text: params.Q?.toString(), type: myConstants.question, isCorrect:false)
			if (params.qnImage != null) { 																																		// adding image support
				aqcQn.imgPath = utilService.saveImage(params.qnImage, session.realPath, session.contextPath) as String;
			}
			aqcQn.save() 																																								// insert question in QnContent table

			AvlQnContent hint = new AvlQnContent(parentQnId: parentId, qnId: parentId, text: params.H?.toString(), type: myConstants.hint, isCorrect: false)
			hint.save() 																																								    // insert hint in QnContent table

			(params.noChoices?.toInteger()).times {
				// insert the options in QnContent table
				def i = it+1
				long questionId = utilService.getSequence(myConstants.seqName)
				AvlQnContent aqcOpt = new AvlQnContent(qnId: questionId, text: params."option$i".toString(), type: myConstants.question, parentQnId: parentId, isCorrect: false)
				aqcOpt.save() 																																					    // create qn record in QnContent table

				AvlQnContent aqcAns = new AvlQnContent(qnId: questionId, text: params."answer$i".toString(), type: myConstants.answer, parentQnId: parentId, isCorrect: false)
				aqcAns.save(flush:true) 																																			// create answer record in the QnContent table

				AvlAnswerSheet ansS = new AvlAnswerSheet(qnId: questionId, ansId: aqcAns.id);
				ansS.save() 																																							//adding qnId/ansId in the AnsSheet table
			}

			def qnPaperId = this.parseQnPaperFromSession(session) 																										// get question paper id
			def maxSeqValue = AvlQnPaper.executeQuery("select  max(d.qnSequence) from AvlQnPaper d");
			AvlQnPaper qnPaper = new AvlQnPaper(contentId: session.selectedContentId , qnPaperId: qnPaperId, qnId: parentId, qnType: myConstants.matchFollowing, qnSequence: maxSeqValue[0]? ((maxSeqValue[0] as int)+1) : 1 )
			qnPaper.save() 																																							// add the qnId/qnPaper record in QnPaper table
			status = true
		}
		catch (Exception e){
			e.printStackTrace()
		} finally{
			return status
		}
	}

	/**
	 * Returns all Match The Following questions for a question paper
	 * return format:
	 * finalList =  [ QnId: [
	 * 			 Q:question , 
	 * 			 H:hint,
	 * 			 option: [[text:option1, id:QnId], .... , [text:optionN, id:QnId]],
	 *		 	 answer: [[text:answer1, id:QnId], .... , [text:answerN, id:QnId]]
	 * 	   		 ],
	 * 			 [],...,[]
	 *  	         ]
	 *  
	 */
	Map rMatchQnAns(session){
		Map res = [:]
		Map res2 = [:]
		Map tra = [Q:"option", A:"answer", H: "hint"]
		Map tra2 = [Q: "qnId", A: "id"]
		try {
			def qns = AvlQnPaper.findAllByContentIdAndQnType(session.selectedContentId as int,  myConstants.matchFollowing, [ sort:"qnSequence"])	// get all questions from QnPaper table
			def qr = qns.size() > 0 ? AvlQnContent.withCriteria{ 'in' ('parentQnId' , qns.qnId)} : ""												// get all question records based on question ids from previous query

			qr?.each(){
				if (res.(it.parentQnId) == null ) {
					res.(it.parentQnId) = [:]
					res.(it.parentQnId).(tra.Q) = []
					res.(it.parentQnId).(tra.A) = []
				}

				if(it.parentQnId != it.qnId){
					res.(it.parentQnId).(tra.(it.type)).add([text:it.text, id: it.(tra2.(it.type))])
				}else{
					res.(it.parentQnId).(it.type) = (it.text)
				}
			}
			// reordering res according to qnSequence order
			qns.qnId.each(){
				res2.("$it") = res."$it"
			}
		}catch (Exception e ) {
			//e.printStackTrace()
		}
		return res2
	}

	/**
	 * Match the following, evaluate answers for all matches
	 * @param qId : list of question ids
	 * @param val : list of answer ids
	 * @return: true if the qId & val (answer id) pairs match, else return false
	 */
	List eMatchQnAns(Map qaPair,Map ansIdPair,session,params){
		def qnContentType = myConstants.answer,avlQuizLogQpSessionInstance=null, ansCorrect, ansExpectedText, ansSelectedText, qnPaperId, duration
		String search="",idList=""
		List wrongAnsList = [], qId = []

		QuizMasterService qms = new QuizMasterService()
		LoginService ls = new LoginService()

		qnPaperId=qms.getQuestionPaperId( session.selectedContentId as int, null)
		duration=ls.getTimeDuration(session.quizStartTime,new Date())

		if(((params.enterValue.equals("1")) && (session.user)))
			avlQuizLogQpSessionInstance=qms.saveQuizLog(session.user.id,qnPaperId,duration,session.sessionid,myConstants.matchFollowing)
		else
			avlQuizLogQpSessionInstance= AvlQuizLogQpSession.find("from AvlQuizLogQpSession where questionPaperId='"+qnPaperId+"' and userId='"+session.user.id+"' and sessionId='"+session.sessionid+"' and quizType = '"+myConstants.matchFollowing+"' order by id desc")

		qId = qaPair.keySet() as List
		List qnAnsSet = (AvlQnContent.withCriteria{
			and{
				'in' ('qnId' , qId)
				'in' ('type', qnContentType)
			}
		}).asList()

		qnAnsSet.sort{k -> k.id}
		qnAnsSet.size().times {
			def qaSet = qnAnsSet[it]
			def qnId=qaSet.qnId
			if(qaPair.keySet().contains(qnId)){
				ansCorrect='Y'
				ansExpectedText = qaSet.text
				if(qaPair.get(qnId) != qaSet.id){
					wrongAnsList.add(qnId)
					ansCorrect='N'
				}
				ansSelectedText = (ansIdPair.get(qnId)!=null)?ansIdPair.get(qnId):"not selected"
				if(avlQuizLogQpSessionInstance)
					qms.saveQuizLogDetails(qnId,params.hntUsed,ansExpectedText,ansSelectedText,ansCorrect,avlQuizLogQpSessionInstance.id,myConstants.matchFollowing)
			}
		}

		if(params.submitFlag=="1")
			qms.addLogAttemptNoAndQuizType(avlQuizLogQpSessionInstance.id, myConstants.matchFollowing,session.user.id,session.sessionid)

		return  wrongAnsList
	}
	/**
	 * Deletes all question related information from the tables
	 * @param qId
	 */
	void delQns(List qIds) {
		[
			AvlQnPaperQnRelation,
			AvlQnHints,
			AvlMchoiceAns,
			AvlQnContent,
			AvlQnPaper,
			AvlAnswerSheet
		].each{
			it.executeUpdate("delete " + it.toString().replaceAll("class ","") + " where qnId in ( "+ qIds.join(",") +" )")
		}
	}

	long cSubSectionHead(GrailsParameterMap params, GrailsHttpSession session) {
		def seq
		try{
			seq = utilService.getSequence(myConstants.seqName)
			AvlQnContent qn = new AvlQnContent(type: params.type.toString(), text: params.text.toString(), qnId: seq)
			qn.save()
			def qnPaperId = this.parseQnPaperFromSession(session)
			AvlQnPaper qnPaper = new AvlQnPaper(contentId: session.selectedContentId , qnPaperId: qnPaperId, qnId: seq, qnType: myConstants.subSection, qnSequence: 0)
			qnPaper.save()

		}catch (Exception e){
			e.printStackTrace()
		}
		return seq as long
	}

	Map rSubSectionHead(session){
		Map res = [:]
		try {

			def qns = AvlQnPaper.findAllByContentIdAndQnType(session.selectedContentId as int, myConstants.subSection)

			def qnRecords = qns.size() > 0 ? AvlQnContent.withCriteria{ 'in' ('qnId' , qns.qnId)} :  null
			qnRecords?.each(){
				if(res.(it.type) == null )
					res.(it.type) = ["qId":(it.qnId),"text":(it.text)]
			}

		}catch (Exception e ) {
			e.printStackTrace()
		}
		return res
	}

	void dSubSectionHead(long qId) {
		[AvlQnContent, AvlQnPaper].each{
			it.executeUpdate("delete " + it.toString().replaceAll("class ","") + " where qnId = "+ qId)
		}
	}
}
