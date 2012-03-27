
<div id="container">

  <div id="header">
             <img  id="headerLeftImage" align ="left"   src="${resource(dir:'images',file:'header-left-content.jpg')} "  border="0"/>
         <img   align ="right" src="${resource(dir:'images',file:'header-right.jpg')}"/>
<!--<img  align ="right" src="${resource(dir:'images',file:'header-tile.jpg')}"/>-->

    
  </div>
  <%
  def ell_integrate_flag = grailsApplication.config.ell_integrate_flag;%>

  
  
    <div id="naviBar">
             <input id="naviTab" name="naviTab" type="hidden" value="" />
             <img  id="naviBarLeftImage" align ="left"     usemap="#Map1" border="0" />
         <img  usemap="#Map2" border="0" align ="right" src="${resource(dir:'images',file:'naviRight.jpg')}"/>
     <map name="Map1" id="Map1">
        <area shape="rect" coords="40,5,108,38"  href="javascript:homeLocation()"> </area>
        <area shape="rect" coords="119,5,190,44" href="#"> </area>
        <area shape="rect" coords="197,6,244,35" href="#"> </area>
        <area shape="rect" coords="256,4,325,41" href="#"> </area>
        <area shape="rect" coords="333,4,398,44" href="#"> </area>
	<area shape="rect" coords="406,4,489,42" href="#"> </area>
        <!-- <area shape="rect" coords="406,4,489,42" href="javascript:logoutLocation()"> </area> -->
	
    </map>

     <map name="Map2" id="Map2">
        
        <area shape="rect" coords="10,2,110,30" href="#"> </area>
        

    </map>
  </div>
  <div id="sub-header">
      <img  align ="left"    style="width:57px;height:105px;" src="${resource(dir:'images',file:'subHead-Left-Img.jpg')}" />
       <img  align ="right"   style="width:61px;height:105px;" src="${resource(dir:'images',file:'subHead-right-Img.jpg')}" />
       <%
       def hostname=request.getScheme() + "://" + request.getServerName() +":"+ request.getServerPort() + request.getContextPath()
	  def user_sessionname = '';
	if(ell_integrate_flag ==1)
	{
	  user_sessionname1 = session.getAttribute("username");
	  user_sessionname = user_sessionname1.bytes.encodeBase64().toString();
          //println ("hi session  " + user_sessionname);
	}

       def homelink=hostname+"/home/index?pg=main";
       String param_pg =request.getParameter("pg");
       Integer url_param_pg=Integer.parseInt( param_pg );
      String param_topic =request.getParameter("topic"); 
      Integer url_param_topic=Integer.parseInt( param_topic );
      String  param_sub1 =request.getParameter("sub1"); 
      Integer url_param_sub1=Integer.parseInt( param_sub1 );
      String param_sub2 =request.getParameter("sub2"); 
      Integer url_param_sub2=Integer.parseInt( param_sub2 );
      String titleData="";
		   def subjectInstance=aell.AvlSubjectMaster.find("from aell.AvlSubjectMaster where id='"+url_param_pg+"'  and subjectStatus='A'");
		   if(subjectInstance)
		   {
		   def topicInstance=aell.AvlTopicMaster.find("from aell.AvlTopicMaster where id='"+url_param_topic+"' and topicStatus='A'");
		   titleData="<div style=\"color:#666;\">You are here-><a style=\"text-decoration:none;color:#666;\" href=\""+homelink+"\">Home</a>->"+subjectInstance.subjectName+"</div>";
		  
		   if(topicInstance)
		   {
		   def subTopicInstance=aell.AvlSubTopicMaster.find("from aell.AvlSubTopicMaster where sub_topic_id='"+url_param_sub1+"' and subTopicStatus='A'");
		   titleData="<div style=\"color:#666;\">You are here-><a style=\"text-decoration:none;color:#666;\" href=\""+homelink+"\">Home</a>->"+subjectInstance.subjectName+"->"+topicInstance.topicName+"</div>";
		   if(subTopicInstance)
		   {
		   def expInstance=aell.AvlExperimentMaster.find("from aell.AvlExperimentMaster where experiment_id='"+url_param_sub2+"' and experimentStatus='A'");
		   if(expInstance)
		   {
			   titleData  ="<div style=\"color:#666;\">You are here-><a style=\"text-decoration:none;color:#666;\" href=\""+homelink+"\">Home</a>->"+subjectInstance.subjectName+"->"+topicInstance.topicName+"->"+expInstance.experimentName+"</div>";
		  
		   }
		   }
		   }
		   }
		   %>
		  
     <div id="breadcum"><%=titleData%></div>
   
       <%
       if(uid){
       def userinstnce=aell.AvlUserMaster.find("from aell.AvlUserMaster where id='"+uid+"'");
       %>
      <table align="right" border="0">
      <tr><td style="font-family: Verdana, Geneva, sans-serif;
	font-size: 11px;
	color:black;
	text-align: justify;
	padding: 5px;">
	
	
	You are logged in as <%=userinstnce.username%>  ||<a  style="color:green;text-decoration:none;" href="#"> help </a>||
	<% if (ell_integrate_flag==0)
	{%>
	 <g:link style="color:green;text-decoration:none;" controller="logout" target="_parent">Logout</g:link>
	 <%}else if(ell_integrate_flag==1){
	 %>
	 <a style="color:green;text-decoration:none;" href="${hostname}/home/logout?pg=main" target="_self"> Logout</a>
	  <%}%></td></tr>
      </table>
      <%}%>
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
               <img  align ="left" src="${resource(dir:'images',file:'footerLeft.jpg')}" />
          <img  align ="right" src="${resource(dir:'images',file:'footerRight.jpg')}"/>
			
				 <div style="font-size:11px; padding-top:16px;color:#FFF;" align="center";>Developed by Amrita University under ERP, NME ICT, MHRD </div>
			</div>

             	</div>
	      </div>
		</div>
	</div>
