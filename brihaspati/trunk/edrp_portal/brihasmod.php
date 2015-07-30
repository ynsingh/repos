
<?php include("header.php");
?>
<?php
$xmlDoc = new DOMDocument();
$xmlDoc->load( 'brihasmod.xml' );
$searchNode = $xmlDoc->getElementsByTagName( "COMPONENT" );

foreach( $searchNode as $searchNode )
{

        $xmlsno = $searchNode->getElementsByTagName( "SNO" );
        $valuesno = $xmlsno->item(0)->nodeValue;
        $xmlmod = $searchNode->getElementsByTagName( "MODULE" );
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
xmlhttp.open("GET","brihasmod.xml",false);
xmlhttp.send();
xmlDoc=xmlhttp.responseXML; 
var mod=xmlDoc.getElementsByTagName("COMPONENT");


var link=xmlDoc.getElementsByTagName("SECONDARYLINK");

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
		        <a href='brihaspati.php'  title='Go To BGAS'>CHOME</a>| 
			</td>
			 <td>
                        <a href='brihasmod.php'  title='Go To BGAS'>COMPONENT MODULE</a>| 
                        </td>
			 <td>
                        <a href='brihaspatilist.php'  title='Go To BGAS'>LIST OF INSTITUTE</a> 
                        </td>
</tr></table>
</div>
<div id="content">
        <div id ="columnC">
        <div style="width:60%; margin-top:-35px; line-height:160%;font-size:14px;color:#333;">
                               <script type="text/javascript">
                for (i=0;i<mod.length;i++)
                { 
                document.write("<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;");
                document.write(mod[i].getElementsByTagName("SNO")[0].childNodes[0].nodeValue);
                document.write("</td><td>&nbsp;&nbsp;&nbsp;&nbsp; ");
                document.write(mod[i].getElementsByTagName("MODULE")[0].childNodes[0].nodeValue);
                document.write("</td><tr><br>");
                }
 </script>
</div>
<div id ="columnD" >
<div style="width:56%;float:right;margin-top:-54.5%;margin-right:-6%">
<img src="images/home/brihaspati.png" />

<?php include("footer.php");
?>

