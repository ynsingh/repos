/**
 * This java script is used for validate the registration form of instructor in specific course in the Institue or organization.
 * This java script return alert message and highlighted the field if the input value in the form is missing or wrong.
 * @see template, InstUserMgmt_Admin.vm
 * @author <a href="mailto:tejdgurung20@gmail.com">Tej Bahadur</a>
 * @Created Date: 10-12-2011
 */

function  checkField(frm,fld) {
var reason = "";
    reason += validateGroup(frm.group);
    reason += validateRole(frm.role);
    reason += validateEmail(frm.EMAIL);
    reason += validateRollNo(frm.rollno);
if (reason != "") {
        alert("Some fields need correction:\n\n" + reason);
        return false;
}
        frm.actionName.value=fld.name;
        frm.submit();
}

function validateGroup(fld){
        var error="";
        if(fld.value.length == 0){
                error="* You havn't selected Group name.\n";
        }
        return error;
}
function validateRole(fld){
        var error="";
        if(fld.value.length == 0){
                error="* You havn't selected user's Role.\n";
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
function validateRollNo(fld){
        var error="";
        if((fld.value.length == 0)&&(fld.disabled==false)){
		fld.style.background = 'Yellow';
                error="* you havn't entered User's Roll No.\n";
        }
	else{
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
        }
}

/**
* This function is used for clear the form data 
*/

function checkClear(frm,field)
{
	frm.EMAIL.value="";
        frm.PASSWD.value="";
        frm.FNAME.value="";
        frm.LNAME.value="";
        frm.role.value="";
        frm.rollno.disabled=true;
        frm.rollno.style.background = '';
}

