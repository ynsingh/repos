<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class admin extends CI_Controller {

    public function __construct() {
        parent::__construct();
        session_start();
        $this->load->helper('security');
        $this->load->helper('file');
        $this->load->helper('url');
        /* $config['upload_path'] = dirname($_SERVER["SCRIPT_FILENAME"])."/uploads/";
          $config['upload_url']=base_url()."uploads/";
          $config['allowed_types'] = '*';
          $config['overwrite']=True;
          $config['max_size'] = '1000000KB';
          $config['max_width'] = '1024';
          $config['max_height'] = '768';

          $this->load->library('upload', $config); */
    }

    public function index() {
        $this->load->view('admin_login');
    }

    public function login() {
        $data = new stdClass();
        $admin_array = $this->input->post();         
        $this->load->model('user');
        if ($admin_array['type'] === 'admin' && $admin_array['email'] === 'admin@eguru.com' && $admin_array['password'] === 'qwerty') {
            $_SESSION['email'] = 'admin';
            $_SESSION['type'] = 'admin';
            $data->success = TRUE;
            $data->success_page = '/admin/profile_admin/';
            $this->load->view('ajax_response', array('data' => $data));
            return;
        }
        $query_name = $this->user->admin_login($admin_array);
        if ($this->user->has_errors()) {
            $errors = $this->user->errors();
            $data->success = FALSE;
            $data->errorThrown = $errors[0];
            $this->load->view('ajax_response', array('data' => $data));
            return;
        }
        $_SESSION['user_id'] = $query_name['user_id'];
        $_SESSION['email'] = $admin_array['email'];
        if ($admin_array['type'] == 'dept_hod') {
            $_SESSION['type'] = 'dept_hod';
            $success_page = '/admin/profile_department_hod';
        } else {
            $_SESSION['type'] = 'sub_hod';
            $success_page = '/admin/profile_subject_hod';
        }
        $data->success = TRUE;
        $data->success_page = $success_page;
        $this->load->view('ajax_response', array('data' => $data));
    }

    public function approve_sub_hod() {
        $sub_hod_details = $this->input->post();
        $this->load->model('dash_details');
        $response_details = $this->dash_details->approval_sub_hod($sub_hod_details);
        $ajax_response_details['data'] = $response_details;
        $this->load->view('ajax_response', $ajax_response_details);
    }

    public function approve_dept_hod() {
        $dept_hod_details = $this->input->post();
        $this->load->model('dash_details');
        $response_details = $this->dash_details->approval_dept_hod($dept_hod_details);
        $ajax_response_details['data'] = $response_details;
        $this->load->view('ajax_response', $ajax_response_details);
    }

    public function profile_department_hod() {
        if (!isset($_SESSION['email'])) {
            header("Location:/eguru/");
            return;
        }
        $this->load->model('dash_details');
        $query_department_hods = $this->dash_details->department_hod();
        $data = $query_department_hods['data'];
        $sub_hod_for_approve = $query_department_hods['sub_hod_for_approve'];
        $subjects = $query_department_hods['subjects'];
        $this->load->view('dashboard_dept_hod', array('data' => $data, 'data_dash' => $sub_hod_for_approve, 'subjects' => $subjects));
    }

    public function profile_subject_hod() {
        if (!isset($_SESSION['email'])) {
            header("Location:/eguru/");
            return;
        }
        $this->load->model('dash_details');
        $sub_hod_details = $this->dash_details->subject_hod();
        $subject_id = $sub_hod_details['subject_id'];
        $dash_details = $this->dash_details->subject_hod_topic_extract($subject_id);
        $topics = $dash_details['topics'];
        $topic_list = $dash_details['topics_list'];
        $this->load->view('dashboard_sub_hod', array('data_autocomplete' => $topics, 'data' => $sub_hod_details, 'topics_list' => $topic_list));
    }

    public function profile_admin() {
        if (!isset($_SESSION['email'])) {
            header("Location:/eguru/");
            return;
        }
        $this->load->model('dash_details');
        $query_admin = $this->dash_details->admin();        
        $this->load->view('dashboard_admin', array('data'=>$query_admin['data'],'departments'=>$query_admin['departments']));
    }

    public function subject_add() {
        $data = new stdClass();
        $subject = $this->input->post();
        $this->load->model('material');
        $this->material->department_hod_subject_add(ucfirst($subject['add_subject']));
        $data->success = TRUE;
        $data->successMsg = "Succesfully added";
        $this->load->view('ajax_response', array('data' => $data));
    }

    public function subject_edit() {
        $data = new stdClass();
        $subjects = $this->input->post();
        $this->load->model('material');
        $this->material->department_hod_subject_edit($subjects);
        $data->success = TRUE;
        $data->successMsg = "Succesfully editted";
        $this->load->view('ajax_response', array('data' => $data));
    }

    public function department_add() {
        $data = new stdClass();
        $department = $this->input->post();
        $this->load->model('material');
        $this->material->admin_department_add(ucfirst($department['add_department']));
        $data->success = TRUE;
        $data->successMsg = "Succesfully added";
        $this->load->view('ajax_response', array('data' => $data));
    }
    public function department_edit() {
        $data = new stdClass();
        $departments = $this->input->post();
        $this->load->model('material');
        $this->material->admin_department_edit($departments);
        $data->success = TRUE;
        $data->successMsg = "Succesfully editted";
        $this->load->view('ajax_response', array('data' => $data));
    }
    public function subject_delete(){
        $data = new stdClass();
        $subjects = $this->input->post();
        $this->load->model('material');
        $this->material->department_hod_subject_delete($subjects);
        $data->success = TRUE;
        $data->successMsg = "Succesfully deleted";
        $this->load->view('ajax_response', array('data' => $data));
    }    
    public function department_delete() {
        $data = new stdClass();
        $departments = $this->input->post();
        $this->load->model('material');
        $this->material->admin_department_delete($departments);
        $data->success = TRUE;
        $data->successMsg = "Succesfully deleted";
        $this->load->view('ajax_response', array('data' => $data));
    }
    public function material_upload_subject_hod() {
        $data = new stdClass();
        $input = $this->input->post();
        $this->load->model('material');
        $this->material->material_submit_sub_hod($input);
        if ($this->material->has_errors()) {
            $errors = $this->material->errors();
            $data->success = FALSE;
            $data->errorThrown = $errors[0];
            $this->load->view('ajax_response', array('data' => $data));
            return;
        }
        $data->success = TRUE;
        $data->successMsg = "Succesfully uploaded";
        $this->load->view('ajax_response', array('data' => $data));
    }

    public function delete_material() {
        $material_id = $this->input->post();
        $this->load->model('material');
        $response_details['response'] = $this->material->to_delete($material_id);
        $this->load->view('ajax_response', array('data' => $response_details));
    }

    public function material_edit() {
        if (!isset($_SESSION['email'])) {
            header("Location:/eguru/");
            return;
        }
        $this->load->model('dash_details');
        $sub_hod_details = $this->dash_details->subject_hod();
        $subject_id = $sub_hod_details['subject_id'];
        $dash_details = $this->dash_details->subject_hod_topic_extract($subject_id);
        $topics = $dash_details['topics'];
        $input = $this->input->post();
        $material_id = $input['id'];
        $this->load->model('material');
        $data_to_edit = $this->material->to_edit($material_id);
        $data_to_edit['success'] = TRUE;
        $this->load->view('dashboard_for_edit', array('data_autocomplete' => $topics, 'data_to_edit' => $data_to_edit, 'data' => $sub_hod_details));
    }

    public function material_for_edit_submit() {
        $input = $this->input->post();
        $this->load->model('material');
        $response_details['response'] = $this->material->editting_of_material($input);
        $this->load->view('ajax_response', array('data' => $response_details));
    }

}
