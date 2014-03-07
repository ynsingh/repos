<?php

class Setting_model extends Model {

	var $upload_path;
	var $logo_path_url;
	function Setting_model()
	{
		parent::Model();
		$this->upload_path= realpath(BASEPATH.'../uploads/logo');
		$this->logo_path_url=base_url().'uploads/logo';
	}

	function get_current()
	{
		$this->db->from('settings')->where('id', 1);
		$account_q = $this->db->get();
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
}
