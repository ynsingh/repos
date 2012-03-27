function getBaseURL(){
	var baseURL = window.document.location.host;
	imgBaseURL = baseURL + "/aell/images";	
}
function ShowWin() {
		document.getElementById('myDiv').style.visibility = 'visible';
		}

		function HideWin() {
		document.getElementById('myDiv').style.visibility = 'hidden';
		}



function addExtension(me,filename){
 var root_url=getRootUrl();
me.src=root_url+"images/tab_icon_image/"+filename+".png";
}

function getTopics()
{
		document.subjectSelectForm.submit();			
}
function getExperiments()
{
		document.topicSelectForm.submit();			
}
function getExperimentDetails()
{
		document.experimentSelectForm.submit();			
}
function getQuizQuestions(){
	
	document.quizSelectForm.submit();
	
}
function showEditForm(id,name,description)
{
	  $('#editForm').show();
	  $('#editedId').val(id);
	  $('#subjectName').val(name);
	  $('#subjectDescription').val(description);
}
function showSecEditForm(id,qnText)
{
	 $('#addSectonHead').show();
	 $("#addSectonHead").find("#qnText").val(qnText);
	 $("#addSectonHead").find("#addNewQn").val("Edit");
	 $("#addSectonHead").find("#qnId").val(id);
	 $("#addSectonHead").find("#mTextQnId").val(id);
}


function showAddForm()
{
	  $('#editForm').show();
	  $('#editedId').val("");
	  $('#subjectName').val("");
	  $('#subjectDescription').val("");
}

function showAddTabForm()
{
	  $('#addTabForm').show();
}

function closeAddTabForm()
{
	  $('#addTabForm').hide();
}

function closeEditForm(id,name,description)
{
	  $('#editForm').hide();
}

function checkEdit(){
	var name = document.subjectForm.subjectName;
	var desc = document.subjectForm.subjectDescription;
	if((name.value=="") || (name.value==null)) {
		alert("Please fill the name field");
		name.focus();
		return false;
	}
	if((desc.value=="") || (desc.value==null)) {
		alert("Please fill the description field");
		desc.focus();
		return false;
	}
}

function show_image(filePath, fileName)
{
	document.getElementById("select_image").src=filePath;
	document.getElementById("selectedImage").value=fileName;
	document.getElementById('PopUp').style.display = 'none' ;

}

function show_edit_image(filePath, fileName)
{
	document.getElementById("edit_image").src=filePath;
	document.getElementById("editedimage").value=fileName;
	document.getElementById('editPopUp').style.display = 'none' ;
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
		var answer = confirm("You have selected to delete this tab. Press 'ok' to continue")
		if(!answer)
		{
			return false;
		}
		
	}
	//	
	return true;
	
}
function editQuestionjs(v){
	var d = 1
}
function saveTextContent(vstatus)
{
		document.forms["saveTextDataForm"].versionstatus.value = vstatus;
	 // 	$('#saveTextDataForm').submit();
}

function saveLinkContent(vstatus)
{
		document.forms["saveLinkDataForm"].versionstatus.value = vstatus;
	  //	$('#saveLinkDataForm').submit();
}

