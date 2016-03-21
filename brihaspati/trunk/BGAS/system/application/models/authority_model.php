<?php

class Authority_model extends Model {

	function Authority_model()
	{
		parent::Model();
	}

	function get_authority_name($id)
	{
		$db1=$this->load->database('login', TRUE);
		$name = 0;
		$db1->from('authorities');
		$db1->select('name')->where('id', $id);
		$group = $db1->get();
		foreach($group->result() as $row)
		{
			$name = $row->name;
		}
		$db1->close();
		return $name;
	}

	function get_all_authorities()
	{
		$db1=$this->load->database('login', TRUE);
		$options = array();
	    	$options[0] = 'Please Select';
	    	$db1->from('authorities')->select('id,name');
	    	$auth_q = $db1->get();
	    	foreach ($auth_q->result() as $row)
	    	{
	            	$auth_id = $row->id;
	            	$options[$auth_id]=$row->name;

	    	}
	    	return $options;
	}
        function get_all_authorities_with_email()
        {
                $db1=$this->load->database('login', TRUE);
                $options = array();
          //  $options[0] = 'Please Select';
            $db1->from('authorities')->select('authority_email,name');
            $auth_q = $db1->get();
            foreach ($auth_q->result() as $row)
            {
                    $auth_id = $row->authority_email;
                    $options[$auth_id]=$row->name;

            }
            $options[0] = 'Please Select';
            return $options;
        }

	/**
	*Code to show Name, Designation and Type of Authority
	*/
	//added by @RAHUL
	function get_all_authorities_map_with_email()
        {
		$to_day = date("Y-m-d H:i:s");
		$today1 = explode(' ',$to_day);
		$today2 = $today1[0];
		$today3 = $today2." "."00:00:00";
                $db1=$this->load->database('login', TRUE);
                $options = array();
         	 //  $options[0] = 'Please Select';
                $db1->select('userprofile.firstname as firstname, userprofile.lastname as lastname, authorities.name as name, edrpuser.username as useremail, authority_map.authority_type as authoritytype');
                $db1->from('authority_map')->join('authorities', 'authority_map.authority_id = authorities.id')->join('userprofile', 'authority_map.user_id = userprofile.userid')->join('edrpuser', 'authority_map.user_id = edrpuser.id')->where('authority_map.map_date <=',$today3)->where('authority_map.till_date >=',$today3);
                $auth_q = $db1->get();
                foreach ($auth_q->result() as $row)
                { 
                 	$auth_id = $row->useremail."`".$row->firstname." ".$row->lastname."(".$row->name."/".$row->authoritytype.")";
			if ($row->authoritytype == 'full')
			{
                       		$options[$auth_id]=$row->name.'/'.$row->firstname.$row->lastname;
			}
			else
			{
				$options[$auth_id]=$row->authoritytype."&nbsp &nbsp".$row->name.'/'.$row->firstname.$row->lastname;
			}
                }
                $options[0] = 'Please Select';
                return $options;
        }

}
?>
