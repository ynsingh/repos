<script type="text/javascript">
$(document).ready(function() {
	/* Show and Hide allowed_over */
	$('#budget').change(function() {
		if (($(this).is(':checked'))){
			$('.budget-expense').show();
		}
		else
		{
			$('.budget-expense').hide();
		}
	});

	/* Show and hideallowed over*/
	$('.budget-parent').change(function() {
		var name = $(".budget-parent").val();
		if(name.match("Main Budget$")){
                        $('.budget-exceed').hide();
                }
                else
                {
                        $('.budget-exceed').show();
                }
        });
	$('#budget').trigger('change');
	$('.budget-parent').trigger('change');
});
</script>
<?php

		//echo"jai ma saraswati";
	      	echo form_open('report/addDep');
		echo "<p>";
      	        echo form_label('Assets name', '$group_name');
                echo "<br />";
        	echo form_dropdown('group_name', $group_name, $group_name_active,"class = \"budget-parent\"");
        	echo "</p>";
 		
		echo "<p>";
		echo form_label('Depreciation Percentage', 'dep_amount');
		echo "<br />";
		echo form_input($dep_amount);
		echo "</p>";
                
                echo form_submit('submit', 'Add');
                echo "</p>";
                echo form_close();
     


?>
