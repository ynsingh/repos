<head>
	<meta name="layout" content="main" />
	<title>LMS List</title>
<script type="text/javascript" src="../jquery-1.3.1.js"></script>
<script type="text/javascript" src="../jquery.corner.js"></script>
<script>
   function Redirect1(thisform)
        {
           if($('#course').val()=='')
             {
               alert("Please select a course");
               return false;
             }
           var course_val=$.trim($('#course').val());         
           $('#hidCourse1').val(course_val);
           thisform.submit();		
        }

        function Redirect2(thisform)
        {
           if($('#course').val()=='')
             {
               alert("Please select a course");
               return false;
             }
           var course_val=$.trim($('#course').val());		  
           $('#hidCourse2').val(course_val);
           thisform.submit();
        }

         function Redirect3(thisform)
        {
           if($('#course').val()=='')
             {
               alert("Please select a course");
               return false;
             }
           var course_val=$.trim($('#course').val());
           $('#hidCourse3').val(course_val);
           thisform.submit();
        }


        function Redirect4()
        {

         if(document.getElementById("course").value=="null")
        	{
        	alert("Please Select a Course");
        	return false;
        	}
         var selected_index = document.getElementById("course").selectedIndex;
          window.location="../courseActivity/listUser?id="+ document.getElementById("course").value+"&siteName="+document.getElementById("course").options[selected_index].text;
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

	<div id="content"> <!-- Start of content div -->
                                       <div align="center"><h3>${institute} - ${year}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;LMS - ${lms_used}</h3></div>
                                        <br />   <br />
					<div class="inner" >
					<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">

					<tr>
					<td height="117"><img src="../courseAnalyiticTitle.gif" width="260" height="102" /></td>
					<td><g:link action="showSummary"><img src="../summaryCourseAnalyitic.jpg" border="0" /></g:link></td>
					<td><g:link action="showVisitDetails"><img src="../visitdetails.jpg" border="0" /></g:link></td>					
					<td><g:link url="../analytics.jsp?query=master"><img src="../adhocTool.jpg" border="0" /></g:link></td>
					</tr>
					</table>
					</div>


                                        <br />
					<div style="padding-left:450px;">


					<h2>Course Wise Analysis </h2>
					<br>
					<label><strong>Course:&nbsp;&nbsp;&nbsp;&nbsp; </strong></label>					
					<g:select keys="${courseList.crsId}"  from="${courseList.crsName}" name="course"  id="course" noSelection="['':'Select']"/>
					</div>


					<div class="inner">
					<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">

					<td width="307"   height="117">
                                          <img src="../studentPerformanceTitle.gif" width="260" height="102" />
                                        </td>

					<td width="169">
                                        <g:form action="showVisualDetails" name="visualpage">
					<img src="../visualDetails.jpg" border="0" onclick="Redirect1(document.visualpage)" style="cursor: pointer;"/>
                                        <input type="hidden" id="hidCourse1" name="hidCourse1" value=""></input>
                                        </g:form>
                                        </td>

					<td width="164">
                                            <g:form action="showTimeUtilization" name="utilization">
                                            <img src="../timeUtilization.jpg" border="0" onclick="Redirect2(document.utilization)"  style="cursor: pointer;"/>
                                            <input type="hidden" id="hidCourse2" name="hidCourse2" value=""></input>
                                            </g:form>
                                        </td>

                              
					<td width="161">
                                           <g:form action="showStudSummary" name="student_summary">
                                          <img src="../summary.jpg" border="0" onclick="Redirect3(document.student_summary)"  style="cursor: pointer;"/></td>
                                           <input type="hidden" id="hidCourse3" name="hidCourse3" value=""></input>
                                            </g:form>
					</tr>
                                       
					</table>
					</div>

        <br />   <br />

         </div> <!-- End of content div -->


	</div>
<g:footer/>
</body>