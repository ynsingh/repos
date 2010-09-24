
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
		                
		                element.value=result[j].value;
		                 //element.readOnly=true;
		                 break;
		                }
            	} 
           }
            
                
   }

