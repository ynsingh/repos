<%@ page import="java.lang.String" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

 <head>
 <g:javascript library="prototype" />
 <title>${grailsApplication.config.page_title}</title> 
 <link rel="shortcut icon" type="image/x-icon" href="${hostname}/aell/images/favicon.ico">
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="contentAdmin" />
<!-- include jQuery library -->
<script type="text/javascript" src="${createLinkTo(dir:'js',file:'jquery.tablednd_0_5.js')}"></script>
<script type="text/javascript" src="${createLinkTo(dir:'js',file:'jquery-ui-1.8.7.custom.min.js')}"></script>
<link rel="stylesheet" type="text/css" href="${resource(dir:'css',file:'dragStyle.css')}" />
<link rel="stylesheet" type="text/css" href="${resource(dir:'css',file:'quiz.css')}" />
<script type="text/javascript" src="${createLinkTo(dir:'js',file:'quizLib.js')}"></script>
<script type="text/javascript" src="${createLinkTo(dir:'js',file:'jquery.shuffle.js')}"></script>
<script type="text/javascript" src="${createLinkTo(dir:'js',file:'editExp.js')}"></script>

<style>
h2{
/* color:#F09;*/
}

.button
{
    color:#2E8AE6;
    font-weight: bold;
    font-family: Verdana,Geneva,sans-serif;
    font-size: 12px;
    padding: 1px 5px;
    background-color: #C1C1C1;
    border:3px #C1C1C1;
    border-style:ridge;
    cursor:pointer;
}

.button-upPub{
    color:black;
    font-weight: bold;
    font-family: Verdana,Geneva,sans-serif;
    font-size: 12px;
    padding: 1px 5px;
    background-color: #C1C1C1;
    border:3px #C1C1C1;
    border-style:ridge;   
    cursor:pointer;
}
.list li{
    background-color:#666;
    color:#FFF;
    list-style:none;
    float:left;
    display:inline;
    margin:5px;
    padding:4px;
    font-family:Verdana, Geneva, sans-serif;
    font-size:11px;
}

.fibdndquiztext {

    width: 536px; 
    height: 83px;
}

#controls{
float:right;
font-family: Verdana, Geneva, sans-serif;
font-size: 11px;
}
</style>
<script type="text/javascript">


</script>
  </head>
<body> 
<div id="innerData">
<table border="1" width="100%" class="noBorderTable">
<tbody>
<tr>
  <td width="1%" bgcolor="#FFCC00">&nbsp;</td>
  <td bgcolor="#FFFF99" class="tblAdminTemplate">${grailsApplication.config.edit_contents}</td>
</tr>

<tr>
    <td colspan="2" valign="top">
