package aell

import java.util.List;

class ReportService
{
    boolean transactional = true

    public List getUniversityList()
	{
		def universityInstance=AvlUniversityMaster.getAll()
		return universityInstance
	}
	
	public List getRoleList()
    {	
		def roleMasterInstanceList=AvlRoleMaster.findAllByStatus('A')
		return roleMasterInstanceList
    }

    public List getStatusList()
	{
		def statusInstance=AvlUserMaster.getAll()
		def userStatusList=statusInstance.userStatus.unique()
		return userStatusList
	}	
	
	public List getUserStatusDetails(unv,stat)
	{
		if(stat=='Active')
		{
			//def userInstanceList=AvlUserMaster.findAll("from AvlUserMaster as AVM where AVM.universityId='"+unv+"' and AVM.userStatus='A'")
			def userInstanceList=AvlUserMaster.executeQuery("Select new map(a.username as username,a.emailId as email,b.authority as role) from AvlUserMaster a,AvlRoleMaster b,AvlRoleUserRel c where a.universityId='"+unv+"' and a.userStatus='A' and a.id=c.user.id and b.id=c.role.id")
			return userInstanceList
		}
		if(stat=='Deactive')
		{
			//def userInstanceList=AvlUserMaster.findAll("from AvlUserMaster as AVM where AVM.universityId='"+unv+"' and AVM.userStatus='D'")
			def userInstanceList=AvlUserMaster.executeQuery("Select new map(a.username as username,a.emailId as email,b.authority as role) from AvlUserMaster a,AvlRoleMaster b,AvlRoleUserRel c where a.universityId='"+unv+"' and a.userStatus='D' and a.id=c.user.id and b.id=c.role.id")
			return userInstanceList
		}
	}
    
	public List getUserLoginDetails()
	{
		def logInstanceList = AvlUserMaster.executeQuery("SELECT new map(a.id as id,a.username as username,c.authority as role,a.emailId as email,date_format(b.loginTime,'%d-%m-%Y %H:%i:%s')as logintime) from AvlUserMaster a,AvlUserLoginDetails b,AvlRoleMaster c,AvlRoleUserRel d where a.id = b.userId AND b.logoutStatus='E' and a.id=d.user.id and c.id=d.role.id")
		return logInstanceList
	}
	
	public Map getUserTabDetails(userid)
	{
		/*def userlogInstanceList=AvlLogDetails.executeQuery("SELECT new map(a.loginTime as logintime,b.experimentId as subtopic,b.contentTypeId as content) from AvlUserLoginDetails a,AvlLogDetails b where a.userId='"+userid+"' and a.userId=b.userId and a.sessionCount=b.sessionCount")
		return userlogInstanceList*/
		//def usertabInstanceList=AvlLogDetails.executeQuery("SELECT new map(a.sessionCount as session,date_format(a.loginTime,'%d-%m-%Y %H:%i:%s') as logintime,SEC_TO_TIME(b.duration) as timespent,c.subjectName as subject,d.subTopicName as topic,e.experimentName as experiment,f.contentTypeName as content) from AvlUserLoginDetails a,AvlLogDetails b,AvlSubjectMaster c,AvlSubTopicMaster d,AvlExperimentMaster e,AvlContentTypeMaster f where a.userId='"+userid+"' and a.userId=b.userId and a.sessionCount=b.sessionCount and b.status='E' and b.subjectId=c.id and b.topicId=d.id and b.experimentId=e.id and b.contentTypeId=f.id")
		def usertabInstanceList=AvlLogDetails.executeQuery("SELECT new map(a.sessionCount as session,date_format(a.loginTime,'%d-%m-%Y %H:%i:%s') as logintime,concat(floor((sum(b.duration))/3600),':', floor(mod((sum(b.duration)),3600)/60),':',ceil(mod(mod((sum(b.duration)),3600),60))) as timespent,count(b.contentTypeId)as nooftimesaccessed,c.subjectName as subject,d.subTopicName as topic,e.experimentName as experiment,f.contentTypeName as content) from AvlUserLoginDetails a,AvlLogDetails b,AvlSubjectMaster c,AvlSubTopicMaster d,AvlExperimentMaster e,AvlContentTypeMaster f where a.userId='"+userid+"' and a.userId=b.userId and a.sessionCount=b.sessionCount and b.status='E' and b.subjectId=c.id and b.topicId=d.id and b.experimentId=e.id and b.contentTypeId=f.id group by a.sessionCount,b.subjectId,b.topicId,b.experimentId,b.contentTypeId")
		Map m = [:]
		Map resp = [:]
		usertabInstanceList.size().times{it ->
			m.(it+1)= usertabInstanceList[it]

		}
		int i=0
		def lt = " "
		m.size().times{it ->
			if(("$it")>0)
						lt = m."$it".logintime
			if(!(lt.equals(m.(it+1).logintime))){
					i +=1
				resp."$i"=[:]
				resp."$i"."logintime" =  m.(it+1).logintime
				resp."$i"."session" =  m.(it+1).session
				resp."$i"."details"=[]
				}
				resp."$i"."details".add([content: m.(it+1).content,experiment: m.(it+1).experiment,topic: m.(it+1).topic,subject: m.(it+1).subject,timespent: m.(it+1).timespent,nooftimesaccessed: m.(it+1).nooftimesaccessed])
		}
		return resp
	}
	
	public Map getUserAccessDetails(userid) 
	{
		def useraccessInstanceList=AvlUserMaster.executeQuery("Select new map(b.username as uname,date_format(a.loginTime,'%d-%m-%Y') as logindate,concat(floor((sum(a.loginDuration))/3600),':', floor(mod((sum(a.loginDuration)),3600)/60),':',ceil(mod(mod((sum(a.loginDuration)),3600),60)) ) as timespent,count(a.sessionCount)as noofsessions) from AvlUserLoginDetails a, AvlUserMaster b where a.userId='"+userid+"' and a.userId=b.id and a.logoutStatus='L' group by date(a.loginTime)")
		Map m = [:]
		Map resp = [:]
		useraccessInstanceList.size().times{it ->
			m.(it+1)= useraccessInstanceList[it]

		}
		int i=0
		def lt = " "
		m.size().times{it ->
			if(("$it")>0)
						lt = m."$it".uname
			if(!(lt.equals(m.(it+1).uname))){
					i +=1
				resp."$i"=[:]
				resp."$i"."uname" =  m.(it+1).uname
				resp."$i"."details"=[]
				}
				resp."$i"."details".add([logindate: m.(it+1).logindate,timespent: m.(it+1).timespent,noofsessions: m.(it+1).noofsessions])
		}
		return resp
	}
}

	
	
	
	
	
	
	
	
	
	
	
	

