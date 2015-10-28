<?
/* @(#)pwdsuccess.php
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
 *  	*	This  dispaly option to view the bill of the administrator		*	                            * *      *       authorities such as view list of pending requests of users or view      *
 *      *      	the deleted or approved list of users by administrator.         	*
 *      *                                                                		*
 *      *                                                                		*
 *  	*	@author<a href="raajkhurana@gmail.com">Raj Kumar</a>                   	*
 *	*                                                                		*
 *	*********************************************************************************
 */
session_start();
include 'config.php';
include 'opendb.php';

  $loginid    =$_SESSION['loginuserid'];

  $ok         =mysql_real_escape_string($_POST['ok']);
  $id         =mysql_real_escape_string($_POST['userid']);
  $submit     =mysql_real_escape_string($_POST['submit']);
  $opwd       =mysql_real_escape_string($_POST['oldpwd']); 
  $npwd       =mysql_real_escape_string($_POST['newpwd']); 
  $cpwd       =mysql_real_escape_string($_POST['confirmpwd']); 
  $adminaction=mysql_real_escape_string($_POST['adminaction']); 
 
    $que  = "select name, department, password from userinfo where userid='$loginid'";
    $result = execute_query($que);
  while($row = mysql_fetch_array($result))
    {   
	  $dbname = $row['name'];
	  $dbdept = $row['department'];
	  $dbpassword = $row['password'];

     }
  if($ok)
	{ 
	  header("Location: bill.php?userid=$id");
	  exit;
	}
        if($submit)
           {
		if(($opwd==$dbpassword) && ($npwd==$cpwd))
                  
              	   {
 			$update_pwd = "update userinfo set password='$npwd' where userid='$loginid'";
			$result1=execute_query($update_pwd);
	  		header("Location: pwdchange.php");
                    }
    
	    	else
	     	  {   
		  	header("Location: pwdchangeerror.php");

	           }
         
            }    
   if($adminaction)
       {
	 header("Location: adminaction.php"); 
       }     			
?>
<html>
<body>
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

	
<hr>




	<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">
		<tr align="right" valign="middle">
   		<td class="txthdmenu"><div id='div_id'><?php include "headerlinks.php"; ?> 
        	<br></div></td></tr>
	</table><br>


</head>


<form action ="" name="" method="post">
<table align ="center" border="2" cellpadding="3" cellspacing="3" width="60%" height="65%" bgcolor="#FFFFF">
	<tr><td>


	<table align ="center" border="0" cellpadding="5" cellspacing="2" width="70%" bgcolor="#fffff">
	

		<tr>
 		<td width="50%" colspan="2" align="right">
    		<font face="times new roman" size="3" color="#003399">WELCOME:</font>
		</td> 

 		<td width="50%" colspan="2" align="left">
    		<font face="times new roman" size="3" color="#0000ff">   
		<?echo$_SESSION['loginuserid']?></font> 
		</td></tr>


	    	<tr>
		<td colspan="4">&nbsp;</td>
	    	</tr>
	
        	<tr><td width=10% ALign="left">
    		<b><font face="times new roman" color="#00000">NAME</font></b>
		</td>

    		<td>
		<input type="text" name="name" value="<?echo $dbname ?>" size="20" readonly>
		</td>

    		<td width="30%" align="right">
    		<b><font face="times new roman" color="#000000">DEPARTMENT&nbsp;&nbsp;</font></b>
		</td>

    		<td width="20%" align="right">
    		<input type="text" name="dept" value="<?echo $dbdept?>" size="20" readonly>
		</td></tr>
	</table>




	<br>



    		<table align ="center" border="2" cellpadding="3" cellspacing="3"  width="75%" height="65%">
     			

			<tr>
       			<td align="center" width="50%">
    			<b><font face="times new roman" color="#800080">BILLING</font></b></td>


			<td align="center" width="50%">
    			<b><font face="times new roman" color="#800080">ADMINISTRATOR</font></b></td>
			</td>
			</tr>



     		<tr>
      		<td>
			
    			<table align ="center" border="0" cellpadding="3" cellspacing="3"  width="100%" height="155">
				<tr><td width="100%" colspan="2">&nbsp;</td>
				</tr>

				<tr><td align="left" width="30%">
        			<font face="Times New Roman" color="#000000">
				<b><font size="2">USER ID</font></b></td>


				<td width="70%" align="left">
				<font face="britanic bold" color="#FF0000"></font>
				<input type="text" name="userid" value="<? echo $loginid ?>" size="15" readonly>
				</td></tr>

				<tr>
				<td  align="left" width="30%">&nbsp;
				</td>


				<td width="70%">
				<a href="changepassword.php?uid=<?echo$loginid?>">Change Password </a>
				</td></tr>


				<tr>
				<td width="30%">&nbsp;</td>

				<td width="70%" align="left" colspan="2">
        			<input type="submit" style="width:70px;" name="ok" value="O K">
      				</td></tr>
                        </table>





		</td>

		

		<td>	
		
    			<table align ="center" border="0" cellpadding="5" cellspacing="0"  width="70%" height="155">

				<tr>
      				<td align="center" colspan="3" height="100">
      				<p><img src="images/iitk.jpg" border="0" width="117" height="112"> </p>
				</td></tr>
		



				<tr><td align="center" colspan="2">
        			<input type="submit" name="adminaction" style="width:110px;" value="ADMIN ACTION">
				</td></tr>

			</table>


		<td></tr>



       		</center>
      		</td>
     		</tr>
   		</table>
		</td></tr>


     	</td>
	</tr>
  	</table>
   	</form>


		<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">

			<tr><td height="30">
			</td></tr>

			<tr align="center" valign="middle">
   			<td class="txthdmenu"><div id='div_id'><? include "footer.php"; ?> 
        		</div></td></tr>
		</table>

<?}?>
</td></tr>
</body>
</html>
