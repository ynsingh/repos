<?
/* @(#)uploadbill.php
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
 *  	*	This is used to upload bill by the administrator.			*	                            * *       *       									     *
 *      *      								         	*
 *      *                                                                		*
 *      *                                                                		*
 *  	*	@author<a href="raajkhurana@gmailcom">Raj Kumar</a>                   	*
 *	*                                                                		*
 *	*********************************************************************************
 */
include 'config.php';
include 'opendb.php';

		$destinationdir = "/var/www/html/telephone/uploadfiles/";
		$fname="bill.txt";
		echo$destinationfile = $destinationdir . $fname;
		if (file_exists($destinationfile)) 
			{
			$handle = fopen($destinationfile, "r");
			$bloadquery="load data local infile '$handle' into table billtable fields terminated by '|' enclosed by '' lines terminated by '\\r\\n'";
				
				if(execute_query($bloadquery) or die(" could not execute"))
					{
					
					echo "Data base update succesfully"; 
					fclose($handle);
					//header("Location: uploadsuccess.php");
					exit;
					}


				else
					{ 
					echo "File does not corporate with database"; 
				//	header("Location: uploaderror.php");
					}


			}


		else
			{
			echo "File does not exist"; 
		//	header("Location: uploaderror.php");
			}



?>
