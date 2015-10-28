<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class student extends CI_Controller {

    public function __construct() {
        parent::__construct();
        session_start();
        $this->load->helper('url');
    }

    public function index() {
        $this->load->view('profile');
    }

    public function login() {
        $data = new stdClass();
        $student_array = $this->input->post();
        $this->load->model('user');
        $user_details = $this->user->student_login($student_array);
        if ($this->user->has_errors()) {
            $errors = $this->user->errors();
            $data->success = FALSE;
            $data->errorThrown = $errors[0];
            $this->load->view('ajax_response', array('data' => $data));
            return;
        }        
        $_SESSION['email'] = $student_array['email'];
        $_SESSION['user_id'] = $user_details['id'];
        $_SESSION['type']='student';
        $data->success = TRUE;
        $data->success_page = '/student/profile_student/';
        $this->load->view('ajax_response', array('data' => $data));
    }

    public function profile_student() {
        $this->load->model('dash_details');
        $data_dash = $this->dash_details->student();
        if ($data_dash['all_test_given'] == 0) {
            if ($data_dash['test1'] == 0)
               $this->test1_view($data_dash);
            else if ($data_dash['test2'] == 0 && $data_dash['test1'] == 1 && $data_dash['type'] == 'text')
                $this->test2_view($data_dash);
            else if ($data_dash['test3'] == 0 && $data_dash['test1'] == 1 && $data_dash['type'] !== 'text')
                $this->test3_view($data_dash);                  
            return;
        }
        $this->view_select_stream($data_dash);
    }
public function test1_view($data_dash){
        $this->load->view('questions1', array('data' => $data_dash));
    }
    public function test1() {       
        $test_answers = $this->input->post();
        $this->load->model('exams');
        $response = $this->exams->test1_submit($test_answers);
        $this->load->model('dash_details');
        $data_dash = $this->dash_details->student();
        if ($response == 'text') {
            $this->test2_view($data_dash);
        } else {
            $this->test3_view($data_dash);
        }
    }
     public function test2_view($data_dash){
        $this->load->view('questions2', array('data' => $data_dash));
    }

    public function test2() {       
        $test_answers = $this->input->post();
        $this->load->model('exams');
        $this->exams->test2_submit($test_answers);
        $this->load->model('dash_details');
        $data_dash = $this->dash_details->student();        
        $this->test3_view($data_dash);       
    }
    public function test3_view($data_dash){
        $this->load->view('questions3', array('data' => $data_dash));
    }

    public function test3() {
        $test_answers = $this->input->post();
        $this->load->model('exams');
        $this->exams->test3_submit($test_answers);
        $this->load->model('dash_details');
        $data_dash = $this->dash_details->student();                
        $this->view_select_stream($data_dash);
    }
    public function view_select_stream($data_dash){
        $this->load->view('select_stream',array('data' => $data_dash));
    }

    public function dashboard($stream){
        if(!isset($_SESSION['email'])){
            header("Location:/eguru/");
            return;
        }
        $this->load->model('dash_details');
        $data_dash = $this->dash_details->student();        
        $subjects=$this->dash_details->student_stream_select($stream);
        $data_dash['subjects']=$subjects;
        $data_dash['stream']=$stream;
        $this->load->view('dashboard_student',array('data' => $data_dash));//,'stream'=>$stream));;
    }
    public function subject_selection(){
        $input=$this->input->post();        
        $subject=$input['sub'];
        $this->load->model('dash_details');
        $topics=$this->dash_details->student_topic_extract($subject);
        $this->load->view('ajax_response',array('data'=> $topics));
    }
    public function topic_material(){
	$data=new stdClass();
        if(!isset($_SESSION['email'])){
            header("Location:/eguru/");
            return;
        }
        $this->load->model('dash_details');
        $data_dash = $this->dash_details->student();
        $input=$this->input->post();
        $topic=$input['topic'];       
        $this->load->model('material');
        $material_details=$this->material->student_topic_submit($topic);
	if (!$material_details)
		header("Location:/student/profile_student");
        $material=$material_details['query_result'];
        $data_dash['subject_id']=$material_details['subject_id'];
        $data_dash['hardness']=$material_details['hardness'];
        $data_dash['topic']=$input['topic'];
        $this->load->view('student_materials',array('material'=>$material,'data'=>$data_dash));
    }    
    public function mode_change(){         
        $data = new stdClass();
        $this->load->model('material');
        $input=$this->input->post();
	$response=$this->material->update_mode($input);        
	$data->topic=$input['topic'];
        $data->path="/student/topic_material";
	$data->response=$response;
        $this->load->view('ajax_response',array('data'=>$data));
    }
}
?>