function addChoice(tableID)
{
	var baseURL = window.document.location.host;
	var hostPath = "http://" + baseURL;
	var mytableId = document.getElementById(tableID);
	var rowCount = mytableId.rows.length;
	var row = mytableId.insertRow(rowCount);
	var answerVal = document.getElementById('ansVal').value;
	var assignId = document.getElementById('idAssign').value;
	answerVal++;
	document.getElementById('ansVal').value=answerVal;
	if(assignId=="")
	{
		document.getElementById('idAssign').value = answerVal;
	}
	else
	{
		document.getElementById('idAssign').value = assignId+","+answerVal;
	}
	var cell3 = row.insertCell(0);
	cell3.setAttribute("class","text");
	var element2 = document.createElement("textarea");
	element2.cols = "50";
	element2.name = "choice"+answerVal;
	element2.id = "choice"+answerVal;
	element2.value = "choice"+answerVal;
	element2.onclick = function (){ 
		if(element2.value=="choice"+answerVal)
		{ 
			element2.value=""; 
		}
	}
	element2.onmouseout = function(){ 
		if(element2.value=="" || element2.value=="choice"+answerVal)
		{ 
			element2.value="choice"+answerVal;
		}
	}
	cell3.appendChild(element2);
	var cell4 = row.insertCell(1);
	cell4.align = 'center';
	var element3 = document.createElement("input");
	element3.name = "correctAns";
	element3.id = "correctAns";
	element3.type = "radio";
	element3.value = answerVal;
	cell4.appendChild(element3);
	var cell5 = row.insertCell(2);
	cell5.align = 'center';
	var deleteBtn = document.createElement("input");
	deleteBtn.type = "image";
	deleteBtn.src = hostPath + "/aell/images/delete.png";
	deleteBtn.setAttribute("width","20px");
	deleteBtn.setAttribute("height","20px");
	deleteBtn.setAttribute("title","delete choice");
	deleteBtn.onclick = function () { 
			deleteChoice(this,answerVal) 
	} ;
	cell5.appendChild(deleteBtn);
}
//function for deleting choice.
function deleteChoice(obj,answerVal) 
{
	var delRow = obj.parentNode.parentNode;
	var tbl = delRow.parentNode.parentNode;
	var rIndex = delRow.sectionRowIndex;
	delRow.parentNode.deleteRow(rIndex);
	var IdAssign = document.getElementById('idAssign').value;
	var idArray = new Array();
	idArray	= IdAssign.split(",");
	for(i in idArray)
	{
		if(idArray[i].indexOf(answerVal)>-1)
		{
			idArray.splice(i, 1);
		}
	}
	var idString = idArray.toString();
	document.getElementById('idAssign').value=idString;		
}

function showAdd(bid)
{ 		
	if(document.getElementById('ansVal').value<4)
	{
		addChoice('dataTable');	
		addChoice('dataTable');	
		addChoice('dataTable');	
		addChoice('dataTable');	
	}	
	var ansType = document.getElementById('choiceType').value;
	if(ansType=='text')
	{
		bid = 'addDiv';
	}
	else if(ansType=='image')
	{
		bid = 'addImage';
	}
	else if(ansType=='sechead')
	{
		bid = 'addSectonHead';
	} 
	else if(ansType=='fib')
	{
		bid = 'addFill';
	}
	else if(ansType=='mf')
	{
		bid = 'addMF';
	}
	else if(ansType=='dnd')
	{
		bid = 'addDND';
	}
	document.getElementById('addImage').style.display = "none";
	document.getElementById('addDiv').style.display = "none";	
	document.getElementById('addSectonHead').style.display = "none";
	document.getElementById('addFill').style.display = "none";
	document.getElementById('addMF').style.display = "none";
	document.getElementById('addDND').style.display = "none";
	if(document.getElementById(bid).style.display == "none") 
	{ 
		document.getElementById(bid).style.display = "block"; 
	} 
	else if (document.getElementById(bid).style.display =="block") 
	{ 
		document.getElementById(bid).style.display = "none"; 
	}	
	return true; 	
}

function hideAddDiv()
{
	document.getElementById('addImage').style.display = "none";
	document.getElementById('addDiv').style.display = "none";	
	document.getElementById('addFill').style.display = "none";	
	document.getElementById('addSectonHead').style.display = "none";	
	document.getElementById('addMF').style.display = "none";
	document.getElementById('addDND').style.display = "none";
	$("#addSectonHead").find("#qnText").val(" ");
	$("#choiceType").val('0');
	ansVal = 0
}

function updateSequence(order) {
	  $('#sequenceOrder').val(order);
	  $('#sequenceForm').submit();
}

