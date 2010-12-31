<!DOCTYPE html>
<html>
<head>
  <style>
  p { width:400px; }
  </style>
  <script src="http://code.jquery.com/jquery-1.4.4.js"></script>
</head>
<br>

<div>
  <button id="button1" style="background:none; border:none;" title="Click to view"><b>Aggregate Clickstream Analysis</b></button>
   <p id="para1">
     <img src="images/click_stram_aggregate.png" width="700px"/>
  </p>
</div>  
<br>

<div>
 <button id="button2" style="background:none; border:none;" title="Click to view"><b>Individual Session Tracks</b></button>
 <p id="para2">
 <img src="images/click_stream_individual.png" width="700px"/>
   
  </p>
</div> <br>


<script>
$("p#para1").hide();
$("p#para2").hide();
    $("button#button1").click(function () {
      $("p#para1").slideToggle("slow");
    });
	 $("button#button2").click(function () {
      $("p#para2").slideToggle("slow");
    });
</script>
