<?php 
if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Ckeditor extends Controller {
	
	public $data = array();
	
	public function __construct() {
		
		parent::Controller();

		/* Check access */
                if ( ! check_access('edit doc'))
                {
                        $this->messages->add('Permission denied.', 'error');
                        redirect('');
                        return;
                }

		$this->load->helper('url'); //You should autoload this one ;)
		$this->load->helper('ckeditor');
		
		//Ckeditor's configuration
		$this->data['ckeditor'] = array(
		
			//ID of the textarea that will be replaced
			'id' 	=> 	'content',
			'path'	=>	'js/ckeditor',
		
			//Optionnal values
			'config' => array(
//				'toolbar' 	=> 	'Full', 	//Using the Full toolbar
				'width' 	=> 	'100%',	//Setting a custom width
				'height' 	=> 	'50%',	//Setting a custom height
				'float'		=>	'left'
			),
		
			//Replacing styles from the "Styles tool"
			'styles' => array(
			
				//Creating a new style named "style 1"
				'style 1' => array (
					'name' 		=> 	'Blue Title',
					'element' 	=> 	'h2',
					'styles' => array(
						'color' 			=> 	'Blue',
						'font-weight' 		=> 	'bold'
					)
				),
				
				//Creating a new style named "style 2"
				'style 2' => array (
					'name' 		=> 	'Red Title',
					'element' 	=> 	'h2',
					'styles' => array(
						'color' 			=> 	'Red',
						'font-weight' 		=> 	'bold',
						'text-decoration'	=> 	'underline'
					)
				)				
			)
		);
		
		$this->data['ckeditor_2'] = array(
		
			//ID of the textarea that will be replaced
			'id' 	=> 	'content_2',
			'path'	=>	'js/ckeditor',
		
			//Optionnal values
			'config' => array(
				'width' 	=> 	"550px",	//Setting a custom width
				'height' 	=> 	'100px',	//Setting a custom height
				'toolbar' 	=> 	array(		//Setting a custom toolbar
					array('Bold', 'Italic'),
					array('Underline', 'Strike', 'FontSize'),
					array('Smiley'),
					'/'
				)
			),
		
			//Replacing styles from the "Styles tool"
			'styles' => array(
			
				//Creating a new style named "style 1"
				'style 3' => array (
					'name' 		=> 	'Green Title',
					'element' 	=> 	'h3',
					'styles' => array(
						'color' 			=> 	'Green',
						'font-weight' 		=> 	'bold'
					)
				)
								
			)
		);
		$this->load->library('session');
                $unspent_type = $this->session->userdata('unspent_type');
                if($unspent_type == 'non-plan'){
		$var = getcwd().'/docs/'.Date("F d, Y").'nonplan_report'.'.txt';
		}elseif($unspent_type == 'plan'){
			 $var = getcwd().'/docs/'.Date("F d, Y").'plan_report'.'.txt';
		}else{
		$var = getcwd().'/docs/notesToAccount.txt';
		}
		$file_content = file_get_contents($var);

		$this->data['textarea'] = array(
			
			'id'	=>	'content',
			'name'	=>	'content',
			'value'	=>	$file_content
		);
	}
	
	public function index() {

		$this->load->view('ckeditor', $this->data);
	}

	public function addToFile(){

		$editor_data = $_POST['content'];

		$var = getcwd().'/docs/notesToAccount.txt';



		if(is_writable($var)){

			$myfile = fopen($var, 'w') or die("Unable to open file!");

			fwrite($myfile, $editor_data);

			fclose($myfile);

		}else{

			$this->messages->add('Please give write permission to "BGAS/docs/notesToAccount.txt" file', 'error');

		}	



		redirect('notes/display_notes');

		return;

	}
}
