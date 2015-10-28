<?
/* @(#)viewregistaration.php
 *
 *  Copyright (c) 2007 Brihaspati Team/ETRG,IIT Kanpur.
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
 *  	*	This file will dispaly the details of user requesting for registration  *
 *  	*	approvement, or details of user approved already or detail of user     	*
 *      *       deleted by Administrator.                                      		*
 *      *                                                                		*
 *      *                                                                		*
 *  	*	@author<a href="raajkhurana@gmail.com">Raj Kumar</a>                   	*
 *	*                                                                		*
 *	*********************************************************************************
 */
session_start(); 
include "config.php";
include "opendb.php";
	$logged_userid=$_SESSION['loginuserid'];

	//************   Server code  *******************
 	$getid 	    = mysql_real_escape_string($_GET['viewid']);         
 	$submit	    = mysql_real_escape_string($_POST['submit']);
 	$name       = mysql_real_escape_string($_POST['name']);
 	$userid     = mysql_real_escape_string($_POST['userid']);
 	$occupation = mysql_real_escape_string($_POST['occupation']);
 	$department = mysql_real_escape_string($_POST['dept']);
 	$password   = mysql_real_escape_string($_POST['password']);  
 	$back       = mysql_real_escape_string($_POST['back']);  
	
/**********************************************
 	$getid 	    = $_GET['viewid'];         
 	$submit	    = $_POST['submit'];
 	$name       = $_POST['name'];
 	$userid     = $_POST['userid'];
 	$occupation = $_POST['occupation'];
 	$department = $_POST['dept'];
 	$password   = $_POST['password'];  
 	$back       = $_POST['back'];  
******************************************************/
	if($getid)
 	{
           $editinfo="select * from userinfo where userid='$getid'";
	   $resultinfo=execute_query($editinfo);
	   while($row=mysql_fetch_array($resultinfo))
	      {
		$dbname             =$row['name'];	
		$dboccupation       =$row['occupation'];	
		$dbdepartment       =$row['department'];	
		$dbpassword         =$row['password'];	
		$dbaddress          =$row['address'];     
		$dbhint_answer      =$row['hint_answer'];	
		$dbpassword_question=$row['password_question'];	
               }

   	}

  if($getid!=null)
       {

           $editphone="select * from paddress where usrid='$getid'";
	   $resultphone=execute_query($editphone);
	   while($row=mysql_fetch_array($resultphone))
		{
		 $dbno_ph  =$row['ph'];
		 $dbph[]=$row['phone_number'];

		}

         }
     
//************************************************************************** 
   if($back)
	{
	   	header("Location: adminaction.php");
	}


//*****************************************************************************


?>      

