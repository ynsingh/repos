<?php

defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name SubprgList.php
 * @author Manorama Pal (palseema30@gmail.com)
 */

class SubprgList extends CI_Controller
{
 
    function __construct() {
        parent::__construct();
        
        $this->load->model("Common_model", "cmodel");
        $this->load->model("pagination_model", "pagemodel");
        $this->load->model("User_model", "usrmodel");
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
        }
    }
 
    public function index(){
        
    $this->load->view('subject/subprg_list');    
    }
    /*getting  subject list  */
    public function subjectlist(){
        $this->load->library('pagination');
        $this->admcyear=$this->usrmodel->getcurrentAcadYear();
        /*default setting for the pagination with gui tags */
        $config = [
            'base_url'      => base_url() ."index.php/SubprgList/subjectlist",
            'per_page'      => 20,
            'total_rows'    => $this->pagemodel->totalnum_rows('pstp_id',$this->admcyear,'program_subject_teacher'),
            'full_tag_open' => "<ul class='pagination'>",
            'full_tag_close' => "</ul>",
            'first_tag_open'  => '<li>',
            'first_tag_close' => '</li>',
            'last_tag_open'  => '<li>',
            'last_tag_close' => '</li>',
            'next_tag_open'  => '<li>',
            'next_tag_close' => '</li>',
            'prev_tag_open'  => '<li>',
            'prev_tag_close' => '</li>',
            'num_tag_open'  => '<li>',
            'num_tag_close' => '</li>',
            'cur_tag_open'  => "<li class='active'><a>",
            'cur_tag_close' => '</a></li>',
            
        ];
            
       
        $uid=$this->session->userdata('id_user');
        /*select campusid*/
        $this->campusid=$this->cmodel->get_listspfic1('user_role_type','scid','userid',$uid)->scid;
        $selectfield=array('pstp_prgid','pstp_subid','pstp_papid','pstp_acadyear','pstp_sem');
        $data=array(
            'pstp_scid' =>$this->campusid,
            'pstp_teachid' => $uid,
            'pstp_acadyear' => $this->admcyear
           
        );
        $this->pagination->initialize($config);
        $this->cdetail=$this->pagemodel->get_listspficemore2('program_subject_teacher',$selectfield,$data,$config['per_page'],$this->uri->segment(3));
       	$this->load->view('subject/subprg_list',$this->cdetail);    
        
        
    }
}    
