<?php
/**
 * Instead of using json_array, we can also easily create the JSON
 * ourselves very easily and faster
 */
?>
var institutes = [
<?foreach($institutes as $i=>$insti):?>
	{
		"latitude": <?=$insti->latitude?>,
		"longitude": <?=$insti->longitude?>,
		"name": <?=H::escapeForJSON($insti->name)?>,
		"seats": <?=$insti->getSeats()?>,
		"courses": <?=$insti->numCourses()?>,
		"address": <?=H::escapeForJSON($insti->address)?>
	},
<?endforeach;?>
]
