<?php include("header.php");
?>

<?php
session_start();
$xmlDoc = new DOMDocument();
$xmlDoc->load( 'bgaslist.xml' );
$searchNode = $xmlDoc->getElementsByTagName( "LIST" );

foreach( $searchNode as $searchNode )
{

        $xmlsno = $searchNode->getElementsByTagName( "SNO" );
        $valuesno = $xmlsno->item(0)->nodeValue;
        $xmlmod = $searchNode->getElementsByTagName( "IName" );
        $valuemod= $xmlmod->item(0)->nodeValue;
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
xmlhttp.open("GET","bgaslist.xml",false);
xmlhttp.send();
xmlDoc=xmlhttp.responseXML; 
var mod=xmlDoc.getElementsByTagName("LIST");


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
		        <a href='bgas.php'  title='Go To BGAS'>CHOME</a>| 
			</td>
			 <td>
                        <a href='cmp.php'  title='Go To MODULE OF BGAS'>COMPONENT MODULE</a>| 
                        </td>
			 <td>
                        <a href='bgaslist.php'  title='Go To INSTITUTE LIST'>LIST OF INSTITUTE</a> 
                        </td>
</tr></table>
</div>
<div id="content">
<div id ="columnC">
        <div style="width:60%; margin-top:-35px; font-size:14px;color:#333;line-height:160%;">
                               <script type="text/javascript">
                for (i=0;i<mod.length;i++)
                { 
                document.write("<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;");
                document.write(mod[i].getElementsByTagName("SNO")[0].childNodes[0].nodeValue);
                document.write("</td><td>&nbsp;&nbsp;&nbsp;&nbsp; ");
                document.write(mod[i].getElementsByTagName("IName")[0].childNodes[0].nodeValue);
                document.write("</td><tr><br>");
                }
 </script>
		 		</div>

<div id ="columnD">
<div style="width:57%;float:right;margin-top:-56.7%;margin-right:-4.6%">
<img src="images/home/bgasmod.png" />
                                </div>
<?php include("footer.php");
?>
