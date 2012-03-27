package aell
import java.util.List
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap


class ExperimentMasterService {

    boolean transactional = true
	
    List getExperimentsByTopicId(Integer topicId) {
		def results = AvlExperimentMaster.executeQuery("SELECT new map(a.id as id, a.experimentName as experimentName, a.experimentDescription as experimentDescription, b.experimentTypeName as experimentTypeName) "+
			" from AvlExperimentMaster a , AvlExperimentTypeMaster b , AvlExperimentSubtopicRel c , AvlSubTopicMaster d"+
			" where a.avlExperimentTypeMaster.id = b.id AND " +  
			" a.id = c.experiment.id AND " +
			" c.sub_topic.id = d.id AND " +
			" a.experimentStatus = 'A' AND " +
			" d.avlTopicMaster.id = ? ORDER BY c.experimentSequence",[topicId])
		return results
    }
	
	List getAllExperimentType(){
		def results= AvlExperimentTypeMaster.findAll()
		return results
	}
	
	
	void updateExperiment(AvlExperimentMaster avlExperimentMaster, Integer experimentType){		
		def avlExperimentTypeMaster =  AvlExperimentTypeMaster.findById(experimentType)
		avlExperimentMaster.avlExperimentTypeMaster = avlExperimentTypeMaster
		avlExperimentMaster.save()
	}
	
	void createExperimentMaster(AvlExperimentMaster avlExperimentMaster, Integer experimentType, Integer topicid)  throws AvlServiceException {
		List<AvlSubTopicMaster> result = AvlSubTopicMaster.findAll("from AvlSubTopicMaster where  avlTopicMaster.id=? and subTopicStatus='A'",[topicid] )
		AvlSubTopicMaster subtopicmaster = null
		if(result !=null && result.size > 0){
			subtopicmaster =result.get(0)
		}
		List avlexperiment = AvlExperimentMaster.findAll("from AvlExperimentMaster aem, AvlExperimentSubtopicRel aestr  where aem.experimentStatus='A' and aem.experimentName =? and aestr.sub_topic.id = ? and aem.id = aestr.experiment.id",[avlExperimentMaster.experimentName,subtopicmaster.id])
		if(avlexperiment !=null && avlexperiment.size()>0){
			AvlServiceException avlexc = new AvlServiceException()
			avlexc.message = "Expertiment Name Already Exist"
			throw avlexc
		}
		avlExperimentMaster.experimentStatus = 'A'
		AvlExperimentTypeMaster avlExperimentType= AvlExperimentTypeMaster.get(experimentType)
		avlExperimentMaster.avlExperimentTypeMaster = avlExperimentType
		def expMasterResult = avlExperimentMaster.save()
		createExperimentSubtopicRel(avlExperimentMaster,topicid,subtopicmaster)
		createContentDetails(avlExperimentMaster)
	}
	
	void createExperimentSubtopicRel(AvlExperimentMaster avlExperimentMaster, Integer topicid,AvlSubTopicMaster subtopicmaster){
		AvlExperimentSubtopicRel avlExperimentSubtopicRel = new AvlExperimentSubtopicRel()
		avlExperimentSubtopicRel.experiment = avlExperimentMaster
		avlExperimentSubtopicRel.sub_topic =subtopicmaster
		avlExperimentSubtopicRel.save()
/*		if(!avlExperimentSubtopicRel.save(flush:true)){
			println("errors: ${avlExperimentSubtopicRel.errors}");
			println("save familyLawFinancial errors: ${errors}");
		}
*/	}
	
	List getContentTypeMaster() {
		def results = AvlContentTypeMaster.findAll("from AvlContentTypeMaster where typeMandatory = 'Y' order by id asc")
		return results
	}
	
