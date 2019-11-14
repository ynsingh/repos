<?php
defined('BASEPATH') OR exit('No direct script access allowed');
/**
 * @name Backups.php
 * @author Nagendra Kumar Singh
 */
class Backups extends CI_Controller {

	/**
	 * Index Page for this controller.
	 *
	 * Maps to the following URL
	 * 		http://example.com/index.php/welcome
	 *	- or -
	 * 		http://example.com/index.php/welcome/index
	 *	- or -
	 * Since this controller is set as the default controller in
	 * config/routes.php, it's displayed at http://example.com/
	 *
	 * So any other public methods not prefixed with an underscore will
	 * map to /index.php/welcome/<method_name>
	 * @see https://codeigniter.com/user_guide/general/urls.html
	 */

	/**
	* folder names to backup that are in the same directory as the CI index.php
	* these can either be just 1 folder name "application" or a path like "application/controllers"
	* if this array is empty then entire site structure is backed up
	* public $directories = array("application", "forum", "assets/images", "assets/js", "assets/css", "system");
	*/
	private $directories = array("application", "docs","database","config", "assets",  "system","uploads","index.php","slcmsindex.php","bgasindex.php","hrmindex.php","sisindex.php","picoindex.php");

	/**
	* containts a list of all the directories to ignore, leave empty to backup all
	*/
	private $ignore_directories = array('backups','site_backup');

	/**
	* the directory name used for the temp file copy when everything is backed up
	*/
	private $copy_directory = "site_backup";

	/**
	* used to mark that the directory structure has alread been copied
	*/
	private $structure_copied = FALSE;
	
	public $base_path = "";

	function __construct() {
        	parent::__construct();
		$this->load->helper('url');
	        $this->load->helper('file');
        	$this->load->helper('download');
		$this->load->helper('directory');
	        $this->load->library('zip');	

        	$this->load->model("login_model", "login");
                $this->load->model("User_model", "usrmodel");
                $this->load->model("Common_model", "commodel");
		$this->load->model("SIS_model", "sismodel");
		 if(empty($this->session->userdata('id_user'))) {
                        $this->session->set_flashdata('flash_data', 'You don\'t have access!');
                        redirect('welcome');
                }

		ini_set('max_execution_time', 600);
		ini_set('memory_limit','1024M');
    	}

	public function index() {
		$cdate = date('Y-m-d');
		$this->listbkupfile();
        }//close index function

	public function dbbackup() {
                $cdate = date('Y-m-d');
                $this->logindbbkup();
                $this->olasdbbkup();
                $this->payrolldbbkup();
                $this->picodbbkup();
                $this->session->set_flashdata('success','All database (Payroll,Login,OLAS,PICO) Backup taken successfully.');
                $this->listbkupfile();
        }//close
	
	public function logindbbkup(){
		$this->db = $this->load->database('login',TRUE);
                $this->load->dbutil();
                $db_format=array('format'=>'zip','filename'=>'login_db_backup.sql');
                $backup=$this->dbutil->backup($db_format);
                $dbname='logindb-backup-on-'.date('Y-m-d').'.zip';
                $save='backups/db_backup/'.$dbname;
                write_file($save,$backup);
//                force_download($dbname,$backup);
		$this->logger->write_logmessage("insert", "Login database backup taken successfully.");
                $this->logger->write_dblogmessage("insert", "Login database backup taken successfully." );

        }//close  function

	public function olasdbbkup(){
		$this->db = $this->load->database('default',TRUE);
                $this->load->dbutil();
                $db_format=array('format'=>'zip','filename'=>'olas_db_backup.sql');
                $backup=$this->dbutil->backup($db_format);
                $dbname='olasdb-backup-on-'.date('Y-m-d').'.zip';
                $save='backups/db_backup/'.$dbname;
                write_file($save,$backup);
//                force_download($dbname,$backup);
		$this->logger->write_logmessage("insert", "OLAS database backup taken successfully.");
                $this->logger->write_dblogmessage("insert", "OLAS database backup taken successfully." );

        }//close  function

	public function payrolldbbkup(){
		$this->db = $this->load->database('payroll',TRUE);
                $this->load->dbutil();
                $db_format=array('format'=>'zip','filename'=>'payroll_db_backup.sql');
                $backup=$this->dbutil->backup($db_format);
                $dbname='payrolldb-backup-on-'.date('Y-m-d').'.zip';
                $save='backups/db_backup/'.$dbname;
                write_file($save,$backup);
  //              force_download($dbname,$backup);
		$this->logger->write_logmessage("insert", "Payroll database backup taken successfully.");
                $this->logger->write_dblogmessage("insert", "Payroll database backup taken successfully." );

        }//close  functionp

	public function picodbbkup(){
		$this->db = $this->load->database('pico',TRUE);
                $this->load->dbutil();
                $db_format=array('format'=>'zip','filename'=>'pico_db_backup.sql');
                $backup=$this->dbutil->backup($db_format);
                $dbname='picodb-backup-on-'.date('Y-m-d').'.zip';
                $save='backups/db_backup/'.$dbname;
                write_file($save,$backup);
//                force_download($dbname,$backup);
		$this->logger->write_logmessage("insert", "pico database backup taken successfully.");
                $this->logger->write_dblogmessage("insert", "pico database backup taken successfully." );

        }//close  function

	public function listbkupfile(){
		$map = directory_map('backups/', FALSE, TRUE);
		$data['record']=$map;
//		print_r($map);
//		die();
		$this->load->view('backups/listbkupfile',$data);	
	}

