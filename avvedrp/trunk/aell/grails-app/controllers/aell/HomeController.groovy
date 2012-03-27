
package aell
import grails.converters.JSON;
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.apache.commons.codec.binary.Base64
import java.lang.Integer
import java.text.*
import java.lang.String
class HomeController {
	def ContentHomeService
	def IndexService
	def ContentService
	def SubjectMasterService
	def LoginService
	def LogService
	//def ExperimentMasterService
	def beforeInterceptor = [action:this.&init,except:[]]
	def QuizMasterService
	
		def init() {
				if(!session.university) {
					def ell_integrate_flag = grailsApplication.config.ell_integrate_flag
					if(ell_integrate_flag!=1)
                   if(session.user.id)
				      {
				       //Store university info
				       session.university = ContentHomeService.universityMasterInfo(session.user.universityId);
				      }	    
			}
		}
				
				
		
    def index = {
		def uname,uid,sesid,roleid,rolename;
		def referal_url = grailsApplication.config.referal_url;
		def ell_integrate_flag = grailsApplication.config.ell_integrate_flag
		def session_parameters = grailsApplication.config.session_parameters;
    	def hostname=request.getScheme() + "://" + request.getServerName() +":"+ request.getServerPort() + request.getContextPath()
		def al_roleid = grailsApplication.config.al_roleid;
	    def subjectList
		// check  ell_integrate_flag
		//if it is 0 then career lab is redirected only through login
		// if it is 1 then career lab is redirected without login authentication and all subjects are listed
		if(session.login_flag =='login'){
	    if(!session.university&&session.user)	
			session.university = ContentHomeService.universityMasterInfo(session.user.universityId)
        subjectList=  SubjectMasterService.getSubjectMasterByUnvIdAndUserId(session.university.id,session.user.id as int)
		}
		else{	
		   def utyId = grailsApplication.config.universityId
		   subjectList=  SubjectMasterService.getSubjectMasterByUnvId(utyId)
		   if(!session.user){
		   //adding user to the usermaster table
		   // for that first check whether the user is already exist in the user master table
		   def url_param_usersess=request.getParameter("ui") ;
		   def url_param_timestamp = request.getParameter("t") ;
		   
			 if(((url_param_usersess != null) && (url_param_timestamp != null)) || ((url_param_usersess != '') && (url_param_timestamp!= null)))
			 {
			 def url_param_timestamp1 = new String(url_param_timestamp.decodeBase64());
			 def url_param_timestamp1_int = Integer.parseInt(url_param_timestamp1);
			 def current_time = System.currentTimeMillis()/1000;
			 def current_time1 = 300 + url_param_timestamp1_int;

			   if(url_param_usersess)
			   {
					if(current_time <= current_time1)
					{
						 //decryption
						 def substring
						 def key = "IMMERSIONKEY"
						 def keychar
						 def keymod
						 StringBuffer sb1 = new StringBuffer();
						 String encodedText;
						 String decodedText;
						 decodedText = new String(Base64.decodeBase64(url_param_usersess.getBytes()));
						 for(int i=0;i<decodedText.size();i++)
						  {
						   substring = decodedText.substring(i,i+1);
						   keymod = i % key.size()                        //i % strlen($key))-1
						   keychar = key.substring(keymod,keymod+1);
						   int k = ((int) keychar)%10;
						   int j = (int) substring;
						   int l = j-k
						   char c = (char)l
						   sb1.append(c);
						  }
						 String usernameRef = sb1.toString();
						// decryption ends here
						def avlUserMasterInstanceRef=IndexService.getUsermasterDetails(usernameRef);
							if(!avlUserMasterInstanceRef)
							  avlUserMasterInstanceRef=IndexService.saveUser(usernameRef,utyId,al_roleid);
							session.Username=usernameRef;
							session.user = avlUserMasterInstanceRef;
							session.sessionid=request.getSession(true).id
							def ipaddress=request.getRemoteAddr()
							def userInstance=LoginService.saveUserDetails(session.Username, session.sessionid,ipaddress)
					}
					else
					{
					  response.sendRedirect(referal_url);
					}
				}
				else
				{
				  response.sendRedirect(referal_url);
				}
			}
			else
			{
			  response.sendRedirect(referal_url);
			}
		}
		}
			
		if(session.user&&session.Username&&session.sessionid)
		{
			uid=session.user.id
			uname=session.Username
			sesid=session.sessionid
		}
		if(uid!=null)
		   rolename=IndexService.getRoleName(uid)
        [session_parameters:session_parameters,
	    referal_url:referal_url,uid:uid,sesid:sesid,rolename:rolename,uname:uname,
	    subjectList:subjectList,hostname:hostname,ell_integrate_flag:ell_integrate_flag]
 }
    
    
	String menuData="";
	String itemAdded="";
	def itemNeedToAdd=[];
	int masterParent=1;
	def sidemenudata={		
	menuData=("<div id=\"sideMenu\"><div id=\"sideMenuHeader\">Topics</div><div id=\"sideMenuContent\"><ul  id=\"browser\"   class=\"filetree\"> ");
	def topicMasterList=ContentService.getTopicList(params.id as int)
	addItems(topicMasterList.size,topicMasterList);
	menuData+=("</ul> </div></div>");
	def result = ["menuData":menuData]
	render result as JSON
	}
		    
