<%@page import="org.IGNOU.ePortfolio.Action.ShowProfilePicture"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<%
    String role = session.getAttribute("role").toString();
    String uName = session.getAttribute("name").toString();
    ShowProfilePicture sp = new ShowProfilePicture();
    String apath = request.getRealPath("/images");
    apath = apath.replace("\\", "/") + "/";
    session.putValue("appPath", apath);
    String pp = sp.ProPict();
%>  
<script type="text/javascript">
    function checkFileSize(inputFile) {
        var max = 0.05 * 1024 * 1024; // 1MB

        if (inputFile.files && inputFile.files[0].size > max) {
            alert("Please Select File Size Less Than 80kb.");
            inputFile.value = null; // Clear the field.
        }
        else {
            UProfilePicture.submit();

        }
    }
</script>
<div class="w100 fl-l">
    <div class="header">
        <div class="w100 fl-l"><img src="<s:url value="/images/header.png"/>" alt=""/></div>
    </div>
    <div class="menu_bg">
        <div class="wau fl-l"><img src="<s:url value="/images/blank.gif"/>" alt="" width="20" height="10" /></div>
        <div class="eportfolio_txt">ePORTFOLIO</div>
        <div class="menu"> 
            <% if (role.contains("admin")) {%>     
            <s:url id="EVID" action="ShowEventInfo" namespace="/Events"/>
            <s:url id="FDBID" action="ShowFeedBackInfo" namespace="/Administrator"/>
            <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>
            <s:a href="%{EVID}">Events</s:a>
          <!--  <a href="<s:url value="/Administrator/UserApproval.jsp"/>">Approved Users</a> -->
            <s:a href="%{FDBID}">Feedback</s:a>
            <% } else {%>
            <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>
            <a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a> 
            <a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a> 
            <a href="<s:url value="/MyConnection.jsp"/>">My Connections</a> 
            <a href="<s:url value="/MyBuilder.jsp"/>">My Builder</a> 
            <% }%> 
        </div>
        <div class="menu_arrow_img">&nbsp;</div>
        <div class="img_panel">
            <div class="profile_img" onmouseover="document.getElementById('pro_img_hov').style.display = 'block';" 
                 onmouseout="document.getElementById('pro_img_hov').style.display = 'none';">
                <img src="<s:url value="/"/><%=pp%>" width="39" height="40"/>
                <div class="pro_img_hov" id="pro_img_hov">
                    <table width="100%" class="fl-l" border="0" cellspacing="0" cellpadding="0">
                        <tr><td width="100%" align="center">
                                <div style="display: block; width: 100px; height: 20px; overflow: hidden;">
                                    Upload Picture
                                    <s:form action="UProfilePicture" name="UProfilePicture" method="post" enctype="multipart/form-data" namespace="/MyProfile" >
                                        <s:file type="file" name="upUserImage" onchange="checkFileSize(this)" style="position: absolute; right: 0px; top: 0px; font-family: Arial; font-size: 118px; margin: 0px; padding: 0px; cursor: pointer; opacity: 0;"/>
                                    </s:form>
                                </div>
                            </td>
                        </tr>
                        <tr><td>

                            </td></tr>
                    </table>
                </div>
            </div>
            <div class="my_profile"> 
                <s:url id="logoutID" action="logout" namespace="/"/>
                <div id="menu">
                    <ul>
                        <li class="w100p"><a href="#" class="fl-l"><strong><p><%=uName%></p></strong></a>
                            <ul class="sub-menu w120p fs11 hght60">
                                <li><a class="mart35" href="<s:url value="/ChangeLoginPassword.jsp"/>">Change Password</a></li>
                                <li><span class="marl10 fcgrey">------------------------</span></li>
                                <li><s:a href="%{logoutID}">Logout</s:a></li>
                                </ul>
                            </li>  
                            <li class="w20p"><img class="fl-l cursor" src="/ePortfolio/images/expanded_arrow.png" alt="" width="16" height="16" />
                                <ul class="sub-menu w120p fs11 hght60">
                                    <li><a class="mart35" href="<s:url value="/ChangeLoginPassword.jsp"/>">Change Password</a></li>
                                    <li><span class="marl10 fcgrey">------------------------</span></li>
                                    <li><s:a href="%{logoutID}">Logout</s:a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
