<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class dash_details extends CI_Model {

    public function __construct() {
        parent::__construct();
        $this->load->database();
        $this->load->helper('url');
       
    }

    public function errors() {
        return $this->errors;
    }

    public function has_errors() {
        return count($this->errors) > 0;
    }

    public function department_hod() {
        $user_id = $_SESSION['user_id'];
        $query_dept = $this->db->get_where('dept_hods', array('status' => 'approved', 'user_id' => $user_id));
        $query_dept_dash = $query_dept->result_array();
        $query_dept_details = $query_dept_dash['0'];
        $subjects_query = $this->db->get_where('subjects', array('department_id' => $query_dept_details['department_id']));
        $query_subject_hods = $this->db->query("select sub_hods.id,name,sub_hods.department_id,first_name,last_name,status,research_papers_publication,research_projects from sub_hods join subjects on subjects.id=subject_id where sub_hods.department_id='" . $query_dept_details['department_id'] . "'");
        $hod_dash_data['sub_hod_for_approve'] = $query_subject_hods;
        $hod_dash_data['data'] = $query_dept_details;
        $hod_dash_data['subjects'] = $subjects_query;
        return ($hod_dash_data);
    }

    public function subject_hod() {
        $user_id = $_SESSION['user_id'];
        $query_dept = $this->db->get_where('sub_hods', array('status' => 'approved', 'user_id' => $user_id));
        $query_dept_dash = $query_dept->result_array();
        $query_dept_details = $query_dept_dash['0'];
        return $query_dept_details;
    }

    public function subject_hod_topic_extract($subject_id) {
        /* $this->db->select('name,link,s_no,type,link,hardness,subject_id');
          $this->db->from('materials');
          $this->db->join('topics','topics.id=materials.id');
          $query_topic=$this->db->get(); */
        $query_topic = $this->db->query("SELECT materials.id,name,file_name,link,type,hardness FROM materials JOIN topics ON topics.id = materials.topic_id WHERE materials.subject_id =$subject_id");
        $topic_details = $query_topic->result_array();
        $topics_topic_list['topics'] = NULL;
        if ($query_topic->num_rows()) {
            for ($i = 0; $i < $query_topic->num_rows(); $i++)
                $topics_subject[$i]['label'] = $topic_details[$i]['name'];
            $topics_topic_list['topics'] = $topics_subject;
        }
        $topics_topic_list['topics_list'] = $query_topic;
        return($topics_topic_list);
    }

    public function approval_sub_hod($sub_hod_details) {
        $this->load->database();
        $id = $sub_hod_details['id'];
        $subject_query = $this->db->get_where('subjects', array('name' => $sub_hod_details['subject']));
        $subject_result = $subject_query->result_array();        
        if ($sub_hod_details['status'] == 'approved') {
            $data = array(
                'status' => 'unapproved'
            );
            $this->db->where('id', $id);
            $this->db->update('sub_hods', $data);
            $this->db->where('name', $sub_hod_details['subject']);
            $data = array('incharge_set' => 0);
            $this->db->update('subjects', $data);
        } else if ($subject_result['0']['incharge_set'] == 0) {
            $data = array(
                'status' => 'approved'
            );
            $this->db->where('id', $id);
            $this->db->update('sub_hods', $data);            
            $this->db->where('name', $sub_hod_details['subject']);
            $data = array('incharge_set' => 1);
            $this->db->update('subjects', $data);
            $data=array('sub_hod_id'=>$id);
            $this->db->where('subject_id',$subject_result['0']['id']);
            $this->db->update('materials',$data);
        }
        else
        {
            $response['response']=0;
        }
        $response['response'] = "done";
        return ($response);
    }

    public function approval_dept_hod($dept_hod_details) {
        $this->load->database();
        $id = $dept_hod_details['id'];
        $department_query = $this->db->get_where('departments', array('name' => $dept_hod_details['department']));
        $department_result = $department_query->result_array();        
        if ($dept_hod_details['status'] == 'approved') {
            $data = array(
                'status' => 'unapproved'
            );
            $this->db->where('id', $id);
            $this->db->update('dept_hods', $data);
            $this->db->where('name', $dept_hod_details['department']);
            $data = array('incharge_set' => 0);
            $this->db->update('departments', $data);
        } else if ($department_result['0']['incharge_set'] == 0) {
            $data = array(
                'status' => 'approved'
            );
            $this->db->where('id', $id);
            $this->db->update('dept_hods', $data);            
            $this->db->where('name', $dept_hod_details['department']);
            $data = array('incharge_set' => 1);
            $this->db->update('departments', $data);            
        }
        else
        {
            $response['response']=0;
        }
        $response['response'] = "done";
        return ($response);
    }

    public function admin() {
        $this->load->database();
        $query_admin_dash_data = $this->db->query("select dept_hods.id,first_name,last_name,departments.name,status,research_papers_publication,research_projects from dept_hods join departments on departments.id=department_id");
        $query_admin_dpartments = $this->db->get('departments');
        $query_admin['data'] = $query_admin_dash_data;
        $query_admin['departments'] = $query_admin_dpartments;
        return $query_admin;
    }

    public function student() {
        $user_id = $_SESSION['user_id'];
        $this->load->database();
        $query_details = $this->db->get_where('students', array('user_id' => $user_id));
        $result_details = $query_details->result_array();
        $details = $result_details['0'];
        return($details);
    }

    public function student_stream_select($stream) {
        $query_details = $this->db->get_where('subjects', array('department_id' => $stream));
        return($query_details);
    }

    public function student_topic_extract($subject) {
        $query_details = $this->db->get_where('subjects', array('id' => $subject));
        $result_details = $query_details->result_array();
        $details = $result_details['0'];
        $subject_id = $details['id'];
        $student_details = $this->student();
        $type = $student_details['type'];
        $hardness = $student_details['hardness'];
        $query_details = $this->db->query("select name from topics join materials on topics.id=materials.topic_id where topics.subject_id=$subject_id and hardness='$hardness' and type='$type'");
        $topic_details = $query_details->result_array();
        $topics_subject = NULL;
        if ($query_details->num_rows()) {
            for ($i = 0; $i < $query_details->num_rows(); $i++)
                $topics_subject[$i]['label'] = $topic_details[$i]['name'];
        }
        return( $topics_subject);
    }

}

?>
