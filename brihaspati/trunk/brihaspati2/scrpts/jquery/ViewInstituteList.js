/**
 * Sets the display options for REGISTERED INSTITUTE LIST page
 * @author: Priyanka Rawat (rpriyanka12@ymail.com)
 */

function validateForm(form) {

    var instField = form.inst;
    var fromField = form.from;
    var messageField = form.message;
    var subjectField = form.subject;

    if (isNotEmpty(instField)) {
        if(isNotEmpty(fromField)) {
	    if(isNotEmpty(subjectField)) {
		if(isNotEmpty(messageField)) {
            		return true;
		}
	    }
        }
    }
	
    return false;
}

function isNotEmpty(field) {

    var fieldData = field.value;

    if (fieldData.length == 0 || fieldData == "" || fieldData == "Select Institute") {
	field.style.background = 'Yellow';
        field.className = "FieldError"; //Classs to highlight error
        alert("Please fill the highlighted field.");
        return false;
    } else {
        field.className = "FieldOk"; //Resets field back to default
        return true; //Submits form
    }
}
