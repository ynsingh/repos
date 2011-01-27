<?php
/**
   * This file contains business logic functions and data retrieval functions
   * @author Mukesh Kumar M, Sreejith S R, Anitha B, Vidya Mohan
   * @version 05-08-2010
*/


/**
* Search for specified php files in the theme folder
* and returns true if the theme file exists
* @author Mukesh Kumar M
* @version 05-08-2010 
* @param string $filename
*/
function isThemeFileExists($fileName)
{
	if(substr($fileName,-4)!='.php')
			$fileName.='.php';
	if (file_exists(THEME_ROOT.$_SESSION['sess_current_theme'].'/'.$fileName))
	{
		return true;
	}
	return false;
}

/**
* Accepts the prefix part of the file name and
* returns the file path if the specified format file exists
* @author Mukesh Kumar M
* @version 05-08-2010 
* @param string $iconPrefix
*/
function getMenuIcons($iconPrefix)
{
	$arrMenuIconFormats=array('gif','jpg','ico','png');
	foreach($arrMenuIconFormats as $valIconFormat)
	{		
		if(file_exists(getThemeImage($iconPrefix.'.'.$valIconFormat)))
		{
			return getThemeImage($iconPrefix.'.'.$valIconFormat);
		}
	}
	return 'images/temp.gif';
}

/**
* Accepts the sub id
* returns the sub name
* @author Mukesh Kumar M
* @version 05-08-2010 
* @param int $subId
*/
function getSubName($subId)
{
	global $dbObject;
	$dbObject->sql="select subject_name from avl_subject_master where subject_id=$subId";
	$dbObject->msg="VLDBER000003";
	$row=$dbObject->querySelectReturn('DB');
	return $row[0][0];
}
/**
* Accepts the name of an image file 
* and returns the relative path of the file from the root folder
* if there is such file in the theme/<CURRENT THEME>/images directory
* else return false
* @author Mukesh Kumar M
* @version 07-08-2010 
* @param string $imageName
*/
function getThemeImage($imageName)
{
	if(file_exists(THEME_ROOT.$_SESSION['sess_current_theme'].'/images/'.$imageName))
	{
		return THEME_ROOT.$_SESSION['sess_current_theme'].'/images/'.$imageName;
	}
	return false;
}
/**
* getting the css file of a theme
* @author Mukesh Kumar M
* @version 07-08-2010 
* @param string $cssFile
*/
function getThemeCss($cssFile)
{
	if(substr($cssFile,-4)!='.css')
			$cssFile.='.css';
	if (file_exists(THEME_ROOT.$_SESSION['sess_current_theme'].'/css/'.$cssFile))
	{
		return THEME_ROOT.$_SESSION['sess_current_theme'].'/css/'.$cssFile;
	}
	return false;
}
/**
* retrieves sub topic under a specified topic from avl_sub_topic_master
* @author Mukesh Kumar M
* @version 11-08-2010 
* @param int $topicID
*/
function getAllSubTopics($topicID)
{
	global $dbObject;
	$dbObject->sql="select sub_topic_id, sub_topic_name, sub_topic_description from avl_sub_topic_master where topic_id=$topicID and sub_topic_status='A'";
	$dbObject->msg="VLDBER000004";
	return $dbObject->querySelectReturn('DB');
}
/**
* retrieves experiments under a specified sub topic
* @author Mukesh Kumar M
* @version 11-08-2010 
* @param int $subTopicID
*/
function getExperimentDetails($subTopicID)
{
	global $dbObject;
	$dbObject->sql="select EM.experiment_id, EM.experiment_name, EM.experiment_description, EM.experiment_type_id , TM.experiment_type_name
from avl_experiment_master EM, avl_experiment_subtopic_rel SR, avl_experiment_type_master TM 
where EM.experiment_id=SR.experiment_id and SR.sub_topic_id=$subTopicID and EM.experiment_status='A' and EM.experiment_type_id = TM.experiment_typeid";
	$dbObject->msg="VLDBER000005";
	return $dbObject->querySelectReturn('DB');
}
/**
* retrieves all subject id, name and description from subject master
* @author Sreejith S R
* @version 11-08-2010 
* @param $universityId
*/
function getAllSubject($universityId="")
{
	if($universityId)
	{
		$addSql=" university_id=$universityId and ";	
	}
	global $dbObject;
	$dbObject->sql="select subject_id, subject_name, subject_description from avl_subject_master where $addSql subject_status='A'";	
	$dbObject->msg="VLDBER000006";
	return $dbObject->querySelectReturn('DB');
}

