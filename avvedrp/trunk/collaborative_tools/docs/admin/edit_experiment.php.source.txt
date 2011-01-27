<?php 
/**
	* This files provides the user interface to edit an experiment details such as adding new tab, rearranging tabs, deleting tabs, editing the content in a tab, chnaging the content type of a tab to link or text etc.
	* @author Sreejith S R 
	* @version 30-08-2010
*/
?>
<link rel="stylesheet" href="tabber/example.css" TYPE="text/css" MEDIA="screen">
<link rel="stylesheet" href="tabber/example-print.css" TYPE="text/css" MEDIA="print">

<style>
h2{
 color:#F09;
}

</style>
<script language="javascript">

function show_image(filePath)
{
		//alert(fileName);
document.getElementById("select_image").src=filePath;
document.getElementById('PopUp').style.display = 'none' 

}

function setVisibility(id, visibility) {
document.getElementById(id).style.display = visibility;
}

function checkSpecialCharacter(fieldName)
{
	var iChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?";
	for (var i = 0; i < fieldName.value.length; i++) 
	{
		if (iChars.indexOf(fieldName.value.charAt(i)) != -1) {
			alert ("Special characters are not allowed.");
			fieldName.focus();
			return false;
		}
	}
	return true;
}
function checkTab(buttonName)
{
	var buttonName;
	
	if(buttonName=='editTab')
	{
		var tabName=document.getElementById('editTabName');
		var specialChar=checkSpecialCharacter(tabName);
		if(!specialChar)
		{
			return false;
		}
		//
		if(tabName.value=="")
		{
			alert("Tab name can not be left blank");
			tabName.focus();
			return(false);
		}
		
	}
	if(buttonName=='addTab')
	{
		var tabName=document.getElementById('addTabName');
		if(tabName.value=="")
		{
			alert("Please provide a new tab name.");
			tabName.focus();
			return(false);
		}
		var specialChar=checkSpecialCharacter(tabName);
		if(!specialChar)
		{
			return false;
		}
		//
		
		
	}
	if(buttonName=='deleteTab')
	{
		var answer = confirm("You have selected to delete this tab. Pree 'ok' to continue")
		if(!answer)
		{
			return false;
		}
		
	}
	//	
	return true;
	
}
function show() {

	document.form1.action = '#2';
	
}

</script>

  

<?php
echo "<link href=\"".getThemeCss('admin_tab.css')."\" rel=\"stylesheet\" type=\"text/css\">";
include_once("fckeditor/fckeditor.php");
$allTopic="";
//Getting all active subject from avl_subject_master
include('get_subject_include.php');
//
extract($_POST);
//
if ($subjectId=='') 
{
	$subjectId=$_GET['subject']; 
}

