


<div id="container">
  <div id="header">
             <img  id="headerLeftImage" align ="left"   src="${resource(dir:'images',file:'header-left-content.jpg')} "  border="0"/>
             <img   align ="right" src="${resource(dir:'images',file:'header-right.jpg')}"/>
  </div>
  <div id="naviBar">
             <input id="naviTab" name="naviTab" type="hidden" value="" />
             <img  id="naviBarLeftImage" align ="left"     usemap="#Map1" border="0" />
             <img  usemap="#Map2" border="0" align ="right" src="${resource(dir:'images',file:'naviRight.jpg')}"/>
             <map name="Map1" id="Map1">
                  <area shape="rect" coords="40,5,108,38"  href="javascript:homeLocation()"> </area>
                  <area shape="rect" coords="119,5,190,44" href="#"> </area>
             </map>
             <map name="Map2" id="Map2">
             <area shape="rect" coords="10,2,110,30" href="#"> </area>
             </map>
  </div>
  <div id="sub-header">
            <img  align ="left"    style="width:57px;height:105px;" src="${resource(dir:'images',file:'subHead-Left-Img.jpg')}" />
            <img  align ="right"   style="width:61px;height:105px;" src="${resource(dir:'images',file:'subHead-right-Img.jpg')}" />
	        <g:set var="breadcumContentUser">
			    You are here-&gt;<a style="text-decoration:none;color:#666;" href=${hostname}/home/index?pg=main>Home</a>-&gt;
	        </g:set>
	        <g:if test="${request['javax.servlet.forward.servlet_path'] == '/home/content'}">
			<g:set var="breadcum" value="${breadcumContentUser+''+breadcum}"/>
		    </g:if>	  
            <div id="breadcum">${breadcum}</div>
            <g:if test="${uname != null}">
              <table align="right" border="0">
                <tr>
                       <td style="font-family: Verdana, Geneva, sans-serif;
	                       font-size: 11px;	color:black;text-align: justify;padding: 5px;">
	                       You are logged in as ${uname}  ||<a  style="color:green;text-decoration:none;" href="#"> help </a>||	                       	
	                        <g:link style="color:green;text-decoration:none;" controller="logout" target="_parent">Logout</g:link>
	                   </td>
	            </tr>
	          </table>
            </g:if>
          <g:render template="/common/contentHeader" />
   </div>
   <div id="content-container1">
   <div id="content-container2">
   <div id="content-container3">
   <div id="content-container4">
   <div id="insideContent">
   <div id="section-navigation">
           <g:render template="/common/sideMenu" />
   </div>
   <div id="content">
	   <span>
         <g:layoutBody />
	   </span>
    </div>
    </div>
    <div id="footer">
        <g:render template="/common/footer" />
	</div>
    </div>
	</div>
	</div>
	</div>
    </div>
  <script type="text/javascript">
    var rootUrl=getRootUrl();
    var rootLength= rootUrl.length;
    var mainFolderloc= document.location.href.substring(rootLength,document.location.href.length);
    mainFolderloc=mainFolderloc.substring(0,mainFolderloc.indexOf("/"))
    if (mainFolderloc == 'home') {
		    document.getElementById("naviBarLeftImage").src="${resource(dir:'images',file:'naviLeftHome.jpg')} ";
      }
    var url_param =getUrlPara('pg');
  function homeLocation(){
    window.location.href =rootUrl+'home/index?pg=main'
  }
 </script>