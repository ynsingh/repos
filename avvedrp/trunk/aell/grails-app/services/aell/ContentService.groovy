
package aell

class ContentService {

    boolean transactional = true

   //function to get contentId based on the subjectId
	Integer getContentID(def subid){
	  def arrayExperiment=[];
	  Integer contentid;
	  Integer expid;
	  def avlTopicMasterInstnce=AvlTopicMaster.findAll("from AvlTopicMaster where avlSubjectMaster='"+subid+"' and topicStatus='A'")
	   if(avlTopicMasterInstnce)
	   {
		   def avlSubTopicMasterInstance=AvlSubTopicMaster.findAll("from AvlSubTopicMaster where avlTopicMaster='"+avlTopicMasterInstnce[0].id+"' and subTopicStatus='A'")
		   if(avlSubTopicMasterInstance)
		   {
				   def avlExperimentSubtopicRelInstance=AvlExperimentSubtopicRel.findAll("from AvlExperimentSubtopicRel where sub_topic='"+avlSubTopicMasterInstance[0].id+"'  ")
				   if(avlExperimentSubtopicRelInstance)
				   {
					  for(int j=0;j<avlExperimentSubtopicRelInstance.size();j++)
					  {
				   def avlExperimentMasterInstance=AvlExperimentMaster.find("from AvlExperimentMaster  where  id='"+avlExperimentSubtopicRelInstance[j].experiment.id+"' and experimentStatus='A' order by id");
				   if(avlExperimentMasterInstance)
						{
						  arrayExperiment.add(avlExperimentMasterInstance.id);
						}
					  }//for loop
				   
					 expid=arrayExperiment[0];
				   }// if contentid_subtopic rel
				   
			   
		   }//if  subtopic1
		   
	}
	   def avlContentDetailsInstance=AvlContentDetails.findAll("from AvlContentDetails where experiment='"+expid+"' and contentStatus='A' and versionId!=NULL order by contentTypeSequence")
	   if(avlContentDetailsInstance)
	   {
	   contentid=avlContentDetailsInstance[0].content_type.id
	   }
	   return contentid
    }
	
	//function to get the experimentId based on subjectId
	Integer getExperimentID(def subid){
		def arrayExperiment=[];
		Integer contentid;
		Integer expid;
		def avlTopicMasterInstnce=AvlTopicMaster.findAll("from AvlTopicMaster where avlSubjectMaster='"+subid+"' and topicStatus='A'")
		 if(avlTopicMasterInstnce)
		 {
			 def avlSubTopicMasterInstance=AvlSubTopicMaster.findAll("from AvlSubTopicMaster where avlTopicMaster='"+avlTopicMasterInstnce[0].id+"' and subTopicStatus='A'")
			 if(avlSubTopicMasterInstance)
			 {
					 def avlExperimentSubtopicRelInstance=AvlExperimentSubtopicRel.findAll("from AvlExperimentSubtopicRel where sub_topic='"+avlSubTopicMasterInstance[0].id+"'  ")
					 if(avlExperimentSubtopicRelInstance)
					 {
						for(int j=0;j<avlExperimentSubtopicRelInstance.size();j++)
						{
					 def avlExperimentMasterInstance=AvlExperimentMaster.find("from AvlExperimentMaster  where  id='"+avlExperimentSubtopicRelInstance[j].experiment.id+"' and experimentStatus='A' order by id");
					 if(avlExperimentMasterInstance)
						  {
							arrayExperiment.add(avlExperimentMasterInstance.id);
						  }
						}//for loop
					 
					   expid=arrayExperiment[0];
					 }// if contentid_subtopic rel
					 
				 
			 }//if  subtopic1
			 
	  }
		 
		 return expid
	  }
	
	//function to get the contentType details to display the content tabs
	List  getContentTypeDetails(Integer  experimentId)
	{
		def contentTypeList=[]
		def results = AvlContentDetails.findAll("from AvlContentDetails where experiment.id=?  and versionId!=null and contentStatus='A' order by contentTypeSequence",[experimentId]);
		if(results != null && results.size() > 0)
		{
			for(int i=0;i<results.size();i++)
			{
				def contentTypeInstance= AvlContentTypeMaster.get(results[i].content_type.id)
				contentTypeList.add(contentTypeInstance)
			}
		}
		return contentTypeList
	}
	
	//function to get contentDetails based on contentId and experimentId
	 def getContentDetailsForContent(Integer  experimentId,Integer contentId)
	{
		def contentDescriptionVersionList,contentDetails
		def aellContentDetailsInstnce=AvlContentDetails.find("from AvlContentDetails where   experiment.id=? and content_type.id=? and contentStatus='A' order by contentTypeSequence ",[experimentId,contentId]);
		if(aellContentDetailsInstnce)
        	contentDescriptionVersionList=AvlContentDescriptionVersion.findAll("from AvlContentDescriptionVersion where content='"+aellContentDetailsInstnce.id+"' and versionStatus='P' order by id")
	   if(contentDescriptionVersionList!=null && contentDescriptionVersionList.size>0)
	       contentDetails=contentDescriptionVersionList[contentDescriptionVersionList.size-1]
	   
		return contentDetails
	}
	
	//function to get the topic list using subjectId
	List getTopicList(Integer subjectId){
		def topicMasterList=AvlTopicMaster.findAll("from AvlTopicMaster where avlSubjectMaster.id=? and topicStatus='A'",[subjectId]);
		return topicMasterList
	}
	//function to get the subtopic list based on topicId
	List getSubTopicList(Integer topicId){
		def avlSubTopicMasterList=AvlSubTopicMaster.findAll("from AvlSubTopicMaster where avlTopicMaster.id=? and subTopicStatus='A'",[topicId])
		return avlSubTopicMasterList
	}
	//function to get the experimentSubtopicRelation using subtopicId
	List getExperimentSubtopicRel(def subtopicId){
	 def avlExperimentSubtopicRelList=AvlExperimentSubtopicRel.findAll("from AvlExperimentSubtopicRel where sub_topic="+subtopicId)
	 return avlExperimentSubtopicRelList
	}
	
