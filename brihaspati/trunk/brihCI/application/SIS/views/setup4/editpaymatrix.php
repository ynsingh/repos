<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Edit Pay matrix</title>
<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
<script>

		function levelofpay(val){
                         //var wt= $('#worktypeid').val();
                         var wt= val;
//                      alert(wt);
                         $.ajax({
                                type: "POST",
                                url: "<?php echo base_url();?>sisindex.php/jslist/getlevelpay",
                                data: {"wt" : wt},
                                dataType:"html",
                                success: function(data){
  //            alert(data);
                                        $('#pmlevel').html(data.replace(/^"|"$/g, ''));
                                }
                        });
                }


</script>
<body>
<div>
<?php $this->load->view('template/header.php');?>

<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
</div>

<div align="left">
<table style="margin-left:0%;">
<tr><td>
<?php //echo anchor('setup4/displaypaymatrix/', "View Pay Matrix", array('title' => 'View Pay Matrix' , 'class' => 'top_parent'));
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
	<form action="<?php echo site_url('setup4/editpaymatrix/'.$id);?>" method="POST" class="form-inline">
		<?php foreach($pmdata as $row){ ?>
          <table cellpadding="16" class="TFtable">
		<tr>
                        <td><label for="paycomm" class="control-label">Pay Commission:</label></td>
                        <td>
                            <select name="paycomm" id="paycomm" style="width:400px;">
                                <option value="<?php echo $row->pm_pc;?>"><?php echo $row->pm_pc;?></option>
                                <option value="" >------Select ---------------</option>
                                <option value="7th">7th</option>
                            </select>
                        </td>
                </tr>
                <tr>
                <td><label for="workingtype" class="control-label">Working Type</label>
                </td><td>
                        <div><select id="worktypeid" name="workingtype" required style="width:400px;" onchange="levelofpay(this.value)">
                                <option value="<?php echo $row->pm_wt;?>"><?php echo $row->pm_wt;?></option>
                        <option value=''>-------- Working Type ---------</option>
                        <option value="Teaching">Teaching</option>
                        <option value="Non Teaching">Non Teaching</option>
                    </select></div>
                </td>
                </tr>
                <tr>
                <td>
                <label for="pmlevel" class="control-label">Salary Level :</label>
                </td>
                <td>
		<div>
		<select name="pmlevel" style="width:400px;" id="pmlevel">
                                <option value="<?php echo $row->pm_level;?>"><?php echo $row->pm_level;?></option>
                <option value="" >------ Select Level------</option>
                </select>
		</div>
                </td>
                 </tr>
<?php		$a ="pm_sublevel"; ?>
	<?php
                        for($i=1;$i<=40;$i++){
				echo "<tr><td>";
		                echo "<label for='sgmlevel' class='control-label'>Salary Sub Level ".$i."</label>";
                		echo "</td>";
                        	echo "<td> ";
?>
				<input type="text" name="<?php echo "subpaylevel".$i;?>" size="40"  value="<?php echo $row->{$a.$i}; ?>" class="form-control" placeholder="Salary Sub Pay level"/> 
<?php				echo "</td>";
				echo "</tr>";
			}
                ?>
			
                <tr><td></td>
<?php } ?>
                <td colspan="2">
                <button type=submit name="editpaymatrix" >Edit Pay Matrix </button>
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
