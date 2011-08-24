
function submitForm(){

   	for(var i=0;i< parent.right.frames['editframe'].document.forms[0].elements.length;i++)
            {
              var element= parent.right.frames['editframe'].document.forms[0].elements[i];
           		if(element.id==1)
           		{
                  if(element.value=='')
		             {
		             alert ("Please enter the "+element.name);
		             element.focus();
		                 //element.readOnly=true;
		              return false;
		             }
		        }
           }
	parent.right.frames['editframe'].document.forms[0].action="saveForm";

 
	parent.right.frames['editframe'].document.forms[0].submit();


}


function enableCastComm()
{
//alert("VALUE IS"+parent.right.frames['editframe'].document.forms[0].scstobc.value);
if((parent.right.frames['editframe'].document.forms[0].scstobc.value =="sc") || 
(parent.right.frames['editframe'].document.forms[0].scstobc.value =="st") ||
(parent.right.frames['editframe'].document.forms[0].scstobc.value =="obc"))
	{
		parent.right.frames['editframe'].document.forms[0].txtCaste.readOnly = false;
		parent.right.frames['editframe'].document.forms[0].community.readOnly = false;
	}
	else
	{
		parent.right.frames['editframe'].document.forms[0].txtCaste.value="";
		parent.right.frames['editframe'].document.forms[0].community.value="";
		parent.right.frames['editframe'].document.forms[0].txtCaste.readOnly = true;
		parent.right.frames['editframe'].document.forms[0].community.readOnly = true;
	
	}
}


 function getForm(){
 	parent.right.frames['editframe'].document.forms[0].action="getForm";
 	parent.right.frames['editframe'].document.forms[0].submit();
 }  




 function returnResult(e)
 {
           
         
         
              var result = eval("("+e.responseText+")"); // evaluate the JSON
            
             
             
           
            for(var i=0;i< parent.right.frames['editframe'].document.forms[0].elements.length;i++)
            {
              var element= parent.right.frames['editframe'].document.forms[0].elements[i];
           
                for(var j=0;j< result.length;j++)
                {
		                if(result[j].field==element.name)
		                {
		                
		              	//element.readOnly=true;
		                 if(element.type=='radio')
		                 {
		                	 var radioName=element.name;
			               	 var radioIns=parent.right.frames['editframe'].document.forms[0].elements[radioName];
			               	for(var k=0;k<radioIns.length;k++)
		                	 {
			              		if(radioIns[k].value==result[j].value)
		                	 	{
		                	 		radioIns[k].checked=true;
		                	 	}
			              		else if(radioIns[k].value==result[j].value)
			              		{
			              			radioIns[k].checked=false;
			              		}
			              		
		                	 	
		                	 }			               	             
		                 }
		                 else
		                 {
		                	 element.value=result[j].value;
		                 }
		                 break;
		                }
            	} 
           }
            
                
   }
   function returnFormResult(data)
 {
           
         
         
               // evaluate the JSON
            
           result = eval(data);
                  
          
            for(var i=0;i< parent.top.document.forms[0].elements.length;i++)
            {
              var element= parent.top.document.forms[0].elements[i];
          
                for(var j=0;j< result.length;j++)
                {
		               
                	if(result[j].field==element.name)
		                {
		               	 if(element.type=='radio')
		               	 {
		               				               		
		               		var radioName=element.name;
		               		var radioIns=document.forms[0].elements[radioName];
		               			if(result[j].value=='Yes')
		               			{
		               				radioIns[0].checked=true;
		               				radioIns[1].checked=false;
		               						               				
		               			}
		               			else if(result[j].value=='No')
		               			{
		               				radioIns[0].checked=false;
		               				radioIns[1].checked=true;
		               				
		               			}
		               	}
		               	 else
		               	 {
		               		element.value=result[j].value;
		               	 }
		               	  //element.readOnly=true;
		                 break;
		                }
            	} 
           }
           for(var i in CKEDITOR.instances)
				{
					var editor=CKEDITOR.instances[i]
															
					if(editor.getData()!='')
					{
						editor.setData('');
					}
									
						if(document.forms[0].elements[i].value != '')
						{
							
							editor.setData(document.forms[0].elements[i].value);
						}
					
					
				}
        
            
                
   }
   function pageNavigation(status,page,lastPage,id)
    {
    	var pageNo = lastPage+1; 	
    	if(status == 'null')
    	{
    		if(pageNo<id)
    		{
    	    	return false;
    		}
    		else
    		{
    	   		return true;
    		}
    	}
    	else
    	{
    		return false;
    	}
    }
    function pageValidation(field,page,id)
    {
    	field.style.backgroundColor='#a3c9de';
    	field.style.textDecoration = 'none';
    	return true;
    }
   
    function validateApplicationForm()
	{
			if( ( (document.getElementById("FirstName").value) == 'null') || ( (document.getElementById("FirstName").value) == '') )
	    	{
			    alert("Please enter the FirstName");
			    document.getElementById("FirstName").focus();
			    return false;
	    	}
		     var speclChars = "!@#$%^&*()+=-_[]\\\';,./{}|\":<>?0123456789";
			
			if(document.getElementById("FirstName").value != "")
			 {
		    	for (var i = 5; i < (document.getElementById("FirstName").value.length); i++) 
		    	{
		   	 		if (speclChars.indexOf(document.getElementById("FirstName").value.charAt(i)) != -1) 
		  			{
		  			alert ("Special characters and numbers are not allowed in First Name");
		  			document.getElementById("FirstName").focus();
		  			return false;
		  			}
		    	}
		    }
		    
		    
		    if( ( (document.getElementById("LastName").value) == 'null') || ( (document.getElementById("LastName").value) == '') )
		    {
			    alert("Please enter the LastName");
			    document.getElementById("LastName").focus();
			    return false;
		    }
		     var speclChars = "!@#$%^&*()+=-_[]\\\';,./{}|\":<>?0123456789";
			
			if(document.getElementById("LastName").value != "")
			 {
		    	for (var i = 5; i < (document.getElementById("LastName").value.length); i++) 
		    	{
		   	 		if (speclChars.indexOf(document.getElementById("LastName").value.charAt(i)) != -1) 
		  			{
		  			alert ("Special characters and numbers are not allowed in Last Name");
		  			document.getElementById("LastName").focus();
		  			return false;
		  			}
		    	}
		    }
		    
		    
		     if( ( (document.getElementById("Designation").value) == 'null') || ( (document.getElementById("Designation").value) == '') )
		    {
			    alert("Please enter the Designation");
			   document.getElementById("Designation").focus();
			    return false;
		    }
		     var speclChars = "!@#$%^&*()+=-_[]\\\';,./{}|\":<>?0123456789";
			
			if(document.getElementById("Designation").value != "")
			 {
		    	for (var i = 5; i < (document.getElementById("Designation").value.length); i++) 
		    	{
		   	 		if (speclChars.indexOf(document.getElementById("Designation").value.charAt(i)) != -1) 
		  			{
		  			alert ("Special characters and numbers are not allowed in Designation");
		  			document.getElementById("Designation").focus();
		  			return false;
		  			}
		    	}
		    }
		    
		    
		     if( ( (document.getElementById("Organisation").value) == 'null') || ( (document.getElementById("Organisation").value) == '') )
		    {
			    alert("Please enter the Organisation");
			    document.getElementById("Organisation").focus();
			    return false;
		    }
		     var speclChars = "!@#$%^&*()+=-_[]\\\';,./{}|\":<>?0123456789";
			
			if(document.getElementById("Organisation").value != "")
			 {
		    	for (var i = 5; i < (document.getElementById("Organisation").value.length); i++) 
		    	{
		   	 		if (speclChars.indexOf(document.getElementById("Organisation").value.charAt(i)) != -1) 
		  			{
		  			alert ("Special characters and numbers are not allowed in Organisation");
		  			document.getElementById("Organisation").focus();
		  			return false;
		  			}
		    	}
		    }
		    
		    
		     if( ( (document.getElementById("PostalAddress").value) == 'null') || ( (document.getElementById("PostalAddress").value) == '') )
		    {
			    alert("Please enter the PostalAddress");
			    document.getElementById("PostalAddress").focus();
			    return false;
		    }
		   
		    
		     if( ( (document.getElementById("City").value) == 'null') || ( (document.getElementById("City").value) == '') )
		    {
			    alert("Please enter the City");
			    document.getElementById("City").focus();
			    return false;
		    }
		     var speclChars = "!@#$%^&*()+=-_[]\\\';,./{}|\":<>?0123456789";
			
			if(document.getElementById("City").value != "")
			 {
		    	for (var i = 5; i < (document.getElementById("City").value.length); i++) 
		    	{
		   	 		if (speclChars.indexOf(document.getElementById("City").value.charAt(i)) != -1) 
		  			{
		  			alert ("Special characters and numbers are not allowed in City");
		  			document.getElementById("City").focus();
		  			return false;
		  			}
		    	}
		    }
		    
		    
		        if( ( (document.getElementById("State").value) == 'null') || ( (document.getElementById("State").value) == '') )
		    {
			    alert("Please enter the State");
			    document.getElementById("State").focus();
			    return false;
		    }
		 
		   if( (document.getElementById("State").value) == '-UNION  TERRITORIES-')
		    {
			    alert("Please enter the Proper State");
			    document.getElementById("State").focus();
			    return false;
		    }
		    
		    
		     if( ( (document.getElementById("ZipCode").value) == 'null') || ( (document.getElementById("ZipCode").value) == '') )
		    {
			    alert("Please enter the Zip Code");
			    document.getElementById("ZipCode").focus();
			    return false;
		    }
		   var str = document.getElementById('ZipCode').value;
		    var oneDecimal = false;
		    var oneChar = 0;
		    str = str.toString();
		
		    for (var i = 0; i < str.length; i++)
		     {
		
		        oneChar = str.charAt(i).charCodeAt(0);
		       // characters outside of 0 through 9 not OK
		
		        if (oneChar < 48 || oneChar > 57)
		         {
		
		            alert("Please enter numeric values for ZipCode (upto 6 digits)");
		            str.focus;
		            return false;
		         }
		      }   
		   if(str.length>6)  
		      {
		      
		            alert("Please enter numeric values for Zip Code(upto 6 digits)");
		            str.focus;
		            return false;
		      }
		    
		     if(str.length<6)  
		      {
		      
		            alert("Please enter numeric values for Zip Code(upto 6 digits)");
		            str.focus;
		            return false;
		      }
		    
		 		
		    
		      if( ( (document.getElementById("STD").value) == 'null') || ( (document.getElementById("STD").value) == '') )
		    {
			    alert("Please enter numeric values for STD (upto 4 digits) and Phone No (upto 7 digits)");
			    document.getElementById("STD").focus();
			    return false;
		    }
		     var str = document.getElementById('STD').value;
		    var oneDecimal = false;
		    var oneChar = 0;
		    str = str.toString();
		
		    for (var i = 0; i < str.length; i++)
		     {
		
		        oneChar = str.charAt(i).charCodeAt(0);
		       // characters outside of 0 through 9 not OK
		
		        if (oneChar < 48 || oneChar > 57)
		         {
		
		            alert("Please enter numeric values for STD (upto 4 digits) and Phone No (upto 7 digits)");
		            str.focus;
		            return false;
		         }
		      }   
		   if(str.length>4)  
		      {
		      
		            alert("Please enter numeric values for STD (upto 4 digits)");
		            str.focus;
		            return false;
		      }
		      
		       if(str.length<4)  
		      {
		      
		            alert("Please enter numeric values for STD (upto 4 digits)");
		            str.focus;
		            return false;
		      }
		    
		      if( ( (document.getElementById("Phone").value) == 'null') || ( (document.getElementById("Phone").value) == '') )
		    {
			    alert("Please enter numeric values for STD (upto 4 digits) and Phone No (upto 7 digits)");
			    document.getElementById("Phone").focus();
			    return false;
		    }
		     var str = document.getElementById('Phone').value;
		    var oneDecimal = false;
		    var oneChar = 0;
		    str = str.toString();
		
		    for (var i = 0; i < str.length; i++)
		     {
		
		        oneChar = str.charAt(i).charCodeAt(0);
		       // characters outside of 0 through 9 not OK
		
		        if (oneChar < 48 || oneChar > 57)
		         {
		
		            alert("Please enter numeric values for STD (upto 4 digits) and Phone No (upto 7 digits)");
		            document.getElementById('Phone').focus;
		            return false;
		         }
		      }   
		    if(str.length>7)  
		      {
		      
		            alert("Please enter numeric values for Phone No  (upto 7 digits)");
		            document.getElementById('Phone').focus;
		            return false;
		      }
		      
		       if(str.length<7)  
		      {
		      
		            alert("Please enter numeric values for Phone No  (upto 7 digits)");
		            document.getElementById('Phone').focus;
		            return false;
		      }
		    
		     if( ( (document.getElementById("FaxSTD").value) == 'null') || ( (document.getElementById("FaxSTD").value) == '') )
		    {
			    alert("Please enter numeric values for STD (upto 4 digits) and Fax No (upto 7 digits)");
			    document.getElementById("FaxSTD").focus();
			    return false;
		    }
		     var str = document.getElementById('FaxSTD').value;
		    var oneDecimal = false;
		    var oneChar = 0;
		    str = str.toString();
		
		    for (var i = 0; i < str.length; i++)
		     {
		
		        oneChar = str.charAt(i).charCodeAt(0);
		       // characters outside of 0 through 9 not OK
		
		        if (oneChar < 48 || oneChar > 57)
		         {
		
		            alert("Please enter numeric values for Fax STD (upto 4 digits) and Fax No (upto 7 digits)");
		           document.getElementById('FaxSTD').focus;
		            return false;
		         }
		      }   
		     if(str.length>4)  
		      {
		      
		            alert("Please enter numeric values for Fax STD (upto 4 digits)");
		            document.getElementById('FaxSTD').focus;
		            return false;
		      }
		      
		        if(str.length<4)  
		      {
		      
		            alert("Please enter numeric values for Fax STD (upto 4 digits)");
		            document.getElementById('FaxSTD').focus;
		            return false;
		      }
		      
		      
		       if( ( (document.getElementById("FaxPhone").value) == 'null') || ( (document.getElementById("FaxPhone").value) == '') )
		    {
			    alert("Please enter numeric values for STD (upto 4 digits) and Fax No (upto 7 digits)");
			    document.getElementById("FaxPhone").focus();
			    return false;
		    }
		     var str = document.getElementById('FaxPhone').value;
		    var oneDecimal = false;
		    var oneChar = 0;
		    str = str.toString();
		
		    for (var i = 0; i < str.length; i++)
		     {
		
		        oneChar = str.charAt(i).charCodeAt(0);
		       // characters outside of 0 through 9 not OK
		
		        if (oneChar < 48 || oneChar > 57)
		         {
		
		            alert("Please enter numeric values for STD (upto 4 digits) and Fax No (upto 7 digits)");
		            document.getElementById('FaxPhone').focus;
		            return false;
		         }
		      }   
		      if(str.length>7)  
		      {
		      
		            alert("Please enter numeric values for Fax No (upto 7 digits)");
		            document.getElementById('FaxPhone').focus;
		            return false;
		      }
		      
		       if(str.length<7)  
		      {
		      
		            alert("Please enter numeric values for Fax No (upto 7 digits)");
		            document.getElementById('FaxPhone').focus;
		            return false;
		      }
		      
		        if( ( (document.getElementById("Email").value) == 'null') || ( (document.getElementById("Email").value) == '') )
		    {
			    alert("Please enter valid E-mail ID");
			    document.getElementById("Email").focus();
			    return false;
		    }
		    var email = document.getElementById('Email');
		    
			var filter =/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			var userCheck=true;
			if(email.value != "")
			{
				if (!filter.test(email.value))
				{
					alert('Please provide a valid email address');
					email.focus;
					return false;
				}
				else
				{
				
					if( ( (document.getElementById("party.id").value) == 'null') || ( (document.getElementById("party.id").value) == '') )
					{
				jQuery.ajax({
		          url: '/gms/proposal/userNameCheck',
		          data: {email: email.value,proposalApplication: document.getElementById('proposalApplication.id').value},
		          dataType: "json",
		          async: false,
		          success: function(data){
		          var person = eval(data);
		          if(person[0].id != null)
		          {
		          	userCheck=false;
		          }
		          },
		          error: function(errorThrown) {
				}
				});
				if(!userCheck)
				{
					alert('A user with that email address already exists');
					return userCheck;
				}
				}
				}
			}
			
			
			
			 if( ( (document.getElementById("Mobile").value) == 'null') || ( (document.getElementById("Mobile").value) == '') )
		    {
			    alert("Please enter numeric values for Mobile (11 digits)");
			    document.getElementById("Mobile").focus();
			    return false;
		    }
		     var str = document.getElementById('Mobile').value;
		    var oneDecimal = false;
		    var oneChar = 0;
		    str = str.toString();
		
		    for (var i = 0; i < str.length; i++)
		     {
		
		        oneChar = str.charAt(i).charCodeAt(0);
		       // characters outside of 0 through 9 not OK
		
		        if (oneChar < 48 || oneChar > 57)
		         {
		
		            alert("Please enter numeric values for Mobile (upto 11 digits)");
		           document.getElementById('Mobile').focus;
		            return false;
		         }
		      } 
		      if(str.length>11)  
		      {
		      
		            alert("Please enter numeric values for Mobile (upto 11 digits)");
		            document.getElementById('Mobile').focus;
		            return false;
		      }
		      
		      if(str.length<11)  
		      {
		      
		            alert("Please enter numeric values for Mobile (upto 11 digits)");
		            document.getElementById('Mobile').focus;
		            return false;
		      }
		       if( ( (document.getElementById("ProjectCategory").value) == 'null') || ( (document.getElementById("ProjectCategory").value) == '') )
		    {
			    alert("Please enter Proposal Category");
			    document.getElementById("ProjectCategory").focus();
			    return false;
		    }
		    
		   
		    
		    return true;
	}
	function validateAppSummary()
	{
				var Obj=document.getElementsByTagName('input');
				for (var i = 0; i < Obj.length; i++)
				{
					if (Obj[i].type == "text") 
					{
						
						if((Obj[i].value=="null")||(Obj[i].value==""))
						{
							alert("Please Fill all the Fields");
							Obj.focus();
							return false;
						}
						
					}
				}
				
				 if( ( (document.getElementById("InHouse").value) == 'null') || ( (document.getElementById("InHouse").value) == '') )
		    {
			    alert("Please Fill all the Fields");
			    document.getElementById("InHouse").focus();
			    return false;
		    }
		    
		     if( ( (document.getElementById("OutSourcing").value) == 'null') || ( (document.getElementById("OutSourcing").value) == '') )
		    {
			    alert("Please Fill all the Fields");
			    document.getElementById("OutSourcing").focus();
			    return false;
		    }
				
				for(var i in CKEDITOR.instances)
						{
						  var editor=CKEDITOR.instances[i]
																	
							if(editor.getData() == "")
							{
							    alert("Please Fill all the Fields");
							    editor.focus();
							    return false;
							}
		                }
		return true;
	}
	
  function validateApplicationFormPartII()
  {
  	   if( ( (document.getElementById("TitleOfTheResearchProject").value) == 'null') || ( (document.getElementById("TitleOfTheResearchProject").value) == '') )
		    {
			    alert("Please enter Title of the Research Project");
			    document.getElementById("TitleOfTheResearchProject").focus();
			    return false;
		    }
  return true;
  }
	
	

	