<table border="1" width="100%" class="noBorderTable">
	<tbody>
	<tr>
	
	    <td valign="top">
	 		<g:if test="${flash.message}">
	           	 <div class="message"><img src="${hostname}/aell/images/tick.gif" title="Success" height="20" width="20">${flash.message}</div>
            </g:if>
            <g:if test="${flash.error}">
    	       	 <div class="error"><img src="${hostname}/aell/images/wrong.gif" title="Failure" height="20" width="20">${flash.error}</div>
            </g:if>
         </td>
         </tr>
	<tr>
	
	
	    <td width="48%" valign="top">
	        <table width="100%" border="1" class="noBorderTable">
	        <tbody>
	        <tr>
	        <td width="40%" colspan="3" class="textHead2" style="padding-left:6px;">University Name</td>
	        <td class="textHead2" style="padding-left:6px;">${session.university.universityName}</td>
	        </tr>
	        <tr class="textHead2">
	        <td width="40%" colspan="3">Select ${grailsApplication.config.module}</td>
	        <td>
					<g:form action="index" name="subjectSelectForm" method="post">
					<g:select name="subjectId"
		          		from="${subjectList}" optionValue="subjectName"
		          		optionKey="id" size="1" onChange="getTopics();" value="${session.selectedSubjectId}"/>
		          	</g:form>		        
		    </td>
	        </tr>
	        <tr class="textHead2">
	        <td colspan="3">Select ${grailsApplication.config.topic}</td>
	        <td>
					<g:form action="index" name="topicSelectForm" method="post" >
					<g:select name="topicId"
		          		from="${topicList}" optionValue="topicName"
		          		optionKey="id" size="1" onChange="getExperiments();" value="${session.selectedTopicId}"/>
		          	</g:form>	
		    </td>
	        </tr>
	        <g:if test="${contentDetails}">

	        <tr class="textHead2">
	        <td colspan="3">Select ${grailsApplication.config.sub_topic}</td>
	        <td>
					<g:form action="index" name="experimentSelectForm" method="post" >
					<g:select name="experimentId"
		          		from="${experimentList}" optionValue="experimentName"
		          		optionKey="id" size="1" onChange="getExperimentDetails();" value="${session.selectedExperimentId}"/>
		          	</g:form>		          		
		    </td>
	        </tr>
	                
	        <tr class="textHead2"><td colspan="3">Content Type</td>
	        <td align="left">
	          <g:if test="${contentDetails}">
		                <g:form action="index" name="quizSelectForm" method="post" >
					<g:select id="contentType" name="contentType"
		          		from="${contentTypes.entrySet()}" optionValue="value"  onChange="getQuizQuestions();"
		          		optionKey="key" size="1" value="${session.selectedContentTypeMode}"/>
		                 </g:form>
		       </g:if>
		    </td>
	        </tr>
	        </g:if>
	        </tbody></table>
	    </td>
	    
           <link rel="stylesheet" href="${resource(dir:'css',file:'admin_tab.css')}" />
	<g:form action="updateSequence" name="sequenceForm" method="post" >
		<input type="hidden" name="sequenceOrder" id="sequenceOrder"/>
	</g:form>
	    <td width="52%">
	    <g:if test="${contentDetails}">
	    		<table border="1" width="100%">
	   	        <tbody>
			  	<g:form action="editTabName" name="tabNameForm" method="post">
			  			                <g:if test="${contentDetails}">
			  	
		   	        <tr>
		                <td class="textHead2" width="44%" valign="top"><div align="right">Edit Tab name:</div></td>

		                
						<g:if test="${contentDetails[0].mandatory == 'Y'}">
			                <td width="41%" align="left">                        
				                  <input type="text" maxlength="30" name="editTabName" id="editTabName" value="${contentDetails[0].contentTypeName}" disabled="">
			                </td>
			                <td align="left" width="15%">
				                <input name="editTab" title="Edit Tab" id="editTab" type="submit" value="." style="background-image:url(${hostname}/aell/images/edit_button.png);  width:67px; height:23px; border:hidden; background-color:Transparent; border:none; color:Transparent; border:thin; opacity: .4;" onclick="return checkTab('editTab');" disabled="">
			                </td> 
						</g:if>
						<g:else>
						<g:if test="${contentDetails}">

						
			                <td width="41%" align="left">                        
			    	              <input type="text" maxlength="30" name="editTabName" id="editTabName" value="${contentDetails[0].contentTypeName}">
			                </td>
			                <td align="left" width="15%">
								<input name="editTab" title="Edit Tab" id="editTab" type="submit" value="." style="background-image:url(${hostname}/aell/images/edit_button.png);  width:67px; height:23px; border:hidden; background-color:Transparent; border:none; color:Transparent; border:thin; " onclick="return checkTab('editTab');">
							</td> 
							</g:if>
						</g:else>
		                 <input type="hidden" name="postTabId" id="postTabId" value="1">  
					</tr>   
					</g:if>    
				</g:form>
              <tr>
                  <td colspan="3">
                  </td>
              </tr>
													<g:form action="editTabIcon" name="tabIconForm"
														method="post">
														<g:if test="${contentDetails}">
															<tr>
																<td><input type="hidden" name="editedimage"
																	id="editedimage"
																	value="${contentDetails[0].contentTypeIcon}"></td>
															</tr>
														</g:if>
														<tr>
															<g:if test="${contentDetails}">
																<td class="textHead2"><div align="right">Edit
																		Tab Icon:</div></td>
															</g:if>
															<td><g:if test="${contentDetails}">
																	<a onmouseover="this.style.cursor=&quot;pointer&quot;"
																		onfocus="this.blur();"
																		onclick="document.getElementById('editPopUp').style.display = 'block' "
																		style="cursor: pointer;"> <img width="30px"
																		name="edit_image" id="edit_image"
																		alt="Click to change" title="Click to change"
																		src="${hostname}/aell/images/tab_icon_image/${contentDetails[0].contentTypeIcon}"
																		onclick="document.getElementById('editPopUp').style.display = 'block'"></a>
																</g:if>
																<div id="editPopUp"
																	style="display: none; position: absolute; left: 780px; top: 150px; border: solid black 1px; padding: 1%; background-color: rgb(255, 255, 225); text-align: justify; font-size: 12px; width: 17%;">
																	<table border="0">
																		<tbody>
																			<tr>
																				<g:each in="${session.fileIcons}" status="i"
																					var="fileIcon">
																					<td><img width="30px" height="30px"
																						src="${hostname}/aell/images/tab_icon_image/${fileIcon}"
																						onclick="show_edit_image('${hostname}/aell/images/tab_icon_image/${fileIcon}','${fileIcon}');"></td>
																					<g:if test="${i%5 == 0 && i != 0}">
																			</tr>
																			<tr>
																				</g:if>
																				</g:each>

																			</tr>
																		</tbody>
																	</table>
																	<br>
																		<div style="text-align: right;">
																			<a
																				onmouseover="this.style.cursor=&quot;pointer&quot; "
																				style="font-size: 12px;" onfocus="this.blur();"
																				onclick="document.getElementById('editPopUp').style.display = 'none' "><span
																				style="text-decoration: underline;">Close</span></a>
																		</div>
																</div></td>
															<td><g:if test="${contentDetails}">
																	<g:if test="${contentDetails[0].mandatory == 'Y'}">
																		<input type="submit" name="editTabIconSubmit"
																			id="editTabIconSubmit" value="."
																			title="Choose the icon and click here to change"
																			style="background-image:url(${hostname}/aell/images/change_button.png);  width:67px; height:23px; background-color:Transparent; border:none; color:Transparent; opacity: .4;"
																			disabled="">
																	</g:if>
																	<g:else>
																		<input type="submit" name="editTabIconSubmit"
																			id="editTabIconSubmit" value="."
																			title="Choose the icon and click here to change"
																			style="background-image:url(${hostname}/aell/images/change_button.png);  width:67px; height:23px; background-color:Transparent; border:none; color:Transparent; ">
																	</g:else>
																</g:if></td>
														</tr>
													</g:form>
	                <tr> 
	                	<td></td>	
	                  <td></td>
                    <td align="left" width="15%">    
	                       <tr>
	                         <td>
									           <g:form url="[controller:'editExperiment', action:'publishTab']">
									            <input name="addTabName" type="hidden" value="${contentDetails.contentTypeName}" />
									            <button type="submit" class="button">Publish</button>
									           </g:form>
									         </td>
									         <td>           
										         <g:form url="[controller:'editExperiment', action:'unPublishTab']">
	                           <input name="addTabName" type="hidden" value="${contentDetails.contentTypeName}" />
	                           <button type="submit" class="button-upPub">Un-Publish</button>
	                            </g:form>
	                         </td>
	                         <td> 
		                         <g:if test="${contentDetails}">
		                         <g:form action="deleteTab" name="tabDeleteForm" method="post">
		          			     		  <input name="deleteTab" id="deleteTab" type="submit" value="." onmouseover="setVisibility('deleteTab', 'inline');" style="background-image: url(${hostname}/aell/images/delete_button.png); width: 67px; height: 23px; background-color: transparent; border-top-style: none; border-right-style: none; border-bottom-style: none; border-left-style: none; border-width: initial; border-color: initial; color: transparent; display: inline; " title="Delete Tab" onclick="return checkTab('deleteTab');">                 
		                         </g:form>
                           </td>
                         </tr>
		                  </td>
	                </tr>
	        	     </g:if>
              <tr>
                  <td colspan="3">
                  </td>
              </tr>
	        </tbody></table></g:if>    

		    </td>
	</tr>
	
	</tbody></table>
         <!--Tabs Div closes here-->
	 		<div id="addTabForm">
		 		<g:form action="addTab" name="addTabForm" method="post" >
					<table border="0" width="100%" align="center" bgcolor="#FFFFF0" style="border:dotted">
			    		<tbody>
				    	     <tr> <td><input type="hidden" name="selectedImage" id="selectedImage" value="temp.gif"> </td></tr>
						     <tr>        
						        <td colspan="2" class="textHead1" valign="top"><div align="center">Add New Tab</div></td>
						     </tr>   
						    <tr>        
						      <td width="15%" class="textHead2" valign="top"><div align="right"> Tab Name:</div></td>
						        <td align="left"><input type="text" maxlength="30" name="addTabName" id="addTabName"></td>              
						    </tr>
						    <tr>
						        <td class="textHead2"><div align="right">Tab Icon:</div></td>       
						        <td align="left">
						        	
						            <a onmouseover="this.style.cursor=&quot;pointer&quot; " onfocus="this.blur();" onclick="document.getElementById('PopUp').style.display = 'block' ">
						            	<img name="select_image" id="select_image" alt="Click to change" title="Click to change" src="${hostname}/aell/images/temp.gif" onclick="document.getElementById('PopUp').style.display = 'block'">
						            </a>
						            <div id="PopUp" style="display: none; position: absolute; left: 580px; top: 310px; border: solid black 1px; padding: 10px; background-color: rgb(255,255,225); text-align: justify; font-size: 12px; width: 17%;">
										<table border="0">
										<tbody>
										<tr>
										<g:each in="${session.fileIcons}" status="i" var="fileIcon">
											<td>
										    <img width="30px" height="30px" src="${hostname}/aell/images/tab_icon_image/${fileIcon}" onclick="show_image('${hostname}/aell/images/tab_icon_image/${fileIcon}','${fileIcon}');">
											</td>
											<g:if test="${i%5 == 0 && i != 0}">
			     								</tr><tr>
											</g:if>
										</g:each>
										</tr>
										</tbody>
										</table>
						            	<br>
						                <div style="text-align: right;"><a onmouseover="this.style.cursor=&quot;pointer&quot; " style="font-size: 12px;" onfocus="this.blur();" onclick="document.getElementById('PopUp').style.display = 'none' "><span style="text-decoration: underline;">Close</span></a>
						                </div>
							       </div>        
							   </td>
							</tr>
						    <tr>
						    	<td></td>
						   	  	<td align="left" width="20%">
						   	  		<input type="submit" name="tabAdd" id="tabAdd" value="." onclick="return checkTab('addTab');" title="Add Tab" style="background-image:url(${hostname}/aell/images/save.png);  width:50px; height:23px; background-color:Transparent; border:none; color:Transparent; ">&nbsp;&nbsp;
									<input type="button" value="." id="cancel" name="cancel" onclick="return closeAddTabForm();" style="background-image:url(${hostname}/aell/images/cancel.png);  width:50px; height:23px; background-color:Transparent; border:none; color:Transparent; ">			                    
						      	</td> 
						    </tr>
					</tbody>
				</table>							 			
			</g:form>
		</div>
