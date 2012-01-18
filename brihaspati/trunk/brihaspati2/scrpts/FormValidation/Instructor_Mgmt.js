/**
 * This java script is used for validate the registration form of instructor in specific course in the Institue or organization.
 * This java script return alert message and highlighted the field if the input value in the form is missing or wrong.
 * @see template, UserMgmt_Instructor.vm, StudentManagement.vm
 * @author <a href="mailto:mail2sunil00@gmail.com">Sunil Yadav</a>
 * @Created Date: 18-01-2012
 */
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
                frm.actionName.value=field.name;
                frm.submit();
        }
		

	/**
 	 * This java script is used for open the popup window for help document. 
	 * @see template InstUserRegistrationManagement.vm, UserMgmt_Instructor.vm.
	 */

	
        function popupWin(url,popupName)
        {
                Win1=window.open(url,popupName,"resizable=0,scrollbars=1,height=400,width=400");
        }
        function popupWin(urlName,popupName)
        {
                window.open(urlName,popupName,"toolbar=yes,scrollbars=yes");
        }