/**
* retrieves all topic id and name from topic master
* @author Sreejith S R
* @version 11-08-2010 
* @param int $subId
*/
function getAllTopics($subId)
{
	global $dbObject;
	$dbObject->sql="select topic_id, topic_name, topic_description from avl_topic_master where subject_id=$subId and topic_status='A'";
	$dbObject->msg="VLDBER000002";
	return $dbObject->querySelectReturn('DB');
}
/**
* retrieves topic name from topic master
* @author Sreejith S R
* @version 11-08-2010 
* @param int $topicId
*/
function getTopicName($topicId)
{
	global $dbObject;
	$dbObject->sql="select topic_name from avl_topic_master where topic_id=$topicId and topic_status='A'";
	$dbObject->msg="VLDBER000007";
	$row=$dbObject->querySelectReturn('DB');
	return $row[0][0];
}
/**
* retrieves all topic name and description from topic master
* @author Sreejith S R
* @version 11-08-2010 
* @param int $topicId
*/
function getTopicDetails($topicId)
{
	global $dbObject;
	$dbObject->sql="select topic_name, topic_description from avl_topic_master where topic_id=$topicId and topic_status='A'";
	$dbObject->msg="VLDBER000010";
	$row=$dbObject->querySelectReturn('DB');
	return $row;
}
/**
* Updates topic name and description in topic master 
* @author Sreejith S R
* @version 11-08-2010 
* @param $topicId, $topicName, $topicDesc
*/
function updateTopic($topicId, $topicName, $topicDesc)
{
	global $dbObject;
	$topicName=htmlspecialchars($topicName,ENT_QUOTES);
	$topicDesc=htmlspecialchars($topicDesc,ENT_QUOTES);
	$dbObject->sql="update avl_topic_master set topic_name='$topicName', topic_description='$topicDesc' where topic_id=$topicId";
	$dbObject->msg="VLDBER000009";
	$row=$dbObject->queryUpdate();
	return $row;
}
/**
* retrieves  sub_topic name and description from sub_topic master
* @author Sreejith S R
* @version 11-08-2010 
* @param int $subTopicId
*/
function getSubTopicDetails($subTopicId)
{
	global $dbObject;
	$dbObject->sql="select sub_topic_name, sub_topic_description from avl_sub_topic_master where sub_topic_id=$subTopicId and sub_topic_status='A'";
	$dbObject->msg="VLDBER000012";
	$row=$dbObject->querySelectReturn('DB');
	return $row;
}
/**
* Updates Sub_topic name and description in Sub_topic master 
* @author Sreejith S R
* @version 11-08-2010 
* @param $subTopicId, $subTopicName, $subTopicDesc
*/
function updateSubTopic($subTopicId, $subTopicName, $subTopicDesc)
{
	global $dbObject;
	$subTopicName=htmlspecialchars($subTopicName,ENT_QUOTES);
	$subTopicDesc=htmlspecialchars($subTopicDesc,ENT_QUOTES);
	$dbObject->sql="update avl_sub_topic_master set sub_topic_name='$subTopicName', sub_topic_description='$subTopicDesc' where sub_topic_id=$subTopicId";
	$dbObject->msg="VLDBER000011";
	$row=$dbObject->queryUpdate();
	return $row;
}
/**
* retrieves experiment name when experiment id passes
* @author Mukesh Kumar M
* @version 17-08-2010 
* @param int $experimentId
*/
function getExperimentName($experimentId)
{
	global $dbObject;
	$dbObject->sql="select experiment_name from avl_experiment_master where experiment_id=".$experimentId;
	$dbObject->msg="VLDBER000008";
	$row=$dbObject->querySelectReturn('DB');
	return $row[0][0];
}
/**
* retrieves experiment_name name and description from avl_experiment_master
* @author Sreejith S R
* @version 11-08-2010 
* @param int $expId
*/
function getExpDetails($expId)
{
	global $dbObject;
	$dbObject->sql="select experiment_name, experiment_description from avl_experiment_master where experiment_id=$expId";
	$dbObject->msg="VLDBER000013";
	$row=$dbObject->querySelectReturn('DB');
	return $row;
}
/**
* Updates experiment_name and description in avl_experiment_master
* @author Sreejith S R
* @version 11-08-2010 
* @param $expId, $expName, $expDesc
*/
function updateExp($expId, $expName, $expDesc)
{
	global $dbObject;
	$expName=htmlspecialchars($expName,ENT_QUOTES);
	$expDesc=htmlspecialchars($expDesc,ENT_QUOTES);
	$dbObject->sql="update avl_experiment_master set experiment_name='$expName', experiment_description='$expDesc' where experiment_id=$expId";
	$dbObject->msg="VLDBER000014";
	$row=$dbObject->queryUpdate();
	return $row;
}
/**
* Insert new topic with name and description in avl_topic_master
* @author Sreejith S R
* @version 11-08-2010 
* @param $subId, $topicName, $topicDesc
*/
function addTopic($subId, $topicName, $topicDesc)
{
	global $dbObject;
	$topicName=htmlspecialchars($topicName,ENT_QUOTES);
	$topicDesc=htmlspecialchars($topicDesc, ENT_QUOTES);
	$dbObject->sql="INSERT INTO `avl_topic_master` (`topic_id`,`topic_name`,`topic_description`,`subject_id`,`topic_status`) VALUES (NULL,'$topicName','$topicDesc',$subId,'A')";
	$dbObject->msg="VLDBER000015";
	$row=$dbObject->queryInsert();
	return $row;
}
/**
* Retrives topic_name under a subject, when topic name and subject id is given. This is to check if a topic already exist udner a subject. 
* @author Sreejith S R
* @version 11-08-2010 
* @param $subId, $topicName
*/
function topicExist($subId, $topicName)
{
	
	global $dbObject;
	$topicName=htmlspecialchars($topicName,ENT_QUOTES);
	$dbObject->sql="select topic_name from avl_topic_master where subject_id=$subId and topic_name='$topicName' and  topic_status='A'";
	$dbObject->msg="VLDBER000016";
	$row=$dbObject->querySelectReturn('DB');
	return $row[0][0];
}
/**
* Retrives sub_topic_name under a topic_id, when sub_topic_name and topic_id id is given. This is to check if same subtopic name exist under a topic. 
* @author Sreejith S R
* @version 11-08-2010 
* @param $topicId, $subTopicName
*/
function subTopicExist($topicId, $subTopicName)
{
	global $dbObject;
	$dbObject->sql="select sub_topic_name from avl_sub_topic_master where topic_id=$topicId and sub_topic_name='$subTopicName' and sub_topic_status='A'";
	$dbObject->msg="VLDBER000017";
	$row=$dbObject->querySelectReturn('DB');
	return $row[0][0];
}
/**
* Insert new sub_topic with name and description in avl_sub_topic_master
* @author Sreejith S R
* @version 11-08-2010 
* @param $topicId, $subTopicName, $subTopicDesc
*/
function addSubTopic($topicId, $subTopicName, $subTopicDesc)
{
	global $dbObject;
	$subTopicName=htmlspecialchars($subTopicName,ENT_QUOTES);
	$subTopicDesc=htmlspecialchars($subTopicDesc,ENT_QUOTES);
	$dbObject->sql="INSERT INTO `avl_sub_topic_master` (`sub_topic_id`,`sub_topic_name`,`sub_topic_description`,`topic_id`,`sub_topic_status`) VALUES (NULL,'$subTopicName','$subTopicDesc',$topicId,'A')";
	$dbObject->msg="VLDBER000092";
	$row=$dbObject->queryInsert();
	return $row;
}
/**
* Update topic_status to disabled
* @author Sreejith S R
* @version 11-08-2010 
* @param int $topicId
*/
function deleteTopic($topicId)
{
	global $dbObject;
	$dbObject->sql="UPDATE `avl_topic_master` SET `topic_status`='D' WHERE `topic_id`='$topicId'";
	$dbObject->msg="VLDBER000093";
	$row=$dbObject->queryUpdate();
	return $row;
}
/**
* retrives content_type_name of a particular item from avl_content_type_master
* @author Sreejith S R
* @version 11-08-2010 
* @param int $itemId
*/
function getExperimentItemName($itemId)
{
	global $dbObject;
	$dbObject->sql="select content_type_name from avl_content_type_master where content_type_id=".$itemId;
	$dbObject->msg="VLDBER000019";
	$row=$dbObject->querySelectReturn('DB');
	return $row[0][0];
}
/**
* retrieves Content Type ids and Names under a specified experiment
* @author Mukesh Kumar M
* @version 
* @param int $experimentId
*/
function getAllContentItems($experimentId)
{
	global $dbObject;
	
	$dbObject->sql="select TM.content_type_id, TM.content_type_name, CD.content_id
from avl_content_type_master TM, avl_content_details CD 
where TM.content_type_id=CD.content_type_id and CD.experiment_id=$experimentId order by CD.content_type_sequence";
	$dbObject->sql;
	$dbObject->msg="VLDBER000041";
	return $dbObject->querySelectReturn('DB');
}
/**
* Update sub_topic_status to disabled
* @author Sreejith S R
* @version 11-08-2010 
* @param string $subTopicId
*/
function deleteSubTopic($subTopicId)
{
	global $dbObject;
	$dbObject->sql="UPDATE `avl_sub_topic_master` SET `sub_topic_status`='D' WHERE `sub_topic_id`=$subTopicId";
	$dbObject->msg="VLDBER000020";
	$row=$dbObject->queryUpdate();
	return $row;
}
/**
* Update experiment_status to disabled
* @author Sreejith S R
* @version 11-08-2010 
* @param int $expId
*/
function deleteExp($expId)
{
	global $dbObject;
	$dbObject->sql="UPDATE `avl_experiment_master` SET `experiment_status`='D' WHERE `experiment_id`=$expId";
	$dbObject->msg="VLDBER000021";
	$row=$dbObject->queryUpdate();
	return $row;
}
/**
* retrives all content_type_id,content_type_name from avl_content_type_master
* @author Sreejith S R
* @version 11-08-2010 
* @param  NULL
*/
function getContentType()
{
	global $dbObject;
	$dbObject->sql="select content_type_id,content_type_name from avl_content_type_master;";
	$dbObject->msg="VLDBER000022";
	$row=$dbObject->querySelectReturn('DB');
	return $row;
}
/**
* retrives all experiment_typeid, experiment_type_name from avl_experiment_type_master
* @author Sreejith S R
* @version 11-08-2010 
* @param NULL
*/
function getExperimentType()
{
	global $dbObject;
	$dbObject->sql="select experiment_typeid, experiment_type_name from avl_experiment_type_master;";
	$dbObject->msg="VLDBER000023";
	$row=$dbObject->querySelectReturn('DB');
	return $row;
}
/**
* retrives content_description from avl_content_description
* @author Sreejith S R
* @version 11-08-2010 
* @param int $cntId
*/
function getContentDescription($cntId)
{
	global $dbObject;
	$dbObject->sql="select content_description from avl_content_description where content_id='$cntId'";
	$dbObject->msg="VLDBER000030";
	$row=$dbObject->querySelectReturn('DB');
	if(count($row)>0)
	{
		return str_replace(DB_FOLDER_NAME,"/".PROJECT_ROOT."/",$row[0][0]);		
	}
	return false;
	
}
/**
* retrives content_id, external from avl_content_details
* @author Sreejith S R
* @version 11-08-2010 
* @param int $cntTypeId, $expId
*/
function getContentIdAndStatus($cntTypeId, $expId)
{
	global $dbObject;
	$dbObject->sql="select content_id, external from avl_content_details where content_type_id=$cntTypeId and experiment_id=$expId  ;";
	$dbObject->msg="VLDBER000024";
	$row=$dbObject->querySelectReturn('DB');
	return $row;
}
/**
* retrives the Default Content Type from avl_content_type_master
* @author Sreejith S R
* @version 11-08-2010 
* @param NULL
*/
function getDefaultContentType()
{
	global $dbObject;
	$dbObject->sql="select content_type_id,content_type_name from avl_content_type_master where type_mandatory='Y'  order by content_type_id";
	$dbObject->msg="VLDBER000025";
	$row=$dbObject->querySelectReturn('DB');
	return $row;
}
/**
* updates avl_content_description when contentId and description is given
* @author Sreejith S R
* @version 11-08-2010 
* @param $contentId, $contentDescr
*/
function updateContentDescription($contentId, $contentDescr)
{
	global $dbObject;
	$contentDescr=htmlspecialchars($contentDescr,ENT_QUOTES);
	$dbObject->sql="update avl_content_description set content_description='$contentDescr' where content_id=$contentId;";
	$dbObject->msg="VLDBER000026";
	$row=$dbObject->queryUpdate();
	return $row;
}
/**
* updates content_link in avl_content_links
* @author Sreejith S R
* @version 11-08-2010 
* @param $content_id,$content_link
*/
function updateContLinks($content_id,$content_link)
{
	global $dbObject;
	$dbObject->sql="update avl_content_links set content_link='$content_link' where content_id=$content_id";	 
	$dbObject->msg="VLDBER000028";
	$row=$dbObject->queryUpdate();
	return $row;
}
/**
* updates external(link_status) in avl_content_details
* @author Sreejith S R
* @version 11-08-2010 
* @param $expId, $contTypeId, $link_status
*/
function updateLinkStatus($expId, $contTypeId, $link_status)
{
	global $dbObject;
	$dbObject->sql="update avl_content_details set external='$link_status' where experiment_id=$expId and content_type_id=$contTypeId";
	$dbObject->msg="VLDBER000032";
	$row=$dbObject->queryUpdate();
	return $row;
}
/**
* insert content details to avl_content_description
* @author Sreejith S R
* @version 11-08-2010 
* @param $contentId, $contentDescr
*/
function insertContentDesc($contentId, $contentDescr)
{
	global $dbObject;
	$contentDescr=htmlspecialchars($contentDescr,ENT_QUOTES);
	$dbObject->sql="INSERT INTO `avl_content_description` (`content_id`,`content_description`) VALUES ($contentId, '$contentDescr')";
	$dbObject->msg="VLDBER000027";
	$row=$dbObject->queryInsert();
	return $row;
}
/**
* Insert content link to avl_content_links
* @author Sreejith S R
* @version 11-08-2010 
* @param string $contentId, $link
*/
function insertContentLink($contentId, $link)
{
	global $dbObject;
	$dbObject->sql="INSERT INTO `avl_content_links` (`content_id`,`content_link`) VALUES ($contentId, '$link')";
	$dbObject->msg="VLDBER000033";
	$row=$dbObject->queryInsert();
	return $row;
}
/**
* returns the link, taking the content id
* @author Mukesh Kumar M
* @version 06-09-2010 
* @param string $contentId
*/
function getContentLink($contentId)
{
	global $dbObject;
	$dbObject->sql="select content_link from avl_content_links where content_id=$contentId";
	$dbObject->msg="VLDBER000031";
	$row= $dbObject->querySelectReturn('DB');
	if(count($row)>0)
	{
		return $row[0][0];
	}
	return false;
	
}
/**
* Insert experiment details to avl_experiment_master
* @author Sreejith S R
* @version 11-08-2010 
* @param string $expName, $expDescr, $expTypeId, $expStatus
*/
function insertExperimentMaster($expName, $expDescr, $expTypeId, $expStatus)
{
	global $dbObject;
	$expName=htmlspecialchars($expName,ENT_QUOTES);
	$expDescr=htmlspecialchars($expDescr,ENT_QUOTES);
	$dbObject->sql="INSERT INTO `avl_experiment_master` (`experiment_id`,`experiment_name`,`experiment_description`,`experiment_type_id`,`experiment_status`,`owner`) VALUES (NULL,'$expName','$expDescr',$expTypeId,'$expStatus',NULL);";
	$dbObject->msg="VLDBER000034";
	$row=$dbObject->queryInsert();
	return $row;	
}
/**
* Insert experiment id, sub_topic_id to experiment_subtopic_rel
* @author Sreejith S R
* @version 11-08-2010 
* @param string $expId, $subTopicId
*/
function insertExperimentSubtopicRelation($expId, $subTopicId)
{
	global $dbObject;
	$dbObject->sql="INSERT INTO `avl_experiment_subtopic_rel` (`experiment_id`,`sub_topic_id`) VALUES ($expId,$subTopicId);";
	$dbObject->msg="VLDBER000035";
	$row=$dbObject->queryInsert();
	return $row;
}
/**
* to check if the experiment name exist under the same subtopic
* @author Sreejith S R
* @version 11-08-2010 
* @param string $expName, $subTpoicId
*/
function checkExperimentName($expName, $subTpoicId)
{
	global $dbObject;
	$expName=htmlspecialchars($expName,ENT_QUOTES);
	$dbObject->sql="select em.experiment_name from avl_experiment_master em, avl_experiment_subtopic_rel esr 
where em.experiment_id=esr.experiment_id and em.experiment_name='$expName' and esr.sub_topic_id=$subTpoicId;";
	$dbObject->msg="VLDBER000036";
	$row= $dbObject->querySelectReturn('DB');
	if(count($row)>0)
	{
		return true;
	}
	return false;
}
/**
* insert the default content data to avl_content_details
* @author Sreejith S R
* @version 11-08-2010 
* @param string $expId
*/
function insertContentDetails($expId)
{
	global $dbObject;
	$defaultContentType=getDefaultContentType();
	for($i=0;$i<count($defaultContentType);$i++)
	{
		if($defaultContentType[$i][0]==SIMULATOR_ID)
		{
			$external='Y';
		}
		else
		{
			$external='N';
		}
			
		if($i==0){$sql_add.="(NULL,".$defaultContentType[$i][0].",$expId,'$external', $i+1)"; }
		else
		{
			
			$sql_add.=",(NULL,".$defaultContentType[$i][0].",$expId,'$external', $i+1)";
		}
	}
	$dbObject->sql="INSERT INTO `avl_content_details` (`content_id`,`content_type_id`,`experiment_id`,`external`, `content_type_sequence`) values $sql_add";
	$dbObject->msg="VLDBER000037";
	$row=$dbObject->queryInsert();
	return $row;
}
/**
* to add additional tab
* @author Sreejith S R
* @version 11-08-2010 
* @param string $content_type_id, $expId, $external
*/
function insertContentDetailsSingle($content_type_id,$expId,$external)
{
	global $dbObject;
	$tabCount=count(getContentTypeDetails($expId));
	$dbObject->sql="INSERT INTO `avl_content_details` (`content_id`,`content_type_id`,`experiment_id`,`external`, `content_type_sequence`) values (NULL,$content_type_id,$expId,'$external', $tabCount+1)";
	$dbObject->msg="VLDBER000038";
	$row=$dbObject->queryInsert();
	return $row;
}
/**
* insert content type name to avl_content_type_master
* @author Sreejith S R
* @version 11-08-2010 
* @param string $content_type_name
*/
function insertContentTypeMaster($name)
{
	global $dbObject;
	$name==htmlspecialchars($name,ENT_QUOTES);
	$dbObject->sql="INSERT INTO `avl_content_type_master` (`content_type_id`,`content_type_name`,`type_mandatory`) VALUES (NULL,'$name','N')";
	$dbObject->msg="VLDBER000048";
	$row=$dbObject->queryInsert();
	return $row;
}
/**
* to check if a content_type_name already exist
* @author Sreejith S R
* @version 11-08-2010 
* @param string $content_type_name
*/
function checkContentTypeMaster($name)
{
	global $dbObject;
	$dbObject->sql="SELECT content_type_name, content_type_id, type_mandatory  FROM `avl_content_type_master` WHERE `content_type_name`='$name'";
	$dbObject->msg="VLDBER000039";
	$row=$dbObject->querySelectReturn('DB');
	return $row;
}
/**
* to check if a content_type_name already exist in an experiment
* @author Sreejith S R
* @version 11-08-2010 
* @param $typeName, $expId
*/
function checkContentTypeExperiment($typeName, $expId)
{
	global $dbObject;
	$dbObject->sql="SELECT CTM.content_type_name, CTM.content_type_id 
FROM avl_content_type_master CTM, avl_content_details CD 
WHERE CTM.content_type_id=CD.content_type_id and CD.experiment_id=$expId and CTM.content_type_name='$typeName'";
	$dbObject->msg="VLDBER000040";
	$row=$dbObject->querySelectReturn('DB');
	return $row;
}
//
/**
* returns the array of theme names
* @author Mukesh Kumar M
* @version 07-09-2010 
* @param null
*/
function getAllThemes()
{
	if ($handle = opendir(THEME_ROOT)) 
	{   
		/* This is the correct way to loop over the directory. */
		$bad = array(".", "..", ".svn");
		while (false !== ($file = readdir($handle)))
		{
			if(is_dir (THEME_ROOT.$file) && ($file!='.'&& $file!='..' && $file!='.svn') )
			{
				$arrTheme[]=$file;
			}
		}
		
	}
	return $arrTheme;
}
/**
* returns the file path if the specified format file exists
* @author Mukesh Kumar M
* @version 05-08-2010 
* @param string $themeName
*/
function getThemeIcon($themeName)
{
	$arrMenuIconFormats=array('gif','jpg','ico','png');
	foreach($arrMenuIconFormats as $valIconFormat)
	{		
		if(file_exists(THEME_ROOT.$themeName.'/images/theme_screenshot.'.$valIconFormat))
		{
			
			return THEME_ROOT.$themeName.'/images/theme_screenshot.'.$valIconFormat;
		}
	}
	if(file_exists('images/temp_theme.jpg'))
	{
		return 'images/temp_theme.jpg';
	}
	return false;
}
/**
* get type_mandatory FROM avl_content_type_master
* @author Sreejith S R
* @version 11-08-2010 
* @param int $content_type_id
*/
function checkContentTypeMandatory($id)
{
	global $dbObject;
	$dbObject->sql="SELECT type_mandatory  FROM `avl_content_type_master` WHERE `content_type_id`=$id";
	$dbObject->msg="VLDBER000042";
	$row=$dbObject->querySelectReturn('DB');
	return $row[0][0];
}
/**
* Update content_type_name in avl_content_type_master
* @author Sreejith S R
* @version 11-08-2010 
* @param $typeId, $name
*/
function updateContentTypeName($typeId, $name)
{

	global $dbObject;
	$dbObject->sql="UPDATE `avl_content_type_master` SET `content_type_name`='$name' WHERE `content_type_id`=$typeId";
	$dbObject->msg="VLDBER000043";
	$row=$dbObject->queryUpdate();
	return $row;
}
/**
* Delete from avl_content_description
* @author Sreejith S R
* @version 11-08-2010 
* @param string $contentId
*/
function deleteContentDescription($contentId)
{
	global $dbObject;
	$dbObject->sql="DELETE FROM `avl_content_description` WHERE `content_id`=$contentId";
	$dbObject->msg="VLDBER000044";
	$row=$dbObject->queryDelete();
	return $row;
}
/**
* Delete from avl_content_details
* @author Sreejith S R
* @version 11-08-2010 
* @param string $contentId
*/
function deleteContentDetails($contentId)
{
	global $dbObject;
	$dbObject->sql="DELETE FROM `avl_content_details` WHERE `content_id`=$contentId";
	$dbObject->msg="VLDBER000045";
	$row=$dbObject->queryDelete();
	return $row;
}
/**
* Delete from avl_content_links
* @author Sreejith S R
* @version 11-08-2010 
* @param string $contentId
*/
function deleteContentLink($contentId)
{
	global $dbObject;
	$dbObject->sql="DELETE FROM `avl_content_links` WHERE `content_id`=$contentId";	
	$dbObject->msg="VLDBER000046";
	$row=$dbObject->queryDelete();
	return $row;
}
/**
* Delete from avl_content_type_master
* @author Sreejith S R
* @version 11-08-2010 
* @param string $contentTypeId
*/
function deleteContentType($contentTypeId)
{
	global $dbObject;
	$dbObject->sql="DELETE FROM `avl_content_type_master` WHERE `content_type_id`=$contentTypeId";
	$dbObject->msg="VLDBER000047";
	$row=$dbObject->queryDelete();
	return $row;
}
/**
* returns the current theme
* @author Mukesh Kumar M
* @version 16-09-2010 
* @param null
*/
function getCurrentTheme()
{	
	$file_path="system_file_write\current_theme.thm";
	/*$fh = fopen($file_path, 'r');
	$theme = fread($fh);*/
	$theme = file_get_contents($file_path);
	
	if($theme!="")
		return $theme;
	return false;
}
/**
* to set the theme
* @author Mukesh Kumar M
* @version 16-09-2010 
* @param $theme
*/
function setTheme($theme)
{
	$file_path="system_file_write\current_theme.thm";
	$fh = fopen($file_path, 'w');
	fwrite($fh, $theme);
	fclose($fh);
	return true;
}
/**
* get provider id from avl_oauth_provider_master
* @author Sreejith S R
* @version 08-10-2010 
* @param string $provider_name
*/
function getProviderId($providerName)
{
	global $dbObject;
	$dbObject->sql="Select provider_id from avl_oauth_provider_master where provider_name='$providerName'";
	$dbObject->msg="VLDBER000049";
	$row=$dbObject->querySelectReturn('DB');
	return $row[0][0];
}
/**
* get user_id from avl_oauth_user_rel. This function is to check the token we get is already in our DB.
* @author Sreejith S R
* @version 08-10-2010 
* @param $tokenId, $providerId 
*/
function authenticateToken($tokenId, $providerId )
{
	global $dbObject;
	$dbObject->sql="Select user_id from avl_oauth_user_rel where token_id='$tokenId' and provider_id=$providerId;";
	$dbObject->msg="VLDBER000050";
	$row=$dbObject->querySelectReturn('DB');
	return $row[0][0];
}
/**
* get role_id from role_master
* @author Sreejith S R
* @version 08-10-2010 
* @param string $role_name
*/
function getRoleId($roleName)
{
	global $dbObject;
	$dbObject->sql="Select role_id from avl_role_master where role_name='$roleName';";
	$dbObject->msg="VLDBER000051";
	$row=$dbObject->querySelectReturn('DB');
	return $row[0][0];
}
/**
* Insert new token with user_id and provider id in avl_oauth_user_rel
* @author Sreejith S R
* @version 11-08-2010 
* @param string $tokenId, $userId, $providerId
*/
function addOauthTokenId($tokenId, $userId, $providerId)
{
	global $dbObject;
	$dbObject->sql="INSERT INTO `avl_oauth_user_rel` (`token_id`,`user_id`,`provider_id`) VALUES ('$tokenId',$userId,$providerId)";
	$dbObject->msg="VLDBER000052";
	$row=$dbObject->queryInsert();
	return $row;
}
/**
* Insert user_id,user_name,password,university_id to avl_user_master
* @author Sreejith S R
* @version 11-08-2010 
* @param string $userName, $userPassword, $universityId
*/
function addUserMaster($userName, $userPassword, $universityId)
{
	global $dbObject;
	$dbObject->sql="INSERT INTO `avl_user_master` (`user_id`,`user_name`,`password`,`university_id`) VALUES (NULL,'$userName','$userPassword',$universityId)";
	$dbObject->msg="VLDBER000053";
	$row=$dbObject->queryInsert();
	return $row;
}
/**
* Update user_name of avl_user_master
* @author Sreejith S R
* @version 11-08-2010 
* @param string $userName, $userId
*/
function updateUserMaster($userName, $userId)
{
	global $dbObject;
	$dbObject->sql="UPDATE `avl_user_master` SET `user_name`='$userName' WHERE `user_id`=$userId";
	$dbObject->msg="VLDBER000054";
	$row=$dbObject->queryUpdate();
	return $row;
}

