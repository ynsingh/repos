/**
 * This java script function is used for validate the Remote Course form.
 * @see template:- CourseMgmt_User/Configuration.vm
 * java script functions returns alert messages and highlighting the field if the input values in the form is missing or incorrect.
 * @author <a href="mailto:palseema30@gmail.com">Manorama Pal</a>
 * @author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>
 * @Created Date: 22-08-2012
 **/


/**
*This method is for check the IP adress
*/
function checkIp (fld){
var error="";
	var ipaddress = fld.value;
	var patt = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
	var match = ipaddress.match(patt);
	if (ipaddress == "0.0.0.0") {
		fld.style.background = 'Yellow';
                error = "* The IP Address field cannot be 0.0.0.0\n";
        }
	else if (ipaddress == "255.255.255.255") {
		fld.style.background = 'Yellow';
                error = "* The IP Address field cannot be 255.255.255.255\n";
	}
	else if (ipaddress == "127.0.0.0"){
		fld.style.background = 'Yellow';
                error = "* 127.0.0.0 is a special IP address and cannot be used here.\n";
	}
	else if (ipaddress == "127.0.0.1") {
		fld.style.background = 'Yellow';
                error = "* 127.0.0.1 is not a valid IP address.\n";
	}
	if (match == null) {
		fld.style.background = 'Yellow';
                error = "* you havn't entered IP Adrress.\n";
	}
	else{
        	/**
                 * there are 5 elements in ipArray
                 */
                 for (i = 0; i < 5; i++)
                 {
                 	thisSegment = match[i];
                        if (thisSegment > 255)
                        {
                        	error = "* is not a valid IP address.Write as 172.28.44.20.\n";
                                /*
                                * break can be used here but break immediately breaks statements
                                * if we specify the last value than also the for loop breaks
                                * but after execution of full loop
                                */
                                i = 4;
                        }

                }//for
	}//else
return error;
}//function
function validateSectkey(fld){
var error = "";
        var string=fld.value;
        var len=string.length;
        if (fld.value == "") {
                fld.style.background = 'Yellow';
                error = "* you havn't entered secret key.\n";
        }
        else if((len>33) || (len<4))
        {
                fld.style.background = 'Yellow';
                error = "* Secret Key must contain minimum 4 characters and maximum 32 characters.\n";
        }
        else {
                fld.style.background = 'White';
        }
        return error;
}

function validateRCourseId(fld){
var error = "";
        if (fld.value == "") {
                fld.style.background = 'Yellow';
                error = "* Please select course Id by click the get button.\n";
        } else {
                fld.style.background = 'White';
        }
        return error;
}
function validateRloginName(fld){
var error = "";
        if (fld.value == "") {
                fld.style.background = 'Yellow';
                error = "* you havn't entered Login Name.\n";
        } else {
                fld.style.background = 'White';
        }
        return error;
}
function validateRInstName(fld){
var error = "";
        if (fld.value == "") {
                fld.style.background = 'Yellow';
                error = "* Please select Institute Name by click the get button.\n";
        } else {
                fld.style.background = 'White';
        }
        return error;
}

function checkForm(frm,fld)
{
var reason = "";
        reason += checkIp(frm.iip);
	reason += validateRInstName(frm.inm);
        reason += validateRCourseId(frm.cid);
        reason+= validateRloginName(frm.riname);
        reason+= validateSectkey(frm.sec_key);
        if (reason != "") {
                alert("Some fields need correction:\n\n" + reason);
                return false;
        }
        frm.submit();

}
function callapi(frm,fld)
{
	var reason = "";
        reason += checkIp(frm.iip);
        //reason += validateRInstName(frm.inm);
        //reason += validateRCourseId(frm.cid);
	if (reason != "") {
                alert("Some fields need correction:\n\n" + reason);
                return false;
        }
	frm.submit();
}
/*
 * This function is used for reset the form.
 */

function checkClear(frm,field)
{
        frm.iip.value="";
        frm.inm.value="";
	frm.riname.value="";
        frm.cid.value="";
        frm.sec_key="";
}

