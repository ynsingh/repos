 


/*    login   page*/
function authenticate(hostname)
{
	window.location.href =hostname+"/login/auth";
}

/*   index page onload function    */

 var root_url=getRootUrl();
  function doSomething(){
  jQuery.ajax({
type:'POST',async:false,
data: {},
dataType: "json",
url:root_url+"home/sessionCheckOnBackButton",
success:function(data,textStatus){
if(data.sessionStatus=='yes')
{
 window.location.href =root_url+'logout';
}
},
error:function(XMLHttpRequest,textStatus,errorThrown){}
});
}
  
  
/*  admin home page loading  */

function adminHome(hostname,uid,sesid)
{
window.location.href =hostname+'/contentHome/index';
}
  
  
/*--------------------if image not found in subject icon----------------*/
  
function loadOther(me){
 var root_url=getRootUrl();
me.src=root_url+"images/temp.png";
}



// function to display the first tab
function displayFirstTab(contentId,experimentId)
{
	if(contentId==false&&experimentId==false)
		{
		var subid=getUrlPara('subjectId');
		jQuery.ajax({
	  type:'POST',async:false,
	  data: {subid:subid},
	  dataType: "json",
	  url:root_url+"home/getContentId",
	  success:function(data,textStatus){
		  var url=document.URL;
			 var finalURL = url+"&exp="+data.experimentId+"&cnt="+data.contentId;
			 window.location.href =finalURL; 
		},
	error:function(XMLHttpRequest,textStatus,errorThrown){}
	    });
	
		}
}

//function to change content based on tab click
function changeLocation(contentid){ 
	var newAdditionalURL = "";
	var tempArray = document.URL.split("?");
	var baseURL = tempArray[0];
	var aditionalURL = tempArray[1]; 
	var temp = "";
	if(aditionalURL)
	{
	var tempArray = aditionalURL.split("&");
	for (var i = 0; i < tempArray.length; i++) {
	        if(tempArray[i].indexOf("cnt") == -1){
	                        newAdditionalURL += temp+tempArray[i];
	                                temp = "&";
	                        }
	                }
	}
	var cnt_txt = temp+"cnt="+contentid;
	var finalURL = baseURL;
	return finalURL;
}

/* function to change page title dynamically */
function changePageTitleDynamically()
{
	var subjectId =getUrlPara('subjectId');
	var experimentId =getUrlPara('exp');
	var contentId =getUrlPara('cnt');
jQuery.ajax({
type:'POST',async:false,
data: {subjectId:subjectId,experimentId:experimentId,
	contentId:contentId},
dataType: "json",
url:root_url+"home/titleChangeDynamically",
success:function(data,textStatus){
  document.title=data.titleData;
	},
error:function(XMLHttpRequest,textStatus,errorThrown){}
  });
}




// this function is used to decodeBase64
var keyStr = "ABCDEFGHIJKLMNOP" +
"QRSTUVWXYZabcdef" +
"ghijklmnopqrstuv" +
"wxyz0123456789+/" +
"=";
function decode64(input) {
var output = "";
var chr1, chr2, chr3 = "";
var enc1, enc2, enc3, enc4 = "";
var i = 0;

// remove all characters that are not A-Z, a-z, 0-9, +, /, or =
var base64test = /[^A-Za-z0-9\+\/\=]/g;
if (base64test.exec(input)) {
alert("There were invalid base64 characters in the input text.\n" +
"Valid base64 characters are A-Z, a-z, 0-9, �+�, �/�, and �=�\n" +
"Expect errors in decoding.");
}
input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

do {
enc1 = keyStr.indexOf(input.charAt(i++));
enc2 = keyStr.indexOf(input.charAt(i++));
enc3 = keyStr.indexOf(input.charAt(i++));
enc4 = keyStr.indexOf(input.charAt(i++));

chr1 = (enc1 << 2) | (enc2 >> 4);
chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
chr3 = ((enc3 & 3) << 6) | enc4;

output = output + String.fromCharCode(chr1);

if (enc3 != 64) {
output = output + String.fromCharCode(chr2);
}
if (enc4 != 64) {
output = output + String.fromCharCode(chr3);
}

chr1 = chr2 = chr3 = "";
enc1 = enc2 = enc3 = enc4 = "";

} while (i < input.length);

return output;
}
//For displaying hint in quiz
function showHint(bid,flagHint){
document.getElementById(flagHint).value='Y';
if(document.getElementById(bid).style.display == "none") {
	document.getElementById(bid).style.display = "block";
} else if (document.getElementById(bid).style.display = "block") {
	document.getElementById(bid).style.display = "none";
}
return true;
}



