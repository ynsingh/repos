<!--
    @author Manorama Pal(palseema30@gmail.com) 
    @author Akash Rathi(akash92y@gmail.com) html part  

-->
<html>
    <head>
       
    </head>
<body>
<?php $this->load->view('template/pheader'); ?>
<!--<img src="uploads/logo/logotanuvas.jpeg" alt="logo" style= " width:100%;height:80px; margin-bottom:15px; " > <br/>-->
      
<div class="scroller_sub_page">
        <table class="TFtable" >
            <thead>
                <tr>
                    <th>Sr.No</th>
                   <!-- <th></th> -->
                    <th>Employee Name</th>
                <!--    <th>Designation</th>
                    <th>University Officer Control</th>
                    <th>Department Name</th>
                    <th>Specialisation(Major Subject)</th> -->
                    <th>Designation</th>
                    <th>Scheme Name </th>
                    <!--<th>Employee Post</th>-->
                   <!-- <th>Pay Band</th>-->
                 <!--   <th>E-Mail ID</th>
                    <th>Contact No</th>
                    <th>Aadhaar No</th>
                    <th>Action</th>
                    -->
                </tr>
               
                
            </thead>
            <?php // print_r($records); ?>;
          <tbody>
                <?php $serial_no = 1;
		$ouoid = 0;
		$odid = 0;
		
               if( count($records) ):  ?>
                    <?php foreach($records as $record){
//                        print_r($record);
			if($ouoid !=$record->emp_uocid){
			echo "<tr>";
			echo "<td colspan=4 style=\"text-align:center;\">";
			echo " <b> UO CONTROL : ";
			echo $this->lgnmodel->get_listspfic1('authorities','name','id' ,$record->emp_uocid)->name;
			echo "</b></td>";
			echo "</tr>";
			$ouoid=$record->emp_uocid;
			}
			if($odid !=$record->emp_dept_code){
                        echo "<tr><td colspan=4 align=left><b> Department : ";
			echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->emp_dept_code)->dept_name;
			echo " ( ". $this->commodel->get_listspfic1('Department','dept_code','dept_id',$record->emp_dept_code)->dept_code ." )";
                        echo "</b></td></tr>";
			$odid = $record->emp_dept_code;
			$serial_no = 1;
                        }
			echo "<tr>";
			echo "<td>". $serial_no++ ." </td>";
			echo "<td>";
			echo anchor("report/viewfull_profile/{$record->emp_id}",$record->emp_name." ( "."PF No:".$record->emp_code." )" ,array('title' => 'View Employee Profile' , 'class' => 'red-link'));

		//	$record->emp_name.
			echo "</td>";
			echo "<td>"; 
			echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$record->emp_desig_code)->desig_name;
			 if ($record->emp_head == "HEAD"){
                                        echo " & ";
                                        echo $record->emp_head;
                                }
	
			echo "</td>";
			echo "<td>";
			echo $this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$record->emp_schemeid)->sd_name ;	
			echo " ( ".$this->sismodel->get_listspfic1('scheme_department','sd_code','sd_id',$record->emp_schemeid)->sd_code ." )";	
			echo "</td>";
?>
                        </tr>
                    <?php }; ?>
                <?php else : ?>
                    <td colspan= "13" align="center"> No Records found...!</td>
                <?php endif;?>
                </tbody>
        </table>

        </div><!------scroller div------>
<!--        <img src="uploads/logo/logo23.png" alt="logo" style= " width:100%;height:30px; margin-top:30px; " > <br/>-->
<?php //$this->load->view('template/footer'); ?>
      </body> 
      </html>
