<head>
	<meta name="layout" content="main" />
	<title>Site Selector</title>
        <script type="text/javascript" src="../jquery-1.3.1.js"></script>
        <script>
        function validatelistGraph(thisform)
        {        	
               // alert("asa");
                if(document.getElementById("siteName").value=="null")
        	{
        	alert("Please Select a Course");
        	return false;
        	}
                thisform.submit();
        }
        </script>
       </head>

<body>

	<div id="container">

        <div id="wrapper">
            <div id="content">

                <br />
                <div id="box">
          <h3 id="adduser">Course Selector</h3>
               <g:if test="${flash.message}">
               <div class="message">${flash.message}</div>
               </g:if>
               <g:form action="listGraph" method="post" name="graph">
                        <table>
                        <tbody>
                        <tr>
                        <td width="250">
                        <label><strong>Course:</strong></label>&nbsp;&nbsp;
                        <g:select optionKey="siteName" optionValue="siteName" from="${siteList}" id="siteName" name="siteName" noSelection="['null':'-Select-']" value="${siteId}" ></g:select>
                        &nbsp;&nbsp; <input  name="listSite" type="submit" value="Go" onClick="return validatelistGraph(document.graph)"  style="cursor: pointer;"" />
                        </td>                        
                        </tr>
                        </tbody>
                        </table>
              </g:form>
            <br>

            <g:javascript library="prototype" />
             <div class="list"  id="updateMe">
                <ofchart:resources/>
		<g:javascript library="prototype"/>
             </div>

                </div>
            </div>           
            <g:sideMenu/>           
      </div>
         <g:styleSwitcher/>
</div>

</body>