if ($topicId=='') 
{
	$topicId=$_GET['topicId']; 
}
if ($subTopicId=='') 
{
	$subTopicId=$_GET['subTopicId']; 
}
if ($experimentId=='') 
{

	$experimentId=$_GET['exp']; 
}
//
if($postTabId=="")
{
	$cnt=$_GET['cnt'];
}
else
{
	$cnt=$postTabId;
}
//
if($edit_tabSequence)
{	
	$cntSelTab = count($selTab); 
	if($cntSelTab == count(array_unique($selTab)))
	{
		for($m=0;$m<$cntSelTab;$m++){	
			updateContentTypeDetails($selTab[$m],$tabSeqContentId[$m]);
			$msg = "Saved";
		}
	}
	else 
	{
		$msg = "Same order number can not be given for more than one tabs.";
	}
}
if($_POST['editTab'])
{
	$checkTypeInExp=checkContentTypeExperiment($editTabName, $experimentId);
	if(count($checkTypeInExp)>0)//if given name already exist in same experiment
	{
		$editMsg="Sorry same tab already exist in this experiment, try different name.";
	}
	else
	{
		if($_POST['editTab']!="")
		{
			updateContentTypeName($cnt, $editTabName);
		}
	}
}
if($_POST['inputTypeSubmit'])
{
		updateLinkStatus($experimentId, $cnt, $inputType);
}
if($_POST['deleteTab'])
{	
		$contentIdDetails=getContentIdAndStatus($cnt, $experimentId);
		$contentId=$contentIdDetails[0][0];		
		$contentExternal=$contentIdDetails[0][1];
		if($contentExternal=='N')
		{
			//description
			deleteContentDescription($contentId);
			
		}
		else
		{
			deleteContentLink($contentId);
		}
		deleteContentDetails($contentId);
		//deleteContentType($cnt);
		$cnt=$defaultContentType[0][0];
	
}
if($_POST['submit1'])
{
	$contDetails=getContentIdAndStatus($cnt, $experimentId);
	//
	
	if(array_key_exists('FCKeditor1', $_POST))
	{
		$sValue = stripslashes($_POST['FCKeditor1'] );
		$foldername='/'.PROJECT_ROOT.'/';
		$sValue=str_replace($foldername,DB_FOLDER_NAME,$sValue);
		$contentDescription=getContentDescription($contDetails[0][0]);
		if($contentDescription===false)
		{
			//insert
			updateLinkStatus($experimentId, $cnt, 'N');
			insertContentDesc($contDetails[0][0], $sValue);
		}
		else
		{
			//update
			updateLinkStatus($experimentId, $cnt, 'N');
			updateContentDescription($contDetails[0][0], $sValue);
		}
		
		
	}
//	
	if(array_key_exists('extLinkText', $_POST))
	{		
		$contentLink=getContentLink($contDetails[0][0]);
		if($contentLink===false)
		{
			//insert
			updateLinkStatus($experimentId, $cnt, 'Y');
			insertContentLink($contDetails[0][0], $extLinkText);
		}
		else
		{
			//update
			updateLinkStatus($experimentId, $cnt, 'Y');
			updateContLinks($contDetails[0][0], $extLinkText);			
		}	
	}	
}


