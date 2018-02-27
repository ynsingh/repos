<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Upload_model extends CI_Model {
	function __construct() {
        parent::__construct();
       }
	var $upload_path;
        var $scopy_path_url;

        function Upload_model()
        {
                parent::Model();
		//$this->load->library('image_lib');
        }
	
	function scancopy_fileupload($filenme,$docno, $patyuid)
        {
                //set path
                $this->upload_path= realpath(BASEPATH.'../uploads/BGAS/scopy');
              
                if (!is_dir($this->upload_path)) {
                $this->messages->add('Please give Permission to the BGAS directory manually by ru command(chmod 777 ~ BGAS)', 'success');
              
		//Code For Save Image With Database Name And Uploaded File Extension...
		$config['upload_path'] = $this->upload_path;
		$config['allowed_types'] = 'gif|jpg|jpeg|png|pdf|';
                $config['max_size'] = '1000';
                $config['max_width'] = '1920';
                $config['max_height'] = '1280';

		$file = $_FILES[$filenme]['name'];
		
		$ext = substr(strrchr($file, '.'), 1);

		// full name  = partyid + documentno+ext
		$full_name = $patyuid.'-'.$docno.'.'.$ext;
		$config['file_name'] = $full_name ;
		//$this->messages->add(" the full name is ".$full_name);
                $this->load->library('upload', $config);
		$this->upload->initialize($config);
		if ( ! $this->upload->do_upload($filenme))
		{
			$error = array('error' => $this->upload->display_errors());
			//print_r($file);
		        $this->messages->add('Error uploading '.$full_name.' and path is .'.$this->upload_path. ' the original name is '.$file.' The error is '.$this->upload->display_errors(), 'error');
		}
		else {
                	$fInfo = $this->upload->data();
			$this->messages->add('Uploading '.$fInfo.' and path is .'.$this->upload_path, 'success');
		}
		
       		}
	return ;  
	}
	function record_count() 
	{
        	return $this->db->count_all("addsecondparty");
        }

	function get_values($pagination_counter,$page_count)
        {
                $data=array();
		$this->db->select('sacunit,partyname,partyrole,mobnum,email,bancacnum,bankname,branchname,ifsccode,pan,u_id,tan,staxnum,vat,gst,opbal');
		$this->db->from('addsecondparty');
                $pdetail = $this->db->get();
		$result = $pdetail->result();
	
		if ($pdetail->num_rows() > 0) 
		{
                	foreach ($pdetail->result() as $row) 
			{
                		$data[] = $row;
                	}
			return $data;
                }
		else
                	return false; 

        }

	function  get_count_num($data_partyrole)
	{
		$this->db->select('*')->from('addsecondparty')->where('partyrole' , $data_partyrole);
                $query = $this->db->get();
        	$num = $query->num_rows();
		return $num;
	}

	function get_random_secunitid($secunit_id,$number)
        {
                $i = 0;
                do{
                        $i++;
                        $num = sprintf('%08d' , $i);
                        $secunitid = $secunit_id . $num;
                }while($i <= $number);

        return $secunitid;
        }

}
?>