<table border="0" width="100%" class="">            
	     <tbody><tr class="text">
    
        <td colspan="5">
        <!--Creating Tabs-->
         <g:if test="${contentDetails}"> 
        <div id="ddtabs4" class="admintabs">
        <!--Div for click and draging the tabs-->
        <div class="list">                <ul class="ui-sortable">


			<g:each in="${contentTabDetails}" status="i" var="contentTab">
				<g:set var="tabClass" value="" />
        <g:set var="unpubTabClass" value="" />
				<g:if
					test="${contentTab.content_type.contentTypeName == contentDetails[0].contentTypeName}">
					<g:set var="tabClass" value="current" />
				</g:if>
        <g:if
          test="${contentTab.contentStatus != 'A'}">
          <g:set var="unpubTabClass" value="unPublished" />
        </g:if>
				<li title="Click and drag to change tab sequence"	id="arrayorder_${contentTab.id}" class="${tabClass}">
					<g:link action="index" controller="editExperiment" params="[contentId:contentTab.id, contentType:contentDetails[0].contentMode ]">
						<span class=${unpubTabClass }>
							${contentTab.content_type.contentTypeName}
						</span>
					</g:link>
				</li>
			</g:each>

														<li title="Add New Tab" class="">
                       <a href="javascript:showAddTabForm();"><span>+</span></a>
                       </li>         
                                  </ul>
            </div>        </div></g:if>   
			  <g:form action="saveTextData" name="saveTextDataForm" method="post">
    			<input id="versionstatus" name="versionstatus" type="hidden"/>
             	<div id="showT" class="MyDiv" style="display: block; ">
                    <div>
                    <g:if test="${contentDetails}">
                    
						<fckeditor:editor name="editor" width="100%" height="400">
						    <g:if test="${contentDetails[0].contentDescription != null}">
								${contentDetails[0].contentDescription.decodeHTML()}
							</g:if>
						</fckeditor:editor>
						</g:if>
					</div>
					<g:if test="${contentDetails}">

					
                    <div align="left">Revision comments: <br>
    					<input name="versionComments2" type="text" id="versionComments2" value="${contentDetails[0].versionComments}" size="40" maxlength="250">
					</div><br>
					
                    <div align="left" style="padding-left:10px; float:left"> 
						<input class="button" type="submit" name="saveData" id="saveData" value="Save &amp; Preview" title="Save &amp; Preview" onclick="saveTextContent('S');"> 
						<input class="button" type="submit" name="saveSendForReview" id="saveSendForReview" value=" Save &amp; Send for Review " onclick="saveTextContent('SR');" title="Save &amp; Send for Review">   
						<input class="button" type="submit" name="publish" id="publish" value=" Save &amp; Publish " onclick="saveTextContent('P');" title="Save &amp; Publish">        				
					</div>
					</g:if>
            	</div>
             </g:form>
			  <g:form action="saveLinkData" name="saveLinkDataForm" method="post">
    			<input id="versionstatus" name="versionstatus" type="hidden"/>
				<div id="showL" class="MyDiv" style="display: none; ">
                    <div>	
                    	<g:if test="${contentDetails}">                    		
                        <p>Add/Edit external link: 
                         <g:if test="${ contentDetails[0].contentMode == 'L'}">
                          <input type="text" name="extLinkText" id="extLinkText" style="width:450px" value="${contentDetails[0].contentDescription}">&nbsp;
                          </g:if>
                          <g:else>
                          <input type="text" name="extLinkText" id="extLinkText" style="width:450px" value="">&nbsp;
                          </g:else>
                        </p>
                        <br>
					    <g:if test="${contentDetails[0].contentDescription != null && contentDetails[0].contentDescription != ''}">
							<p align="center">
								<iframe src="${contentDetails[0].contentDescription}" class="iframeStyle" align="middle" id="frExperiment" name="frExperiment" frameborder="0"> </iframe>
							</p>						
						</g:if>
						</g:if>
                    </div>
                    <g:if test="${contentDetails}">
                    
                    <div align="left">Revision comments: <br>
    					<input name="versionComments3" type="text" id="versionComments3" value="${contentDetails[0].versionComments}" size="40" maxlength="250">
					</div><br>
					</g:if>
                    <div align="left" style="padding-left:10px; float:left"> 
						<input class="button" type="submit" name="saveData" id="saveData" value="Save &amp; Preview" title="Save &amp; Preview" onclick="saveLinkContent('S');"> 
						<input class="button" type="submit" name="saveSendForReview" id="saveSendForReview" value=" Save &amp; Send for Review " onclick="saveLinkContent('SR');" title="Save &amp; Send for Review">   
						<input class="button" type="submit" name="publish" id="publish" value=" Save &amp; Publish " onclick="saveLinkContent('P');" title="Save &amp; Publish">        				
					</div>
				</div>
				</g:form>
            	<div id="showQ" class="MyDiv" style="display: none; ">
                    <div>		
                        <p>&nbsp;</p>	
                        <p align="left">
						