$(document).ready(function(){   
    $(function() { 
    $(".list ul").sortable({ opacity: 0.8, cursor: 'move', update: function() { 
            var order = $(this).sortable("serialize"); 
            updateSequence(order);                            
        }                             
        });
    });
}); 

function updateSequenceQuiz(order) {
	  $('#sequenceOrderQuiz').val(order);
	  $('#sequenceFormQuiz').submit();
}
function updateSequenceFIB(order) {
	  $('#sequenceOrderFIB').val(order);
	  $('#sequenceFormFIB').submit();
}
function updateSequenceMF(order) {
	  $('#sequenceOrderMF').val(order);
	  $('#sequenceFormMF').submit();
}
function updateSequenceDNDT(order) {
	  $('#sequenceOrderDNDT').val(order);
	  $('#sequenceFormDNDT').submit();
}

$(document).ready(function() {
	  initDND("admin");	
	  $('#editForm').hide();
	  $('#addTabForm').hide();
	  $('#editQuestionForm').hide();
	  $('.MyDiv').hide();
	   
	  	if($('select[name="contentType"]').val()=='T')
		{		
			$('#showT').show();
		}	
		else if($('select[name="contentType"]').val()=='L')
		{
			$('#showL').show();
		}
		else if($('select[name="contentType"]').val()=='Q')
		{
			$('#showQ').show();
			$('#fibQuest').show();
			$('#dndTQuest').show();
			$('#showQP').show();
			$("#showDnD").show();
		}
		else
		{
			$('#showT').show();
		}

	    $('select[name="contentType"]').change(function(){
	        var selected = $(this).val();
	        $('.MyDiv').slideUp();
	        $('#show'+selected).slideDown();
	    });
	    	  
	    $("#tableDrag").tableDnD({ onDrop: function(table, row) {	
           var rows = table.tBodies[1].rows;
           var debugStr = rows[0].id;
           for (var i=1; i<rows.length; i++) {
               debugStr += "," + rows[i].id;
           }
	        updateSequence(debugStr);
          }
      });
	    $("#tableDragQuiz").tableDnD({ onDrop: function(table, row) {	
	           var rows = table.tBodies[1].rows;
	           var debugStr = rows[0].id;
	           for (var i=1; i<rows.length; i++) {
	               debugStr += "," + rows[i].id;
	           }
		        updateSequenceQuiz(debugStr);
	          }
	      });
	    
	    $("#tableDragFIB").tableDnD({ onDrop: function(table, row) {	
	           var rows = table.tBodies[1].rows;
	           var debugStr = rows[0].id;
	           for (var i=1; i<rows.length; i++) {
	               debugStr += "," + rows[i].id;
	           }
		       updateSequenceFIB(debugStr);
	          }
	      });
	    $("#tableDragMF").tableDnD({ onDrop: function(table, row) {	
	           var rows = table.tBodies[1].rows;
	           var debugStr = rows[0].id;
	           for (var i=1; i<rows.length; i++) {
	               debugStr += "," + rows[i].id;
	           }
		       updateSequenceMF(debugStr);
	          }
	      });
	    
	    $("#tableDragDNDT").tableDnD({ onDrop: function(table, row) {	
	           var rows = table.tBodies[1].rows;
	           var debugStr = rows[0].id;
	           for (var i=1; i<rows.length; i++) {
	               debugStr += "," + rows[i].id;
	           }
		       updateSequenceDNDT(debugStr);
	          }
	      });
	      counter = 0
	      
	    $("#addButton").click(
	     
	    );
	   /* 
	   $("img.editImg").click(function(){
	    	$("#EditQuestionForm").popOverMe();
	    });
		*/
	   $("#displayQn").mouseover(function() {
	    	  $("#secInsImgFIB").show();
	     }).mouseleave(function() {
	    	  $("#secInsImgFIB").hide();
	   });
	   
	   $("#displayDNDTQn").mouseover(function() {
	    	  $("#secInsImgDNDT").show();
	     }).mouseleave(function() {
	    	  $("#secInsImgDNDT").hide();
	   });
	   
	   $("#tableDragMF").mouseover(function() {
	    	  $("#secInsImgDND").show();
	      }).mouseleave(function() {
	    	  $("#secInsImgDND").hide();
	   });
	   
	   $(".dndch").droppable({
		   drop: function(event, ui) {
		   $(this).val(ui.draggable.text());
		   var txt = ui.draggable.text();
		   $(this).css({'width': txt.length+ 'em'})  
		   }
		 });
	   
	   $(".dndtGroup").each(function(index,element){
    	   $(element).find(".dndTOptions").draggable({
    		   cursor : 'pointer',
    		   containment : element,
    		   helper : 'clone'
    	   });
       });
	   
	   ($("#tableDragQuiz").find("tbody:gt(1)").html())?(""):($("#mChoiceHead").hide());
	   
});

