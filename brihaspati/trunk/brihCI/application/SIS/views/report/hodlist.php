
<!--@filename hodlist.php  @author Manorama Pal(palseema30@gmail.com) 
    @filename hodlist.php  @author Neha Khullar(nehukhullar@gmail.com) 
-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">  
        <style type="text/css" media="print">
            @page {
                size: auto;   /* auto is the initial value */
                margin:0;  /* this affects the margin in the printer settings */
            }
        </style>
          <script>
            function printDiv(printme) {
                var printContents = document.getElementById(printme).innerHTML; 
                //alert("printContents==="+printContents);
                var originalContents = document.body.innerHTML;      
                document.body.innerHTML = "<html><head><title></title></head><body style='width:100%;' ><img src='<?php echo base_url(); ?>uploads/logo/logotanuvas.jpeg' alt='logo' style='width:100%;height:100px;' >"+" <div style='width:100%;height:100px;'>  " + printContents + "</div>"+"</body>";
                window.print();     
                document.body.innerHTML = originalContents;
            }
        </script>     
       
    </head>
    <body>
    <?php $this->load->view('template/header'); ?>
<form action="<?php echo site_url('report/hodlist');?>" id="myForm" method="POST" class="form-inline">
         <table width="100%" border="0">
            <tr style="font-weight:bold;width:100%;">
                <td>   University Officer
                    <select name="uoff" id="uoff" style="width:600px;">
                        <?php if  ((!empty($uolt))&&($uolt != 'All')){ ?>
                        <option value="<?php echo $uolt; ?>" > <?php echo $this->lgnmodel->get_listspfic1('authorities', 'name', 'priority',$uolt)->name ." ( ". $this->lgnmodel->get_listspfic1('authorities', 'code', 'priority',$uolt)->code ." )"; ?></option>
                        <?php  } ?>
                      <option value=""  >- Select University officer -</option>
                      <option value="" >All</option> 
			<?php foreach($uoc as $camdata): ?>
                                <option class="test" value="<?php echo $camdata->priority; ?>"><?php echo $camdata->name ."( ".$camdata->code ." )"; ?></option>
                        <?php endforeach; ?>
                    </select>
                </td>
                <td valign="center">
                    <input type="submit" name="filter" id="crits" value="Search"  />
                </td>
            </tr>
        </table>
</form>
          
        
    <table width="100%">
       <tr style=" background-color: graytext;">
           <td>
            <img src='<?php echo base_url(); ?>uploads/logo/print1.png' alt='print'  onclick="javascript:printDiv('printme')" style='width:30px; height:30px;float:right;padding:2px; margin-right:30px;'  title="Click for print" >  
            <form action="<?php echo site_url('Pdfgen/hodlist/'.$uolt) ?>">
                <input type="submit" value="" style="width:30px; height:30px;float:right;padding:2px; margin-right:10px;background-image:url('<?php echo base_url(); ?>assets/sis/images/pdf.jpeg')" title="Click for pdf">     
            </form>
             <div style="margin-left:600px;"><b>List of HOD</b></div>
        </td>      
      
       <?php /*
       echo "<td align=\"center\" width=\"100%\">";
       echo "<b>List of HOD</b>";
       echo "</td>";
        */
       ?>
                
      
        <div>
        <?php echo validation_errors('<div class="isa_warning">','</div>');?>
         <?php echo form_error('<div class="isa_error">','</div>');?>
          <?php if(isset($_SESSION['success'])){?>
              <div class="isa_success"><?php echo $_SESSION['success'];?></div>
          <?php
          };
          ?>
        <?php if  (isset($_SESSION['err_message'])){?>
             <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
        <?php
        };
        ?>
        </div>

        </tr></table>
        <div id="printme" align="left" style="width:100%;">
        <div class="scroller_sub_page">
            <table class="TFtable" >
                <thead>
                <tr>
                    <th>Department Name</th>
                    <th>HOD Name</th>
                                      
                </tr>
            </thead>
            <tbody>
                <?php 
		$cid = 0;
                $did=0;
                $uoprid = 0;               
                if( count($allsc) ):  ?>
                    <?php foreach($allsc as $record){
			if($record->hl_uopid != $uoprid) {
				$uoprid = $record->hl_uopid;
				echo "<tr>";
	                        echo "<td colspan=2>";
				echo "<b>";
				echo $this->lgnmodel->get_listspfic1('authorities','name','priority',$record->hl_uopid)->name;
                                echo " ( ";
                                echo $this->lgnmodel->get_listspfic1('authorities','code','priority',$record->hl_uopid)->code;
                                echo " ) ";
				echo "</b>";
				echo "</td>";
				echo "</tr>";
			}
			echo "<tr>";
                        echo "<td>";
				echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->hl_deptid)->dept_name;
				echo " ( ";
				echo $this->commodel->get_listspfic1('Department','dept_code','dept_id',$record->hl_deptid)->dept_code;
				echo " ) ";
			echo "</td>";
			echo "<td>";
				if(!empty($record->hl_empcode)){
				$name=$this->sismodel->get_listspfic1('employee_master','emp_name','emp_code',$record->hl_empcode);
				if(!empty($name)){
					echo $name->emp_name;
				}
				}
				//echo $this->sismodel->get_listspfic1('employee_master','emp_name','emp_code',$record->hl_empcode)->emp_name;
				echo " ( ";
				echo $this->lgnmodel->get_listspfic1('edrpuser','username','id',$record->hl_userid)->username;
				echo " ) ";
                        echo "</td>";
                        echo "</tr>";
           /* 
                        if($cid !=$record->hl_scid){    
                            echo "<tr>";
                            echo "<td colspan=2 style=\"text-align:center;\">";
                            echo " <b> Campus Name : ";
                            echo $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$record->hl_scid)->sc_name;
                            echo " ( ".$this->commodel->get_listspfic1('study_center','sc_code','sc_id',$record->hl_scid)->sc_code." )";
                            echo "</b></td>";
                            echo "</tr>";
                            $cid=$record->hl_scid;
                        }
                        $hodlist=$this->sismodel->hoduser($record->hl_scid);
                        foreach($hodlist as $record2){
                        if($did !=$record2->hl_deptid){    
                            echo "<tr>";
                            echo "<td colspan=2 ><b>";
                            echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$record2->hl_deptid)->dept_name;
                            echo " ( ". $this->commodel->get_listspfic1('Department','dept_code','dept_id',$record2->hl_deptid)->dept_code ." )";
                            echo "</b></td>";
                            echo "</tr>";
                        
                            $did=$record2->hl_deptid;
                        } 
                        echo "<tr>";
                        echo "<td > </td>";
                        echo "<td>";
                        $username=$this->lgnmodel->get_listspfic1('edrpuser','username','id',$record2->hl_userid)->username;
                        echo $username;
                        echo "</td>";
                        echo "</tr>";

                        }    
                       */          
                    ?>
                <?php }; ?>
            <?php else : ?>
                    <td colspan= "13" align="center"> No Records found...!</td>
            <?php endif;?>
            </tbody>
        </table>
        </div><!------scroller div------>   
        </div><!------print div------>
        <p> &nbsp; </p>
        <div align="center">  <?php $this->load->view('template/footer');?></div>
    </body>
</html>
