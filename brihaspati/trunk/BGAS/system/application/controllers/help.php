<?php

class Help extends Controller {
	function index()
	{
		$this->template->set('page_title', 'Help');
		$this->template->load('template', 'help/index');
		return;
	}

	function entry(){
		$this->template->load('template', 'help/entry');
		return;
	}
}

/* End of file help.php */
/* Location: ./system/application/controllers/help.php */
