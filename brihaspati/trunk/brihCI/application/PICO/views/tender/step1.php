<!DOCTYPE html>
<html>
<head>
<title>Tender|Input|Form</title>
   <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
  <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
     <?php $this->load->view('template/header');
     
      ?>
     
 


</head>
<body>


     <table width="100%">
            <tr><td>
                <?php // echo anchor('pico', "View Detail ", array('title' => 'Add Detail' ,'class' =>'top_parent'));?>
                <?php
                 echo "<td align=\"right\">";
                 $help_uri = site_url()."/help/helpdoc#Role";
		 echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
		echo "</td>"
                 ?>
                <div  style="width:100%;">
                <?php echo validation_errors('<div class="isa_warning">','');?>
                <?php if(isset($_SESSION['success'])){?>
                <div class="isa_success"><?php echo $_SESSION['success'];?>
                <?php
                };
                ?>
                <?php if(isset($_SESSION['err_message'])){?>
                    <div class="isa_error"><?php echo $_SESSION['err_message'];?>
                <?php
                };
               ?>
              
             </td></tr>
        </table>








<script>
    $(document).ready(function(){
    			$("#offpayrowl").hide();
    			$("#offpayrow").hide();
            $("#onpayrowl").hide();
            $("#onpayrow").hide();
         	 $("#fill").show();
        
                 $('#paytype').on('change',function(){
                        var pt= $('#paytype').val();
                        if(pt == 'offline'){
                               
                                
                                $("#offpayrowl").show();
                                $("#offpayrow").show();
                                $("#onpayrowl").hide();
                                $("#onpayrow").hide();
                                $("#fill").hide(); 
                               
                        }
                        else{
                                $("#onpayrowl").show();
                                $("#onpayrow").show();
                                $("#offpayrowl").hide();
                                $("#offpayrow").hide();
                               $("#fill").hide();
                               
                                
                        }
               });
					
					$('#nocover').on('change',function(){
						var ncov=$('#nocover').val();
			//alert(ncov);

			               if(ncov == ''){
                        	$('#nmecovr').prop('disabled',true);
                    	}else{
                        $('#nmecovr').prop('disabled',false);
                        $.ajax({
                            url: "<?php echo base_url();?>picoindex.php/pjslist/getcoverdetail",
                            type: "POST",
                            data: {"noc" : ncov},
                            dataType:"html",
                            success:function(data){
                                  //alert("data==="+data);
										var sginput=data.split(",");
                             //  alert(sginput[0].replace(/[[\]"|"]/g,""));
                            $('#nmecovr').val(sginput[0].replace(/[[\]"|"]/g,""));
                            $('#typcovr').val(sginput[1].replace(/[[\]"|"]/g,""));
                            },
                            error:function(data){
                            alert("data in error==="+data);
                          
                            	
                            }
                        });
                    }
		});                 
                
                     
    });

</script>







<form action="<?php echo site_url('tender/tenderbasic');?>" method="POST" class="form-inline" enctype="multipart/form-data">


