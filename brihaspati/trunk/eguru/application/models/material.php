<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class material extends CI_Model {

    private $errors = array();

    function __construct() {
        $this->load->database();
        $this->load->helper('url');
       
    }

    public function errors() {
        return $this->errors;
    }

    public function has_errors() {
        return count($this->errors) > 0;
    }

    public function all_subjects_extract($department) {
        $this->load->database();
        $subjects_query = $this->db->get_where('subjects', array('department_id' => $department));
        $subjects_result = $subjects_query->result_array();
        return $subjects_result;
    }

    public function all_department_extract() {
        $this->load->database();
        $departments = $this->db->get('departments');
        return $departments;
    }

    public function department_hod_subject_add($subject) {
        $user_id = $_SESSION['user_id'];
        $this->load->database();
        $user_details = $this->db->get_where('dept_hods', array('user_id' => $user_id));
        $department = $user_details->result_array();
        //$department_id=$department['0'];
        $this->db->insert('subjects', array('name' => $subject, 'department_id' => $department['0']['department_id']));
    }

    public function department_hod_subject_edit($subjects) {
        $user_id = $_SESSION['user_id'];
        $this->load->database();
        $user_details = $this->db->get_where('dept_hods', array('user_id' => $user_id));
        $department = $user_details->result_array();
        $this->db->where('department_id', $department['0']['department_id']);
        $this->db->where('id', $subjects['id']);
        $this->db->update('subjects', array('name' => $subjects['subject']));
    }

    public function admin_department_add($department) {
        $this->load->database();
        $this->db->insert('departments', array('name' => $department));
    }

    public function admin_department_edit($departments) {
        $this->load->database();
        $this->db->where('id', $departments['id']);
        $this->db->update('departments', array('name' => $departments['department']));
    }
public function department_hod_subject_delete($subjects){
  $user_id = $_SESSION['user_id'];
        $this->load->database();
        $user_details = $this->db->get_where('dept_hods', array('user_id' => $user_id));
        $department = $user_details->result_array();
        $this->db->where('department_id', $department['0']['department_id']);
        $this->db->where('id', $subjects['id']);
        $this->db->delete('subjects');  
}
public function admin_department_delete($departments) {
        $this->load->database();
        $this->db->where('id', $departments['id']);
        $this->db->update('departments');
    }
    public function student_topic_submit($topic) {
        $user_id = $_SESSION['user_id'];
        $this->load->database();
        $query_details = $this->db->get_where('topics', array('name' => $topic));
	if (!($query_details->num_rows()))
		return 0;	
        $result_details = $query_details->result_array();
        $topic_id_details = $result_details['0'];
        $topic_id = $topic_id_details['id'];
        $subject_id = $topic_id_details['subject_id'];
        $query_details = $this->db->get_where('students', array('user_id' => $user_id));
        $result_details = $query_details->result_array();
        $hardness_type_details = $result_details['0'];
        $hardness = $hardness_type_details['hardness'];
        $type = $hardness_type_details['type'];
	$_SESSION['topic']=$topic_id;
        $query_details = $this->db->get_where('materials', array('hardness' => $hardness, 'topic_id' => $topic_id, 'type' => $type));
        $dash_details['query_result'] = $query_details;
        $dash_details['subject_id'] = $subject_id;
        $dash_details['hardness'] = $hardness;
        return $dash_details;
    }

    public function update_mode($input) {
	$response=1;
        $user_id = $_SESSION['user_id'];
        $this->load->database();
        $this->db->where('user_id', $user_id);
        if (!(isset($input['type'])))
            $this->db->update('students', array('hardness' => $input['hardness']));
        else if (!(isset($input['hardness'])))
            $this->db->update('students', array('type' => $input['type']));
        else
            $this->db->update('students', array('hardness' => $input['hardness'], 'type' => $input['type']));
	if (isset($_SESSION['topic'])){	
		$topic_id= $_SESSION['topic'];
		 if (!(isset($input['type'])))
            		$query=$this->db->get_where('materials', array('hardness' => $input['hardness'],'topic_id'=>$topic_id));
        	else if (!(isset($input['hardness'])))
            		$query=$this->db->get_where('materials', array('type' => $input['type'],'topic_id'=>$topic_id));
        	else
            		$query=$this->db->get_where('materials', array('hardness' => $input['hardness'], 'type' => $input['type'],'topic_id'=>$topic_id));

		if(!($query->num_rows())){
			$response=0;		
		}
	}
	return $response;
    }

    public function material_submit_sub_hod($input) {
        $user_id = $_SESSION['user_id'];
        $this->load->database();
        $this->db->where('user_id', $user_id);
        $query = $this->db->get('sub_hods');
        $query_result = $query->result_array();
        $result = $query_result['0'];
        $topic = $input['topic'];
        $harndess = $input['hardness'];
        $type = $input['type'];
        $this->db->insert('topics', array('name' => $topic, 'subject_id' => $result['subject_id']));
        $query = $this->db->get_where('topics', array('name' => $topic));
        $query_result = $query->result_array();
        $result_topic_id = $query_result['0'];
        $topic = end(explode(" ", $topic));
        if ($input['link'] == NULL) {
            $config['upload_path'] = dirname($_SERVER["SCRIPT_FILENAME"]) . "/uploads/" . $result['subject_id'] . "/";
            $config['upload_url'] = "uploads/" . $result['subject_id'] . "/";
            $config['allowed_types'] = '*';
            $config['max_size'] = '100000KB';
            $config['max_width'] = '1024';
            $config['max_height'] = '768';
            $config['remove_spaces'] = true;
            $config['overwrite'] = true;
            $config['encrypt_name'] = true;
            $ext = end(explode(".", $_FILES['userfile']['name']));
            $config['file_name'] = $topic . rand(0, 99999999) . "." . $ext;
            $this->load->library('upload', $config);
            $file_name = $config['file_name'];
            if (!$this->upload->do_upload()) {
                //upload failed
                $this->errors[] = $this->upload->display_errors();
                return;
            }
            $data = array('sub_hod_id' => $result['id'], 'topic_id' => $result_topic_id['id'], 'file_name' => $file_name, 'type' => $type, 'hardness' => $harndess,'subject_id'=>$result['subject_id']);
        } else {
            $link = $input['link'];
            $data = array('sub_hod_id' => $result['id'], 'topic_id' => $result_topic_id['id'], 'link' => $link, 'type' => $type, 'hardness' => $harndess,'subject_id'=>$result['subject_id']);
        }
        $this->db->insert('materials', $data);
    }

    public function to_delete($material_id) {
        $query = $this->db->get_where('materials', array('id' => $material_id['id']));  
        $this->db->where('id', $material_id['id']);	
        $this->db->delete('materials');
	if ($query->num_rows() == 1){
        	$query_result = $query->result_array();
		$result = $query_result[0];
        	$this->db->where('id', $result['topic_id']);
        	$this->db->delete('topics');
	}
        return TRUE;
    }

    public function to_edit($material_id) {
        $query = $this->db->query("SELECT materials.id,name,file_name,link,type,hardness FROM materials JOIN topics ON topics.id = materials.topic_id WHERE materials.id =$material_id");
        $query_result = $query->result_array();
        $topic_details = $query_result['0'];
        return $topic_details;
    }

    public function editting_of_material($input) {
        $query = $this->db->get_where('materials', array('id' => $input['id']));
        $query_result = $query->result_array();
        $topic_details = $query_result['0'];
        $data = array('name' => $input['topic']);
        $this->db->where('id', $topic_details['topic_id']);
        $this->db->update('topics', $data);
        if (isset($input['link'])) {
            $data1 = array('link' => $input['link'], 'hardness' => $input['hardness'], 'type' => $input['type']);
        } else if ($_FILES['userfile']['name'] != NULL) {
            $query = $this->db->get_where('topics', array('id' => $topic_details['topic_id']));
            $query_result = $query->result_array();
            $result = $query_result['0'];
            $config['upload_path'] = dirname($_SERVER["SCRIPT_FILENAME"]) . "/uploads/" . $result['subject_id'] . "/";
            $config['upload_url'] = "uploads/" . $result['subject_id'] . "/";
            $config['allowed_types'] = '*';
            $config['max_size'] = '100000KB';
            $config['max_width'] = '1024';
            $config['max_height'] = '768';
            $config['remove_spaces'] = true;
            $config['overwrite'] = true;
            $config['encrypt_name'] = true;
            $ext = end(explode(".", $_FILES['userfile']['name']));
            $config['file_name'] = $input['topic'] . rand(0, 99999999) . "." . $ext;
            $this->load->library('upload', $config);
            $file_name = $config['file_name'];
            if (!$this->upload->do_upload()) {
                //upload failed
                $this->errors[] = $this->upload->display_errors();
                return;
            }

            $data1 = array('file_name' => $file_name, 'hardness' => $input['hardness'], 'type' => $input['type']);
        } else {
            $data1 = array('hardness' => $input['hardness'], 'type' => $input['type']);
        }
        $this->db->where('id', $input['id']);
        $this->db->update('materials', $data1);
        return TRUE;
    }

}
