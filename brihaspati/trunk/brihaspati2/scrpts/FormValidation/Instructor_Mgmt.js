/**
 * This java script is used for validate the registration form of instructor in specific course in the Institue or organization.
 * This java script return alert message and highlighted the field if the input value in the form is missing or wrong.
 * @see template, UserMgmt_Instructor.vm, StudentManagement.vm
 * @author <a href="mailto:mail2sunil00@gmail.com">Sunil Yadav</a>
 * @Created Date: 18-01-2012
 */
		
	
function  checkFieldInst(frm,fld) {
var reason = "";
    reason += validateEmail(frm.EMAIL);
	if (reason != "") {
        alert("Some fields need correction:\n\n" + reason);
        return false;
}
        frm.actionName.value=fld.name;
        frm.submit();
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
                error = "You havn't entered valid  email address.\n";
        }
        else if (!emailFilter.test(tfld))
        {
                fld.style.background = 'Yellow';
                error = "Please enter a valid email address.\n";
        }
        else if (fld.value.match(illegalChars))
        {
                fld.style.background = 'Yellow';
                error = "The email address contains illegal characters.\n";
        }
        else
        {
                fld.style.background = 'White';
        }
	 return error;
}


	function addDeleteList(field,frm)
        {
                if(field.checked){
                        frm.deleteFileNames.value=frm.deleteFileNames.value+field.name+"^";

                }
                else{
                        var rmFile,index,actualString,preString,postString;
                        actualString=frm.deleteFileNames.value;
                        index=actualString.indexOf(field.name+"^",0);
                        preString=actualString.substring(0,index);
                        postString=actualString.substring(index+field.name.length+1);
                        actualString=preString+postString;
                        frm.deleteFileNames.value=actualString;
                }
        }

	/**
         * This java script is used for Removing checkbox selected user from List. 
         * @see template UserMgmt_Instructor.vm, RemoveStudent.vm.
         */

	function DeleteField(frm,field) {
                if(frm.deleteFileNames.value!="")
                {
                frm.actionName.value=field.name;
                frm.submit();
                }else
                alert("Please select checkbox !!");
        }

	function checkNull(frm,field){
                if(frm.valueString.value!=""){
                        frm.actionName.value=field.name;
                        frm.submit();
                }
                else{
                        alert("The 'Match String' text box can not be NULL");
                }
        }



