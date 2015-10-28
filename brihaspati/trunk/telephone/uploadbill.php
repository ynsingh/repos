<?
/* @(#)uploadbill.php
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
 *  	*	This is used to upload bill by the administrator.			*	                            * *       *       									     *
 *      *      								         	*
 *      *                                                                		*
 *      *                                                                		*
 *  	*	@author<a href="nksinghiitk@gmail.com">Nagendra Kumar Singh</a>                   	*
 *  	*	@author<a href="raajkhurana@gmailcom">Raj Kumar</a>                   	*
 *	*                                                                		*
 *	*********************************************************************************
 */
include 'config.php';
include 'opendb.php';

		/*echo$back=$_GET['back'];
		$upload=$_GET['upload'];
		*/
		$destinationdir = "/var/www//html/telephone/uploadfiles/";
		$destinationfile = $destinationdir . $_FILES['myfile']['name'];
		if (move_uploaded_file($_FILES['myfile']['tmp_name'], $destinationfile)) 
			{
			//$bloadquery="load data local infile '$destinationfile' into table billtable fields terminated by '|' enclosed by '' lines terminated by '\\r\\n'";
			$bloadquery="load data local infile '$destinationfile' into table billtable fields terminated by '|' enclosed by '' lines terminated by '\n'";
				
				if(execute_query($bloadquery))
					{
						$msend= array();
			        	        $mnotsend= array();                
				                $getemal="select userid from userinfo where status='a'";
				                $resultinfo=execute_query($getemal);
						$ii=0;
				                while($row=mysql_fetch_array($resultinfo))
				                {
				                        $send2=$row['userid'];
				                        $subject = "Bill Uploaded";
				                        $message = "Dear Sir, Your Telephone bill has been uploaded. I request to you, please check your telephone bill <a href=http://brihaspati.ee.iitk.ac.in/telephone/index.php> here</a>. In case of any difficiency, please let me know. Thank you for using this system. For any query, please contact  <br> Telephone Incharge <br>sanchar@iitk.ac.in";

				                        $header="sanchar@iitk.ac.in";
				                        if(mail($send2, $subject, $message, $header))
                                                        {
                                                                $msend[$ii]=$send2;
                                                        }
                                                        else
                                                        {
                                                                $mnotsend[$ii]=$send2;
                                                        }
						$ii++;
						} 
					include("uploadsuccess.php");
				//	header("Location: uploadsuccess.php");
					exit;
					}


				else
					{ 
					header("Location: uploaderror.php");
					}


			}


		else
			{ 
			header("Location: uploaderror.php");
			}



?>
