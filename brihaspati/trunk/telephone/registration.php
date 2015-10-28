<?
/* @(#)registration.php
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
 *  	*	This dispaly the fields for entering the particulars by user.		*	                            *      *       Mandatoty fields are must for successfull registration		   *
 *      *      								         	*
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
	//*************** Server Code   ********************
 	$submit	   = mysql_real_escape_string($_POST['submit']);
 	$name      = mysql_real_escape_string($_POST['name']);
 	$userid    = mysql_real_escape_string($_POST['userid']);
 	$occupation= mysql_real_escape_string($_POST['occupation']);
 	$department= mysql_real_escape_string($_POST['dept']);
 	$password  = mysql_real_escape_string($_POST['password']);


 	$address       = mysql_real_escape_string($_POST['address']);
 	$no_of_phones  = mysql_real_escape_string($_POST['no_of_ph']);
 	$phone_array[0]= mysql_real_escape_string($_POST['Phone1']);
 	$phone_array[1]= mysql_real_escape_string($_POST['Phone2']);
 	$phone_array[2]= mysql_real_escape_string($_POST['Phone3']);
 	$phone_array[3]= mysql_real_escape_string($_POST['Phone4']);
 	$phone_array[4]= mysql_real_escape_string($_POST['Phone5']);
 	$hint_ans      = mysql_real_escape_string($_POST['hint-ans']);
 	$u_id          = mysql_real_escape_string($_POST['userid']);
 	$password_question = mysql_real_escape_string($_POST['password-question']);
 	$p;
	
//	  return $result;
	/*
	$logged_userid=$_SESSION['loginuserid'];
 	$submit	   = $_POST['submit'];
 	$name      = $_POST['name'];
 	$userid    = $_POST['userid'];
 	$occupation= $_POST['occupation'];
 	$department= $_POST['dept'];
 	$password  = $_POST['password'];

 	$address       = $_POST['address'];
 	$no_of_phones  = $_POST['no_of_ph'];
 	$phone_array[0]= $_POST['Phone1'];
 	$phone_array[1]= $_POST['Phone2'];
 	$phone_array[2]= $_POST['Phone3'];
 	$phone_array[3]= $_POST['Phone4'];
 	$phone_array[4]= $_POST['Phone5'];
 	$hint_ans      = $_POST['hint-ans'];
 	$u_id          = $_POST['userid'];
 	$password_question = $_POST['password-question'];
 	$p;
	*/



if($submit)
{
	if(($name==null) || ($userid==null) || ($hint_ans==null) || ($password==null))
     		{
		header("Location: regiserror.php");  
		exit;
      		}

else
	{   
  	if(!eregi("^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$", $userid)) {
		$u_id=$u_id."@iitk.ac.in";
		$userid=$userid."@iitk.ac.in";
  	}
	$sql1="insert into userinfo (userid, name, password, occupation, department, address, password_question, hint_answer) values ('$userid', '$name', '$password', '$occupation', '$department', '$address', '$password_question', '$hint_ans')";
  


	if(execute_query($sql1)) 	  
     	 	{
       		for($p=0; $p<$no_of_phones; $p++)
         		{
	   	  	$sql2="insert into paddress (usrid, ph, phone_number) values ('$u_id', '$no_of_phones', '$phone_array[$p]')";

 			if(strlen($phone_array[$p])>3)
				{
				$insertph=execute_query($sql2);
				}

                	else
                   	  	{ 
				$delsql1="delete from userinfo where userid='$userid'";
                        	execute_query($delsql1);

                     		for($i=$p-1;$i>-1;$i--)
                      		   	{
                        		$delsql2="delete from paddress where phone_number='$phone_array[$i]'";
                        		execute_query($delsql2);

                       		     	}
					$less_phlength="Phone should have more than 4 digis unlike phone $phone_array[$p]";
                    	     	}

               
                	}
		if($less_phlength==null)
		{
	 	header("location: waitapproval.php");
		//echo"Raaj khurana";
		}
		else{}
           	}
	else{
	 	header("location: errorReg.php");
		}
     }


 }
?>      





<html>
<head>


	<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
  		<tr>
    		<td align="center" width="90%">
    		<font face="Bodoni MT Black" size="5" color="#008000">
		<img border="0" src="images/otbiitk_banner.gif" width="60%" height="60"></marquee></font>
    		</td>
  		</tr>
	</table>

	
<hr>


