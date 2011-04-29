<html>
    <head>
        
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'general.css')}" />
        <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
       
    </head>
    <body>
    <div class="headerWrraper">
  

    <div class="headerTitle">
    <!-- <p>Logged in as  ${session.UserId}<a class="list" href="${resource(dir:'/logout')}">&nbsp;(Logout)</a>
    </p> -->
     </div>
    </div>
     <!--   <div id="spinner" class="spinner" style="display:none;">
            <img src="${createLinkTo(dir:'images',file:'spinner.gif')}" alt="Spinner" />
        </div>	

     
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