</p><div class="textHead2">Add New Question (<% println session.selectedContentId %>)&nbsp;&nbsp;
	<select id="choiceType" name="choiceType" onchange="javascript:showAdd('');" style="width:15%;">
    <option value="select">-select-</option>
    <option value="text">Text choice</option>
    <option value="image">Image choice</option>
    <option value="sechead">Section Head</option>
	<option value="fib">Fill in the Blanks</option>
	<option value="mf">Match the Following</option>
	<option value="dnd">Drag and Drop</option>
	</select>   
</div>
<br>
   
    
<div align="center">

    <div id="addFill" style="display:none;">
             <g:render template="/common/fibaddquestions"/>
    </div>
    

    <div id="addMF" style="display:none;">
             <g:render template="/common/dragDropEdit"/>
    </div>	
	 
	<div id="addDND" style="display:none;">
             <g:render template="/common/dndTextEdit"/>
    </div>
	 
    <div id="addDiv" class="quizTable" style="display:none;" align="center">
	     <g:render template="/common/mTextChoice"/>
    </div>
    
    <div id="mTextEdit" class="qnBorder" style="display:none;" align="center">
       <g:render template="/common/mTextEdit"/>
    </div>

    <div id="addImage" style="display:none;" class="quizTable" align="center">
	 <g:form action="addQuestion" name="addImageQuizForm" method="post">
	 <input type="hidden" name="qnTypeId" id="qnTypeId" value="2"> 
	<input type="hidden" name="ansType" id="ansType" value="Image"> 
	 <input type="hidden" name="ansDisplayOrder" id="ansDisplayOrder" value="Sequential">
	 <input type="hidden" name="ansVal" id="ansVal" value="4">
        <h3>Add Image Question</h3>
        <br>
        <table border="0" width="100%" id="dataTable">    
            <tbody><tr>
                <td height="74" colspan="5" align="center"><textarea onclick="clearcontent('quesnTxt')" onmouseout="fillcontent('quesnTxt')" id="qnText" name="qnText" cols="45" rows="5" class="quiztext">Question text</textarea>&nbsp;
                <img src="${hostname}/aell/images/attach_image.jpg" name="qnimgUpload4" id="qnimgUpload0" onclick="imagePopUp(0)" style="cursor:pointer;" height="60" width="60" title="attach image"><input type="hidden"></td>
                 <input type="hidden" name="qnimgValue0" id="qnimgValue0" />
            </tr>
       
            <tr>
                <td>&nbsp;</td>
            </tr>
            <tr>
                 <td width="15%" height="66" align="center"><img src="${hostname}/aell/images/upload_question.jpg" name="imgUpload1" id="imgUpload1" height="60" width="60" onclick="imagePopUp(1)" style="cursor:pointer;" title="upload image"><input type="hidden" name="imgValue1" id="imgValue1"></td>   			
                 <td width="15%" height="66" align="center"><img src="${hostname}/aell/images/upload_question.jpg" name="imgUpload2" id="imgUpload2" height="60" width="60" onclick="imagePopUp(2)" style="cursor:pointer;" title="upload image"><input type="hidden" name="imgValue2" id="imgValue2"></td>   			
                 <td width="15%" height="66" align="center"><img src="${hostname}/aell/images/upload_question.jpg" name="imgUpload3" id="imgUpload3" height="60" width="60" onclick="imagePopUp(3)" style="cursor:pointer;" title="upload image"><input type="hidden" name="imgValue3" id="imgValue3"></td>   			
                 <td width="15%" height="66" align="center"><img src="${hostname}/aell/images/upload_question.jpg" name="imgUpload4" id="imgUpload4" height="60" width="60" onclick="imagePopUp(4)" style="cursor:pointer;" title="upload image"><input type="hidden" name="imgValue4" id="imgValue4"></td>   			
            </tr> 
            <tr>	
                 <td align="center"><input type="radio" value="1" name="correctAns" id="correctAns"></td>
                 <td align="center"><input type="radio" value="2" name="correctAns" id="correctAns"></td>
                 <td align="center"><input type="radio" value="3" name="correctAns" id="correctAns"></td>
                 <td align="center"><input type="radio" value="4" name="correctAns" id="correctAns"></td>
                 <td width="7%">&nbsp;</td>
            </tr> 
            <tr>
                <td height="69" colspan="5" class="text" style="padding-left:35px;"><textarea onclick="clearcontent('hintText')" onmouseout="fillcontent('hintText')" name="hintText" id="hintText" style="font-style:italic;" cols="40">Hint</textarea></td>
                <input type="hidden" name="hintType" id="hintType" value="Image">      
            </tr>
	        </tbody>
	    </table>
    
        <br>
            <div align="center">
                <input type="submit" name="addImgQn" id="addImgQn" value="&nbsp;Add &amp; Publish&nbsp;" onclick="return addImgQuiz(this);">&nbsp;&nbsp;
                <input type="button" value="&nbsp;Cancel&nbsp;" id="cancel" name="cancel" onclick="hideAddDiv();">
            </div>
     </br>
     </g:form>
    </div>