<html>
<?if($_SESSION['loginuserid']!=1)
	{?>
<head>


	<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
  		<tr>
    		<td align="center" width="90%">
    		<font face="Bodoni MT Black" size="5" color="#008000">
		<img border="0" src="images/otbiitk_banner.gif" width="60%" height="60"></font>
    		</td>
  		</tr>
	</table>

	

</head>
<hr>


	<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">
		<tr align="right" valign="middle">
   		<td class="txthdmenu"><div id='div_id'><?php include "headerlinks.php"; ?> 
        	<br></div></td></tr>
	</table><br>








<form action="" name="" method="post">

<table align="center" border="1" cellpadding="1" cellspacing="1" width="65%" bgcolor="#fffff">


   <tr><td>

   <table align="center" border="0" cellpadding="3" cellspacing="3" width="100%" >


   	<p>&nbsp;</p>
 	<tr><td width="33%"> <b>
 	<font color="#0000" face="times new roman">NAME</font>
    	<font color="RED" face="times new roman">*</font></b></td>
 	<td width="60" align="left"><font face="times new roman" color="#ff0000"><b>
 	<input type="text" size="30" name="name" value="<?echo$dbname?>" readonly></b></font></td></tr>


   	<tr><td width="33%" align="left">
	<!--<font face="times new roman" color="#ff0000">-->
	<b><font face="times new roman" color="#0000">DEPARTMENT</font></b></td>
   	<td><font face="times new roman" color="#ff0000"><b>
   	<input type="text" size="30" name="dept" value="<? echo $dbdepartment?>" readonly></b></font></td></tr>


    	<tr><td width="33%" align="left">
	<b><font face="times new roman" color="#0000">DESIGNATION &nbsp;</font></b></td>
    	<td><font face="times new roman" color="#ff0000"><b>
    	<input type="text" size="30" name="occupation" value="<?echo $dboccupation?>" readonly></b></font></td></tr>


    	<tr><td width="33%" align="left">
    	<b><font color="#0000" face="times new roman">NUMBER OF PHONES</font>
    	<font color="RED" face="times new roman">*</font></b></td>
    	<td><font face="times new roman" color="#ff0000"><b>
    	<input type="text" size="2" name="no-of-phones" value="<?echo$dbno_ph?>" readonly></b></font>&nbsp; </td></tr>




    	<tr><td width="33%" align="left">
	<b><font face="times new roman" color="#0000">TELEPHONE NUMBERS</font>    
    	<font color="RED" face="times new roman">*</font></b></td>
	<td>


	
		<table align="left" border="0" cellpadding="0" cellspacing="2" width="65%" height="50%" id="autonumber1">

		<?

		switch($dbno_ph)
		 	{
			case 1:
    				echo"Phone1:<input type='text' size='13' name='phone1' value='$dbph[0]' readonly>";
				break;

			case 2:
    				echo"Phone1:<input type='text' size='13' name='phone1' value='$dbph[0]' readonly><br>";
    				echo"Phone2:<input type='text' size='13' name='phone2' value='$dbph[1]' readonly>";
				break;

			case 3:
    				echo"Phone1:<input type='text' size='13' name='phone1' value='$dbph[0]' readonly><br>";
    				echo"Phone2:<input type='text' size='13' name='phone2' value='$dbph[1]' readonly><br>";
    				echo"Phone3:<input type='text' size='13' name='phone3' value='$dbph[2]' readonly>";
				break;

			case 4:
    				echo"Phone1:<input type='text' size='13' name='phone1' value='$dbph[0]' readonly><br>";
    				echo"Phone2:<input type='text' size='13' name='phone2' value='$dbph[1]' readonly><br>";
    				echo"Phone3:<input type='text' size='13' name='phone3' value='$dbph[2]' readonly><br>";
    				echo"Phone4:<input type='text' size='13' name='phone4' value='$dbph[3]' readonly>";
				break;

			case 5:
    				echo"Phone1:<input type='text' size='13' name='phone1' value='$dbph[0]' readonly><br>";
    				echo"Phone2:<input type='text' size='13' name='phone2' value='$dbph[1]' readonly><br>";
    				echo"Phone3:<input type='text' size='13' name='phone3' value='$dbph[2]' readonly><br>";
    				echo"Phone4:<input type='text' size='13' name='phone4' value='$dbph[3]' readonly><br>";
    				echo"Phone5:<input type='text' size='13' name='phone5' value='$dbph[4]' readonly>";

				break;

		
			
			}

			?>


      		</table>
	</td></tr>




    	<tr><td width="33%" align="left">
	<b><font face="times new roman" color="#0000">ADDRESS</font></b></td>
    	<td><textarea rows="5" name="address" cols="24" readonly><?echo $dbaddress;?></textarea></td></tr>


    	<tr><td width="33%" align="left">
	<b><font face="times new roman" color="#0000">USER ID</font>
    	<font color="RED" face="times new roman">*</font></b></td>
 	<td><font face="times new roman" color="#ff0000"><b>
    	<input type="text" size="30" name="userid" value="<? echo $getid?>" readonly></b></font> </td></tr>


    	<tr><td width="33%" align="left">
	<b><font face="times new roman" color="#0000">PASSWORD</font>
    	<font color="RED" face="times new roman">*</font></b></td>
    	<td><font face="times new roman" color="#ff0000"><b>
    	<input type="password" size="30" name="password" value="<? echo $dbpassword?>" readonly></b></font></td></tr>

    	<tr><td width="30%" align="left">
	<font face="times new roman" color="#0000"><b>QUESTION</b></td>

    		<td width="70%" align="left"><select size="1" name="password-question">
    		<option selected><?echo$dbpassword_question?></option>

    	</select>&nbsp;<font size="2">&nbsp;&nbsp;&nbsp;&nbsp; </font>
    	<font size="2" face="times new roman" color="#008000">(In case forgot password ! )
    	</font></td></tr>

    	<tr><td width="30%" align="left">
	<font face="times new roman" color="#0000"><b>HINT ANSWER</b></font>
    	<b><font color="RED" face="times new roman">*</font></b></td>
    	<td><input type="text" size="30" name="hint-ans" value="<?echo$dbhint_answer?>" readonly></td></tr>

    	<tr><td width="30%" align="left">
	<font face="times new roman" color="#800080">&nbsp;</td>



    <td>
	<br>

	<table align=center" width="44%">
	<tr><td>
    	<input type="submit" value="GO   BACK " name="back">
	</td>

	<td>&nbsp;		
	</td></tr>		
	</table>

    </td></tr>
    </tbody>
    </table><br>


</tr>
</table>


		<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">


			<tr align="center" valign="middle">
   			<td class="txthdmenu"><div id='div_id'><? include "footer.php"; ?> 
        		</div></td></tr>
			</table>
			
</form>
</body>
<?}?>
</html>
