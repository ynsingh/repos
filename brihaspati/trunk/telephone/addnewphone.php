<?
/* @(#)addnewphone.php
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
 *  	*	This file provide space to enter the userid and select no. of phones    *
 *  	*	to add new phone of already registered user. This can be done by   	*
 *      *      	administrator on request and hiring new telephone number of users       *
 *      *                                                                		*
 *      *                                                                		*
 *  	*	@author<a href="raajkhurana@gmail.com">Raj Kumar</a>                   	*
 *	*                                                                		*
 *	*********************************************************************************
 */
session_start(); 
include 'config.php';
include 'opendb.php';

		$logged_userid=$_SESSION['loginuserid'];
		$back  =mysql_real_escape_string($_POST['back']);
		$submit=mysql_real_escape_string($_POST['submit']);
		$usrid =mysql_real_escape_string($_POST['usrid']);

 		$add_no_of_ph  = mysql_real_escape_string($_POST['no_of_ph']);
 		$phone_array[0]= mysql_real_escape_string($_POST['Phone1']);
 		$phone_array[1]= mysql_real_escape_string($_POST['Phone2']);
 		$phone_array[2]= mysql_real_escape_string($_POST['Phone3']);
 		$phone_array[3]= mysql_real_escape_string($_POST['Phone4']);
 		$phone_array[4]= mysql_real_escape_string($_POST['Phone5']);
		$p;

