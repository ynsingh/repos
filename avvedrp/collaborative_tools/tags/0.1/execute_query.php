<?php
/**
* This file contains the class for executying the sql after establishing/ maintaining connection with the db
* @author Mukesh Kumar M
* @version 04-08-2010
*
*/


include('dbmanage.php');
//
class DbConnection
{
	private $host;
	private $db_name;
	private $user_name;
	private $password;
	private $con;
	public $sql;
	public $msg;
/**
* Constructor function - gives the connection parameters to the class
* @author Mukesh Kumar M
* @version 05-08-2010
*/
	function __construct($host1,$db_name1, $user_name1, $password1)
	{
		$this->host=$host1;
		$this->db_name=$db_name1;
		$this->user_name=$user_name1;
		$this->password=$password1;	
		$this->establishConnection();
		//this->$host=$host;		
	}
	function __destruct()
	{
		$this->closeConnection();
		//this->$host=$host;		
	}
	private function establishConnection()
	{
		$this->con=mysql_pconnect($this->host,$this->user_name,$this->password) or die($this->dbError(1));
		if($this->con)
		{
			mysql_select_db($this->db_name,$this->con) or die($this->dbError(1));
		}
	}
	//
	 //select query executes by using this function
	 //the argument return_type (may be 'OB' or 'AR') indicates whether the 
	 //data retrieved as array or Object
	 //by default it is object
	function querySelectReturn($return_type='DB')
	{
		if(!$this->con)
		{
			$this->establishConnection();
			
		}
		$arr_res=array();
		$Result = mysql_query($this->sql,$this->con) or die($this->dbError($this->msg,1));
		//mysql_close($con);
		if($Result)
		{
			if($return_type=='DB') //returns array
			{
				
				while ($row = mysql_fetch_row($Result))
				{
					$arr_res[]=$row;
				}
				mysql_free_result($Result);
				return $arr_res;
			}
			else if($return_type=='OB') //returns array of objects
			{
				while ($row = mysql_fetch_object($Result))
				{
					$arr_res[]=$row;
				}
				mysql_free_result($Result);
				return $arr_res;		
			}
		}
		else
		{
			return false;
		}
	}
	function queryUpdate() //update sql executes here
	{
		if(!$this->con)
		{
			$this->establishConnection();
		}
		$Result = mysql_query($this->sql,$this->con) or die($this->dbError($this->msg,1));
		//mysql_close($con);
		if($Result)
		{
			return true;
		}
	}
	function queryInsert() // insert query executes here
	{
		if(!$this->con)
		{
			$this->establishConnection();
		}
		$Result = mysql_query($this->sql,$this->con) or die($this->dbError($this->msg,1));
		//mysql_close($con);
		if($Result)
		{
			return true;
		}
	}
	function queryDelete() // delete query executes here
	{
		if(!$this->con)
		{
			$this->establishConnection();
		}
		$Result = mysql_query($this->sql,$this->con) or die($this->dbError($this->msg,1));
		//mysql_close($con);
		if($Result)
		{
			return true;
		}
	}
	function getLastInsertId()
	{
		if($last_insert_id= mysql_insert_id ($this->con))
		{
			return $last_insert_id;
		}
		else
		{
			return false;
		}
		//mysql_close($con);		
	}
	//
	function beginTans()
	{
		if(!$this->con)
		{
			$this->establishConnection();
		}
		mysql_query("BEGIN",$this->con) or die ($this->dbError($this->msg,1));
	}
	//
	function commitTrans()
	{
		if(!$this->con)
		{
			$this->establishConnection();
		}
		mysql_query("COMMIT",$this->con) or die ($this->dbError($this->msg,1));
	}
	//function to close the active connection
	function closeConnection()
	{
		if($this->con)
		{
			mysql_close($this->con);
		}
	}
	//
	private function dbError($msg,$type=0)
	{
		if($msg==1)
		{
			echo "db connection error";
		}
		else
		{
			echo "Error:".$msg;
		}
		//header("Location:".ROOT_URL."control/control.php?ct=".dbError."&msg=$msg&mt=$type");
	}
}
?>
