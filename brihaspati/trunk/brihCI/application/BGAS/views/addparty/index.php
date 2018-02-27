<!DOCTYPE html>
<html>
<head>
<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>-->
<script>
$(document).ready(function(){
    $("#change_order").click(function(){
		                                $.ajax({
                                        //url: <?php echo '\'' . site_url('ledger/set_group_id') . '/\''; ?> + id,
                                        url: <?php echo '\'' . site_url('addparty/set_group_id') . '/\''; ?>,
                                        success: function() {
                                              location.reload();
                                        }
                                 });

    });
	$("span").click(function(){
                                                $.ajax({
                                        //url: <?php echo '\'' . site_url('ledger/set_group_id') . '/\''; ?> + id,
                                        url: <?php echo '\'' . site_url('addparty/change_order') . '/\''; ?>,
                                        success: function() {
                                              location.reload();
                                        }
                                 });

    });

	 $("#change_id_order").click(function(){
                                                $.ajax({
                                        //url: <?php echo '\'' . site_url('ledger/set_group_id') . '/\''; ?> + id,
                                        url: <?php echo '\'' . site_url('addparty/change_id_order') . '/\''; ?>,
                                        success: function() {
                                              location.reload();
                                        }
                                 });

    });


});
</script>
</head>
<body>


</body>
</html>
<?php
	echo form_open('addparty/show');
	echo "<p>";
	echo form_label('Search By', 'search_by');
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo form_dropdown('search_by', $search_by, $search_by_active);
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo form_label('Text', 'text');
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo form_input($text);
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo form_submit('submit', 'Search');
	echo " ";
	echo "</p>";
	echo form_close();
?>
<table border=0 cellpadding=5 class="simple-table">
	<thead>
		<tr>
                        <p><th id="change_id_order">Party ID<?php echo img(array('src' => asset_url() . "assets/bgas/images/icons/rsz_scrollup.png")); ?></th></p>
                        <?php echo "<th id=\"change_order\">"."Party Name"." ".img(array('src' => asset_url() . "assets/bgas/mages/icons/rsz_scrollup.png"))." ". "<span align=\"right\">" . img(array('src' => asset_url() . "images/icons/rsz_scrolldown.png")). ""; ?>

                        <th>Party Type</th>
                        <th>Mobile NO.</th>
                        <th>Email Id</th>
                        <th>Bank A/C No.</th>
			<th>Bank Name</th>
			<th>Branch Name</th>
			<th>IFSC Code</th>
			<th>PAN No.</th>
			<th>UID No.</th>
			<th>TAN No.</th>
                        <th>Service Tax No.</th>
			<th>VAT No.</th>
			<th>GST No.</th>
                        <th>Opening Balance</th>
                        <th>Clossing Balance</th>
                        <th>Available Actions</th>

		</tr>
	</thead>
	<tbody>
	<?php
		$end_uri = site_url()."/report2/sec_report/";
		foreach ($party_detail->result() as $row)
		{
			echo "<tr>";
                        echo "<td>" . $row->sacunit . "</td>";
			$sec_name_id = $this->secunit_model->get_secunitid($row->partyname);
			echo "<td><a style=\"text-decoration: none;color:black;\" href=$end_uri"."$sec_name_id>$row->partyname</a></td>";
                        //echo "<td>" . $row->partyname . "</td>";
                        echo "<td>" . $row->partyrole . "</td>";
                        echo "<td>" . $row->mobnum . "</td>";
                        echo "<td>" . $row->email . "</td>";
                        echo "<td>" . $row->bancacnum . "</td>";
                        echo "<td>" . $row->bankname . "</td>";
                        echo "<td>" . $row->branchname . "</td>";
                        echo "<td>" . $row->ifsccode . "</td>";
                        echo "<td>" . $row->pan . "</td>";
                        echo "<td>" . $row->u_id . "</td>";
                        echo "<td>" . $row->tan . "</td>";
                        echo "<td>" . $row->staxnum . "</td>";
                        echo "<td>" . $row->vat . "</td>";
                        echo "<td>" . $row->gst . "</td>";
			echo "<td>" . $row->dc ." ". $row->opbal . "</td>";
                       	echo "<td>" . $sbal[$row->sacunit] . "</td>";
			echo "<td>" . anchor('addparty/edit/' . $row->sacunit , "Edit", array('title' => 'Edit Details' , 'class' => 'red-link')) . " ";
                        echo "</tr>";
		}
	?>
	</tbody>
</table>
