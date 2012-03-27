package aell

class ReportController
{
    def ReportService
	def ContentHomeService
	def RegisterService
	def UsersService
	def index ={}
	def userStatus=
	{
		def unv=params.universityId
		def stat=params.statusId
		def universityResult=ReportService.getUniversityList()
		def statusResult=ReportService.getStatusList()
		if(params.universityId != null)
			session.selectedUniversityId = params.universityId
		if(params.statusId != null)
			session.selectedstatusId = params.statusId
		def userResult=ReportService.getUserStatusDetails(unv,stat)
		render(view:"/report/userstatusreport",model:[university:universityResult,status:statusResult,user:userResult])
    }
	def loginStatus=
	{
		def user_log_details=ReportService.getUserLoginDetails()
		render(view:"/report/userloginreport",model:[logdetails:user_log_details])
	}
	def tabaccessStatus=
	{
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
		if(usersList.size()>0)
		{
			def userId = (session.selectedUserId != null && session.selectedUserId != "")? session.selectedUserId : usersList[0].uid
		
		//Store the userId in the session (this will be the first one in the list)
		if(session.selectedUserId == null)
			session.selectedUserId = userId
			
		}
		def usertabaccessInstance=ReportService.getUserTabDetails(session.selectedUserId)
		render(view:"/report/useraccessedtabreport",model:[universityInstance:universityInstance,roleList:roleList,usersList:usersList,tabaccessInstance:usertabaccessInstance])
	}
	def useraccessStatus=
	{
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
		if(usersList.size()>0)
		{
			def userId = (session.selectedUserId != null && session.selectedUserId != "")? session.selectedUserId : usersList[0].uid
		
		//Store the userId in the session (this will be the first one in the list)
		if(session.selectedUserId == null)
			session.selectedUserId = userId
			
		}
		def useraccessInstance=ReportService.getUserAccessDetails(session.selectedUserId)
		render(view:"/report/useraccessdetailsreport",model:[universityInstance:universityInstance,roleList:roleList,usersList:usersList,useraccessInstance:useraccessInstance])
	}
}
