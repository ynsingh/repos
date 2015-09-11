<?php include("header.php");
?>
<?php
session_start();
$xmlDoc = new DOMDocument();
$xmlDoc->load( 'bgaslist.xml' );
$searchNode = $xmlDoc->getElementsByTagName( "LIST" );

foreach( $searchNode as $searchNode )
{

       // $xmlsno = $searchNode->getElementsByTagName( "SNO" );
       // $valuesno = $xmlsno->item(0)->nodeValue;
        $xmlINS = $searchNode->getElementsByTagName( "IName" );
        $valueINS= $xmlINS->item(0)->nodeValue;
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
        <div>
        <div style="width:60%; margin-top:-35px;font-size:14px;color:#333;line-height:160%;">
                   <?php
                 if( empty($_SESSION['username']) )
                {?>

                               <script type="text/javascript">
                for (i=0;i<list.length;i++)
                { 
                document.write("<tr>");
                document.write("<td>");
                document.write(list[i].getElementsByTagName("IName")[0].childNodes[0].nodeValue);
                document.write("<br/></td><tr>");
                
                }
 </script>
               <?php
                }else{
                echo    "<form action=\"project.php\" method=\"post\">";
                echo    "<input name='filenm' type='hidden' value='bgaslist.xml'/>";
                echo "<input name='redirect' type='hidden' value='bgaslist.php'/>";
                echo    "<input name='element' type='hidden' value='LIST'/>";
                echo "<br>";
                echo    "<textarea name=\"UserAddress7\" rows=\"30\" cols=\"68\">";
                $xmlDoc->load( 'bgaslist.xml');
                $searchNode = $xmlDoc->getElementsByTagName( "LIST" );
                foreach( $searchNode as $searchNode )
                {
                    $xmlINS = $searchNode->getElementsByTagName( "IName" );
                    $valueINS = $xmlINS->item(0)->nodeValue;
                  //  echo $valueMOD;
                    echo str_replace("<br>","\n", $valueINS);
                }
                echo"</textarea>";
                echo    "<input type='submit'value='update'>";
                }
                ?>
<div id ="columnD">
<!--div style="width:57%;float:right;margin-top:-56.7%;margin-right:-4.6%"-->
<?php
                 if(! empty($_SESSION['username']) )
                {?>
<div style="width:56%;float:right;margin-top:-80%;margin-right:-35%">
 <?php
                }else{
?>
                <div style="width:49%;float:right;margin-top:-101%;margin-right:-28%">
<?php
                }
?>
<img src="images/home/bgasmod.png" />
                                </div>
<?php include("footer.php");
?>

