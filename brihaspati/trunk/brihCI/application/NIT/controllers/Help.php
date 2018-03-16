
<?php
/* 
 * @name Help.php
 * @author Deepika Chaudhary(chaudharydeepika88@gmail.com)  */

class Help extends CI_Controller
{
 function helpdoc(){
		$this->load->view('help/helpdoc');
                return;
      	}
 function helpdocfaculty(){
                $this->load->view('help/helpdocfaculty');
                return;
        }
 function helpdocstudent(){
                $this->load->view('help/helpdocstudent');
                return;
        }


}

