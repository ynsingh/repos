<meta name="layout" content="main" />	
<!-- ##################################  Layout body starts here  ###########################################-->
<g:javascript src="jquery.js"/>

	<style>
		.myform{
		margin:0 auto;
		width:375px;
		float:middle;
		margin:100px;
		padding-left:0px;
		}
		
		/* ----------- stylized ----------- */
		#stylized{
		border:solid 2px #b7ddf2;
		background:#ebf4fb;
		}
		
		#stylized label{
		display:block;
		font-weight:bold;
		text-align:left;
		padding-top:1px;
		width:60px;
		float:middle;
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

	<div id="wrapper">
		<div id="head">
			<div id="logo_user_details">&nbsp;</div>
		             <g:menu/>
        		</div>

	<div id="content" align="center"> <!-- Start of content div -->
	<div align="center ">
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
        </div> <!-- End of content div -->
	</div>
<g:footer/>
<!-- ##################################  Layout body ends here  ###########################################-->