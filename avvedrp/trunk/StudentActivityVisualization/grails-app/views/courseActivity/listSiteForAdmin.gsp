

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Site List</title>
    </head>
    <body>
        
        
            		<div align ="center">
	 	<h2>LMS: ${session.LMS} </h2>
	 </div>
        <div align="center">
            <h1>Site Selector</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:form action="listGraph" method="post" >
            
            <div>
            <br><br>
         
            <table class="tableselector" align="center"   height="178" width="371" background="../bgSiteSelector.gif">
            <tr><td><br><br></td></tr>
                    <tr class="prop">
                        <td align="center" valign="middle">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        
                            <label><font color="#333333"><b>Site :</b></font></label>
                        </td>
                        <td >
            				&nbsp;&nbsp;
                        
            				<g:select optionKey="siteId" optionValue="siteName" from="${siteList}" id="siteId" name="siteId" noSelection="[null:'-Select-']" value="${siteId}" ></g:select>
            				<input type="hidden" name="siteName" value = "">
                        </td>
                        </tr>
                        <tr>
                        <td align="center" colspan="2" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        
                        	<input  name="listSite" type="submit" value="Go" onClick="return validateSite()"  />
                        	
                        	
                        	
                        </td>
                    </tr>
                    
                </table>
            </div>
            </g:form>
            </div>
            
        
		
    </body>
</html>
