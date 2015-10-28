<?
/* @(#)editregistaration.php
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
 *  	*	This is for displaying user particulars filled at registration time.    *
 *  	*	Administrator can edit these particular by this file code.       	*
 *      *           			                                      		*
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
 	$back       = $_POST['back'];  
	$updateerro_msg="";
 	//$getid 	   = $_GET['updateid'];         
 	$getid 	   = mysql_real_escape_string($_GET['updateid']);         

//****************************    Server Code   *****************************
 	$submit	   = mysql_real_escape_string($_POST['submit']);
 	$name      = mysql_real_escape_string($_POST['name']);
 	$userid    = mysql_real_escape_string($_POST['userid']);
 	$occupation= mysql_real_escape_string($_POST['occupation']);
 	$department= mysql_real_escape_string($_POST['dept']);
 	$password  = mysql_real_escape_string($_POST['password']);

 	$address       = mysql_real_escape_string($_POST['address']);
 	$no_of_phones  = mysql_real_escape_string($_POST['no-of-ph']);
 	$phone_array[0]= mysql_real_escape_string($_POST['Phone1']);
 	$phone_array[1]= mysql_real_escape_string($_POST['Phone2']);
 	$phone_array[2]= mysql_real_escape_string($_POST['Phone3']);
 	$phone_array[3]= mysql_real_escape_string($_POST['Phone4']);
 	$phone_array[4]= mysql_real_escape_string($_POST['Phone5']);
 	$hint_ans      = mysql_real_escape_string($_POST['hint-ans']);
 	$u_id          = mysql_real_escape_string($_POST['userid']);
 	$password_question = mysql_real_escape_string($_POST['password-question']);
 	$p;

/***************************************************************************


 	$submit	   = $_POST['submit'];
 	$name      = $_POST['name'];
 	$userid    = $_POST['userid'];
 	$occupation= $_POST['occupation'];
 	$department= $_POST['dept'];
 	$password  = $_POST['password'];

 	$address       = $_POST['address'];
 	$no_of_phones  = $_POST['no-of-ph'];
 	$phone_array[0]= $_POST['Phone1'];
 	$phone_array[1]= $_POST['Phone2'];
 	$phone_array[2]= $_POST['Phone3'];
 	$phone_array[3]= $_POST['Phone4'];
 	$phone_array[4]= $_POST['Phone5'];
 	$hint_ans      = $_POST['hint-ans'];
 	$u_id          = $_POST['userid'];
 	$password_question = $_POST['password-question'];
*********************************************************/


$selectph="select phone_number from paddress where usrid='$getid'";
if($result=execute_query($selectph)){
    		while($row = mysql_fetch_array($result))
     	  		{    
			$dbphones[] = $row['phone_number'];
			}
	}



if($submit)
{
if(($name==null) || ($userid==null) || ($hint_ans==null) || ($password==null)) 
 {
	header("Location: updateerror.php?updateid=$getid");  
	exit;
 }


else
{
$sql1="update userinfo set name='$name', occupation='$occupation', department='$department', address='$address', password_question='$password_question', hint_answer='$hint_ans', password='$password' where userid='$getid'";
  


	if(execute_query($sql1)) 	  
    		{
       		for($p=0; $p<$no_of_phones; $p++)
         		{
	   	  	$sql2="update paddress set ph='$no_of_phones', phone_number='$phone_array[$p]' where phone_number='$dbphones[$p]'";
				if(strlen($phone_array[$p])==0){
					$insertph=execute_query($sql2);
				}
				else{
 					if(strlen($phone_array[$p])>3)
					{
						$insertph=execute_query($sql2);
					}
					else
					{	
						$updateerror_msg="Telephone number '$phone_array[$p]' should have more than 4 digits";
  					}	
				}

			}
			
			$send2="$getid";
                        $subject = "Registration updateion for telephone billing system";
                        $message = "Dear Sir,<br>Your registartion has been updated.<br> Thank you for using this system.<br> For any query, please contact<br>  <br> Telephone Incharge <br>sanchar@iitk.ac.in";
                        $header="sanchar@iitk.ac.in";
                        mail($send2, $subject, $message, $header);
	 		header("location: updatesuccess.php");
			
		}	


           }

     }
		
//*********************************************************
//*********************************************************


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
<head>

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

	

</head>
<hr>


	<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">
		<tr align="right" valign="middle">
   		<td class="txthdmenu"><div id='div_id'><?php include "headerlinks.php"; ?> 
        	<br></div></td></tr>
	</table><br>








<form action="" name="" method="post">

