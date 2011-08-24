<meta name="layout" content="main" />	
<!-- ##################################  Layout body starts here  ###########################################-->
<g:javascript src="jquery.js"/>
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

<!-- ##################################  Layout body starts here  ###########################################-->
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
<!-- Middle area starts here -->	

 <div align="center"><h3>${institute} - ${year}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;LMS - ${lms_used}</h3></div>
 <br />   <br />
<div class="inner" >
<table width="738" style="padding-left:150px;">
  <tr>
    <td width="260"><img src="${resource(dir:'images/links',file:'courseAnalyiticTitle.gif')}" width="260" height="102" /></td>
    <td width="37">&nbsp;</td>
    <td width="186"><g:link action="showSummary"><img src="${resource(dir:'images/links',file:'summaryCourseAnalyitic.jpg')}" border="0" /></g:link></td>
    <td width="113"><g:link action="showVisitDetails"><img src="${resource(dir:'images/links',file:'visitdetails.jpg')}" border="0" /></g:link></td>
    <td width="118"><g:link url="../analytics.jsp?query=master"><img src="${resource(dir:'images/links',file:'adhocTool.jpg')}" border="0" /></g:link></td>
  </tr>
  <tr>
    <td height="62" colspan="5" style="padding:50px 0px 0px 300px">	<h2>Course Wise Analysis </h2></td>
  </tr>
  <tr>
    <td height="38" colspan="5" style="padding:20px 0px 0px 350px">
	<label><strong>Course:&nbsp;&nbsp;&nbsp;&nbsp; </strong></label>					
	<g:select keys="${courseList.crsId}"  from="${courseList.crsName}" name="course"  id="course" noSelection="['':'Select']"/>	</td>
  </tr>
  <tr>
    <td colspan="2"><img src="${resource(dir:'images/links',file:'studentPerformanceTitle.gif')}" width="260" height="102" /></td>
    <td>
	<g:form action="showVisualDetails" name="visualpage">
	<img src="${resource(dir:'images/links',file:'visualDetails.jpg')}" border="0" onclick="Redirect1(document.visualpage)" style="cursor: pointer;"/>
    <input type="hidden" id="hidCourse1" name="hidCourse1" value=""></input>
    </g:form>	</td>
    <td>
	<g:form action="showTimeUtilization" name="utilization">
	<img src="${resource(dir:'images/links',file:'timeUtilization.jpg')}" border="0" onclick="Redirect2(document.utilization)"  style="cursor: pointer;"/>
	<input type="hidden" id="hidCourse2" name="hidCourse2" value=""></input>
	</g:form>    </td>
    <td>
	<g:form action="showStudSummary" name="student_summary">
	<img src="${resource(dir:'images/links',file:'summary.jpg')}" border="0" onclick="Redirect3(document.student_summary)"  style="cursor: pointer;"/>
	<input type="hidden" id="hidCourse3" name="hidCourse3" value=""></input>
	</g:form>	</td>
  </tr>
</table>
</div>

<br />   <br />
 </div> <!-- End of content div -->

	</div>
<g:footer/>
<!-- ##################################  Layout body ends here  ###########################################-->