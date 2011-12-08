<%-- 
    Document   : index
    Created on : Jul 22, 2011, 2:12:14 PM 
Author     : Amit
Version    : 1
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Home</title>
 <script type="text/javascript" src="<s:url value="/JS/jquery-latest.js"/>"></script>

        <script type="text/javascript">
            $(document).ready(function(){
                $("#accordion > li > div").click(function(){
 
                    if(false == $(this).next().is(':visible')) {
                        $('#accordion ul').slideUp(300);
                    }
                    $(this).next().slideToggle(300);
                });
 
                $('#accordion ul:eq(0)').show();

            });
        </script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('fieldset.jcalendar').jcalendar();
            });
        </script>
        <script src="<s:url value="/JS/jquery-1.6.4.min.js"/>" type="text/javascript"></script>
        <script src="<s:url value="/JS/jcalendar-source.js"/>" type="text/javascript"></script>
        <link href="<s:url value="/JS/jcalendar.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/theme1/style.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="../JS/jquery-ui.min.js"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="../JS/jquery-ui.css"/>

        
    </head>

    <body><%        
           if (session.getAttribute("user_id") == null) {
                pageContext.forward("../login.jsp");
            }
                   
        %>
        <jsp:include page="../Header.jsp"/>
        <div id="container">
            <div class="wrapper">
                <jsp:include page="../Left-Nevigation.jsp"/>
                <div id="col2">
                    <h3>My Resources</h3>
                    <br/><br/>
                    <s:actionerror/>
                    <s:form action="addInfo" method="post" enctype="multipart/form-data">
                        <table align="center" border="0" cellpadding="2" cellspacing="0" width="60%">

                            <tr><td><s:textfield name="filename" label="Name"/></td>
                                <td> <s:file name="userImage" label="File"/></td>
                                <td align="center"> <s:submit value="Upload" align="center"/> </td> 
                            </tr>
                        </table>
                    </s:form>
                    <s:form action="CreateFolder" method="post">
                        <table align="center" border="0" cellpadding="2" cellspacing="0" width="60%">

                            <tr><td><s:textfield name="name" label="Create Folder"/></td>
                                <td align="center"><s:submit value="Create" align="center"/> </td> 
                            </tr>
                        </table>

                    </s:form>
                    <s:a action="show_files">Show</s:a>
                    <table cellpadding="0" cellspacing="2" bordercolor="skyblue" border="0">
                        <tr>
                            <th align="center" height="30" width="250">File Name</th>
                            <th align="center" height="30" width="250">Size</th>
                            <th align="center" height="30" width="250">Type</th>
                            <th align="center" height="30" width="250">Description</th>
                            <th align="center" height="30" width="250">Date</th>
                            <th align="center" height="30" width="250">Edit</th>
                            <th align="center" height="30" width="250">Delete</th>
                        </tr>
                        <s:iterator value="imagelistlist" var="imagelist">
                            <tr>
                                <td align="center" height="30" width="250"><s:if test="filetype!=null"><a href="download?fileid=<s:property value="fileid"/>" target="_blank"><s:property value="filename"/></a></s:if><s:else><s:property value="filename"/></s:else></td>
                                <td align="center" height="30" width="250"><s:property value="size"/></td>
                                <td align="center" height="30" width="250"><s:if test="filetype==null"><img src="../images/folder.gif"/></s:if><s:elseif test="filetype!=null"><img src="../images/file.gif"/></s:elseif></td>
                                <td align="center" height="30" width="250"><s:property value="description"/></td>
                                <td align="center" height="30" width="250"><s:property value="filedate"/></td>
                                <td align="center" height="30" width="250"><a href="edit?fileid=<s:property value="fileid"/>"><img src="../icons/edit.gif" title="Edit Record"/></a></td>
                                <td align="center" height="30" width="250"><a href="delete?fileid=<s:property value="fileid"/>" onclick="return confirm('Are U sure')"><img src="../icons/delete.gif" title="Delete Record"/></a></td>
                            </tr>
                        </s:iterator>
                    </table>                  
                </div>
                <jsp:include page="../Right-Nevigation.jsp"/>
                <div class="clear"></div>
            </div>
        </div>
        <jsp:include page="../Footer.jsp"/>
    </body>
</html>
