<?
function execute_query($query_stmt)
{
         global $hostname, $db_username, $db_password, $db_name;
           $link=mysql_pconnect($hostname,$db_username,$db_password) or die("Can't connect to  Database $db_name");
	   mysql_select_db($db_name, $link) or die("Can't select to : Database $db_name");
           $result = mysql_query($query_stmt);// or die("Can't execute query $query_stmt");
           return $result;
}

?>
