<?
/* @(#)approvedreq.php
 *
 *  Copyright (c) 2007, 2011 Brihaspati Team/ETRG,IIT Kanpur.
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
 *  	*	This file will dispaly the list of approved users by Administrator. 	*
 *  	*	Administrator can delete any or all users as per need.              	*
 *      *                                                                		*
 *      *                                                                		*
 *      *                                                                		*
 *  	*	@author<a href="nksinghiitk@gmail.com">Nagendra Kumar Singh</a>                   	*
 *  	*	@author<a href="vipulk@iitk.ac.in">Vipul Pal</a>                   	*
 *  	*	@author<a href="raajkhurana@gmail.com">Raj Kumar</a>                   	*
 *	*                                                                		*
 *	*********************************************************************************
 */
session_start(); 
include 'config.php';
include 'opendb.php';

	$logged_userid=$_SESSION['loginuserid'];
	/*
	$delete     =$_POST['delete'];
	$goback     =$_POST['goback'];
	$approve    =$_POST['approve'];
	$checkbox   =$_POST['cbox'];
	*/
	
	$delete     =mysql_real_escape_string($_POST['delete']);
	$goback     =mysql_real_escape_string($_POST['goback']);
	$approve    =mysql_real_escape_string($_POST['approve']);
	$checkbox[]   =mysql_real_escape_string($_POST['cbox']);
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
  

	<table align="center" border="3" cellpadding="3" cellspacing="3" width="60%" height="60%" bgcolor="#fffff">
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
			
				<form id="form3" name="form3" method="post" action="">
					<td>
					<select name="search">
					<option value="Select">Select Field</option>
					<option value="Nme">Name</option>
					<option value="Phone Number">Phone Number</option>
					<option value="Email">Email</option>
					</select>
				</td><td>
		        		<input type="text" name="match_string" id="match_string" />
	        	        </td><td>
	            			<input name="button3" type="submit" id="button3" value="Search" style="background-color:#CCC" />
					<input type="hidden" name="hdn_btn" value="by_matchstring" />
				</td>
        			</form>
		</tr>

<?php
	if (isset($_POST['hdn_btn'])){
		if ($_POST['hdn_btn'] == "by_matchstring") {
			 $match_string = $_POST['match_string'];
			 $combo = $_POST['search'];
		}
	}
?>

	    	<tr>
		<td colspan="4">&nbsp;</td>
	    	</tr>

 		<tr><td width="50%" colspan="2" align="right" >
    		<font face="times new roman" size="4" color="#003399">APPROVED </font>
		</td>
 		<td width="50%" colspan="2" align="left" >
    		<font face="times new roman" size="4" color="#003399">REQUESTS</font>
		</td></tr>   



			<tr>
			<td colspan="4" align="center">
			<?if($checkbox!="")
				{
				if($delete)
      	  				{ 
					foreach ($_POST['cbox'] as $checkedid) 
        				$tid[]=$checkedid; 
					$no_of_checkedid=count($tid);
					for($r=0;$r<$no_of_checkedid; $r++)
      	  					{ 
						$update[]="update userinfo set status='n' where userid='$tid[$r]'";
						if($rusult[]=execute_query($update[$r]))
							{
							$delsuccess=$r+1;	
							}

						else
							{
							$delsuccess=$r;	
							exit;
							}
	
						
						}	
						
						if($delsuccess>0)
							{
							?>
    							<font face="times new roman" color="#0000ff" size="3" >
							Selected users deleted successfully</font>
							 
							<?
								$send2="$tid[$r]";
                                                                $subject = "Registration deletion from telephone billing system";
                                                                $message = "Dear Sir,<br>Your registartion has been deleted.<br>your information is not sufficient.<br> Thank you for using this system.<br> For any query, please contact <br> Telephone Incharge <br>sanchar@iitk.ac.in";
                                                                $header="brihspti@iitk.ac.in";
                                                                mail($send2, $subject, $message, $header);
		 					}

						else
							{
							?>
    							<font face="times new roman" color="#0000ff" size="3" >
							Selected users could not deleted successfully</font> 
							<?
		 					}

						

             				}

				}




			   elseif($delete)
				{	?>
    					<font face="times new roman" color="#0000ff" size="3" >
					Please select any user first..</font> 
					<?
				}
				

			?> 		

			</td>
			</tr>
		</table>



	<table  align="center" border="1" cellpadding="3" cellspacing="3"  width="90%" >
  	
		<tr>
 		<td width="5%" align="center">
    		<font face="times new roman" color="#800080" size="3" >
		<b>ALL</b></font> 
     		<input type="checkbox" name="checkall" onclick="checkUncheckAll(this);"/>
 	<!--<td>
	<input type="button" name="Check_All" value="ALL" onClick="Check(document.myform.cbox)">
	-->
		</td>
    		<td align="center" width="30%" height="27">
    		<b><font face="times new roman" color="#003399" size="3" >NAME</font></b> </td>
    		<td align="center" width="30%" height="27">
    		<b><font face="times new roman" color="#003399" size="3" >USERID</font></b> </td>
    		<td align="center" width="21%" height="27">
    		<b><font face="times new roman" color="#003399" size="3" >VIEW</font></b> </td>
    		<td align="center" width="21%" height="27">
    		<b><font face="times new roman" color="#003399" size="3" >UPDATE</font></b> </td>
  		</tr>


<?
		if($match_string==""){
                $sql="select userid, name from userinfo where status='a' order by name";
                }elseif($combo=="Nme"){
                $sql="select name, userid from userinfo where status='a' and name LIKE '%$match_string%' order by name";
                }elseif($combo=="Email"){
                $sql="select name, userid from userinfo where status='a'and userid LIKE '%$match_string%' order by name";
                }elseif($combo=="Phone Number"){
                $sql="select name, userid, phone_number from userinfo,paddress where status='a'and userinfo.userid=paddress.usrid and phone_number LIKE '%$match_string%' order by name";
                }
         	//$sql="select name, userid from userinfo where status='a' order by name";
                $result= execute_query($sql);
                while($row=mysql_fetch_array($result))
                {
                	$dbname[]=$row['name'];
                        $dbid[]=$row['userid'];
                
 
?> 
               


  	<tr>
	<td align="center" width="7%"  height="28"><input type="checkbox" name="cbox[]" value="<?echo$row['userid']?>"></td>
    	<td align="left" width="34%" height="28"><? echo$dbname[]=$row['name']?></td>
    	<td align="left" width="38%" height="28"><? echo$dbid[]=$row['userid']?></td>
    	<td align="center" width="21%" height="28">
	<a href="viewregistration.php?viewid=<?=$id=$row['userid']?>">View
	</a></td>

    	<td align="center" width="21%" height="28">
	<a href="editregistration.php?updateid=<?=$id=$row['userid']?>">Update
    	</tr>

     <? }  ?>


	<table  align="center" border="0" cellpadding="3" cellspacing="3"  width="52%" >

		<tr>
		<td><p>&nbsp;</p></td>
		<td><p>&nbsp;</p></td>
		</tr>

		<td align="center" width="33%"><input type="submit" style="width:80px; "value="GO BACK" name="goback">
		<td align="center" width="33%"><input type="submit" style="width:80px; "value="DELETE" name="delete"></td>
		</td></tr>
	</table>
	
	</td></tr>
	</table>

		<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">
			
			<tr><td height="100">
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
