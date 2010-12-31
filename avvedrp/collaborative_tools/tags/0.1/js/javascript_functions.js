function authenticate()
{
	window.open ("login_page.php","openwindowname","width=450,height=400,scrollbars=no,resizable=no,toolbar=no,location=no,directories=no,status=no,top=180, left=130,menu  bar=no,copyhistory=no");	
}
function closewindow() {
	window.close()
	}
function redirAfterLogin() {
	window.opener.location.href = 'index.php?pg=HOME';
	window.close()
}
function validate()
{
	var username=document.getElementById('username');
	var password=document.getElementById('password');
	if(username.value=="")
	{
		alert("Please provide username.");
			username.focus();
			return(false);
	}
	if(password.value=="")
	{
		alert("Please provide password.");
			password.focus();
			return(false);
	}
	return true;
}
function displayMsg(id, msg)
{

	document. getElementById(id). innerHTML = msg;
}

/*Js functions for pop up: CSSPOPUP*/
function toggle(div_id) {
	var el = document.getElementById(div_id);
	if ( el.style.display == 'none' ) {	el.style.display = 'block';}
	else {el.style.display = 'none';}
}
function blanket_size(popUpDivVar) {
	if (typeof window.innerWidth != 'undefined') {
		viewportheight = window.innerHeight;
	} else {
		viewportheight = document.documentElement.clientHeight;
	}
	if ((viewportheight > document.body.parentNode.scrollHeight) && (viewportheight > document.body.parentNode.clientHeight)) {
		blanket_height = viewportheight;
	} else {
		if (document.body.parentNode.clientHeight > document.body.parentNode.scrollHeight) {
			blanket_height = document.body.parentNode.clientHeight;
		} else {
			blanket_height = document.body.parentNode.scrollHeight;
		}
	}
	var blanket = document.getElementById('blanket');
	blanket.style.height = blanket_height + 'px';
	var popUpDiv = document.getElementById(popUpDivVar);
	popUpDiv_height=blanket_height/2-450;
	//150 is half popup's height
	popUpDiv.style.top = popUpDiv_height + 'px';
}
function window_pos(popUpDivVar) {
	if (typeof window.innerWidth != 'undefined') {
		viewportwidth = window.innerHeight;
	} else {
		viewportwidth = document.documentElement.clientHeight;
	}
	if ((viewportwidth > document.body.parentNode.scrollWidth) && (viewportwidth > document.body.parentNode.clientWidth)) {
		window_width = viewportwidth;
	} else {
		if (document.body.parentNode.clientWidth > document.body.parentNode.scrollWidth) {
			window_width = document.body.parentNode.clientWidth;
		} else {
			window_width = document.body.parentNode.scrollWidth;
		}
	}
	var popUpDiv = document.getElementById(popUpDivVar);
	window_width=window_width/2-150;
	//150 is half popup's width
	popUpDiv.style.left = window_width + 'px';
}
function popup(windowname) {
	blanket_size(windowname);
	window_pos(windowname);
	toggle('blanket');
	toggle(windowname);		
}
/*******************************/

//function for validation during Add Name & Description.

function checkAdd(){
	var name1 = document.form1.addName;
	var desc1 = document.form1.addDesc;
	if((name1.value=="") || (name1.value==null)){
		alert("Please fill the name field");
		name1.focus();
		return false;
	}
	if((desc1.value=="") || (desc1.value==null)){
		alert("Please fill the description field");
		desc1.focus();
		return false;
	}
}

//function for validation during Edit Name & Description.

function checkEdit(){
	var name = document.form1.editedName;
	var desc = document.form1.editedDesc;
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

//function for validation during Add Experiment Name & Description.

function checkAddExp(){
	var exp_name = document.form1.expName;
	var exp_desc = document.form1.expDesc;
	if((exp_name.value=="") || (exp_name.value==null)){
		alert("Please fill the Experiment Name");
		exp_name.focus();
		return false;
	}
	if((exp_desc.value=="") || (exp_desc.value==null)){
		alert("Please fill the Experiment Description");
		exp_desc.focus();
		return false;
	}
}

/*******************************/

//function for validation for Experiment Scheduler:Date and email validation.

function checkSchedule(){
	var time = document.scheduleForm.srartTime;
	var mail = document.scheduleForm.email;
	if((time.value=="") || (time.value==null)){
		alert("Please specify the Start time.");
		time.focus();
		return false;
	}
	if((mail.value=="") || (mail.value==null)){
		alert("Please enter the Email id.");
		mail.focus();
		return false;
	}
	if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(scheduleForm.email.value))
	{
		return (true)
	} else {
		alert("Invalid E-mail Address! Please re-enter.")
		return (false)
	}
}
//function to pop up a page
function popUpFile(fileName)
{
	var fileName;
	window.open (fileName,"openwindowname","width=450,height=400,scrollbars=no,resizable=no,toolbar=no,location=no,directories=no,status=no,top=180, left=130,menu  bar=no,copyhistory=no");	
}
//function to validate tab sequence.
function checkSequenceRepeat()
{
        var chks = document.getElementsByName('selTab[]'); //here tabTxt[] is the name of the textbox 
        for (var i=0; i<chks.length; i++)
        {	
			for(var j=i+1; j<chks.length; j++)
			{
				if (chks[i].value==chks[j].value)
				{
					alert("Please select different order number for each tab.");
					chks[j].focus();
					return false;            
				}
			}
        }
}
















