<?php include("header.php");
?>
<?php
$xmlDoc = new DOMDocument();
$xmlDoc->load( 'component.xml' );
$searchNode = $xmlDoc->getElementsByTagName( "LIST" );

foreach( $searchNode as $searchNode )
{

    $xmlINS = $searchNode->getElementsByTagName( "IName" );
    $valueINS = $xmlINS->item(0)->nodeValue;

}
?>

<script>
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.open("GET","component.xml",false);
xmlhttp.send();
xmlDoc=xmlhttp.responseXML; 
var list=xmlDoc.getElementsByTagName("LIST");
</script>

<div id="link">
<table>
<tr>

        <script type="text/javascript">
        for (i=0;i<link.length;i++)
        { 
                document.write("<td><a href=");
                document.write(link[i].getElementsByTagName("TITLE")[0].childNodes[0].nodeValue);
                document.write(">");
                document.write(link[i].getElementsByTagName("NAME")[0].childNodes[0].nodeValue);
                document.write("</a></td>");
        }
        </script> 
		     <td>
		        <a href='brihaspati.php'  title='Go to Brihaspati'>CHOME</a>| 
			</td>
			 <td>
                        <a href='brihasmod.php'  title='Component Module'>COMPONENT MODULE</a>| 
                        </td>
			 <td>
                        <a href=''  title='List of Institute'>LIST OF INSTITUTE</a> 
                        </td>
</tr></table>
</div>
<div id="content">
     <div id ="columnC">
        <div style="width:46%; margin-top:-50px;color:#333;line-height:160%;font-size:14px;">
                                <script type="text/javascript">
                for (i=0;i<list.length;i++)
                { 
		document.write("<tr><td><br>&nbsp;&nbsp;&nbsp;&nbsp;");
                document.write(list[i].getElementsByTagName("SNO")[0].childNodes[0].nodeValue);
		document.write("<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;");
                document.write(list[i].getElementsByTagName("IName")[0].childNodes[0].nodeValue);
                }

 </script>
<p align="right"><a href="http://brihaspati.nmeict.in/brihaspati/servlet/brihaspati/template/ViewInstituteList.vm" target="_blank">View More</a></p>
<div id ="columnD">
<div style="width:59%;float:right;margin-top:-139%;margin-right:-62%">
<img src="images/home/brihaspaticmp.png" />
                                </div>
	 <?php include("footer.php");
?> 
