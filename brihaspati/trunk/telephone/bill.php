<?
/* @(#)bill.php
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
 *  	*	It will display total sum of bill and total sum of all telephone      	*
 *      *       numbers as well.                                                	*
 *      *                                                                		*
 *      *                                                                		*
 *  	*	@author<a href="raajkhurana@gmail.com">Raj Kumar</a>                   	*
 *  	*	@author<a href="nksinghiitk@gmail.com">Nagendra Kumar Singh</a>        	*
 *	*                                                                		*
 *	*********************************************************************************
 */
//session_start();
if(!isset($_SESSION))
{
session_start();
}
include 'config.php';
include 'opendb.php';
     //mysql_real_escape_string
     $id        =$_SESSION['loginuserid'];

     $back      =mysql_real_escape_string($_POST['backpage']);
     $billdetail=mysql_real_escape_string($_POST['billdetail']);
     $radioph   =mysql_real_escape_string($_POST['radio']);                    
     $month     =mysql_real_escape_string($_POST['month']);                    
     $year      =mysql_real_escape_string($_POST['year']);                    


    $que  = "select name, department, phone_number, ph from userinfo, paddress where ((userid='$id') && (usrid='$id'))";
    $result = execute_query($que);

  	while($row = mysql_fetch_array($result))
       		{   
	   	$dbname   = $row['name'];
	   	$dbphone[]= $row['phone_number'];
   	   	$dbdept   = $row['department'];
		$no_of_ph = $row['ph'];
        	}		

	//******************* calldate from db *********************************************		    	
/*	function findmonthyear()
	{
*/
	$monthyr= "select distinct calldate from billtable";
	$myresult= execute_query($monthyr);
		$k=0;
 		$distmonth = array();
	while($row = mysql_fetch_array($myresult))
		{
		$calldate= $row['calldate'];	
		list($days, $months, $year1)=split("/", $calldate);
		$months;
		list($years)=split(" ", $year1);

		$distyear[$k]=$years;
 		$distmonth[$k] = $months;
		$k=$k+1;
		}
//getting all month list
		$umonth = array_unique($distmonth);
		asort($umonth);
		$valm=array_values($umonth);
		$countm=count($valm);
//getting year list
		$uyear = array_unique($distyear);
		asort($uyear);
		$valy=array_values($uyear);
		$county=count($valy);
		//echo"nksinghiitk@gmail.com";	
//getting year specific month list
	//	if($year!=" "){
		$monthyrsp= "select distinct calldate from billtable where calldate like '%/$year'";
	        $myresultsp= execute_query($monthyrsp);
		$kk=0;
		$distmonth1 = array();
		while($row = mysql_fetch_array($myresultsp)){
			$calldate= $row['calldate'];
        	        list($days, $months, $year1)=split("/", $calldate);
	                $months;
			$distmonth1[$kk] = $months;
			$kk=$kk+1;
		}

		$umonth = array_unique($distmonth1);
                asort($umonth);
                $valm=array_values($umonth);
                $countm=count($valm);
	//	}
/*	}
	findmonthyear();
*/
	//******************To Reduce into extention (last four digits) format ************** 

   for($j=0;$j<$no_of_ph; $j++)	{
	$array  = array(); 
	$extn_no=$dbphone[$j];
	for($i  = strlen($extn_no)-4; $i < strlen($extn_no); $i++) { 
 		$array[] = $extn_no[$i];
		}

	$extn[]=($array[0].$array[1].$array[2].$array[3]."\n");

	//******************* calldate from db *********************************************		    	
	$ttime= "select calldate from billtable where extn_number=$extn[$j] and calldate like '%/$month/$year'";
	$timeis= execute_query($ttime);
	while($row = mysql_fetch_array($timeis))
		{
		$calldate= $row['calldate'];	
		list($days, $months, $year1)=split("/", $calldate);

		list($years)=split(" ", $year1);
		$years;
		}

		if($years==$year) 
		{
		$time1="select duration, cost from billtable where extn_number=$extn[$j] and calldate like '%$month/$year'";
			$time2  = execute_query($time1);
			while($row = mysql_fetch_array($time2))	{
				$duration=$row['duration'];
				$cost=$row['cost'];
				list($hours, $minuts, $seconds)=split(":", $duration);
				$hour=$hours;
				$minut=$minuts;
				$second=$seconds;
				$hours_sum[$j]+=$hours;
				$minuts_sum[$j]+=$minuts;
				$seconds_sum[$j]+=$seconds;
				$tcost[$j]+=$cost;
			}
		}
		$seconds_sum[$j]."\n";
		$minuts_sum[$j]."\n";
		$hours_sum[$j]."\n";
		$s2m=($seconds_sum[$j]/60)."\n"; 
		$tseconds[$j] =$seconds_sum[$j]%60;
		$minuts_sum[$j]+=(int)$s2m;
		$m2h=$minuts_sum[$j]/60;
		$tminuts[$j]=$minuts_sum[$j]%60;
		$thours[$j] =$hours_sum[$j]+(int)$m2h;
	}

	//*************************************************
		


	if($back)
		{
		if($id=='sanchar@iitk.ac.in')
    	   		{
     			header("Location: pwdsuccess.php");
    	    		}
		else
			{
     			header("Location: index.php");
    	    		}

		}




     	elseif($billdetail) 
	{  
		if($radioph!="")
		{
	     	header("Location: billdetail.php?userid=$id&radio=$radioph&month=$month&year=$year&gtotal=$grandcost");

		}
			
	}
	 	
?>

