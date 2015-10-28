<?
/* @(#)adminaction.php
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
 *  ***************************************************************
 *  *	This file is used to use to Administrator authorities.    * 
 *  *	Administrator can Approve, Delete ans leave in Pending    *
 *  *	requests of users.                                        *
 *  *                                                             *
 *  *								  *
 *  *	@author<a href="raajkhurana@gmail.com">Raj Kumar</a>	  *
 *  *								  *
 *  ***************************************************************	
 */ 
session_start();
include 'config.php';
include 'opendb.php';
     $logged_userid=$_SESSON['loginuserid'];
/*
     $pendingreq   =$_POST['pending'];                    
     $deletedreq   =$_POST['deleted'];                    
     $approvedreq  =$_POST['approved'];                    
     $uploadbill   =$_POST['uploadbill'];                    
     $addnewphone  =$_POST['addnewphone'];                    
     $delbill	   =$_POST['delbill'];                    
     $updateregistration=$_POST['updateregistration'];                    
     $back         =$_POST['back'];                    
     $findpassword =$_POST['findpassword'];                    
*/
     $pendingreq   =mysql_real_escape_string($_POST['pending']);                    
     $deletedreq   =mysql_real_escape_string($_POST['deleted']);                    
     $approvedreq  =mysql_real_escape_string($_POST['approved']);                    
     $uploadbill   =mysql_real_escape_string($_POST['uploadbill']);                    
     $addnewphone  =mysql_real_escape_string($_POST['addnewphone']);                    
     $delbill	   =$_POST['delbill'];                    
     $back         =mysql_real_escape_string($_POST['back']);                    
     $findpassword =mysql_real_escape_string($_POST['findpassword']);                    
     $updateregistration=mysql_real_escape_string($_POST['updateregistration']);                    

	if($pendingreq)
    		{ 
	     	header("Location: pendingreq.php");
    		}	
	
		
	elseif($deletedreq)
	      { 
		header("Location: deletedreq.php");
              }


	elseif($approvedreq)
	      { 
		header("Location: approvedreq.php");
	      }


	elseif($uploadbill)
	      { 
		header("Location: uploadfile.php");
	      }

	elseif($addnewphone)
	      { 
		header("Location: addnewphone.php");
	      }


	elseif($findpassword)
	      { 
		header("Location: findpassword.php");
	      }


	elseif($updateregistration)
	      { 
		header("Location: updateregistration.php");
	      }


	elseif($back)
		{
		header("Location: pwdsuccess.php");
		}


	elseif($delbill)
		{
		header("Location: delbill.php");
		}
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
	

<body>
<hr>




	<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">
		<tr align="right" valign="middle">
   		<td class="txthdmenu"><div id='div_id'><? include "headerlinks.php"; ?> 
        	<br></div></td></tr>
	</table><br>





<form action="" name="" method="post"> 
	<table align="center" border="1" cellpadding="3" cellspacing="3" width="55%" height="65%" bgcolor="#fffff" >
    	<tr><td>

	<table align="center" border="0" cellpadding="3" cellspacing="3" border-color: #008000 width="70%" >
 	
		<tr><td width="50%" colspan="2" align="right">
    		<font face="times new roman" size="3" color="#003399">WELCOME:</font>
		</td>

 		<td width="50%" colspan="2" align="left">
    		<font face="times new roman" size="3" color="#0000ff">   
		<?echo$_SESSION['loginuserid']?></font> 
		</td></tr>


 		<tr><td width="50%" colspan="2" align="right">
    		<font face="times new roman" size="4" color="#003399">ADMINISTRATOR</font></td>
 		<td width="50%" colspan="2" align="left">
    		<font face="times new roman" size="4" color="#003399">AUTHORITIES</font>
		</td></tr>   

	</table>
	


	<table align="center" border="0" cellpadding="3" cellspacing="3" width="70%">
        <tr>
	<tr><td><p>&nbsp;</p></td>
	<td><p>&nbsp;</p></td></tr>

        <tr>
        <td width="20%" align="center"><input type="submit" style="width:130px;" value=" PENDING USERS " name="pending"></td>
        <td width="20%" align="center"><input type="submit" style="width:130px;" value="DELETED USERS" name="deleted"></td>
	</tr>

	<tr><td><p>&nbsp;</p></td>
	<td><p>&nbsp;</p></td></tr>

        <tr>
        <td width="20%" align="center"><input type="submit"  style="width:130px;" value="APPROVED USERS" name="approved"></td>
        <td width="20%" align="center"><input type="submit" style="width:130px;" value="UPLOAD   BILL"  name="uploadbill"></td>
 	</tr> 

	<tr><td><p>&nbsp;</p></td>
	<td>&nbsp;</td></tr>


        <tr><td width="20%" align="center">
	<input type="submit" style="width:130px;" value="ADD  NEW  PHONE"  name="addnewphone"></td>
	
	 <td width="20%" align="center">&nbsp;
	<input type="submit"  style="width:130px;" value="FIND  PASSWORD"  name="findpassword">
	<!--<input type="submit" value="UPDATE REGISTR" name="updateregistration">-->
	</td></tr>


	<tr><td><p>&nbsp;</p></td>
	<td>&nbsp;</td></tr>


        <tr><td width="20%" align="center">
	<input type="submit" style="width:130px;" value="GO  TO  BACK"  name="back"></td>
	
	 <td width="20%" align="center">
	<input type="submit" style="width:130px;" value="DELETE   BILL"  name="delbill"></td>
	</td></tr>
     </table>

  </td>
  </tr>
  </table>

		<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">
			<tr><td height="20">
			</td></tr>
			<tr align="center" valign="middle">
   			<td class="txthdmenu"><div id='div_id'><? include "footer.php"; ?> 
        		</div></td></tr>
		</table><br>

<?}?>
  </form>
</body>
</html>
