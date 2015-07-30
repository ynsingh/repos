
<html xmlns="http://www.w3.org/1999/xhtml">
<link href="social-buttons.css" rel="stylesheet" type="text/css" />
<style>
        .social-icons{
                font: 14px/140% Arial, Helvetica, sans-serif;
                color: #666;
        }

        .social-icons a {
                color: #669;
                text-decoration: none;
        }
</style>

<?php

$xmlDoc = new DOMDocument();
$xmlDoc->load( 'main.xml' );
?>

<div id="footer">
<div id="header">
<table  border="0" cellspacing="0" cellpadding="0" class="master_table" style="width:1080px;">
<tr>
<td width="50%">
<script type="text/javascript">
                for (i=0;i<x.length;i++)
  { 
  document.write("<p>");
  document.write(y[i].getElementsByTagName("p1")[0].childNodes[0].nodeValue);
        document.write("<br>");
  document.write(y[i].getElementsByTagName("p2")[0].childNodes[0].nodeValue);
        document.write("<br>");
  document.write(y[i].getElementsByTagName("p3")[0].childNodes[0].nodeValue);
        document.write("<br>");
  document.write(y[i].getElementsByTagName("p4")[0].childNodes[0].nodeValue);
  document.write("</p>");
  }

 </script>

</td>
<td align="right">

      <p id="social-icons">
        <a href='http://www.blogger.com/share-post.g?blogID=361933389169269365&postID=3433546715559606585&target=twitter' class="sb flat twitter"></a>
        <a href='https://www.facebook.com/profile.php?id=100005620098367' target=email' class="sb flat facebook"></a>
        <a href='https://in.linkedin.com/pub/brihaspati-erp-mission-iitk/98/93a/456' class="sb flat linkedin"></a>
        <a href="#" class="sb flat pinterest"></a>
        <a href='http://www.blogger.com/share-post.g?blogID=361933389169269365&postID=3433546715559606585&target=email' class="sb flat email"></a>
        

      </p>
</td>
</tr>
</table>
</div>
</div>
<!-- demo js -->
<script src="js/demo/demo.js"></script>

<!-- ad -->
<script src="js/common/fusionad.js"></script>
</body>

</html>

