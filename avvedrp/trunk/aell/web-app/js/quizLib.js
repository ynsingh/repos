var cloneDiv=" ";
/* Function for Fill in the blanks
 * deleteFBQn()		:	Delete a FIB question
 * editFBQn()		:	Edit a FIB question
 * evaluateFBQn()	:	Evaluate a FIB question
 * evalFibAll()		:	Evaluate all FIB questions
 * showfibQuest()	:	Show the div for FIB questions
 * 
 */
function deleteFBQn(qnId){
	$.ajax({
		  url: "/aell/editExperiment/deleteQuestion",
		  data: {'qnId':qnId},
		  success: function(){
			$('#fbQuestion'+qnId).remove();
			$('#'+qnId).remove();
			getQnCount("fibName",".fbQuestion","FIB");
		  }
	});
}

evalFibAll = function(){ 
	$(".fbQuestion").each(function(it,obj){
		setTimeout(function(){evaluateFBQn($("#fbKey"+it).val(),(it+1));},(it/5)*1000);
	});
	$("#evaluateFIB").hide();
}

function evaluateFBQn(qnId,it){
	// make an ajax call to the method eFillBlanksQn(int qnId, String[] enteredAnswers) to get  a response back
	var enteredAnswers=[],outOf=0,total=0,sbmitFlg,hntUsed;
	hntUsed = $("#hntUsed"+qnId).val(); 
	var fibSize = $(".fbQuestion").size();
	if(fibSize == it){
		sbmitFlg=1;
	}
	else{
		sbmitFlg=0;
	}
	$('#fbQuestion'+qnId).find('.fibch').each(function(index,item){
		$(item).addClass("fibchCorrect");
		($(this).val() != "")? enteredAnswers.push($(this).val()):enteredAnswers.push("1");
		outOf++;
	});
	var ajaxReq=$.ajax({
		type:"POST",
		url: "/aell/quiz/evaluatefib",
		context: document.body,
		data: "qnId=" + qnId + "&enteredAnswers=" + enteredAnswers + "&sbmitFlg=" + sbmitFlg + "&enterValue=" +it + "&hntUsed=" + hntUsed  + "&qnType=FB",
		traditional : true,
		success: function(data){
		for(var i = 0 ; i < data.wrongIndex.length ; i++){
			($('#fbQuestion'+qnId).find('#ch'+data.wrongIndex[i]).val() == "")?($('#fbQuestion'+qnId).find('#ch'+data.wrongIndex[i]).val("X")):"";
			$('#fbQuestion'+qnId).find('#ch'+data.wrongIndex[i]).removeClass("fibchCorrect");
			$('#fbQuestion'+qnId).find('#ch'+data.wrongIndex[i]).addClass("fibchWrong");
			}
		}
	});
}

function editFBQn(qnId,hinTxt){
	//Find the occurances of blanks [] and replace each blanks with respective answers
	var occurances=0
	// Clone the div. Used to restore the div back to Initial state. 
	cloneDiv=$("#addFill").clone();
	qsTxt=$("#fibQn"+qnId).val();
	$.each(qsTxt,function(index,Element){
		if(Element=='[')
			occurances++;
	});
	for(var i=0;i<occurances;i++){
		qsTxt=qsTxt.replace("[]","["+$('#fbQuestion'+qnId).find('#ch'+i).val()+"]");
	}
	$("#isEdit").val(1);
	$("#qnId").val(qnId);
	$('#addNewQn').val("Update Question");
	$('#qnHeader').val("Edit Question");
	$('#fillqnText').attr("value",qsTxt);
	$('#hintText').attr("value",hinTxt);
	$("#addFill").popOverMe("#addFibDiv");
}

function showfibQuest(){	
	$("#fibQuest").show();
}

