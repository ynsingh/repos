<?
/* @(#)billdetail.php
 *
 *  Copyright (c) 2007-2008 Brihaspati Team/ETRG,IIT Kanpur.
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or
 *  without modification, are permitted provided that the following
 *  conditions are met:
 *
 *  Redistributions of source code must retain the above copyright
 *  notice, this  list of conditions and the following disclaimer.
 *
 *  Redistribution in binary form must reproducuce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 *
 *
 *  Contributors: Members of ETRG, I.I.T. Kanpur
 *
 *	*********************************************************************************
 *      *                                                                               *
 *  	*	This file will dispaly the details of bill of telephone selected. 	*
 *  	*	by user. It is display the Called date, Called telephone number,       	*
 *      *       ......etc                                                      		*
 *      *                                                                		*
 *      *                                                                		*
 *  	*	@author<a href="nksinghiitk@gmail.com">Nagendra Kumar Singh</a>        	*
 *  	*	@author<a href="raajkhurana@gmail.com">Raj Kumar</a>                   	*
 *	*                                                                		*
 *	*********************************************************************************
 */
session_start();
include 'config.php';
include 'opendb.php';

        $thisid  =$_SESSION['loginuserid'];

	/*
        $getph   =$_GET['radio'];
        $getmonth=$_GET['month'];
        $getyear =$_GET['year'];
     	$grandcost =$_GET['gtotal'];

        $cancel  =$_POST['cancel'];
        $back  =$_POST['backpage'];
	*/
        $getph   =mysql_real_escape_string($_GET['radio']);
        $getmonth=mysql_real_escape_string($_GET['month']);
        $getyear =mysql_real_escape_string($_GET['year']);
     	$grandcost =mysql_real_escape_string($_GET['gtotal']);

        $cancel=mysql_real_escape_string($_POST['cancel']);
        $back  =mysql_real_escape_string($_POST['backpage']);
	$phlength=strlen($getph);



    $que       = "select name, department, phone_number,ph from userinfo, paddress 
	          where ((userid='$thisid') && (usrid='$thisid'))";
    $result    = execute_query($que);
    while($row = mysql_fetch_array($result))
       {   
	   $dbname    = $row['name'];
	   $dbphone[] = $row['phone_number'];
	   $dbdept    = $row['department'];
	   $no_of_ph[]= $row['ph'];

       }





		//******************To Reduce into extention format(last four digits)************** 

			$array  = array(); 
			$extn_no=$getph;
			for($i  = strlen($extn_no)-4; $i < strlen($extn_no); $i++) 
				{ 
    		 		$array[] = $extn_no[$i];
				}
	
		 		$extn=($array[0].$array[1].$array[2].$array[3]."\n");

		//************************ Select   Year ********************************************

         $yearis="select calldate from billtable where extn_number=$extn and calldate like '%/$getmonth/$getyear'";		
			$resultis= execute_query($yearis);
                	while($row=mysql_fetch_array($resultis))
                		{
				$calldate  =$row['calldate'];
				list($month, $day, $year)=split("/",$calldate);
				$year;
				list($years)=split(" ",$year);
				$years;
				}
			
			
		 	if(($back)||($cancel))
	   			{
				header("Location: bill.php");
				exit();
	    			}     	 
			
?>
<html>
	<script>
	function frmsubmit()
        	{
                document.frm.submit();
        	}

	</script>