$defaultContentType=getDefaultContentType();
if($cnt=="")
{
	$cnt=$defaultContentType[0][0];
}
?>
<form name="form1" id="form1" method="post" action="#">
<table border="1" width="100%" class="noBorderTable">
<tr>
    <td width="50%" valign="top">
        <table width="100%" border="1"  class="noBorderTable">
        
        
        <tr class="textHead2">
        <td width="40%" colspan="3"><?php echo SEL_SUBJECT?></td>
        <td>
        <select style="width:252px" name="subjectId" size="1" id="subjectId" onChange="this.form.submit()">
        <?php 
        //displaying subject selection drop down
        for($i=0; $i<count($allSubject); $i++){
            $selsubId="";
            if($allSubject[$i][0]==$subjectId)
                {
                    $selsubId='selected';
                } 
                else
                {$selsubId='';}?>
        <option <?php echo $selsubId?> value="<?php echo $allSubject[$i][0] ?>" ><?php echo $allSubject[$i][1] ?></option>
        <?php }?>    
        </select></td>
        </tr>
        <?php 
        //while acessing the page from side menu, selecting the 1st subject to display topic
        if($subjectId=='')
        {
        $subjectId=$allSubject[0][0];
        }
        $allTopic="";
		if($subjectId)
		{
       		$allTopic=getAllTopics($subjectId);
        ?>
        <tr class="textHead2">
        <td colspan="3"><?php echo SEL_TOPIC?></td>
        <td>
        <select style="width:252px" name="topicId" size="1" id="topicId" onChange="this.form.submit()">
        <?php 
        //displaying topic selection drop down
        for($j=0; $j<count($allTopic); $j++){
            $seltopicId="";						
            if($allTopic[$j][0]==$topicId)
                {
                    $seltopicId='selected';
                } 
                else
                {$seltopicId='';}?>
        <option <?php echo $seltopicId?>  value="<?php echo $allTopic[$j][0] ?>" ><?php echo $allTopic[$j][1] ?></option>
        <?php }
		}
		?>    
        </select>                
        </td>
        </tr>
        <?php 
        //if changing the subject only, selecting 1st topic from topic list
        if($_SESSION['sess_subject']!=$subjectId)
        {
        $_SESSION['sess_subject']="";
        $topicId=$allTopic[0][0];
        }
        $_SESSION['sess_subject']=$subjectId;
        //while acessing the page from side menu, selecting the 1st topic to display subtopic
		if($topicId=='')
        {
        	$topicId=$allTopic[0][0];
        }
		if($topicId)
        $allSubTopic=getAllSubTopics($topicId);
		$subTopicId=$allSubTopic[0][0];
		?>
        <tr class="textHead2">
        <td><input type="hidden" value="<?php echo $subTopicId; ?>" name="subTopicId" id="subTopicId" /></td>
      <!--  <td colspan="3"><?php echo SEL_SUB_TOPIC?></td>
        <td>
        <select style="width:252px" name="subTopicId" size="1" id="subTopicId" onChange="this.form.submit()">
        <?php 
        //displaying sub topic selection drop down
        $j=0;
        for($j=0; $j<count($allSubTopic); $j++){
            $selsubTopicId="";						
            if($allSubTopic[$j][0]==$subTopicId)
                {
                    $selsubTopicId='selected';
                } 
        
                else
                {$selsubTopicId='';}?>
        <option <?php echo $selsubTopicId?>  value="<?php echo $allSubTopic[$j][0] ?>" ><?php echo $allSubTopic[$j][1] ?></option>
        <?php }?>    
        </select>               
        </td> --> 
        </tr>          
        <!-- -->
        <?php
        if($_SESSION['sess_topic']!=$topicId)
        {
        $_SESSION['sess_topic']="";
        $subTopicId=$allsubTopic[0][0];
        }
        $_SESSION['sess_topic']=$topicId;
        if($subTopicId=='')
        {
        $subTopicId=$allSubTopic[0][0];
        }
        //
        if($subTopicId)
        {	
        $allExperiment=getExperimentDetails($subTopicId);
        }	
        //
        if($_SESSION['sess_subTopicId']!=$subTopicId)
        {        
        	$_SESSION['sess_subTopicId']="";
        
        	//$experimentId=$allExperiment[0][0];
        }
        $_SESSION['sess_subTopicId']=$subTopicId;
        //
        if($experimentId=='')
        {
        	$experimentId=$allExperiment[0][0];
        }
        //
        ?>
        <!-- -->
        <tr class="textHead2">
        <td colspan="3"><?php echo SEL_EXPERIMENT?></td>
         <td>
        <select style="width:252px" name="experimentId" size="1" id="experimentId" onChange="this.form.submit()">
        <?php 
            //displaying experimet selection drop down
            $j=0;
            for($j=0; $j<count($allExperiment); $j++){
                $selExperiment="";						
                if($allExperiment[$j][0]==$experimentId)
                    {
                        $selExperiment='selected';
                    } 
                    else
                    {$selExperiment='';}?>
        <option <?php echo $selExperiment?>  value="<?php echo $allExperiment[$j][0] ?>" ><?php echo $allExperiment[$j][1] ?></option>
        <?php }?>    
        </select>                    </td>
        </tr>
        </table>
    </td>
    
    <?php 
	if($_GET[exp]!="")
	{
		if($experimentId!=$_GET[exp])
		{
			$cnt=$defaultContentType[0][0];
		}
	}
	if($_POST['tabAdd'])
	{
		$addTabName=trim($addTabName);
		$addTabName=trim($addTabName,'!@#$%^&*()+=-[]\\\';,./{}|\":<>?');
		if($addTabName!="")
		{
			$msg="";
			//add in type master and details
			$checkTypeInExp=checkContentTypeExperiment($addTabName, $experimentId);
			if(count($checkTypeInExp)>0)//if given name already exist in same experiment
			{
				$addMsg="Sorry same tab already exist in this experiment";
			}
			else
			{
				insertContentTypeMaster($addTabName);
				$newTypeId=$dbObject->getLastInsertId();
				insertContentDetailsSingle($newTypeId,$experimentId, $newInputType);
				$addMsg="New Tab added";
				$cnt=$newTypeId;
			}
		}
		else{$add_msg="Tab name can not be left blank.";}	
	}
	else
	{
		$addMsg="";
	}

