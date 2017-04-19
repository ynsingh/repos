
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 

echo "<ul class=\"sf-menu\">";
			echo "<li class=\"current\">";
				//echo "<a href=" . base_url() . "title=\"Dashboard\">Dashboard</a>";
				echo anchor('setup', 'Setup', array('title' => 'Setup'));
			echo "</li>";
			echo "<li>";
				echo anchor('map', 'Map', array('title' => 'Map'));
			echo "</li>";
			echo "<li>";
				echo anchor('upload', 'Upload', array('title' => 'Upload')); 
			echo "</li>";
			echo "<li>";
				echo anchor('report', 'Report', array('title' => 'Report')); 
			echo "</li>";
echo "</ul>";