</head>



	<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">
		<tr align="right" valign="middle">
   		<td class="txthdmenu"><div id='div_id'><?php include "headerlinks.php"; ?> 
        	<br></div></td></tr>
	</table><br>





	<body>






	<script language="javascript" type="text/javascript">
	function checkname(entered, alertbox)
	   	{
		with (entered)
			{
 		  	var noalpha = /^[0-9]*$|,\$|,|@|#|~|`|\%|\*|\^|\&|\(|\)|\+|\=|\[|\-|\_|\]|\[|\}|\{|\;|\:|\'|\"|\<|\>|\?|\||\\|\!|\$/;     
			if (noalpha.test(document.regisform.name.value)) 
				{
				if (alertbox) 
					{
					alert(alertbox);
					}
					return false;
				}


 			else
 				{
 				return true;
 				}

			}


		}







	function checkdept(entered, alertbox)
		{
		with (entered)
			{
 			var noalpha = /^[0-9]*$|,\$|,|@|#|~|`|\%|\*|\^|\(|\)|\+|\=|\[|\-|\_|\]|\[|\}|\{|\;|\:|\'|\"|\<|\>|\?|\||\\|\!|\$/;     
			if (noalpha.test(document.regisform.dept.value)) 
				{
				if (alertbox) 
					{
					alert(alertbox);
					}
					return false;
				}


 			else
 				{
 				return true;
 				}

			}

		}






	function checkoccup(entered, alertbox)
		{
		with (entered)
			{
 			var noalpha = /^[0-9]*$|,\$|,|@|#|~|`|\%|\*|\^|\&|\(|\)|\+|\=|\[|\-|\_|\]|\[|\}|\{|\;|\:|\'|\"|\<|\>|\?|\||\\|\!|\$|\./;
 			if (noalpha.test(document.regisform.occupation.value)) 
				{
				if (alertbox) 
					{
					alert(alertbox);
					}
					return false;
				}

 			else
 				{
 				return true;
 				}

			}

		}









	function checkphnum1(entered, alertbox)
		{
		with (entered)
			{
 			var noalpha = /^[A-Z]|[a-z]|\$|,|@|#|~|`|\%|\*|\^|\&|\(|\)|\+|\=|\[|\-|\_|\]|\[|\}|\{|\;|\:|\'|\"|\<|\>|\?|\||\\|\!|\$|\./;
 			if (noalpha.test(document.regisform.Phone1.value)) 
				{
				if (alertbox) 
					{
					alert(alertbox);
					}
					return false;
				}
			else
				{
				return true;
				}
			}
	
		}





	function checkphnum2(entered, alertbox)
		{
		with (entered)
			{
 			var noalpha = /^[A-Z]|[a-z]|\$|,|@|#|~|`|\%|\*|\^|\&|\(|\)|\+|\=|\[|\-|\_|\]|\[|\}|\{|\;|\:|\'|\"|\<|\>|\?|\||\\|\!|\$|\./;
 			if (noalpha.test(document.regisform.Phone2.value)) 
				{
				if (alertbox) 
					{
					alert(alertbox);
					}
					return false;
				}
			else
				{
				return true;
				}
			}
	
		}







	function checkphnum3(entered, alertbox)
		{
		with (entered)
			{
 			var noalpha = /^[A-Z]|[a-z]|\$|,|@|#|~|`|\%|\*|\^|\&|\(|\)|\+|\=|\[|\-|\_|\]|\[|\}|\{|\;|\:|\'|\"|\<|\>|\?|\||\\|\!|\$|\./;
 			if (noalpha.test(document.regisform.Phone3.value)) 
				{
				if (alertbox) 
					{
					alert(alertbox);
					}
					return false;
				}
			else
				{
				return true;
				}
			}
	
		}






	function checkphnum4(entered, alertbox)
		{
		with (entered)
			{
 			var noalpha = /^[A-Z]|[a-z]|\$|,|@|#|~|`|\%|\*|\^|\&|\(|\)|\+|\=|\[|\-|\_|\]|\[|\}|\{|\;|\:|\'|\"|\<|\>|\?|\||\\|\!|\$|\./;
 			if (noalpha.test(document.regisform.Phone4.value)) 
				{
				if (alertbox) 
					{
					alert(alertbox);
					}
					return false;
				}
			else
				{
				return true;
				}
			}
	
		}





	function checkphnum5(entered, alertbox)
		{
		with (entered)
			{
 			var noalpha = /^[A-Z]|[a-z]|\$|,|@|#|~|`|\%|\*|\^|\&|\(|\)|\+|\=|\[|\-|\_|\]|\[|\}|\{|\;|\:|\'|\"|\<|\>|\?|\||\\|\!|\$|\./;
 			if (noalpha.test(document.regisform.Phone5.value)) 
				{
				if (alertbox) 
					{
					alert(alertbox);
					}
					return false;
				}
			else
				{
				return true;
				}
			}
	
		}





	function emailvalidation(entered, alertbox)
		{
		with (entered)
			{
			apos=value.indexOf("@");
			dotpos=value.lastIndexOf(".");
			lastpos=value.length-1;
			if (apos<1 || dotpos-apos<2 || lastpos-dotpos>3 || lastpos-dotpos<2)
				{
				if (alertbox) 
					{
 					alert(alertbox);
				//	userid.focus();
                                //        document.regisform.userid.value='';

					}
 					return false;

				}

			else
				{
 				return true;
				}

			}

		}

	

	function formvalidation(thisform)
		{
		with (thisform)
			{
			if (checkphnum1(Phone1,"Enter Phone as Numeric Numbers only (eg. 05122597070)")==false) 
			{Phone1.focus(); return false;};

			if (checkphnum2(Phone2,"Enter Phone as Numeric Numbers only (eg. 05122597070)")==false) 
			{Phone2.focus(); return false;};

			if (checkphnum3(Phone3,"Enter Phone as Numeric Numbers only (eg. 05122597070)")==false) 
			{Phone3.focus(); return false;};

			if (checkphnum4(Phone4,"Enter Phone as Numeric Numbers only (eg. 05122597070)")==false) 
			{Phone4.focus(); return false;};

			if (checkphnum5(Phone5,"Enter Phone as Numeric Numbers only (eg. 05122597070)")==false) 
			{Phone5.focus(); return false;};

			if (emailvalidation(userid,"Enter User Id")==false) {userid.focus(); return false;};
			}

		}

   </script>


