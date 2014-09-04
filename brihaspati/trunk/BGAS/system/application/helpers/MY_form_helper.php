<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');

if ( ! function_exists('form_dropdown_dc'))
{
	function form_dropdown_dc($name, $selected = NULL, $extra = '')
	{
		$options = array("D" => "Dr", "C" => "Cr");

		// If no selected state was submitted we will attempt to set it automatically
		if ( ! ($selected == "D" || $selected == "C"))
		{
			// If the form name appears in the $_POST array we have a winner!
			if (isset($_POST[$name]))
			{
				$selected = $_POST[$name];
			}
		}

		if ($extra != '') $extra = ' '.$extra;

		$form = '<select name="'.$name.'"'.$extra.' class="dc-dropdown" >';

		foreach ($options as $key => $val)
		{
			$key = (string) $key;
			$sel = ($key == $selected) ? ' selected="selected"' : '';
			$form .= '<option value="'.$key.'"'.$sel.'>'.(string) $val."</option>\n";
		}

		$form .= '</select>';

		return $form;
	}
}

if ( ! function_exists('form_dropdown_payt'))
{
        function form_dropdown_payt($name, $selected = NULL, $extra = '')
        {
                $options = array("0" => "select", "1" => "Cheque", "2" => "Cash", "3" => "Bank Transfer","4" => "Credit Card", "5" => "Debit Card" , "6" => "Demand Draft", "7" => "IPO", "8" => "Others" );

                // If no selected state was submitted we will attempt to set it automatically
                if ( ! ($selected == "1" || $selected == "2" || $selected == "3" || $selected == "4"|| $selected == "5" || $selected == "6" || $selected == "7" || $selected == "8"))
                {
                        // If the form name appears in the $_POST array we have a winner!
                        if (isset($_POST[$name]))
                        {
                                $selected = $_POST[$name];
                        }
                }

                if ($extra != '') $extra = ' '.$extra;

                $form = '<select name="'.$name.'"'.$extra.' class="payt-dropdown" >';

                foreach ($options as $key => $val)
                {
                        $key = (string) $key;
                        $sel = ($key == $selected) ? ' selected="selected"' : '';
                        $form .= '<option value="'.$key.'"'.$sel.'>'.(string) $val."</option>\n";
                }

                $form .= '</select>';

                return $form;
        }
}


if ( ! function_exists('form_dropdown_fund'))
{
        function form_dropdown_fund($name, $selected = NULL, $extra = '')
        {
		$CI =& get_instance();
                $CI->load->model('Ledger_model');

                $options = $CI->Ledger_model->get_ledgers();

                // If no selected state was submitted we will attempt to set it automatically
                if ( ! ($selected))
                {
                        // If the form name appears in the $_POST array we have a winner!
                        if (isset($_POST[$name]))
                        {
                                $selected = $_POST[$name];
                        }
                }

                if ($extra != '') $extra = ' '.$extra;

                $form = '<select name="'.$name.'"'.$extra.' >';

                foreach ($options as $key => $val)
                {
                        $key = (string) $key;
                        $sel = ($key == $selected) ? ' selected="selected"' : '';
                        $form .= '<option value="'.$key.'"'.$sel.'>'.(string) $val."</option>\n";
                }

                $form .= '</select>';

                return $form;
        }
}

/*if ( ! function_exists('form_dropdown_secunit'))
{
        function form_dropdown_secunit($name, $selected = NULL, $extra = '')
        {
		$CI =& get_instance();
                $CI->load->model('Secunit_model');

                $options = $CI->Secunit_model->get_all_secunitid();

                if ( ! ($selected))
                {
                        if (isset($_POST[$name]))
                        {
                                $selected = $_POST[$name];
                        }
                }

                if ($extra != '') $extra = ' '.$extra;

                $form = '<select name="'.$name.'"'.$extra.' >';

                foreach ($options as $key => $val)
                {
                        $key = (string) $key;
                        $sel = ($key == $selected) ? ' selected="selected"' : '';
                        $form .= '<option value="'.$key.'"'.$sel.'>'.(string) $val."</option>\n";
                }

                $form .= '</select>';

                return $form;
        }
}*/
if ( ! function_exists('form_input_date'))
{
	function form_input_date($data = '', $value = '', $extra = '')
	{
		$defaults = array('type' => 'text', 'name' => (( ! is_array($data)) ? $data : ''), 'value' => $value);

		return "<input "._parse_form_attributes($data, $defaults).$extra." class=\"datepicker\"/>";
	}
}

