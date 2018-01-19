<!--@filename salaryhead_list.php  @author Manorama Pal(palseema30@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   
    </head>
    <body>
            <?php $this->load->view('template/header'); ?>
           
        <table width="100%"><tr colspan="2">
        <?php 
        echo "<td align=\"left\" width=\"33%\">";
	echo anchor('setup3/salaryhead/', "Add Salary Head" ,array('title' => 'View salary head ' , 'class' => 'top_parent'));
        echo "</td>";
        echo "<td align=\"center\" width=\"34%\">";
        echo "<b>Salary Head List</b>";
        echo "</td>";
        echo "<td align=\"right\" width=\"33%\">";
	$help_uri = site_url()."/help/helpdoc#SalaryHeadList";
        echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
        echo "</td>";
        ?>
        <div>     
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php echo form_error('<div class="isa_error">','</div>');?>
                <?php
                if((isset($_SESSION['success'])) && ($_SESSION['success'])!=''){
                    echo "<div  class=\"isa_success\">";
                    echo $_SESSION['success'];
                    echo "</div>";
                }
                if((isset($_SESSION['err_message'])) && (($_SESSION['err_message'])!='')){
                    echo "<div  class=\"isa_error\">";
                    echo $_SESSION['err_message'];
                    echo "</div>";
                }
                ;?>
        </div>
</tr>
        </table>
        <div class="scroller_sub_page">
        <table class="TFtable" >
            <thead>
                <tr>
                    <th>Sr.No</th>
                    <th>Ledger Code</th>
                    <th>Head Code</th>
                    <th>Head Name</th>
                    <th>Short Name</th>
                    <th>Category</th>
                    <th>Income</th>
                    <th>Deduction</th>
                    <th>Formula</th>
                    <th>Taxable</th>
                    <th>Head Description</th>
                    <th>Action</th>
                    
                </tr>
            </thead>
            <tbody>
                <?php $serial_no = 1;?>
              <?php if( count($records) ):  ?>
                    <?php foreach($records as $record){ ?>
                        <tr>
                            <td><?php echo $serial_no++; ?></td>
                            
                            <td>
                                <?php if(!empty($record->sh_ledgercode)):?>
                                <?php echo $record->sh_ledgercode;?>
                                <?php endif;?>
                            </td>    
                            <td><?php echo $record->sh_code; ?></td>
                            <td><?php echo $record->sh_name; ?></td>
                            <td><?php echo $record->sh_shortname; ?></td>
                            <td><?php echo $record->sh_category; ?></td>
                            <td>
                                <?php if($record->sh_type == "I"): ?>
                                <input type="checkbox" name="Income" checked ="true" value="<?php echo $record->sh_type; ?>" />
                                <?php else : ?>
                                <input type="checkbox" name="Income"  value="<?php echo $record->sh_type; ?>" />
                                <?php endif;?>
                            </td>
                            <td>
                                <?php if($record->sh_type == "D"): ?>
                                <input type="checkbox" name="Deduction" checked ="true" value="<?php echo $record->sh_type; ?>" />
                                <?php else : ?>
                                <input type="checkbox" name="Income"  value="<?php echo $record->sh_type; ?>" />
                                <?php endif;?>
                            </td>
                            <td>
                                <?php if($record->sh_calc_type == "Y"): ?>
                                <input type="checkbox" name="formula" checked ="true"  value="<?php echo $record->sh_calc_type; ?>" />
                                <?php else : ?>
                                <input type="checkbox" name="formula"  value="<?php echo $record->sh_calc_type; ?>" />
                                <?php endif;?>
                            </td>
                            <td>
                                <?php if($record->sh_taxable == "Y"): ?>
                                <input type="checkbox" name="taxable" checked ="true" value="<?php echo $record->sh_taxable; ?>" />
                                <?php else : ?>
                                <input type="checkbox" name="taxable" value="<?php echo $record->sh_taxable; ?>" />
                                <?php endif;?>
                            </td>
                            <td><?php echo $record->sh_description; ?></td>
                            <td> <?php echo anchor("setup3/edit_salaryhead/{$record->sh_id}","Edit",array('title' => 'Edit Details' , 'class' => 'red-link')); ?><br/>
                            </td>
                            
                        </tr>
                        
                    <?php }; ?>
                    <!--   <tr>
                            <td colspan="12">
                            <?php //echo form_open_multipart('setup3/salaryhead_list');?>
                            <div><input type='submit' name='applydata' id="btnUpload"  value='Apply'/></div> 
                            </form>
                            </td>
                        </tr>   -->   
                <?php else : ?>
                    <td colspan= "13" align="center"> No Records found...!</td>
                <?php endif;?>
               
		</tbody>
        </table>
        </div><!------scroller div------>
        <p> &nbsp; </p>
        <div align="center">  <?php $this->load->view('template/footer');?></div>
        
    </body>    
</html>   