</div>
 <script type="text/javascript">
    var rootUrl=getRootUrl();

    var url_param_uid =getUrlPara('uid');
var url_param_sesid =getUrlPara('sesid');
    var rootLength= rootUrl.length;
     var mainFolderloc= document.location.href.substring(rootLength,document.location.href.length);
   mainFolderloc=mainFolderloc.substring(0,mainFolderloc.indexOf("/"))
  // alert (mainFolderloc);
  if (mainFolderloc == 'home') {
	  if('${ell_integrate_flag}'==0)
		  {
		    document.getElementById("naviBarLeftImage").src="${resource(dir:'images',file:'naviLeftHome.jpg')} ";
			  
		  }
	  else
		  {
		    document.getElementById("naviBarLeftImage").src="${resource(dir:'images',file:'naviLeftHome_new.jpg')} ";
			  
		  }
  }else if(mainFolderloc == 'project'){
    document.getElementById("naviBarLeftImage").src="${resource(dir:'images',file:'naviLeftProject.jpg')} ";

  }else if(mainFolderloc == 'vision'){
    document.getElementById("naviBarLeftImage").src="${resource(dir:'images',file:'naviLeftVision.jpg')} ";

  }else if(mainFolderloc == 'people'){
    document.getElementById("naviBarLeftImage").src="${resource(dir:'images',file:'naviLeftPeople.jpg')} ";

  }else if(mainFolderloc == 'FAQ'){
    document.getElementById("naviBarLeftImage").src="${resource(dir:'images',file:'naviLeftFAQ.jpg')} ";

  }else if(mainFolderloc == 'contactUs'){
    document.getElementById("naviBarLeftImage").src="${resource(dir:'images',file:'naviLeftContactUs.jpg')} ";

  }else if(mainFolderloc == 'logout'){
    document.getElementById("naviBarLeftImage").src="${resource(dir:'images',file:'naviLeftContactUs.jpg')} ";
  }

 var url_param =getUrlPara('pg');
 if(url_param=='admin')
 {
     document.getElementById("naviBarLeftImage").src="${resource(dir:'images',file:'naviLeftHomeAdmin.jpg')} ";
 
 }
 var url_param_role =getUrlPara('roleId');
 //alert(url_param);
  if (url_param == 'main' || url_param == 'project' ||url_param == 'vision' ||url_param == 'people' ||url_param == 'FAQ' || url_param == 'contactUs' || url_param == 'logout') {
                document.getElementById("section-navigation").style.display = 'none';
     document.getElementById("content").style.backgroundImage='none';

            } else{
              document.getElementById("section-navigation").style.display = '';
            document.getElementById("content").style.backgroundImage='';
  }
  function logoutLocation(){
    window.location.href =rootUrl+'home/logout?pg=main';
   }
	
 function homeLocation(){
window.location.href =rootUrl+'home/index?pg=main';
  
 }
function  projectLocation(){
  window.location.href =rootUrl+'project/project.gsp?pg=project';

}
 function  visionLocation(){
  window.location.href =rootUrl+'vision/vision.gsp?pg=vision';

}
   function  peopleLocation(){
    window.location.href =rootUrl+'people/people.gsp?pg=people';

}
   function  FAQLocation(){
    window.location.href =rootUrl+'FAQ/FAQ.gsp?pg=FAQ';

}
   function  contactUsLocation(){
   window.location.href =rootUrl+'contactUs/contactUs.gsp?pg=contactUs';

}

function  changePasswordLocation(){ 
window.location.href =rootUrl+'ellUserMaster/changePassword'

}
 </script>