<h2 class="title">Basic Details</h2>
<p class="subtitle">Step 1</p>
  
          <table class="TFtable">
            <tr>
                <td><label for="bd_trn" class="control-label">Tender Reference No:</label></td>
                <td><input type="text" name="bd_trn"  class="form-control" style="width:300px ;" /><br></td>
              
             
         
                <td><label for="bd_tt" class="control-label">Tender type:</label></td>
                <td>
                <select name="bd_tt"  style="width:317px ;">
				    <option selected hidden value="">--option--</option>
				    <option value="open">		  Open  		</option>
				    <option value="limited">		Limited 		</option>
				    <option value="EOI">		  EOI   		</option>
				    <option value="auction">		Auction 		</option>
				    <option value="single">		 Single 		</option>
				    </select>
                
                
                
                
                
                <br></td>
             
           </tr>
               
                <td><label for="bd_foc" class="control-label">Form of contract:</label></td>
                <td>
                    <select name="bd_foc"  style="width:317px ;" >
				    <option selected hidden value="">--option--</option>
				    <option value="work contract">Work contract</option>
				    <option value="auction">Auction</option>
				    <option value="service contract">Service contract</option>
				    <option value="buy">Buy</option>
				    <option value="empanelment">Empanelment</option>
				    <option value="sell">Sell</option>
				    <option value="buy & service">Buy & Service</option>
				    </select>
                
                
                <br></td>
              
             
           
         
                <td><label for="bd_noc" class="control-label">No. of Covers:</label></td>
                <td>   
                 <select name="bd_noc" id="nocover" style="width:317px ;">
				    <option selected hidden value="">--option--</option>
				    <option value="1">1</option>
				    <option value="2">2</option>
				    <option value="3">3</option>
				    <option value="4">4</option>
				    </select>
                <br></td>
              
         </tr> 
        
                <td><label for="bd_tc" class="control-label">Tender category:</label></td>
                <td>    
                <select name="bd_tc"  style="width:317px ;">
				    <option selected hidden value="">--option--</option>
				    <option value="goods">Goods</option>
				    <option value="works">Works</option>
				    <option value="services">Services</option>
				
				    </select>
				    
                
                
                <br></td>
               
   
             
             
               <td><label for="bd_ars" class="control-label">Allow Re-submission :</label></td>
                <td>
                		<input type="radio" name="bd_ars" value="yes" >YES
                     <input type="radio" name="bd_ars" value="no" >NO
                
                <br></td>
               
               
              
         
         </tr>
         <tr>
                <td><label for="bd_aw" class="control-label">Allow Withdrawal :</label></td>
                <td><input type="radio" name="bd_aw" value="yes" >YES
                     <input type="radio" name="bd_aw" value="no" >NO
                     <br></td>
             
        
                <td><label for="bd_aos" class="control-label">Allow Offline Submission :</label></td>
                <td> <input type="radio" name="bd_aos" value="yes" >YES
                     <input type="radio" name="bd_aos" value="no" >NO
                     <br></td>
               
            </tr>
         
            <tr>   
            <td><label  id="paytypel" for="bd_pm" class="control-label">Payment Mode:</label></td>
                <td >   
                 <select id="paytype"  name="bd_pm"  style="width:317px ;" class="my_dropdown" >
				    <option selected disabled value="">--option--</option>
				    <option value="offline">offline </option>
				    <option value="online">online </option>
				  
				    </select>
                <br></td>
             
            
               <td colspan="2" id="fill"></td>
       
                <td id="offpayrowl">
               
                
                <label id="subpurchtype" for="bd_offline" class="control-label">Offline:</label></td>
                <td id="offpayrow">   
                 <select id="subpurchtype"  name="bd_offline"  style="width:317px;" class="my_dropdown"  >
				    <option selected disabled value="">--option--</option>
			        <option value="SS-small saving instrument">SS-small savings instrument</option>
				    <option value="BG-bank guarantee">BG-bank guarantee</option>
				    <option value="BC-bankers cheque">BC-bankers cheque</option>
				    <option value="DD-demand draft">DD-demand draft</option>
				   </select>
				    
                <br>
             
                
                </td>
         
                <td id="onpayrowl"><label for="bd_online" class="control-label">Online:</label></td>
                <td id="onpayrow">   
                 <select name="bd_online"  style="width:317px ;">
				    <option selected hidden value="">--option--</option>
				    <option value="ICICI">ICICI</option>
				    <option value="UTI">UTI</option>
				    <option value="SBI">SBI</option>
				    <option value="PNB">PNB</option>
				    </select>
                <br></td>
               
           
           
           </tr>
           </table>
           
          <table class="TFtable">

	<thead>
 		 		<tr>
 		 			<th>No of Covers</th>
 		 			<th>Cover Type</th>
 		 			<th>Cover contents<th>

 		 		</th>
 		 		</tr>	
 		 	</thead>
 		 	<tbody>
 		 		<tr>
 		 			<td >
 		 				<input type="text" id="nmecovr" name="nmecovr" placeholder="No of Cover" value="" size="15" readonly=""> 
 		 			<!--	<select name="" style="width:150px" ;>
 		 				<option name="select" value="disabled" selected="selected" disabled selected>----Select----</option>
 		 				<option value="1">single cover</option>
 		 				<option value="2">two cover</option>
 		 				<option value="3">three cover</option>	
 		 			   <option value="4">four cover</option>	
 		 				</select>
-->
 		 			</td>
 		 			<td>
 		 			<input type="textarea" id="typcovr"  name="typcovr" placeholder="Cover Type" value="" size="45" readonly="">
 		 			</td>
                  	
 		 			<td colspan="2" >
 		 				<input type="text" name="bd_covcon" placeholder="Give Description..." size="25"> 
 		 			</td>
 		 			
 		 		</tr>
 		 		<br>
 		 		<br>
 		 		<br>
 		 			

  	</tbody>

</table>           




          <table class="TFtable"><td>
                    <button name="bd">Next</button>
                    <input type="hidden" name="flag"  value="<?php  
                                                                    $a=1;
                                                                  
                                                                   echo $a++ ;  ?> " />
                    <button name="reset">Clear</button>
                   
 			 <td>    
 			 </table>    
</form>           

</div>
</div>
</body>
<br>
<br>
<p>&nbsp;</p>
  <div align="center"> <?php $this->load->view('template/footer');?>
</html>
