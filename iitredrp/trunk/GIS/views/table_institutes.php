<table>
	<thead>
		<tr>
			<th>Name</th>
			<th>Address</th>
			<th>State</th>
			<th>Phone</th>
			<th>Year</th>
			<th>URL</th>
		</tr>
	<tbody>
		<?foreach($institutes as $i):?>
		<tr>
			<td><?=$i['name']?></td>
			<td><?=$i['address']?></td>
			<td><?=$i['state']?></td>
			<td><?=$i['phone']?></td>
			<td><?=$i['year']?></td>
			<td><a href="<?=$i['url']?>"><?=$i['url']?></a></td>
		</tr>
		<?endforeach;?>
	</tbody>
</table>
