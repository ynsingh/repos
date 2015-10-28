<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class exams extends CI_Model {

    public function test1_submit($test_answers) {       
        $user_id = $_SESSION['user_id'];
        $this->load->database();
        $this->load->helper('url');

        $var_text=0;
        $var_video=0;
        $ans[1] = $test_answers['preferred_color1'];
        $ans[2] = $test_answers['preferred_color2'];
        $ans[3] = $test_answers['preferred_color3'];
        $ans[4] = $test_answers['preferred_color4'];
        $ans[5] = $test_answers['preferred_color5'];
        $ans[6] = $test_answers['preferred_color6'];
        $ans[7] = $test_answers['preferred_color7'];
        $ans[8] = $test_answers['preferred_color8'];
        $ans[9] = $test_answers['preferred_color9'];
        $ans[10] = $test_answers['preferred_color10'];
        $ans[11] = $test_answers['preferred_color11'];
        $ans[12] = $test_answers['preferred_color12'];
        $ans[13] = $test_answers['preferred_color13'];
        $ans[14] = $test_answers['preferred_color14'];
        $ans[15] = $test_answers['preferred_color15'];
        $ans[16] = $test_answers['preferred_color16'];
        $ans[17] = $test_answers['preferred_color17'];
        $ans[18] = $test_answers['preferred_color18'];
        $ans[19] = $test_answers['preferred_color19'];
        for ($i=1;$i<=19;$i++){
            if($ans[$i]==1)
                $var_video++;
            else
                $var_text++;
        }
        if($var_text >= $var_video)
            $type='text';
        else
            $type='video';
        $data = array('test1' => 1,'type'=> $type);
        $this->db->where('user_id', $user_id);
        $this->db->update('students', $data);
	return $type;
    }

    public function test2_submit($test_answers) {
        $user_id = $_SESSION['user_id'];
        $this->load->database();
          $var_ppt=0;
        $var_descriptive=0;
        $ans[1] = $test_answers['preferred_color1'];
        $ans[2] = $test_answers['preferred_color2'];
        $ans[3] = $test_answers['preferred_color3'];
        $ans[4] = $test_answers['preferred_color4'];
        $ans[5] = $test_answers['preferred_color5'];
        $ans[6] = $test_answers['preferred_color6'];
        $ans[7] = $test_answers['preferred_color7'];
        for ($i=1;$i<=7;$i++){
            if($ans[$i]==1)
                $var_descriptive++;
            else
                $var_ppt++;
        }
        if($var_descriptive >= $var_ppt)
            $type='descriptive';
        else
            $type='ppt';
        $data = array('test2' => 1,'type'=> $type);        
        $this->db->where('user_id', $user_id);
        $this->db->update('students', $data);
    }

    public function test3_submit($test_answers) {
        $user_id = $_SESSION['user_id'];
        $this->load->database();
       $var_hard=0;
        $var_harder=0;
        $ans[1] = $test_answers['preferred_color1'];
        $ans[2] = $test_answers['preferred_color2'];
        $ans[3] = $test_answers['preferred_color3'];
        $ans[4] = $test_answers['preferred_color4'];
        $ans[5] = $test_answers['preferred_color5'];
        $ans[6] = $test_answers['preferred_color6'];
        $ans[7] = $test_answers['preferred_color7'];
        $ans[8] = $test_answers['preferred_color8'];
        $ans[9] = $test_answers['preferred_color9'];
        $ans[10] = $test_answers['preferred_color10'];
        $ans[11] = $test_answers['preferred_color11'];
        $ans[12] = $test_answers['preferred_color12'];
        $ans[13] = $test_answers['preferred_color13'];
        $ans[14] = $test_answers['preferred_color14'];
        $ans[15] = $test_answers['preferred_color15'];
        $ans[16] = $test_answers['preferred_color16'];
        $ans[17] = $test_answers['preferred_color17'];
        $ans[18] = $test_answers['preferred_color18'];        
        for ($i=1;$i<=18;$i++){
            if($ans[$i]==1)
                $var_harder++;
            else
                $var_hard++;
        }
        if($var_hard >= $var_harder)
            $hardness=1;
        else
            $hardness=2;
        $data = array('test3' => 1,'hardness'=> $hardness,'all_test_given'=>1);
        $this->db->where('user_id', $user_id);
        $this->db->update('students', $data);
    }

}

?>
