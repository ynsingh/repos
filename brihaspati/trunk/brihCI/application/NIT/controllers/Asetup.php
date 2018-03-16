<?php

/* 
 * @name Asetup.php
 * @author Nagendra Kumar Singh(nksinghiitk@gmail.com)  
 */
 
defined('BASEPATH') OR exit('No direct script access allowed');


class Asetup extends CI_Controller
{
    function __construct() {
        parent::__construct();
		$this->load->model('common_model','commodel'); 
	        $this->load->model('login_model','logmodel');
        	$this->load->model('dependrop_model','depmodel');
        	$this->load->model('university_model','unimodel');
        	$this->load->model("Mailsend_model","mailmodel");
	 	$this->load->model("DateSem_model","datemodel");
		$this->load->helper('file');
        	if(empty($this->session->userdata('id_user'))) {
            		$this->session->set_flashdata('flash_data', 'You don\'t have access!');
			redirect('welcome');
        	}
    }

    /** This is default function  */

    public function index() {
        $this->sqlrootpasswd();
    }

    /** This function is used for setup mysql root password */
    public function sqlrootpasswd() {
	if(isset($_POST['mysqlsetting'])) {
                 $this->form_validation->set_rules('spasswd','Root Password','trim|xss_clean|required');
                 if($this->form_validation->run()==TRUE){
			$data_database_password = $_POST['spasswd'];
			$ini_file = "assets/accounts/sqladmin.ini";
//			print_r($ini_file); die;
			if ( ! get_file_info($ini_file))
			{	 mkdir($ini_file, 0777, true);
				//create file and give the permission
			//	$this->session->set_flashdata('err_message','MySQL settings file labeled sqladmin.ini does not exists.'.$ini_file, 'error');
			//	redirect('asetup/sqlrootpasswd');
			} 
			//else {
				$con_details = "[database]" . "\r\n" . "sql_admin_name = \"" . "root" . "\"" . "\r\n" . "sql_admin_password = \"" . $data_database_password . "\"" . "\r\n";

				$con_details_html = '[database]' . '<br />sql_admin_name = "' . "root" . '"<br />sql_admin_password = "' . $data_database_password . '"<br />';
				if ( ! write_file($ini_file, $con_details))
                        	{
                                	$this->session->set_flashdata('err_message','Failed to edit sql admin settings file. Check if "' . $ini_file . '" file is writable.', 'error');
                                	$this->session->set_flashdata('err_message','You can manually update the text file "' . $ini_file . '" with the following content :<br /><br />' . $con_details_html, 'error');
                                	redirect('asetup/sqlrootpasswd');
                                	return;
                        	} else {
                                	$this->session->set_flashdata('success','Updated MySQL root pasword settings.');
                                	redirect('asetup/sqlrootpasswd');
                                	return;
                        	}
			//}
                }//close if vallidation
        }//
	 $this->load->view('asetup/sqlrootpasswd');
    }