	void createContentDetails(AvlExperimentMaster avlExperimentMaster){
		List results = getContentTypeMaster()
		if(results != null){
			int i=0
			try{
			for (AvlContentTypeMaster result:results) {
				AvlContentDetails avlContentDetails = new AvlContentDetails()
				avlContentDetails.content_type = result
				avlContentDetails.experiment = avlExperimentMaster				
				avlContentDetails.contentTypeSequence = new Integer(i+1)
				avlContentDetails.contentStatus ='A'
				avlContentDetails.versionId = null
				avlContentDetails.authenticationNeeded ='N'
				avlContentDetails.save()
				++i
			}
			}catch(Exception e){
			e.printStackTrace()
			}
		}
	}
	
	
	
	/*
	 * Added the following for Edit Experiment functionality
	 * 
	 */
	
	List getContentDetailsForContent(Integer contentId)
	{
		def results = AvlContentDetails.executeQuery("SELECT new map (a.id as contentId, a.content_type.id as contentTypeId, a.authenticationNeeded as authenticationNeeded, a.versionId as publishedVersionId, " +
			" c.typeMandatory as mandatory, c.contentTypeName as contentTypeName, c.contentTypeIcon as contentTypeIcon) " +
			" FROM  AvlContentDetails a , AvlContentTypeMaster c " +
			" WHERE a.id = ? AND a.contentStatus in ('A','E') " +
			" AND c.id = a.content_type.id "+
			" ORDER BY a.contentTypeSequence",[contentId],[max:1]);
		//AvlContentDetails.findAll("from AvlContentDetails acd where acd.id = ? AND contentStatus = 'A' order by contentTypeSequence ASC",[contentId],[max:1]);
		
		def versionDetailResults = AvlContentDescriptionVersion.executeQuery("SELECT new map (" +
			" b.id as versionId , b.contentDescription as contentDescription, b.versionStatus as versionStatus, b.dateTime as versionDateTime, b.contentMode as contentMode, " +
			" d.username as userName) " +
			" FROM  AvlContentDescriptionVersion b , AvlUserMaster d " +
			" WHERE b.content.id = ? " +
			" AND b.userId = d.id ORDER BY b.id DESC ",[contentId],[max:1]);
		
		if(versionDetailResults != null && versionDetailResults.size() > 0)
		{
			results[0] += versionDetailResults[0];
			def versionCommentsResults = AvlVersionComments.findByVersionId(results[0].versionId);
			if(versionCommentsResults != null)
				results[0] += [versionComments:versionCommentsResults.comments]
		}
		
		return results;
	}
	
	
	List getVersionDetails(Integer contentId)
	{
		def results = AvlContentDescriptionVersion.executeQuery("SELECT new map (a.id as versionId, a.versionStatus as versionStatus, a.dateTime as versionDateTime, a.content.id as contentId, b.username as userName)" + 
					" FROM AvlContentDescriptionVersion a, AvlUserMaster b" +
					" WHERE a.content.id=? " +
					" AND a.userId = b.id " +
					" ORDER BY a.id DESC",[contentId])
				return results
	}
	
	List getContentTabDetails(Integer experimentId)
	{
		def results = AvlContentDetails.findAll("from AvlContentDetails where experiment.id=? and contentStatus in ('A','E') order by contentTypeSequence",[experimentId])
		return results
	}
	
	List getContentDetailsForContentAndVersion(Integer contentId,Integer versionId)
	{
		def results = AvlContentDetails.executeQuery("SELECT new map (a.id as contentId, a.content_type.id as contentTypeId, a.authenticationNeeded as authenticationNeeded, a.versionId as publishedVersionId, " +
			" b.id as versionId , b.contentDescription as contentDescription, b.versionStatus as versionStatus, b.dateTime as versionDateTime, b.contentMode as contentMode, " +
			" c.typeMandatory as mandatory, c.contentTypeName as contentTypeName, c.contentTypeIcon as contentTypeIcon, d.username as userName) " +
			" FROM  AvlContentDetails a , AvlContentDescriptionVersion b , AvlContentTypeMaster c , AvlUserMaster d " +
			" WHERE a.id = ? AND b.id = ? AND a.contentStatus='A' " +
			" AND b.content.id = a.id " +
			" AND c.id = a.content_type.id "+
			" AND b.userId = d.id ORDER BY a.contentTypeSequence",[contentId,versionId],[max:1])

		if(results != null && results.size() > 0)
		{
			def versionCommentsResults = AvlVersionComments.findByVersionId(results[0].versionId)
			if(versionCommentsResults != null)
				results[0] += [versionComments:versionCommentsResults.comments]
		}

		return results;
	}
	