	//function to get experiment details using experimentID
	def getExperimentDetails(def experimentId){
	def avlExperimentMasterInstance=AvlExperimentMaster.find("from AvlExperimentMaster where id='"+experimentId+"' and experimentStatus='A' order by id")
	return avlExperimentMasterInstance
	}
	
	//function to get content details using experimentId
	def getContentDetails(Integer experimentId){
		def avlContentDetailsInstance=AvlContentDetails.find("from AvlContentDetails where experiment.id=? and contentStatus='A' and versionId!=NULL order by contentTypeSequence ",[experimentId])
		return avlContentDetailsInstance
		}
	
	// function to get heading description 
	def getHeading(def subjectId,def experimentId,def universityId){
		def headingDesc=""
		def subjectInstance=AvlSubjectMaster.find("from AvlSubjectMaster where id='"+subjectId+"' and subjectStatus='A' and universityId='"+universityId+"'")
		if(subjectInstance)
		  {
			def expInstance=AvlExperimentMaster.find("from AvlExperimentMaster where experiment_id=? and experimentStatus='A'",[experimentId]);
			if(expInstance)
			  headingDesc+=(expInstance.experimentName) ;
		  }
	    return headingDesc		  
	}
	
	//function to change the page title of browser dynamicallt
	def getTitleDynamically(def subjectId,def experimentId,def contentId){
	def titleData="";
	def subjectInstance,subTopicInstance,topicInstance,avlExperimentSubtopicRel,tabInstance,expInstance
	subjectInstance=AvlSubjectMaster.find("from AvlSubjectMaster where id='"+subjectId+"'  and subjectStatus='A'")
	if(experimentId!='null')
	{
		 expInstance=AvlExperimentMaster.find("from AvlExperimentMaster where experiment_id='"+experimentId+"' and experimentStatus='A'");
	}
	else
	{
		topicInstance=AvlTopicMaster.find("from AvlTopicMaster where avlSubjectMaster.id='"+subjectInstance.id+"'")
	}
	tabInstance=AvlContentTypeMaster.find("from AvlContentTypeMaster where content_type_id='"+contentId+"'");
	avlExperimentSubtopicRel=AvlExperimentSubtopicRel.find("from AvlExperimentSubtopicRel where experiment='"+experimentId+"'")
	if(avlExperimentSubtopicRel)
		subTopicInstance=AvlSubTopicMaster.find("from AvlSubTopicMaster where id='"+avlExperimentSubtopicRel.sub_topic.id+"'")
	if(subTopicInstance)
	   topicInstance=AvlTopicMaster.find("from AvlTopicMaster where id='"+subTopicInstance.avlTopicMaster.id+"'")
	if(subjectInstance)
	   titleData=subjectInstance.subjectName.decodeHTML()
	if((subjectInstance)&&(topicInstance))
	   titleData=topicInstance.topicName.decodeHTML()+":"+subjectInstance.subjectName.decodeHTML()
	if((subjectInstance)&&(topicInstance)&&(expInstance))
	   titleData=expInstance.experimentName.decodeHTML()+":"+topicInstance.topicName.decodeHTML()+":"+subjectInstance.subjectName.decodeHTML()
	if((subjectInstance)&&(topicInstance)&&(expInstance)&&(tabInstance))
	   titleData=expInstance.experimentName.decodeHTML()+"("+tabInstance.contentTypeName.decodeHTML()+"):"+topicInstance.topicName.decodeHTML()+":"+subjectInstance.subjectName.decodeHTML()
	return titleData
	}
	
	//function to get the breadcum data to display in content header
	def getBreadcum(def subjectId,def experimentId)
	{
		def breadcum
		def subjectInstance,expInstance,avlExperimentSubtopicRel,subTopicInstance,topicInstance
		 subjectInstance=AvlSubjectMaster.find("from AvlSubjectMaster where id='"+subjectId+"'  and subjectStatus='A'")
       if(experimentId!='null')
	   {
		    expInstance=AvlExperimentMaster.find("from AvlExperimentMaster where experiment_id='"+experimentId+"' and experimentStatus='A'");
	   } 
	   else
	   {
		   topicInstance=AvlTopicMaster.find("from AvlTopicMaster where avlSubjectMaster.id='"+subjectInstance.id+"'")
	   }	
	   avlExperimentSubtopicRel=AvlExperimentSubtopicRel.find("from AvlExperimentSubtopicRel where experiment='"+experimentId+"'")
	   if(avlExperimentSubtopicRel)
	       subTopicInstance=AvlSubTopicMaster.find("from AvlSubTopicMaster where id='"+avlExperimentSubtopicRel.sub_topic.id+"'")
	   if(subTopicInstance)
	      topicInstance=AvlTopicMaster.find("from AvlTopicMaster where id='"+subTopicInstance.avlTopicMaster.id+"'")
	   
	   if(subjectInstance)
	      breadcum=subjectInstance.subjectName
	   if((subjectInstance)&&(topicInstance))
	     breadcum=subjectInstance.subjectName+"->"+topicInstance.topicName
	   
		if((subjectInstance)&&(topicInstance)&&(expInstance))
			 breadcum=subjectInstance.subjectName+"->"+topicInstance.topicName+"->"+expInstance.experimentName
		  return breadcum
		  
	}
}
