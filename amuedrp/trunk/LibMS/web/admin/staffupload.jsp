<%--  Design by Iqubal Ahmad
      Modified on 2011-02-02
     This jsp page is meant for Opening  File and provide facility to choose Image This will Submit Image on jsp page.
     This jsp page call ImageUploadAction Class.
     This jsp page is First page in Process of Image UPload .
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
  <head>
    <title>LibMS: Manage Staff</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script language="javascript" type="text/javascript">
    function submit()
    {
        alert(document.getElementById("img").value);
        document.getElementsById("filename").value=document.getElementById("img").value;
        alert(document.getElementsById("filename").value);
}
    function display()
    {
        window.width.value = 250;
        window.height.value = 150;
    }
</script>
  </head>
  <body onload="display()">
      <%request.setAttribute("imagechange1", 1);
      %>
      <html:form action="/staffimageupload" method="post" styleId="form1" enctype="multipart/form-data">
          Image Upload<html:file  property="img" name="ImageUploadActionForm" styleId="img" value="" onchange="submit()"  />
          <input type="hidden" name="filename" id="filename"  />
          <html:submit value="Submit"/>

               
      </html:form>
  </body>
</html>
