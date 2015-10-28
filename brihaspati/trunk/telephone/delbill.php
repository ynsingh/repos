<?
/* @(#)delbill.php
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
 *  	*	This file will dispaly the brief  bill of all telephones held by user. 	*
 *  	*	It will display total sum of bill and total sum of all telephone      	*                                      *      *       numbers as well.                                          	      *	
 *      *                                                                		*
 *  	*	@author<a href="raajkhurana@gmail.com">Raj Kumar</a>                   	*
 *	*                                                                		*
 *	*********************************************************************************
 */
session_start();
include 'config.php';
include 'opendb.php';
     $id        =$_SESSION['loginuserid'];

     $back    =mysql_real_escape_string($_POST['back']);
     //$radioph   =$_POST['radio'];                    
     $month   =mysql_real_escape_string($_POST['month']);                    
     $year    =mysql_real_escape_string($_POST['year']);                    
     $delete  =mysql_real_escape_string($_POST['delete']);                    

    $que  = "select name, department from userinfo where userid='$id'";
    $result = execute_query($que);

  	while($row = mysql_fetch_array($result))
       		{   
	   	$dbname   = $row['name'];
   	   	$dbdept   = $row['department'];
        	}		
	



	//******************* calldate from db *********************************************		    	

	//$month_year="/"."$month"."/"."$year";
	$monthyr= "select DISTINCT calldate from billtable";
	$myresult= execute_query($monthyr);
	$k=0;
//	$i=0;
 		$distmonth = array();
	while($row = mysql_fetch_array($myresult))
		{
		$calldate= $row['calldate'];	
		//list($months, $days, $year1)=split("/", $calldate);
		list($days, $months, $year1)=split("/", $calldate);
		//$months;
		list($years)=split(" ", $year1);

		$distyear[$k]=$years;
 		$distmonth[$k] = $months;
		$k=$k+1;
		}

		$umonth = array_unique($distmonth);
		asort($umonth);
		$valm=array_values($umonth);
		$countm=count($valm);


		$uyear = array_unique($distyear);
		asort($uyear);
		$valy=array_values($uyear);
		$county=count($valy);
	//************************************************************************	
	
	if($delete)
		{
		$delqry="delete from billtable where calldate like '%/$month/$year'";
		if(execute_query($delqry))
			{
			$delmsg="Bill of month $month and year $year has deleted";
			}
		else
			{
			$delmsg="Bill of month $month and year $year could not deleted";
			}

		}




	if($back)
		{
     		header("Location: adminaction.php");
    	    	}




	 	
?>

<html>
<head>
</head>
<body>
	
	<script language="javascript" type="text/javascript">
	function frmsubmit()
        	{
                document.delfrm.submit();
        	}

	</script>

<?if($_SESSION['loginuserid']!=1)
	{?>


	<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
  		<tr>
    		<td align="center" width="90%">
    		<font face="Bodoni MT Black" size="5" color="#008000">
		<img border="0" src="images/otbiitk_banner.gif" width="60%" height="60"></font>
    		</td>
  		</tr>
	</table>
	
	<hr>

	<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">
		<tr align="right" valign="middle">
   		<td class="txthdmenu"><div id='div_id'><?php include "headerlinks.php"; ?> 
        	<br></div></td></tr>
	</table><br>



	<form action="" name="delfrm" method="post"> 

	<table align="center" border="3" cellpadding="3" cellspacing="3" border-color=" #008000"  bgcolor="#fffff">

    	<tr><td width="100%" valign="top">
	<table align="center" border="0" cellpadding="3" cellspacing="3" border-color="#008000" width="70%" height="80%" >
 	
		<tr><td width="50%" colspan="2" align="right">
    		<font face="times new roman" size="3" color="#003399">WELCOME:</font>
		</td>

 		<td width="50%" colspan="2" align="left">
    		<font face="times new roman" size="3" color="#0000ff">   
		<?echo$_SESSION['loginuserid']?></font> 
		</td></tr>
		<tr><td colspan="6" align="center"> <b><font face="times new roman" size="4" color="#003399">DELETE BILL</font></b></td></tr>
		<tr><td colspan="6"><hr></td></tr>
		<?if($delmsg!="") 
			{?>
			<tr><td colspan="6" align="center">
			<font face="times new roman" color="003399"><?echo$delmsg?></font>
			</td></tr>
			<?}?>
		


        	<tr><td width="7%" align="left">
		<b><font face="Times New Roman" color="#00000">NAME:</font></b></td>
	
        	<td width="15%" align="left">
		<b><font face="Times New Roman" color="#00000" size="4"> </font>
		<input type="text" size="18" name="name" value="<?echo $dbname ?>" readonly></font></b></td>

		<td width="20%" align="right">
		<b><font face="Times New Roman" color="#00000">DEPARTMENT:</font></b></td>

		<td width="15%" align="left">
		<input type="text" name="dept" value="<?echo $dbdept ?>" size="18" readonly></font>
		</td></tr>


		<tr>
			<td ><p align="right"><font face="Britannic Bold" color="#FF0000">
                <b><font face="Times New Roman" color="#00000">YEAR:</font></b></td></font>
                <td>

                <select align="left" size="1" name="year" tabindex="3"  onChange="frmsubmit();">
                                <option><? print$year;?></option>
                        <?for($y=1;$y<=$county;$y++)
                                {?>
                                <option><? print$valy[$y];?></option>
                                <?}?>
                                </select></td>


		<td width="7%" align="left"><p align="left"><font face="Britannic Bold" color="#FF0000">
		<b><font face="Times New Roman" color="#00000">MONTH:</font></b></td></font>
        	<td ><p align="left">

        	<select align="left" size="1" name="month" tabindex="3" onChange="frmsubmit();">
        			<option><? print$month;?></option>
			<?for($m=1;$m<=$countm;$m++)
				{?>	
        			<option><? echo$valm[$m]; ?></option>
				<?}?>
        			</select></td>

	</tr>


	<tr>
	<td width="7%" align="left">&nbsp;
	</td>

	<td width="7%" align="right">
	<input type="submit" name="back" style="width:80px;" value="BACK">
	</td>


	<td width="7%" align="right">
	<input type="submit" name="delete" style="width:80px;" value="DELETE">
	</td>


	<td width="7%" align="left">&nbsp;
	</td>
	</tr>
	</table>

  </td>
  </tr>
  </table>
		<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">

			<tr align="center" valign="middle">
   			<td class="txthdmenu"><div id='div_id'><? include "footer.php"; ?> 
        		</div></td></tr>
		</table>

<?}?>
  </form>

</body>
</html>
