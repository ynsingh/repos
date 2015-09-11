<?php include("header.php");
?>

<?php
session_start();
$xmlDoc = new DOMDocument();
$xmlDoc->load( 'bsyncmod.xml' );
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
xmlhttp.open("GET","bsyncmod.xml",false);
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
		        <a href='bsync.php'  title='Go To BSYNC'>CHOME</a>| 
			</td>
			 <td>
                        <a href='bsyncmod.php'  title='Go To MODULE OF BSYNC'>COMPONENT MODULE</a> 
                        </td>
			 <!--td>
                        <a href=''  title='Go To '>LIST OF INSTITUTE</a> 
                        </td-->
</tr></table>
</div>
<div id="content">
<div id ="columnC">
        <div style="width:60%; margin-top:-35px;font-size:14px;color:#333;">
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
                echo    "<input name='filenm' type='hidden' value='bsyncmod.xml'/>";
                echo "<input name='redirect' type='hidden' value='bsyncmod.php'/>";
                 echo    "<input name='element' type='hidden' value='COMPONENT'/>";
                echo "<br>";
                echo    "<textarea name=\"UserAddress6\" rows=\"30\" cols=\"55\">";
                $xmlDoc = new DOMDocument();
                $xmlDoc->load( 'bsyncmod.xml' );
                $searchNode = $xmlDoc->getElementsByTagName( "COMPONENT" );
                foreach( $searchNode as $searchNode6 )
                {

                    $xmlMOD = $searchNode6->getElementsByTagName( "MODULE" );
                    $valueMOD = $xmlMOD->item(0)->nodeValue;
                    echo str_replace("<br>","\n", $valueMOD);
                    //echo $valueSNO." ".$valueMOD."\n";
                }
                echo"</textarea>";
                echo    "<input type='submit'value='update'>";
                }
                ?>		
	
		 		</div>
<div id ="columnD">
<!--div style="width:57%;float:right;margin-top:-27%;margin-right:-6%"-->
<?php
                 if(! empty($_SESSION['username']) )
                {?>
<div style="width:56%;float:right;margin-top:-46%;margin-right:-4.4%">
 <?php
                }else{
?>
               <div style="width:57%;float:right;margin-top:-30.8%;margin-right:-6.3%">
<?php
                }
?>

<img src="images/home/bsync.png" />
                                </div>
	 	
<?php include("footer.php");
?>
		
