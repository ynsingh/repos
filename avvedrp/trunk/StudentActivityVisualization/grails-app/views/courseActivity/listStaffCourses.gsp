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
	 if($('#course').val()=='')
	  {
		alert("Please select a Course.");
		return false;
	  }
	  /*
	 if($('#year').val()=='')
	  {
		alert("Please select a Year.");
		return false;
	  }
	  */
	thisform.submit();
  }
</script>
		
<!-- ##################################  Layout body starts here  ###########################################-->
	  <div id="wrapper">
		<div id="head">
			<div id="logo_user_details">&nbsp;</div>
		       <g:menu/>
		</div>		
	<div id="content" align="center"> 					
			<g:form action="redirectstaffpage" name="courses">
               <div id="stylized" class="myform"> <!-- Start of content div -->
         <br >
  		
			<table>					  
			<tr>
			<td><label><strong>Course:&nbsp;</strong></label></td>
			<td>
			<g:select keys="${courseList.crsId}"  from="${courseList.crsName}" name="course"  id="course" style="width:150px;"/>
			</td>
			</tr>	
			<tr><td>&nbsp;</td>
			<td><input type="button" value="Go"  class="formbutton" onclick="return validateForm(document.courses)"/></td>
			</tr>
			</table>
			</g:form>
			<br >
        </div> <!-- End of content div -->
  
		</div>
		
		<div style="clear: both;">&nbsp;</div>
		<br />
<!-- Middle area ends here -->		
 </div> <!-- End of content div -->

	</div>
<g:footer/>
<!-- ##################################  Layout body ends here  ###########################################-->