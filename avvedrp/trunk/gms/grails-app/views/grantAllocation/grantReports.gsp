<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Grant Expense</title>         
    </head>
        
    <body  onload="setValue()">
     <div class="wrapper">
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="grantAllocationTrackingReports" controller="grantAllocationTracking">Grant Allocation/Project Status Reports</g:link></span>
        	<span class="menuButton"><g:link class="list" action="list" controller="utilization">Utilization Certificates</g:link></span>
        	
        </div>
        <br>
         <g:form action="listReport" method="post" name="grantReport" >
         <div class="body">
         <div class="dialog">
             <table width="75%">
                        <tbody>
                        
                        <tr>
                                <td >
                               <table>
                            <tbody>
                        
                        <tr>
                                <td valign="top" >  
                                    <label for="project"> Project :</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'projects','errors')}">
                                    <g:select optionKey="id" optionValue="code" from="${projectInstanceList}" name="projects" onChange="setValue()" value="${grantAllocationInstance?.projects?.id}"></g:select>
                                      <input type="hidden" id="projectHidden" name="projectHidden" value=""/>
                                      <input type="hidden" id="party" name="party" value="${Party.find('from Party P where P.activeYesNo=\'Y\' and P.id in '+session.PartyID).id}"/>
                                </td>
                                
                                <td valign="top" >
                                    <label for="grantPeriod">Grant Period:</label>
                                                                   </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'grantPeriod','errors')}">
                                    <g:select optionKey="id" optionValue="name" from="${GrantPeriod.list()}" onChange="setValue()" name="grantPeriod" value="id" ></g:select>
                                      <input type="hidden" id="periodHidden" name="periodHidden" value="${fieldValue(bean:projectInstance, field:'id')}"/>
                                </td>
                                <td valign="top" ><label for="projectType">Project Type:</label></td>
                                <td valign="top" class="value ${hasErrors(bean:projectInstance,field:'projectType','errors')}">
                                    <g:select optionKey="type" optionValue="type" from="${ProjectType.list()}" onChange="setValue()" name="projectType" value="type" ></g:select>
                                      <input type="hidden" id="typeHidden" name="typeHidden" value="${fieldValue(bean:projectInstance, field:'id')}"/>
                                </td>
						    </tr> 
						    <tr>
						          <td class='name'>Report Date From:</td>
						          <td> 
						           <input type="hidden" name="reportDate" value="struct" /><select name="reportDate_day" id="reportDate_day"  onChange="setValue()">
									<option value="1"
									
									>1</option>
									<option value="2"
									>2</option>
									
									<option value="3"
									>3</option>
									<option value="4"
									>4</option>
									<option value="5"
									>5</option>
									<option value="6"
									>6</option>
									<option value="7"
									>7</option>
									<option value="8"
									>8</option>
									<option value="9"
									>9</option>
									<option value="10"
									>10</option>
									<option value="11"
									>11</option>
									
									<option value="12"
									>12</option>
									<option value="13"
									>13</option>
									<option value="14"
									>14</option>
									<option value="15"
									>15</option>
									<option value="16"
									>16</option>
									<option value="17"
									>17</option>
									<option value="18"
									>18</option>
									<option value="19"
									>19</option>
									<option value="20"
									>20</option>
									
									<option value="21"
									>21</option>
									<option value="22"
									>22</option>
									<option value="23"
									>23</option>
									<option value="24"
									>24</option>
									<option value="25"
									>25</option>
									<option value="26"
									>26</option>
									<option value="27"
									>27</option>
									<option value="28"
									>28</option>
									<option value="29"
									>29</option>
									
									<option value="30"
									>30</option>
									<option value="31"
									>31</option>
									</select>
									<select name="reportDate_month" id="reportDate_month"  onChange="setValue()">
									<option value="Jan">Jan</option>
									<option value="Feb">Feb</option>
									<option value="Mar">Mar</option>
									<option value="Apr">Apr</option>
									<option value="May">May</option>
									<option value="Jun">Jun</option>
									
									<option value="Jul">Jul</option>
									<option value="Aug">Aug</option>
									<option value="Sep">Sep</option>
									<option value="Oct">Oct</option>
									<option value="Nov">Nov</option>
									<option value="Dec">Dec</option>
									</select>
									<select name="reportDate_year" id="reportDate_year"  onChange="setValue()">
									
									<option value="2009"
									
									>2009</option>
									
									<option value="2010"
									>2010</option>
									<option value="2011"
									>2011</option>
									<option value="2012"
									>2012</option>
									<option value="2013"
									>2013</option>
									<option value="2014"
									>2014</option>
									<option value="2015"
									>2015</option>
									<option value="2016"
									>2016</option>
									<option value="2017"
									>2017</option>
									<option value="2018"
									>2018</option>
									
									<option value="2019"
									>2019</option>
									<option value="2020"
									>2020</option>
									<option value="2021"
									>2021</option>
									<option value="2022"
									>2022</option>
									<option value="2023"
									>2023</option>
									<option value="2024"
									>2024</option>
									<option value="2025"
									>2025</option>
									<option value="2026"
									>2026</option>
									<option value="2027"
									>2027</option>
									
									<option value="2028"
									>2028</option>
									<option value="2029"
									>2029</option>
									<option value="2030"
									>2030</option>
									<option value="2031"
									>2031</option>
									<option value="2032"
									>2032</option>
									<option value="2033"
									>2033</option>
									<option value="2034"
									>2034</option>
									<option value="2035"
									>2035</option>
									<option value="2036"
									>2036</option>
									
									<option value="2037"
									>2037</option>
									<option value="2038"
									>2038</option>
									<option value="2039"
									>2039</option>
									<option value="2040"
									>2040</option>
									<option value="2041"
									>2041</option>
									<option value="2042"
									>2042</option>
									<option value="2043"
									>2043</option>
									<option value="2044"
									>2044</option>
									<option value="2045"
									>2045</option>
									
									<option value="2046"
									>2046</option>
									<option value="2047"
									>2047</option>
									<option value="2048"
									>2048</option>
									<option value="2049"
									>2049</option>
									<option value="2050"
									>2050</option>
									
									</select>
									
									          </td>
   						<td class='name'>Report Date To:</td>
						          <td> 
						           <input type="hidden" name="reportDateTo" value="struct" /><select name="reportDateTo_day" id="reportDateTo_day"  onChange="setValue()">
									<option value="1"
									
									>1</option>
									<option value="2"
									>2</option>
									
									<option value="3"
									>3</option>
									<option value="4"
									>4</option>
									<option value="5"
									>5</option>
									<option value="6"
									>6</option>
									<option value="7"
									>7</option>
									<option value="8"
									>8</option>
									<option value="9"
									>9</option>
									<option value="10"
									>10</option>
									<option value="11"
									>11</option>
									
									<option value="12"
									>12</option>
									<option value="13"
									>13</option>
									<option value="14"
									>14</option>
									<option value="15"
									>15</option>
									<option value="16"
									>16</option>
									<option value="17"
									>17</option>
									<option value="18"
									>18</option>
									<option value="19"
									>19</option>
									<option value="20"
									>20</option>
									
									<option value="21"
									>21</option>
									<option value="22"
									>22</option>
									<option value="23"
									>23</option>
									<option value="24"
									>24</option>
									<option value="25"
									>25</option>
									<option value="26"
									>26</option>
									<option value="27"
									>27</option>
									<option value="28"
									>28</option>
									<option value="29"
									>29</option>
									
									<option value="30"
									>30</option>
									<option value="31"
									>31</option>
									</select>
									<select name="reportDateTo_month" id="reportDateTo_month"  onChange="setValue()">
									<option value="Jan">Jan</option>
									<option value="Feb">Feb</option>
									<option value="Mar">Mar</option>
									<option value="Apr">Apr</option>
									<option value="May">May</option>
									<option value="Jun">Jun</option>
									
									<option value="Jul">Jul</option>
									<option value="Aug">Aug</option>
									<option value="Sep">Sep</option>
									<option value="Oct">Oct</option>
									<option value="Nov">Nov</option>
									<option value="Dec">Dec</option>
									</select>
									<select name="reportDateTo_year" id="reportDateTo_year"  onChange="setValue()">
									
									<option value="2009"
									
									>2009</option>
									
									<option value="2010"
									>2010</option>
									<option value="2011"
									>2011</option>
									<option value="2012"
									>2012</option>
									<option value="2013"
									>2013</option>
									<option value="2014"
									>2014</option>
									<option value="2015"
									>2015</option>
									<option value="2016"
									>2016</option>
									<option value="2017"
									>2017</option>
									<option value="2018"
									>2018</option>
									
									<option value="2019"
									>2019</option>
									<option value="2020"
									>2020</option>
									<option value="2021"
									>2021</option>
									<option value="2022"
									>2022</option>
									<option value="2023"
									>2023</option>
									<option value="2024"
									>2024</option>
									<option value="2025"
									>2025</option>
									<option value="2026"
									>2026</option>
									<option value="2027"
									>2027</option>
									
									<option value="2028"
									>2028</option>
									<option value="2029"
									>2029</option>
									<option value="2030"
									>2030</option>
									<option value="2031"
									>2031</option>
									<option value="2032"
									>2032</option>
									<option value="2033"
									>2033</option>
									<option value="2034"
									>2034</option>
									<option value="2035"
									>2035</option>
									<option value="2036"
									>2036</option>
									
									<option value="2037"
									>2037</option>
									<option value="2038"
									>2038</option>
									<option value="2039"
									>2039</option>
									<option value="2040"
									>2040</option>
									<option value="2041"
									>2041</option>
									<option value="2042"
									>2042</option>
									<option value="2043"
									>2043</option>
									<option value="2044"
									>2044</option>
									<option value="2045"
									>2045</option>
									
									<option value="2046"
									>2046</option>
									<option value="2047"
									>2047</option>
									<option value="2048"
									>2048</option>
									<option value="2049"
									>2049</option>
									<option value="2050"
									>2050</option>
									
									</select>
									
									          </td>
                            </tr> 
                            
                            
                            
                            </tbody>   
                             </table>
                            
                             
                           
                                 </g:form>
                                 
                                </td>
                                
                                   
                            </tr>    
                                 
                                   
                    		<tr>
	                            <td valign="top" class="name"><g:jasperReport jasper="List_of_on_going_projects"  name="List of on going projects - Statement No:2" format="XLS,PDF,CSV,HTML"  >
					            <input type="hidden" name="id" value="${session.AppFormID}" />
					            <input type="hidden" name="partyID" value="" />
					            <input type="hidden" name="Path" value="${application.getRealPath("reports")}"/>
				  	         	</g:jasperReport>
					           	</td>
				           	</tr>
				           	<tr>
	                            <td valign="top" class="name"><g:jasperReport jasper="ResearchProjectGrantDailyAccounts"  name="Research Project Grant Daily Accounts-Statement No:3(a)" format="XLS,PDF,CSV,HTML"  >
					            <input type="hidden" name="id" value="${session.AppFormID}" />
					            <input type="hidden" name="reportDate" value="" />
					            <input type="hidden" name="projectID" value="" />
					            <input type="hidden" name="Path" value="${application.getRealPath("reports")}"/>
				  	         	</g:jasperReport>
					           	</td>
				           	</tr>
				          <tr>
							<td valign="top" class="name">
							   <g:jasperReport jasper="ResearchProjectGrantMonthwiseAccount" format="XLS,PDF,CSV,HTML" name="Research Project Grant Monthwise Account - Statement 4" >
							    <input type="hidden" name="id" value="${session.AppFormID}" />
							   	<input type="hidden" name="projectID" value="" />
							   	<input type="hidden" name="reportDate" value="" />
							  	 <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
								</g:jasperReport>
							</td>
							</tr>

				           	
					            <tr>
                                <td valign="top" class="name">
                                   <g:jasperReport jasper="ResearchProjectGrantAnnualStatement" format="XLS,PDF,CSV,HTML" name="Research Project Grant Annual Statement - Statement 5" >
					            <input type="hidden" name="id" value="${session.AppFormID}" />
					           <input type="hidden" name="projectID" value="" />
					           <input type="hidden" name="partyID" value="" />
					           <input type="hidden" name="reportDate" value="" />
					           <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
				  	         </g:jasperReport>
					           </td>
					           </tr>
					        <tr>
								<td valign="top" class="name">
									<g:jasperReport jasper="ResearchProjectGrantFinalStatement" format="XLS,PDF,CSV,HTML" name="Research Project Grant Final Statement - Statement 6" >
										<input type="hidden" name="id" value="${session.AppFormID}" />
										<input type="hidden" name="projectID" value="" />
										<input type="hidden" name="reportDate" value="" />
										<input type="hidden" name="reportDateTo" value="" />
										<input type="hidden" name="partyID" value="" />
										<input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
									</g:jasperReport>
								</td>
							</tr>
					           <tr>
	                            <td valign="top" class="name"><g:jasperReport jasper="expenditures"  name="On Going Research Project Grant(Central/State)- Statement 7" format="XLS,PDF,CSV,HTML"  >
					            <input type="hidden" name="id" value="${session.AppFormID}" />
					            <input type="hidden" name="reportDate" value="" />
					            <input type="hidden" name="partyID" value="" />
					            <input type="hidden" name="Path" value="${application.getRealPath("reports")}"/>
				  	         	</g:jasperReport>
					           	</td>
				           		</tr>
				           	  <tr>
                                <td valign="top" class="name">
                                   <g:jasperReport jasper="OnGoingClinicalTrials" format="XLS,PDF,CSV,HTML" name="On Going Clinical Trials - Statement 8" >
					            <input type="hidden" name="id" value="${session.AppFormID}" />
					           <input type="hidden" name="partyID" value="" />
					            <input type="hidden" name="projectType" value="" />
					             <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
				  	         </g:jasperReport>
					           </td>
					           
					           </tr>
					            <tr>
                                <td valign="top" class="name">
                                   <g:jasperReport jasper="GrantsAndContractsStatement9" format="XLS,PDF,CSV,HTML" name="Grants And Contracts Statement9(GOVT.STATE)" >
					            <input type="hidden" name="id" value="${session.AppFormID}" />
					           <input type="hidden" name="partyID" value="" />
					            <input type="hidden" name="projectType" value="" />
					             <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
				  	         </g:jasperReport>
					           </td>
					           
					           </tr>
					          <tr>
                                <td valign="top" class="name">
                                   <g:jasperReport jasper="GrantsAndContractsStatement9Abstract" format="XLS,PDF,CSV,HTML" name="Grants And Contracts Statement 9 Abstract(GOVT.CENTRAL)" >
					            <input type="hidden" name="id" value="${session.AppFormID}" />
					           <input type="hidden" name="partyID" value="" />
					            <input type="hidden" name="projectType" value="" />
					             <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
				  	         </g:jasperReport>
					           </td>
					           
					           </tr>   	           
					         <tr>
                                <td valign="top" class="name">
                                   <g:jasperReport jasper="GrantsAndContractsStatement10Abstract" format="XLS,PDF,CSV,HTML" name="Grants And Contracts Statement 10 Abstract(CLINICAL)" >
					            <input type="hidden" name="id" value="${session.AppFormID}" />
					           <input type="hidden" name="partyID" value="" />
					            <input type="hidden" name="projectType" value="" />
					             <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
				  	         </g:jasperReport>
					           </tr>
					           <tr>
                                <td valign="top" class="name">
                                   <g:jasperReport jasper="GrantsAndContractsStatement11ConsolidatedAbstract" format="XLS,PDF,CSV,HTML" name="Grants And Contracts Consolidated Abstract - Statement 11" >
					            <input type="hidden" name="id" value="${session.AppFormID}" />
					            <input type="hidden" name="partyID" value="" />
					             <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
				  	         </g:jasperReport>
					           </tr>
                                </table>  
                      </div>   </div> 
                      </div> 
    </body>
</html>