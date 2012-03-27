
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>English Language Lab</title> 
<link rel="shortcut icon" type="image/x-icon" href="${hostname}/images/favicon.ico">
<meta name="layout" content="main" />
<script type="text/javascript" src="${createLinkTo(dir:'js',file:'csspopup.js')}"></script>
<script type="text/javascript">
  window.onload = function() {
     doSomething();
     var mydate = new Date();
     mydate.setTime(mydate.getTime() - 1);
     document.cookie= "username=; expires=" + mydate.toGMTString();
     popup('popUpDiv');
     if(document.getElementById("popUpDiv")!=null)
       setTimeout("document.getElementById('popUpDiv').style.display='none'",2000)}
  function redirect()
  {
	location.href ="http://aell.amritalearning.com/php/";
  } 
  
</script>

<style type="text/css">

#popUpDiv {
position:absolute;
	z-index:10;  
	 width:xxpx;
  height:xpx;
  text-align:justify;
  color:#000;
  font: 11px Georgia, "Times New Roman", Times, serif, Arial, Helvetica, sans-serif;
   background-color:#FFC;
    border: 1px solid #000 ;
 display:none;
 -moz-border-radius: 7px;
 border-radius: 9px;
padding-bottom:6px;
padding-right:6px;
padding-left:6px;
}
</style>
</head>

<body>
<table width="1027" border="0" cellpadding="0" cellspacing="0">
	<tr>
	         <td>
	                <img  align ="left" style="height:105px;margin-left:0%;" src="${hostname}/images/header-left.jpg"/>
			        <img style="height:105px;width:18.5%;margin-right:-480px;"  src="${hostname}/images/header-tile.jpg"  alt="">
			        <img src="${hostname}/images/header-right.jpg" width="432"  align ="right" style="height:105px;"/>
	         </td>
	</tr>
	<tr>
	          <td height="20px" style="background:url(${hostname}/images/top_sec.jpg)">
	               <ul id="menuTop">
	                   <li><a href="${hostname}/home/index" target="_self">Home</a></li>
	                   <g:if test="${rolename != 'Administrator'&&session.login_flag=='login'}">
	                       <li><g:link controller="users" action="editUserProfile" params="[user: 1,id:uid]" target="_parent">Edit Profile</g:link></li>
	                    </g:if>
	                   <li><a href="#" target="_self">Contact us</a></li>
	                 
		               <g:if test="${uid == null&&ell_integrate_flag!=1}">
		               	<li><a href="javascript: authenticate('${hostname}');" target="_self">Log In</a></li>
		               </g:if> 
			
		           </ul>           
	       <div class="searchbox">								
		   		<form  id="cse-search-box">
		       		 <div>
							<input type="text" name="q" size="25" />
							<input type="submit" name="sa" value="Search" />
				     </div>
		        </form>
		   </div>
		</td>
    </tr>
</table>
<!--  
<table border="0" cellpadding="0" cellspacing="0" width="1024" bgcolor="#FFFFFF">
<tr valign="top"> 
<td align="right">
<p class="featuredTitle">Welcome, you are logged in as <span class="utype"> ${uname} </span></p></td>
<td align="right"> <g:if test="${uid != null}">
		               <g:link controller="logout" target="_parent" class="featuredTitle">Logout</g:link>
		               </g:if>  </td>
</tr>
</table> 
-->
<table border="0" cellpadding="0" cellspacing="0" width="1024" bgcolor="#FFFFFF" >

