<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Add Pay matrix</title>
<body>
<div>
<?php $this->load->view('template/header.php');?>

<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
</div>

<div align="left">
<table style="margin-left:0%;">
<tr><td>
<?php echo anchor('setup4/displaypaymatrix/', "View Pay Matrix", array('title' => 'View Pay Matrix' , 'class' => 'top_parent'));
//$help_uri = site_url()."/help/helpdoc#ViewProgramandseatDetail";
//echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:70%\">Click for Help</b></a>";
?>
</td></tr>
</table>
</div>

        <table> 
            <tr><td>    
                <div>
		<?php echo validation_errors('<div class="isa_warning>','</div>');?>

                <?php if(isset($_SESSION['success'])){?>
                    <div  class="isa_success"><?php echo $_SESSION['success'];?></div>

                <?php
                };
                ?>
                <?php if(isset($_SESSION['err_message'])){?>
                    <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>

                <?php
                };
                ?>

            </div>
        </td></tr>   
        </table>   
    <div align="left" style="margin-left:0%;">
	<form action="<?php echo site_url('setup4/paymatrix');?>" method="POST" class="form-inline">
          <table cellpadding="16" class="TFtable">
                <tr>
                <td>
                <label for="pmlevel" class="control-label">Salary Level :</label>
                </td>
                <td><select name="pmlevel" style="width:410px;" id="pmlevel">
                <option selected="selected" disabled selected>------ Select Level------</option>
                        <option value="Level-1">Level-1</option>
                        <option value="Level-2">Level-2</option>
                        <option value="Level-3">Level-3</option>
                        <option value="Level-4">Level-4</option>
                        <option value="Level-5">Level-5</option>
                        <option value="Level-6">Level-6</option>
                        <option value="Level-7">Level-7</option>
                        <option value="Level-8">Level-8</option>
                        <option value="Level-9">Level-9</option>
                        <option value="Level-10">Level-10</option>
                        <option value="Level-11">Level-11</option>
                        <option value="Level-12">Level-12</option>
                        <option value="Level-13">Level-13</option>
                        <option value="Level-13A">Level-13A</option>
                        <option value="Level-14">Level-14</option>
                        <option value="Level-15">Level-15</option>
                        <option value="Level-16">Level-16</option>
                        <option value="Level-17">Level-17</option>
                        <option value="Level-18">Level-18</option>
                        <option value="Level-18">Level-19</option>
                        <option value="Level-18">Level-20</option>
                        <option value="Level-18">Level-21</option>
                        <option value="Level-18">Level-22</option>
                        <option value="Level-18">Level-23</option>
                        <option value="Level-18">Level-24</option>
                        <option value="Level-18">Level-25</option>
                        <option value="Level-18">Level-26</option>
                        <option value="Level-18">Level-27</option>
                        <option value="Level-18">Level-28</option>
                        <option value="Level-18">Level-29</option>
                        <option value="Level-18">Level-30</option>
                        <option value="Level-18">Level-31</option>
                        <option value="Level-18">Level-32</option>
                </select>
                </td>
                 </tr>
	<?php
                        for($i=1;$i<=40;$i++){
				echo "<tr><td>";
		                echo "<label for='sgmlevel' class='control-label'>Salary Sub Level ".$i."</label>";
                		echo "</td>";
                        	echo "<td> ";
?>
				<input type="text" name="<?php echo "subpaylevel".$i;?>" size="40"  value="<?php echo isset($_POST["subpaylevel"]) ? $_POST["subpaylevel"] : '0'; ?>" class="form-control" placeholder="Salary Sub Pay level"/> 
<?php				echo "</td>";
				echo "</tr>";
			}
                ?>
			
                <tr><td></td>
                <td colspan="2">
                <button type=submit name="paymatrix" >Add Pay Matrix </button>
                <input type="reset" name="Reset" value="Clear"/>
                </td>
            </tr>
	<tr><td colspan=2> </td> </tr>
           </table>
        </form>

    </div>    

<div>
<?php $this->load->view('template/footer.php');?>
</div>
</body>
</html>
