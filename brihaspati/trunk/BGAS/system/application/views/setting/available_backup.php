
<script type="text/javascript">
$(document).ready(function() {
 $("p").click(function(){
	 location.reload();
  });
});
</script>


<?php
$this->load->library('session');
$backup_path_url= realpath(BASEPATH.'../backups');
$file_list = get_filenames($backup_path_url);
$arr_len=count($file_list);
$i=0;
echo"<p>";
echo anchor('setting/backup/create_backup', 'Create Backup', array('title' => 'Create Backup')); 
echo"</p>";
echo "<table border=0 cellpadding=5 class=\"simple-table balance-sheet-table\" width=\"50%\">";
echo "<thead><tr><th>S.No</th><th>Name Of Backup File</th><th>Download Backup</th><th>Delete Backup file</th></tr></thead>";
echo "<tbody>";
for($i=0; $i<$arr_len; $i++)
{
$s_no=$i+1;
echo "<tr>";
echo "<td>" . $s_no . '.' . "</td>";
echo "<td>" . $file_list[$i] . "</td>";
echo"<p>";
$ext = substr(strrchr($file_list[$i], '.'), 1);
if($ext == 'gz'){
echo"<td>" . anchor('setting/backup/get_backup/'.  $file_list[$i] , 'Download', array('title' => 'Download Backup')) . "</td>"; 

}else{
echo "<td>" . anchor('setting/backup/download/'. $file_list[$i], 'Download') . "</td>";
}
echo "<td>" . anchor('setting/backup/delete/'. $file_list[$i], 'Delete') . "</td>";
echo"</p>";
echo "</tr>";
}
echo "</tbody>";
echo "</table>";
?>
