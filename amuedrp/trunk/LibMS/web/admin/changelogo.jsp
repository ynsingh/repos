<%@page contentType="text/html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page pageEncoding="UTF-8"%>
<html><head>
    </head>
    <body  >
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
        </script>
        <link rel="StyleSheet" href="<%=request.getContextPath()%>/css/page.css"/>
<jsp:include page="header.jsp" flush="true" />
<div
   style="
      top: 20%;
   left:10%;
   right:5px;
      position: absolute;

      visibility: show;">


      



    <table width="100%" height="600px" valign="top" style="" >
        
        <tr><td valign="top" width="90%" style="">
                
 



   <html:form action="/instilogo" method="post" styleId="form1" enctype="multipart/form-data">


    <table class="table" style="border:1px solid black;" align="center" height="150px" width="500px">
        <tr><td class="headerStyle" align="center" height="30px">Upload Institute Logo</td></tr>
        <tr><td width="250px" laign="center">Select Image(.jpg/.png file only):
 
<html:file  property="img" name="ImageUploadActionForm" styleId="img"    />
      <input type="hidden" name="filename" tabindex="16" id="filename" />
         <input type="submit" value="Upload Image"/>

</html:form>
               </td><%--<td align="center">
                   <%if (request.getAttribute("imagechange")!=null){%>
                        <html:img src="LibMS/admin/logo.jsp"  alt="no Image Selected" width="120" height="120"/>
                        <%}else{%>
                        <html:img src="LibMS/admin/viewimage.jsp" width="128" height="120" />
                        <%}%>

               </td>--%></tr></table>
         

                     



            </td>               <td   valign="top">
                </td> </tr>

                </table>


                </div>


      </body>
</html>