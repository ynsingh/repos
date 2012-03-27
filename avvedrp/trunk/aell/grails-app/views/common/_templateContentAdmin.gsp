<div id="container">

  <div id="header">
	<img  id="headerLeftImage" align ="left"   src="${resource(dir:'images',file:'header-left-content.jpg')} "  border="0"/>
	<img   align ="right" src="${resource(dir:'images',file:'header-right.jpg')}"/>
  </div>
  
  <div id="naviBar">
	<input id="naviTab" name="naviTab" type="hidden" value="" />
    <img  id="naviBarLeftImage" align ="left"  src="${resource(dir:'images',file:'naviLeftHome.jpg')}"   usemap="#Map1" border="0" />
	<img  usemap="#Map2" border="0" align ="right" src="${resource(dir:'images',file:'naviRight.jpg')}"/>
	<map name="Map1" id="Map1">
	       <area shape="rect" coords="40,5,108,38"  href="javascript:homeLocation()"> </area>
	       <area shape="rect" coords="119,5,190,44" href="#"> </area>
	       <area shape="rect" coords="197,6,244,35" href="#"> </area>
	       <area shape="rect" coords="256,4,325,41" href="#"> </area>
	       <area shape="rect" coords="333,4,398,44" href="#"> </area>
	       <area shape="rect" coords="406,4,489,42" href="#"> </area>
			
	</map>
     <map name="Map2" id="Map2">
        <area shape="rect" coords="10,2,110,30" href="#"> </area>
    </map>
  </div>

  	<div id="sub-header">
		<img  align ="left"    style="width:57px;height:105px;" src="${resource(dir:'images',file:'subHead-Left-Img.jpg')}" />
		<img  align ="right"   style="width:61px;height:105px;" src="${resource(dir:'images',file:'subHead-right-Img.jpg')}" />
		<g:if test="${controllerName == 'users'||controllerName == 'role'}">
		    <g:if test="${actionName == 'changePassword'}">
		         <g:set var="breadcumContentHome">
			         You are here-&gt;<a style="text-decoration:none;color:#666;" href="${hostname}/aell/home/index?pg=main">Home</a>-&gt;${grailsApplication.config.account}
		         </g:set>
		    </g:if>
            <g:else>
                 <g:set var="breadcumContentHome">
			         You are here-&gt;<a style="text-decoration:none;color:#666;" href="${hostname}/aell/home/index?pg=main">Home</a>-&gt;${grailsApplication.config.admin_dashboard_portal}
		         </g:set>
		    </g:else>
		</g:if>
		<%--Added--%>
		<g:elseif test="${controllerName == 'report'}">
				<g:set var="breadcumContentHome">
			         You are here-&gt;<a style="text-decoration:none;color:#666;" href="${hostname}/aell/home/index?pg=main">Home</a>-&gt;${grailsApplication.config.report}
		         </g:set>
		</g:elseif>
        <g:else>
            <g:set var="breadcumContentHome">
			      You are here-&gt;<a style="text-decoration:none;color:#666;" href="${hostname}/aell/home/index?pg=main">Home</a>-&gt;${grailsApplication.config.admindashboard_title}
		    </g:set>  
		</g:else>
		<g:if test="${request['javax.servlet.forward.servlet_path'] == '/contentHome/index'}">
			<g:set var="breadcum" value="${breadcumContentHome+'-&gt;'}${grailsApplication.config.module}"/>
		</g:if>
		<g:elseif test="${request['javax.servlet.forward.servlet_path'] == '/topic/index'}">
			<g:set var="breadcum" value="${breadcumContentHome+'-&gt;'}${grailsApplication.config.topic}"/>
		</g:elseif>
		<g:elseif test="${request['javax.servlet.forward.servlet_path'] == '/experiment/index'}">
			<g:set var="breadcum" value="${breadcumContentHome+'-&gt;'}${grailsApplication.config.sub_topic}"/>
		</g:elseif>
		<g:elseif test="${request['javax.servlet.forward.servlet_path'] == '/editExperiment/index'}">
			<g:set var="breadcum" value="${breadcumContentHome+'-&gt;'}${grailsApplication.config.edit_contents}"/>
		</g:elseif>
		<g:elseif test="${request['javax.servlet.forward.servlet_path'] == '/users/index'}">
			<g:set var="breadcum" value="${breadcumContentHome+'-&gt;'}${grailsApplication.config.user_management}"/>
		</g:elseif>
		<g:elseif test="${request['javax.servlet.forward.servlet_path'] == '/users/adminUserRegistration'}">
			<g:set var="breadcum" value="${breadcumContentHome+'-&gt;'}${grailsApplication.config.user_registration}"/>
		</g:elseif>
		<g:elseif test="${request['javax.servlet.forward.servlet_path'] == '/users/managePrivilege'}">
			<g:set var="breadcum" value="${breadcumContentHome+'-&gt;'}${grailsApplication.config.manage_privileges}"/>
		</g:elseif>
		<g:elseif test="${request['javax.servlet.forward.servlet_path'] == '/role/index'}">
			<g:set var="breadcum" value="${breadcumContentHome+'-&gt;'}${grailsApplication.config.role_management}"/>
		</g:elseif>
		<g:elseif test="${request['javax.servlet.forward.servlet_path'] == '/users/changePassword'}">
			<g:set var="breadcum" value="${breadcumContentHome+'-&gt;'}${grailsApplication.config.change_password}"/>
		</g:elseif>
		<%--Added--%>
		<g:elseif test="${request['javax.servlet.forward.servlet_path'] == '/report/userStatus'}">
			<g:set var="breadcum" value="${breadcumContentHome+'-&gt;'}${grailsApplication.config.user_status}"/>
		</g:elseif>
		<g:elseif test="${request['javax.servlet.forward.servlet_path'] == '/report/loginStatus'}">
			<g:set var="breadcum" value="${breadcumContentHome+'-&gt;'}${grailsApplication.config.current_login_details}"/>
		</g:elseif>
		<g:elseif test="${request['javax.servlet.forward.servlet_path'] == '/report/tabaccessStatus'}">
			<g:set var="breadcum" value="${breadcumContentHome+'-&gt;'}${grailsApplication.config.user_accessed_tabs}"/>
		</g:elseif>
		<g:elseif test="${request['javax.servlet.forward.servlet_path'] == '/report/useraccessStatus'}">
			<g:set var="breadcum" value="${breadcumContentHome+'-&gt;'}${grailsApplication.config.user_access_details}"/>
		</g:elseif>
		<div id="breadcum"><div style="color:#666;">${breadcum}</div></div>	 
		<table align="right" border="0">
      <tr><td style="font-family: Verdana, Geneva, sans-serif;
	font-size: 11px;
	color:black;
	text-align: justify;
	padding: 5px;">
	<g:if test="${session.user.username}">
	You are logged in as ${session.user.username}
	||<a  style="color:green;text-decoration:none;" href="#"> help </a>||
	<g:link style="color:green;text-decoration:none;" controller="logout" target="_parent">Logout</g:link>
	</g:if>
	</td></tr>
    </table> 
		<g:render template="/common/adminContentHeader" />
    </div>
    
    
   <div id="content-container1">
      <div id="content-container2">
          <div id="content-container3">
           <div id="content-container4admin">
              
            <div id="insideContent">
             <div id="section-navigation-admin">
                  
                  <g:render template="/common/contentSideMenu" />

			</div>
           	<div id="contentAdmin">

			<span>
              <g:layoutBody />
			</span>
			
            </div>
            					<img  align ="right"   style="width:61px;height:105px;" src="${resource(dir:'images',file:'side-bar-rightt.jpg')}" />
            
              </div>
           			<div id="footer">
               <img  align ="left" src="${resource(dir:'images',file:'footerLeft.jpg')}" />
          <img  align ="right" src="${resource(dir:'images',file:'footerRight.jpg')}"/>
			
				 <div style="font-size:11px; padding-top:16px;color:#FFF;" align="center";>${grailsApplication.config.copy_right1}</div>
			</div>

             	</div>
	      </div>
		</div>
	</div>
</div>
	
<script type="text/javascript">
var rootUrl=getRootUrl();
function homeLocation(){
 window.location.href =rootUrl+'home/index?pg=main';
 }
 </script>