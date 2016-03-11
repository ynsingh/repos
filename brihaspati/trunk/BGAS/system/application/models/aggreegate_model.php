<?php

/*** @author sharad singh<sharad23nov@yahoo.com> date:10-12-2015 ***/

if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Aggreegate_model extends Model {
    function Aggreegate_model()
    {
        parent::Model();
    }


    function get_op_balance_agg($ledger_id,$accname)
    {
        $CI =& get_instance();
        $db1=$CI->load->database('login', TRUE);
        $db1->from('bgasAccData')->where('dblable', $accname);
        $accdetail = $db1->get();
        foreach ($accdetail->result() as $row)
        {
            $db_name = $row->databasename;
            $db_username = $row->uname;
            $db_password = $row->dbpass;
            $host_name = $row->hostname;
            $port = $row->port;
        }
        $op_balance='';
        $dbcon = new PDO("mysql:host=$host_name;dbname=$db_name", $db_username, $db_password);
        try
        {
            $ledgeropid = "select * from ledgers where id=$ledger_id limit 1";
            $stmt = $dbcon->query($ledgeropid);
            if($stmt != false)  
            {
                foreach ($stmt as $row)
                {
                    $op_balance = array($row['op_balance'],$row['op_balance_dc']);
                    return $op_balance;    
                }
            }
        }
        catch(PDOException $e)
        {
            echo $e->getMessage();
        }
    }



	function get_op_closing_balance_agg($ledger_id,$accname)
	{
        
        list ($op_bal, $op_bal_type) = $this->get_op_balance_agg($ledger_id,$accname);
        

        $CI =& get_instance();
        $db1=$CI->load->database('login', TRUE);
        $db1->from('bgasAccData')->where('dblable', $accname);
        $accdetail = $db1->get();
        foreach ($accdetail->result() as $row)
        {
            $db_name = $row->databasename;
            $db_username = $row->uname;
            $db_password = $row->dbpass;
            $host_name = $row->hostname;
            $port = $row->port;
        }
        $dbcon = new PDO("mysql:host=$host_name;dbname=$db_name", $db_username, $db_password);
        try
        {
            $dr_amount = "select sum(amount)from entry_items INNER JOIN entries ON entry_items.entry_id = entries.id where entry_items.ledger_id = '$ledger_id' and entry_items.dc = 'D'";
            $stmt1 = $dbcon->query($dr_amount);
            if($stmt1 != false)
            {
                foreach ($stmt1 as $row)
                {
                    $dr_total = $row['sum(amount)'];
                }
            }
            $cr_amount = "select sum(amount)from entry_items INNER JOIN entries ON entry_items.entry_id = entries.id where entry_items.ledger_id = '$ledger_id' and entry_items.dc = 'C'";
            $stmt2 = $dbcon->query($cr_amount);
            if($stmt2 != false)
            {
                foreach ($stmt2 as $row)
                {
                    $cr_total = $row['sum(amount)'];
                }
            }
            $total = '';
            if ($op_bal_type == "D")
                $total = float_ops($total, $op_bal, '+');
            else
                $total = float_ops($total, $op_bal, '-');

            //echo "Total--->".$total;echo "<br>";
            $op_balance=abs($total);
            if($total >= 0)
                $op_type="D";
            else
                $op_type="C";
            
            return array($op_balance, $op_type);;
        }
        catch(PDOException $e)
        {
            echo $e->getMessage();
        }


	}

    function get_closing_balance_agg($ledger_id,$accname)
    {
        list ($op_bal, $op_bal_type) = $this->get_op_closing_balance_agg($ledger_id,$accname);

        $dr_total = $this->get_dr_total1_agg($ledger_id,$accname);
        $cr_total = $this->get_cr_total1_agg($ledger_id,$accname);
        //echo $cr_total."==";
        $total = float_ops($dr_total, $cr_total, '-');

        if ($op_bal_type == "D")
            $total = float_ops($total, $op_bal, '+');
        else
            $total = float_ops($total, $op_bal, '-');
        return $total;
    }

    /* Return debit total of current financial year as positive value */
    function get_dr_total1_agg($ledger_id,$accname)
    {
        $CI =& get_instance();
        $db1=$CI->load->database('login', TRUE);
        $db1->from('bgasAccData')->where('dblable', $accname);
        $accdetail = $db1->get();
        foreach ($accdetail->result() as $row)
        {
            $db_name = $row->databasename;
            $db_username = $row->uname;
            $db_password = $row->dbpass;
            $host_name = $row->hostname;
            $port = $row->port;
        }
        $dbcon = new PDO("mysql:host=$host_name;dbname=$db_name", $db_username, $db_password);
        $op_balance = array();
        try
        {

            $abc = "select entry_items.entry_id, sum(amount)from entry_items INNER JOIN entries ON entry_items.entry_id = entries.id where entry_items.ledger_id = '$ledger_id' and entry_items.dc = 'D' ";
            $stmt1 = $dbcon->query($abc);
            if($stmt1 != false)
            {
                foreach ($stmt1 as $row)
                {
                    $dr_total = $row['sum(amount)'];
                    return $dr_total;
                }
            }
        }
        catch(PDOException $e)
        {
            echo $e->getMessage();
        }
    }

    /* Return credit total of current financial year as positive value */
    function get_cr_total1_agg($ledger_id,$accname)
    {
        $CI =& get_instance();
        $db1=$CI->load->database('login', TRUE);
        $db1->from('bgasAccData')->where('dblable', $accname);
        $accdetail = $db1->get();
        foreach ($accdetail->result() as $row)
        {
            $db_name = $row->databasename;
            $db_username = $row->uname;
            $db_password = $row->dbpass;
            $host_name = $row->hostname;
            $port = $row->port;
        }
        $dbcon = new PDO("mysql:host=$host_name;dbname=$db_name", $db_username, $db_password);
        $op_balance = array();
        try
        {

            $abc = "select entry_items.entry_id, sum(amount)from entry_items INNER JOIN entries ON entry_items.entry_id = entries.id where entry_items.ledger_id = '$ledger_id' and entry_items.dc = 'C' ";
            $stmt1 = $dbcon->query($abc);
            if($stmt1 != false)
            {
                foreach ($stmt1 as $row)
                {
                    $cr_total = $row['sum(amount)'];
                    return $cr_total;
                }
            }
        }
        catch(PDOException $e)
        {
            echo $e->getMessage();
        }
    }

    /* Return debit total of balancesheet for multiple accounts for aggregation */

    function get_balancesheet_dr_total_agg($ledger_id,$accname)
    {
        $db_name ='';
        $db_username ='';
        $db_password ='';
        $host_name ='';
        $port ='';
        $db_name ='';

        $this->load->library('session');

        $CI =& get_instance();
        $db1=$CI->load->database('login', TRUE);
        $db1->from('bgasAccData')->where('dblable', $accname);
        $db_name_q = $db1->get();
        foreach ($db_name_q->result() as $row)
        {
            $db_name = $row->databasename;
            $db_username = $row->uname;
            $db_password = $row->dbpass;
            $host_name = $row->hostname;
            $port = $row->port;
        }
        $op_balance = array();
        $dbcon = new PDO("mysql:host=$host_name;dbname=$db_name", $db_username, $db_password);
        try
        {
            $abc = "select entry_items.entry_id, sum(amount)from entry_items INNER JOIN entries ON entry_items.entry_id = entries.id where entry_items.ledger_id = '$ledger_id' and entry_items.dc = 'D'";
            $stmt = $dbcon->query($abc);
            if($stmt != false)
            {
                foreach ($stmt as $row)
                {
                    $dr_total = $row['sum(amount)'];                
                    return $dr_total;
                }
            }
        }
        catch(PDOException $e)
        {
            echo $e->getMessage();
        }
        
    }
    /* Return credit total of balancesheet for multiple accounts for aggregation */
    function get_balancesheet_cr_total_agg($ledger_id,$accname)
    {
        $this->load->library('session');
        $db_name ='';
        $db_username ='';
        $db_password ='';
        $host_name ='';
        $port ='';
        $CI =& get_instance();
        $db1=$CI->load->database('login', TRUE);
        $db1->from('bgasAccData')->where('dblable', $accname);
        $db_name_q = $db1->get();
        foreach ($db_name_q->result() as $row)
        {
            $db_name = $row->databasename;
            $db_username = $row->uname;
            $db_password = $row->dbpass;
            $host_name = $row->hostname;
            $port = $row->port;
        }
        $op_balance = array();
        $dbcon = new PDO("mysql:host=$host_name;dbname=$db_name", $db_username, $db_password);
        try
        {
            $abc = "select entry_items.entry_id, sum(amount)from entry_items INNER JOIN entries ON entry_items.entry_id = entries.id where entry_items.ledger_id = '$ledger_id' and entry_items.dc = 'C'";
            $stmt = $dbcon->query($abc);
            if($stmt != false)
            {
                foreach ($stmt as $row)
                {
                    $cr_total = $row['sum(amount)'];
                    return $cr_total;
                }
            }
        $dbcon = null;
        }
        catch(PDOException $e)
        {
            echo $e->getMessage();
        }
    }

}
?>