function mTextHide(){
	$("#mTextEdit").hide();
	$("#backgroundPopup").hide();
//	hideAddDiv();
}

function mTextvalidate(){
	if($("input[name='correctAns']:checked").val() == null){
		alert("Please select the correct choice");
		return false;
	}
	else{
	if($("#mTextEdit").find("#qnTypeId").val() == "1"){
		$("#mTextEdit").find("#ansVal").val($("#mTextEdit").find("tr.choiceRow").size());
		$("#mTextEdit").find("#ansType").val("Text");
	}
	else if($("#mTextEdit").find("#qnTypeId").val() == "2"){
		$("#mTextEdit").find("#ansVal").val($("#mTextEdit").find("tr.mImgOptions td").size());
		$("#mTextEdit").find("#ansType").val("Image");
	}
	return true;
	}
}

function editMText(index,type){
	var rowCount = 0;
	$("#mTextEdit").find("#qnText").html($.trim($("tr#"+index).find("#quiztext"+index).html()));
	$("#mTextEdit").find("#hintText").html($.trim($("tr#"+index).find("#mHint"+index).html()));
	$("#mTextEdit").find("#eimgUpload0").attr('src',($("tr#"+index).find("#qImage"+index).attr('src')));
	$("#mTextEdit").find("#eimgValue0").val($("tr#"+index).find("#qImage"+index).attr('title'));
	$("#mTextEdit").find("#qnTypeId").val(type);
	//Remove all the Choices and Options prior to the div load 
	$("tr.choiceRow")? ($("tr.choiceRow").remove()):("");
	$("tr.mImgOptions")? ($("tr.mImgOptions").remove()):("");
	$("tr.mImgRadio")? ($("tr.mImgRadio").remove()):("");
	// Textual multiple choices
	if(type == "1"){
		var selectedCh = $("#"+index).find("div img").siblings("p").text();
		$("#mChoiceAdd").show();
		$("#mQnControls").show();
		rowCount=$("#mTextEdit").find("#dataTable tr").size();	
		$("tr#"+index).find(".mtext").each(function(it,ele){
			if(selectedCh == $(ele).text()){
			$("#mTextEdit").find("#dataTable").append("<tr class=choiceRow><td class=text><textarea cols=50 rows=1 name=choice"+(it+1)+" id=choice"+(it+1)+">"+$(ele).html()+"</textarea></td><td align=center><input type=radio name=correctAns id=correctAns value="+(it+1)+" checked='checked'></td><td align=center><img id=delChImg width=20px onclick=delChoice(this); height=20px src=/aell/images/delete.png title='Delete choice'></td></tr>");
			}
			else{
				$("#mTextEdit").find("#dataTable").append("<tr class=choiceRow><td class=text><textarea cols=50 rows=1 name=choice"+(it+1)+" id=choice"+(it+1)+">"+$(ele).html()+"</textarea></td><td align=center><input type=radio name=correctAns id=correctAns value="+(it+1)+"></td><td align=center><img id=delChImg width=20px onclick=delChoice(this); height=20px src=/aell/images/delete.png title='Delete choice'></td></tr>");
			}
		});
	}
	// Image multiple choices
	else if(type == "2"){
		var chPosition=0,selectedImg = $("#Success"+index).val();
		$("#mChoiceAdd").hide();
		$("#mQnControls").hide();
		$("#mTextEdit").find("#imgValue0").val($("tr#"+index).find("#qImage"+index).attr('title'));
		$("#mTextEdit").find("#dataTable").append("<tr class=mImgOptions></tr>");
		$("tr#"+index).find(".mImgQn").each(function(it,ele){
			if(selectedImg == $(ele).attr('title')){
				chPosition=it;
			}
			$("#mTextEdit").find("#dataTable tr.mImgOptions").append("<td align=center width=15% height=66><img width=60 height=60 title='upload image' style=cursor:pointer; onclick=imagePopUp("+(it+1)+") id=imgUpload"+(it+1)+" name=imgUpload"+(it+1)+" src='"+($(ele).attr('src'))+"'><input type=hidden id=imgValue"+(it+1)+" name=imgValue"+(it+1)+" value='"+($(ele).attr('title'))+"'></td>");
		});
		$("#mTextEdit").find("#dataTable").append("<tr class=mImgRadio></tr>");
		$("tr#"+index).find(".mImgQn").each(function(it,ele){
			if(chPosition == it){
				$("#mTextEdit").find("#dataTable tr.mImgRadio").append("<td align=center><input type=radio id=correctAns name=correctAns value="+(it+1)+" checked='checked'></td>");
			}
			else{
				$("#mTextEdit").find("#dataTable tr.mImgRadio").append("<td align=center><input type=radio id=correctAns name=correctAns value="+(it+1)+"></td>");
			}
		});
	}
	$("#mTextQnId").val(index);
	$("#mTextEdit").popOverMe();
}

