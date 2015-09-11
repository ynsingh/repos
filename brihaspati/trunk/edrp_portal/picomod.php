<?php include("header.php");
?>
<?php
session_start();
$xmlDoc = new DOMDocument();
$xmlDoc->load( 'picomod.xml' );
$searchNode = $xmlDoc->getElementsByTagName( "COMPONENT" );

foreach( $searchNode as $searchNode )
{

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
xmlhttp.open("GET","picomod.xml",false);
xmlhttp.send();
xmlDoc=xmlhttp.responseXML; 
var mod=xmlDoc.getElementsByTagName("COMPONENT");



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
		        <a href='pico.php'  title='Go To PICO'>CHOME</a>| 
			</td>
			 <td>
                        <a href='picomod.php'  title='Go To MODULE OF COMPONENT'>COMPONENT MODULE</a> 
                        </td>
			 <!--td>
                        <a href='bgaslist.php'  title='Go To BGAS'>LIST OF INSTITUTE</a> 
                        </td-->
</tr></table>
</div>
<div id="content">
<div id ="columnC">
        <div style="width:60%; margin-top:-35px;color:#333;line-height:160%;font-size:14px;">
                    <?php
                 if( empty($_SESSION['username']) )
                {?>

                               <script type="text/javascript">

                for (i=0;i<mod.length;i++)
                { 
                  document.write("<tr>");
                document.write("<td>");
                document.write(mod[i].getElementsByTagName("MODULE")[0].childNodes[0].nodeValue);
                document.write("<br/></td><tr>");

                }

       
 </script>

          <?php
                }else{
                echo    "<form action=\"project.php\" method=\"post\">";
                echo    "<input name='filenm' type='hidden' value='picomod.xml'/>";
                echo "<input name='redirect' type='hidden' value='picomod.php'/>";
                echo    "<input name='element' type='hidden' value='COMPONENT'/>";
                echo "<br>";
                echo    "<textarea name=\"UserAddress6\" rows=\"30\" cols=\"60\">";
                $xmlDoc->load( 'picomod.xml' );
                $searchNode = $xmlDoc->getElementsByTagName( "COMPONENT" );
                foreach( $searchNode as $searchNode3 )
                {

                    $xmlMOD = $searchNode3->getElementsByTagName( "MODULE" );
                    $valueMOD = $xmlMOD->item(0)->nodeValue;
                  //  echo $valueMOD;
                    echo str_replace("<br>","\n", $valueMOD);

                }
                echo"</textarea>";
                echo    "<input type='submit'value='update'>";
                }
                ?>

</div>
<div id ="columnD">
<?php
                 if(! empty($_SESSION['username']) )
                {?>
<div style="width:56%;float:right;margin-top:-46%;margin-right:-5%">
 <?php
                }else{
?>
               <div style="width:57%;float:right;margin-top:-60%;margin-right:-6%">
<?php
                }
?>

<!--div style="width:57%;float:right;margin-top:-58.7%;margin-right:-6%"-->
<img src="images/home/pico.png" />
                                </div>
<?php include("footer.php");
?>