/**
* Insert new user into avl_role_user_rel
* @author Sreejith S R
* @version 11-08-2010 
* @param string $userId, $roleId
*/
function addRoleUserRel($userId, $roleId)
{
	global $dbObject;
	$dbObject->sql="INSERT INTO `avl_role_user_rel` (`user_id`,`role_id`) VALUES ($userId,$roleId)";
	$dbObject->msg="VLDBER000055";
	$row=$dbObject->queryInsert();
	return $row;
}
/**
* get role_name from avl_user_master
* @author Sreejith S R
* @version 08-10-2010 
* @param int $userId
*/
function getUserRole($userId)
{
	global $dbObject;
	$dbObject->sql="Select RM.role_name from avl_user_master UM, avl_role_user_rel RR, avl_role_master RM  
where UM.user_id=RR.user_id and RR.role_id=RM.role_id and UM.user_id=$userId";
	$dbObject->msg="VLDBER000056";
	$row=$dbObject->querySelectReturn('DB');
	return $row[0][0];
}
/**
* Authenticate UserRole
* @author Sreejith S R
* @version 08-10-2010 
* @param string $userName, $password
*/
function AuthenticateUserRole($userName, $password)
{
	global $dbObject;
	$dbObject->sql="select RM.role_name from avl_user_master UM, avl_role_master RM, avl_role_user_rel RUR 
where UM.user_name='$userName' and UM.`password`=md5('$password') and UM.user_id=RUR.user_id and RUR.role_id=RM.role_id";
	$dbObject->msg="VLDBER000057";
	$row=$dbObject->querySelectReturn('DB');
	return $row[0][0];
}
/**
* to check if a user can access a page
* @author Sreejith S R
* @version 08-10-2010 
* @param $usertype_access
*/
function checkUserPageAccess($usertype_access)
	{
		$userRole=$_SESSION['sessUserRoleId'];
		$typeExist = array_search($userRole,$usertype_access);
		if(strlen($typeExist) == 0 )
		{
			echo "You are not allowed to view this page.";
			exit();
		}
	}