<Script type="text/javascript">
function insertRows(isTable,nphone)
	{
	var index   = document.getElementById(isTable).rows.length;
	var nextRow = document.getElementById(isTable).insertRow(index);
	var isText  = nextRow.insertCell(0);
	var isBox   = nextRow.insertCell(1);
	index++;

	index       = index.toString();
	var nameStr = 'Phone'+index;
	var txtStr  = "Phone "+index+":";    
	isText.innerHTML = txtStr;
	isBox.innerHTML = "<input name="+nameStr+" type='text' size='10' maxlength='12'>"
	}

function adjustRows(isVal,isTable,nphone)
	{
	var currRows = document.getElementById(isTable).rows.length;
	var newRows = isVal;
	if (currRows > 0)
		{
		for (i=0; i<currRows; i++)
			{
			document.getElementById(isTable).deleteRow(0)
			}
		}


		for (i=0; i<newRows; i++)
			{
			insertRows(isTable,nphone)
			}
	}


	</Script>

<form action="" name="regisform" method="post" id="regisform" onchange="return formvalidation(this)">
<table align="center" border="2" cellpadding="1" cellspacing="1" width="60%" height="80%" bgcolor="#fffff">


   <tr><td>
	<?if($_SESSION['loginuserid']!=1)
	{?>
	<table align="center" border="0" cellpadding="3" cellspacing="3" border-color: #008000 width="60%" >
 	
		<tr><td width="50%" colspan="2" align="right">
    		<font face="times new roman" size="3" color="#003399">WELCOME:</font>
		</td>

 		<td width="50%" colspan="2" align="left">
    		<font face="times new roman" size="3" color="#0000ff">   
		<?echo$_SESSION['loginuserid']?></font> 
		</td></tr>

	</table><?}?>

   <table align="center" border="0" cellpadding="3" cellspacing="3" width="100%" >

	    	<tr>
		<td colspan="2" align="center">
    		<font face="times new roman" size="4" color="#003399"><b>REGISTRATION &nbsp;FORM</b> </font>
		</td></tr>

	    	<tr>
		<td colspan="2" align="center">&nbsp;</td></tr>

 	<tr><td width="35%"> <b>
 	<font color="#00000" face="times new roman">NAME</font></b>
    	<b><font color="RED" face="times new roman">*</font></b></td>
 	<td width="65" align="left"><font face="times new roman" color="#ff0000"><b>
 	<input type="text" size="30" name="name" id="name" class="txtfield" onchange="checkname(this,'Invalid Name (eg. Raj Kumar)');">
	</b></font></td></tr>


   	<tr><td width="35%" align="left">
	<font face="times new roman" color="#ff0000">
	<b><font face="times new roman" color="#00000">DEPARTMENT</font></b></td>
   	<td><b>
   	<input type="text" size="30" name="dept" id="dept" 
	onchange="checkdept(this,'Invalid Department Name ( eg. EE & ACES IITK)');">
	</b></font></td></tr>


    	<tr><td width="35%" align="left">
	<b><font face="times new roman" color="#00000">DESIGNATION &nbsp;</font></b></td>
	<td width="65%" align="left"><font face="times new roman" color="#ff0000"><b>
    	<input type="text" size="30" name="occupation" id="occupation" 
	onchange="checkoccup(this,'Invalid Occupation (eg. Associate Prof.)' );">
	</b></font></td></tr>


    	<tr><td width="35%" align="left">
    	<b><font color="#00000" face="times new roman">NUMBER OF PHONES</font>
    	<font color="RED" face="times new roman">*</font></b></td>

	<td width="65%" align="left">

		<select name="no_of_ph" maxlength="10" onchange="adjustRows(this.value,'telephone',1)">
		<option value="0"> None </option>
		<option value="1">1 </option>
		<option value="2">2 </option>
		<option value="3">3 </option>
		<option value="4">4 </option>
		<option value="5">5 </option>
		</select>
	</td></tr>



    	<tr><td width="35%" align="left">
	<b><font face="times new roman" color="#00000">TELEPHONE NUMBERS</font>    
    	<font color="RED" face="times new roman">*</font></b></td>


	<td width="65%" align="left">
	<table id='telephone'>
	</table>
	</td></tr>




    	<tr><td width="35%" align="left">
	<b><font face="times new roman" color="#00000">ADDRESS</font></b></td>
    	<td><textarea rows="4" name="address" cols="23"></textarea></td></tr>


    	<tr><td width="35%" align="left">
	<b><font face="times new roman" color="#00000">USER ID</font>
    	<font color="RED" face="times new roman">*</font></b></td>
 	<td><font face="times new roman" color="#ff0000"><b>
    	<input type="text" size="30" name="userid" id="userid" onchange="emailvalidation(this,'Invalid userid (eg. ynsingh@iitk.ac.in)');">
	</b></font> </td></tr> 


    	<tr><td width="35%" align="left">
	<b><font face="times new roman" color="#00000">PASSWORD</font>
    	<font color="RED" face="times new roman">*</font></b></td>
    	<td><font face="times new roman" color="#ff0000"><b>
    	<input type="password" size="30" name="password" ></b></font></td></tr>

    	<tr><td width="35%" align="left">
	<font face="times new roman" color="#00000"><b>QUESTION</b></td>

    		<td width="70%" align="left"><select size="1" name="password-question">
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

		</select>

    	<font size="2">&nbsp;&nbsp; </font>
    	<font size="2" face="times new roman" color="#008000">(In case forgot password ! )
    	</font></td></tr>

    	<tr><td width="35%" align="left">
	<font face="times new roman" color="#00000"><b>HINT ANSWER</b></font>
    	<b><font color="RED" face="times new roman">*</font></b></td>
    	<td><input type="text" size="30" name="hint-ans"></td></tr>

    	<tr><td width="35%" align="left">
	<font face="times new roman" color="#800080">&nbsp;</td>



    	<td>

		<table border="0" align="center" cellspacing="3" cellpading="3" width="100%">
			<tr>
			<td width="25%" align="left">
    			<font face="times new roman" color="#ff0000">
    			<input type="reset" value="RESET" name="reset">
			</td>

			<td width="25%" align="right">
    			<input type="submit" value="SUBMIT" name="submit" language="javascript"/>
			</td>

			<td width="25%" align="left">&nbsp;
			</td>


			<td width="25%" align="left">&nbsp;
			</td>
			</tr>


		</table>
    	</td>

			<tr><td width="25%" align="left" colspan="3">
    			<font face="times new roman" color="#000ff">
			Mandatory fields are asigned as</font>
    			<font face="times new roman" color="red">*</font>

	</td></tr>
    </tbody>
    </table><br>


</td></tr>
</table>
		<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">


			<tr align="center" valign="middle">
   			<td class="txthdmenu"><div id='div_id'><? include "footer.php"; ?> 
        		</div></td></tr>
		</table>

</form>	
</body>
</form>
</html>
