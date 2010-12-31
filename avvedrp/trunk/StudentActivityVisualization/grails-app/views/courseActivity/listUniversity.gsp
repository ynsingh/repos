

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>LMS List</title>
       	
    </head>
    <body>
        
        <div align="center">
            <h1>LMS Selector</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:form action="listLMS" method="post" >
            
            <div>
            <br><br>
            <table class="tableselector" align="center"   height="178" width="371" background="../bgSiteSelector.gif">
            <tr><td><br><br></td></tr>
                    <tr class="prop">
                        <td align="center" valign="middle">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        
                            <label><font color="#333333"><b>University :</b></font></label>
                        </td>
                        <td >
            				&nbsp;&nbsp;
                        
            				<g:select id="siteId" name="siteId" from="${['Amtia Viswa Vidya Peetham -Coimbatore','Amtia Viswa Vidya Peetham -Amritapuri','Amtia Viswa Vidya Peetham -Banglore']}" noSelection="[null:'-Select-']" value="" onChange="return getSiteName()" ></g:select>
            				<input type="hidden" id="siteName" name="siteName" value=""></input>
                        </td>
                        </tr>
                        <tr>
                        <td align="center" colspan="2" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        
                        	<input  name="ListLMS" type="submit" value="Go"  onClick="return validate()" />
                        	
                        	
                        	
                        </td>
                    </tr>
                    
                </table>
            </div>
            </g:form>
            </div>
            
        
		
    </body>
</html>
