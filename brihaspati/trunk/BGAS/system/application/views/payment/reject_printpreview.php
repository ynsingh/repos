<?php	
	  if ( ! defined('BASEPATH')) exit('No direct script access allowed');

	echo form_open();
        echo "<p>";
        echo form_label('BILL NO','bill_no');
        echo "<br />";
        echo form_input($bill_no);
        echo "</p>";

        echo "<p>";
        echo form_label('BILL NAME','bill_name');
        echo "<br />";
        echo form_input($bill_name);
        echo "</p>";

        echo "<p>";
        echo form_label('SUBMIT BY','submitted_by');
        echo "<br />";
        echo form_input($submitted_by);
        echo "</p>";

        echo "<p>";
        echo form_label('DATE','date');
        echo "<br />";
        echo form_input($date,date("Y-m-d H:i:s"),"readonly = true");
        echo "</p>";

        echo "<p>";
        echo form_label('TOTAL AMOUNT','total_amount');
        echo "<br />";
        echo form_input($total_amount);
	echo "</p>";

	echo "<p>";
        echo form_label('EXPENSES TYPE','expensestype');
       echo "<br />";
       echo form_input($expensestype);
       echo "</p>";

       echo "<p>";
       echo form_label('DECISION', 'decision');
       echo "<br />";
       echo form_input($decision);
       echo "</p>";

      echo "<p>";
       echo form_label('BEING','being');
       echo "<br />";
      echo form_input($being);
      echo "</p>";

?>
      <input class="hide-print" type="button" onClick="window.print()" value="Print entry">
       <input class="hide-print" type="button" onClick="window.back()" value="Back">

     <?php echo form_close();?>
                                
