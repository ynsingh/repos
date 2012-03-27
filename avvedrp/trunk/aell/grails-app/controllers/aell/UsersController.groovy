
package aell
import org.codehaus.groovy.grails.commons.ApplicationHolder as AH
class UsersController {
	def ContentHomeService
	def UsersService
	def RegisterService
	def SubjectMasterService
	
	def MymailService
	def init() {
		if(!session.university) {
			if(session.user.id)
			{
			//Store university info
			session.university = ContentHomeService.universityMasterInfo(session.user.universityId);
			}
			
		}
    }
    def index = {
		def universityInstance=ContentHomeService.universityMasterInfo(session.university.id)
		if(universityInstance)
		{
			def universityList=RegisterService.getUniversityList()
			
			//If the sectionId is passed in params, fired by onChange of the section list
			if(params.universityId != null)
				session.selectedUniversityId = params.universityId
			// if the page is accessed directly, session.selectedUniversityId will be null and set to the first element in the list
			//Get the university id from params or the first one in the list (if the page is accessed directly)
			def universityId = (session.selectedUniversityId != null && session.selectedUniversityId != "")? session.selectedUniversityId :universityList[0].id
			//Store the universityId in the session (this will be the first one in the list)
			if(session.selectedUniversityId == null)
				session.selectedUniversityId = universityId;
			//get the user master info based on universityId
				def userInstnaceList =  UsersService.getUserInformationByUnvId(session.selectedUniversityId as int)
				def roleList=RegisterService.getCompleteRoleList()
				if(userInstnaceList.size>0)
				   [userInstnaceList : userInstnaceList, universityInstance : universityInstance,roleList:roleList,universityList:universityList]
				else
				{
					flash.error="No users to list"
				   [userInstnaceList : userInstnaceList, universityInstance : universityInstance,roleList:roleList,universityList:universityList]
				}
		}
		
    }
	def editUserProfile={
		def hostname=request.getScheme() + "://" + request.getServerName() +":"+ request.getServerPort() + request.getContextPath()
		def userMasterInstance=AvlUserMaster.get(params.id as int)
		def roleId=UsersService.getRoleIdByUserId(params.id as int)
		def roleList=RegisterService.getCompleteRoleList()
		def roleListUser=RegisterService.getRoleList()
		def userDetailsInstance=UsersService.getUserDetailsByUserId(params.id as long)
		def universityList=RegisterService.getUniversityList()
		// this is the edit profile of normal user
         if(params.user=="1")
		   render view:"/register/editUserProfile",model:[userMasterInstance:userMasterInstance,userDetailsInstance:userDetailsInstance,roleListUser:roleListUser,universityList:universityList,roleId:roleId,hostname:hostname]
		else
		 render (template:"editUserProfile",model : [userMasterInstance:userMasterInstance,userDetailsInstance:userDetailsInstance,roleList:roleList,universityList:universityList,roleId:roleId])
	}
	def saveUser={
		def user
		if(params.user)
		  user= params.user
		def checkUpdateInstance=UsersService.updateUser(params)
		if(checkUpdateInstance){
			flash.message="Successfully Updated Profile"
			if(params.userStatus=="Deactive")
			  redirect(controller:'logout',action:'index')
			  else
			  {
			     if(params.user)
			          // redirect(action:editUserProfile,params:[user:user,id:params.userId])
			          redirect(controller:'home',action:'index')
			     else
			          redirect(action:index)
			  }
		}
		else
		{
			flash.error="Failed to update profile"
			if(params.user)
			   redirect(action:editUserProfile,params:[user:user,id:params.userId])
		    else
			   redirect(action:index)
		}
	}
	def adminUserRegistration={
		def universityInstance=ContentHomeService.universityMasterInfo(session.university.id)
		def roleList=RegisterService.getCompleteRoleList()
		def universityList=RegisterService.getUniversityList()
		render( view:"register" , model: [roleList:roleList,universityList:universityList])
	}
	
