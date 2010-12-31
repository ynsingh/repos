<head>
	<meta name="layout" content="main" />
	<title>LMS List</title>
       </head>

<body>

	<div id="container">

        <div id="wrapper">
            <div id="content">

                <br />
                <div id="box">
          <h3 id="adduser">LMS Selector</h3>
        <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:form action="listSiteForLoginUser" method="post" >
<table>
       <tbody>
         <tr>
                       <td>
                            <label><strong>LMS :</strong></label>&nbsp;&nbsp;
							<g:select id="siteId" name="siteId" from="${lns}" noSelection="[null:'-Select-']" value="" onChange="return getSiteName()" ></g:select>
            				<input type="hidden" id="siteName" name="siteName" value=""></input>
&nbsp;&nbsp;
<input  name="listSite" type="submit" value="Go"  onClick="return validate()" />
                        </td>  
						<td>
                           
                        </td>                      
                    </tr>
                 </tbody>
            </table>
     </g:form>




                </div>
            </div>
           
            <g:sideMenu/>
         
      </div>
         <g:styleSwitcher/>
</div>

</body>