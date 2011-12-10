/**
 * This java script is used for validate the registration form of instructor in specific course in the Institue or organization.
 * This java script return alert message and highlighted the field if the input value in the form is missing or wrong.
 * @see template InstUserRegistrationManagement.vm, RegisterationManagement.vm
 * @author <a href="mailto:tejdgurung20@gmail.com">Tej Bahadur</a>
 * @Created Date: 10-12-2011
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
                error = "* You havn't entered course id.\n";
        } else {
                fld.style.background = 'White';
        }
        return error;
}
function validateCourseName(fld){
var error = "";
        if (fld.value == "") {
                fld.style.background = 'Yellow';
                error = "* You havn't entered course name.\n";
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
 * This java script is used for open the popup window for help document. 
 * @see template InstUserRegistrationManagement.vm,RegisterationManagement.vm
 */

function popupWin(url,popupName)
{
	Win1=window.open(url,popupName,"resizable=0,scrollbars=1,height=400,width=400");
}

/**
 * This java script is used validate the File Upload field for automatic multiple courses.
 * @see template InstUserRegistrationManagement.vm, RegisterationManagement.vm 
 */

function  CheckFields(frm,fld) {
var reason = "";
	reason += validateFile(frm.file);
if (reason != "") {
        alert(""+ reason);
        return false;
}
        frm.actionName.value=fld.name;
        frm.submit();
}
function validateFile(fld){
var error = "";
        if (fld.value.length == 0) {
                fld.style.background = 'Yellow';
                error = "* you havn't selected specific file for multiple course registration !!\n";
        } else {
                fld.style.background = 'White';
        }
        return error;
}

/**
 * This java script is used validate the Match String.
 */

function checkNull(frm,fld) {
var reason = "";
    reason += validateMatchString(frm.valueString);

if (reason != "") {
        alert("" + reason);
        return false;
}
        frm.actionName.value=fld.name;
        frm.submit();
}
function validateMatchString(fld){
var error = "";
        if (fld.value.length == 0) {
                fld.style.background = 'Yellow';
                error = "\n* The 'Match box' can not be empty !!";
        } else {
                fld.style.background = 'White';
        }
        return error;
}

/*
* This function in used for clear the form data
*/

function checkClear(frm,field)
{
	frm.COURSEID.value="";
        frm.EMAIL.value="";
        frm.CNAME.value="";
}

