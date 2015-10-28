<?
/* @(#)pendingreq.php
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
 *  	*	This file will dispaly the list of pending users. 			*
 *  	*	Administrator can delete or approve any or all users as per need.     	*
 *      *                                                                		*
 *      *                                                                		*
 *      *                                                                		*
 *  	*	@author<a href="raajkhurana@gmail.com">Raj Kumar</a>                   	*
 *  	*	@author<a href="nksinghiitk@gmail.com">Nagendra Kumar Singh</a>       	*
 *	*                                                                		*
 *	*********************************************************************************
 */
session_start(); 
include 'config.php';
include 'opendb.php';

//	$checkbox[]= array();
	$logged_userid=$_SESSION['loginuserid'];

	$delete  =mysql_real_escape_string($_POST['delete']);
	$goback  =mysql_real_escape_string($_POST['goback']);
	$approve =mysql_real_escape_string($_POST['approve']);
//	$checkbox[]=mysql_real_escape_string($_POST['cbox']);
	$checkbox=$_POST['cbox'];
	//print_r($checkbox);
//	echo$checkbox[0][0];
	//echo$checkbox[1];
	//$checkboxid[]=mysql_real_escape_string($_POST['cbox']);
	$mess1="User approved and send mail successfully";
	$mess2="User approved but mail can not send";
	$mess3="User is not approved";
	$mess4="User go to delete list and send mail successfully";
	$mess5="User go to delete list and mail can not send";

	if($goback)
	   {
		header("Location: adminaction.php");
	    	exit;
	   }
?>
<html>
<head>

