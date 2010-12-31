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
    <div id="content"> <br />
      <div id="box"> 
        <h3 >Software Design</h3>
      </div>
    </div>
  </div>
</div>
<ul>
  <li> 
    <div> 
      <div> 
        <div> 
          <div> 
            <div class="jflow-content-slider"> 
              <div> 
                <div class="slide-wrapper"><strong>Data Transformation: Transformation 
                  of CMS database&#8217;s data to the .xml file using Kettle Tool. 
                  </strong></div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </li>
  <li>
    <div>
      <div>
        <div>
          <div>
            <div class="jflow-content-slider">
              <div>
                <div class="slide-wrapper"><strong>Data Transformation from xml 
                  file to the system schema using kettle</strong></div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </li>
  <li> 
    <div> 
      <div> 
        <div> 
          <div> 
            <div class="jflow-content-slider"> 
              <div> 
                <div class="slide-wrapper"><strong> Visualization : In this level 
                  system will visualize the data by using its own database. For 
                  the pictorial representation of data it will use the chart utility.</strong></div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </li>
  <li> 
    <div> 
      <div> 
        <div> 
          <div> 
            <div class="jflow-content-slider"> 
              <div> 
                <div class="slide-wrapper"><strong> Web Service : Implementation 
                  of a web service which can be used by other users to post their 
                  data to the Visualization system. </strong><br>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </li>
</ul>
<div>
  <div>
    <div>
      <div>
        <div class="jflow-content-slider">
          <div>
            <div class="slide-wrapper"> 
              <p>&nbsp; </p>
              <div class="clear"></div>
            </div>
            <div class="slide-wrapper"> 
              <div class="slide-details"> 
                <h2>System Process Diagram</h2>
                <p>&nbsp;</p>
                <div class="description">
<p>&nbsp;</p>
                </div>
              </div>
              <div class="clear"></div>
            </div>
            <div class="slide-wrapper"> 
              <p><img name="" src="soft1.JPG" width="764" height="640" alt=""></p>
              <p>&nbsp;</p>
              <div class="slide-details"> 
                <p>&nbsp;</p>
                <h2>Help Contents</h2>
                <p>&nbsp;</p>
                <div class="description"> </div>
              </div>
              <div class="clear"></div>
            </div>
            <div class="slide-wrapper"> 
              <div class="slide-details"> 
                <h2>Help Contents</h2>
                <div class="description"> </div>
              </div>
              <div class="clear"></div>
            </div>
            <div class="slide-wrapper"> 
              <div class="slide-details"> 
                <h2>Help Contents</h2>
                <div class="description"> </div>
              </div>
              <div class="clear"></div>
            </div>
          </div>
          <div id="myController"> <span class="jFlowPrev">Prev</span> <span class="jFlowControl">1</span> 
            <span class="jFlowControl">2</span> <span class="jFlowControl">3</span> 
            <span class="jFlowControl">4</span> <span class="jFlowControl">5</span> 
            <span class="jFlowNext">Next</span> </div>
          <div class="clear"></div>
        </div>
      </div>
    </div>
    <g:sideMenu/> </div>
  <g:styleSwitcher/> </div>

</body>