if(checkContentTypeMandatory($cnt)=='N')
{
	$disabled="";
}
else
{
	$opacity="opacity: .4;";
	$disabled="disabled";
}
/*if($cnt==SIMULATOR_ID)
{
	$disabled_delete="disabled=\"disabled\"";
}*/
?>

    <td width="50%">
    <?php
	if($experimentId)
	{
	?>	
    	

    	<table border="1" width="100%" >
   	    <!--<tr align="right">
            		<td class="textHead2" valign="top"><div align="right" >New Tab:</div></td>
                	<td align="left"><input type="text" name="addTabName" id="addTabName"  /></td>
                	<td align="left" width="20%"><input type="submit" name="tabAdd" id="tabAdd" style="background-image:url(<?php echo getThemeImage('add_button.png')?>);  width:65px; height:21px; background-color:Transparent; border:thin; color:Transparent; " onclick="return checkTab('addTab');" title="Add Tab"  />			                    
                    </td>
                    
			</tr>-->
            <!--<tr>
            	<td colspan="3" align="right"><p class="textRed"><?php echo $msg?></p></td>
            </tr>-->
            <tr>
                <td  class="textHead2" width="40%" valign="top"><div align="right">Edit Tab name:</div></td>
                <td align="left">                        
                    <input type="text"  maxlength="17" name="editTabName" id="editTabName" value="<?php echo getExperimentItemName($cnt); ;?>" <?php echo $disabled; ?> />
                </td>
                <td align="left" width="15%">
                	
                        <input name="editTab" id="editTab" type="submit" value="." style="background-image:url(<?php echo getThemeImage('edit_button.png')?>);  width:67px; height:23px; border:hidden; background-color:Transparent; border:none; color:Transparent; border:thin; <?php echo $opacity;?>" title="Edit Tab" onclick="return checkTab('editTab');" <?php echo $disabled; ?>/>   
                                      
                    
              </td> 
			</tr>       
            <tr>
                <td colspan="3">
                <?php if($editMsg){?>
                           <div align="right" class="textRed"><?php echo $editMsg;?></div>
                           <?php }?>
                </td>
            </tr>    
  				
                <?php 
						$content_details=getContentIdAndStatus($cnt,$experimentId); 
						if($content_details[0][1]=='Y')
						{
								$checked1="checked=\"checked\"";
						}
						elseif($content_details[0][1]=='N')
						{
								$checked2="checked=\"checked\"";
						}
						?>   
                <tr>
                	<td class="textHead2">
                    	<div align="right">Change Data type:</div>
                    </td>
                    <td align="left">
                     <input type="radio" <?php echo $checked3?> name="inputType" id="inputType"  disabled="disabled" />                     
                      Q / A&nbsp;&nbsp;&nbsp;
                      <input type="radio" <?php echo $checked1?> name="inputType" id="inputType" value="Y" /> Link &nbsp;&nbsp;&nbsp;
                    <input type="radio" <?php echo $checked2?> name="inputType" id="inputType" value="N" /> Text 
          </td>
                    <td align="left" width="15%">    
                    	<input type="hidden" name="postTabId" id="postTabId" value="<?php echo $cnt; ?>" />                                       
                      <input type="submit" name="inputTypeSubmit" id="inputTypeSubmit" value="." title="Choose the input type and click here to change" alt="Choose the input type and click here to change"  style="background-image:url(<?php echo getThemeImage('change_button.png')?>);  width:67px; height:23px; background-color:Transparent; border:none; color:Transparent; "/>
                        
                    </td>                   
                </tr>   
                             
                <tr> 
                	<td><a href="?pg=EDIT_EXP&editTabSeq=1&subject=<?php echo $subjectId?>&topicId=<?php echo $topicId?>&subTopicId=<?php echo $subTopicId?>&exp=<?php echo $experimentId;?>"></a><a href="?pg=EDIT_EXP&editTabSeq=1&subject=<?php echo $subjectId?>&topicId=<?php echo $topicId?>&subTopicId=<?php echo $subTopicId?>&exp=<?php echo $experimentId;?>"><img src="<?php echo getThemeImage('re_arrange_tabs.png')?>" title="Re Arrange Tab Sequence" alt="Re Arrangr Tab Sequence"/></a></td>	
                    <td></td>  
                    <td align="left" width="15%">        
                    <input name="deleteTab" id="deleteTab" type="submit" value="." nMouseOver="setVisibility('deleteTab', 'inline');" style="background-image:url(<?php echo getThemeImage('delete_button.png')?>);  width:67px; height:23px; background-color:Transparent; border:none; color:Transparent; " title="Delete Tab" onclick="return checkTab('deleteTab');" />
                    
                    </td>
                </tr>
                            
						
                    
        </table>
        <!--<div align="right" style="padding-top:5px">
        <a href="?pg=EDIT_EXP&editTabSeq=1&subject=<?php echo $subjectId?>&topicId=<?php echo $topicId?>&subTopicId=<?php echo $subTopicId?>&exp=<?php echo $experimentId;?>"><img src="<?php echo getThemeImage('re_arrange_tabs.png')?>" title="Re Arrange Tab Sequence" alt="Re Arrangr Tab Sequence" border="0"  /></a>
        
         &nbsp;&nbsp;
         <a href="?pg=EDIT_EXP&newTabAdd=1&subject=<?php echo $subjectId?>&topicId=<?php echo $topicId?>&subTopicId=<?php echo $subTopicId?>&exp=<?php echo $experimentId;?>"> <img src="<?php echo getThemeImage('add_new_tab.png')?>" title="Add new tab" alt="Add new tab" border="0"   /> </a>
         
      </div>-->
	<?php
    }
    ?>
    </td>