	void editTabName(Integer experimentId, Integer contentId, String tabName) throws AvlServiceException{
		if(tabExists(experimentId,tabName)){
			AvlServiceException avlexc = new AvlServiceException()
			avlexc.message = "Tab Name Already Exists: "+tabName
			throw avlexc
		}
		AvlContentDetails contentDetails = AvlContentDetails.get(contentId)
		if(contentDetails != null){
			AvlContentTypeMaster contentTypeMaster = AvlContentTypeMaster.get(contentDetails.content_type.id)
			contentTypeMaster.contentTypeName = tabName
			contentTypeMaster.save()
			
		}
		
	}

	void editTabIcon(Integer contentId, String iconName){
		AvlContentDetails contentDetails = AvlContentDetails.get(contentId)
		if(contentDetails != null){
			AvlContentTypeMaster contentTypeMaster = AvlContentTypeMaster.get(contentDetails.content_type.id)
			contentTypeMaster.contentTypeIcon = iconName
			contentTypeMaster.save()
			
		}
		
	}
	
	void publishTab(int selectedContentId){
		AvlContentDetails acd = AvlContentDetails.get(selectedContentId)
		if (acd){
			acd.contentStatus = 'A'
			acd.save()
		}
	}
	void unPublishTab(int selectedContentId) {
		AvlContentDetails acd = AvlContentDetails.get(selectedContentId)
		if (acd){
			acd.contentStatus = 'E'
			acd.save()
		}
	}
	
	void deleteContentDetails(Integer contentId){
		AvlContentDetails contentDetails = AvlContentDetails.get(contentId)
		if(contentDetails != null){
			contentDetails.contentStatus = 'D'
			contentDetails.save()
		}
		
	}
	
	void addTab(Integer experimentId, String tabName, String tabIcon) throws AvlServiceException
	{
		if(tabExists(experimentId,tabName)){
			AvlServiceException avlexc = new AvlServiceException()
			avlexc.message = "Tab Name Already Exists:"+tabName
			throw avlexc
		}
		def avlContentTypeMaster = addContentType(tabName,tabIcon)
		addContentDetails(experimentId,avlContentTypeMaster)
	}
	
	private boolean tabExists(Integer experimentId, String tabName)
	{
		List avlContentDetails = AvlContentDetails.findAll("FROM AvlContentDetails a WHERE a.experiment.id = ? AND a.content_type.contentTypeName=? and a.contentStatus = 'A'",[experimentId,tabName])
		return (avlContentDetails !=null && avlContentDetails.size()>0)
	}
	
	private AvlContentTypeMaster addContentType(String tabName, String tabIcon)
	{
		def avlContentTypeMaster = new AvlContentTypeMaster()
		avlContentTypeMaster.contentTypeName = tabName
		avlContentTypeMaster.contentTypeIcon = tabIcon
		avlContentTypeMaster.typeMandatory = 'N'
		avlContentTypeMaster.save()
		return avlContentTypeMaster
	}
	
	private AvlContentDetails addContentDetails(Integer experimentId, AvlContentTypeMaster avlContentTypeMaster)
	{
		def avlContentDetails = new AvlContentDetails()
		avlContentDetails.content_type = avlContentTypeMaster
		avlContentDetails.experiment = AvlExperimentMaster.get(experimentId)
		avlContentDetails.contentStatus = 'E'
		avlContentDetails.authenticationNeeded = 'N'
		avlContentDetails.versionId = null
		def avlContentDetailsMaxSequence = getContentDetailWithMaxSequence(experimentId)
		if(avlContentDetailsMaxSequence != null)
			avlContentDetails.contentTypeSequence = avlContentDetailsMaxSequence.contentTypeSequence + 1
		else
		avlContentDetails.contentTypeSequence = 1
		avlContentDetails.save()
		return avlContentDetails
	}
	