	def saveAdminUser={
		try{
			RegisterService.registerNewUser(params)
			flash.message="New User Created"

			//MymailService.sendMessage("sruthikb@amritapuri.amrita.edu","test","testemail")
			if(grailsApplication.config.mail_send_flag != 0)
			{
			//println "here1"
			String host = grailsApplication.config.mail_host;
			String username = grailsApplication.config.mail_authusername // your authsmtp username
			String password = grailsApplication.config.mail_authpassword // your authsmtp password
			String from = grailsApplication.config.mail_from;
			String port = grailsApplication.config.mail_port
			def mail_tosend = grailsApplication.config.mail_tosend
			def mail_subject = grailsApplication.config.mail_subject
			def mail_content = grailsApplication.config.mail_content
			def mail_userSubject = grailsApplication.config.mail_userSubject
			def mail_userContent = grailsApplication.config.mail_userContent
			MymailService.sendMessage(mail_tosend,mail_subject,mail_content,host,username,password,from,port,mail_userSubject,mail_userContent,params)
			}

			redirect(action:"adminUserRegistration")
			}catch(AvlServiceException ase){
				flash.error = ase.message
				redirect(action:"adminUserRegistration")
			}
	}
	def managePrivilege={
		if(params.submit)
		{
		   if(params.roleId && params.UserId ){
			 UsersService.assignPrivilege(params.universityId as int,params.roleId as int,params.UserId as int,params.userCheckBox)
			 flash.message="Privilege Created"
		   }
		}
		def universityInstance=ContentHomeService.universityMasterInfo(session.university.id)
		if(params.roleListId!=null)
		{
			session.selectedRoleId=params.roleListId
			session.selectedUserId=null
		}
		def roleList=RegisterService.getCompleteRoleList()
		// if the page is accessed directly, session.selectedRoleId will be null and set to the first element in the list
		//Get the role id from params or the first one in the list (if the page is accessed directly)
		def roleId = (session.selectedRoleId != null && session.selectedRoleId != "")? session.selectedRoleId : roleList[0].id
		//Store the roleId in the session (this will be the first one in the list)
		if(session.selectedRoleId == null)
			session.selectedRoleId = roleId;
		//Get the list of users based on the role
		def usersList =  UsersService.getUsersByRoleIdAndUniversityId(session.selectedRoleId as long,session.university.id as long)
		if(params.userId!=null)
		{
			session.selectedUserId=params.userId
		}
		// if the page is accessed directly, session.selectedUserId will be null and set to the first element in the list
		//Get the user id from params or the first one in the list (if the page is accessed directly)
		if(usersList.size()>0){
		    def userId = (session.selectedUserId != null && session.selectedUserId != "")? session.selectedUserId : usersList[0].uid
		//Store the userId in the session (this will be the first one in the list)
		if(session.selectedUserId == null)
			session.selectedUserId = userId
		}
		//Get all the subjects
		def subjectList =  SubjectMasterService.getSubjectMasterByUnvIdAndUserPrivilege(session.university.id,session.selectedUserId as int)
 		[universityInstance:universityInstance,roleList:roleList,usersList:usersList,subjectList:subjectList]
	}
//  change password action
	
   def changePassword = {
	     def hostname=request.getScheme() + "://" + request.getServerName() +":"+ request.getServerPort() + request.getContextPath()
		 def avlUserMasterInstance=UsersService.getUserInformationById(session.user.id as int)
		 if(params.user=="1")
		    render view:"/register/changePassword",model:[avlUserMasterInstance:avlUserMasterInstance,hostname:hostname]
		 else
		    return [avlUserMasterInstance:avlUserMasterInstance]
	   
   }
   
   //update password action.
   
  def updatePassword={ def hostname=request.getScheme() + "://" + request.getServerName() +":"+ request.getServerPort() + request.getContextPath()
	 def user
	 if(params.user)
	   user= params.user
	 def avlUserMasterInstance=UsersService.getUserInformationById(params.id as int)
	  def ctx = AH.application.mainContext
	  def springSecurityService=ctx.springSecurityService
	  if (!avlUserMasterInstance) {
		  flash.error = "User Not Found With id $params.id"
		  redirect action: changePassword,params:[user:user]
		  return
	  }
	  // Check whether entered old password is correct or not.
	  def oldPasswd = springSecurityService.encodePassword(params.oldPassword)
	  if(!oldPasswd.equals(avlUserMasterInstance.password)){
		  flash.error = "Old Password Not Correct"
		  redirect action: changePassword,params:[user:user]
	  }
	  else{
		  def newpassword_encoded = springSecurityService.encodePassword(params.newPassword)
		  if (!newpassword_encoded.equals(avlUserMasterInstance.password)) {
			  def avlUserMasterInstanceCheck = UsersService.updatePassword(newpassword_encoded,avlUserMasterInstance)
			  if(avlUserMasterInstanceCheck){
				  flash.message = "Password Changed"
				  if(params.user=="1")
				     redirect(controller:'home',action:'index')
			      else
				    redirect action: changePassword,params:[user:user]
			  }
			  else{
				  flash.error = "Password Not Changed"
				  render view: 'changePassword', params: [avlUserMasterInstance: avlUserMasterInstance]
			  }
		  }
		  else
		  {
				  flash.error= "Oldpassword And New Password Are Same"
				  redirect action: changePassword,params:[user:user]
		  }
	  }
	   
	       render(view: "changePassword", model: [avlUserMasterInstance: avlUserMasterInstance])
  }
	
}
