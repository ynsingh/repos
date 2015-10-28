<?
/* @(#)deletedreq.php
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
 *  	*	This file will dispaly the list of deleted users by Administrator. 	*
 *  	*	Administrator can delete any or all users permanently as per need.     	*
 *      *                                                                		*
 *      *                                                                		*
 *      *                                                                		*
 *  	*	@author<a href="nksinghiitk@gmail.com">Nagendra Kumar Singh</a>        	*
 *  	*	@author<a href="raajkhurana@gmail.com">Raj Kumar</a>                   	*
 *	*                                                                		*
 *	*********************************************************************************
 */
session_start(); 
include 'config.php';
include 'opendb.php';

	$logged_userid=$_SESSION['loginuserid'];

	//*********************        Sever Code     **************
	$delete    =mysql_real_escape_string($_POST['delete']);
	$goback    =mysql_real_escape_string($_POST['goback']);
	$restore   =mysql_real_escape_string($_POST['restore']);
	//$checkbox[]=mysql_real_escape_string($_POST['cbox']);
	$checkbox=$_POST['cbox'];
	
	$mess1="User deleted and send mail successfully";
        $mess2="User deleted but mail can not send";
        $mess3="User is not deleted";
        $mess4="User is restored in pending list";
        $mess5="User is not restored,contact to administrator";
	/*************************************************************
	$delete    =$_POST['delete'];
	$goback    =$_POST['goback'];
	$approve   =$_POST['approve'];
	$checkbox[]=$_POST['cbox'];
	***********************************************************/
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
	</table>
<br>





