function pleaseFill(thisElement) {
	if(empty(thisElement.value)) {
		thisElement.style.border = "1px  RED SOLID";
	}
	else {
		thisElement.style.border = "1px grey SOLID";
	}
}

function empty(contents) {
	if(contents == "") {
		return true;
	}
}

function isalpha(contents) {
	var ret_val = true;
	var checkOK = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.";
	var checkStr = contents;
	for (i = 0;  i < checkStr.length;  i++)
	{
		ch = checkStr.charAt(i);
		for (j = 0;  j < checkOK.length;  j++)
		  if (ch == checkOK.charAt(j))
			break;
		if (j == checkOK.length)
		{
		  ret_val = false;
		  break;
		}
	}
	
	return ret_val;
}

function Form1_Validator(theForm)
{
	/* Validation for the First name */
	var firstName = theForm.Firstname.value;
	if(empty(firstName)){
		alert("You must enter the First name.");
		theForm.Firstname.focus();
		return (false);
	}
	else if(!isalpha(firstName))
	{
		alert("Please enter only Alphabets in the Firstname");
		theForm.Firstname.focus();
		return (false);
	}
	/* End of validtion for First name */

	/*Validations for the Lastname */
	var LastName = theForm.Lastname.value;
	if(empty(LastName)){
		alert("You must enter the Last name.");
		theForm.Lastname.focus();
		return (false);
	}
	else if(!isalpha(LastName))
	{
		alert("Please enter only Alphabets in the Lastname");
		theForm.Lastname.focus();
		return (false);
	}
	/* End of validtion for Last name */

	/*Validation for the Gender*/
	/*check if no drop down has been selected*/
	if (theForm.Gender.selectedIndex < 0)
	{
		alert("Please select one of the \"Gender\" options.");
		theForm.Gender.focus();
		return (false);
	  }
 
	/*check if the first drop down is selected, if so, invalid selection*/
	if (theForm.Gender.selectedIndex == 0)
	{
		alert("The \"Gender\" option is not a valid selection.");
		theForm.Gender.focus();
		return (false);
	}

	/*Validation for the Date of Birth*/
	/*Validation for the Date*/
	if (theForm.day.selectedIndex < 0)
	{
		alert("Please select one of the \"Date\" options.");
		theForm.day.focus();
		return (false);
	}
	/*check if the first drop down is selected, if so, invalid selection*/
	if (theForm.day.selectedIndex == 0)
	{
		alert("The  \"Date\" option is not a valid selection.");
		theForm.day.focus();
		return (false);
	}

	/*Validation for the MONTH*/
	/*check if the first drop down is selected, if so, invalid selection*/
	if (theForm.month.selectedIndex == 0)
	{
		alert("The first \"Month\" option is not a valid selection.");
		theForm.month.focus();
		return (false);
	}

	/*Validation for the YEAR*/
	/*check if the first drop down is selected, if so, invalid selection*/
	if (theForm.year.selectedIndex == 0)
	{
		alert("The \"Year\" option is not a valid selection.");
		theForm.year.focus();
		return (false);
	}
	
	/*Validations for the City */
	var city= theForm.res_City.value;
	if(empty(city)){
		alert("You must enter the City.");
		theForm.res_City.focus();
		return (false);
	}
	
	/* End of validtion for City */
	
	/*Validations for the State */
	var state= theForm.res_State.value;
	if(empty(state)){
		alert("You must enter the State.");
		theForm.res_State.focus();
		return (false);
	}
	
	/* End of validtion for State */
	
	/*Validations for the Country */
	var country= theForm.res_Country.value;
	if(empty(country)){
		alert("You must enter the Country.");
		theForm.res_Country.focus();
		return (false);
	}
	
	/* End of validtion for Country */
	
	/*Validations for the Pin */
	var pin= theForm.res_pincode.value;
	if(empty(pin)){
		alert("You must enter the Pin.");
		theForm.res_pincode.focus();
		return (false);
	}
	else if(isalpha(pin))
	{
		alert("Please enter only Numeric in the Pin");
		theForm.res_pincode.focus();
		return (false);
	}
	/* End of validtion for Pin */
	
	/*test if valid email address, must have @ and .*/
	var checkEmail = "@.";
	var checkStr = theForm.email.value;
	var EmailValid = false;
	var EmailAt = false;
	var EmailPeriod = false;
	for (i = 0;  i < checkStr.length;  i++)
	{
		ch = checkStr.charAt(i);
		for (j = 0;  j < checkEmail.length;  j++)
		{
			if (ch == checkEmail.charAt(j) && ch == "@")
			EmailAt = true;
			if (ch == checkEmail.charAt(j) && ch == ".")
			EmailPeriod = true;
			if (EmailAt && EmailPeriod)
			break;
			if (j == checkEmail.length)
			break;
		}
		/*if both the @ and . were in the string*/
		if (EmailAt && EmailPeriod)
		{
			EmailValid = true
			break;
		}
	}
	if (!EmailValid)
	{
		alert("Please enter the valid E-mail Address");
		theForm.email.focus();
		return (false);
	}
	/*check if email field is blank*/
	if (theForm.email.value == "")
	{
		alert("Please enter your Email Address");
		theForm.email.focus();
		return (false);
	}


	/*Validation for the first Degree1*/
	/*check if no drop down has been selected*/
	if (theForm.Degree1.selectedIndex < 0)
	{
		alert("Please select one of the Degree options");
		theForm.Degree1.focus();
		return (false);
	}
	/*check if the first drop down is selected, if so, invalid selection*/
	if (theForm.Degree1.selectedIndex == 0)
	{
		alert("The first Degree option is not a valid selection.");
		theForm.Degree1.focus();
		return (false);
	}

	/*Validation for the first Department*/
	/*check if no drop down has been selected*/
	if (theForm.Dept1.selectedIndex < 0)
	{
		alert("Please select one of the Department options.");
		theForm.Dept1.focus();
		return (false);
	}
	/*check if the first drop down is selected, if so, invalid selection*/
	if (theForm.Dept1.selectedIndex == 0)
	{
		alert("The first Department option is not a valid selection.");
		theForm.Dept1.focus();
		return (false);
	}


	/*validation for the first Batch field*/
	if (theForm.Batch1.selectedIndex < 0)
	{
		alert("Please select one of the Batch options.");
		theForm.Batch1.focus();
		return (false);
	}
	/*check if the first drop down is selected, if so, invalid selection*/
	if (theForm.Batch1.selectedIndex == 0)
	{
		alert("The first Batch option is not a valid selection.");
		theForm.Batch1.focus();
		return (false);
	}


	/*validation for the  first Hostel field*/
	if (theForm.Hostel1.selectedIndex < 0)
	{
		alert("Please select one of the Hostel options.");
		theForm.Hostel1.focus();
		return (false);
	}
	/*check if the first drop down is selected, if so, invalid selection*/
	if (theForm.Hostel1.selectedIndex == 0)
	{
		alert("The first Hostel option is not a valid selection.");
		theForm.Hostel1.focus();
		return (false);
	}

	/*Validation for the Occupation*/
	if (theForm.Occupation.selectedIndex < 0)
	{
		alert("Please select one of the Occupation options.");
		theForm.Occupation.focus();
		return (false);
	}
	/*check if the first drop down is selected, if so, invalid selection*/
	if (theForm.Occupation.selectedIndex == 0)
	{
		alert('The first Occupation option is not a valid selection.');
		theForm.Occupation.focus();
		return (false);
	}


	/*Validation for the Area of Interest*/
	var interest= theForm.Interest.value;
	if(empty(interest))
	{
		alert("You must enter the Area of Interest");
		theForm.Interest.focus();
		return (false);
	}
	
	/*Validations for the Office Address */
	var office= theForm.Off_addr.value;
	if(empty(office)){
		alert("You must enter the Office Address.");
		theForm.Off_addr.focus();
		return (false);
	}
		/* End of validtion for Office Address */
	
	/*Validations for the City */
	var city1= theForm.Off_City.value;
	if(empty(city1))
	{
		alert("You must enter the City.");
		theForm.Off_City.focus();
		return (false);
	}
	
	/* End of validtion for City */
	
	/*Validations for the State */
	var state1= theForm.Off_State.value;
	if(empty(state1))
	{
		alert("You must enter the State.");
		theForm.Off_State.focus();
		return (false);
	}
	
	/* End of validtion for State */
	
	/*Validations for the Country */
	var country1= theForm.Off_Country.value;
	if(empty(country1))
	{
		alert("You must enter the Country.");
		theForm.Off_Country.focus();
		return (false);
	}
	
	
	/* End of validtion for Country */
	
	/*Validations for the Pin */
	var pin1= theForm.Off_pincode.value;
	if(empty(pin1))
	{
		alert("You must enter the Pin.");
		theForm.Off_pincode.focus();
		return (false);
	}
	else if(isalpha(pin1))
	{
		alert("Please enter only Numeric in the Pin");
		theForm.Off_pincode.focus();
		return (false);
	}
	/* End of validtion for Pin */
	
	/*test if valid email address, must have @ and .*/
	var checkEmail1 = "@.";
	var checkStr1 = theForm.LoginEmailid.value;
	var EmailValid1 = false;
	var EmailAt1 = false;
	var EmailPeriod1 = false;
	for (i = 0;  i < checkStr1.length;  i++)
	{
		ch = checkStr1.charAt(i);
		for (j = 0;  j < checkEmail1.length;  j++)
		{
			if (ch == checkEmail1.charAt(j) && ch == "@")
			EmailAt1 = true;
			if (ch == checkEmail1.charAt(j) && ch == ".")
			EmailPeriod1 = true;
			if (EmailAt1 && EmailPeriod1)
			break;
			if (j == checkEmail1.length)
			break;
		}
		/*if both the @ and . were in the string*/
		if (EmailAt1 && EmailPeriod1)
		{
			EmailValid1 = true
			break;
		}
	}
	if (!EmailValid1)
	{
		alert("Please enter the valid E-mail Address");
		theForm.LoginEmailid.focus();
		return (false);
	}
	/*check if email field is blank*/
	if (theForm.LoginEmailid.value == "")
	{
		alert("Please enter your Email Address");
		theForm.LoginEmailid.focus();
		return (false);
	}
	/* End of validtion for Email */

	/*Validation for the Answer1 field*/
	if (theForm.Password.value == "")
	{
		alert("You must enter the Password");
		theForm.Password.focus();
		return (false);
	}
	/*require at least 4 characters be entered*/
	if (theForm.Password.value.length < 6)
	{
		alert("Please enter at least 6 characters in the Password");
		theForm.Password.focus();
		return (false);
	}

	if (theForm.confirmPassword.value == "")
	{
		alert("Please Enter the Password for Confirmation.");
		theForm.confirmPassword.focus();
		return (false);
	}
	
	var checkStr3 = theForm.Password.value;
	var checkStr4 = theForm.confirmPassword.value;
	
	
	if( checkStr3.length != checkStr4.length)
	{ 
		alert(" You Password Do Not Match11.....");
    	return (false);
    }
    for (i = 0;  i < checkStr3.length;  i++)
	{
		ch = checkStr3.charAt(i);
		ch1 = checkStr4.charAt(i)
        if (ch == ch1)
        {
            break;
        }
		else
		{
			alert(" You Password Do Not Match.....");
			return (false);
		}
	}
}







