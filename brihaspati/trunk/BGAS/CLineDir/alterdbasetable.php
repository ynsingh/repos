<?php
	$home=realpath('/home');
	$sysname=get_current_user();
	$fullpath=$home.'/'.$sysname.'/public_html/BGAS';	
	$path=$fullpath.'/config/accounts/';
	$accounts_list = scandir($path);
        if ($accounts_list)
        {
                foreach ($accounts_list as $row)
                {
                        /* Only include file ending with .ini */
                        if (substr($row, -4) == ".ini")
                        {
                                $ini_label = substr($row, 0, -4);
                                $ini_file = $path . $ini_label.".ini";
                                $lines = file($ini_file);
                                $dbhost=$lines[2];
                                $dbport=$lines[3];
                                $dbname=$lines[4];
                                $dbuname= $lines[5];
                                $dbpsw= $lines[6];
                                $dhost=explode('=',$dbhost);
                                if(isset($dhost[1])){}
                                $dbhst=$dhost[1];
                                $dport=explode('=',$dbport);
                                if(isset($dport[1])){}
                                $dbprt=$dport[1];

                                $dname = explode('=', $dbname);
                                if (isset($dname[1])){}
                                $dbn=$dname[1];
                                $duname = explode('=', $dbuname);
                                if (isset($duname[1])){}
                                $dunm=$duname[1];
                                $dpsw = explode('=', $dbpsw);
                                if (isset($dpsw[1])){}
                                $dbp=$dpsw[1];
                                $hst= trim(str_replace('"',"",$dbhst));
                                $dnm= trim(str_replace('"',"",$dbn));
                                $name= trim(str_replace('"',"",$dunm));
                                $psw= trim(str_replace('"',"",$dbp));
                                $conn =@mysql_connect($hst, $name, $psw);
                                $cdate= date("F d Y ");
                                if ($conn)
                                {
					$query= mysql_select_db(trim($dnm), $conn);
                                        if($query)
                                        {
						$sqlpath=$fullpath.'/config/sqlscripts/';	
                                                $sqlfile = scandir($sqlpath);
                                                $latest_ctime = 0;
                                                $latest_filename = array();
                                                $d = dir($sqlpath);
                                                while (false !== ($entry = $d->read())) {
							if (substr($entry, -4) == ".sql")
							{
								echo"$entry\n";	
                                                        	$filepath = "{$sqlpath}{$entry}";
                                                        	if (is_file($filepath) && filectime($filepath) > $latest_ctime) {
                                                                	$latest_ctime = date("F d Y " ,filectime($filepath));
                                                                	if($cdate == $latest_ctime)
                                                                	{
                                                                        	$latest_filename[] = $entry;
                                                                	}
                                                        	}
							}
                                                }
                                                if($latest_filename)
                                                {
                                                        foreach ($latest_filename as $file)
                                                        {
                                                                $rdfile= $sqlpath. $file;
                                                                if (pathinfo($rdfile))
                                                                {
                                                                        $contents = file_get_contents($rdfile);
                                                                        $statements = explode(";\n", $contents);
                                                                        foreach ($statements as $query)
                                                                        {
                                                                                if(trim($query) != '') {
                                                                                mysql_query($query);
                                                                                }
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                } else {
                                        echo ('Not connect to database.' . mysql_error(). 'error');
                                        return;
                                }
				  mysql_close();
                         }
                }
        }
?>
