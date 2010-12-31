<html>
    <head>
        
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="application" />	
        <script>
          function getSiteName()
        {
        	document.getElementById("siteName").value = document.getElementById("siteId").value;	
        }
        
          function getUNName()
        {
        	document.getElementById("UID").value = document.getElementById("UI").value;	
        }
        
        function validate()
        {
           
        	if(document.getElementById("siteId").value=="null")
        	{
        	alert("Please Select a LMS");
        	return false;
        	}
        }
        
          function validateSite()
        {
           
        	if(document.getElementById("siteId").value=="null")
        	{
        	alert("Please Select a Site");
        	return false;
        	}
        }



      //   var StyleFile = "theme" + document.cookie.charAt(6) + ".css";
      //   document.writeln('<link rel="stylesheet" type="text/css" href="../css/' + StyleFile + '">');
        </script>
      <!--[if IE]>
      <link rel="stylesheet" type="text/css" href="css/ie-sucks.css" />
      <![endif]-->
        			
    </head>
    <body>
    <div class="headerWrraper">
  

    <div class="headerTitle">
    <p>Logged in as  ${session.UserId}<a class="list" href="${resource(dir:'/logout')}">&nbsp;(Logout)</a>
    </p>
     </div>
    </div>
       <div id="spinner" class="spinner" style="display:none;">
            <img src="${createLinkTo(dir:'images',file:'spinner.gif')}" alt="Spinner" />
        </div>	

      <!--
     	  <div class="nav" >
     	   <g:if test="${session.ROLE == 'ROLE_STAFF'}">
            <span class="menuButton"><a class="home" href="${resource(dir:'/courseActivity/listGraph.gsp')}">Home</a></span>
            </g:if >
             <g:if test="${session.ROLE == 'ROLE_ADMIN'}">
            <span class="menuButton"><a class="home" href="${resource(dir:'/courseActivity/listGraphAdmin')}">Home</a></span>
            </g:if >
            <span class="menuButton"><a class="list" href="${resource(dir:'/courseActivity/listLMS.gsp')}">LMS</a></span>
        </div> -->
        <g:layoutBody />		
    </body>	
</html>