if($submit)
{
	if(($usrid!=null) && ($add_no_of_ph!=0))
		{
		$addph="select usrid, ph from paddress where usrid='$usrid'";
		//echo"raaj";
		if($result=execute_query($addph) or die("Can't execute query $addph"))
			{
			while($row=mysql_fetch_array($result))
				{
				$dbid=$row['usrid'];
				$db_no_ph=$row['ph'];
				}
				
				if(($usrid!=$dbid)||($dbid==null))
					{
		 			$addpherr="Entered User Id doesn't match...";
					}


				elseif(($usrid==$dbid)&&($dbid!=null))
					{
					$no_of_phones=$db_no_ph+$add_no_of_ph;
					$updateph="update paddress set ph='$no_of_phones' where usrid='$usrid'";
					if(execute_query($updateph) or die("Phone could not added"));
						{
					
       						for($p=0; $p<$add_no_of_ph; $p++)
         						{
	   	  					$sql2="insert into paddress(usrid, ph, phone_number) values('$usrid', '$no_of_phones', '$phone_array[$p]')";
 							if(strlen($phone_array[$p])>3)
								{
								$insertph=execute_query($sql2);
								}


                					else
                   	  					{ 
                     						for($i=$p-1;$i>-1;$i--)
                      		   					{
                        						$delsql2="delete from paddress where phone_number='$phone_array[$i]'";
                        						execute_query($delsql2);
                       		     					}

									
									$updateph="update paddress set ph='$db_no_ph' where usrid='$usrid'";
									if(execute_query($updateph) or die("Phone could not updaed"));
								$addpherr="Phone should have more than 4 digis unlike phone $phone_array[$p]";
                    	     					}


               
                					}

						if($addpherr==null)
							{
	 						header("location: addnewphsuccess.php");
							}
					
						}
					
					


        				}


			}
	
		}
							
					
	else
		{
		 $addpherr="Error..! enter user Id and select no. of phones";
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

	function checkphnum1(entered, alertbox)
		{
		with (entered)
			{
 			var noalpha = /^[A-Z]|[a-z]|\$|,|@|#|~|`|\%|\*|\^|\&|\(|\)|\+|\=|\[|\-|\_|\]|\[|\}|\{|\;|\:|\'|\"|\<|\>|\?|\||\\|\!|\$|\./;
 			if (noalpha.test(document.addphoneform.Phone1.value)) 
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
 			if (noalpha.test(document.addphoneform.Phone2.value)) 
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
 			if (noalpha.test(document.addphoneform.Phone3.value)) 
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
 			if (noalpha.test(document.addphoneform.Phone4.value)) 
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
 			if (noalpha.test(document.addphoneform.Phone5.value)) 
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
	isBox.innerHTML = "<input name="+nameStr+" type='text' size='12' maxlength='12'>"
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


<!--************************************************Above-->



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



	<form action="" name="addphoneform" method="post" id="addphoneform" onchange="return formvalidation(this)">
	<table align="center" border="2" cellspacing="3" cellpeding="3" width="55%" height="60%" bgcolor="#fffff">
		<tr><td>
	<table align="center" border="0" cellpadding="3" cellspacing="3" border-color: #008000 width="55%" >
 	
		<tr><td width="50%" colspan="2" align="right">
    		<font face="times new roman" size="3" color="#003399">WELCOME:</font>
		</td>

 		<td width="50%" colspan="2" align="left">
    		<font face="times new roman" size="3" color="#0000ff">   
		<?echo$_SESSION['loginuserid']?></font> 
		</td></tr>


 		<tr><td width="50%" colspan="2" align="right">
    		<font face="times new roman" size="4" color="#003399">ADD NEW</font></td>
 		<td width="50%" colspan="2" align="left">
    		<font face="times new roman" size="4" color="#003399">TELEPHONE</font>
		</td></tr>   

	</table><br><br>
	
  			<table align="center" border="0" width="65%">

			<?if(($dbpasswd!="")&&($searchedpwd==""))
				{?>
  				<tr><td width="100%" align="center" colspan="3">
				<font face="times new roman" size="3" color="#0000ff">Searched Password is: 
				<font face="times new roman" size="4" color="00999"> 
				<?echo$dbpasswd?></font>
				</td></tr>
				<?}elseif($addpherr!=""){?>
  				<tr><td width="100%" align="center" colspan="3">
				<font face="times new roman" size="3" color="#ff0000">
				<?echo$addpherr;?></font>
				</td></tr>
				
				<?}?>

  			<tr><td width="40%" align="">
    			<font color="#00000" face="times new roman" size="2">PLEASE ENTER USER ID </font>
    			<font color="RED" face="times new roman">*</font>
  			 </td>

  			<td width="60%" align="left" colspan="2">
			<input type="text" name="usrid" size="22" id="usrid" onchange="emailvalidation(this,'Invalid User Id (valid eg. raj@iitk.ac.in)');"> 
			</td></tr>

			
    	<tr><td width="40%" align="left">
    	<font color="#00000" face="times new roman" size="2">NUMBER OF PHONES</font>
    	<font color="RED" face="times new roman">*</font></td>

	<td width="60%" align="left" colspan="2">

		<select name="no_of_ph" maxlength="10" onchange="adjustRows(this.value,'telephone',1)">
		<option value="0"> None </option>
		<option value="1">1 </option>
		<option value="2">2 </option>
		<option value="3">3 </option>
		<option value="4">4 </option>
		<option value="5">5 </option>
		</select>
	</td></tr>



    	<tr><td width="50%" align="left">&nbsp;
    	</td>
	

	<td width="50%" align="left" colspan="3">
	<table id='telephone'>
	</table>
	</td></tr>

  			<tr><td width="100%" align="right" colspan="3">&nbsp;
			</td></tr>


        		<tr><td align="right" width="50%">
			<input type="submit" name="back" style="width:80px;" value="BACK">
			</td>

  			<td align="right" width="25%">
			<input type="submit" name="submit" style="width:80px;" value="SUBMIT">
			</td>

        		<td align="center" width="25%">&nbsp;
			</td>
			</tr>
  			</table>
	</tr></td>
	</table>
		
	</td></tr>
	</table>

		<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">

			<tr><td height="70">
			</td></tr>

			<tr align="center" valign="middle">
   			<td class="txthdmenu"><div id='div_id'><? include "footer.php"; ?> 
        		</div></td></tr>
		</table>

  	</form>
<?}?>
  </body>
  </html>
  
