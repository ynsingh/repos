<!DOCTYPE html>
<html>
<head>
<title>Tender input form</title>
<!-- Including CSS File Here --
<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/abhay.css">
<!-- Including JS File Here --
 <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-abhay.js" ></script> -->
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
     $('#purchtype').on('change',function(){
                var worktype = $(this).val();
                //alert(worktype);
                if(worktype == ''){
                    $('#subpurchtype').prop('disabled',true);
                   
                }
                else
                
                
                { 
                    $('#subpurchtype').prop('disabled',false);
                    $.ajax({

                        url: "<?php echo base_url();?>picoindex.php/tender/getpaymentmode",
                        type: "POST",
                        data: {"purchase" : worktype},
                        dataType:"html",
                        success:function(data)  
                        
                        { //alert("data==1="+data);
                            $('#subpurchtype').html(data.replace(/^"|"$/g, ''));
                                          }
                                    
                                                 ,
                        error:function(data){
                            alert("data in error==="+data);
                            alert("error occur..!!");
                 
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
                <td><input type="text" name="bd_trn"  class="form-control" style="width:300 ;" /><br></td>
                <td>
                    <?php // echo form_error('bd_trn')?>
                </td>
             
         
                <td><label for="bd_tt" class="control-label">Tender type:</label></td>
                <td>
                <select name="bd_tt"  style="width:300 ;">
				    <option selected hidden value="">--option--</option>
				    <option value="open">		  Open  		</option>
				    <option value="limited">		Limited 		</option>
				    <option value="EOI">		  EOI   		</option>
				    <option value="auction">		Auction 		</option>
				    <option value="single">		 Single 		</option>
				    </select>
                
                
                
                
                
                <br></td>
                <td>
                    <?php //echo form_error('bd_tt')?>
                </td>
               
           </tr>
               
                <td><label for="bd_foc" class="control-label">Form of contract:</label></td>
                <td>
                    <select name="bd_foc"  style="width:300 ;" >
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
                <td>
                    <?php //echo //form_error('bd_foc')?>
                </td>
             
           
         
                <td><label for="bd_noc" class="control-label">No. of Covers:</label></td>
                <td>   
                 <select name="bd_noc"  style="width:300 ;">
				    <option selected hidden value="">--option--</option>
				    <option value="1">1</option>
				    <option value="2">2</option>
				    <option value="3">3</option>
				    <option value="4">4</option>
				    </select>
                <br></td>
                <td>
                    <?php //echo //form_error('bd_noc')?>
                </td>
         </tr> 
        
                <td><label for="bd_tc" class="control-label">Tender category:</label></td>
                <td>    
                <select name="bd_tc"  style="width:300 ;">
				    <option selected hidden value="">--option--</option>
				    <option value="goods">Goods</option>
				    <option value="works">Works</option>
				    <option value="services">Services</option>
				
				    </select>
				    
                
                
                <br></td>
                <td>
                    <?php //echo //form_error('bd_tc')?>
                </td>
   
             
             
               <td><label for="bd_ars" class="control-label">Allow Re-submission :</label></td>
                <td>
                		<input type="radio" name="bd_ars" value="yes" >YES
                     <input type="radio" name="bd_ars" value="no" >NO
                
                <br></td>
               
               
                <td>
                    <?php //echo //form_error('bd_ars')?>
                </td>
         
         </tr>
         <tr>
                <td><label for="bd_aw" class="control-label">Allow Withdrawal :</label></td>
                <td><input type="radio" name="bd_aw" value="yes" >YES
                     <input type="radio" name="bd_aw" value="no" >NO
                     <br></td>
                <td>
                    <?php //echo //form_error('bd_aw')?>
                </td>
        
                <td><label for="bd_aos" class="control-label">Allow Offline Submission :</label></td>
                <td> <input type="radio" name="bd_aos" value="yes" >YES
                     <input type="radio" name="bd_aos" value="no" >NO
                     <br></td>
                <td>
                    <?php //echo //form_error('bd_aos')?>
                </td>
            </tr>
         
            <tr>   
            <td><label  id="purchtype" for="bd_pm" class="control-label">Payment Mode:</label></td>
                <td>   
                 <select id="purchtype"  name="bd_pm"  style="width:300 ;" class="my_dropdown" >
				    <option selected disabled value="">--option--</option>
				    <option value="offline">offline </option>
				    <option value="online">online </option>
				  
				    </select>
                <br></td>
                <td>
                    <?php //echo form_error('bd_pm')?>
                </td>
            
           </tr>  
           
           <tr>  
       
                <td>
               
                
                <label id="subpurchtype" for="bd_offline" class="control-label">if Offline:</label></td>
                <td>   
                 <select id="subpurchtype"  name="bd_offline"  style="width:300;" class="my_dropdown"  >
				    <option selected disabled value="">--option--</option>
			        <option value="SS-small saving instrument">SS-small savings instrument</option>
				    <option value="BG-bank guarantee">BG-bank guarantee</option>
				    <option value="BC-bankers cheque">BC-bankers cheque</option>
				    <option value="DD-demand draft">DD-demand draft</option>
				   </select>
				    
                <br>
             
                
                </td>
         
                <td>
                    <?php //echo //form_error('bd_offline')?>
                </td>
           </tr>
           <tr>
                <td><label for="bd_online" class="control-label">if Online:</label></td>
                <td>   
                 <select name="bd_online"  style="width:600 ;">
				    <option selected hidden value="">--option--</option>
				    <option value="ICICI">ICICI</option>
				    <option value="UTI">UTI</option>
				    <option value="SBI">SBI</option>
				    <option value="PNB">PNB</option>
				    </select>
                <br></td>
                <td>
                    <?php //echo //form_error('bd_online')?>
                </td>
           
           
           </tr>
           </table>
           
          <table class="TFtable">

	<thead>
 		 		<tr>
 		 			<th>No. of cover</th>
 		 			<th>Cover contents<th>

 		 		</th>
 		 		</tr>	
 		 	</thead>
 		 	<tbody>
 		 		<tr>
 		 			<td >
 		 				<select name="">
 		 				<option name="select" value="disabled" selected="selected" disabled selected>----Select----</option>
 		 				<option value="">single cover</option>
 		 				<option value="">two cover</option>
 		 				<option value="">three cover</option>	
 		 			   <option value="">four cover</option>	
 		 				</select>

 		 			</td>
                  	
 		 			<td>
 		 				<input type="text" name="bd_covcon" placeholder="Give Description..." size="95" style="float:right;"> 
 		 			</td>
 		 			
 		 		</tr>
 		 		<br>
 		 		<br>
 		 		<br>
 		 			

 <!-- <tr> 
     <td>  
    <input type="file" name="file_a" id="file_a" > 
     </td>
     </tr>
 		--> 	</tbody>

</table>           




          <table class="TFtable"><td>
                    <button name="bd">Next</button>
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
