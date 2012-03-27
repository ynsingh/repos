
 <g:form action="saveUser" name="editUserForm" method="POST" >		
				    <table border="0" width="100%" style="border:dotted">
				    	<tr class="textHead1"><td  ><h3 align="center"><u> Edit User</u></h3></td></tr>
				        <tr class="text">
				             <td class="text" align="left">User Name</td>
				             <td><label  class="text"  name="username" id="username" >${userMasterInstance.username }</label></td>
				        </tr>
				        <tr class="text">
				             <td class="text" align="left">Email id</td>
				             <td><label  class="text" name="emailId" id="emailId" >${userMasterInstance.emailId }</label></td>
				        </tr>
				       <!--<tr>
                             <td class="text" align="left">
                                  Password
                             </td>
                             <td class="text" align="left">
                                  <input type="password" name="paswd" id="paswd" value="${userMasterInstance.password }"/> <span style="color: maroon;font-weight: bold;">*</span>
                             </td>
                       </tr>  --> 
				        <tr class="text">
				             <td class="text" align="left">User Role</td>
				             <td>
								<g:select id="userRole" name='roleList.id' optionKey="id" optionValue="authority" from="${roleList}" value="${roleId }"  ></g:select> <span style="color: maroon;font-weight: bold;">*</span>
							</td>
				         </tr>
				          <tr>
                <td class="text" align="left">
                    University
                </td>
                <td class="text" align="left">
                      <g:select name='universityList.id' optionKey="id" optionValue="universityName" from="${universityList}"  value="${userMasterInstance.universityId }"></g:select> <span style="color: maroon;font-weight: bold;">*</span>
                </td>
            </tr>
            <tr>
                <td colspan="2">&nbsp;</td>
            </tr>        
            <tr>
                <td class="text" align="left">                    	
                   First Name
                </td>
                <td class="text" align="left">
                    <input type="text" name="firstName" id="firstName" value="${userDetailsInstance.firstName }"/>
                </td>
            </tr>
            <tr>
                <td class="text" align="left">
                    Last Name
                </td>
                <td class="text" align="left">
                    <input type="text" name="lastName" id="lastName" value="${userDetailsInstance.lastName }"/>
                </td>
            </tr>
            <tr>
                <td class="text" align="left">
                   Age Group
                </td>
                <td class="text" align="left">
                    <g:select name="age"   from="${['0-15','16-20','21-25','26-30','31-35','36-40','41-45','46-50','Above 50']}" value="${userDetailsInstance.age }" />
                </td>
            </tr>
            <tr>
                <td class="text" align="left">
                   Gender
                </td>
                <td class="text" style="color:black;" align="left">
                     
                    <input type="radio" id="gender" name="gender" value="M" checked="<g:if test="${userDetailsInstance.gender=='M'}">checked </g:if>">&nbsp;Male</input>&nbsp;&nbsp;
                    <input type="radio" id="gender" name="gender" value="F" checked="<g:if test="${userDetailsInstance.gender=='F'}">checked </g:if>">&nbsp;Female</input>
                </td>
            </tr>   
            <tr>
                <td class="text" align="left">
                    Phone
                </td>
                <td class="text" align="left">
                    <input type="text" name="phoneNumber" id="phoneNumber" value="${userDetailsInstance.phoneNumber }"/>
                </td>
            </tr>             
            <tr>
                <td class="text" align="left">   
                	Profession             
                </td>
                <td class="text" align="left">
                    <g:select name="profession"   from="${['Student','Academician','Others']}" value="${userDetailsInstance.profession }" />
              </td>    
            </tr>
            <tr>
                <td class="text" align="left">
                    College
                </td>
                <td class="text" align="left">
                    <input type="text" name="college" id="college" value="${userDetailsInstance.college }"/>
                </td>
            </tr>
            <tr>
                <td class="text" align="left">
                    Subject
                </td>
                <td class="text" align="left">
                    <input type="text" name="specialization" id="specialization" value="${userDetailsInstance.specialization }"/>
                </td>
            </tr>  
           
                         
            <tr>
                <td class="text" align="left">
                    Country
                </td>               
                <td class="text" align="left">
                    <g:select name="country"   from="${['India','Australia','Belgium','Canada','China','Finland','France','Germany','Ireland','Italy','Japan','Kuwait','Netherlands','New Zealand','Oman','Singapore','Sweden','Switzerland','United States','United Kingdom','Other']}" value="${userDetailsInstance.country }" />
                </td>                
            </tr>
            <tr>
                <td class="text" align="left">
                    State
                </td>
                <td class="text" align="left">
                    <input type="text" name="state" id="state" value="${userDetailsInstance.state }"/>
                </td>
            </tr>                                  
            
				         <tr class="text">
				            <td class="text" align="left">Status</td>
				            <td>
				            <g:if test="${userMasterInstance.userStatus =='A'}">
				                 <g:set var="status" value="Active" />
				            </g:if>
				            <g:elseif test="${userMasterInstance.userStatus =='D'}">
				                 <g:set var="status" value="Deactive" />
				            </g:elseif>
				            <g:select name="userStatus"   from="${['Active','Deactive']}" value="${status }" />
                            </td>
				          </tr>
				          <tr>
                <td colspan="2">&nbsp;</td>
            </tr>
				          <tr class="text">
				            <td colspan="2" align="center">
				        	      <input type="submit" value="Submit" id="edit_submit" name="edit_submit"  style="width:50px"/>
				        	      &nbsp;&nbsp;<input type="button" value="Cancel" id="edit_cancel" name="edit_cancel"  onclick="return closeEditUserForm();" style="width:50px"/>
				            </td>
				          </tr>
				  </table>	
				 <input type="hidden" name="userId" id="userId" value="${userMasterInstance.id }"/>
			     </g:form>