</div>
<div align="center">
	 <g:form action="addQuestion" name="addSectionHeadQuizFrom" method="post">
    <div id="addSectonHead" class="quizTable" style="display:none;" align="center">
		<input type="hidden" name="ansType" id="ansType" value="Section head"> 
		<input type="hidden" name="qnTypeId" id="qnTypeId" value="3"> 
		<input type="hidden" name="addNewQn" id="addNewQn">
		<input type="hidden" name="mTextQnId" id="mTextQnId">
		<input type="hidden" name="ansDisplayOrder" id="ansDisplayOrder" value="Sequential"> 
		<h3>Add/Edit Section Head</h3>
		<input type="hidden" name="qnId" id="qnId"> 
    	 <table border="0" width="100%" id="dataTable" align="center">         
	         <tbody><tr class="text">     
	          </tr><tr>
	         	<td colspan="2">&nbsp;</td>            
	         </tr>    
	         	<tr><td align="right">Section Head : </td>
	            <td class="text"><input type="text" id="qnText" name="qnText" size="65%"></td>
	         </tr>
	         <tr>
	         	<td colspan="2">&nbsp;</td>            
	         </tr>
	          <tr class="text">         
	         	<td>&nbsp;</td>
	            <td align="left" class="text">
	            	<input type="submit" id="sectionHeadSubmit" name="sectionHeadSubmit" value="&nbsp;Submit&nbsp;" onclick="return validateSectionHead();">&nbsp;
	            	<input type="button" id="cancel" name="cancel" value="&nbsp;Cancel&nbsp;" onclick="hideAddDiv();"> 
	            </td>
	         </tr>
	         </tbody>
	     </table>
   </div>
   </g:form>
