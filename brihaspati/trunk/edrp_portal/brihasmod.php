
<?php include("header.php");
?>
<?php
$xmlDoc = new DOMDocument();
$xmlDoc->load( 'brihasmod.xml' );
$searchNode = $xmlDoc->getElementsByTagName( "COMPONENT" );

foreach( $searchNode as $searchNode )
{

        $xmlMOD = $searchNode->getElementsByTagName( "MODULE" );
        $valueMOD= $xmlMOD->item(0)->nodeValue;

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
        <div style="width:60%; margin-top:-10px; line-height:160%;font-size:14px;color:#333;">
                <?php
                 if( empty($_SESSION['username']) )
                {?>

                 <script type="text/javascript">
                for (i=0;i<mod.length;i++)
                { 
                document.write("</td><td> ");
                document.write(mod[i].getElementsByTagName("MODULE")[0].childNodes[0].nodeValue);
                document.write("</td><tr><br>");
                document.write("<br>");
                }
 </script>
              <?php
                }else{
                echo    "<form action=\"project.php\" method=\"post\">";
                echo    "<input name='filenm' type='hidden' value='brihasmod.xml'/>";
                echo "<input name='redirect' type='hidden' value='brihasmod.php'/>";
                echo    "<input name='element' type='hidden' value='COMPONENT'/>";
                echo "<br>";
                echo    "<textarea name=\"UserAddress6\" rows=\"30\" cols=\"64\">";
                $xmlDoc = new DOMDocument();
                $xmlDoc->load( 'brihasmod.xml' );
                $searchNode = $xmlDoc->getElementsByTagName( "COMPONENT" );
                foreach( $searchNode as $searchNode1 )
                {

                    $xmlMOD = $searchNode1->getElementsByTagName( "MODULE" );
                    $valueMOD = $xmlMOD->item(0)->nodeValue;
                    //echo $valueSNO." ".$valueMOD."\n";
                    echo str_replace("<br>","\n", $valueMOD);
                }
                echo"</textarea>";
                echo    "<input type='submit'value='update'>";
                }
                ?>


</div>
<div id ="columnD" >
<!--div style="width:56%;float:right;margin-top:-54.5%;margin-right:-6%"-->
 <?php
                 if(! empty($_SESSION['username']) )
                {?>
<div style="width:56%;float:right;margin-top:-46%;margin-right:-6%">
 <?php
                }else{
?>
		<div style="width:56%;float:right;margin-top:-60%;margin-right:-5%">
<?php
		}
?>
<img src="images/home/brihaspati.png" />

<?php include("footer.php");
?>
