<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?>
<html lang="en" class="loading">
    <head>
        <meta charset="utf-8">
        <link rel="icon" href="img/favicon.png">
        <title>Payumoney </title>   
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
         
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-2"></div>  
                <div class="col-md-8">
                	<span="text-center">* All feilds are required</span>
                    <form method="post" id="booking_car_detailes" enctype="multipart/form-data" action="<?= base_url()?>Payumoney/check">                                                                  
                        <div class="form-group">                      
                          <input type="text"  name="payble_amount" id="payble_amount" class="form-control" placeholder="Enter Payble Amount"/>
                        </div>
                        <div class="form-group">
                            <input type="text" name="product_info" id="product_info" class="form-control"  Placehosder="Product info" />
                        </div>
                       <div class="form-group">                      
                          <input type="text"  name="customer_name" id="customer_name" class="form-control" placeholder="Full Name (Only alphabets)"/>
                        </div>
                        <div class="form-group">                                   
                          <input type="number"  name="mobile_number" id="mobile_number" class="form-control" placeholder="Mobile Number(10 digits)"/>
                        </div>
                        <div class="form-group">                                   
                          <input type="email"  name="customer_email" id="customer_email" class="form-control" placeholder="Email" />
                        </div>
                        <div class="form-group">
                          <textarea class="form-control" name="customer_address" id="customer_address" placeholder="Address"></textarea>
                        </div>
                        <div class="form-group text-right">
                          <button type="submit" class="btn btn-primary">Submit</button>
                          <button class="btn btn-default"  data-dismiss="modal" >Cancel</button>
                        </div>
                    </form>                 
                </div>
                <div class="col-md-2"></div>
            </div>
        </div>    
    </body>
</html>    