/*
 * Functions for Match the following Module
 *dndAddChoice()	:	Add answer choices in MF add question. 
 *delchoice()		:	Delete the answer choices in MF add question
 *initDND()			:	Initialise the draggables and droppables. Assign question IDs.  
 *restoreDD()		:	Restores all the draggables and re-initialise all draggables and droppables.
 *						(Cannot restore once question is attempted).
 *resetDD()			:	Reset the entire question after evaluation. Re-initialise all draggables and droppables.
 *setPosition()		:	Set the position of draggables after dropped onto a droppable.
 *setOldPosition()	:	Capture the initial position of draggables prior to dragging. Used in Restore()
 *setDefaultChoices	:	Initialise the question:answer pairs
 *evalDndAll()		:	Evaluate all MF questions
 *evaluateDD()		:	Evaluate a MF question
 *deleteDD()		:	Delete a MF question
 *editDD() 			:	Edit a MF question
 *
 */

function dndAddChoice() {
	var Optns = 0;
	$('#dndChoice').find('.dndOption').each(function(index) {
		Optns++;
	});

	$('#dndChoice').append("<tr id=dndrow"+ (Optns + 1)+ "><td>Option"+ (Optns + 1)+ ":&nbsp;&nbsp;<textarea style='margin-left:5px;width:65%;height:20px;' id='option"+ (Optns + 1)
							+ "' class='dndOption' name='option"+ (Optns + 1)+ "'></textarea></td>"+ "<td>Answer"
							+ (Optns + 1)+ ":&nbsp;&nbsp;<textarea style='margin-left:5px;width:65%;height:20px;' id='answer"+ (Optns + 1)+ "' class='dndAnswer' name='answer"
							+ (Optns + 1)+ "'></textarea></td>"+ "<td align='center'><img width='20px' height='20px' src='/aell/images/delete.png' onclick='return delchoice("
							+ (Optns + 1) + ")' alt='delete choice'></td>"+ "</tr>");
}

function dndValidate(obj) {
	$("#noChoices").val($('#dndChoice').find('.dndOption').size());
	$("#dndChoice tr").each(function(it,ele){
		$(ele).find("textarea.dndOption").attr('id','option'+(it+1));
		$(ele).find("textarea.dndOption").attr('name','option'+(it+1));
		$(ele).find("textarea.dndAnswer").attr('id','answer'+(it+1));
		$(ele).find("textarea.dndAnswer").attr('name','answer'+(it+1));
	});
	return true;
}

function delchoice(obj) {
	var numChoices=$('#dndChoice tr').size();
	if (numChoices <= 2) {
		alert("Two choices are mandatory")
		return false;
	} else {
		$('#dndrow' + obj).remove();
		return true;
	}
}

function initDND(status) {
	$('.content').each(function(ix) {
		// Adding the data values for Questions
		$('#Questions' + ix).find('div').each(function(i, qitem) {
			var idValue = $(qitem).attr('id');
			$(qitem).data('number', idValue);
		});
		// Creating the draggable elements
		$('#Questions' + ix).find('div').draggable({
			revert : 'invalid',
			containment : '#content' + ix,
			cursor : 'move',
			create : setDefaultChoices,
			start : function(event, ui) {
				$(this).data('posLeft', ui.offset.left);
				$(this).data('posTop', ui.offset.top);
				$(this).data('content', ix);
			}
		});
		//Sorting The Droppables for Assessments
		if(status=="assessments"){
			$('#answerSlots' + ix).each(function(index,element){
				$(element).find("#ansTable").shuffle();
			});
		}
		
		// Adding the data values for Answers
		$('#answerSlots' + ix).find('div').each(function(i, aitem) {
			var idValue = $(aitem).attr('id');
			$(aitem).data('number', idValue);
		});
		// Creating the droppable elements
		$('#answerSlots' + ix).find('div').droppable({
			accept : '#Questions' + ix + ' div',
			hoverClass : 'hovered',
			drop : setPosition
		});
	});
	$('.ddRes').hide();
}

function restoreDD(index) {
	$('#Questions' + index).find('div').each(function(ik, item) {
		if (($(item).data('posLeft') > 0) && ($(item).data('posTop') > 0)) {
			$(item).offset({
				left : $(item).data('posLeft'),
				top : $(item).data('posTop')
			});
			$(item).css("background", "#666");
		}
	});
	$('#Questions' + index).find('div').draggable("destroy");
	$('#Questions' + index).find('div').draggable({
		revert : 'invalid',
		containment : '#content' + index,
		cursor : 'move',
		create : setDefaultChoices,
		start : function(event, ui) {
			$(this).data('posLeft', ui.offset.left);
			$(this).data('posTop', ui.offset.top);
			$(this).data('content', index);
		}
	});
	
	$('#answerSlots' + index).find('div').droppable("destroy");
	$('#answerSlots' + index).find('div').droppable({
		accept : '#Questions' + index + ' div',
		hoverClass : 'hovered',
		drop : setPosition
	});
	
	$('#ddRes' + index).hide();
}

