<?php

class User_model extends CI_Model {
  function __construct() {
        parent::__construct();
 }

	function get_user_name($userid)
	{
		$db1=$this->load->database('login', TRUE);
		$name = 0;
		$db1->from('userprofile');
		$db1->select('firstname, lastname')->where('userid', $userid);
		$group = $db1->get();
		if($group->num_rows() > 0)
		{
			foreach($group->result() as $row)
			{
				$firstname = $row->firstname;
				$lastname = $row->lastname;
			}
			$db1->close();
			$name = $firstname. " ".$lastname;
		}
		else
		{
			$name = '';
		}
		return $name;
	}

	function get_user_email($id)
	{
		$user_email = '';
		$db1=$this->load->database('login', TRUE);
		$db1->from('edrpuser');
		$db1->select('email')->where('id', $id);
		$group = $db1->get();
		foreach($group->result() as $row)
		{
			$user_email = $row->email;
		}
		$db1->close();
		return $user_email;

	}

	//Returns id of user according to email. added by @RAHUL
	function get_user_id($email)
        {
                $user_id = '';
                $db1=$this->load->database('login', TRUE);
                $db1->from('edrpuser');
                $db1->select('id')->where('username', $email);
                $group = $db1->get();
                foreach($group->result() as $row)
                {
                        $user_id = $row->id;
                }
                $db1->close();
                return $user_id;

        }

	function get_all_users()
	{
		$db1=$this->load->database('login', TRUE);
		$options = array();
	    	$options[0] = 'Please Select';
	    	$db1->from('edrpuser')->select('id,username,email')->where("(componentreg='*' OR componentreg='BGAS')", NULL, FALSE);
	    	$user_q = $db1->get();
	    	foreach ($user_q->result() as $row)
	    	{
	            	$user_id = $row->id;
	            	$options[$user_id]=$row->username;
		}
	    	return $options;
	}
	
	//Returns name according to email. added by @RAHUL
	function get_user_name_femail($email)
        {
       		$fids = $this->get_user_id($email);
		$fname = $this->get_user_name($fids);
		return $fname;
	}	
}
?>