</tr>

</table><br />
<!--<div class="textRed"><?php echo $addMsg; ?></div>-->

<?php
if($_GET['editTabSeq']!="" and count($_POST)<1 )
{?>
	<table border="0" width="100%" align="center" bgcolor="#FFFFF0" style="border:dotted">
    <tr>
        <td><?php include('edit_tab_sequence_include.php');	?></td>
    </tr>
	</table>    
        
<?php }

if($_GET['newTabAdd']!="" and count($_POST)<1 )
{
	
	?>
	<table border="0" width="100%" align="center" bgcolor="#FFFFF0" style="border:dotted">
     <tr>        
        <td colspan="2" class="textHead1" valign="top"><div align="center" >Add New Tab</div></td>
     </tr>   
    <tr>        
      <td width="15%" class="textHead2" valign="top"><div align="right" > Tab Name:</div></td>
        <td align="left"><input type="text" maxlength="17" name="addTabName" id="addTabName"   /></td>              
    </tr>
    
    <tr>
        <td class="textHead2"><div align="right" >Data type:</div></td>
        <td align="left">
            <input type="radio"  name="newInputType" id="newInputType"  disabled="disabled" /> 
            Q / A &nbsp;&nbsp;&nbsp;
            <input type="radio"  name="newInputType" id="newInputType" value="Y" /> Link &nbsp;&nbsp;&nbsp;
            <input type="radio" checked="checked" name="newInputType" id="newInputType" value="N" /> Text 
        </td>
    </tr>
    <tr>
        <td class="textHead2"><div align="right">Tab Icon:</div></td>
        <td align="left">
        	
           <a onmouseover='this.style.cursor="pointer" ' onfocus='this.blur();' onclick="document.getElementById('PopUp').style.display = 'block' " ><img name="select_image" id="select_image" alt="Click to change" title="Click to change" src="images/temp.gif" onclick="document.getElementById('PopUp').style.display = 'block'" />
            </a>
            <div id='PopUp' style='display: none; position: absolute; left: 55%; top: 33%; border: solid black 1px; padding: 10px; background-color: rgb(255,255,225); text-align: justify; font-size: 12px; width: 15%;'>
            
            <?php  include('admin/icon_search_include.php'); ?>
            <br />
                <div style='text-align: right;'><a onmouseover='this.style.cursor="pointer" ' style='font-size: 12px;' onfocus='this.blur();' onclick="document.getElementById('PopUp').style.display = 'none' " ><span style="text-decoration: underline;">Close</span></a>
                </div>
            </div>        
        </td>
        </tr>
    <tr >
    	<td></td>
   	  <td align="left" width="20%"><input type="submit" name="tabAdd" id="tabAdd" value="." onclick="return checkTab('addTab');" title="Add Tab" style="background-image:url(<?php echo getThemeImage('save.png')?>);  width:50px; height:23px; background-color:Transparent; border:none; color:Transparent; "  />&nbsp;&nbsp;
      
<input type="button" value="." id="cancel" name="cancel" onclick="window.location.href='?pg=EDIT_EXP&subject=<?php echo $subjectId; ?>&topicId=<?php echo $topicId; ?>&subTopicId=<?php echo $subTopicId; ?>&exp=<?php echo $experimentId; ?>'"  style="background-image:url(<?php echo getThemeImage('cancel.png')?>);  width:50px; height:23px; background-color:Transparent; border:none; color:Transparent; " />			                    
        </td> 
    </tr>
</table>
<?php }
?>

             
      
 <table border="1" width="100%" class="noBorderTable">            
                 <?php 
				 	$oFCKeditor = new FCKeditor('FCKeditor1') ;
					$oFCKeditor->Height		= '450' ;
					$oFCKeditor->BasePath = 'fckeditor/' ;
					$_SESSION['sess_projectFolder']=PROJECT_ROOT;
					//
				if($experimentId)
				{
				$experimentContentType=getAllContentItems($experimentId);// this function gets the default content type from database.
				}
				if($experimentContentType)
				{
					$i=0;
					for($i=0; $i<count($experimentContentType);$i++)
					{
						$arrContentItem[$i]=$experimentContentType[$i][1];
						$contentTypeName=getExperimentItemName($cnt);
						if($contentTypeName==$arrContentItem[$i])
						{
							$currentItem=$i;
						}
									
					}
					?>
					<tr class="text">
					<a name="2" id="2"></a>
                        <td colspan="5">
                        
                        <div id="ddtabs4" class="admintabs"><ul >           
                             <?php
                             $i=0;?>
							 
                             <?php
                             for($i=0;$i<count($arrContentItem);$i++)
                             { 
                              $current_sel="";
                             if($cnt==$experimentContentType[$i][0])
                             {
                                 $current_sel="current";
                             }
                             ?>
                             
                              <li class="<?php echo $current_sel ?>"><a href="?pg=EDIT_EXP&subject=<?php echo $subjectId?>&topicId=<?php echo $topicId?>&subTopicId=<?php echo $subTopicId?>&exp=<?php echo $experimentId?>&cnt=<?php echo $experimentContentType[$i][0]; ?>" ><span ><?php echo $arrContentItem[$i]; ?></span></a></li>
                              <?php } ?>  
                               <li class="<?php echo $current_sel ?>"><a href="?pg=EDIT_EXP&newTabAdd=1&subject=<?php echo $subjectId?>&topicId=<?php echo $topicId?>&subTopicId=<?php echo $subTopicId?>&exp=<?php echo $experimentId;?>" ><span wi>+</span></a></li>            
                        </ul></div>
                        
						<?php 
						if($content_details[0][1]=='Y')
                        {
							$content_Link=getContentLink($content_details[0][0]);
							?>
							<div><b><p>Add/Edit external link: <input type="text" name="extLinkText" id="extLinkText" value="<?php echo $content_Link;?>" style="width:450px"/>&nbsp;<input name="submit1" id="submit1" type="submit" value="." onclick="show();" style="background-image:url(<?php echo getThemeImage('save_link.png')?>);  width:60px; height:23px; background-color:Transparent; border:none; color:Transparent; "  /></p>
							
							</b>
							<br />
							<?php
							displayLink($content_Link);?>
							</div>
                        <?php 
						}
                        else
                        { 
						?>
                        <div><?php 
                            
                            $ContentDescr=getContentDescription($content_details[0][0]);
                            $foldername='/'.PROJECT_ROOT.'/';
                            $oFCKeditor->Value=str_replace(DB_FOLDER_NAME,$foldername,htmlspecialchars_decode($ContentDescr));
                            $oFCKeditor->Create() ;
							echo "<br>";							
						//echo "<div >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name=\"submit1\" id=\"submit1\" type=\"submit\" value=\"Submit\" style=\"width:60px\" onclick=\"show();\" /></div>";
							?>
                        <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="submit1" id="submit1"  value="." onclick="show();" style="background-image:url(<?php echo getThemeImage('save_data.png')?>);  width:60px; height:23px; background-color:Transparent; border:none; color:Transparent; " />
                        </div>
                        </div>
                        <?php } ?>
                        </td>
					</tr>
		 <?php 
		 }
		 ?>
            </table>	
        </form>	
       
