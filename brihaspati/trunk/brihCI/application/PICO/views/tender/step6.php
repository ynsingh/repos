<!DOCTYPE html>
<html>
<head>
<title>Tender-DOC|BID</title>

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



<form action="<?php echo site_url('tender/tenderbid');?>" method="POST" class="form-inline">


<h2 class="title">Bid & Docs</h2>
<p class="subtitle">Step 6</p>
  
           <table class="TFtable">
           <caption style="color:black;  "><big><big><big><b>Bid Openers Selection</b></big></big></big></caption>
 				<br><br><br>  
           <thead><th>Sr.No</th><th><center>Name</center></th><th><center>Designation</center></th><th><center>Email ID</center></th></tr></thead>
	            
            
            
            <tr>
            <td><input name="b1" value="BO1" readonly   size="1" /> </td>
            <td><input type="text" name="n1"  class="form-control" size="25" /><br></td>
            <td><input type="text" name="d1"  class="form-control" size="30" /><br></td>
            <td><input type="text" name="e1"  class="form-control" size="30" /><br></td>
            </tr>
            <tr>
            <td><input name="b2" value="BO2" readonly   size="1" /> </td>
            <td><input type="text" name="n2"  class="form-control" size="25" /><br></td>
            <td><input type="text" name="d2"  class="form-control" size="30" /><br></td>
            <td><input type="text" name="e2"  class="form-control" size="30" /><br></td>
            </tr>
            <tr>
            <td><input name="b3" value="BO3" readonly   size="1" /> </td>
            <td><input type="text" name="n3"  class="form-control" size="25" /><br></td>
            <td><input type="text" name="d3"  class="form-control" size="30" /><br></td>
            <td><input type="text" name="e3"  class="form-control" size="30" /><br></td>
            </tr>
            <tr>
            <td><input name="b4" value="BO4" readonly   size="1" /> </td>
            <td><input type="text" name="n4"  class="form-control" size="25" /><br></td>
            <td><input type="text" name="d4"  class="form-control" size="30" /><br></td>
            <td><input type="text" name="e4"  class="form-control" size="30" /><br></td>
            </tr>
            <tr>
            <td><input name="b5" value="BO5" readonly   size="1" /> </td>
            <td><input type="text" name="n5"  class="form-control" size="25" /><br></td>
            <td><input type="text" name="d5"  class="form-control" size="30" /><br></td>
            <td><input type="text" name="e5"  class="form-control" size="30" /><br></td>
            </tr>   
               
          
         </table>
         <br><br><br>
         <table class="TFtable">
         
           <caption style="color:black;  "><big><big><big><b>Uploading Tender Documents</b></big></big></big></caption>
        
         <thead><th>Sr.No</th><th><center>File Name</center></th><th><center>Description</center></th><th><center>Type</center></th><th><center>Size(Kilo-bytes)</center></th></tr></thead>
	            
            
            
            <tr>
            <td><input name="s1" value="1" readonly   size="1" /> </td>
            <td><input type="text" name="f1"  class="form-control" size="20" /><br></td>
            <td><input type="text" name="de1"  class="form-control" size="40" /><br></td>
            <td> <select name="ty1"  style="width:100px ;">
				    <option selected hidden value="">--option--</option>
				    <option value="pdf">PDF</option>
				    <option value="jpg">JPG</option>
				    <option value="png">PNG</option>
				    <option value="doc">DOC</option>
				    <option value="txt">TEXT</option>
				    <option value="rar">RAR</option>
				    <option value="zip">ZIP</option>
				    </select>
            
            <br></td>
            <td><input type="text" name="si1"  class="form-control" size="10" /><br></td>
            </tr>
            <tr>
            <td><input name="s2" value="2" readonly   size="1" /> </td>
            <td><input type="text" name="f2"  class="form-control" size="20" /><br></td>
            <td><input type="text" name="de2"  class="form-control" size="40" /><br></td>
            <td> <select name="ty2"  style="width:100px ;">
				    <option selected hidden value="">--option--</option>
				    <option value="pdf">		  PDF  		</option>
				    <option value="jpg">		JPG 		</option>
				    <option value="png">		  PNG   		</option>
				    <option value="doc">		DOC 		</option>
				    <option value="txt">		 TEXT 		</option>
				     <option value="rar">RAR</option>
				    <option value="zip">ZIP</option>
				    </select>
            
            <br></td>
            <td><input type="text" name="si2"  class="form-control" size="10" /><br></td>
            </tr>
            <tr>
            <td><input name="s3" value="3" readonly   size="1" /> </td>
            <td><input type="text" name="f3"  class="form-control" size="20" /><br></td>
            <td><input type="text" name="de3"  class="form-control" size="40" /><br></td>
            <td> <select name="ty3"  style="width:100px ;">
				    <option selected hidden value="">--option--</option>
				    <option value="pdf">		  PDF  		</option>
				    <option value="jpg">		JPG 		</option>
				    <option value="png">		  PNG   		</option>
				    <option value="doc">		DOC 		</option>
				    <option value="txt">		 TEXT 		</option>
				     <option value="rar">RAR</option>
				    <option value="zip">ZIP</option>
				    </select>
            
            <br></td>
            <td><input type="text" name="si3"  class="form-control" size="10" /><br></td>
            </tr>
            <tr>
            <td><input name="s4" value="4" readonly   size="1" /> </td>
            <td><input type="text" name="f4"  class="form-control" size="20" /><br></td>
            <td><input type="text" name="de4"  class="form-control" size="40" /><br></td>
            <td> <select name="ty4"  style="width:100px ;">
				    <option selected hidden value="">--option--</option>
				    <option value="pdf">		  PDF  		</option>
				    <option value="jpg">		JPG 		</option>
				    <option value="png">		  PNG   		</option>
				    <option value="doc">		DOC 		</option>
				    <option value="txt">		 TEXT 		</option>
				     <option value="rar">RAR</option>
				    <option value="zip">ZIP</option>
				    </select>
            
            <br></td>
            <td><input type="text" name="si4"  class="form-control" size="10" /><br></td>
            </tr>
            <tr>
            <td><input name="s5" value="5" readonly   size="1" /> </td>
            <td><input type="text" name="f5"  class="form-control" size="20" /><br></td>
            <td><input type="text" name="de5"  class="form-control" size="40" /><br></td>
            <td>
            <select name="ty5"  style="width:100px ;">
				    <option selected hidden value="">--option--</option>
				    <option value="pdf">		  PDF  		</option>
				    <option value="jpg">		JPG 		</option>
				    <option value="png">		  PNG   		</option>
				    <option value="doc">		DOC 		</option>
				    <option value="txt">		 TEXT 		</option>
				     <option value="rar">RAR</option>
				    <option value="zip">ZIP</option>
				    </select>
            
            <br></td>
            <td><input type="text" name="si5"  class="form-control" size="10" /><br></td>
            </tr>   
         
         
         </table>  
         
         
         <br>
         <br>
         <br>
         
         
         <table class="TFtable" >
       
        <tr>
           <td><label for="tp" class="control-label">Tender Prepared By.:</label></td>
           <td><input type="text" name="tp" value="<?php echo $this->session->userdata('username')  ?>" class="form-control" size="20" readonly /><br></td>
           <td><label for="tpd" class="control-label">Designation.:</label></td>
           <td><input type="text" name="tpd" value="<?php echo 'admin' ?>" class="form-control" size="20" readonly  /><br></td>
        </tr>
     
       
         
   <tr>
 
     <td colspan="5">
         <input  type="submit" value="Submit Details" name="submit">
         <input  type="submit" value="Reset" name="reset">
         <input type="hidden" name="tid" value="<?php  echo $tid ;?>" >
     </td>
   </tr>         
         
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
