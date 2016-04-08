<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');
        echo form_open('aggregation/aggregatetrialbalance1');
        echo "<p>";
        echo "Username : ";
	    echo $username;
        echo "</p>";
       // echo $username;

        echo "<p>";
        echo "<b>Aggregate accounts list : </b>";
        echo "</p>";
        $db1=$this->load->database('login', TRUE);
        $db1->from('aggregateaccounts')->where('username', $username);
        $userrec = $db1->get();
        //$acountlist = array();
        foreach($userrec->result() as $row)
        {
                 $acountlist=$row->accounts;

        }

        //$value[$row1->dblable]=$row1->dblable;
        $accarray = array();
        $accarray1 = array();
        $accarray = explode(",", $acountlist);
        foreach ($accarray as $key => $value) {
                $accarray1[$value]=$value;
        }
        //print_r($accarray1);
        echo "<p>";
        echo "Select account";
        echo "<br />";
        echo form_multiselect('accounts[]', $accarray1);
        echo "</p>";
        echo "<p>";
        echo "select multiple accounts by using ctrl key";
        echo "<br />";
        echo "</p>";


        echo "<p>";
        echo form_submit('submit', 'Get Aggregation');
        echo "</p>";

        echo form_close();

?>
