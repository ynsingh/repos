<table border=0 cellpadding=5 class="simple-table">
	<thead>
		<tr>
                        <th>Secondary Unit</th>
                        <th>Party Name</th>
                        <th>Mobile NO.</th>
                        <th>Email Id</th>
                        <th>Bank A/C No.</th>
			<th>Bank Name</th>
			<th>Branch Name</th>
			<th>IFSC Code</th>
			<th>PAN No.</th>
			<th>TAN No.</th>
                        <th>Service Tax No.</th>

		</tr>
	</thead>
	<tbody>
	<?php
		foreach ($party_detail->result() as $row)
		{
			echo "<tr>";
                        echo "<td>" . $row->sacunit . "</td>";
                        echo "<td>" . $row->partyname . "</td>";
                        echo "<td>" . $row->mobnum . "</td>";
                        echo "<td>" . $row->email . "</td>";
                        echo "<td>" . $row->bancacnum . "</td>";
                        echo "<td>" . $row->bankname . "</td>";
                        echo "<td>" . $row->branchname . "</td>";
                        echo "<td>" . $row->ifsccode . "</td>";
                        echo "<td>" . $row->pan . "</td>";
                        echo "<td>" . $row->tan . "</td>";
                        echo "<td>" . $row->staxnum . "</td>";
                        echo "</tr>";
		}
	?>
	</tbody>
</table>
