<!--@filename program.php ==>  @kumar.abhay.4187@gmail.com(repopluate) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<?php $this->load->view('template/header.php');?>
<?php $this->load->view('template/menu.php');?>
<html>
<title>Add Program</title>
<?php
/*    echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
    echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
    echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
    echo "Setup";
    echo "<span  style='padding: 8px 8px 8px 20px;'> ";
    echo "|";
    echo anchor('setup/viewprogram/', "View Program", array('title' => 'View Program' , 'class' => 'top_parent'));
    echo "<span  style='padding: 8px 8px 8px 20px;'> ";
    echo "|";
    echo "<span  style='padding: 8px 8px 8px 20px;'>";
    echo "Add Program";
//    echo "View Programs";
    echo "</span>";
    echo "</td>";
    echo "</tr>";
    echo "</table>";
*/
?>
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
<div>
<table style="width:100%;">
<tr><td>
<?php echo anchor('setup/viewprogram/', "Program List" ,array('title' => 'Program List' , 'class' => 'top_parent'));
echo "<td align=\"right\">";
$help_uri = site_url()."/help/helpdoc#ProgramandSeat";
echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
echo "</td>";
?>
        <div>
        <?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>
        <?php if(isset($_SESSION['success'])){?>
        <div class="isa_success"><?php echo $_SESSION['success'];?></div>
        <?php
        };
        if(isset($_SESSION['error']))
        {
        ?> <div  class="isa_success">"<?php echo $_SESSION['error'];?> </div>
        <?php
        }
        ?>
    </div>
    </td></tr>  
