<?php

defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Pjslist.php
 * @author Nagendra Kumar Singh nksinghiitk@gmail.com
 */

class Pjslist extends CI_Controller
{
 
    	function __construct() {
        	parent::__construct();

        	$this->load->model('Common_model',"commodel");
        	$this->load->model('Login_model',"lgnmodel"); 
        	$this->load->model('SIS_model',"sismodel");
        	$this->load->model('PICO_model',"picomodel");
        	$this->load->model('Dependrop_model',"depmodel");

        	if(empty($this->session->userdata('id_user'))) {
            		$this->session->set_flashdata('flash_data', 'You don\'t have access!');
            		redirect('welcome');
        	}
    	}
 
    	public function index(){
    	}


	public function getcoverdetail(){
	        
        	$coverno= $this->input->post('noc');
        	
        	$whdata =array('ct_id' =>$coverno);
        	$covrdata=$this->picomodel->get_orderlistspficemore('cover_type','*',$whdata,''); 
	      $values=array();
        	$covern='';
        	if(count($covrdata)>0){
			 foreach($covrdata as $detail){
		                $coverno=$detail->ct_coverno;
		                $covern=$detail->ct_cover1;
		                $covern2=$detail->ct_cover2;
		                if(!empty($covern2)){
		                	$covern="(a)".$covern." "." (b)".$covern2." ";
		                }
		               $covern3=$detail->ct_cover3;
		               if(!empty($covern3)){
		                	$covern=$covern." (c)".$covern3." ";
		                }
							$covern4=$detail->ct_cover4;
							 if(!empty($covern4)){
		                	$covern=$covern." (d)".$covern4;
		                }
							
                		
				array_push($values, $coverno,$covern);
			}
		}
		else{
			$mess="Please select the valid  Number";
			array_push($values,$mess);
		}
		//implode("+",$values);
		echo json_encode( $values);
	}
    /*********************************************************************************/
}    
