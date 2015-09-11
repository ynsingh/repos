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
        <div>
        <div style="width:47%; margin-top:-35px;font-size:14px;color:#333;line-height:160%;">
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
                echo    "<input name='filenm' type='hidden' value='component.xml'/>";
                echo "<input name='redirect' type='hidden' value='brihaspatilist.php'/>";
                echo    "<input name='element' type='hidden' value='LIST'/>";
                echo "<br>";
                echo    "<textarea name=\"UserAddress7\" rows=\"31\" cols=\"62\">";
                $xmlDoc->load( 'component.xml');
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
<p align="right"><a href="http://brihaspati.nmeict.in/brihaspati/servlet/brihaspati/template/ViewInstituteList.vm" target="_blank">View More</a></p>
<div id ="columnD">
<?php
                 if(! empty($_SESSION['username']) )
                {?>
<div style="width:56%;float:right;margin-top:-110%;margin-right:-52%">
 <?php
                }else{
?>
                <div style="width:49%;float:right;margin-top:-126%;margin-right:-50%">
<?php
                }
?>
<!--div style="width:62%;float:right;margin-top:-91%;margin-right:-41%"-->
<img src="images/home/brihaspaticmp.png" />
                                </div>
	 <?php include("footer.php");
?> 