if ( ! function_exists('form_input_date_restrict'))
{
	function form_input_date_restrict($data = '', $value = '', $extra = '')
	{
		$defaults = array('type' => 'text', 'name' => (( ! is_array($data)) ? $data : ''), 'value' => $value);

		return "<input "._parse_form_attributes($data, $defaults).$extra." class=\"datepicker-restrict\"/>";
	}
}

if ( ! function_exists('form_input_ledger'))
{
	function form_input_ledger($name, $selected = NULL, $extra = '', $type = 'all')
	{
		$CI =& get_instance();
		$CI->load->model('Ledger_model');
                $data_user_name= $CI->session->userdata('user_name');
                //get role of user
                $user_account_active = $CI->session->userdata('active_account');
                $db1=$CI->load->database('login', TRUE);
                $db1->select('role')->from('bgasuser')->where('username', $data_user_name);
                $role= $db1->get();
                //$userrole;
                foreach($role->result() as $row)
                {
                        $userrole=$row->role;
                }
                //$db1->close();

                //get account detail and find out if user have all account head permission.     
                $db1=$CI->load->database('login', TRUE);
                $db1->from('bgasAccData')->where('dblable', $user_account_active);
                $accdetail = $db1->get();
                foreach ($accdetail->result() as $row)
                {
                        $databasehost=$row->hostname;
                        $dbname= $row->databasename;
                        $databaseport=$row->port;
                        $databaseusername=$row->uname;
                        $databasepassword=$row->dbpass;
                }
                $db1->close();
                /*$con = mysql_connect($databasehost, $databaseusername, $databasepassword);
                if($con){
                        $value = mysql_select_db($dbname, $con);
                        $query = "select * from bgas_acl where username='$data_user_name' and headid='*'";
                        $val = mysql_query($query);
                        $num_rows = mysql_num_rows($val);
                }
		*/
		$CI->db->from('bgas_acl')->where('username', $data_user_name);
                $CI->db->where('headid', '*');
                $val = $CI->db->get();
                $num_rows = $val->num_rows();

                if(($userrole == 'administrator') || ($num_rows != "0"))
                {
		if ($type == 'bankcash')
			$options = $CI->Ledger_model->get_all_ledgers_bankcash();
		else if ($type == 'nobankcash')
			$options = $CI->Ledger_model->get_all_ledgers_nobankcash();
		else if ($type == 'reconciliation')
			$options = $CI->Ledger_model->get_all_ledgers_reconciliation();
		else
			$options = $CI->Ledger_model->get_all_ledgers();

		}
                else
                {
                if ($type == 'bankcash')
                        $options = $CI->Ledger_model->get_all_ledgers_bankcash();
                else if ($type == 'nobankcash')
                        $options = $CI->Ledger_model->get_all_ledgers_nobankcash();
                else if ($type == 'reconciliation')
                        $options = $CI->Ledger_model->get_all_ledgers_reconciliation();
                else
                {
                        $options = $CI->Ledger_model->get_all_ledgers_permission();
                        //        $options = $CI->Ledger_model->get_all_ledgers();
                }

                }


		// If no selected state was submitted we will attempt to set it automatically
		if ( ! ($selected))
		{
			// If the form name appears in the $_POST array we have a winner!
			if (isset($_POST[$name]))
			{
				$selected = $_POST[$name];
			}
		}


		if ($extra != '') $extra = ' '.$extra;

		$form = '<select name="'.$name.'"'.$extra.' class="ledger-dropdown">';

		foreach ($options as $key => $val)
		{
			$key = (string) $key;
			$sel = ($key == $selected) ? ' selected="selected"' : '';
			$form .= '<option value="'.$key.'"'.$sel.'>'.(string) $val."</option>\n";
		}

		$form .= '</select>';

		return $form;
	}
}

