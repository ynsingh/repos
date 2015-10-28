<?php

//namespace Eguru\Helpers;

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class user extends CI_Model {

    private $errors = array();

    function __construct() {
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

    public function student_register($student_array_register, $student_unique_key, $user_unique_key) {
        $fname = $student_array_register['f_name'];
        $lname = $student_array_register['l_name'];
        $email = $student_array_register['email'];
        $password = $student_array_register['pass'];
        $number = $student_array_register['mobile'];
        $m_name = $student_array_register['m_name'];
        $institute_name = $student_array_register['institute'];
        $stream_name = $student_array_register['stream'];
        $user_type = $student_array_register['type'];
        $passing_year = $student_array_register['passing_year'];
        $specialisation = $student_array_register['specialization'];
        $high_qualify = $student_array_register['high_qualify'];
        $this->load->database();
        $query_status = $this->db->query("select * from users where email= '$email'");
        $user_status = $query_status->result_array();        
        if ($query_status->num_rows()) {
            $status_user = $user_status['0'];
            if ($status_user['status'] == 'unconfirmed' || $status_user['status'] == 'active') {
                $this->errors[] = "This email id is already registered ";
                return;
            }
        }
        $this->db->query("INSERT INTO users (email,password,type,unique_key)VALUES('$email',PASSWORD('$password'),'$user_type' ,'$user_unique_key')");
        $query_id = $this->db->query("select * from users where email= '$email'");
        $query_result = $query_id->result_array();
        $user_id_array = $query_result['0'];
        $user_id = $user_id_array['id'];
        $query_student_details = $this->db->query("INSERT INTO students (user_id,first_name,last_name,mothers_name,mobile,unique_key,institute,stream,passing_year,specialisation,high_qualify)VALUES($user_id,'$fname','$lname','$m_name','$number','$student_unique_key','$institute_name','$stream_name','$passing_year','$specialisation','$high_qualify')");
        return;
    }

    public function student_login($student_array) {
        $this->load->database();
        $email = $student_array['email'];
        $pass = $student_array['password'];        
        $user_logged = $this->db->query("select * from users where email='$email' and password=PASSWORD('$pass') and type='student'");      
        if (!($user_logged->num_rows())) {
            $this->errors[] = "Wrong Email id or Password or probably you are not a student";
            return;
        }
        $query_user = $user_logged->result_array();
        $user_details = $query_user['0'];
        return ($user_details);
    }
    
    public function others_user_submit($entries_others) {
        $this->load->database();
        $email = $entries_others['email'];
        $passwd = $entries_others['pass'];
        $type = $entries_others['apply_for'];
        $first_name = $entries_others['first_name'];
        $last_name = $entries_others['last_name'];
        $department_id = $entries_others['department'];
        if(isset($entries_others['subject']))
        $subject_id = $entries_others['subject'];
        $research_papers_publication = $entries_others['research_papers_publication'];
        $research_projects = $entries_others['research_projects'];
        $user_unique_key = md5(uniqid());
        $admin_unique_key = md5(uniqid());
        $query_status = $this->db->query("select * from users where email= '$email'");
        $user_status = $query_status->result_array();        
        if ($query_status->num_rows()) {
            $status_user=$user_status[0];
            if ($status_user['status'] == 'unconfirmed' || $status_user['status'] == 'active') {
                $this->errors[] = "This email id is already registered ";
                return;
            }
        }
        $this->db->query("insert into users (email,password,type,unique_key)values('$email',PASSWORD('$passwd'),'$type','$user_unique_key')");
        $query_id = $this->db->query("select id from users where email='$email'");
        $query_id_array = $query_id->result_array();        
        $id_result = $query_id_array[0];        
        $id=$id_result['id'];
        if ($type == "sub_hod") {
            $query = $this->db->query("insert into sub_hods (user_id,first_name,last_name,unique_key,department_id,subject_id,research_papers_publication,research_projects)values($id,'$first_name','$last_name','$admin_unique_key','$department_id',$subject_id,'$research_papers_publication','$research_projects')");
        }
        if ($type == "dept_hod") {
            $query = $this->db->query("insert into dept_hods (user_id,first_name,last_name,unique_key,department_id,research_papers_publication,research_projects)values($id,'$first_name','$last_name','$admin_unique_key','$department_id','$research_papers_publication','$research_projects')");
        }
        // return $query->result();
    }

    public function admin_login($admin_array) {
        $email = $admin_array['email'];
        $pass = $admin_array['password'];
        $category = $admin_array['type'];
        $query_login = $this->db->query("select * from users where type='$category' and email='$email' and password=PASSWORD('$pass')");
        if (!$query_login->num_rows()) {
            $this->errors[] = 'Wrong email or password';
            return;
        }
        $result_login = $query_login->result_array();
        $login_details = $result_login[0];
        if ($category == 'sub_hod') {
            $query_name = $this->db->get_where('sub_hods', array('user_id' => $login_details['id']));
            $result_name=$query_name->result_array();
            $details=$result_name[0];
            if($details['status']=='unapproved'){
                $this->errors[]= 'Your status is unapproved';
                return;
            }
        } else {
            $query_name = $this->db->get_where('dept_hods', array('user_id' => $login_details['id']));
            $result_name=$query_name->result_array();
            $details=$result_name[0];
            if($details['status']=='unapproved'){
                $this->errors[]= 'Your status is unapproved';
                return;
            }           
        }
        return($details);
    }
    public function forgot_password_submit($email_password){
        $email=$email_password['email'];
        $password=$email_password['password'];        
        $this->db->query("update users set password=PASSWORD('$password') where email='$email'");        
      
    }
    public function user_details($email){
        if($_SESSION['type']=="admin"){
            return NULL;
        }
        $this->load->database();
        $query_details=$this->db->get_where("users",array('email'=>$email));
        $result_details=$query_details->result_array();
        $result=$result_details['0'];
        $user_id=$result['id'];
        if($result['type']=="student")
        $query_details=$this->db->get_where("students",array('user_id'=>$user_id));
        else if($result['type']=="sub_hod")
        $query_details=$this->db->get_where("sub_hods",array('user_id'=>$user_id));
        else 
            $query_details=$this->db->get_where("dept_hods",array('user_id'=>$user_id));
        
        $result_details_final=$query_details->result_array();
        $result_final=$result_details_final['0'];
        return $result_final;
    }   
}
