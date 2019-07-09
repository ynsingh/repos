<!--
    @author Manorama Pal(palseema30@gmail.com) pdf report
    @author Akash Rathi(akash92y@gmail.com) html part  

-->

<html>
      
    <body>
     
        <img src="uploads/logo/logotanuvas.jpeg" alt="logo" style= " width:100%;height:80px; margin-bottom:15px; " > <br/>
 
        <div class="scroller_sub_page">
            <table class="TFtable"> 
                <thead>
                    <tr>
                    <th> Sr.No </th>
                    <th> Name </th>
                    <th> Designation </th>
                    <th> Department Name </th>
                </thead>
                
                <tbody>
                <?php   $count = 0;
                        $sbid=0;
                        if(count($this->result)) {
                            foreach ($this->result as $row) {
                                if($sbid!=$row->emp_specialisationid){
                            ?>
                                    <tr>
                                    <?php echo "<td colspan=4><b>Major subject :";
                                    echo $this->commodel->get_listspfic1('subject','sub_name','sub_id' ,$row->emp_specialisationid)->sub_name; 
                                    echo "</td>";
                                    echo "</tr>";
                                    $sbid = $row->emp_specialisationid; 
                                    $count = 0;
                                } ?>
                                <tr>
                                <td><?php echo ++$count; ?> </td>
                                <td><?php 
				echo anchor("report/viewfull_profile/{$row->emp_id}",$row->emp_name." ( "."PF No:".$row->emp_code." )" ,array('title' => 'View Employee Profile' , 'class' => 'red-link'));
			
				?> </td>
                                <td><?php echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$row->emp_desig_code)->desig_name; 
				if ($row->emp_head == "HEAD"){
					echo " & ";
					echo $row->emp_head;
				}
                                ?> </td>
                                <td><?php echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$row->emp_dept_code)->dept_name; ?> 
                                ( <?php echo $this->commodel->get_listspfic1('Department','dept_code','dept_id',$row->emp_dept_code)->dept_code; ?> ) </td>
                                </tr>
                            <?php  }; 
                        }else{
                        ?>  
                        <tr><td colspan= "13" align="center"> No Records found...!</td></tr>
                        <?php } ?> 
                    </tbody> 
                </table>
            </div><!------scroller div------> 

            <img src="uploads/logo/logo23.png" alt="logo" style= " width:100%;height:30px; margin-top:30px; " > <br/>
        </body>

    </html> 
      