<head>
	<meta name="layout" content="main" />
	<title>LMS List</title>	
	<style>
		.myform{
		margin:0 auto;
		width:375px;
		float:left;
		margin:20px;
		padding-left:10px;
		}
		
		/* ----------- stylized ----------- */
		#stylized{
		border:solid 2px #b7ddf2;
		background:#ebf4fb;
		}
		
		#stylized label{
		display:block;
		font-weight:bold;
		text-align:right;
		padding-top:4px;
		width:140px;
		float:left;
		}
			
		</style>
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
			
		      <g:if test="${session.ROLE == 'ROLE_SUPERADMIN' || session.ROLE == 'ROLE_UNIVERSITY'}">
                        <g:menu/>
                     
                        </g:if >
		</div>
		

	<div style="padding:80px 0px 0px 300px;"> <!-- Start of content div -->
          <g:form action="redirectpage" name="institutes">
                <div id="stylized" class="myform">	
					<br >
						<table>					  
						<tr>
						<td><label><strong>Institute&nbsp;</strong></label></td>
						<td>
						<g:select keys="${instList.instId}"  from="${instList.instName}" name="institute"  id="institute"/>
						</td>
						</tr>
						<tr><td><label><strong>Year&nbsp;</strong></label></td>
						<td><g:select  from="${2008..year}" id="year" name="year" value="${siteId}" ></g:select></td>
						</tr>
						<tr><td>&nbsp;</td>
						<td><input type="button" value="Go" onclick="return validateForm(document.institutes)"/></td>
						</tr>
						</table>
					<br >
				   </div>	 
              </g:form>
        </div> <!-- End of content div -->
	</div>
<g:footer/>
</body>