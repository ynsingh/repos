<!DOCTYPE html>
<html>
    <head>
        <title>EGURU | HOME</title>
        <link href="<?php echo base_url(); ?>/static/css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="<?php echo base_url(); ?>/static/css/login.css" rel="stylesheet" type="text/css">
        <script src="<?php echo base_url(); ?>/static/js/jquery-1.10.2.js"></script>
        <!--<script src="/static/js/bootstrap.responsive.min.js" ></script>-->
        <script src="<?php echo base_url(); ?>/static/js/bootstrap.min.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/jquery-1.10.2.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/jquery.validate.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/student/questions.js"></script>
    </head>
    <body>
        <?php 
        if (!isset($_SESSION['email']))
            header("Location:/eguru/");
        if($data['all_test_given'])
            header ('Location :/student/profile_student');
        include 'header_login.php';?>
        <form class="well-large" id="questions3" style="margin-left: 120px" name = "form1" method="post" action="/student/test3/">            
        <td align="left" valign="top"><div class="control-group"><p>1.When I start a homework problem, I am more likely to:<br/>
            <input type="radio"  name="preferred_color1" value=1 />
            (a) start working on the solution immediately. <br />
            <input type="radio" name="preferred_color1" value=2 />
            (b) try to fully understand the problem first. <br />
           </div>           
          <p>&nbsp;</p>
         <div class="control-group"> 2. I prefer the idea of:<br />
            <input type="radio" name="preferred_color2" value=1 />
			(a) certainty. <br />
            <input type="radio" name="preferred_color2" value=2 />
			(b) theory. <br />
           </div>     
                    
            <p>&nbsp;</p>
           <div class="control-group"> <p>3. I prefer to study:<br />
                <input type="radio" name="preferred_color3" value=1 />
			(a) in a study group. <br />
              <input type="radio" name="preferred_color3" value=2 />
		(b) alone. <br />
           </div>
              
               </p>
          
          
            <p>&nbsp;</p>
           <div class="control-group"> <p>4. I am more likely to be considered: <br/>
                <input type="radio" name="preferred_color4" value=1 />
              (a) careful about the details of my work.  <br />
              <input type="radio" name="preferred_color4" value=2 />
              (b) creative about how to do my work.  <br />
              
            </p>
          </div>
		  
		     <p>&nbsp;</p>
		<div class="control-group">	 <p>5. i am patient with details,but i am impatient when the details get complicated:<br/>
                <input type="radio" name="preferred_color5" value=1 />
            (a) yes. <br />
            <input type="radio" name="preferred_color5" value=2 />
            	(b) no. <br />
            </p>
           </div> 
		   
		  
            
              <p>&nbsp;</p>
             <div class="control-group"> <p>6. I learn:<br/>
                <input type="radio" name="preferred_color6" value=1 />
			  (a) at a fairly regular pace. If I study hard, I'll "get it.".
             <br />
            <input type="radio" name="preferred_color6" value=2 />
			  (b) in fits and starts. I'll be totally confused and then suddenly it all "clicks.". 
             <br />
                       
              </p>
           </div> 
              
              <p>&nbsp;</p>
            <div class="control-group">  <p>7. When I have to perform a task, I prefer to:<br/>
                <input type="radio" name="preferred_color7" value=1 />
            (a) master one way of doing it. <br />
            <input type="radio" name="preferred_color7" value=2 />
            	(b) come up with new ways of doing it. <br />
            </p>
           </div> 
		   
		     <p>&nbsp;</p>
		 <div class="control-group">   <p>8. When someone is showing me data, I prefer:<br/>
                <input type="radio" name="preferred_color8" value=1 />
            (a) charts or graphs. <br />
            <input type="radio" name="preferred_color8" value=2 />
            	(b) text summarizing the results. <br />
            </p>
           </div> 
		   
		     <p>&nbsp;</p>
                     <div class="control-group">	 <p>9. When writing a paper, I am more likely to:<br/>
                <input type="radio" name="preferred_color9" value=1 />
            (a) work on (think about or write) the beginning of the paper and progress forward. <br />
            <input type="radio" name="preferred_color9" value=2 />
            	(b) work on (think about or write) different parts of the paper and then order them. <br />
            </p>
           </div> 
		   
		     <p>&nbsp;</p>
		<div class="control-group">	 <p>10. When I have to work on a group project, I first want to:<br/>
                <input type="radio" name="preferred_color10" value=1 />
            (a) have "group brainstorming" where everyone contributes ideas.. <br />
            <input type="radio" name="preferred_color10" value=2 />
            	(b) brainstorm individually and then come together as a group to compare ideas. <br />
            </p>
            </div>
		   
		     <p>&nbsp;</p>
			<div class="control-group"> <p>11. When I am learning a new subject, I prefer to:<br/>
                <input type="radio" name="preferred_color11" value=1 />
            (a) stay focused on that subject, learning as much about it as I can. <br />
            <input type="radio" name="preferred_color11" value=2 />
            	(b) try to make connections between that subject and related subjects. <br />
            </p>
            </div>
		   
		     <p>&nbsp;</p>
		<div class="control-group">	 <p>12. I tend to picture places I have been:<br/>
                <input type="radio" name="preferred_color12" value=1 />
            (a) easily and fairly accurately. <br />
            <input type="radio" name="preferred_color12" value=2 />
            	(b) with difficulty and without much detail. <br />
            </p>
            </div>
		   
		     <p>&nbsp;</p>
		<div class="control-group">	 <p>13. When solving problems in a group, I would be more likely to:<br/>
                <input type="radio" name="preferred_color13" value=1 />
            (a) think of the steps in the solution process. <br />
            <input type="radio" name="preferred_color13" value=2 />
            	(b) think of possible consequences or applications of the solution in a wide range of areas. <br />
            </p>
            </div>
		   
		     <p>&nbsp;</p>
			<div class="control-group"> <p>14. do you blog ?:<br/>
                <input type="radio" name="preferred_color14" value=1 />
            (a) yes. <br />
            <input type="radio" name="preferred_color14" value=2 />
            	(b) no. <br />
            </p>
            </div>
		   
		     <p>&nbsp;</p>
			<div class="control-group"> <p>15. do you solve crossword puzzles ?:<br/>
                <input type="radio" name="preferred_color15" value=1 />
            (a) yes. <br />
            <input type="radio" name="preferred_color15" value=2 />
            	(b) no. <br />
            </p>
            </div>
		   
		     <p>&nbsp;</p>
			<div class="control-group"> <p>16. do you try an activity for self improvement ?:<br/>
                <input type="radio" name="preferred_color16" value=1 />
            (a) yes. <br />
            <input type="radio" name="preferred_color16" value=2 />
            	(b) no. <br />
            </p>
            </div>
		   
		     <p>&nbsp;</p>
			<div class="control-group"> <p>17. do you try to build or fix something yourself ?:<br/>
                <input type="radio" name="preferred_color17" value=1 />
            (a) yes. <br />
            <input type="radio" name="preferred_color17" value=2 />
            	(b) no. <br />
            </p>
          </div>
		     <p>&nbsp;</p>
			<div class="control-group"> <p>18. i navigate well in unfamiliar places:<br/>
                <input type="radio" name="preferred_color18" value=1 />
            (a) yes. <br />
            <input type="radio" name="preferred_color18" value=2 />
            	(b) no. <br />
            </p>
            </div>
		   
		     <p>&nbsp;</p>
			 <br/>
	       </p>
          </p>
          <p><input name="submit" type="submit" value="Submit" />&nbsp;</p>
	</form>
