<head>
	<meta name="layout" content="main" />
	<title>LMS List</title>
         <script language="JavaScript" type="text/javascript">
//--------------- LOCALIZEABLE GLOBALS ---------------
var d=new Date();
var monthname=new Array("January","February","March","April","May","June","July","August","September","October","November","December");
//Ensure correct for language. English is "January 1, 2004"
var TODAY = monthname[d.getMonth()] + " " + d.getDate() + ", " + d.getFullYear();
//---------------   END LOCALIZEABLE   ---------------
</script>


<script type="text/javascript" src="../jquery-1.3.1.js"></script>
<script type="text/javascript" src="../jquery.corner.js"></script>

<script type="text/javascript">

/*
    $(function(){

         $('div.inner').wrap('<div class="outer"></div>');
	$('div.inner').corner("round 8px").parent().css('padding', '4px').corner("round 10px")
    });
    */


          function getSiteName()
        {
        var selected_index = document.getElementById("siteId").selectedIndex;

      	document.getElementById("courseName").value = document.getElementById("siteId").value;

         }

        function Redirect()
        {

			 if(document.getElementById("siteId").value=="null")
        	{
        	alert("Please Select a Course");
        	return false;
        	}
          var selected_index = document.getElementById("siteId").selectedIndex;
          window.location="../courseActivity/listSite?id="+ document.getElementById("siteId").value+"&siteName="+document.getElementById("siteId").options[selected_index].text;
        }

         function Redirect1()
        {

         if(document.getElementById("siteId").value=="null")
        	{
        	alert("Please Select a Course");
        	return false;
        	}

                var selected_index = document.getElementById("siteId").selectedIndex;
          window.location="../courseActivity/listSiteEvent?id="+ document.getElementById("siteId").value+"&siteName="+document.getElementById("siteId").options[selected_index].text;
        }

       function Redirect2()
        {

         if(document.getElementById("siteId").value=="null")
        	{
        	alert("Please Select a Course");
        	return false;
        	}
         var selected_index = document.getElementById("siteId").selectedIndex;
          window.location="../courseActivity/listUser?id="+ document.getElementById("siteId").value+"&siteName="+document.getElementById("siteId").options[selected_index].text;
        }

       function Redirect3()
        {

         if(document.getElementById("siteId").value=="null")
        	{
        	alert("Please Select a Course");
        	return false;
        	}
          var selected_index = document.getElementById("siteId").selectedIndex;
          window.location="../analytics.jsp?query=PerStudentSummaryTotal&siteName="+document.getElementById("siteId").options[selected_index].text;
        }


</script>
       </head>
<body>
	<div id="wrapper">
		<div id="head">
			<div id="logo_user_details">&nbsp;</div>

                        <g:menu/>

		</div>

	<div id="content"> <!-- Start of content div -->

									<div align ="center">
				<h2>Course: ${session.siteName}</h2>
				</div>
				<h3>User Activity Summary</h3>
				<g:if test="${flash.message}">
				<div class="message">${flash.message}</div>
				</g:if>

				<g:formRemote name="userform" on404="alert('not found!')" update="updateChart" action="userusage" url="${[action:'userusage']}">
				<table >
				<tbody>
				<tr>
				<td class="name">
				<label><strong>User:</strong></label>&nbsp;&nbsp;
				<g:select id="uname" name="uname" from="${studentList}" value="" noSelection="['':'Please Select...'] onchange="${remoteFunction(controller:'courseActivity',action:'userusage',update:'updateChart', params:'\'uname=\' + this.value' )}"/>
				</td>
				<td>

				</tr>
				</tbody>
				</table>
				</g:formRemote>
				<br>


				<div class="list"  id="updateChart">
				<ofchart:resources/>
				<g:javascript library="prototype"/>
				<center><ofchart:chart name="demo-chart" url="${createLink(action:'USER_ALLCOMP_LINE')}" width="600" height="400"/></center>
				</div>




         </div> <!-- End of content div -->


	</div>
<g:footer/>
</body>