<meta name="layout" content="main" />
<g:javascript src="jquery.js"/>
<g:javascript src="ddaccordion.js"/>
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
		

<!-- ##################################  Layout body starts here  ###########################################-->
	<div id="wrapper">
		<div id="head">
			<div class="innnerBanner">
			<g:isLoggedIn>
			<div class="loginLink">
			<span>
			<font face="verdana" color:#01518e; font-weight:bold; text-decoration: none>			
			<b>${session.UserId}</b> (<a href="${resource(dir:'/logout')}" class="logout">Logout</a>)
			</span>
			</div>
			</g:isLoggedIn>
			</div>		    
		</div>
		
		<br /><br />
	<div id="content"> 	
<!-- Middle area starts here -->	
		<g:if test="${session.ROLE == 'ROLE_SUPERADMIN' || session.ROLE == 'ROLE_UNIVERSITY'}">
		<g:menu/>
		</g:if >
		<div style="padding-left:300px;">						
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
		</div>
		
		<div style="clear: both;">&nbsp;</div>
		<br />
<!-- Middle area ends here -->		
  </div> <!-- End of content div -->
</div>
<g:footer/>
<!-- ##################################  Layout body ends here  ###########################################-->