	def addItems(size,topicMaster){
	  def sub2=0;
	  def cnt=0;
	  def flag=0;
	  def thirdLevelData=[];
      def lessonurl="home/content"
	  for(int i=0;i<size;i++){
		 masterParent=topicMaster[i].id;
   	     if(size<=0)
		  sub2=0;
   	     def childData=ContentService.getSubTopicList(topicMaster[i].id as int)
		 if(childData.size()>0){
		    if(i==0){
		    	flag=1;
		        menuData+=("<li class=\"open\"><a><span class=\"folder\"><span class=\"mainH\">"+topicMaster[i].topicName+"</span></span></a>")
		    	}
		   else{
		  	    menuData+=("<li class=\"closed\"><a><span class=\"folder\"><span class=\"mainH\">"+topicMaster[i].topicName+"</span></span></a>")
	  			}
		   for(int j=0;j<childData.size;j++){
		       if(childData.size()<=0)
		    	sub2=0;
              if(j==childData.size-1){
		    	menuData+=("<ul class=\"lastUll\">")
		      }
		    else{
		      menuData+=("<ul>")
		    }
		   def avlExperimentSubtopicRelInstance=ContentService.getExperimentSubtopicRel(childData[j].id as int)
		   for(int p=0;p<avlExperimentSubtopicRelInstance.experiment.id.size();p++){
		    def expinstance=ContentService.getExperimentDetails(avlExperimentSubtopicRelInstance[p].experiment.id)
		    if(expinstance)
		     thirdLevelData.add(expinstance);
		   }
		  if(thirdLevelData.size()>0){
		    for(int k=0;k<thirdLevelData.size();k++){
		      if(thirdLevelData.size()>0)
		          sub2=thirdLevelData[k].id
		      else
		          sub2=0;
		      if(k==thirdLevelData.size-1)
		    	 menuData+=("<ul class=\"lastUl\">")
		      else
		    	 menuData+=("<ul>")
		      def avlContentDetailsInstance=ContentService.getContentDetails(sub2 as int)
		      if(avlContentDetailsInstance)
		    	cnt=avlContentDetailsInstance.content_type.id
		      else
		    	cnt=0;
		      menuData+=("<li><a name=\"a\" href=\""+params.host+lessonurl+"?subjectId="+params.id+"&exp="+sub2+"&cnt="+cnt+"\"><span class=\"file\"><span class=\"subH\">");
		      menuData+=( ""+thirdLevelData[k].experimentName+"</span></span></a>") ;
		      menuData+=("</li></ul>")
		 }
		}else{
		   sub2=0;
		   def avlContentDetailsInstance=ContentService.getContentDetails(sub2 as int)
		   if(avlContentDetailsInstance)
		     cnt=avlContentDetailsInstance.content_type.id
		   else
		      cnt=0;
		}
	 menuData+=("</ul>")
	 }
	 thirdLevelData=[];
	 menuData+=("</li>")
	}
   }	  
  }
   def content={
	   def ell_integrate_flag = grailsApplication.config.ell_integrate_flag
	   def referal_url = grailsApplication.config.referal_url;
	   def  aellContentTypeMasterList,contentDetails,contentText,questionContentList
	   def hostname=request.getScheme() + "://" + request.getServerName() +":"+ request.getServerPort() + request.getContextPath()
	   def server_upload_path=grailsApplication.config.upload_path
	   def uid,sesid,uname
	   if(session.user&&session.Username&&session.sessionid)
			{
				uid=session.user.id
				sesid=session.sessionid
				uname=session.Username

			}
			if(params.subjectId!='null')
			{
				session.subjectId=params.subjectId
			}
			if(params.exp!='null')
			{
				session.experimentId=params.exp
			}
			if(params.cnt!='null')
			{
				session.contentId=params.cnt
			}
			//Get the tab details
              if(session.experimentId!='null')
	             aellContentTypeMasterList=ContentService.getContentTypeDetails(session.experimentId as int);
		   
		   //Get the content details for the selected content id
		   if(session.experimentId!='null'&&session.contentId!='null')
		   contentDetails = ContentService.getContentDetailsForContent(session.experimentId as int,session.contentId as int);
		   def fbqn,dndList,ssHead,dndqn
		  if(contentDetails!=null){
		   contentText=contentDetails.contentDescription
		    if(contentDetails.contentMode == 'T')
		      {
			   def imagePath=grailsApplication.config.ImageFolder
			   def audioPath=grailsApplication.config.AudioFolder
			   //contentText=contentText.replaceAll(audioPath, hostname+"/"+"/"+audioPath)
			   //contentText=contentText.replaceAll(imagePath, hostname+"/"+imagePath)
		      }
			  if(contentDetails.contentMode == 'Q')
			  {
				  session.quizStartTime=new Date()
				  session.selectedContentId=contentDetails.content.id
				  questionContentList = QuizMasterService.getAllQuizQuestion(session)
				  session.questionContentList =questionContentList
				  fbqn = QuizMasterService.rFillBlanksQn(session,'FB')
				  dndqn = QuizMasterService.rFillBlanksQn(session,'DND')
				  dndList = QuizMasterService.rMatchQnAns(session)
				  ssHead = QuizMasterService.rSubSectionHead(session)
			  }			  
			  
		   }
		  def heading
		  if(ell_integrate_flag!=1)
		     heading=ContentService.getHeading(session.subjectId ,session.experimentId ,session.university.id )
		  else{
	            def utyId = grailsApplication.config.universityId
				heading=ContentService.getHeading(session.subjectId ,session.experimentId ,utyId )
		  }
		  def breadcum=ContentService.getBreadcum(session.subjectId,session.experimentId)
		  if(params.ajaxStatus == "ok"){
			  render(template:"/common/mChoiceAssessments", model:[questionContentList:questionContentList,contentText:contentText,hostname:hostname,contentDetails:contentDetails])
		  }
		  else{
		  render(view:"/home/content", model:[questionContentList:questionContentList,uname:uname,hostname:hostname,aellContentTypeMasterList:aellContentTypeMasterList,contentText:contentText,
				contentDetails:contentDetails,breadcum:breadcum,heading:heading,fbqn:fbqn,ell_integrate_flag:ell_integrate_flag,referal_url:referal_url,qaList:dndList,ssHead:ssHead])
		  }
    }

