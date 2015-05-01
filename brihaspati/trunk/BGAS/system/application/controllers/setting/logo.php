<?php

class Logo extends Controller {
	var $upload_path;
	var $logo_path_url;
	function Logo()
	{
		parent::Controller();
		$this->load->model('Setting_model');
		$this->load->helper(array('form', 'url'));
		$this->load->database();
		$this->load->library('image_lib');
		//set path
		$this->upload_path= realpath(BASEPATH.'../uploads/logo');
		 $this->logo_path_url=base_url().'uploads/logo';
		/* Check access */
		if ( ! check_access('upload logo'))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('');
			return;
		}

		return;
	}

	function index()
	{
		$this->template->set('page_title', 'Upload Logo');
		$account_data = $this->Setting_model->get_current();
		$this->upload_path1= realpath(BASEPATH.'../');
		if (!is_dir($this->upload_path1 . "/uploads")) {
		$this->messages->add('Please give Permission to the BGAS directory manually by ru command(chmod 777 ~ BGAS)', 'success');
		}
		
		$this->load->helper('file');
		/* Form fields */
		$data['ins_name'] = array(
			'name' => 'ins_name',
			'id' => 'ins_name',
			'maxlength' => '255',
			'size' => '40',
			'value' => '',
		);

		$data['dept_name'] = array(
			'name' => 'dept_name',
			'id' => 'dept_name',
			'maxlength' => '255',
			'size' => '40',
			'value' => '',
		);
		$data['uni_name'] = array(
			'name' => 'uni_name',
			'id' => 'uni_name',
			'maxlength' => '255',
			'size' => '40',
			'value' => '',
		);
		$data['logofile'] = array(
			'name' => 'logofile',
			'id' => 'logofile',
			'value' => '',
		);
		if ($account_data)
		{
			$data['ins_name']['value'] = ($account_data->ins_name) ? print_value($account_data->ins_name) : '';
			
			$data['dept_name']['value'] = ($account_data->dept_name) ? print_value($account_data->dept_name) : '';
			$data['uni_name']['value'] = ($account_data->uni_name) ? print_value($account_data->uni_name) : '';
		}
                
		/* Form validations */
		$this->form_validation->set_rules('ins_name', 'Institute name', 'trim|max_length[255]');
		$this->form_validation->set_rules('dept_name', 'Dept name', 'trim|max_length[255]');
		$this->form_validation->set_rules('uni_name', 'Unit name', 'trim|max_length[255]');

		/* Repopulating form */
		if ($_POST)
		{
			$data['ins_name']['value'] = $this->input->post('ins_name', TRUE);
			$data['dept_name']['value'] = $this->input->post('dept_name', TRUE);
			$data['uni_name']['value'] = $this->input->post('uni_name', TRUE);
		}
		
		/* Validating form */
		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('template', 'setting/logo', $data);
			return;
		}
		else
		{
			$data_ins_name = $this->input->post('ins_name', TRUE);
			$data_dept_name = $this->input->post('dept_name', TRUE);
			$data_uni_name = $this->input->post('uni_name', TRUE);
		        //Code For Save Image With Database Name And Uploaded File Extension...
			$file_name = $_FILES["userfile"]["name"];
			$size = $_FILES["userfile"]["size"] / 1024;
			$this->db->select('name')->from('settings')->where('ins_name',$data_ins_name);
                        $ins_id = $this->db->get();
                        foreach( $ins_id->result() as $row)
                        {
                                $name= $row->name;
                        }
			//Update Intitute name in bgasAccdata in login table.................
			$idl='';
                        $CI =& get_instance();
                        $CI->db->from('settings')->where('id', 1);
                        $settings_q = $CI->db->get();
                        $settings= $settings_q->row();
                        $ins_name = $settings->ins_name;
                        $uni_name = $settings->uni_name;

                        $date1 = explode("-", $settings->fy_start);
                        $date2 = explode("-", $settings->fy_end);
                        $year_start = $date1[0];
                        $year_end = $date2[0];
                        $curr_year = $year_start . "-" . $year_end;

                        $CI =& get_instance();
                        $db = $CI->load->database('login', TRUE);
                        $db->select('id');
                        $db->from('bgasAccData')->where('organization', $ins_name)->where('unit', $uni_name)->where('fyear', $curr_year);
                        $login_q = $db->get();
                        foreach ($login_q->result() as $row){
                                $idl=$row->id;
                        }
                        $db->trans_start();
                        $update_data = array(
                                'organization' => $data_ins_name,
                                'unit' => $data_uni_name
                        );
                        if (!$db->where('id', $idl)->update('bgasAccData', $update_data))
                        {
                                //$this->db1->trans_rollback();
                                $db->trans_rollback();
                                $this->messages->add('Error updating Institute and unit name in bgasAccData under login database.', 'error');
                                $this->logger->write_message("error", "Error updating Institute and unit name in bgasAccData under login database.");
                                $this->template->load('template', 'setting/logo', $data);
                                return;
                        } else {
                                $db->trans_complete();
                                $this->messages->add('Institute and unit name updated in bgasAccData under login database.', 'success');
                        }
                        $db->close();///////////////////.....................................

                        if($size)
                        {
				$ext = substr(strrchr($file_name, '.'), 1);
                        	$full_name =  $name . '.' .  $ext;
                        	$config['file_name'] = $full_name ;
				$file_list = get_filenames($this->upload_path);
				$len=count($file_list);
				for($count=0; $count<=$len; $count++)
				{
					$my_values = explode('.',$file_list[$count]);
					if($my_values[0] == $name)
					{
						unlink($this->upload_path.'/'.$file_list[$count]);  
					}
				}
				}else
                        	{
                        		$string=get_filenames($this->upload_path);
                       			$len1=count($string);
                        		for($count1=0; $count1<$len1; $count1++)
                        		{
					
						$my_values1 = explode('.',$string[$count1]);
						
                                		if($my_values1[0] == $name)
                                		{
							$config['file_name'] = $string[$count1] ;
							$this-> load->library('upload', $config);
                                		}
                        		}

			}//end
			
			
			/* Update settings */
			$this->db->trans_start();
			$update_data = array(
				'ins_name' => $data_ins_name,
				'dept_name' => $data_dept_name,
				'uni_name' => $data_uni_name
			);
			if ( ! $this->db->where('id', 1)->update('settings', $update_data))
			{
				$this->db->trans_rollback();
				$this->messages->add('Error updating Institute name and logo  settings.', 'error');
				$this->logger->write_message("error", "Error updating Institute name and logo settings");
				$this->template->load('template', 'setting/logo', $data);
				return;
			} else {
				$this->db->trans_complete();
				$this->messages->add('Institute name  updated.', 'success');
				$this->logger->write_message("success", "Updated Institute name ");
					//start upload
					$config = array(
                 		       		'allowed_types' => 'jpg|jpeg|gif|png|txt|doc|pdf|odt',
		                        	'upload_path' => $this->upload_path,	 
						'overwrite' => true,
                				);
                                	$size = $_FILES["userfile"]["size"] / 1024;
					$file =$_FILES['userfile']['name'];
					$ext = substr(strrchr($file, '.'), 1);
                	        	$full_name =  $name . '.' .  $ext;
					$config['file_name'] = $full_name ;
		                	$this-> load->library('upload', $config);
                		 
					if ( ! $this->upload->do_upload())
					{
						$error = array('error' => $this->upload->display_errors());
		        	                $this->messages->add('Error uploading  Institute logo  settings and path is .'.$this->upload_path.' The error is '.$this->upload->display_errors(), 'error');
                			        $this->logger->write_message("error", "Error uploading Institute logo settings");
		                        	$this->template->load('template', 'setting/logo', $data);
					
						return;
			                } else {
							$data = $this->upload->data();
							$max_height = 80;
							$max_width = 100;			
							/* Resize the uploaded image */
							$filename = $data['file_name'];
        	        	                	$config['image_library'] = 'gd2';
                                			$config['source_image'] = $data['file_path'] . $filename;
					        	$config['maintain_ratio'] = TRUE;
		                                	$config['width'] = $max_width;
							$config['height'] =$max_height;
							$this->image_lib->clear();
       							$this->image_lib->initialize($config);
		                                	$this->image_lib->resize();
							$this->messages->add('Institute logo  updated.', 'success');
							redirect('setting/logo');
							return ;
						}
			}//else 
			//get the id of this institute and accounting unit from bgasAccData under login
			//$db1=$this->load->database('login', TRUE);
			/*$db1->select('id')->from('bgasAccData')->where('organization',$data_ins_name )->where('unit',$data_uni_name);
	                $query = $db1->get();
                	if ($query->num_rows() > 0){
                       		foreach($query->result() as $row){
                                	$idl = $row -> id;
        	                }
                	}
                	else{
                        	$this->messages->add('Organization name and unit name not exist inside logo.php','error');
				$db1->close();
				redirect('setting/logo');
				return;
                	}
			*/
			// update data in bgasAccData under login database
			//his->db1->trans_start();
			/*$db1->trans_start();
                        $update_data = array(
                                'organization' => $data_ins_name,
                                'unit' => $data_uni_name
                        );
                        //if (!$this->db1->where('id', $idl)->update('bgasAccData', $update_data))
                        if (!$db1->where('id', $idl)->update('bgasAccData', $update_data))
                        {
                                //$this->db1->trans_rollback();
                                $db1->trans_rollback();
                                $this->messages->add('Error updating Institute and unit name in bgasAccData under login database.', 'error');
                                $this->logger->write_message("error", "Error updating Institute and unit name in bgasAccData under login database.");
                                $this->template->load('template', 'setting/logo', $data);
                                return;
                        } else {
                                //$this->db1->trans_complete();
                                $db1->trans_complete();
                                $this->messages->add('Institute and unit name updated in bgasAccData under login database.', 'success');
			}
			$db1->close();*/

		redirect('setting/logo');
		$this->template->load('template', 'setting/logo', $data);
		return;
		}
	}
/*
	function do_upload()
        {
                $config = array(
                        'allowed_types' => 'jpg|jpeg|gif|png|txt|doc|pdf|odt',
                        'upload_path' => $this->upload_path,
                        'max_size' => 2000
                );

                $this-> load->library('upload', $config);
		if ( ! $this->upload->do_upload()) {
			$this->messages->add('Error uploading  Institute logo  settings.', 'error');
			$this->logger->write_message("error", "Error uploading Institute logo settings");
                        $this->template->load('template', 'setting/logo', $data);
                        return;
		} else {
                	$data = $this->upload->data();
	                $config = array(
        	                'source_image' => $image_data['full_path'],
                	        'new_image' => $this->upload_path. '/thumbs',
                        	'maintain_ratios' => true,
	                        'width' => 150,
        	                'height' => 100
                	);
	                $this->load->library('image_lib', $config);
        	        $this->image_lib->resize();
		
//		$this->template->load('template', 'setting/logo', $data);
		}
        }
*/
}
/* End of file logo.php */
/* Location: ./system/application/controllers/setting/logo.php */
?>
