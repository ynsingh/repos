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
<?php 
	if($this->ftype == "semfee"){
		$fetype="Semester Fees";
	}
	if($this->ftype == "exmfee"){
		$fetype="Exam Fee";
	}
	if($this->ftype == "finefee"){
		$fetype="Penalty/Fine Fee";
	}
?>
           <tr> 
           <td>Fees Type: <?php echo " ". $fetype;?></td>
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
	if($this->ftype == "semfee"){
                $totalfees = '';
                //$this->progresult = $this->Common_model->get_list('fees_master');
                foreach($this->feesresult as $d2){
                	echo "<tr>";
                	echo "<td>". $d2->fm_head."</td>";
                	echo "<td>". $d2->fm_amount."</td>";
                	$totalfees = $totalfees+$d2->fm_amount;
                	echo "</tr>";

         	} 
		echo "<tr><td align=right>Total</td><td>".$totalfees."</td></tr>";	
	}
		echo "<tr><td align=right>Paid Fees</td><td>". $this->feeamount;"</td></tr>";
	if($this->ftype == "semfee"){
		$due=$totalfees-$this->feeamount;
		if($due == 0){
			echo "<tr><td align=right> Balance Amount</td><td>". $due ."</td></tr>";
		}
		if($due > 0){
			echo "<tr><td align=right> Dues Amount</td><td>". $due ."</td></tr>";
		}
		if($due < 0){
			echo "<tr><td align=right>Excess Amount </td><td>". $due ."</td></tr>";
		}
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