   def sessionCheckOnBackButton={
	  def sesid=request.getSession(true).id
	  def sessionStatus=IndexService.getCurrentSessionStatus(sesid)
      def desc = ["sessionStatus":sessionStatus]
      render desc as JSON
   }
  
   
 def titleChangeDynamically={
		  def titleData=ContentService.getTitleDynamically(params.subjectId,params.experimentId ,params.contentId )
		  titleData=titleData+":${message(code: 'default.title.label')}"
		   def desc = ["titleData":titleData]
		     render desc as JSON
      }
     
   def getContentId={
	   Integer contentId=ContentService.getContentID(params.subid )
	   Integer experimentId=ContentService.getExperimentID(params.subid )
	   def desc = ["contentId":contentId,"experimentId":experimentId];
	   render desc as JSON
   }
   
   def quizAction={
		def avlQuizLogQpSessionInstance,ansCorrect,ansSelectedText,ansExpectedText
		def questionList=session.questionContentList
		def qnPaperId=QuizMasterService.getQuestionPaperId( session.selectedContentId as int)
		def quizSubmitTime=new Date()
		Date date1 = session.quizStartTime
		Date date2 = quizSubmitTime
		def duration=LoginService.getTimeDuration(date1,date2)
		//def quizTypeInstance=AvlQnPaper.find("from AvlQnPaper a")
		//def quiztype=quizTypeInstance.qnType
		flash.submitMessage="submit"
		if(session.user)
			avlQuizLogQpSessionInstance=QuizMasterService.saveQuizLog(session.user.id,qnPaperId,duration,session.sessionid,'MC')
		flash.answer=""
		flash.checked=""
		for(i in questionList){
			ansExpectedText=QuizMasterService.getSelectedAnswer(i.correct?.toInteger())
			if(params."myAns${i.qnId }"){
	
				if(i.correct.toInteger()== params."myAns${i.qnId }".toInteger()){
					flash.answer+="_succe${i.qnId }"
					ansCorrect='Y'
				}
				else
				{
					flash.answer+="_error${i.qnId }"
					ansCorrect='N'
				}
				flash.checked+="Q${i.qnId }="+params."myAns${i.qnId }"+"_"
				ansSelectedText=QuizMasterService.getSelectedAnswer(params."myAns${i.qnId }".toInteger())
			}
			else{
				flash.answer+="_error${i.qnId }"
				flash.checked+="Q${i.qnId }=-1_"
				ansCorrect='N'
				ansSelectedText="Not Selected"
			}
			if(avlQuizLogQpSessionInstance)
				QuizMasterService.saveQuizLogDetails(i.qnId,params."hintUsed${i.qnId }",ansExpectedText,ansSelectedText,ansCorrect,avlQuizLogQpSessionInstance.id,'MC')
		}
		QuizMasterService.addLogAttemptNoAndQuizType(avlQuizLogQpSessionInstance.id, 'MC',session.user.id,session.sessionid)
		flash.answer+="_"
		redirect(action:content,params:["subjectId":session.subjectId,"exp":session.experimentId,"cnt":session.contentId,"ajaxStatus":"ok"])
	}
   
