function clearcontent(id)
{
	
	if(id=='hintText')
	{
		if(document.getElementById(id).value=="Hint")
		{
				document.getElementById(id).value="";
		}
	}
}
function fillcontent(id)
{
	
	if(id=='hintText')
	{
		if(document.getElementById(id).value=="")
			{
					document.getElementById(id).value="Hint";
			}	
	}
}
//add choice text validation
function validateAddQuiz(formname)
{	
	//var temp='form1['correctAns']';
	/* ansChCnt - Answer choice count gives the number of choices. */
	var ansChCnt=($("#addDiv").find("#dataTable").find("tr").size()) - 2;
	document.getElementById('ansVal').value = ansChCnt;
	var qntext = document.getElementById('qnText');	
	var incVal = document.getElementById('ansVal').value;
	var radAns = document.getElementById('correctAns');
	var assign = document.getElementById('idAssign').value;
	var assignArr	= assign.split(",");	
	if(qntext.value==" " || qntext.value=="Question text" || qntext.value=="")
	{
		alert('Please enter the question');
		qntext.value ="";
		qntext.focus();
		return false;
	}
	if(assignArr.length >= 2)
	{			
		var j;
		for (var i = 0; i <= incVal; i++)
		{
			j=i+1;
			if(document.getElementById("choice"+j))
			{
				if (document.getElementById("choice"+j).value ==" " || document.getElementById("choice"+j).value == "choice"+j || document.getElementById("choice"+j).value=="")
				{		   
					alert("Please enter all choices");
					document.getElementById("choice"+j).value="";
					document.getElementById("choice"+j).focus();
					return false;
				}	
			}
		}
	
	}
	else
	{
		alert('Atleast two choices are needed');
		return false;
	}
	if(assignArr.length >= 2)
	{

		if (!checkRadio("addQuizFrom","correctAns"))
		{
			alert("Please select the correct choice");
			return false;
		}
	}

	return true;
}
function checkRadio (frmName, rbGroupName) 
{
	var radios=document.getElementsByName( rbGroupName );

	 for (var i=0; i <radios.length; i++) 
	 {
		if (radios[i].checked) 
		{
			return true;
		}
	}
	 return false;
} 

function closeEditQuestionForm(id)
{
	  $('#editQuestionForm').hide();
	  $("#backgroundPopup").hide();
}

//edit text choice validation
function validateEditQuiz(form1)
{
	
	var quest = document.getElementById('questTxt');
	
	if(quest.value=="" || quest.value==" " || quest.value=="Question text")
	{
		alert('Please enter the question');
		quest.focus();		
		return false;
	}
	
	var chks = document.getElementsByName('choices[]');//here rr[] is the name of the textbox
 
	for (var i = 0; i < chks.length; i++)
	{        
		if (chks[i].value=="")
		{
			alert("Please fill all the choices");
			chks[i].focus();
			return false;            
		}
	}
	
	return true;
}

//function used to pop up an image window
function imagePopUp(number){
	 var root_url=getRootUrl();
	window.open (root_url+"editExperiment/cropImage?number="+number,"childWindow",
	"width=650,height=500,scrollbars=1,resizable=no,toolbar=no,location=no,directories=no,status=no,top=150,left=130,menu bar=no,copyhistory=no");
}

// function which shows the cropped image 
function CallAlert(path,num,filename)
{	
	if(document.getElementById('imgUpload'+num) && document.getElementById('imgValue'+num)) 
	{
		document.getElementById('imgUpload'+num).src=path;	
		document.getElementById('imgValue'+num).value=filename;
	}
	if(document.getElementById('qnimgUpload'+num) && document.getElementById('qnimgValue'+num)) 
	{
		document.getElementById('qnimgUpload'+num).src=path;	
		document.getElementById('qnimgValue'+num).value=filename;
	}
	if(document.getElementById('editImage'+num) && document.getElementById('editHid'+num)) 
	{
		document.getElementById('editImage'+num).src=path;
		document.getElementById('editHid'+num).value=filename;
	}
	if(document.getElementById('editImgUpload'+num) && document.getElementById('editImgValue'+num)) 
	{
		document.getElementById('editImgUpload'+num).src=path;	
		document.getElementById('editImgValue'+num).value=filename;
	}	
	if(document.getElementById('img'+num) && document.getElementById('imgHidVal'+num))
	{
		document.getElementById('img'+num).src=path;
		document.getElementById('imgHidVal'+num).value=filename;	
	}
	if(document.getElementById('eimgUpload'+num) && document.getElementById('eimgValue'+num)) 
	{
		document.getElementById('eimgUpload'+num).src=path;	
		document.getElementById('eimgValue'+num).value=filename;
	}
}

//add choice image validation
function addImgQuiz(form1)
{	
	var qtext = document.forms["addImageQuizForm"].elements['qnText'];		
	if(qtext.value==" " || qtext.value=="Question text")
	{
		alert('Please enter the question');
		qtext.value ="";
		qtext.focus();
		return false;
	}
	
	for(var j=1; j<5; j++)
	{			
		if(document.getElementById('imgUpload'+j))
		{
			var urlString = document.getElementById('imgUpload'+j).src;
			var lastIndex = urlString.lastIndexOf('/');
			var suburl = urlString.substr(lastIndex+1,urlString.length);
			
			if(suburl=="upload_question.jpg")
			{
				alert('Please upload image choice');
				return false;
			}
		}		
	}
	if (!checkRadio("addImageQuizForm","correctAns"))
	{
		alert("Please select the correct choice");
		return false;
	}

	return true;	
}

//edit choice image validation
function imgQuizValidate()
{
	var qnImgTxt = document.getElementById('questTxt');
	
	if(qnImgTxt.value=="" || qnImgTxt.value==" ")
	{
		alert('Please enter the question');
		qnImgTxt.focus();
		return false;
	}
	return true;
}
