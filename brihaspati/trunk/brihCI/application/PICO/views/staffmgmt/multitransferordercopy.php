<!--@filename multitransferordercopy.php  @author Manorama Pal(palseema30@gmail.com) -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title> </title>
	<style>
		.TFtable tr td{border:0px solid black;}	
                .table2 th,td {
                    border: 1px solid black;
                    border-collapse:separate;
                    border-spacing: 1px; 
                }
	</style>
    </head>
    <body style="border:1px solid black;margin-top:5px;">
	<div >
	<img src="uploads/logo/logotanuvas.jpeg" alt="logo" style="width:100%;height:70px;"><br/>
	<table style="width:100%;margin-top:10px;">
        <tr><th>
            <center style="font-size:9;">PROCEEDINGS OF THE REGISTRAR<br/>
            <?php echo $orgname;?><br/>
            <?php echo $orgaddres."  " .$orgpincode;?></center>
            
        </th></tr>
        <tr><th>
            <center style="font-size:9;">PRESENT : <?php echo $regname;?><br/>
            <?php echo $uitdesig;?></center>
            </center>
        </th></tr>
        </table><br/>
        <table style="width:100%;" class="TFtable">
            <thead  style="background-color:#38B0DE;color:white;font-size:18px;"><tr><td colspan="8"></td></tr></thead><br/>
           <br/><?php 
                    $this->data=$this->sismodel->get_listrow('user_input_transfer','uit_id',$alldetail[0]);
                    $allvalues=$this->data->row();
                    echo "<tr><td style=font-size:9;> USO No.</td><td style=font-size:9;>". $allvalues->uit_uso_no ."</td>";
                    echo "<td align=right style=\"font-size:9;float:right;margin-left:500px;\">Dated: ". date('d-m-Y H:i:s',strtotime($allvalues->uit_date)). "</td></tr>";
                    echo "<tr><td style=font-size:9;>Rc No.</td><td style=font-size:9;>".$allvalues->uit_rc_no."</td></tr>";
                    echo "<tr><td style=font-size:9;>Subject:</td><td style=font-size:9;>".$allvalues->uit_subject."</td></tr>";
                    echo "<tr><td style=font-size:9;>Reference:</td><td style=font-size:9;>".$allvalues->uit_referenceno."</td></tr>";
                    echo "<tr><td style=font-size:9;>Order:</td><td style=font-size:9;>".$allvalues->uit_ordercontent."</td></td></tr>";
                ;?>
            </table>
            <table style="font-size: 9;border:1px solid black;" align="center"> 
                <tr align="center" >
                    <th style="border:1px solid black;">Sl.No.</th>
                    <th style="border:1px solid black;">Name and designation</th>
                    <th style="border:1px solid black;">Post & Place to which transferred</th>
                </tr>
                <?php $sno=1;?>
                <?php foreach ($alldetail as $uitdata){ ?>
                    <tr align="center">
                        <td><?php echo $sno++ ; ?></td>
                        <td>
                            <?php $this->data=$this->sismodel->get_listrow('user_input_transfer','uit_id',$uitdata);
                                $detail= $this->data->row();
                                $pfno=$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$detail->uit_staffname)->emp_code;
                                $is_exist= $this->sismodel->isduplicate('hod_list','hl_empcode',$pfno);
                                if($is_exist){
                                    $cdate = date('Y-m-d');
                                    $selectfield="hl_dateto";
                                    $whdata = array ('hl_empcode' => $pfno);
                                    $whorder = 'hl_dateto desc';
                                    $emp_data['rama']= $this->sismodel->get_orderlistspficemore('hod_list',$selectfield,$whdata,$whorder);
                                    $todate=$emp_data['rama'][0]->hl_dateto;
                                    if($todate >= $cdate){        
                                        echo $this->sismodel->get_listspfic1('employee_master','emp_name','emp_id',$detail->uit_staffname)->emp_name."("."HEAD".")";
                                    }
                                    else{
                                        echo $this->sismodel->get_listspfic1('employee_master','emp_name','emp_id',$detail->uit_staffname)->emp_name;
                                    }
                                }
                                else{
                                    echo $this->sismodel->get_listspfic1('employee_master','emp_name','emp_id',$detail->uit_staffname)->emp_name;
                                }
                            ?>
                            <?php if(!empty($detail->uit_desig_from)){
                                echo "<br/> ". $this->commodel->get_listspfic1('designation','desig_name','desig_id',$detail->uit_desig_from)->desig_name;}
                            ?>
                            <?php if(!empty($detail->uit_workingpost_from)){echo " <br/> Working against the post of ". $detail->uit_workingpost_from.",";}?>
                            <?php if(!empty($detail->uit_schm_from)){echo "<br/>". $this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$detail->uit_schm_from)->sd_name.",";}?>
                            <?php if(!empty($detail->uit_workdept_from)){echo "<br/>".$this->commodel->get_listspfic1('Department','dept_name','dept_id',$detail->uit_workdept_from)->dept_name.",";}?>
                            <?php if(!empty($detail->uit_scid_from)){echo "<br/>".$this->commodel->get_listspfic1('study_center','sc_name','sc_id',$detail->uit_scid_from)->sc_name;}?> 
                            
                        </td>
                        <td>
                            <?php if(!empty($detail->uit_desig_to)){echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$detail->uit_desig_to)->desig_name;}?>
                            <?php if(!empty($detail->uit_post_to)){echo "<br/>Against the vacant post of  ".$this->commodel->get_listspfic1('designation','desig_name','desig_id',$detail->uit_post_to)->desig_name.",";}?>
                            <?php if(!empty($detail->uit_schm_to)){echo "<br/>".$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$detail->uit_schm_to)->sd_name.",";}?>
                            <?php if(!empty($detail->uit_dept_to)){echo "<br/>".$this->commodel->get_listspfic1('Department','dept_name','dept_id',$detail->uit_dept_to)->dept_name.",";}?>
                            <?php if(!empty($detail->uit_scid_to)){echo "<br/>".$this->commodel->get_listspfic1('study_center','sc_name','sc_id',$detail->uit_scid_to)->sc_name;}?> 
                        </td>    
                    </tr>         
                <?php   };?> 
            
            </table><br/><br/>
           
            <table class="TFtable" style="width:100%;" >
                
                <tr>
                    <td> <?php echo "All the above teaching staff transferred above are not eligible fot TTA";?>
                    <?php echo "<br/>The date(s)of relief/joining of the above individuals should be intimated by the Heads
                    of/University Officers concerned to this office promptly.";?>
                    </td>
                </tr>
            </table>
            <br/><table class="TFtable" style="width:100%;" >
                <tr><td align="right"><?php  echo $uitdesig;?>&nbsp;&nbsp;&nbsp;&nbsp;                        
                </td></tr>
            </table>
            <br/><table class="TFtable" style="width:100%;" >
                <tr><td>To </td></tr>
                <tr><td style="font-size:9;">The <?php 
                echo "individuals through the University Officers concerned";
                ?></td></tr>
                <br/><br/>
                <tr><td >Copy To</td></tr>
                <tr><td style="font-size:9;">The Dean,  <?php 
                    foreach ($alldetail as $detail1) {
                    $this->data2=$this->sismodel->get_listrow('user_input_transfer','uit_id',$detail1);
                    $detail5= $this->data2->row();
                    echo $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$detail5->uit_scid_to)->sc_name." , ";
                    
                    }
                ?>
                </td></tr>
                <tr><td style="font-size:9;"> The Heads of Departments/Centres/Stations/Labs/Unit concerned</td></tr> 
                <tr><td style="font-size:9;">The System Programmer, Tanuvas chennai</td></tr>
            </table>
           
        </div>   
    </body> 
   
</html>    