function resetDD(index){
	restoreDD(index);
	$('#answerSlots' + index).find('div').droppable({
		accept : '#Questions' + index + ' div',
		hoverClass : 'hovered',
		drop : setPosition
	});
	$('#output'+index).html('');
	$('#ddEval'+index).show();
	$("#Questions"+index).find(".dndOutput").removeClass("dndWrong");
	$("#Questions"+index).find(".dndOutput").removeClass("dndCorrect");
	$("#Questions"+index).find(".dndOutput").addClass("correct");
	$("#Questions"+index).find(".dndOutput").html("");
	$("#evaluateDND").show();
}

function setOldPosition(event, ui) {
	ui.draggable.css("background", "#666");
}

function setPosition(event, ui) {
	ui.draggable.css("background", "orange");
	ui.draggable.position({
		of : $(this),
		 my: "center",
		 at: "center"
	});
	var dropData = $(this).attr("id");
	var dropText = $.trim($("#aText"+dropData).html());
	$(this).droppable("destroy");
	var dragData = ui.draggable.attr("id");
	ui.draggable.data('number', dragData + ":" + dropData);
	ui.draggable.data('ansText', dragData + ":" + dropText);
	ui.draggable.draggable('disable');
	var index = ui.draggable.data('content');
	$('#ddRes' + index).show();
}

function setDefaultChoices(event, ui) {
	var dragData = $(this).attr("id");
	$(this).data('number', dragData+":0");
	$(this).data('ansText', dragData + ":Not Selected");
}

function evalDndAll(){
	$(".content").each(function(index,element){
		setTimeout(function(){evaluateDD(index,(index+1));},(index/5)*1000);
	});
}

function evaluateDD(index,x) {
	var matchString = "",ansIdPairs = "",hntUsed;
	hntUsed=$("#hntUsed"+index).val();
	var sbmitFlg;
	var dndSize = $(".content").size();
	if(dndSize == (index+1)){
		sbmitFlg=1;
	}
	else{
		sbmitFlg=0;
	}
	$('#answerSlots' + index + ' div').each(
			function(i) {
				var choicePairs = $('#Questions' + index + ' div').eq(i).data('number');
				matchString += choicePairs + ",";
				var ansId = $('#Questions' + index + ' div').eq(i).data('ansText');
				ansIdPairs += ansId + "~";
			});
		$.ajax({
		type : "POST",
		url : "/aell/quiz/eMatchQnAns",
		context : document.body,
		data : "choice=" + matchString + "&enterValue=" + x + "&submitFlag=" + sbmitFlg + "&ansIdPairs=" + ansIdPairs + "&hntUsed=" + hntUsed,
		cache:false,
		traditional : true,
		success : function(data) {
			for(var i=0;i<data.length;i++){
				$("#content"+index).find("#dndOutput"+data[i]).removeClass("correct").addClass("dndWrong").html("X");
			}
			$("#content"+index).find(".correct").each(function(item,ele){
					$(ele).addClass("dndCorrect").html("&#10004;");
			});
			$('#evaluateDND').hide();
			$('#ddRes' + index).hide();
		}
	});
}

function deleteDD(index) {
	var qnIdString = "";
	qnIdString = $('#parQId' + index).val();
	$('#Questions' + index + ' div').each(
			function(i, item) {
				var choicePairs = $('#Questions' + index + ' div').eq(i).attr('id');
				qnIdString += "," + choicePairs;
			});
	$.ajax({
		type : "POST",
		url : "/aell/quiz/dMatchQnAns",
		context : document.body,
		data : "qnIds=" + qnIdString,
		traditional : true,
		success : function() {
			$('#content' + index).closest("tr").remove();
			$('#content' + index).remove();
			$('#output' + index).remove();
			$('#rightContent' + index).remove();
			getQnCount("dndName",".content","DND");
		}
	});
}