/*insert the user name and other details in user master
* now this function calls when the outh provider gives user credetials
* role has been set as guest by default
* @author Mukesh Kumar M
* @version 12-10-09
* @param string $userName, $emailId,  $universityId
*/ 
function insertUserCredentials($userName, $emailId, $universityId)
{
	global $dbObject;
	$dbObject->sql="INSERT INTO `avl_user_master` (`user_name`,`university_id`,`email_id`) VALUES ('$userName','$universityId','$emailId')";
	$dbObject->msg="VLDBER000058";
	$row=$dbObject->queryInsert();
	addRoleUserRel($dbObject->getLastInsertId(), ROLE_ID_GUEST);
	return $row;
}
/*take user_id of a particular user from avl_user_master  
* @author Mukesh Kumar M
* @version 15-10-09
* @param string $userName
*/ 
function getUserIdFromUserName($userName)
{
	global $dbObject;
	//
	$dbObject->sql="select user_id from avl_user_master where user_name='$userName'";
	$dbObject->msg="VLDBER000059";
	$row=$dbObject->querySelectReturn();
	//
	return $row[0][0];
}
/*take the role id of a particular user 
* from user role relation table
* @author Mukesh Kumar M
* @version 15-10-09
* @param integer $userId 
*/ 
function getUserRoleId($userId)
{
	global $dbObject;
	$dbObject->sql="select role_id from avl_role_user_rel where user_id=$userId";
	$dbObject->sql;
	$dbObject->msg="VLDBER000060";
	$row=$dbObject->querySelectReturn('DB');
	return $row[0][0];
}
/*take the role name from user role master table while giving role id
* @author Mukesh Kumar M
* @version 15-10-09
* @param integer $roleId 
*/ 
function getUserRoleName($roleId)
{
	global $dbObject;
	$dbObject->sql="select role_name from avl_role_master where role_id=$roleId";
	$dbObject->msg="VLDBER000061";
	$row=$dbObject->querySelectReturn('DB');
	return $row[0][0];
}
/*to check if the given time slot is available or not. This function returns an arrayof the equipment id of an experiment which is not available(means in use or reserved) in the given time period.
* @author Sreejith S R
* @version 21-10-09
* @param integer $srartTime, $endTime, $expId 
*/ 
function checkTimeSlot($srartTime, $endTime, $expId)
{
	global $dbObject;
	$dbObject->sql="select DISTINCT ts.equipment_id from avl_remote_trigger_schedule ts, avl_remote_trigger_equipment_master em 
where ('$srartTime' BETWEEN  ts.start_time and ts.end_time )
or ('$endTime' BETWEEN  ts.start_time and ts.end_time ) and ts.equipment_id=em.equipment_id and em.experiment_id=$expId and ts.`status` in ('R','S');";
	$dbObject->msg="VLDBER000062";
	$row=$dbObject->querySelectReturn('DB');
	return $row;
}
/*get randon equipment id of an experiment which is not in the given array of equipment
* @author Sreejith S R
* @version 21-10-09
* @param integer $expId, $array_equipment
*/ 
function getUnscheduledEquipment($expId, $array_equipment)
{
	global $dbObject;
	$dbObject->sql="select equipment_id from avl_remote_trigger_equipment_master 
where experiment_id=$expId and equipment_id not in $array_equipment order by rand() limit 1;";
	$dbObject->msg="VLDBER000063";
	$row=$dbObject->querySelectReturn('DB');
	return $row[0][0];
}
/*get randon equipment id of an experiment which is not in the given array of equipment
* @author Sreejith S R
* @version 21-10-09
* @param integer $expId 
*/ 
function getRandEquipment($expId)
{
	global $dbObject;
	$dbObject->sql="select equipment_id from avl_remote_trigger_equipment_master where experiment_id=$expId order by rand() limit 1;";
	$dbObject->msg="VLDBER000063";
	$row=$dbObject->querySelectReturn('DB');
	return $row[0][0];
}
/*get randon equipment id of an experiment which is free in the given time period
* @author Sreejith S R
* @version 21-10-09
* @param integer $srartTime, $endTime, $expId 
*/ 
function getFreeEquipment($srartTime, $endTime, $expId)
{
	$equipmentIdArray=checkTimeSlot($srartTime, $endTime, $expId);//list busy equipment id
	$i=0;
	if($equipmentIdArray)
	{
		for($i=0;$i<count($equipmentIdArray);$i++)
		{
			if($i==0)
			{
				$equipmentIdArray1.="(";
			}
			$equipmentIdArray1.="'".$equipmentIdArray[$i][0]."'";
			if($i==count($equipmentIdArray)-1)
			{
				$equipmentIdArray1.=")";
			}
			else
			{
				$equipmentIdArray1.=",";
			}	
			
		}
	
		$equipmentId = getUnscheduledEquipment($expId, $equipmentIdArray1);//getting a random eqp id which is not busy
		if($equipmentId)
		{
			return($equipmentId);
		}
		else
		{
			return('NULL');//none of the equipmet is free in this time period for this experiment
		}
	}
	else//equipmentIdArray null means, all eqp are free in this time period; so get a randon eqp of that experiment.
	{
		$equipmentId=getRandEquipment($expId);
		if($equipmentId)
		{
			return($equipmentId);
		}
		else
		{
			return('NULL');//if no experiment is defined for this experiment in eqp master
		}
	}
}
/*get duration of the remote trigger
* @author Sreejith S R
* @version 21-10-09
* @param integer $expId
*/ 
function getRemoteDuration($expId)
{
	global $dbObject;
	$dbObject->sql="select duration from avl_remote_trigger_details where experiment_id=$expId";
	$dbObject->msg="VLDBER000064";
	$row=$dbObject->querySelectReturn('DB');
	return $row[0][0];
}
/**
* Insert the schedule of a remote trigger experiment in to 
* @author Sreejith S R
* @version 23-10-2010 
* @param string $equipment_id, $user_email, $start_time, $end_time,$authorization_token, $status
*/
function insertRemoteTriggerSchedule($equipment_id, $user_email, $start_time, $end_time,$authorization_token, $status)
{
	global $dbObject;
	$dbObject->sql="INSERT INTO `avl_remote_trigger_schedule` (`equipment_id`,`user_email`,`start_time`,`end_time`,`authorization_token`,`status`) VALUES ($equipment_id,'$user_email','$start_time','$end_time','$authorization_token','$status')";
	$dbObject->msg="VLDBER000065";
	$row=$dbObject->queryInsert();
	return $row;
}
/**
* retrieves experiment type id when experiment id passes
* @author Sreejith S R	
* @version 25-10-2010 
* @param integer $experimentId
*/
function getExperimentTypeId($experimentId)
{
	global $dbObject;
	$dbObject->sql="select experiment_type_id from avl_experiment_master where experiment_id=".$experimentId;
	$dbObject->msg="VLDBER000066";
	$row=$dbObject->querySelectReturn('DB');
	return $row[0][0];
}
/*to get the user details from avl_user_master
* from user role relation table
* @author Sreejith S R
* @version 25-10-09
* @param integer $userId 
*/ 
function getUserDetails($userId)
{
	global $dbObject;
	$dbObject->sql="select user_name, email_id, university_id from avl_user_master where user_id=$userId";
	$dbObject->msg="VLDBER000067";
	$row=$dbObject->querySelectReturn('DB');
	return $row[0];
}
/*to get remote trigger equipment url
* from user role relation table
* @author Sreejith S R
* @version 25-10-09
* @param integer $equipment_id 
*/ 
function getEquipmentUrl($equipment_id)
{
	global $dbObject;
	$dbObject->sql="select URL from avl_remote_trigger_equipment_master where equipment_id=$equipment_id";
	$dbObject->msg="VLDBER000068";
	$row=$dbObject->querySelectReturn('DB');
	return $row[0][0];
}
/*to get the equipment id, if any alloted this an user at current time
* from user role relation table
* @author Sreejith S R
* @version 25-10-09
* @param integer $experimentId, $userEmail, $startTime, $authToken
*/ 
function getCurrEquipmentOfUser($experimentId, $userEmail, $startTime, $authToken)
{
	global $dbObject;
	$dbObject->sql="select DISTINCT ts.equipment_id from avl_remote_trigger_schedule ts, avl_remote_trigger_equipment_master em 
where ('$startTime' BETWEEN  ts.start_time and ts.end_time ) and ts.equipment_id=em.equipment_id and em.experiment_id=$experimentId 
and ts.user_email='$userEmail' and ts.authorization_token='$authToken' and ts.`status` in ('R');";
	$dbObject->msg="VLDBER000068";
	$row=$dbObject->querySelectReturn('DB');
	return $row[0][0];
}
/**
* Updates equipment status 
* @author Sreejith S R
* @version 28-10-2010 
* @param string $jobId, $status
*/
function updatEquipmentStatus($jobId, $status)
{
	global $dbObject;
	$dbObject->sql="UPDATE `avl_remote_trigger_schedule` SET `status`='$status' WHERE `job_id`=$jobId";
	$dbObject->msg="VLDBER000069";
	$row=$dbObject->queryUpdate();
	return $row;
}
/*to get the experiment details which is currently ready, when token id and curent time is given 
* from user role relation table
* @author Sreejith S R
* @version 28-10-09
* @param $startTime, $authToken
*/ 
function getTokenDetails($startTime, $authToken)
{
	global $dbObject;
	$dbObject->sql="select rs.job_id, em.experiment_id, rs.equipment_id, rs.`status`
from avl_remote_trigger_schedule rs, avl_remote_trigger_equipment_master em
where rs.authorization_token='$authToken' and ('$startTime' BETWEEN  rs.start_time and rs.end_time ) 
and rs.equipment_id=em.equipment_id";
	$dbObject->msg="VLDBER000070";
	$row=$dbObject->querySelectReturn('DB');
	return $row[0];
}
/*to get  experiment url when experimetn id is given
* from user role relation table
* @author Sreejith S R
* @version 28-10-09
* @param integer $rootUrl, $expId, $expContentType
*/ 
function getExperimentUrl($rootUrl, $expId, $expContentType=1)
{
	
	global $dbObject;
	$dbObject->sql="select stm.topic_id, tm.subject_id
from avl_experiment_subtopic_rel esr, avl_sub_topic_master stm , avl_topic_master tm
where esr.experiment_id='$expId' and esr.sub_topic_id=stm.sub_topic_id and stm.topic_id=tm.topic_id;";
	$dbObject->msg="VLDBER000071";
	
	$row=$dbObject->querySelectReturn('DB');
	$url=$rootUrl."?sub=".$row[0][1]."&brch=".$row[0][0]."&sim=".$expId."&cnt=".$expContentType;
	
	return($url);
}
/*to get the experiment details which is currently ready, when job_id and curent time is given 
* from user role relation table
* @author Sreejith S R
* @version 28-10-09
* @param integer $startTime, $jobId 
*/ 
function getJobDetails($startTime, $jobId)
{
	global $dbObject;
	$dbObject->sql="select em.experiment_id, rs.equipment_id, rs.`status`, rs.start_time, rs.end_time
from avl_remote_trigger_schedule rs, avl_remote_trigger_equipment_master em
where rs.job_id='$jobId' and ('$startTime' BETWEEN  rs.start_time and rs.end_time ) 
and rs.equipment_id=em.equipment_id";
	$dbObject->msg="VLDBER000072";
	$row=$dbObject->querySelectReturn('DB');
	return $row[0];
}
/*to check if the given experimet is already scheduled for an user in the given time period.
* from user role relation table
* @author Sreejith S R
* @version 28-10-09
* @param $email, $startTime, $expId
*/ 
function checkScheduledExperiment($email, $startTime, $expId)
{
	global $dbObject;
	$dbObject->sql="select rs.job_id 
from avl_remote_trigger_schedule rs,  avl_remote_trigger_equipment_master em
where rs.user_email='$email' and ('$startTime' BETWEEN  rs.start_time and rs.end_time ) 
and rs.equipment_id=em.equipment_id and em.experiment_id=$expId;";
	$dbObject->msg="VLDBER000073";
	$row=$dbObject->querySelectReturn('DB');
	return $row[0][0];
}
/**
* retrieves subject details assigned to an user
* @author Sreejith S R
* @version 15-11-2010 
* @param int $userId
*/
function getUserSubject($userId)
{
	
	global $dbObject;
	$dbObject->sql="select sm.subject_id, sm.subject_name, sm.subject_description from avl_subject_master sm, avl_user_subject_access_rel usr 
where subject_status='A' and usr.user_id=$userId and usr.subject_id=sm.subject_id";
	$dbObject->msg="VLDBER000074";
	return $dbObject->querySelectReturn('DB');
}
/**
* retrieves the privilege of an user
* @author Sreejith S R
* @version 15-11-2010 
* @param int $userId
*/
function getUserprivilege($userId)
{
	
	global $dbObject;
	$dbObject->sql="select rpr.privilege_id from avl_role_privilege_rel rpr, avl_role_user_rel rur where rpr.role_id=rur.role_id and rur.user_id=$userId";
	$dbObject->msg="VLDBER000075";
	$row= $dbObject->querySelectReturn('DB');
	return $row;
	
}

