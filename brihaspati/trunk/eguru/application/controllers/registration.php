<?php

class registration extends CI_Controller {
    public function __construct() {
        parent::__construct();
        session_start();
        $this->load->helper('url');
    }

    public function form() {
        $data_dash= new stdClass();
        $this->load->model('material');        
        $data_dash->departments=$this->material->all_department_extract();      
        $this->load->view('reg_form',array('data'=>$data_dash));
    }
    public function all_subject_extract(){
        $department=$this->input->post();               
        $this->load->model('material');        
        $all_subjects=$this->material->all_subjects_extract($department['department']);
        $this->load->view('ajax_response', array('data' => $all_subjects));
    }

    public function student_submit() {
        $data = new stdClass();
        $student_unique_key = md5(uniqid());
        $user_unique_key = md5(uniqid());
        $this->load->model('user');      
        $student_array_register = $this->input->post();
        $student_array_register['type'] = 'student';
        $this->user->student_register($student_array_register, $student_unique_key, $user_unique_key);
        if ($this->user->has_errors()) {
            $errors = $this->user->errors();
            $data->success = FALSE;
            $data->errorThrown = $errors[0];
            $this->load->view('ajax_response', array('data' => $data));
            return;
        }
        $data->success = TRUE;
        $success_page = "/eguru/";
        $data->successPage = $success_page;
        $data->successMsg = 'Successfully registered';
        $this->load->view('ajax_response', array('data' => $data));
        //$registration_conf_link = base_url('/registration/student_confirmation/' . $id . '/' . urlencode($stu_unique_key));
    }

    public function apply_for_what() {
        $this->load->model('admin');
        $apply_for = $this->input->post();
        $this->admin_reg->apply_for_what($apply_for);
    }

    public function input_mode() {
        $data = new stdClass();
        $mode = $this->input->post();
        if ($mode == "false")
            $data->success = FALSE;
        $this->load->view('ajax_response', $data);
    }

    public function others_submit() {
        $data = new stdClass();
        $entries_others = $this->input->post();
        $this->load->model('user');
        $this->user->others_user_submit($entries_others);
        if ($this->user->has_errors()) {
            $errors = $this->user->errors();
            $data->success = FALSE;
            $data->errorThrown = $errors[0];
            $this->load->view('ajax_response', array('data' => $data));
            return;
        }
        $data->success = TRUE;
        $success_page = "/eguru/";
        $data->successPage = $success_page;
        $data->successMsg = 'Successfully registered';
        $this->load->view('ajax_response', array('data' => $data));
    }

}

?>