function editDD(index) {
	var qnIdString = "";
	var numOptns = 0;
	// Clone the div. Used to restore the div back to Initial state. 
	cloneDiv=$("#addMF").clone();
	qnIdString = $('#parQId' + index).val();
	$('#Questions' + index + ' div').each(
			function(i, item) {
				var choicePairs = $('#Questions' + index + ' div').eq(i).attr('id');
				qnIdString += "," + choicePairs;
				numOptns++;
			});

	for ( var i = 2; i < numOptns; i++) {
		$('#dndChoice').append("<tr id=dndrow"+ (i + 1)+ "><td>Option"+ (i + 1)+ ":&nbsp;&nbsp;<textarea style='margin-left:5px;width:65%;height:20px;' id='option"+ (i + 1)
								+ "' class='dndOption' name='option"+ (i + 1)+ "'></textarea></td>"
								+ "<td>Answer"+ (i + 1)+ ":&nbsp;&nbsp;<textarea style='margin-left:5px;width:65%;height:20px;' id='answer"+ (i + 1)+ "' class='dndAnswer' name='answer"
								+ (i + 1)+ "'></textarea></td>"
								+ "<td align='center'><img width='20px' height='20px' src='/aell/images/delete.png' onclick='return delchoice("
								+ (i + 1) + ")' alt='delete choice'></td></tr>");
	}

	$('#qnIds').val(qnIdString);
	// Setting flag value for deleting the question and creating the new.
	$('#dndEdit').val("1");
	// Populating values to the Questions,Hints and Options
	$('#content' + index).find('.QuestText').each(function(i, item) {
		$('#dndChoice').find('.dndOption').eq(i).html($.trim(($(item).html())));
	});
	$('#content' + index).find('.answerText').each(function(i, item) {
		$('#dndChoice').find('.dndAnswer').eq(i).html($.trim(($(item).html())));
	});
	$('#dndTable').find('#Q').val($('#ques' + index).val());
	$('#dndTable').find('#H').val($('#hint' + index).val());
	$("#dndTable").css({'height':'600px','overflow':'scroll','width':'1000px'});
	$("#addMF").popOverMe("#dndTable");
		
}


/*
 * Function for Drag and Drop Textual Questions
 * editDNDTQn 		:	Edit DNDT Quiz Questions
 * deleteDNDTQn		:	Delete DNDT Quiz Questions
 * evaluateDNDTQn 	:	Evaluate each DNDT Quiz Questions
 * evalDNDTAll		:	Evaluate all DNDT Quiz Questions
 */

function editDNDTQn(qnId,hinTxt){
	//Find the occurances of blanks [] and replace each blanks with respective answers
	var occurances=0
	// Clone the div. Used to restore the div back to Initial state. 
	cloneDiv=$("#addDND").clone();
	qsTxt=$("#dndtQn"+qnId).val();
	$.each(qsTxt,function(index,Element){
		if(Element=='[')
			occurances++;
	});
	for(var i=0;i<occurances;i++){
		qsTxt=qsTxt.replace("[]","["+$('#dndTQuestion'+qnId).find('#ch'+i).val()+"]");
	}
	$("#addDND").find("#isEdit").val(1);
	$("#addDND").find("#qnId").val(qnId);
	$("#addDND").find('#addNewQn').val("Update Question");
	$("#addDND").find('#qnHeader').html("Edit Question");
	$('#dndqnText').attr("value",qsTxt);
	$('#dndtHintText').attr("value",hinTxt);
	$("#addDND").popOverMe("#addDndDiv");
}

evalDNDTAll = function(){
	$(".dndTQuestion").each(function(it,obj){
		setTimeout(function(){evaluateDNDTQn($("#dndTKey"+it).val(),(it+1));},(it/5)*1000);
	});
	$("#evaluateDNDT").hide();
}

