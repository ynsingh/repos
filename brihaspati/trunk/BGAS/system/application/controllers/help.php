<?php

class Help extends Controller {
	function index()
	{
		$this->template->set('page_title', 'Help');
		$this->template->load('template', 'help/index');
		return;
	}

	function entry(){
		$this->template->set('help', 'true');
		$this->template->load('template', 'help/entry');
		return;
	}

	function helpdoc(){
		$this->template->load('template', 'help/helpdoc');
		return;
	}
}

/* End of file help.php */
/* Location: ./system/application/controllers/help.php */