</table>
    <form action="<?php echo site_url('setup/program');?>" method="POST" class="form-inline">
    <div>
    <table>
    <tr>
        <td><label for="prgcampus">Select Campus</label></td>
        <td>
            <select name="prgcampus" style="width:100%;">
        	<option value=""disabled selected>------------------Select Campus------------</option>
                <?php foreach($this->scresult as $datas): ?>	
                    <option value="<?php echo $datas->sc_id; ?>"><?php echo $datas->sc_name; ?></option>
		<?php endforeach; ?>
	    </select>          
        <td>
        
    </tr>
    <tr>
        <td><label for="prgdepartment">Select Department</label></td>
         <td>
            <select name="prgdepartment" style="width:100%;">
        	<option value=""disabled selected>--------------Select Department----------</option>
                <?php foreach($this->deptresult as $deptdata): ?>	
                    <option value="<?php echo $deptdata->dept_id; ?>"><?php echo $deptdata->dept_name; ?></option>
		<?php endforeach; ?>
	    </select>          
        <td>
        
    </tr> 
    <tr>
        <td><label for="prgcat">Program Category</label></td>
        <td>
            <select name="prgcat" style="width:100%;">
                <option value=""disabled selected>-----------------Select Category------------</option>
                <!---<option value="Under Graduate" class="dropdown-item">Under Graduate</option>
                <option value="Post Graduate" class="dropdown-item">Post Graduate</option>
                <option value="Research" class="dropdown-item">Research</option>
                <option value="Diploma" class="dropdown-item">Diploma Course</option>
                <option value="Certificate" class="dropdown-item">Certificate Course</option>-->
		<?php foreach($this->prgcat as $datas): ?>	
                    <option value="<?php echo $datas->prgcat_name; ?>"><?php echo $datas->prgcat_name; ?></option>
		<?php endforeach; ?>
            </select>    
        </td>
           <!-- <input type="text" name="prgcat" size="35"/>-->
        <td><?php echo form_error('prgcat')?></td>
	<!--<td>Example : UG, PG, R, Dip etc</td>-->
    </tr> 
	<tr>
        <td><label for="prgpattern">Program Pattern</label></td>
        <td>
            <select name="prgpattern" style="width:100%;">
                <option value=""disabled selected>--------------------Select Pattern------------</option>
                <option value="Yearly" class="dropdown-item">Yearly</option>
                <option value="Semester" class="dropdown-item">Semester</option>
            </select>
        </td>
        <td><?php echo form_error('prgpattern')?></td>
    </tr>
    <tr><td>
        <label for="prgname">Program Name</label></td>
        <td><input type="text" name="prgname"   size="43" value="<?php echo isset($_POST["prgname"]) ? $_POST["prgname"] : ''; ?>" />
        </td><td><?php echo form_error('prgname')?>
	</td><td>Example : Batchlor of Art, Master of Art etc  
    </td></tr>
    <tr><td>
        <label for="prgbranch">Program Branch</label></td>
        <td><input type="text" name="prgbranch"   size="43" value="<?php echo isset($_POST["prgbranch"]) ? $_POST["prgbranch"] : ''; ?>" />
        </td><td><?php echo form_error('prgbranch')?>
	</td><td>Example :UG (Arts, Science, commerce etc), PG (Physics, Math  etc)
    </td></tr>

    <tr><td>
        <label for="prgcode">Program Code</label></td>
        <td><input type="text" name="prgcode"  size="43" value="<?php echo isset($_POST["prgcode"]) ? $_POST["prgcode"] : ''; ?>" />
        </td><td><?php echo form_error('prgcode')?>
    </td></tr>
    <tr><td>
        <label for="prgshort">Program Short</label></td>
        <td><input type="text" name="prgshort"  size="43" value="<?php echo isset($_POST["prgshort"]) ? $_POST["prgshort"] : ''; ?>" />
        </td><td><?php echo form_error('prgshort')?>
    </td></tr>
    <tr><td>
        <label for="prgdesc">Program Description</label></td>
        <td><input type="text" name="prgdesc"   size="43" value="<?php echo isset($_POST["prgdesc"]) ? $_POST["prgdesc"] : ''; ?>" />
        </td><td><?php echo form_error('prgdesc')?>
    </td></tr>
    <tr><td>
        <label for="prgcredit">Program Credit</label></td>
        <td><input type="text" name="prgcredit"   size="43" value="<?php echo isset($_POST["prgcredit"]) ? $_POST["prgcredit"] : ''; ?>" />
        </td><td><?php echo form_error('prgcredit')?>
    </td></tr>
    <tr><td>
        <label for="prgseat">Total Seat</label></td>
        <td><input type="text" name="prgseat"   size="43" value="<?php echo isset($_POST["prgseat"]) ? $_POST["prgseat"] : ''; ?>" />
        </td><td><?php echo form_error('prgseat')?>
    </td></tr>
    <tr>
        <td><label for="prgmintime">Program Min Time</label></td>
        <td><input type="text" name="prgmintime"   size="43" value="<?php echo isset($_POST["prgmintime"]) ? $_POST["prgmintime"] : ''; ?>" />
        </td><td><?php echo form_error('prgmintime')?></td>
        <td>in Years</td>
    </tr>
    <tr>
        <td><label for="prgmaxtime">Program Max Time</label></td>
        <td><input type="text" name="prgmaxtime"   size="43" value="<?php echo isset($_POST["prgmaxtime"]) ? $_POST["prgmaxtime"] : ''; ?>" />
        </td><td><?php echo form_error('prgmaxtime')?></td>
         <td>in Years</td>
    </tr>
<!--    <tr><td>
        <label for="prgcrtid">Program Creator Id</label></td>
        <td><input type="text" name="prgcrtid" />
        </td><td><?//php echo form_error('prgcrtid')?>
    </td></tr>
-->
<!--    <tr><td>
        <input type="submit" name="submit" value="Add Program">
    </td></tr>
-->
    <tr><td></td>
    <td colspan="2">   
        <button name="program" >Submit</button>
        <button name="reset" >Clear</button>
    </td>
    </tr>
    </table></div>
    </form>
<p><br></p>
    <?php echo form_close();	?>
</body>
<?php $this->load->view('template/footer.php');?>
</html>
