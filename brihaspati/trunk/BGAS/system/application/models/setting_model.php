<?php

class Setting_model extends Model {

	var $upload_path;
	var $logo_path_url;
	function Setting_model()
	{
		parent::Model();
		//$this->upload_path= realpath(BASEPATH.'../uploads/logo');
		//$this->logo_path_url=base_url().'uploads/logo';
	}

	function get_current()
	{
		$this->db->from('settings')->where('id', 1);
		$account_q = $this->db->get();
		$account_q->row();
		return $account_q->row();
	}

	function get_from_settings($key)
	{
		$value = '';
		$this->db->select($key);
		$this->db->from('settings')->where('id', 1);
		$query_result = $this->db->get();
		$result = $query_result->row();
		$value = $result->$key;
		return $value;
	}

/*	function do_upload()
	{
		$config = array(
			'allowed_types' => 'jpg|jpeg|gif|png|txt|doc|pdf|odt',
			'upload_path' => $this->upload_path,
			'max_size' => 2000

		);

		$this-> load->library('upload', $config);
		$this->upload->do_upload();
		$config = array(
			'source_image' => $image_data['full_path'],
			'new_image' => $this->upload_path. '/thumbs',
			'maintain_ratios' => true,
			'width' => 150,
			'height' => 100	
		);
		$this->load->library('image_lib', $config);
		$this->image_lib->resize(); 
	}
*/

	function get_logo()
	{
		$files = scandir($this->upload_path);
		$images = array();
		foreach ($files as $file){
			$images[]=array(
				'url' =>$this->logo_path_url . $file
			);	
		}
	return $images;
	}

	// code for aggregate balanacesheet

        function get_from_settings_agg($key,$accname)
        {
                /* connectivity with login database for getting the previous year database name */
                $db_name = '';
                $host_name = '';
                $db_username = '';
                $db_password = '';
		$db_name ='';
		$port = '';
                $db1=$this->load->database('login', TRUE);
                $db1->from('bgasAccData')->where('dblable',$accname);
                $db_name_q = $db1->get();
                //$db1->close();
                foreach ($db_name_q->result() as $row)
                {
                        $db_name = $row->databasename;
                        $db_username = $row->uname;
                        $db_password = $row->dbpass;
                        $host_name = $row->hostname;
                        $port = $row->port;
                }
		$value = '';
                if( $db_name_q->num_rows() == 1 ) {
                        $con = mysql_connect($host_name, $db_username, $db_password);
                        $op_balance = array();
                        if($con){
                                $value = mysql_select_db($db_name, $con);
                                $cl = "select * from settings where id = '1'";
                                $val = mysql_query($cl);
                                if($val != ''){
                                        while($row = mysql_fetch_assoc($val))
                                        {
                                               	$value=$row['ledger_name']; 
                                                //mysql_close($con);
                                                return $value;
                                        }
                                }
                        }
                }
		//$db->close();
/*
                $value = '';
                $this->db->select($key);
                $this->db->from('settings')->where('id', 1);
                $query_result = $this->db->get();
                $result = $query_result->row();
                echo "Value in setting Model". $value = $result->$key;
                return $value;
        }
*/
	}
}