	private AvlContentDetails getContentDetailWithMaxSequence(Integer experimentId)
	{
		def avlContentDetailsMaxSequence = AvlContentDetails.findAll("from AvlContentDetails where experiment.id = ? AND contentStatus = 'A' order by contentTypeSequence DESC",[experimentId],[max:1])
		if(avlContentDetailsMaxSequence != null && avlContentDetailsMaxSequence.size() > 0)
			return avlContentDetailsMaxSequence[0]
		else
			return null;	
	}
	
	AvlContentDetails saveContent(AvlContentDescriptionVersion contentDescriptionVersion, Integer contentId, String versionComments) {
		AvlContentDetails contentDetails = AvlContentDetails.get(contentId)
		if(contentDetails != null){
			//println "contentDetails____________________="+contentDetails
			contentDescriptionVersion.content = contentDetails
			contentDescriptionVersion.save()
			saveContentDescriptionVersion(contentDescriptionVersion,versionComments)
			//For publish content
			if(contentDescriptionVersion.versionStatus == 'P'){
				contentDetails.versionId =contentDescriptionVersion.id
				contentDetails.save()
			} 
		}
		else
		{
			//println "hi_______________"
		}
		return contentDetails;				
	} 
	
	void saveContentDescriptionVersion(AvlContentDescriptionVersion contentDescriptionVersion,String comments) {
		AvlVersionComments versionComments = new AvlVersionComments()
			versionComments.versionId = contentDescriptionVersion.id
			versionComments.comments = comments
			//println "versionComments="+versionComments.id+"comments="+versionComments.comments+"versionId="+versionComments.versionId
			versionComments.save()
	}
	
	void updateSequence(String sequenceOrder)
	{
		String[] ids = sequenceOrder.split(",")
		for(int i=0;i<ids.length;i++)
		{
			def experimentSubtopicRelList = AvlExperimentSubtopicRel.findAll("from AvlExperimentSubtopicRel where experiment.id = ?",[ids[i] as int])
			if(experimentSubtopicRelList != null && experimentSubtopicRelList.size() > 0)
			{
				experimentSubtopicRelList[0].experimentSequence = i+1
				experimentSubtopicRelList[0].save()
			}
		}
	}

	void updateTabSequence(String sequenceOrder)
	{
		String[] ids = sequenceOrder.split("&")
		for(int i=0;i<ids.length;i++)
		{
			def contentDetail = AvlContentDetails.findById(ids[i].split("=")[1] as int)
			if(contentDetail != null)
			{
				contentDetail.contentTypeSequence = i+1
				contentDetail.save()
			}
		}
	}
	List getTabCount(Integer contentId)
	{
			AvlContentDetails contentDetails = AvlContentDetails.get(contentId)
			if(contentDetails != null){
				def contentDetailsList = AvlContentDetails.findAll("from AvlContentDetails where contentStatus='A' and experiment='"+contentDetails.experiment.id+"'")
				return contentDetailsList
			}
			
		
	}
	def getContentTypeModeUsingSelectedContentId(selectedContentId){
		def versionDetailResults = AvlContentDescriptionVersion.findAll("from AvlContentDescriptionVersion b  where  b.content.id = ?  ORDER BY b.id DESC ",[selectedContentId]);
		if(versionDetailResults != null && versionDetailResults.size() > 0)
			return versionDetailResults[0].contentMode
	}
	def getContentTypeModeUsingSelectedExperimentId(selectedExpId){
		def selectedContentMode
		def selectedExpIdInstance= AvlContentDetails.findAll("from AvlContentDetails a where a.experiment.id=? and contentStatus='A' ORDER BY a.contentTypeSequence",[selectedExpId as int]);
        if(selectedExpIdInstance != null && selectedExpIdInstance.size() > 0)
			selectedContentMode=getContentTypeModeUsingSelectedContentId(selectedExpIdInstance[0].id)
		return selectedContentMode
	}

}
