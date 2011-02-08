<head>
	<meta name="layout" content="main" />
	<title>LMS List</title>
        <g:javascript src="jquery.js"/>
        <script type="text/javascript">

          function validateForm(thisform)
          {
             if($('#institute').val()=='')
              {
                alert("Please select a Institute.");
                return false;
              }
              if($('#year').val()=='')
              {
                alert("Please select a Year.");
                return false;
              }
            thisform.submit();
          }

        </script>
       </head>
<body>
	<div id="wrapper">
		<div id="head">
			<div id="logo_user_details">&nbsp;</div>
		      <g:if test="${session.ROLE == 'ROLE_ADMIN'}">
                        <g:menu/>
                        </g:if >
		</div>

	<div id="content" style="padding-top:80px;"> <!-- Start of content div -->
        <g:form action="redirectpage" name="institutes">

        <table align="center">
         <tr>
         <td><label><strong>Select a Institute:</strong></label></td>
         <td><g:select  from="${instList}" id="institute" name="institute" noSelection="['':'------------ Select ----------']" value="${siteId}"></g:select></td>
         </tr>
         <tr>
         <td><label><strong>Select a Year:</strong></label></td>
         <td><g:select  from="${2008..year}" id="year" name="year" noSelection="['':'--- Select ---']" value="${siteId}"></g:select></td>
         </tr>
         <tr>
         <td>&nbsp;</td>
         <td><input type="button" value="Go" onclick="return validateForm(document.institutes)"/></td>
         </tr>
         </table>
        </g:form>
        </div> <!-- End of content div -->
	</div>
<g:footer/>
</body>