    /** This function is used for create new session as per academic year */
    public function creatensession() {
	   // if(isset()){
	    $this->load->helper('file');
	    $this->load->dbutil();

	    //	get the complete schema file
	    $schfile = "database/olas.sql";
	    //  get dump of setup and map
	    $prefs = array(
	        'tables'        => array('Department','category','degree_rule','designation','email_setting','examtype','fees_master','grade_master','org_profile','program','program_subject_teacher','programcategory','role','seat_program_studycenter','seat_reservation','semester_rule','set_date','study_center','subject','subject_paper','subject_prerequisite','subject_semester','user_role_type'),   // Array of tables to backup.
        	'ignore'        => array(),                     // List of tables to omit from the backup
	        'format'        => 'txt',                       // gzip, zip, txt
        	'filename'      => 'database/olasbackup.sql',   // File name - NEEDED ONLY WITH ZIP FILES
	        'add_drop'      => TRUE,                        // Whether to add DROP TABLE statements to backup file
        	'add_insert'    => TRUE,                        // Whether to add INSERT data to backup file
	        'newline'       => "\n"                         // Newline character used in backup file
		);
	    $bfname = 'database/olasbackup.sql';
	    $ibackup=$this->dbutil->backup($prefs);
	    write_file($bfname, $ibackup);
	    $nfname = 'database/olasbackup'.date('Ymd').'.sql';
	    if (!copy($bfname, $nfname)) {
		    $this->logger->write_logmessage("insert","failed to copy due to failed to open stream: Permission denied " .$nfname);
                    $this->logger->write_dblogmessage("insert", "failed to copy due to failed to open stream: Permission denied " .$nfname);
		}
	   // die;
	    //print_r($ibackup); 

	    //	get the root user name and password from file
	   /* $ini_file = "assets/accounts/sqladmin.ini";
	    $data_sql_accounts = parse_ini_file($ini_file);
	    $data_database_host = "localhost";
            $data_database_admin_username = $data_sql_accounts['sql_admin_name'];
            $data_database_admin_password = $data_sql_accounts['sql_admin_password'];
	    //$new_link = @mysqli_connect($data_database_host . ':' . $data_database_port, $data_database_admin_username, $data_database_admin_password);
	   // $new_link = mysqli_connect($data_database_host, $data_database_admin_username, $data_database_admin_password,'*');
	    $mysqliroot = new mysqli(
		    $db['default']['hostname'],
		    $data_database_admin_username,
		    $data_database_admin_password
	    );
	    //get the name of database for new batch
		//get old db name 
		// get acad year
	    $acadyear = '1617'; 
	    $data_database_name = 'olas'.$acadyear;
	    $data_database_username = $db['default']['username'];
	    $data_database_password = $db['default']['password'];
	    //	create new batch database 
	    //	$city = $mysqli->real_escape_string($city);
	    $db_create_q = 'CREATE DATABASE ' . $data_database_name .'; ';
           // $db_create_q1 = 'GRANT ALL ON '. $data_database_name .'.* TO '. $data_database_username.'@127.0.0.1 IDENTIFIED BY "'. $data_database_password .'"; ';
          //  $db_create_q2 = 'GRANT ALL ON '. mysqli_real_escape_string($data_database_name).'.* TO '. mysqli_real_escape_string($data_database_username).'@localhost IDENTIFIED BY "'. mysqli_real_escape_string($data_database_password).'"; ';
            if (mysqli_query($new_link,$db_create_q))
            {
		    $eflag=false;
		    $this->logger->write_logmessage("insert","Created new account database. " .$data_database_name);
                    $this->logger->write_dblogmessage("insert", "Created new account database. " .$data_database_name);
	    }
	    die;
/*            if (mysql_query($db_create_q1, $new_link))
	    {
            	$eflag1=false;
		$this->logger->write_logmessage("insert"," Granting permission to user to access new database  with local ip." .$data_database_name);
                $this->logger->write_dblogmessage("insert", "Granting permission to user to access new database  with local ip. " .$data_database_name);

            }
            if (mysql_query($db_create_q2, $new_link))
            {
		    $eflag2=false;
		    $this->logger->write_logmessage("insert","Granting permission to user to access new database  with local name. " .$data_database_name);
                    $this->logger->write_dblogmessage("insert", "Granting permission to user to access new database  with local name. " .$data_database_name);
            }
	    //	run the schema
	    $mysqli = new mysqli(
		    $db['default']['hostname'],
		    $db['default']['username'],
    		    $db['default']['password']
	    );
	    //	$this->ndb=$this->load->database('$data_database_name', TRUE);
	    $setup_account = read_file($schfile);
	    $setup_account_array = explode(";", $setup_account);
	    $newacc->trans_start();
            foreach($setup_account_array as $row)
            {
            	if (strlen($row) < 5)
                	continue;
                $newacc->query($row);
                if ($newacc->_error_message() != "")
                {  
		        $this->logger->write_logmessage("insert","Error initializing account database under schema.sql. " .$data_database_name);
			$this->logger->write_dblogmessage("insert", "Error initializing account database under schema.sql. " .$data_database_name);
			redirect('home');
                }
	    }
	    $newacc->trans_rollback();
	    $newacc->trans_complete();*/
	    //  run the dump
	    //// Set line to collect lines that wrap
		//$templine = '';

		// Read in entire file
		//$lines = file('database/olasbackup.sql');

		// Loop through each line
		//foreach ($lines as $line)
		//{
			// Skip it if it's a comment
			//if (substr($line, 0, 2) == '--' || $line == '')
			//continue;

			// Add this line to the current templine we are creating
			//$templine .= $line;

			// If it has a semicolon at the end, it's the end of the query so can process this templine
			//if (substr(trim($line), -1, 1) == ';')
			//{
				// Perform the query
				//$this->ndb->query($templine);

				// Reset temp variable to empty
				//$templine = '';
			//}
		//}
	    // insert into login database ofr create session
	    // $insdata = array(
	    //		'batchyear' => $batchyear,
	    //		'dbname' => $dbname,
	    //		'setupdate' => date('y-m-d')
	    // );
	    //  $insflag=$this->logmodel->insertrec('createsession', $insdata) ;
	    //  if(! $insflag)
            //{
                //$this->logger->write_logmessage("insert", "Error in inserting session configuration detail  "  );
               // $this->logger->write_dblogmessage("insert", "Error in inserting session configuration detail  ");
               // $this->session->set_flashdata('err_message','Error in inserting session configuration detail - ' );
             //   redirect('home');
           // }
           // else{
               // $this->logger->write_logmessage("insert", " Inserting session configuration details ");
               // $this->logger->write_dblogmessage("insert", "Inserting session configuration details ");
             //   $this->session->set_flashdata('success','Inserting session configuration details...');
           //     redirect('home');
         //   }
	//}
	redirect('home');
    }

    /** This function is used for finalise the new setup */
    public function finalsession() {
	   // if(isset()){
	    // update the create session table in login database
/*	    //get the current database
	    $dbname = ;	    
 	    $data_id = $this->logmodel->get_listspfic1('createsession','id','dbname',$dbname);
 	    $update_data = array(
               'fstatus' => 'final',
               'fsetupdate' => date('y-m-d')
            );
	    $editflag=$this->logmodel->updaterec('createsession', $update_data,'id',$data_id);
	    if(! $editflag)
            {
                $this->logger->write_logmessage("update", "Error in updating session configuration detail  "  );
                $this->logger->write_dblogmessage("update", "Error in updating session configuration detail  ");
		$this->session->set_flashdata('err_message','Error in updating session configuration detail - ' );
		redirect('home');
            }
            else{
                $this->logger->write_logmessage("update", " Updating session configuration details ");
                $this->logger->write_dblogmessage("update", "Updating session configuration details ");
		$this->session->set_flashdata('success','Updating session configuration details...');
		redirect('home');
	    }*/
	//}
	redirect('home');

    }
}