<html>
<head>
</head>
<body>
	
	<script language="javascript" type="text/javascript">
	function frmsubmit()
        	{
                document.frm1.submit();
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



	<form action="" name="frm1" method="post"> 
	<div align="center">
	<table align="center" border="3" cellpadding="3" cellspacing="3" border-color: #008000" 
	width="65%" height="70%" bgcolor="#fffff">

    	<tr><td width="100%">
	<table align="center" border="0" cellpadding="3" cellspacing="3" border-color: #008000" 
	width="75%" >
 	
		<tr><td width="50%" colspan="2" align="right">
    		<font face="times new roman" size="3" color="#003399">WELCOME:</font>
		</td>

 		<td width="50%" colspan="2" align="left">
    		<font face="times new roman" size="3" color="#0000ff">   
		<?echo$_SESSION['loginuserid']?></font> 
		</td></tr>
		<tr><td colspan="4" align="center"> <b><font face="times new roman" size="4" color="#003399">BRIEF BILL</font></b></td></tr>
		

 	<tr><td width="100%" colspan="4" align="center">




     	<?	if($billdetail) 
	   		{  
		  	if(!$radioph)
				{
			    	?>
    					<font face="times new roman" color="#0000ff" size="3" >
					Please select any phone first..</font> 
	      		    	<?
				}
				

	    		}  
	
	?>




	</td></tr>

 	



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
	
	<td width="7%" align="left"><p align="left"><font face="Britannic Bold" color="#FF0000">
	<b><font face="Times New Roman" color="#00000">YEAR:</font></b></td></font>

                <td><p align="left">
                <select align="left" size="1" name="year" tabindex="3"  onChange="frmsubmit();">
                                <option><? print$year;?></option>
                                <?for($y=1;$y<=$county;$y++)
                                {?>
                                <option><? print$valy[$y];?></option>
                                <?}?>
                </select></td>

		<td ><p align="right"><font face="Britannic Bold" color="#FF0000">

		<b><font face="Times New Roman" color="#00000">MONTH:</font></b></td></font>

                <td ><p align="left">
                <select align="left" size="1" name="month" tabindex="3" onChange="frmsubmit();">
                                <option><? print$month; ?></option>
                                <?for($m=0;$m<$countm;$m++)
                                        {?>
                                        <option><? print$valm[$m]; ?></option>
                                        <?}
                                ?>
                </select></td>


	</table><hr>

  <table align="center" border="2" cellpadding="3" cellspacing="3" width="80%" >

      	<tr>
	<td width="5%" align="center">&nbsp;</td>
	<td width="26%"><p align="center"><font face="Britannic Bold" color="#FF0000">
	<b><font face="Times New Roman" color="#003399">PHONE NO.</font></b></td>
	<td width="30%"><p align="center"><font face="Britannic Bold" color="#FF0000">
	<b><font face="Times New Roman" color="#003399">TOTAL TIME</font></b></td>
	<td width="39%"><p align="center"><font face="Britannic Bold" color="#FF0000">
	<b><font face="Times New Roman" color="#003399"> AMOUNT(IN RUPEES)</font></b></td>
	</tr>


<?
for($r=0;$r<$no_of_ph;$r++)
	{
		$ttimeph[$r]=$thours[$r].":".$tminuts[$r].":".$tseconds[$r];
		$grandcost+=$tcost[$r];
	?>		

      	<tr>
<?	if ($r==0){   ?>
	<td width="5%"><input type="radio" name="radio" value="<? echo $dbphone[$r]; ?>" checked=true ></td> 
<? }else{?>
	<td width="5%"><input type="radio" name="radio" value="<? echo $dbphone[$r]; ?>" ></td> 
<?}?>
        <td width="26%"><p align="center"><font face="Britannic Bold" color="#FF0000">
        <input type="text" name="phone1" size="16" value="<? echo $dbphone[$r] ?>" readonly></font></td>  
        <td width="30%"><p align="center"><font face="Britannic Bold" color="#FF0000">
        <input type="text" name="ttime1" size="15" value="<?echo$ttimeph[$r] ?>" readonly></font></td> 

        <td width="39%"><p align="center"><font face="Britannic Bold" color="#FF0000">
        <input type="text" name="cost0" size="18" value="<?echo $tcost[$r];?>" readonly></font></td>                     
      </tr>

	<? }?>


   </table>



	<table align="center" border="0" cellpadding="3" cellspacing="3" border-color: #008000" width="80%" >

        <tr><td width="5%">&nbsp;</td>
        <td width="26%">&nbsp;</td>
            <td width="30%" align="right">
	    <font face="Times New Roman" color="#003399"><b> TOTAL AMOUNT:</b></font></td>


            <td width="39%" align="center" colspan="2">
	    <input type="text" name="gtotal" value="<?echo $grandcost?>"  size="18" readonly>
	</tr>	


        <tr>
        <td width="5%">&nbsp;</td>
	<td  align="left" colspan="2">&nbsp;</td>
        <td width="39%">&nbsp;</td>
	</tr>	


        <tr>
        <td width="5%">&nbsp;</td>

        <td width="26%" align="center" >
	<a href="changepassword.php?uid=<?echo$loginid?>">Change Password </a></td>
	</td>

        <td width="30%" align="center">
	<input type="submit" style="width:80px; "value="BACK" name="backpage" >
	</td>

        <td width="39%" align="left" >
	<input type="submit" style="width:80px; "value="DETAIL" name="billdetail">
	</td>

	</tr>	

        
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

  </div>
<?}?>
  </form>

</body>
</html>
