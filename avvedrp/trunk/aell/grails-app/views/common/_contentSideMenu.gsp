<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<style type="text/css">
.textSideMenu {
	font-family: Verdana, Arial, Helvetica, Bitstream Vera Sans;
	font-size: 14px;
	/*padding-right: 15px;*/
	padding-left: 30px;
	/*text-align: justify;*/
	font-weight: bold;
	text-transform: capitalize;
	color:#CC3399;
	padding-bottom:5px;
		
	}
</style>

<div style="width:140; background-color:#FFF">
		<style type="text/css">
		/*
		
		This is one of the free scripts from www.dhtmlgoodies.com
		You are free to use this script as long as this copyright message is kept intact
		
		(c) Alf Magne Kalleland, http://www.dhtmlgoodies.com - 2005
		
		*/		
		#dhtmlgoodies_tree{
		height:250px;
		background:   url(../images/side-barleftt.jpg) no-repeat;
		padding-left:15px;
		}
		#dhtmlgoodies_tree li{
			list-style-type:none;	
			font-family:Verdana, Geneva, sans-serif;
			font-size:12px;
			font-weight:bold;
			vertical-align:middle;
			padding-top:5px;
		}
		#dhtmlgoodies_topNodes{
			margin-left:0px;
			padding-left:0px;
			
		}
		#dhtmlgoodies_topNodes ul{
			margin-left:20px;
			padding-left:0px;
			display:none;
			
		}
		#dhtmlgoodies_tree .tree_link{
			line-height:13px;
			padding-left:2px;

		}
		#dhtmlgoodies_tree img{
			vertical-align:middle;
		}
		#dhtmlgoodies_tree a{
			color: #000000;
			text-decoration:none;
			vertical-align:middle;
		}
		.activeNodeLink{
			background-color: #316AC5;
			color: #000000;
			font-weight:bold;
			
		}
		</style>		
				<script type="text/javascript">

	
		var plusNode = '${hostname}/aell/images/expand.gif';
		var minusNode = '${hostname}/aell/images/collapse.gif';
		var nameOfCookie = 'dhtmlgoodies_expanded';
		var initExpandedNodes =",102,101,100,99";
		
		/*
		These cookie functions are downloaded from 
		http://www.mach5.com/support/analyzer/manual/html/General/CookiesJavaScript.htm
		*/
		function Get_Cookie(name) { 
		   var start = document.cookie.indexOf(name+"="); 
		   var len = start+name.length+1; 
		   if ((!start) && (name != document.cookie.substring(0,name.length))) return null; 
		   if (start == -1) return null; 
		   var end = document.cookie.indexOf(";",len); 
		   if (end == -1) end = document.cookie.length; 
		   return unescape(document.cookie.substring(len,end)); 
		} 
		// This function has been slightly modified
		function Set_Cookie(name,value,expires,path,domain,secure) { 
			expires = expires * 60*60*24*1000;
			var today = new Date();
			var expires_date = new Date( today.getTime() + (expires) );
		    var cookieString = name + "=" +escape(value) + 
		       ( (expires) ? ";expires=" + expires_date.toGMTString() : "") + 
		       ( (path) ? ";path=" + path : "") + 
		       ( (domain) ? ";domain=" + domain : "") + 
		       ( (secure) ? ";secure" : ""); 
		    document.cookie = cookieString; 
		} 
		/*
		End downloaded cookie functions
		*/
		
		function expandAll()
		{
			var treeObj = document.getElementById('dhtmlgoodies_tree');
			var images = treeObj.getElementsByTagName('img');
			for(var no=0;no<images.length;no++){
				if(images[no].className=='tree_plusminus' && images[no].src.indexOf(plusNode)>=0)expandNode(false,images[no]);
			}
		}
		function collapseAll()
		{
			var treeObj = document.getElementById('dhtmlgoodies_tree');
			var images = treeObj.getElementsByTagName('img');
			for(var no=0;no<images.length;no++){
				if(images[no].className=='tree_plusminus' && images[no].src.indexOf(minusNode)>=0)expandNode(false,images[no]);
			}
		}
		
		
		function expandNode(e,inputNode)
		{
			if(initExpandedNodes.length==0)initExpandedNodes=",";
			if(!inputNode)inputNode = this; 
			if(inputNode.tagName.toLowerCase()!='img')inputNode = inputNode.parentNode.getElementsByTagName('IMG')[0];	
			
			var inputId = inputNode.id.replace(/[^\d]/g,'');			
			
			var parentUl = inputNode.parentNode;
			var subUl = parentUl.getElementsByTagName('UL');

			if(subUl.length==0)return;
			if(subUl[0].style.display=='' || subUl[0].style.display=='none'){
				subUl[0].style.display = 'block';
				inputNode.src = minusNode;
				initExpandedNodes = initExpandedNodes.replace(',' + inputId+',',',');
				initExpandedNodes = initExpandedNodes + inputId + ',';
				
			}else{
				subUl[0].style.display = '';
				inputNode.src = plusNode;	
				initExpandedNodes = initExpandedNodes.replace(','+inputId+',',',');			
			}
			Set_Cookie(nameOfCookie,initExpandedNodes,60);
			
			
			
		}
		
		function initTree()
		{
			// Assigning mouse events
			var parentNode = document.getElementById('dhtmlgoodies_tree');
			var lis = parentNode.getElementsByTagName('LI'); // Get reference to all the images in the tree
			for(var no=0;no<lis.length;no++){
				var subNodes = lis[no].getElementsByTagName('UL');
				if(subNodes.length>0){
					lis[no].childNodes[0].style.visibility='visible';	
				}else{
					lis[no].childNodes[0].style.visibility='hidden';
				}
			}	
			
			var images = parentNode.getElementsByTagName('IMG');
			for(var no=0;no<images.length;no++){
				if(images[no].className=='tree_plusminus')images[no].onclick = expandNode;				
			}	

			var aTags = parentNode.getElementsByTagName('A');
			var cursor = 'pointer';
			if(document.all)cursor = 'hand';
			for(var no=0;no<aTags.length;no++){
				aTags[no].onclick = expandNode;		
				aTags[no].style.cursor = cursor;		
			}
			var initExpandedArray = initExpandedNodes.split(',');

			for(var no=0;no<initExpandedArray.length;no++){
				if(document.getElementById('plusMinus' + initExpandedArray[no])){
					var obj = document.getElementById('plusMinus' + initExpandedArray[no]);	
					expandNode(false,obj);
				}
			}				
		}
		//collapseAll();
		window.onload = initTree;
		
		</script>	
