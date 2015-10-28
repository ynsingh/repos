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
        
        <form class="well-large" id="questions1" style="margin-left: 120px" name = "form1" method="post" action="/student/test1/">            
        <p><div class="control-group">1.When I think about what I did yesterday, I am most likely to get:<br/>
            
                <input type="radio" name="preferred_color1" value=1 >
            (a) a picture. <br />
            <input type="radio" name="preferred_color1" value=2 />
            (b) words. <br />
           </div>           
          <p>&nbsp;</p>
          <div class="control-group">2. If I were a teacher, I would rather teach a course:<br />
            <input type="radio" name="preferred_color2" value=1 />
			(a) that deals with facts and real life situations. <br />
            <input type="radio" name="preferred_color2" value=2 />
			(b) that deals with ideas and theories. <br />
             </div>   
                    
            <p>&nbsp;</p>
            <div class="control-group"><p>3. I prefer to get new information in:<br />
                <input type="radio" name="preferred_color3" value=1 />
			(a) pictures, diagrams, graphs, or maps. <br />
              <input type="radio" name="preferred_color3" value=2 />
			(b) written directions or verbal information. <br />
              
               </p>
          </div>
          
            <p>&nbsp;</p>
            <div class="control-group"><p>4. In a study group working on difficult material, I am more likely to: <br/>
                <input type="radio" name="preferred_color4" value=1 />
              (a) sit back and listen.  <br />
              <input type="radio" name="preferred_color4" value=2 />
              (b) jump in and contribute ideas.  <br />
              
            </p>
          </div>
		  
		    <p>&nbsp;</p>
		    <div class="control-group"><p>5. In a book with lots of pictures and charts, I am likely to:<br/>
		        <input type="radio" name="preferred_color5" value=1 />
			  (a) look over the pictures and charts carefully. 
		       <br />
		      <input type="radio" name="preferred_color5" value=2 />
     		  (b) focus on the written text. 
		       <br />
		    </p>
		 </div> 
            
              <p>&nbsp;</p>
              <div class="control-group">
              <p>6. I like teachers<br/>
                <input type="radio" name="preferred_color6" value=1 />
			  (a) who put a lot of diagrams on the board.
             <br />
            <input type="radio" name="preferred_color6" value=2 />
			  (b) who spend a lot of time explaining. 
             <br />
                       
              </p>
            </div>
              
              <p>&nbsp;</p>
              <div class="control-group">
              <p>7. When I get directions to a new place, I prefer:<br/>
                <input type="radio" name="preferred_color7" value=1 />
            (a) a map. <br />
            <input type="radio" name="preferred_color7" value=2 />
            	(b) written instructions. <br />
            </p>
            </div>
		   
		     <p>&nbsp;</p>
		   <div class="control-group">  <p>8. When I see a diagram or sketch in class, I am most likely to remember:<br/>
	            <input type="radio" name="preferred_color8" value=1 />
			(a) the picture.
		       <br />
		       <input type="radio" name="preferred_color8" value=2 />
		    (b) what the instructor said about it.
		        <br />
		         </p>
		   </div>
		     
		     <p>&nbsp;</p>
		 <div class="control-group">    <p>9. When someone is showing me data, I prefer:<br/>
	            <input type="radio" name="preferred_color9" value=1 />
		       (a) charts or graphs. <br />
		       <input type="radio" name="preferred_color9" value=2 />
		       (b) text summarizing the results. <br />
		       
	          </p>
		   </div>
		   
		     <p>&nbsp;</p>
		  <div class="control-group">   <p>10. When considering a body of information, I am more likely to:<br/>
	            <input type="radio" name="preferred_color10" value=1 />
		       (a) focus on details and miss the big picture. <br />
		       <input type="radio" name="preferred_color10" value=2 />
		       (b) try to understand the big picture before getting into the details. <br />
		       </p>
	        </div>
			  
		     <p>&nbsp;</p>
		   <div class="control-group">  <p>11. For entertainment, I would rather:<br/>
	            <input type="radio" name="preferred_color11" value=1 />
		       (a) watch television. <br />
		       <input type="radio" name="preferred_color11" value=2 />
		       	(b) read a book. <br />
		     </p>
		   </div>
		   
			 <p>&nbsp;</p>
			<div class="control-group"> <p>12. I really enjoy books:<br/>
	            <input type="radio" name="preferred_color12" value=1 />
		       (a) yes. <br />
		       <input type="radio" name="preferred_color12" value=2 />
		       	(b) no. <br />
		     </p>
		   
		   </div>
			 <p>&nbsp;</p>
			<div class="control-group"> <p>13. i think that geometry was easier than algebra:<br/>
	            <input type="radio" name="preferred_color13" value=1 />
		       (a) yes. <br />
		       <input type="radio" name="preferred_color13" value=2 />
		       	(b) no. <br />
		     </p>
		   
		   </div>
			 <p>&nbsp;</p>
			<div class="control-group"> <p>14. i prefer video games to board games:<br/>
	            <input type="radio" name="preferred_color14" value=1 />
		       (a) yes. <br />
		       <input type="radio" name="preferred_color14" value=2 />
		       	(b) no. <br />
		     </p>
		   </div>
		   
			 <p>&nbsp;</p>
			<div class="control-group"> <p>15. when out driving or travelling, i notice the words on the ad boards more than scenery:<br/>
	            <input type="radio" name="preferred_color15" value=1 />
		       (a) no. <br />
		       <input type="radio" name="preferred_color15" value=2 />
		       	(b) yes. <br />
		     </p>
		   </div>
		   
			 <p>&nbsp;</p>
			<div class="control-group"> <p>16. i keep a diary to record events of inner life:<br/>
	            <input type="radio" name="preferred_color16" value=1 />
		       (a) no. <br />
		       <input type="radio" name="preferred_color16" value=2 />
		       	(b) yes. <br />
		     </p>
		   </div>
		   
			 <p>&nbsp;</p>
			<div class="control-group"> <p>17. i get more out of reading newspaper than i do out of TV:<br/>
	            <input type="radio" name="preferred_color17" value=1 />
		       (a) no. <br />
		       <input type="radio" name="preferred_color17" value=2 />
		       	(b) yes. <br />
		     </p>
		   
		   </div>
			 <p>&nbsp;</p>
			<div class="control-group"> <p>18. i often use a camcorder to record my surroundings:<br/>
	            <input type="radio" name="preferred_color18" value=1 />
		       (a) yes. <br />
		       <input type="radio" name="preferred_color18" value=2 />
		       	(b) no. <br />
		     </p>
		   </div>
		   
			 <p>&nbsp;</p>
			<div class="control-group"> <p>19. when i close my eyes,i can see clear visual images:<br/>
	            <input type="radio" name="preferred_color19" value=1 />
		       (a) yes. <br />
		       <input type="radio" name="preferred_color19" value=2 />
		       	(b) no. <br />
		     </p>
		   </div>
		   
			 <p>&nbsp;</p>
			 <br/>
	       </p>
          </p>
          <p><input name="submit" type="submit" value="Submit" />&nbsp;</p>
	</form>
