<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');?>
<script type ="text/javascript">

$(document).ready(function() {
        $('.decision').change(function() {
	var value =$("select.decision option:selected").val();
      	if(value != 'Select'){
     	 	alert("Are You Sure You want to "+value+" this bill?");
	         }
        });

       $('.sanc-type').change(function() {
                var name = $('.sanc-type').val();
                
                if(name != "select"){
                        if(name == "plan"){
                                $('.plan').show();
                                $('.plan-label').show();
                                $('.non-plan').hide();
                                $('.non-plan-label').hide();
                        }
                        else
                        {
                                $('.non-plan').show();
                                $('.non-plan-label').show();
                                $('.plan').hide();
                                $('.plan-label').hide();
                        }
                }else{
                        $('.plan').hide();
                        $('.plan-label').hide();
                        $('.non-plan').hide();
                        $('.non-plan-label').hide();    
                }
        });
	 $('.sanc-type').trigger('change');

//$('.sanc-type').trigger('change');
});

</script>
  
<?php //echo $bill_no['value']);
	$bill_array = "";
	echo form_open_multipart('payment/billapproval/'.$bill_no['value']);
        echo "<p>";
        echo form_label('Bill No','bill_no');
        echo "<br />";
        echo form_input($bill_no);
        echo "</p>";

/*	echo "<p>";
        echo form_label('VIEW BILL','bill_name');
        echo "<br />";
        echo form_input($bill_name);
        echo "</p>";*/
//	print_r($bill_name);
	echo "View Bill";
	echo "<br />";
//	echo "class=\".photopopup\">";
	$bill_array = explode('.', $bill_name);
	$bill_ext = $bill_array[1];
	if($bill_ext == "pdf")
	   {
		echo anchor(('../uploads/Bills/'.$bill_name), $bill_name);
	   }
	else{
		echo anchor(('../uploads/Bills/'.$bill_name), $bill_name, "class=\"thickbox\"");
	}

	//echo "<td>".anchor(('../uploads/Bills/'.$bill_app->bill_name), $bill_app->bill_name)."</td>";	
        echo "<td class=\"ledger-balance\"><div></div></td>";
        echo "<p>";
        echo form_label('Submitted By','submitted_by');
        echo "<br />";
        echo form_input($submitted_by);
        echo "</p>";
	
	echo "<p>";
        echo form_label('Date','date');
        echo "<br />";
        echo form_input($date,date("Y-m-d H:i:s"),"readonly = true");
        echo "</p>";

	echo "<p>";
        echo form_label('Total Amount','total_amount');
        echo "<br />";
        echo form_input($total_amount);
        echo "</p>";

        echo "<p>";
        echo form_label('Expense Type','expensestype');
       echo "<br />";
       echo form_input($expensestype);
       echo "</p>";

	echo "<p>";
        echo form_label('Secondary Unit ID','expensestype');
       echo "<br />";
       echo form_input($secunitid);
       echo "</p>";
	
	echo "<p>";
       echo form_label('Select Fund', 'fund');
       echo "<br />";
       echo form_dropdown('fund' ,$fund, $fund_active);
       echo "</p>";

       echo "<p>";
       echo form_label('Expenditure Type', 'exp_type');
       echo "<br />";
       echo form_dropdown('exp_type' ,$exp_type, $exp_type_active);
       echo "</p>";

        echo form_label('Sanction Type');
        echo " ";
	echo "<br />";
        echo form_dropdown('sanc_type', $sanc_type, $active_sanc_type, "class = \"sanc-type\"");
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

        $plan_attr = array('class' => 'plan-label');
        echo form_label('Plan', 'plan', $plan_attr);
        echo " ";
        echo form_dropdown('plan', $plan, $active_plan, "class = \"plan\"");
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

        $non_plan_attr = array('class' => 'non-plan-label');
        echo form_label('Non Plan', 'non_plan', $non_plan_attr);
        echo " ";
        echo form_dropdown('non_plan', $non_plan, $active_non_plan, "class = \"non-plan\"");
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo "</p>";

       echo "<p>";
       echo form_label('Approve/Reject', 'decision');
       echo "<br />";
	echo "<p class=\"decision\">";
       echo form_dropdown('decision' ,$decision, $decision_active,"class =\"decision\"");
       echo "</p>";

       echo "<p class=\"approved_amount\">";
       echo form_label('Approved Amount','approved_amount');
       echo "<br />";
       echo form_input($approved_amount);
       echo "</p>";

      echo "<p>";
       echo form_label('* Narration','being');
       echo "<br />";
      echo form_textarea($being);
      echo "</p>";

      echo "<br />";
      echo form_submit('submit', 'Submit' );
?>
	<input type="button" onClick="window.back()" value="Back">
     <?php echo form_close();?>
