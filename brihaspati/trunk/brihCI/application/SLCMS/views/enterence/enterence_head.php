
<!--<table id="head" border=0>
		<tr>
			<td align=left style="width:5%;">
			<a href="<?php echo site_url('');?>" style="">Home</a></td>
			<td align=left style="width:10%;">
			<a href="<?php echo site_url('Enterence/important_date');?>">Important Date</a></td>
			<td align=left style="width:12%;"><a href="<?php echo site_url('Enterence/important_information'); ?>">Important Information</a></td>
			<td align=left style="width:10%;"><a href="<?php echo site_url('Enterence/email_address');?>">Mailing Address</a></td>
			<td align=left style="width:7%;"><a href="<?php echo site_url('Enterence/contactus');?>">Contact Us</a></td>
			<td align=left style="width:4%;"><a href="<?php echo site_url('Enterence/faqs');?>">FAQ</a></td>
			<td align=left style="width:13%;"><a href="<?php echo site_url('Enterence/prtadmission_form');?>">Print Admission Application</a></td>
		</tr>
	</table>-->
<html>
<head>
<style>
ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    /*overflow: hidden;*/
}

#menu li {
    float: left;
    border-left: 0px solid white;
    height:40px;
}

#menu li a {
    display: block;
    color: white;
    text-align: center;
    padding: 8px 20px;
    font-family: "Times New Roman", Times, serif;	
    font-size:18px;	
    text-decoration: none;
}

#menu li a:hover {
   /* background-color: #111;*/
}
</style>
</head>
<body>
<table style="width:70%;border:0px solid black;" align=center>
<tr><td  colspan=3>
<ul id="menu" style="background-color:#067eb7;height:40px;">
  <li><a class="active" href="<?php echo site_url('');?>">Home</a></li>
  <li><a href="<?php echo site_url('Enterence/important_date');?>">Important Date</a></li>
  <li><a href="<?php echo site_url('Enterence/important_information'); ?>">Important Information</a></li>
  <li><a href="<?php echo site_url('Enterence/email_address');?>">Mailing Address</a></li>
  <li><a href="<?php echo site_url('Enterence/contactus');?>">Contact Us</a></li>
  <li><a href="<?php echo site_url('Enterence/faqs');?>">FAQ</a></li>
  <li><a href="<?php echo site_url('Enterence/prtadmission_form');?>">Print Admission Application</a></li>
  <li><a href="<?php echo site_url('Enterence/stu_hallticket');?>">Download Hall Ticket</a></li>		
</ul>
</td></tr>

</table>
</body>
</html>