/**
* Retrieves subject details based on the subject id passed
* @author Vidya Mohan
* @version 20-11-2010 
* @param int $subject id
*/
function getSubjectDetails($subjectId)
{
	
	global $dbObject;
	$dbObject->sql="select subject_name, subject_description from avl_subject_master where subject_id=$subjectId;";
	$dbObject->msg="VLDBER000076";
	$row= $dbObject->querySelectReturn('DB');
	return $row;
	
}
/**
* Updates subject name and description in avl_subject_master 
* @author Vidya Mohan
* @version 20-11-2010 
* @param string $subjectId, $subjectName, $subjectDesc
*/
function updateSubject($subjectId, $subjectName, $subjectDesc)
{
	
	global $dbObject;
	$subjectName=htmlspecialchars($subjectName,ENT_QUOTES);
	$subjectDesc=htmlspecialchars($subjectDesc,ENT_QUOTES);
	$dbObject->sql="update avl_subject_master set subject_name='$subjectName', subject_description='$subjectDesc' where subject_id=$subjectId";
	$dbObject->msg="VLDBER000077";
	$row=$dbObject->queryUpdate();
	return $row;
	
}

/**
* Retrieves university_id from avl_user_master based on the user id 
* @author Vidya Mohan
* @version 20-11-2010 
* @param int $userId
*/
function getUserUniversityId($userId)
{	
	
	global $dbObject;
	$dbObject->sql="select university_id from avl_user_master where user_id=$userId";
	$dbObject->msg="VLDBER000078";
	$row=$dbObject->querySelectReturn('DB');
	return $row[0][0];
	
}