//function for validate user registration form.
function registrationFormValidation(regForm)
{	
	var passwd = document.regForm.paswd;
	var confPwd = document.regForm.confPaswd;
	if(document.regForm.emailId){
	var email = document.regForm.emailId;

	AtPos = email.value.indexOf("@");
	dotPos = email.value.indexOf(".");
	
	//email validation
	if((email.value=="") || (email.value==null))
	{
		alert("Please enter the email id.");
		email.focus();
		return false;
	}	
	else if((AtPos==-1) || (AtPos==0))
	{
		alert("Please enter a valid email id");
		email.focus();
		return false;
	}
	else if((dotPos==-1) || (dotPos==0))
	{
		alert("Please enter a valid email id");
		email.focus();
		return false;
	}
	}
	//Password Validation 
 	 if((passwd.value=="") || (passwd.value==null))
	{
		alert("Please enter a password");
		passwd.focus();
		return false;
	}	
	/*else if(passwd.value.length < 5) 
	{ 
		alert("Password must contain at least five characters!"); 
		passwd.value="";
		confPwd.value="";
		passwd.focus();
		return false; 
	} */
	
	//Confirm Pasword Validation	
	else if(confPwd.value=="")
	{
		alert("Please enter confirm password");
		confPwd.focus();
		return false;
	}
	
	else if(confPwd.value!=passwd.value)
	{
		alert("The password you entered do not match!");
		passwd.value="";
		confPwd.value="";
		passwd.focus();
		return false;
	}
	if( ( (document.getElementById("roleList.id").value) == 'null')  )
    {
	    alert("Please Select Role");
	    document.getElementById("roleList.id").focus();
	    return false;
    }
	if( ( (document.getElementById("universityList.id").value) == 'null')  )
    {
	    alert("Please Select University");
	    document.getElementById("universityList.id").focus();
	    return false;
    }
	var phoneNumber=document.regForm.phoneNumber.value;
   if(phoneNumber){
	   if(!isInteger(phoneNumber))
		{
			 alert('Enter Valid Phone Number.');
			 document.regForm.phoneNumber.value="";
			 document.regForm.phoneNumber.focus();
			   return false;
		}
   }
	if(confirm("Are you sure, you want to register?"))
	{
		return true;
	}
	else
	{
		return false;
	}
}

function closeEditUserForm()
{
	  $('#editUserForm').hide();
	 location.reload(true);

}



function validateUser(editUserForm){
	
	var phoneNumber=document.editUserForm.phoneNumber.value;
	if(phoneNumber){
	if(!isInteger(phoneNumber))
	{
		 alert('Enter Valid Phone Number.');
		 document.editUserForm.phoneNumber.focus();
		   return false;
	}
	else{
    	return true
	}
	}
}


function isInteger (s)
{
var i;
if (isEmpty(s))
if (isInteger.arguments.length == 1)
  return 0;
else
  return (isInteger.arguments[1] == true);
for (i = 0; i < s.length; i++)
{
var c = s.charAt(i);
if (!isDigit(c))
  return false;
}
return true;
}


function isEmpty(s)
{
return ((s == null) || (s.length == 0))
}


function isDigit (c)
{
return ((c>="0") && (c<="9"))
}