if ( ! function_exists('form_dropdown_type'))
{
        function form_dropdown_type($name, $selected = NULL, $extra = '')
        {
                $options = array("Select" => "Select Type", "Earn" => "Earned", "Accru" => "Accrued");

                // If no selected state was submitted we will attempt to set it automatically
                if ( ! ($selected == "Earn" || $selected == "Accru"))
                {
                        // If the form name appears in the $_POST array we have a winner!
                        if (isset($_POST[$name]))
                        {
                                $selected = $_POST[$name];
                        }
                }

                if ($extra != '') $extra = ' '.$extra;

                $form = '<select name="'.$name.'"'.$extra.'>';
                foreach ($options as $key => $val)
                {
                        $key = (string) $key;
                        $sel = ($key == $selected) ? ' selected="selected"' : '';
                        $form .= '<option value="'.$key.'"'.$sel.'>'.(string) $val."</option>\n";
                }

                $form .= '</select>';

                return $form;
        }
}

if ( ! function_exists('form_dropdown_exptype'))
{
        function form_dropdown_exptype($name, $selected = NULL, $extra = '')
        {
                $options = array("Select" => "Select Type", "Capital" => "Capital Expenditure", "Revenue" => "Revenue Expenditure");

                // If no selected state was submitted we will attempt to set it automatically
                if ( ! ($selected == "Capital" || $selected == "Revenue"))
                {
                        // If the form name appears in the $_POST array we have a winner!
                        if (isset($_POST[$name]))
                        {
                                $selected = $_POST[$name];
                        }
                }

                if ($extra != '') $extra = ' '.$extra;

                $form = '<select name="'.$name.'"'.$extra.'>';
                foreach ($options as $key => $val)
                {
                        $key = (string) $key;
                        $sel = ($key == $selected) ? ' selected="selected"' : '';
                        $form .= '<option value="'.$key.'"'.$sel.'>'.(string) $val."</option>\n";
                }

                $form .= '</select>';

                return $form;
        }
}

if(! function_exists('my_form_label_start'))
{
	function my_form_label_start(){

		return "<label style=\"width: 50px; float: left; margin: 0 20px 0 0;\">";
	}
}

if(! function_exists('my_form_label_end'))
{
	function my_form_label_end(){
		return "</label>";
	}
}

if(! function_exists('my_form_anchor'))
{
	function my_form_anchor($var){
//		$anchor = '<a href="'.$url.'"';
		return "<a href=\"".$var."\"><button>Cancel</button></a>";
	}
}

if ( ! function_exists('form_dropdown_secunit'))
{
        function form_dropdown_secunit($name, $selected = NULL, $extra = '')
        {
		$CI =& get_instance();
                $CI->load->model('Secunit_model');

                $options = $CI->Secunit_model->get_all_secunitid();

                if ( ! ($selected))
                {
                        if (isset($_POST[$name]))
                        {
                                $selected = $_POST[$name];
                        }
                }

                if ($extra != '') $extra = ' '.$extra;

                $form = '<select name="'.$name.'"'.$extra.' >';

                foreach ($options as $key => $val)
                {
                        $key = (string) $key;
                        $sel = ($key == $selected) ? ' selected="selected"' : '';
                        $form .= '<option value="'.$key.'"'.$sel.'>'.(string) $val."</option>\n";
                }

                $form .= '</select>';

                return $form;
        }
}
/* End of file MY_form_helper.php */
/* Location: ./system/application/helpers/MY_form_helper.php */