/**
* Retrives subject_name under a univerisity, when university id is given. This is to check if a subject already exist under a university. 
* @author Vidya Mohan
* @version 20-11-2010 
* @param int $universityId, string $subjectName
*/
function subjectExist($universityId,$subjectName)
{
	global $dbObject;
	$subjectName=htmlspecialchars($subjectName,ENT_QUOTES);
    $dbObject->sql="select subject_name from avl_subject_master where university_id='$universityId' and subject_name='$subjectName' and subject_status='A'";
	$dbObject->msg="VLDBER000079";
	$row=$dbObject->querySelectReturn('DB');
	return $row[0][0];
	
}
/**
* Insert new subject with name and description in avl_subject_master
* @author Vidya Mohan
* @version 20-11-2010 
* @param int $universityId, string $subjectName, $subjectDesc
*/
function addSubject($universityId, $subjectName, $subjectDesc)
{
	global $dbObject;
	$subjectName=htmlspecialchars($subjectName,ENT_QUOTES);
	$subjectDesc=htmlspecialchars($subjectDesc,ENT_QUOTES);
	$dbObject->sql="INSERT INTO avl_subject_master(subject_id,subject_name,subject_description,university_id,subject_status) 
	VALUES (NULL,'$subjectName','$subjectDesc','$universityId','A')";
	$dbObject->msg="VLDBER000080";
	$row=$dbObject->queryInsert();
	return $row;
}
/**
* Update subject status as 'D' in avl_subject_master based on the subject id passed
* @author Vidya Mohan
* @version 20-11-2010 
* @param  int $subjectId
*/
function deleteSubject($subjectId)
{
	global $dbObject;
	$dbObject->sql="UPDATE avl_subject_master SET subject_status='D' WHERE subject_id='$subjectId'";
	$dbObject->msg="VLDBER000081";
	$row=$dbObject->queryUpdate();
	return $row;
}
/**
* get all university details
* @author Antiha B
* @version 21-11-2010 
* @param NULL 
*/
function getUniversityDetails()
{
	global $dbObject;
	$dbObject->sql="select university_id, university_name from avl_university_master;";
	$dbObject->msg="VLDBER000082";
	$row= $dbObject->querySelectReturn('DB');
	return $row;
}