<div align="center" id="dhtmlgoodies_tree"  >
<table border="0">
<tr>
<td>
<ul id="dhtmlgoodies_topNodes">
<li class="tree_node" id="node_102"><img id="plusMinus102" class="tree_plusminus" src="${hostname}/aell/images/expand.gif" style="visibility: visible; "><img src="${hostname}/aell/images/ico_admin.png"><a class="tree_link" style="cursor: pointer; ">${grailsApplication.config.admindashboard_title}</a>
	<ul style=""><li class="tree_node"><img class="tree_plusminus" id="plusMinus13" src="${hostname}/aell/images/expand.gif" style="visibility: hidden; "><img src="${hostname}/aell/images/subject.png"><g:link class="tree_link" action="index" controller="contentHome" style="cursor: pointer; ">${grailsApplication.config.module}</g:link></li>
		<li class="tree_node"><img class="tree_plusminus" id="plusMinus14" src="${hostname}/aell/images/expand.gif" style="visibility: hidden; "><img src="${hostname}/aell/images/topic.png"><g:link class="tree_link" action="index" controller="topic" style="cursor: pointer; ">${grailsApplication.config.topic}</g:link></li>
		<li class="tree_node"><img class="tree_plusminus" id="plusMinus16" src="${hostname}/aell/images/expand.gif" style="visibility: hidden; "><img src="${hostname}/aell/images/experiment.png"><g:link class="tree_link" action="index" controller="experiment" style="cursor: pointer; ">${grailsApplication.config.sub_topic}</g:link></li>
		<li class="tree_node"><img class="tree_plusminus" id="plusMinus17" src="${hostname}/aell/images/expand.gif" style="visibility: hidden; "><img src="${hostname}/aell/images/edit.png"><g:link class="tree_link" action="index" controller="editExperiment" style="cursor: pointer; ">${grailsApplication.config.edit_contents}</g:link></li>
	</ul>
	</li>
</ul>

 <g:if test="${session.roleName != 'Faculty'}">
