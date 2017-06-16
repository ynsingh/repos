<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<?php $this->load->view('template/header.php');?>
 <h1>Welcome <?= $this->session->userdata('username') ?></h1>
 <?php $this->load->view('template/menu.php');?>
<html>

<body>
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
<br>
<div align="left">
<table style="margin-left:10px;">
<tr><td>
<?php echo anchor('setup/viewprogram/', "Program List" ,array('title' => 'Program List' , 'class' => 'top_parent'));
$help_uri = site_url()."/help/helpdoc#ProgramandSeat";
echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:77%\">Click for Help</b></a>";
?>
</td></tr>
</table>
</div>

<table> 
    <tr colspan="2"><td>    
    <div align="left" style="margin-left:30px;width:1700px;">
        <?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>
        <?php if(isset($_SESSION['success'])){?>
        <div class="isa_success"><?php echo $_SESSION['success'];?></div>
        <?php
        };
        if(isset($_SESSION['error']))
        {
        ?> <div style="margin-left:30px"; class="isa_success">"<?php echo $_SESSION['error'];?> </div>
        <?php
        }
        ?>
    </div>
    </td></tr>  
</table>

    <form action="<?php echo site_url('setup/program');?>" method="POST" class="form-inline">
    <table style="margin-left:30px;">
    <tr>
        <td><label for="prgcampus">Select Campus</label></td>
        <td>
            <select name="prgcampus">
        	<option value=""disabled selected>--------------Select Campus------------</option>
                <?php foreach($this->scresult as $datas): ?>	
                    <option value="<?php echo $datas->sc_id; ?>"><?php echo $datas->sc_name; ?></option>
		<?php endforeach; ?>
	    </select>          
        <td>
        
    </tr>
    <tr>
        <td><label for="prgdepartment">Select Depatment</label></td>
         <td>
            <select name="prgdepartment">
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
            <select name="prgcat">
                <option value=""disabled selected>--------------Select Category------------</option>
                <option value="Under Graduate" class="dropdown-item">Under Graduate</option>
                <option value="Post Graduate" class="dropdown-item">Post Graduate</option>
                <option value="Research" class="dropdown-item">Research</option>
                <option value="Diploma Course" class="dropdown-item">Diploma Course</option>
                <option value="Certificate Course" class="dropdown-item">Certificate Course</option>
            </select>    
        </td>
           <!-- <input type="text" name="prgcat" size="35"/>-->
        <td><?php echo form_error('prgcat')?></td>
	<!--<td>Example : UG, PG, R, Dip etc</td>-->
    </tr> 
    <tr><td>
        <label for="prgname">Program Name</label></td>
        <td><input type="text" name="prgname" size="35"/>
        </td><td><?php echo form_error('prgname')?>
	</td><td>Example : Batchlor of Art, Master of Art etc  
    </td></tr>
    <tr><td>
        <label for="prgbranch">Program Branch</label></td>
        <td><input type="text" name="prgbranch" size="35"/>
        </td><td><?php echo form_error('prgbranch')?>
	</td><td>Example : Physics, Math  etc
    </td></tr>
    <tr><td>
        <label for="prgseat">Total Seat</label></td>
        <td><input type="text" name="prgseat" size="35"/>
        </td><td><?php echo form_error('prgseat')?>
    </td></tr>

    <tr><td>
        <label for="prgcode">Program Code</label></td>
        <td><input type="text" name="prgcode" size="35"/>
        </td><td><?php echo form_error('prgcode')?>
    </td></tr>
    <tr><td>
        <label for="prgshort">Program Short</label></td>
        <td><input type="text" name="prgshort" size="35"/>
        </td><td><?php echo form_error('prgshort')?>
    </td></tr>
    <tr><td>
        <label for="prgdesc">Program Description</label></td>
        <td><input type="text" name="prgdesc" size="35"/>
        </td><td><?php echo form_error('prgdesc')?>
    </td></tr>
    <tr>
        <td><label for="prgmintime">Program Min Time</label></td>
        <td><input type="text" name="prgmintime" size="35"/>
        </td><td><?php echo form_error('prgmintime')?></td>
        <td>in Years</td>
    </tr>
    <tr>
        <td><label for="prgmaxtime">Program Max Time</label></td>
        <td><input type="text" name="prgmaxtime" size="35"/>
        </td><td><?php echo form_error('prgmaxtime')?></td>
         <td>in Years</td>
    </tr>
<!--    <tr><td>
        <label for="prgcrtid">Program Creator Id</label></td>
        <td><input type="text" name="prgcrtid" />
        </td><td><?php echo form_error('prgcrtid')?>
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
    </table>
    </form>
    <?php echo form_close();	?>
</body>
<?php $this->load->view('template/footer.php');?>
</html>
