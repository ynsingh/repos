<%@page contentType="text/html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page pageEncoding="UTF-8"%>
<html><head>
    </head>
    <body  onload="showdiv();">
        <script>
            function showdiv(){

        var ele = document.getElementById("image1");


	if(ele.style.display == "block") {
    		ele.style.display = "none";

  	}
	else {
		ele.style.display = "block";

	}


    }
    function back(){
        window.location.href="<%=request.getContextPath()%>/cataloguing/cat_viewAll_biblio.jsp";

    }
    
        </script>
        <link rel="StyleSheet" href="<%=request.getContextPath()%>/css/page.css"/>

<div
   style="
      
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">


      



    <table width="100%" height="600px" valign="top" style="" >
        <tr><td valign="top" width="90%" style="">
                <br/>  <a href="#" onclick="javascript:showdiv();"> Add Digital Content of Bibliograhic details </a>
 <div id="image1"
   style="  top:20%;background: white;
   left:10%;
   overflow: hidden;
      position: absolute;
      display: none;"

      >


   <html:form action="/catimageupload1" method="post" styleId="form1" enctype="multipart/form-data">
<%

 session.setAttribute("id", (String)request.getParameter("id"));
     

%>

    <table class="table" style="border:2px solid cyan;" align="center" height="150px" width="500px">
        <tr><td>Content Description: <html:textarea   property="comment" name="catImageUploadActionForm" styleId="comment" rows="5"  cols="50"></html:textarea></td></tr>
        <tr><td width="250px" laign="center">Select PDF/PPT/Audio or video file :
 
<html:file  property="img" name="catImageUploadActionForm" styleId="img"    />

      <input type="hidden" name="filename" tabindex="16" id="filename" />
         <input type="submit" value="Upload Image"/>
         <input type="button" value="Cancel" onclick="back();"/>

</html:form>
               </td><%--<td align="center">
                   <%if (request.getAttribute("imagechange")!=null){%>
                        <html:img src="LibMS/admin/logo.jsp"  alt="no Image Selected" width="120" height="120"/>
                        <%}else{%>
                        <html:img src="LibMS/admin/viewimage.jsp" width="128" height="120" />
                        <%}%>

               </td>--%></tr></table>
         </div>

                     



            </td>               <td   valign="top">
                </td> </tr>

                </table>


                </div>


      </body>
</html>