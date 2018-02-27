<script type="text/javascript">
$(document).ready(function() {

	/* Show and Hide affects_gross */
	$('.group-parent').change(function() {
		if ($(this).val() == "3" || $(this).val() == "4") {
			$('.affects-gross').show();
		} else {
			$('.affects-gross').hide();
		}

		//code for displaying parent hierarchy
                if($(this).val() == "0"){
                        $('.parent').hide();
                        
                }else{
                        $('.parent').show();
                        id = $(this).val();
                        $.ajax({
                                        url: <?php echo '\'' . site_url('group/set_group_id') . '/\''; ?> + id,
                                        success: function() {
                                                location.reload();
                                        }
                        });
                }
	});		
	//$('.group-parent').trigger('change');
});
</script>
<?php
	echo form_open('group/edit/' . $group_id);

	echo "<p>";
	echo form_label('Group name', 'group_name');
	echo "<br />";
	echo form_input($group_name);
	echo "</p>";

	echo "<p>";
	echo form_label('Group code', 'group_code');
	echo "<br />";
	echo form_input($group_code);
	echo "</p>";

	echo "<p>";
	echo form_label('Parent group', 'group_parent');
	echo "<br />";
	echo form_dropdown('group_parent', $group_parent, $group_parent_active, "class = \"group-parent\"");
	echo "</p>";

	/**
         * Code for diplaying parent child hierarchy
         * for the selected group head.
         * @author Priyanka Rawat <rpriyanka12@ymail.com>
         */
	$attributes = array('class' => "parent", 'style' => "width:600px");
        echo form_fieldset('Parent Child Hierarchy', $attributes);
        echo "<p>";
	
        $this->load->library('session');
        $groupid = $this->session->userdata('group_id');
        $group_array = array();
        $counter = 0;

        if($groupid != 0)
		$id = $groupid;
	else
		$id = $group_id;

	if($id != 0){
                //$id = $groupid;
                do{
                        $this->db->select('parent_id, name');
                        $this->db->from('groups')->where('id =', $id);
                        $query_result = $this->db->get();
                        $data = $query_result->row();
                        //echo $data->name;
                        $group_array[$counter] = $data->name;
                        $counter++;

                        //if($data->parent_id >  4){
			if($data->parent_id){
                        //      echo " -> ";
                                $id = $data->parent_id;
                        }else{
                                $id = 0;
                                $counter--;
                        }

                }while($id != 0);


                do{
                        echo $group_array[$counter];
                        $counter--;

                        if($counter >= 0)
                                echo " -> ";
                }while($counter >= 0);

                $this->session->unset_userdata('group_id');
        }
        echo "</p>";
        echo form_fieldset_close();

        echo "<p>";
        echo form_label('Group Description', 'group_description');
        echo "<br />";
        echo form_textarea($group_description);
        echo "</p>";
/*	echo "<p>";
        echo form_label('Group Schedule', 'schedule');
        echo "<br />";
        echo form_input($schedule);
        echo "</p>";
*/
	echo "<p class=\"affects-gross\">";
	echo "<span id=\"tooltip-target-1\">";
	echo form_checkbox('affects_gross', 1, $affects_gross) . " Affects Gross Profit/Loss Calculations";
	echo "</span>";
	echo "<span id=\"tooltip-content-1\">If selected the Group account will affect Gross Profit and Loss calculations, otherwise it will affect only Net Profit and Loss calculations.</span>";
	echo "</p>";

	echo "<p>";
	echo form_hidden('group_id', $group_id);
	echo form_submit('submit', 'Update');
	echo " ";
	echo anchor('account', 'Back', 'Back to Chart of Accounts');
	echo "</p>";

	echo form_close();
?>
