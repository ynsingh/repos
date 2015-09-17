<?php

class Backup extends Controller {

	function Backup()
	{
		parent::Controller();
		$this->load->model('Setting_model');

		/* Check access */
		if ( ! check_access('change account settings'))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('');
			return;
		}

		return;
	}

	function get_backup($filename)
	{
		$this->load->library('session');
		$this->load->dbutil();
		$this->load->helper('download');

		/* Check access */
		if ( ! check_access('backup account'))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('setting');
			return;
		}

		$backup_filename = "backup" . date("dmYHis") . ".gz";

		/* Backup your entire database and assign it to a variable */
		$backup_data =& $this->dbutil->backup();
		/* Send the file to your desktop */
		force_download($filename, $backup_data);
		$this->logger->write_message("success", "Downloaded account backup");
		redirect('setting');
		return;
	}

	function available_backup()
	{
		$this->template->set('nav_links', array('setting/backup/create_backup' => 'Create Backup'));
//		$this->template->set('page_title', 'Available Backup');
		$this->messages->add('Download backup otherwise backup delete automatically in seven days.', 'success');
		$this->template->load('template', 'setting/available_backup');
		return;

	}
	
	function download($filename)
	{
		$this->template->set('page_title', 'Available Backup');
		$this->load->library('zip');
		//set path
		$path=$this->upload_path= realpath(BASEPATH.'../backups/'. $filename);
		// Download the file to your desktop......
		$this->zip->read_file($path); 
		$this->zip->download($filename); 
		$this->template->load('template', 'setting/available_backup');
        	return;
	}

	function create_backup()
	{
		$this->load->library('zip');
		$this->load->dbutil();
		$this->load->helper('download');
		//Code for creating backup of directory...
		$this->load->helper('recursivezip');
                $dst= realpath(BASEPATH.'../backups');
                $src= realpath(BASEPATH.'../uploads');
                $z= new recursiveZip();
                echo $z->compress($src,$dst);
		//end.........
		/* Check access */
		if ( ! check_access('backup account'))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('setting');
			return;
		}
		
		$backup_filename = "backup" . date("dmYHis") . ".gz";
		/* Write the backup file to server*/ 
		if ( ! write_file($this->config->item('backup_path') . $backup_filename, $backup_data))
		{
			$this->messages->add('Error saving backup file to server.' . ' Check if "' . $this->config->item('backup_path') . '" folder is writable.', 'error');
			redirect('setting');
			return;
		}

		/* Send the file to your desktop */
		$this->logger->write_message("success", "Downloaded account backup");
		$this->messages->add('Backup created successfully.', 'success');
		redirect('setting/backup/available_backup');
		return;

	}

	function delete($filename)
        {
		//set path where we want to delete file...
		$this->template->set('nav_links', array('setting/backup/create_backup' => 'Create Backup'));
                $path=$this->upload_path= realpath(BASEPATH.'../backups');
		@unlink($path.'/'.$filename);
		$this->messages->add('Download backup otherwise backup delete automatically in seven days.', 'success');
		$this->messages->add('Backup file deleted successfully.', 'success');
                $this->template->load('template', 'setting/available_backup');
                return;
        }


}

/* End of file backup.php */
/* Location: ./system/application/controllers/setting/backup.php */