	public $backup_type=2;
	//public function get_site_backup($file_path, $backup_type, $file_format = 1) {
		// file format (zip=1)
	public function get_site_backup($file_format = 1) {
		$file_path = "backups/site_backups/";
		$this->zip->clear_data();
		$this->check_directory($file_path);
		$this->check_directory($this->copy_directory);
		//loop each of the folder that will be backed up
		if (count($this->directories) > 0) {
			foreach ($this->directories as $dir) {
				if (!in_array($dir, $this->ignore_directories)) {
					$location = $this->base_path . $dir . "/";
					$this->zip->read_dir($location, FALSE);
				}
			}
		} else {
			//takes a copy of the code to ensure that the backup of backups is not made.
			$copied = FALSE;
			if ($this->structure_copied === FALSE) {
				$path = str_replace('\\', '/', realpath($this->base_path));
				$copied = $this->copy_site_files($path, $this->base_path . $this->copy_directory . "/");
			}	
			if (($copied === TRUE) || ($this->structure_copied === TRUE)) {
				$this->zip->read_dir($this->base_path . $this->copy_directory . "/", FALSE);
			}
		}
		flush();
		//$key_name = date("d_m_Y_H_i_s");
		$key_name = date("d_m_Y");
		if ($file_format == 1) {
			//$file_name = md5($key_name) . '_site.zip';
			$file_name = 'App_'.$key_name . '_site.zip';
			$zipped = $this->zip->archive($file_path . $file_name);
			$this->zip->clear_data();
			if ($this->structure_copied === TRUE) { //we need to remove the copied files to ensure that the server is kept nice and tidy
				$this->remove_temp_files($this->base_path . $this->copy_directory . "/");
			}		
/*			if ($zipped == 1) {
				$this->handle_success('File Name: ' . $file_name . '. ');
				$this->handle_success('Site backup successfully written to disk. ');
				$date_arr = explode('_', $key_name);
				$date = $date_arr[0] . '-' . $date_arr[1] . '-' . $date_arr[2] . ' ' . $date_arr[3] . ':' . $date_arr[4] . ':' . $date_arr[5];
				$file = $file_path . $file_name;
				$saved_data = $this->backup->save_backup_details($file_name, $file_path, $backup_type);
				if ($saved_data !== NULL) {
					$this->handle_success('Site Backup details successfully saved to database. ');
					$this->handle_success('You can get site backup here ' . anchor($this->site_download_url . $saved_data, 'download', array('class' => 'download')));
					$this->handle_success('You can delete site backup here ' . anchor($this->site_delete_url . $saved_data, 'delete', array('class' => 'delete', 'onclick' => "return confirm('Are you sure want to delete this file ?')")));
				} else {
					if (file_exists($file)) {
						unlink($file);
					}
					$this->handle_success('Error while saving site backup to database: ' . $file_name);
				}
			} else {
				$this->handle_error('Error while writing site backup to disk: ' . $file_name);
			}
		*/
		}
		$this->session->set_flashdata('success','Application Backup taken successfully.');
		$this->listbkupfile();
	}

	private function copy_site_files($path, $dest) {
		if (is_dir($path)) {
			@mkdir($dest);
			$objects = scandir($path);
			if (sizeof($objects) > 0) {
				foreach ($objects as $file) {
					if ($file == "." || $file == "..") {
						continue;
					}
					// go on
					if (is_dir($path . "/" . $file)) {
						if ((!in_array($file, $this->ignore_directories)) && ($file != $this->copy_directory)) {
							$this->copy_site_files($path . "/" . $file, $dest . "/" . $file);
						}
					} else {
						copy($path . "/" . $file, $dest . "/" . $file);
					}
				}
			}
			$this->structure_copied = TRUE;
			return TRUE;
		} elseif (is_file($path)) {
			$this->structure_copied = TRUE;
			return copy($path, $dest);
		} else {
			$this->structure_copied = TRUE;
			return FALSE;
		}
	}

	private function check_directory($path) {
		if (!@opendir($path)) {
			mkdir($path, 0755);
		} //if(!@opendir($path))
		return;
	}

	private function remove_temp_files($directory) {
		if (substr($directory, -1) == "/") {
			$directory = substr($directory, 0, -1);
		}
		if (!file_exists($directory) || !is_dir($directory)) {
			return FALSE;
		} elseif (!is_readable($directory)) {
			return FALSE;
		} else {
			$directoryHandle = opendir($directory);
			while ($contents = readdir($directoryHandle)) {
				if ($contents != '.' && $contents != '..') {
					$path = $directory . "/" . $contents;
					if (is_dir($path)) {
						$this->remove_temp_files($path);
					} else {
						unlink($path);
					}
				}
			}
			closedir($directoryHandle);
			if (!rmdir($directory)) {
				return FALSE;
			} else {
				return TRUE;
			}
		}
	}

	function delete_bkup_file($loc,$filename) {
		$file_path = $this->base_path."backups/".$loc."/".$filename;
		if (file_exists($file_path)) {
			unlink($file_path);
                        $this->session->set_flashdata('success','Backup file deleted.');
		}else{
                        $this->session->set_flashdata('error','Backup file does not exist.');
                }

		$this->listbkupfile();
	}

	function download_bkup_file($loc,$filename) {
		$file_path = $this->base_path."backups/".$loc."/".$filename;
		if (file_exists($file_path)) {
			$this->load->helper('download');
			$data = file_get_contents($file_path); // Read the file's contents
			$name = $filename;	
			force_download($name, $data);
		}else{
			 $this->session->set_flashdata('error','Backup file does not exist.');
		}
		$this->listbkupfile();
	}


}//close class