<tr valign="top">
         <td rowspan="8" style="background:url(${hostname}/images/left_tile.gif)">
         </td>
		 <td rowspan="8" style="background:url(${hostname}/images/left_tile.gif)">
		        <img src="${hostname}/images/mainpage_blue_slice1_05.jpg" width="40" height="515" alt=""> </img>
		 </td>
		 <td  valign="top" colspan="3" rowspan="3" width="271" height="332" style="background:url(${hostname}/images/mainpage_blue_slice1_06.jpg)  no-repeat;background-size: 271px 368px;">
                <g:if test="${flash.message}">
                   <div id="blanket" style="display:none;"></div>
                  <div id="popUpDiv" style="display:none;" ><img src="${hostname}/images/tick1.gif" style="background-color: blue;" title="Success" height="20" width="20">${flash.message}</div>
                </g:if>
                <g:if test="${flash.error}"> <br/><br/>
                   <div class="error" id="error"><img src="${hostname}/images/wrong1.gif" title="Failure" height="20" width="20">${flash.error}</div>
                </g:if>  
                <div class="menuText" align="left" style="padding-top:35px; padding-left:20px; word-wrap: break-word; ">Featured Language Lab</div>
			    <div align="left" style="padding-top:5px; padding-left:30px; word-wrap: break-word; width:220px">				                
                <a href="#">
                <div style="padding-top:5px;"><span class="featuredTitle">Preparing a Resume</span></span><br />
                <img style="padding-top:5px;" src="${hostname}/images/resume.bmp" width="210px" /> 
                <p class="text-yellowBox"> A Curriculum Vitae(CV) or a resume is an outline of a person's educational and professional history, prepared for job applications that show an employer a person's capability for the position being sought.</p> 
                <img align="right"  src="${hostname}/images/read-more.jpg" width="61" height="14" alt="read" />
                </div>
                </a>           
         <!-- visit dashboard display after login (start) -->
          <g:if test="${uid != null}">
                <br/><br/><br/><br/>
                <div align="left">
                <p class="featuredTitle">Welcome <span class="name"> </span>, you are logged in as <span class="utype">
                ${uname} 
                
                      </span></p><br/><g:if test="${rolename == 'Administrator'}"><p >
                      <input type="button" value="Visit Admin Dashboard" style="cursor:pointer;background-image:url(${hostname}/images/enter_dash.gif); height:37px; width:140px; border:none; background-color:Transparent; color:#FFF;" onClick="javascript:adminHome('${hostname}','${uid}','${sesid}');" />
                      <br></br>
                      </g:if>
                      <br>
                      <g:link controller="logout" target="_parent"><b>Logout</b></g:link>
                      <g:if test="${rolename != 'Administrator'&&ell_integrate_flag!=1}">
                         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<g:link controller="users" action="changePassword" params="[user: 1]" target="_parent"><b>Change Password</b></g:link></p><br>
                       </g:if>         
                     </p><br>    
      	       </div>
	      </g:if>
	     </div>    
	   </td>
	   <td colspan="2">
			<img src="${hostname}/images/mainpage_blue_slice1_07.jpg" width="672" height="29" alt="">
	   </td>
	   <td rowspan="8" style="background:url(${hostname}/images/right_sidebar_bottom.jpg)">
			<img src="${hostname}/images/mainpage_blue_slice1_08.jpg" width="44" height="515" alt="">
	   </td>
		
    </tr>
	<tr>
		<td>
			<img src="${hostname}/images/main-sidebar_01.jpg" width="652" height="272" alt="">
		</td>
		<td>
			<img src="${hostname}/images/mainpage_blue_slice1_10.jpg" width="20" height="272" alt="">
		</td>
	 </tr>
	 <tr>
		<td valign="top" colspan="2" rowspan="6" width="672" height="214" style="background-image:url(${hostname}/images/mainpage_blue_slice1_11.jpg)">
              <div style="padding-top:10px; max-width:655px">
                  <b class="title-box-round">
                  <b class="title-box-round1"><b></b></b>
                  <b class="title-box-round2"><b></b></b>
                  <b class="title-box-round3"></b>
                  <b class="title-box-round4"></b>
                  <b class="title-box-round5"></b></b>
                  <div class="title-box-roundfg">
                     <div class="title-main-page">&nbsp;English Language Lab </div>
                  </div>
               </div>
               <table border="0" cellspacing="2" cellpadding="0" align="left">
                  <tr>
                       <td>
                         <!-- this is the table of content -->
                         <g:each in="${subjectList}" status="i" var="SubjectInstance"> 
                                 <table id='tblSubject' align="left" width="100%">
                                       <tr>
                                           <td width='1%'>
                                                  <img style="padding-right:10px;" src="${hostname}/uploads/Image/subject_${SubjectInstance.id}.jpg" id="myImg" onerror="javascript:loadOther(this);" />
                                            </td>
                                            <br/>
                                           <td>
                                                  <g:link action="content" controller="home" params="[subjectId:SubjectInstance.id]">${fieldValue(bean: SubjectInstance, field: "subjectName")}</g:link>
                                                  <br/> 
                                                  <span class="subjectDescription">
                                                      <p style="padding-right:10px;padding-top:2px;">${SubjectInstance.subjectDescription?.encodeAsHTML()}</p>
                
                                                  </span>
                                            </td>
                                         </tr>
                                    </table>      
				           </g:each>
		                 </td>               
                     </tr>
                 </table>
	     </td>
	</tr>
	<tr>
		<td colspan="5">
			<img src="${hostname}/images/mainpage_blue_slice1_12.jpg" width="271" height="20" alt="">
		</td>
	 </tr>
	 <tr>
		 <td rowspan="2"><img src="${hostname}/images/mainpage_blue_slice1_13.jpg" width="17" height="70" alt=""></td>
		 <td><img src="${hostname}/images/mainpage_blue_slice1_14.jpg" width="217" height="54" alt=""></td>
		 <td rowspan="4"><img src="${hostname}/images/mainpage_blue_slice1_15.jpg" width="37" height="163" alt=""></td>
	 </tr>
	 <tr>
		  <td colspan="2"><img src="${hostname}/images/mainpage_blue_slice1_16.jpg" width="217" height="16" alt=""></td>
	 </tr>
	 <tr>
		<td rowspan="2"><img src="${hostname}/images/mainpage_blue_slice1_17.jpg" width="16" height="93" alt=""></td>
		<td><img src="${hostname}/images/mainpage_blue_slice1_18.jpg" width="216" height="54" alt=""></td>		
	 </tr>
	 <tr>
		<td><img src="${hostname}/images/mainpage_blue_slice1_20.jpg" width="216" height="39" alt=""></td>
     </tr>
     <tr>
      <td></td>
     </tr>
</table>
</body>
</html>
 

