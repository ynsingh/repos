<!--@name add_shdefaultvalue.php  @author Manorama Pal(palseema30@gmail.com) -->

 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <title>Salary Head</title>
    <head>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <script>
            function goBack() {
                // window.location="<?php echo site_url('setup3/salaryhead_list/');?>";
                window.history.back();
            }
        </script>
    </head>
    <body>
        <?php $this->load->view('template/header'); ?>
        <table width="100%">
            <tr>
                <?php
                    echo "<td align=\"left\" width=\"33%\">";
                    echo anchor('setup3/salaryformula_list/', "View Salary Head Formula" ,array('title' => 'View salary head formula' , 'class' => 'top_parent'));
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Salary Head Configuration</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\">";

                ?>
            </tr>
        </table>
        <table width="100%">
           <tr><td>
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
            </td></tr>
        </table>
        <form id="myform" action="<?php echo site_url('setup3/shdefaultvalue');?>" method="POST" enctype="multipart/form-data">
        <div class="scroller_sub_page">
            <table width="100%" border="0">
                <tr>
                    
                    <?php if($this->uri->segment(3) != FALSE){
                            $seloption=$this->uri->segment(3);
                        }
                    //echo "seloption====".$seloption."=======".$urloption;?>
                    <td> Select Pay Band <font color='Red'>*</font>
                        <!--<select id="payband" style="width:350px;" name="payband" required onchange="this.form.submit();"> -->
                        <select id="payband" style="width:350px;" name="payband" required>
                        <?php if (!empty($seloption)) :?>
                        <option value="<?php echo $seloption;?>"><?php 
                            $pname=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$seloption)->sgm_name;
                            $min=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$seloption)->sgm_min;
                            $max=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$seloption)->sgm_max;
                            $gp=$this->sismodel->get_listspfic1('salary_grade_master','sgm_gradepay','sgm_id',$seloption)->sgm_gradepay;
                            echo $pname."( ".$min." - ".$max." )".$gp;
                        ?></option>
                        <?php else: ?>
                        <option selected="selected" disabled selected>--------Select Pay Band-----</option>
                        <?php endif ;?>
                            <?php foreach($this->salgrade as $sgdata): ?>	
   				<option value="<?php echo $sgdata->sgm_id; ?>"><?php echo $sgdata->sgm_name."( ".$sgdata->sgm_min." - ".$sgdata->sgm_max." )".$sgdata->sgm_gradepay; ?></option> 
                            <?php endforeach; ?>
                        </select>
                        <button name="load" >Submit</button> 
                    </td>
                   
	     	</tr>
            </table>                  
            <table class="TFtable">
                <thead>
                <tr>
                    <th>Sr.No</th>
                    <th>Head Code</th>
                    <th>Head Name</th>
                    <th>Under</th>
                    <th>Default value</th>
                </tr>
                </thead>
                <tbody>
                    <?php $serial_no = 1; $i=0;$flag=false; ?>
                        <?php if( count($this->salhead ) ):  ?>
                            <?php foreach($this->salhead as $record){ 
                               // $flag = false;
                            ?>
                            
                            <tr>
                                <td><?php echo $serial_no++; ?></td>
                                <td><?php echo $record->sh_code; ?></td>
                                <td><?php echo $record->sh_name."( ".$record->sh_shortname." )"; ?></td>
                                <td><?php
                                if($record->sh_type =="I"){
                                    echo "Income";
                                }
                                else{
                                   echo "Deduction"; 
                                }
                                ; ?></td>
                                <?php if($record->sh_calc_type =="Y") :?>
                                
                                    <td><input type="text" name="forval" value="<?php $salformula=$this->sismodel->get_listspfic1('salary_formula','sf_formula ','sf_salhead_id',$record->sh_id);
                                    if(!empty($salformula)){
                                        echo $salformula->sf_formula;
                                    }
                                    ;?>" 
                                readonly class="form-control" size="20" /><br></td>
                                    
                                <?php else: ?>
                                    <td>
                                   <?php if(!empty($shdvalue)): ?> 
                                       
                                        <?php foreach($shdvalue as $dfline){
                                              //  echo"if==".$record->sh_id."---".$dfline->shdv_salheadid."===".$seloption."===".$dfline->shdv_paybandid;
                                                if($record->sh_id === $dfline->shdv_salheadid && $seloption === $dfline->shdv_paybandid): 
                                                   //  echo"idsh===".$dfline->shdv_salheadid."reco===".$record->sh_id."jh====".$dfline->shdv_paybandid."seloption====".$seloption;
                                                   $dfval=$dfline->shdv_defaultvalue; 
                                                  // echo "dfval===".$dfval;
                                                   $flag = true;
                                                endif;   
                                                };   
                                            if($flag === true): ?> 
                                                    <?php  // echo "in truecase"; ?>                             
                                                <input type="text" name="defaultval<?php echo $i;?>" value="<?php echo $dfval; ?>" class="form-control" size="20" /><br>    
                                            <?php else: ?>
                                                <?php // echo "in true else case"; ?> 
                                                <input type="text" name="defaultval<?php echo $i;?>" value="<?php echo isset($_POST["defaultval".$i]) ? $_POST["defaultval".$i] : '0'; ?>" class="form-control" size="20" /><br> 
                                            <?php endif ;?>                                   
                                      
                                    <?php  else: ?> 
                                               <?php // echo "in last else case"; ?>  
                                    <input type="text" name="defaultval<?php echo $i;?>" value="<?php echo isset($_POST["defaultval".$i]) ? $_POST["defaultval".$i] : '0'; ?>" class="form-control" size="20" /><br></td>             
                                    <?php endif ;?> 
                                                
                                <?php endif ;?> 
                               <input type="hidden" name="sheadid<?php echo $i;?>" value="<?php echo $record->sh_id ; ?>"> 
                               
                            </tr> 
                               
                        <?php $i++;  }; ?>
                    <?php else : ?>
                        <td colspan= "13" align="center"> No Records found...!</td>
                    <?php endif;?>
                     <tr><td colspan="6"> <button name="update">Update</button></td></tr>    
                     <input type="hidden" name="totalsize" value="<?php echo $i;?>">

               </tbody>    
            </table>    
         
             </div><!------scroller div------>    
            </form>    
      
        <p> &nbsp; </p>
      
        <div align="center"> <?php $this->load->view('template/footer');?></div>  
    </body>   
    