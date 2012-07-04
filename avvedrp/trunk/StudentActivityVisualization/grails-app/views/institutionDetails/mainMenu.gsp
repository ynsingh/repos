<meta name="layout" content="main" />
<div id="wrapper">
		<div id="head">
			     <div id="logo_user_details">&nbsp;</div>
			     <g:render template="/menu" />
				 <script type="text/javascript">
						function showHide(n) 
						{
								if(n==0) 
								{
										document.Form.institutionDetails.hide();
								}
								else 
								{
										document.Form.institutionDetails.show();
								}
						}
				</script>

				<script type="text/javascript">
						function OnSubmitForm()
						{
								if(document.Form.Institution[0].checked == true)
										{
										document.Form.action ="create";
										}
								else
										if(document.Form.Institution[1].checked == true)
										{
										document.Form.action ="update";
										}
						return true;
						}
				</script>
        </div>
		<div id="content">
				<g:form name="Form" method="post" style="height:150%;">
						<center>
								<div class="dialog">
								<br /><br /><h1>ALL INDIA SURVEY ON HIGHER EDUCATION</h1>
								<h3>DATA CAPTURE FORMAT - II</h3>
								<h4>COLLEGES / INSTITUTIONS AFFILIATED/ RECOGNISED BY THE UNIVERSITY</h4><br /><br /><br /><br /><br /><br />
										<fieldset style="width:50%; border-color:#4682B4;">
										<legend>Please Select</legend>
												<table >
														<tbody>
														 		<input type="hidden" name="InstId" value="${session.InstId}" />
																<tr style="background-color: transparent;"><td colspan="10">&nbsp;</td></tr>
																<tr class="prop">
																		<input name="Institution" type="radio" id="NEW" value="NEW" onclick="showHide(0)"/>Create New Institution&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<input name="Institution" type="radio" id="OLD" value="OLD" checked="checked" onclick="showHide(1)" />Update Existing Institution<br />  
																</tr>
																<tr class="prop">
																		<td valign="top"  class="value ">
																				<g:select from= "${institutionDetailsInstanceList}" id="institutionDetails" optionKey="id" name="institutionDetails"   noSelection="['null':'-Select your Institution-']" value="${institutionDetailsInstance?.id}"></g:select>
																		</td>
																</tr>
														</tbody> 
												</table>
										<div>
												<input class="pushbutton"  type="submit" value="Proceed" onClick="return OnSubmitForm();" />
										</div>   
										</fieldset>
								</div>
						</center>
				</g:form>
		</div>
</div>
<g:render template="/footer" />
