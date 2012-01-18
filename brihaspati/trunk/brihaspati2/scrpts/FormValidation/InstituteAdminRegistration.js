/**
 * This java script is used for validate the registration form of institute admin in the Institue or organization.
 * This java script return alert message and highlighted the field if the input value in the form is missing or incorrect.
 * @see template, InstituteAdminRegistration.vm
 * @author <a href="mailto:tejdgurung20@gmail.com">Tej Bahadur</a>
 * @Created Date: 10-12-2011
 */

function  checkField(frm,fld) {
var reason = "";
    reason += validateIName(frm.INAME);
    reason += validateIAddress(frm.IADDRESS);
    reason += validateICity(frm.ICITY);
    reason += validatePincode(frm.IPINCODE);
    reason += validateState(frm.ISTATE);
    reason += validateCountryCode(frm.ccode);
    reason += validateRegionCode(frm.rcode);
    reason += validatePhoneNumber(frm.phnumber);
    reason += validateInsDomain(frm.IDOMAIN);
    reason += validateInsWebsite(frm.IWEBSITE);
    reason += validateAdminFName(frm.IADMINFNAME);
    reason += validateAdminLName(frm.IADMINLNAME);
    reason += validateDesgnation(frm.IADMINDESIGNATION);
    reason += validateEmail(frm.IADMINEMAIL);
    reason += validatePaswd(frm.IADMINPASSWORD);
if (reason != "") {
        alert("Some fields need correction:\n\n" + reason);
        return false;
}
        frm.actionName.value=fld.name;
        frm.submit();
}
function validateIName(fld) {
        var error = "";
        if (fld.value.length == 0) {
                fld.style.background = 'Yellow';
                error = "* You havn't enterd Instiute name.\n";
        } else {
                fld.style.background = 'White';
        }
        return error;
}
function validateIAddress(fld) {
        var error = "";
        if (fld.value.length == 0) {
                fld.style.background = 'Yellow';
                error = "* You havn't enterd Instiute addres.\n";
        } else {
                fld.style.background = 'White';
        }
        return error;
}
function validateICity(fld){
        var error="";
        if (fld.value.length == 0) {
                fld.style.background = 'Yellow';
                error="* You havn't enterd Instiute city.\n";
        }else{
                fld.style.background = 'White';
    }
        return error;
}

function validatePincode(fld){
        var error="";
        var stripped = fld.value.replace(/[\(\)\.\-\ ]/g, '');
        if (fld.value == "") {
                fld.style.background = 'Yellow';
                error = "* You havn't enterd Pincode.\n";
        } else if (isNaN(parseInt(stripped))) {
                fld.style.background = 'Yellow';
                error = "* Pincode contains illegal characters.\n";
        } else if (!(stripped.length == 6)) {
                fld.style.background = 'Yellow';
                error = "* Pincode has wrong length. Make sure you have enter 6 digit numeric number.\n";
        } else{
		fld.style.background = 'White';
	}
        return error;
}
function validateState(fld){
        var error="";
        if (fld.value.length == 0) {
                fld.style.background = 'Yellow';
                error="* You havn't enterd State name.\n";
        }else{
                fld.style.background = 'White';
        }
        return error;
}
function validateCountryCode(fld){
        var error="";
        var stripped = fld.value.replace(/[\(\)\.\-\ ]/g, '');
        if (fld.value == "") {
                fld.style.background = 'Yellow';
                error = "* You havn't enterd Country code.\n";
        } else if (isNaN(parseInt(stripped))) {
                fld.style.background = 'Yellow';
                error = "* Country code contains illegal characters.\n";
        } else if (!(stripped.length <3)) {
  		fld.style.background = 'Yellow';
                error = "* Country code should not be more then 2 digit.\n";
        }else{
                fld.style.background = 'White';
        }
        return error;
}

function validateRegionCode(fld){
        var error="";
        var stripped = fld.value.replace(/[\(\)\.\-\ ]/g, '');
        if (fld.value == "") {
                fld.style.background = 'Yellow';
                error = "* You havn't enterd Region code.\n";
        } else if (isNaN(parseInt(stripped))) {
                fld.style.background = 'Yellow';
                error = "* Region code contains illegal characters.\n";
        } else if (!(stripped.length <6)) {
                fld.style.background = 'Yellow';
                error = "* Region code should not be more then 5 digit.\n";
        }else{
                fld.style.background = 'White';
        }
        return error;
}
function validatePhoneNumber(fld){
 var error="";
        var stripped = fld.value.replace(/[\(\)\.\-\ ]/g, '');
        if (fld.value == "") {
                fld.style.background = 'Yellow';
                error = "* You havn't enterd Phone number.\n";
        } else if (isNaN(parseInt(stripped))) {
                fld.style.background = 'Yellow';
                error = "* Phone number has illegal characters.\n";
        } else if (!(stripped.length <9)) {
                fld.style.background = 'Yellow';
                error = "* Phone number should not be more than 8 digit.\n";
        }else{
                fld.style.background = 'White';
        }
        return error;
}
function validateInsDomain(fld) {
        var error = "";
        if (fld.value.length == 0) {
                fld.style.background = 'Yellow';
                error = "* You havn't enterd Institute Domain.\n";
  } else {
                fld.style.background = 'White';
        }
        return error;
}

function validateInsWebsite(fld) {
        var error="";
        if ((fld.value.length == 0)||(fld.value.indexOf("www")== -1)||(fld.value.indexOf(".") == -1)) {
                fld.style.background = 'Yellow';
                error = "* You havn't enterd valid website name.\n";
        }
        else {
                fld.style.background = 'White';
        }
        return error;
}

function validateAdminFName(fld){
        var error="";
         if (fld.value.length == 0){
                fld.style.background="Yellow";
                error="* You havn't enterd admin's first name.\n";
        }
        else{
                fld.style.background="White";
        }
        return error;
}

function validateAdminLName(fld){
        var error="";
        if(fld.value.length == 0 ){
                fld.style.background="Yellow";
                error="* You havn't enterd admin's last name.\n";
        }
        else{
                fld.style.background="White";
        }
        return error;
}
function validateDesgnation(fld){
        var error="";
        if(fld.value.length == 0){
                fld.style.background="Yellow";
                error="* You havn't enterd admin's designation.\n";
        }
        else{
                fld.style.background="White";
        }
        return error;
}

function trim(s){
        return s.replace(/^\s+|\s+$/, '');
}

function validateEmail(fld) {
        var error="";
        var tfld = trim(fld.value);          //value of field with whitespace trimmed off
        var emailFilter = /^[^@]+@[^@.]+\.[^@]*\w\w$/ ;
        var illegalChars= /[\(\)\<\>\,\;\:\\\"\[\]]/ ;
        if (fld.value == "")
        {
                fld.style.background = 'Yellow';
                error = "* You havn't enterd valid email address.\n";
        }
        else if (!emailFilter.test(tfld))
        {
                //test email for illegal characters
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
function validatePaswd(fld){
        var error="";
        if(fld.value.length == 0){
                fld.style.background="Yellow";
                error="* You havn't enterd Password.\n";
        }
        else{
                fld.style.background="White";
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
