/**
 * This java script is used for validate the online registration form of student in the specific course.
 * This java script return alert message and highlighted text field if the input value in the form is missing or wrong.
 * @see template InstUserRegistrationManagement.vm
 * @author <a href="mailto:tejdgurung20@gmail.com">Tej Bahadur</a>
 * @Created Date: 10-12-2011
 */

function  checkField(frm,fld) {
var reason = "";
    reason += validateInstName(frm.instName);
    reason += validateGroup(frm.group);
    reason += validateRole(frm.role);
    reason += validateEmail(frm.EMAIL);
if (reason != "") {
        alert("Some fields need correction:\n\n" + reason);
        return false;
}
        frm.actionName.value=fld.name;
        frm.submit();
}

function validateInstName(fld){
        var error="";
        if(fld.value == "Select Institute"){
                error="* You havn't selected Institute name.\n";
        }
        return error;
}
function validateGroup(fld){
        var error="";
        if(fld.value == ""){
                error="* Please search specific group name according to Insitute name.\n";
        }
        return error;
}
function validateRole(fld){
        var error="";
        if(fld.value.length == 0){
                error="* you havn't selected User's Role.\n";
        }
        return error;
}

function trim(s){
        return s.replace(/^\s+|\s+$/, '');
}

function validateEmail(fld) {
        var error="";
        var tfld = trim(fld.value);                         //value of field with whitespace trimmed off
        var emailFilter = /^[^@]+@[^@.]+\.[^@]*\w\w$/ ;
        var illegalChars= /[\(\)\<\>\,\;\:\\\"\[\]]/ ;
        if (fld.value == "")
        {
                fld.style.background = 'Yellow';
                error = "* You havn't entered valid email id.\n";
        }
        else if (!emailFilter.test(tfld))
        {
                //test email for illegal characters
                 fld.style.background = 'Yellow';
                 error = "* Please enter a valid email id.\n";
                 }
	else if (fld.value.match(illegalChars))
        {
        	fld.style.background = 'Yellow';
        	error = "* The email id contains illegal characters.\n";
        }
        else
        {
        	fld.style.background = 'White';
            }
        return error;
 }

/**
* This function is used for enable and disable the Roll No textbox if program selected by user.
*/

function ChkPrg(frm,field)
{
	if((frm.prg.value=="Select Program")||(frm.prg.value=="RWP"))
        {
        	frm.rollno.value="";
                frm.rollno.disabled=true;
                frm.rollno.style.background = '';
        }
        else
        {
        	frm.rollno.disabled=false;
		frm.rollno.style.background = 'Yellow';
		alert("* Please insert roll no !!");
		if(frm.rollno.value!=""){
                frm.rollno.style.background = 'white';
                }

       }
}

/**
* This function is used for clear the form data 
*/

function clearField(frm,field)
{
	frm.EMAIL.value="";
        frm.rollno.disabled=true;
        frm.rollno.style.background='';
}

/**
 * This java script is used for validate the online registration form of instructor in  the specific courses
 * This java script return alert message and highlighted the field if the input value in the form is missing or wrong.
 * @see template InstUserRegistrationManagement.vm
 */

function  checkCourseName(frm,fld) {
var reason = "";
    reason += validateCourseId(frm.COURSEID);
    reason += validateCourseName(frm.CNAME);
    reason += validateEmail(frm.EMAIL);
if (reason != "") {
        alert("Some fields need correction:\n\n"+reason);
        return false;
}
        frm.actionName.value=fld.name;
        frm.submit();
}

function validateCourseId(fld){
var error = "";
        if (fld.value == "") {
                fld.style.background = 'Yellow';
                error = "* you havn't entered course id.\n";
        } else {
                fld.style.background = 'White';
        }
        return error;
}
function validateCourseName(fld){
var error = "";
        if (fld.value == "") {
                fld.style.background = 'Yellow';
                error = "* you havn't entered course name.\n";
        } else {
                fld.style.background = 'White';
        }
        return error;
}
function trim(s){
        return s.replace(/^\s+|\s+$/, '');
}
function validateEmail(fld) {
        var error="";
        var tfld = trim(fld.value);                        // value of field with whitespace trimmed off
        var emailFilter = /^[^@]+@[^@.]+\.[^@]*\w\w$/ ;
        var illegalChars= /[\(\)\<\>\,\;\:\\\"\[\]]/ ;
        if (fld.value == "")
        {
                fld.style.background = 'Yellow';
                error = "* You havn't entered valid  email address.\n";
        }
        else if (!emailFilter.test(tfld))
        {
                fld.style.background = 'Yellow';
                error = "* Please enter a valid email address.\n";
        }
        else if (fld.value.match(illegalChars))
        {
                fld.style.background = 'Yellow';
                error = "* The email address contains illegal characters.\n";
        }
        else
        {
                fld.style.background = 'White';
        }
        return error;
}

/**
* This function is used for clear the form data 
*/

function checkClear(frm,field)
{
	frm.COURSEID.value="";
        frm.CNAME.value="";
        frm.EMAIL.value="";
}