function delChoice(object){
	$(object).closest("tr").remove();
}

function mChoiceAdd(elementId){
	var chRowCount=$("#"+elementId).find("tr.choiceRow").size();
	$("#mTextEdit").find("#dataTable").append("<tr class=choiceRow><td class=text><textarea cols=50 rows=1 name=choice"+(chRowCount+1)+" id=choice"+(chRowCount+1)+"></textarea></td><td align=center><input type=radio name=correctAns id=correctAns value="+(chRowCount+1)+"></td><td align=center><img id=delChImg width=20px onclick=delChoice(this); height=20px src=/aell/images/delete.png title='Delete choice'></td></tr>");
}

function secAdd(type){
	$("#secInsData"+type).toggle(200);
	$("#secIns"+type).toggle(500);
	$("#secIns"+type).addClass("secIns");
}

function secInsSave(type){
	var secInsType = "";
	if(type == "FIB"){
		secInsType = "FBH";
	}
	else if(type == "DND"){
		secInsType = "MFH";
	}
	else if(type == "DNDT"){
		secInsType = "DDH";
	}
	else{
		secInsType = "MCH";
	}
	var secText=$("#secInsText"+type).val();
	if($("#secIsEdit"+type).val() == "false"){
		var queryString="type=" + secInsType + "&text=" + secText;
	}
	else{
		var queryString="type=" + secInsType + "&text=" + secText + "&subSectionEdit=1&qnId=" +$("#secQId"+type).val();
	}
	$.ajax({
		  url: "/aell/quiz/cSubSectionHead",
		  type: "POST",
		  data: queryString,
		  success: function(data){
			  $("#secQId"+type).val(data);
			  $("#secIsEdit"+type).val("true");
			  $("#secIns"+type).toggle(500);
			  $("#secInsData"+type).find("h3").html($("#secInsText"+type).val());
			  $("#secInsData"+type).toggle(500);
		  }
		});
}

function add(type) {
    //Create an input type dynamically.
    var element = document.createElement("input");
    //Assign different attributes to the element.
    element.setAttribute("type", type);
    element.setAttribute("value", type);
    element.setAttribute("name", type);
    var foo = document.getElementById("fooBar");
    //Append the element in page (in span).
    foo.appendChild(element);
}
