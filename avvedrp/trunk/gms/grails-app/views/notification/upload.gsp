<html>
    <head>
     
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
        <title>UploadNotification</title>         
    </head>      
  
    <style>
        .tablewrapperpopup
    { 
    padding: 0px;
    text-align:left;
    width: 500px; 
    }
    </style>
  <%--  <script>
        function refreshParent() {
      window.opener.location.href = window.opener.location.href;
    
      if (window.opener.progressWindow)
    		
     {
        window.opener.progressWindow.close()
      }
      top.close();
    }
    </script>--%>
        
        
        <body>
        <div class="tablewrapperpopup"> 
            
        <table  class="tablewrapperpopup" border="0" cellspacing="0" cellpadding="0" >
        <tr>
         <td scope="col"><div > 
    
    
  
        
      
            <h1> UploadNotification</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${notificationInstance}">
            <div class="errors">
                <g:renderErrors bean="${notificationInstance}" as="list" />
            </div>
            </g:hasErrors>
           <g:form controller="notification" method="post" action="savefile" enctype="multipart/form-data" >
	       
                <div class="dialog">
                 <table>
                        <tbody>    
                
                 <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="type">attachmentType:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:notificationInstance,field:'type','errors')}">
                                    <g:select optionKey="id" optionValue="type" id="type" from="${AttachmentType.list()}"  name="type.id" value="${notificationInstance?.type?.id}" noSelection="['null':'select']" ></g:select>
                                </td>
                            </tr>    
                        
                        <input type="file" name="file"/>
                        
		        <input type="submit"  value="upload" >
		         </div>
               </tbody>
               </table>
                        
               
                
            </g:form>
            </div>
            </td>
        
        </tr>
    
        </div>
        <tr>
     <td scope="row">
    </body>
</html>