<table align="center" border="1" cellpadding="1" cellspacing="1" width="65%" bgcolor="#fffff">



 	<tr><td align="center" colspan="2"><font face="times new noman" size="4" color="003399">
	<b>UPDATE  FORM</b></font></td></tr>
	
	<?if($updateerror_msg!="")
	{?>
 	<tr><td align="center" colspan="2"><font face="times new noman" size="4" color="003399">
	<?echo $updateerror_msg;?></font></td></tr> 
	<?}?>
	
   <tr><td>
   <table align="center" border="0" cellpadding="3" cellspacing="3" width="100%" >


 	<tr><td width="33%"> <b>
 	<font color="#0000" face="times new roman">NAME</font>
    	<font color="RED" face="times new roman">*</font></b></td>
 	<td width="60" align="left"><font face="times new roman" color="#ff0000"><b>
 	<input type="text" size="30" name="name" value="<?print$dbname?>" ></b></font></td></tr>


   	<tr><td width="33%" align="left">
	<!--<font face="times new roman" color="#ff0000">-->
	<b><font face="times new roman" color="#0000">DEPARTMENT</font></b></td>
   	<td><font face="times new roman" color="#ff0000"><b>
   	<input type="text" size="30" name="dept" value="<? print $dbdepartment?>" ></b></font></td></tr>


    	<tr><td width="33%" align="left">
	<b><font face="times new roman" color="#0000">OCCUPATION&nbsp;</font></b></td>
    	<td><font face="times new roman" color="#ff0000"><b>
    	<input type="text" size="30" name="occupation" value="<?print $dboccupation?>" ></b></font></td></tr>


    	<tr><td width="33%" align="left">
    	<b><font color="#0000" face="times new roman">NUMBER OF PHONES</font>
    	<font color="RED" face="times new roman">*</font></b></td>
    	<td><font face="times new roman" color="#ff0000"><b>
    	<input type="text" size="2" name="no-of-ph" value="<?print$dbno_ph?>" readonly></b></font>&nbsp; </td></tr>




    	<tr><td width="33%" align="left">
	<b><font face="times new roman" color="#0000">TELEPHONE NUMBERS</font>    
    	<font color="RED" face="times new roman">*</font></b></td>
	<td>


	
		<table align="left" border="1" cellpadding="0" cellspacing="2" width="65%" height="50%" id="autonumber1">

		<?

		switch($dbno_ph)
		 	{
			case 1:
    				print"Phone1:<input type='text' size='13' name='Phone1' value='$dbph[0]'>";
				break;

			case 2:
    				print"Phone1:<input type='text' size='13' name='Phone1' value='$dbph[0]'><br>";
    				print"Phone2:<input type='text' size='13' name='Phone2' value='$dbph[1]'>";
				break;

			case 3:
    				print"Phone1:<input type='text' size='13' name='Phone1' value='$dbph[0]'><br>";
    				print"Phone2:<input type='text' size='13' name='Phone2' value='$dbph[1]'><br>";
    				print"Phone3:<input type='text' size='13' name='Phone3' value='$dbph[2]'>";
				break;

			case 4:
    				print"Phone1:<input type='text' size='13' name='Phone1' value='$dbph[0]'><br>";
    				print"Phone2:<input type='text' size='13' name='Phone2' value='$dbph[1]'><br>";
    				print"Phone3:<input type='text' size='13' name='Phone3' value='$dbph[2]'><br>";
    				print"Phone4:<input type='text' size='13' name='Phone4' value='$dbph[3]'>";
				break;

			case 5:
    				print"Phone1:<input type='text' size='13' name='Phone1' value='$dbph[0]'><br>";
    				print"Phone2:<input type='text' size='13' name='Phone2' value='$dbph[1]'><br>";
    				print"Phone3:<input type='text' size='13' name='Phone3' value='$dbph[2]'><br>";
    				print"Phone4:<input type='text' size='13' name='Phone4' value='$dbph[3]'><br>";
    				print"Phone5:<input type='text' size='13' name='Phone5' value='$dbph[4]'>";

				break;

		
			
			}

			?>


      		</table>
	</td></tr>




    	<tr><td width="33%" align="left">
	<b><font face="times new roman" color="#0000">ADDRESS</font></b></td>
    	<td><textarea rows="3" name="address" cols="23"><?print $dbaddress;?></textarea></td></tr>


    	<tr><td width="33%" align="left">
	<b><font face="times new roman" color="#0000">USER ID</font>
    	<font color="RED" face="times new roman">*</font></b></td>
 	<td><font face="times new roman" color="#ff0000"><b>
    	<input type="text" size="30" name="userid" value="<? echo$getid?>" readonly></b></font> </td></tr>


    	<tr><td width="33%" align="left">
	<b><font face="times new roman" color="#0000">PASSWORD</font>
    	<font color="RED" face="times new roman">*</font></b></td>
    	<td><font face="times new roman" color="#ff0000"><b>
    	<input type="password" size="30" name="password" value="<? print $dbpassword?>" ></b></font></td></tr>

    	<tr><td width="30%" align="left">
	<font face="times new roman" color="#0000"><b>QUESTION</b></td>

    		<td width="70%" align="left"><select size="1" name="password-question">
    		<option selected><?print$dbpassword_question?></option>
    		<option selected>Select your choice</option>
		<option>What is your pet's name?</option>
                <option>Who is your childhood hero?</option>
                <option>Your favorite past time?</option>
                <option>Your favorite sports team?</option>
                <option>Your first school name?</option>
                <option>Your father's middle name?</option>
                <option>Your high school mascot?</option>
                <option>Your first bike or car?</option>
                <option>Your best friend name?</option>
                <option>Your favorite magazine?</option>
                <option>Your favorite movie?</option>
                <option>Your favorite food?</option>
                <option>Your favorite sport?</option>

    	</select>&nbsp;<font size="2">&nbsp;&nbsp;&nbsp;&nbsp; </font>
    	<font size="2" face="times new roman" color="#008000">(In case forgot password ! )
    	</font></td></tr>

    	<tr><td width="30%" align="left">
	<font face="times new roman" color="#0000"><b>HINT ANSWER</b></font>
    	<b><font color="RED" face="times new roman">*</font></b></td>
    	<td><input type="text" size="30" name="hint-ans" value="<?print$dbhint_answer?>"></td></tr>

    	<tr><td width="30%" align="left">
	<font face="times new roman" color="#800080">&nbsp;</td>



    	<td>


	<br>
	<br>

	<table align=center" width="55%">
	<tr><td>
    	<input type="submit" value="GO  BACK" name="back">
	</td>

	<td>		
    	<input type="submit" value=" SUBMIT " name="submit">
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
<?}?>
</body>
</html>
