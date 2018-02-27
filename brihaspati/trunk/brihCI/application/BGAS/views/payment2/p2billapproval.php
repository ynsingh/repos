<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');?>
<script type="text/javascript" language="javascript">

function DisableBackButton() {
        window.history.forward()
        }
        DisableBackButton();
        window.onload = DisableBackButton;
        window.onpageshow = function(evt) { if (evt.persisted) DisableBackButton() }
        window.onunload = function() { void (0) }
</script>

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
                
                if(name == "plan" || name == "non_plan"){
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

	$('.fund-dropdown').change(function() {
                var ledger_name =$("select.fund-dropdown option:selected").val();
			var name = ledger_name;
		$.ajax({ 
				url: <?php echo '\'' . site_url('payment2/p2prev_fund') . '/\''; ?> + name,
				success: function(data) {
				var chk_fund = $.trim(data);
				if(chk_fund == "1")
				{
                                	$('p.fund-bal-label').show();
				}
				else
				{
					$('p.fund-bal-label').hide();
				}
			}
		});
                 
		$.ajax({
                 		url: <?php echo '\'' . site_url('payment2/p2fund_balance') . '/\''; ?> + name,
                        	success: function(data) {
                        	var ledger_bal = $.trim(data);
				var ledgernumb = ledger_bal.split(' ');
				var legbal = ledgernumb[0];
                       		var lgno = ledgernumb[1];
                                var text = "Available Fund";
				if(lgno == "0"){
                                        if(legbal == "0.00"){
   						alert("Please Add atleast one parent group for this ledger entry for Payment");
                                             	$("#result").html(text+" = "+legbal).show();
                                	}
                                 	else{
                                 		$("#result").html(text+" = "+legbal).show();
                               		}  
				}
				else 
				{
					if(legbal == "0.00"){
                                                alert("Please Add atleast one parent group for this ledger entry for Payment");
                                                $("#result").html(text+" = "+legbal).hide();
                                        }
                                        else{
                                                $("#result").html(text+" = "+legbal).hide();
                                        } 
				}       
          		}
    		});


        });
});

</script>
  
<?php //echo $bill_no['value']);
	$this->load->library('session');
	$newdata = array(
                      'bill_no'  => $bill_no['value'],
                     );
        $this->session->set_userdata($newdata);

        echo "<table width=\"100%\">";
        echo "<tr valign=\"top\">";
	
	echo "<ul style = \"background: #fff8c6;border: 1px solid #ffec8b;display: block;color: #222222;margin: 5px 0 12px;list-style-position:outside;list-style-type: disc;\">";
	echo "<li style = \"margin: 15px 0px 0px 0px \">"; 
	echo "&nbsp;&nbsp;&nbsp;* is Mandatory field only when bill is approved and forwarded to next authority otherwise leave this field.";
	echo "</li>";
	echo "<li>";
	echo "&nbsp;&nbsp;&nbsp;** is mandatory field only when you select Fund otherwise leave this field."; 
	echo "</li>";
	echo "<li>"; 
	echo "&nbsp;&nbsp;&nbsp;*** is mandatory field only when you approve bill or bill is forwarded for Voucher Creation.";
	echo "</li>";
	echo "<li style = \"margin: 0px 0px 15px 0px \">"; 
	echo "&nbsp;&nbsp;&nbsp;# is Mandatory field only when bill is approved first time and forwarded to next authority otherwise leave this field.";
	echo "</li>";
	echo "</ul>";

        echo "<td>";
        echo "<table width=\"50%\">";
        echo "<tr>";

	$bill_array = "";
	echo form_open_multipart('payment2/p2billapproval/'.$bill_no['value']);
        echo "<p>";
        echo form_label('Bill No','bill_no');
        echo "<br />";
        echo form_input($bill_no);
        echo "</p>";
        
	echo "<p>";
        echo form_label('Supplier Bill No', 'supplier_bill_no');
        echo "<br />";
        echo form_input($supplier_bill_no);
        echo "</p>";
        
	echo "<p>";
        echo form_label('Purchase Order No', 'purchase_order_no');
        echo "<br />";
        echo form_input($purchase_order_no);
        echo "</p>";

	echo "View Bill";
	echo "<br />";
	$atts = array(
		'width'      => '800',
		'height'     => '600',
		'scrollbars' => 'yes',
		'status'     => 'yes',
		'resizable'  => 'yes',
		'screenx'    => '0',
		'screeny'    => '0'
        );
	$bill_array = explode('.', $bill_name);
	$bill_ext = $bill_array[1];
	if($bill_ext == "pdf")
	{
		echo anchor_popup(('../uploads/BGAS/Bills/'.$bill_name), $bill_name,$atts);
	}
	else
	{
		echo anchor_popup(('../uploads/BGAS/Bills/'.$bill_name), $bill_name,$atts, "class=\"thickbox\"");
	}

        echo "<td class=\"ledger-balance\"><div></div></td>";
        echo "<p>";
        echo form_label('Submitted By','submitted_by');
        echo "<br />";
        echo form_input($submitted_by);
        echo "</p>";
	
	echo "<p>";
        echo form_label('Date','date');
        echo "<br />";
        echo form_input($submitdate);
        echo "</p>";
        
        echo "<p>";
        echo form_label('Forward From', 'submitter_id');
        echo "<br />";
        echo form_input($forward_from);
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
//        echo "</p>";