<ul id="dhtmlgoodies_topNodes">
<li class="tree_node" id="node_101"><img id="plusMinus101" class="tree_plusminus" src="${hostname}/aell/images/expand.gif" style="visibility: visible; "><img src="${hostname}/aell/images/ico_admin.png"><a class="tree_link" style="cursor: pointer; ">Portal Admin</a>
	<ul style=""><li class="tree_node"><img class="tree_plusminus" id="plusMinus13" src="${hostname}/aell/images/expand.gif" style="visibility: hidden; "><img src="${hostname}/aell/images/role_manage.png"><g:link  action="index" class="tree_link" controller="users" style="cursor: pointer; ">User Management</g:link> </li>
		<li class="tree_node"><img class="tree_plusminus" id="plusMinus13" src="${hostname}/aell/images/expand.gif" style="visibility: hidden; "><img src="${hostname}/aell/images/user_registration.png"><g:link  action="adminUserRegistration" class="tree_link" controller="users" style="cursor: pointer; ">User Registration</g:link></li>
		<li class="tree_node"><img class="tree_plusminus" id="plusMinus13" src="${hostname}/aell/images/expand.gif" style="visibility: hidden; "><img src="${hostname}/aell/images/accessibility.png"><g:link  action="managePrivilege" class="tree_link" controller="users" style="cursor: pointer; ">Manage Privileges</g:link></li><li class="tree_node"><img class="tree_plusminus" id="plusMinus13" src="${hostname}/aell/images/expand.gif" style="visibility: hidden; "><img src="${hostname}/aell/images/role.png"><g:link  action="index" class="tree_link" controller="role" style="cursor: pointer; ">Manage Role</g:link></li>
	</ul>
</li>
</ul>

 </g:if>
<ul id="dhtmlgoodies_topNodes">
<li class="tree_node" id="node_100"><img id="plusMinus100" class="tree_plusminus" src="${hostname}/aell/images/expand.gif" style="visibility: visible; "><img src="${hostname}/aell/images/ico_admin.png"><a class="tree_link" style="cursor: pointer; ">Account</a>
	<ul style=""><li class="tree_node"><img class="tree_plusminus" id="plusMinus13" src="${hostname}/aell/images/expand.gif" style="visibility: hidden; "><img src="${hostname}/aell/images/changePassword.png"><g:link  action="changePassword" class="tree_link" controller="users" style="cursor: pointer; ">Change Password</g:link></li></ul>
</li>
<!--
<li class="tree_node" id="node_99"><img id="plusMinus99" class="tree_plusminus" src="${hostname}/aell/images/expand.gif" style="visibility: visible; "><img src="${hostname}/aell/images/ico_admin.png"><a class="tree_link" style="cursor: pointer; ">Reports</a>
<ul style="">
	<li class="tree_node"><img class="tree_plusminus" id="plusMinus13" src="${hostname}/aell/images/expand.gif" style="visibility: hidden; "><img src="${hostname}/aell/images/role_manage.png"><g:link  action="" class="tree_link" controller="report" action="userStatus" style="cursor: pointer; " params="[statusId:'Active',universityId:'1']">Enrolled Users</g:link></li>
    <li class="tree_node"><img class="tree_plusminus" id="plusMinus13" src="${hostname}/aell/images/expand.gif" style="visibility: hidden; "><img src="${hostname}/aell/images/role_manage.png"><g:link  action="" class="tree_link" controller="report" action="loginStatus" style="cursor: pointer; ">Current Active Users</g:link></li>
    <li class="tree_node"><img class="tree_plusminus" id="plusMinus13" src="${hostname}/aell/images/expand.gif" style="visibility: hidden; "><img src="${hostname}/aell/images/role_manage.png"><g:link  action="" class="tree_link" controller="report" action="tabaccessStatus" style="cursor: pointer; ">Accessed Tab Details</g:link></li>
    <li class="tree_node"><img class="tree_plusminus" id="plusMinus13" src="${hostname}/aell/images/expand.gif" style="visibility: hidden; "><img src="${hostname}/aell/images/role_manage.png"><g:link  action="" class="tree_link" controller="report" action="useraccessStatus" style="cursor: pointer; ">User Access Details</g:link></li> 
</ul>
</li> 
-->
</ul>
</td>
</tr>
</table>
</div>
</div>

