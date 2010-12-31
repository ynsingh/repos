<head>
	<meta name="layout" content="main" />
	<title>Home Page</title>
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

	<div id="container">

        <div id="wrapper">
            <div id="content">

                <br />
                <div id="box">
          <h3 id="adduser">Student Wise Module Usage</h3>
         <g:if test="${flash.message}">
	             <div class="message">${flash.message}</div>
	             </g:if>


<table>
       <tbody>
         <tr>
                        <td>
                            <label><strong>Select Module:</strong></label>&nbsp;&nbsp;<g:select id="authorSelection" name="modname" from="${siteList}" value="" noSelection="[null:'-- Select --']" onchange="${remoteFunction(controller:'courseActivity', action:'studAct',update:'updateMe', params:'\'modname=\' + this.value' )}" />
                        </td>                       
                    </tr>
                 </tbody>
            </table>

            <br>
          
            <g:javascript library="prototype" />           
             <div class="list"  id="updateMe">
                <ofchart:resources/>
		<g:javascript library="prototype"/>
             </div>




                </div>
            </div>
           
            <g:sideMenu/>
         
      </div>
         <g:styleSwitcher/>
</div>

</body>