   def contabs={
	   //Calling LogService
	   def userLogInstanceResult=LogService.getUserDetails(params,session)
	   
	   def ell_integrate_flag = grailsApplication.config.ell_integrate_flag
	   def referal_url = grailsApplication.config.referal_url;
	   def  aellContentTypeMasterList,contentDetails,contentText,questionContentList
	   def hostname=request.getScheme() + "://" + request.getServerName() +":"+ request.getServerPort() + request.getContextPath()
	   def server_upload_path=grailsApplication.config.upload_path
	   def uid,sesid,uname
	   if(session.user&&session.Username&&session.sessionid)
			{
				uid=session.user.id
				sesid=session.sessionid
				uname=session.Username

			}
			if(params.subjectId!='null')
			{
				session.subjectId=params.subjectId
			}
			if(params.exp!='null')
			{
				session.experimentId=params.exp
			}
			if(params.cnt!='null')
			{
				session.contentId=params.cnt
			}
			//Get the tab details
			  if(session.experimentId!='null')
				 aellContentTypeMasterList=ContentService.getContentTypeDetails(session.experimentId as int);
		   
		   //Get the content details for the selected content id
		   if(session.experimentId!='null'&&session.contentId!='null')
		   contentDetails = ContentService.getContentDetailsForContent(session.experimentId as int,session.contentId as int);
		   def fbqn,dndList,ssHead,dndqn
		  if(contentDetails!=null){
		   contentText=contentDetails.contentDescription
			if(contentDetails.contentMode == 'T')
			  {
			   def imagePath=grailsApplication.config.ImageFolder
			   def audioPath=grailsApplication.config.AudioFolder
			  }
			  if(contentDetails.contentMode == 'Q')
			  {
				  session.quizStartTime=new Date()
				  session.selectedContentId=contentDetails.content.id
				  questionContentList = QuizMasterService.getAllQuizQuestion(session)
				  session.questionContentList =questionContentList
				  fbqn = QuizMasterService.rFillBlanksQn(session,'FB')
				  dndqn = QuizMasterService.rFillBlanksQn(session,'DND')
				  dndList = QuizMasterService.rMatchQnAns(session)
				  ssHead = QuizMasterService.rSubSectionHead(session)
			  }
			  
		   }
		  def heading
		  if(ell_integrate_flag!=1)
			 heading=ContentService.getHeading(session.subjectId ,session.experimentId ,session.university.id )
		  else{
				def utyId = grailsApplication.config.universityId
				heading=ContentService.getHeading(session.subjectId ,session.experimentId ,utyId )
		  }
		  render(view:"/home/qnAssessments", model:[questionContentList:questionContentList,uname:uname,hostname:hostname,aellContentTypeMasterList:aellContentTypeMasterList,contentText:contentText,
			  contentDetails:contentDetails,heading:heading,fbqn:fbqn,ell_integrate_flag:ell_integrate_flag,referal_url:referal_url,qaList:dndList,ssHead:ssHead,dndqn:dndqn])
	}
}