/**
* Retrieves user details based on the university id passed
* @author Vidya Mohan
* @version 22-11-2010 
* @param int $universityId
*/
function getUniversityUserDetails($universityId)
{
	
	global $dbObject;
	$dbObject->sql="select user_id,user_name,email_id from avl_user_master where university_id='$universityId';";
	$dbObject->msg="VLDBER000083";
	$row= $dbObject->querySelectReturn('DB');
	return $row;
	
}
/**
* Retrieves user details based on the university id passed
* @author Vidya Mohan
* @version 22-11-2010 
* @param int $userId
*/
function getUserSubjectDetails($userId)
{
	
	global $dbObject;
	$dbObject->sql="select ausar.subject_id,asm.subject_name from avl_user_subject_access_rel ausar,avl_subject_master asm 
	where asm.subject_id=ausar.subject_id and ausar.user_id='$userId' and asm.subject_status='A' ";	 
	$dbObject->msg="VLDBER000084";
	$row= $dbObject->querySelectReturn('DB');
	return $row;
	
}
/**
* Insert user with new subject in avl_user_subject_access_rel
* @author Anitha
* @version 22-11-2010 
* @param $user_id, $subject_id
*/
function addUserSubject($userId, $subjectId)
{
	global $dbObject;
	$dbObject->sql="insert into avl_user_subject_access_rel(user_id, subject_id) values ('$userId','$subjectId')";
	$dbObject->msg="VLDBER000085";
	$row=$dbObject->queryInsert();
	return $row;
}