<div align="center">
<center>

   <form name="myform" method="post" action="">
	<table align="center" border="2" cellpadding="3" cellspacing="3" width="60%" height="60%" bgcolor="#FFFFF">
		<tr><td align="center" colspan="3">

	<table align="center" border="0" cellpadding="3" cellspacing="3" border-color="#008000" width="70%" >
 	
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
    		<font face="times new roman" size="4" color="#003399">DELETED </font>
		</td>

 		<td width="50%" colspan="2" align="left" >
    		<font face="times new roman" size="4" color="#003399">REQUESTS</font>
		</td></tr>   

			<tr><td colspan="4" align="center">
			<!--if($checkbox!="")
				{
				if($delete)
      	  				{ 
					foreach ($_POST['cbox'] as $checkedid) 
        				$tid[]=$checkedid; 
					$no_of_checkedid=count($tid);
					for($r=0;$r<$no_of_checkedid; $r++)
				-->

			<?$size=count($checkbox);
				$msend= array();
                                $mnotsend= array();
                                $failReg= array();
				$mdsend= array();
                                $mdnotsend= array();
			if($checkbox!="")
				{
				if($delete)
      	  				{ 
					for($r=0;$r<$size; $r++)
      	  						{ 
							$del="delete from userinfo where userid='$checkbox[$r]' and status='n'";
							$delph="delete from paddress where usrid='$checkbox[$r]'";   //...?
							if((execute_query($del))&&(execute_query($delph)))
								{
								$send2=$checkbox[$r];
							if(!eregi("^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$", $send2)) {
					                $send2=$send2."@iitk.ac.in";
        						}

                                                        $subject = "Registration incomplete ";
                                                        $message = "Dear Sir,\n\n  Your Telephone billing registartion Can not be approved since userid is incomplete. \n  So please make registration again.\n  Thank you for using this system.\n\n\nFor any query, please contact:\n\n \nTelephone Incharge \nsanchar@iitk.ac.in";
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
             			}//close delete if
                                if($restore)
                                        {
                                        for($r=0;$r<$size; $r++)
                                                {
                                                $update="update userinfo set status='y' where userid='$checkbox[$r]'";
						if($rusult=execute_query($update))
                                                        {
                                                        $mdsend[]=$checkbox[$r];
                                                        }
                                                        else
                                                        {
                                                        $mdnotsend[]=$checkbox[$r];
                                                        }

						}//close for
				}//close restore if

			}//close checkbox if
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
       			
			</td>
    			<td align="center" width="35%" height="27">
    			<b><font face="times new roman" color="#800080" size="3" >NAME</font></b> </td>
    			<td align="center" width="40%" height="27">
    			<b><font face="times new roman" color="#800080" size="3" >USERID</font></b> </td>
    			<td align="center" width="10%" height="27">
    			<b><font face="times new roman" color="#800080" size="3" >&nbsp;</font></b> </td>
  			</tr>


<?
	
         	$sql="select name, userid from userinfo where status='n' order by name";
                $result= execute_query($sql);
                while($row=mysql_fetch_array($result))
                	{
                	$dbname[]=$row['name'];
                        $dbid[]=$row['userid'];
 
?> 
               


  			<tr>
			<td align  ="center" width ="5%"  height="28">
			<input type="checkbox" name="cbox[]" value=  "<? echo$dbid[]  =$row['userid']?>"></td>
    			<td align  ="left" width   ="35%" height="28"><? echo$dbname[]=$row['name']  ?>  </td>
    			<td align  ="left" width   ="40%" height="28"><? echo$dbid[]  =$row['userid']?>  </td>
    			<td align  ="center" width ="10%">
			<a href    ="viewregistration.php?viewid=<?=$id=$row['userid']?>">View</a></td>
    			</tr>

<?			
		
 			}  



?>



		<table  align="center" border="0" cellpadding="3" cellspacing="3"  width="52%" >
			<tr>
			<td><p>&nbsp;</p></td>
			<td><p>&nbsp;</p></td>
			</tr>

			<tr>
			<td align="center" width="50%"><input type="submit" style="width:80px;" value="GO  BACK" name="goback"></td>
			<td align="center" width="50%"><input type="submit" style="width:80px;" value="DELETE" name="delete"></td>
			<td align="center" width="50%"><input type="submit" style="width:180px;" value="RESTORE TO PENDING LIST" name="restore"></td>
        		</tr>
         	</table>

		</table>

	<?                      //This is for display result
                $j=0; ?>
                <table border=1>
                        <tr><td align="center"><b>Sr. No.</b></td><td align="center"><b>User Id</b></td><td align="center"><b>Message</b></td></tr>
                <?//for delete
                        for($i=0;$i<count($msend);$i++){
                                $j=$i+1;  ?>
                                <tr><td><? echo $j ?></td><td><? echo $msend[$i] ?></td><td><? echo $mess1 ?></td></tr>
                <?      }
                        for($i=0;$i<count($mnotsend);$i++){
                                $j=$j+$i+1; ?>
                                <tr><td> <? echo $j ?></td><td><? echo $mnotsend[$i] ?></td><td><? echo $mess2 ?></td></tr>
                <?      }
                        for($i=0;$i<count($failReg);$i++){
                                $j=$j+$i+1; ?>
                                <tr><td><? echo $j ?></td><td> <? echo $failReg[$i] ?></td><td> <? echo $mess3 ?></td></tr>
                <?      } ?>

		<? //for restore
                        for($i=0;$i<count($mdsend);$i++){
                                $j=$i+1;  ?>
                                <tr><td><? echo $j ?></td><td><? echo $mdsend[$i] ?></td><td><? echo $mess4 ?></td></tr>
                <?      }
                        for($i=0;$i<count($mdnotsend);$i++){
                                $j=$j+$i+1; ?>
                                <tr><td> <? echo $j ?></td><td><? echo $mdnotsend[$i] ?></td><td><? echo $mess5 ?></td></tr>
                <?      }?>

                </table>


	</td></tr>
	</table>


		<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">

			<tr><td height="75">
			</td></tr>

			<tr align="center" valign="middle">
   			<td class="txthdmenu"><div id='div_id'><? include "footer.php"; ?> 
        		</div></td></tr>
			

	<?}?>
	</form>
	</center>
	</div>
</body>
</html>
