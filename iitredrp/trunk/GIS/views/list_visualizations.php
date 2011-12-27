<h3>Query Builder</h3>
<form method="POST" id="query_builder">
	<ul>
		<li>Show me institutes in states:</li>
		<li>
			<select size="4" multiple="multiple" name="state[]">
			  <option value="all">All States</option>
			  <?foreach($states as $state):?>
			  <?='<option value="'.$state['state'].'">'.$state['state'].'</option>'?>
			  <?endforeach;?>
			</select>
		</li>
		<li>Older than:</li>
		<li>
			<input size="3" name="older_than" placeholder="10"> years
		</li>
		<li>in a </li>
		<li>
			<select name="visualization_type">
				<option value="map">Map</option>
				<option value="bubble">Bubble Chart</option>
				<option value="table">Table</option>
			</select>
		</li>
		<li>
		<input type="submit">
		</li>
	</ul>
</form>
