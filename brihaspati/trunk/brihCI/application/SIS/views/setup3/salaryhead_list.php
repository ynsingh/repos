<!--@filename salaryhead_list.php  @author Manorama Pal(palseema30@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   
    </head>
    <body>
            <?php $this->load->view('template/header'); ?>
           
        <table width="100%">
	<tr>
        <?php 
        echo "<td align=\"left\" width=\"25%\">";
	echo anchor('setup3/salaryhead/', "Add Salary Head" ,array('title' => 'View salary head ' , 'class' => 'top_parent'));
        echo "</td>";
        echo "<td align=\"center\" width=\"25%\">";
	echo anchor('setup3/salaryhead_list/I', "View Salary Earning Head" ,array('title' => 'View Salary Earning Head ' , 'class' => 'top_parent'));
        echo "</td>";
        echo "<td align=\"center\" width=\"25%\">";
	echo anchor('setup3/salaryhead_list/D', "View Salary Deduction-Subscription Head" ,array('title' => 'View Salary Deduction-Subscription Head ' , 'class' => 'top_parent'));
        echo "</td>";
        echo "<td align=\"right\" width=\"25%\">";
	echo anchor('setup3/salaryhead_list/L', "View Loan Salary Head" ,array('title' => 'View Salary Loan Head ' , 'class' => 'top_parent'));
        echo "</td>";
        ?>
	</tr><tr>
        <?php 
//        echo "<td align=\"left\" width=\"33%\">";
//	echo anchor('setup3/salaryhead/', "Add Salary Head" ,array('title' => 'View salary head ' , 'class' => 'top_parent'));
  //      echo "</td>";
        echo "<td align=\"center\" colspan=4>";
        echo "<b>Salary ";
		if($ht == "I")
			echo "Earning";
		if($ht == "D")
			echo "Deduction-Subscription";
		if($ht == "L")
			echo "Loan";
	echo " Head List</b>";
        echo "</td>";
   //     echo "<td align=\"right\" width=\"33%\">";
//	$help_uri = site_url()."/help/helpdoc#SalaryHeadList";
//        echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
  //      echo "</td>";
        ?>
	</tr><tr>
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
            <!--<form name="actionForm" action="action.php" method="post" onsubmit="return deleteConfirm();"/>-->
            <form id="myform" action="<?php echo site_url('setup3/salaryhead_list/');?>" method="POST" enctype="multipart/form-data">
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
                    <th>Loan</th> 
		<?php 
		//	echo "<th>";
		//	 if($ht == "I")
                //	    	echo "Income";
		//	 if($ht == "D")
                  //  		echo "Deduction";
		//	 if($ht == "L")
                  //  		echo "Loan";
		//	echo "</th>";
		?>
                    <th>Formula</th>
                    <th>Taxable</th>
                    <!-- <th>Head Description</th> -->
                    <th>Action</th>
                    
                </tr>
            </thead>
            <tbody>
                <?php $serial_no = 1;
		?>
            <!-- ------------------------------------bothtnt heads --------------------------------- -->
           
            <?php if( count($tntboth) ):  ?>
                    <tr> <td colspan="13"><?php echo "<b>Common Salary Heads</b>"; ?></td></tr>
                    <?php foreach($tntboth as $record){ ?>
                        <tr>
                        
                            <td><?php echo $serial_no++; ?></td>
                            
                            <td>
                                <?php $ledgercode=$this->sismodel->get_listspfic1('salary_head','sh_ledgercode','sh_id',$record);
                                   if(!empty($ledgercode)){     
                                    $lcode=$ledgercode->sh_ledgercode; 
                               
                                    echo $lcode;
                                   } ?>
                                    
                            </td>    
                            <td><?php $hcode=$this->sismodel->get_listspfic1('salary_head','sh_code','sh_id',$record)->sh_code;
                                echo $hcode; ?></td>
                            <td><?php $shname=$this->sismodel->get_listspfic1('salary_head','sh_name','sh_id',$record)->sh_name;
                                echo $shname; 
                               /* $shtnt=$this->sismodel->get_listspfic1('salary_head','sh_tnt','sh_id',$record);
                                if(!empty($shtnt)){
                                    $tnt=$shtnt->sh_tnt;
                                    echo '( '.$tnt .' )';
                                }*/
                            ?></td>
                            <td><?php $shortname=$this->sismodel->get_listspfic1('salary_head','sh_shortname','sh_id',$record);
                                if(!empty($shortname)){
                                    $shortn=$shortname->sh_shortname;
                                 echo $shortn;
                                }?></td>
                            <td><?php $catrgy=$this->sismodel->get_listspfic1('salary_head','sh_category','sh_id',$record);
                                if(!empty($catrgy)){
                                    $shcatrgy=$catrgy->sh_category;
                                    echo $shcatrgy; 
                                }?></td>
                            
                            
                            <td> 
                                
                                <?php $shtype=$this->sismodel->get_listspfic1('salary_head','sh_type','sh_id',$record)->sh_type;
                                if($shtype == "I"): ?>
                                <input type="checkbox" name="check_list[]" checked ="true" value="<?php echo $record.',I,'.$shtype; ?>" />
                                <?php else : ?>
                                <input type="checkbox" name="check_list[]"  value="<?php echo $record.',I,'.$shtype; ?>" />
                                <?php endif;?>
                            </td>
                            <td>
                                <?php if($shtype == "D"): ?>
                                <input type="checkbox" name="check_list[]" checked ="true" value="<?php echo $record.',D,'.$shtype; ?>" />
                                <?php else : ?>
                                <input type="checkbox" name="check_list[]"  value="<?php echo $record.',D,'.$shtype; ?>" />
                                <?php endif;?>
                            </td>
                            <td>
                                <?php if($shtype == "L"): ?>
                                <input type="checkbox" name="check_list[]" checked ="true" value="<?php echo $record.',L,'.$shtype; ?>" />
                                <?php else : ?>
                                <input type="checkbox" name="check_list[]"  value="<?php echo $record.',L,'.$shtype; ?>" />
                                <?php endif;?>
                            </td>
                            <td>
                                <?php $shcaltype=$this->sismodel->get_listspfic1('salary_head','sh_calc_type','sh_id',$record)->sh_calc_type;
                                if($shcaltype == "Y"): ?>
                                <input type="checkbox" name="check_list[]" checked ="true"  value="<?php echo $record.',F,'.$shcaltype; ?>" />
                                <?php else : ?>
                                <input type="checkbox" name="check_list[]"  value="<?php echo $record.',F,'.$shcaltype; ?>" />
                                <?php endif;?>
                            </td>
                            <td>
                                <?php $shtaxable=$this->sismodel->get_listspfic1('salary_head','sh_taxable','sh_id',$record)->sh_taxable;
                                if($shtaxable == "Y"): ?>
                                <input type="checkbox" name="check_list[]" checked ="true" value="<?php echo $record.',T,'.$shtaxable; ?>" />
                                <?php else : ?>
                                <input type="checkbox" name="check_list[]" value="<?php echo $record.',T,'.$shtaxable; ?>" />
                                <?php endif;?>
                            </td>
                            <!-- <td><?php /* $shdesc=$this->sismodel->get_listspfic1('salary_head','sh_description','sh_id',$record);
                                if(!empty($shdesc)){
                                    $description=$shdesc->sh_description;
                                echo $description; } */ ?></td> -->
                            <td> <?php echo anchor("setup3/edit_salaryhead/{$record}","Edit",array('title' => 'Edit Details' , 'class' => 'red-link')); ?><br/>
                            </td>
                            <input type="hidden" name="item_id" value="<?php echo $record;?>"/>
                        </tr>
                        
                    <?php }; ?>
                    
                <?php else : ?>
                 <tr>   <td colspan= "13" align="center"> No Records found...!</td></tr>
                <?php endif;?>
            <!-----------------------------------------------end both------------------------------>
             <!--------------------------------------teaching heads ---------------------------------->
              <?php if( count($teach) ):  ?>
                    <tr> <td colspan="13"><?php echo "<b>Teaching Salary Heads</b>"; ?></td></tr>
                    <?php foreach($teach as $record){ ?>
                        <tr>
                        
                            <td><?php echo $serial_no++; ?></td>
                            
                            <td>
                                <?php $ledgercode=$this->sismodel->get_listspfic1('salary_head','sh_ledgercode','sh_id',$record);
                                   if(!empty($ledgercode)){     
                                    $lcode=$ledgercode->sh_ledgercode; 
                               
                                    echo $lcode;
                                   } ?>
                                    
                            </td>    
                            <td><?php $hcode=$this->sismodel->get_listspfic1('salary_head','sh_code','sh_id',$record)->sh_code;
                                echo $hcode; ?></td>
                            <td><?php $shname=$this->sismodel->get_listspfic1('salary_head','sh_name','sh_id',$record)->sh_name;
                                echo $shname; 
                                $shtnt=$this->sismodel->get_listspfic1('salary_head','sh_tnt','sh_id',$record);
                                if(!empty($shtnt)){
                                    $tnt=$shtnt->sh_tnt;
                                    echo '( '.$tnt .' )';
                                }
                            ?></td>
                            <td><?php $shortname=$this->sismodel->get_listspfic1('salary_head','sh_shortname','sh_id',$record);
                                if(!empty($shortname)){
                                    $shortn=$shortname->sh_shortname;
                                 echo $shortn;
                                }?></td>
                            <td><?php $catrgy=$this->sismodel->get_listspfic1('salary_head','sh_category','sh_id',$record);
                                if(!empty($catrgy)){
                                    $shcatrgy=$catrgy->sh_category;
                                    echo $shcatrgy; 
                                }?></td>
                            
                            
                            <td> 
                                
                                <?php $shtype=$this->sismodel->get_listspfic1('salary_head','sh_type','sh_id',$record)->sh_type;
                                if($shtype == "I"): ?>
                                <input type="checkbox" name="check_list[]" checked ="true" value="<?php echo $record.',I,'.$shtype; ?>" />
                                <?php else : ?>
                                <input type="checkbox" name="check_list[]"  value="<?php echo $record.',I,'.$shtype; ?>" />
                                <?php endif;?>
                            </td>
                            <td>
                                <?php if($shtype == "D"): ?>
                                <input type="checkbox" name="check_list[]" checked ="true" value="<?php echo $record.',D,'.$shtype; ?>" />
                                <?php else : ?>
                                <input type="checkbox" name="check_list[]"  value="<?php echo $record.',D,'.$shtype; ?>" />
                                <?php endif;?>
                            </td>
                            <td>
                                <?php if($shtype == "L"): ?>
                                <input type="checkbox" name="check_list[]" checked ="true" value="<?php echo $record.',L,'.$shtype; ?>" />
                                <?php else : ?>
                                <input type="checkbox" name="check_list[]"  value="<?php echo $record.',L,'.$shtype; ?>" />
                                <?php endif;?>
                            </td>
                            <td>
                                <?php $shcaltype=$this->sismodel->get_listspfic1('salary_head','sh_calc_type','sh_id',$record)->sh_calc_type;
                                if($shcaltype == "Y"): ?>
                                <input type="checkbox" name="check_list[]" checked ="true"  value="<?php echo $record.',F,'.$shcaltype; ?>" />
                                <?php else : ?>
                                <input type="checkbox" name="check_list[]"  value="<?php echo $record.',F,'.$shcaltype; ?>" />
                                <?php endif;?>
                            </td>
                            <td>
                                <?php $shtaxable=$this->sismodel->get_listspfic1('salary_head','sh_taxable','sh_id',$record)->sh_taxable;
                                if($shtaxable == "Y"): ?>
                                <input type="checkbox" name="check_list[]" checked ="true" value="<?php echo $record.',T,'.$shtaxable; ?>" />
                                <?php else : ?>
                                <input type="checkbox" name="check_list[]" value="<?php echo $record.',T,'.$shtaxable; ?>" />
                                <?php endif;?>
                            </td>
                           <!-- <td><?php /* $shdesc=$this->sismodel->get_listspfic1('salary_head','sh_description','sh_id',$record);
                                if(!empty($shdesc)){
                                    $description=$shdesc->sh_description;
                                echo $description; } */?></td> -->
                            <td> <?php echo anchor("setup3/edit_salaryhead/{$record}","Edit",array('title' => 'Edit Details' , 'class' => 'red-link')); ?><br/>
                            </td>
                            <input type="hidden" name="item_id" value="<?php echo $record;?>"/>
                        </tr>
                        
                    <?php }; ?>
                    
                <?php else : ?>
                   <tr> <td colspan= "13" align="center"> No Records found...!</td></tr>
                <?php endif;?>
               <!--------------------------------------end teaching heads ------------------------------------>      
               <!---------------------------------NON taeching--------------------------------------> 
               
               <?php if( count($nonteach) ):  ?>
                    <tr> <td colspan="13"><?php echo "<b> Non Teaching Salary Heads</b>"; ?></td></tr>
                    <?php foreach($nonteach as $record){ ?>
                        <tr>
                        
                            <td><?php echo $serial_no++; ?></td>
                            
                            <td>
                                <?php $ledgercode=$this->sismodel->get_listspfic1('salary_head','sh_ledgercode','sh_id',$record);
                                   if(!empty($ledgercode)){     
                                    $lcode=$ledgercode->sh_ledgercode; 
                               
                                    echo $lcode;
                                   } ?>
                                    
                            </td>    
                            <td><?php $hcode=$this->sismodel->get_listspfic1('salary_head','sh_code','sh_id',$record)->sh_code;
                                echo $hcode; ?></td>
                            <td><?php $shname=$this->sismodel->get_listspfic1('salary_head','sh_name','sh_id',$record)->sh_name;
                                echo $shname; 
                                $shtnt=$this->sismodel->get_listspfic1('salary_head','sh_tnt','sh_id',$record);
                                if(!empty($shtnt)){
                                    $tnt=$shtnt->sh_tnt;
                                    echo '( '.$tnt .' )';
                                }
                            ?></td>
                            <td><?php $shortname=$this->sismodel->get_listspfic1('salary_head','sh_shortname','sh_id',$record);
                                if(!empty($shortname)){
                                    $shortn=$shortname->sh_shortname;
                                 echo $shortn;
                                }?></td>
                            <td><?php $catrgy=$this->sismodel->get_listspfic1('salary_head','sh_category','sh_id',$record);
                                if(!empty($catrgy)){
                                    $shcatrgy=$catrgy->sh_category;
                                    echo $shcatrgy; 
                                }?></td>
                            
                            
                            <td> 
                                
                                <?php $shtype=$this->sismodel->get_listspfic1('salary_head','sh_type','sh_id',$record)->sh_type;
                                if($shtype == "I"): ?>
                                <input type="checkbox" name="check_list[]" checked ="true" value="<?php echo $record.',I,'.$shtype; ?>" />
                                <?php else : ?>
                                <input type="checkbox" name="check_list[]"  value="<?php echo $record.',I,'.$shtype; ?>" />
                                <?php endif;?>
                            </td>
                            <td>
                                <?php if($shtype == "D"): ?>
                                <input type="checkbox" name="check_list[]" checked ="true" value="<?php echo $record.',D,'.$shtype; ?>" />
                                <?php else : ?>
                                <input type="checkbox" name="check_list[]"  value="<?php echo $record.',D,'.$shtype; ?>" />
                                <?php endif;?>
                            </td>
                            <td>
                                <?php if($shtype == "L"): ?>
                                <input type="checkbox" name="check_list[]" checked ="true" value="<?php echo $record.',L,'.$shtype; ?>" />
                                <?php else : ?>
                                <input type="checkbox" name="check_list[]"  value="<?php echo $record.',L,'.$shtype; ?>" />
                                <?php endif;?>
                            </td>
                            <td>
                                <?php $shcaltype=$this->sismodel->get_listspfic1('salary_head','sh_calc_type','sh_id',$record)->sh_calc_type;
                                if($shcaltype == "Y"): ?>
                                <input type="checkbox" name="check_list[]" checked ="true"  value="<?php echo $record.',F,'.$shcaltype; ?>" />
                                <?php else : ?>
                                <input type="checkbox" name="check_list[]"  value="<?php echo $record.',F,'.$shcaltype; ?>" />
                                <?php endif;?>
                            </td>
                            <td>
                                <?php $shtaxable=$this->sismodel->get_listspfic1('salary_head','sh_taxable','sh_id',$record)->sh_taxable;
                                if($shtaxable == "Y"): ?>
                                <input type="checkbox" name="check_list[]" checked ="true" value="<?php echo $record.',T,'.$shtaxable; ?>" />
                                <?php else : ?>
                                <input type="checkbox" name="check_list[]" value="<?php echo $record.',T,'.$shtaxable; ?>" />
                                <?php endif;?>
                            </td>
                           <!-- <td><?php /* $shdesc=$this->sismodel->get_listspfic1('salary_head','sh_description','sh_id',$record);
                                if(!empty($shdesc)){
                                    $description=$shdesc->sh_description;
                                echo $description; } */?></td> -->
                            <td> <?php echo anchor("setup3/edit_salaryhead/{$record}","Edit",array('title' => 'Edit Details' , 'class' => 'red-link')); ?><br/>
                            </td>
                            <input type="hidden" name="item_id" value="<?php echo $record;?>"/>
                        </tr>
                        
                    <?php }; ?>
                    
                <?php else : ?>
			<tr> <td colspan= "13" align="center"> No Records found...!</td></tr>
                <?php endif;?>
               
		</tbody>
               <!-- <tr>
                    <td colspan="12">
                        <div><button  name='applydata'>Update</button></div> 
                    </td>
                </tr> -->
                </form>
        </table>
        </div><!------scroller div------>
        <p> &nbsp; </p>
        <div align="center">  <?php $this->load->view('template/footer');?></div>
        
    </body>    
</html>   