</div>    
<br>

<g:form action="updateSequenceQuiz" name="sequenceFormQuiz" method="post" >
	<input type="hidden" name="sequenceOrderQuiz" id="sequenceOrderQuiz"/>
</g:form>
<table  id="tableDragQuiz" border="0" width="100%">	
   <tbody>
       <tr>       
		    <td width="1px"></td>
		    <td width="95%">
		      <div id="mChoiceHead" class="qnType ui-corner-tl ui-corner-tr ui-corner-bl ui-corner-br">
            <h2 align="center">Multiple choice questions.</h2><br>
          </div>
		    </td>
		    <td width="2%"></td>
	   </tr>	
   </tbody>
   <tbody>
	   <g:set var="count" value="${0}" />
	   <g:each in="${questionContentList}" status="ik" var="i">     
		  <tr  id="${i.qnId}" style="cursor: move; ">
			 <td class="dragDropQuiz" title="Click and drag to change the display order." >
                   &nbsp;&nbsp;&nbsp;&nbsp;
        	  </td>
			  <td width="92%" class="text" align="left">
				    <table border="0" width="100%" >
                        <tr style="cursor: move;">
                             <td valign="top" width="6%">
                                <p class="quiztext" style="display:inline;">
                                    <g:if test="${i.type != 3}" >	
							           	<g:set var="count" value="${count+1}" />
							            ${count})
						             </g:if>
						         </p>
						       </td>
						       <td width="93%">
						           <g:if test="${i.type != 3}" >
						                 <p id="quiztext${i.qnId}" style="display:inline" class="quiztext">
						                   ${i.qnText}
						                 </p>
						            </g:if>
						            <g:if test="${i.type == 3}" >
						                 <div id="quizSectionHead${i.qnId}" class="quizSectionHead">${i.qnText}</div><br /><br />
						            </g:if>
						       </td>
                         </tr> 
              </table>
                         <br />
              <g:if test="${i.image}" >
				               <p style="padding-left:40px"><img id="qImage${i.qnId}" width="100" style="border:dotted; border-color:#999;" height="100" src="${hostname}/aell/uploads/quiz/crop/${i.image}" title="${i.image}"/></p><br/>
				         </g:if>
				         <g:if test="${i.type == 1}" >
				             <g:each status="k" in="${i.answers}" var="j">
				                   <g:if test="${i.correct?.toInteger() == j.key?.toInteger()}">
				                       <div style="padding-left:40px;">
				                       <img src="${hostname}/aell/images/tick.gif" title="Success" height="20" width="20"/>${k + 1})&nbsp;<p style="display: inline;" class="mtext">${j.text }</p>
				                       </div>
				                       <br />
				                   </g:if>
				                   <g:else>
				                    <div style="padding-left:60px;">  
				                        ${k + 1})&nbsp;<p class="mtext" style="display: inline;">${j.text }</p>
				                    </div>    
				                        <br />
				                   </g:else>
				              </g:each>
				          </g:if>
				          <g:elseif test="${i.type  == 2}">
                                <table border="0" align="center">
                                   <tr>
                                      <g:each status="k" in="${i.answers}" var="j">
				                            <g:if test="${j.text }" >
							                       <td><img class="mImgQn" style="border:dotted; border-color:#999;" width="100" height="100" src="${hostname}/aell/uploads/quiz/crop/${j.text }" title="${j.text }"  />&nbsp;&nbsp;&nbsp;</td>
							                </g:if>
							          </g:each>		
							        </tr>
							        <tr>			
							           <g:each status="k" in="${i.answers}" var="j"> 
						                    <g:if test="${i.correct?.toInteger() == j.key?.toInteger()}">
                                                   <td align="center"> <img src="${hostname}/aell/images/tick.gif" title="Success" height="20" width="20"></td>
                                                   <input type="hidden" value="${i.answers[k].text}" id="Success${i.qnId}"/>
                            	            </g:if> 
                            	            <g:else>
                            	                   <td align="center"></td>
                            	            </g:else>
                                       </g:each>					
                                    </tr>
                                </table>
				           </g:elseif> 
				           <br />
				           <g:if test="${i.qnHint}">
				           <div style="padding-left:30px; font-style:italic">
				           <b>Hint: </b><p id="mHint${i.qnId}" style="display:inline;">${i.qnHint}</p><br />
				           </div>
				           </g:if>
				           <br></br>
				           <br></br>
				 </td>
				 <g:if test="${i.type != 3}" >	
				 <td width="8%" valign="top" style="padding-top:25px; padding-left:15px">
				          <img class="editImg" src="${hostname}/aell/images/Edit.gif" name="mTextedit" title="edit" id="mTextedit"  style="cursor:pointer" onclick="editMText('${i.qnId}','${i.type}');" /><br /><br />
				       <g:link  action="deleteQuestion" params="[qnId:i.qnId]"  onclick="return confirm('${message(code: 'default.button.delete.confirm.message')}');">
                             <img src="${hostname}/aell/images/delete.png" width="20px" height="20px" title="delete" id="delete" name="delete"   style="cursor:pointer"/>
                        </g:link>
      	          </td>
      	     </g:if>
      	     <g:elseif test="${i.type == 3}">
      	     <td width="8%" valign="top" style="padding-top:25px; padding-left:15px">
					   <img src="${hostname}/aell/images/Edit.gif" title="Edit Topic" height="20" width="20" onclick="showSecEditForm(${i.qnId},'${i.qnText}');"/>
				       <g:link  action="deleteQuestion" params="[qnId:i.qnId]"  onclick="return confirm('${message(code: 'default.button.delete.confirm.message')}');">
                             <img src="${hostname}/aell/images/delete.png" width="20px" height="20px" title="delete" id="delete" name="delete"   style="cursor:pointer"/>
                        </g:link>
      	          </td>
      	       </g:elseif>
		</tr>
	</g:each>
	 </tbody>