function evaluateDNDTQn(qnId,it){
	// make an ajax call to the method eFillBlanksQn(int qnId, String[] enteredAnswers) to get  a response back
	var enteredAnswers=[],outOf=0,total=0,sbmitFlg,hntUsed;
	hntUsed = $("#hntUsed"+qnId).val(); 
	var fibSize = $(".dndTQuestion").size();
	if(fibSize == it){
		sbmitFlg=1;
	}
	else{
		sbmitFlg=0;
	}
	$('#dndTQuestion'+qnId).find('.dndch').each(function(index,item){
		$(item).removeClass("dndch");
		$(item).addClass("fibchCorrect");
		($(this).val() != "")? enteredAnswers.push($(this).val()):enteredAnswers.push("1");
		outOf++;
	});
	var ajaxReq=$.ajax({
		type:"POST",
		url: "/aell/quiz/evaluatefib",
		context: document.body,
		data: "qnId=" + qnId + "&enteredAnswers=" + enteredAnswers + "&sbmitFlg=" + sbmitFlg + "&enterValue=" +it + "&hntUsed=" + hntUsed + "&qnType=DND",
		traditional : true,
		success: function(data){
		for(var i = 0 ; i < data.wrongIndex.length ; i++){
			($('#dndTQuestion'+qnId).find('#ch'+data.wrongIndex[i]).val() == "")?($('#dndTQuestion'+qnId).find('#ch'+data.wrongIndex[i]).val("X")):"";
			$('#dndTQuestion'+qnId).find('#ch'+data.wrongIndex[i]).removeClass("fibchCorrect");
			$('#dndTQuestion'+qnId).find('#ch'+data.wrongIndex[i]).addClass("fibchWrong");
			}
		}
	});
}

function deleteDNDTQn(qnId){
	$.ajax({
		  url: "/aell/editExperiment/deleteQuestion",
		  data: {'qnId':qnId},
		  success: function(){
			$('#dndTQuestion'+qnId).remove();
			$('#'+qnId).remove();
			getQnCount("dndTName",".dndTQuestion","DNDT");
		  }
	});
}

/*
 * Functions for popover style for Editing Quiz questions
 * inViewport()	:	Get the top positon of the div w.r.t the viewport
 * popOverMe()	:	Popover styling for the edit div
 */

$.fn.popOverMe=function(subelement){
	this.addClass("popOver");
	$("#backgroundPopup").css({  
		"opacity": "0.85"  
	}); 
	$("#backgroundPopup").fadeIn(400);  
	this.inViewport();
	(subelement=="") ? "" : ($(subelement).addClass("qnBorder"));
	this.fadeIn(500);
}

$.fn.inViewport = function(){
	var top = Math.max($(window).height() / 4 - this[0].offsetHeight / 4, 0);
	top=top-150;
    this.css('top', top + "px");
    this.css('position', 'fixed');
}

/* Function for closing the popover divs*/
function closeDivs(element){
	hideAddDiv();
	$("#backgroundPopup").hide();
	(cloneDiv!=" ")?($("#"+element).replaceWith(cloneDiv)):" ";
	cloneDiv=" ";
}

/*
 * showDndHint()	:	Toggle the hint for all Match the following questions	
 * showFibHint()	:	Toggle the hint for all Fill in the blanks questions
 * showDndTHint()	:	Toggle the hint for all Drag and drop textual questions
 */
function showDndHint(elementId,hntElement){
	$(hntElement).val("Y");
	$("#"+elementId).toggle();
}

function showFibHint(elementId,x){
	$("#hntUsed"+x).val("Y");
	$("#"+elementId).toggle();
}

function showDndTHint(elementId,x){
	$("#hntUsed"+x).val("Y");
	$("#"+elementId).toggle();
}

/*
 *  Get the count of questions under each section and
 * delete the Section header when no questions are present in a section
 */
function getQnCount(elements,className,type){
	var count=$(className).size();
	if(count==0){
		$("#"+elements).hide();
		$("#secInsImg"+type).closest("tr").remove();
		$("#evaluate"+type).closest("tr").remove();
	}
}

/* 
 * Function to validate both FIB and DNDT Quiz
 */
function validateFibDndQuiz(formname){
	var qntext = document.getElementById(formname);	
	if(qntext.value==" " || qntext.value==""){
		alert('Please enter the question');
		qntext.focus();
		return false;
	}
	return true;
}