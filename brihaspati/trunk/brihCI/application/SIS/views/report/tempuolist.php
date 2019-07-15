
<html>
<body>
<?php $this->load->view('template/pheader'); ?>
    
<!--    <img src="uploads/logo/logotanuvas.jpeg" alt="logo" style= " width:100%;height:80px; margin-bottom:15px; " > <br/>-->
<div class="scroller_sub_page">
            <table class="TFtable" >
                <thead>
                <tr>
                    <th align="left">Sr. No.</th>
                    <th align="left">University Officer</th>
                    <th align="left">UO Name</th>
                                      
                </tr>
            </thead>
            <tbody>
                <?php 
		$cid = 0;
                $did=0;
                $uoid=0;   
		$i=1;
                if( count($allsc) ):  ?>
                    <?php foreach($allsc as $record){
				$usrnme=$this->lgnmodel->get_listspfic1('edrpuser','username','id',$record->ul_userid)->username;
				$uos = array("asection@tanuvas.org.in", "rsection@tanuvas.org.in");
				if (!(in_array($usrnme, $uos))) {
					if ($uoid != ($record->ul_authuoid)){
						echo "<tr>";
						echo "<td>";
						echo $i;
						echo "</td>";
                        			echo "<td>";
						if (!empty($record->ul_authuoid)){
                			               echo $this->lgnmodel->get_listspfic1('authorities', 'name', 'id', $record->ul_authuoid)->name; 
						       echo " ( ";
                        			       echo $this->lgnmodel->get_listspfic1('authorities','code','id',$record->ul_authuoid)->code;
			                               echo " ) ";
                        			       echo " ( ";
		                                       echo $this->lgnmodel->get_listspfic1('authorities','priority','id',$record->ul_authuoid)->priority;
                		                       echo " ) ";
						}
						echo "</td>";
						echo "<td>";
				
						if (!empty($record->ul_empcode)){
							echo $this->sismodel->get_listspfic1('employee_master','emp_name','emp_code',$record->ul_empcode)->emp_name;
						}
						if (!empty($record->ul_userid)){ 
							echo " ( ";
							echo $this->lgnmodel->get_listspfic1('edrpuser','username','id',$record->ul_userid)->username;
							echo " ) ";
						}
                			        echo "</td>";
						$i++;
                        			echo "</tr>";
						$uoid = $record->ul_authuoid;
					}
				}
                    ?>
                <?php }; ?>
            <?php else : ?>
                    <td colspan= "13" align="center"> No Records found...!</td>
            <?php endif;?>
            </tbody>
        </table>
        </div><!------scroller div------>   
<!--       <img src="uploads/logo/logo23.png" alt="logo" style= " width:100%;height:30px; margin-top:30px; " > <br/>-->
<?php $this->load->view('template/footer'); ?>
</body>
</html>