<head>
<body>
<!--header.php -->

	
<?if($_SESSION['loginuserid']!=1)
	{?>

	<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
  		<tr>
    		<td align="center" width="90%">
    		<font face="Bodoni MT Black" size="5" color="#008000">
		<img border="0" src="images/otbiitk_banner.gif" width="60%" height="60">
		</font>
	</table>
  
	<hr>

	<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">
		<tr align="right" valign="middle">
   		<td class="txthdmenu"><div id='div_id'><? include "headerlinks.php"; ?> 
        	<br></div></td></tr>
	</table>


<br>
  <form action="" name="frm" method="post" onchage="frmsubmit();">
  <table  align="center" border="2" cellSpacing=3 cellPadding=3 width="90%" height="90%" bgcolor="#FFFFF">

 	<tr><td width="100%" colspan="6" valign="top">
	<table  valign="top" border="0" cellpadding="3" cellspacing="3" border-color=" #008000" width="90%" >
 		<tr><td width="50%" colspan="3" align="right">
    		<b><font face="times new roman" size="4" color="#003399">&nbsp;&nbsp;</font></b></td>
 		<td width="50%" colspan="3" align="left">
    		<b><font face="times new roman" size="4" color="#003399">&nbsp;&nbsp;</b></font>
		</td>  

		</td></tr>
		
<tr><td colspan="6" align="center"> <b><font face="times new roman" size="4" color="#003399">BRIEF BILL</font></b></td></tr> 	

        	<tr><td width="12%" align="right">
		<b><font face="Times New Roman" color="#003399">NAME:</font></b></td>
	


        	<td width="18%" align="left">
		<b><font face="Times New Roman" color="#800000" size="4"> </font>
		<input type="text" size="13" name="name" value="<?echo $dbname ?>" readonly></font></b></td>



		<td width="18%" align="right">
		<b><font face="Times New Roman" color="#003399">DEPARTMENT:</font></b></td>



		<td width="18%" align="left">
		<input type="text" name="dept" value="<?echo $dbdept ?>" size="23" readonly></font>
		</td>

		<td width="50%"  align="right">
    		<font face="times new roman" size="3" color="#003399">WELCOME:</font>
		</td>

 		<td width="50%"  align="left">
    		<font face="times new roman" size="3" color="#0000ff">   
		<?echo$_SESSION['loginuserid']?></font> 

		</td></tr>


		<tr><td width="13%" align="right">
		<b><font face="Times New Roman" color="#003399">PHONE:</font></b>
		</td>



		<td width="18%" align="left">
		<input type="text" name="dept" value="<?echo $getph ?>" size="13" readonly></font></td>



		<td width="13%" align="right">
		<b><font face="Times New Roman" color="#003399">YEAR:</font></b></td>



		<td width="18%" align="left">
		<input type="text" name="dept" value="<?echo $getyear ?>" size="23" readonly>
		</font></td>

		<td width="13%" align="right">
		<b><font face="Times New Roman" color="#003399">MONTH:</font></b></td>
		<td width="18%" align="left">
		<input type="text" name="dept" value="<?echo $getmonth ?>" size="4" readonly>
		</font></td>
		</tr>


	</table>
	<hr>


  <table  border="1" cellSpacing=3 cellPadding=3 width="90%" align=center >
     <tbody>


		<tr><td align="center" width="10%">
		<font face="Times New Roman" color=#008000>Call Date
		</font></td>

        	<td align="center" width="10%">
		<font face="Times New Roman" color=#008000>Call Time
		</font></td>

        	<td align="center" width="10%">
		<font face="Times New Roman" color=#008000>Dialed Number
		</font></td>

        		<td align="center" width="10%">
			<font face="Times New Roman" color=#008000>Destination
			</font></td>

        		<td align="center" width="10%">
			<font face="Times New Roman" color=#008000>Call Type
			</font></td>

        			<td align="center" width="10%">
				<font face="Times New Roman" color=#008000>Duration
				</font></td>

        			<td align="center" width="10%">
				<font face="Times New Roman" color=#008000>Amount
				</font></td>


	<?
    			
		if($years==$getyear)
			{
         		$sql="select calldate, calltime, dialedno, destination, calltype, duration, cost from billtable where extn_number=$extn and calldate like '%/$getmonth/$getyear'";	
                	$result=execute_query($sql);
                	while($row=mysql_fetch_array($result))
                		{
				$calldates[]  =$row['calldate'];
				$calltime[]   =$row['calltime'];
				$dialedno[]   =$row['dialedno'];
				$destination[]=$row['destination'];
				$calltype[]   =$row['calltype'];
				$duration[]   =$row['duration'];
				$cost[]       =$row['cost'];
		

	?>
	
  					<tr>
    					<td align="left" > <? echo$calldates[]  =$row['calldate']?>   </td>
    					<td align="left" > <? echo$calltime[]   =$row['calltime']?>   </td>
    					<td align="left" > <? echo$dialedno[]   =$row['dialedno']?>   </td>
    					<td align="left" > <? echo$destination[]=$row['destination']?></td>
    					<td align="left" > <? echo$calltype[]   =$row['calltype']?>   </td>
    					<td align="left" > <? echo$duration[]   =$row['duration']?>   </td>
    					<td align="left" > <? echo$cost[]       =$row['cost']?>       </td>
					</tr>




			<?}     }?>



     
</tbody>
</table>
	<hr>
  	<table  align="center" border="0" cellSpacing=3 cellPadding=3 width="90%" bgcolor="#FFFFF">
		<tr>
      		<td width="14%">&nbsp;</td>
      		<td width="14%">&nbsp;</td>
      		<td width="15%" align="center">
		<input type="submit" style="width:80px;" value=" BACK " name="backpage"></td>
      		<td width="14%">&nbsp;</td>

      		<td width="15%"><input type="submit" style="width:80px;" value="CANCEL" name="cancel"></td>
      		<td width="14%">&nbsp;</td>
      		<td width="14%">&nbsp;</td>
		</tr>
		</table>
</tbody>
</table>
</td></tr>
</table>
		<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">
			<tr><td height="50">
			</td></tr>
			<tr align="center" valign="middle">
   			<td class="txthdmenu"><div id='div_id'><? include "footer.php"; ?> 
        		</div></td></tr>
		</table><br>

</form>
<?}?>
</body></html>