/**
* Retrieves role details based on the user id passed
* @author Vidya Mohan
* @version 22-11-2010 
* @param int $userId
*/
function getUserRoleDetails($userId)
{
	
	global $dbObject;
	$dbObject->sql="select rur.role_id,rm.role_name from avl_role_user_rel rur,avl_role_master rm where rur.role_id=rm.role_id and rur.user_id='$userId'";
	$dbObject->msg="VLDBER000086";
	$row= $dbObject->querySelectReturn('DB');
	return $row;
	
}
/**
* Retrieves role details 
* @author Vidya Mohan
* @version 22-11-2010 
* @param NULL
*/
function gerAllRoleName(){
	
	global $dbObject;
	$dbObject->sql="select role_id,role_name from avl_role_master;";
	$dbObject->msg="VLDBER000087";
	$row= $dbObject->querySelectReturn('DB');
	return $row;
	
}

/**
* Updates role in avl_role_user_rel
* @author Vidya Mohan
* @version 22-11-2010 
* @param int $userId, $roleId
*/
function updateUserRole($userId, $roleId)
{
	global $dbObject;
	$dbObject->sql="update avl_role_user_rel set role_id='$roleId' where user_id=$userId";	
	$dbObject->msg="VLDBER000088";
	$row=$dbObject->queryUpdate();
	return $row;
}

/**
* Delete from avl_user_subject_access_rel
* @author Vidya Mohan
* @version 23-11-2010 
* @param int $userId
*/
function deleteUserSubject($userId)
{
	global $dbObject;
	$dbObject->sql="DELETE FROM avl_user_subject_access_rel WHERE user_id='$userId' ";	
	$dbObject->msg="VLDBER000089";
	$row=$dbObject->queryDelete();
	return $row;
}
/**
* Insert content to avl_user_subject_access_rel
* @author Vidya Mohan
* @version 23-11-2010 
* @param int $userId, $subjectId
*/
function insertUserSubject($userId, $subjectId)
{
	global $dbObject;
	$dbObject->sql="INSERT INTO avl_user_subject_access_rel (user_id, subject_id) VALUES ('$userId', '$subjectId')";
	$dbObject->msg="VLDBER000090";
	$row=$dbObject->queryInsert();
	return $row;
}
/**
* Retrieves content_id,content_type_id,content_type_name
* @author Vidya Mohan
* @version 29-11-2010 
* @param int $experimentId
*/
function getContentTypeDetails($experimentId)
{
	
	global $dbObject;
	$dbObject->sql="select cd.content_id,cd.content_type_id,ctm.content_type_name,cd.content_type_sequence from avl_content_details cd, avl_content_type_master ctm 
	where cd.content_type_id=ctm.content_type_id and cd.experiment_id='$experimentId' order by cd.content_type_sequence";
	$dbObject->msg="VLDBER000091";
	$row=$dbObject->querySelectReturn('DB');
	return $row;
	
}
/**
* Updates content_type_sequence in avl_content_details
* @author Vidya Mohan
* @version 29-11-2010 
* @param int $sequence,$contentId
*/
function updateContentTypeDetails($sequence,$contentId)
{
	global $dbObject;
	$dbObject->sql="update avl_content_details set content_type_sequence=$sequence where content_id=$contentId ";	
	$dbObject->msg="VLDBER000092";
	$row=$dbObject->queryUpdate();
	return $row;
}
/**
* Retrieves experiment_name from avl_experiment_master	
* @author Vidya Mohan
* @version 01-12-2010 
* @param int experimentId
*/
function getRemoteTriggerExperimentName($experimentId)
{
	
	global $dbObject;
	$dbObject->sql="select experiment_name from avl_experiment_master where experiment_id=$experimentId and experiment_status='A' and experiment_type_id=3";
	$dbObject->msg="VLDBER000093";
	$row=$dbObject->querySelectReturn('DB');
	return $row[0][0];
	
}
/**
* Insert experiment_id,URL,status into avl_remote_trigger_equipment_master 
* @author Vidya Mohan
* @version 01-12-2010 
* @param int $experimentId,$experimentURL
*/
function addRemoteEquipmentDetails($experimentId,$experimentURL)
{
	global $dbObject;
	$dbObject->sql="INSERT INTO avl_remote_trigger_equipment_master(equipment_id,experiment_id,URL,status) VALUES (NULL,$experimentId,'$experimentURL','A')";
	$dbObject->msg="VLDBER000094";
	$row=$dbObject->queryInsert();
	return $row;
}

/**
* Insert experiment_id,duration into avl_remote_trigger_details
* @author Vidya Mohan
* @version 01-12-2010 
* @param int $experimentId,$exptDuration
*/
function addRemoteTriggerDetails($experimentId,$exptDuration)
{
	global $dbObject;
	$dbObject->sql="INSERT INTO avl_remote_trigger_details (experiment_id,duration) VALUES ($experimentId,$exptDuration);";
	$dbObject->msg="VLDBER000095";
	$row=$dbObject->queryInsert();
	return $row;
}
/**
* Retrieves URL from avl_remote_trigger_equipment_master	
* @author Vidya Mohan
* @version 02-12-2010 
* @param null
*/
function getRemoteTriggerURL()
{
	
	global $dbObject;
	$dbObject->sql="select URL from avl_remote_trigger_equipment_master where status='A'";
	$dbObject->msg="VLDBER000096";
	$row=$dbObject->querySelectReturn('DB');
	return $row;
	
}

/**
* For getting privilege details.
* @author Anitha
* @version 30-11-2010 
* @param null
*/
function getPrivilegeDetails()
{
	global $dbObject;
	$dbObject->sql="select privilege_id, privilege_name from avl_privilege_master";	
	$dbObject->msg="VLDBER000094";
	$row=$dbObject->querySelectReturn();
	return $row;
}

/**
* For getting privilege id for each role using role_id.
* @author Anitha
* @version 1-12-2010 
* @param int $role_id
*/
function getPrivilegeDetailsOfRole($role_id)
{
	global $dbObject;
	$dbObject->sql="select RP.privilege_id, PM.privilege_name from avl_role_privilege_rel RP, avl_privilege_master PM 
	where RP.privilege_id=PM.privilege_id and role_id='$role_id'";	
	$dbObject->msg="VLDBER000095";
	$row=$dbObject->querySelectReturn();
	return $row;
}


/**
* Delete delete privileges from avl_role_privilege_rel
* @author Anitha
* @version 03-12-2010 
* @param int $role_id
*/
function deleteRolePrivilege($role_id)
{
	global $dbObject;
	$dbObject->sql="delete from avl_role_privilege_rel where role_id='$role_id';";
	$dbObject->msg="VLDBER000096";
	$row=$dbObject->queryDelete();
	return $row;
}

/**
* Insert new role_id and privilege_id in avl_role_privilege_rel
* @author Anitha
* @version 11-08-2010 
* @param int $role_id, $add_privilege
*/
function addRolePrivileges($role_id, $add_privilege)
{
	global $dbObject;
	$dbObject->sql="INSERT INTO `avl_role_privilege_rel` (`role_id`,`privilege_id`) VALUES $add_privilege";
	$dbObject->msg="VLDBER000097";
	$row=$dbObject->queryInsert();
	return $row;
}

?>