//	echo "<p>";
        echo form_label('Budget Balance','exp_budget');
//        echo "<br />";
        echo form_input($exp_budget);
        echo "</p>";

	echo "<p>";
        echo form_label('Party Name','expensestype');
        echo "<br />";
        echo form_input($secunitid);
        echo "</p>";

        echo "</tr>";
        echo "</table>";
        echo "</td>";

        echo "<td>";
        echo "<table width=\"50%\">";
        echo "<tr>";
        
        echo "<p>";
	echo "<font color = RED>*</font>";
        echo "&nbsp;";
        echo form_label('Forward To', 'forward_to');
        echo "<br />";
        echo form_dropdown('forward_to' , $forward_to, $forward_to_active, "class=\"forward_to-dropdown\"");
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo "</p>";
	
	echo "<p>";
//	echo "<font color = RED>*</font>";
  //      echo "&nbsp;";
        echo form_label('Select Fund', 'fund');
        echo "<br />";
        echo form_dropdown('fund',$fund, $fund_active,"class =\"fund-dropdown\"");
	echo "<span id=\"result\"></span>";
	if($fund_active == "0")
	{
	}
	else
	{
        //	$fund_bal_attr = array('class' => 'fund-bal-label');
		echo "<p class=\"fund-bal-label\">";
		echo form_label('Fund Balance','fund_bal');//,$fund_bal_attr);
		echo "<br />";
        	echo form_input($fund_bal);//,"class = \"fund-bal\"");
		echo "</p>";
	}
        echo "</p>";

	echo "<p>";
	echo "<font color = RED>**</font>";
        echo "&nbsp;";
        echo form_label('Expenditure Type', 'exp_type');
        echo "<br />";
        echo form_dropdown('exp_type' ,$exp_type, $exp_type_active, "class =\"exp-type\"");
        echo "</p>";

        echo "<p>";
	echo "<font color = RED>***</font>";
        echo "&nbsp;";
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

	echo "<p class=\"decision\">";
	echo "<br>";
	echo "<font color = RED>***</font>";
        echo "&nbsp;";
        echo form_label('Approve/Reject', 'decision');
        echo "<br /> ";
        echo form_dropdown('decision' ,$decision, $decision_active,"class =\"decision\"");
	echo "<font color = #708090>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;IF YOU ARE THE LAST AUTHORITY FOR BILL APPROVAL THEN PLEASE SELECT </font>";echo "<font color = #4169E1>VOUCHER CREATION.</font>";
        echo "</p>";

        echo "<p class=\"approved_amount\">";
	echo "<font color = RED>***</font>";
        echo "&nbsp;";
        echo form_label('Approved Amount','approved_amount');
        echo "<br />";
        echo form_input($approved_amount);

	echo form_label('Previously Approved Amount','prev_approved_amount');
        echo " ";
        echo form_input($prev_approved_amount);
        echo "</p>";

        echo "<p>";
	echo "<font color = RED>#</font>";
        echo "&nbsp;";
        echo form_label('Narration','being');
        echo "<br />";
        echo form_textarea($being);
        echo "</p>";

        echo "<p>";
        echo "<font color = RED>*</font>";
	echo "&nbsp;";
        echo form_label('Comments','being1');
        echo "<br />";
        echo form_textarea($being1);
        echo "</p>";

        echo "</tr>";
        echo "</table>";
        echo "</td>";

        echo "</tr>";
        echo "</table>";

        echo "<br />";
        echo form_submit('submit', 'Submit' );

?>
	<input type="button" onClick="window.history.back()" value="Back">
     <?php echo form_close();?>
