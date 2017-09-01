<!--@filename feesreciptdw.pdf  @author kishore kr shukla(kishore.shukla@gmail.com) -->

<html>
    <head>
        <title>Welcome </title>
    </head>
    <body>
	<div style="border:2px solid black;">
	<img src="uploads/logo/logo2.jpg" alt="logo" style="width:100%;height:70px;">
	<table style="width:100%;">
<tr><th>
	<center> Fees Receipt</center>
</th></tr></table>
    <!--     <div style="">-->

	<table border=1 style="width:100%;">
	   <tr> <?php //echo $this->sfeeid;?>
               <th style="text-align:justify;background-color:#dbdbdb;color:black;font-size:18px;">Student details</th>
            </tr>
	</table>	
        <table style="width:100%;">
           <tr>
            <td style="width:33%;">Name: <?php echo " ". $this->sname; ?></td>
            <td style="width:33%;">Roll No: <?php echo " ".$this->srollno; ?> </td>
           <td style="width:33%;">Academic Year: <?php echo " ".$this->acadyear;?></td>
           </tr>
	  <tr>
           <td>Program:<?php echo " ".$this->prgname;?> </td>
           <td>Branch & Semester:<?php echo " ". $this->branch ." & ". $this->semester;?></td>
           <td>Category: <?php echo " ".$this->scategoryn ;?> </td>
         </tr>
           <tr> 
           <td>Fees Type: <?php echo " ". $this->ftype;?></td>
           <td>Payment Mode: <?php echo " ".$this->fstatus;?></td>
           <td>Reference No: <?php echo " ".$this->refno;?></td>
          </tr>
           <tr>
           <td>Gender: <?php echo " ".$this->sgender ;?> </td>
           <td>Email:<?php echo " ".$this->semail;?> </td>
           <td>Mob No:<?php echo " ".$this->smobile;?> </td>
          </tr>
	</table>
	
	<table border=1 style="width:100%;margin-top:10px;">
	   <tr>
               <th style="text-align:justify;background-color:#dbdbdb;color:black;font-size:18px;">Fees details</th>
            </tr>

	</table>
          <table  border=1 style="width:100%;">
        <thead style="background-color:#dbdbdb;color:black;font-size:15px;">
                <tr>
                <td><b>Description<b></td>
                <td><b>Amount</b></td>

                </tr>
        </thead>
	
        <tbody>
        <?php
                $totalfees = '';
                //$this->progresult = $this->Common_model->get_list('fees_master');
                foreach($this->feesresult as $d2){
                 ?>
                <tr>
                <td><?php echo $d2->fm_head;?></td>

                <td><?php echo $d2->fm_amount;?></td>
                <?php $totalfees = $totalfees+$d2->fm_amount;?>
                </tr>

        <?php } ?>
		<tr><td></td><td>Total: <?php echo $totalfees;?></td></tr>
			
		<tr><td></td><td>Paid Fees: <?php echo $this->feeamount;?></td></tr>
		<?php $due=$totalfees-$this->feeamount;
		if($due == 0){
			echo "<tr><td></td><td>Balence Amount:". $due ."</td></tr>";
		}
		if($due > 0){
			echo "<tr><td></td><td>Dues Amount:". $due ."</td></tr>";
		}
		if($due < 0){
			echo "<tr><td></td><td>Excess Amount:". $due ."</td></tr>";
		}
		
		?>

        </tbody>

</table>

<table style="margin-top:20px;">
	<tr>
		<td>Date of deposit : <?php echo $this->fdepositedate;?></td>
	</tr>
</table>
  </div>
<table>
	<tr>
		<td style="color:"><i>This is computer generated receipt and no signature required.</i></td>
	</tr>
</table>
    </body>  
</html>    