</table>
	
	<tr>
	  <td></td>
	  <td colspan="1">
	    <div id="fibQuest" align="center" style="display:none;">	
	      <g:render template="/common/fibDisplayQn" model="['mode':'admin','val':fbqn,'Qtype':'FIB','subSec':ssHead]"/>			
	    </div>
	  </td>
	</tr>
	<tr>
	  <td></td>
	  <td colspan="1">
	    <div id="dndTQuest" align="center" style="display:none;">	
	      <g:render template="/common/dndTextDisplay" model="['mode':'admin','val':dndqn,'Qtype':'DNDT','subSec':ssHead]"/>			
	    </div>
	  </td>
	</tr>
	<g:render template="/common/dragDropDisplay" model="['mode':'admin','val':qaList,'Qtype':'DND','subSec':ssHead]"/>
</tbody>
</table>		

<br />
<br />
<div id="EditQuestionForm">
</div>
<table width="100%" border="0" style="border-color:#CCC; border-spacing:0px;" id="tableDrag">
 <tbody>
 </tbody>
</table>
<p></p>
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                    </div>
				</div> 
				<g:if test="${contentDetails}">
                <div align="right">             
					<g:form action="index" name="versionSelectForm" method="post">
                        Revisions &nbsp;<select style="width:252px" name="versionId" id="versionId" size="1" onchange="this.form.submit();">
							<g:each in="${versionDetails}" status="i" var="versionDetail">							
								<g:if test="${versionDetail.versionId == session.selectedVersionId}">
	                                <option value="${versionDetail.versionId}" selected="selected">${versionDetail.versionDateTime}:${versionDetail.userName}  (${versionDetail.versionStatus})</option>
	                            </g:if>
	                            <g:else>
	                                <option value="${versionDetail.versionId}">${versionDetail.versionDateTime}:${versionDetail.userName}  (${versionDetail.versionStatus.decodeVersionStatus()})</option>
	                            </g:else>    
		                    </g:each>      
                        </select>
                    </g:form>               
        			</div> 
        			</g:if>
			        </td>
    </tr>
		</tbody></table>
</tr>
</tbody></table>
</div>
<div id="backgroundPopup"></div>
</body>
</html>