</head>
<body>

    <script type="text/javascript">
	function checkUncheckAll(theElement) 
		{
     		var theForm = theElement.form, z = 0;
	 	for(z=0; z<theForm.length;z++)
			{
      			if(theForm[z].type == 'checkbox' && theForm[z].name != 'checkall')
				{
	  			theForm[z].checked = theElement.checked;
	  			}
			}
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

<div align="center">
<center>

<form name="myform" action="" method="post">
	<table align="center" border="2" cellpadding="3" cellspacing="3" width="60%" height="60%" bgcolor="#FFFFF">
	<tr><td align="center" colspan="3">

	<table align="center" border="0" cellpadding="3" cellspacing="3" border-color: #008000" width="70%" >
 	
		<tr><td width="50%" colspan="2" align="right">
    		<font face="times new roman" size="3" color="#003399">WELCOME:</font>
		</td>

 		<td width="50%" colspan="2" align="left">
    		<font face="times new roman" size="3" color="#0000ff">   
		<?echo$_SESSION['loginuserid']?></font> 
		</td></tr>

	    	<tr>
		<td colspan="4">&nbsp;</td>
	    	</tr>

 		<tr><td width="50%" colspan="2" align="right" >
    		<font face="times new roman" size="4" color="#003399">PENDING </font>
		</td>
 		<td width="50%" colspan="2" align="left" >
    		<font face="times new roman" size="4" color="#003399">REQUESTS</font>
		</td></tr>   

	</table>

		<table  align="center" border="0" cellpadding="3" cellspacing="3"  width="80%" >
			<tr>
			<td colspan="2" align="center">
			<?
			//**************************************************************************************	
				$msend= array();
				$mnotsend= array();
				$failReg= array();
				$mdsend= array();
				$mdnotsend= array();
				$size=count($checkbox);	
				if($checkbox!='')
				{ 
					if($approve)
					{
						for($r=0;$r<$size; $r++)
						{ 
						$update="update userinfo set status='a' where userid='$checkbox[$r]'";
							if($rusult=execute_query($update))
							{
							$send2=$checkbox[$r];
                                                        $subject = "Registration Approval";
                                                        $message = "Dear Sir,\n\n  Your Telephone billing registartion has been approved.\n  Now you are authorised to see your telephone bill.\n  Thank you for using this system.\n\n\nFor any query, please contact:\n\nTelephone Incharge \nsanchar@iitk.ac.in";
                                                        $header="From:sanchar@iitk.ac.in";
								if(mail($send2, $subject, $message, $header))
								{
								$msend[]=$send2;
								}
								else	
								{
								$mnotsend[]=$send2;
								}		
							}
							else
							{
							$failReg[]=$checkbox[$r];
							}
	
						}//close for
					}//close approve if
					if($delete)
      	  				{ 
						for($r=0;$r<$size; $r++)
      	  					{ 
						$update   ="update userinfo set status='n' where userid='$checkbox[$r]'";
							if($rusult=execute_query($update))
							{
							$mdsend[]=$checkbox[$r];
							}
							else
							{
							$mdnotsend[]=$checkbox[$r];
							}
						}//close for
					}//close delete if
				}//close check box if
				elseif(($delete)||($approve))
				{	?>
    					<font face="times new roman" color="#0000ff" size="3">Please select any user first..</font> 
				<?
				}//close else check box if
				?> 	

	<table  align="center" border="1" cellpadding="3" cellspacing="3"  width="90%" >
  	
		<tr>
 		<td width="5%" align="center">
    		<font face="times new roman" color="#003399" size="3" >
		<b>ALL</b></font> 
		<input type="checkbox" name="checkall" onclick="checkUncheckAll(this);"/>

    		<td align="center" width="35%" height="27">
    		<b><font face="times new roman" color="#003399" size="3" >NAME</font></b> </td>
    		<td align="center" width="40%" height="27">
    		<b><font face="times new roman" color="#003399" size="3" >USERID</font></b> </td>
    		<td align="center" width="10%" height="27">
    		<b><font face="times new roman" color="#003399" size="3" >VIEW</font></b> </td>
  		</tr>
	<?
         	$sql="select name, userid from userinfo where status='y' order by name";
         	$result= execute_query($sql);
         	while($row=mysql_fetch_array($result))
            		{
               		$dbname[]=$row['name'];
                	$dbid[]  =$row['userid'];
	?> 
               
  		<tr>
		<td align="center" width="5%"  height="28">
		<input type="checkbox" name="cbox[]" value="<?echo$row['userid']?>"></td>
    		<td align="left" width="35%" height="28"><? echo$dbname[]=$row['name']?></td>
    		<td align="left" width="40%" height="28"><? echo$dbid[]=$row['userid']?></td>

    		<td align="center" width="10%" height="28">
		<a href="viewregistration.php?viewid=<?=$id=$row['userid']?>">View</a></td>
    		</tr>
     <? }  ?>

	</table>

	<table  align="center" border="0" cellpadding="3" cellspacing="3"  width="80%" >
				<tr>
				<td><p>&nbsp;</p></td>
				<td><p>&nbsp;</p></td>
				<td><p>&nbsp;</p></td>
				<td><p>&nbsp;</p></td>
				</tr>

		<tr>
		<td align="right" width="25%"><input type="submit" style="width:80px;" value="GO  BACK" name="goback"></td>
		<td align="center" width="40%"><input type="submit" style="width:80px;" value="APPROVE" name="approve"></td>
		<td align="left" width="25%"><input type="submit" style="width:80px; " value=" DELETE " name="delete"></td>
		<td width="10%"><p>&nbsp;</p>
		</td></tr>
         </table>

	</td></tr>
	</table>
<hr>
	<?			//This is for display result
		$j=0; ?>
		<table border=1>
			<tr><td align="center"><b>Sr. No.</b></td><td align="center"><b>User Id</b></td><td align="center"><b>Message</b></td></tr>
		<?//for approve case
			for($i=0;$i<count($msend);$i++){
				$j=$i+1;  ?>
				<tr><td><? echo $j ?></td><td><? echo $msend[$i] ?></td><td><? echo $mess1 ?></td></tr>	
		<?	}
			for($i=0;$i<count($mnotsend);$i++){
				$j=$j+$i+1; ?>
				<tr><td> <? echo $j ?></td><td><? echo $mnotsend[$i] ?></td><td><? echo $mess2 ?></td></tr>	
		<?	}
			for($i=0;$i<count($failReg);$i++){
				$j=$j+$i+1; ?>
				<tr><td><? echo $j ?></td><td> <? echo $failReg[$i] ?></td><td> <? echo $mess3 ?></td></tr>	
		<?	} ?>

		<? //for delete case
                        for($i=0;$i<count($mdsend);$i++){
                                $j=$i+1;  ?>
                                <tr><td><? echo $j ?></td><td><? echo $mdsend[$i] ?></td><td><? echo $mess4 ?></td></tr>
                <?      }
                        for($i=0;$i<count($mdnotsend);$i++){
                                $j=$j+$i+1; ?>
                                <tr><td> <? echo $j ?></td><td><? echo $mdnotsend[$i] ?></td><td><? echo $mess5 ?></td></tr>
                <?      } ?>


		</table>
</td></tr></table>
		<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">
			
			<tr><td height="70">
			</td></tr>

			<tr align="center" valign="middle">
   			<td class="txthdmenu"><div id='div_id'><? include "footer.php"; ?> 
        		</div></td></tr>
		</table>

<?}?>
	</form>
	</center>
	</div>
</body>
</html>
