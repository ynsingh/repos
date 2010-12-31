<head>
	<meta name="layout" content="main" />
	<title>Site Help</title>
        <style>

          #jFlowSlide{ background:#FFFEF7; font-family: Georgia; }
          #myController { font-family: Arial; font-weight: bold; padding:2px 0;  width:738px; background:#E0B225; }
          #myController span.jFlowSelected { background:#43A0D5;margin-right:0px; }

          .slide-wrapper { padding: 5px; }
          .slide-thumbnail { width:300px; float:left; }
          .slide-thumbnail img {max-width:300px; }
          .slide-details { width:290px; float:right; margin-left:10px;}
          .slide-details h2 { font-size:1.5em; font-style: italic; font-weight:normal; line-height: 1; margin:0; }
          .slide-details .description { margin-top:10px; }

          .jFlowControl, .jFlowPrev, .jFlowNext { color:#FFF; cursor:pointer; padding-left:5px; padding-right:5px; padding-top:2px; padding-bottom:2px; }
          .jFlowControl:hover, .jFlowPrev:hover, .jFlowNext:hover { background: #43A0D5; }


        </style>
        <script language="javascript" type="text/javascript" src="../jquery-1.3.2.min.js"></script>
        <script language="javascript" type="text/javascript" src="../jquery.flow.1.2.min.js"></script>
        <script type="text/javascript">
        $(document).ready(function(){
                $("#myController").jFlow({
                        slides: "#slides",
                        controller: ".jFlowControl", // must be class, use . sign
                        slideWrapper : "#jFlowSlide", // must be id, use # sign
                        selectedWrapper: "jFlowSelected",  // just pure text, no sign
                        auto: true,		//auto change slide, default true
                        width: "738px",
                        height: "405px",
                        duration: 400,
                        prev: ".jFlowPrev", // must be class, use . sign
                        next: ".jFlowNext" // must be class, use . sign
                });
        });
        </script>
       </head>

<body>

	<div id="container">

        <div id="wrapper">
            <div id="content">

                <br />
                <div id="box">
                 <h3 >User Guidelines</h3>


                
                 
                 
                 <div class="jflow-content-slider">
		<div id="slides">
			<div class="slide-wrapper">
                                    <p>
                                    <strong>User Registration</strong></p>
                                    <p>
                                    <strong>Users can register into the system using this option. You can register into the system by using your e-mail id and password. Once the administrator approved the registration you can use the system to visualize the student data.</strong></p>
                                    </p>
                                    <p>
                                    <strong>Login:</strong></p>
                                    <p>
                                    <strong>Once Administrator approved your registration you can login the system using your e-mail user id and password.&nbsp; It will redirect to the Home page of the System.</strong></p>
                                    <p>&nbsp;
                                    </p>
                                    <p>
                                    <strong>User Home page</strong></p>
                                    <p>
                                    <strong>Contains two options</strong></p>
                                    <ol>
                                    <li>
                                    <strong>COURSE ANALYTICS -&nbsp; Displays the course wise statistics </strong>
                                    <ul>
                                    <li>
                                    <strong>Summary&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;:&nbsp; Displays summary of the module usage &nbsp;of the whole courses</strong></li>
                                    <li>
                                    <strong>Visit Details&nbsp;&nbsp; &nbsp;&nbsp;:&nbsp; Displays the course wise usage of the system.</strong></li>
                                    <li>
                                    <strong>ADHOC Tools : &nbsp;provides the Drill down reporting facility</strong></li>
                                    </ul>
                                    </li>
                                    </ol>
                                    <p>&nbsp;
                                    </p>
                                    <ol>
                                    <li>
                                    <strong>&nbsp;STUDENT PERFORMANCE- Tracking and Visualizing student activity across the course. Before selecting reports you have to select any&nbsp; of the course</strong>
                                    <ul>
                                    <li>
                                    <strong>Visual Details : Displays Student wise module usage on the basis of selection.</strong></li>
                                    <li>
                                    <strong>Time Utilization : Displays Date wise student activity chart</strong></li>
                                    <li>
                                    <strong>Summary&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : It will display selected students activity of the selected course </strong></li>
                                    </ul>
                                    </li>
                                    </ol>
                                    <p>&nbsp;
                                    </p>

				<div class="clear"></div>
			</div>


			<div class="slide-wrapper">				
				<div class="slide-details">
					
                <h2>Help Contents</h2>
                <p>&nbsp;</p>
					<div class="description">
						
					
                  <p>&nbsp;</p>
                  <p>&nbsp;</p>
                </div>
				</div>
				<div class="clear"></div>
			</div>


			<div class="slide-wrapper">
				<div class="slide-details">
					<h2>Help Contents</h2>
					<div class="description">

					</div>
				</div>
				<div class="clear"></div>
			</div>


			<div class="slide-wrapper">
				<div class="slide-details">
					<h2>Help Contents</h2>
					<div class="description">

					</div>
				</div>
				<div class="clear"></div>
			</div>


			<div class="slide-wrapper">
				<div class="slide-details">
					<h2>Help Contents</h2>
					<div class="description">

					</div>
				</div>
				<div class="clear"></div>
			</div>




			
		</div>

		<div id="myController">
			<span class="jFlowPrev">Prev</span>
			<span class="jFlowControl">1</span>
			<span class="jFlowControl">2</span>
			<span class="jFlowControl">3</span>
			<span class="jFlowControl">4</span>
			<span class="jFlowControl">5</span>
			<span class="jFlowNext">Next</span>
		</div>
		<div class="clear"></div>
	</div>





                </div>
            </div>

            <g:sideMenu/>

      </div>
         <g:styleSwitcher/>
</div>

</body>
